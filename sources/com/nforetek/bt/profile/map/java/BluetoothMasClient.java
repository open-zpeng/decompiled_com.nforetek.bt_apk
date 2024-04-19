package com.nforetek.bt.profile.map.java;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import com.nforetek.bt.profile.map.java.BluetoothMasRequestSetMessageStatus;
import com.nforetek.bt.profile.map.java.BluetoothMasRequestSetPath;
import com.nforetek.bt.profile.map.java.utils.ObexTime;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.bt.res.obex.ObexTransport;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.bt.NfReflection;
import com.nforetek.util.normal.NfLog;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
/* loaded from: classes.dex */
public class BluetoothMasClient {
    public static final int EVENT_CONNECT = 1;
    public static final int EVENT_EVENT_REPORT = 11;
    public static final int EVENT_GET_FOLDER_LISTING = 4;
    public static final int EVENT_GET_FOLDER_LISTING_SIZE = 5;
    public static final int EVENT_GET_MESSAGE = 7;
    public static final int EVENT_GET_MESSAGES_LISTING = 6;
    public static final int EVENT_GET_MESSAGES_LISTING_SIZE = 12;
    public static final int EVENT_MAS_DISCONNECTED = 17;
    public static final int EVENT_MNS_ADD_SDP = 15;
    public static final int EVENT_PUSH_MESSAGE = 9;
    public static final int EVENT_SET_MESSAGE_STATUS = 8;
    public static final int EVENT_SET_NOTIFICATION_REGISTRATION = 10;
    public static final int EVENT_SET_PATH = 3;
    public static final int EVENT_UPDATE_INBOX = 2;
    public static final int EVENT__MNS_REG = 13;
    public static final int EVENT__MNS_UNREG = 16;
    public static final int MAS_REASON_DISCONNECTED = 0;
    public static final int MAS_REASON_DISCONNECTED_ALREADY = 1;
    public static final int MAS_REASON_DISCONNECTED_NOTHING = 2;
    public static final int PARAMETER_ATTACHMENT_SIZE = 1024;
    public static final int PARAMETER_DATETIME = 2;
    public static final int PARAMETER_DEFAULT = 0;
    public static final int PARAMETER_PRIORITY = 2048;
    public static final int PARAMETER_PROTECTED = 16384;
    public static final int PARAMETER_READ = 4096;
    public static final int PARAMETER_RECEPTION_STATUS = 256;
    public static final int PARAMETER_RECIPIENT_ADDRESSING = 32;
    public static final int PARAMETER_RECIPIENT_NAME = 16;
    public static final int PARAMETER_REPLYTO_ADDRESSING = 32768;
    public static final int PARAMETER_SENDER_ADDRESSING = 8;
    public static final int PARAMETER_SENDER_NAME = 4;
    public static final int PARAMETER_SENT = 8192;
    public static final int PARAMETER_SIZE = 128;
    public static final int PARAMETER_SUBJECT = 1;
    public static final int PARAMETER_TEXT = 512;
    public static final int PARAMETER_TYPE = 64;
    private static final int SOCKET_CONNECTED = 10;
    private static final int SOCKET_ERROR = 11;
    private static final String STAG = "BluetoothMasClient";
    public static final int STATUS_FAILED = 1;
    public static final int STATUS_OK = 0;
    public static final boolean USE_MNS_SDP_VERSION = false;
    public static final int onConnectTimeout = 14;
    public static final int onNextCommand = 0;
    private String TAG;
    HandlerThread ht;
    private final Handler mCallback;
    private BluetoothDevice mDevice;
    private int mL2capPsm;
    private final SdpMasRecord mMas;
    private ArrayDeque<String> mPath;
    private SessionHandler mSessionHandler;
    BluetoothMasRequest mrequest;
    public static final Boolean DBG = true;
    private static BluetoothMnsService mMnsService = null;
    public static final ParcelUuid MAS = ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB");
    private ConnectionState mConnectionState = ConnectionState.DISCONNECTED;
    private boolean mNotificationEnabled = false;
    private boolean mIsRegistered = false;
    private SocketConnectThread mConnectThread = null;
    private ObexTransport mObexTransport = null;
    private BluetoothMasObexClientSession mObexSession = null;
    private Date mServerTime = null;
    private boolean disconnected = false;

