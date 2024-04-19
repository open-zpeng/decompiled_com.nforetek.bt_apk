package com.nforetek.bt.aidl;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackJni;
import com.nforetek.bt.aidl.INfCallbackJniPbap;
import java.util.List;
/* loaded from: classes.dex */
public interface INfCommandJni extends IInterface {
    boolean abortAvrcpContinuingResponse() throws RemoteException;

    boolean acceptHfpCall(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean acceptIncomingConnect(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean acceptOppReceiveFile(boolean z) throws RemoteException;

    void beginGattServiceDeclaration(int i, int i2, int i3, int i4, ParcelUuid parcelUuid) throws RemoteException;

    void closeGattServer() throws RemoteException;

    boolean connectHfpAudio() throws RemoteException;

    int createSdpRecord(String str, int i, int i2, int i3, int i4, int i5) throws RemoteException;

    boolean disconnectHfpAudio() throws RemoteException;

    void endGattServiceDeclaration(int i) throws RemoteException;

    boolean enterHfpPrivateMode(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean explicitHfpCallTransfer(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean getAvrcpCapabilitySupportEvent() throws RemoteException;

    boolean getAvrcpElementAttributes() throws RemoteException;

    boolean getAvrcpPlayStatus() throws RemoteException;

    boolean getAvrcpPlayerSettingAttributeText(byte b) throws RemoteException;

    boolean getAvrcpPlayerSettingAttributesList() throws RemoteException;

    boolean getAvrcpPlayerSettingValueText(byte b, byte b2) throws RemoteException;

    boolean getAvrcpPlayerSettingValues() throws RemoteException;

    boolean getAvrcpPlayerSettingValuesList(byte b) throws RemoteException;

    int getAvrcpVersion(String str) throws RemoteException;

    String getBluedroidVersion() throws RemoteException;

    int getBtRoleMode() throws RemoteException;

    int getHfpAudioState(BluetoothDevice bluetoothDevice) throws RemoteException;

    List<BluetoothDevice> getHfpConnectedDevices() throws RemoteException;

    int getHfpConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException;

    Bundle getHfpCurrentAgEvents(BluetoothDevice bluetoothDevice) throws RemoteException;

    Bundle getHfpCurrentAgFeatures(BluetoothDevice bluetoothDevice) throws RemoteException;

    List<NfHfpClientCall> getHfpCurrentCalls(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean getHfpLastVoiceTagNumber(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getHfpPriority(BluetoothDevice bluetoothDevice) throws RemoteException;

    String getNfJniServiceVersion() throws RemoteException;

    String getOppFilePath() throws RemoteException;

    boolean getRemoteServiceRecord(String str, ParcelUuid parcelUuid) throws RemoteException;

    boolean holdHfpCall(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean initJniA2dp() throws RemoteException;

    boolean initJniAvrcp() throws RemoteException;

    boolean initJniGattServer() throws RemoteException;

    boolean initJniHfp() throws RemoteException;

    boolean initJniHid() throws RemoteException;

    boolean initJniMap() throws RemoteException;

    boolean initJniOpp() throws RemoteException;

    boolean initJniPbap() throws RemoteException;

    boolean interruptOppReceiveFile() throws RemoteException;

    boolean isMapNotificationRegistered() throws RemoteException;

    void muteHfpMic(boolean z) throws RemoteException;

    void openGattServer() throws RemoteException;

    void pauseA2dpRender() throws RemoteException;

    void pauseHfpRender() throws RemoteException;

    boolean registerAvrcpEvent(byte b, int i) throws RemoteException;

    boolean registerJniCallback(INfCallbackJni iNfCallbackJni) throws RemoteException;

    boolean registerJniPbapCallback(INfCallbackJniPbap iNfCallbackJniPbap) throws RemoteException;

    boolean rejectHfpCall(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean rejectHfpIncomingConnect(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean reqA2dpConnect(String str) throws RemoteException;

    boolean reqA2dpDisconnect(String str) throws RemoteException;

    boolean reqAvrcpAddToNowPlaying(long j, int i) throws RemoteException;

    boolean reqAvrcpBackward() throws RemoteException;

    boolean reqAvrcpBrowseConnect(String str) throws RemoteException;

    boolean reqAvrcpBrowseDisconnect(String str) throws RemoteException;

    boolean reqAvrcpChangePath(byte b, long j) throws RemoteException;

    boolean reqAvrcpConnect(String str) throws RemoteException;

    boolean reqAvrcpContinuingResponse() throws RemoteException;

    boolean reqAvrcpDisconnect(String str) throws RemoteException;

    boolean reqAvrcpForward() throws RemoteException;

    boolean reqAvrcpGetFolderItem(byte b) throws RemoteException;

    boolean reqAvrcpGetItemAttributes(byte b, long j, int i) throws RemoteException;

    boolean reqAvrcpNextGroup() throws RemoteException;

    boolean reqAvrcpPause() throws RemoteException;

    boolean reqAvrcpPlay() throws RemoteException;

    boolean reqAvrcpPlayItem(long j, int i) throws RemoteException;

    boolean reqAvrcpPreviousGroup() throws RemoteException;

    boolean reqAvrcpQueryVersion(String str) throws RemoteException;

    boolean reqAvrcpSearchString(String str) throws RemoteException;

    boolean reqAvrcpSetBrowsedPlayer(int i) throws RemoteException;

    boolean reqAvrcpStartFastForward() throws RemoteException;

    boolean reqAvrcpStartRewind() throws RemoteException;

    boolean reqAvrcpStop() throws RemoteException;

    boolean reqAvrcpStopFastForward() throws RemoteException;

    boolean reqAvrcpStopRewind() throws RemoteException;

    boolean reqAvrcpVersion(String str) throws RemoteException;

    boolean reqAvrcpVolumeDown() throws RemoteException;

    boolean reqAvrcpVolumeUp() throws RemoteException;

    boolean reqBtRemoveAclConnection(String str) throws RemoteException;

    void reqGattAddCharacteristic(int i, ParcelUuid parcelUuid, int i2, int i3) throws RemoteException;

    void reqGattAddDescriptor(int i, ParcelUuid parcelUuid, int i2) throws RemoteException;

    void reqGattAddIncludedService(int i, int i2, int i3, ParcelUuid parcelUuid) throws RemoteException;

    void reqGattClearServices(int i) throws RemoteException;

    void reqGattRemoveService(int i, int i2, int i3, ParcelUuid parcelUuid) throws RemoteException;

    void reqGattSendNotification(int i, String str, int i2, int i3, ParcelUuid parcelUuid, int i4, ParcelUuid parcelUuid2, boolean z, byte[] bArr) throws RemoteException;

    void reqGattSendResponse(int i, String str, int i2, int i3, int i4, byte[] bArr) throws RemoteException;

    void reqGattServerConnect(int i, String str, boolean z) throws RemoteException;

    void reqGattServerDisconnect(int i, String str) throws RemoteException;

    void reqGattServerListen(boolean z) throws RemoteException;

    boolean reqHfpConnect(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean reqHfpDial(BluetoothDevice bluetoothDevice, String str) throws RemoteException;

    boolean reqHfpDialMemory(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean reqHfpDisconnect(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean reqHfpRedial(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean reqHfpSendDTMF(BluetoothDevice bluetoothDevice, byte b) throws RemoteException;

    boolean reqHidConnect(String str) throws RemoteException;

    boolean reqHidDisconnect(String str) throws RemoteException;

    boolean reqHidSendData(String str, String str2) throws RemoteException;

    boolean reqMapChangeReadStatus(String str, int i, String str2, boolean z) throws RemoteException;

    boolean reqMapDeleteMessage(String str, int i, String str2) throws RemoteException;

    boolean reqMapDisconnect(String str) throws RemoteException;

    boolean reqMapDownloadAll(String str, int i, int i2, boolean z) throws RemoteException;

    boolean reqMapDownloadInterrupt(String str) throws RemoteException;

    boolean reqMapDownloadOne(String str, String str2, int i, int i2) throws RemoteException;

    boolean reqMapRegisterNotification(String str, boolean z) throws RemoteException;

    boolean reqMapSendMessage(String str, String str2, String str3) throws RemoteException;

    boolean reqPbapConnect(String str) throws RemoteException;

    boolean reqPbapDisconnect(String str) throws RemoteException;

    boolean reqPbapGetSize(String str, int i) throws RemoteException;

    boolean reqPbapStartDownload(String str, int i, int i2, int i3, int i4) throws RemoteException;

    boolean reqPbapStopDownload(String str) throws RemoteException;

    boolean setA2dpLocalVolume(float f) throws RemoteException;

    boolean setA2dpStreamType(int i) throws RemoteException;

    boolean setAvrcpPlayerSettingValue(byte b, byte b2) throws RemoteException;

    boolean setHfpPriority(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean setOppFilePath(String str) throws RemoteException;

    void startA2dpRender() throws RemoteException;

    void startHfpRender() throws RemoteException;

    boolean startHfpVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean stopHfpVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean switchBtRoleMode(int i) throws RemoteException;

    boolean terminateHfpCall(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean unregisterAvrcpEvent(byte b) throws RemoteException;

    boolean unregisterJniCallback(INfCallbackJni iNfCallbackJni) throws RemoteException;

    boolean unregisterJniPbapCallback(INfCallbackJniPbap iNfCallbackJniPbap) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCommandJni {
        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean initJniA2dp() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean initJniAvrcp() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean initJniHid() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean initJniPbap() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean initJniMap() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean initJniHfp() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean initJniOpp() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean initJniGattServer() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public int getBtRoleMode() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean switchBtRoleMode(int mode) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean getRemoteServiceRecord(String address, ParcelUuid uuid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public int createSdpRecord(String serviceName, int profile, int rfcommChannel, int l2capPsm, int version, int features) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean registerJniCallback(INfCallbackJni cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean unregisterJniCallback(INfCallbackJni cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean registerJniPbapCallback(INfCallbackJniPbap cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean unregisterJniPbapCallback(INfCallbackJniPbap cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqA2dpConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqA2dpDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqBtRemoveAclConnection(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public String getBluedroidVersion() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public String getNfJniServiceVersion() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpPlay() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpStop() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpPause() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpForward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpBackward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpVolumeUp() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpVolumeDown() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpStartFastForward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpStopFastForward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpStartRewind() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpStopRewind() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean getAvrcpCapabilitySupportEvent() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean getAvrcpPlayerSettingAttributesList() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean getAvrcpPlayerSettingValuesList(byte attributeId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean getAvrcpPlayerSettingValues() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean setAvrcpPlayerSettingValue(byte attributeId, byte valueId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean getAvrcpElementAttributes() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean getAvrcpPlayStatus() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean registerAvrcpEvent(byte event_id, int interval) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean unregisterAvrcpEvent(byte event_id) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpNextGroup() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpPreviousGroup() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean getAvrcpPlayerSettingAttributeText(byte attributeId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean getAvrcpPlayerSettingValueText(byte attributeId, byte valueId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpContinuingResponse() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean abortAvrcpContinuingResponse() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpQueryVersion(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void pauseA2dpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void startA2dpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean setA2dpLocalVolume(float vol) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean setA2dpStreamType(int type) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpVersion(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public int getAvrcpVersion(String address) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpBrowseConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpBrowseDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpGetFolderItem(byte scope) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpChangePath(byte updown, long uid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpSetBrowsedPlayer(int player) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpPlayItem(long item, int uid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpAddToNowPlaying(long item, int uid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpSearchString(String data) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqAvrcpGetItemAttributes(byte scope, long item, int uid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqHidConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqHidDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqHidSendData(String address, String report) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqPbapConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqPbapDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqPbapStartDownload(String address, int access, int filter, int offset, int max_count) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqPbapStopDownload(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqPbapGetSize(String address, int access) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqMapDownloadAll(String address, int type, int folder, boolean isDownloadDetail) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqMapDownloadOne(String address, String handle, int type, int folder) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqMapRegisterNotification(String address, boolean enable) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqMapDownloadInterrupt(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqMapDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean isMapNotificationRegistered() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqMapSendMessage(String address, String msg, String target) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqMapDeleteMessage(String address, int folder, String handle) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqMapChangeReadStatus(String address, int folder, String handle, boolean isReadStatus) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqHfpConnect(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqHfpDisconnect(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public List<BluetoothDevice> getHfpConnectedDevices() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public int getHfpConnectionState(BluetoothDevice device) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean setHfpPriority(BluetoothDevice device, int priority) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public int getHfpPriority(BluetoothDevice device) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean startHfpVoiceRecognition(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean stopHfpVoiceRecognition(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean acceptIncomingConnect(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean rejectHfpIncomingConnect(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public int getHfpAudioState(BluetoothDevice device) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean connectHfpAudio() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean disconnectHfpAudio() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean acceptHfpCall(BluetoothDevice device, int flag) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean rejectHfpCall(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean holdHfpCall(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean terminateHfpCall(BluetoothDevice device, int index) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean enterHfpPrivateMode(BluetoothDevice device, int index) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqHfpRedial(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqHfpDial(BluetoothDevice device, String number) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqHfpDialMemory(BluetoothDevice device, int location) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean reqHfpSendDTMF(BluetoothDevice device, byte code) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean getHfpLastVoiceTagNumber(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public List<NfHfpClientCall> getHfpCurrentCalls(BluetoothDevice device) throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean explicitHfpCallTransfer(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public Bundle getHfpCurrentAgEvents(BluetoothDevice device) throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public Bundle getHfpCurrentAgFeatures(BluetoothDevice device) throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean setOppFilePath(String path) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public String getOppFilePath() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean acceptOppReceiveFile(boolean accept) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public boolean interruptOppReceiveFile() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void pauseHfpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void startHfpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void muteHfpMic(boolean mute) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void openGattServer() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void closeGattServer() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void reqGattServerConnect(int servertIf, String address, boolean isDirect) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void reqGattServerDisconnect(int serverIf, String address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void beginGattServiceDeclaration(int serverIf, int srvcType, int srvcInstanceId, int minHandles, ParcelUuid srvcId) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void reqGattAddIncludedService(int serverIf, int srvcType, int srvcInstanceId, ParcelUuid srvcId) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void reqGattAddCharacteristic(int serverIf, ParcelUuid charId, int properties, int permissions) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void reqGattAddDescriptor(int serverIf, ParcelUuid descId, int permissions) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void endGattServiceDeclaration(int serverIf) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void reqGattRemoveService(int serverIf, int srvcType, int srvcInstanceId, ParcelUuid srvcId) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void reqGattClearServices(int serverIf) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void reqGattSendResponse(int serverIf, String address, int requestId, int status, int offset, byte[] value) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void reqGattSendNotification(int serverIf, String address, int srvcType, int srvcInstanceId, ParcelUuid srvcId, int charInstanceId, ParcelUuid charId, boolean confirm, byte[] value) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandJni
        public void reqGattServerListen(boolean listen) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCommandJni {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCommandJni";
        static final int TRANSACTION_abortAvrcpContinuingResponse = 49;
        static final int TRANSACTION_acceptHfpCall = 96;
        static final int TRANSACTION_acceptIncomingConnect = 91;
        static final int TRANSACTION_acceptOppReceiveFile = 112;
        static final int TRANSACTION_beginGattServiceDeclaration = 121;
        static final int TRANSACTION_closeGattServer = 118;
        static final int TRANSACTION_connectHfpAudio = 94;
        static final int TRANSACTION_createSdpRecord = 12;
        static final int TRANSACTION_disconnectHfpAudio = 95;
        static final int TRANSACTION_endGattServiceDeclaration = 125;
        static final int TRANSACTION_enterHfpPrivateMode = 100;
        static final int TRANSACTION_explicitHfpCallTransfer = 107;
        static final int TRANSACTION_getAvrcpCapabilitySupportEvent = 35;
        static final int TRANSACTION_getAvrcpElementAttributes = 40;
        static final int TRANSACTION_getAvrcpPlayStatus = 41;
        static final int TRANSACTION_getAvrcpPlayerSettingAttributeText = 46;
        static final int TRANSACTION_getAvrcpPlayerSettingAttributesList = 36;
        static final int TRANSACTION_getAvrcpPlayerSettingValueText = 47;
        static final int TRANSACTION_getAvrcpPlayerSettingValues = 38;
        static final int TRANSACTION_getAvrcpPlayerSettingValuesList = 37;
        static final int TRANSACTION_getAvrcpVersion = 56;
        static final int TRANSACTION_getBluedroidVersion = 22;
        static final int TRANSACTION_getBtRoleMode = 9;
        static final int TRANSACTION_getHfpAudioState = 93;
        static final int TRANSACTION_getHfpConnectedDevices = 85;
        static final int TRANSACTION_getHfpConnectionState = 86;
        static final int TRANSACTION_getHfpCurrentAgEvents = 108;
        static final int TRANSACTION_getHfpCurrentAgFeatures = 109;
        static final int TRANSACTION_getHfpCurrentCalls = 106;
        static final int TRANSACTION_getHfpLastVoiceTagNumber = 105;
        static final int TRANSACTION_getHfpPriority = 88;
        static final int TRANSACTION_getNfJniServiceVersion = 23;
        static final int TRANSACTION_getOppFilePath = 111;
        static final int TRANSACTION_getRemoteServiceRecord = 11;
        static final int TRANSACTION_holdHfpCall = 98;
        static final int TRANSACTION_initJniA2dp = 1;
        static final int TRANSACTION_initJniAvrcp = 2;
        static final int TRANSACTION_initJniGattServer = 8;
        static final int TRANSACTION_initJniHfp = 6;
        static final int TRANSACTION_initJniHid = 3;
        static final int TRANSACTION_initJniMap = 5;
        static final int TRANSACTION_initJniOpp = 7;
        static final int TRANSACTION_initJniPbap = 4;
        static final int TRANSACTION_interruptOppReceiveFile = 113;
        static final int TRANSACTION_isMapNotificationRegistered = 79;
        static final int TRANSACTION_muteHfpMic = 116;
        static final int TRANSACTION_openGattServer = 117;
        static final int TRANSACTION_pauseA2dpRender = 51;
        static final int TRANSACTION_pauseHfpRender = 114;
        static final int TRANSACTION_registerAvrcpEvent = 42;
        static final int TRANSACTION_registerJniCallback = 13;
        static final int TRANSACTION_registerJniPbapCallback = 15;
        static final int TRANSACTION_rejectHfpCall = 97;
        static final int TRANSACTION_rejectHfpIncomingConnect = 92;
        static final int TRANSACTION_reqA2dpConnect = 17;
        static final int TRANSACTION_reqA2dpDisconnect = 18;
        static final int TRANSACTION_reqAvrcpAddToNowPlaying = 63;
        static final int TRANSACTION_reqAvrcpBackward = 28;
        static final int TRANSACTION_reqAvrcpBrowseConnect = 57;
        static final int TRANSACTION_reqAvrcpBrowseDisconnect = 58;
        static final int TRANSACTION_reqAvrcpChangePath = 60;
        static final int TRANSACTION_reqAvrcpConnect = 19;
        static final int TRANSACTION_reqAvrcpContinuingResponse = 48;
        static final int TRANSACTION_reqAvrcpDisconnect = 20;
        static final int TRANSACTION_reqAvrcpForward = 27;
        static final int TRANSACTION_reqAvrcpGetFolderItem = 59;
        static final int TRANSACTION_reqAvrcpGetItemAttributes = 65;
        static final int TRANSACTION_reqAvrcpNextGroup = 44;
        static final int TRANSACTION_reqAvrcpPause = 26;
        static final int TRANSACTION_reqAvrcpPlay = 24;
        static final int TRANSACTION_reqAvrcpPlayItem = 62;
        static final int TRANSACTION_reqAvrcpPreviousGroup = 45;
        static final int TRANSACTION_reqAvrcpQueryVersion = 50;
        static final int TRANSACTION_reqAvrcpSearchString = 64;
        static final int TRANSACTION_reqAvrcpSetBrowsedPlayer = 61;
        static final int TRANSACTION_reqAvrcpStartFastForward = 31;
        static final int TRANSACTION_reqAvrcpStartRewind = 33;
        static final int TRANSACTION_reqAvrcpStop = 25;
        static final int TRANSACTION_reqAvrcpStopFastForward = 32;
        static final int TRANSACTION_reqAvrcpStopRewind = 34;
        static final int TRANSACTION_reqAvrcpVersion = 55;
        static final int TRANSACTION_reqAvrcpVolumeDown = 30;
        static final int TRANSACTION_reqAvrcpVolumeUp = 29;
        static final int TRANSACTION_reqBtRemoveAclConnection = 21;
        static final int TRANSACTION_reqGattAddCharacteristic = 123;
        static final int TRANSACTION_reqGattAddDescriptor = 124;
        static final int TRANSACTION_reqGattAddIncludedService = 122;
        static final int TRANSACTION_reqGattClearServices = 127;
        static final int TRANSACTION_reqGattRemoveService = 126;
        static final int TRANSACTION_reqGattSendNotification = 129;
        static final int TRANSACTION_reqGattSendResponse = 128;
        static final int TRANSACTION_reqGattServerConnect = 119;
        static final int TRANSACTION_reqGattServerDisconnect = 120;
        static final int TRANSACTION_reqGattServerListen = 130;
        static final int TRANSACTION_reqHfpConnect = 83;
        static final int TRANSACTION_reqHfpDial = 102;
        static final int TRANSACTION_reqHfpDialMemory = 103;
        static final int TRANSACTION_reqHfpDisconnect = 84;
        static final int TRANSACTION_reqHfpRedial = 101;
        static final int TRANSACTION_reqHfpSendDTMF = 104;
        static final int TRANSACTION_reqHidConnect = 66;
        static final int TRANSACTION_reqHidDisconnect = 67;
        static final int TRANSACTION_reqHidSendData = 68;
        static final int TRANSACTION_reqMapChangeReadStatus = 82;
        static final int TRANSACTION_reqMapDeleteMessage = 81;
        static final int TRANSACTION_reqMapDisconnect = 78;
        static final int TRANSACTION_reqMapDownloadAll = 74;
        static final int TRANSACTION_reqMapDownloadInterrupt = 77;
        static final int TRANSACTION_reqMapDownloadOne = 75;
        static final int TRANSACTION_reqMapRegisterNotification = 76;
        static final int TRANSACTION_reqMapSendMessage = 80;
        static final int TRANSACTION_reqPbapConnect = 69;
        static final int TRANSACTION_reqPbapDisconnect = 70;
        static final int TRANSACTION_reqPbapGetSize = 73;
        static final int TRANSACTION_reqPbapStartDownload = 71;
        static final int TRANSACTION_reqPbapStopDownload = 72;
        static final int TRANSACTION_setA2dpLocalVolume = 53;
        static final int TRANSACTION_setA2dpStreamType = 54;
        static final int TRANSACTION_setAvrcpPlayerSettingValue = 39;
        static final int TRANSACTION_setHfpPriority = 87;
        static final int TRANSACTION_setOppFilePath = 110;
        static final int TRANSACTION_startA2dpRender = 52;
        static final int TRANSACTION_startHfpRender = 115;
        static final int TRANSACTION_startHfpVoiceRecognition = 89;
        static final int TRANSACTION_stopHfpVoiceRecognition = 90;
        static final int TRANSACTION_switchBtRoleMode = 10;
        static final int TRANSACTION_terminateHfpCall = 99;
        static final int TRANSACTION_unregisterAvrcpEvent = 43;
        static final int TRANSACTION_unregisterJniCallback = 14;
        static final int TRANSACTION_unregisterJniPbapCallback = 16;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCommandJni asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCommandJni)) {
                return (INfCommandJni) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelUuid _arg4;
            ParcelUuid _arg6;
            ParcelUuid _arg3;
            ParcelUuid _arg1;
            ParcelUuid _arg12;
            ParcelUuid _arg32;
            ParcelUuid _arg42;
            BluetoothDevice _arg0;
            BluetoothDevice _arg02;
            BluetoothDevice _arg03;
            BluetoothDevice _arg04;
            BluetoothDevice _arg05;
            BluetoothDevice _arg06;
            BluetoothDevice _arg07;
            BluetoothDevice _arg08;
            BluetoothDevice _arg09;
            BluetoothDevice _arg010;
            BluetoothDevice _arg011;
            BluetoothDevice _arg012;
            BluetoothDevice _arg013;
            BluetoothDevice _arg014;
            BluetoothDevice _arg015;
            BluetoothDevice _arg016;
            BluetoothDevice _arg017;
            BluetoothDevice _arg018;
            BluetoothDevice _arg019;
            BluetoothDevice _arg020;
            BluetoothDevice _arg021;
            BluetoothDevice _arg022;
            BluetoothDevice _arg023;
            BluetoothDevice _arg024;
            ParcelUuid _arg13;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = initJniA2dp();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result2 = initJniAvrcp();
                    reply.writeNoException();
                    reply.writeInt(_result2 ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result3 = initJniHid();
                    reply.writeNoException();
                    reply.writeInt(_result3 ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result4 = initJniPbap();
                    reply.writeNoException();
                    reply.writeInt(_result4 ? 1 : 0);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result5 = initJniMap();
                    reply.writeNoException();
                    reply.writeInt(_result5 ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result6 = initJniHfp();
                    reply.writeNoException();
                    reply.writeInt(_result6 ? 1 : 0);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result7 = initJniOpp();
                    reply.writeNoException();
                    reply.writeInt(_result7 ? 1 : 0);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result8 = initJniGattServer();
                    reply.writeNoException();
                    reply.writeInt(_result8 ? 1 : 0);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _result9 = getBtRoleMode();
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg025 = data.readInt();
                    boolean _result10 = switchBtRoleMode(_arg025);
                    reply.writeNoException();
                    reply.writeInt(_result10 ? 1 : 0);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg026 = data.readString();
                    if (data.readInt() != 0) {
                        _arg13 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }
                    boolean _result11 = getRemoteServiceRecord(_arg026, _arg13);
                    reply.writeNoException();
                    reply.writeInt(_result11 ? 1 : 0);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg027 = data.readString();
                    int _arg14 = data.readInt();
                    int _arg2 = data.readInt();
                    int _arg33 = data.readInt();
                    int _arg43 = data.readInt();
                    int _arg5 = data.readInt();
                    int _result12 = createSdpRecord(_arg027, _arg14, _arg2, _arg33, _arg43, _arg5);
                    reply.writeNoException();
                    reply.writeInt(_result12);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackJni _arg028 = INfCallbackJni.Stub.asInterface(data.readStrongBinder());
                    boolean _result13 = registerJniCallback(_arg028);
                    reply.writeNoException();
                    reply.writeInt(_result13 ? 1 : 0);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackJni _arg029 = INfCallbackJni.Stub.asInterface(data.readStrongBinder());
                    boolean _result14 = unregisterJniCallback(_arg029);
                    reply.writeNoException();
                    reply.writeInt(_result14 ? 1 : 0);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackJniPbap _arg030 = INfCallbackJniPbap.Stub.asInterface(data.readStrongBinder());
                    boolean _result15 = registerJniPbapCallback(_arg030);
                    reply.writeNoException();
                    reply.writeInt(_result15 ? 1 : 0);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackJniPbap _arg031 = INfCallbackJniPbap.Stub.asInterface(data.readStrongBinder());
                    boolean _result16 = unregisterJniPbapCallback(_arg031);
                    reply.writeNoException();
                    reply.writeInt(_result16 ? 1 : 0);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg032 = data.readString();
                    boolean _result17 = reqA2dpConnect(_arg032);
                    reply.writeNoException();
                    reply.writeInt(_result17 ? 1 : 0);
                    return true;
                case TRANSACTION_reqA2dpDisconnect /* 18 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg033 = data.readString();
                    boolean _result18 = reqA2dpDisconnect(_arg033);
                    reply.writeNoException();
                    reply.writeInt(_result18 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpConnect /* 19 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg034 = data.readString();
                    boolean _result19 = reqAvrcpConnect(_arg034);
                    reply.writeNoException();
                    reply.writeInt(_result19 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpDisconnect /* 20 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg035 = data.readString();
                    boolean _result20 = reqAvrcpDisconnect(_arg035);
                    reply.writeNoException();
                    reply.writeInt(_result20 ? 1 : 0);
                    return true;
                case TRANSACTION_reqBtRemoveAclConnection /* 21 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg036 = data.readString();
                    boolean _result21 = reqBtRemoveAclConnection(_arg036);
                    reply.writeNoException();
                    reply.writeInt(_result21 ? 1 : 0);
                    return true;
                case TRANSACTION_getBluedroidVersion /* 22 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _result22 = getBluedroidVersion();
                    reply.writeNoException();
                    reply.writeString(_result22);
                    return true;
                case TRANSACTION_getNfJniServiceVersion /* 23 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _result23 = getNfJniServiceVersion();
                    reply.writeNoException();
                    reply.writeString(_result23);
                    return true;
                case TRANSACTION_reqAvrcpPlay /* 24 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result24 = reqAvrcpPlay();
                    reply.writeNoException();
                    reply.writeInt(_result24 ? 1 : 0);
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result25 = reqAvrcpStop();
                    reply.writeNoException();
                    reply.writeInt(_result25 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpPause /* 26 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result26 = reqAvrcpPause();
                    reply.writeNoException();
                    reply.writeInt(_result26 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpForward /* 27 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result27 = reqAvrcpForward();
                    reply.writeNoException();
                    reply.writeInt(_result27 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpBackward /* 28 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result28 = reqAvrcpBackward();
                    reply.writeNoException();
                    reply.writeInt(_result28 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpVolumeUp /* 29 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result29 = reqAvrcpVolumeUp();
                    reply.writeNoException();
                    reply.writeInt(_result29 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpVolumeDown /* 30 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result30 = reqAvrcpVolumeDown();
                    reply.writeNoException();
                    reply.writeInt(_result30 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpStartFastForward /* 31 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result31 = reqAvrcpStartFastForward();
                    reply.writeNoException();
                    reply.writeInt(_result31 ? 1 : 0);
                    return true;
                case 32:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result32 = reqAvrcpStopFastForward();
                    reply.writeNoException();
                    reply.writeInt(_result32 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpStartRewind /* 33 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result33 = reqAvrcpStartRewind();
                    reply.writeNoException();
                    reply.writeInt(_result33 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpStopRewind /* 34 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result34 = reqAvrcpStopRewind();
                    reply.writeNoException();
                    reply.writeInt(_result34 ? 1 : 0);
                    return true;
                case TRANSACTION_getAvrcpCapabilitySupportEvent /* 35 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result35 = getAvrcpCapabilitySupportEvent();
                    reply.writeNoException();
                    reply.writeInt(_result35 ? 1 : 0);
                    return true;
                case TRANSACTION_getAvrcpPlayerSettingAttributesList /* 36 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result36 = getAvrcpPlayerSettingAttributesList();
                    reply.writeNoException();
                    reply.writeInt(_result36 ? 1 : 0);
                    return true;
                case TRANSACTION_getAvrcpPlayerSettingValuesList /* 37 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg037 = data.readByte();
                    boolean _result37 = getAvrcpPlayerSettingValuesList(_arg037);
                    reply.writeNoException();
                    reply.writeInt(_result37 ? 1 : 0);
                    return true;
                case TRANSACTION_getAvrcpPlayerSettingValues /* 38 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result38 = getAvrcpPlayerSettingValues();
                    reply.writeNoException();
                    reply.writeInt(_result38 ? 1 : 0);
                    return true;
                case TRANSACTION_setAvrcpPlayerSettingValue /* 39 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg038 = data.readByte();
                    byte _arg15 = data.readByte();
                    boolean _result39 = setAvrcpPlayerSettingValue(_arg038, _arg15);
                    reply.writeNoException();
                    reply.writeInt(_result39 ? 1 : 0);
                    return true;
                case TRANSACTION_getAvrcpElementAttributes /* 40 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result40 = getAvrcpElementAttributes();
                    reply.writeNoException();
                    reply.writeInt(_result40 ? 1 : 0);
                    return true;
                case TRANSACTION_getAvrcpPlayStatus /* 41 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result41 = getAvrcpPlayStatus();
                    reply.writeNoException();
                    reply.writeInt(_result41 ? 1 : 0);
                    return true;
                case TRANSACTION_registerAvrcpEvent /* 42 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg039 = data.readByte();
                    int _arg16 = data.readInt();
                    boolean _result42 = registerAvrcpEvent(_arg039, _arg16);
                    reply.writeNoException();
                    reply.writeInt(_result42 ? 1 : 0);
                    return true;
                case TRANSACTION_unregisterAvrcpEvent /* 43 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg040 = data.readByte();
                    boolean _result43 = unregisterAvrcpEvent(_arg040);
                    reply.writeNoException();
                    reply.writeInt(_result43 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpNextGroup /* 44 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result44 = reqAvrcpNextGroup();
                    reply.writeNoException();
                    reply.writeInt(_result44 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpPreviousGroup /* 45 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result45 = reqAvrcpPreviousGroup();
                    reply.writeNoException();
                    reply.writeInt(_result45 ? 1 : 0);
                    return true;
                case TRANSACTION_getAvrcpPlayerSettingAttributeText /* 46 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg041 = data.readByte();
                    boolean _result46 = getAvrcpPlayerSettingAttributeText(_arg041);
                    reply.writeNoException();
                    reply.writeInt(_result46 ? 1 : 0);
                    return true;
                case TRANSACTION_getAvrcpPlayerSettingValueText /* 47 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg042 = data.readByte();
                    byte _arg17 = data.readByte();
                    boolean _result47 = getAvrcpPlayerSettingValueText(_arg042, _arg17);
                    reply.writeNoException();
                    reply.writeInt(_result47 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpContinuingResponse /* 48 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result48 = reqAvrcpContinuingResponse();
                    reply.writeNoException();
                    reply.writeInt(_result48 ? 1 : 0);
                    return true;
                case TRANSACTION_abortAvrcpContinuingResponse /* 49 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result49 = abortAvrcpContinuingResponse();
                    reply.writeNoException();
                    reply.writeInt(_result49 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpQueryVersion /* 50 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg043 = data.readString();
                    boolean _result50 = reqAvrcpQueryVersion(_arg043);
                    reply.writeNoException();
                    reply.writeInt(_result50 ? 1 : 0);
                    return true;
                case TRANSACTION_pauseA2dpRender /* 51 */:
                    data.enforceInterface(DESCRIPTOR);
                    pauseA2dpRender();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_startA2dpRender /* 52 */:
                    data.enforceInterface(DESCRIPTOR);
                    startA2dpRender();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setA2dpLocalVolume /* 53 */:
                    data.enforceInterface(DESCRIPTOR);
                    float _arg044 = data.readFloat();
                    boolean _result51 = setA2dpLocalVolume(_arg044);
                    reply.writeNoException();
                    reply.writeInt(_result51 ? 1 : 0);
                    return true;
                case TRANSACTION_setA2dpStreamType /* 54 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg045 = data.readInt();
                    boolean _result52 = setA2dpStreamType(_arg045);
                    reply.writeNoException();
                    reply.writeInt(_result52 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpVersion /* 55 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg046 = data.readString();
                    boolean _result53 = reqAvrcpVersion(_arg046);
                    reply.writeNoException();
                    reply.writeInt(_result53 ? 1 : 0);
                    return true;
                case TRANSACTION_getAvrcpVersion /* 56 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg047 = data.readString();
                    int _result54 = getAvrcpVersion(_arg047);
                    reply.writeNoException();
                    reply.writeInt(_result54);
                    return true;
                case TRANSACTION_reqAvrcpBrowseConnect /* 57 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg048 = data.readString();
                    boolean _result55 = reqAvrcpBrowseConnect(_arg048);
                    reply.writeNoException();
                    reply.writeInt(_result55 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpBrowseDisconnect /* 58 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg049 = data.readString();
                    boolean _result56 = reqAvrcpBrowseDisconnect(_arg049);
                    reply.writeNoException();
                    reply.writeInt(_result56 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpGetFolderItem /* 59 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg050 = data.readByte();
                    boolean _result57 = reqAvrcpGetFolderItem(_arg050);
                    reply.writeNoException();
                    reply.writeInt(_result57 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpChangePath /* 60 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg051 = data.readByte();
                    long _arg18 = data.readLong();
                    boolean _result58 = reqAvrcpChangePath(_arg051, _arg18);
                    reply.writeNoException();
                    reply.writeInt(_result58 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpSetBrowsedPlayer /* 61 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg052 = data.readInt();
                    boolean _result59 = reqAvrcpSetBrowsedPlayer(_arg052);
                    reply.writeNoException();
                    reply.writeInt(_result59 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpPlayItem /* 62 */:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg053 = data.readLong();
                    int _arg19 = data.readInt();
                    boolean _result60 = reqAvrcpPlayItem(_arg053, _arg19);
                    reply.writeNoException();
                    reply.writeInt(_result60 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpAddToNowPlaying /* 63 */:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg054 = data.readLong();
                    int _arg110 = data.readInt();
                    boolean _result61 = reqAvrcpAddToNowPlaying(_arg054, _arg110);
                    reply.writeNoException();
                    reply.writeInt(_result61 ? 1 : 0);
                    return true;
                case 64:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg055 = data.readString();
                    boolean _result62 = reqAvrcpSearchString(_arg055);
                    reply.writeNoException();
                    reply.writeInt(_result62 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpGetItemAttributes /* 65 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg056 = data.readByte();
                    long _arg111 = data.readLong();
                    int _arg22 = data.readInt();
                    boolean _result63 = reqAvrcpGetItemAttributes(_arg056, _arg111, _arg22);
                    reply.writeNoException();
                    reply.writeInt(_result63 ? 1 : 0);
                    return true;
                case 66:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg057 = data.readString();
                    boolean _result64 = reqHidConnect(_arg057);
                    reply.writeNoException();
                    reply.writeInt(_result64 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHidDisconnect /* 67 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg058 = data.readString();
                    boolean _result65 = reqHidDisconnect(_arg058);
                    reply.writeNoException();
                    reply.writeInt(_result65 ? 1 : 0);
                    return true;
                case 68:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg059 = data.readString();
                    String _arg112 = data.readString();
                    boolean _result66 = reqHidSendData(_arg059, _arg112);
                    reply.writeNoException();
                    reply.writeInt(_result66 ? 1 : 0);
                    return true;
                case TRANSACTION_reqPbapConnect /* 69 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg060 = data.readString();
                    boolean _result67 = reqPbapConnect(_arg060);
                    reply.writeNoException();
                    reply.writeInt(_result67 ? 1 : 0);
                    return true;
                case 70:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg061 = data.readString();
                    boolean _result68 = reqPbapDisconnect(_arg061);
                    reply.writeNoException();
                    reply.writeInt(_result68 ? 1 : 0);
                    return true;
                case 71:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg062 = data.readString();
                    int _arg113 = data.readInt();
                    int _arg23 = data.readInt();
                    int _arg34 = data.readInt();
                    int _arg44 = data.readInt();
                    boolean _result69 = reqPbapStartDownload(_arg062, _arg113, _arg23, _arg34, _arg44);
                    reply.writeNoException();
                    reply.writeInt(_result69 ? 1 : 0);
                    return true;
                case 72:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg063 = data.readString();
                    boolean _result70 = reqPbapStopDownload(_arg063);
                    reply.writeNoException();
                    reply.writeInt(_result70 ? 1 : 0);
                    return true;
                case 73:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg064 = data.readString();
                    int _arg114 = data.readInt();
                    boolean _result71 = reqPbapGetSize(_arg064, _arg114);
                    reply.writeNoException();
                    reply.writeInt(_result71 ? 1 : 0);
                    return true;
                case 74:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg065 = data.readString();
                    int _arg115 = data.readInt();
                    int _arg24 = data.readInt();
                    boolean _arg35 = data.readInt() != 0;
                    boolean _result72 = reqMapDownloadAll(_arg065, _arg115, _arg24, _arg35);
                    reply.writeNoException();
                    reply.writeInt(_result72 ? 1 : 0);
                    return true;
                case TRANSACTION_reqMapDownloadOne /* 75 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg066 = data.readString();
                    String _arg116 = data.readString();
                    int _arg25 = data.readInt();
                    int _arg36 = data.readInt();
                    boolean _result73 = reqMapDownloadOne(_arg066, _arg116, _arg25, _arg36);
                    reply.writeNoException();
                    reply.writeInt(_result73 ? 1 : 0);
                    return true;
                case 76:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg067 = data.readString();
                    boolean _arg117 = data.readInt() != 0;
                    boolean _result74 = reqMapRegisterNotification(_arg067, _arg117);
                    reply.writeNoException();
                    reply.writeInt(_result74 ? 1 : 0);
                    return true;
                case 77:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg068 = data.readString();
                    boolean _result75 = reqMapDownloadInterrupt(_arg068);
                    reply.writeNoException();
                    reply.writeInt(_result75 ? 1 : 0);
                    return true;
                case 78:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg069 = data.readString();
                    boolean _result76 = reqMapDisconnect(_arg069);
                    reply.writeNoException();
                    reply.writeInt(_result76 ? 1 : 0);
                    return true;
                case 79:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result77 = isMapNotificationRegistered();
                    reply.writeNoException();
                    reply.writeInt(_result77 ? 1 : 0);
                    return true;
                case TRANSACTION_reqMapSendMessage /* 80 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg070 = data.readString();
                    String _arg118 = data.readString();
                    String _arg26 = data.readString();
                    boolean _result78 = reqMapSendMessage(_arg070, _arg118, _arg26);
                    reply.writeNoException();
                    reply.writeInt(_result78 ? 1 : 0);
                    return true;
                case TRANSACTION_reqMapDeleteMessage /* 81 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg071 = data.readString();
                    int _arg119 = data.readInt();
                    String _arg27 = data.readString();
                    boolean _result79 = reqMapDeleteMessage(_arg071, _arg119, _arg27);
                    reply.writeNoException();
                    reply.writeInt(_result79 ? 1 : 0);
                    return true;
                case TRANSACTION_reqMapChangeReadStatus /* 82 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg072 = data.readString();
                    int _arg120 = data.readInt();
                    String _arg28 = data.readString();
                    boolean _arg37 = data.readInt() != 0;
                    boolean _result80 = reqMapChangeReadStatus(_arg072, _arg120, _arg28, _arg37);
                    reply.writeNoException();
                    reply.writeInt(_result80 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpConnect /* 83 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg024 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg024 = null;
                    }
                    boolean _result81 = reqHfpConnect(_arg024);
                    reply.writeNoException();
                    reply.writeInt(_result81 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpDisconnect /* 84 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg023 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg023 = null;
                    }
                    boolean _result82 = reqHfpDisconnect(_arg023);
                    reply.writeNoException();
                    reply.writeInt(_result82 ? 1 : 0);
                    return true;
                case TRANSACTION_getHfpConnectedDevices /* 85 */:
                    data.enforceInterface(DESCRIPTOR);
                    List<BluetoothDevice> _result83 = getHfpConnectedDevices();
                    reply.writeNoException();
                    reply.writeTypedList(_result83);
                    return true;
                case TRANSACTION_getHfpConnectionState /* 86 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg022 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg022 = null;
                    }
                    int _result84 = getHfpConnectionState(_arg022);
                    reply.writeNoException();
                    reply.writeInt(_result84);
                    return true;
                case TRANSACTION_setHfpPriority /* 87 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg021 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg021 = null;
                    }
                    int _arg121 = data.readInt();
                    boolean _result85 = setHfpPriority(_arg021, _arg121);
                    reply.writeNoException();
                    reply.writeInt(_result85 ? 1 : 0);
                    return true;
                case TRANSACTION_getHfpPriority /* 88 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg020 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg020 = null;
                    }
                    int _result86 = getHfpPriority(_arg020);
                    reply.writeNoException();
                    reply.writeInt(_result86);
                    return true;
                case TRANSACTION_startHfpVoiceRecognition /* 89 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg019 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg019 = null;
                    }
                    boolean _result87 = startHfpVoiceRecognition(_arg019);
                    reply.writeNoException();
                    reply.writeInt(_result87 ? 1 : 0);
                    return true;
                case TRANSACTION_stopHfpVoiceRecognition /* 90 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg018 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg018 = null;
                    }
                    boolean _result88 = stopHfpVoiceRecognition(_arg018);
                    reply.writeNoException();
                    reply.writeInt(_result88 ? 1 : 0);
                    return true;
                case TRANSACTION_acceptIncomingConnect /* 91 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg017 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg017 = null;
                    }
                    boolean _result89 = acceptIncomingConnect(_arg017);
                    reply.writeNoException();
                    reply.writeInt(_result89 ? 1 : 0);
                    return true;
                case TRANSACTION_rejectHfpIncomingConnect /* 92 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg016 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg016 = null;
                    }
                    boolean _result90 = rejectHfpIncomingConnect(_arg016);
                    reply.writeNoException();
                    reply.writeInt(_result90 ? 1 : 0);
                    return true;
                case TRANSACTION_getHfpAudioState /* 93 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg015 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg015 = null;
                    }
                    int _result91 = getHfpAudioState(_arg015);
                    reply.writeNoException();
                    reply.writeInt(_result91);
                    return true;
                case 94:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result92 = connectHfpAudio();
                    reply.writeNoException();
                    reply.writeInt(_result92 ? 1 : 0);
                    return true;
                case 95:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result93 = disconnectHfpAudio();
                    reply.writeNoException();
                    reply.writeInt(_result93 ? 1 : 0);
                    return true;
                case 96:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg014 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg014 = null;
                    }
                    int _arg122 = data.readInt();
                    boolean _result94 = acceptHfpCall(_arg014, _arg122);
                    reply.writeNoException();
                    reply.writeInt(_result94 ? 1 : 0);
                    return true;
                case 97:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg013 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg013 = null;
                    }
                    boolean _result95 = rejectHfpCall(_arg013);
                    reply.writeNoException();
                    reply.writeInt(_result95 ? 1 : 0);
                    return true;
                case 98:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg012 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg012 = null;
                    }
                    boolean _result96 = holdHfpCall(_arg012);
                    reply.writeNoException();
                    reply.writeInt(_result96 ? 1 : 0);
                    return true;
                case 99:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg011 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg011 = null;
                    }
                    int _arg123 = data.readInt();
                    boolean _result97 = terminateHfpCall(_arg011, _arg123);
                    reply.writeNoException();
                    reply.writeInt(_result97 ? 1 : 0);
                    return true;
                case 100:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg010 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg010 = null;
                    }
                    int _arg124 = data.readInt();
                    boolean _result98 = enterHfpPrivateMode(_arg010, _arg124);
                    reply.writeNoException();
                    reply.writeInt(_result98 ? 1 : 0);
                    return true;
                case 101:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg09 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg09 = null;
                    }
                    boolean _result99 = reqHfpRedial(_arg09);
                    reply.writeNoException();
                    reply.writeInt(_result99 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpDial /* 102 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg08 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg08 = null;
                    }
                    String _arg125 = data.readString();
                    boolean _result100 = reqHfpDial(_arg08, _arg125);
                    reply.writeNoException();
                    reply.writeInt(_result100 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpDialMemory /* 103 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg07 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg07 = null;
                    }
                    int _arg126 = data.readInt();
                    boolean _result101 = reqHfpDialMemory(_arg07, _arg126);
                    reply.writeNoException();
                    reply.writeInt(_result101 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpSendDTMF /* 104 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg06 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg06 = null;
                    }
                    byte _arg127 = data.readByte();
                    boolean _result102 = reqHfpSendDTMF(_arg06, _arg127);
                    reply.writeNoException();
                    reply.writeInt(_result102 ? 1 : 0);
                    return true;
                case TRANSACTION_getHfpLastVoiceTagNumber /* 105 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg05 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg05 = null;
                    }
                    boolean _result103 = getHfpLastVoiceTagNumber(_arg05);
                    reply.writeNoException();
                    reply.writeInt(_result103 ? 1 : 0);
                    return true;
                case TRANSACTION_getHfpCurrentCalls /* 106 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg04 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    List<NfHfpClientCall> _result104 = getHfpCurrentCalls(_arg04);
                    reply.writeNoException();
                    reply.writeTypedList(_result104);
                    return true;
                case TRANSACTION_explicitHfpCallTransfer /* 107 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    boolean _result105 = explicitHfpCallTransfer(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result105 ? 1 : 0);
                    return true;
                case TRANSACTION_getHfpCurrentAgEvents /* 108 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    Bundle _result106 = getHfpCurrentAgEvents(_arg02);
                    reply.writeNoException();
                    if (_result106 != null) {
                        reply.writeInt(1);
                        _result106.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case TRANSACTION_getHfpCurrentAgFeatures /* 109 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    Bundle _result107 = getHfpCurrentAgFeatures(_arg0);
                    reply.writeNoException();
                    if (_result107 != null) {
                        reply.writeInt(1);
                        _result107.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 110:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg073 = data.readString();
                    boolean _result108 = setOppFilePath(_arg073);
                    reply.writeNoException();
                    reply.writeInt(_result108 ? 1 : 0);
                    return true;
                case 111:
                    data.enforceInterface(DESCRIPTOR);
                    String _result109 = getOppFilePath();
                    reply.writeNoException();
                    reply.writeString(_result109);
                    return true;
                case 112:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg074 = data.readInt() != 0;
                    boolean _result110 = acceptOppReceiveFile(_arg074);
                    reply.writeNoException();
                    reply.writeInt(_result110 ? 1 : 0);
                    return true;
                case 113:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result111 = interruptOppReceiveFile();
                    reply.writeNoException();
                    reply.writeInt(_result111 ? 1 : 0);
                    return true;
                case 114:
                    data.enforceInterface(DESCRIPTOR);
                    pauseHfpRender();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_startHfpRender /* 115 */:
                    data.enforceInterface(DESCRIPTOR);
                    startHfpRender();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_muteHfpMic /* 116 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg075 = data.readInt() != 0;
                    muteHfpMic(_arg075);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_openGattServer /* 117 */:
                    data.enforceInterface(DESCRIPTOR);
                    openGattServer();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_closeGattServer /* 118 */:
                    data.enforceInterface(DESCRIPTOR);
                    closeGattServer();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_reqGattServerConnect /* 119 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg076 = data.readInt();
                    String _arg128 = data.readString();
                    boolean _arg29 = data.readInt() != 0;
                    reqGattServerConnect(_arg076, _arg128, _arg29);
                    reply.writeNoException();
                    return true;
                case 120:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg077 = data.readInt();
                    String _arg129 = data.readString();
                    reqGattServerDisconnect(_arg077, _arg129);
                    reply.writeNoException();
                    return true;
                case 121:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg078 = data.readInt();
                    int _arg130 = data.readInt();
                    int _arg210 = data.readInt();
                    int _arg38 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg42 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg42 = null;
                    }
                    beginGattServiceDeclaration(_arg078, _arg130, _arg210, _arg38, _arg42);
                    reply.writeNoException();
                    return true;
                case 122:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg079 = data.readInt();
                    int _arg131 = data.readInt();
                    int _arg211 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg32 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    reqGattAddIncludedService(_arg079, _arg131, _arg211, _arg32);
                    reply.writeNoException();
                    return true;
                case 123:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg080 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg12 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    int _arg212 = data.readInt();
                    int _arg39 = data.readInt();
                    reqGattAddCharacteristic(_arg080, _arg12, _arg212, _arg39);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_reqGattAddDescriptor /* 124 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg081 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg1 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    int _arg213 = data.readInt();
                    reqGattAddDescriptor(_arg081, _arg1, _arg213);
                    reply.writeNoException();
                    return true;
                case 125:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg082 = data.readInt();
                    endGattServiceDeclaration(_arg082);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_reqGattRemoveService /* 126 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg083 = data.readInt();
                    int _arg132 = data.readInt();
                    int _arg214 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg3 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    reqGattRemoveService(_arg083, _arg132, _arg214, _arg3);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_reqGattClearServices /* 127 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg084 = data.readInt();
                    reqGattClearServices(_arg084);
                    reply.writeNoException();
                    return true;
                case 128:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg085 = data.readInt();
                    String _arg133 = data.readString();
                    int _arg215 = data.readInt();
                    int _arg310 = data.readInt();
                    int _arg45 = data.readInt();
                    byte[] _arg52 = data.createByteArray();
                    reqGattSendResponse(_arg085, _arg133, _arg215, _arg310, _arg45, _arg52);
                    reply.writeNoException();
                    return true;
                case 129:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg086 = data.readInt();
                    String _arg134 = data.readString();
                    int _arg216 = data.readInt();
                    int _arg311 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg4 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg4 = null;
                    }
                    int _arg53 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg6 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg6 = null;
                    }
                    boolean _arg7 = data.readInt() != 0;
                    byte[] _arg8 = data.createByteArray();
                    reqGattSendNotification(_arg086, _arg134, _arg216, _arg311, _arg4, _arg53, _arg6, _arg7, _arg8);
                    reply.writeNoException();
                    return true;
                case 130:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg087 = data.readInt() != 0;
                    reqGattServerListen(_arg087);
                    reply.writeNoException();
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
        public static class Proxy implements INfCommandJni {
            public static INfCommandJni sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean initJniA2dp() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().initJniA2dp();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean initJniAvrcp() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().initJniAvrcp();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean initJniHid() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().initJniHid();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean initJniPbap() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().initJniPbap();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean initJniMap() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().initJniMap();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean initJniHfp() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().initJniHfp();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean initJniOpp() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().initJniOpp();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean initJniGattServer() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().initJniGattServer();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public int getBtRoleMode() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean switchBtRoleMode(int mode) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean getRemoteServiceRecord(String address, ParcelUuid uuid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getRemoteServiceRecord(address, uuid);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public int createSdpRecord(String serviceName, int profile, int rfcommChannel, int l2capPsm, int version, int features) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(serviceName);
                    _data.writeInt(profile);
                    _data.writeInt(rfcommChannel);
                    _data.writeInt(l2capPsm);
                    _data.writeInt(version);
                    _data.writeInt(features);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().createSdpRecord(serviceName, profile, rfcommChannel, l2capPsm, version, features);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean registerJniCallback(INfCallbackJni cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerJniCallback(cb);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean unregisterJniCallback(INfCallbackJni cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterJniCallback(cb);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean registerJniPbapCallback(INfCallbackJniPbap cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerJniPbapCallback(cb);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean unregisterJniPbapCallback(INfCallbackJniPbap cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterJniPbapCallback(cb);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqA2dpConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqA2dpDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqA2dpDisconnect, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqBtRemoveAclConnection(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqBtRemoveAclConnection, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqBtRemoveAclConnection(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public String getBluedroidVersion() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBluedroidVersion, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getBluedroidVersion();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public String getNfJniServiceVersion() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getNfJniServiceVersion, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getNfJniServiceVersion();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpStop() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpForward() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpForward, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpStopFastForward() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpStopRewind() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpStopRewind, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean getAvrcpCapabilitySupportEvent() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAvrcpCapabilitySupportEvent, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getAvrcpCapabilitySupportEvent();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean getAvrcpPlayerSettingAttributesList() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAvrcpPlayerSettingAttributesList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getAvrcpPlayerSettingAttributesList();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean getAvrcpPlayerSettingValuesList(byte attributeId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(attributeId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAvrcpPlayerSettingValuesList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getAvrcpPlayerSettingValuesList(attributeId);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean getAvrcpPlayerSettingValues() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAvrcpPlayerSettingValues, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getAvrcpPlayerSettingValues();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean setAvrcpPlayerSettingValue(byte attributeId, byte valueId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(attributeId);
                    _data.writeByte(valueId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setAvrcpPlayerSettingValue, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().setAvrcpPlayerSettingValue(attributeId, valueId);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean getAvrcpElementAttributes() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAvrcpElementAttributes, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getAvrcpElementAttributes();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean getAvrcpPlayStatus() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAvrcpPlayStatus, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getAvrcpPlayStatus();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean registerAvrcpEvent(byte event_id, int interval) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(event_id);
                    _data.writeInt(interval);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerAvrcpEvent, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerAvrcpEvent(event_id, interval);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean unregisterAvrcpEvent(byte event_id) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(event_id);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterAvrcpEvent, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterAvrcpEvent(event_id);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpNextGroup() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpNextGroup, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpNextGroup();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpPreviousGroup() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpPreviousGroup, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpPreviousGroup();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean getAvrcpPlayerSettingAttributeText(byte attributeId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(attributeId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAvrcpPlayerSettingAttributeText, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getAvrcpPlayerSettingAttributeText(attributeId);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean getAvrcpPlayerSettingValueText(byte attributeId, byte valueId) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(attributeId);
                    _data.writeByte(valueId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAvrcpPlayerSettingValueText, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getAvrcpPlayerSettingValueText(attributeId, valueId);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpContinuingResponse() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpContinuingResponse, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpContinuingResponse();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean abortAvrcpContinuingResponse() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_abortAvrcpContinuingResponse, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().abortAvrcpContinuingResponse();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpQueryVersion(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpQueryVersion, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpQueryVersion(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void pauseA2dpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_pauseA2dpRender, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void startA2dpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_startA2dpRender, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean setA2dpLocalVolume(float vol) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(vol);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setA2dpLocalVolume, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean setA2dpStreamType(int type) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setA2dpStreamType, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpVersion(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpVersion, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpVersion(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public int getAvrcpVersion(String address) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAvrcpVersion, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getAvrcpVersion(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpBrowseConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpBrowseConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpBrowseConnect(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpBrowseDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpBrowseDisconnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpBrowseDisconnect(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpGetFolderItem(byte scope) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(scope);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpGetFolderItem, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpGetFolderItem(scope);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpChangePath(byte updown, long uid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(updown);
                    _data.writeLong(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpChangePath, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpChangePath(updown, uid);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpSetBrowsedPlayer(int player) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(player);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpSetBrowsedPlayer, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpSetBrowsedPlayer(player);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpPlayItem(long item, int uid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(item);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpPlayItem, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpPlayItem(item, uid);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpAddToNowPlaying(long item, int uid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(item);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpAddToNowPlaying, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpAddToNowPlaying(item, uid);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpSearchString(String data) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(data);
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpSearchString(data);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqAvrcpGetItemAttributes(byte scope, long item, int uid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(scope);
                    _data.writeLong(item);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqAvrcpGetItemAttributes, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqAvrcpGetItemAttributes(scope, item, uid);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqHidConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqHidDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHidDisconnect, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqHidSendData(String address, String report) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(report);
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHidSendData(address, report);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqPbapConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqPbapConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapConnect(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqPbapDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(70, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapDisconnect(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqPbapStartDownload(String address, int access, int filter, int offset, int max_count) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(access);
                    _data.writeInt(filter);
                    _data.writeInt(offset);
                    _data.writeInt(max_count);
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapStartDownload(address, access, filter, offset, max_count);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqPbapStopDownload(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapStopDownload(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqPbapGetSize(String address, int access) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(access);
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqPbapGetSize(address, access);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqMapDownloadAll(String address, int type, int folder, boolean isDownloadDetail) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(type);
                    _data.writeInt(folder);
                    _data.writeInt(isDownloadDetail ? 1 : 0);
                    boolean _status = this.mRemote.transact(74, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapDownloadAll(address, type, folder, isDownloadDetail);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqMapDownloadOne(String address, String handle, int type, int folder) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(type);
                    _data.writeInt(folder);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqMapDownloadOne, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapDownloadOne(address, handle, type, folder);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqMapRegisterNotification(String address, boolean enable) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(76, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapRegisterNotification(address, enable);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqMapDownloadInterrupt(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(77, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqMapDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(78, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapDisconnect(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean isMapNotificationRegistered() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isMapNotificationRegistered();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqMapSendMessage(String address, String msg, String target) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(msg);
                    _data.writeString(target);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqMapSendMessage, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqMapSendMessage(address, msg, target);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqMapDeleteMessage(String address, int folder, String handle) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(folder);
                    _data.writeString(handle);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqMapDeleteMessage, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
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
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqMapChangeReadStatus, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqHfpConnect(BluetoothDevice device) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpConnect(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqHfpDisconnect(BluetoothDevice device) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpDisconnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpDisconnect(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public List<BluetoothDevice> getHfpConnectedDevices() throws RemoteException {
                List<BluetoothDevice> createTypedArrayList;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpConnectedDevices, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getHfpConnectedDevices();
                    } else {
                        _reply.readException();
                        createTypedArrayList = _reply.createTypedArrayList(BluetoothDevice.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public int getHfpConnectionState(BluetoothDevice device) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpConnectionState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHfpConnectionState(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean setHfpPriority(BluetoothDevice device, int priority) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(priority);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setHfpPriority, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().setHfpPriority(device, priority);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public int getHfpPriority(BluetoothDevice device) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpPriority, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHfpPriority(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean startHfpVoiceRecognition(BluetoothDevice device) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_startHfpVoiceRecognition, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().startHfpVoiceRecognition(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean stopHfpVoiceRecognition(BluetoothDevice device) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_stopHfpVoiceRecognition, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().stopHfpVoiceRecognition(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean acceptIncomingConnect(BluetoothDevice device) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_acceptIncomingConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().acceptIncomingConnect(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean rejectHfpIncomingConnect(BluetoothDevice device) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_rejectHfpIncomingConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().rejectHfpIncomingConnect(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public int getHfpAudioState(BluetoothDevice device) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpAudioState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHfpAudioState(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean connectHfpAudio() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(94, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().connectHfpAudio();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean disconnectHfpAudio() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(95, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().disconnectHfpAudio();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean acceptHfpCall(BluetoothDevice device, int flag) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(flag);
                    boolean _status = this.mRemote.transact(96, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().acceptHfpCall(device, flag);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean rejectHfpCall(BluetoothDevice device) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(97, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().rejectHfpCall(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean holdHfpCall(BluetoothDevice device) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(98, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().holdHfpCall(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean terminateHfpCall(BluetoothDevice device, int index) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(index);
                    boolean _status = this.mRemote.transact(99, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().terminateHfpCall(device, index);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean enterHfpPrivateMode(BluetoothDevice device, int index) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(index);
                    boolean _status = this.mRemote.transact(100, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().enterHfpPrivateMode(device, index);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqHfpRedial(BluetoothDevice device) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(101, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpRedial(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqHfpDial(BluetoothDevice device, String number) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(number);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpDial, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpDial(device, number);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqHfpDialMemory(BluetoothDevice device, int location) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(location);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpDialMemory, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpDialMemory(device, location);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean reqHfpSendDTMF(BluetoothDevice device, byte code) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByte(code);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpSendDTMF, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpSendDTMF(device, code);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean getHfpLastVoiceTagNumber(BluetoothDevice device) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpLastVoiceTagNumber, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getHfpLastVoiceTagNumber(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public List<NfHfpClientCall> getHfpCurrentCalls(BluetoothDevice device) throws RemoteException {
                List<NfHfpClientCall> createTypedArrayList;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpCurrentCalls, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getHfpCurrentCalls(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean explicitHfpCallTransfer(BluetoothDevice device) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_explicitHfpCallTransfer, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().explicitHfpCallTransfer(device);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public Bundle getHfpCurrentAgEvents(BluetoothDevice device) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpCurrentAgEvents, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getHfpCurrentAgEvents(device);
                    } else {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public Bundle getHfpCurrentAgFeatures(BluetoothDevice device) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpCurrentAgFeatures, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().getHfpCurrentAgFeatures(device);
                    } else {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean setOppFilePath(String path) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(110, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public String getOppFilePath() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(111, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean acceptOppReceiveFile(boolean accept) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(accept ? 1 : 0);
                    boolean _status = this.mRemote.transact(112, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().acceptOppReceiveFile(accept);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public boolean interruptOppReceiveFile() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(113, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().interruptOppReceiveFile();
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void pauseHfpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(114, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void startHfpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_startHfpRender, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
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

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void openGattServer() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_openGattServer, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openGattServer();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void closeGattServer() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_closeGattServer, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeGattServer();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void reqGattServerConnect(int servertIf, String address, boolean isDirect) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(servertIf);
                    _data.writeString(address);
                    _data.writeInt(isDirect ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqGattServerConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqGattServerConnect(servertIf, address, isDirect);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void reqGattServerDisconnect(int serverIf, String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(120, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqGattServerDisconnect(serverIf, address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void beginGattServiceDeclaration(int serverIf, int srvcType, int srvcInstanceId, int minHandles, ParcelUuid srvcId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    _data.writeInt(srvcType);
                    _data.writeInt(srvcInstanceId);
                    _data.writeInt(minHandles);
                    if (srvcId != null) {
                        _data.writeInt(1);
                        srvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(121, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().beginGattServiceDeclaration(serverIf, srvcType, srvcInstanceId, minHandles, srvcId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void reqGattAddIncludedService(int serverIf, int srvcType, int srvcInstanceId, ParcelUuid srvcId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    _data.writeInt(srvcType);
                    _data.writeInt(srvcInstanceId);
                    if (srvcId != null) {
                        _data.writeInt(1);
                        srvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(122, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqGattAddIncludedService(serverIf, srvcType, srvcInstanceId, srvcId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void reqGattAddCharacteristic(int serverIf, ParcelUuid charId, int properties, int permissions) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    if (charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(properties);
                    _data.writeInt(permissions);
                    boolean _status = this.mRemote.transact(123, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqGattAddCharacteristic(serverIf, charId, properties, permissions);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void reqGattAddDescriptor(int serverIf, ParcelUuid descId, int permissions) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    if (descId != null) {
                        _data.writeInt(1);
                        descId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(permissions);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqGattAddDescriptor, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqGattAddDescriptor(serverIf, descId, permissions);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void endGattServiceDeclaration(int serverIf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    boolean _status = this.mRemote.transact(125, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().endGattServiceDeclaration(serverIf);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void reqGattRemoveService(int serverIf, int srvcType, int srvcInstanceId, ParcelUuid srvcId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    _data.writeInt(srvcType);
                    _data.writeInt(srvcInstanceId);
                    if (srvcId != null) {
                        _data.writeInt(1);
                        srvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqGattRemoveService, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqGattRemoveService(serverIf, srvcType, srvcInstanceId, srvcId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void reqGattClearServices(int serverIf) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqGattClearServices, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqGattClearServices(serverIf);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void reqGattSendResponse(int serverIf, String address, int requestId, int status, int offset, byte[] value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    _data.writeString(address);
                    _data.writeInt(requestId);
                    _data.writeInt(status);
                    _data.writeInt(offset);
                    _data.writeByteArray(value);
                    boolean _status = this.mRemote.transact(128, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqGattSendResponse(serverIf, address, requestId, status, offset, value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void reqGattSendNotification(int serverIf, String address, int srvcType, int srvcInstanceId, ParcelUuid srvcId, int charInstanceId, ParcelUuid charId, boolean confirm, byte[] value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    _data.writeString(address);
                    _data.writeInt(srvcType);
                    _data.writeInt(srvcInstanceId);
                    if (srvcId != null) {
                        _data.writeInt(1);
                        srvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(charInstanceId);
                    if (charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(confirm ? 1 : 0);
                    _data.writeByteArray(value);
                    boolean _status = this.mRemote.transact(129, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqGattSendNotification(serverIf, address, srvcType, srvcInstanceId, srvcId, charInstanceId, charId, confirm, value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandJni
            public void reqGattServerListen(boolean listen) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(listen ? 1 : 0);
                    boolean _status = this.mRemote.transact(130, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqGattServerListen(listen);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INfCommandJni impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCommandJni getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
