package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackAvrcp;
/* loaded from: classes.dex */
public interface INfCommandAvrcp extends IInterface {
    String getAvrcpConnectedAddress() throws RemoteException;

    int getAvrcpConnectionState() throws RemoteException;

    boolean isAvrcp13Supported(String str) throws RemoteException;

    boolean isAvrcp14BrowsingChannelEstablished() throws RemoteException;

    boolean isAvrcp14Supported(String str) throws RemoteException;

    boolean isAvrcpConnected() throws RemoteException;

    boolean isAvrcpServiceReady() throws RemoteException;

    boolean registerAvrcpCallback(INfCallbackAvrcp iNfCallbackAvrcp) throws RemoteException;

    boolean reqAvrcp13GetCapabilitiesSupportEvent() throws RemoteException;

    boolean reqAvrcp13GetElementAttributesPlaying() throws RemoteException;

    boolean reqAvrcp13GetPlayStatus() throws RemoteException;

    boolean reqAvrcp13GetPlayerSettingAttributesList() throws RemoteException;

    boolean reqAvrcp13GetPlayerSettingCurrentValues() throws RemoteException;

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

    boolean reqAvrcpQueryVersion(String str) throws RemoteException;

    boolean reqAvrcpRegisterEventWatcher(byte b, long j) throws RemoteException;

    boolean reqAvrcpStartFastForward() throws RemoteException;

    boolean reqAvrcpStartRewind() throws RemoteException;

    boolean reqAvrcpStop() throws RemoteException;

    boolean reqAvrcpStopFastForward() throws RemoteException;

    boolean reqAvrcpStopRewind() throws RemoteException;

    boolean reqAvrcpUnregisterEventWatcher(byte b) throws RemoteException;

    boolean reqAvrcpVolumeDown() throws RemoteException;

    boolean reqAvrcpVolumeUp() throws RemoteException;

