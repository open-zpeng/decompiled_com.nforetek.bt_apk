package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackA2dp;
/* loaded from: classes.dex */
public interface INfCommandA2dp extends IInterface {
    String getA2dpConnectedAddress() throws RemoteException;

    int getA2dpConnectionState() throws RemoteException;

    int getA2dpStreamType() throws RemoteException;

    boolean isA2dpConnected() throws RemoteException;

    boolean isA2dpServiceReady() throws RemoteException;

    void pauseA2dpRender() throws RemoteException;

    boolean registerA2dpCallback(INfCallbackA2dp iNfCallbackA2dp) throws RemoteException;

    boolean reqA2dpConnect(String str) throws RemoteException;

    boolean reqA2dpDisconnect(String str) throws RemoteException;

    boolean setA2dpLocalVolume(float f) throws RemoteException;

    boolean setA2dpStreamType(int i) throws RemoteException;

    void startA2dpRender() throws RemoteException;

    boolean unregisterA2dpCallback(INfCallbackA2dp iNfCallbackA2dp) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCommandA2dp {
        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean isA2dpServiceReady() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean registerA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean unregisterA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public int getA2dpConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean isA2dpConnected() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public String getA2dpConnectedAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean reqA2dpConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean reqA2dpDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public void pauseA2dpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public void startA2dpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean setA2dpLocalVolume(float vol) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean setA2dpStreamType(int type) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public int getA2dpStreamType() throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCommandA2dp {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCommandA2dp";
        static final int TRANSACTION_getA2dpConnectedAddress = 6;
        static final int TRANSACTION_getA2dpConnectionState = 4;
        static final int TRANSACTION_getA2dpStreamType = 13;
        static final int TRANSACTION_isA2dpConnected = 5;
        static final int TRANSACTION_isA2dpServiceReady = 1;
        static final int TRANSACTION_pauseA2dpRender = 9;
        static final int TRANSACTION_registerA2dpCallback = 2;
        static final int TRANSACTION_reqA2dpConnect = 7;
        static final int TRANSACTION_reqA2dpDisconnect = 8;
        static final int TRANSACTION_setA2dpLocalVolume = 11;
        static final int TRANSACTION_setA2dpStreamType = 12;
        static final int TRANSACTION_startA2dpRender = 10;
        static final int TRANSACTION_unregisterA2dpCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCommandA2dp asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCommandA2dp)) {
                return (INfCommandA2dp) iin;
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
                    boolean _result = isA2dpServiceReady();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackA2dp _arg0 = INfCallbackA2dp.Stub.asInterface(data.readStrongBinder());
                    boolean _result2 = registerA2dpCallback(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result2 ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackA2dp _arg02 = INfCallbackA2dp.Stub.asInterface(data.readStrongBinder());
                    boolean _result3 = unregisterA2dpCallback(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result3 ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = getA2dpConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result5 = isA2dpConnected();
                    reply.writeNoException();
                    reply.writeInt(_result5 ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String _result6 = getA2dpConnectedAddress();
                    reply.writeNoException();
                    reply.writeString(_result6);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    boolean _result7 = reqA2dpConnect(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result7 ? 1 : 0);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    boolean _result8 = reqA2dpDisconnect(_arg04);
                    reply.writeNoException();
                    reply.writeInt(_result8 ? 1 : 0);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    pauseA2dpRender();
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    startA2dpRender();
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    float _arg05 = data.readFloat();
                    boolean _result9 = setA2dpLocalVolume(_arg05);
                    reply.writeNoException();
                    reply.writeInt(_result9 ? 1 : 0);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg06 = data.readInt();
                    boolean _result10 = setA2dpStreamType(_arg06);
                    reply.writeNoException();
                    reply.writeInt(_result10 ? 1 : 0);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int _result11 = getA2dpStreamType();
                    reply.writeNoException();
                    reply.writeInt(_result11);
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
        public static class Proxy implements INfCommandA2dp {
            public static INfCommandA2dp sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public boolean isA2dpServiceReady() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isA2dpServiceReady();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public boolean registerA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public boolean unregisterA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public int getA2dpConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public boolean isA2dpConnected() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public String getA2dpConnectedAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public boolean reqA2dpConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public boolean reqA2dpDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public void pauseA2dpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public void startA2dpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public boolean setA2dpLocalVolume(float vol) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(vol);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public boolean setA2dpStreamType(int type) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandA2dp
            public int getA2dpStreamType() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
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
        }

        public static boolean setDefaultImpl(INfCommandA2dp impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCommandA2dp getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
