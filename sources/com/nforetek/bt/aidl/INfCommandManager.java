package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackA2dp;
import com.nforetek.bt.aidl.INfCallbackAvrcp;
import com.nforetek.bt.aidl.INfCallbackBluetooth;
import com.nforetek.bt.aidl.INfCallbackGattServer;
import com.nforetek.bt.aidl.INfCallbackHfp;
import com.nforetek.bt.aidl.INfCallbackHid;
import com.nforetek.bt.aidl.INfCallbackMap;
import com.nforetek.bt.aidl.INfCallbackOpp;
import com.nforetek.bt.aidl.INfCallbackPbap;
import com.nforetek.bt.aidl.INfCallbackSpp;
import java.util.List;
/* loaded from: classes.dex */
public interface INfCommandManager extends IInterface {
    boolean cancelBtDiscovery() throws RemoteException;

    String getA2dpConnectedAddress() throws RemoteException;

    int getA2dpConnectionState() throws RemoteException;

    int getA2dpStreamType() throws RemoteException;

    String getAvrcpConnectedAddress() throws RemoteException;

    int getAvrcpConnectionState() throws RemoteException;

    int getBtAutoConnectCondition() throws RemoteException;

    int getBtAutoConnectPeriod() throws RemoteException;

    int getBtAutoConnectState() throws RemoteException;

    String getBtAutoConnectingAddress() throws RemoteException;

    String getBtLocalAddress() throws RemoteException;

    String getBtLocalName() throws RemoteException;

    String getBtRemoteDeviceName(String str) throws RemoteException;

    int getBtRemoteUuids(String str) throws RemoteException;

    int getBtRoleMode() throws RemoteException;

    int getBtState() throws RemoteException;

    List<ParcelUuid> getGattAddedGattCharacteristicUuidList(ParcelUuid parcelUuid) throws RemoteException;

    List<ParcelUuid> getGattAddedGattDescriptorUuidList(ParcelUuid parcelUuid, ParcelUuid parcelUuid2) throws RemoteException;

    List<ParcelUuid> getGattAddedGattServiceUuidList() throws RemoteException;

    int getGattServerConnectionState() throws RemoteException;

    int getHfpAudioConnectionState() throws RemoteException;

    List<NfHfpClientCall> getHfpCallList() throws RemoteException;

    String getHfpConnectedAddress() throws RemoteException;

    int getHfpConnectionState() throws RemoteException;

    int getHfpRemoteBatteryIndicator() throws RemoteException;

    String getHfpRemoteNetworkOperator() throws RemoteException;

    int getHfpRemoteSignalStrength() throws RemoteException;

    String getHfpRemoteSubscriberNumber() throws RemoteException;

    String getHidConnectedAddress() throws RemoteException;

    int getHidConnectionState() throws RemoteException;

    int getMapCurrentState(String str) throws RemoteException;

    int getMapRegisterState(String str) throws RemoteException;

    String getNfServiceVersionName() throws RemoteException;

    String getOppFilePath() throws RemoteException;

    int getPbapConnectionState() throws RemoteException;

    String getPbapDownloadingAddress() throws RemoteException;

    boolean isA2dpConnected() throws RemoteException;

    boolean isAvrcp13Supported(String str) throws RemoteException;

    boolean isAvrcp14BrowsingChannelEstablished() throws RemoteException;

    boolean isAvrcp14Supported(String str) throws RemoteException;

    boolean isAvrcpConnected() throws RemoteException;

    boolean isBtAutoConnectEnable() throws RemoteException;

    boolean isBtDiscoverable() throws RemoteException;

    boolean isBtDiscovering() throws RemoteException;

    boolean isBtEnabled() throws RemoteException;

    boolean isDeviceAclDisconnected(String str) throws RemoteException;

    boolean isHfpConnected() throws RemoteException;

    boolean isHfpInBandRingtoneSupport() throws RemoteException;

    boolean isHfpMicMute() throws RemoteException;

    boolean isHfpRemoteOnRoaming() throws RemoteException;

    boolean isHfpRemoteTelecomServiceOn() throws RemoteException;

    boolean isHfpRemoteVoiceDialOn() throws RemoteException;

    boolean isHidConnected() throws RemoteException;

    boolean isMapNotificationRegistered(String str) throws RemoteException;

    boolean isPbapDownloading() throws RemoteException;

    boolean isSppConnected(String str) throws RemoteException;

    void muteHfpMic(boolean z) throws RemoteException;

    void pauseA2dpRender() throws RemoteException;

    void pauseHfpRender() throws RemoteException;

    boolean registerA2dpCallback(INfCallbackA2dp iNfCallbackA2dp) throws RemoteException;

    boolean registerAvrcpCallback(INfCallbackAvrcp iNfCallbackAvrcp) throws RemoteException;

    boolean registerBtCallback(INfCallbackBluetooth iNfCallbackBluetooth) throws RemoteException;

    boolean registerGattServerCallback(INfCallbackGattServer iNfCallbackGattServer) throws RemoteException;

    boolean registerHfpCallback(INfCallbackHfp iNfCallbackHfp) throws RemoteException;

    boolean registerHidCallback(INfCallbackHid iNfCallbackHid) throws RemoteException;

    boolean registerMapCallback(INfCallbackMap iNfCallbackMap) throws RemoteException;

    boolean registerOppCallback(INfCallbackOpp iNfCallbackOpp) throws RemoteException;

    boolean registerPbapCallback(INfCallbackPbap iNfCallbackPbap) throws RemoteException;

    boolean registerSppCallback(INfCallbackSpp iNfCallbackSpp) throws RemoteException;

    boolean reqA2dpConnect(String str) throws RemoteException;

    boolean reqA2dpDisconnect(String str) throws RemoteException;

    boolean reqAvrcp13GetCapabilitiesSupportEvent() throws RemoteException;

    boolean reqAvrcp13GetElementAttributesPlaying() throws RemoteException;

    boolean reqAvrcp13GetPlayStatus() throws RemoteException;

    boolean reqAvrcp13GetPlayerSettingAttributeText(byte b) throws RemoteException;

    boolean reqAvrcp13GetPlayerSettingAttributesList() throws RemoteException;

    boolean reqAvrcp13GetPlayerSettingCurrentValues() throws RemoteException;

    boolean reqAvrcp13GetPlayerSettingValueText(byte b, byte b2) throws RemoteException;

    boolean reqAvrcp13GetPlayerSettingValuesList(byte b) throws RemoteException;

    boolean reqAvrcp13NextGroup() throws RemoteException;

    boolean reqAvrcp13PreviousGroup() throws RemoteException;

    boolean reqAvrcp13SetPlayerSettingValue(byte b, byte b2) throws RemoteException;

    boolean reqAvrcp14AddToNowPlaying(byte b, int i, long j) throws RemoteException;

    boolean reqAvrcp14ChangePath(int i, long j, byte b) throws RemoteException;

    boolean reqAvrcp14GetFolderItems(byte b) throws RemoteException;

    boolean reqAvrcp14GetItemAttributes(byte b, int i, long j) throws RemoteException;

    boolean reqAvrcp14PlaySelectedItem(byte b, int i, long j) throws RemoteException;

    boolean reqAvrcp14Search(String str) throws RemoteException;

    boolean reqAvrcp14SetAbsoluteVolume(byte b) throws RemoteException;

    boolean reqAvrcp14SetAddressedPlayer(int i) throws RemoteException;

    boolean reqAvrcp14SetBrowsedPlayer(int i) throws RemoteException;

    boolean reqAvrcpBackward() throws RemoteException;

    boolean reqAvrcpConnect(String str) throws RemoteException;

    boolean reqAvrcpDisconnect(String str) throws RemoteException;

    boolean reqAvrcpForward() throws RemoteException;

    boolean reqAvrcpPause() throws RemoteException;

    boolean reqAvrcpPlay() throws RemoteException;

    boolean reqAvrcpRegisterEventWatcher(byte b, long j) throws RemoteException;

    boolean reqAvrcpStartFastForward() throws RemoteException;

    boolean reqAvrcpStartRewind() throws RemoteException;

    boolean reqAvrcpStop() throws RemoteException;

    boolean reqAvrcpStopFastForward() throws RemoteException;

    boolean reqAvrcpStopRewind() throws RemoteException;

    boolean reqAvrcpUnregisterEventWatcher(byte b) throws RemoteException;

    boolean reqAvrcpVolumeDown() throws RemoteException;

    boolean reqAvrcpVolumeUp() throws RemoteException;

    int reqBtConnectHfpA2dp(String str) throws RemoteException;

    int reqBtDisconnectAll() throws RemoteException;

    boolean reqBtPair(String str) throws RemoteException;

    boolean reqBtPairedDevices() throws RemoteException;

    boolean reqBtUnpair(String str) throws RemoteException;

    boolean reqGattServerAddCharacteristic(ParcelUuid parcelUuid, int i, int i2) throws RemoteException;

    boolean reqGattServerAddDescriptor(ParcelUuid parcelUuid, int i) throws RemoteException;

    boolean reqGattServerBeginServiceDeclaration(int i, ParcelUuid parcelUuid) throws RemoteException;

    boolean reqGattServerClearServices() throws RemoteException;

    boolean reqGattServerDisconnect(String str) throws RemoteException;

    boolean reqGattServerEndServiceDeclaration() throws RemoteException;

    boolean reqGattServerListen(boolean z) throws RemoteException;

    boolean reqGattServerRemoveService(int i, ParcelUuid parcelUuid) throws RemoteException;

    boolean reqGattServerSendNotification(String str, int i, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, boolean z, byte[] bArr) throws RemoteException;

    boolean reqGattServerSendResponse(String str, int i, int i2, int i3, byte[] bArr) throws RemoteException;

    boolean reqHfpAnswerCall(int i) throws RemoteException;

    boolean reqHfpAudioTransferToCarkit() throws RemoteException;

    boolean reqHfpAudioTransferToPhone() throws RemoteException;

    boolean reqHfpConnect(String str) throws RemoteException;

    boolean reqHfpDialCall(String str) throws RemoteException;

    boolean reqHfpDisconnect(String str) throws RemoteException;

    boolean reqHfpMemoryDial(String str) throws RemoteException;

    boolean reqHfpReDial() throws RemoteException;

    boolean reqHfpRejectIncomingCall() throws RemoteException;

    boolean reqHfpSendDtmf(String str) throws RemoteException;

    boolean reqHfpTerminateCurrentCall() throws RemoteException;

    boolean reqHfpVoiceDial(boolean z) throws RemoteException;

    boolean reqHidConnect(String str) throws RemoteException;

    boolean reqHidDisconnect(String str) throws RemoteException;

    boolean reqMapChangeReadStatus(String str, int i, String str2, boolean z) throws RemoteException;

    void reqMapCleanDatabase() throws RemoteException;

    void reqMapDatabaseAvailable() throws RemoteException;

    void reqMapDeleteDatabaseByAddress(String str) throws RemoteException;

    boolean reqMapDeleteMessage(String str, int i, String str2) throws RemoteException;

    boolean reqMapDownloadInterrupt(String str) throws RemoteException;

    boolean reqMapDownloadMessage(String str, int i, boolean z, int i2, int i3, int i4, String str2, String str3, String str4, String str5, int i5, int i6) throws RemoteException;

    boolean reqMapDownloadSingleMessage(String str, int i, String str2, int i2) throws RemoteException;

    boolean reqMapRegisterNotification(String str, boolean z) throws RemoteException;

    boolean reqMapSendMessage(String str, String str2, String str3) throws RemoteException;

    void reqMapUnregisterNotification(String str) throws RemoteException;

    boolean reqOppAcceptReceiveFile(boolean z) throws RemoteException;

    boolean reqOppInterruptReceiveFile() throws RemoteException;

    void reqPbapCleanDatabase() throws RemoteException;

    void reqPbapDatabaseAvailable(String str) throws RemoteException;

    void reqPbapDatabaseQueryNameByNumber(String str, String str2) throws RemoteException;

    void reqPbapDatabaseQueryNameByPartialNumber(String str, String str2, int i) throws RemoteException;

    void reqPbapDeleteDatabaseByAddress(String str) throws RemoteException;

    boolean reqPbapDownload(String str, int i, int i2) throws RemoteException;

    boolean reqPbapDownloadInterrupt(String str) throws RemoteException;

    boolean reqPbapDownloadRange(String str, int i, int i2, int i3, int i4) throws RemoteException;

    boolean reqPbapDownloadRangeToContactsProvider(String str, int i, int i2, int i3, int i4) throws RemoteException;

    boolean reqPbapDownloadRangeToDatabase(String str, int i, int i2, int i3, int i4) throws RemoteException;

