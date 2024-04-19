package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackBluetooth;
/* loaded from: classes.dex */
public interface INfCommandBluetooth extends IInterface {
    boolean cancelBtDiscovery() throws RemoteException;

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

    String getNfServiceVersionName() throws RemoteException;

    boolean isBluetoothServiceReady() throws RemoteException;

    boolean isBtAutoConnectEnable() throws RemoteException;

    boolean isBtDiscoverable() throws RemoteException;

    boolean isBtDiscovering() throws RemoteException;

    boolean isBtEnabled() throws RemoteException;

    boolean isDeviceAclDisconnected(String str) throws RemoteException;

    boolean registerBtCallback(INfCallbackBluetooth iNfCallbackBluetooth) throws RemoteException;

    int reqBtConnectHfpA2dp(String str) throws RemoteException;

    int reqBtDisconnectAll() throws RemoteException;

    boolean reqBtPair(String str) throws RemoteException;

    boolean reqBtPairedDevices() throws RemoteException;

    boolean reqBtUnpair(String str) throws RemoteException;

    void setBtAutoConnect(int i, int i2) throws RemoteException;

    boolean setBtDiscoverableTimeout(int i) throws RemoteException;

    boolean setBtEnable(boolean z) throws RemoteException;

    boolean setBtLocalName(String str) throws RemoteException;

    boolean startBtDiscovery() throws RemoteException;

    boolean switchBtRoleMode(int i) throws RemoteException;

