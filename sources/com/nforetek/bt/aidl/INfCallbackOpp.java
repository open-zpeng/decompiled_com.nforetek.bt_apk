package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface INfCallbackOpp extends IInterface {
    void onOppReceiveFileInfo(String str, int i, String str2, String str3) throws RemoteException;

    void onOppReceiveProgress(int i) throws RemoteException;

    void onOppServiceReady() throws RemoteException;

    void onOppStateChanged(String str, int i, int i2, int i3) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCallbackOpp {
        @Override // com.nforetek.bt.aidl.INfCallbackOpp
        public void onOppServiceReady() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackOpp
        public void onOppStateChanged(String address, int preState, int currentState, int reason) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackOpp
        public void onOppReceiveFileInfo(String fileName, int fileSize, String deviceName, String savePath) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackOpp
        public void onOppReceiveProgress(int receivedOffset) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCallbackOpp {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCallbackOpp";
        static final int TRANSACTION_onOppReceiveFileInfo = 3;
        static final int TRANSACTION_onOppReceiveProgress = 4;
        static final int TRANSACTION_onOppServiceReady = 1;
        static final int TRANSACTION_onOppStateChanged = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCallbackOpp asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCallbackOpp)) {
                return (INfCallbackOpp) iin;
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
                    onOppServiceReady();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    int _arg3 = data.readInt();
                    onOppStateChanged(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg02 = data.readString();
                    int _arg12 = data.readInt();
                    String _arg22 = data.readString();
                    String _arg32 = data.readString();
                    onOppReceiveFileInfo(_arg02, _arg12, _arg22, _arg32);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    onOppReceiveProgress(_arg03);
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
        public static class Proxy implements INfCallbackOpp {
            public static INfCallbackOpp sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCallbackOpp
            public void onOppServiceReady() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOppServiceReady();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackOpp
            public void onOppStateChanged(String address, int preState, int currentState, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(preState);
                    _data.writeInt(currentState);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOppStateChanged(address, preState, currentState, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackOpp
            public void onOppReceiveFileInfo(String fileName, int fileSize, String deviceName, String savePath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(fileName);
                    _data.writeInt(fileSize);
                    _data.writeString(deviceName);
                    _data.writeString(savePath);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOppReceiveFileInfo(fileName, fileSize, deviceName, savePath);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackOpp
            public void onOppReceiveProgress(int receivedOffset) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(receivedOffset);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOppReceiveProgress(receivedOffset);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INfCallbackOpp impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCallbackOpp getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
