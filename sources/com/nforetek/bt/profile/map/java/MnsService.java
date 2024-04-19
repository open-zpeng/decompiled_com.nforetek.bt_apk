package com.nforetek.bt.profile.map.java;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.nforetek.bt.res.obex.BluetoothObexTransport;
import com.nforetek.bt.res.obex.IObexConnectionHandler;
import com.nforetek.bt.res.obex.ObexServerSockets;
import com.nforetek.bt.res.obex.ServerSession;
import com.nforetek.util.normal.NfLog;
import java.io.IOException;
import java.lang.ref.WeakReference;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MnsService {
    static final int EVENT_MNS_REG = 1002;
    static final int EVENT_MNS_UNREG = 1003;
    static final int EVENT_REPORT = 1001;
    static final int MNS_ADD_SDP = 1004;
    static final int MNS_ADD_SDP_INTERNAL = 5;
    private static final int MNS_FEATURE_BITS = 2;
    private static final int MNS_VERSION = 257;
    static final int MSG_EVENT = 1;
    static final int MSG_MNS_REG = 2;
    static final int MSG_MNS_REG_FAIL = 4;
    static final int MSG_MNS_UNREG = 3;
    private static final String TAG = "MnsService";
    private HandlerThread ht;
    private volatile boolean mShutdown = false;
    private static final Boolean DBG = BluetoothMasClient.DBG;
    private static SocketAcceptor mAcceptThread = null;
    private static Handler mSessionHandler = null;
    private static BluetoothServerSocket mServerSocket = null;
    private static ObexServerSockets mServerSockets = null;
    private static Handler mCallback = null;

    /* loaded from: classes.dex */
    private static class SessionHandler extends Handler {
        private final WeakReference<MnsService> mService;

        SessionHandler(MnsService service) {
            this.mService = new WeakReference<>(service);
        }

        SessionHandler(MnsService service, Looper looper) {
            super(looper);
            this.mService = new WeakReference<>(service);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            BluetoothMapEventReport ev;
            switch (msg.what) {
                case 1:
                    NfLog.d(MnsService.TAG, "handle MSG_EVENT");
                    int instanceId = msg.arg1;
                    int connectionStatus = msg.arg2;
                    Handler cb = MnsService.mCallback;
                    if (cb != null) {
                        if (connectionStatus == 1) {
                            cb.obtainMessage(MnsService.EVENT_REPORT, null).sendToTarget();
                            return;
                        } else if (msg.obj != null && (ev = (BluetoothMapEventReport) msg.obj) != null) {
                            cb.obtainMessage(MnsService.EVENT_REPORT, ev).sendToTarget();
                            return;
                        } else {
                            return;
                        }
                    }
                    NfLog.w(MnsService.TAG, "Got event for instance which is not registered: " + instanceId);
                    return;
                case 2:
                    NfLog.d(MnsService.TAG, "handle MSG_MNS_REG");
                    Handler cb2 = MnsService.mCallback;
                    if (cb2 != null) {
                        cb2.obtainMessage(MnsService.EVENT_MNS_REG, null).sendToTarget();
                        return;
                    } else {
                        NfLog.w(MnsService.TAG, "reg(ed) when no callback? ");
                        return;
                    }
                case 3:
                    NfLog.d(MnsService.TAG, "handle MSG_MNS_UNREG");
                    Handler cb3 = MnsService.mCallback;
                    if (cb3 != null) {
                        cb3.obtainMessage(MnsService.EVENT_MNS_UNREG, null).sendToTarget();
                        return;
                    } else {
                        NfLog.w(MnsService.TAG, "unreg when no callback? ");
                        return;
                    }
                case 4:
                default:
                    return;
                case 5:
                    NfLog.d(MnsService.TAG, "handle MNS_ADD_SDP_INTERNAL: " + msg.arg1);
                    Handler cb4 = MnsService.mCallback;
                    if (cb4 != null) {
                        cb4.obtainMessage(MnsService.MNS_ADD_SDP, msg.arg1, 0, null).sendToTarget();
                        return;
                    } else {
                        NfLog.w(MnsService.TAG, "MNS_ADD_SDP_INTERNAL when no callback? ");
                        return;
                    }
            }
        }
    }

    MnsService(Handler callback) {
        NfLog.v(TAG, "BluetoothMnsService()");
        registerCallback(6, callback);
        if (this.ht == null || mSessionHandler == null) {
            NfLog.v(TAG, "BluetoothMnsService(): allocating handler thread");
            this.ht = new HandlerThread("NfMasHandler");
            this.ht.start();
            mSessionHandler = new SessionHandler(this, this.ht.getLooper());
        }
        mAcceptThread = new SocketAcceptor(this, null);
        mServerSockets = ObexServerSockets.create(mAcceptThread);
        if (mServerSockets == null) {
            NfLog.v(TAG, "BluetoothMnsService(): create socket fail");
            return;
        }
        try {
            int channel = mServerSockets.getRfcommChannel();
            NfLog.v(TAG, "BluetoothMnsService(): getRfcommChannel " + channel);
            NfLog.v(TAG, "BluetoothMnsService(): getL2capPsm " + mServerSockets.getL2capPsm());
            mSessionHandler.obtainMessage(5, channel, 0, null).sendToTarget();
        } catch (Exception e) {
            NfLog.e(TAG, "Create MNS record Failed.");
        }
    }

    void stop() {
        NfLog.d(DBG.booleanValue(), TAG, "stop()");
        this.mShutdown = true;
        if (mServerSockets != null) {
            NfLog.v(TAG, "deallocating socket");
            try {
                mServerSockets.shutdown(false);
            } catch (Exception e) {
            }
            mServerSockets = null;
        }
    }

    public void clean() {
        NfLog.v(TAG, "==clean called");
        stop();
        if (mSessionHandler != null) {
            NfLog.v(TAG, "deallocating session handler");
            mSessionHandler = null;
        }
        if (mCallback != null) {
            NfLog.v(TAG, "deallocating callback");
            mCallback = null;
        }
        if (this.ht != null) {
            NfLog.v(TAG, "deallocating handler thread");
            this.ht.getLooper().quit();
            this.ht = null;
        }
        NfLog.v(TAG, "  ");
    }

    public void registerCallback(int instanceId, Handler callback) {
        NfLog.v(TAG, "registerCallback()");
        mCallback = callback;
    }

    public void unregisterCallback(int instanceId) {
        NfLog.v(TAG, "unregisterCallback()");
        mCallback = null;
        clean();
    }

    /* loaded from: classes.dex */
    private class SocketAcceptor implements IObexConnectionHandler {
        private boolean mInterrupted;

        private SocketAcceptor() {
            this.mInterrupted = false;
        }

        /* synthetic */ SocketAcceptor(MnsService mnsService, SocketAcceptor socketAcceptor) {
            this();
        }

        @Override // com.nforetek.bt.res.obex.IObexConnectionHandler
        public synchronized void onAcceptFailed() {
            Log.e(MnsService.TAG, "OnAcceptFailed");
            MnsService.mServerSockets = null;
            if (MnsService.this.mShutdown) {
                Log.e(MnsService.TAG, "Failed to accept incomming connection - shutdown");
            }
        }

        @Override // com.nforetek.bt.res.obex.IObexConnectionHandler
        public synchronized boolean onConnect(BluetoothDevice device, BluetoothSocket socket) {
            boolean z;
            NfLog.d(MnsService.DBG.booleanValue(), MnsService.TAG, "onConnect" + device + " SOCKET: " + socket);
            MnsObexServer srv = new MnsObexServer(MnsService.mSessionHandler, MnsService.mServerSockets);
            BluetoothObexTransport transport = new BluetoothObexTransport(socket);
            try {
                new ServerSession(transport, srv, null);
                z = true;
            } catch (IOException e) {
                Log.e(MnsService.TAG, e.toString());
                z = false;
            }
            return z;
        }
    }
}
