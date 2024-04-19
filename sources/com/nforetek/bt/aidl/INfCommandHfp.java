package com.nforetek.bt.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackHfp;
import java.util.List;
/* loaded from: classes.dex */
public interface INfCommandHfp extends IInterface {
    int getHfpAudioConnectionState() throws RemoteException;

    List<NfHfpClientCall> getHfpCallList() throws RemoteException;

    String getHfpConnectedAddress() throws RemoteException;

    int getHfpConnectionState() throws RemoteException;

    int getHfpRemoteBatteryIndicator() throws RemoteException;

    String getHfpRemoteNetworkOperator() throws RemoteException;

    int getHfpRemoteSignalStrength() throws RemoteException;

    String getHfpRemoteSubscriberNumber() throws RemoteException;

    boolean isHfpConnected() throws RemoteException;

    boolean isHfpInBandRingtoneSupport() throws RemoteException;

    boolean isHfpMicMute() throws RemoteException;

    boolean isHfpRemoteOnRoaming() throws RemoteException;

    boolean isHfpRemoteTelecomServiceOn() throws RemoteException;

    boolean isHfpRemoteVoiceDialOn() throws RemoteException;

    boolean isHfpServiceReady() throws RemoteException;

    void muteHfpMic(boolean z) throws RemoteException;

    void pauseHfpRender() throws RemoteException;

    boolean registerHfpCallback(INfCallbackHfp iNfCallbackHfp) throws RemoteException;

    boolean reqHfpAnswerCall(int i) throws RemoteException;

    boolean reqHfpAudioTransferToCarkit() throws RemoteException;

    boolean reqHfpAudioTransferToPhone() throws RemoteException;

    boolean reqHfpConnect(String str) throws RemoteException;

    boolean reqHfpDialCall(String str) throws RemoteException;

    boolean reqHfpDisconnect(String str) throws RemoteException;

    boolean reqHfpMemoryDial(String str) throws RemoteException;

    boolean reqHfpReDial() throws RemoteException;

    boolean reqHfpRejectIncomingCall() throws RemoteException;

    boolean reqHfpSendDtmf(String str) throws RemoteException;

    boolean reqHfpTerminateCurrentCall() throws RemoteException;

    boolean reqHfpVoiceDial(boolean z) throws RemoteException;

    void startHfpRender() throws RemoteException;

