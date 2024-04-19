package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackHid;
/* loaded from: classes.dex */
public interface INfCommandHid extends IInterface {
    String getHidConnectedAddress() throws RemoteException;

    int getHidConnectionState() throws RemoteException;

    boolean isHidConnected() throws RemoteException;

    boolean isHidServiceReady() throws RemoteException;

    boolean registerHidCallback(INfCallbackHid iNfCallbackHid) throws RemoteException;

    boolean reqHidConnect(String str) throws RemoteException;

    boolean reqHidDisconnect(String str) throws RemoteException;

    boolean reqSendHidMouseCommand(int i, int i2, int i3, int i4) throws RemoteException;

    boolean reqSendHidVirtualKeyCommand(int i, int i2) throws RemoteException;

    boolean unregisterHidCallback(INfCallbackHid iNfCallbackHid) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCommandHid {
        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean isHidServiceReady() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean registerHidCallback(INfCallbackHid cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean unregisterHidCallback(INfCallbackHid cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean reqHidConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean reqHidDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean reqSendHidMouseCommand(int button, int offset_x, int offset_y, int wheel) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean reqSendHidVirtualKeyCommand(int key_1, int key_2) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public int getHidConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean isHidConnected() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public String getHidConnectedAddress() throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCommandHid {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCommandHid";
        static final int TRANSACTION_getHidConnectedAddress = 10;
        static final int TRANSACTION_getHidConnectionState = 8;
        static final int TRANSACTION_isHidConnected = 9;
        static final int TRANSACTION_isHidServiceReady = 1;
        static final int TRANSACTION_registerHidCallback = 2;
        static final int TRANSACTION_reqHidConnect = 4;
        static final int TRANSACTION_reqHidDisconnect = 5;
        static final int TRANSACTION_reqSendHidMouseCommand = 6;
        static final int TRANSACTION_reqSendHidVirtualKeyCommand = 7;
        static final int TRANSACTION_unregisterHidCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCommandHid asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCommandHid)) {
                return (INfCommandHid) iin;
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
                    boolean _result = isHidServiceReady();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackHid _arg0 = INfCallbackHid.Stub.asInterface(data.readStrongBinder());
                    boolean _result2 = registerHidCallback(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result2 ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackHid _arg02 = INfCallbackHid.Stub.asInterface(data.readStrongBinder());
                    boolean _result3 = unregisterHidCallback(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result3 ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    boolean _result4 = reqHidConnect(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result4 ? 1 : 0);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    boolean _result5 = reqHidDisconnect(_arg04);
                    reply.writeNoException();
                    reply.writeInt(_result5 ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    int _arg3 = data.readInt();
                    boolean _result6 = reqSendHidMouseCommand(_arg05, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    reply.writeInt(_result6 ? 1 : 0);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg06 = data.readInt();
                    int _arg12 = data.readInt();
                    boolean _result7 = reqSendHidVirtualKeyCommand(_arg06, _arg12);
                    reply.writeNoException();
                    reply.writeInt(_result7 ? 1 : 0);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _result8 = getHidConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result8);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result9 = isHidConnected();
                    reply.writeNoException();
                    reply.writeInt(_result9 ? 1 : 0);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String _result10 = getHidConnectedAddress();
                    reply.writeNoException();
                    reply.writeString(_result10);
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
        public static class Proxy implements INfCommandHid {
            public static INfCommandHid sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCommandHid
            public boolean isHidServiceReady() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHidServiceReady();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandHid
            public boolean registerHidCallback(INfCallbackHid cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandHid
            public boolean unregisterHidCallback(INfCallbackHid cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandHid
            public boolean reqHidConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandHid
            public boolean reqHidDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandHid
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
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandHid
            public boolean reqSendHidVirtualKeyCommand(int key_1, int key_2) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(key_1);
                    _data.writeInt(key_2);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandHid
            public int getHidConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandHid
            public boolean isHidConnected() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandHid
            public String getHidConnectedAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
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
        }

        public static boolean setDefaultImpl(INfCommandHid impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCommandHid getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