    boolean unregisterAvrcpCallback(INfCallbackAvrcp iNfCallbackAvrcp) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCommandAvrcp {
        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean isAvrcpServiceReady() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean registerAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean unregisterAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public int getAvrcpConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean isAvrcpConnected() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public String getAvrcpConnectedAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean isAvrcp13Supported(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean isAvrcp14Supported(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpPlay() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpStop() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpPause() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpForward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpBackward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpVolumeUp() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpVolumeDown() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpStartFastForward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpStopFastForward() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpStartRewind() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpStopRewind() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetCapabilitiesSupportEvent() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetPlayerSettingAttributesList() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetPlayerSettingValuesList(byte attributeId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetPlayerSettingCurrentValues() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13SetPlayerSettingValue(byte attributeId, byte valueId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetElementAttributesPlaying() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13GetPlayStatus() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpRegisterEventWatcher(byte eventId, long interval) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpUnregisterEventWatcher(byte eventId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13NextGroup() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp13PreviousGroup() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean isAvrcp14BrowsingChannelEstablished() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14SetAddressedPlayer(int playerId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14SetBrowsedPlayer(int playerId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14GetFolderItems(byte scopeId) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14ChangePath(int uidCounter, long uid, byte direction) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14GetItemAttributes(byte scopeId, int uidCounter, long uid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14PlaySelectedItem(byte scopeId, int uidCounter, long uid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14Search(String text) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14AddToNowPlaying(byte scopeId, int uidCounter, long uid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcp14SetAbsoluteVolume(byte volume) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandAvrcp
        public boolean reqAvrcpQueryVersion(String address) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCommandAvrcp {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCommandAvrcp";
        static final int TRANSACTION_getAvrcpConnectedAddress = 6;
        static final int TRANSACTION_getAvrcpConnectionState = 4;
        static final int TRANSACTION_isAvrcp13Supported = 9;
        static final int TRANSACTION_isAvrcp14BrowsingChannelEstablished = 33;
        static final int TRANSACTION_isAvrcp14Supported = 10;
        static final int TRANSACTION_isAvrcpConnected = 5;
        static final int TRANSACTION_isAvrcpServiceReady = 1;
        static final int TRANSACTION_registerAvrcpCallback = 2;
        static final int TRANSACTION_reqAvrcp13GetCapabilitiesSupportEvent = 22;
        static final int TRANSACTION_reqAvrcp13GetElementAttributesPlaying = 27;
        static final int TRANSACTION_reqAvrcp13GetPlayStatus = 28;
        static final int TRANSACTION_reqAvrcp13GetPlayerSettingAttributesList = 23;
        static final int TRANSACTION_reqAvrcp13GetPlayerSettingCurrentValues = 25;
        static final int TRANSACTION_reqAvrcp13GetPlayerSettingValuesList = 24;
        static final int TRANSACTION_reqAvrcp13NextGroup = 31;
        static final int TRANSACTION_reqAvrcp13PreviousGroup = 32;
        static final int TRANSACTION_reqAvrcp13SetPlayerSettingValue = 26;
        static final int TRANSACTION_reqAvrcp14AddToNowPlaying = 41;
        static final int TRANSACTION_reqAvrcp14ChangePath = 37;
        static final int TRANSACTION_reqAvrcp14GetFolderItems = 36;
        static final int TRANSACTION_reqAvrcp14GetItemAttributes = 38;
        static final int TRANSACTION_reqAvrcp14PlaySelectedItem = 39;
        static final int TRANSACTION_reqAvrcp14Search = 40;
        static final int TRANSACTION_reqAvrcp14SetAbsoluteVolume = 42;
        static final int TRANSACTION_reqAvrcp14SetAddressedPlayer = 34;
        static final int TRANSACTION_reqAvrcp14SetBrowsedPlayer = 35;
        static final int TRANSACTION_reqAvrcpBackward = 15;
        static final int TRANSACTION_reqAvrcpConnect = 7;
        static final int TRANSACTION_reqAvrcpDisconnect = 8;
        static final int TRANSACTION_reqAvrcpForward = 14;
        static final int TRANSACTION_reqAvrcpPause = 13;
        static final int TRANSACTION_reqAvrcpPlay = 11;
        static final int TRANSACTION_reqAvrcpQueryVersion = 43;
        static final int TRANSACTION_reqAvrcpRegisterEventWatcher = 29;
        static final int TRANSACTION_reqAvrcpStartFastForward = 18;
        static final int TRANSACTION_reqAvrcpStartRewind = 20;
        static final int TRANSACTION_reqAvrcpStop = 12;
        static final int TRANSACTION_reqAvrcpStopFastForward = 19;
        static final int TRANSACTION_reqAvrcpStopRewind = 21;
        static final int TRANSACTION_reqAvrcpUnregisterEventWatcher = 30;
        static final int TRANSACTION_reqAvrcpVolumeDown = 17;
        static final int TRANSACTION_reqAvrcpVolumeUp = 16;
        static final int TRANSACTION_unregisterAvrcpCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCommandAvrcp asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCommandAvrcp)) {
                return (INfCommandAvrcp) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = isAvrcpServiceReady();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackAvrcp _arg0 = INfCallbackAvrcp.Stub.asInterface(data.readStrongBinder());
                    boolean _result2 = registerAvrcpCallback(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result2 ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackAvrcp _arg02 = INfCallbackAvrcp.Stub.asInterface(data.readStrongBinder());
                    boolean _result3 = unregisterAvrcpCallback(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result3 ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = getAvrcpConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result5 = isAvrcpConnected();
                    reply.writeNoException();
                    reply.writeInt(_result5 ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String _result6 = getAvrcpConnectedAddress();
                    reply.writeNoException();
                    reply.writeString(_result6);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    boolean _result7 = reqAvrcpConnect(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result7 ? 1 : 0);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    boolean _result8 = reqAvrcpDisconnect(_arg04);
                    reply.writeNoException();
                    reply.writeInt(_result8 ? 1 : 0);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    boolean _result9 = isAvrcp13Supported(_arg05);
                    reply.writeNoException();
                    reply.writeInt(_result9 ? 1 : 0);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    boolean _result10 = isAvrcp14Supported(_arg06);
                    reply.writeNoException();
                    reply.writeInt(_result10 ? 1 : 0);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result11 = reqAvrcpPlay();
                    reply.writeNoException();
                    reply.writeInt(_result11 ? 1 : 0);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result12 = reqAvrcpStop();
                    reply.writeNoException();
                    reply.writeInt(_result12 ? 1 : 0);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result13 = reqAvrcpPause();
                    reply.writeNoException();
                    reply.writeInt(_result13 ? 1 : 0);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result14 = reqAvrcpForward();
                    reply.writeNoException();
                    reply.writeInt(_result14 ? 1 : 0);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result15 = reqAvrcpBackward();
                    reply.writeNoException();
                    reply.writeInt(_result15 ? 1 : 0);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result16 = reqAvrcpVolumeUp();
                    reply.writeNoException();
                    reply.writeInt(_result16 ? 1 : 0);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result17 = reqAvrcpVolumeDown();
                    reply.writeNoException();
                    reply.writeInt(_result17 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpStartFastForward /* 18 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result18 = reqAvrcpStartFastForward();
                    reply.writeNoException();
                    reply.writeInt(_result18 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpStopFastForward /* 19 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result19 = reqAvrcpStopFastForward();
                    reply.writeNoException();
                    reply.writeInt(_result19 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpStartRewind /* 20 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result20 = reqAvrcpStartRewind();
                    reply.writeNoException();
                    reply.writeInt(_result20 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpStopRewind /* 21 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result21 = reqAvrcpStopRewind();
                    reply.writeNoException();
                    reply.writeInt(_result21 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetCapabilitiesSupportEvent /* 22 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result22 = reqAvrcp13GetCapabilitiesSupportEvent();
                    reply.writeNoException();
                    reply.writeInt(_result22 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetPlayerSettingAttributesList /* 23 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result23 = reqAvrcp13GetPlayerSettingAttributesList();
                    reply.writeNoException();
                    reply.writeInt(_result23 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetPlayerSettingValuesList /* 24 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg07 = data.readByte();
                    boolean _result24 = reqAvrcp13GetPlayerSettingValuesList(_arg07);
                    reply.writeNoException();
                    reply.writeInt(_result24 ? 1 : 0);
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result25 = reqAvrcp13GetPlayerSettingCurrentValues();
                    reply.writeNoException();
                    reply.writeInt(_result25 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13SetPlayerSettingValue /* 26 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg08 = data.readByte();
                    byte _arg1 = data.readByte();
                    boolean _result26 = reqAvrcp13SetPlayerSettingValue(_arg08, _arg1);
                    reply.writeNoException();
                    reply.writeInt(_result26 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetElementAttributesPlaying /* 27 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result27 = reqAvrcp13GetElementAttributesPlaying();
                    reply.writeNoException();
                    reply.writeInt(_result27 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13GetPlayStatus /* 28 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result28 = reqAvrcp13GetPlayStatus();
                    reply.writeNoException();
                    reply.writeInt(_result28 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpRegisterEventWatcher /* 29 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg09 = data.readByte();
                    long _arg12 = data.readLong();
                    boolean _result29 = reqAvrcpRegisterEventWatcher(_arg09, _arg12);
                    reply.writeNoException();
                    reply.writeInt(_result29 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpUnregisterEventWatcher /* 30 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg010 = data.readByte();
                    boolean _result30 = reqAvrcpUnregisterEventWatcher(_arg010);
                    reply.writeNoException();
                    reply.writeInt(_result30 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp13NextGroup /* 31 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result31 = reqAvrcp13NextGroup();
                    reply.writeNoException();
                    reply.writeInt(_result31 ? 1 : 0);
                    return true;
                case 32:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result32 = reqAvrcp13PreviousGroup();
                    reply.writeNoException();
                    reply.writeInt(_result32 ? 1 : 0);
                    return true;
                case TRANSACTION_isAvrcp14BrowsingChannelEstablished /* 33 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result33 = isAvrcp14BrowsingChannelEstablished();
                    reply.writeNoException();
                    reply.writeInt(_result33 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14SetAddressedPlayer /* 34 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg011 = data.readInt();
                    boolean _result34 = reqAvrcp14SetAddressedPlayer(_arg011);
                    reply.writeNoException();
                    reply.writeInt(_result34 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14SetBrowsedPlayer /* 35 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg012 = data.readInt();
                    boolean _result35 = reqAvrcp14SetBrowsedPlayer(_arg012);
                    reply.writeNoException();
                    reply.writeInt(_result35 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14GetFolderItems /* 36 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg013 = data.readByte();
                    boolean _result36 = reqAvrcp14GetFolderItems(_arg013);
                    reply.writeNoException();
                    reply.writeInt(_result36 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14ChangePath /* 37 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg014 = data.readInt();
                    long _arg13 = data.readLong();
                    byte _arg2 = data.readByte();
                    boolean _result37 = reqAvrcp14ChangePath(_arg014, _arg13, _arg2);
                    reply.writeNoException();
                    reply.writeInt(_result37 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14GetItemAttributes /* 38 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg015 = data.readByte();
                    int _arg14 = data.readInt();
                    long _arg22 = data.readLong();
                    boolean _result38 = reqAvrcp14GetItemAttributes(_arg015, _arg14, _arg22);
                    reply.writeNoException();
                    reply.writeInt(_result38 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14PlaySelectedItem /* 39 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg016 = data.readByte();
                    int _arg15 = data.readInt();
                    long _arg23 = data.readLong();
                    boolean _result39 = reqAvrcp14PlaySelectedItem(_arg016, _arg15, _arg23);
                    reply.writeNoException();
                    reply.writeInt(_result39 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14Search /* 40 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg017 = data.readString();
                    boolean _result40 = reqAvrcp14Search(_arg017);
                    reply.writeNoException();
                    reply.writeInt(_result40 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14AddToNowPlaying /* 41 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg018 = data.readByte();
                    int _arg16 = data.readInt();
                    long _arg24 = data.readLong();
                    boolean _result41 = reqAvrcp14AddToNowPlaying(_arg018, _arg16, _arg24);
                    reply.writeNoException();
                    reply.writeInt(_result41 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcp14SetAbsoluteVolume /* 42 */:
                    data.enforceInterface(DESCRIPTOR);
                    byte _arg019 = data.readByte();
                    boolean _result42 = reqAvrcp14SetAbsoluteVolume(_arg019);
                    reply.writeNoException();
                    reply.writeInt(_result42 ? 1 : 0);
                    return true;
                case TRANSACTION_reqAvrcpQueryVersion /* 43 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg020 = data.readString();
                    boolean _result43 = reqAvrcpQueryVersion(_arg020);
                    reply.writeNoException();
                    reply.writeInt(_result43 ? 1 : 0);
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
        public static class Proxy implements INfCommandAvrcp {
            public static INfCommandAvrcp sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean isAvrcpServiceReady() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isAvrcpServiceReady();
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean registerAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean unregisterAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public int getAvrcpConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean isAvrcpConnected() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public String getAvrcpConnectedAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean reqAvrcpConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean reqAvrcpDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean isAvrcp13Supported(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean isAvrcp14Supported(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean reqAvrcpPlay() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean reqAvrcpStop() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean reqAvrcpPause() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean reqAvrcpForward() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean reqAvrcpBackward() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean reqAvrcpVolumeUp() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean reqAvrcpVolumeDown() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean reqAvrcp13GetPlayerSettingCurrentValues() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
            public boolean reqAvrcp13PreviousGroup() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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

            @Override // com.nforetek.bt.aidl.INfCommandAvrcp
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
        }

        public static boolean setDefaultImpl(INfCommandAvrcp impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCommandAvrcp getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
