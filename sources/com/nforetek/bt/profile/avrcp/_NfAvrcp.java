package com.nforetek.bt.profile.avrcp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Message;
import com.nforetek.bt.aidl.INfCallbackAvrcp;
import com.nforetek.bt.callback.NfDoCallbackAvrcp;
import com.nforetek.bt.jni.NfJniBundle;
import com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp;
import com.nforetek.bt.profile.NfAvrcpCallbackInterface;
import com.nforetek.bt.profile._NfProfile;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.db.DbHelperAvrcp;
import com.nforetek.util.normal.NfLog;
import com.nforetek.util.normal.NfUtils;
import java.util.Arrays;
import java.util.HashMap;
/* loaded from: classes.dex */
public final class _NfAvrcp extends _NfProfile<INfCallbackAvrcp, NfDoCallbackAvrcp, NfAvrcpCallbackInterface> implements NfJniCallbackInterfaceAvrcp {
    static final int CONNECTION_STATE_CONNECTED = 2;
    static final int CONNECTION_STATE_CONNECTING = 1;
    static final int CONNECTION_STATE_DISCONNECTED = 0;
    static final int CONNECTION_STATE_DISCONNECTING = 3;
    private static final int GET_ELEMENT_EXPIRATION_MS = 1000;
    private static long sLastGetElementTime = 0;
    private DbHelperAvrcp dbHelper;
    private short mGetFolderItems_scopeId;
    private RegisterSongPosThread mRegisterSongPosThread;
    private int mA2dpState = 110;
    private int prevBrowseState = 110;
    private int mBrowseState = 110;
    private String mAddress = NfDef.DEFAULT_ADDRESS;
    private long prevRequestTime = 0;
    private boolean[] mEventRegisterTable = new boolean[13];
    private int mInterval = -1;
    private byte mLastPlaybackState = -1;
    private int mFolderItemCount = 0;
    private int mMediaItemCount = 0;
    private int mMediaPlayerItemCount = 0;
    private byte mFakePlayStatus = 0;
    boolean sqlTransaction = true;
    private HashMap<String, Integer> mVersionDictionary = new HashMap<>();

    private void setEventRegisted(int e, boolean b) {
        if (e >= this.mEventRegisterTable.length || e < 0) {
            NfLog.e(this.TAG, "setEventRegisted out of range. index: " + e);
        } else {
            this.mEventRegisterTable[e] = b;
        }
    }

