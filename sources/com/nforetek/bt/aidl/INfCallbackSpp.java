package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface INfCallbackSpp extends IInterface {
    void onSppAppleIapAuthenticationRequest(String str) throws RemoteException;

    void onSppDataReceived(String str, byte[] bArr) throws RemoteException;

    void onSppErrorResponse(String str, int i) throws RemoteException;

    void onSppSendData(String str, int i) throws RemoteException;

    void onSppServiceReady() throws RemoteException;

    void onSppStateChanged(String str, String str2, int i, int i2) throws RemoteException;

    void retSppConnectedDeviceAddressList(int i, String[] strArr, String[] strArr2) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCallbackSpp {
        @Override // com.nforetek.bt.aidl.INfCallbackSpp
        public void onSppServiceReady() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackSpp
        public void onSppStateChanged(String address, String deviceName, int prevState, int newState) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackSpp
        public void onSppErrorResponse(String address, int errorCode) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackSpp
        public void retSppConnectedDeviceAddressList(int totalNum, String[] addressList, String[] nameList) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackSpp
        public void onSppDataReceived(String address, byte[] receivedData) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackSpp
        public void onSppSendData(String address, int length) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackSpp
        public void onSppAppleIapAuthenticationRequest(String address) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCallbackSpp {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCallbackSpp";
        static final int TRANSACTION_onSppAppleIapAuthenticationRequest = 7;
        static final int TRANSACTION_onSppDataReceived = 5;
        static final int TRANSACTION_onSppErrorResponse = 3;
        static final int TRANSACTION_onSppSendData = 6;
        static final int TRANSACTION_onSppServiceReady = 1;
        static final int TRANSACTION_onSppStateChanged = 2;
        static final int TRANSACTION_retSppConnectedDeviceAddressList = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCallbackSpp asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCallbackSpp)) {
                return (INfCallbackSpp) iin;
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
                    onSppServiceReady();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    String _arg1 = data.readString();
                    int _arg2 = data.readInt();
                    int _arg3 = data.readInt();
                    onSppStateChanged(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg02 = data.readString();
                    int _arg12 = data.readInt();
                    onSppErrorResponse(_arg02, _arg12);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    String[] _arg13 = data.createStringArray();
                    String[] _arg22 = data.createStringArray();
                    retSppConnectedDeviceAddressList(_arg03, _arg13, _arg22);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    byte[] _arg14 = data.createByteArray();
                    onSppDataReceived(_arg04, _arg14);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    int _arg15 = data.readInt();
                    onSppSendData(_arg05, _arg15);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    onSppAppleIapAuthenticationRequest(_arg06);
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
        public static class Proxy implements INfCallbackSpp {
            public static INfCallbackSpp sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCallbackSpp
            public void onSppServiceReady() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSppServiceReady();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackSpp
            public void onSppStateChanged(String address, String deviceName, int prevState, int newState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeString(deviceName);
                    _data.writeInt(prevState);
                    _data.writeInt(newState);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSppStateChanged(address, deviceName, prevState, newState);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackSpp
            public void onSppErrorResponse(String address, int errorCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(errorCode);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSppErrorResponse(address, errorCode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackSpp
            public void retSppConnectedDeviceAddressList(int totalNum, String[] addressList, String[] nameList) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(totalNum);
                    _data.writeStringArray(addressList);
                    _data.writeStringArray(nameList);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().retSppConnectedDeviceAddressList(totalNum, addressList, nameList);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackSpp
            public void onSppDataReceived(String address, byte[] receivedData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeByteArray(receivedData);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSppDataReceived(address, receivedData);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackSpp
            public void onSppSendData(String address, int length) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(length);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSppSendData(address, length);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackSpp
            public void onSppAppleIapAuthenticationRequest(String address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSppAppleIapAuthenticationRequest(address);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INfCallbackSpp impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCallbackSpp getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