    boolean reqPbapDownloadToContactsProvider(String str, int i, int i2) throws RemoteException;

    boolean reqPbapDownloadToDatabase(String str, int i, int i2) throws RemoteException;

    boolean reqSendHidMouseCommand(int i, int i2, int i3, int i4) throws RemoteException;

    boolean reqSendHidVirtualKeyCommand(int i, int i2) throws RemoteException;

    boolean reqSppConnect(String str) throws RemoteException;

    void reqSppConnectedDeviceAddressList() throws RemoteException;

    boolean reqSppDisconnect(String str) throws RemoteException;

    void reqSppSendData(String str, byte[] bArr) throws RemoteException;

    boolean setA2dpLocalVolume(float f) throws RemoteException;

    boolean setA2dpStreamType(int i) throws RemoteException;

    void setBtAutoConnect(int i, int i2) throws RemoteException;

    boolean setBtDiscoverableTimeout(int i) throws RemoteException;

    boolean setBtEnable(boolean z) throws RemoteException;

    boolean setBtLocalName(String str) throws RemoteException;

    boolean setMapDownloadNotify(int i) throws RemoteException;

    boolean setOppFilePath(String str) throws RemoteException;

    boolean setPbapDownloadNotify(int i) throws RemoteException;

    void startA2dpRender() throws RemoteException;

    boolean startBtDiscovery() throws RemoteException;

    void startHfpRender() throws RemoteException;

    boolean switchBtRoleMode(int i) throws RemoteException;

    boolean unregisterA2dpCallback(INfCallbackA2dp iNfCallbackA2dp) throws RemoteException;

    boolean unregisterAvrcpCallback(INfCallbackAvrcp iNfCallbackAvrcp) throws RemoteException;

    boolean unregisterBtCallback(INfCallbackBluetooth iNfCallbackBluetooth) throws RemoteException;

    boolean unregisterGattServerCallback(INfCallbackGattServer iNfCallbackGattServer) throws RemoteException;

    boolean unregisterHfpCallback(INfCallbackHfp iNfCallbackHfp) throws RemoteException;

    boolean unregisterHidCallback(INfCallbackHid iNfCallbackHid) throws RemoteException;

    boolean unregisterMapCallback(INfCallbackMap iNfCallbackMap) throws RemoteException;

    boolean unregisterOppCallback(INfCallbackOpp iNfCallbackOpp) throws RemoteException;

    boolean unregisterPbapCallback(INfCallbackPbap iNfCallbackPbap) throws RemoteException;

