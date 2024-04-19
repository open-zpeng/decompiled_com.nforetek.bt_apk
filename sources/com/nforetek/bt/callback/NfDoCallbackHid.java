package com.nforetek.bt.callback;

import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackHid;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public final class NfDoCallbackHid extends NfDoCallback<INfCallbackHid> {
    private final int onHidStateChanged = 1;

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected String getLogTag() {
        return "NfDoCallbackHid";
    }

    public void onHidStateChanged(String address, int prevState, int newState, int reason) {
        NfLog.d(this.TAG, "onHidStateChanged() : " + prevState + " -> " + newState);
        Message m = Message.obtain(this.mHandler, 1);
        Bundle b = new Bundle();
        b.putString("address", address);
        b.putInt("prevState", prevState);
        b.putInt("newState", newState);
        b.putInt("reason", reason);
        m.setData(b);
        enqueueMessage(m);
    }

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected void dequeueMessage(Message msg) {
        Bundle b = msg.getData();
        if (NfConfig.isCallbackByAidl()) {
            switch (msg.what) {
                case 1:
                    callbackOnHidStateChanged(b.getString("address"), b.getInt("prevState"), b.getInt("newState"), b.getInt("reason"));
                    return;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.callback.NfDoCallback
    public synchronized void callbackOnServiceReady(INfCallbackHid cb) throws RemoteException {
        cb.onHidServiceReady();
    }

    private synchronized void callbackOnHidStateChanged(String address, int prevState, int newState, int reason) {
        NfLog.v(this.TAG, "callbackOnHidStateChanged() : " + prevState + " -> " + newState);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onHidStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackHid) this.mRemoteCallbacks.getBroadcastItem(i)).onHidStateChanged(address, prevState, newState, reason);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onHidStateChanged CallBack Finish.");
    }
}
