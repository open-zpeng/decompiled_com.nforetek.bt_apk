package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackGattServer;
import java.util.List;
/* loaded from: classes.dex */
public interface INfCommandGattServer extends IInterface {
    List<ParcelUuid> getGattAddedGattCharacteristicUuidList(ParcelUuid parcelUuid) throws RemoteException;

    List<ParcelUuid> getGattAddedGattDescriptorUuidList(ParcelUuid parcelUuid, ParcelUuid parcelUuid2) throws RemoteException;

    List<ParcelUuid> getGattAddedGattServiceUuidList() throws RemoteException;

    int getGattServerConnectionState() throws RemoteException;

    boolean isGattServiceReady() throws RemoteException;

    boolean registerGattServerCallback(INfCallbackGattServer iNfCallbackGattServer) throws RemoteException;

    boolean reqGattServerAddCharacteristic(ParcelUuid parcelUuid, int i, int i2) throws RemoteException;

    boolean reqGattServerAddDescriptor(ParcelUuid parcelUuid, int i) throws RemoteException;

    boolean reqGattServerBeginServiceDeclaration(int i, ParcelUuid parcelUuid) throws RemoteException;

    boolean reqGattServerClearServices() throws RemoteException;

    boolean reqGattServerDisconnect(String str) throws RemoteException;

    boolean reqGattServerEndServiceDeclaration() throws RemoteException;

    boolean reqGattServerListen(boolean z) throws RemoteException;

    boolean reqGattServerRemoveService(int i, ParcelUuid parcelUuid) throws RemoteException;

    boolean reqGattServerSendNotification(String str, int i, ParcelUuid parcelUuid, ParcelUuid parcelUuid2, boolean z, byte[] bArr) throws RemoteException;

    boolean reqGattServerSendResponse(String str, int i, int i2, int i3, byte[] bArr) throws RemoteException;