    boolean unregisterSppCallback(INfCallbackSpp iNfCallbackSpp) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCommandManager {
        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean registerA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean unregisterA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getA2dpConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isA2dpConnected() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getA2dpConnectedAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqA2dpConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqA2dpDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void pauseA2dpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void startA2dpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean setA2dpLocalVolume(float vol) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean setA2dpStreamType(int type) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getA2dpStreamType() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean registerAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean unregisterAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getAvrcpConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isAvrcpConnected() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getAvrcpConnectedAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isAvrcp13Supported(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isAvrcp14Supported(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpPlay() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpStop() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpPause() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpForward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpBackward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpVolumeUp() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpVolumeDown() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpStartFastForward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpStopFastForward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpStartRewind() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpStopRewind() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp13GetCapabilitiesSupportEvent() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp13GetPlayerSettingAttributesList() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp13GetPlayerSettingValuesList(byte attributeId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp13GetPlayerSettingCurrentValues() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp13SetPlayerSettingValue(byte attributeId, byte valueId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp13GetElementAttributesPlaying() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp13GetPlayStatus() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpRegisterEventWatcher(byte eventId, long interval) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcpUnregisterEventWatcher(byte eventId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp13NextGroup() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp13PreviousGroup() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp13GetPlayerSettingAttributeText(byte attributeId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp13GetPlayerSettingValueText(byte attributeId, byte valueId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isAvrcp14BrowsingChannelEstablished() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp14SetAddressedPlayer(int playerId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp14SetBrowsedPlayer(int playerId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp14GetFolderItems(byte scopeId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp14ChangePath(int uidCounter, long uid, byte direction) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp14GetItemAttributes(byte scopeId, int uidCounter, long uid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp14PlaySelectedItem(byte scopeId, int uidCounter, long uid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp14Search(String text) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp14AddToNowPlaying(byte scopeId, int uidCounter, long uid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqAvrcp14SetAbsoluteVolume(byte volume) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean registerBtCallback(INfCallbackBluetooth cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean unregisterBtCallback(INfCallbackBluetooth cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getNfServiceVersionName() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean setBtEnable(boolean enable) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean setBtDiscoverableTimeout(int timeout) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean startBtDiscovery() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean cancelBtDiscovery() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqBtPair(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqBtUnpair(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqBtPairedDevices() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getBtLocalName() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getBtRemoteDeviceName(String address) throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getBtLocalAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean setBtLocalName(String name) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isBtEnabled() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getBtState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isBtDiscovering() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isBtDiscoverable() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isBtAutoConnectEnable() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int reqBtConnectHfpA2dp(String address) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int reqBtDisconnectAll() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getBtRemoteUuids(String address) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isDeviceAclDisconnected(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean switchBtRoleMode(int mode) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getBtRoleMode() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void setBtAutoConnect(int condition, int peroid) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getBtAutoConnectCondition() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getBtAutoConnectPeriod() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getBtAutoConnectState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getBtAutoConnectingAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean registerHfpCallback(INfCallbackHfp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean unregisterHfpCallback(INfCallbackHfp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getHfpConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isHfpConnected() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getHfpConnectedAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getHfpAudioConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getHfpRemoteSignalStrength() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public List<NfHfpClientCall> getHfpCallList() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isHfpRemoteOnRoaming() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getHfpRemoteBatteryIndicator() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isHfpRemoteTelecomServiceOn() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isHfpRemoteVoiceDialOn() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpDialCall(String number) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpReDial() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpMemoryDial(String index) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpAnswerCall(int flag) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpRejectIncomingCall() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpTerminateCurrentCall() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpSendDtmf(String number) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpAudioTransferToCarkit() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpAudioTransferToPhone() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getHfpRemoteNetworkOperator() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getHfpRemoteSubscriberNumber() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHfpVoiceDial(boolean enable) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void pauseHfpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void startHfpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isHfpMicMute() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void muteHfpMic(boolean mute) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isHfpInBandRingtoneSupport() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean registerPbapCallback(INfCallbackPbap cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean unregisterPbapCallback(INfCallbackPbap cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getPbapConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isPbapDownloading() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getPbapDownloadingAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqPbapDownload(String address, int storage, int property) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqPbapDownloadRange(String address, int storage, int property, int startPos, int offset) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqPbapDownloadToDatabase(String address, int storage, int property) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqPbapDownloadRangeToDatabase(String address, int storage, int property, int startPos, int offset) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqPbapDownloadToContactsProvider(String address, int storage, int property) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqPbapDownloadRangeToContactsProvider(String address, int storage, int property, int startPos, int offset) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void reqPbapDatabaseQueryNameByNumber(String address, String target) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void reqPbapDatabaseQueryNameByPartialNumber(String address, String target, int findPosition) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void reqPbapDatabaseAvailable(String address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void reqPbapDeleteDatabaseByAddress(String address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void reqPbapCleanDatabase() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqPbapDownloadInterrupt(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean setPbapDownloadNotify(int frequency) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean registerHidCallback(INfCallbackHid cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean unregisterHidCallback(INfCallbackHid cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHidConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqHidDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqSendHidMouseCommand(int button, int offset_x, int offset_y, int wheel) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqSendHidVirtualKeyCommand(int key_1, int key_2) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getHidConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isHidConnected() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getHidConnectedAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean registerSppCallback(INfCallbackSpp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean unregisterSppCallback(INfCallbackSpp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqSppConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqSppDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void reqSppConnectedDeviceAddressList() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isSppConnected(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void reqSppSendData(String address, byte[] sppData) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean registerMapCallback(INfCallbackMap cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean unregisterMapCallback(INfCallbackMap cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqMapDownloadSingleMessage(String address, int folder, String handle, int storage) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqMapDownloadMessage(String address, int folder, boolean isContentDownload, int count, int startPos, int storage, String periodBegin, String periodEnd, String sender, String recipient, int readStatus, int typeFilter) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqMapRegisterNotification(String address, boolean downloadNewMessage) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void reqMapUnregisterNotification(String address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean isMapNotificationRegistered(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqMapDownloadInterrupt(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void reqMapDatabaseAvailable() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void reqMapDeleteDatabaseByAddress(String address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public void reqMapCleanDatabase() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getMapCurrentState(String address) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getMapRegisterState(String address) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqMapSendMessage(String address, String message, String target) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqMapDeleteMessage(String address, int folder, String handle) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqMapChangeReadStatus(String address, int folder, String handle, boolean isReadStatus) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean setMapDownloadNotify(int frequency) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean registerOppCallback(INfCallbackOpp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean unregisterOppCallback(INfCallbackOpp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean setOppFilePath(String path) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public String getOppFilePath() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqOppAcceptReceiveFile(boolean accept) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqOppInterruptReceiveFile() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean registerGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean unregisterGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public int getGattServerConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqGattServerDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqGattServerBeginServiceDeclaration(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqGattServerAddCharacteristic(ParcelUuid charUuid, int properties, int permissions) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqGattServerAddDescriptor(ParcelUuid descUuid, int permissions) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqGattServerEndServiceDeclaration() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqGattServerRemoveService(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqGattServerClearServices() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqGattServerListen(boolean listen) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqGattServerSendResponse(String address, int requestId, int status, int offset, byte[] value) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public boolean reqGattServerSendNotification(String address, int srvcType, ParcelUuid srvcUuid, ParcelUuid charUuid, boolean confirm, byte[] value) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public List<ParcelUuid> getGattAddedGattServiceUuidList() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public List<ParcelUuid> getGattAddedGattCharacteristicUuidList(ParcelUuid srvcUuid) throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandManager
        public List<ParcelUuid> getGattAddedGattDescriptorUuidList(ParcelUuid srvcUuid, ParcelUuid charUuid) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCommandManager {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCommandManager";
        static final int TRANSACTION_cancelBtDiscovery = 62;
        static final int TRANSACTION_getA2dpConnectedAddress = 5;
        static final int TRANSACTION_getA2dpConnectionState = 3;
        static final int TRANSACTION_getA2dpStreamType = 12;
        static final int TRANSACTION_getAvrcpConnectedAddress = 17;
        static final int TRANSACTION_getAvrcpConnectionState = 15;
        static final int TRANSACTION_getBtAutoConnectCondition = 82;
        static final int TRANSACTION_getBtAutoConnectPeriod = 83;
        static final int TRANSACTION_getBtAutoConnectState = 84;
        static final int TRANSACTION_getBtAutoConnectingAddress = 85;
        static final int TRANSACTION_getBtLocalAddress = 68;
        static final int TRANSACTION_getBtLocalName = 66;
        static final int TRANSACTION_getBtRemoteDeviceName = 67;
        static final int TRANSACTION_getBtRemoteUuids = 77;
        static final int TRANSACTION_getBtRoleMode = 80;
        static final int TRANSACTION_getBtState = 71;
        static final int TRANSACTION_getGattAddedGattCharacteristicUuidList = 188;
        static final int TRANSACTION_getGattAddedGattDescriptorUuidList = 189;
        static final int TRANSACTION_getGattAddedGattServiceUuidList = 187;
        static final int TRANSACTION_getGattServerConnectionState = 176;
        static final int TRANSACTION_getHfpAudioConnectionState = 91;
        static final int TRANSACTION_getHfpCallList = 95;
        static final int TRANSACTION_getHfpConnectedAddress = 90;
        static final int TRANSACTION_getHfpConnectionState = 88;
        static final int TRANSACTION_getHfpRemoteBatteryIndicator = 97;
        static final int TRANSACTION_getHfpRemoteNetworkOperator = 109;
        static final int TRANSACTION_getHfpRemoteSignalStrength = 94;
        static final int TRANSACTION_getHfpRemoteSubscriberNumber = 110;
        static final int TRANSACTION_getHidConnectedAddress = 143;
        static final int TRANSACTION_getHidConnectionState = 141;
        static final int TRANSACTION_getMapCurrentState = 162;
        static final int TRANSACTION_getMapRegisterState = 163;
        static final int TRANSACTION_getNfServiceVersionName = 58;
        static final int TRANSACTION_getOppFilePath = 171;
        static final int TRANSACTION_getPbapConnectionState = 119;
        static final int TRANSACTION_getPbapDownloadingAddress = 121;
        static final int TRANSACTION_isA2dpConnected = 4;
        static final int TRANSACTION_isAvrcp13Supported = 20;
        static final int TRANSACTION_isAvrcp14BrowsingChannelEstablished = 46;
        static final int TRANSACTION_isAvrcp14Supported = 21;
        static final int TRANSACTION_isAvrcpConnected = 16;
        static final int TRANSACTION_isBtAutoConnectEnable = 74;
        static final int TRANSACTION_isBtDiscoverable = 73;
        static final int TRANSACTION_isBtDiscovering = 72;
        static final int TRANSACTION_isBtEnabled = 70;
        static final int TRANSACTION_isDeviceAclDisconnected = 78;
        static final int TRANSACTION_isHfpConnected = 89;
        static final int TRANSACTION_isHfpInBandRingtoneSupport = 116;
        static final int TRANSACTION_isHfpMicMute = 114;
        static final int TRANSACTION_isHfpRemoteOnRoaming = 96;
        static final int TRANSACTION_isHfpRemoteTelecomServiceOn = 98;
        static final int TRANSACTION_isHfpRemoteVoiceDialOn = 99;
        static final int TRANSACTION_isHidConnected = 142;
        static final int TRANSACTION_isMapNotificationRegistered = 157;
        static final int TRANSACTION_isPbapDownloading = 120;
        static final int TRANSACTION_isSppConnected = 149;
        static final int TRANSACTION_muteHfpMic = 115;
        static final int TRANSACTION_pauseA2dpRender = 8;
        static final int TRANSACTION_pauseHfpRender = 112;
        static final int TRANSACTION_registerA2dpCallback = 1;
        static final int TRANSACTION_registerAvrcpCallback = 13;
        static final int TRANSACTION_registerBtCallback = 56;
        static final int TRANSACTION_registerGattServerCallback = 174;
        static final int TRANSACTION_registerHfpCallback = 86;
        static final int TRANSACTION_registerHidCallback = 135;
        static final int TRANSACTION_registerMapCallback = 151;
        static final int TRANSACTION_registerOppCallback = 168;
        static final int TRANSACTION_registerPbapCallback = 117;
        static final int TRANSACTION_registerSppCallback = 144;
        static final int TRANSACTION_reqA2dpConnect = 6;
        static final int TRANSACTION_reqA2dpDisconnect = 7;
        static final int TRANSACTION_reqAvrcp13GetCapabilitiesSupportEvent = 33;
        static final int TRANSACTION_reqAvrcp13GetElementAttributesPlaying = 38;
        static final int TRANSACTION_reqAvrcp13GetPlayStatus = 39;
        static final int TRANSACTION_reqAvrcp13GetPlayerSettingAttributeText = 44;
        static final int TRANSACTION_reqAvrcp13GetPlayerSettingAttributesList = 34;
        static final int TRANSACTION_reqAvrcp13GetPlayerSettingCurrentValues = 36;
        static final int TRANSACTION_reqAvrcp13GetPlayerSettingValueText = 45;
        static final int TRANSACTION_reqAvrcp13GetPlayerSettingValuesList = 35;
        static final int TRANSACTION_reqAvrcp13NextGroup = 42;
        static final int TRANSACTION_reqAvrcp13PreviousGroup = 43;
        static final int TRANSACTION_reqAvrcp13SetPlayerSettingValue = 37;
        static final int TRANSACTION_reqAvrcp14AddToNowPlaying = 54;
        static final int TRANSACTION_reqAvrcp14ChangePath = 50;
        static final int TRANSACTION_reqAvrcp14GetFolderItems = 49;
        static final int TRANSACTION_reqAvrcp14GetItemAttributes = 51;
        static final int TRANSACTION_reqAvrcp14PlaySelectedItem = 52;
        static final int TRANSACTION_reqAvrcp14Search = 53;
        static final int TRANSACTION_reqAvrcp14SetAbsoluteVolume = 55;
        static final int TRANSACTION_reqAvrcp14SetAddressedPlayer = 47;
        static final int TRANSACTION_reqAvrcp14SetBrowsedPlayer = 48;
        static final int TRANSACTION_reqAvrcpBackward = 26;
        static final int TRANSACTION_reqAvrcpConnect = 18;
        static final int TRANSACTION_reqAvrcpDisconnect = 19;
        static final int TRANSACTION_reqAvrcpForward = 25;
        static final int TRANSACTION_reqAvrcpPause = 24;
        static final int TRANSACTION_reqAvrcpPlay = 22;
        static final int TRANSACTION_reqAvrcpRegisterEventWatcher = 40;
        static final int TRANSACTION_reqAvrcpStartFastForward = 29;
        static final int TRANSACTION_reqAvrcpStartRewind = 31;
        static final int TRANSACTION_reqAvrcpStop = 23;
        static final int TRANSACTION_reqAvrcpStopFastForward = 30;
        static final int TRANSACTION_reqAvrcpStopRewind = 32;
        static final int TRANSACTION_reqAvrcpUnregisterEventWatcher = 41;
        static final int TRANSACTION_reqAvrcpVolumeDown = 28;
        static final int TRANSACTION_reqAvrcpVolumeUp = 27;
        static final int TRANSACTION_reqBtConnectHfpA2dp = 75;
        static final int TRANSACTION_reqBtDisconnectAll = 76;
        static final int TRANSACTION_reqBtPair = 63;
        static final int TRANSACTION_reqBtPairedDevices = 65;
        static final int TRANSACTION_reqBtUnpair = 64;
        static final int TRANSACTION_reqGattServerAddCharacteristic = 179;
        static final int TRANSACTION_reqGattServerAddDescriptor = 180;
        static final int TRANSACTION_reqGattServerBeginServiceDeclaration = 178;
        static final int TRANSACTION_reqGattServerClearServices = 183;
        static final int TRANSACTION_reqGattServerDisconnect = 177;
        static final int TRANSACTION_reqGattServerEndServiceDeclaration = 181;
        static final int TRANSACTION_reqGattServerListen = 184;
        static final int TRANSACTION_reqGattServerRemoveService = 182;
        static final int TRANSACTION_reqGattServerSendNotification = 186;
        static final int TRANSACTION_reqGattServerSendResponse = 185;
        static final int TRANSACTION_reqHfpAnswerCall = 103;
        static final int TRANSACTION_reqHfpAudioTransferToCarkit = 107;
        static final int TRANSACTION_reqHfpAudioTransferToPhone = 108;
        static final int TRANSACTION_reqHfpConnect = 92;
        static final int TRANSACTION_reqHfpDialCall = 100;
        static final int TRANSACTION_reqHfpDisconnect = 93;
        static final int TRANSACTION_reqHfpMemoryDial = 102;
        static final int TRANSACTION_reqHfpReDial = 101;
        static final int TRANSACTION_reqHfpRejectIncomingCall = 104;
        static final int TRANSACTION_reqHfpSendDtmf = 106;
        static final int TRANSACTION_reqHfpTerminateCurrentCall = 105;
        static final int TRANSACTION_reqHfpVoiceDial = 111;
        static final int TRANSACTION_reqHidConnect = 137;
        static final int TRANSACTION_reqHidDisconnect = 138;
        static final int TRANSACTION_reqMapChangeReadStatus = 166;
        static final int TRANSACTION_reqMapCleanDatabase = 161;
        static final int TRANSACTION_reqMapDatabaseAvailable = 159;
        static final int TRANSACTION_reqMapDeleteDatabaseByAddress = 160;
        static final int TRANSACTION_reqMapDeleteMessage = 165;
        static final int TRANSACTION_reqMapDownloadInterrupt = 158;
        static final int TRANSACTION_reqMapDownloadMessage = 154;
        static final int TRANSACTION_reqMapDownloadSingleMessage = 153;
        static final int TRANSACTION_reqMapRegisterNotification = 155;
        static final int TRANSACTION_reqMapSendMessage = 164;
        static final int TRANSACTION_reqMapUnregisterNotification = 156;
        static final int TRANSACTION_reqOppAcceptReceiveFile = 172;
        static final int TRANSACTION_reqOppInterruptReceiveFile = 173;
        static final int TRANSACTION_reqPbapCleanDatabase = 132;
        static final int TRANSACTION_reqPbapDatabaseAvailable = 130;
        static final int TRANSACTION_reqPbapDatabaseQueryNameByNumber = 128;
        static final int TRANSACTION_reqPbapDatabaseQueryNameByPartialNumber = 129;
        static final int TRANSACTION_reqPbapDeleteDatabaseByAddress = 131;
        static final int TRANSACTION_reqPbapDownload = 122;
        static final int TRANSACTION_reqPbapDownloadInterrupt = 133;
        static final int TRANSACTION_reqPbapDownloadRange = 123;
        static final int TRANSACTION_reqPbapDownloadRangeToContactsProvider = 127;
        static final int TRANSACTION_reqPbapDownloadRangeToDatabase = 125;
        static final int TRANSACTION_reqPbapDownloadToContactsProvider = 126;
        static final int TRANSACTION_reqPbapDownloadToDatabase = 124;
        static final int TRANSACTION_reqSendHidMouseCommand = 139;
        static final int TRANSACTION_reqSendHidVirtualKeyCommand = 140;
        static final int TRANSACTION_reqSppConnect = 146;
        static final int TRANSACTION_reqSppConnectedDeviceAddressList = 148;
        static final int TRANSACTION_reqSppDisconnect = 147;
        static final int TRANSACTION_reqSppSendData = 150;
        static final int TRANSACTION_setA2dpLocalVolume = 10;
        static final int TRANSACTION_setA2dpStreamType = 11;
        static final int TRANSACTION_setBtAutoConnect = 81;
        static final int TRANSACTION_setBtDiscoverableTimeout = 60;
        static final int TRANSACTION_setBtEnable = 59;
        static final int TRANSACTION_setBtLocalName = 69;
        static final int TRANSACTION_setMapDownloadNotify = 167;
        static final int TRANSACTION_setOppFilePath = 170;
        static final int TRANSACTION_setPbapDownloadNotify = 134;
        static final int TRANSACTION_startA2dpRender = 9;
        static final int TRANSACTION_startBtDiscovery = 61;
        static final int TRANSACTION_startHfpRender = 113;
        static final int TRANSACTION_switchBtRoleMode = 79;
        static final int TRANSACTION_unregisterA2dpCallback = 2;
        static final int TRANSACTION_unregisterAvrcpCallback = 14;
        static final int TRANSACTION_unregisterBtCallback = 57;
        static final int TRANSACTION_unregisterGattServerCallback = 175;
        static final int TRANSACTION_unregisterHfpCallback = 87;
        static final int TRANSACTION_unregisterHidCallback = 136;
        static final int TRANSACTION_unregisterMapCallback = 152;
        static final int TRANSACTION_unregisterOppCallback = 169;
        static final int TRANSACTION_unregisterPbapCallback = 118;
        static final int TRANSACTION_unregisterSppCallback = 145;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCommandManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCommandManager)) {
                return (INfCommandManager) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelUuid _arg0;
            ParcelUuid _arg1;
            ParcelUuid _arg02;
            ParcelUuid _arg2;
            ParcelUuid _arg3;
            ParcelUuid _arg12;
            ParcelUuid _arg03;
            ParcelUuid _arg04;
            ParcelUuid _arg13;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackA2dp _arg05 = INfCallbackA2dp.Stub.asInterface(data.readStrongBinder());
                    boolean _result = registerA2dpCallback(_arg05);
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackA2dp _arg06 = INfCallbackA2dp.Stub.asInterface(data.readStrongBinder());
                    boolean _result2 = unregisterA2dpCallback(_arg06);
                    reply.writeNoException();
                    reply.writeInt(_result2 ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = getA2dpConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result4 = isA2dpConnected();
                    reply.writeNoException();
                    reply.writeInt(_result4 ? 1 : 0);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _result5 = getA2dpConnectedAddress();
                    reply.writeNoException();
                    reply.writeString(_result5);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    boolean _result6 = reqA2dpConnect(_arg07);
                    reply.writeNoException();
                    reply.writeInt(_result6 ? 1 : 0);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    boolean _result7 = reqA2dpDisconnect(_arg08);
                    reply.writeNoException();
                    reply.writeInt(_result7 ? 1 : 0);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    pauseA2dpRender();
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    startA2dpRender();
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    float _arg09 = data.readFloat();
                    boolean _result8 = setA2dpLocalVolume(_arg09);
                    reply.writeNoException();
                    reply.writeInt(_result8 ? 1 : 0);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg010 = data.readInt();
                    boolean _result9 = setA2dpStreamType(_arg010);
                    reply.writeNoException();
                    reply.writeInt(_result9 ? 1 : 0);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    int _result10 = getA2dpStreamType();
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackAvrcp _arg011 = INfCallbackAvrcp.Stub.asInterface(data.readStrongBinder());
                    boolean _result11 = registerAvrcpCallback(_arg011);
                    reply.writeNoException();
                    reply.writeInt(_result11 ? 1 : 0);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackAvrcp _arg012 = INfCallbackAvrcp.Stub.asInterface(data.readStrongBinder());
                    boolean _result12 = unregisterAvrcpCallback(_arg012);
                    reply.writeNoException();
                    reply.writeInt(_result12 ? 1 : 0);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    int _result13 = getAvrcpConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result13);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result14 = isAvrcpConnected();
                    reply.writeNoException();
                    reply.writeInt(_result14 ? 1 : 0);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    String _result15 = getAvrcpConnectedAddress();
                    reply.writeNoException();
                    reply.writeString(_result15);
                    return true;
                case TRANSACTION_reqAvrcpConnect /* 18 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg013 = data.readString();
                    boolean _result16 = reqAvrcpConnect(_arg013);
                    reply.writeNoException();
                    reply.writeInt(_result16 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpDisconnect /* 19 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg014 = data.readString();
                    boolean _result17 = reqAvrcpDisconnect(_arg014);
                    reply.writeNoException();
                    reply.writeInt(_result17 ? 1 : 0);
                    return true;
                case TRANSACTION_isAvrcp13Supported /* 20 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg015 = data.readString();
                    boolean _result18 = isAvrcp13Supported(_arg015);
                    reply.writeNoException();
                    reply.writeInt(_result18 ? 1 : 0);
                    return true;
                case TRANSACTION_isAvrcp14Supported /* 21 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg016 = data.readString();
                    boolean _result19 = isAvrcp14Supported(_arg016);
                    reply.writeNoException();
                    reply.writeInt(_result19 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpPlay /* 22 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result20 = reqAvrcpPlay();
                    reply.writeNoException();
                    reply.writeInt(_result20 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpStop /* 23 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result21 = reqAvrcpStop();
                    reply.writeNoException();
                    reply.writeInt(_result21 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpPause /* 24 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result22 = reqAvrcpPause();
                    reply.writeNoException();
                    reply.writeInt(_result22 ? 1 : 0);
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result23 = reqAvrcpForward();
                    reply.writeNoException();
                    reply.writeInt(_result23 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpBackward /* 26 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result24 = reqAvrcpBackward();
                    reply.writeNoException();
                    reply.writeInt(_result24 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpVolumeUp /* 27 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result25 = reqAvrcpVolumeUp();
                    reply.writeNoException();
                    reply.writeInt(_result25 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpVolumeDown /* 28 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result26 = reqAvrcpVolumeDown();
                    reply.writeNoException();
                    reply.writeInt(_result26 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpStartFastForward /* 29 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result27 = reqAvrcpStartFastForward();
                    reply.writeNoException();
                    reply.writeInt(_result27 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpStopFastForward /* 30 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result28 = reqAvrcpStopFastForward();
                    reply.writeNoException();
                    reply.writeInt(_result28 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpStartRewind /* 31 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result29 = reqAvrcpStartRewind();
                    reply.writeNoException();
                    reply.writeInt(_result29 ? 1 : 0);
                    return true;
                case 32:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result30 = reqAvrcpStopRewind();
                    reply.writeNoException();
                    reply.writeInt(_result30 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetCapabilitiesSupportEvent /* 33 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result31 = reqAvrcp13GetCapabilitiesSupportEvent();
                    reply.writeNoException();
                    reply.writeInt(_result31 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetPlayerSettingAttributesList /* 34 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result32 = reqAvrcp13GetPlayerSettingAttributesList();
                    reply.writeNoException();
                    reply.writeInt(_result32 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetPlayerSettingValuesList /* 35 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg017 = data.readByte();
                    boolean _result33 = reqAvrcp13GetPlayerSettingValuesList(_arg017);
                    reply.writeNoException();
                    reply.writeInt(_result33 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetPlayerSettingCurrentValues /* 36 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result34 = reqAvrcp13GetPlayerSettingCurrentValues();
                    reply.writeNoException();
                    reply.writeInt(_result34 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13SetPlayerSettingValue /* 37 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg018 = data.readByte();
                    byte _arg14 = data.readByte();
                    boolean _result35 = reqAvrcp13SetPlayerSettingValue(_arg018, _arg14);
                    reply.writeNoException();
                    reply.writeInt(_result35 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetElementAttributesPlaying /* 38 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result36 = reqAvrcp13GetElementAttributesPlaying();
                    reply.writeNoException();
                    reply.writeInt(_result36 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetPlayStatus /* 39 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result37 = reqAvrcp13GetPlayStatus();
                    reply.writeNoException();
                    reply.writeInt(_result37 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpRegisterEventWatcher /* 40 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg019 = data.readByte();
                    long _arg15 = data.readLong();
                    boolean _result38 = reqAvrcpRegisterEventWatcher(_arg019, _arg15);
                    reply.writeNoException();
                    reply.writeInt(_result38 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpUnregisterEventWatcher /* 41 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg020 = data.readByte();
                    boolean _result39 = reqAvrcpUnregisterEventWatcher(_arg020);
                    reply.writeNoException();
                    reply.writeInt(_result39 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13NextGroup /* 42 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result40 = reqAvrcp13NextGroup();
                    reply.writeNoException();
                    reply.writeInt(_result40 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13PreviousGroup /* 43 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result41 = reqAvrcp13PreviousGroup();
                    reply.writeNoException();
                    reply.writeInt(_result41 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetPlayerSettingAttributeText /* 44 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg021 = data.readByte();
                    boolean _result42 = reqAvrcp13GetPlayerSettingAttributeText(_arg021);
                    reply.writeNoException();
                    reply.writeInt(_result42 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetPlayerSettingValueText /* 45 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg022 = data.readByte();
                    byte _arg16 = data.readByte();
                    boolean _result43 = reqAvrcp13GetPlayerSettingValueText(_arg022, _arg16);
                    reply.writeNoException();
                    reply.writeInt(_result43 ? 1 : 0);
                    return true;
                case TRANSACTION_isAvrcp14BrowsingChannelEstablished /* 46 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result44 = isAvrcp14BrowsingChannelEstablished();
                    reply.writeNoException();
                    reply.writeInt(_result44 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14SetAddressedPlayer /* 47 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg023 = data.readInt();
                    boolean _result45 = reqAvrcp14SetAddressedPlayer(_arg023);
                    reply.writeNoException();
                    reply.writeInt(_result45 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14SetBrowsedPlayer /* 48 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg024 = data.readInt();
                    boolean _result46 = reqAvrcp14SetBrowsedPlayer(_arg024);
                    reply.writeNoException();
                    reply.writeInt(_result46 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14GetFolderItems /* 49 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg025 = data.readByte();
                    boolean _result47 = reqAvrcp14GetFolderItems(_arg025);
                    reply.writeNoException();
                    reply.writeInt(_result47 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14ChangePath /* 50 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg026 = data.readInt();
                    long _arg17 = data.readLong();
                    byte _arg22 = data.readByte();
                    boolean _result48 = reqAvrcp14ChangePath(_arg026, _arg17, _arg22);
                    reply.writeNoException();
                    reply.writeInt(_result48 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14GetItemAttributes /* 51 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg027 = data.readByte();
                    int _arg18 = data.readInt();
                    long _arg23 = data.readLong();
                    boolean _result49 = reqAvrcp14GetItemAttributes(_arg027, _arg18, _arg23);
                    reply.writeNoException();
                    reply.writeInt(_result49 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14PlaySelectedItem /* 52 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg028 = data.readByte();
                    int _arg19 = data.readInt();
                    long _arg24 = data.readLong();
                    boolean _result50 = reqAvrcp14PlaySelectedItem(_arg028, _arg19, _arg24);
                    reply.writeNoException();
                    reply.writeInt(_result50 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14Search /* 53 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg029 = data.readString();
                    boolean _result51 = reqAvrcp14Search(_arg029);
                    reply.writeNoException();
                    reply.writeInt(_result51 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14AddToNowPlaying /* 54 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg030 = data.readByte();
                    int _arg110 = data.readInt();
                    long _arg25 = data.readLong();
                    boolean _result52 = reqAvrcp14AddToNowPlaying(_arg030, _arg110, _arg25);
                    reply.writeNoException();
                    reply.writeInt(_result52 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14SetAbsoluteVolume /* 55 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg031 = data.readByte();
                    boolean _result53 = reqAvrcp14SetAbsoluteVolume(_arg031);
                    reply.writeNoException();
                    reply.writeInt(_result53 ? 1 : 0);
                    return true;
                case TRANSACTION_registerBtCallback /* 56 */:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackBluetooth _arg032 = INfCallbackBluetooth.Stub.asInterface(data.readStrongBinder());
                    boolean _result54 = registerBtCallback(_arg032);
                    reply.writeNoException();
                    reply.writeInt(_result54 ? 1 : 0);
                    return true;
                case TRANSACTION_unregisterBtCallback /* 57 */:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackBluetooth _arg033 = INfCallbackBluetooth.Stub.asInterface(data.readStrongBinder());
                    boolean _result55 = unregisterBtCallback(_arg033);
                    reply.writeNoException();
                    reply.writeInt(_result55 ? 1 : 0);
                    return true;
                case TRANSACTION_getNfServiceVersionName /* 58 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _result56 = getNfServiceVersionName();
                    reply.writeNoException();
                    reply.writeString(_result56);
                    return true;
                case TRANSACTION_setBtEnable /* 59 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg034 = data.readInt() != 0;
                    boolean _result57 = setBtEnable(_arg034);
                    reply.writeNoException();
                    reply.writeInt(_result57 ? 1 : 0);
                    return true;
                case TRANSACTION_setBtDiscoverableTimeout /* 60 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg035 = data.readInt();
                    boolean _result58 = setBtDiscoverableTimeout(_arg035);
                    reply.writeNoException();
                    reply.writeInt(_result58 ? 1 : 0);
                    return true;
                case TRANSACTION_startBtDiscovery /* 61 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result59 = startBtDiscovery();
                    reply.writeNoException();
                    reply.writeInt(_result59 ? 1 : 0);
                    return true;
                case TRANSACTION_cancelBtDiscovery /* 62 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result60 = cancelBtDiscovery();
                    reply.writeNoException();
                    reply.writeInt(_result60 ? 1 : 0);
                    return true;
                case TRANSACTION_reqBtPair /* 63 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg036 = data.readString();
                    boolean _result61 = reqBtPair(_arg036);
                    reply.writeNoException();
                    reply.writeInt(_result61 ? 1 : 0);
                    return true;
                case 64:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg037 = data.readString();
                    boolean _result62 = reqBtUnpair(_arg037);
                    reply.writeNoException();
                    reply.writeInt(_result62 ? 1 : 0);
                    return true;
                case TRANSACTION_reqBtPairedDevices /* 65 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result63 = reqBtPairedDevices();
                    reply.writeNoException();
                    reply.writeInt(_result63 ? 1 : 0);
                    return true;
                case 66:
                    data.enforceInterface(DESCRIPTOR);
                    String _result64 = getBtLocalName();
                    reply.writeNoException();
                    reply.writeString(_result64);
                    return true;
                case TRANSACTION_getBtRemoteDeviceName /* 67 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg038 = data.readString();
                    String _result65 = getBtRemoteDeviceName(_arg038);
                    reply.writeNoException();
                    reply.writeString(_result65);
                    return true;
                case 68:
                    data.enforceInterface(DESCRIPTOR);
                    String _result66 = getBtLocalAddress();
                    reply.writeNoException();
                    reply.writeString(_result66);
                    return true;
                case TRANSACTION_setBtLocalName /* 69 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg039 = data.readString();
                    boolean _result67 = setBtLocalName(_arg039);
                    reply.writeNoException();
                    reply.writeInt(_result67 ? 1 : 0);
                    return true;
                case 70:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result68 = isBtEnabled();
                    reply.writeNoException();
                    reply.writeInt(_result68 ? 1 : 0);
                    return true;
                case 71:
                    data.enforceInterface(DESCRIPTOR);
                    int _result69 = getBtState();
                    reply.writeNoException();
                    reply.writeInt(_result69);
                    return true;
                case 72:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result70 = isBtDiscovering();
                    reply.writeNoException();
                    reply.writeInt(_result70 ? 1 : 0);
                    return true;
                case 73:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result71 = isBtDiscoverable();
                    reply.writeNoException();
                    reply.writeInt(_result71 ? 1 : 0);
                    return true;
                case 74:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result72 = isBtAutoConnectEnable();
                    reply.writeNoException();
                    reply.writeInt(_result72 ? 1 : 0);
                    return true;
                case TRANSACTION_reqBtConnectHfpA2dp /* 75 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg040 = data.readString();
                    int _result73 = reqBtConnectHfpA2dp(_arg040);
                    reply.writeNoException();
                    reply.writeInt(_result73);
                    return true;
                case 76:
                    data.enforceInterface(DESCRIPTOR);
                    int _result74 = reqBtDisconnectAll();
                    reply.writeNoException();
                    reply.writeInt(_result74);
                    return true;
                case 77:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg041 = data.readString();
                    int _result75 = getBtRemoteUuids(_arg041);
                    reply.writeNoException();
                    reply.writeInt(_result75);
                    return true;
                case 78:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg042 = data.readString();
                    boolean _result76 = isDeviceAclDisconnected(_arg042);
                    reply.writeNoException();
                    reply.writeInt(_result76 ? 1 : 0);
                    return true;
                case 79:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg043 = data.readInt();
                    boolean _result77 = switchBtRoleMode(_arg043);
                    reply.writeNoException();
                    reply.writeInt(_result77 ? 1 : 0);
                    return true;
                case TRANSACTION_getBtRoleMode /* 80 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result78 = getBtRoleMode();
                    reply.writeNoException();
                    reply.writeInt(_result78);
                    return true;
                case TRANSACTION_setBtAutoConnect /* 81 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg044 = data.readInt();
                    int _arg111 = data.readInt();
                    setBtAutoConnect(_arg044, _arg111);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getBtAutoConnectCondition /* 82 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result79 = getBtAutoConnectCondition();
                    reply.writeNoException();
                    reply.writeInt(_result79);
                    return true;
                case TRANSACTION_getBtAutoConnectPeriod /* 83 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result80 = getBtAutoConnectPeriod();
                    reply.writeNoException();
                    reply.writeInt(_result80);
                    return true;
                case TRANSACTION_getBtAutoConnectState /* 84 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result81 = getBtAutoConnectState();
                    reply.writeNoException();
                    reply.writeInt(_result81);
                    return true;
                case TRANSACTION_getBtAutoConnectingAddress /* 85 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _result82 = getBtAutoConnectingAddress();
                    reply.writeNoException();
                    reply.writeString(_result82);
                    return true;
                case TRANSACTION_registerHfpCallback /* 86 */:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackHfp _arg045 = INfCallbackHfp.Stub.asInterface(data.readStrongBinder());
                    boolean _result83 = registerHfpCallback(_arg045);
                    reply.writeNoException();
                    reply.writeInt(_result83 ? 1 : 0);
                    return true;
                case TRANSACTION_unregisterHfpCallback /* 87 */:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackHfp _arg046 = INfCallbackHfp.Stub.asInterface(data.readStrongBinder());
                    boolean _result84 = unregisterHfpCallback(_arg046);
                    reply.writeNoException();
                    reply.writeInt(_result84 ? 1 : 0);
                    return true;
                case TRANSACTION_getHfpConnectionState /* 88 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result85 = getHfpConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result85);
                    return true;
                case TRANSACTION_isHfpConnected /* 89 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result86 = isHfpConnected();
                    reply.writeNoException();
                    reply.writeInt(_result86 ? 1 : 0);
                    return true;
                case TRANSACTION_getHfpConnectedAddress /* 90 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _result87 = getHfpConnectedAddress();
                    reply.writeNoException();
                    reply.writeString(_result87);
                    return true;
                case TRANSACTION_getHfpAudioConnectionState /* 91 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result88 = getHfpAudioConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result88);
                    return true;
                case TRANSACTION_reqHfpConnect /* 92 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg047 = data.readString();
                    boolean _result89 = reqHfpConnect(_arg047);
                    reply.writeNoException();
                    reply.writeInt(_result89 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpDisconnect /* 93 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg048 = data.readString();
                    boolean _result90 = reqHfpDisconnect(_arg048);
                    reply.writeNoException();
                    reply.writeInt(_result90 ? 1 : 0);
                    return true;
                case 94:
                    data.enforceInterface(DESCRIPTOR);
                    int _result91 = getHfpRemoteSignalStrength();
                    reply.writeNoException();
                    reply.writeInt(_result91);
                    return true;
                case 95:
                    data.enforceInterface(DESCRIPTOR);
                    List<NfHfpClientCall> _result92 = getHfpCallList();
                    reply.writeNoException();
                    reply.writeTypedList(_result92);
                    return true;
                case 96:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result93 = isHfpRemoteOnRoaming();
                    reply.writeNoException();
                    reply.writeInt(_result93 ? 1 : 0);
                    return true;
                case 97:
                    data.enforceInterface(DESCRIPTOR);
                    int _result94 = getHfpRemoteBatteryIndicator();
                    reply.writeNoException();
                    reply.writeInt(_result94);
                    return true;
                case 98:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result95 = isHfpRemoteTelecomServiceOn();
                    reply.writeNoException();
                    reply.writeInt(_result95 ? 1 : 0);
                    return true;
                case 99:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result96 = isHfpRemoteVoiceDialOn();
                    reply.writeNoException();
                    reply.writeInt(_result96 ? 1 : 0);
                    return true;
                case 100:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg049 = data.readString();
                    boolean _result97 = reqHfpDialCall(_arg049);
                    reply.writeNoException();
                    reply.writeInt(_result97 ? 1 : 0);
                    return true;
                case 101:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result98 = reqHfpReDial();
                    reply.writeNoException();
                    reply.writeInt(_result98 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpMemoryDial /* 102 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg050 = data.readString();
                    boolean _result99 = reqHfpMemoryDial(_arg050);
                    reply.writeNoException();
                    reply.writeInt(_result99 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpAnswerCall /* 103 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg051 = data.readInt();
                    boolean _result100 = reqHfpAnswerCall(_arg051);
                    reply.writeNoException();
                    reply.writeInt(_result100 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpRejectIncomingCall /* 104 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result101 = reqHfpRejectIncomingCall();
                    reply.writeNoException();
                    reply.writeInt(_result101 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpTerminateCurrentCall /* 105 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result102 = reqHfpTerminateCurrentCall();
                    reply.writeNoException();
                    reply.writeInt(_result102 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpSendDtmf /* 106 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg052 = data.readString();
                    boolean _result103 = reqHfpSendDtmf(_arg052);
                    reply.writeNoException();
                    reply.writeInt(_result103 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpAudioTransferToCarkit /* 107 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result104 = reqHfpAudioTransferToCarkit();
                    reply.writeNoException();
                    reply.writeInt(_result104 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpAudioTransferToPhone /* 108 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result105 = reqHfpAudioTransferToPhone();
                    reply.writeNoException();
                    reply.writeInt(_result105 ? 1 : 0);
                    return true;
                case TRANSACTION_getHfpRemoteNetworkOperator /* 109 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _result106 = getHfpRemoteNetworkOperator();
                    reply.writeNoException();
                    reply.writeString(_result106);
                    return true;
                case 110:
                    data.enforceInterface(DESCRIPTOR);
                    String _result107 = getHfpRemoteSubscriberNumber();
                    reply.writeNoException();
                    reply.writeString(_result107);
                    return true;
                case 111:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg053 = data.readInt() != 0;
                    boolean _result108 = reqHfpVoiceDial(_arg053);
                    reply.writeNoException();
                    reply.writeInt(_result108 ? 1 : 0);
                    return true;
                case 112:
                    data.enforceInterface(DESCRIPTOR);
                    pauseHfpRender();
                    reply.writeNoException();
                    return true;
                case 113:
                    data.enforceInterface(DESCRIPTOR);
                    startHfpRender();
                    reply.writeNoException();
                    return true;
                case 114:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result109 = isHfpMicMute();
                    reply.writeNoException();
                    reply.writeInt(_result109 ? 1 : 0);
                    return true;
                case TRANSACTION_muteHfpMic /* 115 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg054 = data.readInt() != 0;
                    muteHfpMic(_arg054);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_isHfpInBandRingtoneSupport /* 116 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result110 = isHfpInBandRingtoneSupport();
                    reply.writeNoException();
                    reply.writeInt(_result110 ? 1 : 0);
                    return true;
                case TRANSACTION_registerPbapCallback /* 117 */:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackPbap _arg055 = INfCallbackPbap.Stub.asInterface(data.readStrongBinder());
                    boolean _result111 = registerPbapCallback(_arg055);
                    reply.writeNoException();
                    reply.writeInt(_result111 ? 1 : 0);
                    return true;
                case TRANSACTION_unregisterPbapCallback /* 118 */:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackPbap _arg056 = INfCallbackPbap.Stub.asInterface(data.readStrongBinder());
                    boolean _result112 = unregisterPbapCallback(_arg056);
                    reply.writeNoException();
                    reply.writeInt(_result112 ? 1 : 0);
                    return true;
                case TRANSACTION_getPbapConnectionState /* 119 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result113 = getPbapConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result113);
                    return true;
                case 120:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result114 = isPbapDownloading();
                    reply.writeNoException();
                    reply.writeInt(_result114 ? 1 : 0);
                    return true;
                case 121:
                    data.enforceInterface(DESCRIPTOR);
                    String _result115 = getPbapDownloadingAddress();
                    reply.writeNoException();
                    reply.writeString(_result115);
                    return true;
                case 122:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg057 = data.readString();
                    int _arg112 = data.readInt();
                    int _arg26 = data.readInt();
                    boolean _result116 = reqPbapDownload(_arg057, _arg112, _arg26);
                    reply.writeNoException();
                    reply.writeInt(_result116 ? 1 : 0);
                    return true;
                case 123:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg058 = data.readString();
                    int _arg113 = data.readInt();
                    int _arg27 = data.readInt();
                    int _arg32 = data.readInt();
                    int _arg4 = data.readInt();
                    boolean _result117 = reqPbapDownloadRange(_arg058, _arg113, _arg27, _arg32, _arg4);
                    reply.writeNoException();
                    reply.writeInt(_result117 ? 1 : 0);
                    return true;
                case TRANSACTION_reqPbapDownloadToDatabase /* 124 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg059 = data.readString();
                    int _arg114 = data.readInt();
                    int _arg28 = data.readInt();
                    boolean _result118 = reqPbapDownloadToDatabase(_arg059, _arg114, _arg28);
                    reply.writeNoException();
                    reply.writeInt(_result118 ? 1 : 0);
                    return true;
                case 125:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg060 = data.readString();
                    int _arg115 = data.readInt();
                    int _arg29 = data.readInt();
                    int _arg33 = data.readInt();
                    int _arg42 = data.readInt();
                    boolean _result119 = reqPbapDownloadRangeToDatabase(_arg060, _arg115, _arg29, _arg33, _arg42);
                    reply.writeNoException();
                    reply.writeInt(_result119 ? 1 : 0);
                    return true;
                case TRANSACTION_reqPbapDownloadToContactsProvider /* 126 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg061 = data.readString();
                    int _arg116 = data.readInt();
                    int _arg210 = data.readInt();
                    boolean _result120 = reqPbapDownloadToContactsProvider(_arg061, _arg116, _arg210);
                    reply.writeNoException();
                    reply.writeInt(_result120 ? 1 : 0);
                    return true;
                case TRANSACTION_reqPbapDownloadRangeToContactsProvider /* 127 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg062 = data.readString();
                    int _arg117 = data.readInt();
                    int _arg211 = data.readInt();
                    int _arg34 = data.readInt();
                    int _arg43 = data.readInt();
                    boolean _result121 = reqPbapDownloadRangeToContactsProvider(_arg062, _arg117, _arg211, _arg34, _arg43);
                    reply.writeNoException();
                    reply.writeInt(_result121 ? 1 : 0);
                    return true;
                case 128:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg063 = data.readString();
                    String _arg118 = data.readString();
                    reqPbapDatabaseQueryNameByNumber(_arg063, _arg118);
                    reply.writeNoException();
                    return true;
                case 129:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg064 = data.readString();
                    String _arg119 = data.readString();
                    int _arg212 = data.readInt();
                    reqPbapDatabaseQueryNameByPartialNumber(_arg064, _arg119, _arg212);
                    reply.writeNoException();
                    return true;
                case 130:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg065 = data.readString();
                    reqPbapDatabaseAvailable(_arg065);
                    reply.writeNoException();
                    return true;
                case 131:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg066 = data.readString();
                    reqPbapDeleteDatabaseByAddress(_arg066);
                    reply.writeNoException();
                    return true;
                case 132:
                    data.enforceInterface(DESCRIPTOR);
                    reqPbapCleanDatabase();
                    reply.writeNoException();
                    return true;
                case 133:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg067 = data.readString();
                    boolean _result122 = reqPbapDownloadInterrupt(_arg067);
                    reply.writeNoException();
                    reply.writeInt(_result122 ? 1 : 0);
                    return true;
                case 134:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg068 = data.readInt();
                    boolean _result123 = setPbapDownloadNotify(_arg068);
                    reply.writeNoException();
                    reply.writeInt(_result123 ? 1 : 0);
                    return true;
                case 135:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackHid _arg069 = INfCallbackHid.Stub.asInterface(data.readStrongBinder());
                    boolean _result124 = registerHidCallback(_arg069);
                    reply.writeNoException();
                    reply.writeInt(_result124 ? 1 : 0);
                    return true;
                case 136:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackHid _arg070 = INfCallbackHid.Stub.asInterface(data.readStrongBinder());
                    boolean _result125 = unregisterHidCallback(_arg070);
                    reply.writeNoException();
                    reply.writeInt(_result125 ? 1 : 0);
                    return true;
                case 137:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg071 = data.readString();
                    boolean _result126 = reqHidConnect(_arg071);
                    reply.writeNoException();
                    reply.writeInt(_result126 ? 1 : 0);
                    return true;
                case 138:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg072 = data.readString();
                    boolean _result127 = reqHidDisconnect(_arg072);
                    reply.writeNoException();
                    reply.writeInt(_result127 ? 1 : 0);
                    return true;
                case 139:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg073 = data.readInt();
                    int _arg120 = data.readInt();
                    int _arg213 = data.readInt();
                    int _arg35 = data.readInt();
                    boolean _result128 = reqSendHidMouseCommand(_arg073, _arg120, _arg213, _arg35);
                    reply.writeNoException();
                    reply.writeInt(_result128 ? 1 : 0);
                    return true;
                case 140:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg074 = data.readInt();
                    int _arg121 = data.readInt();
                    boolean _result129 = reqSendHidVirtualKeyCommand(_arg074, _arg121);
                    reply.writeNoException();
                    reply.writeInt(_result129 ? 1 : 0);
                    return true;
                case 141:
                    data.enforceInterface(DESCRIPTOR);
                    int _result130 = getHidConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result130);
                    return true;
                case 142:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result131 = isHidConnected();
                    reply.writeNoException();
                    reply.writeInt(_result131 ? 1 : 0);
                    return true;
                case TRANSACTION_getHidConnectedAddress /* 143 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _result132 = getHidConnectedAddress();
                    reply.writeNoException();
                    reply.writeString(_result132);
                    return true;
                case 144:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackSpp _arg075 = INfCallbackSpp.Stub.asInterface(data.readStrongBinder());
                    boolean _result133 = registerSppCallback(_arg075);
                    reply.writeNoException();
                    reply.writeInt(_result133 ? 1 : 0);
                    return true;
                case 145:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackSpp _arg076 = INfCallbackSpp.Stub.asInterface(data.readStrongBinder());
                    boolean _result134 = unregisterSppCallback(_arg076);
                    reply.writeNoException();
                    reply.writeInt(_result134 ? 1 : 0);
                    return true;
                case TRANSACTION_reqSppConnect /* 146 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg077 = data.readString();
                    boolean _result135 = reqSppConnect(_arg077);
                    reply.writeNoException();
                    reply.writeInt(_result135 ? 1 : 0);
                    return true;
                case TRANSACTION_reqSppDisconnect /* 147 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg078 = data.readString();
                    boolean _result136 = reqSppDisconnect(_arg078);
                    reply.writeNoException();
                    reply.writeInt(_result136 ? 1 : 0);
                    return true;
                case TRANSACTION_reqSppConnectedDeviceAddressList /* 148 */:
                    data.enforceInterface(DESCRIPTOR);
                    reqSppConnectedDeviceAddressList();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_isSppConnected /* 149 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg079 = data.readString();
                    boolean _result137 = isSppConnected(_arg079);
                    reply.writeNoException();
                    reply.writeInt(_result137 ? 1 : 0);
                    return true;
                case 150:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg080 = data.readString();
                    byte[] _arg122 = data.createByteArray();
                    reqSppSendData(_arg080, _arg122);
                    reply.writeNoException();
                    return true;
                case 151:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackMap _arg081 = INfCallbackMap.Stub.asInterface(data.readStrongBinder());
                    boolean _result138 = registerMapCallback(_arg081);
                    reply.writeNoException();
                    reply.writeInt(_result138 ? 1 : 0);
                    return true;
                case 152:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackMap _arg082 = INfCallbackMap.Stub.asInterface(data.readStrongBinder());
                    boolean _result139 = unregisterMapCallback(_arg082);
                    reply.writeNoException();
                    reply.writeInt(_result139 ? 1 : 0);
                    return true;
                case TRANSACTION_reqMapDownloadSingleMessage /* 153 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg083 = data.readString();
                    int _arg123 = data.readInt();
                    String _arg214 = data.readString();
                    int _arg36 = data.readInt();
                    boolean _result140 = reqMapDownloadSingleMessage(_arg083, _arg123, _arg214, _arg36);
                    reply.writeNoException();
                    reply.writeInt(_result140 ? 1 : 0);
                    return true;
                case TRANSACTION_reqMapDownloadMessage /* 154 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg084 = data.readString();
                    int _arg124 = data.readInt();
                    boolean _arg215 = data.readInt() != 0;
                    int _arg37 = data.readInt();
                    int _arg44 = data.readInt();
                    int _arg5 = data.readInt();
                    String _arg6 = data.readString();
                    String _arg7 = data.readString();
                    String _arg8 = data.readString();
                    String _arg9 = data.readString();
                    int _arg10 = data.readInt();
                    int _arg11 = data.readInt();
                    boolean _result141 = reqMapDownloadMessage(_arg084, _arg124, _arg215, _arg37, _arg44, _arg5, _arg6, _arg7, _arg8, _arg9, _arg10, _arg11);
                    reply.writeNoException();
                    reply.writeInt(_result141 ? 1 : 0);
                    return true;
                case TRANSACTION_reqMapRegisterNotification /* 155 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg085 = data.readString();
                    boolean _arg125 = data.readInt() != 0;
                    boolean _result142 = reqMapRegisterNotification(_arg085, _arg125);
                    reply.writeNoException();
                    reply.writeInt(_result142 ? 1 : 0);
                    return true;
                case TRANSACTION_reqMapUnregisterNotification /* 156 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg086 = data.readString();
                    reqMapUnregisterNotification(_arg086);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_isMapNotificationRegistered /* 157 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg087 = data.readString();
                    boolean _result143 = isMapNotificationRegistered(_arg087);
                    reply.writeNoException();
                    reply.writeInt(_result143 ? 1 : 0);
                    return true;
                case TRANSACTION_reqMapDownloadInterrupt /* 158 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg088 = data.readString();
                    boolean _result144 = reqMapDownloadInterrupt(_arg088);
                    reply.writeNoException();
                    reply.writeInt(_result144 ? 1 : 0);
                    return true;
                case TRANSACTION_reqMapDatabaseAvailable /* 159 */:
                    data.enforceInterface(DESCRIPTOR);
                    reqMapDatabaseAvailable();
                    reply.writeNoException();
                    return true;
                case 160:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg089 = data.readString();
                    reqMapDeleteDatabaseByAddress(_arg089);
                    reply.writeNoException();
                    return true;
                case 161:
                    data.enforceInterface(DESCRIPTOR);
                    reqMapCleanDatabase();
                    reply.writeNoException();
                    return true;
                case 162:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg090 = data.readString();
                    int _result145 = getMapCurrentState(_arg090);
                    reply.writeNoException();
                    reply.writeInt(_result145);
                    return true;
                case 163:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg091 = data.readString();
                    int _result146 = getMapRegisterState(_arg091);
                    reply.writeNoException();
                    reply.writeInt(_result146);
                    return true;
                case 164:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg092 = data.readString();
                    String _arg126 = data.readString();
                    String _arg216 = data.readString();
                    boolean _result147 = reqMapSendMessage(_arg092, _arg126, _arg216);
                    reply.writeNoException();
                    reply.writeInt(_result147 ? 1 : 0);
                    return true;
                case 165:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg093 = data.readString();
                    int _arg127 = data.readInt();
                    String _arg217 = data.readString();
                    boolean _result148 = reqMapDeleteMessage(_arg093, _arg127, _arg217);
                    reply.writeNoException();
                    reply.writeInt(_result148 ? 1 : 0);
                    return true;
                case 166:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg094 = data.readString();
                    int _arg128 = data.readInt();
                    String _arg218 = data.readString();
                    boolean _arg38 = data.readInt() != 0;
                    boolean _result149 = reqMapChangeReadStatus(_arg094, _arg128, _arg218, _arg38);
                    reply.writeNoException();
                    reply.writeInt(_result149 ? 1 : 0);
                    return true;
                case TRANSACTION_setMapDownloadNotify /* 167 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg095 = data.readInt();
                    boolean _result150 = setMapDownloadNotify(_arg095);
                    reply.writeNoException();
                    reply.writeInt(_result150 ? 1 : 0);
                    return true;
                case TRANSACTION_registerOppCallback /* 168 */:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackOpp _arg096 = INfCallbackOpp.Stub.asInterface(data.readStrongBinder());
                    boolean _result151 = registerOppCallback(_arg096);
                    reply.writeNoException();
                    reply.writeInt(_result151 ? 1 : 0);
                    return true;
                case TRANSACTION_unregisterOppCallback /* 169 */:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackOpp _arg097 = INfCallbackOpp.Stub.asInterface(data.readStrongBinder());
                    boolean _result152 = unregisterOppCallback(_arg097);
                    reply.writeNoException();
                    reply.writeInt(_result152 ? 1 : 0);
                    return true;
                case 170:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg098 = data.readString();
                    boolean _result153 = setOppFilePath(_arg098);
                    reply.writeNoException();
                    reply.writeInt(_result153 ? 1 : 0);
                    return true;
                case TRANSACTION_getOppFilePath /* 171 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _result154 = getOppFilePath();
                    reply.writeNoException();
                    reply.writeString(_result154);
                    return true;
                case TRANSACTION_reqOppAcceptReceiveFile /* 172 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg099 = data.readInt() != 0;
                    boolean _result155 = reqOppAcceptReceiveFile(_arg099);
                    reply.writeNoException();
                    reply.writeInt(_result155 ? 1 : 0);
                    return true;
                case TRANSACTION_reqOppInterruptReceiveFile /* 173 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result156 = reqOppInterruptReceiveFile();
                    reply.writeNoException();
                    reply.writeInt(_result156 ? 1 : 0);
                    return true;
                case TRANSACTION_registerGattServerCallback /* 174 */:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackGattServer _arg0100 = INfCallbackGattServer.Stub.asInterface(data.readStrongBinder());
                    boolean _result157 = registerGattServerCallback(_arg0100);
                    reply.writeNoException();
                    reply.writeInt(_result157 ? 1 : 0);
                    return true;
                case TRANSACTION_unregisterGattServerCallback /* 175 */:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackGattServer _arg0101 = INfCallbackGattServer.Stub.asInterface(data.readStrongBinder());
                    boolean _result158 = unregisterGattServerCallback(_arg0101);
                    reply.writeNoException();
                    reply.writeInt(_result158 ? 1 : 0);
                    return true;
                case 176:
                    data.enforceInterface(DESCRIPTOR);
                    int _result159 = getGattServerConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result159);
                    return true;
                case 177:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0102 = data.readString();
                    boolean _result160 = reqGattServerDisconnect(_arg0102);
                    reply.writeNoException();
                    reply.writeInt(_result160 ? 1 : 0);
                    return true;
                case 178:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0103 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg13 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }
                    boolean _result161 = reqGattServerBeginServiceDeclaration(_arg0103, _arg13);
                    reply.writeNoException();
                    reply.writeInt(_result161 ? 1 : 0);
                    return true;
                case 179:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg04 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    int _arg129 = data.readInt();
                    int _arg219 = data.readInt();
                    boolean _result162 = reqGattServerAddCharacteristic(_arg04, _arg129, _arg219);
                    reply.writeNoException();
                    reply.writeInt(_result162 ? 1 : 0);
                    return true;
                case 180:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    int _arg130 = data.readInt();
                    boolean _result163 = reqGattServerAddDescriptor(_arg03, _arg130);
                    reply.writeNoException();
                    reply.writeInt(_result163 ? 1 : 0);
                    return true;
                case 181:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result164 = reqGattServerEndServiceDeclaration();
                    reply.writeNoException();
                    reply.writeInt(_result164 ? 1 : 0);
                    return true;
                case TRANSACTION_reqGattServerRemoveService /* 182 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0104 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg12 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    boolean _result165 = reqGattServerRemoveService(_arg0104, _arg12);
                    reply.writeNoException();
                    reply.writeInt(_result165 ? 1 : 0);
                    return true;
                case TRANSACTION_reqGattServerClearServices /* 183 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result166 = reqGattServerClearServices();
                    reply.writeNoException();
                    reply.writeInt(_result166 ? 1 : 0);
                    return true;
                case TRANSACTION_reqGattServerListen /* 184 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg0105 = data.readInt() != 0;
                    boolean _result167 = reqGattServerListen(_arg0105);
                    reply.writeNoException();
                    reply.writeInt(_result167 ? 1 : 0);
                    return true;
                case TRANSACTION_reqGattServerSendResponse /* 185 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0106 = data.readString();
                    int _arg131 = data.readInt();
                    int _arg220 = data.readInt();
                    int _arg39 = data.readInt();
                    byte[] _arg45 = data.createByteArray();
                    boolean _result168 = reqGattServerSendResponse(_arg0106, _arg131, _arg220, _arg39, _arg45);
                    reply.writeNoException();
                    reply.writeInt(_result168 ? 1 : 0);
                    return true;
                case TRANSACTION_reqGattServerSendNotification /* 186 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0107 = data.readString();
                    int _arg132 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg2 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg3 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    boolean _arg46 = data.readInt() != 0;
                    byte[] _arg52 = data.createByteArray();
                    boolean _result169 = reqGattServerSendNotification(_arg0107, _arg132, _arg2, _arg3, _arg46, _arg52);
                    reply.writeNoException();
                    reply.writeInt(_result169 ? 1 : 0);
                    return true;
                case TRANSACTION_getGattAddedGattServiceUuidList /* 187 */:
                    data.enforceInterface(DESCRIPTOR);
                    List<ParcelUuid> _result170 = getGattAddedGattServiceUuidList();
                    reply.writeNoException();
                    reply.writeTypedList(_result170);
                    return true;
                case TRANSACTION_getGattAddedGattCharacteristicUuidList /* 188 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    List<ParcelUuid> _result171 = getGattAddedGattCharacteristicUuidList(_arg02);
                    reply.writeNoException();
                    reply.writeTypedList(_result171);
                    return true;
                case TRANSACTION_getGattAddedGattDescriptorUuidList /* 189 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg1 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    List<ParcelUuid> _result172 = getGattAddedGattDescriptorUuidList(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeTypedList(_result172);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements INfCommandManager {
            public static INfCommandManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean registerA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerA2dpCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean unregisterA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterA2dpCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getA2dpConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getA2dpConnectionState();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isA2dpConnected() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isA2dpConnected();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getA2dpConnectedAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getA2dpConnectedAddress();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqA2dpConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqA2dpConnect(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqA2dpDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqA2dpDisconnect(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void pauseA2dpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pauseA2dpRender();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void startA2dpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startA2dpRender();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean setA2dpLocalVolume(float vol) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(vol);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().setA2dpLocalVolume(vol);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean setA2dpStreamType(int type) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().setA2dpStreamType(type);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getA2dpStreamType() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getA2dpStreamType();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean registerAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerAvrcpCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean unregisterAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterAvrcpCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getAvrcpConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getAvrcpConnectionState();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isAvrcpConnected() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isAvrcpConnected();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getAvrcpConnectedAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getAvrcpConnectedAddress();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpConnect(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpDisconnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpDisconnect(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isAvrcp13Supported(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isAvrcp13Supported, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isAvrcp13Supported(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isAvrcp14Supported(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isAvrcp14Supported, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isAvrcp14Supported(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpPlay() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpPlay, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpPlay();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpStop() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpStop, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpStop();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpPause() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpPause, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpPause();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpForward() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpForward();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpBackward() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpBackward, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpBackward();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpVolumeUp() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpVolumeUp, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpVolumeUp();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpVolumeDown() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpVolumeDown, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpVolumeDown();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpStartFastForward() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpStartFastForward, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpStartFastForward();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpStopFastForward() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpStopFastForward, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpStopFastForward();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpStartRewind() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpStartRewind, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpStartRewind();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpStopRewind() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpStopRewind();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp13GetCapabilitiesSupportEvent() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp13GetCapabilitiesSupportEvent, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp13GetCapabilitiesSupportEvent();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp13GetPlayerSettingAttributesList() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp13GetPlayerSettingAttributesList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp13GetPlayerSettingAttributesList();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp13GetPlayerSettingValuesList(byte attributeId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(attributeId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp13GetPlayerSettingValuesList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp13GetPlayerSettingValuesList(attributeId);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp13GetPlayerSettingCurrentValues() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp13GetPlayerSettingCurrentValues, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp13GetPlayerSettingCurrentValues();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp13SetPlayerSettingValue(byte attributeId, byte valueId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(attributeId);
                    _data.writeByte(valueId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp13SetPlayerSettingValue, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp13SetPlayerSettingValue(attributeId, valueId);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp13GetElementAttributesPlaying() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp13GetElementAttributesPlaying, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp13GetElementAttributesPlaying();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp13GetPlayStatus() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp13GetPlayStatus, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp13GetPlayStatus();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpRegisterEventWatcher(byte eventId, long interval) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(eventId);
                    _data.writeLong(interval);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpRegisterEventWatcher, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpRegisterEventWatcher(eventId, interval);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcpUnregisterEventWatcher(byte eventId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(eventId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpUnregisterEventWatcher, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpUnregisterEventWatcher(eventId);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp13NextGroup() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp13NextGroup, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp13NextGroup();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp13PreviousGroup() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp13PreviousGroup, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp13PreviousGroup();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp13GetPlayerSettingAttributeText(byte attributeId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(attributeId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp13GetPlayerSettingAttributeText, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp13GetPlayerSettingAttributeText(attributeId);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp13GetPlayerSettingValueText(byte attributeId, byte valueId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(attributeId);
                    _data.writeByte(valueId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp13GetPlayerSettingValueText, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp13GetPlayerSettingValueText(attributeId, valueId);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isAvrcp14BrowsingChannelEstablished() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isAvrcp14BrowsingChannelEstablished, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isAvrcp14BrowsingChannelEstablished();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp14SetAddressedPlayer(int playerId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(playerId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp14SetAddressedPlayer, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp14SetAddressedPlayer(playerId);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp14SetBrowsedPlayer(int playerId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(playerId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp14SetBrowsedPlayer, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp14SetBrowsedPlayer(playerId);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp14GetFolderItems(byte scopeId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(scopeId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp14GetFolderItems, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp14GetFolderItems(scopeId);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp14ChangePath(int uidCounter, long uid, byte direction) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uidCounter);
                    _data.writeLong(uid);
                    _data.writeByte(direction);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp14ChangePath, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp14ChangePath(uidCounter, uid, direction);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp14GetItemAttributes(byte scopeId, int uidCounter, long uid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(scopeId);
                    _data.writeInt(uidCounter);
                    _data.writeLong(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp14GetItemAttributes, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp14GetItemAttributes(scopeId, uidCounter, uid);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp14PlaySelectedItem(byte scopeId, int uidCounter, long uid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(scopeId);
                    _data.writeInt(uidCounter);
                    _data.writeLong(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp14PlaySelectedItem, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp14PlaySelectedItem(scopeId, uidCounter, uid);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp14Search(String text) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(text);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp14Search, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp14Search(text);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp14AddToNowPlaying(byte scopeId, int uidCounter, long uid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(scopeId);
                    _data.writeInt(uidCounter);
                    _data.writeLong(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp14AddToNowPlaying, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp14AddToNowPlaying(scopeId, uidCounter, uid);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqAvrcp14SetAbsoluteVolume(byte volume) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(volume);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcp14SetAbsoluteVolume, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcp14SetAbsoluteVolume(volume);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean registerBtCallback(INfCallbackBluetooth cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerBtCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerBtCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean unregisterBtCallback(INfCallbackBluetooth cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterBtCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterBtCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getNfServiceVersionName() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getNfServiceVersionName, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getNfServiceVersionName();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean setBtEnable(boolean enable) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setBtEnable, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().setBtEnable(enable);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean setBtDiscoverableTimeout(int timeout) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(timeout);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setBtDiscoverableTimeout, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().setBtDiscoverableTimeout(timeout);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean startBtDiscovery() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_startBtDiscovery, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().startBtDiscovery();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean cancelBtDiscovery() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_cancelBtDiscovery, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().cancelBtDiscovery();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqBtPair(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqBtPair, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqBtPair(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqBtUnpair(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqBtUnpair(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqBtPairedDevices() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqBtPairedDevices, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqBtPairedDevices();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getBtLocalName() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getBtLocalName();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getBtRemoteDeviceName(String address) throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBtRemoteDeviceName, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getBtRemoteDeviceName(address);
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getBtLocalAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getBtLocalAddress();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean setBtLocalName(String name) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setBtLocalName, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().setBtLocalName(name);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isBtEnabled() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(70, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isBtEnabled();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getBtState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getBtState();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isBtDiscovering() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isBtDiscovering();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isBtDiscoverable() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isBtDiscoverable();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isBtAutoConnectEnable() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(74, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isBtAutoConnectEnable();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int reqBtConnectHfpA2dp(String address) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqBtConnectHfpA2dp, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().reqBtConnectHfpA2dp(address);
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int reqBtDisconnectAll() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(76, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().reqBtDisconnectAll();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getBtRemoteUuids(String address) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(77, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getBtRemoteUuids(address);
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isDeviceAclDisconnected(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(78, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isDeviceAclDisconnected(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean switchBtRoleMode(int mode) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().switchBtRoleMode(mode);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getBtRoleMode() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBtRoleMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getBtRoleMode();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void setBtAutoConnect(int condition, int peroid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(condition);
                    _data.writeInt(peroid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setBtAutoConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBtAutoConnect(condition, peroid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getBtAutoConnectCondition() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBtAutoConnectCondition, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getBtAutoConnectCondition();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getBtAutoConnectPeriod() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBtAutoConnectPeriod, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getBtAutoConnectPeriod();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getBtAutoConnectState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBtAutoConnectState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getBtAutoConnectState();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getBtAutoConnectingAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBtAutoConnectingAddress, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getBtAutoConnectingAddress();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean registerHfpCallback(INfCallbackHfp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerHfpCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerHfpCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean unregisterHfpCallback(INfCallbackHfp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterHfpCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterHfpCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getHfpConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpConnectionState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHfpConnectionState();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isHfpConnected() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isHfpConnected, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpConnected();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getHfpConnectedAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpConnectedAddress, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getHfpConnectedAddress();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getHfpAudioConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpAudioConnectionState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHfpAudioConnectionState();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpConnect(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpDisconnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpDisconnect(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getHfpRemoteSignalStrength() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(94, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHfpRemoteSignalStrength();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public List<NfHfpClientCall> getHfpCallList() throws RemoteException {
                List<NfHfpClientCall> createTypedArrayList;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(95, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getHfpCallList();
                    } else {
                        _reply.readException();
                        createTypedArrayList = _reply.createTypedArrayList(NfHfpClientCall.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isHfpRemoteOnRoaming() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(96, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpRemoteOnRoaming();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getHfpRemoteBatteryIndicator() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(97, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHfpRemoteBatteryIndicator();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isHfpRemoteTelecomServiceOn() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(98, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpRemoteTelecomServiceOn();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isHfpRemoteVoiceDialOn() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(99, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpRemoteVoiceDialOn();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpDialCall(String number) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    boolean _status = this.mRemote.transact(100, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpDialCall(number);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpReDial() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(101, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpReDial();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpMemoryDial(String index) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(index);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpMemoryDial, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpMemoryDial(index);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpAnswerCall(int flag) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flag);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpAnswerCall, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpAnswerCall(flag);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpRejectIncomingCall() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpRejectIncomingCall, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpRejectIncomingCall();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpTerminateCurrentCall() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpTerminateCurrentCall, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpTerminateCurrentCall();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpSendDtmf(String number) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpSendDtmf, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpSendDtmf(number);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpAudioTransferToCarkit() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpAudioTransferToCarkit, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpAudioTransferToCarkit();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpAudioTransferToPhone() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpAudioTransferToPhone, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpAudioTransferToPhone();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getHfpRemoteNetworkOperator() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpRemoteNetworkOperator, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getHfpRemoteNetworkOperator();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getHfpRemoteSubscriberNumber() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(110, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getHfpRemoteSubscriberNumber();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHfpVoiceDial(boolean enable) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(111, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpVoiceDial(enable);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void pauseHfpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(112, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pauseHfpRender();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void startHfpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(113, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startHfpRender();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isHfpMicMute() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(114, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpMicMute();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void muteHfpMic(boolean mute) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mute ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_muteHfpMic, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().muteHfpMic(mute);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isHfpInBandRingtoneSupport() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isHfpInBandRingtoneSupport, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpInBandRingtoneSupport();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean registerPbapCallback(INfCallbackPbap cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerPbapCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerPbapCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean unregisterPbapCallback(INfCallbackPbap cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterPbapCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterPbapCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getPbapConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getPbapConnectionState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getPbapConnectionState();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isPbapDownloading() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(120, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isPbapDownloading();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getPbapDownloadingAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(121, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getPbapDownloadingAddress();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqPbapDownload(String address, int storage, int property) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(storage);
                    _data.writeInt(property);
                    boolean _status = this.mRemote.transact(122, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapDownload(address, storage, property);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqPbapDownloadRange(String address, int storage, int property, int startPos, int offset) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(storage);
                    _data.writeInt(property);
                    _data.writeInt(startPos);
                    _data.writeInt(offset);
                    boolean _status = this.mRemote.transact(123, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapDownloadRange(address, storage, property, startPos, offset);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqPbapDownloadToDatabase(String address, int storage, int property) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(storage);
                    _data.writeInt(property);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqPbapDownloadToDatabase, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapDownloadToDatabase(address, storage, property);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqPbapDownloadRangeToDatabase(String address, int storage, int property, int startPos, int offset) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(storage);
                    _data.writeInt(property);
                    _data.writeInt(startPos);
                    _data.writeInt(offset);
                    boolean _status = this.mRemote.transact(125, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapDownloadRangeToDatabase(address, storage, property, startPos, offset);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqPbapDownloadToContactsProvider(String address, int storage, int property) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(storage);
                    _data.writeInt(property);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqPbapDownloadToContactsProvider, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapDownloadToContactsProvider(address, storage, property);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqPbapDownloadRangeToContactsProvider(String address, int storage, int property, int startPos, int offset) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(storage);
                    _data.writeInt(property);
                    _data.writeInt(startPos);
                    _data.writeInt(offset);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqPbapDownloadRangeToContactsProvider, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapDownloadRangeToContactsProvider(address, storage, property, startPos, offset);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void reqPbapDatabaseQueryNameByNumber(String address, String target) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(target);
                    boolean _status = this.mRemote.transact(128, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqPbapDatabaseQueryNameByNumber(address, target);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void reqPbapDatabaseQueryNameByPartialNumber(String address, String target, int findPosition) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(target);
                    _data.writeInt(findPosition);
                    boolean _status = this.mRemote.transact(129, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqPbapDatabaseQueryNameByPartialNumber(address, target, findPosition);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void reqPbapDatabaseAvailable(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(130, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqPbapDatabaseAvailable(address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void reqPbapDeleteDatabaseByAddress(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(131, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqPbapDeleteDatabaseByAddress(address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void reqPbapCleanDatabase() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(132, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqPbapCleanDatabase();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqPbapDownloadInterrupt(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(133, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapDownloadInterrupt(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean setPbapDownloadNotify(int frequency) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(frequency);
                    boolean _status = this.mRemote.transact(134, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().setPbapDownloadNotify(frequency);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean registerHidCallback(INfCallbackHid cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(135, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerHidCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean unregisterHidCallback(INfCallbackHid cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(136, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterHidCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHidConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(137, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHidConnect(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqHidDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(138, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHidDisconnect(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqSendHidMouseCommand(int button, int offset_x, int offset_y, int wheel) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(button);
                    _data.writeInt(offset_x);
                    _data.writeInt(offset_y);
                    _data.writeInt(wheel);
                    boolean _status = this.mRemote.transact(139, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqSendHidMouseCommand(button, offset_x, offset_y, wheel);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqSendHidVirtualKeyCommand(int key_1, int key_2) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(key_1);
                    _data.writeInt(key_2);
                    boolean _status = this.mRemote.transact(140, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqSendHidVirtualKeyCommand(key_1, key_2);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getHidConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(141, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHidConnectionState();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isHidConnected() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(142, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHidConnected();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getHidConnectedAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHidConnectedAddress, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getHidConnectedAddress();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean registerSppCallback(INfCallbackSpp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(144, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerSppCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean unregisterSppCallback(INfCallbackSpp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(145, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterSppCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqSppConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqSppConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqSppConnect(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqSppDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqSppDisconnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqSppDisconnect(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void reqSppConnectedDeviceAddressList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqSppConnectedDeviceAddressList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqSppConnectedDeviceAddressList();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isSppConnected(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isSppConnected, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isSppConnected(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void reqSppSendData(String address, byte[] sppData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeByteArray(sppData);
                    boolean _status = this.mRemote.transact(150, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqSppSendData(address, sppData);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean registerMapCallback(INfCallbackMap cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(151, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerMapCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean unregisterMapCallback(INfCallbackMap cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(152, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterMapCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqMapDownloadSingleMessage(String address, int folder, String handle, int storage) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(folder);
                    _data.writeString(handle);
                    _data.writeInt(storage);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqMapDownloadSingleMessage, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapDownloadSingleMessage(address, folder, handle, storage);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqMapDownloadMessage(String address, int folder, boolean isContentDownload, int count, int startPos, int storage, String periodBegin, String periodEnd, String sender, String recipient, int readStatus, int typeFilter) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(folder);
                    _data.writeInt(isContentDownload ? 1 : 0);
                    _data.writeInt(count);
                    _data.writeInt(startPos);
                    _data.writeInt(storage);
                    _data.writeString(periodBegin);
                    _data.writeString(periodEnd);
                    _data.writeString(sender);
                    _data.writeString(recipient);
                    _data.writeInt(readStatus);
                    _data.writeInt(typeFilter);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqMapDownloadMessage, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapDownloadMessage(address, folder, isContentDownload, count, startPos, storage, periodBegin, periodEnd, sender, recipient, readStatus, typeFilter);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqMapRegisterNotification(String address, boolean downloadNewMessage) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(downloadNewMessage ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqMapRegisterNotification, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapRegisterNotification(address, downloadNewMessage);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void reqMapUnregisterNotification(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqMapUnregisterNotification, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqMapUnregisterNotification(address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean isMapNotificationRegistered(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isMapNotificationRegistered, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isMapNotificationRegistered(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqMapDownloadInterrupt(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqMapDownloadInterrupt, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapDownloadInterrupt(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void reqMapDatabaseAvailable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqMapDatabaseAvailable, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqMapDatabaseAvailable();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void reqMapDeleteDatabaseByAddress(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(160, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqMapDeleteDatabaseByAddress(address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public void reqMapCleanDatabase() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(161, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqMapCleanDatabase();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getMapCurrentState(String address) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(162, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getMapCurrentState(address);
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getMapRegisterState(String address) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(163, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getMapRegisterState(address);
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqMapSendMessage(String address, String message, String target) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(message);
                    _data.writeString(target);
                    boolean _status = this.mRemote.transact(164, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapSendMessage(address, message, target);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqMapDeleteMessage(String address, int folder, String handle) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(folder);
                    _data.writeString(handle);
                    boolean _status = this.mRemote.transact(165, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapDeleteMessage(address, folder, handle);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqMapChangeReadStatus(String address, int folder, String handle, boolean isReadStatus) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(folder);
                    _data.writeString(handle);
                    _data.writeInt(isReadStatus ? 1 : 0);
                    boolean _status = this.mRemote.transact(166, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapChangeReadStatus(address, folder, handle, isReadStatus);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean setMapDownloadNotify(int frequency) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(frequency);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setMapDownloadNotify, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().setMapDownloadNotify(frequency);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean registerOppCallback(INfCallbackOpp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerOppCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerOppCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean unregisterOppCallback(INfCallbackOpp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterOppCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterOppCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean setOppFilePath(String path) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(170, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().setOppFilePath(path);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public String getOppFilePath() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getOppFilePath, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getOppFilePath();
                    } else {
                        _reply.readException();
                        readString = _reply.readString();
                    }
                    return readString;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqOppAcceptReceiveFile(boolean accept) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(accept ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqOppAcceptReceiveFile, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqOppAcceptReceiveFile(accept);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqOppInterruptReceiveFile() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqOppInterruptReceiveFile, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqOppInterruptReceiveFile();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean registerGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerGattServerCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerGattServerCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean unregisterGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterGattServerCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterGattServerCallback(cb);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public int getGattServerConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(176, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getGattServerConnectionState();
                    } else {
                        _reply.readException();
                        readInt = _reply.readInt();
                    }
                    return readInt;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqGattServerDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(177, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerDisconnect(address);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqGattServerBeginServiceDeclaration(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(srvcType);
                    if (srvcUuid != null) {
                        _data.writeInt(1);
                        srvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(178, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerBeginServiceDeclaration(srvcType, srvcUuid);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqGattServerAddCharacteristic(ParcelUuid charUuid, int properties, int permissions) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (charUuid != null) {
                        _data.writeInt(1);
                        charUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(properties);
                    _data.writeInt(permissions);
                    boolean _status = this.mRemote.transact(179, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerAddCharacteristic(charUuid, properties, permissions);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqGattServerAddDescriptor(ParcelUuid descUuid, int permissions) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (descUuid != null) {
                        _data.writeInt(1);
                        descUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(permissions);
                    boolean _status = this.mRemote.transact(180, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerAddDescriptor(descUuid, permissions);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqGattServerEndServiceDeclaration() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(181, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerEndServiceDeclaration();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqGattServerRemoveService(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(srvcType);
                    if (srvcUuid != null) {
                        _data.writeInt(1);
                        srvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqGattServerRemoveService, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerRemoveService(srvcType, srvcUuid);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqGattServerClearServices() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqGattServerClearServices, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerClearServices();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqGattServerListen(boolean listen) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(listen ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqGattServerListen, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerListen(listen);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqGattServerSendResponse(String address, int requestId, int status, int offset, byte[] value) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(requestId);
                    _data.writeInt(status);
                    _data.writeInt(offset);
                    _data.writeByteArray(value);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqGattServerSendResponse, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerSendResponse(address, requestId, status, offset, value);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public boolean reqGattServerSendNotification(String address, int srvcType, ParcelUuid srvcUuid, ParcelUuid charUuid, boolean confirm, byte[] value) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(srvcType);
                    if (srvcUuid != null) {
                        _data.writeInt(1);
                        srvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (charUuid != null) {
                        _data.writeInt(1);
                        charUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(confirm ? 1 : 0);
                    _data.writeByteArray(value);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqGattServerSendNotification, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerSendNotification(address, srvcType, srvcUuid, charUuid, confirm, value);
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public List<ParcelUuid> getGattAddedGattServiceUuidList() throws RemoteException {
                List<ParcelUuid> createTypedArrayList;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getGattAddedGattServiceUuidList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getGattAddedGattServiceUuidList();
                    } else {
                        _reply.readException();
                        createTypedArrayList = _reply.createTypedArrayList(ParcelUuid.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public List<ParcelUuid> getGattAddedGattCharacteristicUuidList(ParcelUuid srvcUuid) throws RemoteException {
                List<ParcelUuid> createTypedArrayList;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (srvcUuid != null) {
                        _data.writeInt(1);
                        srvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getGattAddedGattCharacteristicUuidList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getGattAddedGattCharacteristicUuidList(srvcUuid);
                    } else {
                        _reply.readException();
                        createTypedArrayList = _reply.createTypedArrayList(ParcelUuid.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandManager
            public List<ParcelUuid> getGattAddedGattDescriptorUuidList(ParcelUuid srvcUuid, ParcelUuid charUuid) throws RemoteException {
                List<ParcelUuid> createTypedArrayList;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (srvcUuid != null) {
                        _data.writeInt(1);
                        srvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (charUuid != null) {
                        _data.writeInt(1);
                        charUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getGattAddedGattDescriptorUuidList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getGattAddedGattDescriptorUuidList(srvcUuid, charUuid);
                    } else {
                        _reply.readException();
                        createTypedArrayList = _reply.createTypedArrayList(ParcelUuid.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INfCommandManager impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCommandManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
