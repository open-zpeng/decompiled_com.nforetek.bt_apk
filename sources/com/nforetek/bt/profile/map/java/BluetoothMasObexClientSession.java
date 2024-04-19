package com.nforetek.bt.profile.map.java;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.obex.ClientSession;
import com.nforetek.bt.res.obex.HeaderSet;
import com.nforetek.bt.res.obex.ObexTransport;
import com.nforetek.util.normal.NfLog;
import java.lang.ref.WeakReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BluetoothMasObexClientSession {
    private static final int CONNECT = 0;
    private static final int DISCONNECT = 1;
    private static final byte[] MAS_TARGET = {-69, 88, 43, 64, 66, 12, NfDef.AVRCP_BROWSING_STATUS_SEARCH_IN_PROGRESS, -37, -80, -34, 8, 0, 32, 12, -102, 102};
    static final int MSG_OBEX_CONNECTED = 100;
    static final int MSG_OBEX_DISCONNECTED = 101;
    static final int MSG_REQUEST_COMPLETED = 102;
    private static final int REQUEST = 2;
    private static final String TAG = "ObexClientSession";
    private boolean DBG = true;
    private boolean mConnected;
    private Handler mHandler;
    private ClientSession mSession;
    private final Handler mSessionHandler;
    private HandlerThread mThread;
    private ObexTransport mTransport;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ObexClientHandler extends Handler {
        WeakReference<BluetoothMasObexClientSession> mInst;

        ObexClientHandler(Looper looper, BluetoothMasObexClientSession inst) {
            super(looper);
            this.mInst = new WeakReference<>(inst);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            BluetoothMasObexClientSession inst = this.mInst.get();
            if (!inst.connected() && msg.what != 0) {
                NfLog.w(BluetoothMasObexClientSession.TAG, "Cannot execute " + msg + " when not CONNECTED.");
                return;
            }
            switch (msg.what) {
                case 0:
                    NfLog.d(BluetoothMasObexClientSession.TAG, "handleMessage CONNECT");
                    inst.connect();
                    return;
                case 1:
                    NfLog.d(BluetoothMasObexClientSession.TAG, "handleMessage DISCONNECT");
                    inst.disconnect();
                    return;
                case 2:
                    NfLog.d(BluetoothMasObexClientSession.TAG, "handleMessage REQUEST");
                    inst.executeRequest((BluetoothMasRequest) msg.obj);
                    return;
                default:
                    return;
            }
        }
    }

    public void finalize() {
        NfLog.d(TAG, "===finalize");
    }

    public BluetoothMasObexClientSession(ObexTransport transport, Handler handler) {
        this.mTransport = transport;
        this.mSessionHandler = handler;
    }

    public void start() {
        NfLog.d(this.DBG, TAG, "start called.");
        if (this.mConnected) {
            NfLog.d(this.DBG, TAG, "Already connected, nothing to do.");
            return;
        }
        this.mThread = new HandlerThread("BluetoothMasObexClientSessionThread");
        this.mThread.start();
        this.mHandler = new ObexClientHandler(this.mThread.getLooper(), this);
        this.mHandler.obtainMessage(0).sendToTarget();
    }

    public boolean makeRequest(BluetoothMasRequest request) {
        NfLog.d(this.DBG, TAG, "makeRequest called with: " + request);
        boolean status = this.mHandler.sendMessage(this.mHandler.obtainMessage(2, request));
        if (status) {
            return true;
        }
        NfLog.d(TAG, "Adding messages failed, state: " + this.mConnected);
        return false;
    }

    public void stop() {
        NfLog.d(this.DBG, TAG, "stop called...");
        this.mThread.quit();
        disconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connect() {
        try {
            NfLog.d(this.DBG, TAG, "OBEX connect... ");
            this.mSession = new ClientSession(this.mTransport);
            HeaderSet headerset = new HeaderSet();
            headerset.setHeader(70, MAS_TARGET);
            int resCode = this.mSession.connect(headerset).getResponseCode();
            if (resCode == 160) {
                NfLog.e(TAG, "OBEX connect OBEX_HTTP_OK");
                this.mConnected = true;
                this.mSessionHandler.obtainMessage(100).sendToTarget();
            } else {
                NfLog.e(TAG, "OBEX connect Fail: resCode " + resCode);
                disconnect(1);
            }
            NfLog.d(this.DBG, TAG, "OBEX connect wait... ");
        } catch (Exception e) {
            NfLog.e(TAG, "map obex client connect catch ", e);
            disconnect(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnect() {
        disconnect(0);
    }

    private void disconnect(int reason) {
        NfLog.d(this.DBG, TAG, "===OBEX disconnect...");
        if (this.mSession != null) {
            NfLog.d(TAG, "===OBEX disconnect mSession disconnect");
            try {
                this.mSession.disconnect(null);
            } catch (Exception e) {
                NfLog.e(TAG, "map disconnect fail", e);
            }
            try {
                this.mSession.close();
            } catch (Exception e2) {
                NfLog.e(TAG, "map close fail.", e2);
            }
            this.mSession = null;
        }
        this.mConnected = false;
        NfLog.d(this.DBG, TAG, "===OBEX disconnect MSG_OBEX_DISCONNECTED...");
        sendToClient(101, reason);
    }

    private void sendToClient(int event, int reason) {
        sendToClient(event, reason, 0, null);
    }

    private void sendToClient(int event, int reason, int arg2, Object param) {
        if (this.mSessionHandler != null) {
            try {
                this.mSessionHandler.obtainMessage(event, reason, arg2, param).sendToTarget();
            } catch (Exception e) {
                NfLog.d(TAG, "map sendToClient fail", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeRequest(BluetoothMasRequest request) {
        int MAX_RETRY = 3;
        try {
            if (request instanceof BluetoothMasRequestGetMessagesListing) {
                MAX_RETRY = 5;
            }
            for (int i = 0; i < MAX_RETRY && !request.isSuccess(); i++) {
                NfLog.d(TAG, "MAP executeRequest");
                request.execute(this.mSession);
            }
            this.mSessionHandler.obtainMessage(MSG_REQUEST_COMPLETED, request).sendToTarget();
        } catch (Exception e) {
            NfLog.e(TAG, "Request failed: " + request, e);
            if (e.getMessage().contains("Broken pipe")) {
                NfLog.d(TAG, " got exception: Broken pipe");
                if (isBtEnable()) {
                    disconnect(3);
                    return;
                } else {
                    disconnect(2);
                    return;
                }
            }
            disconnect();
        }
    }

    private boolean isBtEnable() {
        if (BluetoothAdapter.getDefaultAdapter() == null) {
            NfLog.e(TAG, "BluetoothAdapter is null.");
        } else {
            int state = BluetoothAdapter.getDefaultAdapter().getState();
            on = state == 12;
            if (!on) {
                NfLog.e(TAG, "BluetoothAdapter state is not ON. state: " + state);
            }
        }
        return on;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean connected() {
        return this.mConnected;
    }
}
