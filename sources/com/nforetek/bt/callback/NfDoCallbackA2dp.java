package com.nforetek.bt.callback;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackA2dp;
import com.nforetek.bt.callback.def.NfDefBroadcastA2dp;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public final class NfDoCallbackA2dp extends NfDoCallback<INfCallbackA2dp> {
    private final int onA2dpStateChanged;

    public NfDoCallbackA2dp(Context context) {
        super(context);
        this.onA2dpStateChanged = 1;
    }

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected String getLogTag() {
        return "NfDoCallbackA2dp";
    }

    public void onA2dpStateChanged(String address, int prevState, int newState) {
        NfLog.d(this.TAG, "onA2dpStateChanged() : " + prevState + " -> " + newState);
        Message m = Message.obtain(this.mHandler, 1);
        m.arg1 = prevState;
        m.arg2 = newState;
        m.obj = address;
        enqueueMessage(m);
    }

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected void dequeueMessage(Message msg) {
        String address = (String) msg.obj;
        if (NfConfig.isCallbackByAidl()) {
            switch (msg.what) {
                case 1:
                    callbackOnA2dpStateChanged(address, msg.arg1, msg.arg2);
                    break;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    break;
            }
        }
        if (NfConfig.isCallbackByBroadcast()) {
            switch (msg.what) {
                case 1:
                    broadcastOnA2dpStateChanged(address, msg.arg1, msg.arg2);
                    return;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.callback.NfDoCallback
    public synchronized void callbackOnServiceReady(INfCallbackA2dp cb) throws RemoteException {
        cb.onA2dpServiceReady();
    }

    private synchronized void callbackOnA2dpStateChanged(String address, int prevState, int newState) {
        NfLog.v(this.TAG, "callbackOnA2dpStateChanged() : " + prevState + " -> " + newState);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onA2dpStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackA2dp) this.mRemoteCallbacks.getBroadcastItem(i)).onA2dpStateChanged(address, prevState, newState);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onA2dpStateChanged CallBack Finish.");
    }

    private synchronized void broadcastOnA2dpStateChanged(String address, int prevState, int newState) {
        synchronized (this) {
            NfLog.v(this.TAG, "broadcastOnA2dpStateChanged() : " + prevState + " -> " + newState);
            if ((newState == 150 && prevState == 140) || (newState == 140 && prevState == 150)) {
                BluetoothDevice device = NfPrimitive.getDevice(address);
                Intent intent = new Intent(NfDefBroadcastA2dp.ACTION_PLAYING_STATE_CHANGED);
                intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
                intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState == 150 ? 0 : 2);
                intent.putExtra("android.bluetooth.profile.extra.STATE", newState != 150 ? 2 : 0);
                NfPrimitive.sendBroadcast(intent);
                NfLog.v(this.TAG, "onA2dpStateChanged broadcast Finish.");
            } else {
                switch (prevState) {
                    case 110:
                        prevState = 0;
                        break;
                    case 120:
                        prevState = 1;
                        break;
                    case NfDef.STATE_DISCONNECTING /* 125 */:
                        prevState = 3;
                        break;
                    case 140:
                    case 150:
                        prevState = 2;
                        break;
                }
                switch (newState) {
                    case 110:
                        newState = 0;
                        break;
                    case 120:
                        newState = 1;
                        break;
                    case NfDef.STATE_DISCONNECTING /* 125 */:
                        newState = 3;
                        break;
                    case 140:
                    case 150:
                        newState = 2;
                        break;
                }
                Intent intent2 = new Intent(NfDefBroadcastA2dp.ACTION_CONNECTION_STATE_CHANGED);
                BluetoothDevice device2 = NfPrimitive.getDevice(address);
                intent2.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
                intent2.putExtra("android.bluetooth.profile.extra.STATE", newState);
                intent2.putExtra("android.bluetooth.device.extra.DEVICE", device2);
                NfPrimitive.sendBroadcast(intent2);
                NfLog.v(this.TAG, "onA2dpStateChanged broadcast Finish.");
            }
        }
    }
}
