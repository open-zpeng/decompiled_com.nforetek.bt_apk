package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface INfCallbackJni extends IInterface {
    void onAclStateChanged(int i, byte[] bArr, int i2, int i3) throws RemoteException;

    void onJniA2dpStateChanged(int i, byte[] bArr) throws RemoteException;

    void onJniAvrcpBrowseStateChanged(int i, byte[] bArr) throws RemoteException;

    void onJniAvrcpGetElementAttributes(int[] iArr, String[] strArr) throws RemoteException;

    void onJniAvrcpRegisterEventCallbackPlaybackPosChanged(int i) throws RemoteException;

    void onJniAvrcpRegisterEventCallbackPlaybackStatusChanged(byte b) throws RemoteException;

    void onJniAvrcpRegisterEventCallbackSettingChanged(int i, byte[] bArr, byte[] bArr2) throws RemoteException;

    void onJniAvrcpRegisterEventCallbackTrackChanged(byte[] bArr) throws RemoteException;

    void onJniAvrcpRegisterEventWatcherSuccess(byte b) throws RemoteException;

    void onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged(byte b, byte b2) throws RemoteException;

    void onJniAvrcpRetCapabilitiesSupportEvent(int i, byte[] bArr) throws RemoteException;

    void onJniAvrcpStateChanged(int i, byte[] bArr) throws RemoteException;

    void onJniBtRoleModeChanged(int i) throws RemoteException;

    void onJniGattListen(int i) throws RemoteException;

    void onJniGattServerCharacteristicReadRequest(String str, int i, int i2, boolean z, int i3, int i4, ParcelUuid parcelUuid, int i5, ParcelUuid parcelUuid2) throws RemoteException;

    void onJniGattServerCharacteristicWriteRequest(String str, int i, int i2, int i3, boolean z, boolean z2, int i4, int i5, ParcelUuid parcelUuid, int i6, ParcelUuid parcelUuid2, byte[] bArr) throws RemoteException;

    void onJniGattServerConnectionState(String str, int i, boolean z) throws RemoteException;

    void onJniGattServerDescriptorReadRequest(String str, int i, int i2, boolean z, int i3, int i4, ParcelUuid parcelUuid, int i5, ParcelUuid parcelUuid2, ParcelUuid parcelUuid3) throws RemoteException;

    void onJniGattServerDescriptorWriteRequest(String str, int i, int i2, int i3, boolean z, boolean z2, int i4, int i5, ParcelUuid parcelUuid, int i6, ParcelUuid parcelUuid2, ParcelUuid parcelUuid3, byte[] bArr) throws RemoteException;

    void onJniGattServerExecuteWrite(String str, int i, boolean z) throws RemoteException;

    void onJniGattServerScanResult(String str, int i, byte[] bArr) throws RemoteException;

    void onJniGattServerServerRegistered(int i) throws RemoteException;

    void onJniGattServerServiceAdded(int i, int i2, int i3, ParcelUuid parcelUuid) throws RemoteException;

    void onJniHfpAgEvent(String str, int i, int i2, int i3, int i4, String str2, int i5, int i6, String str3) throws RemoteException;

    void onJniHfpAudioStateChanged(String str, int i, int i2) throws RemoteException;

    void onJniHfpCallChanged(NfHfpClientCall nfHfpClientCall) throws RemoteException;

    void onJniHfpConnectionStateChanged(String str, int i, int i2) throws RemoteException;

    void onJniHidStateChanged(int i, byte[] bArr, int i2) throws RemoteException;

    void onJniMapChangeReadStatusState(String str, String str2, int i) throws RemoteException;

    void onJniMapConnectionStateChanged(String str, int i, int i2) throws RemoteException;

    void onJniMapDeleteMessageState(String str, String str2, int i) throws RemoteException;

    void onJniMapMemoryAvailable(String str, int i, int i2, int i3) throws RemoteException;

    void onJniMapMessageDeleted(String str, String str2, int i, int i2) throws RemoteException;

    void onJniMapMessageDeliverStatus(String str, String str2, int i) throws RemoteException;

    void onJniMapMessageSendingStatus(String str, String str2, int i) throws RemoteException;

    void onJniMapMessageShifted(String str, String str2, int i, int i2, int i3) throws RemoteException;

    void onJniMapReceiveMessageContent(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, String str7, String str8, int i3, int i4, int i5) throws RemoteException;

    void onJniMapSendMessageState(String str, String str2, int i) throws RemoteException;

    void onJniOppReceiveFileInfo(String str, int i, String str2, String str3) throws RemoteException;

    void onJniOppReceiveProgress(int i) throws RemoteException;

    void onJniOppStateChanged(String str, int i, int i2, int i3) throws RemoteException;

    void onJniReceiveNewMessage(String str, String str2, int i, int i2) throws RemoteException;

    void onJniRecreateBondDevice(String str, int i) throws RemoteException;

    void onJniScoStateChanged(int i, int i2, byte[] bArr) throws RemoteException;

    void retJniAvrcp13PlayStatus(long j, long j2, byte b) throws RemoteException;

    void retJniAvrcp13PlayerSettingAttributesList(byte[] bArr) throws RemoteException;

    void retJniAvrcp13PlayerSettingCurrentValues(byte[] bArr, byte[] bArr2) throws RemoteException;

    void retJniAvrcp13PlayerSettingValuesList(byte b, byte[] bArr) throws RemoteException;

    void retJniAvrcp13SetPlayerSettingValueSuccess(int i) throws RemoteException;

    void retJniAvrcp14AddToNowPlaying(byte b) throws RemoteException;

    void retJniAvrcp14ChangePath(byte b, int i) throws RemoteException;

    void retJniAvrcp14FolderItem(byte b, byte b2, byte[] bArr, int i, byte[] bArr2, long[] jArr, byte[] bArr3, byte[] bArr4, char[] cArr, String[] strArr, boolean z) throws RemoteException;

    void retJniAvrcp14GetItemAttributes(byte b, byte b2, int i, byte[] bArr, char[] cArr, String[] strArr) throws RemoteException;

    void retJniAvrcp14MediaItem(byte b, byte b2, byte[] bArr, int i, byte[] bArr2, long[] jArr, byte[] bArr3, char[] cArr, String[] strArr, int i2, int[] iArr, String[] strArr2, boolean z) throws RemoteException;

    void retJniAvrcp14MediaItemElementAttribute(byte[] bArr, long j, int i, int i2, String str) throws RemoteException;

    void retJniAvrcp14MediaPlayerItem(byte b, byte b2, byte[] bArr, int i, byte[] bArr2, char[] cArr, byte[] bArr3, int[] iArr, byte[] bArr4, char[] cArr2, String[] strArr, boolean z) throws RemoteException;

    void retJniAvrcp14PlayItem(byte b) throws RemoteException;

    void retJniAvrcp14SearchString(byte b, int i) throws RemoteException;

    void retJniAvrcp14SetBrowsedPlayer(byte b, byte b2, byte[] bArr, int i, char c, byte b3, String[] strArr) throws RemoteException;

    void retJniAvrcpQueryVersion(int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCallbackJni {
        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniA2dpStateChanged(int state, byte[] address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpStateChanged(int state, byte[] address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniScoStateChanged(int index, int state, byte[] address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventWatcherSuccess(byte eventId) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged(byte eventId, byte playbackState) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventCallbackPlaybackStatusChanged(byte playbackState) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventCallbackTrackChanged(byte[] uid) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventCallbackPlaybackPosChanged(int songPos) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRegisterEventCallbackSettingChanged(int count, byte[] attr, byte[] value) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpGetElementAttributes(int[] metadataAtrributeIds, String[] texts) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpRetCapabilitiesSupportEvent(int count, byte[] events) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp13PlayerSettingAttributesList(byte[] attributeIds) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp13PlayerSettingValuesList(byte attributeId, byte[] valueIds) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp13PlayerSettingCurrentValues(byte[] attributeIds, byte[] valueIds) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp13SetPlayerSettingValueSuccess(int reason) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp13PlayStatus(long songLen, long songPos, byte statusId) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcpQueryVersion(int version) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onAclStateChanged(int status, byte[] address, int state, int reason) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniHidStateChanged(int state, byte[] address, int reason) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniAvrcpBrowseStateChanged(int state, byte[] address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14FolderItem(byte status, byte pdu, byte[] uidCounter, int count, byte[] itemType, long[] folder, byte[] folderType, byte[] isPlayable, char[] charset, String[] name, boolean isFinal) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14MediaItem(byte status, byte pdu, byte[] uidCounter, int count, byte[] itemType, long[] folder, byte[] folderType, char[] charset, String[] name, int attr_count, int[] attr_ids, String[] attr_values, boolean isFinal) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14MediaItemElementAttribute(byte[] uidCounter, long uid, int attribute_id, int charset, String name) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14MediaPlayerItem(byte status, byte pdu, byte[] idCounter, int count, byte[] itemType, char[] playerId, byte[] majorType, int[] subType, byte[] playStatus, char[] charset, String[] name, boolean isFinal) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14ChangePath(byte status, int itemNum) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14SetBrowsedPlayer(byte status, byte pdu, byte[] uidCounter, int count, char charset, byte depth, String[] name) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14PlayItem(byte status) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14AddToNowPlaying(byte status) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14GetItemAttributes(byte status, byte pdu, int count, byte[] attrId, char[] charset, String[] attrValue) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void retJniAvrcp14SearchString(byte status, int num) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapConnectionStateChanged(String address, int state, int reason) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapReceiveMessageContent(String address, String handle, String senderName, String senderAddr, String date, String recipientAddr, int type, int folder, String subject, String message, int readStatus, int currentCount, int totalCount) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniReceiveNewMessage(String address, String handle, int type, int folder) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapSendMessageState(String address, String target, int state) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapDeleteMessageState(String address, String handle, int reason) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapChangeReadStatusState(String address, String handle, int reason) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapMemoryAvailable(String address, int type, int structure, int available) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapMessageSendingStatus(String address, String handle, int status) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapMessageDeliverStatus(String address, String handle, int status) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapMessageShifted(String address, String handle, int type, int newFolder, int oldFolder) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniMapMessageDeleted(String address, String handle, int type, int folder) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniHfpConnectionStateChanged(String address, int prevState, int state) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniHfpAudioStateChanged(String address, int prevState, int state) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniHfpAgEvent(String address, int network, int roaming, int signal, int battery, String operator, int voice, int inBandRingtone, String subscriber) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniHfpCallChanged(NfHfpClientCall hcc) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniOppStateChanged(String address, int preState, int currentState, int reason) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniOppReceiveFileInfo(String fileName, int fileSize, String devName, String savePath) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniOppReceiveProgress(int receivedOffset) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniRecreateBondDevice(String address, int is_connect) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniBtRoleModeChanged(int mode) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerServerRegistered(int status) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerScanResult(String address, int rssi, byte[] advData) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerConnectionState(String address, int status, boolean connected) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerServiceAdded(int status, int srvcType, int srvcInstId, ParcelUuid srvcId) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerCharacteristicReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerDescriptorReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerCharacteristicWriteRequest(String address, int transId, int offset, int length, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, byte[] value) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerDescriptorWriteRequest(String address, int transId, int offset, int length, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId, byte[] value) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattServerExecuteWrite(String address, int transId, boolean execWrite) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJni
        public void onJniGattListen(int status) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCallbackJni {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCallbackJni";
        static final int TRANSACTION_onAclStateChanged = 18;
        static final int TRANSACTION_onJniA2dpStateChanged = 1;
        static final int TRANSACTION_onJniAvrcpBrowseStateChanged = 20;
        static final int TRANSACTION_onJniAvrcpGetElementAttributes = 10;
        static final int TRANSACTION_onJniAvrcpRegisterEventCallbackPlaybackPosChanged = 8;
        static final int TRANSACTION_onJniAvrcpRegisterEventCallbackPlaybackStatusChanged = 6;
        static final int TRANSACTION_onJniAvrcpRegisterEventCallbackSettingChanged = 9;
        static final int TRANSACTION_onJniAvrcpRegisterEventCallbackTrackChanged = 7;
        static final int TRANSACTION_onJniAvrcpRegisterEventWatcherSuccess = 4;
        static final int TRANSACTION_onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged = 5;
        static final int TRANSACTION_onJniAvrcpRetCapabilitiesSupportEvent = 11;
        static final int TRANSACTION_onJniAvrcpStateChanged = 2;
        static final int TRANSACTION_onJniBtRoleModeChanged = 50;
        static final int TRANSACTION_onJniGattListen = 60;
        static final int TRANSACTION_onJniGattServerCharacteristicReadRequest = 55;
        static final int TRANSACTION_onJniGattServerCharacteristicWriteRequest = 57;
        static final int TRANSACTION_onJniGattServerConnectionState = 53;
        static final int TRANSACTION_onJniGattServerDescriptorReadRequest = 56;
        static final int TRANSACTION_onJniGattServerDescriptorWriteRequest = 58;
        static final int TRANSACTION_onJniGattServerExecuteWrite = 59;
        static final int TRANSACTION_onJniGattServerScanResult = 52;
        static final int TRANSACTION_onJniGattServerServerRegistered = 51;
        static final int TRANSACTION_onJniGattServerServiceAdded = 54;
        static final int TRANSACTION_onJniHfpAgEvent = 44;
        static final int TRANSACTION_onJniHfpAudioStateChanged = 43;
        static final int TRANSACTION_onJniHfpCallChanged = 45;
        static final int TRANSACTION_onJniHfpConnectionStateChanged = 42;
        static final int TRANSACTION_onJniHidStateChanged = 19;
        static final int TRANSACTION_onJniMapChangeReadStatusState = 36;
        static final int TRANSACTION_onJniMapConnectionStateChanged = 31;
        static final int TRANSACTION_onJniMapDeleteMessageState = 35;
        static final int TRANSACTION_onJniMapMemoryAvailable = 37;
        static final int TRANSACTION_onJniMapMessageDeleted = 41;
        static final int TRANSACTION_onJniMapMessageDeliverStatus = 39;
        static final int TRANSACTION_onJniMapMessageSendingStatus = 38;
        static final int TRANSACTION_onJniMapMessageShifted = 40;
        static final int TRANSACTION_onJniMapReceiveMessageContent = 32;
        static final int TRANSACTION_onJniMapSendMessageState = 34;
        static final int TRANSACTION_onJniOppReceiveFileInfo = 47;
        static final int TRANSACTION_onJniOppReceiveProgress = 48;
        static final int TRANSACTION_onJniOppStateChanged = 46;
        static final int TRANSACTION_onJniReceiveNewMessage = 33;
        static final int TRANSACTION_onJniRecreateBondDevice = 49;
        static final int TRANSACTION_onJniScoStateChanged = 3;
        static final int TRANSACTION_retJniAvrcp13PlayStatus = 16;
        static final int TRANSACTION_retJniAvrcp13PlayerSettingAttributesList = 12;
        static final int TRANSACTION_retJniAvrcp13PlayerSettingCurrentValues = 14;
        static final int TRANSACTION_retJniAvrcp13PlayerSettingValuesList = 13;
        static final int TRANSACTION_retJniAvrcp13SetPlayerSettingValueSuccess = 15;
        static final int TRANSACTION_retJniAvrcp14AddToNowPlaying = 28;
        static final int TRANSACTION_retJniAvrcp14ChangePath = 25;
        static final int TRANSACTION_retJniAvrcp14FolderItem = 21;
        static final int TRANSACTION_retJniAvrcp14GetItemAttributes = 29;
        static final int TRANSACTION_retJniAvrcp14MediaItem = 22;
        static final int TRANSACTION_retJniAvrcp14MediaItemElementAttribute = 23;
        static final int TRANSACTION_retJniAvrcp14MediaPlayerItem = 24;
        static final int TRANSACTION_retJniAvrcp14PlayItem = 27;
        static final int TRANSACTION_retJniAvrcp14SearchString = 30;
        static final int TRANSACTION_retJniAvrcp14SetBrowsedPlayer = 26;
        static final int TRANSACTION_retJniAvrcpQueryVersion = 17;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCallbackJni asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCallbackJni)) {
                return (INfCallbackJni) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelUuid _arg8;
            ParcelUuid _arg10;
            ParcelUuid _arg11;
            ParcelUuid _arg82;
            ParcelUuid _arg102;
            ParcelUuid _arg6;
            ParcelUuid _arg83;
            ParcelUuid _arg9;
            ParcelUuid _arg62;
            ParcelUuid _arg84;
            ParcelUuid _arg3;
            NfHfpClientCall _arg0;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    byte[] _arg1 = data.createByteArray();
                    onJniA2dpStateChanged(_arg02, _arg1);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    byte[] _arg12 = data.createByteArray();
                    onJniAvrcpStateChanged(_arg03, _arg12);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    int _arg13 = data.readInt();
                    byte[] _arg2 = data.createByteArray();
                    onJniScoStateChanged(_arg04, _arg13, _arg2);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg05 = data.readByte();
                    onJniAvrcpRegisterEventWatcherSuccess(_arg05);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg06 = data.readByte();
                    byte _arg14 = data.readByte();
                    onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged(_arg06, _arg14);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg07 = data.readByte();
                    onJniAvrcpRegisterEventCallbackPlaybackStatusChanged(_arg07);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg08 = data.createByteArray();
                    onJniAvrcpRegisterEventCallbackTrackChanged(_arg08);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg09 = data.readInt();
                    onJniAvrcpRegisterEventCallbackPlaybackPosChanged(_arg09);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg010 = data.readInt();
                    byte[] _arg15 = data.createByteArray();
                    byte[] _arg22 = data.createByteArray();
                    onJniAvrcpRegisterEventCallbackSettingChanged(_arg010, _arg15, _arg22);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _arg011 = data.createIntArray();
                    String[] _arg16 = data.createStringArray();
                    onJniAvrcpGetElementAttributes(_arg011, _arg16);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg012 = data.readInt();
                    byte[] _arg17 = data.createByteArray();
                    onJniAvrcpRetCapabilitiesSupportEvent(_arg012, _arg17);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg013 = data.createByteArray();
                    retJniAvrcp13PlayerSettingAttributesList(_arg013);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg014 = data.readByte();
                    byte[] _arg18 = data.createByteArray();
                    retJniAvrcp13PlayerSettingValuesList(_arg014, _arg18);
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg015 = data.createByteArray();
                    byte[] _arg19 = data.createByteArray();
                    retJniAvrcp13PlayerSettingCurrentValues(_arg015, _arg19);
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg016 = data.readInt();
                    retJniAvrcp13SetPlayerSettingValueSuccess(_arg016);
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    long _arg017 = data.readLong();
                    long _arg110 = data.readLong();
                    byte _arg23 = data.readByte();
                    retJniAvrcp13PlayStatus(_arg017, _arg110, _arg23);
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg018 = data.readInt();
                    retJniAvrcpQueryVersion(_arg018);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onAclStateChanged /* 18 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg019 = data.readInt();
                    byte[] _arg111 = data.createByteArray();
                    int _arg24 = data.readInt();
                    int _arg32 = data.readInt();
                    onAclStateChanged(_arg019, _arg111, _arg24, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniHidStateChanged /* 19 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg020 = data.readInt();
                    byte[] _arg112 = data.createByteArray();
                    int _arg25 = data.readInt();
                    onJniHidStateChanged(_arg020, _arg112, _arg25);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniAvrcpBrowseStateChanged /* 20 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg021 = data.readInt();
                    byte[] _arg113 = data.createByteArray();
                    onJniAvrcpBrowseStateChanged(_arg021, _arg113);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_retJniAvrcp14FolderItem /* 21 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg022 = data.readByte();
                    byte _arg114 = data.readByte();
                    byte[] _arg26 = data.createByteArray();
                    int _arg33 = data.readInt();
                    byte[] _arg4 = data.createByteArray();
                    long[] _arg5 = data.createLongArray();
                    byte[] _arg63 = data.createByteArray();
                    byte[] _arg7 = data.createByteArray();
                    char[] _arg85 = data.createCharArray();
                    String[] _arg92 = data.createStringArray();
                    boolean _arg103 = data.readInt() != 0;
                    retJniAvrcp14FolderItem(_arg022, _arg114, _arg26, _arg33, _arg4, _arg5, _arg63, _arg7, _arg85, _arg92, _arg103);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_retJniAvrcp14MediaItem /* 22 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg023 = data.readByte();
                    byte _arg115 = data.readByte();
                    byte[] _arg27 = data.createByteArray();
                    int _arg34 = data.readInt();
                    byte[] _arg42 = data.createByteArray();
                    long[] _arg52 = data.createLongArray();
                    byte[] _arg64 = data.createByteArray();
                    char[] _arg72 = data.createCharArray();
                    String[] _arg86 = data.createStringArray();
                    int _arg93 = data.readInt();
                    int[] _arg104 = data.createIntArray();
                    String[] _arg116 = data.createStringArray();
                    boolean _arg122 = data.readInt() != 0;
                    retJniAvrcp14MediaItem(_arg023, _arg115, _arg27, _arg34, _arg42, _arg52, _arg64, _arg72, _arg86, _arg93, _arg104, _arg116, _arg122);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_retJniAvrcp14MediaItemElementAttribute /* 23 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg024 = data.createByteArray();
                    long _arg117 = data.readLong();
                    int _arg28 = data.readInt();
                    int _arg35 = data.readInt();
                    String _arg43 = data.readString();
                    retJniAvrcp14MediaItemElementAttribute(_arg024, _arg117, _arg28, _arg35, _arg43);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_retJniAvrcp14MediaPlayerItem /* 24 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg025 = data.readByte();
                    byte _arg118 = data.readByte();
                    byte[] _arg29 = data.createByteArray();
                    int _arg36 = data.readInt();
                    byte[] _arg44 = data.createByteArray();
                    char[] _arg53 = data.createCharArray();
                    byte[] _arg65 = data.createByteArray();
                    int[] _arg73 = data.createIntArray();
                    byte[] _arg87 = data.createByteArray();
                    char[] _arg94 = data.createCharArray();
                    String[] _arg105 = data.createStringArray();
                    boolean _arg119 = data.readInt() != 0;
                    retJniAvrcp14MediaPlayerItem(_arg025, _arg118, _arg29, _arg36, _arg44, _arg53, _arg65, _arg73, _arg87, _arg94, _arg105, _arg119);
                    reply.writeNoException();
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg026 = data.readByte();
                    int _arg120 = data.readInt();
                    retJniAvrcp14ChangePath(_arg026, _arg120);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_retJniAvrcp14SetBrowsedPlayer /* 26 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg027 = data.readByte();
                    byte _arg121 = data.readByte();
                    byte[] _arg210 = data.createByteArray();
                    int _arg37 = data.readInt();
                    char _arg45 = (char) data.readInt();
                    byte _arg54 = data.readByte();
                    String[] _arg66 = data.createStringArray();
                    retJniAvrcp14SetBrowsedPlayer(_arg027, _arg121, _arg210, _arg37, _arg45, _arg54, _arg66);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_retJniAvrcp14PlayItem /* 27 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg028 = data.readByte();
                    retJniAvrcp14PlayItem(_arg028);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_retJniAvrcp14AddToNowPlaying /* 28 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg029 = data.readByte();
                    retJniAvrcp14AddToNowPlaying(_arg029);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_retJniAvrcp14GetItemAttributes /* 29 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg030 = data.readByte();
                    byte _arg123 = data.readByte();
                    int _arg211 = data.readInt();
                    byte[] _arg38 = data.createByteArray();
                    char[] _arg46 = data.createCharArray();
                    String[] _arg55 = data.createStringArray();
                    retJniAvrcp14GetItemAttributes(_arg030, _arg123, _arg211, _arg38, _arg46, _arg55);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_retJniAvrcp14SearchString /* 30 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg031 = data.readByte();
                    int _arg124 = data.readInt();
                    retJniAvrcp14SearchString(_arg031, _arg124);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniMapConnectionStateChanged /* 31 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg032 = data.readString();
                    int _arg125 = data.readInt();
                    int _arg212 = data.readInt();
                    onJniMapConnectionStateChanged(_arg032, _arg125, _arg212);
                    reply.writeNoException();
                    return true;
                case 32:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg033 = data.readString();
                    String _arg126 = data.readString();
                    String _arg213 = data.readString();
                    String _arg39 = data.readString();
                    String _arg47 = data.readString();
                    String _arg56 = data.readString();
                    int _arg67 = data.readInt();
                    int _arg74 = data.readInt();
                    String _arg88 = data.readString();
                    String _arg95 = data.readString();
                    int _arg106 = data.readInt();
                    int _arg1110 = data.readInt();
                    int _arg127 = data.readInt();
                    onJniMapReceiveMessageContent(_arg033, _arg126, _arg213, _arg39, _arg47, _arg56, _arg67, _arg74, _arg88, _arg95, _arg106, _arg1110, _arg127);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniReceiveNewMessage /* 33 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg034 = data.readString();
                    String _arg128 = data.readString();
                    int _arg214 = data.readInt();
                    int _arg310 = data.readInt();
                    onJniReceiveNewMessage(_arg034, _arg128, _arg214, _arg310);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniMapSendMessageState /* 34 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg035 = data.readString();
                    String _arg129 = data.readString();
                    int _arg215 = data.readInt();
                    onJniMapSendMessageState(_arg035, _arg129, _arg215);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniMapDeleteMessageState /* 35 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg036 = data.readString();
                    String _arg130 = data.readString();
                    int _arg216 = data.readInt();
                    onJniMapDeleteMessageState(_arg036, _arg130, _arg216);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniMapChangeReadStatusState /* 36 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg037 = data.readString();
                    String _arg131 = data.readString();
                    int _arg217 = data.readInt();
                    onJniMapChangeReadStatusState(_arg037, _arg131, _arg217);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniMapMemoryAvailable /* 37 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg038 = data.readString();
                    int _arg132 = data.readInt();
                    int _arg218 = data.readInt();
                    int _arg311 = data.readInt();
                    onJniMapMemoryAvailable(_arg038, _arg132, _arg218, _arg311);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniMapMessageSendingStatus /* 38 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg039 = data.readString();
                    String _arg133 = data.readString();
                    int _arg219 = data.readInt();
                    onJniMapMessageSendingStatus(_arg039, _arg133, _arg219);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniMapMessageDeliverStatus /* 39 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg040 = data.readString();
                    String _arg134 = data.readString();
                    int _arg220 = data.readInt();
                    onJniMapMessageDeliverStatus(_arg040, _arg134, _arg220);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniMapMessageShifted /* 40 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg041 = data.readString();
                    String _arg135 = data.readString();
                    int _arg221 = data.readInt();
                    int _arg312 = data.readInt();
                    int _arg48 = data.readInt();
                    onJniMapMessageShifted(_arg041, _arg135, _arg221, _arg312, _arg48);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniMapMessageDeleted /* 41 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg042 = data.readString();
                    String _arg136 = data.readString();
                    int _arg222 = data.readInt();
                    int _arg313 = data.readInt();
                    onJniMapMessageDeleted(_arg042, _arg136, _arg222, _arg313);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniHfpConnectionStateChanged /* 42 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg043 = data.readString();
                    int _arg137 = data.readInt();
                    int _arg223 = data.readInt();
                    onJniHfpConnectionStateChanged(_arg043, _arg137, _arg223);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniHfpAudioStateChanged /* 43 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg044 = data.readString();
                    int _arg138 = data.readInt();
                    int _arg224 = data.readInt();
                    onJniHfpAudioStateChanged(_arg044, _arg138, _arg224);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniHfpAgEvent /* 44 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg045 = data.readString();
                    int _arg139 = data.readInt();
                    int _arg225 = data.readInt();
                    int _arg314 = data.readInt();
                    int _arg49 = data.readInt();
                    String _arg57 = data.readString();
                    int _arg68 = data.readInt();
                    int _arg75 = data.readInt();
                    String _arg89 = data.readString();
                    onJniHfpAgEvent(_arg045, _arg139, _arg225, _arg314, _arg49, _arg57, _arg68, _arg75, _arg89);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniHfpCallChanged /* 45 */:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = NfHfpClientCall.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onJniHfpCallChanged(_arg0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniOppStateChanged /* 46 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg046 = data.readString();
                    int _arg140 = data.readInt();
                    int _arg226 = data.readInt();
                    int _arg315 = data.readInt();
                    onJniOppStateChanged(_arg046, _arg140, _arg226, _arg315);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniOppReceiveFileInfo /* 47 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg047 = data.readString();
                    int _arg141 = data.readInt();
                    String _arg227 = data.readString();
                    String _arg316 = data.readString();
                    onJniOppReceiveFileInfo(_arg047, _arg141, _arg227, _arg316);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniOppReceiveProgress /* 48 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg048 = data.readInt();
                    onJniOppReceiveProgress(_arg048);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniRecreateBondDevice /* 49 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg049 = data.readString();
                    int _arg142 = data.readInt();
                    onJniRecreateBondDevice(_arg049, _arg142);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniBtRoleModeChanged /* 50 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg050 = data.readInt();
                    onJniBtRoleModeChanged(_arg050);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniGattServerServerRegistered /* 51 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg051 = data.readInt();
                    onJniGattServerServerRegistered(_arg051);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniGattServerScanResult /* 52 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg052 = data.readString();
                    int _arg143 = data.readInt();
                    byte[] _arg228 = data.createByteArray();
                    onJniGattServerScanResult(_arg052, _arg143, _arg228);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniGattServerConnectionState /* 53 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg053 = data.readString();
                    int _arg144 = data.readInt();
                    boolean _arg229 = data.readInt() != 0;
                    onJniGattServerConnectionState(_arg053, _arg144, _arg229);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniGattServerServiceAdded /* 54 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg054 = data.readInt();
                    int _arg145 = data.readInt();
                    int _arg230 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg3 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    onJniGattServerServiceAdded(_arg054, _arg145, _arg230, _arg3);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniGattServerCharacteristicReadRequest /* 55 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg055 = data.readString();
                    int _arg146 = data.readInt();
                    int _arg231 = data.readInt();
                    boolean _arg317 = data.readInt() != 0;
                    int _arg410 = data.readInt();
                    int _arg58 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg62 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg62 = null;
                    }
                    int _arg76 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg84 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg84 = null;
                    }
                    onJniGattServerCharacteristicReadRequest(_arg055, _arg146, _arg231, _arg317, _arg410, _arg58, _arg62, _arg76, _arg84);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniGattServerDescriptorReadRequest /* 56 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg056 = data.readString();
                    int _arg147 = data.readInt();
                    int _arg232 = data.readInt();
                    boolean _arg318 = data.readInt() != 0;
                    int _arg411 = data.readInt();
                    int _arg59 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg6 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg6 = null;
                    }
                    int _arg77 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg83 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg83 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg9 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg9 = null;
                    }
                    onJniGattServerDescriptorReadRequest(_arg056, _arg147, _arg232, _arg318, _arg411, _arg59, _arg6, _arg77, _arg83, _arg9);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniGattServerCharacteristicWriteRequest /* 57 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg057 = data.readString();
                    int _arg148 = data.readInt();
                    int _arg233 = data.readInt();
                    int _arg319 = data.readInt();
                    boolean _arg412 = data.readInt() != 0;
                    boolean _arg510 = data.readInt() != 0;
                    int _arg69 = data.readInt();
                    int _arg78 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg82 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg82 = null;
                    }
                    int _arg96 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg102 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg102 = null;
                    }
                    byte[] _arg1111 = data.createByteArray();
                    onJniGattServerCharacteristicWriteRequest(_arg057, _arg148, _arg233, _arg319, _arg412, _arg510, _arg69, _arg78, _arg82, _arg96, _arg102, _arg1111);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniGattServerDescriptorWriteRequest /* 58 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg058 = data.readString();
                    int _arg149 = data.readInt();
                    int _arg234 = data.readInt();
                    int _arg320 = data.readInt();
                    boolean _arg413 = data.readInt() != 0;
                    boolean _arg511 = data.readInt() != 0;
                    int _arg610 = data.readInt();
                    int _arg79 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg8 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg8 = null;
                    }
                    int _arg97 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg10 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg10 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg11 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg11 = null;
                    }
                    byte[] _arg1210 = data.createByteArray();
                    onJniGattServerDescriptorWriteRequest(_arg058, _arg149, _arg234, _arg320, _arg413, _arg511, _arg610, _arg79, _arg8, _arg97, _arg10, _arg11, _arg1210);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniGattServerExecuteWrite /* 59 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg059 = data.readString();
                    int _arg150 = data.readInt();
                    boolean _arg235 = data.readInt() != 0;
                    onJniGattServerExecuteWrite(_arg059, _arg150, _arg235);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onJniGattListen /* 60 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg060 = data.readInt();
                    onJniGattListen(_arg060);
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
        public static class Proxy implements INfCallbackJni {
            public static INfCallbackJni sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniA2dpStateChanged(int state, byte[] address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeByteArray(address);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniA2dpStateChanged(state, address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniAvrcpStateChanged(int state, byte[] address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeByteArray(address);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniAvrcpStateChanged(state, address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniScoStateChanged(int index, int state, byte[] address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(index);
                    _data.writeInt(state);
                    _data.writeByteArray(address);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniScoStateChanged(index, state, address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniAvrcpRegisterEventWatcherSuccess(byte eventId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(eventId);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniAvrcpRegisterEventWatcherSuccess(eventId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged(byte eventId, byte playbackState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(eventId);
                    _data.writeByte(playbackState);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged(eventId, playbackState);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniAvrcpRegisterEventCallbackPlaybackStatusChanged(byte playbackState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(playbackState);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniAvrcpRegisterEventCallbackPlaybackStatusChanged(playbackState);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniAvrcpRegisterEventCallbackTrackChanged(byte[] uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(uid);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniAvrcpRegisterEventCallbackTrackChanged(uid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniAvrcpRegisterEventCallbackPlaybackPosChanged(int songPos) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(songPos);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniAvrcpRegisterEventCallbackPlaybackPosChanged(songPos);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniAvrcpRegisterEventCallbackSettingChanged(int count, byte[] attr, byte[] value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(count);
                    _data.writeByteArray(attr);
                    _data.writeByteArray(value);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniAvrcpRegisterEventCallbackSettingChanged(count, attr, value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniAvrcpGetElementAttributes(int[] metadataAtrributeIds, String[] texts) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(metadataAtrributeIds);
                    _data.writeStringArray(texts);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniAvrcpGetElementAttributes(metadataAtrributeIds, texts);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniAvrcpRetCapabilitiesSupportEvent(int count, byte[] events) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(count);
                    _data.writeByteArray(events);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniAvrcpRetCapabilitiesSupportEvent(count, events);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp13PlayerSettingAttributesList(byte[] attributeIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(attributeIds);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp13PlayerSettingAttributesList(attributeIds);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp13PlayerSettingValuesList(byte attributeId, byte[] valueIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(attributeId);
                    _data.writeByteArray(valueIds);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp13PlayerSettingValuesList(attributeId, valueIds);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp13PlayerSettingCurrentValues(byte[] attributeIds, byte[] valueIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(attributeIds);
                    _data.writeByteArray(valueIds);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp13PlayerSettingCurrentValues(attributeIds, valueIds);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp13SetPlayerSettingValueSuccess(int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp13SetPlayerSettingValueSuccess(reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp13PlayStatus(long songLen, long songPos, byte statusId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(songLen);
                    _data.writeLong(songPos);
                    _data.writeByte(statusId);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp13PlayStatus(songLen, songPos, statusId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcpQueryVersion(int version) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(version);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcpQueryVersion(version);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onAclStateChanged(int status, byte[] address, int state, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    _data.writeByteArray(address);
                    _data.writeInt(state);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onAclStateChanged, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAclStateChanged(status, address, state, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniHidStateChanged(int state, byte[] address, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeByteArray(address);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniHidStateChanged, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniHidStateChanged(state, address, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniAvrcpBrowseStateChanged(int state, byte[] address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeByteArray(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniAvrcpBrowseStateChanged, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniAvrcpBrowseStateChanged(state, address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp14FolderItem(byte status, byte pdu, byte[] uidCounter, int count, byte[] itemType, long[] folder, byte[] folderType, byte[] isPlayable, char[] charset, String[] name, boolean isFinal) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(status);
                    _data.writeByte(pdu);
                    _data.writeByteArray(uidCounter);
                    _data.writeInt(count);
                    _data.writeByteArray(itemType);
                    _data.writeLongArray(folder);
                    _data.writeByteArray(folderType);
                    _data.writeByteArray(isPlayable);
                    _data.writeCharArray(charset);
                    _data.writeStringArray(name);
                    _data.writeInt(isFinal ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_retJniAvrcp14FolderItem, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp14FolderItem(status, pdu, uidCounter, count, itemType, folder, folderType, isPlayable, charset, name, isFinal);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp14MediaItem(byte status, byte pdu, byte[] uidCounter, int count, byte[] itemType, long[] folder, byte[] folderType, char[] charset, String[] name, int attr_count, int[] attr_ids, String[] attr_values, boolean isFinal) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(status);
                    _data.writeByte(pdu);
                    _data.writeByteArray(uidCounter);
                    _data.writeInt(count);
                    _data.writeByteArray(itemType);
                    _data.writeLongArray(folder);
                    _data.writeByteArray(folderType);
                    _data.writeCharArray(charset);
                    _data.writeStringArray(name);
                    _data.writeInt(attr_count);
                    _data.writeIntArray(attr_ids);
                    _data.writeStringArray(attr_values);
                    _data.writeInt(isFinal ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_retJniAvrcp14MediaItem, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp14MediaItem(status, pdu, uidCounter, count, itemType, folder, folderType, charset, name, attr_count, attr_ids, attr_values, isFinal);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp14MediaItemElementAttribute(byte[] uidCounter, long uid, int attribute_id, int charset, String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(uidCounter);
                    _data.writeLong(uid);
                    _data.writeInt(attribute_id);
                    _data.writeInt(charset);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_retJniAvrcp14MediaItemElementAttribute, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp14MediaItemElementAttribute(uidCounter, uid, attribute_id, charset, name);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp14MediaPlayerItem(byte status, byte pdu, byte[] idCounter, int count, byte[] itemType, char[] playerId, byte[] majorType, int[] subType, byte[] playStatus, char[] charset, String[] name, boolean isFinal) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(status);
                    _data.writeByte(pdu);
                    _data.writeByteArray(idCounter);
                    _data.writeInt(count);
                    _data.writeByteArray(itemType);
                    _data.writeCharArray(playerId);
                    _data.writeByteArray(majorType);
                    _data.writeIntArray(subType);
                    _data.writeByteArray(playStatus);
                    _data.writeCharArray(charset);
                    _data.writeStringArray(name);
                    _data.writeInt(isFinal ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_retJniAvrcp14MediaPlayerItem, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp14MediaPlayerItem(status, pdu, idCounter, count, itemType, playerId, majorType, subType, playStatus, charset, name, isFinal);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp14ChangePath(byte status, int itemNum) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(status);
                    _data.writeInt(itemNum);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp14ChangePath(status, itemNum);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp14SetBrowsedPlayer(byte status, byte pdu, byte[] uidCounter, int count, char charset, byte depth, String[] name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(status);
                    _data.writeByte(pdu);
                    _data.writeByteArray(uidCounter);
                    _data.writeInt(count);
                    _data.writeInt(charset);
                    _data.writeByte(depth);
                    _data.writeStringArray(name);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_retJniAvrcp14SetBrowsedPlayer, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp14SetBrowsedPlayer(status, pdu, uidCounter, count, charset, depth, name);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp14PlayItem(byte status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(status);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_retJniAvrcp14PlayItem, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp14PlayItem(status);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp14AddToNowPlaying(byte status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(status);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_retJniAvrcp14AddToNowPlaying, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp14AddToNowPlaying(status);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp14GetItemAttributes(byte status, byte pdu, int count, byte[] attrId, char[] charset, String[] attrValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(status);
                    _data.writeByte(pdu);
                    _data.writeInt(count);
                    _data.writeByteArray(attrId);
                    _data.writeCharArray(charset);
                    _data.writeStringArray(attrValue);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_retJniAvrcp14GetItemAttributes, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp14GetItemAttributes(status, pdu, count, attrId, charset, attrValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void retJniAvrcp14SearchString(byte status, int num) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByte(status);
                    _data.writeInt(num);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_retJniAvrcp14SearchString, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniAvrcp14SearchString(status, num);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniMapConnectionStateChanged(String address, int state, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(state);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniMapConnectionStateChanged, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniMapConnectionStateChanged(address, state, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniMapReceiveMessageContent(String address, String handle, String senderName, String senderAddr, String date, String recipientAddr, int type, int folder, String subject, String message, int readStatus, int currentCount, int totalCount) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeString(senderName);
                    _data.writeString(senderAddr);
                    _data.writeString(date);
                    _data.writeString(recipientAddr);
                    _data.writeInt(type);
                    _data.writeInt(folder);
                    _data.writeString(subject);
                    _data.writeString(message);
                    _data.writeInt(readStatus);
                    _data.writeInt(currentCount);
                    _data.writeInt(totalCount);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniMapReceiveMessageContent(address, handle, senderName, senderAddr, date, recipientAddr, type, folder, subject, message, readStatus, currentCount, totalCount);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniReceiveNewMessage(String address, String handle, int type, int folder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(type);
                    _data.writeInt(folder);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniReceiveNewMessage, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniReceiveNewMessage(address, handle, type, folder);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniMapSendMessageState(String address, String target, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(target);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniMapSendMessageState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniMapSendMessageState(address, target, state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniMapDeleteMessageState(String address, String handle, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniMapDeleteMessageState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniMapDeleteMessageState(address, handle, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniMapChangeReadStatusState(String address, String handle, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniMapChangeReadStatusState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniMapChangeReadStatusState(address, handle, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniMapMemoryAvailable(String address, int type, int structure, int available) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(type);
                    _data.writeInt(structure);
                    _data.writeInt(available);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniMapMemoryAvailable, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniMapMemoryAvailable(address, type, structure, available);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniMapMessageSendingStatus(String address, String handle, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniMapMessageSendingStatus, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniMapMessageSendingStatus(address, handle, status);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniMapMessageDeliverStatus(String address, String handle, int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniMapMessageDeliverStatus, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniMapMessageDeliverStatus(address, handle, status);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniMapMessageShifted(String address, String handle, int type, int newFolder, int oldFolder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(type);
                    _data.writeInt(newFolder);
                    _data.writeInt(oldFolder);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniMapMessageShifted, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniMapMessageShifted(address, handle, type, newFolder, oldFolder);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniMapMessageDeleted(String address, String handle, int type, int folder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(type);
                    _data.writeInt(folder);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniMapMessageDeleted, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniMapMessageDeleted(address, handle, type, folder);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniHfpConnectionStateChanged(String address, int prevState, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(prevState);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniHfpConnectionStateChanged, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniHfpConnectionStateChanged(address, prevState, state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniHfpAudioStateChanged(String address, int prevState, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(prevState);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniHfpAudioStateChanged, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniHfpAudioStateChanged(address, prevState, state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniHfpAgEvent(String address, int network, int roaming, int signal, int battery, String operator, int voice, int inBandRingtone, String subscriber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(network);
                    _data.writeInt(roaming);
                    _data.writeInt(signal);
                    _data.writeInt(battery);
                    _data.writeString(operator);
                    _data.writeInt(voice);
                    _data.writeInt(inBandRingtone);
                    _data.writeString(subscriber);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniHfpAgEvent, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniHfpAgEvent(address, network, roaming, signal, battery, operator, voice, inBandRingtone, subscriber);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniHfpCallChanged(NfHfpClientCall hcc) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (hcc != null) {
                        _data.writeInt(1);
                        hcc.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniHfpCallChanged, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniHfpCallChanged(hcc);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniOppStateChanged(String address, int preState, int currentState, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(preState);
                    _data.writeInt(currentState);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniOppStateChanged, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniOppStateChanged(address, preState, currentState, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniOppReceiveFileInfo(String fileName, int fileSize, String devName, String savePath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(fileName);
                    _data.writeInt(fileSize);
                    _data.writeString(devName);
                    _data.writeString(savePath);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniOppReceiveFileInfo, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniOppReceiveFileInfo(fileName, fileSize, devName, savePath);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniOppReceiveProgress(int receivedOffset) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(receivedOffset);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniOppReceiveProgress, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniOppReceiveProgress(receivedOffset);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniRecreateBondDevice(String address, int is_connect) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(is_connect);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniRecreateBondDevice, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniRecreateBondDevice(address, is_connect);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniBtRoleModeChanged(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniBtRoleModeChanged, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniBtRoleModeChanged(mode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniGattServerServerRegistered(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniGattServerServerRegistered, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniGattServerServerRegistered(status);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniGattServerScanResult(String address, int rssi, byte[] advData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(rssi);
                    _data.writeByteArray(advData);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniGattServerScanResult, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniGattServerScanResult(address, rssi, advData);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniGattServerConnectionState(String address, int status, boolean connected) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(status);
                    _data.writeInt(connected ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniGattServerConnectionState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniGattServerConnectionState(address, status, connected);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniGattServerServiceAdded(int status, int srvcType, int srvcInstId, ParcelUuid srvcId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    _data.writeInt(srvcType);
                    _data.writeInt(srvcInstId);
                    if (srvcId != null) {
                        _data.writeInt(1);
                        srvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniGattServerServiceAdded, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniGattServerServiceAdded(status, srvcType, srvcInstId, srvcId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniGattServerCharacteristicReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(transId);
                    _data.writeInt(offset);
                    _data.writeInt(isLong ? 1 : 0);
                    _data.writeInt(srvcType);
                    _data.writeInt(srvcInstId);
                    if (srvcId != null) {
                        _data.writeInt(1);
                        srvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(charInstId);
                    if (charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniGattServerCharacteristicReadRequest, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniGattServerCharacteristicReadRequest(address, transId, offset, isLong, srvcType, srvcInstId, srvcId, charInstId, charId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniGattServerDescriptorReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(transId);
                    _data.writeInt(offset);
                    _data.writeInt(isLong ? 1 : 0);
                    _data.writeInt(srvcType);
                    _data.writeInt(srvcInstId);
                    if (srvcId != null) {
                        _data.writeInt(1);
                        srvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(charInstId);
                    if (charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (descrId != null) {
                        _data.writeInt(1);
                        descrId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniGattServerDescriptorReadRequest, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniGattServerDescriptorReadRequest(address, transId, offset, isLong, srvcType, srvcInstId, srvcId, charInstId, charId, descrId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniGattServerCharacteristicWriteRequest(String address, int transId, int offset, int length, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, byte[] value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(transId);
                    _data.writeInt(offset);
                    _data.writeInt(length);
                    _data.writeInt(isPrep ? 1 : 0);
                    _data.writeInt(needRsp ? 1 : 0);
                    _data.writeInt(srvcType);
                    _data.writeInt(srvcInstId);
                    if (srvcId != null) {
                        _data.writeInt(1);
                        srvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(charInstId);
                    if (charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByteArray(value);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniGattServerCharacteristicWriteRequest, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniGattServerCharacteristicWriteRequest(address, transId, offset, length, isPrep, needRsp, srvcType, srvcInstId, srvcId, charInstId, charId, value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniGattServerDescriptorWriteRequest(String address, int transId, int offset, int length, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId, byte[] value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(transId);
                    _data.writeInt(offset);
                    _data.writeInt(length);
                    _data.writeInt(isPrep ? 1 : 0);
                    _data.writeInt(needRsp ? 1 : 0);
                    _data.writeInt(srvcType);
                    _data.writeInt(srvcInstId);
                    if (srvcId != null) {
                        _data.writeInt(1);
                        srvcId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(charInstId);
                    if (charId != null) {
                        _data.writeInt(1);
                        charId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (descrId != null) {
                        _data.writeInt(1);
                        descrId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByteArray(value);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniGattServerDescriptorWriteRequest, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniGattServerDescriptorWriteRequest(address, transId, offset, length, isPrep, needRsp, srvcType, srvcInstId, srvcId, charInstId, charId, descrId, value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniGattServerExecuteWrite(String address, int transId, boolean execWrite) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(transId);
                    _data.writeInt(execWrite ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniGattServerExecuteWrite, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniGattServerExecuteWrite(address, transId, execWrite);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJni
            public void onJniGattListen(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_onJniGattListen, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniGattListen(status);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INfCallbackJni impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCallbackJni getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