    boolean unregisterBtCallback(INfCallbackBluetooth iNfCallbackBluetooth) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCommandBluetooth {
        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isBluetoothServiceReady() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean registerBtCallback(INfCallbackBluetooth cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean unregisterBtCallback(INfCallbackBluetooth cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public String getNfServiceVersionName() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean setBtEnable(boolean enable) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean setBtDiscoverableTimeout(int timeout) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean startBtDiscovery() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean cancelBtDiscovery() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean reqBtPair(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean reqBtUnpair(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean reqBtPairedDevices() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public String getBtLocalName() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public String getBtRemoteDeviceName(String address) throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public String getBtLocalAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean setBtLocalName(String name) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isBtEnabled() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isBtDiscovering() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isBtDiscoverable() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isBtAutoConnectEnable() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int reqBtConnectHfpA2dp(String address) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int reqBtDisconnectAll() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtRemoteUuids(String address) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isDeviceAclDisconnected(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean switchBtRoleMode(int mode) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtRoleMode() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public void setBtAutoConnect(int condition, int period) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtAutoConnectCondition() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtAutoConnectPeriod() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtAutoConnectState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public String getBtAutoConnectingAddress() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCommandBluetooth {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCommandBluetooth";
        static final int TRANSACTION_cancelBtDiscovery = 8;
        static final int TRANSACTION_getBtAutoConnectCondition = 28;
        static final int TRANSACTION_getBtAutoConnectPeriod = 29;
        static final int TRANSACTION_getBtAutoConnectState = 30;
        static final int TRANSACTION_getBtAutoConnectingAddress = 31;
        static final int TRANSACTION_getBtLocalAddress = 14;
        static final int TRANSACTION_getBtLocalName = 12;
        static final int TRANSACTION_getBtRemoteDeviceName = 13;
        static final int TRANSACTION_getBtRemoteUuids = 23;
        static final int TRANSACTION_getBtRoleMode = 26;
        static final int TRANSACTION_getBtState = 17;
        static final int TRANSACTION_getNfServiceVersionName = 4;
        static final int TRANSACTION_isBluetoothServiceReady = 1;
        static final int TRANSACTION_isBtAutoConnectEnable = 20;
        static final int TRANSACTION_isBtDiscoverable = 19;
        static final int TRANSACTION_isBtDiscovering = 18;
        static final int TRANSACTION_isBtEnabled = 16;
        static final int TRANSACTION_isDeviceAclDisconnected = 24;
        static final int TRANSACTION_registerBtCallback = 2;
        static final int TRANSACTION_reqBtConnectHfpA2dp = 21;
        static final int TRANSACTION_reqBtDisconnectAll = 22;
        static final int TRANSACTION_reqBtPair = 9;
        static final int TRANSACTION_reqBtPairedDevices = 11;
        static final int TRANSACTION_reqBtUnpair = 10;
        static final int TRANSACTION_setBtAutoConnect = 27;
        static final int TRANSACTION_setBtDiscoverableTimeout = 6;
        static final int TRANSACTION_setBtEnable = 5;
        static final int TRANSACTION_setBtLocalName = 15;
        static final int TRANSACTION_startBtDiscovery = 7;
        static final int TRANSACTION_switchBtRoleMode = 25;
        static final int TRANSACTION_unregisterBtCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCommandBluetooth asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCommandBluetooth)) {
                return (INfCommandBluetooth) iin;
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
                    boolean _result = isBluetoothServiceReady();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackBluetooth _arg0 = INfCallbackBluetooth.Stub.asInterface(data.readStrongBinder());
                    boolean _result2 = registerBtCallback(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result2 ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackBluetooth _arg02 = INfCallbackBluetooth.Stub.asInterface(data.readStrongBinder());
                    boolean _result3 = unregisterBtCallback(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result3 ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String _result4 = getNfServiceVersionName();
                    reply.writeNoException();
                    reply.writeString(_result4);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg03 = data.readInt() != 0;
                    boolean _result5 = setBtEnable(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result5 ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg04 = data.readInt();
                    boolean _result6 = setBtDiscoverableTimeout(_arg04);
                    reply.writeNoException();
                    reply.writeInt(_result6 ? 1 : 0);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result7 = startBtDiscovery();
                    reply.writeNoException();
                    reply.writeInt(_result7 ? 1 : 0);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result8 = cancelBtDiscovery();
                    reply.writeNoException();
                    reply.writeInt(_result8 ? 1 : 0);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    boolean _result9 = reqBtPair(_arg05);
                    reply.writeNoException();
                    reply.writeInt(_result9 ? 1 : 0);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    boolean _result10 = reqBtUnpair(_arg06);
                    reply.writeNoException();
                    reply.writeInt(_result10 ? 1 : 0);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result11 = reqBtPairedDevices();
                    reply.writeNoException();
                    reply.writeInt(_result11 ? 1 : 0);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String _result12 = getBtLocalName();
                    reply.writeNoException();
                    reply.writeString(_result12);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    String _result13 = getBtRemoteDeviceName(_arg07);
                    reply.writeNoException();
                    reply.writeString(_result13);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    String _result14 = getBtLocalAddress();
                    reply.writeNoException();
                    reply.writeString(_result14);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    boolean _result15 = setBtLocalName(_arg08);
                    reply.writeNoException();
                    reply.writeInt(_result15 ? 1 : 0);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result16 = isBtEnabled();
                    reply.writeNoException();
                    reply.writeInt(_result16 ? 1 : 0);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    int _result17 = getBtState();
                    reply.writeNoException();
                    reply.writeInt(_result17);
                    return true;
                case TRANSACTION_isBtDiscovering /* 18 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result18 = isBtDiscovering();
                    reply.writeNoException();
                    reply.writeInt(_result18 ? 1 : 0);
                    return true;
                case TRANSACTION_isBtDiscoverable /* 19 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result19 = isBtDiscoverable();
                    reply.writeNoException();
                    reply.writeInt(_result19 ? 1 : 0);
                    return true;
                case TRANSACTION_isBtAutoConnectEnable /* 20 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result20 = isBtAutoConnectEnable();
                    reply.writeNoException();
                    reply.writeInt(_result20 ? 1 : 0);
                    return true;
                case TRANSACTION_reqBtConnectHfpA2dp /* 21 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg09 = data.readString();
                    int _result21 = reqBtConnectHfpA2dp(_arg09);
                    reply.writeNoException();
                    reply.writeInt(_result21);
                    return true;
                case TRANSACTION_reqBtDisconnectAll /* 22 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result22 = reqBtDisconnectAll();
                    reply.writeNoException();
                    reply.writeInt(_result22);
                    return true;
                case TRANSACTION_getBtRemoteUuids /* 23 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg010 = data.readString();
                    int _result23 = getBtRemoteUuids(_arg010);
                    reply.writeNoException();
                    reply.writeInt(_result23);
                    return true;
                case TRANSACTION_isDeviceAclDisconnected /* 24 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg011 = data.readString();
                    boolean _result24 = isDeviceAclDisconnected(_arg011);
                    reply.writeNoException();
                    reply.writeInt(_result24 ? 1 : 0);
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg012 = data.readInt();
                    boolean _result25 = switchBtRoleMode(_arg012);
                    reply.writeNoException();
                    reply.writeInt(_result25 ? 1 : 0);
                    return true;
                case TRANSACTION_getBtRoleMode /* 26 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result26 = getBtRoleMode();
                    reply.writeNoException();
                    reply.writeInt(_result26);
                    return true;
                case TRANSACTION_setBtAutoConnect /* 27 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg013 = data.readInt();
                    int _arg1 = data.readInt();
                    setBtAutoConnect(_arg013, _arg1);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getBtAutoConnectCondition /* 28 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result27 = getBtAutoConnectCondition();
                    reply.writeNoException();
                    reply.writeInt(_result27);
                    return true;
                case TRANSACTION_getBtAutoConnectPeriod /* 29 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result28 = getBtAutoConnectPeriod();
                    reply.writeNoException();
                    reply.writeInt(_result28);
                    return true;
                case TRANSACTION_getBtAutoConnectState /* 30 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _result29 = getBtAutoConnectState();
                    reply.writeNoException();
                    reply.writeInt(_result29);
                    return true;
                case TRANSACTION_getBtAutoConnectingAddress /* 31 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _result30 = getBtAutoConnectingAddress();
                    reply.writeNoException();
                    reply.writeString(_result30);
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
        public static class Proxy implements INfCommandBluetooth {
            public static INfCommandBluetooth sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean isBluetoothServiceReady() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isBluetoothServiceReady();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean registerBtCallback(INfCallbackBluetooth cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean unregisterBtCallback(INfCallbackBluetooth cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public String getNfServiceVersionName() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean setBtEnable(boolean enable) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean setBtDiscoverableTimeout(int timeout) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(timeout);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean startBtDiscovery() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean cancelBtDiscovery() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean reqBtPair(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean reqBtUnpair(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean reqBtPairedDevices() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public String getBtLocalName() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public String getBtRemoteDeviceName(String address) throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public String getBtLocalAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean setBtLocalName(String name) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean isBtEnabled() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public int getBtState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean isBtDiscovering() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isBtDiscovering, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean isBtDiscoverable() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isBtDiscoverable, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean isBtAutoConnectEnable() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isBtAutoConnectEnable, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public int reqBtDisconnectAll() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqBtDisconnectAll, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public int getBtRemoteUuids(String address) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBtRemoteUuids, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean isDeviceAclDisconnected(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isDeviceAclDisconnected, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public boolean switchBtRoleMode(int mode) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
            public void setBtAutoConnect(int condition, int period) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(condition);
                    _data.writeInt(period);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setBtAutoConnect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBtAutoConnect(condition, period);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
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

            @Override // com.nforetek.bt.aidl.INfCommandBluetooth
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
        }

        public static boolean setDefaultImpl(INfCommandBluetooth impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCommandBluetooth getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