    boolean unregisterHfpCallback(INfCallbackHfp iNfCallbackHfp) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements INfCommandHfp {
        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpServiceReady() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean registerHfpCallback(INfCallbackHfp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean unregisterHfpCallback(INfCallbackHfp cb) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public int getHfpConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpConnected() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public String getHfpConnectedAddress() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public int getHfpAudioConnectionState() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpConnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpDisconnect(String address) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public int getHfpRemoteSignalStrength() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public List<NfHfpClientCall> getHfpCallList() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpRemoteOnRoaming() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public int getHfpRemoteBatteryIndicator() throws RemoteException {
            return 0;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpRemoteTelecomServiceOn() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpRemoteVoiceDialOn() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpDialCall(String number) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpReDial() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpMemoryDial(String index) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpAnswerCall(int flag) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpRejectIncomingCall() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpTerminateCurrentCall() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpSendDtmf(String number) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpAudioTransferToCarkit() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpAudioTransferToPhone() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public String getHfpRemoteNetworkOperator() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public String getHfpRemoteSubscriberNumber() throws RemoteException {
            return null;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean reqHfpVoiceDial(boolean enable) throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public void pauseHfpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public void startHfpRender() throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpMicMute() throws RemoteException {
            return false;
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public void muteHfpMic(boolean mute) throws RemoteException {
        }

        @Override // com.nforetek.bt.aidl.INfCommandHfp
        public boolean isHfpInBandRingtoneSupport() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements INfCommandHfp {
        private static final String DESCRIPTOR = "com.nforetek.bt.aidl.INfCommandHfp";
        static final int TRANSACTION_getHfpAudioConnectionState = 7;
        static final int TRANSACTION_getHfpCallList = 11;
        static final int TRANSACTION_getHfpConnectedAddress = 6;
        static final int TRANSACTION_getHfpConnectionState = 4;
        static final int TRANSACTION_getHfpRemoteBatteryIndicator = 13;
        static final int TRANSACTION_getHfpRemoteNetworkOperator = 25;
        static final int TRANSACTION_getHfpRemoteSignalStrength = 10;
        static final int TRANSACTION_getHfpRemoteSubscriberNumber = 26;
        static final int TRANSACTION_isHfpConnected = 5;
        static final int TRANSACTION_isHfpInBandRingtoneSupport = 32;
        static final int TRANSACTION_isHfpMicMute = 30;
        static final int TRANSACTION_isHfpRemoteOnRoaming = 12;
        static final int TRANSACTION_isHfpRemoteTelecomServiceOn = 14;
        static final int TRANSACTION_isHfpRemoteVoiceDialOn = 15;
        static final int TRANSACTION_isHfpServiceReady = 1;
        static final int TRANSACTION_muteHfpMic = 31;
        static final int TRANSACTION_pauseHfpRender = 28;
        static final int TRANSACTION_registerHfpCallback = 2;
        static final int TRANSACTION_reqHfpAnswerCall = 19;
        static final int TRANSACTION_reqHfpAudioTransferToCarkit = 23;
        static final int TRANSACTION_reqHfpAudioTransferToPhone = 24;
        static final int TRANSACTION_reqHfpConnect = 8;
        static final int TRANSACTION_reqHfpDialCall = 16;
        static final int TRANSACTION_reqHfpDisconnect = 9;
        static final int TRANSACTION_reqHfpMemoryDial = 18;
        static final int TRANSACTION_reqHfpReDial = 17;
        static final int TRANSACTION_reqHfpRejectIncomingCall = 20;
        static final int TRANSACTION_reqHfpSendDtmf = 22;
        static final int TRANSACTION_reqHfpTerminateCurrentCall = 21;
        static final int TRANSACTION_reqHfpVoiceDial = 27;
        static final int TRANSACTION_startHfpRender = 29;
        static final int TRANSACTION_unregisterHfpCallback = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INfCommandHfp asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INfCommandHfp)) {
                return (INfCommandHfp) iin;
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
                    boolean _result = isHfpServiceReady();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackHfp _arg0 = INfCallbackHfp.Stub.asInterface(data.readStrongBinder());
                    boolean _result2 = registerHfpCallback(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result2 ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    INfCallbackHfp _arg02 = INfCallbackHfp.Stub.asInterface(data.readStrongBinder());
                    boolean _result3 = unregisterHfpCallback(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result3 ? 1 : 0);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = getHfpConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result5 = isHfpConnected();
                    reply.writeNoException();
                    reply.writeInt(_result5 ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String _result6 = getHfpConnectedAddress();
                    reply.writeNoException();
                    reply.writeString(_result6);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _result7 = getHfpAudioConnectionState();
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    boolean _result8 = reqHfpConnect(_arg03);
                    reply.writeNoException();
                    reply.writeInt(_result8 ? 1 : 0);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    boolean _result9 = reqHfpDisconnect(_arg04);
                    reply.writeNoException();
                    reply.writeInt(_result9 ? 1 : 0);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _result10 = getHfpRemoteSignalStrength();
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    List<NfHfpClientCall> _result11 = getHfpCallList();
                    reply.writeNoException();
                    reply.writeTypedList(_result11);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result12 = isHfpRemoteOnRoaming();
                    reply.writeNoException();
                    reply.writeInt(_result12 ? 1 : 0);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int _result13 = getHfpRemoteBatteryIndicator();
                    reply.writeNoException();
                    reply.writeInt(_result13);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result14 = isHfpRemoteTelecomServiceOn();
                    reply.writeNoException();
                    reply.writeInt(_result14 ? 1 : 0);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result15 = isHfpRemoteVoiceDialOn();
                    reply.writeNoException();
                    reply.writeInt(_result15 ? 1 : 0);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    boolean _result16 = reqHfpDialCall(_arg05);
                    reply.writeNoException();
                    reply.writeInt(_result16 ? 1 : 0);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result17 = reqHfpReDial();
                    reply.writeNoException();
                    reply.writeInt(_result17 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpMemoryDial /* 18 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    boolean _result18 = reqHfpMemoryDial(_arg06);
                    reply.writeNoException();
                    reply.writeInt(_result18 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpAnswerCall /* 19 */:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg07 = data.readInt();
                    boolean _result19 = reqHfpAnswerCall(_arg07);
                    reply.writeNoException();
                    reply.writeInt(_result19 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpRejectIncomingCall /* 20 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result20 = reqHfpRejectIncomingCall();
                    reply.writeNoException();
                    reply.writeInt(_result20 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpTerminateCurrentCall /* 21 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result21 = reqHfpTerminateCurrentCall();
                    reply.writeNoException();
                    reply.writeInt(_result21 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpSendDtmf /* 22 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    boolean _result22 = reqHfpSendDtmf(_arg08);
                    reply.writeNoException();
                    reply.writeInt(_result22 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpAudioTransferToCarkit /* 23 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result23 = reqHfpAudioTransferToCarkit();
                    reply.writeNoException();
                    reply.writeInt(_result23 ? 1 : 0);
                    return true;
                case TRANSACTION_reqHfpAudioTransferToPhone /* 24 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result24 = reqHfpAudioTransferToPhone();
                    reply.writeNoException();
                    reply.writeInt(_result24 ? 1 : 0);
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    String _result25 = getHfpRemoteNetworkOperator();
                    reply.writeNoException();
                    reply.writeString(_result25);
                    return true;
                case TRANSACTION_getHfpRemoteSubscriberNumber /* 26 */:
                    data.enforceInterface(DESCRIPTOR);
                    String _result26 = getHfpRemoteSubscriberNumber();
                    reply.writeNoException();
                    reply.writeString(_result26);
                    return true;
                case TRANSACTION_reqHfpVoiceDial /* 27 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg09 = data.readInt() != 0;
                    boolean _result27 = reqHfpVoiceDial(_arg09);
                    reply.writeNoException();
                    reply.writeInt(_result27 ? 1 : 0);
                    return true;
                case TRANSACTION_pauseHfpRender /* 28 */:
                    data.enforceInterface(DESCRIPTOR);
                    pauseHfpRender();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_startHfpRender /* 29 */:
                    data.enforceInterface(DESCRIPTOR);
                    startHfpRender();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_isHfpMicMute /* 30 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result28 = isHfpMicMute();
                    reply.writeNoException();
                    reply.writeInt(_result28 ? 1 : 0);
                    return true;
                case TRANSACTION_muteHfpMic /* 31 */:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg010 = data.readInt() != 0;
                    muteHfpMic(_arg010);
                    reply.writeNoException();
                    return true;
                case 32:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result29 = isHfpInBandRingtoneSupport();
                    reply.writeNoException();
                    reply.writeInt(_result29 ? 1 : 0);
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
        public static class Proxy implements INfCommandHfp {
            public static INfCommandHfp sDefaultImpl;
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean isHfpServiceReady() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpServiceReady();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean registerHfpCallback(INfCallbackHfp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().registerHfpCallback(cb);
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean unregisterHfpCallback(INfCallbackHfp cb) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().unregisterHfpCallback(cb);
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public int getHfpConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHfpConnectionState();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean isHfpConnected() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpConnected();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public String getHfpConnectedAddress() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getHfpConnectedAddress();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public int getHfpAudioConnectionState() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHfpAudioConnectionState();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpConnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpConnect(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpDisconnect(String address) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(address);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpDisconnect(address);
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public int getHfpRemoteSignalStrength() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHfpRemoteSignalStrength();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public List<NfHfpClientCall> getHfpCallList() throws RemoteException {
                List<NfHfpClientCall> createTypedArrayList;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getHfpCallList();
                    } else {
                        _reply.readException();
                        createTypedArrayList = _reply.createTypedArrayList(NfHfpClientCall.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean isHfpRemoteOnRoaming() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpRemoteOnRoaming();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public int getHfpRemoteBatteryIndicator() throws RemoteException {
                int readInt;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getHfpRemoteBatteryIndicator();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean isHfpRemoteTelecomServiceOn() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpRemoteTelecomServiceOn();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean isHfpRemoteVoiceDialOn() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpRemoteVoiceDialOn();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpDialCall(String number) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpDialCall(number);
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpReDial() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpReDial();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpMemoryDial(String index) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(index);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpMemoryDial, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpMemoryDial(index);
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpAnswerCall(int flag) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flag);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpAnswerCall, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpAnswerCall(flag);
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpRejectIncomingCall() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpRejectIncomingCall, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpRejectIncomingCall();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpTerminateCurrentCall() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpTerminateCurrentCall, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpTerminateCurrentCall();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpSendDtmf(String number) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpSendDtmf, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpSendDtmf(number);
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpAudioTransferToCarkit() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpAudioTransferToCarkit, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpAudioTransferToCarkit();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpAudioTransferToPhone() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpAudioTransferToPhone, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpAudioTransferToPhone();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public String getHfpRemoteNetworkOperator() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getHfpRemoteNetworkOperator();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public String getHfpRemoteSubscriberNumber() throws RemoteException {
                String readString;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getHfpRemoteSubscriberNumber, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getHfpRemoteSubscriberNumber();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean reqHfpVoiceDial(boolean enable) throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqHfpVoiceDial, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().reqHfpVoiceDial(enable);
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public void pauseHfpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_pauseHfpRender, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pauseHfpRender();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public void startHfpRender() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_startHfpRender, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startHfpRender();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean isHfpMicMute() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isHfpMicMute, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpMicMute();
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

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public void muteHfpMic(boolean mute) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mute ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_muteHfpMic, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().muteHfpMic(mute);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.nforetek.bt.aidl.INfCommandHfp
            public boolean isHfpInBandRingtoneSupport() throws RemoteException {
                boolean _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        _result = Stub.getDefaultImpl().isHfpInBandRingtoneSupport();
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

        public static boolean setDefaultImpl(INfCommandHfp impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INfCommandHfp getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
