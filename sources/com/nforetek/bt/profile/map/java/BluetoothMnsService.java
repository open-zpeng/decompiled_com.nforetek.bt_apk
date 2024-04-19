package com.nforetek.bt.profile.map.java;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.bt.res.obex.ServerSession;
import com.nforetek.util.normal.NfLog;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BluetoothMnsService {
    static final int EVENT_MNS_INIT_FAIL = 1005;
    static final int EVENT_MNS_INIT_RETRY = 1006;
    static final int EVENT_MNS_REG = 1002;
    static final int EVENT_MNS_UNREG = 1003;
    static final int EVENT_REPORT = 1001;
    static final int MNS_ADD_SDP = 1004;
    static final int MNS_ADD_SDP_INTERNAL = 5;
    static final int MSG_EVENT = 1;
    static final int MSG_MNS_REG = 2;
    static final int MSG_MNS_REG_FAIL = 4;
    static final int MSG_MNS_UNREG = 3;
    private static final String TAG = "BluetoothMnsService";
    private static final boolean isUseHandlerThread = true;
    private static final ParcelUuid MAP_MNS = ParcelUuid.fromString("00001133-0000-1000-8000-00805F9B34FB");
    private static Handler mCallback = null;
    private static Handler mSessionHandler = null;
    private static BluetoothServerSocket mServerSocket = null;
    private static SocketAcceptThread mAcceptThread = null;
    private static HandlerThread ht = null;
    private static getScnThread mGetScnThread = null;
    private static ServerSession mMnsServerSession = null;
    boolean mFinalized = false;
    private boolean actived = true;

    /* loaded from: classes.dex */
    private static class SessionHandler extends Handler {
        private final WeakReference<BluetoothMnsService> mService;
        private final BluetoothMnsService mServiceHard;

        SessionHandler(BluetoothMnsService service) {
            this.mServiceHard = service;
            this.mService = new WeakReference<>(service);
        }

        SessionHandler(BluetoothMnsService service, Looper looper) {
            super(looper);
            this.mServiceHard = service;
            this.mService = new WeakReference<>(service);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            BluetoothMapEventReport ev;
            switch (msg.what) {
                case 1:
                    try {
                        NfLog.d(BluetoothMnsService.TAG, "handle MSG_EVENT");
                        int instanceId = msg.arg1;
                        int connectionStatus = msg.arg2;
                        Handler cb = BluetoothMnsService.mCallback;
                        if (cb != null) {
                            if (connectionStatus == 1) {
                                cb.obtainMessage(BluetoothMnsService.EVENT_REPORT, null).sendToTarget();
                            } else if (msg.obj != null && (ev = (BluetoothMapEventReport) msg.obj) != null) {
                                cb.obtainMessage(BluetoothMnsService.EVENT_REPORT, ev).sendToTarget();
                            }
                        } else {
                            NfLog.w(BluetoothMnsService.TAG, "Got event for instance which is not registered: " + instanceId);
                        }
                        return;
                    } catch (Exception e) {
                        NfLog.e(BluetoothMnsService.TAG, "exception when trying to callback", e);
                        return;
                    }
                case 2:
                    NfLog.d(BluetoothMnsService.TAG, "handle MSG_MNS_REG");
                    sendToClient(BluetoothMnsService.EVENT_MNS_REG, null);
                    return;
                case 3:
                    NfLog.d(BluetoothMnsService.TAG, "handle MSG_MNS_UNREG");
                    sendToClient(BluetoothMnsService.EVENT_MNS_UNREG, null);
                    return;
                case 4:
                default:
                    return;
                case 5:
                    NfLog.d(BluetoothMnsService.TAG, "handle MNS_ADD_SDP_INTERNAL: " + msg.arg1);
                    Handler unused = BluetoothMnsService.mCallback;
                    sendToClient(BluetoothMnsService.MNS_ADD_SDP, msg.arg1, 0, null);
                    return;
            }
        }

        public void finalize() {
            NfLog.d(BluetoothMnsService.TAG, "===finalize");
        }

        public void sendToClient(int event, int arg1, int arg2, Object o) {
            try {
                if (BluetoothMnsService.mCallback != null) {
                    BluetoothMnsService.mCallback.obtainMessage(event, arg1, arg2, o).sendToTarget();
                } else {
                    NfLog.w(BluetoothMnsService.TAG, "callback null for event: " + event);
                }
            } catch (Exception e) {
                NfLog.e(BluetoothMnsService.TAG, "exception when trying to callback", e);
            }
        }

        public void sendToClient(int event, Object o) {
            try {
                if (BluetoothMnsService.mCallback != null) {
                    BluetoothMnsService.mCallback.obtainMessage(event, o).sendToTarget();
                } else {
                    NfLog.w(BluetoothMnsService.TAG, "callback null for event: " + event);
                }
            } catch (Exception e) {
                NfLog.e(BluetoothMnsService.TAG, "exception when trying to callback", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SocketAcceptThread extends Thread {
        static final int REASON_FAIL = 2;
        static final int REASON_FINALLY = 1;
        static final int REASON_NOT_CLEAN = 3;
        static final int REASON_SUCCESS = 0;
        private boolean mInterrupted;

        private SocketAcceptThread() {
            this.mInterrupted = false;
        }

        /* synthetic */ SocketAcceptThread(SocketAcceptThread socketAcceptThread) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int ret = runInternal();
            NfLog.d(BluetoothMnsService.TAG, "SocketAcceptThread return: " + ret);
            if (ret >= 2) {
                NfLog.d(BluetoothMnsService.TAG, "SocketAcceptThread return EVENT_MNS_INIT_FAIL");
                if (BluetoothMnsService.mCallback != null) {
                    try {
                        BluetoothMnsService.mCallback.sendEmptyMessage(BluetoothMnsService.EVENT_MNS_INIT_FAIL);
                    } catch (Exception e) {
                        NfLog.e(BluetoothMnsService.TAG, "exception when trying to sendEmptyMessage MNS_INIT_FAIL", e);
                    }
                }
            }
        }

        private int runInternal() {
            if (BluetoothMnsService.mServerSocket != null) {
                NfLog.w(BluetoothMnsService.TAG, "Socket already created, exiting");
                return 3;
            }
            try {
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                BluetoothMnsService.mServerSocket = adapter.listenUsingRfcommWithServiceRecord("MAP Message Notification Service", BluetoothMnsService.MAP_MNS.getUuid());
                if (NfConfig.isUseMapAddSdp()) {
                    if (BluetoothMnsService.mGetScnThread == null) {
                        NfLog.v(BluetoothMnsService.TAG, "start mGetScnThread");
                        BluetoothMnsService.mGetScnThread = new getScnThread(null);
                        BluetoothMnsService.mGetScnThread.setName("getScnThread");
                        BluetoothMnsService.mGetScnThread.start();
                    } else {
                        NfLog.v(BluetoothMnsService.TAG, "mGetScnThread not null");
                    }
                }
                while (!this.mInterrupted) {
                    try {
                        NfLog.v(BluetoothMnsService.TAG, "waiting to accept connection...");
                        BluetoothSocket sock = BluetoothMnsService.mServerSocket.accept();
                        NfLog.v(BluetoothMnsService.TAG, "new incoming connection from " + sock.getRemoteDevice().getName());
                        BluetoothMnsObexServer srv = new BluetoothMnsObexServer(BluetoothMnsService.mSessionHandler);
                        BluetoothMapRfcommTransport transport = new BluetoothMapRfcommTransport(sock);
                        BluetoothMnsService.mMnsServerSession = new ServerSession(transport, srv, null);
                    } catch (IOException ex) {
                        NfLog.e(BluetoothMnsService.TAG, "I/O exception when waiting to accept (aborted?)", ex);
                        return 2;
                    } catch (Exception ex2) {
                        NfLog.e(BluetoothMnsService.TAG, "exception when waiting to accept (aborted?)", ex2);
                        return 2;
                    }
                }
                NfLog.e(BluetoothMnsService.TAG, "mns accept thread complete");
                return 1;
            } catch (IOException e) {
                NfLog.e(BluetoothMnsService.TAG, "I/O exception when trying to create server socket", e);
                return 2;
            } catch (Exception e2) {
                NfLog.e(BluetoothMnsService.TAG, "exception when trying to create server socket", e2);
                return 2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class getScnThread extends Thread {
        private boolean mInterrupted;

        private getScnThread() {
            this.mInterrupted = false;
        }

        /* synthetic */ getScnThread(getScnThread getscnthread) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!this.mInterrupted && BluetoothMnsService.mServerSocket != null) {
                int rfcommChannel = -1;
                try {
                    Field pField = BluetoothMnsService.mServerSocket.getClass().getDeclaredField("mSocket");
                    pField.setAccessible(true);
                    BluetoothSocket socket = (BluetoothSocket) pField.get(BluetoothMnsService.mServerSocket);
                    Field pField2 = socket.getClass().getDeclaredField("mPort");
                    pField2.setAccessible(true);
                    rfcommChannel = ((Integer) pField2.get(socket)).intValue();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                } catch (NoSuchMethodException e3) {
                    e3.printStackTrace();
                } catch (SecurityException e4) {
                    e4.printStackTrace();
                } catch (InvocationTargetException e5) {
                    e5.printStackTrace();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
                if (rfcommChannel < 0) {
                    NfLog.d(BluetoothMnsService.TAG, "get rfcommChannel fail: " + rfcommChannel);
                } else {
                    NfLog.d(BluetoothMnsService.TAG, "mns get rfcommChannel success: " + rfcommChannel);
                    try {
                        BluetoothMnsService.mSessionHandler.obtainMessage(5, rfcommChannel, 0, null).sendToTarget();
                        this.mInterrupted = true;
                        return;
                    } catch (Exception ee) {
                        NfLog.e(BluetoothMnsService.TAG, " ");
                        NfLog.e(BluetoothMnsService.TAG, "Create MNS record Failed.");
                        ee.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException e7) {
                    e7.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothMnsService(Handler callback) {
        NfLog.v(TAG, "   ");
        NfLog.v(TAG, "===init BluetoothMnsService()");
        registerCallback(0, callback);
        if (ht == null) {
            NfLog.v(TAG, "BluetoothMnsService(): allocating handler thread");
            ht = new HandlerThread("NfMasHandler");
            ht.start();
            mSessionHandler = new SessionHandler(this, ht.getLooper());
        } else if (mSessionHandler == null) {
            NfLog.v(TAG, "BluetoothMnsService(): allocating mSessionHandler");
            mSessionHandler = new SessionHandler(this, ht.getLooper());
        }
        if (mAcceptThread == null) {
            NfLog.v(TAG, "registerCallback(): starting MNS server");
            mAcceptThread = new SocketAcceptThread(null);
            mAcceptThread.setName("BluetoothMnsAcceptThread");
            mAcceptThread.start();
        }
        NfLog.v(TAG, "===init BluetoothMnsService() end");
    }

    public void finalize() {
        NfLog.d(TAG, "===finalize");
        this.mFinalized = true;
    }

    public boolean isFinalize() {
        return this.mFinalized;
    }

    public boolean isActive() {
        return this.actived;
    }

    public void clean() {
        this.actived = false;
        NfLog.v(TAG, "===clean called");
        if (mServerSocket != null) {
            NfLog.v(TAG, "deallocating socket");
            try {
                mServerSocket.close();
            } catch (Exception e) {
                NfLog.e(TAG, "deallocating mServerSocket fail", e);
            }
            mServerSocket = null;
        }
        if (mMnsServerSession != null) {
            NfLog.v(TAG, "deallocating mMnsServerSession");
            try {
                mMnsServerSession.close();
            } catch (Exception e2) {
                NfLog.e(TAG, "deallocating mMnsServerSession fail", e2);
            }
            mServerSocket = null;
        }
        if (mGetScnThread != null) {
            NfLog.v(TAG, "deallocating mGetScnThread");
            try {
                mGetScnThread.interrupt();
            } catch (Exception e3) {
                NfLog.e(TAG, "mGetScnThread.interrupt Failed.", e3);
            }
            mGetScnThread = null;
        }
        if (mAcceptThread != null) {
            NfLog.v(TAG, "deallocating accept thread");
            try {
                mAcceptThread.interrupt();
            } catch (Exception e4) {
                NfLog.e(TAG, "mAcceptThread.interrupt Failed.", e4);
            }
            mAcceptThread = null;
        }
        if (mSessionHandler != null) {
            NfLog.v(TAG, "deallocating session handler");
            mSessionHandler = null;
        }
        if (mCallback != null) {
            NfLog.v(TAG, "deallocating callback");
            mCallback = null;
        }
        if (ht != null) {
            NfLog.v(TAG, "deallocating handler thread");
            try {
                ht.getLooper().quit();
            } catch (Exception e5) {
                NfLog.e(TAG, "ERROR : Calling ht.getLooper() when ht == null", e5);
            }
            ht = null;
        }
        NfLog.v(TAG, "===clean end");
        NfLog.v(TAG, "  ");
    }

    public void registerCallback(int instanceId, Handler callback) {
        NfLog.v(TAG, "registerCallback()");
        mCallback = callback;
    }

    public void unregisterCallback(int instanceId) {
        NfLog.v(TAG, "unregisterCallback(): shutting down MNS server");
        mCallback = null;
    }
}
