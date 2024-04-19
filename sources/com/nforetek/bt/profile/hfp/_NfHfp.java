package com.nforetek.bt.profile.hfp;

import android.bluetooth.BluetoothAdapter;
import android.os.Message;
import com.nforetek.bt.aidl.INfCallbackHfp;
import com.nforetek.bt.aidl.NfHfpClientCall;
import com.nforetek.bt.callback.NfDoCallbackHfp;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.jni.NfJniBundle;
import com.nforetek.bt.jni.NfJniCallbackInterfaceHfp;
import com.nforetek.bt.profile.NfHfpCallbackInterface;
import com.nforetek.bt.profile._NfProfile;
import com.nforetek.bt.profile.bluetooth._NfBluetooth;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.Constants;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public final class _NfHfp extends _NfProfile<INfCallbackHfp, NfDoCallbackHfp, NfHfpCallbackInterface> implements NfJniCallbackInterfaceHfp {
    public static final int STATE_AUDIO_CONNECTED = 2;
    public static final int STATE_AUDIO_CONNECTING = 1;
    public static final int STATE_AUDIO_DISCONNECTED = 0;
    public static final int STATE_AUDIO_DISCONNECTING = 3;
    private List<NfHfpClientCall> mCallList;
    private long prevTransferTime;
    private long prevRequestTime = 0;
    private int TIME_PERIOD_LIMIT_REQUEST_MS = 1500;
    private int mScoState = 110;
    private int mRoaming = -1;
    private int mSignal = -1;
    private int mBattery = -1;
    private int mService = -1;
    private int mVoiceDial = -1;
    private int mIsInBandRingtone = -1;
    private String mNetworkOperator = "";
    private String mSubscriberNumber = "";
    private HashMap<Integer, NfHfpClientCall> mCallDictionary = new HashMap<>();
    private boolean mIsHfpMic = NfConfig.isHfpMicDefaultMute();
    private CheckTerminateCallThread mCheckTerminateThread = null;
    private CheckInvalidScoThread mCheckInvalidScoThread = null;
    private CheckStateConnectingThread mCheckConnectingThread = null;
    CheckScoStateBlockThread mCheckScoStateBlockThread = null;

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfHfp";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return 1;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
        if (this.mCheckTerminateThread != null) {
            this.mCheckTerminateThread.interrupt(true);
            this.mCheckTerminateThread = null;
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.profile._NfProfile
    public void onCreate() {
        super.onCreate();
        this.prevTransferTime = System.currentTimeMillis();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
        super.forceResetState();
    }

    public boolean isConnected() {
        return getProfileState() >= 140;
    }

    public boolean isConnecting() {
        return getProfileState() == 120;
    }

    public int getScoState() {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return 110;
        }
        return getNfDefState(jni().getHfpAudioState(getConnectedAddress()));
    }

    public boolean connect(String address) {
        NfLog.v(this.TAG, "connect() " + address);
        if (getProfileState() != 110) {
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
            return jni().reqHfpConnect(address);
        } else {
            return false;
        }
    }

    public boolean disconnect(String address) {
        NfLog.v(this.TAG, "disconnect() " + address);
        if (getProfileState() <= 110 || getProfileState() == 125) {
            NfLog.e(this.TAG, "State is " + getProfileState() + ". Reject disconnect command.");
            return false;
        } else if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        } else if (NfPrimitive.isAddressValid(address)) {
            return jni().reqHfpDisconnect(address);
        } else {
            return false;
        }
    }

    public int getSignal() {
        return this.mSignal;
    }

    public boolean isRoaming() {
        return this.mRoaming > 0;
    }

    public int getBattery() {
        return this.mBattery;
    }

    public boolean isTelServiceOn() {
        return this.mService > 0;
    }

    public boolean isVoiceDialOn() {
        return this.mVoiceDial > 0;
    }

    public boolean dial(String number) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        }
        Pattern p = Pattern.compile("[^0-9*#+ABC]");
        Matcher m = p.matcher(number);
        String result = m.replaceAll("").trim();
        if (!result.equals(number)) {
            NfLog.e(this.TAG, "number has invalid character: " + number + ", after trim: " + result);
        }
        return jni().reqHfpDial(getConnectedAddress(), result);
    }

    public boolean redial() {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        }
        return jni().reqHfpRedial(getConnectedAddress());
    }

    public boolean memoryDial(String index) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        } else if (!BluetoothAdapter.checkBluetoothAddress(getConnectedAddress())) {
            NfLog.e(this.TAG, "Bluetooth address (" + getConnectedAddress() + ") not valid. ");
            return false;
        } else {
            try {
                int location = Integer.parseInt(index);
                return jni().reqHfpDialMemory(getConnectedAddress(), location);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean answerCall(int flag) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        } else if (!BluetoothAdapter.checkBluetoothAddress(getConnectedAddress())) {
            NfLog.e(this.TAG, "Bluetooth address (" + getConnectedAddress() + ") not valid. ");
            return false;
        } else {
            List<NfHfpClientCall> list = getCallList();
            boolean isNeedSendHoldCall = false;
            if (list.size() == 1) {
                for (int i = 0; i < list.size(); i++) {
                    NfHfpClientCall call = list.get(i);
                    if (call.getState() == 0) {
                        isNeedSendHoldCall = true;
                    }
                }
            }
            if (isNeedSendHoldCall) {
                NfLog.v(this.TAG, "Send hold call command when call list size is 1 and state is ACTIVE.");
            }
            return isNeedSendHoldCall ? jni().holdHfpCall(getConnectedAddress()) : jni().acceptHfpCall(getConnectedAddress(), flag);
        }
    }

    public boolean rejectIncomingCall() {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        } else if (!BluetoothAdapter.checkBluetoothAddress(getConnectedAddress())) {
            NfLog.e(this.TAG, "Bluetooth address (" + getConnectedAddress() + ") not valid. ");
            return false;
        } else {
            return jni().rejectHfpCall(getConnectedAddress());
        }
    }

    public boolean reqHfpTerminateCall(int index) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        } else if (!BluetoothAdapter.checkBluetoothAddress(getConnectedAddress())) {
            NfLog.e(this.TAG, "Bluetooth address (" + getConnectedAddress() + ") not valid. ");
            return false;
        } else if (!NfConfig.isPcmScoEnable() || checkPrevRequestTime()) {
            if (getCallList().size() == 1) {
                if (this.mCheckTerminateThread != null) {
                    this.mCheckTerminateThread.interrupt(true);
                    this.mCheckTerminateThread = null;
                }
                this.mCheckTerminateThread = new CheckTerminateCallThread(index);
                this.mCheckTerminateThread.start();
            }
            return jni().terminateHfpCall(getConnectedAddress(), index);
        } else {
            return false;
        }
    }

    public boolean reqInternalHfpTerminateCall(int index) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        } else if (!BluetoothAdapter.checkBluetoothAddress(getConnectedAddress())) {
            NfLog.e(this.TAG, "Bluetooth address (" + getConnectedAddress() + ") not valid. ");
            return false;
        } else {
            return jni().terminateHfpCall(getConnectedAddress(), index);
        }
    }

    public boolean sendDtmf(String number) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        } else if (!BluetoothAdapter.checkBluetoothAddress(getConnectedAddress())) {
            NfLog.e(this.TAG, "Bluetooth address (" + getConnectedAddress() + ") not valid. ");
            return false;
        } else {
            int code = number.charAt(0);
            if ((code < 48 || code > 57) && code != 35 && code != 42 && (code < 65 || code > 68)) {
                return false;
            }
            return jni().reqHfpSendDTMF(getConnectedAddress(), (byte) code);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class CheckScoStateBlockThread extends Thread {
        CheckScoStateBlockThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Thread.sleep((long) Constants.MAX_RECORDS_IN_DATABASE);
                int state = _NfHfp.this.getScoState();
                NfLog.d(_NfHfp.this.TAG, "After " + Constants.MAX_RECORDS_IN_DATABASE + "ms delay, SCO state is " + state);
                if (state == 120) {
                    NfLog.e(_NfHfp.this.TAG, "Disconnect SCO cause state blocked.");
                    _NfHfp.this.transferToPhone(true);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                _NfHfp.this.mCheckScoStateBlockThread = null;
            }
        }
    }

    public boolean transferToCarkit() {
        boolean success = false;
        NfLog.e(this.TAG, "transferToCarkit.");
        if (!NfConfig.isPcmScoEnable() || isAcceptAudioTransfer()) {
            if (getScoState() != 110) {
                NfLog.e(this.TAG, "SCO state is " + getScoState() + " reject command.");
            } else if (jni() == null) {
                NfLog.e(this.TAG, "jni interface is null. return here.");
            } else {
                success = jni().connectHfpAudio();
                if (success) {
                    if (this.mCheckScoStateBlockThread == null) {
                        this.mCheckScoStateBlockThread = new CheckScoStateBlockThread();
                        this.mCheckScoStateBlockThread.start();
                    } else {
                        NfLog.e(this.TAG, "mCheckScoStateBlockThread is not null !!");
                    }
                }
            }
        }
        return success;
    }

    public boolean transferToPhone(boolean forceDisconnect) {
        NfLog.e(this.TAG, "transferToPhone");
        if (getScoState() != 140 && !forceDisconnect) {
            NfLog.e(this.TAG, "SCO state is " + getScoState() + " reject command.");
            return false;
        } else if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        } else {
            if (this.mCheckScoStateBlockThread != null) {
                this.mCheckScoStateBlockThread.interrupt();
                this.mCheckScoStateBlockThread = null;
            }
            return jni().disconnectHfpAudio();
        }
    }

    public String getNetworkOperator() {
        return this.mNetworkOperator;
    }

    public String getSubscriberNumber() {
        return this.mSubscriberNumber;
    }

    public List<NfHfpClientCall> getCallList() {
        if (jni() == null) {
            return new ArrayList();
        }
        this.mCallList = jni().getHfpCurrentCalls(getConnectedAddress());
        if (this.mCallList != null) {
            for (int i = 0; i < this.mCallList.size(); i++) {
                NfLog.e(this.TAG, "Piggy Check CallList: " + this.mCallList.get(i));
            }
        } else {
            this.mCallList = new ArrayList();
        }
        NfLog.e(this.TAG, "Piggy Check list: ==========================================");
        return this.mCallList;
    }

    private boolean isAcceptAudioTransfer() {
        int i;
        List<NfHfpClientCall> list = getCallList();
        if (list.size() <= 1 && !NfConfig.isAllowScoTransferBeforeCallActive()) {
            if (list.size() == 1 && this.mCallList != null) {
                while (i < this.mCallList.size()) {
                    i = (list.get(i).getState() == 0 || list.get(i).getState() == 1 || list.get(i).getState() == 6) ? 0 : i + 1;
                    return true;
                }
            }
            NfLog.e(this.TAG, "Not accept for audio transfer !!");
            return false;
        }
        return true;
    }

    public boolean voiceDial(boolean enable) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return false;
        } else if (enable) {
            return jni().startHfpVoiceRecognition(getConnectedAddress());
        } else {
            return jni().stopHfpVoiceRecognition(getConnectedAddress());
        }
    }

    public boolean multiCallControl(byte opCode) {
        return false;
    }

    public void pauseRender() {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
        } else {
            jni().pauseHfpRender();
        }
    }

    public void startRender() {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
        } else {
            jni().startHfpRender();
        }
    }

    public boolean isHfpMicMute() {
        NfLog.e(this.TAG, "isHfpMicMute() return " + this.mIsHfpMic);
        return this.mIsHfpMic;
    }

    public void muteMic(boolean mute) {
        NfLog.e(this.TAG, "muteMic() " + mute);
        if (jni() == null) {
            NfLog.e(this.TAG, "jni interface is null. return here.");
            return;
        }
        this.mIsHfpMic = mute;
        if (1 != 0) {
            jni().muteHfpMic(mute);
        }
    }

    public boolean isSupportInBandRingtone() {
        return this.mIsInBandRingtone == 1;
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
                return 100;
        }
    }

    private static String getConnectionStateString(int state) {
        switch (state) {
            case 0:
                return "Ready";
            case 1:
                return "Connecting";
            case 2:
                return "Connected";
            case 3:
                return "Disconnecting";
            default:
                return "Unknown";
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceHfp
    public void onJniHfpConnectionStateChanged(String address, int prevState, int newState) {
        NfLog.d(this.TAG, "onJniHfpConnectionStateChanged() " + address + " state: " + prevState + "->" + newState);
        setState(address, getNfDefState(newState));
        if (newState == 2) {
            this.mCallDictionary.clear();
        } else {
            this.mRoaming = -1;
            this.mSignal = -1;
            this.mBattery = -1;
            this.mService = -1;
            this.mVoiceDial = -1;
            this.mNetworkOperator = "";
            this.mSubscriberNumber = "";
            this.mIsInBandRingtone = -1;
        }
        if (getProfileState() == 120) {
            if (this.mCheckConnectingThread != null) {
                NfLog.e(this.TAG, "State is connecting but check thread is not null !?");
                this.mCheckConnectingThread.interrupt();
                this.mCheckConnectingThread = null;
            }
            this.mCheckConnectingThread = new CheckStateConnectingThread(address);
            this.mCheckConnectingThread.start();
        } else if (this.mCheckConnectingThread != null) {
            this.mCheckConnectingThread.interrupt();
            this.mCheckConnectingThread = null;
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceHfp
    public void onJniHfpAudioStateChanged(String address, int prevState, int newState) {
        NfLog.d(this.TAG, "onJniHfpAudioStateChanged()");
        this.mScoState = getNfDefState(newState);
        callback().onHfpAudioStateChanged(address, getNfDefState(prevState), getNfDefState(newState));
        switch (getNfDefState(newState)) {
            case 110:
                if (this.mCheckInvalidScoThread != null) {
                    this.mCheckInvalidScoThread.interrupt();
                    this.mCheckInvalidScoThread = null;
                    return;
                }
                return;
            case 140:
                if (this.mCheckInvalidScoThread != null) {
                    this.mCheckInvalidScoThread.interrupt();
                    this.mCheckInvalidScoThread = null;
                }
                this.mCheckInvalidScoThread = new CheckInvalidScoThread(address);
                this.mCheckInvalidScoThread.start();
                return;
            default:
                return;
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceHfp
    public void onJniHfpAgEvent(String address, int network, int roaming, int signal, int battery, String operator, int voice, int inBandRingtone, String subscriber) {
        NfLog.d(this.TAG, "onJniHfpAgEvent()");
        if (getProfileState() == 110) {
            NfLog.e(this.TAG, "Connection is disconnected. Reject update AG Event.");
            return;
        }
        if (network != -1) {
            NfLog.e(this.TAG, "Update service: " + network);
            this.mService = network;
            callback().onHfpRemoteTelecomService(getConnectedAddress(), this.mService > 0);
        }
        if (roaming != -1) {
            NfLog.e(this.TAG, "Update roaming: " + roaming);
            this.mRoaming = roaming;
            callback().onHfpRemoteRoamingStatus(getConnectedAddress(), this.mRoaming > 0);
        }
        if (signal != -1) {
            NfLog.e(this.TAG, "Update signal: " + signal);
            this.mSignal = signal;
            callback().onHfpRemoteSignalStrength(getConnectedAddress(), this.mSignal, 5, 0);
        }
        if (battery != -1) {
            NfLog.e(this.TAG, "Update battery: " + battery);
            this.mBattery = battery;
            callback().onHfpRemoteBatteryIndicator(getConnectedAddress(), this.mBattery, 5, 0);
        }
        if (voice != -1) {
            NfLog.e(this.TAG, "Update voiceDial: " + voice);
            this.mVoiceDial = voice;
            callback().onHfpVoiceDial(getConnectedAddress(), this.mVoiceDial > 0);
        }
        if (subscriber != null) {
            NfLog.e(this.TAG, "Update SubscriberNumber: " + subscriber);
            this.mSubscriberNumber = subscriber;
        }
        if (operator != null) {
            NfLog.e(this.TAG, "Update NetworkOperator: " + operator);
            this.mNetworkOperator = operator;
        }
        if (inBandRingtone != -1) {
            NfLog.e(this.TAG, "Update inBandRingtone: " + (inBandRingtone == 1));
            this.mIsInBandRingtone = inBandRingtone;
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceHfp
    public void onJniHfpCallChanged(NfHfpClientCall cc) {
        NfLog.d(this.TAG, "onJniHfpCallChanged() " + cc);
        List<NfHfpClientCall> list = getCallList();
        switch (cc.getState()) {
            case 7:
                if (NfPrimitive.isAppleDevice(getConnectedAddress())) {
                    NfLog.v(this.TAG, "Ignore sco connection check after call terminated.");
                } else if ((this.mScoState == 140 || this.mScoState == 120) && list.size() == 0) {
                    Thread t = new Thread() { // from class: com.nforetek.bt.profile.hfp._NfHfp.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            try {
                                sleep(1000L);
                                if (_NfHfp.this.getCallList().size() == 0) {
                                    _NfHfp.this.transferToPhone(true);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    t.start();
                }
                if (this.mCheckTerminateThread != null) {
                    this.mCheckTerminateThread.interrupt(true);
                    this.mCheckTerminateThread = null;
                    break;
                }
                break;
        }
        if (!NfPrimitive.isBtEnabled()) {
            NfLog.e(this.TAG, "When Bt Off get call changed callback! Drop it.");
        } else {
            callback().onHfpCallChanged(getConnectedAddress(), cc);
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
        manager().onHfpStateChanged(address, prevState, state);
        callback().onHfpStateChanged(address, prevState, state);
        if (this.mCheckInvalidScoThread != null && this.mCheckInvalidScoThread.getAddress().equals(address)) {
            this.mCheckInvalidScoThread.interrupt();
            this.mCheckInvalidScoThread = null;
        }
    }

    private void updateCallList(Integer id, NfHfpClientCall cc) {
        if (this.mCallDictionary.get(id) != null) {
            this.mCallDictionary.remove(id);
            this.mCallDictionary.put(id, cc);
            return;
        }
        NfLog.i(this.TAG, "Update NfHeadsetClientCall: " + cc);
        this.mCallDictionary.put(id, cc);
    }

    /* loaded from: classes.dex */
    private class CheckScoStateThread extends Thread {
        public CheckScoStateThread(String address) {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(1750L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (_NfHfp.this.mScoState == 140) {
                _NfHfp.this.startRender();
            }
        }
    }

    private boolean checkPrevRequestTime() {
        if (System.currentTimeMillis() - this.prevRequestTime <= NfConfig.getProtectTimeHfpTerminateCommand()) {
            NfLog.e(this.TAG, "In checkPrevRequestTime() : REJECT !!! Request command is too frequent !!!");
            NfLog.e(this.TAG, "System Time : " + System.currentTimeMillis() + " prevRequestTime Time : " + this.prevRequestTime);
            return false;
        }
        this.prevRequestTime = System.currentTimeMillis();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.profile._NfProfile
    public void onSystemTimeChanged() {
        NfLog.v(this.TAG, "onSystemTimeChanged()");
        this.prevRequestTime = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CheckTerminateCallThread extends Thread {
        int idx;
        boolean isForceFinish = false;

        public CheckTerminateCallThread(int index) {
            this.idx = -1;
            NfLog.d(_NfHfp.this.TAG, "CheckTerminateCallThread() index: " + index);
            this.idx = index;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            List<NfHfpClientCall> list = _NfHfp.this.getCallList();
            int id = -1;
            for (int i = 0; i < list.size(); i++) {
                NfHfpClientCall call = list.get(i);
                if (call.getState() == 0) {
                    id = call.getId();
                }
            }
            NfLog.d(_NfHfp.this.TAG, "Piggy Check active call id: " + id);
            if (id == -1) {
                NfLog.e(_NfHfp.this.TAG, "Can't find active call.");
                return;
            }
            try {
                Thread.sleep((long) Constants.MAX_RECORDS_IN_DATABASE);
            } catch (InterruptedException e) {
                e.printStackTrace();
                if (this.isForceFinish) {
                    return;
                }
            }
            List<NfHfpClientCall> list2 = _NfHfp.this.getCallList();
            boolean isNeedTerminateAgain = false;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                NfLog.e(_NfHfp.this.TAG, "After " + Constants.MAX_RECORDS_IN_DATABASE + "ms delay. Recheck index:" + i2 + " call state is " + list2.get(i2).getState());
                isNeedTerminateAgain = true;
            }
            if (isNeedTerminateAgain && list2.size() == 1) {
                NfLog.e(_NfHfp.this.TAG, "Terminate call again.");
                _NfHfp.this.reqInternalHfpTerminateCall(this.idx);
            }
            super.run();
            _NfHfp.this.mCheckTerminateThread = null;
        }

        public void interrupt(boolean forceFinish) {
            this.isForceFinish = forceFinish;
            super.interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CheckInvalidScoThread extends Thread {
        private String mAddr;

        public CheckInvalidScoThread(String address) {
            this.mAddr = NfDef.DEFAULT_ADDRESS;
            NfLog.d(_NfHfp.this.TAG, "CheckInvalidScoThread() " + address);
            if (address != null && !address.equals(NfDef.DEFAULT_ADDRESS)) {
                this.mAddr = address;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(2000L);
                if (!NfPrimitive.isAppleDevice(this.mAddr)) {
                    try {
                        if (_NfHfp.this.mCallList == null) {
                            NfLog.v(_NfHfp.this.TAG, "CheckInvalidScoThread() - calllist is NULL !");
                        } else if (!_NfHfp.this.isVoiceDialOn() && _NfHfp.this.mCallList.size() == 0 && _NfHfp.this.mScoState == 140) {
                            NfLog.e(_NfHfp.this.TAG, "CheckInvalidScoThread() is invalid SCO connection! Disconnect it.");
                            _NfHfp.this.transferToPhone(true);
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                _NfHfp.this.mCheckInvalidScoThread = null;
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                _NfHfp.this.mCheckInvalidScoThread = null;
            }
        }

        public String getAddress() {
            return this.mAddr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CheckStateConnectingThread extends Thread {
        private String mAddr;

        public CheckStateConnectingThread(String address) {
            this.mAddr = NfDef.DEFAULT_ADDRESS;
            NfLog.d(_NfHfp.this.TAG, "CheckStateConnectingThread() " + address);
            if (address != null && !address.equals(NfDef.DEFAULT_ADDRESS)) {
                this.mAddr = address;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (_NfHfp.this.isConnected()) {
                NfLog.d(_NfHfp.this.TAG, "Device: " + this.mAddr + " connected. CheckStateConnectingThread() finished.");
                _NfHfp.this.mCheckConnectingThread = null;
                return;
            }
            try {
                Thread.sleep(10000L);
                if (_NfHfp.this.isConnecting()) {
                    NfLog.d(_NfHfp.this.TAG, "CheckStateConnectingThread() stuck in connecting state, disconnect it.");
                    _NfHfp.this.disconnect(this.mAddr);
                }
                _NfHfp.this.mCheckConnectingThread = null;
                NfLog.d(_NfHfp.this.TAG, "CheckStateConnectingThread() " + this.mAddr + " finished.");
            } catch (InterruptedException e) {
                e.printStackTrace();
                _NfHfp.this.mCheckConnectingThread = null;
            }
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void dequeueMessage(Message msg) {
        NfJniBundle b = (NfJniBundle) msg.obj;
        switch (msg.what) {
            case NfJni.onJniHfpConnectionStateChanged /* 501 */:
                NfLog.v(this.TAG, "onJniHfpConnectionStateChanged()");
                String address = b.getString1();
                int prevState = b.getInt1();
                int newState = b.getInt2();
                int retryCount = b.getInt3();
                if (_NfBluetooth.isDeviceAclDisconnected(address) && newState >= 110) {
                    if (retryCount < 10) {
                        NfLog.e(this.TAG, "Want to set state greater than CONNECTED (" + prevState + "->" + newState + ") but ACL is disconnected! Delay it. retryCount: " + retryCount);
                        b.setInt3(retryCount + 1);
                        this.mJniHandler.sendMessageDelayed(msg, 100L);
                        return;
                    }
                    NfLog.e(this.TAG, "Over retryCount 10, process it anyway.");
                }
                onJniHfpConnectionStateChanged(address, prevState, newState);
                return;
            case NfJni.onJniHfpAudioStateChanged /* 502 */:
                NfLog.v(this.TAG, "onJniHfpAudioStateChanged()");
                onJniHfpAudioStateChanged(b.getString1(), b.getInt1(), b.getInt2());
                return;
            case NfJni.onJniHfpAgEvent /* 503 */:
                NfLog.v(this.TAG, "onJniHfpAgEvent()");
                onJniHfpAgEvent(b.getString1(), b.getInt1(), b.getInt2(), b.getInt3(), b.getInt4(), b.getString2(), b.getInt5(), b.getInt6(), b.getString3());
                return;
            case NfJni.onJniHfpCallChanged /* 504 */:
                NfLog.v(this.TAG, "onJniHfpCallChanged()");
                onJniHfpCallChanged(b.getNfHeadsetClientCall1());
                return;
            default:
                return;
        }
    }
}
