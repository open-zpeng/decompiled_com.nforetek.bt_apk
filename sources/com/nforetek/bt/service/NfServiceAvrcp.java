package com.nforetek.bt.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackAvrcp;
import com.nforetek.bt.aidl.INfCommandAvrcp;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class NfServiceAvrcp extends NfService {
    private INfCommandAvrcp.Stub mBinder = new INfCommandAvrcp.Stub() { // from class: com.nforetek.bt.service.NfServiceAvrcp.1
        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean isAvrcpServiceReady() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "isAvrcpServiceReady()");
            return NfServiceAvrcp.this.isNfServiceConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean registerAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "registerAvrcpCallback()");
            if (NfServiceAvrcp.this.command() == null) {
                NfServiceAvrcp.this.addPendingCallback(cb);
                return true;
            }
            return NfServiceAvrcp.this.command().registerAvrcpCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean unregisterAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "unregisterAvrcpCallback()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().unregisterAvrcpCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpConnect(String address) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpConnect()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpConnect(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpDisconnect(String address) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpDisconnect() " + address);
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpDisconnect(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpPlay() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpPlay()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpPlay();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpPause() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpPause()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpPause();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public int getAvrcpConnectionState() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "getAvrcpConnectionState()");
            if (NfServiceAvrcp.this.command() == null) {
                return 100;
            }
            return NfServiceAvrcp.this.command().getAvrcpConnectionState();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean isAvrcpConnected() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "isAvrcpConnected()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().isAvrcpConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public String getAvrcpConnectedAddress() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "getAvrcpConnectedAddress()");
            return NfServiceAvrcp.this.command() == null ? NfDef.DEFAULT_ADDRESS : NfServiceAvrcp.this.command().getAvrcpConnectedAddress();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean isAvrcp13Supported(String address) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "isAvrcp13Supported() " + address);
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().isAvrcp13Supported(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean isAvrcp14Supported(String address) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "isAvrcp14Supported() " + address);
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().isAvrcp14Supported(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpStop() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpStop()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpStop();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpForward() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpForward()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpForward();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpBackward() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpBackward()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpBackward();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpVolumeUp() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpVolumeUp()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpVolumeUp();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpVolumeDown() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpVolumeDown()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpVolumeDown();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpStartFastForward() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpStartFastForward()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpStartFastForward();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpStopFastForward() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpStopFastForward()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpStopFastForward();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpStartRewind() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpStartRewind( ");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpStartRewind();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpStopRewind() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpStopRewind()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpStopRewind();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetCapabilitiesSupportEvent() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp13GetCapabilitiesSupportEvent()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp13GetCapabilitiesSupportEvent();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetPlayerSettingAttributesList() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp13GetPlayerSettingAttributesList()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp13GetPlayerSettingAttributesList();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetPlayerSettingValuesList(byte attributeId) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp13GetPlayerSettingValuesList() attributeId: " + ((int) attributeId));
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp13GetPlayerSettingValuesList(attributeId);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetPlayerSettingCurrentValues() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp13GetPlayerSettingCurrentValues()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp13GetPlayerSettingCurrentValues();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13SetPlayerSettingValue(byte attributeId, byte valueId) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp13SetPlayerSettingValue() attributeId: " + ((int) attributeId) + " valueId: " + ((int) valueId));
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp13SetPlayerSettingValue(attributeId, valueId);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetElementAttributesPlaying() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp13GetElementAttributesPlaying()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp13GetElementAttributesPlaying();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetPlayStatus() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp13GetPlayStatus()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp13GetPlayStatus();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpRegisterEventWatcher(byte eventId, long interval) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpRegisterEventWatcher() eventId: " + ((int) eventId) + " interval: " + interval);
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpRegisterEventWatcher(eventId, interval);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpUnregisterEventWatcher(byte eventId) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcpUnregisterEventWatcher() eventId: " + ((int) eventId));
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcpUnregisterEventWatcher(eventId);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13NextGroup() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp13NextGroup()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp13NextGroup();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13PreviousGroup() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp13PreviousGroup()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp13PreviousGroup();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean isAvrcp14BrowsingChannelEstablished() throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "isAvrcp14BrowsingChannelEstablished()");
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().isAvrcp14BrowsingChannelEstablished();
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14SetAddressedPlayer(int playerId) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp14SetAddressedPlayer() playerId: " + playerId);
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp14SetAddressedPlayer(playerId);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14SetBrowsedPlayer(int playerId) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp14SetBrowsedPlayer() playerId: " + playerId);
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp14SetBrowsedPlayer(playerId);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14GetFolderItems(byte scopeId) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp14GetFolderItems() scopeId: " + ((int) scopeId));
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp14GetFolderItems(scopeId);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14ChangePath(int uidCounter, long uid, byte direction) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp14ChangePath() uidCounter: " + uidCounter + " uid: " + uid + " direction: " + ((int) direction));
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp14ChangePath(uidCounter, uid, direction);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14GetItemAttributes(byte scopeId, int uidCounter, long uid) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp14GetItemAttributes() scopeId: " + ((int) scopeId) + " uidCounter: " + uidCounter + " uid: " + uid);
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp14GetItemAttributes(scopeId, uidCounter, uid);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14PlaySelectedItem(byte scopeId, int uidCounter, long uid) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp14PlaySelectedItem() scopeId: " + ((int) scopeId) + " uidCounter: " + uidCounter + " uid: " + uid);
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp14PlaySelectedItem(scopeId, uidCounter, uid);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14Search(String text) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp14Search() text: " + text);
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp14Search(text);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14AddToNowPlaying(byte scopeId, int uidCounter, long uid) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp14AddToNowPlaying() scopeId: " + ((int) scopeId) + " uidCounter: " + uidCounter + " uid: " + uid);
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp14AddToNowPlaying(scopeId, uidCounter, uid);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14SetAbsoluteVolume(byte volume) throws RemoteException {
            NfLog.v(NfServiceAvrcp.this.TAG, "reqAvrcp14SetAbsoluteVolume() volume: " + ((int) volume));
            if (NfServiceAvrcp.this.command() == null) {
                return false;
            }
            return NfServiceAvrcp.this.command().reqAvrcp14SetAbsoluteVolume(volume);
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpQueryVersion(String address) throws RemoteException {
            if (NfServiceAvrcp.this.command() == null) {
            }
            return false;
        }
    };

    @Override // com.nforetek.bt.service.NfService
    protected String getLogTag() {
        return "NfServiceAvrcp";
    }

    @Override // com.nforetek.bt.service.NfService
    public boolean isEnableService() {
        return NfConfig.isEnableAvrcp();
    }

    @Override // com.nforetek.bt.service.NfService
    IBinder getBinder() {
        return this.mBinder;
    }

    @Override // com.nforetek.bt.service.NfService
    protected void onNfServiceConnected(IInterface cb) throws RemoteException {
        command().registerAvrcpCallback((INfCallbackAvrcp) cb);
    }
}
