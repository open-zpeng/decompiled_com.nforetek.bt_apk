package com.nforetek.bt.callback;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IInterface;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public abstract class NfDoCallback<E extends IInterface> {
    protected String TAG;
    private Context mContext;
    protected Handler mHandler;
    HandlerThread mHandlerThread;
    protected RemoteCallbackList<E> mRemoteCallbacks;
    private final int onServiceReady;

    protected abstract void callbackOnServiceReady(E e) throws RemoteException;

    protected abstract void dequeueMessage(Message message);

    protected abstract String getLogTag();

    public NfDoCallback() {
        this(null);
    }

    public NfDoCallback(Context context) {
        this.onServiceReady = 0;
        this.TAG = getLogTag();
        this.mRemoteCallbacks = new RemoteCallbackList<>();
        this.mHandlerThread = new HandlerThread(this.TAG);
        this.mHandlerThread.start();
        this.mHandler = initCallbackHandler();
        this.mContext = context;
        NfLog.v(this.TAG, String.valueOf(this.TAG) + "() init");
    }

    public boolean register(E cb) {
        NfLog.d(this.TAG, "Piggy Check register: " + cb);
        if (this.mRemoteCallbacks.register(cb)) {
            onServiceReady(cb);
            return true;
        }
        return false;
    }

    public boolean unregister(E cb) {
        return this.mRemoteCallbacks.unregister(cb);
    }

    public void kill() {
        this.mRemoteCallbacks.kill();
        this.mRemoteCallbacks = null;
        if (this.mHandlerThread != null) {
            this.mHandlerThread.getLooper().quit();
            this.mHandlerThread = null;
        }
    }

    public void onServiceReady(E cb) {
        NfLog.d(this.TAG, "onServiceReady()");
        Message m = Message.obtain(this.mHandler, 0);
        m.obj = cb;
        enqueueMessage(m);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void enqueueMessage(Message msg) {
        if (isCallbackValid()) {
            if (this.mHandler != null) {
                this.mHandler.sendMessage(msg);
            } else {
                NfLog.e(this.TAG, "mHandler is null !!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Message getMessage(int what) {
        if (this.mHandler == null) {
            NfLog.e(this.TAG, "mHandler is null !!");
            return null;
        }
        return this.mHandler.obtainMessage(what);
    }

    private Handler initCallbackHandler() {
        if (this.mHandler == null) {
            if (this.mHandlerThread == null) {
                NfLog.e(this.TAG, "mHandlerThread is null !!");
                return null;
            }
            return new Handler(this.mHandlerThread.getLooper()) { // from class: com.nforetek.bt.callback.NfDoCallback.1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    NfLog.v(NfDoCallback.this.TAG, "handleMessage : " + msg.what);
                    if (msg.what == 0) {
                        NfDoCallback.this.callbackOnProfileServiceReady((IInterface) msg.obj);
                    } else {
                        NfDoCallback.this.dequeueMessage(msg);
                    }
                }
            };
        }
        return this.mHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkCallbacksValid(int index) {
        E cb = this.mRemoteCallbacks.getBroadcastItem(index);
        if (cb == null) {
            NfLog.e(this.TAG, "Callback " + cb + " is null !! unregister here.");
            this.mRemoteCallbacks.unregister(cb);
        }
    }

    protected boolean isCallbackValid() {
        if (this.mRemoteCallbacks == null) {
            NfLog.e(this.TAG, "Remote Callbacks is null !!");
            return false;
        }
        return true;
    }

    protected synchronized void callbackOnProfileServiceReady(E cb) {
        NfLog.v(this.TAG, "callbackOnProfileServiceReady()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onServiceReady beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    try {
                    } catch (NullPointerException e) {
                        checkCallbacksValid(i);
                    }
                } catch (RemoteException e2) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                }
                if (!this.mRemoteCallbacks.getBroadcastItem(i).equals(cb)) {
                    continue;
                } else {
                    callbackOnServiceReady(cb);
                    break;
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onServiceReady CallBack Finish.");
    }

    protected Context getContext() {
        return this.mContext;
    }
}
