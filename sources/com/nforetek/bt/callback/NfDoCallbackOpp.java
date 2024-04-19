package com.nforetek.bt.callback;

import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackOpp;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public final class NfDoCallbackOpp extends NfDoCallback<INfCallbackOpp> {
    private final int onOppStateChanged = 1;
    private final int onOppReceiveFileInfo = 2;
    private final int onOppReceiveProgress = 3;

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected String getLogTag() {
        return "NfDoCallbackOpp";
    }

    public void onOppStateChanged(String address, int preState, int currentState, int reason) {
        NfLog.d(this.TAG, "onOppStateChanged() " + address);
        Message m = getMessage(1);
        m.obj = address;
        m.arg1 = preState;
        m.arg2 = currentState;
        Bundle b = new Bundle();
        b.putInt("reason", reason);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onOppReceiveFileInfo(String fileName, int fileSize, String deviceName, String savePath) {
        NfLog.d(this.TAG, "onOppReceiveFileInfo() fileName: " + fileName + "fileSize: " + fileSize + "deviceName: " + deviceName + "savePath: " + savePath);
        Message m = getMessage(2);
        Bundle b = new Bundle();
        b.putString("fileName", fileName);
        b.putInt("fileSize", fileSize);
        b.putString("deviceName", deviceName);
        b.putString("savePath", savePath);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onOppReceiveProgress(int receivedOffset) {
        NfLog.d(this.TAG, "onOppReceiveProgress() receivedOffset: " + receivedOffset);
        Message m = getMessage(3);
        Bundle b = new Bundle();
        b.putInt("receivedOffset", receivedOffset);
        m.setData(b);
        enqueueMessage(m);
    }

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected void dequeueMessage(Message msg) {
        Bundle b = msg.getData();
        String address = (String) msg.obj;
        int prevState = msg.arg1;
        int newState = msg.arg2;
        if (NfConfig.isCallbackByAidl()) {
            switch (msg.what) {
                case 1:
                    callbackOnOppStateChanged(address, prevState, newState, b.getInt("reason"));
                    return;
                case 2:
                    callbackOnOppReceiveFileInfo(b.getString("fileName"), b.getInt("fileSize"), b.getString("deviceName"), b.getString("savePath"));
                    return;
                case 3:
                    callbackOnOppReceiveProgress(b.getInt("receivedOffset"));
                    return;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.callback.NfDoCallback
    public synchronized void callbackOnServiceReady(INfCallbackOpp cb) throws RemoteException {
        cb.onOppServiceReady();
    }

    private synchronized void callbackOnOppStateChanged(String address, int preState, int currentState, int reason) {
        NfLog.v(this.TAG, "callbackOnOppStateChanged() " + address + " state: " + preState + "->" + currentState + ", reason: " + reason);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onOppStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackOpp) this.mRemoteCallbacks.getBroadcastItem(i)).onOppStateChanged(address, preState, currentState, reason);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onOppStateChanged CallBack Finish.");
    }

    private synchronized void callbackOnOppReceiveFileInfo(String fileName, int fileSize, String deviceName, String savePath) {
        NfLog.v(this.TAG, "callbackOnOppReceiveFileInfo() fileName: " + fileName + " fileSize: " + fileSize + " deviceName: " + deviceName + " savePath: " + savePath);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onOppReceiveFileInfo beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackOpp) this.mRemoteCallbacks.getBroadcastItem(i)).onOppReceiveFileInfo(fileName, fileSize, deviceName, savePath);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onOppReceiveFileInfo CallBack Finish.");
    }

    private synchronized void callbackOnOppReceiveProgress(int receivedOffset) {
        NfLog.v(this.TAG, "callbackOnOppReceiveProgres() receivedOffset: " + receivedOffset);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onOppReceiveProgress beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackOpp) this.mRemoteCallbacks.getBroadcastItem(i)).onOppReceiveProgress(receivedOffset);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onOppReceiveProgress CallBack Finish.");
    }
}
