package com.nforetek.bt.profile.gatt;

import android.bluetooth.BluetoothDevice;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackGattServer;
import com.nforetek.bt.callback.NfDoCallbackGattServer;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.jni.NfJniBundle;
import com.nforetek.bt.jni.NfJniCallbackInterfaceGatt;
import com.nforetek.bt.profile.NfGattCallbackInterface;
import com.nforetek.bt.profile._NfProfile;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class _NfGatt extends _NfProfile<INfCallbackGattServer, NfDoCallbackGattServer, NfGattCallbackInterface> implements NfJniCallbackInterfaceGatt {
    private final boolean SPLIT_NOTIFY_PACKAET = false;
    private boolean mIsRequestListening = false;

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfGatt";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return 9;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
        NfLog.i(this.TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onCreate() {
        NfLog.i(this.TAG, "onCreate");
        super.onCreate();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
        super.forceResetState();
    }

    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) throws RemoteException {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
            return null;
        } else if (jni() == null) {
            return new ArrayList();
        } else {
            return null;
        }
    }

    public void registerClient(ParcelUuid uuid) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void unregisterClient(int clientIf) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void startScan(int appIf, boolean isServer) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void startScanWithUuids(int appIf, boolean isServer, ParcelUuid[] ids) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void stopScan(int appIf, boolean isServer) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void clientConnect(int clientIf, String address, boolean isDirect) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void clientDisconnect(int clientIf, String address) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void refreshDevice(int clientIf, String address) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void discoverServices(int clientIf, String address) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void readCharacteristic(int clientIf, String address, int srvcType, int srvcInstanceId, ParcelUuid srvcId, int charInstanceId, ParcelUuid charId, int authReq) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void writeCharacteristic(int clientIf, String address, int srvcType, int srvcInstanceId, ParcelUuid srvcId, int charInstanceId, ParcelUuid charId, int writeType, int authReq, byte[] value) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void readDescriptor(int clientIf, String address, int srvcType, int srvcInstanceId, ParcelUuid srvcId, int charInstanceId, ParcelUuid charId, int descrInstanceId, ParcelUuid descrId, int authReq) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void writeDescriptor(int clientIf, String address, int srvcType, int srvcInstanceId, ParcelUuid srvcId, int charInstanceId, ParcelUuid charId, int descrInstanceId, ParcelUuid descrId, int writeType, int authReq, byte[] value) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void beginReliableWrite(int clientIf, String address) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void endReliableWrite(int clientIf, String address, boolean execute) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void registerForNotification(int clientIf, String address, int srvcType, int srvcInstanceId, ParcelUuid srvcId, int charInstanceId, ParcelUuid charId, boolean enable) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    public void readRemoteRssi(int clientIf, String address) {
        if (NfPrimitive.isAddressValid(address) && jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
        callback().onGattServerStateChanged(address, state);
    }

    public boolean reqGattServerListen(boolean listen) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
            return false;
        }
        this.mIsRequestListening = listen;
        jni().reqGattServerListen(listen);
        return true;
    }

    public boolean beginServiceDeclaration(int srvcType, ParcelUuid srvcId) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
            return false;
        }
        jni().beginGattServiceDeclaration(srvcType, 0, 0, srvcId);
        return true;
    }

    public boolean addCharacteristic(ParcelUuid charId, int properties, int permissions) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
            return false;
        }
        jni().reqGattAddCharacteristic(charId, properties, permissions);
        return true;
    }

    public boolean addDescriptor(ParcelUuid descId, int permissions) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
            return false;
        }
        jni().reqGattAddDescriptor(descId, permissions);
        return true;
    }

    public boolean endServiceDeclaration() {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
            return false;
        }
        jni().endGattServiceDeclaration();
        return true;
    }

    public boolean removeService(int srvcType, ParcelUuid srvcId) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
            return false;
        }
        jni().reqGattRemoveService(srvcType, 0, srvcId);
        return true;
    }

    public boolean clearServices() {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
            return false;
        }
        jni().reqGattClearServices();
        return true;
    }

    public boolean sendResponse(String address, int requestId, int status, int offset, byte[] value) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
            return false;
        }
        jni().reqGattSendResponse(address, requestId, status, offset, value);
        return true;
    }

    public boolean sendNotification(String address, int srvcType, ParcelUuid srvcUuid, ParcelUuid charUuid, boolean confirm, byte[] value) {
        if (jni() == null) {
            NfLog.e(this.TAG, "jni() is null !");
            return false;
        } else if (value.length > 20) {
            NfLog.e(this.TAG, "value length over 20 bytes!");
            return false;
        } else {
            jni().reqGattSendNotification(address, srvcType, 0, srvcUuid, 0, charUuid, confirm, value);
            return true;
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceGatt
    public void onJniGattServerServerRegistered(int status) {
        NfLog.v(this.TAG, "onJniGattServerServerRegistered() status: " + status);
        if (status == 0) {
            setState(NfDef.DEFAULT_ADDRESS, 110);
        } else if (status == 1) {
            setState(NfDef.DEFAULT_ADDRESS, 100);
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceGatt
    public void onJniGattServerScanResult(String address, int rssi, byte[] advData) {
        NfLog.v(this.TAG, "onJniGattServerScanResult()");
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceGatt
    public void onJniGattServerConnectionState(String address, int status, boolean connected) {
        NfLog.v(this.TAG, "onJniGattServerConnectionState() " + address + " status: " + status);
        if (connected) {
            setState(address, 140);
        } else if (this.mIsRequestListening) {
            setState(address, 130);
        } else {
            setState(address, 110);
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceGatt
    public void onJniGattServerServiceAdded(int status, int srvcType, int srvcInstId, ParcelUuid srvcId) {
        NfLog.v(this.TAG, "onJniGattServerServiceAdded() " + srvcId);
        callback().onGattServerServiceAdded(status, srvcType, srvcInstId, srvcId);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceGatt
    public void onJniGattServerCharacteristicReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId) {
        NfLog.v(this.TAG, "onJniGattServerCharacteristicReadRequest()");
        callback().onGattServerCharacteristicReadRequest(address, transId, offset, isLong, srvcType, srvcInstId, srvcId, charInstId, charId);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceGatt
    public void onJniGattServerDescriptorReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId) {
        NfLog.v(this.TAG, "onJniGattServerDescriptorReadRequest()");
        callback().onGattServerDescriptorReadRequest(address, transId, offset, isLong, srvcType, srvcInstId, srvcId, charInstId, charId, descrId);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceGatt
    public void onJniGattServerCharacteristicWriteRequest(String address, int transId, int offset, int length, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, byte[] value) {
        NfLog.v(this.TAG, "onJniGattServerCharacteristicWriteRequest()");
        callback().onGattServerCharacteristicWriteRequest(address, transId, offset, isPrep, needRsp, srvcType, srvcInstId, srvcId, charInstId, charId, value);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceGatt
    public void onJniGattServerDescriptorWriteRequest(String address, int transId, int offset, int length, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId, byte[] value) {
        NfLog.v(this.TAG, "onJniGattServerDescriptorWriteRequest()");
        callback().onGattServerDescriptorWriteRequest(address, transId, offset, isPrep, needRsp, srvcType, srvcInstId, srvcId, charInstId, charId, descrId, value);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceGatt
    public void onJniGattServerExecuteWrite(String address, int transId, boolean execWrite) {
        NfLog.v(this.TAG, "onJniGattServerExecuteWrite()");
        callback().onGattServerExecuteWrite(address, transId, execWrite);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceGatt
    public void onJniGattListen(int status) {
        NfLog.v(this.TAG, "onJniGattListen() " + status);
        if (status == 0) {
            this.mIsRequestListening = true;
            setState(NfPrimitive.getBtAddress(), 130);
            return;
        }
        this.mIsRequestListening = false;
        if (getProfileState() != 140 && getProfileState() == 130) {
            setState(NfPrimitive.getBtAddress(), 110);
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void dequeueMessage(Message msg) {
        NfJniBundle b = (NfJniBundle) msg.obj;
        switch (msg.what) {
            case NfJni.onJniGattServerServerRegistered /* 801 */:
                NfLog.v(this.TAG, "onJniGattServerServerRegistered()");
                onJniGattServerServerRegistered(b.getInt1());
                return;
            case NfJni.onJniGattServerScanResult /* 802 */:
                NfLog.v(this.TAG, "onJniGattServerScanResult()");
                onJniGattServerScanResult(b.getString1(), b.getInt1(), b.getByteArray1());
                return;
            case NfJni.onJniGattServerConnectionState /* 803 */:
                NfLog.v(this.TAG, "onJniGattServerConnectionState()");
                onJniGattServerConnectionState(b.getString1(), b.getInt1(), b.getBoolean1());
                return;
            case NfJni.onJniGattServerServiceAdded /* 804 */:
                NfLog.v(this.TAG, "onJniGattServerServiceAdded()");
                onJniGattServerServiceAdded(b.getInt1(), b.getInt2(), b.getInt3(), b.getUuid1());
                return;
            case NfJni.onJniGattServerCharacteristicReadRequest /* 805 */:
                NfLog.v(this.TAG, "onJniGattServerCharacteristicReadRequest()");
                onJniGattServerCharacteristicReadRequest(b.getString1(), b.getInt1(), b.getInt2(), b.getBoolean1(), b.getInt3(), b.getInt4(), b.getUuid1(), b.getInt5(), b.getUuid2());
                return;
            case NfJni.onJniGattServerDescriptorReadRequest /* 806 */:
                NfLog.v(this.TAG, "onJniGattServerDescriptorReadRequest()");
                onJniGattServerDescriptorReadRequest(b.getString1(), b.getInt1(), b.getInt2(), b.getBoolean1(), b.getInt3(), b.getInt4(), b.getUuid1(), b.getInt5(), b.getUuid2(), b.getUuid3());
                return;
            case NfJni.onJniGattServerCharacteristicWriteRequest /* 807 */:
                NfLog.v(this.TAG, "onJniGattServerCharacteristicWriteRequest()");
                onJniGattServerCharacteristicWriteRequest(b.getString1(), b.getInt1(), b.getInt2(), b.getInt3(), b.getBoolean1(), b.getBoolean2(), b.getInt4(), b.getInt5(), b.getUuid1(), b.getInt6(), b.getUuid2(), b.getByteArray1());
                return;
            case NfJni.onJniGattServerDescriptorWriteRequest /* 808 */:
                NfLog.v(this.TAG, "onJniGattServerDescriptorWriteRequest()");
                onJniGattServerDescriptorWriteRequest(b.getString1(), b.getInt1(), b.getInt2(), b.getInt3(), b.getBoolean1(), b.getBoolean2(), b.getInt4(), b.getInt5(), b.getUuid1(), b.getInt6(), b.getUuid2(), b.getUuid3(), b.getByteArray1());
                return;
            case NfJni.onJniGattServerExecuteWrite /* 809 */:
                NfLog.v(this.TAG, "onJniGattServerExecuteWrite()");
                onJniGattServerExecuteWrite(b.getString1(), b.getInt1(), b.getBoolean1());
                return;
            case NfJni.onJniGattListen /* 901 */:
                NfLog.v(this.TAG, "onJniGattListen()");
                onJniGattListen(b.getInt1());
                return;
            default:
                return;
        }
    }
}
