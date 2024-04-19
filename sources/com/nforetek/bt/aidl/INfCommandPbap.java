package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackPbap;
/* loaded from: classes.dex */
public interface INfCommandPbap extends IInterface {
    int getPbapConnectionState() throws RemoteException;

    String getPbapDownloadingAddress() throws RemoteException;

    boolean isPbapDownloading() throws RemoteException;

    boolean isPbapServiceReady() throws RemoteException;

    boolean registerPbapCallback(INfCallbackPbap iNfCallbackPbap) throws RemoteException;

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

    boolean setPbapDownloadNotify(int i) throws RemoteException;

    boolean unregisterPbapCallback(INfCallbackPbap iNfCallbackPbap) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCommandPbap {
        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean isPbapServiceReady() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean registerPbapCallback(INfCallbackPbap cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean unregisterPbapCallback(INfCallbackPbap cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public int getPbapConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean isPbapDownloading() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public String getPbapDownloadingAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownload(String address, int storage, int property) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadRange(String address, int storage, int property, int startPos, int offset) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadToDatabase(String address, int storage, int property) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadRangeToDatabase(String address, int storage, int property, int startPos, int offset) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadToContactsProvider(String address, int storage, int property) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadRangeToContactsProvider(String address, int storage, int property, int startPos, int offset) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public void reqPbapDatabaseQueryNameByNumber(String address, String target) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public void reqPbapDatabaseQueryNameByPartialNumber(String address, String target, int findPosition) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public void reqPbapDatabaseAvailable(String address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public void reqPbapDeleteDatabaseByAddress(String address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public void reqPbapCleanDatabase() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadInterrupt(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean setPbapDownloadNotify(int frequency) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCommandPbap {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCommandPbap";
        static final int TRANSACTION_getPbapConnectionState = 4;
        static final int TRANSACTION_getPbapDownloadingAddress = 6;
        static final int TRANSACTION_isPbapDownloading = 5;
        static final int TRANSACTION_isPbapServiceReady = 1;
        static final int TRANSACTION_registerPbapCallback = 2;
        static final int TRANSACTION_reqPbapCleanDatabase = 17;
        static final int TRANSACTION_reqPbapDatabaseAvailable = 15;
        static final int TRANSACTION_reqPbapDatabaseQueryNameByNumber = 13;
        static final int TRANSACTION_reqPbapDatabaseQueryNameByPartialNumber = 14;
        static final int TRANSACTION_reqPbapDeleteDatabaseByAddress = 16;
        static final int TRANSACTION_reqPbapDownload = 7;
        static final int TRANSACTION_reqPbapDownloadInterrupt = 18;
        static final int TRANSACTION_reqPbapDownloadRange = 8;
        static final int TRANSACTION_reqPbapDownloadRangeToContactsProvider = 12;
        static final int TRANSACTION_reqPbapDownloadRangeToDatabase = 10;
        static final int TRANSACTION_reqPbapDownloadToContactsProvider = 11;
        static final int TRANSACTION_reqPbapDownloadToDatabase = 9;
        static final int TRANSACTION_setPbapDownloadNotify = 19;
        static final int TRANSACTION_unregisterPbapCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCommandPbap asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCommandPbap)) {
                return (INfCommandPbap) iin;
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
                    boolean _result = isPbapServiceReady();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackPbap _arg0 = INfCallbackPbap.Stub.asInterface(data.readStrongBinder());
                    boolean _result2 = registerPbapCallback(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result2 ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackPbap _arg02 = INfCallbackPbap.Stub.asInterface(data.readStrongBinder());
                    boolean _result3 = unregisterPbapCallback(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result3 ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = getPbapConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result5 = isPbapDownloading();
                    reply.writeNoException();
                    reply.writeInt(_result5 ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String _result6 = getPbapDownloadingAddress();
                    reply.writeNoException();
                    reply.writeString(_result6);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    boolean _result7 = reqPbapDownload(_arg03, _arg1, _arg2);
                    reply.writeNoException();
                    reply.writeInt(_result7 ? 1 : 0);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    int _arg12 = data.readInt();
                    int _arg22 = data.readInt();
                    int _arg3 = data.readInt();
                    int _arg4 = data.readInt();
                    boolean _result8 = reqPbapDownloadRange(_arg04, _arg12, _arg22, _arg3, _arg4);
                    reply.writeNoException();
                    reply.writeInt(_result8 ? 1 : 0);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    int _arg13 = data.readInt();
                    int _arg23 = data.readInt();
                    boolean _result9 = reqPbapDownloadToDatabase(_arg05, _arg13, _arg23);
                    reply.writeNoException();
                    reply.writeInt(_result9 ? 1 : 0);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    int _arg14 = data.readInt();
                    int _arg24 = data.readInt();
                    int _arg32 = data.readInt();
                    int _arg42 = data.readInt();
                    boolean _result10 = reqPbapDownloadRangeToDatabase(_arg06, _arg14, _arg24, _arg32, _arg42);
                    reply.writeNoException();
                    reply.writeInt(_result10 ? 1 : 0);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    int _arg15 = data.readInt();
                    int _arg25 = data.readInt();
                    boolean _result11 = reqPbapDownloadToContactsProvider(_arg07, _arg15, _arg25);
                    reply.writeNoException();
                    reply.writeInt(_result11 ? 1 : 0);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    int _arg16 = data.readInt();
                    int _arg26 = data.readInt();
                    int _arg33 = data.readInt();
                    int _arg43 = data.readInt();
                    boolean _result12 = reqPbapDownloadRangeToContactsProvider(_arg08, _arg16, _arg26, _arg33, _arg43);
                    reply.writeNoException();
                    reply.writeInt(_result12 ? 1 : 0);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg09 = data.readString();
                    String _arg17 = data.readString();
                    reqPbapDatabaseQueryNameByNumber(_arg09, _arg17);
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg010 = data.readString();
                    String _arg18 = data.readString();
                    int _arg27 = data.readInt();
                    reqPbapDatabaseQueryNameByPartialNumber(_arg010, _arg18, _arg27);
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg011 = data.readString();
                    reqPbapDatabaseAvailable(_arg011);
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg012 = data.readString();
                    reqPbapDeleteDatabaseByAddress(_arg012);
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    reqPbapCleanDatabase();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_reqPbapDownloadInterrupt /* 18 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg013 = data.readString();
                    boolean _result13 = reqPbapDownloadInterrupt(_arg013);
                    reply.writeNoException();
                    reply.writeInt(_result13 ? 1 : 0);
                    return true;
                case TRANSACTION_setPbapDownloadNotify /* 19 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg014 = data.readInt();
                    boolean _result14 = setPbapDownloadNotify(_arg014);
                    reply.writeNoException();
                    reply.writeInt(_result14 ? 1 : 0);
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
        public static class Proxy implements INfCommandPbap {
            public static INfCommandPbap sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public boolean isPbapServiceReady() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isPbapServiceReady();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public boolean registerPbapCallback(INfCallbackPbap cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public boolean unregisterPbapCallback(INfCallbackPbap cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public int getPbapConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public boolean isPbapDownloading() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public String getPbapDownloadingAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public boolean reqPbapDownload(String address, int storage, int property) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(storage);
                    _data.writeInt(property);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
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
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public boolean reqPbapDownloadToDatabase(String address, int storage, int property) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(storage);
                    _data.writeInt(property);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
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
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public boolean reqPbapDownloadToContactsProvider(String address, int storage, int property) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(storage);
                    _data.writeInt(property);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
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
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public void reqPbapDatabaseQueryNameByNumber(String address, String target) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(target);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public void reqPbapDatabaseQueryNameByPartialNumber(String address, String target, int findPosition) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(target);
                    _data.writeInt(findPosition);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public void reqPbapDatabaseAvailable(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public void reqPbapDeleteDatabaseByAddress(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public void reqPbapCleanDatabase() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public boolean reqPbapDownloadInterrupt(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqPbapDownloadInterrupt, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandPbap
            public boolean setPbapDownloadNotify(int frequency) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(frequency);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setPbapDownloadNotify, _data, _reply, 0);
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
        }

        public static boolean setDefaultImpl(INfCommandPbap impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCommandPbap getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
