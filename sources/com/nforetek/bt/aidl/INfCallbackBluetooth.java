package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface INfCallbackBluetooth extends IInterface {
    void onAdapterDiscoverableModeChanged(int i, int i2) throws RemoteException;

    void onAdapterDiscoveryFinished() throws RemoteException;

    void onAdapterDiscoveryStarted() throws RemoteException;

    void onAdapterStateChanged(int i, int i2) throws RemoteException;

    void onBluetoothServiceReady() throws RemoteException;

    void onBtAutoConnectStateChanged(String str, int i, int i2) throws RemoteException;

    void onBtRoleModeChanged(int i) throws RemoteException;

    void onDeviceAclDisconnected(String str) throws RemoteException;

    void onDeviceBondStateChanged(String str, String str2, int i, int i2) throws RemoteException;

    void onDeviceFound(String str, String str2, byte b) throws RemoteException;

    void onDeviceOutOfRange(String str) throws RemoteException;

    void onDeviceUuidsUpdated(String str, String str2, int i) throws RemoteException;

    void onLocalAdapterNameChanged(String str) throws RemoteException;

    void retPairedDevices(int i, String[] strArr, String[] strArr2, int[] iArr, byte[] bArr) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCallbackBluetooth {
        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onBluetoothServiceReady() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onAdapterStateChanged(int prevState, int newState) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onAdapterDiscoverableModeChanged(int prevState, int newState) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onAdapterDiscoveryStarted() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onAdapterDiscoveryFinished() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void retPairedDevices(int elements, String[] address, String[] name, int[] supportProfile, byte[] category) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onDeviceFound(String address, String name, byte category) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onDeviceBondStateChanged(String address, String name, int prevState, int newState) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onDeviceUuidsUpdated(String address, String name, int supportProfile) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onLocalAdapterNameChanged(String name) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onDeviceOutOfRange(String address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onDeviceAclDisconnected(String address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onBtRoleModeChanged(int mode) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
        public void onBtAutoConnectStateChanged(String address, int prevState, int newState) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCallbackBluetooth {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCallbackBluetooth";
        static final int TRANSACTION_onAdapterDiscoverableModeChanged = 3;
        static final int TRANSACTION_onAdapterDiscoveryFinished = 5;
        static final int TRANSACTION_onAdapterDiscoveryStarted = 4;
        static final int TRANSACTION_onAdapterStateChanged = 2;
        static final int TRANSACTION_onBluetoothServiceReady = 1;
        static final int TRANSACTION_onBtAutoConnectStateChanged = 14;
        static final int TRANSACTION_onBtRoleModeChanged = 13;
        static final int TRANSACTION_onDeviceAclDisconnected = 12;
        static final int TRANSACTION_onDeviceBondStateChanged = 8;
        static final int TRANSACTION_onDeviceFound = 7;
        static final int TRANSACTION_onDeviceOutOfRange = 11;
        static final int TRANSACTION_onDeviceUuidsUpdated = 9;
        static final int TRANSACTION_onLocalAdapterNameChanged = 10;
        static final int TRANSACTION_retPairedDevices = 6;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCallbackBluetooth asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCallbackBluetooth)) {
                return (INfCallbackBluetooth) iin;
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
                    onBluetoothServiceReady();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    int _arg1 = data.readInt();
                    onAdapterStateChanged(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg02 = data.readInt();
                    int _arg12 = data.readInt();
                    onAdapterDiscoverableModeChanged(_arg02, _arg12);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    onAdapterDiscoveryStarted();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    onAdapterDiscoveryFinished();
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    String[] _arg13 = data.createStringArray();
                    String[] _arg2 = data.createStringArray();
                    int[] _arg3 = data.createIntArray();
                    byte[] _arg4 = data.createByteArray();
                    retPairedDevices(_arg03, _arg13, _arg2, _arg3, _arg4);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    String _arg14 = data.readString();
                    byte _arg22 = data.readByte();
                    onDeviceFound(_arg04, _arg14, _arg22);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    String _arg15 = data.readString();
                    int _arg23 = data.readInt();
                    int _arg32 = data.readInt();
                    onDeviceBondStateChanged(_arg05, _arg15, _arg23, _arg32);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    String _arg16 = data.readString();
                    int _arg24 = data.readInt();
                    onDeviceUuidsUpdated(_arg06, _arg16, _arg24);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    onLocalAdapterNameChanged(_arg07);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    onDeviceOutOfRange(_arg08);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg09 = data.readString();
                    onDeviceAclDisconnected(_arg09);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg010 = data.readInt();
                    onBtRoleModeChanged(_arg010);
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg011 = data.readString();
                    int _arg17 = data.readInt();
                    int _arg25 = data.readInt();
                    onBtAutoConnectStateChanged(_arg011, _arg17, _arg25);
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
        public static class Proxy implements INfCallbackBluetooth {
            public static INfCallbackBluetooth sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onBluetoothServiceReady() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBluetoothServiceReady();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onAdapterStateChanged(int prevState, int newState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(prevState);
                    _data.writeInt(newState);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdapterStateChanged(prevState, newState);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onAdapterDiscoverableModeChanged(int prevState, int newState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(prevState);
                    _data.writeInt(newState);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdapterDiscoverableModeChanged(prevState, newState);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onAdapterDiscoveryStarted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdapterDiscoveryStarted();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onAdapterDiscoveryFinished() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdapterDiscoveryFinished();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void retPairedDevices(int elements, String[] address, String[] name, int[] supportProfile, byte[] category) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(elements);
                    _data.writeStringArray(address);
                    _data.writeStringArray(name);
                    _data.writeIntArray(supportProfile);
                    _data.writeByteArray(category);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retPairedDevices(elements, address, name, supportProfile, category);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onDeviceFound(String address, String name, byte category) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(name);
                    _data.writeByte(category);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceFound(address, name, category);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onDeviceBondStateChanged(String address, String name, int prevState, int newState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(name);
                    _data.writeInt(prevState);
                    _data.writeInt(newState);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceBondStateChanged(address, name, prevState, newState);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onDeviceUuidsUpdated(String address, String name, int supportProfile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(name);
                    _data.writeInt(supportProfile);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceUuidsUpdated(address, name, supportProfile);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onLocalAdapterNameChanged(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLocalAdapterNameChanged(name);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onDeviceOutOfRange(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceOutOfRange(address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onDeviceAclDisconnected(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceAclDisconnected(address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onBtRoleModeChanged(int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBtRoleModeChanged(mode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackBluetooth
            public void onBtAutoConnectStateChanged(String address, int prevState, int newState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(prevState);
                    _data.writeInt(newState);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onBtAutoConnectStateChanged(address, prevState, newState);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INfCallbackBluetooth impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCallbackBluetooth getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
