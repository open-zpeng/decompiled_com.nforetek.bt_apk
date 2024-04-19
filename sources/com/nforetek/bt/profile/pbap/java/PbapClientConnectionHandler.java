package com.nforetek.bt.profile.pbap.java;

import android.accounts.Account;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.nforetek.bt.profile.pbap.java.BluetoothPbapRequestPullPhoneBook;
import com.nforetek.bt.profile.pbap.java.vcard.VCardEntry;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.BluetoothUuid;
import com.nforetek.bt.res.obex.BluetoothObexTransport;
import com.nforetek.bt.res.obex.ClientSession;
import com.nforetek.bt.res.obex.HeaderSet;
import com.nforetek.util.bt.NfReflection;
import com.nforetek.util.normal.NfLog;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class PbapClientConnectionHandler extends Handler implements BluetoothPbapRequestPullPhoneBook.PbapHandlerListener {
    static final boolean DBG = true;
    public static final String ICH_PATH = "telecom/ich.vcf";
    public static final String MCH_PATH = "telecom/mch.vcf";
    static final int MSG_CONNECT = 1;
    static final int MSG_DISCONNECT = 2;
    static final int MSG_DOWNLOAD = 3;
    public static final String OCH_PATH = "telecom/och.vcf";
    private static final byte[] PBAP_TARGET = {121, 97, 53, -16, -16, -59, NfDef.AVRCP_BROWSING_STATUS_SEARCH_IN_PROGRESS, -40, 9, 102, 8, 0, 32, 12, -102, 102};
    public static final String PB_PATH = "telecom/pb.vcf";
    static final String TAG = "PBAP PCE handler";
    public static final byte VCARD_TYPE_21 = 0;
    public static final byte VCARD_TYPE_30 = 1;
    private Account mAccount;
    private BluetoothPbapObexAuthenticator mAuth;
    private Context mContext;
    private final BluetoothDevice mDevice;
    private boolean mDownloadCanceled;
    private int mDownloadPath;
    private int mFilter;
    private boolean mIsSocketCloseing;
    private int mL2capPsm;
    private ClientSession mObexSession;
    private int mOffset;
    private final PbapClientStateMachine mPbapClientStateMachine;
    private BluetoothPbapRequestPullPhoneBook mRequest;
    private BluetoothSocket mSocket;
    private int mStartPos;

    PbapClientConnectionHandler(Looper looper, Context context, PbapClientStateMachine stateMachine, BluetoothDevice device) {
        super(looper);
        this.mAuth = null;
        this.mIsSocketCloseing = false;
        this.mDownloadCanceled = false;
        this.mL2capPsm = -1;
        this.mDevice = device;
        this.mContext = context;
        this.mPbapClientStateMachine = stateMachine;
        this.mAuth = new BluetoothPbapObexAuthenticator(this);
    }

    PbapClientConnectionHandler(Builder pceHandlerbuild) {
        super(pceHandlerbuild.mLooper);
        this.mAuth = null;
        this.mIsSocketCloseing = false;
        this.mDownloadCanceled = false;
        this.mL2capPsm = -1;
        this.mDevice = pceHandlerbuild.mDevice;
        this.mContext = pceHandlerbuild.mContext;
        this.mPbapClientStateMachine = pceHandlerbuild.mClientStateMachine;
        this.mDownloadPath = pceHandlerbuild.mDownloadPath;
        this.mFilter = pceHandlerbuild.mFilter;
        this.mOffset = pceHandlerbuild.mOffset;
        this.mStartPos = pceHandlerbuild.mStartPos;
        this.mL2capPsm = pceHandlerbuild.mL2capPsm;
        this.mAuth = new BluetoothPbapObexAuthenticator(this);
    }

    private String getDownloadPath() {
        switch (this.mDownloadPath) {
            case 1:
                return "SIM1/telecom/pb.vcf";
            case 2:
                return PB_PATH;
            case 3:
            default:
                return null;
            case 4:
                return "telecom/fav.vcf";
            case 5:
                return MCH_PATH;
            case 6:
                return ICH_PATH;
            case 7:
                return OCH_PATH;
            case 8:
                return "telecom/cch.vcf";
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private PbapClientStateMachine mClientStateMachine;
        private Context mContext;
        private BluetoothDevice mDevice;
        private int mDownloadPath;
        private int mFilter;
        private int mL2capPsm = -1;
        private Looper mLooper;
        private int mOffset;
        private int mStartPos;

        public Builder setDownloadPath(int downloadPath) {
            this.mDownloadPath = downloadPath;
            return this;
        }

        public Builder setFilter(int filter) {
            this.mFilter = filter;
            return this;
        }

        public Builder setOffset(int offset) {
            this.mOffset = offset;
            return this;
        }

        public Builder setStartPos(int startPos) {
            this.mStartPos = startPos;
            return this;
        }

        public Builder setLooper(Looper loop) {
            this.mLooper = loop;
            return this;
        }

        public Builder setClientSM(PbapClientStateMachine clientStateMachine) {
            this.mClientStateMachine = clientStateMachine;
            return this;
        }

        public Builder setRemoteDevice(BluetoothDevice device) {
            this.mDevice = device;
            return this;
        }

        public Builder setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public Builder setL2capPsm(int psm) {
            this.mL2capPsm = psm;
            return this;
        }

        public PbapClientConnectionHandler build() {
            PbapClientConnectionHandler pbapClientHandler = new PbapClientConnectionHandler(this);
            return pbapClientHandler;
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        NfLog.d(TAG, "Handling Message = " + msg.what);
        switch (msg.what) {
            case 1:
                if (connectSocket()) {
                    NfLog.d(TAG, "Socket connected");
                    if (connectObexSession()) {
                        NfLog.i(TAG, "Obex connected");
                        this.mPbapClientStateMachine.obtainMessage(5).sendToTarget();
                        return;
                    }
                    NfLog.i(TAG, "Obex fail");
                    this.mPbapClientStateMachine.obtainMessage(6).sendToTarget();
                    return;
                }
                NfLog.w(TAG, "Socket CONNECT Failure ");
                this.mPbapClientStateMachine.obtainMessage(6).sendToTarget();
                return;
            case 2:
                NfLog.d(TAG, "Starting Disconnect");
                try {
                    if (this.mObexSession != null) {
                        NfLog.d(TAG, "obexSessionDisconnect" + this.mObexSession);
                        this.mObexSession.disconnect(null);
                        this.mObexSession.close();
                    }
                    NfLog.d(TAG, "Closing Socket");
                    closeSocket();
                } catch (IOException e) {
                    NfLog.w(TAG, "DISCONNECT Failure ", e);
                }
                NfLog.d(TAG, "Completing Disconnect");
                Message messageClosed = new Message();
                messageClosed.what = 7;
                this.mPbapClientStateMachine.sendMessage(messageClosed);
                return;
            case 3:
                NfLog.i(TAG, "Downloading Start");
                try {
                    if (!this.mDownloadCanceled) {
                        BluetoothPbapRequestPullPhoneBookSize req = new BluetoothPbapRequestPullPhoneBookSize(getDownloadPath());
                        req.execute(this.mObexSession);
                        this.mPbapClientStateMachine.onResponePhoneBookSize(req.getSize());
                        if (this.mRequest == null) {
                            this.mRequest = new BluetoothPbapRequestPullPhoneBook(this, getDownloadPath(), this.mAccount, this.mFilter, (byte) 1, this.mOffset, this.mStartPos);
                        }
                        this.mRequest.execute(this.mObexSession);
                        return;
                    }
                    return;
                } catch (IOException e2) {
                    NfLog.w(TAG, "DOWNLOAD_CONTACTS Failure: " + e2.toString());
                    Message m = new Message();
                    m.what = 7;
                    this.mPbapClientStateMachine.sendMessage(m);
                    return;
                }
            default:
                NfLog.w(TAG, "Received Unexpected Message");
                return;
        }
    }

    private boolean connectSocket() {
        try {
            NfLog.v(TAG, "connectSocket: UUID: " + BluetoothUuid.PBAP_PSE.getUuid() + " mL2capPsm: " + this.mL2capPsm);
            if (this.mSocket != null) {
                NfLog.v(TAG, "Socket need close");
                this.mSocket.close();
            }
            if (this.mL2capPsm == -1) {
                this.mSocket = this.mDevice.createRfcommSocketToServiceRecord(BluetoothUuid.PBAP_PSE.getUuid());
            } else {
                this.mSocket = NfReflection.createBluetoothSocket(3, -1, true, true, this.mDevice, this.mL2capPsm, null);
            }
        } catch (IOException e) {
            NfLog.e(TAG, "Error while connecting socket", e);
        }
        if (this.mSocket != null) {
            NfLog.v(TAG, "Start Socket");
            this.mSocket.connect();
            return true;
        }
        NfLog.w(TAG, "Could not create socket");
        return false;
    }

    private boolean connectObexSession() {
        boolean connectionSuccessful = false;
        try {
            NfLog.v(TAG, "Start Obex Client Session");
            BluetoothObexTransport transport = new BluetoothObexTransport(this.mSocket);
            this.mObexSession = new ClientSession(transport);
            this.mObexSession.setAuthenticator(this.mAuth);
            HeaderSet connectionRequest = new HeaderSet();
            connectionRequest.setHeader(70, PBAP_TARGET);
            HeaderSet connectionResponse = this.mObexSession.connect(connectionRequest);
            connectionSuccessful = connectionResponse.getResponseCode() == 160;
            NfLog.d(TAG, "Success = " + Boolean.toString(connectionSuccessful));
        } catch (IOException e) {
            NfLog.w(TAG, "CONNECT Failure " + e.toString());
            closeSocket();
        }
        return connectionSuccessful;
    }

    public void abort() {
        closeSocket();
        getLooper().getThread().interrupt();
    }

    private void closeSocket() {
        NfLog.d(TAG, "closeSocket()");
        try {
            if (this.mSocket != null) {
                NfLog.d(TAG, "Closing socket" + this.mSocket);
                if (!this.mIsSocketCloseing) {
                    this.mIsSocketCloseing = true;
                    this.mSocket.close();
                    this.mSocket = null;
                } else {
                    NfLog.d(TAG, "now Socket closeing");
                }
            }
        } catch (IOException e) {
            NfLog.e(TAG, "Error when closing socket", e);
            this.mSocket = null;
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
        NfLog.d(TAG, "closeSocket is successful");
        this.mIsSocketCloseing = false;
    }

    @Override // com.nforetek.bt.profile.pbap.java.BluetoothPbapRequestPullPhoneBook.PbapHandlerListener
    public void onPhoneBookPullDone(VCardEntry entry) {
        this.mPbapClientStateMachine.onPhoneBookPullDone(entry);
    }

    @Override // com.nforetek.bt.profile.pbap.java.BluetoothPbapRequestPullPhoneBook.PbapHandlerListener
    public void onPhoneBookPullDoneCheckSize(int size) {
        this.mPbapClientStateMachine.onPhoneBookPullDoneCheckSize(size);
    }

    public void pbapDownloadInterrupt() {
        this.mDownloadCanceled = true;
        if (this.mRequest != null) {
            this.mRequest.onDownloadCanceled();
            try {
                this.mRequest.interruptObex();
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        closeSocket();
    }
}
