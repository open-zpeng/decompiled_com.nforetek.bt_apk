package com.nforetek.bt.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackHfp;
import com.nforetek.bt.aidl.INfCommandHfp;
import com.nforetek.bt.aidl.NfHfpClientCall;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
import java.util.List;
/* loaded from: classes.dex */
public class NfServiceHfp extends NfService {
    private INfCommandHfp.Stub mBinder = new INfCommandHfp.Stub() { // from class: com.nforetek.bt.service.NfServiceHfp.1
        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpServiceReady() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "isHfpServiceReady()");
            return NfServiceHfp.this.isNfServiceConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean registerHfpCallback(INfCallbackHfp cb) throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "registerHfpCallback()");
            if (NfServiceHfp.this.command() == null) {
                NfServiceHfp.this.addPendingCallback(cb);
                return true;
            }
            return NfServiceHfp.this.command().registerHfpCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean unregisterHfpCallback(INfCallbackHfp cb) throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "unregisterHfpCallback()");
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().unregisterHfpCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public int getHfpConnectionState() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "getHfpConnectionState()");
            if (NfServiceHfp.this.command() == null) {
                return 100;
            }
            return NfServiceHfp.this.command().getHfpConnectionState();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpConnected() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "isHfpConnected()");
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().isHfpConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public String getHfpConnectedAddress() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "getHfpConnectedAddress()");
            return NfServiceHfp.this.command() == null ? NfDef.DEFAULT_ADDRESS : NfServiceHfp.this.command().getHfpConnectedAddress();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public int getHfpAudioConnectionState() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "getHfpAudioConnectionState()");
            if (NfServiceHfp.this.command() == null) {
                return 100;
            }
            return NfServiceHfp.this.command().getHfpAudioConnectionState();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpConnect(String address) throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpConnect() " + address);
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpConnect(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpDisconnect(String address) throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpDisconnect() " + address);
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpDisconnect(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public int getHfpRemoteSignalStrength() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "getHfpRemoteSignalStrength()");
            if (NfServiceHfp.this.command() == null) {
                return -1;
            }
            return NfServiceHfp.this.command().getHfpRemoteSignalStrength();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public List<NfHfpClientCall> getHfpCallList() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "getHfpCallList()");
            if (NfServiceHfp.this.command() == null) {
                return null;
            }
            return NfServiceHfp.this.command().getHfpCallList();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpRemoteOnRoaming() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "isHfpRemoteOnRoaming()");
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().isHfpRemoteOnRoaming();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public int getHfpRemoteBatteryIndicator() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "getHfpRemoteBatteryIndicator()");
            if (NfServiceHfp.this.command() == null) {
                return -1;
            }
            return NfServiceHfp.this.command().getHfpRemoteBatteryIndicator();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpRemoteTelecomServiceOn() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "isHfpRemoteTelecomServiceOn()");
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().isHfpRemoteTelecomServiceOn();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpRemoteVoiceDialOn() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "isHfpRemoteVoiceDialOn()");
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().isHfpRemoteVoiceDialOn();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpDialCall(String number) throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpDialCall() number: " + number);
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpDialCall(number);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpReDial() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpReDial()");
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpReDial();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpMemoryDial(String index) throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpMemoryDial() index: " + index);
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpMemoryDial(index);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpAnswerCall(int flag) throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpAnswerCall() " + flag);
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpAnswerCall(flag);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpRejectIncomingCall() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpRejectIncomingCall()");
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpRejectIncomingCall();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpTerminateCurrentCall() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpTerminateCurrentCall()");
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpTerminateCurrentCall();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpSendDtmf(String number) throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpSendDtmf() number: " + number);
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpSendDtmf(number);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpAudioTransferToCarkit() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpAudioTransferToCarkit()");
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpAudioTransferToCarkit();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpAudioTransferToPhone() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpAudioTransferToPhone()");
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpAudioTransferToPhone();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public String getHfpRemoteNetworkOperator() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "getHfpRemoteNetworkOperator()");
            return NfServiceHfp.this.command() == null ? "" : NfServiceHfp.this.command().getHfpRemoteNetworkOperator();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public String getHfpRemoteSubscriberNumber() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "getHfpRemoteSubscriberNumber()");
            return NfServiceHfp.this.command() == null ? "" : NfServiceHfp.this.command().getHfpRemoteSubscriberNumber();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpVoiceDial(boolean enable) throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "reqHfpVoiceDial() enable: " + enable);
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().reqHfpVoiceDial(enable);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public void pauseHfpRender() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "pauseHfpRender()");
            if (NfServiceHfp.this.command() != null) {
                NfServiceHfp.this.command().pauseHfpRender();
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public void startHfpRender() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "startHfpRender()");
            if (NfServiceHfp.this.command() != null) {
                NfServiceHfp.this.command().startHfpRender();
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpMicMute() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "isHfpMicMute()");
            if (NfServiceHfp.this.command() == null) {
                return false;
            }
            return NfServiceHfp.this.command().isHfpMicMute();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public void muteHfpMic(boolean mute) throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "muteHfpMic() " + mute);
            if (NfServiceHfp.this.command() != null) {
                NfServiceHfp.this.command().muteHfpMic(mute);
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpInBandRingtoneSupport() throws RemoteException {
            NfLog.v(NfServiceHfp.this.TAG, "isHfpInBandRingtoneSupport()");
            return NfServiceHfp.this.command().isHfpInBandRingtoneSupport();
        }
    };

    @Override // com.nforetek.bt.service.NfService
    protected String getLogTag() {
        return "NfServiceHfp";
    }

    @Override // com.nforetek.bt.service.NfService
    public boolean isEnableService() {
        return NfConfig.isEnableHfp();
    }

    @Override // com.nforetek.bt.service.NfService
    IBinder getBinder() {
        return this.mBinder;
    }

    @Override // com.nforetek.bt.service.NfService
    protected void onNfServiceConnected(IInterface cb) throws RemoteException {
        command().registerHfpCallback((INfCallbackHfp) cb);
    }
}