    boolean unregisterGattServerCallback(INfCallbackGattServer iNfCallbackGattServer) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCommandGattServer {
        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean isGattServiceReady() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean registerGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean unregisterGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public int getGattServerConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerBeginServiceDeclaration(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerAddCharacteristic(ParcelUuid charUuid, int properties, int permissions) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerAddDescriptor(ParcelUuid descUuid, int permissions) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerEndServiceDeclaration() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerRemoveService(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerClearServices() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerListen(boolean listen) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerSendResponse(String address, int requestId, int status, int offset, byte[] value) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerSendNotification(String address, int srvcType, ParcelUuid srvcUuid, ParcelUuid charUuid, boolean confirm, byte[] value) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public List<ParcelUuid> getGattAddedGattServiceUuidList() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public List<ParcelUuid> getGattAddedGattCharacteristicUuidList(ParcelUuid srvcUuid) throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public List<ParcelUuid> getGattAddedGattDescriptorUuidList(ParcelUuid srvcUuid, ParcelUuid charUuid) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCommandGattServer {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCommandGattServer";
        static final int TRANSACTION_getGattAddedGattCharacteristicUuidList = 16;
        static final int TRANSACTION_getGattAddedGattDescriptorUuidList = 17;
        static final int TRANSACTION_getGattAddedGattServiceUuidList = 15;
        static final int TRANSACTION_getGattServerConnectionState = 4;
        static final int TRANSACTION_isGattServiceReady = 1;
        static final int TRANSACTION_registerGattServerCallback = 2;
        static final int TRANSACTION_reqGattServerAddCharacteristic = 7;
        static final int TRANSACTION_reqGattServerAddDescriptor = 8;
        static final int TRANSACTION_reqGattServerBeginServiceDeclaration = 6;
        static final int TRANSACTION_reqGattServerClearServices = 11;
        static final int TRANSACTION_reqGattServerDisconnect = 5;
        static final int TRANSACTION_reqGattServerEndServiceDeclaration = 9;
        static final int TRANSACTION_reqGattServerListen = 12;
        static final int TRANSACTION_reqGattServerRemoveService = 10;
        static final int TRANSACTION_reqGattServerSendNotification = 14;
        static final int TRANSACTION_reqGattServerSendResponse = 13;
        static final int TRANSACTION_unregisterGattServerCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCommandGattServer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCommandGattServer)) {
                return (INfCommandGattServer) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelUuid _arg0;
            ParcelUuid _arg1;
            ParcelUuid _arg02;
            ParcelUuid _arg2;
            ParcelUuid _arg3;
            ParcelUuid _arg12;
            ParcelUuid _arg03;
            ParcelUuid _arg04;
            ParcelUuid _arg13;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = isGattServiceReady();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackGattServer _arg05 = INfCallbackGattServer.Stub.asInterface(data.readStrongBinder());
                    boolean _result2 = registerGattServerCallback(_arg05);
                    reply.writeNoException();
                    reply.writeInt(_result2 ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackGattServer _arg06 = INfCallbackGattServer.Stub.asInterface(data.readStrongBinder());
                    boolean _result3 = unregisterGattServerCallback(_arg06);
                    reply.writeNoException();
                    reply.writeInt(_result3 ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = getGattServerConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    boolean _result5 = reqGattServerDisconnect(_arg07);
                    reply.writeNoException();
                    reply.writeInt(_result5 ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg08 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg13 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }
                    boolean _result6 = reqGattServerBeginServiceDeclaration(_arg08, _arg13);
                    reply.writeNoException();
                    reply.writeInt(_result6 ? 1 : 0);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg04 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    int _arg14 = data.readInt();
                    int _arg22 = data.readInt();
                    boolean _result7 = reqGattServerAddCharacteristic(_arg04, _arg14, _arg22);
                    reply.writeNoException();
                    reply.writeInt(_result7 ? 1 : 0);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    int _arg15 = data.readInt();
                    boolean _result8 = reqGattServerAddDescriptor(_arg03, _arg15);
                    reply.writeNoException();
                    reply.writeInt(_result8 ? 1 : 0);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result9 = reqGattServerEndServiceDeclaration();
                    reply.writeNoException();
                    reply.writeInt(_result9 ? 1 : 0);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg09 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg12 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    boolean _result10 = reqGattServerRemoveService(_arg09, _arg12);
                    reply.writeNoException();
                    reply.writeInt(_result10 ? 1 : 0);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result11 = reqGattServerClearServices();
                    reply.writeNoException();
                    reply.writeInt(_result11 ? 1 : 0);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg010 = data.readInt() != 0;
                    boolean _result12 = reqGattServerListen(_arg010);
                    reply.writeNoException();
                    reply.writeInt(_result12 ? 1 : 0);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg011 = data.readString();
                    int _arg16 = data.readInt();
                    int _arg23 = data.readInt();
                    int _arg32 = data.readInt();
                    byte[] _arg4 = data.createByteArray();
                    boolean _result13 = reqGattServerSendResponse(_arg011, _arg16, _arg23, _arg32, _arg4);
                    reply.writeNoException();
                    reply.writeInt(_result13 ? 1 : 0);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg012 = data.readString();
                    int _arg17 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg2 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg3 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    boolean _arg42 = data.readInt() != 0;
                    byte[] _arg5 = data.createByteArray();
                    boolean _result14 = reqGattServerSendNotification(_arg012, _arg17, _arg2, _arg3, _arg42, _arg5);
                    reply.writeNoException();
                    reply.writeInt(_result14 ? 1 : 0);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    List<ParcelUuid> _result15 = getGattAddedGattServiceUuidList();
                    reply.writeNoException();
                    reply.writeTypedList(_result15);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    List<ParcelUuid> _result16 = getGattAddedGattCharacteristicUuidList(_arg02);
                    reply.writeNoException();
                    reply.writeTypedList(_result16);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg1 = (ParcelUuid) ParcelUuid.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    List<ParcelUuid> _result17 = getGattAddedGattDescriptorUuidList(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeTypedList(_result17);
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
        public static class Proxy implements INfCommandGattServer {
            public static INfCommandGattServer sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean isGattServiceReady() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isGattServiceReady();
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean registerGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerGattServerCallback(cb);
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean unregisterGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterGattServerCallback(cb);
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public int getGattServerConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getGattServerConnectionState();
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean reqGattServerDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerDisconnect(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean reqGattServerBeginServiceDeclaration(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(srvcType);
                    if (srvcUuid != null) {
                        _data.writeInt(1);
                        srvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerBeginServiceDeclaration(srvcType, srvcUuid);
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean reqGattServerAddCharacteristic(ParcelUuid charUuid, int properties, int permissions) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (charUuid != null) {
                        _data.writeInt(1);
                        charUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(properties);
                    _data.writeInt(permissions);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerAddCharacteristic(charUuid, properties, permissions);
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean reqGattServerAddDescriptor(ParcelUuid descUuid, int permissions) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (descUuid != null) {
                        _data.writeInt(1);
                        descUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(permissions);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerAddDescriptor(descUuid, permissions);
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean reqGattServerEndServiceDeclaration() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerEndServiceDeclaration();
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean reqGattServerRemoveService(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(srvcType);
                    if (srvcUuid != null) {
                        _data.writeInt(1);
                        srvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerRemoveService(srvcType, srvcUuid);
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean reqGattServerClearServices() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerClearServices();
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean reqGattServerListen(boolean listen) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(listen ? 1 : 0);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerListen(listen);
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean reqGattServerSendResponse(String address, int requestId, int status, int offset, byte[] value) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(requestId);
                    _data.writeInt(status);
                    _data.writeInt(offset);
                    _data.writeByteArray(value);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerSendResponse(address, requestId, status, offset, value);
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public boolean reqGattServerSendNotification(String address, int srvcType, ParcelUuid srvcUuid, ParcelUuid charUuid, boolean confirm, byte[] value) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(srvcType);
                    if (srvcUuid != null) {
                        _data.writeInt(1);
                        srvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (charUuid != null) {
                        _data.writeInt(1);
                        charUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(confirm ? 1 : 0);
                    _data.writeByteArray(value);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqGattServerSendNotification(address, srvcType, srvcUuid, charUuid, confirm, value);
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

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public List<ParcelUuid> getGattAddedGattServiceUuidList() throws RemoteException {
                List<ParcelUuid> createTypedArrayList;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getGattAddedGattServiceUuidList();
                    } else {
                        _reply.readException();
                        createTypedArrayList = _reply.createTypedArrayList(ParcelUuid.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public List<ParcelUuid> getGattAddedGattCharacteristicUuidList(ParcelUuid srvcUuid) throws RemoteException {
                List<ParcelUuid> createTypedArrayList;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (srvcUuid != null) {
                        _data.writeInt(1);
                        srvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getGattAddedGattCharacteristicUuidList(srvcUuid);
                    } else {
                        _reply.readException();
                        createTypedArrayList = _reply.createTypedArrayList(ParcelUuid.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandGattServer
            public List<ParcelUuid> getGattAddedGattDescriptorUuidList(ParcelUuid srvcUuid, ParcelUuid charUuid) throws RemoteException {
                List<ParcelUuid> createTypedArrayList;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (srvcUuid != null) {
                        _data.writeInt(1);
                        srvcUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (charUuid != null) {
                        _data.writeInt(1);
                        charUuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getGattAddedGattDescriptorUuidList(srvcUuid, charUuid);
                    } else {
                        _reply.readException();
                        createTypedArrayList = _reply.createTypedArrayList(ParcelUuid.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INfCommandGattServer impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCommandGattServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
