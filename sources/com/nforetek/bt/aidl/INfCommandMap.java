package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackMap;
/* loaded from: classes.dex */
public interface INfCommandMap extends IInterface {
    int getMapCurrentState(String str) throws RemoteException;

    int getMapRegisterState(String str) throws RemoteException;

    boolean isMapNotificationRegistered(String str) throws RemoteException;

    boolean isMapServiceReady() throws RemoteException;

    boolean registerMapCallback(INfCallbackMap iNfCallbackMap) throws RemoteException;

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

    boolean setMapDownloadNotify(int i) throws RemoteException;

    boolean unregisterMapCallback(INfCallbackMap iNfCallbackMap) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCommandMap {
        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean isMapServiceReady() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean registerMapCallback(INfCallbackMap cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean unregisterMapCallback(INfCallbackMap cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapDownloadSingleMessage(String address, int folder, String handle, int storage) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapDownloadMessage(String address, int folder, boolean isContentDownload, int count, int startPos, int storage, String periodBegin, String periodEnd, String sender, String recipient, int readStatus, int typeFilter) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapRegisterNotification(String address, boolean downloadNewMessage) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public void reqMapUnregisterNotification(String address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean isMapNotificationRegistered(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapDownloadInterrupt(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public void reqMapDatabaseAvailable() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public void reqMapDeleteDatabaseByAddress(String address) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public void reqMapCleanDatabase() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public int getMapCurrentState(String address) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public int getMapRegisterState(String address) throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapSendMessage(String address, String message, String target) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapDeleteMessage(String address, int folder, String handle) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapChangeReadStatus(String address, int folder, String handle, boolean isReadStatus) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean setMapDownloadNotify(int frequency) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCommandMap {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCommandMap";
        static final int TRANSACTION_getMapCurrentState = 13;
        static final int TRANSACTION_getMapRegisterState = 14;
        static final int TRANSACTION_isMapNotificationRegistered = 8;
        static final int TRANSACTION_isMapServiceReady = 1;
        static final int TRANSACTION_registerMapCallback = 2;
        static final int TRANSACTION_reqMapChangeReadStatus = 17;
        static final int TRANSACTION_reqMapCleanDatabase = 12;
        static final int TRANSACTION_reqMapDatabaseAvailable = 10;
        static final int TRANSACTION_reqMapDeleteDatabaseByAddress = 11;
        static final int TRANSACTION_reqMapDeleteMessage = 16;
        static final int TRANSACTION_reqMapDownloadInterrupt = 9;
        static final int TRANSACTION_reqMapDownloadMessage = 5;
        static final int TRANSACTION_reqMapDownloadSingleMessage = 4;
        static final int TRANSACTION_reqMapRegisterNotification = 6;
        static final int TRANSACTION_reqMapSendMessage = 15;
        static final int TRANSACTION_reqMapUnregisterNotification = 7;
        static final int TRANSACTION_setMapDownloadNotify = 18;
        static final int TRANSACTION_unregisterMapCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCommandMap asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCommandMap)) {
                return (INfCommandMap) iin;
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
                    boolean _result = isMapServiceReady();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackMap _arg0 = INfCallbackMap.Stub.asInterface(data.readStrongBinder());
                    boolean _result2 = registerMapCallback(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result2 ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackMap _arg02 = INfCallbackMap.Stub.asInterface(data.readStrongBinder());
                    boolean _result3 = unregisterMapCallback(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result3 ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    int _arg1 = data.readInt();
                    String _arg2 = data.readString();
                    int _arg3 = data.readInt();
                    boolean _result4 = reqMapDownloadSingleMessage(_arg03, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    reply.writeInt(_result4 ? 1 : 0);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    int _arg12 = data.readInt();
                    boolean _arg22 = data.readInt() != 0;
                    int _arg32 = data.readInt();
                    int _arg4 = data.readInt();
                    int _arg5 = data.readInt();
                    String _arg6 = data.readString();
                    String _arg7 = data.readString();
                    String _arg8 = data.readString();
                    String _arg9 = data.readString();
                    int _arg10 = data.readInt();
                    int _arg11 = data.readInt();
                    boolean _result5 = reqMapDownloadMessage(_arg04, _arg12, _arg22, _arg32, _arg4, _arg5, _arg6, _arg7, _arg8, _arg9, _arg10, _arg11);
                    reply.writeNoException();
                    reply.writeInt(_result5 ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    boolean _arg13 = data.readInt() != 0;
                    boolean _result6 = reqMapRegisterNotification(_arg05, _arg13);
                    reply.writeNoException();
                    reply.writeInt(_result6 ? 1 : 0);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    reqMapUnregisterNotification(_arg06);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    boolean _result7 = isMapNotificationRegistered(_arg07);
                    reply.writeNoException();
                    reply.writeInt(_result7 ? 1 : 0);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    boolean _result8 = reqMapDownloadInterrupt(_arg08);
                    reply.writeNoException();
                    reply.writeInt(_result8 ? 1 : 0);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    reqMapDatabaseAvailable();
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg09 = data.readString();
                    reqMapDeleteDatabaseByAddress(_arg09);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    reqMapCleanDatabase();
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg010 = data.readString();
                    int _result9 = getMapCurrentState(_arg010);
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg011 = data.readString();
                    int _result10 = getMapRegisterState(_arg011);
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg012 = data.readString();
                    String _arg14 = data.readString();
                    String _arg23 = data.readString();
                    boolean _result11 = reqMapSendMessage(_arg012, _arg14, _arg23);
                    reply.writeNoException();
                    reply.writeInt(_result11 ? 1 : 0);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg013 = data.readString();
                    int _arg15 = data.readInt();
                    String _arg24 = data.readString();
                    boolean _result12 = reqMapDeleteMessage(_arg013, _arg15, _arg24);
                    reply.writeNoException();
                    reply.writeInt(_result12 ? 1 : 0);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg014 = data.readString();
                    int _arg16 = data.readInt();
                    String _arg25 = data.readString();
                    boolean _arg33 = data.readInt() != 0;
                    boolean _result13 = reqMapChangeReadStatus(_arg014, _arg16, _arg25, _arg33);
                    reply.writeNoException();
                    reply.writeInt(_result13 ? 1 : 0);
                    return true;
                case TRANSACTION_setMapDownloadNotify /* 18 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg015 = data.readInt();
                    boolean _result14 = setMapDownloadNotify(_arg015);
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
        public static class Proxy implements INfCommandMap {
            public static INfCommandMap sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public boolean isMapServiceReady() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isMapServiceReady();
                    } else {
                        _reply.readException();
                        _result = _reply.readInt() != 0;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public boolean registerMapCallback(INfCallbackMap cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public boolean unregisterMapCallback(INfCallbackMap cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
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
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
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
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public boolean reqMapRegisterNotification(String address, boolean downloadNewMessage) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(downloadNewMessage ? 1 : 0);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public void reqMapUnregisterNotification(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public boolean isMapNotificationRegistered(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public boolean reqMapDownloadInterrupt(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public void reqMapDatabaseAvailable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public void reqMapDeleteDatabaseByAddress(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public void reqMapCleanDatabase() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public int getMapCurrentState(String address) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public int getMapRegisterState(String address) throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public boolean reqMapSendMessage(String address, String message, String target) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(message);
                    _data.writeString(target);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
            public boolean reqMapDeleteMessage(String address, int folder, String handle) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(folder);
                    _data.writeString(handle);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
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
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
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

            @Override // com.nforetek.bt.aidl.INfCommandMap
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
        }

        public static boolean setDefaultImpl(INfCommandMap impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCommandMap getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
