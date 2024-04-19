package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
/* loaded from: classes.dex */
public interface INfCallbackHfp extends IInterface {
    void onHfpAudioStateChanged(String str, int i, int i2) throws RemoteException;

    void onHfpCallChanged(String str, NfHfpClientCall nfHfpClientCall) throws RemoteException;

    void onHfpErrorResponse(String str, int i) throws RemoteException;

    void onHfpRemoteBatteryIndicator(String str, int i, int i2, int i3) throws RemoteException;

    void onHfpRemoteRoamingStatus(String str, boolean z) throws RemoteException;

    void onHfpRemoteSignalStrength(String str, int i, int i2, int i3) throws RemoteException;

    void onHfpRemoteTelecomService(String str, boolean z) throws RemoteException;

    void onHfpServiceReady() throws RemoteException;

    void onHfpStateChanged(String str, int i, int i2) throws RemoteException;

    void onHfpVoiceDial(String str, boolean z) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCallbackHfp {
        @Override // com.nforetek.bt.aidl.INfCallbackHfp
        public void onHfpServiceReady() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackHfp
        public void onHfpStateChanged(String address, int prevState, int newState) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackHfp
        public void onHfpAudioStateChanged(String address, int prevState, int newState) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackHfp
        public void onHfpVoiceDial(String address, boolean isVoiceDialOn) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackHfp
        public void onHfpErrorResponse(String address, int code) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackHfp
        public void onHfpRemoteTelecomService(String address, boolean isTelecomServiceOn) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackHfp
        public void onHfpRemoteRoamingStatus(String address, boolean isRoamingOn) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackHfp
        public void onHfpRemoteBatteryIndicator(String address, int currentValue, int maxValue, int minValue) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackHfp
        public void onHfpRemoteSignalStrength(String address, int currentStrength, int maxStrength, int minStrength) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCallbackHfp
        public void onHfpCallChanged(String address, NfHfpClientCall call) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCallbackHfp {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCallbackHfp";
        static final int TRANSACTION_onHfpAudioStateChanged = 3;
        static final int TRANSACTION_onHfpCallChanged = 10;
        static final int TRANSACTION_onHfpErrorResponse = 5;
        static final int TRANSACTION_onHfpRemoteBatteryIndicator = 8;
        static final int TRANSACTION_onHfpRemoteRoamingStatus = 7;
        static final int TRANSACTION_onHfpRemoteSignalStrength = 9;
        static final int TRANSACTION_onHfpRemoteTelecomService = 6;
        static final int TRANSACTION_onHfpServiceReady = 1;
        static final int TRANSACTION_onHfpStateChanged = 2;
        static final int TRANSACTION_onHfpVoiceDial = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCallbackHfp asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCallbackHfp)) {
                return (INfCallbackHfp) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            NfHfpClientCall _arg1;
            boolean _arg12;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onHfpServiceReady();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    int _arg13 = data.readInt();
                    int _arg2 = data.readInt();
                    onHfpStateChanged(_arg0, _arg13, _arg2);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg02 = data.readString();
                    int _arg14 = data.readInt();
                    int _arg22 = data.readInt();
                    onHfpAudioStateChanged(_arg02, _arg14, _arg22);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    _arg12 = data.readInt() != 0;
                    onHfpVoiceDial(_arg03, _arg12);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    onHfpErrorResponse(_arg04, data.readInt());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    _arg12 = data.readInt() != 0;
                    onHfpRemoteTelecomService(_arg05, _arg12);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    _arg12 = data.readInt() != 0;
                    onHfpRemoteRoamingStatus(_arg06, _arg12);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    int _arg15 = data.readInt();
                    int _arg23 = data.readInt();
                    int _arg3 = data.readInt();
                    onHfpRemoteBatteryIndicator(_arg07, _arg15, _arg23, _arg3);
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    int _arg16 = data.readInt();
                    int _arg24 = data.readInt();
                    int _arg32 = data.readInt();
                    onHfpRemoteSignalStrength(_arg08, _arg16, _arg24, _arg32);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg09 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = NfHfpClientCall.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    onHfpCallChanged(_arg09, _arg1);
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
        public static class Proxy implements INfCallbackHfp {
            public static INfCallbackHfp sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCallbackHfp
            public void onHfpServiceReady() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHfpServiceReady();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackHfp
            public void onHfpStateChanged(String address, int prevState, int newState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(prevState);
                    _data.writeInt(newState);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHfpStateChanged(address, prevState, newState);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackHfp
            public void onHfpAudioStateChanged(String address, int prevState, int newState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(prevState);
                    _data.writeInt(newState);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHfpAudioStateChanged(address, prevState, newState);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackHfp
            public void onHfpVoiceDial(String address, boolean isVoiceDialOn) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(isVoiceDialOn ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHfpVoiceDial(address, isVoiceDialOn);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackHfp
            public void onHfpErrorResponse(String address, int code) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(code);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHfpErrorResponse(address, code);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackHfp
            public void onHfpRemoteTelecomService(String address, boolean isTelecomServiceOn) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(isTelecomServiceOn ? 1 : 0);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHfpRemoteTelecomService(address, isTelecomServiceOn);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackHfp
            public void onHfpRemoteRoamingStatus(String address, boolean isRoamingOn) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(isRoamingOn ? 1 : 0);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHfpRemoteRoamingStatus(address, isRoamingOn);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackHfp
            public void onHfpRemoteBatteryIndicator(String address, int currentValue, int maxValue, int minValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(currentValue);
                    _data.writeInt(maxValue);
                    _data.writeInt(minValue);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHfpRemoteBatteryIndicator(address, currentValue, maxValue, minValue);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackHfp
            public void onHfpRemoteSignalStrength(String address, int currentStrength, int maxStrength, int minStrength) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    _data.writeInt(currentStrength);
                    _data.writeInt(maxStrength);
                    _data.writeInt(minStrength);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHfpRemoteSignalStrength(address, currentStrength, maxStrength, minStrength);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCallbackHfp
            public void onHfpCallChanged(String address, NfHfpClientCall call) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    if (call != null) {
                        _data.writeInt(1);
                        call.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onHfpCallChanged(address, call);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INfCallbackHfp impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCallbackHfp getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
