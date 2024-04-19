package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface INfCallbackMap extends IInterface {
    void onMapDownloadNotify(String str, int i, int i2, int i3) throws RemoteException;

    void onMapMemoryAvailableEvent(String str, int i, boolean z) throws RemoteException;

    void onMapMessageDeletedEvent(String str, String str2, int i, int i2) throws RemoteException;

    void onMapMessageDeliverStatusEvent(String str, String str2, boolean z) throws RemoteException;

    void onMapMessageSendingStatusEvent(String str, String str2, boolean z) throws RemoteException;

    void onMapMessageShiftedEvent(String str, String str2, int i, int i2, int i3) throws RemoteException;

    void onMapNewMessageReceivedEvent(String str, String str2, String str3, String str4) throws RemoteException;

    void onMapServiceReady() throws RemoteException;

    void onMapStateChanged(String str, int i, int i2, int i3) throws RemoteException;

    void retMapChangeReadStatusCompleted(String str, String str2, int i) throws RemoteException;

    void retMapCleanDatabaseCompleted(boolean z) throws RemoteException;

    void retMapDatabaseAvailable() throws RemoteException;

    void retMapDeleteDatabaseByAddressCompleted(String str, boolean z) throws RemoteException;

    void retMapDeleteMessageCompleted(String str, String str2, int i) throws RemoteException;

    void retMapDownloadedMessage(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, boolean z, String str7, String str8) throws RemoteException;

    void retMapSendMessageCompleted(String str, String str2, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCallbackMap {
        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void onMapServiceReady() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void onMapStateChanged(String address, int prevState, int newState, int reason) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void retMapDownloadedMessage(String address, String handle, String senderName, String senderNumber, String recipientNumber, String date, int type, int folder, boolean isReadStatus, String subject, String message) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void onMapNewMessageReceivedEvent(String address, String handle, String sender, String message) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void onMapDownloadNotify(String address, int folder, int totalMessages, int currentMessages) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void retMapDatabaseAvailable() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void retMapDeleteDatabaseByAddressCompleted(String address, boolean isSuccess) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void retMapCleanDatabaseCompleted(boolean isSuccess) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void retMapSendMessageCompleted(String address, String target, int state) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void retMapDeleteMessageCompleted(String address, String handle, int reason) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void retMapChangeReadStatusCompleted(String address, String handle, int reason) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void onMapMemoryAvailableEvent(String address, int structure, boolean available) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void onMapMessageSendingStatusEvent(String address, String handle, boolean isSuccess) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void onMapMessageDeliverStatusEvent(String address, String handle, boolean isSuccess) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void onMapMessageShiftedEvent(String address, String handle, int type, int newFolder, int oldFolder) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackMap
        public void onMapMessageDeletedEvent(String address, String handle, int type, int folder) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCallbackMap {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCallbackMap";
        static final int TRANSACTION_onMapDownloadNotify = 5;
        static final int TRANSACTION_onMapMemoryAvailableEvent = 12;
        static final int TRANSACTION_onMapMessageDeletedEvent = 16;
        static final int TRANSACTION_onMapMessageDeliverStatusEvent = 14;
        static final int TRANSACTION_onMapMessageSendingStatusEvent = 13;
        static final int TRANSACTION_onMapMessageShiftedEvent = 15;
        static final int TRANSACTION_onMapNewMessageReceivedEvent = 4;
        static final int TRANSACTION_onMapServiceReady = 1;
        static final int TRANSACTION_onMapStateChanged = 2;
        static final int TRANSACTION_retMapChangeReadStatusCompleted = 11;
        static final int TRANSACTION_retMapCleanDatabaseCompleted = 8;
        static final int TRANSACTION_retMapDatabaseAvailable = 6;
        static final int TRANSACTION_retMapDeleteDatabaseByAddressCompleted = 7;
        static final int TRANSACTION_retMapDeleteMessageCompleted = 10;
        static final int TRANSACTION_retMapDownloadedMessage = 3;
        static final int TRANSACTION_retMapSendMessageCompleted = 9;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCallbackMap asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCallbackMap)) {
                return (INfCallbackMap) iin;
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
                    onMapServiceReady();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    int _arg3 = data.readInt();
                    onMapStateChanged(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg02 = data.readString();
                    String _arg12 = data.readString();
                    String _arg22 = data.readString();
                    String _arg32 = data.readString();
                    String _arg4 = data.readString();
                    String _arg5 = data.readString();
                    int _arg6 = data.readInt();
                    int _arg7 = data.readInt();
                    boolean _arg8 = data.readInt() != 0;
                    String _arg9 = data.readString();
                    String _arg10 = data.readString();
                    retMapDownloadedMessage(_arg02, _arg12, _arg22, _arg32, _arg4, _arg5, _arg6, _arg7, _arg8, _arg9, _arg10);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    String _arg13 = data.readString();
                    String _arg23 = data.readString();
                    String _arg33 = data.readString();
                    onMapNewMessageReceivedEvent(_arg03, _arg13, _arg23, _arg33);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    int _arg14 = data.readInt();
                    int _arg24 = data.readInt();
                    int _arg34 = data.readInt();
                    onMapDownloadNotify(_arg04, _arg14, _arg24, _arg34);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    retMapDatabaseAvailable();
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    boolean _arg15 = data.readInt() != 0;
                    retMapDeleteDatabaseByAddressCompleted(_arg05, _arg15);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg06 = data.readInt() != 0;
                    retMapCleanDatabaseCompleted(_arg06);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    String _arg16 = data.readString();
                    int _arg25 = data.readInt();
                    retMapSendMessageCompleted(_arg07, _arg16, _arg25);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    String _arg17 = data.readString();
                    int _arg26 = data.readInt();
                    retMapDeleteMessageCompleted(_arg08, _arg17, _arg26);
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg09 = data.readString();
                    String _arg18 = data.readString();
                    int _arg27 = data.readInt();
                    retMapChangeReadStatusCompleted(_arg09, _arg18, _arg27);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg010 = data.readString();
                    int _arg19 = data.readInt();
                    boolean _arg28 = data.readInt() != 0;
                    onMapMemoryAvailableEvent(_arg010, _arg19, _arg28);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg011 = data.readString();
                    String _arg110 = data.readString();
                    boolean _arg29 = data.readInt() != 0;
                    onMapMessageSendingStatusEvent(_arg011, _arg110, _arg29);
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg012 = data.readString();
                    String _arg111 = data.readString();
                    boolean _arg210 = data.readInt() != 0;
                    onMapMessageDeliverStatusEvent(_arg012, _arg111, _arg210);
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg013 = data.readString();
                    String _arg112 = data.readString();
                    int _arg211 = data.readInt();
                    int _arg35 = data.readInt();
                    int _arg42 = data.readInt();
                    onMapMessageShiftedEvent(_arg013, _arg112, _arg211, _arg35, _arg42);
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg014 = data.readString();
                    String _arg113 = data.readString();
                    int _arg212 = data.readInt();
                    int _arg36 = data.readInt();
                    onMapMessageDeletedEvent(_arg014, _arg113, _arg212, _arg36);
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
        public static class Proxy implements INfCallbackMap {
            public static INfCallbackMap sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void onMapServiceReady() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapServiceReady();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void onMapStateChanged(String address, int prevState, int newState, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(prevState);
                    _data.writeInt(newState);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapStateChanged(address, prevState, newState, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void retMapDownloadedMessage(String address, String handle, String senderName, String senderNumber, String recipientNumber, String date, int type, int folder, boolean isReadStatus, String subject, String message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeString(senderName);
                    _data.writeString(senderNumber);
                    _data.writeString(recipientNumber);
                    _data.writeString(date);
                    _data.writeInt(type);
                    _data.writeInt(folder);
                    _data.writeInt(isReadStatus ? 1 : 0);
                    _data.writeString(subject);
                    _data.writeString(message);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retMapDownloadedMessage(address, handle, senderName, senderNumber, recipientNumber, date, type, folder, isReadStatus, subject, message);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void onMapNewMessageReceivedEvent(String address, String handle, String sender, String message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeString(sender);
                    _data.writeString(message);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapNewMessageReceivedEvent(address, handle, sender, message);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void onMapDownloadNotify(String address, int folder, int totalMessages, int currentMessages) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(folder);
                    _data.writeInt(totalMessages);
                    _data.writeInt(currentMessages);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapDownloadNotify(address, folder, totalMessages, currentMessages);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void retMapDatabaseAvailable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retMapDatabaseAvailable();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void retMapDeleteDatabaseByAddressCompleted(String address, boolean isSuccess) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(isSuccess ? 1 : 0);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retMapDeleteDatabaseByAddressCompleted(address, isSuccess);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void retMapCleanDatabaseCompleted(boolean isSuccess) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(isSuccess ? 1 : 0);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retMapCleanDatabaseCompleted(isSuccess);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void retMapSendMessageCompleted(String address, String target, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(target);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retMapSendMessageCompleted(address, target, state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void retMapDeleteMessageCompleted(String address, String handle, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retMapDeleteMessageCompleted(address, handle, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void retMapChangeReadStatusCompleted(String address, String handle, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retMapChangeReadStatusCompleted(address, handle, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void onMapMemoryAvailableEvent(String address, int structure, boolean available) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(structure);
                    _data.writeInt(available ? 1 : 0);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapMemoryAvailableEvent(address, structure, available);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void onMapMessageSendingStatusEvent(String address, String handle, boolean isSuccess) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(isSuccess ? 1 : 0);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapMessageSendingStatusEvent(address, handle, isSuccess);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void onMapMessageDeliverStatusEvent(String address, String handle, boolean isSuccess) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(isSuccess ? 1 : 0);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapMessageDeliverStatusEvent(address, handle, isSuccess);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void onMapMessageShiftedEvent(String address, String handle, int type, int newFolder, int oldFolder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(type);
                    _data.writeInt(newFolder);
                    _data.writeInt(oldFolder);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapMessageShiftedEvent(address, handle, type, newFolder, oldFolder);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackMap
            public void onMapMessageDeletedEvent(String address, String handle, int type, int folder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(handle);
                    _data.writeInt(type);
                    _data.writeInt(folder);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapMessageDeletedEvent(address, handle, type, folder);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INfCallbackMap impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCallbackMap getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