    /* loaded from: classes.dex */
    public enum CharsetType {
        NATIVE,
        UTF_8;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static CharsetType[] valuesCustom() {
            CharsetType[] valuesCustom = values();
            int length = valuesCustom.length;
            CharsetType[] charsetTypeArr = new CharsetType[length];
            System.arraycopy(valuesCustom, 0, charsetTypeArr, 0, length);
            return charsetTypeArr;
        }
    }

    /* loaded from: classes.dex */
    public enum ConnectionState {
        DISCONNECTED,
        CONNECTING,
        CONNECTED,
        DISCONNECTING;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static ConnectionState[] valuesCustom() {
            ConnectionState[] valuesCustom = values();
            int length = valuesCustom.length;
            ConnectionState[] connectionStateArr = new ConnectionState[length];
            System.arraycopy(valuesCustom, 0, connectionStateArr, 0, length);
            return connectionStateArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SessionHandler extends Handler {
        private static /* synthetic */ int[] $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMasRequestSetPath$SetPathDir;
        private final WeakReference<BluetoothMasClient> mClient;

        static /* synthetic */ int[] $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMasRequestSetPath$SetPathDir() {
            int[] iArr = $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMasRequestSetPath$SetPathDir;
            if (iArr == null) {
                iArr = new int[BluetoothMasRequestSetPath.SetPathDir.valuesCustom().length];
                try {
                    iArr[BluetoothMasRequestSetPath.SetPathDir.DOWN.ordinal()] = 3;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[BluetoothMasRequestSetPath.SetPathDir.ROOT.ordinal()] = 1;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[BluetoothMasRequestSetPath.SetPathDir.UP.ordinal()] = 2;
                } catch (NoSuchFieldError e3) {
                }
                $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMasRequestSetPath$SetPathDir = iArr;
            }
            return iArr;
        }

        public SessionHandler(BluetoothMasClient client, Looper looper) {
            super(looper);
            this.mClient = new WeakReference<>(client);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            BluetoothMasClient client = this.mClient.get();
            if (client != null) {
                switch (msg.what) {
                    case 10:
                        NfLog.v(client.TAG, "SOCKET_CONNECTED");
                        client.mConnectThread = null;
                        client.mObexTransport = (ObexTransport) msg.obj;
                        client.mObexSession = new BluetoothMasObexClientSession(client.mObexTransport, client.mSessionHandler);
                        client.mObexSession.start();
                        return;
                    case 11:
                        NfLog.v(client.TAG, "SOCKET_ERROR");
                        client.mConnectThread = null;
                        client.sendToClient(1, false, 1);
                        return;
                    case 100:
                        NfLog.v(client.TAG, "MSG_OBEX_CONNECTED");
                        client.mPath.clear();
                        client.mConnectionState = ConnectionState.CONNECTED;
                        client.sendToClient(1, true, 0);
                        return;
                    case 101:
                        NfLog.v(client.TAG, "MSG_OBEX_DISCONNECTED");
                        client.mConnectionState = ConnectionState.DISCONNECTED;
                        client.mNotificationEnabled = false;
                        client.mIsRegistered = false;
                        client.mObexSession = null;
                        client.mL2capPsm = -1;
                        client.sendToClient(1, false, msg.arg1);
                        return;
                    case 102:
                        BluetoothMasRequest request = (BluetoothMasRequest) msg.obj;
                        int status = request.isSuccess() ? 0 : 1;
                        NfLog.v(client.TAG, " ");
                        if (request instanceof BluetoothMasRequestUpdateInbox) {
                            NfLog.v(client.TAG, "BluetoothMasRequestUpdateInbox " + status);
                            client.sendToClient(2, request.isSuccess());
                            return;
                        } else if (request instanceof BluetoothMasRequestSetPath) {
                            NfLog.v(client.TAG, "BluetoothMasRequestSetPath " + status);
                            if (request.isSuccess()) {
                                BluetoothMasRequestSetPath req = (BluetoothMasRequestSetPath) request;
                                switch ($SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMasRequestSetPath$SetPathDir()[req.mDir.ordinal()]) {
                                    case 1:
                                        client.mPath.clear();
                                        break;
                                    case 2:
                                        if (client.mPath.size() > 0) {
                                            client.mPath.removeLast();
                                            break;
                                        }
                                        break;
                                    case 3:
                                        client.mPath.addLast(req.mName);
                                        break;
                                }
                            }
                            client.sendToClient(3, request.isSuccess(), client.getCurrentPath());
                            return;
                        } else if (request instanceof BluetoothMasRequestGetFolderListing) {
                            NfLog.v(client.TAG, "BluetoothMasRequestGetFolderListing blocked");
                            return;
                        } else if (request instanceof BluetoothMasRequestGetFolderListingSize) {
                            int size = ((BluetoothMasRequestGetFolderListingSize) request).getSize();
                            client.sendToClient(5, request.isSuccess(), size);
                            return;
                        } else if (request instanceof BluetoothMasRequestGetMessagesListing) {
                            NfLog.v(client.TAG, "BluetoothMasRequestGetMessagesListing " + status);
                            client.mrequest = null;
                            BluetoothMasRequestGetMessagesListing req2 = (BluetoothMasRequestGetMessagesListing) request;
                            client.setMseTime(req2.getMseTime());
                            ArrayList<BluetoothMapMessage> msgs = req2.getList();
                            client.sendToClient(6, request.isSuccess(), msgs);
                            return;
                        } else if (request instanceof BluetoothMasRequestGetMessage) {
                            NfLog.v(client.TAG, "BluetoothMasRequestGetMessage " + status);
                            BluetoothMapBmessage bmsg = ((BluetoothMasRequestGetMessage) request).getMessage();
                            client.sendToClient(7, request.isSuccess(), bmsg);
                            return;
                        } else if (request instanceof BluetoothMasRequestSetMessageStatus) {
                            NfLog.v(client.TAG, "BluetoothMasRequestSetMessageStatus " + status);
                            client.sendToClient(8, request.isSuccess());
                            return;
                        } else if (request instanceof BluetoothMasRequestPushMessage) {
                            NfLog.v(client.TAG, "BluetoothMasRequestPushMessage " + status);
                            String handle = ((BluetoothMasRequestPushMessage) request).getMsgHandle();
                            client.sendToClient(9, request.isSuccess(), handle);
                            return;
                        } else if (request instanceof BluetoothMasRequestSetNotificationRegistration) {
                            NfLog.v(client.TAG, "BluetoothMasRequestSetNotificationRegistration request success?" + status);
                            BluetoothMasRequestSetNotificationRegistration req3 = (BluetoothMasRequestSetNotificationRegistration) request;
                            NfLog.d(client.TAG, "for SetNotificationRegistration: " + req3.getStatus());
                            client.mNotificationEnabled = req3.isSuccess() ? req3.getStatus() : client.mNotificationEnabled;
                            client.sendToClient(10, request.isSuccess(), client.mNotificationEnabled ? 1 : 0);
                            return;
                        } else if (request instanceof BluetoothMasRequestGetMessagesListingSize) {
                            int size2 = ((BluetoothMasRequestGetMessagesListingSize) request).getSize();
                            client.sendToClient(12, request.isSuccess(), size2);
                            return;
                        } else {
                            return;
                        }
                    case 1001:
                        if (msg.obj == null) {
                            NfLog.v(client.TAG, "onClose");
                            client.mIsRegistered = false;
                        }
                        NfLog.v(client.TAG, "EVENT_REPORT");
                        client.sendToClient(11, true, msg.obj);
                        return;
                    case 1002:
                        NfLog.v(client.TAG, "EVENT_MNS_REG");
                        client.mIsRegistered = true;
                        client.sendToClient(13, true, msg.obj);
                        return;
                    case 1003:
                        NfLog.v(client.TAG, "EVENT_MNS_UNREG");
                        client.mIsRegistered = false;
                        try {
                            if (BluetoothMasClient.mMnsService != null) {
                                BluetoothMasClient.mMnsService.unregisterCallback(0);
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        client.sendToClient(16, true, msg.obj);
                        return;
                    case 1004:
                        NfLog.v(client.TAG, "MNS_ADD_SDP " + msg.arg1);
                        client.sendToClient(15, true, msg.arg1, null);
                        return;
                    case 1005:
                        NfLog.v(client.TAG, "EVENT_MNS_INIT_FAIL");
                        if (BluetoothMasClient.mMnsService != null) {
                            BluetoothMasClient.mMnsService.clean();
                            sendEmptyMessageDelayed(1006, 2000L);
                            return;
                        }
                        return;
                    case 1006:
                        NfLog.v(client.TAG, "EVENT_MNS_INIT_RETRY");
                        if (NfPrimitive.isBtEnabled()) {
                            if (BluetoothMasClient.mMnsService != null) {
                                BluetoothMasClient.mMnsService.clean();
                            }
                            BluetoothMasClient.mMnsService = new BluetoothMnsService(client.mSessionHandler);
                            return;
                        }
                        sendEmptyMessageDelayed(1006, 2000L);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendToClient(int event, boolean success) {
        sendToClient(event, success, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendToClient(int event, boolean success, int param) {
        sendToClient(event, success, Integer.valueOf(param));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendToClient(int event, boolean success, Object param) {
        sendToClient(event, success, this.mMas.getMasInstanceId(), param);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendToClient(int event, boolean success, int arg2, Object param) {
        if (this.mCallback == null) {
            NfLog.v(this.TAG, "mCallback null " + event);
            return;
        }
        int arg1 = success ? 0 : 1;
        try {
            this.mCallback.obtainMessage(event, arg1, arg2, param).sendToTarget();
        } catch (Exception e) {
            NfLog.e(this.TAG, "Error pass event to NfMap: " + event, e);
        }
    }

    /* loaded from: classes.dex */
    private class SocketConnectThread extends Thread {
        String TAG;
        private BluetoothSocket socket;

        public SocketConnectThread() {
            super("SocketConnectThread");
            this.socket = null;
            this.TAG = "connect";
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (BluetoothMasClient.this.getMasID() == 0) {
                if (BluetoothMasClient.this.mDevice != null) {
                    try {
                        if (BluetoothMasClient.this.mL2capPsm >= 0) {
                            NfLog.d(this.TAG, "createBluetoothSocket with PSM: " + BluetoothMasClient.this.mL2capPsm);
                            this.socket = NfReflection.createBluetoothSocket(3, -1, true, true, BluetoothMasClient.this.mDevice, BluetoothMasClient.this.mL2capPsm, null);
                        } else {
                            NfLog.d(this.TAG, "createRfcommSocketToServiceRecord");
                            this.socket = BluetoothMasClient.this.mDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001132-0000-1000-8000-00805F9B34FB"));
                        }
                        if (this.socket == null) {
                            NfLog.e(this.TAG, "socket is null, return.");
                            sendToClient(11);
                            return;
                        }
                        NfLog.d(this.TAG, "socket created.");
                        try {
                            NfLog.d(this.TAG, "socket connect..");
                            this.socket.connect();
                            NfLog.d(this.TAG, "socket Connected");
                        } catch (Exception e) {
                            NfLog.e(this.TAG, "Error when connecting socket", e);
                            sendToClient(11);
                            return;
                        }
                    } catch (Exception e2) {
                        NfLog.e(this.TAG, "Error creating socket", e2);
                        sendToClient(11);
                        return;
                    }
                } else {
                    NfLog.d(this.TAG, "mDevice is null");
                    sendToClient(11);
                    return;
                }
            } else {
                NfLog.d(this.TAG, "getMasID!=0, should not be here");
            }
            BluetoothMapRfcommTransport transport = new BluetoothMapRfcommTransport(this.socket);
            if (BluetoothMasClient.this.mSessionHandler != null) {
                BluetoothMasClient.this.mSessionHandler.obtainMessage(10, transport).sendToTarget();
            }
        }

        @Override // java.lang.Thread
        public void interrupt() {
            closeSocket();
        }

        private void closeSocket() {
            try {
                if (this.socket != null) {
                    this.socket.close();
                }
            } catch (Exception e) {
                NfLog.e(this.TAG, "Error when closing socket", e);
            }
        }

        private void sendToClient(int r) {
            try {
                BluetoothMasClient.this.mSessionHandler.obtainMessage(r).sendToTarget();
            } catch (Exception e) {
                NfLog.e(this.TAG, "Not pass msg to Mas: " + r);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class MessagesFilter {
        public static final byte MESSAGE_TYPE_ALL = 0;
        public static final byte MESSAGE_TYPE_EMAIL = 4;
        public static final byte MESSAGE_TYPE_MMS = 8;
        public static final byte MESSAGE_TYPE_SMS_CDMA = 2;
        public static final byte MESSAGE_TYPE_SMS_GSM = 1;
        public static final byte PRIORITY_ANY = 0;
        public static final byte PRIORITY_HIGH = 1;
        public static final byte PRIORITY_NON_HIGH = 2;
        public static final byte READ_STATUS_ANY = 0;
        public static final byte READ_STATUS_READ = 2;
        public static final byte READ_STATUS_UNREAD = 1;
        byte messageType = 0;
        String periodBegin = null;
        String periodEnd = null;
        byte readStatus = 0;
        String recipient = null;
        String originator = null;
        byte priority = 0;

        public void setMessageType(byte filter) {
            this.messageType = filter;
        }

        public void setPeriod(Date filterBegin, Date filterEnd) {
            if (filterBegin != null) {
                this.periodBegin = new ObexTime(filterBegin).toString();
            }
            if (filterEnd != null) {
                this.periodEnd = new ObexTime(filterEnd).toString();
            }
        }

        public void setReadStatus(byte readfilter) {
            this.readStatus = readfilter;
        }

        public void setRecipient(String filter) {
            if ("".equals(filter)) {
                this.recipient = null;
            } else {
                this.recipient = filter;
            }
        }

        public void setOriginator(String filter) {
            if ("".equals(filter)) {
                this.originator = null;
            } else {
                this.originator = filter;
            }
        }

        public void setPriority(byte filter) {
            this.priority = filter;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (this.periodBegin != null) {
                builder.append(", periodBegin: " + this.periodBegin);
            }
            if (this.periodEnd != null) {
                builder.append(", periodEnd: " + this.periodEnd);
            }
            if (this.recipient != null) {
                builder.append(", recipient: " + this.recipient);
            }
            if (this.originator != null) {
                builder.append(", originator: " + this.originator);
            }
            if (this.messageType != 0) {
                builder.append(", messageType: " + ((int) this.messageType));
            }
            if (this.readStatus != 0) {
                builder.append(", readStatus: " + ((int) this.readStatus));
            }
            if (this.priority != 0) {
                builder.append(", priority: " + ((int) this.priority));
            }
            return builder.toString();
        }
    }

    public BluetoothMasClient(BluetoothDevice device, SdpMasRecord mas, Handler callback) {
        this.TAG = STAG;
        this.mL2capPsm = -1;
        this.mSessionHandler = null;
        this.mPath = null;
        this.TAG = String.valueOf(this.TAG) + mas.getMasInstanceId();
        NfLog.d(this.TAG, "   ");
        NfLog.d(this.TAG, "===init BluetoothMasClient");
        this.mDevice = device;
        this.mMas = mas;
        this.mCallback = callback;
        if (this.mMas != null && this.mMas.getL2capPsm() > 0) {
            this.mL2capPsm = this.mMas.getL2capPsm();
        }
        this.mPath = new ArrayDeque<>();
        if (this.ht == null) {
            NfLog.d(this.TAG, "alloc ht");
            this.ht = new HandlerThread("NfMasHandler");
            this.ht.start();
        }
        if (this.mSessionHandler == null) {
            NfLog.d(this.TAG, "alloc SessionHandler");
            this.mSessionHandler = new SessionHandler(this, this.ht.getLooper());
        }
        if (NfConfig.isUseMapAddSdp()) {
            if (mMnsService == null) {
                NfLog.d(this.TAG, "alloc BluetoothMnsService");
                mMnsService = new BluetoothMnsService(this.mSessionHandler);
            } else {
                mMnsService.registerCallback(0, this.mSessionHandler);
            }
        }
        NfLog.d(this.TAG, "===init BluetoothMasClient end");
    }

    public SdpMasRecord getInstanceData() {
        return this.mMas;
    }

    public void connect() {
        NfLog.d(this.TAG, "===connect start");
        if (this.mConnectThread == null && this.mObexSession == null) {
            this.disconnected = false;
            NfLog.d(this.TAG, "new SocketConnectThread");
            this.mConnectionState = ConnectionState.CONNECTING;
            this.mConnectThread = new SocketConnectThread();
            this.mConnectThread.start();
        }
        NfLog.d(this.TAG, "===connect end");
    }

    public void disconnect() {
        NfLog.d(this.TAG, "===disconnect start");
        if (this.disconnected) {
            NfLog.d(this.TAG, "===disconnect already done");
            return;
        }
        this.disconnected = true;
        if (this.mConnectThread == null && this.mObexSession == null) {
            NfLog.d(this.TAG, "===disconnect... nothing");
            sendToClient(17, true, 2);
            return;
        }
        if (this.mConnectionState == ConnectionState.CONNECTED) {
            disableNotifications();
        }
        if (mMnsService != null) {
            mMnsService.clean();
            mMnsService.unregisterCallback(0);
            mMnsService = null;
        }
        this.mConnectionState = ConnectionState.DISCONNECTING;
        if (this.mConnectThread != null) {
            NfLog.d(this.TAG, "===disconnect interrupt connect");
            this.mConnectThread.interrupt();
            this.mConnectThread = null;
        }
        if (this.mObexSession != null) {
            NfLog.d(this.TAG, "===disconnect stop obex");
            this.mObexSession.stop();
            this.mObexSession = null;
        }
        sendToClient(17, true, 0);
        NfLog.d(this.TAG, "===disconnect end");
    }

    public void finalize() {
        NfLog.d(this.TAG, "===finalize");
        if (this.ht != null) {
            this.ht.getLooper().quit();
            this.ht = null;
        }
        if (this.mSessionHandler != null) {
            NfLog.d(this.TAG, "===finalize stop mSessionHandler");
            this.mSessionHandler = null;
        }
    }

    public ConnectionState getState() {
        return this.mConnectionState;
    }

    private boolean enableNotifications() {
        NfLog.v(this.TAG, "enableNotifications()");
        if (mMnsService != null) {
            NfLog.v(this.TAG, "mMnsService != null");
            mMnsService.clean();
            mMnsService.unregisterCallback(0);
            mMnsService = null;
        }
        if (mMnsService == null) {
            if (this.mSessionHandler != null) {
                this.mSessionHandler.removeMessages(1006);
            }
            mMnsService = new BluetoothMnsService(this.mSessionHandler);
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                NfLog.e(this.TAG, "enableNotifications catch ", e);
            }
        }
        mMnsService.registerCallback(0, this.mSessionHandler);
        BluetoothMasRequest request = new BluetoothMasRequestSetNotificationRegistration(true);
        return makeRequest(request);
    }

    private boolean disableNotifications() {
        boolean ret = false;
        NfLog.v(this.TAG, "disableNotifications()");
        if (this.mObexSession != null) {
            BluetoothMasRequest request = new BluetoothMasRequestSetNotificationRegistration(false);
            ret = makeRequest(request);
            if (ret) {
                NfLog.v(this.TAG, "request maked for SetNotificationRegistration");
            }
        }
        return ret;
    }

    public boolean setNotificationRegistration(boolean status) {
        NfLog.e(this.TAG, "setNotificationRegistration " + status);
        if (this.mObexSession == null) {
            return false;
        }
        if (status) {
            return enableNotifications();
        }
        return disableNotifications();
    }

    public boolean getNotificationRegistration() {
        NfLog.e(this.TAG, "getNotificationRegistration " + this.mNotificationEnabled);
        return this.mNotificationEnabled;
    }

    public boolean isRegistered() {
        NfLog.e(this.TAG, "isRegistered " + this.mIsRegistered);
        return this.mIsRegistered && mMnsService != null && mMnsService.isActive();
    }

    public void setRegistered() {
        this.mIsRegistered = true;
        this.mNotificationEnabled = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getMasID() {
        if (this.mMas != null) {
            return this.mMas.getMasInstanceId();
        }
        return -1;
    }

    public boolean setFolderRoot() {
        if (this.mObexSession == null) {
            return false;
        }
        BluetoothMasRequest request = new BluetoothMasRequestSetPath(true);
        return makeRequest(request);
    }

    public boolean setFolderUp() {
        if (this.mObexSession == null) {
            return false;
        }
        BluetoothMasRequest request = new BluetoothMasRequestSetPath(false);
        return makeRequest(request);
    }

    public boolean setFolderDown(String name) {
        if (this.mObexSession == null || name == null || name.isEmpty() || name.contains("/")) {
            return false;
        }
        BluetoothMasRequest request = new BluetoothMasRequestSetPath(name);
        return makeRequest(request);
    }

    public String getCurrentPath() {
        if (this.mPath.size() == 0) {
            return "";
        }
        Iterator<String> iter = this.mPath.iterator();
        StringBuilder sb = new StringBuilder(iter.next());
        while (iter.hasNext()) {
            sb.append("/").append(iter.next());
        }
        return sb.toString();
    }

    public boolean getFolderListing() {
        return getFolderListing(0, 0);
    }

    public boolean getFolderListing(int maxListCount, int listStartOffset) {
        if (this.mObexSession == null) {
            return false;
        }
        BluetoothMasRequest request = new BluetoothMasRequestGetFolderListing(maxListCount, listStartOffset);
        return makeRequest(request);
    }

    public boolean getFolderListingSize() {
        if (this.mObexSession == null) {
            return false;
        }
        BluetoothMasRequest request = new BluetoothMasRequestGetFolderListingSize();
        return makeRequest(request);
    }

    public boolean getMessagesListing(String folder, int parameters) {
        return getMessagesListing(folder, parameters, null, 0, 0, 0);
    }

    public boolean getMessagesListing(String folder, int parameters, MessagesFilter filter, int subjectLength) {
        return getMessagesListing(folder, parameters, filter, subjectLength, 0, 0);
    }

    public boolean getMessagesListing(String folder, int parameters, MessagesFilter filter, int subjectLength, int maxListCount, int listStartOffset) {
        if (this.mObexSession == null) {
            NfLog.d(this.TAG, "getMessagesListing mObexSession == null");
            return false;
        }
        NfLog.d(this.TAG, "getMessagesListing start");
        this.mServerTime = null;
        this.mrequest = new BluetoothMasRequestGetMessagesListing(folder, parameters, filter, subjectLength, maxListCount, listStartOffset);
        return makeRequest(this.mrequest);
    }

    public boolean downloadInterrupt(String address) {
        NfLog.d(this.TAG, "downloadInterrupt");
        if (this.mObexSession == null || this.mrequest == null) {
            return false;
        }
        Thread t = new Thread() { // from class: com.nforetek.bt.profile.map.java.BluetoothMasClient.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    sleep(1L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    BluetoothMasClient.this.mrequest.abort();
                    NfLog.d(BluetoothMasClient.this.TAG, "downloadInterrupt, return");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        return true;
    }

    public boolean getMessagesListingSize() {
        if (this.mObexSession == null) {
            return false;
        }
        BluetoothMasRequest request = new BluetoothMasRequestGetMessagesListingSize();
        return makeRequest(request);
    }

    public boolean getMessage(String handle, boolean attachment) {
        if (this.mObexSession == null) {
            return false;
        }
        NfLog.d(this.TAG, "getMessage");
        BluetoothMasRequest request = new BluetoothMasRequestGetMessage(handle, CharsetType.UTF_8, attachment);
        return makeRequest(request);
    }

    public boolean getMessage(ArrayList<BluetoothMapMessage> list, int offset, int one_shot) {
        if (this.mObexSession == null) {
            return false;
        }
        if (list == null) {
            NfLog.e(this.TAG, "getMessage list null ");
            return false;
        }
        NfLog.e(this.TAG, "getMessage detail with offset " + offset);
        if (offset >= list.size()) {
            return false;
        }
        if (one_shot == 1) {
            BluetoothMasRequest request = new BluetoothMasRequestGetMessage(list.get(offset).mHandle, CharsetType.UTF_8, false);
            makeRequest(request);
            return true;
        }
        int i = -1;
        int end = offset + one_shot;
        if (end > list.size()) {
            end = list.size();
        }
        Iterator<BluetoothMapMessage> it = list.iterator();
        while (it.hasNext()) {
            BluetoothMapMessage m = it.next();
            i++;
            if (offset <= i) {
                if (i >= end) {
                    break;
                }
                NfLog.d(this.TAG, "CHECK MSG ITEM " + i);
                BluetoothMasRequest request2 = new BluetoothMasRequestGetMessage(m.mHandle, CharsetType.UTF_8, false);
                makeRequest(request2);
            }
        }
        return true;
    }

    public boolean setMessageReadStatus(String handle, boolean read) {
        if (this.mObexSession == null) {
            return false;
        }
        BluetoothMasRequest request = new BluetoothMasRequestSetMessageStatus(handle, BluetoothMasRequestSetMessageStatus.StatusIndicator.READ, read);
        return makeRequest(request);
    }

    public boolean setMessageDeletedStatus(String handle, boolean deleted) {
        if (this.mObexSession == null) {
            return false;
        }
        BluetoothMasRequest request = new BluetoothMasRequestSetMessageStatus(handle, BluetoothMasRequestSetMessageStatus.StatusIndicator.DELETED, deleted);
        return makeRequest(request);
    }

    public boolean pushMessage(String folder, BluetoothMapBmessage bmsg, CharsetType charset) {
        return pushMessage(folder, bmsg, charset, false, false);
    }

    public boolean pushMessage(String folder, BluetoothMapBmessage bmsg, CharsetType charset, boolean transparent, boolean retry) {
        if (this.mObexSession == null) {
            return false;
        }
        String bmsgString = BluetoothMapBmessageBuilder.createBmessage(bmsg);
        BluetoothMasRequest request = new BluetoothMasRequestPushMessage(folder, bmsgString, charset, transparent, retry);
        return makeRequest(request);
    }

    public boolean updateInbox() {
        if (this.mObexSession == null) {
            return false;
        }
        BluetoothMasRequest request = new BluetoothMasRequestUpdateInbox();
        return makeRequest(request);
    }

    public synchronized void setMseTime(Date d) {
        this.mServerTime = d;
    }

    public synchronized Date getMseTime() {
        return this.mServerTime;
    }

    private boolean makeRequest(BluetoothMasRequest request) {
        NfLog.d(this.TAG, "map makeRequest start");
        try {
            if (this.mObexSession == null) {
                throw new Exception("mObexSession is null");
            }
            return this.mObexSession.makeRequest(request);
        } catch (Exception e) {
            NfLog.e(this.TAG, "makeRequest fail: mObexSession");
            e.printStackTrace();
            return false;
        }
    }

    public synchronized void setDevice(BluetoothDevice d) {
        this.mDevice = d;
    }
}
