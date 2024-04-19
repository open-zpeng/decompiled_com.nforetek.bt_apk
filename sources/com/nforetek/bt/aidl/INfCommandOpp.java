package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackOpp;
/* loaded from: classes.dex */
public interface INfCommandOpp extends IInterface {
    String getOppFilePath() throws RemoteException;

    boolean isOppServiceReady() throws RemoteException;

    boolean registerOppCallback(INfCallbackOpp iNfCallbackOpp) throws RemoteException;

    boolean reqOppAcceptReceiveFile(boolean z) throws RemoteException;

    boolean reqOppInterruptReceiveFile() throws RemoteException;

    boolean setOppFilePath(String str) throws RemoteException;

    boolean unregisterOppCallback(INfCallbackOpp iNfCallbackOpp) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCommandOpp {
        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean isOppServiceReady() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean registerOppCallback(INfCallbackOpp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean unregisterOppCallback(INfCallbackOpp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean setOppFilePath(String path) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public String getOppFilePath() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean reqOppAcceptReceiveFile(boolean accept) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean reqOppInterruptReceiveFile() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCommandOpp {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCommandOpp";
        static final int TRANSACTION_getOppFilePath = 5;
        static final int TRANSACTION_isOppServiceReady = 1;
        static final int TRANSACTION_registerOppCallback = 2;
        static final int TRANSACTION_reqOppAcceptReceiveFile = 6;
        static final int TRANSACTION_reqOppInterruptReceiveFile = 7;
        static final int TRANSACTION_setOppFilePath = 4;
        static final int TRANSACTION_unregisterOppCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCommandOpp asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCommandOpp)) {
                return (INfCommandOpp) iin;
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
                    boolean _result = isOppServiceReady();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackOpp _arg0 = INfCallbackOpp.Stub.asInterface(data.readStrongBinder());
                    boolean _result2 = registerOppCallback(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result2 ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackOpp _arg02 = INfCallbackOpp.Stub.asInterface(data.readStrongBinder());
                    boolean _result3 = unregisterOppCallback(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result3 ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    boolean _result4 = setOppFilePath(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result4 ? 1 : 0);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _result5 = getOppFilePath();
                    reply.writeNoException();
                    reply.writeString(_result5);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg04 = data.readInt() != 0;
                    boolean _result6 = reqOppAcceptReceiveFile(_arg04);
                    reply.writeNoException();
                    reply.writeInt(_result6 ? 1 : 0);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result7 = reqOppInterruptReceiveFile();
                    reply.writeNoException();
                    reply.writeInt(_result7 ? 1 : 0);
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
        public static class Proxy implements INfCommandOpp {
            public static INfCommandOpp sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCommandOpp
            public boolean isOppServiceReady() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isOppServiceReady();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandOpp
            public boolean registerOppCallback(INfCallbackOpp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandOpp
            public boolean unregisterOppCallback(INfCallbackOpp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandOpp
            public boolean setOppFilePath(String path) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandOpp
            public String getOppFilePath() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandOpp
            public boolean reqOppAcceptReceiveFile(boolean accept) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(accept ? 1 : 0);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandOpp
            public boolean reqOppInterruptReceiveFile() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
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
        }

        public static boolean setDefaultImpl(INfCommandOpp impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCommandOpp getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
