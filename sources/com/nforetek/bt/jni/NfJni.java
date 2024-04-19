package com.nforetek.bt.jni;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackJni;
import com.nforetek.bt.aidl.INfCallbackJniPbap;
import com.nforetek.bt.aidl.INfCommandJni;
import com.nforetek.bt.aidl.NfHfpClientCall;
import com.nforetek.bt.manager.NfManagerService;
import com.nforetek.bt.profile.bluetooth._NfBluetooth;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.Constants;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.bt.res.bt.NfPreference;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import com.nforetek.util.normal.NfUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class NfJni {
    public static final int PROFILE_A2DP = 2;
    public static final int PROFILE_AVRCP = 3;
    public static final int PROFILE_BT = 5;
    public static final int PROFILE_GATT = 9;
    public static final int PROFILE_HFP = 1;
    public static final int PROFILE_HID = 4;
    public static final int PROFILE_MAP = 7;
    public static final int PROFILE_MAP_MNS = 2;
    public static final int PROFILE_OPP = 8;
    public static final int PROFILE_PBAP = 6;
    public static final int PROFILE_PBAP_PCE = 1;
    public static final int onJniA2dpStateChanged = 0;
    public static final int onJniAvrcpBrowseStateChanged = 201;
    public static final int onJniAvrcpGetElementAttributes = 9;
    public static final int onJniAvrcpRegisterEventCallbackPlaybackPosChanged = 7;
    public static final int onJniAvrcpRegisterEventCallbackPlaybackStatusChanged = 5;
    public static final int onJniAvrcpRegisterEventCallbackSettingChanged = 8;
    public static final int onJniAvrcpRegisterEventCallbackTrackChanged = 6;
    public static final int onJniAvrcpRegisterEventWatcherSuccess = 4;
    public static final int onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged = 17;
    public static final int onJniAvrcpRetCapabilitiesSupportEvent = 3;
    public static final int onJniAvrcpStateChanged = 1;
    public static final int onJniBluetoothAclStateChanged = 16;
    public static final int onJniBtRoleModeChanged = 702;
    public static final int onJniGattListen = 901;
    public static final int onJniGattServerCharacteristicReadRequest = 805;
    public static final int onJniGattServerCharacteristicWriteRequest = 807;
    public static final int onJniGattServerConnectionState = 803;
    public static final int onJniGattServerDescriptorReadRequest = 806;
    public static final int onJniGattServerDescriptorWriteRequest = 808;
    public static final int onJniGattServerExecuteWrite = 809;
    public static final int onJniGattServerScanResult = 802;
    public static final int onJniGattServerServerRegistered = 801;
    public static final int onJniGattServerServiceAdded = 804;
    public static final int onJniHfpAgEvent = 503;
    public static final int onJniHfpAudioStateChanged = 502;
    public static final int onJniHfpCallChanged = 504;
    public static final int onJniHfpConnectionStateChanged = 501;
    public static final int onJniHidStateChanged = 101;
    public static final int onJniInitFinished = 5000;
    public static final int onJniMapChangeReadStatusState = 406;
    public static final int onJniMapConnectionStateChanged = 401;
    public static final int onJniMapDeleteMessageState = 405;
    public static final int onJniMapMemoryAvailable = 407;
    public static final int onJniMapMessageDeleted = 411;
    public static final int onJniMapMessageDeliverStatus = 409;
    public static final int onJniMapMessageSendingStatus = 408;
    public static final int onJniMapMessageShifted = 410;
    public static final int onJniMapReceiveMessageContent = 402;
    public static final int onJniMapReceiveNewMessage = 403;
    public static final int onJniMapSendMessageState = 404;
    public static final int onJniOppReceiveFileInfo = 602;
    public static final int onJniOppReceiveProgress = 603;
    public static final int onJniOppStateChanged = 601;
    public static final int onJniPbapConnectionStateChanged = 301;
    public static final int onJniPbapDownloadStateChanged = 302;
    public static final int onJniRecreateBondDevice = 701;
    public static final int onJniScoStateChanged = 2;
    public static final int retAvrcp14AddToNowPlaying = 209;
    public static final int retAvrcp14ChangePath = 206;
    public static final int retAvrcp14FolderItem = 202;
    public static final int retAvrcp14GetItemAttributes = 210;
    public static final int retAvrcp14MediaItem = 203;
    public static final int retAvrcp14MediaItemElementAttribute = 204;
    public static final int retAvrcp14MediaPlayerItem = 205;
    public static final int retAvrcp14PlayItem = 208;
    public static final int retAvrcp14SearchString = 211;
    public static final int retAvrcp14SetBrowsedPlayer = 207;
    public static final int retJniAvrcp13PlayStatus = 14;
    public static final int retJniAvrcp13PlayerSettingAttributesList = 10;
    public static final int retJniAvrcp13PlayerSettingCurrentValues = 12;
    public static final int retJniAvrcp13PlayerSettingValuesList = 11;
    public static final int retJniAvrcp13SetPlayerSettingValueSuccess = 13;
    public static final int retJniAvrcpQueryVersion = 15;
    public static final int retJniPbapReceiveContacts = 304;
    public static final int retJniPbapReceivePhonebookSize = 303;
    private static boolean sIsJniInitFinished = false;
    private String TAG;
    private Handler mA2dpHandler;
    private Handler mAvrcpHandler;
    private Handler mBtHandler;
    private INfCommandJni mCommand;
    private Handler mGattHandler;
    private Handler mHfpHandler;
    private Handler mHidHandler;
    Thread mInitJniProfileThread;
    Thread mInitJniProfileWithoutA2dpAvrcpThread;
    private NfManagerService mManagerService;
    private Handler mMapHandler;
    private Handler mOppHandler;
    private Handler mPbapHandler;
    private boolean isDead = false;
    private boolean isServiceBinded = false;
    private boolean isServiceBinding = false;
    private int mBtRoleMode = -1;
    private boolean mIsSetJniCallbackFinished = false;
    private CheckJniBindingTimeoutThread mCheckBindedTimeoutThread = null;
    private byte[] bind_service_lock = new byte[0];
    public ServiceConnection mConnection = new ServiceConnection() { // from class: com.nforetek.bt.jni.NfJni.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName className, IBinder service) {
            NfLog.v(NfJni.this.TAG, "ready onServiceConnected");
            if (!NfJni.this.isDead) {
                NfLog.v(NfJni.this.TAG, "Piggy Check className : " + className);
                NfJni.this.setServiceBinding(false);
                if (className.equals(new ComponentName(Constants.THIS_PACKAGE_NAME, "com.android.bluetooth.nfservice.NfJniService"))) {
                    NfLog.v(NfJni.this.TAG, "ComponentName(com.android.bluetooth.nfservice.NfJniService)");
                    if (NfJni.this.mCheckBindedTimeoutThread != null) {
                        NfJni.this.mCheckBindedTimeoutThread.interrupt();
                        NfJni.this.mCheckBindedTimeoutThread = null;
                    }
                    NfJni.this.setCommandInterface(INfCommandJni.Stub.asInterface(service));
                    NfJni.this.updateBluedroidVersion();
                }
                NfJni.this.initAfterServiceConnected();
                NfLog.v(NfJni.this.TAG, "onServiceConnected end ");
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName className) {
            NfLog.v(NfJni.this.TAG, "ready onServiceDisconnected");
            if (!NfJni.this.isDead) {
                NfJni.this.mCommand = null;
                NfJni.this.setServiceBinded(false);
                NfJni.this.setServiceBinding(false);
                NfJni.this.setJniProfileInitialed(false);
                NfJni.this.mIsSetJniCallbackFinished = false;
                NfJniBundle b = new NfJniBundle();
                b.setInt1(-1);
                Message m = Message.obtain(NfJni.this.mBtHandler, 702, b);
                NfJni.this.checkCallbackValid(m);
                NfLog.v(NfJni.this.TAG, "end onServiceDisconnected");
            }
        }
    };
    private INfCallbackJni mCallback = new INfCallbackJni.Stub() { // from class: com.nforetek.bt.jni.NfJni.2
        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onAclStateChanged(int status, byte[] address, int state, int reason) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onAclStateChanged() " + NfUtils.getAddressStringFromByte(address) + " status: " + status + " state: " + state + " reason: " + reason);
            NfJniBundle b = new NfJniBundle();
            b.setInt1(status);
            b.setByteArray1(address);
            b.setInt2(state);
            b.setInt3(reason);
            Message m = Message.obtain(NfJni.this.mBtHandler, 16, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniRecreateBondDevice(String address, int is_connect) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniRecreateBondDevice(): is_connect = " + is_connect);
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(is_connect);
            Message m = Message.obtain(NfJni.this.mBtHandler, 701, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniA2dpStateChanged(int newState, byte[] address) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniA2dpStateChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setInt1(newState);
            b.setByteArray1(address);
            Message m = Message.obtain(NfJni.this.mA2dpHandler, 0, b);
            NfJni.this.processConnectionMessage(NfUtils.getAddressStringFromByte(address), m, NfJni.this.mA2dpHandler, 500);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpStateChanged(int newState, byte[] address) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniAvrcpStateChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setInt1(newState);
            b.setByteArray1(address);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 1, b);
            NfJni.this.processConnectionMessage(NfUtils.getAddressStringFromByte(address), m, NfJni.this.mAvrcpHandler, 500);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniScoStateChanged(int index, int newState, byte[] address) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniScoStateChanged()");
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRetCapabilitiesSupportEvent(int count, byte[] events) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniAvrcpRetCapabilitiesSupportEvent()");
            NfJniBundle b = new NfJniBundle();
            b.setInt1(count);
            b.setByteArray1(events);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 3, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventWatcherSuccess(byte eventId) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniAvrcpRegisterEventWatcherSuccess()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(eventId);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 4, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged(byte eventId, byte playbackState) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(eventId);
            b.setByte2(playbackState);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 17, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventCallbackPlaybackStatusChanged(byte playbackState) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniAvrcpRegisterEventCallbackPlaybackStatusChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(playbackState);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 5, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventCallbackTrackChanged(byte[] uid) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniAvrcpRegisterEventCallbackTrackChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setByteArray1(uid);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 6, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventCallbackPlaybackPosChanged(int songPos) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniAvrcpRegisterEventCallbackPlaybackPosChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setInt1(songPos);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 7, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventCallbackSettingChanged(int count, byte[] attr, byte[] value) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniAvrcpRegisterEventCallbackSettingChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setInt1(count);
            b.setByteArray1(attr);
            b.setByteArray2(value);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 8, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpGetElementAttributes(int[] metadataAtrributeIds, String[] texts) throws RemoteException {
            NfJniBundle b = new NfJniBundle();
            NfLog.v(NfJni.this.TAG, "onJniAvrcpGetElementAttributes()");
            b.setIntArray1(metadataAtrributeIds);
            b.setStringArray1(texts);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 9, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp13PlayerSettingAttributesList(byte[] attributeIds) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retJniAvrcp13PlayerSettingAttributesList()");
            NfJniBundle b = new NfJniBundle();
            b.setByteArray1(attributeIds);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 10, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp13PlayerSettingValuesList(byte attributeId, byte[] valueIds) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retJniAvrcp13PlayerSettingValuesList()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(attributeId);
            b.setByteArray1(valueIds);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 11, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp13PlayerSettingCurrentValues(byte[] attributeIds, byte[] valueIds) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retJniAvrcp13PlayerSettingCurrentValues()");
            NfJniBundle b = new NfJniBundle();
            b.setByteArray1(attributeIds);
            b.setByteArray2(valueIds);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 12, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp13SetPlayerSettingValueSuccess(int reason) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retJniAvrcp13SetPlayerSettingValueSuccess()" + reason);
            NfJniBundle b = new NfJniBundle();
            b.setInt1(reason);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 13, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp13PlayStatus(long songLen, long songPos, byte statusId) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retJniAvrcp13PlayStatus()");
            NfJniBundle b = new NfJniBundle();
            b.setLong1(songLen);
            b.setLong2(songPos);
            b.setByte1(statusId);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 14, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcpQueryVersion(int version) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retJniAvrcpQueryVersion() version: " + version);
            NfJniBundle b = new NfJniBundle();
            b.setInt1(version);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 15, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpBrowseStateChanged(int state, byte[] address) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniAvrcpBrowseStateChanged() state: " + state + ", address=" + address);
            NfJniBundle b = new NfJniBundle();
            b.setByteArray1(address);
            b.setInt1(state);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 201, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14FolderItem(byte status, byte pdu, byte[] uidCounter, int count, byte[] itemType, long[] folder, byte[] folderType, byte[] isPlayable, char[] charset, String[] name, boolean isFinal) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retAvrcp14FolderItem()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(status);
            b.setByte2(pdu);
            b.setByteArray1(uidCounter);
            b.setInt1(count);
            b.setByteArray2(itemType);
            b.setLongArray1(folder);
            b.setByteArray3(folderType);
            b.setByteArray4(isPlayable);
            b.setCharArray1(charset);
            b.setStringArray1(name);
            b.setBoolean1(isFinal);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 202, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14MediaItem(byte status, byte pdu, byte[] uidCounter, int count, byte[] itemType, long[] folder, byte[] folderType, char[] charset, String[] name, int attr_count, int[] attr_ids, String[] attr_values, boolean isFinal) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retJniAvrcp14MediaItem()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(status);
            b.setByte2(pdu);
            b.setByteArray1(uidCounter);
            b.setInt1(count);
            b.setByteArray2(itemType);
            b.setLongArray1(folder);
            b.setByteArray3(folderType);
            b.setCharArray1(charset);
            b.setStringArray1(name);
            b.setBoolean1(isFinal);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 203, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14MediaItemElementAttribute(byte[] uidCounter, long uid, int attribute_id, int charset, String name) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14MediaPlayerItem(byte status, byte pdu, byte[] uidCounter, int count, byte[] itemType, char[] playerId, byte[] majorType, int[] subType, byte[] playStatus, char[] charset, String[] name, boolean isFinal) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retAvrcp14MediaPlayerItem()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(status);
            b.setByte2(pdu);
            b.setByteArray1(uidCounter);
            b.setInt1(count);
            b.setByteArray2(itemType);
            b.setCharArray1(playerId);
            b.setByteArray3(majorType);
            b.setIntArray1(subType);
            b.setByteArray4(playStatus);
            b.setCharArray2(charset);
            b.setStringArray1(name);
            b.setBoolean1(isFinal);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 205, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14ChangePath(byte status, int itemNum) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retAvrcp14ChangePath()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(status);
            b.setInt1(itemNum);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 206, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14SetBrowsedPlayer(byte status, byte pdu, byte[] uidCounter, int count, char charset, byte depth, String[] name) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retAvrcp14SetBrowsedPlayer()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(status);
            b.setByte2(pdu);
            b.setByteArray1(uidCounter);
            b.setInt1(count);
            b.setByte4(depth);
            b.setStringArray1(name);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 207, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14PlayItem(byte status) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retAvrcp14PlayItem()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(status);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 208, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14AddToNowPlaying(byte status) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retAvrcp14AddToNowPlaying()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(status);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 209, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14GetItemAttributes(byte status, byte pdu, int count, byte[] attrId, char[] charset, String[] attrValue) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retAvrcp14GetItemAttributes()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(status);
            b.setByte2(pdu);
            b.setInt1(count);
            b.setByteArray1(attrId);
            b.setCharArray1(charset);
            b.setStringArray1(attrValue);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 210, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14SearchString(byte status, int num) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retAvrcp14SearchString()");
            NfJniBundle b = new NfJniBundle();
            b.setByte1(status);
            b.setInt1(num);
            Message m = Message.obtain(NfJni.this.mAvrcpHandler, 211, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapConnectionStateChanged(String address, int state, int reason) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniMapConnectionStateChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(state);
            b.setInt2(reason);
            Message m = Message.obtain(NfJni.this.mMapHandler, NfJni.onJniMapConnectionStateChanged, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapReceiveMessageContent(String address, String handle, String senderName, String senderAddr, String date, String recipientAddr, int type, int folder, String subject, String message, int readStatus, int currentCount, int totalCount) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniMapReceiveMessageContent()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setString2(handle);
            b.setString3(senderName);
            b.setString4(senderAddr);
            b.setString5(date);
            b.setString6(recipientAddr);
            b.setInt1(type);
            b.setInt2(folder);
            b.setString7(subject);
            b.setString8(message);
            b.setInt3(readStatus);
            b.setInt4(currentCount);
            b.setInt5(totalCount);
            Message m = Message.obtain(NfJni.this.mMapHandler, NfJni.onJniMapReceiveMessageContent, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniReceiveNewMessage(String address, String handle, int type, int folder) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniReceiveNewMessage()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setString2(handle);
            b.setInt1(type);
            b.setInt2(folder);
            Message m = Message.obtain(NfJni.this.mMapHandler, NfJni.onJniMapReceiveNewMessage, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapSendMessageState(String address, String target, int state) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniMapSendMessageState()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setString2(target);
            b.setInt1(state);
            Message m = Message.obtain(NfJni.this.mMapHandler, NfJni.onJniMapSendMessageState, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapDeleteMessageState(String address, String handle, int reason) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniMapDeleteMessageState()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setString2(handle);
            b.setInt1(reason);
            Message m = Message.obtain(NfJni.this.mMapHandler, NfJni.onJniMapDeleteMessageState, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapChangeReadStatusState(String address, String handle, int reason) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniMapChangeReadStatusState()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setString2(handle);
            b.setInt1(reason);
            Message m = Message.obtain(NfJni.this.mMapHandler, NfJni.onJniMapChangeReadStatusState, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapMemoryAvailable(String address, int type, int structure, int available) {
            NfLog.v(NfJni.this.TAG, "onJniMapMemoryAvailable()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(type);
            b.setInt2(structure);
            b.setInt3(available);
            Message m = Message.obtain(NfJni.this.mMapHandler, NfJni.onJniMapMemoryAvailable, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapMessageSendingStatus(String address, String handle, int status) {
            NfLog.v(NfJni.this.TAG, "onJniMapMessageSendingStatus()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setString2(handle);
            b.setInt1(status);
            Message m = Message.obtain(NfJni.this.mMapHandler, NfJni.onJniMapMessageSendingStatus, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapMessageDeliverStatus(String address, String handle, int status) {
            NfLog.v(NfJni.this.TAG, "onJniMapMessageDeliverStatus()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setString2(handle);
            b.setInt1(status);
            Message m = Message.obtain(NfJni.this.mMapHandler, NfJni.onJniMapMessageDeliverStatus, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapMessageShifted(String address, String handle, int type, int newFolder, int oldFolder) {
            NfLog.v(NfJni.this.TAG, "onJniMapMessageShifted()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setString2(handle);
            b.setInt1(type);
            b.setInt2(newFolder);
            b.setInt3(oldFolder);
            Message m = Message.obtain(NfJni.this.mMapHandler, NfJni.onJniMapMessageShifted, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapMessageDeleted(String address, String handle, int type, int folder) {
            NfLog.v(NfJni.this.TAG, "onJniMapMessageDeleted()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setString2(handle);
            b.setInt1(type);
            b.setInt2(folder);
            Message m = Message.obtain(NfJni.this.mMapHandler, NfJni.onJniMapMessageDeleted, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniHfpConnectionStateChanged(String address, int prevState, int state) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniHfpConnectionStateChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(prevState);
            b.setInt2(state);
            Message m = Message.obtain(NfJni.this.mHfpHandler, NfJni.onJniHfpConnectionStateChanged, b);
            NfJni.this.processConnectionMessage(address, m, NfJni.this.mHfpHandler, 500);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniHfpAudioStateChanged(String address, int prevState, int state) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniHfpAudioStateChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(prevState);
            b.setInt2(state);
            Message m = Message.obtain(NfJni.this.mHfpHandler, NfJni.onJniHfpAudioStateChanged, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniHfpAgEvent(String address, int network, int roaming, int signal, int battery, String operator, int voice, int inBandRingtone, String subscriber) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniHfpAgEvent()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(network);
            b.setInt2(roaming);
            b.setInt3(signal);
            b.setInt4(battery);
            b.setString2(operator);
            b.setInt5(voice);
            b.setInt6(inBandRingtone);
            b.setString3(subscriber);
            Message m = Message.obtain(NfJni.this.mHfpHandler, NfJni.onJniHfpAgEvent, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniHfpCallChanged(NfHfpClientCall hcc) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniHfpCallChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setNfHeadsetClientCall1(hcc);
            Message m = Message.obtain(NfJni.this.mHfpHandler, NfJni.onJniHfpCallChanged, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniOppStateChanged(String address, int preState, int currentState, int reason) {
            NfLog.v(NfJni.this.TAG, "onJniOppStateChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(preState);
            b.setInt2(currentState);
            b.setInt3(reason);
            Message m = Message.obtain(NfJni.this.mOppHandler, NfJni.onJniOppStateChanged, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniOppReceiveFileInfo(String fileName, int fileSize, String devName, String savePath) {
            NfLog.v(NfJni.this.TAG, "onJniOppReceiveFileInfo()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(fileName);
            b.setInt1(fileSize);
            b.setString2(devName);
            b.setString3(savePath);
            Message m = Message.obtain(NfJni.this.mOppHandler, NfJni.onJniOppReceiveFileInfo, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniOppReceiveProgress(int receivedOffset) {
            NfLog.v(NfJni.this.TAG, "onJniOppReceiveProgress()");
            NfJniBundle b = new NfJniBundle();
            b.setInt1(receivedOffset);
            Message m = Message.obtain(NfJni.this.mOppHandler, NfJni.onJniOppReceiveProgress, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniHidStateChanged(int state, byte[] address, int reason) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniHidStateChanged() state: " + state + ", address=" + address + ", reason=" + reason);
            NfJniBundle b = new NfJniBundle();
            b.setByteArray1(address);
            b.setInt1(state);
            b.setInt2(reason);
            Message m = Message.obtain(NfJni.this.mHidHandler, 101, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniBtRoleModeChanged(int mode) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniBtRoleModeChanged() " + mode);
            NfJniBundle b = new NfJniBundle();
            b.setInt1(mode);
            Message m = Message.obtain(NfJni.this.mBtHandler, 702, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerServerRegistered(int status) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniGattServerServerRegistered() " + status);
            NfJniBundle b = new NfJniBundle();
            b.setInt1(status);
            Message m = Message.obtain(NfJni.this.mGattHandler, NfJni.onJniGattServerServerRegistered, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerScanResult(String address, int rssi, byte[] advData) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniGattServerScanResult() " + address);
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(rssi);
            b.setByteArray1(advData);
            Message m = Message.obtain(NfJni.this.mGattHandler, NfJni.onJniGattServerScanResult, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerConnectionState(String address, int status, boolean connected) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniGattServerConnectionState() " + status);
            NfJniBundle b = new NfJniBundle();
            b.setInt1(status);
            b.setBoolean1(connected);
            b.setString1(address);
            Message m = Message.obtain(NfJni.this.mGattHandler, NfJni.onJniGattServerConnectionState, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerServiceAdded(int status, int srvcType, int srvcInstId, ParcelUuid srvcId) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniGattServerServiceAdded() " + status + " srvcId: " + srvcId);
            NfJniBundle b = new NfJniBundle();
            b.setInt1(status);
            b.setInt2(srvcType);
            b.setInt3(srvcInstId);
            b.setUuid1(srvcId);
            Message m = Message.obtain(NfJni.this.mGattHandler, NfJni.onJniGattServerServiceAdded, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerCharacteristicReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniGattServerCharacteristicReadRequest() " + address);
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(transId);
            b.setInt2(offset);
            b.setBoolean1(isLong);
            b.setInt3(srvcType);
            b.setInt4(srvcInstId);
            b.setUuid1(srvcId);
            b.setInt5(charInstId);
            b.setUuid2(charId);
            Message m = Message.obtain(NfJni.this.mGattHandler, NfJni.onJniGattServerCharacteristicReadRequest, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerDescriptorReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniGattServerDescriptorReadRequest() " + address);
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(transId);
            b.setInt2(offset);
            b.setBoolean1(isLong);
            b.setInt3(srvcType);
            b.setInt4(srvcInstId);
            b.setUuid1(srvcId);
            b.setInt5(charInstId);
            b.setUuid2(charId);
            b.setUuid3(descrId);
            Message m = Message.obtain(NfJni.this.mGattHandler, NfJni.onJniGattServerDescriptorReadRequest, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerCharacteristicWriteRequest(String address, int transId, int offset, int length, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, byte[] value) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniGattServerCharacteristicWriteRequest() " + address);
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(transId);
            b.setInt2(offset);
            b.setInt3(length);
            b.setBoolean1(isPrep);
            b.setBoolean2(needRsp);
            b.setInt4(srvcType);
            b.setInt5(srvcInstId);
            b.setUuid1(srvcId);
            b.setInt5(charInstId);
            b.setUuid2(charId);
            b.setByteArray1(value);
            Message m = Message.obtain(NfJni.this.mGattHandler, NfJni.onJniGattServerCharacteristicWriteRequest, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerDescriptorWriteRequest(String address, int transId, int offset, int length, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId, byte[] value) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniGattServerDescriptorWriteRequest() " + address);
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(transId);
            b.setInt2(offset);
            b.setInt3(length);
            b.setBoolean1(isPrep);
            b.setBoolean2(needRsp);
            b.setInt4(srvcType);
            b.setInt5(srvcInstId);
            b.setUuid1(srvcId);
            b.setInt5(charInstId);
            b.setUuid2(charId);
            b.setUuid3(descrId);
            b.setByteArray1(value);
            Message m = Message.obtain(NfJni.this.mGattHandler, NfJni.onJniGattServerDescriptorWriteRequest, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerExecuteWrite(String address, int transId, boolean execWrite) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniGattServerExecuteWrite() " + address);
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(transId);
            b.setBoolean1(execWrite);
            Message m = Message.obtain(NfJni.this.mGattHandler, NfJni.onJniGattServerExecuteWrite, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattListen(int status) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniGattListen() " + status);
            NfJniBundle b = new NfJniBundle();
            b.setInt1(status);
            Message m = Message.obtain(NfJni.this.mGattHandler, NfJni.onJniGattListen, b);
            NfJni.this.checkCallbackValid(m);
        }
    };
    private INfCallbackJniPbap mCallbackPbap = new INfCallbackJniPbap.Stub() { // from class: com.nforetek.bt.jni.NfJni.3
        @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
        public void onJniPbapConnectionStateChanged(String address, int state) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniPbapConnectionStateChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(state);
            Message m = Message.obtain(NfJni.this.mPbapHandler, 301, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
        public void onJniPbapDownloadStateChanged(String address, int state) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "onJniPbapDownloadStateChanged()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(state);
            Message m = Message.obtain(NfJni.this.mPbapHandler, 302, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
        public void retJniPbapReceivePhonebookSize(String address, int access, int size) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retJniPbapReceivePhonebookSize()");
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(access);
            b.setInt2(size);
            Message m = Message.obtain(NfJni.this.mPbapHandler, 303, b);
            NfJni.this.checkCallbackValid(m);
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
        public void retJniPbapReceiveContacts(String bt_address, int contactsId, String firstName, String middleName, String lastName, int encode, String[] number, int[] phoneType, String timestamp, int contactType, byte[] photo, int photoType, int[] emailType, String[] email, int[] addressType, String[] address, String org2) throws RemoteException {
            NfLog.v(NfJni.this.TAG, "retJniPbapReceiveContacts()");
            if (!NfPrimitive.isBtEnabled()) {
                NfLog.v(NfJni.this.TAG, "BT Off now. drop this contact");
                NfJni.this.mPbapHandler.removeMessages(NfJni.retJniPbapReceiveContacts);
                return;
            }
            NfJniBundle b = new NfJniBundle();
            b.setString1(bt_address);
            b.setInt1(contactsId);
            b.setString2(firstName);
            b.setString3(middleName);
            b.setString4(lastName);
            b.setInt2(encode);
            b.setStringArray1(number);
            b.setIntArray1(phoneType);
            b.setString5(timestamp);
            b.setInt3(contactType);
            b.setByteArray1(photo);
            b.setInt4(photoType);
            b.setIntArray2(emailType);
            b.setStringArray2(email);
            b.setIntArray3(addressType);
            b.setStringArray3(address);
            b.setString6(org2);
            Message m = Message.obtain(NfJni.this.mPbapHandler, NfJni.retJniPbapReceiveContacts, b);
            NfJni.this.checkCallbackValid(m);
        }
    };

    public NfJni(NfManagerService c) {
        this.TAG = "NfJni";
        this.TAG = "NfJni_" + hashCode();
        NfLog.v(this.TAG, "init()");
        this.mManagerService = c;
    }

    public void onDestroy() {
        NfLog.v(this.TAG, "onDestroy()");
        this.isDead = true;
        this.mCommand = null;
        this.mConnection = null;
        this.mManagerService = null;
        this.mCallback = null;
        this.mCallbackPbap = null;
        this.isServiceBinded = false;
        this.isServiceBinding = false;
        this.mA2dpHandler = null;
        this.mAvrcpHandler = null;
        this.mHidHandler = null;
        this.mBtHandler = null;
        this.mPbapHandler = null;
        this.mMapHandler = null;
        this.mHfpHandler = null;
        this.mOppHandler = null;
        this.mGattHandler = null;
    }

    public void bindService() {
        boolean result;
        NfLog.v(this.TAG, "bindService()");
        synchronized (this.bind_service_lock) {
            if (!isServiceBinded() && !isServiceBinding()) {
                if (this.mCheckBindedTimeoutThread != null) {
                    this.mCheckBindedTimeoutThread.interrupt();
                    this.mCheckBindedTimeoutThread = null;
                }
                Intent intent = NfUtils.createExplicitFromImplicitIntent(this.mManagerService.getApplicationContext(), new Intent("com.nforetek.bt.NfJniService"));
                if (intent == null) {
                    ComponentName component = new ComponentName(Constants.THIS_PACKAGE_NAME, "com.android.bluetooth.nfservice.NfJniService");
                    Intent explicitIntent = new Intent("com.nforetek.bt.NfJniService");
                    explicitIntent.setComponent(component);
                    result = this.mManagerService.getApplicationContext().bindService(explicitIntent, this.mConnection, 1);
                } else {
                    result = this.mManagerService.getApplicationContext().bindService(intent, this.mConnection, 1);
                }
                setServiceBinding(result);
                this.mCheckBindedTimeoutThread = new CheckJniBindingTimeoutThread(this, null);
                if (this.mCheckBindedTimeoutThread != null) {
                    this.mCheckBindedTimeoutThread.start();
                } else {
                    this.mCheckBindedTimeoutThread = new CheckJniBindingTimeoutThread(this, null);
                    if (this.mCheckBindedTimeoutThread != null) {
                        this.mCheckBindedTimeoutThread.start();
                    }
                }
            } else if (isServiceBinded()) {
                NfLog.e(this.TAG, "In NfJni bindService but service already binded.");
                if (!sIsJniInitFinished) {
                    NfLog.e(this.TAG, "In NfJni bindService but service already binded but profile not initialize. init profile now.");
                    initAfterServiceConnected();
                } else {
                    NfLog.e(this.TAG, "In NfJni bindService but service already binded and profile already initialized.");
                }
            } else {
                NfLog.e(this.TAG, "In NfJni bindService. isServiceBinded: " + isServiceBinded() + " isServiceBinding: " + isServiceBinding());
            }
        }
        NfLog.v(this.TAG, "bindService() finished.");
    }

    public synchronized void unbindService() {
        NfLog.v(this.TAG, "unbindService()");
        if (this.isServiceBinded) {
            try {
                this.mManagerService.getApplicationContext().unbindService(this.mConnection);
            } catch (Exception e) {
                e.printStackTrace();
            }
            setServiceBinded(false);
        }
    }

    public synchronized boolean isServiceBinded() {
        NfLog.v(this.TAG, "isServiceBinded: " + this.isServiceBinded);
        return this.isServiceBinded;
    }

    public synchronized boolean isServiceBinding() {
        NfLog.v(this.TAG, "isServiceBinding: " + this.isServiceBinding);
        return this.isServiceBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setServiceBinded(boolean binded) {
        NfLog.v(this.TAG, "setServiceBinded: " + binded);
        this.isServiceBinded = binded;
        this.mManagerService.onNfJniServiceConnected(this.isServiceBinded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setServiceBinding(boolean binding) {
        NfLog.v(this.TAG, "setServiceBinding: " + binding);
        this.isServiceBinding = binding;
    }

    public void setCallbackHandler(int profile, Handler handler) {
        NfLog.e(this.TAG, "setCallbackHandler() profile: " + profile);
        switch (profile) {
            case 1:
                if (this.mHfpHandler != null) {
                    this.mHfpHandler.removeCallbacksAndMessages(null);
                }
                this.mHfpHandler = handler;
                return;
            case 2:
                if (this.mA2dpHandler != null) {
                    this.mA2dpHandler.removeCallbacksAndMessages(null);
                }
                this.mA2dpHandler = handler;
                return;
            case 3:
                if (this.mAvrcpHandler != null) {
                    this.mAvrcpHandler.removeCallbacksAndMessages(null);
                }
                this.mAvrcpHandler = handler;
                return;
            case 4:
                if (this.mHidHandler != null) {
                    this.mHidHandler.removeCallbacksAndMessages(null);
                }
                this.mHidHandler = handler;
                return;
            case 5:
                if (this.mBtHandler != null) {
                    this.mBtHandler.removeCallbacksAndMessages(null);
                }
                this.mBtHandler = handler;
                return;
            case 6:
                if (this.mPbapHandler != null) {
                    this.mPbapHandler.removeCallbacksAndMessages(null);
                }
                this.mPbapHandler = handler;
                return;
            case 7:
                if (this.mMapHandler != null) {
                    this.mMapHandler.removeCallbacksAndMessages(null);
                }
                this.mMapHandler = handler;
                return;
            case 8:
                if (this.mOppHandler != null) {
                    this.mOppHandler.removeCallbacksAndMessages(null);
                }
                this.mOppHandler = handler;
                return;
            case 9:
                if (this.mGattHandler != null) {
                    this.mGattHandler.removeCallbacksAndMessages(null);
                }
                this.mGattHandler = handler;
                return;
            default:
                NfLog.e(this.TAG, "Undefine profile code: " + profile);
                return;
        }
    }

    public void setCommandInterface(INfCommandJni c) {
        this.mCommand = c;
        if (isJniCommandSendable()) {
            registerJniCallback();
            this.mIsSetJniCallbackFinished = true;
        }
    }

    public INfCommandJni getCommandInterface() {
        return this.mCommand;
    }

    public boolean switchRoleMode(int mode) throws RemoteException {
        NfLog.v(this.TAG, "switchRoleMode() " + mode);
        if (!NfConfig.isAllowRoleSwitch()) {
            NfLog.e(this.TAG, "Not allow switch role mode!");
            return false;
        }
        boolean result = false;
        if (isJniCommandSendable()) {
            try {
                try {
                    sleepAndCheckBtEnable();
                    this.mCommand.switchBtRoleMode(mode);
                    if (mode == 1) {
                        try {
                            Thread.sleep(1500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        startInitJniProfileThread();
                        byte[] address = new byte[6];
                        this.mCallback.onJniA2dpStateChanged(0, address);
                        this.mCallback.onJniAvrcpStateChanged(0, address);
                    } else if (mode == 2) {
                        startInitJniProfileWithoutA2dpAvrcpThread();
                        byte[] address2 = new byte[6];
                        this.mCallback.onJniA2dpStateChanged(-1, address2);
                        this.mCallback.onJniAvrcpStateChanged(-1, address2);
                    } else {
                        NfLog.e(this.TAG, "Unknown mode: " + mode);
                    }
                    NfJniBundle b = new NfJniBundle();
                    b.setInt1(mode);
                    Message m = Message.obtain(this.mBtHandler, 702, b);
                    checkCallbackValid(m);
                    result = true;
                    return true;
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    return result;
                }
            } catch (RemoteException e3) {
                e3.printStackTrace();
                return result;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
        return false;
    }

    public int getRoleMode() throws RemoteException {
        NfLog.v(this.TAG, "getRoleMode()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getBtRoleMode();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return -1;
    }

    public int createSdpRecord(String serviceName, int profile, int rfcommChannel, int l2capPsm, int version, int features) throws RemoteException {
        NfLog.v(this.TAG, "createSdpRecord(): " + profile + ", port: " + rfcommChannel);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.createSdpRecord(serviceName, profile, rfcommChannel, l2capPsm, version, features);
            } catch (RemoteException e) {
                NfLog.v(this.TAG, "createSdpRecord() fail: ", e);
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return -1;
    }

    public boolean reqA2dpConnect(String address) {
        NfLog.v(this.TAG, "reqA2dpConnect() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqA2dpConnect(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqA2dpDisconnect(String address) {
        NfLog.v(this.TAG, "reqA2dpDisconnect() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqA2dpDisconnect(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpConnect(String address) {
        NfLog.v(this.TAG, "reqAvrcpConnect() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpConnect(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpDisconnect(String address) {
        NfLog.v(this.TAG, "reqAvrcpDisconnect() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpDisconnect(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqBtRemoveAclConnection(String address) {
        NfLog.v(this.TAG, "reqBtRemoveAclConnection() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqBtRemoveAclConnection(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return true;
    }

    public String getBluedroidVersion() {
        NfLog.v(this.TAG, "getBluedroidVersion()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getBluedroidVersion();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return null;
    }

    public String getNfJniServiceVersion() {
        NfLog.v(this.TAG, "getNfJniServiceVersion()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getNfJniServiceVersion();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return null;
    }

    public boolean reqAvrcpPlay() {
        NfLog.v(this.TAG, "reqAvrcpPlay()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpPlay();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpStop() {
        NfLog.v(this.TAG, "reqAvrcpStop()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpStop();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpPause() {
        NfLog.v(this.TAG, "reqAvrcpPause()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpPause();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpBackward() {
        NfLog.v(this.TAG, "reqAvrcpBackward()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpBackward();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpForward() {
        NfLog.v(this.TAG, "reqAvrcpForward()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpForward();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpVolumeUp() {
        NfLog.v(this.TAG, "reqAvrcpVolumeUp()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpVolumeUp();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpVolumeDown() {
        NfLog.v(this.TAG, "reqAvrcpVolumeDown()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpVolumeDown();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpStartFastForward() {
        NfLog.v(this.TAG, "reqAvrcpStartFastForward()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpStartFastForward();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpStopFastForward() {
        NfLog.v(this.TAG, "reqAvrcpStopFastForward()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpStopFastForward();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpStartRewind() {
        NfLog.v(this.TAG, "reqAvrcpStartRewind()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpStartRewind();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpStopRewind() {
        NfLog.v(this.TAG, "reqAvrcpStopRewind()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpStopRewind();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean getAvrcpCapabilitySupportEvent() {
        NfLog.v(this.TAG, "getAvrcpCapabilitySupportEvent()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getAvrcpCapabilitySupportEvent();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean getAvrcpPlayerSettingAttributesList() {
        NfLog.v(this.TAG, "getAvrcpPlayerSettingAttributesList()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getAvrcpPlayerSettingAttributesList();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean getAvrcpPlayerSettingValuesList(byte attributeId) {
        NfLog.v(this.TAG, "getAvrcpPlayerSettingValuesList() attributeId: 0x" + Byte.toString(attributeId));
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getAvrcpPlayerSettingValuesList(attributeId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean getAvrcpPlayerSettingValues() {
        NfLog.v(this.TAG, "getAvrcpPlayerSettingValues()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getAvrcpPlayerSettingValues();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean setAvrcpPlayerSettingValue(byte attributeId, byte valueId) {
        NfLog.v(this.TAG, "setAvrcpPlayerSettingValue() attributeId: 0x" + Byte.toString(attributeId) + " valueId: 0x" + Byte.toString(valueId));
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.setAvrcpPlayerSettingValue(attributeId, valueId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean getAvrcpElementAttributes() {
        NfLog.v(this.TAG, "getAvrcpElementAttributes()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getAvrcpElementAttributes();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean getAvrcpPlayStatus() {
        NfLog.v(this.TAG, "getAvrcpPlayStatus()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getAvrcpPlayStatus();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean registerAvrcpEvent(byte event_id, int interval) {
        NfLog.v(this.TAG, "registerAvrcpEvent() event_id: 0x" + Byte.toString(event_id));
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.registerAvrcpEvent(event_id, interval);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean unregisterAvrcpEvent(byte event_id) {
        NfLog.v(this.TAG, "unregisterAvrcpEvent() event_id: 0x" + Byte.toString(event_id));
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.unregisterAvrcpEvent(event_id);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpNextGroup() {
        NfLog.v(this.TAG, "reqAvrcpNextGroup()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpNextGroup();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpPreviousGroup() {
        NfLog.v(this.TAG, "reqAvrcpPreviousGroup()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpPreviousGroup();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean getAvrcpPlayerSettingAttributeText(byte attributeId) {
        NfLog.v(this.TAG, "getAvrcpPlayerSettingAttributeText() attributeId: 0x" + Byte.toString(attributeId));
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getAvrcpPlayerSettingAttributeText(attributeId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean getAvrcpPlayerSettingValueText(byte attributeId, byte valueId) {
        NfLog.v(this.TAG, "getAvrcpPlayerSettingValueText() attributeId: 0x" + Byte.toString(attributeId) + " valueId: 0x" + Byte.toString(valueId));
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getAvrcpPlayerSettingValueText(attributeId, valueId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpContinuingResponse() {
        NfLog.v(this.TAG, "reqAvrcpContinuingResponse()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpContinuingResponse();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean abortAvrcpContinuingResponse() {
        NfLog.v(this.TAG, "abortAvrcpContinuingResponse()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.abortAvrcpContinuingResponse();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpQueryVersion(String address) {
        NfLog.v(this.TAG, "reqAvrcpQueryVersion() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpQueryVersion(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public void pauseA2dpRender() {
        NfLog.v(this.TAG, "pauseA2dpRender()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.pauseA2dpRender();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void startA2dpRender() {
        NfLog.v(this.TAG, "startA2dpRender()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.startA2dpRender();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public boolean setA2dpLocalVolume(float vol) {
        NfLog.v(this.TAG, "setA2dpLocalVolume() " + vol);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.setA2dpLocalVolume(vol);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean setA2dpStreamType(int type) {
        NfLog.v(this.TAG, "setA2dpStreamType() " + type);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.setA2dpStreamType(type);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpVersion(String address) {
        NfLog.v(this.TAG, "reqAvrcpVersion()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpVersion(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public int getAvrcpVersion(String address) {
        NfLog.v(this.TAG, "getAvrcpVersion()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getAvrcpVersion(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return -1;
    }

    public boolean reqAvrcpBrowseConnect(String address) {
        NfLog.v(this.TAG, "reqAvrcpBrowseConnect()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpBrowseConnect(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpBrowseDisconnect(String address) {
        NfLog.v(this.TAG, "reqAvrcpBrowseDisconnect()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpBrowseDisconnect(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpGetFolderItem(byte scope) {
        NfLog.v(this.TAG, "reqAvrcpGetFolderItem()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpGetFolderItem(scope);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpChangePath(byte updown, long uid) {
        NfLog.v(this.TAG, "reqAvrcpChangePath()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpChangePath(updown, uid);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpSetBrowsedPlayer(int player) {
        NfLog.v(this.TAG, "reqAvrcpSetBrowsedPlayer()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpSetBrowsedPlayer(player);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpPlayItem(long item, int uid) {
        NfLog.v(this.TAG, "reqAvrcpPlayItem()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpPlayItem(item, uid);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpAddToNowPlaying(long item, int uid) {
        NfLog.v(this.TAG, "reqAvrcpAddToNowPlaying()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpAddToNowPlaying(item, uid);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpSearchString(String data) {
        NfLog.v(this.TAG, "reqAvrcpSearchString()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpSearchString(data);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqAvrcpGetItemAttributes(byte scope, long item, int uid) {
        NfLog.v(this.TAG, "reqAvrcpGetItemAttributes()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqAvrcpGetItemAttributes(scope, item, uid);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqHidConnect(String address) {
        NfLog.v(this.TAG, "reqHidConnect() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqHidConnect(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqHidDisconnect(String address) {
        NfLog.v(this.TAG, "reqHidDisconnect() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqHidDisconnect(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqSendHidMouseCommand(String address, String data) {
        NfLog.v(this.TAG, "reqSendHidMouseCommand() data: " + data);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqHidSendData(address, data);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqSendHidVirtualKeyCommand(String address, String data) {
        NfLog.v(this.TAG, "reqSendHidVirtualKeyCommand() data: " + data);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqHidSendData(address, data);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqPbapConnect(String address, boolean isByPass) {
        NfLog.v(this.TAG, "reqPbapConnect() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqPbapConnect(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqPbapDisconnect(String address) {
        NfLog.v(this.TAG, "reqPbapDisconnect() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqPbapDisconnect(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqPbapStartDownload(String address, int access, int filter, int offset, int max_count) {
        NfLog.v(this.TAG, "reqPbapStartDownload() " + address + " access: " + access + " filter: " + filter + " offset: " + offset + " max_count: " + max_count);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqPbapStartDownload(address, access, filter, offset, max_count);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqPbapStopDownload(String address) {
        NfLog.v(this.TAG, "reqPbapStopDownload() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqPbapStopDownload(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqPbapGetSize(String address, int access) {
        NfLog.v(this.TAG, "reqPbapGetSize() " + address + " access: " + access);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqPbapGetSize(address, access);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqMapDownloadAll(String address, int type, int folder, boolean isDownloadDetail) {
        NfLog.v(this.TAG, "reqMapDownloadAll() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqMapDownloadAll(address, type, folder, isDownloadDetail);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqMapDownloadOne(String address, String handle, int type, int folder) {
        NfLog.v(this.TAG, "reqMapDownloadOne() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqMapDownloadOne(address, handle, type, folder);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqMapRegisterNotification(String address, boolean enable) {
        NfLog.v(this.TAG, "reqMapRegisterNotification() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqMapRegisterNotification(address, enable);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqMapDownloadInterrupt(String address) {
        NfLog.v(this.TAG, "reqMapDownloadInterrupt() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqMapDownloadInterrupt(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqMapDisconnect(String address) {
        NfLog.v(this.TAG, "reqMapDisconnect() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqMapDisconnect(address);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean isMapNotificationRegistered() {
        NfLog.v(this.TAG, "isMapNotificationRegistered()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.isMapNotificationRegistered();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqMapSendMessage(String address, String msg, String target) {
        NfLog.v(this.TAG, "reqMapSendMessage() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqMapSendMessage(address, msg, target);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqMapDeleteMessage(String address, int folder, String handle) {
        NfLog.v(this.TAG, "reqMapDeleteMessage() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqMapDeleteMessage(address, folder, handle);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqMapChangeReadStatus(String address, int folder, String handle, boolean isReadStatus) {
        NfLog.v(this.TAG, "reqMapChangeReadStatus() " + address);
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqMapChangeReadStatus(address, folder, handle, isReadStatus);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqHfpConnect(String address) {
        NfLog.v(this.TAG, "reqHfpConnect()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqHfpConnect(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqHfpDisconnect(String address) {
        NfLog.v(this.TAG, "reqHfpDisconnect()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqHfpDisconnect(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public List<BluetoothDevice> getHfpConnectedDevices() {
        NfLog.v(this.TAG, "getHfpConnectedDevices()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getHfpConnectedDevices();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return null;
    }

    public int getHfpConnectionState(String address) {
        NfLog.v(this.TAG, "getHfpConnectionState()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getHfpConnectionState(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return -1;
    }

    public boolean setHfpPriority(String address, int priority) {
        NfLog.v(this.TAG, "setHfpPriority()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.setHfpPriority(getDevice(address), priority);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public int getHfpPriority(String address) {
        NfLog.v(this.TAG, "getHfpPriority()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getHfpPriority(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return -1;
    }

    public boolean startHfpVoiceRecognition(String address) {
        NfLog.v(this.TAG, "startHfpVoiceRecognition()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.startHfpVoiceRecognition(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean stopHfpVoiceRecognition(String address) {
        NfLog.v(this.TAG, "stopHfpVoiceRecognition()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.stopHfpVoiceRecognition(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean rejectHfpIncomingConnect(String address) {
        NfLog.v(this.TAG, "rejectHfpIncomingConnect()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.rejectHfpIncomingConnect(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public int getHfpAudioState(String address) {
        NfLog.v(this.TAG, "getHfpAudioState()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getHfpAudioState(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return -1;
    }

    public boolean connectHfpAudio() {
        NfLog.v(this.TAG, "connectHfpAudio()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.connectHfpAudio();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean disconnectHfpAudio() {
        NfLog.v(this.TAG, "disconnectHfpAudio()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.disconnectHfpAudio();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean acceptHfpCall(String address, int flag) {
        NfLog.v(this.TAG, "acceptHfpCall()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.acceptHfpCall(getDevice(address), flag);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean rejectHfpCall(String address) {
        NfLog.v(this.TAG, "rejectHfpCall()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.rejectHfpCall(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean holdHfpCall(String address) {
        NfLog.v(this.TAG, "holdHfpCall()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.holdHfpCall(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean terminateHfpCall(String address, int index) {
        NfLog.v(this.TAG, "terminateHfpCall()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.terminateHfpCall(getDevice(address), index);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean explicitHfpCallTransfer(String address) {
        NfLog.v(this.TAG, "explicitHfpCallTransfer()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.explicitHfpCallTransfer(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean enterHfpPrivateMode(String address, int index) {
        NfLog.v(this.TAG, "enterHfpPrivateMode()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.enterHfpPrivateMode(getDevice(address), index);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqHfpRedial(String address) {
        NfLog.v(this.TAG, "reqHfpRedial()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqHfpRedial(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqHfpDial(String address, String number) {
        NfLog.v(this.TAG, "reqHfpDial()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqHfpDial(getDevice(address), number);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean reqHfpDialMemory(String address, int location) {
        NfLog.v(this.TAG, "reqHfpDialMemory()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqHfpDialMemory(getDevice(address), location);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public List<NfHfpClientCall> getHfpCurrentCalls(String address) {
        NfLog.v(this.TAG, "getHfpCurrentCalls()");
        if (address.equals(NfDef.DEFAULT_ADDRESS)) {
            return new ArrayList();
        }
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getHfpCurrentCalls(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return null;
    }

    public boolean reqHfpSendDTMF(String address, byte code) {
        NfLog.v(this.TAG, "reqHfpSendDTMF()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.reqHfpSendDTMF(getDevice(address), code);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean getHfpLastVoiceTagNumber(String address) {
        NfLog.v(this.TAG, "getHfpLastVoiceTagNumber()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getHfpLastVoiceTagNumber(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public Bundle getHfpCurrentAgEvents(String address) {
        NfLog.v(this.TAG, "getHfpCurrentAgEvents()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getHfpCurrentAgEvents(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return null;
    }

    public Bundle getHfpCurrentAgFeatures(String address) {
        NfLog.v(this.TAG, "getHfpCurrentAgFeatures()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getHfpCurrentAgFeatures(getDevice(address));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return null;
    }

    public boolean setOppFilePath(String path) {
        NfLog.v(this.TAG, "setOppFilePath()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.setOppFilePath(path);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public String getOppFilePath() {
        NfLog.v(this.TAG, "getOppFilePath()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.getOppFilePath();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return "";
    }

    public boolean acceptOppReceiveFile(boolean accept) {
        NfLog.v(this.TAG, "acceptOppReceiveFile()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.acceptOppReceiveFile(accept);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public boolean interruptOppReceiveFile() {
        NfLog.v(this.TAG, "interruptOppReceiveFile()");
        if (isJniCommandSendable()) {
            try {
                return this.mCommand.interruptOppReceiveFile();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            NfLog.e(this.TAG, "mCommandJni is null!!");
        }
        return false;
    }

    public void pauseHfpRender() {
        NfLog.v(this.TAG, "pauseHfpRender()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.pauseHfpRender();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void startHfpRender() {
        NfLog.v(this.TAG, "startHfpRender()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.startHfpRender();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void muteHfpMic(boolean mute) {
        NfLog.v(this.TAG, "muteHfpMic() " + mute);
        if (isJniCommandSendable()) {
            try {
                this.mCommand.muteHfpMic(mute);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void reqGattServerListen(boolean listen) {
        NfLog.v(this.TAG, "reqGattServerListen() " + listen);
        if (isJniCommandSendable()) {
            try {
                this.mCommand.reqGattServerListen(listen);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void reqGattServerConnect(String address, boolean isDirect) {
        NfLog.v(this.TAG, "reqGattServerConnect()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.reqGattServerConnect(0, address, isDirect);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void reqGattServerDisconnect(String address) {
        NfLog.v(this.TAG, "reqGattServerDisconnect()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.reqGattServerDisconnect(0, address);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void beginGattServiceDeclaration(int srvcType, int srvcInstanceId, int minHandles, ParcelUuid srvcId) {
        NfLog.v(this.TAG, "beginGattServiceDeclaration()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.beginGattServiceDeclaration(0, srvcType, srvcInstanceId, minHandles, srvcId);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void reqGattAddIncludedService(int srvcType, int srvcInstanceId, ParcelUuid srvcId) {
        NfLog.v(this.TAG, "reqGattAddIncludedService()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.reqGattAddIncludedService(0, srvcType, srvcInstanceId, srvcId);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void reqGattAddCharacteristic(ParcelUuid charId, int properties, int permissions) {
        NfLog.v(this.TAG, "reqGattAddCharacteristic()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.reqGattAddCharacteristic(0, charId, properties, permissions);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void reqGattAddDescriptor(ParcelUuid descId, int permissions) {
        NfLog.v(this.TAG, "reqGattAddDescriptor()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.reqGattAddDescriptor(0, descId, permissions);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void endGattServiceDeclaration() {
        NfLog.v(this.TAG, "endGattServiceDeclaration()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.endGattServiceDeclaration(0);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void reqGattRemoveService(int srvcType, int srvcInstanceId, ParcelUuid srvcId) {
        NfLog.v(this.TAG, "reqGattRemoveService()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.reqGattRemoveService(0, srvcType, srvcInstanceId, srvcId);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void reqGattClearServices() {
        NfLog.v(this.TAG, "reqGattClearServices()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.reqGattClearServices(0);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void reqGattSendResponse(String address, int requestId, int status, int offset, byte[] value) {
        NfLog.v(this.TAG, "reqGattSendResponse()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.reqGattSendResponse(0, address, requestId, status, offset, value);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    public void reqGattSendNotification(String address, int srvcType, int srvcInstanceId, ParcelUuid srvcId, int charInstanceId, ParcelUuid charId, boolean confirm, byte[] value) {
        NfLog.v(this.TAG, "reqGattSendNotification()");
        if (isJniCommandSendable()) {
            try {
                this.mCommand.reqGattSendNotification(0, address, srvcType, srvcInstanceId, srvcId, charInstanceId, charId, confirm, value);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        NfLog.e(this.TAG, "mCommandJni is null!!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkCallbackValid(Message m) {
        if (!isCallbackValid(m.what)) {
            NfLog.e(this.TAG, "Callback invalid!!");
        } else {
            m.sendToTarget();
        }
    }

    private void checkCallbackValidWithDelay(Message m, Handler handler, int delay) {
        if (!isCallbackValid(m.what)) {
            NfLog.e(this.TAG, "Callback invalid!!");
            return;
        }
        NfLog.d(this.TAG, "send message: " + m + " delay " + delay + " ms to " + handler.getClass().getSimpleName());
        handler.sendMessageDelayed(m, delay);
    }

    private boolean isMessageAlreadyInQueue(Message m, Handler handler) {
        boolean result = handler.hasMessages(m.what);
        if (result) {
            NfLog.e(this.TAG, "Message: " + m + " is already in queue: " + handler.getClass().getSimpleName());
        }
        return result;
    }

    private BluetoothDevice getDevice(String address) {
        return BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
    }

    private boolean isCallbackValid(int what) {
        if (this.isDead) {
            return false;
        }
        switch (what) {
            case 0:
                return this.mA2dpHandler != null;
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 17:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
                return this.mAvrcpHandler != null;
            case 16:
            case 701:
            case 702:
            case onJniInitFinished /* 5000 */:
                return this.mBtHandler != null;
            case 101:
                return this.mHidHandler != null;
            case 301:
            case 302:
            case 303:
            case retJniPbapReceiveContacts /* 304 */:
                return this.mPbapHandler != null;
            case onJniMapConnectionStateChanged /* 401 */:
            case onJniMapReceiveMessageContent /* 402 */:
            case onJniMapReceiveNewMessage /* 403 */:
            case onJniMapSendMessageState /* 404 */:
            case onJniMapDeleteMessageState /* 405 */:
            case onJniMapChangeReadStatusState /* 406 */:
            case onJniMapMemoryAvailable /* 407 */:
            case onJniMapMessageSendingStatus /* 408 */:
            case onJniMapMessageDeliverStatus /* 409 */:
            case onJniMapMessageShifted /* 410 */:
            case onJniMapMessageDeleted /* 411 */:
                return this.mMapHandler != null;
            case onJniHfpConnectionStateChanged /* 501 */:
            case onJniHfpAudioStateChanged /* 502 */:
            case onJniHfpAgEvent /* 503 */:
            case onJniHfpCallChanged /* 504 */:
                return this.mHfpHandler != null;
            case onJniOppStateChanged /* 601 */:
            case onJniOppReceiveFileInfo /* 602 */:
            case onJniOppReceiveProgress /* 603 */:
                return this.mOppHandler != null;
            case onJniGattServerServerRegistered /* 801 */:
            case onJniGattServerScanResult /* 802 */:
            case onJniGattServerConnectionState /* 803 */:
            case onJniGattServerServiceAdded /* 804 */:
            case onJniGattServerCharacteristicReadRequest /* 805 */:
            case onJniGattServerDescriptorReadRequest /* 806 */:
            case onJniGattServerCharacteristicWriteRequest /* 807 */:
            case onJniGattServerDescriptorWriteRequest /* 808 */:
            case onJniGattServerExecuteWrite /* 809 */:
            case onJniGattListen /* 901 */:
                return this.mGattHandler != null;
            default:
                NfLog.e(this.TAG, "Unknown callback type: " + what);
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initJniProfile() throws InterruptedException {
        if (NfConfig.isEnableHfp()) {
            sleepAndCheckBtEnable();
            try {
                this.mCommand.initJniHfp();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            }
        }
        if (NfConfig.isEnableA2dp()) {
            sleepAndCheckBtEnable();
            try {
                this.mCommand.initJniA2dp();
            } catch (RemoteException e3) {
                e3.printStackTrace();
            } catch (NullPointerException e4) {
                e4.printStackTrace();
            }
        }
        if (NfConfig.isEnableAvrcp()) {
            sleepAndCheckBtEnable();
            try {
                this.mCommand.initJniAvrcp();
            } catch (RemoteException e5) {
                e5.printStackTrace();
            } catch (NullPointerException e6) {
                e6.printStackTrace();
            }
        }
        if (NfConfig.isEnablePbap() && !NfConfig.isPbapImplementByJava()) {
            sleepAndCheckBtEnable();
            try {
                this.mCommand.initJniPbap();
            } catch (RemoteException e7) {
                e7.printStackTrace();
            } catch (NullPointerException e8) {
                e8.printStackTrace();
            }
        }
        if (NfConfig.isEnableMap() && !NfConfig.isMapImplementByJava()) {
            sleepAndCheckBtEnable();
            try {
                this.mCommand.initJniMap();
            } catch (RemoteException e9) {
                e9.printStackTrace();
            } catch (NullPointerException e10) {
                e10.printStackTrace();
            }
        }
        if (NfConfig.isEnableHid()) {
            sleepAndCheckBtEnable();
            try {
                this.mCommand.initJniHid();
            } catch (RemoteException e11) {
                e11.printStackTrace();
            } catch (NullPointerException e12) {
                e12.printStackTrace();
            }
        }
        if (NfConfig.isEnableOpp()) {
            sleepAndCheckBtEnable();
            try {
                boolean result = this.mCommand.initJniOpp();
                if (result) {
                    this.mCommand.setOppFilePath(NfPreference.getOppPath());
                }
            } catch (RemoteException e13) {
                e13.printStackTrace();
            } catch (NullPointerException e14) {
                e14.printStackTrace();
            }
        }
        if (NfConfig.isEnableGattServer()) {
            sleepAndCheckBtEnable();
            try {
                this.mCommand.initJniGattServer();
            } catch (RemoteException e15) {
                e15.printStackTrace();
            } catch (NullPointerException e16) {
                e16.printStackTrace();
            }
        }
        setJniProfileInitialed(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initJniProfileWithoutA2dpAvrcp() throws InterruptedException {
        sleepAndCheckBtEnable();
        if (NfConfig.isEnableHid()) {
            try {
                this.mCommand.initJniHid();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            }
        }
        sleepAndCheckBtEnable();
        if (NfConfig.isEnablePbap() && !NfConfig.isPbapImplementByJava()) {
            try {
                this.mCommand.initJniPbap();
            } catch (RemoteException e3) {
                e3.printStackTrace();
            } catch (NullPointerException e4) {
                e4.printStackTrace();
            }
        }
        sleepAndCheckBtEnable();
        if (NfConfig.isEnableMap() && !NfConfig.isMapImplementByJava()) {
            try {
                this.mCommand.initJniMap();
            } catch (RemoteException e5) {
                e5.printStackTrace();
            } catch (NullPointerException e6) {
                e6.printStackTrace();
            }
        }
        sleepAndCheckBtEnable();
        if (NfConfig.isEnableHfp()) {
            try {
                this.mCommand.initJniHfp();
            } catch (RemoteException e7) {
                e7.printStackTrace();
            } catch (NullPointerException e8) {
                e8.printStackTrace();
            }
        }
        sleepAndCheckBtEnable();
        if (NfConfig.isEnableOpp()) {
            try {
                boolean result = this.mCommand.initJniOpp();
                if (result) {
                    this.mCommand.setOppFilePath(NfPreference.getOppPath());
                }
            } catch (RemoteException e9) {
                e9.printStackTrace();
            } catch (NullPointerException e10) {
                e10.printStackTrace();
            }
        }
        setJniProfileInitialed(true);
    }

    private void initJniProfileOnlyA2dpAvrcp() {
        if (NfConfig.isEnableA2dp()) {
            try {
                this.mCommand.initJniA2dp();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            }
        }
        if (NfConfig.isEnableAvrcp()) {
            try {
                this.mCommand.initJniAvrcp();
            } catch (RemoteException e3) {
                e3.printStackTrace();
            } catch (NullPointerException e4) {
                e4.printStackTrace();
            }
        }
    }

    public void startInitJniProfileThread() {
        if (this.mInitJniProfileThread != null) {
            this.mInitJniProfileThread.interrupt();
            this.mInitJniProfileThread = null;
        }
        if (this.mInitJniProfileWithoutA2dpAvrcpThread != null) {
            this.mInitJniProfileWithoutA2dpAvrcpThread.interrupt();
            this.mInitJniProfileWithoutA2dpAvrcpThread = null;
        }
        this.mInitJniProfileThread = new Thread(new Runnable() { // from class: com.nforetek.bt.jni.NfJni.4
            /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
                if (r1 < 0) goto L14;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r5 = this;
                    r4 = 0
                    r1 = 12
                L3:
                    boolean r2 = com.nforetek.util.bt.NfPrimitive.isBtEnabled()
                    if (r2 == 0) goto L1e
                L9:
                    com.nforetek.bt.jni.NfJni r2 = com.nforetek.bt.jni.NfJni.this     // Catch: java.lang.InterruptedException -> L3c
                    com.nforetek.bt.jni.NfJni.access$21(r2)     // Catch: java.lang.InterruptedException -> L3c
                Le:
                    com.nforetek.bt.jni.NfJni r2 = com.nforetek.bt.jni.NfJni.this
                    r2.mInitJniProfileThread = r4
                    com.nforetek.bt.jni.NfJni r2 = com.nforetek.bt.jni.NfJni.this
                    java.lang.String r2 = com.nforetek.bt.jni.NfJni.access$0(r2)
                    java.lang.String r3 = "InitJniProfileThread finished."
                    com.nforetek.util.normal.NfLog.d(r2, r3)
                L1d:
                    return
                L1e:
                    r2 = 500(0x1f4, double:2.47E-321)
                    java.lang.Thread.sleep(r2)     // Catch: java.lang.InterruptedException -> L28
                    int r1 = r1 + (-1)
                    if (r1 >= 0) goto L3
                    goto L9
                L28:
                    r0 = move-exception
                    r0.printStackTrace()
                    com.nforetek.bt.jni.NfJni r2 = com.nforetek.bt.jni.NfJni.this
                    java.lang.String r2 = com.nforetek.bt.jni.NfJni.access$0(r2)
                    java.lang.String r3 = "InitJniProfileThread Interrupted when waiting BT enable."
                    com.nforetek.util.normal.NfLog.e(r2, r3)
                    com.nforetek.bt.jni.NfJni r2 = com.nforetek.bt.jni.NfJni.this
                    r2.mInitJniProfileThread = r4
                    goto L1d
                L3c:
                    r0 = move-exception
                    r0.printStackTrace()
                    com.nforetek.bt.jni.NfJni r2 = com.nforetek.bt.jni.NfJni.this
                    java.lang.String r2 = com.nforetek.bt.jni.NfJni.access$0(r2)
                    java.lang.String r3 = "InitJniProfileThread and error occured."
                    com.nforetek.util.normal.NfLog.e(r2, r3)
                    goto Le
                */
                throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.jni.NfJni.AnonymousClass4.run():void");
            }
        });
        this.mInitJniProfileThread.start();
    }

    public void startInitJniProfileWithoutA2dpAvrcpThread() {
        if (this.mInitJniProfileThread != null) {
            this.mInitJniProfileThread.interrupt();
            this.mInitJniProfileThread = null;
        }
        if (this.mInitJniProfileWithoutA2dpAvrcpThread != null) {
            this.mInitJniProfileWithoutA2dpAvrcpThread.interrupt();
            this.mInitJniProfileWithoutA2dpAvrcpThread = null;
        }
        this.mInitJniProfileWithoutA2dpAvrcpThread = new Thread(new Runnable() { // from class: com.nforetek.bt.jni.NfJni.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    NfJni.this.initJniProfileWithoutA2dpAvrcp();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    NfLog.e(NfJni.this.TAG, "InitJniProfileWithoutA2dpAvrcpThread and error occured.");
                }
                NfJni.this.mInitJniProfileWithoutA2dpAvrcpThread = null;
                NfLog.d(NfJni.this.TAG, "InitJniProfileWithoutA2dpAvrcpThread finished.");
            }
        });
        this.mInitJniProfileWithoutA2dpAvrcpThread.start();
    }

    public void sleepAndCheckBtEnable() throws InterruptedException {
        Thread.sleep(50);
        if (!NfPrimitive.isBtEnabled() || this.mCommand == null) {
            throw new InterruptedException();
        }
    }

    /* loaded from: classes.dex */
    private class CheckJniBindingTimeoutThread extends Thread {
        private CheckJniBindingTimeoutThread() {
        }

        /* synthetic */ CheckJniBindingTimeoutThread(NfJni nfJni, CheckJniBindingTimeoutThread checkJniBindingTimeoutThread) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            NfLog.d(NfJni.this.TAG, "CheckJniBindingTimeoutThread(" + hashCode() + ") started.");
            if (!NfJni.this.isServiceBinding()) {
                NfLog.d(NfJni.this.TAG, "CheckJniBindingTimeoutThread(" + hashCode() + ") stopped cause not in binding.");
                return;
            }
            try {
                Thread.sleep(1000L);
                NfLog.e(NfJni.this.TAG, "Check Jni binding timeout occured!!");
                NfJni.this.setServiceBinding(false);
                NfLog.d(NfJni.this.TAG, "CheckJniBindingTimeoutThread(" + hashCode() + ") stopped.");
            } catch (InterruptedException e) {
                e.printStackTrace();
                NfLog.d(NfJni.this.TAG, "CheckJniBindingTimeoutThread(" + hashCode() + ") interrupted.");
            }
        }
    }

    private boolean isJniCommandSendable() {
        if (this.mCommand != null && NfPrimitive.isBtEnabled()) {
            return true;
        }
        if (this.mCommand == null) {
            NfLog.e(this.TAG, "Jni Command interface is null!");
        }
        if (!NfPrimitive.isBtEnabled()) {
            NfLog.e(this.TAG, "Bluetooth adapter state is not enable! state is " + NfPrimitive.getAdapterState());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAfterServiceConnected() {
        try {
            if (!NfConfig.isAllowRoleSwitch()) {
                startInitJniProfileThread();
            } else if (NfConfig.isDefaultCarMode()) {
                try {
                    this.mCommand.switchBtRoleMode(1);
                    if (!NfConfig.isAfterAndroid9()) {
                        Thread.sleep(1500L);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                startInitJniProfileThread();
            } else {
                startInitJniProfileWithoutA2dpAvrcpThread();
            }
            setServiceBinded(true);
            NfJniBundle b = new NfJniBundle();
            b.setInt1(NfConfig.isDefaultCarMode() ? 1 : 2);
            Message m = Message.obtain(this.mBtHandler, 702, b);
            checkCallbackValid(m);
        } catch (NullPointerException e3) {
            e3.printStackTrace();
        }
        if (!this.mIsSetJniCallbackFinished) {
            registerJniCallback();
        }
    }

    private void registerJniCallback() {
        NfLog.v(this.TAG, "registerJniCallback");
        try {
            this.mCommand.registerJniCallback(this.mCallback);
            this.mCommand.registerJniPbapCallback(this.mCallbackPbap);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static boolean isJniProfileInitialed() {
        return sIsJniInitFinished;
    }

    public void setJniProfileInitialed(boolean isInitialed) {
        if (sIsJniInitFinished == isInitialed) {
            NfLog.e(this.TAG, "setJniProfileInitialed with same value!?");
            return;
        }
        NfLog.d(this.TAG, "setJniProfileInitialed() " + isInitialed);
        sIsJniInitFinished = isInitialed;
        NfJniBundle b1 = new NfJniBundle();
        b1.setBoolean1(sIsJniInitFinished);
        Message m1 = Message.obtain(this.mBtHandler, onJniInitFinished, b1);
        checkCallbackValid(m1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBluedroidVersion() {
        String bluedroid_version = getBluedroidVersion();
        String bluetooth_apk_version = getNfJniServiceVersion();
        NfLog.d(this.TAG, "bluedroid version: " + bluedroid_version);
        NfLog.d(this.TAG, "NfJniService version: " + bluetooth_apk_version);
        if (bluedroid_version != null) {
            NfPreference.setBluedroidVersion(bluedroid_version);
        } else {
            NfLog.e(this.TAG, "Can't get bluedroid version, start a thread try to get bluedroid version.");
            Thread t = new Thread() { // from class: com.nforetek.bt.jni.NfJni.6
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    for (int count = 10; count > 0; count--) {
                        String bluedroid_version2 = NfJni.this.getBluedroidVersion();
                        if (bluedroid_version2 != null) {
                            NfLog.d(NfJni.this.TAG, "In retry thread bluedroid version: " + bluedroid_version2);
                            NfPreference.setBluedroidVersion(bluedroid_version2);
                            return;
                        }
                        NfLog.v(NfJni.this.TAG, "In retry thread bluedroid version still is null.");
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            t.start();
        }
        if (bluetooth_apk_version != null) {
            NfPreference.setBluetoothApkVersion(bluetooth_apk_version);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processConnectionMessage(String address, Message m, Handler handler, int delay) {
        if (_NfBluetooth.isDeviceAclDisconnected(address)) {
            checkCallbackValidWithDelay(m, handler, delay);
        } else if (isMessageAlreadyInQueue(m, handler)) {
            checkCallbackValidWithDelay(m, handler, delay);
        } else {
            checkCallbackValid(m);
        }
    }
}
