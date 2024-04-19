package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface INfCallbackJniPbap extends IInterface {
    void onJniPbapConnectionStateChanged(String str, int i) throws RemoteException;

    void onJniPbapDownloadStateChanged(String str, int i) throws RemoteException;

    void retJniPbapReceiveContacts(String str, int i, String str2, String str3, String str4, int i2, String[] strArr, int[] iArr, String str5, int i3, byte[] bArr, int i4, int[] iArr2, String[] strArr2, int[] iArr3, String[] strArr3, String str6) throws RemoteException;

    void retJniPbapReceivePhonebookSize(String str, int i, int i2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCallbackJniPbap {
        @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
        public void onJniPbapConnectionStateChanged(String address, int state) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
        public void onJniPbapDownloadStateChanged(String address, int state) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
        public void retJniPbapReceivePhonebookSize(String address, int access, int size) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
        public void retJniPbapReceiveContacts(String bt_address, int contactsId, String firstName, String middleName, String lastName, int encode, String[] number, int[] phoneType, String timestamp, int contactType, byte[] photo, int photoType, int[] emailType, String[] email, int[] addressType, String[] address, String org2) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCallbackJniPbap {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCallbackJniPbap";
        static final int TRANSACTION_onJniPbapConnectionStateChanged = 1;
        static final int TRANSACTION_onJniPbapDownloadStateChanged = 2;
        static final int TRANSACTION_retJniPbapReceiveContacts = 4;
        static final int TRANSACTION_retJniPbapReceivePhonebookSize = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCallbackJniPbap asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCallbackJniPbap)) {
                return (INfCallbackJniPbap) iin;
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
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    onJniPbapConnectionStateChanged(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg02 = data.readString();
                    int _arg12 = data.readInt();
                    onJniPbapDownloadStateChanged(_arg02, _arg12);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    int _arg13 = data.readInt();
                    int _arg2 = data.readInt();
                    retJniPbapReceivePhonebookSize(_arg03, _arg13, _arg2);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    int _arg14 = data.readInt();
                    String _arg22 = data.readString();
                    String _arg3 = data.readString();
                    String _arg4 = data.readString();
                    int _arg5 = data.readInt();
                    String[] _arg6 = data.createStringArray();
                    int[] _arg7 = data.createIntArray();
                    String _arg8 = data.readString();
                    int _arg9 = data.readInt();
                    byte[] _arg10 = data.createByteArray();
                    int _arg11 = data.readInt();
                    int[] _arg122 = data.createIntArray();
                    String[] _arg132 = data.createStringArray();
                    int[] _arg142 = data.createIntArray();
                    String[] _arg15 = data.createStringArray();
                    String _arg16 = data.readString();
                    retJniPbapReceiveContacts(_arg04, _arg14, _arg22, _arg3, _arg4, _arg5, _arg6, _arg7, _arg8, _arg9, _arg10, _arg11, _arg122, _arg132, _arg142, _arg15, _arg16);
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
        public static class Proxy implements INfCallbackJniPbap {
            public static INfCallbackJniPbap sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
            public void onJniPbapConnectionStateChanged(String address, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniPbapConnectionStateChanged(address, state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
            public void onJniPbapDownloadStateChanged(String address, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onJniPbapDownloadStateChanged(address, state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
            public void retJniPbapReceivePhonebookSize(String address, int access, int size) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(access);
                    _data.writeInt(size);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniPbapReceivePhonebookSize(address, access, size);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackJniPbap
            public void retJniPbapReceiveContacts(String bt_address, int contactsId, String firstName, String middleName, String lastName, int encode, String[] number, int[] phoneType, String timestamp, int contactType, byte[] photo, int photoType, int[] emailType, String[] email, int[] addressType, String[] address, String org2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(bt_address);
                    _data.writeInt(contactsId);
                    _data.writeString(firstName);
                    _data.writeString(middleName);
                    _data.writeString(lastName);
                    _data.writeInt(encode);
                    _data.writeStringArray(number);
                    _data.writeIntArray(phoneType);
                    _data.writeString(timestamp);
                    _data.writeInt(contactType);
                    _data.writeByteArray(photo);
                    _data.writeInt(photoType);
                    _data.writeIntArray(emailType);
                    _data.writeStringArray(email);
                    _data.writeIntArray(addressType);
                    _data.writeStringArray(address);
                    _data.writeString(org2);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retJniPbapReceiveContacts(bt_address, contactsId, firstName, middleName, lastName, encode, number, phoneType, timestamp, contactType, photo, photoType, emailType, email, addressType, address, org2);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INfCallbackJniPbap impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCallbackJniPbap getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