    private boolean isEventRegisted(byte event) {
        if (event < this.mEventRegisterTable.length || event >= 0) {
            return this.mEventRegisterTable[event];
        }
        NfLog.e(this.TAG, "isEventRegisted out of range. index: " + ((int) event));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RegisterSongPosThread extends Thread {
        private boolean isRegistered = false;
        private int mThreadInterval;

        public RegisterSongPosThread(int interval) {
            this.mThreadInterval = 0;
            NfLog.v(_NfAvrcp.this.TAG, "RegisterSongPosThread(" + hashCode() + ") interval: " + interval);
            this.mThreadInterval = interval;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            NfLog.v(_NfAvrcp.this.TAG, "RegisterSongPosThread(" + hashCode() + ") started.");
            while (this.isRegistered) {
                if (isStreaming()) {
                    _NfAvrcp.this.jni().registerAvrcpEvent((byte) 5, this.mThreadInterval);
                }
                try {
                    Thread.sleep((this.mThreadInterval * 1000) + 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            NfLog.v(_NfAvrcp.this.TAG, "RegisterSongPosThread(" + hashCode() + ") finished.");
            _NfAvrcp.this.mRegisterSongPosThread = null;
        }

        public void setRegisteredState(boolean register) {
            NfLog.v(_NfAvrcp.this.TAG, "RegisterSongPosThread(" + hashCode() + ") setRegisteredState: " + register);
            this.isRegistered = register;
            interrupt();
        }

        private boolean isStreaming() {
            return _NfAvrcp.this.getA2dpState() == 150;
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfAvrcp";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return 3;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.profile._NfProfile
    public void onCreate() {
        super.onCreate();
        this.mAddress = NfDef.DEFAULT_ADDRESS;
        this.dbHelper = new DbHelperAvrcp(context());
        clearTable((short) 0);
        clearTable((short) 1);
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
        super.forceResetState();
    }

    private void setBrowseState(String address, int state) {
        this.prevBrowseState = this.mBrowseState;
        this.mBrowseState = state;
        manager().onAvrcpBrowseStateChanged(address, this.prevBrowseState, this.mBrowseState);
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
        manager().onAvrcpStateChanged(address, prevState, state);
        callback().onAvrcpStateChanged(address, prevState, state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getA2dpState() {
        return this.mA2dpState;
    }

    public int getBrowseState() {
        return this.mBrowseState;
    }

    public boolean isConnected() {
        return getProfileState() >= 140;
    }

    public boolean isConnecting() {
        return getProfileState() == 120;
    }

    public boolean connect(String address) {
        NfLog.v(this.TAG, "connect() " + address);
        if (getProfileState(address) != 110) {
            NfLog.e(this.TAG, "State is " + getProfileState() + ". Reject connect command.");
            return false;
        } else if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        } else if (NfPrimitive.isAddressValid(address)) {
            if (NfPrimitive.isDiscovering()) {
                NfLog.e(this.TAG, "Before connect, stop scan");
                NfPrimitive.startScan(false);
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!NfPrimitive.isAddressValid(address) || address.equals(NfDef.DEFAULT_ADDRESS)) {
                NfLog.e(this.TAG, "address is not valid. Reject connect command. address = " + address);
                return false;
            }
            return jni().reqAvrcpConnect(address);
        } else {
            return false;
        }
    }

    public boolean disconnect(String address) {
        NfLog.v(this.TAG, "disconnect() " + address);
        if (NfPrimitive.isAddressValid(address)) {
            if (jni() == null) {
                NfLog.e(this.TAG, "jni interface is null. return here.");
                return false;
            }
            if (getBrowseState() > 110) {
                jni().reqAvrcpBrowseDisconnect(address);
            }
            if (getProfileState(address) <= 110 || getProfileState() == 125) {
                NfLog.e(this.TAG, "State is " + getProfileState() + ". Reject disconnect command.");
                return false;
            }
            return jni().reqAvrcpDisconnect(address);
        }
        return false;
    }

    public boolean play() {
        NfLog.v(this.TAG, "play()");
        if (getProfileState() == 140 && checkPrevRequestTime()) {
            return jni().reqAvrcpPlay();
        }
        return false;
    }

    public boolean stop() {
        NfLog.v(this.TAG, "stop()");
        if (getProfileState() == 140 && checkPrevRequestTime()) {
            return jni().reqAvrcpStop();
        }
        return false;
    }

    public boolean pause() {
        NfLog.v(this.TAG, "pause()");
        if (getProfileState() == 140 && checkPrevRequestTime()) {
            return jni().reqAvrcpPause();
        }
        return false;
    }

    public boolean forward() {
        NfLog.v(this.TAG, "forward()");
        if (getProfileState() == 140 && checkPrevRequestTime()) {
            return jni().reqAvrcpForward();
        }
        return false;
    }

    public boolean backward() {
        NfLog.v(this.TAG, "backward()");
        if (getProfileState() == 140 && checkPrevRequestTime()) {
            return jni().reqAvrcpBackward();
        }
        return false;
    }

    public boolean volumeUp() {
        NfLog.v(this.TAG, "volumeUp()");
        if (getProfileState() == 140 && checkPrevRequestTime()) {
            return jni().reqAvrcpVolumeUp();
        }
        return false;
    }

    public boolean volumeDown() {
        NfLog.v(this.TAG, "volumeDown()");
        if (getProfileState() == 140 && checkPrevRequestTime()) {
            return jni().reqAvrcpVolumeDown();
        }
        return false;
    }

    public boolean startFastForward() {
        NfLog.v(this.TAG, "startFastForward()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().reqAvrcpStartFastForward();
    }

    public boolean stopFastForward() {
        NfLog.v(this.TAG, "stopFastForward()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().reqAvrcpStopFastForward();
    }

    public boolean startRewind() {
        NfLog.v(this.TAG, "startRewind()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().reqAvrcpStartRewind();
    }

    public boolean stopRewind() {
        NfLog.v(this.TAG, "stopRewind()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().reqAvrcpStopRewind();
    }

    public boolean getCapabilities() {
        NfLog.v(this.TAG, "getCapabilities()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().getAvrcpCapabilitySupportEvent();
    }

    public boolean getAttributesList() {
        NfLog.v(this.TAG, "getAttributesList()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().getAvrcpPlayerSettingAttributesList();
    }

    public boolean getValuesList(byte attributeId) {
        NfLog.v(this.TAG, "getValuesList()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().getAvrcpPlayerSettingValuesList(attributeId);
    }

    public boolean getValues() {
        NfLog.v(this.TAG, "getValues()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().getAvrcpPlayerSettingValues();
    }

    public boolean getValue(byte attributeId, byte valueId) {
        NfLog.v(this.TAG, "getValue()");
        if (getProfileState() != 140) {
        }
        return false;
    }

    public boolean setValue(byte attributeId, byte valueId) {
        NfLog.v(this.TAG, "setValue()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().setAvrcpPlayerSettingValue(attributeId, valueId);
    }

    public boolean getElementAttributes() {
        NfLog.v(this.TAG, "getElementAttributes()");
        if (getProfileState() != 140) {
            return false;
        }
        if (sLastGetElementTime + 1000 > System.currentTimeMillis()) {
            NfLog.d(this.TAG, "Reject : Too frequent, sLastGetElementTime = " + sLastGetElementTime + " currentTimeMillis = " + System.currentTimeMillis());
            return false;
        }
        sLastGetElementTime = System.currentTimeMillis();
        return jni().getAvrcpElementAttributes();
    }

    public boolean getPlayStatus() {
        NfLog.v(this.TAG, "getPlayStatus()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().getAvrcpPlayStatus();
    }

    public boolean registerEvent(byte eventId, int interval) {
        NfLog.v(this.TAG, "registerEvent() eventId: " + ((int) eventId) + " interval: " + interval);
        if (getProfileState() != 140) {
            return false;
        }
        if (eventId == 5) {
            if (interval > 0) {
                this.mInterval = interval;
                if (this.mRegisterSongPosThread == null) {
                    this.mRegisterSongPosThread = new RegisterSongPosThread(interval);
                    this.mRegisterSongPosThread.setRegisteredState(true);
                    this.mRegisterSongPosThread.start();
                    return true;
                }
                return false;
            }
            NfLog.e(this.TAG, "invalid interval: " + interval);
            return false;
        }
        return jni().registerAvrcpEvent(eventId, interval);
    }

    public boolean unregisterEvent(byte eventId) {
        NfLog.v(this.TAG, "unregisterEvent() eventId: " + ((int) eventId));
        if (getProfileState() != 140) {
            return false;
        }
        setEventRegisted(eventId, false);
        if (eventId == 1) {
            this.mLastPlaybackState = (byte) -1;
        }
        if (eventId == 5) {
            if (this.mRegisterSongPosThread != null) {
                this.mRegisterSongPosThread.setRegisteredState(false);
                return true;
            }
            return false;
        }
        return jni().unregisterAvrcpEvent(eventId);
    }

    public boolean nextGroup() {
        NfLog.v(this.TAG, "nextGroup()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().reqAvrcpNextGroup();
    }

    public boolean previousGroup() {
        NfLog.v(this.TAG, "previousGroup()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().reqAvrcpPreviousGroup();
    }

    public boolean getAttributeText(byte attributeId) {
        NfLog.v(this.TAG, "getAttributeText()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().getAvrcpPlayerSettingAttributeText(attributeId);
    }

    public boolean getValueText(byte attributeId, byte valueId) {
        NfLog.v(this.TAG, "getValueText()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().getAvrcpPlayerSettingValueText(attributeId, valueId);
    }

    public boolean continuingResponse() {
        NfLog.v(this.TAG, "continuingResponse()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().reqAvrcpContinuingResponse();
    }

    public boolean abortContinuingResponse() {
        NfLog.v(this.TAG, "abortContinuingResponse()");
        if (getProfileState() != 140) {
            return false;
        }
        return jni().abortAvrcpContinuingResponse();
    }

    public boolean reqAvrcpQueryVersion(String address) {
        NfLog.v(this.TAG, "reqAvrcpQueryVersion()");
        return jni().reqAvrcpQueryVersion(address);
    }

    public boolean browseConnect(String address) {
        NfLog.v(this.TAG, "browseConnect()");
        return jni().reqAvrcpBrowseConnect(address);
    }

    public boolean browseDisconnect(String address) {
        NfLog.v(this.TAG, "browseDisconnect()");
        return jni().reqAvrcpBrowseDisconnect(address);
    }

    public boolean getFolderItem(byte scope) {
        NfLog.v(this.TAG, "getFolderItem()");
        if (scope == 0) {
            clearTable((short) 0);
            clearTable((short) 1);
        } else {
            clearTable(scope);
        }
        this.mGetFolderItems_scopeId = scope;
        return jni().reqAvrcpGetFolderItem(scope);
    }

    public boolean changePath(byte updown, long uid) {
        NfLog.v(this.TAG, "changePath()");
        return jni().reqAvrcpChangePath(updown, uid);
    }

    public boolean setBrowsedPlayer(int player) {
        NfLog.v(this.TAG, "setBrowsedPlayer()");
        return jni().reqAvrcpSetBrowsedPlayer(player);
    }

    public boolean playItem(long item, int uid) {
        NfLog.v(this.TAG, "playItem()");
        return jni().reqAvrcpPlayItem(item, uid);
    }

    public boolean addToNowPlaying(long item, int uid) {
        NfLog.v(this.TAG, "addToNowPlaying()");
        return jni().reqAvrcpAddToNowPlaying(item, uid);
    }

    public boolean searchString(String data) {
        NfLog.v(this.TAG, "searchString()");
        return jni().reqAvrcpSearchString(data);
    }

    public boolean getItemAttributes(byte scope, long item, int uid) {
        NfLog.v(this.TAG, "getItemAttributes()");
        return jni().reqAvrcpGetItemAttributes(scope, item, uid);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void onJniAvrcpStateChanged(int newState, byte[] address) {
        NfLog.v(this.TAG, "onJniAvrcpStateChanged() " + NfUtils.getAddressStringFromByte(address) + " state: " + newState);
        if (newState >= 140 && !getConnectedAddress().equals(NfUtils.getAddressStringFromByte(address))) {
            disconnect(NfUtils.getAddressStringFromByte(address));
        }
        setState(NfUtils.getAddressStringFromByte(address), getNfDefState(newState));
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void onJniAvrcpRetCapabilitiesSupportEvent(int count, byte[] events) {
        NfLog.v(this.TAG, "onJniAvrcpRetCapabilitiesSupportEvent() count: " + count);
        byte[] result = new byte[count];
        Arrays.fill(result, (byte) 0);
        if (count != events.length) {
            NfLog.e(this.TAG, "count didn't match events length! length is " + events.length);
        }
        for (int i = 0; i < events.length; i++) {
            NfLog.v(this.TAG, "events[" + i + "]: " + Integer.toHexString(events[i] & NfDef.AVRCP_PLAYING_STATUS_ID_ERROR));
            if (i < count) {
                result[i] = events[i];
            }
        }
        if (callback() == null) {
            NfLog.e(this.TAG, "Callback is null!!");
        } else {
            callback().retAvrcp13CapabilitiesSupportEvent(result);
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void onJniAvrcpRegisterEventWatcherSuccess(byte eventId) {
        NfLog.v(this.TAG, "onJniAvrcpRegisterEventWatcherSuccess() 0x" + Integer.toHexString(eventId & NfDef.AVRCP_PLAYING_STATUS_ID_ERROR));
        setEventRegisted(eventId, true);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged(byte eventId, byte playbackState) {
        NfLog.v(this.TAG, "onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged() 0x" + Integer.toHexString(eventId & NfDef.AVRCP_PLAYING_STATUS_ID_ERROR) + " PlaybackStatus: " + ((int) playbackState));
        setEventRegisted(eventId, true);
        this.mLastPlaybackState = playbackState;
        if (updateFakePlayStatus(playbackState)) {
            callback().onAvrcp13EventPlaybackStatusChanged(getFakePlayStatus());
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void onJniAvrcpRegisterEventCallbackPlaybackStatusChanged(byte playbackState) {
        NfLog.v(this.TAG, "onJniAvrcpRegisterEventCallbackPlaybackStatusChanged() " + ((int) playbackState));
        this.mLastPlaybackState = playbackState;
        registerEvent((byte) 1, -1);
        if (updateFakePlayStatus(playbackState)) {
            callback().onAvrcp13EventPlaybackStatusChanged(getFakePlayStatus());
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void onJniAvrcpRegisterEventCallbackTrackChanged(byte[] uid) {
        NfLog.v(this.TAG, "onJniAvrcpRegisterEventCallbackTrackChanged()");
        registerEvent((byte) 2, -1);
        long elementId = 0;
        for (byte b : uid) {
            elementId = (elementId << 8) + (b & NfDef.AVRCP_PLAYING_STATUS_ID_ERROR);
        }
        callback().onAvrcp13EventTrackChanged(elementId);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void onJniAvrcpRegisterEventCallbackPlaybackPosChanged(int songPos) {
        NfLog.v(this.TAG, "onJniAvrcpRegisterEventCallbackPlaybackPosChanged() " + songPos);
        if (songPos >= 0) {
            callback().onAvrcp13EventPlaybackPosChanged(songPos);
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void onJniAvrcpRegisterEventCallbackSettingChanged(int count, byte[] attr, byte[] value) {
        NfLog.v(this.TAG, "onJniAvrcpRegisterEventCallbackSettingChanged()");
        byte[] a = new byte[count];
        byte[] v = new byte[count];
        for (int i = 0; i < count; i++) {
            a[i] = attr[i];
            v[i] = value[i];
        }
        registerEvent((byte) 8, -1);
        callback().onAvrcp13EventPlayerSettingChanged(a, v);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void onJniAvrcpGetElementAttributes(int[] metadataAtrributeIds, String[] texts) {
        NfLog.v(this.TAG, "onJniAvrcpGetElementAttributes()");
        sLastGetElementTime = 0L;
        for (int i = 0; i < metadataAtrributeIds.length; i++) {
            switch (metadataAtrributeIds[i]) {
                case 7:
                    NfLog.i(this.TAG, "Song length: " + texts[i]);
                    try {
                        Integer.parseInt(texts[i]);
                        break;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        NfLog.d(this.TAG, "Set it to -1");
                        texts[i] = "-1";
                        break;
                    }
            }
        }
        callback().retAvrcp13ElementAttributesPlaying(metadataAtrributeIds, texts);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp13PlayerSettingAttributesList(byte[] attributeIds) {
        NfLog.v(this.TAG, "retJniAvrcp13PlayerSettingAttributesList()");
        for (int i = 0; i < attributeIds.length; i++) {
            NfLog.e(this.TAG, "Piggy Check attr id: " + ((int) attributeIds[i]));
        }
        callback().retAvrcp13PlayerSettingAttributesList(attributeIds);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp13PlayerSettingValuesList(byte attributeId, byte[] valueIds) {
        NfLog.v(this.TAG, "retJniAvrcp13PlayerSettingValuesList()");
        NfLog.e(this.TAG, "Piggy Check attr id: " + ((int) attributeId));
        for (int i = 0; i < valueIds.length; i++) {
            NfLog.e(this.TAG, "Piggy Check value: " + ((int) valueIds[i]));
        }
        if (callback() == null) {
            NfLog.e(this.TAG, "Callback is null!!");
        } else {
            callback().retAvrcp13PlayerSettingValuesList(attributeId, valueIds);
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp13PlayerSettingCurrentValues(byte[] attributeIds, byte[] valueIds) {
        NfLog.v(this.TAG, "retJniAvrcp13PlayerSettingCurrentValues()");
        callback().retAvrcp13PlayerSettingCurrentValues(attributeIds, valueIds);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp13SetPlayerSettingValueSuccess(int reason) {
        NfLog.v(this.TAG, "retJniAvrcp13SetPlayerSettingValueSuccess() reason: " + reason);
        if (reason == 4) {
            if (callback() == null) {
                NfLog.e(this.TAG, "Callback is null!!");
            } else {
                callback().retAvrcp13SetPlayerSettingValueSuccess();
            }
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp13PlayStatus(long songLen, long songPos, byte statusId) {
        NfLog.v(this.TAG, "retJniAvrcp13PlayStatus() statusId: " + ((int) statusId));
        if (callback() == null) {
            NfLog.e(this.TAG, "Callback is null!!");
            return;
        }
        updateFakePlayStatus(statusId);
        callback().retAvrcp13PlayStatus(songLen, songPos, getFakePlayStatus());
        if (this.mLastPlaybackState != -1 && isEventRegisted((byte) 1) && this.mLastPlaybackState != statusId) {
            NfLog.e(this.TAG, "Piggy Check maybe play status event lost packet, re-register here.");
            registerEvent((byte) 1, -1);
            this.mLastPlaybackState = statusId;
        }
    }

    private static int getNfDefState(int state) {
        switch (state) {
            case 0:
                return 110;
            case 1:
                return 120;
            case 2:
                return 140;
            case 3:
                return NfDef.STATE_DISCONNECTING;
            default:
                return -1;
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcpQueryVersion(int version) {
        NfLog.v(this.TAG, "retJniAvrcpQueryVersion() version: " + version);
    }

    private void setVersionDictionary(String address, int version) {
        if (this.mVersionDictionary.containsKey(address)) {
            this.mVersionDictionary.remove(address);
        }
        this.mVersionDictionary.put(address, Integer.valueOf(version));
    }

    public int getVersionFromDictionary(String address) {
        if (!this.mVersionDictionary.containsKey(address)) {
            return -1;
        }
        int v = this.mVersionDictionary.get(address).intValue();
        return v;
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void onJniAvrcpBrowseStateChanged(int newState, byte[] address) {
        NfLog.v(this.TAG, "onJniAvrcpBrowseStateChanged() " + NfUtils.getAddressStringFromByte(address) + " state: " + newState);
        setBrowseState(NfUtils.getAddressStringFromByte(address), getNfDefState(newState));
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp14FolderItem(byte status, byte pdu, byte[] uidCounter, int count, byte[] itemType, long[] folder, byte[] folderType, byte[] isPlayable, char[] charset, String[] name, boolean isFinal) {
        NfLog.v(this.TAG, "retJniAvrcp14FolderItem() 0x" + Integer.toHexString(status & NfDef.AVRCP_PLAYING_STATUS_ID_ERROR));
        if (this.mFolderItemCount == 0) {
            clearTable((short) 1);
        }
        if (status == 4 && !isFinal) {
            this.mFolderItemCount += count;
            for (int i = 0; i < count; i++) {
                ContentValues values = new ContentValues();
                values.put("UIDcounter", (Integer) 0);
                values.put("UID", Long.valueOf(folder[i]));
                values.put("FolderType", Byte.valueOf(itemType[i]));
                values.put("IsPlayable", Byte.valueOf(isPlayable[i]));
                values.put("Name", name[i]);
                this.dbHelper.getClass();
                boolean result = insertIntoTable("FolderItems", values);
                this.sqlTransaction = this.sqlTransaction && result;
            }
            return;
        }
        callback().retAvrcp14FolderItems(0, this.mFolderItemCount);
        this.mFolderItemCount = 0;
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp14MediaItem(byte status, byte pdu, byte[] uidCounter, int count, byte[] itemType, long[] folder, byte[] mediaType, char[] charset, String[] name, boolean isFinal) {
        NfLog.v(this.TAG, "retJniAvrcp14MediaItem() 0x" + Integer.toHexString(status & NfDef.AVRCP_PLAYING_STATUS_ID_ERROR) + " isFinal: " + isFinal);
        NfLog.v(this.TAG, "mMediaItemCount: " + this.mMediaItemCount);
        if (this.mMediaItemCount == 0) {
            clearTable((short) 1);
        }
        if (status == 4 && !isFinal) {
            this.mMediaItemCount += count;
            for (int i = 0; i < count; i++) {
                ContentValues values = new ContentValues();
                values.put("UIDcounter", (Integer) 0);
                values.put("UID", Long.valueOf(folder[i]));
                values.put("MediaType", Byte.valueOf(mediaType[i]));
                values.put("Name", name[i]);
                NfLog.d("scope_id", new StringBuilder().append((int) this.mGetFolderItems_scopeId).toString());
                values.put("ScopeId", Short.valueOf(this.mGetFolderItems_scopeId));
                this.dbHelper.getClass();
                boolean result = insertIntoTable("MediaItems", values);
                this.sqlTransaction = this.sqlTransaction && result;
            }
            return;
        }
        callback().retAvrcp14MediaItems(0, this.mMediaItemCount);
        this.mMediaItemCount = 0;
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp14MediaItemElementAttribute(byte[] uidCounter, long uid, int attribute_id, int charset, String name) {
        NfLog.v(this.TAG, "retJniAvrcp14MediaItemElementAttribute()");
        NfLog.v(this.TAG, "UID: " + uid);
        NfLog.v(this.TAG, "Id: " + attribute_id);
        NfLog.v(this.TAG, "charset: " + charset);
        NfLog.v(this.TAG, "name: " + name);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp14MediaPlayerItem(byte status, byte pdu, byte[] uidCounter, int count, byte[] itemType, char[] playerId, byte[] majorType, int[] subType, byte[] playStatus, char[] charset, String[] name, boolean isFinal) {
        NfLog.v(this.TAG, "retJniAvrcp14MediaPlayerItem() 0x" + Integer.toHexString(status & NfDef.AVRCP_PLAYING_STATUS_ID_ERROR));
        if (this.mMediaPlayerItemCount == 0) {
            clearTable((short) 0);
        }
        if (status == 4 && !isFinal) {
            this.mMediaPlayerItemCount += count;
            for (int i = 0; i < count; i++) {
                NfLog.e(this.TAG, "Piggy Check UIDcounter: 0");
                NfLog.e(this.TAG, "Piggy Check PlayerId: " + ((int) ((short) playerId[i])));
                NfLog.e(this.TAG, "Piggy Check MajorPlayerType: " + ((int) majorType[i]));
                NfLog.e(this.TAG, "Piggy Check PlayerSubType: " + subType[i]);
                NfLog.e(this.TAG, "Piggy Check PlayStatus: " + ((int) playStatus[i]));
                NfLog.e(this.TAG, "Piggy Check Name: " + name[i]);
                ContentValues values = new ContentValues();
                values.put("UIDcounter", (Integer) 0);
                values.put("PlayerId", Short.valueOf((short) playerId[i]));
                values.put("MajorPlayerType", Byte.valueOf(majorType[i]));
                values.put("PlayerSubType", Integer.valueOf(subType[i]));
                values.put("PlayStatus", Byte.valueOf(playStatus[i]));
                values.put("Name", name[i]);
                this.dbHelper.getClass();
                boolean result = insertIntoTable("MediaPlayerItems", values);
                this.sqlTransaction = this.sqlTransaction && result;
            }
            return;
        }
        callback().retAvrcp14FolderItems(0, this.mMediaPlayerItemCount);
        this.mMediaPlayerItemCount = 0;
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp14ChangePath(byte status, int itemNum) {
        NfLog.v(this.TAG, "retJniAvrcp14ChangePath() 0x" + Integer.toHexString(status) + " count: " + itemNum);
        if (status == 4) {
            callback().retAvrcp14ChangePathSuccess(itemNum);
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp14SetBrowsedPlayer(byte status, byte pdu, byte[] uidCounter, int count, char charset, byte depth, String[] name) {
        NfLog.v(this.TAG, "retJniAvrcp14SetBrowsedPlayer()");
        if (status == 4) {
            if (depth == 1) {
                name = new String[depth];
                name[0] = NfPrimitive.getRemoteDeviceName(getConnectedAddress());
            }
            callback().retAvrcp14SetBrowsedPlayerSuccess(name, 0, count);
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp14PlayItem(byte status) {
        NfLog.v(this.TAG, "retJniAvrcp14PlayItem()");
        if (status == 4) {
            callback().retAvrcp14PlaySelectedItemSuccess();
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp14AddToNowPlaying(byte status) {
        NfLog.v(this.TAG, "retJniAvrcp14GetItemAttributes()");
        if (status == 4) {
            callback().retAvrcp14AddToNowPlayingSuccess();
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp14GetItemAttributes(byte status, byte pdu, int count, byte[] attrId, char[] charset, String[] attrValue) {
        NfLog.v(this.TAG, "retJniAvrcp14GetItemAttributes()");
        if (status == 4) {
            int[] attrs = new int[attrId.length];
            for (int i = 0; i < attrId.length; i++) {
                attrs[i] = attrId[i] & NfDef.AVRCP_PLAYING_STATUS_ID_ERROR;
            }
            callback().retAvrcp14ItemAttributes(attrs, attrValue);
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceAvrcp
    public void retJniAvrcp14SearchString(byte status, int num) {
        NfLog.v(this.TAG, "retJniAvrcp14SearchString()");
        if (status == 4) {
            callback().retAvrcp14SearchResult(0, num);
        }
    }

    public boolean insertIntoTable(String tableName, ContentValues values) {
        NfLog.d(this.TAG, "insertIntoTable()");
        SQLiteDatabase sqlDatabase = null;
        try {
            sqlDatabase = this.dbHelper.getWritableDatabase();
            sqlDatabase.beginTransaction();
            if (values != null && values.size() > 0) {
                sqlDatabase.insert(tableName, null, values);
            }
            sqlDatabase.setTransactionSuccessful();
            sqlDatabase.endTransaction();
            return true;
        } catch (Exception e) {
            NfLog.e(this.TAG, "In insertIntoTable() : Exception on doing database access e: " + e);
            e.printStackTrace();
            return false;
        } finally {
            sqlDatabase.close();
        }
    }

    public boolean updateTable(String tableName, ContentValues values, long uid, String path, short scope) {
        NfLog.d(this.TAG, " +++ Begin of updateTable() +++ ");
        SQLiteDatabase sqlDatabase = null;
        try {
            sqlDatabase = this.dbHelper.getWritableDatabase();
            sqlDatabase.beginTransaction();
            if (values != null && values.size() > 0) {
                sqlDatabase.update(tableName, values, "ScopeId=? and UID=?", new String[]{new StringBuilder().append((int) scope).toString(), new StringBuilder().append(uid).toString()});
            }
            sqlDatabase.setTransactionSuccessful();
            sqlDatabase.endTransaction();
            return true;
        } catch (Exception e) {
            NfLog.e(this.TAG, "In updateTable() : Exception on doing database access e: " + e);
            e.printStackTrace();
            return false;
        } finally {
            sqlDatabase.close();
        }
    }

    public void clearTable(short scope_id) {
        NfLog.d(this.TAG, "clearTable(" + ((int) scope_id) + ") ");
        String sql = "";
        try {
            if (scope_id == 0) {
                sql = this.dbHelper.clearMediaPlayerItems;
            } else if (scope_id == 1) {
                sql = this.dbHelper.clearFolderItems;
            } else if (scope_id == 2 || scope_id == 3) {
                sql = String.valueOf(this.dbHelper.clearMediaItemsByScopeId) + ((int) scope_id) + ";";
            }
            NfLog.v(this.TAG, "Piggy Check sql = " + sql);
            SQLiteDatabase sqlDatabase = this.dbHelper.getWritableDatabase();
            sqlDatabase.execSQL(sql);
            if (scope_id == 1) {
                sqlDatabase.execSQL(this.dbHelper.clearMediaItems);
            }
            sqlDatabase.close();
        } catch (Exception e) {
            NfLog.e(this.TAG, "In updateTable() : Exception on doing database access e: " + e);
            e.printStackTrace();
        }
    }

    private boolean checkPrevRequestTime() {
        if (System.currentTimeMillis() - this.prevRequestTime <= NfConfig.getProtectTimeAvrcp10Command()) {
            NfLog.e(this.TAG, "In checkPrevRequestTime() : REJECT !!! Request command is too frequent !!!");
            NfLog.e(this.TAG, "System Time : " + System.currentTimeMillis() + " prevRequestTime Time : " + this.prevRequestTime);
            return false;
        }
        this.prevRequestTime = System.currentTimeMillis();
        return true;
    }

    public void onA2dpStateChanged(String address, int state) {
        NfLog.v(this.TAG, "onA2dpStateChanged() " + state);
        this.mA2dpState = state;
        if (state == 110) {
            CheckAvrcpStateThread t = new CheckAvrcpStateThread(address);
            t.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.profile._NfProfile
    public void onSystemTimeChanged() {
        NfLog.v(this.TAG, "onSystemTimeChanged()");
        this.prevRequestTime = 0L;
        sLastGetElementTime = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CheckAvrcpStateThread extends Thread {
        private String mAddress;

        public CheckAvrcpStateThread(String address) {
            this.mAddress = address;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(500L);
                NfLog.e(_NfAvrcp.this.TAG, "KC check if AVRCP state is disconnected after 1sec!");
                if (_NfAvrcp.this.getProfileState() >= 140 && _NfAvrcp.this.getA2dpState() == 110) {
                    _NfAvrcp.this.disconnect(this.mAddress);
                }
            } catch (Exception e) {
            }
        }
    }

    private boolean updateFakePlayStatus(byte status) {
        NfLog.v(this.TAG, "updateFakePlayStatus() " + ((int) status));
        boolean result = false;
        if (this.mFakePlayStatus != status) {
            this.mFakePlayStatus = status;
            result = true;
        }
        NfLog.v(this.TAG, "updateFakePlayStatus() mFakePlayStatus: " + ((int) this.mFakePlayStatus));
        return result;
    }

    private byte getFakePlayStatus() {
        return this.mFakePlayStatus;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void dequeueMessage(Message msg) {
        NfJniBundle b = (NfJniBundle) msg.obj;
        switch (msg.what) {
            case 1:
                NfLog.v(this.TAG, "onJniAvrcpStateChanged() " + NfUtils.getAddressStringFromByte(b.getByteArray1()) + " state: " + b.getInt1());
                onJniAvrcpStateChanged(b.getInt1(), b.getByteArray1());
                return;
            case 3:
                NfLog.v(this.TAG, "onJniAvrcpRetCapabilitiesSupportEvent() count: " + b.getInt1());
                onJniAvrcpRetCapabilitiesSupportEvent(b.getInt1(), b.getByteArray1());
                return;
            case 4:
                NfLog.v(this.TAG, "onJniAvrcpRegisterEventWatcherSuccess()");
                onJniAvrcpRegisterEventWatcherSuccess(b.getByte1());
                return;
            case 5:
                NfLog.v(this.TAG, "onJniAvrcpRegisterEventCallbackPlaybackStatusChanged()");
                onJniAvrcpRegisterEventCallbackPlaybackStatusChanged(b.getByte1());
                return;
            case 6:
                NfLog.v(this.TAG, "onJniAvrcpRegisterEventCallbackTrackChanged()");
                onJniAvrcpRegisterEventCallbackTrackChanged(b.getByteArray1());
                return;
            case 7:
                NfLog.v(this.TAG, "onJniAvrcpRegisterEventCallbackPlaybackPosChanged()");
                onJniAvrcpRegisterEventCallbackPlaybackPosChanged(b.getInt1());
                return;
            case 8:
                NfLog.v(this.TAG, "onJniAvrcpRegisterEventCallbackSettingChanged()");
                onJniAvrcpRegisterEventCallbackSettingChanged(b.getInt1(), b.getByteArray1(), b.getByteArray2());
                return;
            case 9:
                NfLog.v(this.TAG, "onJniAvrcpGetElementAttributes() in Handler. cause: " + b.getInt2());
                onJniAvrcpGetElementAttributes(b.getIntArray1(), b.getStringArray1());
                return;
            case 10:
                NfLog.v(this.TAG, "retJniAvrcp13PlayerSettingAttributesList()");
                retJniAvrcp13PlayerSettingAttributesList(b.getByteArray1());
                return;
            case 11:
                NfLog.v(this.TAG, "retJniAvrcp13PlayerSettingValuesList()");
                retJniAvrcp13PlayerSettingValuesList(b.getByte1(), b.getByteArray1());
                return;
            case 12:
                NfLog.v(this.TAG, "retJniAvrcp13PlayerSettingCurrentValues()");
                retJniAvrcp13PlayerSettingCurrentValues(b.getByteArray1(), b.getByteArray2());
                return;
            case 13:
                NfLog.v(this.TAG, "retJniAvrcp13SetPlayerSettingValueSuccess()");
                retJniAvrcp13SetPlayerSettingValueSuccess(b.getInt1());
                return;
            case 14:
                NfLog.v(this.TAG, "retJniAvrcp13PlayStatus()");
                retJniAvrcp13PlayStatus(b.getLong1(), b.getLong2(), b.getByte1());
                return;
            case 15:
                NfLog.v(this.TAG, "retJniAvrcpQueryVersion()");
                retJniAvrcpQueryVersion(b.getInt1());
                return;
            case 17:
                NfLog.v(this.TAG, "onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged()");
                onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged(b.getByte1(), b.getByte2());
                return;
            case 201:
                NfLog.v(this.TAG, "onJniAvrcpBrowseStateChanged() " + NfUtils.getAddressStringFromByte(b.getByteArray1()) + " state: " + b.getInt1());
                onJniAvrcpBrowseStateChanged(b.getInt1(), b.getByteArray1());
                return;
            case 202:
                NfLog.v(this.TAG, "retAvrcp14FolderItem()");
                retJniAvrcp14FolderItem(b.getByte1(), b.getByte2(), b.getByteArray1(), b.getInt1(), b.getByteArray2(), b.getLongArray1(), b.getByteArray3(), b.getByteArray4(), b.getCharArray1(), b.getStringArray1(), b.getBoolean1());
                return;
            case 203:
                NfLog.v(this.TAG, "retAvrcp14MediaItem()");
                retJniAvrcp14MediaItem(b.getByte1(), b.getByte2(), b.getByteArray1(), b.getInt1(), b.getByteArray2(), b.getLongArray1(), b.getByteArray3(), b.getCharArray1(), b.getStringArray1(), b.getBoolean1());
                return;
            case 204:
                NfLog.v(this.TAG, "retAvrcp14MediaItemElementAttribute()");
                retJniAvrcp14MediaItemElementAttribute(b.getByteArray1(), b.getLong1(), b.getInt1(), b.getInt2(), b.getString1());
                return;
            case 205:
                NfLog.v(this.TAG, "retAvrcp14MediaPlayerItem()");
                retJniAvrcp14MediaPlayerItem(b.getByte1(), b.getByte2(), b.getByteArray1(), b.getInt1(), b.getByteArray2(), b.getCharArray1(), b.getByteArray3(), b.getIntArray1(), b.getByteArray4(), b.getCharArray2(), b.getStringArray1(), b.getBoolean1());
                return;
            case 206:
                NfLog.v(this.TAG, "retJniAvrcp14ChangePath()");
                retJniAvrcp14ChangePath(b.getByte1(), b.getInt1());
                return;
            case 207:
                NfLog.v(this.TAG, "retAvrcp14SetBrowsedPlayer()");
                retJniAvrcp14SetBrowsedPlayer(b.getByte1(), b.getByte2(), b.getByteArray1(), b.getInt1(), b.getChar1(), b.getByte4(), b.getStringArray1());
                return;
            case 208:
                NfLog.v(this.TAG, "retAvrcp14PlayItem()");
                retJniAvrcp14PlayItem(b.getByte1());
                return;
            case 209:
                NfLog.v(this.TAG, "retAvrcp14AddToNowPlaying()");
                retJniAvrcp14AddToNowPlaying(b.getByte1());
                return;
            case 210:
                NfLog.v(this.TAG, "retAvrcp14GetItemAttributes()");
                retJniAvrcp14GetItemAttributes(b.getByte1(), b.getByte2(), b.getInt1(), b.getByteArray1(), b.getCharArray1(), b.getStringArray1());
                return;
            case 211:
                NfLog.v(this.TAG, "retAvrcp14SearchString()");
                retJniAvrcp14SearchString(b.getByte1(), b.getInt1());
                return;
            default:
                return;
        }
    }
}
