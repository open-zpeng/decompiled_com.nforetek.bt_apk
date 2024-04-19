package com.nforetek.bt.callback;

import android.os.Bundle;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackGattServer;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public final class NfDoCallbackGattServer extends NfDoCallback<INfCallbackGattServer> {
    private final int onGattServerStateChanged = 2;
    private final int onGattServerServiceAdded = 3;
    private final int onGattServerServiceDeleted = 4;
    private final int onGattServerCharacteristicReadRequest = 5;
    private final int onGattServerDescriptorReadRequest = 6;
    private final int onGattServerCharacteristicWriteRequest = 7;
    private final int onGattServerDescriptorWriteRequest = 8;
    private final int onGattServerExecuteWrite = 9;

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected String getLogTag() {
        return "NfDoCallbackGattServer";
    }

    public void onGattServerStateChanged(String address, int state) {
        NfLog.d(this.TAG, "onGattServerStateChanged()");
        Message m = Message.obtain(this.mHandler, 2);
        Bundle b = new Bundle();
        m.obj = address;
        b.putInt("state", state);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onGattServerServiceAdded(int status, int srvcType, int srvcInstId, ParcelUuid srvcId) {
        NfLog.d(this.TAG, "onGattServerServiceAdded()");
        Message m = Message.obtain(this.mHandler, 3);
        Bundle b = new Bundle();
        b.putInt("status", status);
        b.putInt("srvcInstId", srvcInstId);
        b.putParcelable("srvcId", srvcId);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onGattServerServiceDeleted(int status, int srvcType, int srvcInstId, ParcelUuid srvcId) {
        NfLog.d(this.TAG, "onGattServerServiceDeleted()");
        Message m = Message.obtain(this.mHandler, 4);
        Bundle b = new Bundle();
        b.putInt("status", status);
        b.putInt("srvcInstId", srvcInstId);
        b.putParcelable("srvcId", srvcId);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onGattServerCharacteristicReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId) {
        NfLog.d(this.TAG, "onGattServerCharacteristicReadRequest()");
        Message m = Message.obtain(this.mHandler, 5);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("transId", transId);
        b.putInt("offset", offset);
        b.putBoolean("isLong", isLong);
        b.putInt("srvcType", srvcType);
        b.putInt("srvcInstId", srvcInstId);
        b.putParcelable("srvcId", srvcId);
        b.putInt("charInstId", charInstId);
        b.putParcelable("charId", charId);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onGattServerDescriptorReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId) {
        NfLog.d(this.TAG, "onGattServerDescriptorReadRequest()");
        Message m = Message.obtain(this.mHandler, 6);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("transId", transId);
        b.putInt("offset", offset);
        b.putBoolean("isLong", isLong);
        b.putInt("srvcType", srvcType);
        b.putInt("srvcInstId", srvcInstId);
        b.putParcelable("srvcId", srvcId);
        b.putInt("charInstId", charInstId);
        b.putParcelable("charId", charId);
        b.putParcelable("descrId", descrId);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onGattServerCharacteristicWriteRequest(String address, int transId, int offset, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, byte[] value) {
        NfLog.d(this.TAG, "onGattServerCharacteristicWriteRequest()");
        Message m = Message.obtain(this.mHandler, 7);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("transId", transId);
        b.putInt("offset", offset);
        b.putBoolean("isPrep", isPrep);
        b.putBoolean("needRsp", needRsp);
        b.putInt("srvcType", srvcType);
        b.putInt("srvcInstId", srvcInstId);
        b.putParcelable("srvcId", srvcId);
        b.putInt("charInstId", charInstId);
        b.putParcelable("charId", charId);
        b.putByteArray("value", value);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onGattServerDescriptorWriteRequest(String address, int transId, int offset, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId, byte[] value) {
        NfLog.d(this.TAG, "onGattServerDescriptorWriteRequest()");
        Message m = Message.obtain(this.mHandler, 8);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("transId", transId);
        b.putInt("offset", offset);
        b.putBoolean("isPrep", isPrep);
        b.putBoolean("needRsp", needRsp);
        b.putInt("srvcType", srvcType);
        b.putInt("srvcInstId", srvcInstId);
        b.putParcelable("srvcId", srvcId);
        b.putInt("charInstId", charInstId);
        b.putParcelable("charId", charId);
        b.putParcelable("descrId", descrId);
        b.putByteArray("value", value);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onGattServerExecuteWrite(String address, int transId, boolean execWrite) {
        NfLog.d(this.TAG, "onGattServerExecuteWrite()");
        Message m = Message.obtain(this.mHandler, 9);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("transId", transId);
        b.putBoolean("execWrite", execWrite);
        m.setData(b);
        enqueueMessage(m);
    }

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected void dequeueMessage(Message msg) {
        Bundle b = msg.getData();
        String address = (String) msg.obj;
        if (NfConfig.isCallbackByAidl()) {
            switch (msg.what) {
                case 2:
                    callbackOnGattServerStateChanged(address, b.getInt("state"));
                    return;
                case 3:
                    callbackOnGattServerServiceAdded(b.getInt("status"), b.getInt("srvcType"), b.getInt("srvcInstId"), (ParcelUuid) b.getParcelable("srvcId"));
                    return;
                case 4:
                    callbackOnGattServerServiceDeleted(b.getInt("status"), b.getInt("srvcType"), b.getInt("srvcInstId"), (ParcelUuid) b.getParcelable("srvcId"));
                    return;
                case 5:
                    callbackOnGattServerCharacteristicReadRequest(address, b.getInt("transId"), b.getInt("offset"), b.getBoolean("isLong"), b.getInt("srvcType"), b.getInt("srvcInstId"), (ParcelUuid) b.getParcelable("srvcId"), b.getInt("charInstId"), (ParcelUuid) b.getParcelable("charId"));
                    return;
                case 6:
                    callbackOnGattServerDescriptorReadRequest(address, b.getInt("transId"), b.getInt("offset"), b.getBoolean("isLong"), b.getInt("srvcType"), b.getInt("srvcInstId"), (ParcelUuid) b.getParcelable("srvcId"), b.getInt("charInstId"), (ParcelUuid) b.getParcelable("charId"), (ParcelUuid) b.getParcelable("descrId"));
                    return;
                case 7:
                    callbackOnGattServerCharacteristicWriteRequest(address, b.getInt("transId"), b.getInt("offset"), b.getBoolean("isPrep"), b.getBoolean("needRsp"), b.getInt("srvcType"), b.getInt("srvcInstId"), (ParcelUuid) b.getParcelable("srvcId"), b.getInt("charInstId"), (ParcelUuid) b.getParcelable("charId"), b.getByteArray("value"));
                    return;
                case 8:
                    callbackOnGattServerDescriptorWriteRequest(address, b.getInt("transId"), b.getInt("offset"), b.getBoolean("isPrep"), b.getBoolean("needRsp"), b.getInt("srvcType"), b.getInt("srvcInstId"), (ParcelUuid) b.getParcelable("srvcId"), b.getInt("charInstId"), (ParcelUuid) b.getParcelable("charId"), (ParcelUuid) b.getParcelable("descrId"), b.getByteArray("value"));
                    return;
                case 9:
                    callbackOnGattServerExecuteWrite(address, b.getInt("transId"), b.getBoolean("execWrite"));
                    return;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.callback.NfDoCallback
    public synchronized void callbackOnServiceReady(INfCallbackGattServer cb) throws RemoteException {
        cb.onGattServiceReady();
    }

    private synchronized void callbackOnGattServerStateChanged(String address, int state) {
        NfLog.v(this.TAG, "callbackOnGattServerStateChanged()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onGattServerStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackGattServer) this.mRemoteCallbacks.getBroadcastItem(i)).onGattServerStateChanged(address, state);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onGattServerStateChanged CallBack Finish.");
    }

    private synchronized void callbackOnGattServerServiceAdded(int status, int srvcType, int srvcInstId, ParcelUuid srvcId) {
        NfLog.v(this.TAG, "callbackOnGattServerServiceAdded()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onGattServerServiceAdded beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackGattServer) this.mRemoteCallbacks.getBroadcastItem(i)).onGattServerServiceAdded(status, srvcType, srvcId);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onGattServerServiceAdded CallBack Finish.");
    }

    private synchronized void callbackOnGattServerServiceDeleted(int status, int srvcType, int srvcInstId, ParcelUuid srvcId) {
        NfLog.v(this.TAG, "callbackOnGattServerServiceDeleted()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onGattServerServiceDeleted beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackGattServer) this.mRemoteCallbacks.getBroadcastItem(i)).onGattServerServiceDeleted(status, srvcType, srvcId);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onGattServerServiceDeleted CallBack Finish.");
    }

    private synchronized void callbackOnGattServerCharacteristicReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId) {
        NfLog.v(this.TAG, "callbackOnGattServerCharacteristicReadRequest()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onGattServerCharacteristicReadRequest beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    try {
                        ((INfCallbackGattServer) this.mRemoteCallbacks.getBroadcastItem(i)).onGattServerCharacteristicReadRequest(address, transId, offset, isLong, srvcType, srvcId, charId);
                    } catch (NullPointerException e) {
                        checkCallbacksValid(i);
                    }
                } catch (RemoteException e2) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onGattServerCharacteristicReadRequest CallBack Finish.");
    }

    private synchronized void callbackOnGattServerDescriptorReadRequest(String address, int transId, int offset, boolean isLong, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId) {
        NfLog.v(this.TAG, "callbackOnGattServerDescriptorReadRequest()");
        NfLog.v(this.TAG, "charId: " + charId);
        NfLog.v(this.TAG, "descrId: " + descrId);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onGattServerDescriptorReadRequest beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    try {
                        ((INfCallbackGattServer) this.mRemoteCallbacks.getBroadcastItem(i)).onGattServerDescriptorReadRequest(address, transId, offset, isLong, srvcType, srvcId, charId, descrId);
                    } catch (NullPointerException e) {
                        checkCallbacksValid(i);
                    }
                } catch (RemoteException e2) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onGattServerDescriptorReadRequest CallBack Finish.");
    }

    private synchronized void callbackOnGattServerCharacteristicWriteRequest(String address, int transId, int offset, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, byte[] value) {
        NfLog.v(this.TAG, "callbackOnGattServerCharacteristicWriteRequest()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onGattServerCharacteristicWriteRequest beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackGattServer) this.mRemoteCallbacks.getBroadcastItem(i)).onGattServerCharacteristicWriteRequest(address, transId, offset, isPrep, needRsp, srvcType, srvcId, charId, value);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onGattServerCharacteristicWriteRequest CallBack Finish.");
    }

    private synchronized void callbackOnGattServerDescriptorWriteRequest(String address, int transId, int offset, boolean isPrep, boolean needRsp, int srvcType, int srvcInstId, ParcelUuid srvcId, int charInstId, ParcelUuid charId, ParcelUuid descrId, byte[] value) {
        NfLog.v(this.TAG, "callbackOnGattServerDescriptorWriteRequest()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onGattServerDescriptorWriteRequest beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackGattServer) this.mRemoteCallbacks.getBroadcastItem(i)).onGattServerDescriptorWriteRequest(address, transId, offset, isPrep, needRsp, srvcType, srvcId, charId, descrId, value);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onGattServerDescriptorWriteRequest CallBack Finish.");
    }

    private synchronized void callbackOnGattServerExecuteWrite(String address, int transId, boolean execWrite) {
        NfLog.v(this.TAG, "callbackOnGattServerExecuteWrite()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onGattServerExecuteWrite beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackGattServer) this.mRemoteCallbacks.getBroadcastItem(i)).onGattServerExecuteWrite(address, transId, execWrite);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onGattServerExecuteWrite CallBack Finish.");
    }
}
