package com.nforetek.bt.profile.pbap.java;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import com.nforetek.bt.profile.pbap.java.PbapClientConnectionHandler;
import com.nforetek.bt.profile.pbap.java.vcard.VCardEntry;
import com.nforetek.bt.res.bt.State;
import com.nforetek.bt.res.bt.StateMachine;
import com.nforetek.util.normal.NfLog;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class PbapClientStateMachine extends StateMachine {
    static final int CONNECT_TIMEOUT = 6000;
    private static final boolean DBG = true;
    static final int DISCONNECT_TIMEOUT = 10000;
    static final int MSG_CONNECTION_CLOSED = 7;
    static final int MSG_CONNECTION_COMPLETE = 5;
    static final int MSG_CONNECTION_FAILED = 6;
    private static final int MSG_CONNECT_TIMEOUT = 3;
    private static final int MSG_DISCONNECT = 2;
    private static final int MSG_DISCONNECT_TIMEOUT = 4;
    private static final int MSG_STATE_CHAGE_TO_DISCONNECTING = 10;
    private static final String TAG = "PbapClientStateMachine";
    private boolean isDownloading;
    private State mConnected;
    private State mConnecting;
    private PbapClientConnectionHandler mConnectionHandler;
    private Context mContext;
    private BluetoothDevice mCurrentDevice;
    private State mDisconnected;
    private State mDisconnecting;
    private int mDownloadPath;
    private int mFilter;
    private HandlerThread mHandlerThread;
    private int mL2capPsm;
    private final Object mLock;
    private int mMostRecentState;
    private int mOffset;
    private PbapListener mPbapClientService;
    private int mStartPos;

    /* loaded from: classes.dex */
    public interface PbapListener {
        void cleanupDevice(BluetoothDevice bluetoothDevice);

        void onConnectionStateChanged(BluetoothDevice bluetoothDevice, int i, int i2);

        void onPhoneBookPullDone(VCardEntry vCardEntry);

        void onPhoneBookPullDoneCheckSize(int i);

        void onResponePhoneBookSize(int i);

        void resetStateMachine();
    }

    public void onResponePhoneBookSize(int size) {
        this.mPbapClientService.onResponePhoneBookSize(size);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectionStateChanged(BluetoothDevice device, int prevState, int state) {
        if (device == null) {
            NfLog.w(TAG, "onConnectionStateChanged with invalid device");
        } else if (this.mPbapClientService != null) {
            this.mPbapClientService.onConnectionStateChanged(device, prevState, state);
        }
    }

    public void onPhoneBookPullDoneCheckSize(int size) {
        this.mPbapClientService.onPhoneBookPullDoneCheckSize(size);
    }

    public void onPhoneBookPullDone(VCardEntry vcardEntry) {
        this.mPbapClientService.onPhoneBookPullDone(vcardEntry);
    }

    public void disconnect(BluetoothDevice device) {
        NfLog.d(TAG, "Disconnect Request " + device);
        sendMessage(2, device);
    }

    public void pbapDownloadInterrupt() {
        NfLog.d(TAG, "pbapDownloadInterrupt()");
        if (this.mConnectionHandler != null) {
            this.mConnectionHandler.pbapDownloadInterrupt();
        }
    }

    public void changeStateMachineToDisconnecting() {
        NfLog.d(TAG, "changeStateToDisconnecting");
        removeMessages(3);
        sendMessage(10);
    }

    public int getMostRecentState() {
        NfLog.d(TAG, "StateMachine now state =" + this.mMostRecentState);
        return this.mMostRecentState;
    }

    public boolean isDownloading() {
        return this.isDownloading;
    }

    public void setDownloadPath(int downloadPath) {
        this.mDownloadPath = downloadPath;
    }

    public void setFilter(int filter) {
        this.mFilter = filter;
    }

    public void setTargetDevice(BluetoothDevice device) {
        this.mCurrentDevice = device;
    }

    public void setOffset(int count) {
        this.mOffset = count;
    }

    public void setStartPos(int startPos) {
        this.mStartPos = startPos;
    }

    public void setL2capPsm(int psm) {
        NfLog.d(TAG, "setL2capPsm: " + psm);
        this.mL2capPsm = psm;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PbapClientStateMachine(PbapListener pbapClientService, BluetoothDevice device, Context context) {
        super(TAG);
        this.mHandlerThread = null;
        this.isDownloading = false;
        this.mDownloadPath = 0;
        this.mFilter = 0;
        this.mOffset = 0;
        this.mStartPos = 0;
        this.mL2capPsm = -1;
        this.mMostRecentState = 0;
        this.mContext = context;
        this.mPbapClientService = pbapClientService;
        this.mCurrentDevice = device;
        this.mLock = new Object();
        this.mDisconnected = new Disconnected();
        this.mConnecting = new Connecting();
        this.mDisconnecting = new Disconnecting();
        this.mConnected = new Connected();
        addState(this.mDisconnected);
        addState(this.mConnecting);
        addState(this.mDisconnecting);
        addState(this.mConnected);
        setInitialState(this.mConnecting);
    }

    /* loaded from: classes.dex */
    class Connecting extends State {
        Connecting() {
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public void enter() {
            NfLog.d(PbapClientStateMachine.TAG, "Enter Connecting: " + PbapClientStateMachine.this.getCurrentMessage().what);
            PbapClientStateMachine.this.onConnectionStateChanged(PbapClientStateMachine.this.mCurrentDevice, PbapClientStateMachine.this.mMostRecentState, 1);
            PbapClientStateMachine.this.mMostRecentState = 1;
            PbapClientStateMachine.this.mHandlerThread = new HandlerThread("PBAP PCE handler", 10);
            PbapClientStateMachine.this.mHandlerThread.start();
            PbapClientStateMachine.this.mConnectionHandler = new PbapClientConnectionHandler.Builder().setLooper(PbapClientStateMachine.this.mHandlerThread.getLooper()).setContext(PbapClientStateMachine.this.mContext).setClientSM(PbapClientStateMachine.this).setRemoteDevice(PbapClientStateMachine.this.mCurrentDevice).setDownloadPath(PbapClientStateMachine.this.mDownloadPath).setFilter(PbapClientStateMachine.this.mFilter).setOffset(PbapClientStateMachine.this.mOffset).setStartPos(PbapClientStateMachine.this.mStartPos).setL2capPsm(PbapClientStateMachine.this.mL2capPsm).build();
            PbapClientStateMachine.this.sendMessageDelayed(3, 6000L);
            PbapClientStateMachine.this.mConnectionHandler.obtainMessage(1, null).sendToTarget();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public boolean processMessage(Message message) {
            NfLog.d(PbapClientStateMachine.TAG, "Processing MSG " + message.what + " from " + getName());
            switch (message.what) {
                case 2:
                    PbapClientStateMachine.this.removeMessages(3);
                    PbapClientStateMachine.this.transitionTo(PbapClientStateMachine.this.mDisconnecting);
                    break;
                case 3:
                case 6:
                    NfLog.e(PbapClientStateMachine.TAG, "MSG_CONNECT_TIMEOUT");
                    PbapClientStateMachine.this.mPbapClientService.resetStateMachine();
                    break;
                case 4:
                case 7:
                case 8:
                case 9:
                default:
                    NfLog.w(PbapClientStateMachine.TAG, "Received unexpected message while Connecting");
                    return false;
                case 5:
                    PbapClientStateMachine.this.removeMessages(3);
                    PbapClientStateMachine.this.transitionTo(PbapClientStateMachine.this.mConnected);
                    break;
                case 10:
                    NfLog.i(PbapClientStateMachine.TAG, "MSG_STATE_CHAGE_TO_DISCONNECTING");
                    PbapClientStateMachine.this.removeMessages(3);
                    PbapClientStateMachine.this.transitionTo(PbapClientStateMachine.this.mDisconnecting);
                    break;
            }
            return true;
        }
    }

    /* loaded from: classes.dex */
    class Connected extends State {
        Connected() {
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public void enter() {
            NfLog.d(PbapClientStateMachine.TAG, "Enter Connected: " + PbapClientStateMachine.this.getCurrentMessage().what);
            PbapClientStateMachine.this.isDownloading = true;
            PbapClientStateMachine.this.mMostRecentState = 2;
            PbapClientStateMachine.this.onConnectionStateChanged(PbapClientStateMachine.this.mCurrentDevice, PbapClientStateMachine.this.mMostRecentState, 2);
            PbapClientStateMachine.this.mConnectionHandler.obtainMessage(3).sendToTarget();
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public boolean processMessage(Message message) {
            NfLog.d(PbapClientStateMachine.TAG, "Processing MSG " + message.what + " from " + getName());
            switch (message.what) {
                case 2:
                    PbapClientStateMachine.this.removeMessages(3);
                    PbapClientStateMachine.this.transitionTo(PbapClientStateMachine.this.mDisconnecting);
                    return true;
                case 3:
                    NfLog.i(PbapClientStateMachine.TAG, "Connect timeout");
                    PbapClientStateMachine.this.transitionTo(PbapClientStateMachine.this.mDisconnecting);
                    return true;
                case 4:
                case 5:
                case 6:
                case 8:
                case 9:
                default:
                    NfLog.w(PbapClientStateMachine.TAG, "Received unexpected message while Connected");
                    return false;
                case 7:
                    PbapClientStateMachine.this.onConnectionStateChanged(PbapClientStateMachine.this.mCurrentDevice, PbapClientStateMachine.this.mMostRecentState, 0);
                    PbapClientStateMachine.this.mHandlerThread.quitSafely();
                    PbapClientStateMachine.this.transitionTo(PbapClientStateMachine.this.mDisconnected);
                    NfLog.w(PbapClientStateMachine.TAG, "Received unexpected message while Connected");
                    return false;
                case 10:
                    PbapClientStateMachine.this.removeMessages(3);
                    NfLog.i(PbapClientStateMachine.TAG, "Connect timeout");
                    PbapClientStateMachine.this.transitionTo(PbapClientStateMachine.this.mDisconnecting);
                    return true;
            }
        }
    }

    @SuppressLint({"NewApi"})
    /* loaded from: classes.dex */
    class Disconnecting extends State {
        Disconnecting() {
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public void enter() {
            NfLog.d(PbapClientStateMachine.TAG, "Enter Disconnecting: " + PbapClientStateMachine.this.getCurrentMessage().what);
            PbapClientStateMachine.this.mConnectionHandler.obtainMessage(2).sendToTarget();
            PbapClientStateMachine.this.mMostRecentState = 3;
            PbapClientStateMachine.this.sendMessageDelayed(4, 10000L);
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public boolean processMessage(Message message) {
            NfLog.d(PbapClientStateMachine.TAG, "Processing MSG " + message.what + " from " + getName());
            switch (message.what) {
                case 2:
                    PbapClientStateMachine.this.removeMessages(4);
                    PbapClientStateMachine.this.onConnectionStateChanged(PbapClientStateMachine.this.mCurrentDevice, PbapClientStateMachine.this.mMostRecentState, 0);
                    PbapClientStateMachine.this.deferMessage(message);
                    break;
                case 3:
                case 5:
                case 6:
                default:
                    NfLog.w(PbapClientStateMachine.TAG, "Received unexpected message while Disconnecting");
                    return false;
                case 4:
                    NfLog.w(PbapClientStateMachine.TAG, "Disconnect Timeout, Forcing");
                    PbapClientStateMachine.this.mConnectionHandler.abort();
                    break;
                case 7:
                    PbapClientStateMachine.this.removeMessages(4);
                    PbapClientStateMachine.this.onConnectionStateChanged(PbapClientStateMachine.this.mCurrentDevice, PbapClientStateMachine.this.mMostRecentState, 0);
                    PbapClientStateMachine.this.mHandlerThread.quitSafely();
                    PbapClientStateMachine.this.transitionTo(PbapClientStateMachine.this.mDisconnected);
                    break;
            }
            return true;
        }
    }

    /* loaded from: classes.dex */
    class Disconnected extends State {
        Disconnected() {
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public void enter() {
            PbapClientStateMachine.this.mMostRecentState = 0;
            PbapClientStateMachine.this.quit();
        }
    }

    @Override // com.nforetek.bt.res.bt.StateMachine
    protected void onQuitting() {
        this.mPbapClientService.cleanupDevice(this.mCurrentDevice);
    }
}
