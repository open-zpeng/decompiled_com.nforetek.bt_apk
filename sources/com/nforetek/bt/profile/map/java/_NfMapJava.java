package com.nforetek.bt.profile.map.java;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.RemoteException;
import com.nforetek.bt.callback.NfDoCallbackMap;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.jni.NfJniBundle;
import com.nforetek.bt.profile.map._NfMap;
import com.nforetek.bt.profile.map.java.BluetoothMapBmessage;
import com.nforetek.bt.profile.map.java.BluetoothMapEventReport;
import com.nforetek.bt.profile.map.java.BluetoothMapMessage;
import com.nforetek.bt.profile.map.java.BluetoothMasClient;
import com.nforetek.bt.profile.map.java.NfMapCommand;
import com.nforetek.bt.profile.map.java.vcard.VCardEntry;
import com.nforetek.bt.profile.map.java.vcard.VCardProperty;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.Constants;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.MessageHelper;
import com.nforetek.util.normal.NfLog;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class _NfMapJava extends _NfMap {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMapBmessage$Status = null;
    private static /* synthetic */ int[] $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMapEventReport$Type = null;
    private static final int CONNECTED = 3;
    private static final int CONNECTED_MNS = 4;
    private static final int CONNECTING = 1;
    private static final boolean DBG = true;
    private static final int DISCONNECTED = 0;
    private static final int DISCONNECTING = 2;
    private static final int DISCONNECT_TIME = 800;
    private static final int DOWNLOADING = 5;
    private static final String FOLDER_INBOX = "inbox";
    private static final String FOLDER_MSG = "msg";
    private static final String FOLDER_OUTBOX = "outbox";
    private static final String FOLDER_TELECOM = "telecom";
    private static final int HANDLER_MESSAGE_DB_AVAILABLE = 1;
    private static final int HANDLER_MESSAGE_DB_CLEAN = 3;
    private static final int HANDLER_MESSAGE_DB_DELETE = 2;
    private static final int MAP_ONT_SHOT = 1;
    public static final int MnsFlightNone = 0;
    public static final int MnsFlightRegister = 1;
    public static final int MnsFlightTimeout = 4;
    public static final int MnsFlightUnregister = 2;
    public static final int MnsResetCurrentFlight = 3;
    private static final int RECONNECT_TO_OTHER_TIME = 2500;
    private static final int SDP = 7;
    private static final int UPLOADING = 6;
    private int cnt_all;
    private int cnt_cur;
    private int cnt_progress;
    private int cnt_total;
    HandlerThread ht;
    private HandlerThread htMns;
    private boolean isDetail;
    private BluetoothMasClient mClient;
    private BluetoothMasClient mClient2;
    NfMapCommandProcessor mCommandProcessor;
    NfMapCommand mCurrentCommand;
    private BluetoothDevice mDevice;
    private InitMapConnectThread mInitMapConnectThread;
    private BluetoothMapMessage mIphoneMsg;
    private BluetoothMasClient mMasClient;
    private Handler mMnsFlightHandler;
    private ArrayList<BluetoothMapMessage> mMsgListArray;
    private HashMap<String, BluetoothMapMessage> mMsgListHash;
    private int mDownloadInterrupt = 0;
    private int mCurrentFolder = 0;
    private int mGetMsgListCnt = 0;
    private String mTestHandle = "";
    private int mMapConnectionStatus = 0;
    private boolean mAttemptEnableNotifications = false;
    private boolean mDownloadNewMessage = false;
    private int flagInitMap = 0;
    private int mL2capPsm = -1;
    private String mFolder = "";
    private long mInterruptTickCount = 0;
    private int mInterruptBlockCount = 0;
    private boolean mIsChangeToOtherPhone = false;
    private String mChangeToOtherPhoneAddress = NfDef.DEFAULT_ADDRESS;
    private int countDownloadSingle = 0;
    Handler mCheckDbAvailableHandler = null;
    private String mAddress = NfDef.DEFAULT_ADDRESS;
    private String mRegisteringAddress = NfDef.DEFAULT_ADDRESS;
    private int mNotifyFreq = 0;
    private final int CONNECT_TIMEOUT = 16500;
    private final int COMMAND_FLIGHT_TIMEOUT = 16500;
    boolean isForceDisconnect = true;
    private int currentMnsFlight = 0;
    private long lastMnsFlightTime = 0;
    private boolean mMapBusyBlock = false;
    private RegisterNotificationThread mRegisterNotificationThread = null;
    private int mMnsPortID = -1;

    static /* synthetic */ int[] $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMapBmessage$Status() {
        int[] iArr = $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMapBmessage$Status;
        if (iArr == null) {
            iArr = new int[BluetoothMapBmessage.Status.valuesCustom().length];
            try {
                iArr[BluetoothMapBmessage.Status.READ.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BluetoothMapBmessage.Status.UNREAD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMapBmessage$Status = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMapEventReport$Type() {
        int[] iArr = $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMapEventReport$Type;
        if (iArr == null) {
            iArr = new int[BluetoothMapEventReport.Type.valuesCustom().length];
            try {
                iArr[BluetoothMapEventReport.Type.DELIVERY_FAILURE.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BluetoothMapEventReport.Type.DELIVERY_SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BluetoothMapEventReport.Type.MEMORY_AVAILABLE.ordinal()] = 7;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BluetoothMapEventReport.Type.MEMORY_FULL.ordinal()] = 6;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[BluetoothMapEventReport.Type.MESSAGE_DELETED.ordinal()] = 8;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[BluetoothMapEventReport.Type.MESSAGE_SHIFT.ordinal()] = 9;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[BluetoothMapEventReport.Type.NEW_MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[BluetoothMapEventReport.Type.SENDING_FAILURE.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[BluetoothMapEventReport.Type.SENDING_SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMapEventReport$Type = iArr;
        }
        return iArr;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfMap";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return 7;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
        onServiceStat(0, 2);
        forceDisconnect();
        if (this.mCheckDbAvailableHandler != null) {
            this.mCheckDbAvailableHandler.removeCallbacksAndMessages(null);
            this.mCheckDbAvailableHandler = null;
        }
        if (this.mMnsFlightHandler != null) {
            this.mMnsFlightHandler.removeCallbacksAndMessages(null);
            this.mMnsFlightHandler = null;
        }
        if (this.mRegisterNotificationThread != null) {
            this.mRegisterNotificationThread.shutdown();
            this.mRegisterNotificationThread = null;
        }
        this.ht.getLooper().quit();
        this.ht = null;
        if (this.htMns != null && this.htMns.getLooper() != null) {
            this.htMns.getLooper().quit();
        }
        this.htMns = null;
        this.mCommandProcessor.release();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.profile._NfProfile
    public void onCreate() {
        super.onCreate();
        this.ht = new HandlerThread("NfMapHandler");
        this.ht.start();
        this.htMns = new HandlerThread("NfMnsHandler");
        this.htMns.start();
        this.mCheckDbAvailableHandler = initCheckDbAvaliableHandler();
        this.mMnsFlightHandler = initMnsFlightHandler();
        this.mCommandProcessor = new NfMapCommandProcessor();
        if (NfConfig.isUseMapAddSdp()) {
            this.mMasClient = new BluetoothMasClient(null, new SdpMasRecord(0, 6, 6, 1, 6, 6, "MAP"), this.mJniHandler);
        }
    }

    private Handler initCheckDbAvaliableHandler() {
        return this.mCheckDbAvailableHandler == null ? new Handler(this.ht.getLooper()) { // from class: com.nforetek.bt.profile.map.java._NfMapJava.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String address = bundle.getString(NfDef.EXTRA_DEVICE_ADDRESS);
                NfLog.v(_NfMapJava.this.TAG, "CheckDbAvailableHandler handleMessage: " + msg.what);
                switch (msg.what) {
                    case 1:
                        if (!_NfMapJava.this.isNoAnyCommandProcessing()) {
                            NfLog.v(_NfMapJava.this.TAG, "Waiting for command processing. command queue size: " + _NfMapJava.this.mCommandProcessor.getCommandQueueSize());
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        } else if (hasMessages(2) || hasMessages(3)) {
                            NfLog.v(_NfMapJava.this.TAG, "Waiting for DB delete.");
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        } else if (!MessageHelper.isUpdating()) {
                            ((NfDoCallbackMap) _NfMapJava.this.callback()).retMapDatabaseAvailable();
                            return;
                        } else {
                            NfLog.v(_NfMapJava.this.TAG, "Waiting for DB updating.");
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        }
                    case 2:
                        if (!_NfMapJava.this.mCommandProcessor.isCommandQueueEmpty()) {
                            NfLog.v(_NfMapJava.this.TAG, "Waiting for command processing. command queue size: " + _NfMapJava.this.mCommandProcessor.getCommandQueueSize());
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        } else if (!MessageHelper.isUpdating()) {
                            MessageHelper.deleteMessageByMacAddress(_NfMapJava.this.context(), address);
                            return;
                        } else {
                            NfLog.v(_NfMapJava.this.TAG, "Waiting for DB updating.");
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        }
                    case 3:
                        if (!_NfMapJava.this.mCommandProcessor.isCommandQueueEmpty()) {
                            NfLog.v(_NfMapJava.this.TAG, "Waiting for command processing. command queue size: " + _NfMapJava.this.mCommandProcessor.getCommandQueueSize());
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        } else if (!MessageHelper.isUpdating()) {
                            MessageHelper.deleteAllMessage(_NfMapJava.this.context());
                            return;
                        } else {
                            NfLog.v(_NfMapJava.this.TAG, "Waiting for DB updating.");
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        }
                    default:
                        NfLog.e(_NfMapJava.this.TAG, "Unknown message type: " + msg.what);
                        return;
                }
            }

            private void processSendMessageDelayed(Message msg, int delay) {
                Message newMsg = obtainMessage();
                newMsg.what = msg.what;
                removeMessages(msg.what);
                sendMessageDelayed(newMsg, delay);
            }
        } : this.mCheckDbAvailableHandler;
    }

    @Override // com.nforetek.bt.profile.map._NfMap, com.nforetek.bt.profile._NfProfile
    public String getConnectedAddress() {
        return this.mAddress;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean isConnected() {
        return getProfileState() >= 140;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public String getConnetedAddress() {
        return NfDef.DEFAULT_ADDRESS;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public void setDownloadNotifyFrequency(int notify) {
        if (notify < 0) {
            this.mNotifyFreq = 0;
        } else {
            this.mNotifyFreq = notify;
        }
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public synchronized boolean downloadOne(String address, int folder, String handle, int storage) {
        boolean z = false;
        synchronized (this) {
            if (!NfConfig.isPtsTest() && !isAddrWrong(address)) {
                String handle2 = ifTestHandle(handle);
                if (!isStorageWrong(storage)) {
                    NfMapCommand.DownloadSingle cmd = new NfMapCommand.DownloadSingle(address, 2, handle2, 0, folder, storage);
                    z = fireCommand(cmd, address);
                }
            }
        }
        return z;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public synchronized boolean downloadAll(String address, int folder, boolean isContentDownload, int count, int startPos, int storage, String periodBegin, String periodEnd, String sender, String recipient, int readStatus, int type) {
        boolean fireCommand;
        NfLog.e(this.TAG, "reqMapDownloadMessage(): " + address);
        if (isAddrWrong(address)) {
            fireCommand = false;
        } else if (isStorageWrong(storage)) {
            fireCommand = false;
        } else if (count == 0) {
            fireCommand = false;
        } else {
            NfMapCommand.DownloadAll cmd = new NfMapCommand.DownloadAll(address, 3, 0, folder, storage, isContentDownload, count, startPos);
            if (!cmd.setFilter(periodBegin, periodEnd, sender, recipient, readStatus, type)) {
                NfLog.e(this.TAG, "reqMapDownloadMessage got paramter(s) of filter invalid");
                fireCommand = false;
            } else {
                fireCommand = fireCommand(cmd, address);
            }
        }
        return fireCommand;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public synchronized boolean sendMessage(String address, String msg, String target) {
        boolean z = false;
        synchronized (this) {
            if (!isAddrWrong(address)) {
                if (msg.length() > 1024) {
                    NfLog.e(this.TAG, "The length of message cannot exceed 1024 bytes. current message length is " + msg.length());
                } else {
                    NfMapCommand.Send cmd = new NfMapCommand.Send(address, 0, msg, target);
                    z = fireCommand(cmd, address);
                }
            }
        }
        return z;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public synchronized boolean deleteMessage(String address, int folder, String handle) {
        boolean z = false;
        synchronized (this) {
            NfLog.d(this.TAG, "deleteMessage");
            if (!isAddrWrong(address)) {
                String handle2 = ifTestHandle(handle);
                if (!isHandleWrong(handle2)) {
                    NfMapCommand.Delete cmd = new NfMapCommand.Delete(address, 1, folder, handle2);
                    z = fireCommand(cmd, address);
                }
            }
        }
        return z;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public synchronized boolean changeReadStatus(String address, int folder, String handle, boolean isReadStatus) {
        boolean z = false;
        synchronized (this) {
            NfLog.d(this.TAG, "changeReadStatus");
            if (!isAddrWrong(address)) {
                String handle2 = ifTestHandle(handle);
                if (!isHandleWrong(handle2)) {
                    NfMapCommand.ReadStatus cmd = new NfMapCommand.ReadStatus(address, 4, folder, handle2, isReadStatus);
                    z = fireCommand(cmd, address);
                }
            }
        }
        return z;
    }

    private String ifTestHandle(String in) {
        if (in.equals("###1")) {
            NfLog.e(this.TAG, "ifTestHandle, handle change to " + this.mTestHandle);
            return this.mTestHandle;
        }
        return in;
    }

    public synchronized boolean startInitMapProfile(String address, int delay) {
        boolean z = false;
        synchronized (this) {
            NfLog.v(this.TAG, "startInitMapProfile() " + address + " delay: " + delay);
            if (this.mInitMapConnectThread != null) {
                NfLog.d(this.TAG, "mInitMapConnectThread != null !!");
            } else if (!isAddrWrong(address)) {
                this.mFolder = "";
                this.mInitMapConnectThread = new InitMapConnectThread(address, delay);
                this.mInitMapConnectThread.start();
                z = true;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class InitMapConnectThread extends Thread {
        String address;
        int mDelay;

        public InitMapConnectThread(String addr, int delay) {
            super("InitMapConnectThread");
            NfLog.d(_NfMapJava.this.TAG, "InitMapConnectThread: " + addr);
            this.address = addr;
            this.mDelay = delay;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            long currentTimeMillis;
            if (this.mDelay != 0) {
                currentTimeMillis = System.currentTimeMillis() + this.mDelay;
            } else {
                currentTimeMillis = _NfMapJava.this.lastMnsFlightTime;
            }
            double delayTo = currentTimeMillis;
            while (delayTo > System.currentTimeMillis()) {
                try {
                    NfLog.d(_NfMapJava.this.TAG, "InitMapConnectThread waiting to delay time");
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    NfLog.d(_NfMapJava.this.TAG, "InitMapConnectThread interrupted");
                    _NfMapJava.this.mInitMapConnectThread = null;
                    return;
                }
            }
            _NfMapJava.this.initMapProfile(this.address);
            _NfMapJava.this.mInitMapConnectThread = null;
        }

        public String getTargetAddress() {
            return this.address;
        }
    }

    public synchronized boolean initMapProfile(String address) {
        boolean z = false;
        synchronized (this) {
            NfLog.d(this.TAG, "initMapProfile: " + address);
            if (this.mClient == null) {
                if (!isAddrWrong(address)) {
                    setMapBusyBlock(false);
                    this.mFolder = "";
                    NfLog.e(this.TAG, "initMapProfile do connect!");
                    this.mDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
                    this.mAddress = address;
                    connect();
                }
            }
            z = true;
        }
        return z;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean downloadInterrupt(String address) {
        long InterruptTickCount = System.currentTimeMillis();
        if (InterruptTickCount < this.mInterruptTickCount) {
            return false;
        }
        this.mInterruptBlockCount = 0;
        NfLog.e(this.TAG, "interrupted@" + InterruptTickCount);
        this.mInterruptTickCount = 750 + InterruptTickCount;
        if (this.mClient == null) {
            NfLog.e(this.TAG, "mas null, try forceDisconnect");
            forceDisconnect();
        }
        if (this.mDownloadInterrupt != 0) {
            NfLog.e(this.TAG, "already interrupted");
            return false;
        }
        this.mDownloadInterrupt = 1;
        if (getProfileState() == 160 || getProfileState() == 170) {
            NfLog.d(this.TAG, "downloadInterrupt try to cancel current cmd");
            if (this.mClient == null) {
                return false;
            }
            if (this.mClient.downloadInterrupt(address)) {
                NfLog.d(this.TAG, "downloadInterrupt when request processing");
            } else {
                onServiceStat(3, 4, 1500);
            }
        } else if (getProfileState() == 120 || getProfileState() == 140) {
            NfLog.d(this.TAG, "downloadInterrupt try to drop current cmd");
            this.mCommandProcessor.poll();
            if (NfConfig.isPtsTest()) {
                NfLog.d(this.TAG, "downloadInterrupt: isPtsTest do disconnect");
                onServiceStat(2, 4);
                forceDisconnect();
            }
        } else if (getProfileState() == 110) {
            NfLog.d(this.TAG, "downloadInterrupt try to remove all cmds in queue");
            onServiceStat(2, 4);
            forceDisconnect();
        } else if (getProfileState() == 140) {
            NfLog.d(this.TAG, "downloadInterrupt try to remove all cmds in queue");
            onServiceStat(2, 4);
            forceDisconnect();
        } else {
            NfLog.e(this.TAG, "downloadInterrupt reject cause MAP state wrong: " + getProfileState());
            return false;
        }
        NfLog.d(this.TAG, "downloadInterrupt return");
        return true;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean forceDisconnect() {
        if (this.mAddress.equals(NfDef.DEFAULT_ADDRESS)) {
            return false;
        }
        return forceDisconnect(this.mAddress, 2);
    }

    public boolean forceDisconnect(String address) {
        if (getProfileState(address) > 110) {
            return forceDisconnect(address, 2);
        }
        return false;
    }

    public boolean forceDisconnect(String address, int reason) {
        NfLog.d(this.TAG, "forceDisconnect() " + address + " reason: " + reason);
        NfLog.d(this.TAG, "forceDisconnect called, reset command queue");
        setMapBusyBlock(false);
        resetCurrentMnsFlight();
        setLastMnsFlightTime();
        this.mCurrentCommand = null;
        if (this.mCommandProcessor != null) {
            this.mCommandProcessor.removeDevice(address);
        }
        if (getProfileState() > 110) {
            NfLog.d(this.TAG, "forceDisconnect invoke disconnect");
            this.isForceDisconnect = true;
            disconnect(reason);
            if (this.mCheckDbAvailableHandler != null) {
                this.mCheckDbAvailableHandler.removeMessages(1);
                this.mCheckDbAvailableHandler.removeMessages(2);
                this.mCheckDbAvailableHandler.removeMessages(3);
            }
        } else {
            NfLog.d(this.TAG, "masClient set null");
            try {
                if (this.mMasClient != null) {
                    this.mMasClient.finalize();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            this.mMasClient = null;
            this.mClient = null;
        }
        try {
            this.mInitMapConnectThread.interrupt();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
        this.mInitMapConnectThread = null;
        return false;
    }

    private String getStringFromFolder(int f) {
        switch (f) {
            case 0:
                return FOLDER_INBOX;
            case 1:
                return "sent";
            case 2:
                return "deleted";
            case 3:
                return FOLDER_OUTBOX;
            case 4:
                return "draft";
            default:
                return "unknow";
        }
    }

    private int getFolderFromStr(String s) {
        String s2 = s.toLowerCase().trim();
        int v = -1;
        if (s2.endsWith(FOLDER_INBOX)) {
            v = 0;
        } else if (s2.endsWith("sent")) {
            v = 1;
        } else if (s2.endsWith("deleted")) {
            v = 2;
        } else if (s2.endsWith(FOLDER_OUTBOX)) {
            v = 3;
        } else if (s2.endsWith("draft")) {
            v = 4;
        }
        NfLog.d(this.TAG, String.valueOf(v) + " /getFolderFromStr /" + s2);
        return v;
    }

    private boolean isAddrWrongOrSet(String address) {
        boolean ret = isAddrWrong(address);
        if (!ret) {
            this.mAddress = address;
        }
        return ret;
    }

    private boolean isStorageWrong(int storage) {
        if (storage < 0 || storage > 1) {
            NfLog.e(this.TAG, "storage not valid!! " + storage);
            return true;
        }
        return false;
    }

    private boolean isAddrWrong(String address) {
        this.mIsChangeToOtherPhone = false;
        if (address == null || address.equals(NfDef.DEFAULT_ADDRESS) || !BluetoothAdapter.checkBluetoothAddress(address)) {
            NfLog.e(this.TAG, "Address not valid!! " + address);
            return true;
        } else if (this.mAddress == null || this.mAddress.equals(NfDef.DEFAULT_ADDRESS)) {
            NfLog.e(this.TAG, "phone new connection: " + address);
            return false;
        } else if (this.mAddress.equals(address)) {
            NfLog.e(this.TAG, "phone is the same: " + this.mAddress);
            return false;
        } else {
            NfLog.e(this.TAG, "phone change from: " + this.mAddress);
            NfLog.e(this.TAG, "phone change to  : " + address);
            this.mIsChangeToOtherPhone = true;
            this.mChangeToOtherPhoneAddress = address;
            if (this.mMsgListHash != null) {
                this.mMsgListHash.clear();
            }
            this.mMsgListHash = null;
            return false;
        }
    }

    private boolean isHandleWrong(String handle) {
        try {
            new BigInteger(handle, 16);
            return false;
        } catch (NumberFormatException e) {
            NfLog.e(this.TAG, "Handle not valid!! handle: " + handle);
            return true;
        }
    }

    private boolean fireCommand(NfMapCommand cmd, String address) {
        if (NfMapCommand.isCommandEquals(this.mCurrentCommand, cmd)) {
            NfLog.e(this.TAG, "Current Command already processing ! cmd: " + cmd);
            return false;
        } else if (this.mCommandProcessor.isCommandInQueue(cmd)) {
            NfLog.e(this.TAG, "Command already in command queue! cmd: " + cmd);
            return false;
        } else {
            if (!this.mAddress.equals(address) && !this.mAddress.equals(NfDef.DEFAULT_ADDRESS)) {
                NfLog.d(this.TAG, "fireCommand mIsChangeToOtherPhone:yes, try to forceDisconnect");
                forceDisconnect();
                NfLog.d(this.TAG, "Add into command queue. cmd: " + cmd);
                this.mCommandProcessor.add(cmd);
            } else if (isMapBusyBlock() && !cmd.getAddress().equals(this.mChangeToOtherPhoneAddress)) {
                NfLog.d(this.TAG, "MAP Busy block, refuse address command!");
                return false;
            } else {
                NfLog.d(this.TAG, "Add into command queue. cmd: " + cmd);
                this.mCommandProcessor.add(cmd);
            }
            if (this.mClient == null && this.mDevice == null) {
                NfLog.d(this.TAG, "Map mClient is null, try to connect");
                if (NfConfig.isPtsTest()) {
                    this.mCommandProcessor.poll();
                }
                startInitMapProfile(address, 0);
            } else if (isNoAnyCommandProcessing()) {
                NfLog.d(this.TAG, "===direct process===in fireCommand");
                nextCommandFlight();
            }
            return true;
        }
    }

    private void setLastMnsFlightTime() {
        this.lastMnsFlightTime = System.currentTimeMillis() + 1000;
    }

    private boolean forceDisconnectDirect(int reason) {
        NfLog.d(this.TAG, "forceDisconnectDirect called, reset command queue");
        setMapBusyBlock(false);
        resetCurrentMnsFlight();
        setLastMnsFlightTime();
        if (this.mCommandProcessor != null) {
            this.mCommandProcessor.reset();
        }
        NfLog.d(this.TAG, "forceDisconnect invoke disconnect");
        this.isForceDisconnect = true;
        disconnect(reason);
        if (this.mCheckDbAvailableHandler != null) {
            this.mCheckDbAvailableHandler.removeMessages(1);
            this.mCheckDbAvailableHandler.removeMessages(2);
            this.mCheckDbAvailableHandler.removeMessages(3);
        }
        onServiceStatDelay(0, 0, DISCONNECT_TIME);
        return true;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public void queryDatabaseAvailable() {
        if (this.mCheckDbAvailableHandler.hasMessages(1)) {
            this.mCheckDbAvailableHandler.removeMessages(1);
        }
        this.mCheckDbAvailableHandler.obtainMessage(1).sendToTarget();
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean deleteDatabaseByAddress(String address) {
        if (address == null || address.equals(NfDef.DEFAULT_ADDRESS) || !BluetoothAdapter.checkBluetoothAddress(address)) {
            NfLog.e(this.TAG, "Address not valid!! address: " + address);
            return false;
        }
        Message msg = Message.obtain(this.mCheckDbAvailableHandler, 2);
        Bundle bundle = new Bundle();
        bundle.putString(NfDef.EXTRA_DEVICE_ADDRESS, address);
        msg.setData(bundle);
        this.mCheckDbAvailableHandler.sendMessage(msg);
        return true;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean cleanDatabase() {
        if (this.mCheckDbAvailableHandler.hasMessages(2)) {
            this.mCheckDbAvailableHandler.removeMessages(2);
        }
        Message msg = Message.obtain(this.mCheckDbAvailableHandler, 3);
        this.mCheckDbAvailableHandler.sendMessage(msg);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean isNoAnyCommandProcessing() {
        boolean ret;
        if (this.mCommandProcessor.isCommandQueueEmpty()) {
            NfLog.d(this.TAG, "isCommandQueueEmpty: yes");
        }
        NfLog.d(this.TAG, "mCurrentCommand " + this.mCurrentCommand);
        ret = this.mCurrentCommand == null;
        NfLog.d(this.TAG, String.valueOf(ret) + " isNoAnyCommandProcessing " + getConnectionStateString(getProfileState()));
        return ret;
    }

    private void processSendMessageDelayed(int what, int delay) {
        if (!isJniHandlerNull()) {
            if (14 == what) {
                try {
                    if (this.mJniHandler.hasMessages(14)) {
                        NfLog.d(this.TAG, "onConnectTimeout already in queue: resend " + delay);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            Message m = Message.obtain(this.mJniHandler, what);
            this.mJniHandler.removeMessages(m.what);
            this.mJniHandler.sendMessageDelayed(m, delay);
        }
    }

    private void processSendMessageDelayed(int what, int delay, NfJniBundle b) {
        if (!isJniHandlerNull()) {
            try {
                Message m = Message.obtain(this.mJniHandler, what, b);
                this.mJniHandler.removeMessages(m.what);
                this.mJniHandler.sendMessageDelayed(m, delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processSendMessageDelayed(int what, int delay, NfMapCommand b) {
        if (!isJniHandlerNull()) {
            try {
                Message m = Message.obtain(this.mJniHandler, what, b);
                this.mJniHandler.removeMessages(m.what);
                this.mJniHandler.sendMessageDelayed(m, delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void nextCommandFlight() {
        processSendMessageDelayed(0, DISCONNECT_TIME);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapStateChanged(String address, int state, int reason) {
        int newState = getNfDefState(state);
        if (newState == 140 && isNotificationRegistered()) {
            newState = 150;
        }
        if (newState == getProfileState()) {
            if (isConnected()) {
                this.mCurrentCommand = null;
                if (isTryToRegister()) {
                    NfLog.d(this.TAG, "onJniMapStateChanged: connected try to register");
                    enableNotifications(true);
                } else if (!this.mCommandProcessor.isCommandQueueEmpty()) {
                    nextCommandFlight();
                } else if (!NfConfig.isPtsTest()) {
                    NfLog.d(this.TAG, "command queue empty. disconnect...");
                    disconnect(2);
                }
            }
            NfLog.d(this.TAG, "status same??");
        } else if (110 == getProfileState() && newState == 125) {
            NfLog.d(this.TAG, "strange status ready->disconnecting??");
        } else {
            if (this.mInitMapConnectThread != null && (isConnected() || newState == 110 || newState == 125)) {
                try {
                    this.mInitMapConnectThread.interrupt();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                this.mInitMapConnectThread = null;
            }
            NfLog.d(this.TAG, "onJniMapStateChanged() " + address + ", " + getConnectionStateString(newState) + ", " + getReasonString(reason));
            setState(address, newState, reason);
            if (newState == 110) {
                resetCurrentMnsFlight();
                NfLog.d(this.TAG, "onJniMapStateChanged STATE_READY invoke cleanup");
                this.mAddress = NfDef.DEFAULT_ADDRESS;
                this.mMapConnectionStatus = 0;
                this.mCurrentCommand = null;
                this.mRegisteringAddress = NfDef.DEFAULT_ADDRESS;
                cleanMas();
            }
            if (newState == 150) {
                resetCurrentMnsFlight();
                this.mCurrentCommand = null;
                this.mRegisteringAddress = NfDef.DEFAULT_ADDRESS;
                setTryToRegister(false);
                if (!this.mCommandProcessor.isCommandQueueEmpty()) {
                    nextCommandFlight();
                }
            } else if (newState == 140) {
                this.mCurrentCommand = null;
                this.mRegisteringAddress = NfDef.DEFAULT_ADDRESS;
                if (isTryToRegister()) {
                    enableNotifications(true);
                } else if (!this.mCommandProcessor.isCommandQueueEmpty()) {
                    nextCommandFlight();
                } else if (!NfConfig.isPtsTest()) {
                    NfLog.d(this.TAG, "command queue empty. disconnect...");
                    disconnect(2);
                }
            }
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniReceiveMessageContent(String address, String handle, String senderName, String senderAddr, String date, String recipientAddr, int type, int folder, String subject, String message, int readStatus, int currentCount, int totalCount) {
        int storage = -1;
        NfLog.d(this.TAG, "onJniReceiveMessageContent readStatus:  " + readStatus);
        if (!isInterruptedLog("sendMessageContentFromBMSG interrupted")) {
            if (this.mCurrentCommand instanceof NfMapCommand.DownloadAll) {
                storage = ((NfMapCommand.DownloadAll) this.mCurrentCommand).getStorage();
                if (((NfMapCommand.DownloadAll) this.mCurrentCommand).getCount() < 65535) {
                }
            } else if (this.mCurrentCommand instanceof NfMapCommand.DownloadSingle) {
                storage = ((NfMapCommand.DownloadSingle) this.mCurrentCommand).getStorage();
            }
            this.mTestHandle = handle;
            if (!isInterruptedLog("sendMessageContentFromBMSG interrupted")) {
                switch (storage) {
                    case 0:
                        NfLog.d(this.TAG, "MAP_STORAGE_TYPE_BY_PASS");
                        callback().retMapDownloadedMessage(address, handle, senderName, senderAddr, date, recipientAddr, type, folder, readStatus, subject, message);
                        break;
                    case 1:
                        NfLog.d(this.TAG, "MAP_STORAGE_TYPE_TO_DATABASE");
                        if (this.mCurrentCommand.getType() == 2) {
                            int rows = MessageHelper.updateMessageToDatabases(context(), address, folder, handle, message);
                            if (rows == 1) {
                                NfLog.d(this.TAG, "Data exist! update success");
                                break;
                            } else if (rows == 0) {
                                NfLog.d(this.TAG, "Data no exist! insert a new rows");
                                MessageHelper.addMessageToDatabases(context(), address, handle, senderName, senderAddr, date, recipientAddr, type, folder, subject, message, readStatus);
                                break;
                            } else {
                                NfLog.e(this.TAG, "Update message with weird rows: " + rows);
                                break;
                            }
                        } else if (this.mCurrentCommand.getType() == 3) {
                            if (currentCount == 1) {
                                NfLog.e(this.TAG, "currentCount: " + currentCount);
                                MessageHelper.deleteMessageByMacAddressAndFolder(context(), address, folder);
                            }
                            MessageHelper.addMessageToDatabases(context(), address, handle, senderName, senderAddr, date, recipientAddr, type, folder, subject, message, readStatus);
                            break;
                        }
                        break;
                    default:
                        NfLog.e(this.TAG, "Unknown storage type: " + storage);
                        break;
                }
                if (isInterruptedLog("sendMessageContentFromBMSG interrupted") || this.mNotifyFreq == 0) {
                    return;
                }
                if (currentCount % this.mNotifyFreq == 0 || currentCount == totalCount) {
                    callback().onMapDownloadNotify(address, folder, totalCount, currentCount);
                }
            }
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onReceiveNewMessage(String address, String handle, int type, int folder) {
        NfLog.d(this.TAG, "onReceiveNewMessage() " + address + " handle: " + handle + " type: " + type + " folder: " + folder);
        callback().onMapNewMessageReceived(address, handle, "", "");
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapSendMessage(String address, String target, int state) {
        NfLog.d(this.TAG, "onJniMapSendMessageState() " + address + " target: " + target + " state: " + state);
        callback().retMapSendMessageCompleted(address, target, state);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapDeleteMessageState(String address, String handle, int reason) {
        NfLog.d(this.TAG, "onJniMapDeleteMessageState() " + address + " handle: " + handle + " reason: " + reason);
        callback().retMapDeleteMessageCompleted(address, handle, reason);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapChangeReadStatusState(String address, String handle, int reason) {
        NfLog.d(this.TAG, "onJniMapChangeReadStatusState() " + address + " handle: " + handle + " reason: " + reason);
        callback().retMapChangeReadStatusCompleted(address, handle, reason);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapMemoryAvailable(String address, int type, int structure, int available) {
        NfLog.d(this.TAG, "onJniMapMemoryAvailable() " + address + " structure: " + structure + " available: " + available);
        callback().onMapMemoryAvailable(address, structure, available == 1);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapMessageSendingStatus(String address, String handle, int isSuccess) {
        this.mTestHandle = handle;
        NfLog.d(this.TAG, "onJniMapMessageSendingStatus() " + address + " handle: " + handle + " status: " + isSuccess);
        callback().onMapMessageSendingStatus(address, handle, isSuccess == 1);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapMessageDeliverStatus(String address, String handle, int isSuccess) {
        NfLog.d(this.TAG, "onJniMapMessageDeliverStatus() " + address + " handle: " + handle + " isSuccess: " + isSuccess);
        callback().onMapMessageDeliverStatus(address, handle, isSuccess == 1);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapMessageShifted(String address, String handle, int type, int newFolder, int oldFolder) {
        NfLog.d(this.TAG, "onJniMapMessageShifted() " + address + " handle: " + handle + " type: " + type + " folder: " + oldFolder + " -> " + newFolder);
        callback().onMapMessageShifted(address, handle, type, newFolder, oldFolder);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapMessageDeleted(String address, String handle, int type, int folder) {
        NfLog.d(this.TAG, "onJniMapMessageDeleted() " + address + " handle: " + handle + " type: " + type + " folder: " + folder);
        callback().onMapMessageDeleted(address, handle, type, folder);
    }

    private static int getNfDefState(int state) {
        switch (state) {
            case 0:
                return 110;
            case 1:
                return 120;
            case 2:
                return NfDef.STATE_DISCONNECTING;
            case 3:
                return 140;
            case 4:
                return 150;
            case 5:
                return 160;
            case 6:
                return NfDef.STATE_UPLOADING;
            default:
                return 100;
        }
    }

    private static String getConnectionStateString(int state) {
        switch (state) {
            case 110:
                return "Ready";
            case 120:
                return "Connecting";
            case NfDef.STATE_DISCONNECTING /* 125 */:
                return "Disconnecting";
            case 140:
                return "Connected";
            case 150:
                return "Connected and Registered";
            case 160:
                return "Downloading";
            case NfDef.STATE_UPLOADING /* 170 */:
                return "Uploading";
            default:
                return "Unknown " + state;
        }
    }

    private static String getReasonString(int r) {
        switch (r) {
            case 0:
                return "MAP_REASON_NONE";
            case 1:
                return "MAP_REASON_BAD_PARAMS";
            case 2:
                return "MAP_REASON_DISCONNECT_FROM_LOCAL";
            case 3:
                return "MAP_REASON_DISCONNECT_BY_PEER";
            case 4:
                return "MAP_REASON_INTERRUPT";
            case 5:
                return "MAP_REASON_DOWNLOAD_FINISH";
            case 6:
                return "MAP_REASON_PEER_NO_MAP_SERVICE";
            case 7:
                return "MAP_REASON_TIMEOUT";
            case 8:
                return "MAP_REASON_DOWNLOAD_FAIL";
            default:
                return "Unknown " + r;
        }
    }

    private synchronized void resetProgress(int state) {
        this.isForceDisconnect = true;
        this.countDownloadSingle = 0;
        this.mDownloadInterrupt = 0;
        this.mIphoneMsg = null;
        this.isDetail = false;
        this.cnt_all = 0;
        this.cnt_cur = 0;
        this.cnt_progress = 1;
        this.cnt_total = 0;
        if (state != -10) {
            onServiceStat(state);
        }
    }

    private synchronized void processCommand() {
        if (this.mClient == null && this.mDevice == null) {
            NfLog.d(this.TAG, "Map mClient is null, not try to connect" + this.mAddress);
        } else if (isNoAnyCommandProcessing()) {
            NfLog.d(this.TAG, "===direct process=== in processCommand");
            processCommand(this.mCommandProcessor.poll());
        }
    }

    private synchronized void processCommand(String address) {
        if (this.mClient == null && this.mDevice == null) {
            NfLog.d(this.TAG, "Map mClient is null, try to connect");
            startInitMapProfile(address, 0);
        } else if (isNoAnyCommandProcessing()) {
            NfLog.d(this.TAG, "===direct process=== in processCommand with address");
            processCommand(this.mCommandProcessor.poll());
        }
    }

    private synchronized void processCommand(NfMapCommand command) {
        try {
            NfLog.d(this.TAG, "processCommand(): " + command);
            this.mCurrentCommand = command;
            if (command == null) {
                NfLog.d(this.TAG, "processCommand(null): direct return");
                resetProgress(3);
            }
            this.mAddress = command.getAddress();
            switch (command.getType()) {
                case 0:
                    resetProgress(6);
                    break;
                case 1:
                    resetProgress(6);
                    break;
                case 2:
                    resetProgress(5);
                    break;
                case 3:
                    resetProgress(5);
                    break;
                case 4:
                    resetProgress(6);
                    break;
            }
            processSendMessageDelayed(0, 500, command);
        } catch (Exception e) {
            NfLog.e(this.TAG, "processCommand Exception: " + e);
            resetProgress(3);
        }
    }

    private synchronized void processCommandInternal(NfMapCommand command) {
        NfLog.d(this.TAG, "processCommandInternal(): " + command);
        this.mCurrentCommand = command;
        if (command == null) {
            NfLog.d(this.TAG, "processCommandInternal(null): direct return");
        } else {
            this.mAddress = command.getAddress();
            switch (command.getType()) {
                case 0:
                    NfMapCommand.Send cmd = (NfMapCommand.Send) command;
                    pushMessage(cmd.getMessage(), cmd.getTarget());
                    break;
                case 1:
                    NfMapCommand.Delete cmd2 = (NfMapCommand.Delete) command;
                    this.mCurrentFolder = cmd2.getFolder();
                    if (this.mClient != null) {
                        this.mClient.setMessageDeletedStatus(cmd2.getHandle(), true);
                        break;
                    }
                    break;
                case 2:
                    this.cnt_total = 1;
                    this.cnt_all = 1;
                    NfMapCommand.DownloadSingle cmd3 = (NfMapCommand.DownloadSingle) command;
                    this.mCurrentFolder = cmd3.getFolder();
                    getMessage(cmd3.getHandle());
                    break;
                case 3:
                    NfMapCommand.DownloadAll cmd4 = (NfMapCommand.DownloadAll) command;
                    this.isDetail = cmd4.isDetail();
                    this.mCurrentFolder = cmd4.getFolder();
                    getMessagesListing(getStringFromFolder(cmd4.getFolder()), cmd4.getCount(), cmd4.getStartPos(), cmd4.getFilter(), cmd4.isDetail() ? 255 : 20, 4335);
                    break;
                case 4:
                    NfMapCommand.ReadStatus cmd5 = (NfMapCommand.ReadStatus) command;
                    this.mCurrentFolder = cmd5.getFolder();
                    NfLog.d(this.TAG, "setMessageReadStatus, isRead: " + cmd5.isRead());
                    if (this.mClient != null) {
                        this.mClient.setMessageReadStatus(cmd5.getHandle(), cmd5.isRead());
                        break;
                    }
                    break;
            }
        }
    }

    private synchronized boolean pushMessage(String msg, String target) {
        boolean status = false;
        synchronized (this) {
            NfLog.d(true, this.TAG, "pushMessage called.");
            if (msg == null || target == null || msg.length() == 0 || target.length() == 0) {
                NfLog.d(this.TAG, "pushMessage msg null");
            } else if (this.mMapConnectionStatus == 3) {
                BluetoothMapBmessage bmsg = new BluetoothMapBmessage();
                bmsg.setType(BluetoothMapMessage.Type.SMS_GSM);
                bmsg.setStatus(BluetoothMapBmessage.Status.READ);
                VCardEntry dest_entry = new VCardEntry();
                VCardProperty dest_entry_phone = new VCardProperty();
                dest_entry_phone.setName("TEL");
                dest_entry_phone.addValues(target);
                NfLog.d(this.TAG, "Recipient: " + target);
                dest_entry.addProperty(dest_entry_phone);
                bmsg.addRecipient(dest_entry);
                bmsg.setBodyContent(msg);
                status = false;
                if (this.mClient != null) {
                    status = this.mClient.pushMessage(FOLDER_OUTBOX, bmsg, null);
                }
                if (!status) {
                    onPushMessage(-1);
                }
            }
        }
        return status;
    }

    private synchronized boolean pushMessage(BluetoothMapMessage msg) {
        NfLog.d(true, this.TAG, "pushMessage called.");
        if (msg == null) {
            NfLog.d(this.TAG, "pushMessage msg null");
        } else if (this.mMapConnectionStatus == 3) {
            BluetoothMapBmessage bmsg = new BluetoothMapBmessage();
            bmsg.setType(BluetoothMapMessage.Type.SMS_GSM);
            bmsg.setStatus(BluetoothMapBmessage.Status.READ);
            VCardEntry dest_entry = new VCardEntry();
            VCardProperty dest_entry_phone = new VCardProperty();
            dest_entry_phone.setName("TEL");
            dest_entry_phone.addValues(msg.mRecipientAddressing);
            NfLog.d(this.TAG, "Recipient: " + msg.mRecipientAddressing);
            dest_entry.addProperty(dest_entry_phone);
            bmsg.addRecipient(dest_entry);
            bmsg.setBodyContent(msg.msg);
            if (this.mClient != null) {
                this.mClient.pushMessage(FOLDER_OUTBOX, bmsg, null);
            }
            if (0 == 0) {
                onPushMessage(-1);
            }
        }
        return false;
    }

    public void onPushMessage(int isSuccess) {
        if (isSuccess == -1) {
            onServiceStat(3, 1);
        }
        if (this.mCurrentCommand.getType() == 0) {
            NfMapCommand.Send cmd = (NfMapCommand.Send) this.mCurrentCommand;
            onJniMapSendMessage(this.mAddress, cmd.getTarget(), isSuccess);
            if (isSuccess == 1) {
                onServiceStat(3);
                return;
            } else {
                onServiceStat(3, 1);
                return;
            }
        }
        NfLog.e(this.TAG, "strange: onPushMessage but cmd is not [TYPE_SEND], state: " + getProfileState());
        if (getProfileState() > 140) {
            onServiceStat(3, 1);
        }
    }

    private Handler initMnsFlightHandler() {
        return this.mMnsFlightHandler == null ? new Handler(this.htMns.getLooper()) { // from class: com.nforetek.bt.profile.map.java._NfMapJava.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String address = bundle.getString(NfDef.EXTRA_DEVICE_ADDRESS);
                switch (msg.what) {
                    case 1:
                        _NfMapJava.this.setMapBusyBlock(false);
                        NfLog.v(_NfMapJava.this.TAG, "handle MnsFlightRegister");
                        if (_NfMapJava.this.currentMnsFlight != 0) {
                            NfLog.d(_NfMapJava.this.TAG, "MnsFlightRegister wait previous flight");
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE, address);
                            return;
                        } else if (_NfMapJava.this.lastMnsFlightTime > System.currentTimeMillis()) {
                            NfLog.d(_NfMapJava.this.TAG, "MnsFlightRegister wait until lastMnsFlightTime");
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE, address);
                            return;
                        } else {
                            _NfMapJava.this.setTryToRegister(true);
                            if (_NfMapJava.this.registerNotificationInternal(address, true)) {
                                NfLog.d(_NfMapJava.this.TAG, "MnsFlightRegister success");
                                return;
                            }
                            NfLog.d(_NfMapJava.this.TAG, "MnsFlightRegister fail, maybe just block because already registered.");
                            _NfMapJava.this.mRegisteringAddress = NfDef.DEFAULT_ADDRESS;
                            _NfMapJava.this.currentMnsFlight = 0;
                            removeMessages(4);
                            _NfMapJava.this.setTryToRegister(false);
                            return;
                        }
                    case 2:
                        _NfMapJava.this.setMapBusyBlock(false);
                        NfLog.v(_NfMapJava.this.TAG, "handle MnsFlightUnregister");
                        if (_NfMapJava.this.currentMnsFlight != 0) {
                            NfLog.d(_NfMapJava.this.TAG, "MnsFlightUnregister wait previous flight");
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE, address);
                            return;
                        } else if (_NfMapJava.this.registerNotificationInternal(address, false)) {
                            NfLog.d(_NfMapJava.this.TAG, "MnsFlightUnregister success");
                            return;
                        } else {
                            NfLog.d(_NfMapJava.this.TAG, "MnsFlightUnregister fail, maybe just block because already un-registered.");
                            _NfMapJava.this.currentMnsFlight = 0;
                            removeMessages(4);
                            return;
                        }
                    case 3:
                        NfLog.d(_NfMapJava.this.TAG, "handle MnsResetCurrentFlight");
                        _NfMapJava.this.currentMnsFlight = 0;
                        removeMessages(4);
                        return;
                    case 4:
                        NfLog.d(_NfMapJava.this.TAG, "handle MnsFlightTimeout: " + _NfMapJava.this.currentMnsFlight);
                        _NfMapJava.this.mMnsFlightHandler.removeMessages(1);
                        _NfMapJava.this.mMnsFlightHandler.removeMessages(2);
                        if (!_NfMapJava.this.isNotificationRegistered() && _NfMapJava.this.currentMnsFlight == 1) {
                            _NfMapJava.this.registerNotificationInternal(address, false);
                        }
                        _NfMapJava.this.currentMnsFlight = 0;
                        _NfMapJava.this.setTryToRegister(false);
                        return;
                    default:
                        NfLog.e(_NfMapJava.this.TAG, "MnsFlightHandler Unknown " + msg.what);
                        return;
                }
            }

            private void processSendMessageDelayed(Message msg, int delay, String address) {
                Message newMsg = obtainMessage();
                newMsg.what = msg.what;
                Bundle bundle = new Bundle();
                bundle.putString(NfDef.EXTRA_DEVICE_ADDRESS, address);
                newMsg.setData(bundle);
                removeMessages(1);
                removeMessages(2);
                sendMessageDelayed(newMsg, delay);
            }
        } : this.mMnsFlightHandler;
    }

    public boolean sendToMnsFlight(String address, boolean isRegister) {
        if (this.mRegisterNotificationThread != null) {
            this.mRegisterNotificationThread.shutdown();
            this.mRegisterNotificationThread = null;
        }
        return sendToMnsFlight(address, isRegister, 0);
    }

    public boolean sendToMnsFlight(String address, boolean isRegister, int delay) {
        try {
            if (this.mMnsFlightHandler == null) {
                return false;
            }
            int what = isRegister ? 1 : 2;
            Message newMsg = this.mMnsFlightHandler.obtainMessage(what);
            Bundle bundle = new Bundle();
            bundle.putString(NfDef.EXTRA_DEVICE_ADDRESS, address);
            newMsg.setData(bundle);
            this.mMnsFlightHandler.removeMessages(1);
            this.mMnsFlightHandler.removeMessages(2);
            this.mMnsFlightHandler.removeMessages(4);
            this.mMnsFlightHandler.sendMessageDelayed(newMsg, delay);
            if (isRegister) {
                Bundle bundle2 = new Bundle();
                bundle2.putString(NfDef.EXTRA_DEVICE_ADDRESS, address);
                Message msg = this.mMnsFlightHandler.obtainMessage(4);
                msg.setData(bundle2);
                this.mMnsFlightHandler.sendMessageDelayed(msg, 16500L);
                return true;
            }
            return true;
        } catch (Exception e) {
            NfLog.d(this.TAG, "sendToMnsFlight fail");
            return false;
        }
    }

    private void resetCurrentMnsFlight() {
        try {
            NfLog.d(this.TAG, "resetCurrentMnsFlight");
            if (this.mMnsFlightHandler != null) {
                this.mMnsFlightHandler.obtainMessage(3).sendToTarget();
            }
        } catch (Exception e) {
            NfLog.d(this.TAG, "resetCurrentMnsFlight fail");
        }
    }

    public boolean registerNotificationInternal(String address, boolean enable) {
        if (isAddrWrongOrSet(address)) {
            return false;
        }
        NfLog.e(this.TAG, "registerNotificationInternal: got command is register?" + enable);
        int MnsCommand = enable ? 1 : 2;
        boolean isDirectProcess = false;
        if (this.currentMnsFlight == 0) {
            isDirectProcess = true;
        } else if (this.currentMnsFlight == 1 && MnsCommand == 2) {
            isDirectProcess = true;
        }
        if (!isDirectProcess) {
            NfLog.e(this.TAG, "MnsFlight: should not been not direct process " + MnsCommand);
            return false;
        }
        NfLog.e(this.TAG, "MnsFlight: Processing " + MnsCommand);
        setLastMnsFlightTime();
        this.currentMnsFlight = MnsCommand;
        if (isNotificationRegistered() && enable) {
            NfLog.e(this.TAG, "Mns block register, state: " + getProfileState());
            return false;
        }
        return enableNotifications(address, enable);
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean registerNotification(String address, boolean enable) {
        NfLog.d(this.TAG, "registerNotification() address: " + address + " enable: " + enable);
        if (!enable) {
            if (!this.mRegisteringAddress.equals(address) && !this.mRegisteringAddress.equals(NfDef.DEFAULT_ADDRESS)) {
                NfLog.d(this.TAG, "Unregister other device while registering, ignore it.");
                return false;
            }
        } else if (this.mRegisteringAddress.equals(address)) {
            NfLog.d(this.TAG, "Register same device while registering, ignore it.");
            return false;
        }
        if (getProfileState(address) == 150 && enable) {
            NfLog.d(this.TAG, "This device already registered.");
            return false;
        } else if (isAddrWrong(address)) {
            return false;
        } else {
            NfLog.d(this.TAG, "registerNotification send command");
            this.mRegisteringAddress = address;
            if (this.mIsChangeToOtherPhone) {
                this.mIsChangeToOtherPhone = false;
                NfLog.d(this.TAG, "phone mIsChangeToOtherPhone:yes, try to forceDisconnect");
                forceDisconnect();
                if (!enable) {
                    NfLog.d(this.TAG, "need not to unregister");
                    return true;
                }
                NfLog.d(this.TAG, "mIsChangeToOtherPhone:yes, try to register!");
                setMapBusyBlock(true);
                registerThreadStart(address, enable);
                return true;
            }
            return sendToMnsFlight(address, enable);
        }
    }

    public void registerThreadStart(String addr, boolean enable) {
        if (this.mRegisterNotificationThread != null) {
            NfLog.d(this.TAG, "RegisterNotificationThread already has instance");
            return;
        }
        NfLog.d(this.TAG, "RegisterNotificationThread start");
        this.mRegisterNotificationThread = new RegisterNotificationThread(addr, enable);
        this.mRegisterNotificationThread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class RegisterNotificationThread extends Thread {
        final String address;
        final boolean enable;
        boolean isStopped = false;

        public RegisterNotificationThread(String a, boolean e) {
            this.address = a;
            this.enable = e;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                NfLog.d(_NfMapJava.this.TAG, "Before delay DISCONNECT_TIME for registerNotification");
                Thread.sleep(2500L);
            } catch (InterruptedException e) {
            }
            if (!this.isStopped) {
                NfLog.d(_NfMapJava.this.TAG, "After delay DISCONNECT_TIME for registerNotification");
                _NfMapJava.this.sendToMnsFlight(this.address, this.enable);
            }
        }

        public void shutdown() {
            if (!this.isStopped) {
                this.isStopped = true;
            }
            if (!Thread.currentThread().equals(this)) {
                NfLog.d(_NfMapJava.this.TAG, "shutdown called from another thread - interrupt().");
                interrupt();
            }
        }
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public synchronized boolean isNotificationRegistered() {
        boolean ret;
        ret = false;
        if (this.mClient != null) {
            ret = this.mClient.getNotificationRegistration() && this.mClient.isRegistered();
        }
        if (!ret && this.mClient2 != null) {
            ret = this.mClient2.getNotificationRegistration() && this.mClient2.isRegistered();
        }
        NfLog.d(this.TAG, "isNotificationRegistered " + ret);
        return ret;
    }

    private synchronized boolean isTryToRegister() {
        return this.mAttemptEnableNotifications;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setTryToRegister(boolean enable) {
        NfLog.e(this.TAG, "mAttemptEnableNotifications set to " + enable);
        this.mAttemptEnableNotifications = enable;
    }

    private synchronized boolean isAutoDownloadOne() {
        return this.mDownloadNewMessage;
    }

    private synchronized boolean enableNotifications(String address, boolean enable) {
        boolean z = false;
        synchronized (this) {
            if (this.mMapConnectionStatus == 0 && enable) {
                NfLog.d(this.TAG, "mns enableRegistration 1: Not connected. Try to connect first.");
                if (!isAddrWrong(address)) {
                    startInitMapProfile(address, 0);
                    z = true;
                }
            } else if (this.mMapConnectionStatus == 1 && enable) {
                NfLog.d(this.TAG, "mns enableRegistration 1: when MAS CONNECTING.");
                z = true;
            } else if (this.mMapConnectionStatus == 3) {
                NfLog.d(this.TAG, "mns enableRegistration: when MAS CONNECTED.");
                boolean ret = setNotificationRegistration(enable);
                if (ret) {
                    z = true;
                } else {
                    NfLog.d(this.TAG, "mns enableRegistration: failed");
                }
            } else if (!enable) {
                NfLog.d(this.TAG, "mns enableRegistration 0");
                boolean ret2 = setNotificationRegistration(enable);
                if (ret2) {
                    z = true;
                } else {
                    NfLog.d(this.TAG, "mns enableRegistration: failed");
                }
            } else {
                NfLog.d(this.TAG, "mns enableRegistration: block.");
            }
        }
        return z;
    }

    private synchronized boolean setNotificationRegistration(boolean enable) {
        return this.mClient != null ? this.mClient.setNotificationRegistration(enable) : false;
    }

    private synchronized boolean enableNotifications(boolean enable) {
        return enableNotifications(null, enable);
    }

    public void onEnableNotifications(int enabled) {
        if (enabled == 1) {
            NfLog.d(this.TAG, "onEnableNotifications enabled");
            return;
        }
        NfLog.d(this.TAG, "onEnableNotifications not enabled");
        onNotificationStat();
    }

    private void onEventReport(BluetoothMapEventReport eventReport) {
        if (eventReport != null) {
            NfLog.d(true, this.TAG, eventReport.toString());
            if (this.mMapConnectionStatus != 3) {
                NfLog.e(this.TAG, "onEventReport(): Returning early because not connected: " + this.mMapConnectionStatus);
                return;
            }
            switch ($SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMapEventReport$Type()[eventReport.getType().ordinal()]) {
                case 1:
                    NfLog.e(this.TAG, "onEventReport NEW_MESSAGE: " + eventReport);
                    onReceiveNewMessage(this.mAddress, eventReport.getHandle(), 0, getFolderFromStr(eventReport.getFolder()));
                    return;
                case 2:
                    onJniMapMessageDeliverStatus(this.mAddress, eventReport.getHandle(), 1);
                    return;
                case 3:
                    onJniMapMessageSendingStatus(this.mAddress, eventReport.getHandle(), 1);
                    return;
                case 4:
                    onJniMapMessageDeliverStatus(this.mAddress, eventReport.getHandle(), 0);
                    return;
                case 5:
                    onJniMapMessageSendingStatus(this.mAddress, eventReport.getHandle(), 0);
                    return;
                case 6:
                    onJniMapMemoryAvailable(this.mAddress, 0, getFolderFromStr(eventReport.getFolder()), 0);
                    return;
                case 7:
                    onJniMapMemoryAvailable(this.mAddress, 0, getFolderFromStr(eventReport.getFolder()), 1);
                    return;
                case 8:
                    onJniMapMessageDeleted(this.mAddress, eventReport.getHandle(), 0, getFolderFromStr(eventReport.getFolder()));
                    return;
                case 9:
                    onJniMapMessageShifted(this.mAddress, eventReport.getHandle(), 0, getFolderFromStr(eventReport.getFolder()), getFolderFromStr(eventReport.getOldFolder()));
                    return;
                default:
                    NfLog.e(this.TAG, "onEventReport cannot understand the report: " + eventReport);
                    return;
            }
        }
    }

    private synchronized void getMessage(String handle) {
        boolean status = this.mClient != null ? this.mClient.getMessage(handle, false) : false;
        if (!status) {
            onGetMessage(null);
        }
    }

    private void onGetMessage(BluetoothMapBmessage msg) {
        try {
            if (this.isDetail && this.cnt_all > 0) {
                this.cnt_cur++;
                boolean gotMessage = true;
                if (msg == null) {
                    NfLog.d(this.TAG, "onGetMessage got null cur/all " + this.cnt_cur + "/" + this.cnt_all);
                    gotMessage = false;
                }
                BluetoothMapMessage listing = null;
                if (gotMessage) {
                    BluetoothMapMessage listing2 = this.mMsgListHash.get(msg.mHandle);
                    listing = listing2;
                    if (listing == null) {
                        NfLog.e(this.TAG, "onGetMessage, not such message handle");
                        gotMessage = false;
                    }
                }
                if (gotMessage) {
                    this.mCurrentFolder = getFolderFromStr(msg.getFolder());
                    NfLog.d(true, this.TAG, "onGetMessage cur/all " + this.cnt_cur + "/" + this.cnt_all);
                    NfLog.d(true, this.TAG, String.valueOf(msg.getBodyContent()) + " folder: " + this.mCurrentFolder);
                    listing.msg = msg.getBodyContent();
                    if (listing.mSubject == null) {
                        listing.mSubject = listing.msg;
                    }
                    NfLog.d(this.TAG, String.format("size %d(%s)", Integer.valueOf(listing.mSize), listing.msg));
                    if (isInterruptedLog("onGetMessage detect interrupt")) {
                        return;
                    }
                }
                if (this.cnt_cur == this.cnt_all) {
                    NfLog.d(true, this.TAG, "download complete");
                    NfLog.e(" ", "Check msg@downloaded, hashSize " + this.mMsgListHash.size());
                    NfLog.e(" ", "Check msg@downloaded, arraySize " + this.mMsgListArray.size());
                } else if (this.cnt_cur % 1 == 0) {
                    NfLog.e(this.TAG, "NEXT SHOT");
                    if (this.mClient != null) {
                        this.mClient.getMessage(this.mMsgListArray, this.cnt_cur, 1);
                    }
                }
                if (gotMessage) {
                    sendMessageContentFromMSG(listing);
                    return;
                }
                NfLog.e(this.TAG, "Send Msg back! " + this.mMsgListArray.get(this.cnt_cur - 1).msg);
                sendMessageContentFromMSG(this.mMsgListArray.get(this.cnt_cur - 1));
            } else if (this.mDownloadInterrupt == 2) {
                NfLog.e(this.TAG, "DROP!!");
            } else if (msg == null) {
                NfLog.e(this.TAG, "onGetMessage null");
                onServiceStat(3, 8);
            } else {
                sendMessageContentFromBMSG(msg);
            }
        } catch (Exception e) {
            NfLog.e(this.TAG, "onGetMessage Exception: " + e);
            onServiceStat(3, 8);
        }
    }

    private synchronized boolean getMessagesListingInternal(String folder, int count, int offset, BluetoothMasClient.MessagesFilter filter, int subjectLength) {
        this.mDownloadInterrupt = 0;
        this.isDetail = false;
        this.cnt_all = 0;
        this.cnt_cur = 0;
        this.cnt_progress = 1;
        this.cnt_total = 0;
        return getMessagesListing(folder, count, offset, filter, subjectLength, 46);
    }

    private synchronized boolean getMessagesListing(String folder, int count, int offset, BluetoothMasClient.MessagesFilter filter, int subjectLength, int parameters) {
        boolean z;
        NfLog.e(this.TAG, "getMessagesListing");
        if (count < 0) {
            NfLog.d(this.TAG, "MAX count " + count);
            count = NfMapCommand.COUNT_DOWNLOAD_ALL;
        }
        if (offset < 0) {
            NfLog.d(this.TAG, "Offset cannot be < 0: " + offset);
            offset = 0;
        }
        if (count < 65535) {
            NfLog.d(this.TAG, "getMsgList " + count);
            this.mGetMsgListCnt = count;
        } else {
            this.mGetMsgListCnt = -1;
        }
        NfLog.d(this.TAG, "getMessgesListing called. send to masClinet");
        if (this.mMapConnectionStatus != 3) {
            NfLog.d(this.TAG, "getMessgesListing called. != CONNECTED");
            z = false;
        } else {
            boolean status = false;
            if (this.mClient != null) {
                status = this.mClient.getMessagesListing(folder, parameters, filter, subjectLength, count, offset);
            } else {
                NfLog.d(this.TAG, "mClient == null");
            }
            if (!status) {
                onGetMessagesListing(null);
            }
            NfLog.d(this.TAG, "getMessgesListing called. sended to masClinet");
            z = true;
        }
        return z;
    }

    private void onGetIphoneMessagesListing(ArrayList<BluetoothMapMessage> msgsListing) {
        NfLog.d(this.TAG, "onGetNumberMessagesListing");
        if (msgsListing == null) {
            if (this.mClient != null) {
                if (this.mClient.getMseTime() != null) {
                    NfLog.d(this.TAG, "onGetNumberMessagesListing null, force return! time");
                    this.mIphoneMsg.mDateTime = this.mClient.getMseTime();
                } else {
                    NfLog.d(this.TAG, "onGetNumberMessagesListing null, force return! no time");
                    this.mIphoneMsg.mDateTime = new Date();
                }
            }
            sendMessageContentFromMSG(this.mIphoneMsg);
            this.mIphoneMsg = null;
            return;
        }
        if (this.mMsgListHash == null) {
            this.mMsgListHash = new HashMap<>();
        }
        Iterator<BluetoothMapMessage> it = msgsListing.iterator();
        while (it.hasNext()) {
            BluetoothMapMessage listing = it.next();
            this.mMsgListHash.put(listing.mHandle, listing);
        }
        BluetoothMapMessage msg = this.mMsgListHash.get(this.mIphoneMsg.mHandle);
        if (msg != null) {
            NfLog.d(this.TAG, "onGetNumberMessagesListing Success! " + this.mIphoneMsg.mHandle);
            NfLog.d(this.TAG, String.format("%s S:%s, R:%s", msg.mDateTime, msg.mSenderAddressing, msg.mRecipientAddressing));
            this.mIphoneMsg.mDateTime = msg.mDateTime;
            if (!msg.mSenderAddressing.equals("unknown")) {
                this.mIphoneMsg.mSenderAddressing = msg.mSenderAddressing;
            }
            if (!msg.mSenderName.equals("unknown")) {
                this.mIphoneMsg.mSenderName = msg.mSenderName;
            }
            if (!msg.mRecipientAddressing.equals("unknown")) {
                this.mIphoneMsg.mRecipientAddressing = msg.mRecipientAddressing;
            }
            sendMessageContentFromMSG(this.mIphoneMsg);
            this.mIphoneMsg = null;
            return;
        }
        if (this.countDownloadSingle == 0) {
            getMessagesListingInternal(getStringFromFolder(this.mCurrentFolder), -1, 0, null, 0);
        } else {
            NfLog.d(this.TAG, "onGetNumberMessagesListing failed, force return!");
            sendMessageContentFromMSG(this.mIphoneMsg);
            this.mIphoneMsg = null;
        }
        this.countDownloadSingle++;
    }

    private void onGetMessagesListing(ArrayList<BluetoothMapMessage> msgsListing) {
        boolean isFail = false;
        if (msgsListing == null) {
            NfLog.e(this.TAG, "onGetMessagesListing Fail");
            onServiceStat(3, 8);
            isFail = true;
        } else if (msgsListing.size() == 0) {
            NfLog.e(this.TAG, "onGetMessagesListing count 0");
            onServiceStat(3, 5);
            isFail = true;
        } else if (this.cnt_total > 0 || this.cnt_all > 0) {
            NfLog.e(this.TAG, "double on get messages listing. FATAL!");
            forceDisconnect();
            return;
        }
        if (isFail) {
            if (isIphoneGetPhoneNumber()) {
                onGetIphoneMessagesListing(null);
                return;
            }
            return;
        }
        NfLog.d(this.TAG, "onGetMessagesListing 1 list.size " + msgsListing.size());
        NfLog.d(this.TAG, "do sort! onGetMessagesListing 1 wanted " + this.mGetMsgListCnt);
        if (isIphoneGetPhoneNumber()) {
            onGetIphoneMessagesListing(msgsListing);
        } else if (!isInterruptedLog("onGetMessagesListing detect interrupt")) {
            Collections.sort(msgsListing, Collections.reverseOrder());
            this.cnt_total = msgsListing.size();
            List<BluetoothMapMessage> finalList = null;
            if (this.mGetMsgListCnt > 0 && this.cnt_total > this.mGetMsgListCnt) {
                NfLog.d(this.TAG, "trimList to wanted");
                finalList = msgsListing.subList(0, this.mGetMsgListCnt);
                this.cnt_total = this.mGetMsgListCnt;
                if (finalList.size() != this.mGetMsgListCnt) {
                    NfLog.e(this.TAG, "assert count fail " + finalList.size());
                    onServiceStat(3, 8);
                    return;
                }
            }
            if (!isInterruptedLog("onGetMessagesListing detect interrupt")) {
                if (finalList == null) {
                    finalList = msgsListing;
                }
                NfLog.d("=========", "DOWNLOADALL Detail?" + this.isDetail);
                if (this.isDetail) {
                    NfLog.d(this.TAG, "onGetMessagesListing list to list");
                    this.mMsgListArray = new ArrayList<>();
                    if (this.mMsgListHash == null) {
                        this.mMsgListHash = new HashMap<>();
                    }
                    for (BluetoothMapMessage msg : finalList) {
                        if (!isInterruptedLog("onGetMessagesListing detect interrupt")) {
                            if (msg.mSubject == null) {
                                msg.mSubject = "";
                            }
                            this.mMsgListHash.put(msg.mHandle, msg);
                            if (msg.mSubject.length() == 0 || (msg.mSize > 0 && msg.mSize > msg.mSubject.length())) {
                                if (msg.mSize > 2048) {
                                    NfLog.d(this.TAG, "got picture");
                                    sendMessageContentFromMSG(msg);
                                } else {
                                    this.cnt_all++;
                                    NfLog.d("len:" + msg.mSubject.length(), String.format("totSize%3d +hash%-4d (%s)", Integer.valueOf(msg.mSize), Integer.valueOf(this.cnt_all), msg.mSubject));
                                    if (msg.mSubject.length() > 20) {
                                        msg.mSubject = msg.mSubject.substring(0, 20);
                                    }
                                    this.mMsgListArray.add(msg);
                                }
                            } else {
                                msg.msg = msg.mSubject;
                                if (msg.mSubject.length() > 20) {
                                    msg.mSubject = msg.mSubject.substring(0, 20);
                                }
                                sendMessageContentFromMSG(msg);
                            }
                        } else {
                            return;
                        }
                    }
                    if (this.mMsgListArray != null && this.mMsgListArray.size() > 0 && this.mClient != null) {
                        this.mClient.getMessage(this.mMsgListArray, 0, 1);
                    }
                } else {
                    if (finalList != null) {
                        NfLog.d(this.TAG, "direct return MsgList without download detail " + finalList.size());
                    }
                    if (this.mMsgListHash == null) {
                        this.mMsgListHash = new HashMap<>();
                    }
                    for (BluetoothMapMessage listing : finalList) {
                        this.mMsgListHash.put(listing.mHandle, listing);
                        if (!isInterruptedLog("onGetMessagesListing detect interrupt")) {
                            sendMessageContentFromMSG(listing);
                        } else {
                            return;
                        }
                    }
                }
                NfLog.d("=========", "DOWNLOADALL END");
            }
        }
    }

    private synchronized void sendMessageContentFromBMSG(BluetoothMapBmessage msg) {
        if (msg == null) {
            sendMessageContentFromMSG(null);
        }
        BluetoothMapMessage retMsg = new BluetoothMapMessage();
        retMsg.mType = msg.getType();
        this.mCurrentFolder = getFolderFromStr(msg.getFolder());
        NfLog.e(this.TAG, "sendMessageContentFromBMSG folder: " + this.mCurrentFolder);
        switch ($SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMapBmessage$Status()[msg.getStatus().ordinal()]) {
            case 1:
                retMsg.mRead = true;
                break;
            case 2:
                retMsg.mRead = false;
                break;
            default:
                retMsg.mRead = false;
                break;
        }
        retMsg.mFolder = msg.getFolder();
        this.mCurrentFolder = getFolderFromStr(msg.getFolder());
        retMsg.msg = msg.getBodyContent();
        if (retMsg.msg.length() > 20) {
            retMsg.mSubject = retMsg.msg.substring(0, 20);
        } else {
            retMsg.mSubject = retMsg.msg;
        }
        retMsg.mHandle = msg.mHandle;
        VCardEntry origin = msg.getOriginator();
        if (origin == null) {
            NfLog.e(this.TAG, "No originator found. " + msg);
        } else if (origin.getPhoneList() != null && origin.getPhoneList().size() > 0 && origin.getPhoneList().get(0) != null) {
            if (origin.getPhoneList().get(0).getNumber() != null) {
                retMsg.mSenderAddressing = origin.getPhoneList().get(0).getNumber();
            } else {
                NfLog.e(this.TAG, "originator mSenderAddressing none. ");
                retMsg.mSenderAddressing = "";
            }
        } else {
            NfLog.e(this.TAG, "no originator address, handle: " + retMsg.mHandle);
        }
        if (origin != null && origin.getDisplayName() != null) {
            retMsg.mSenderName = origin.getDisplayName();
        }
        try {
            retMsg.mRecipientAddressing = msg.getRecipients().get(0).getPhoneList().get(0).getNumber();
            retMsg.mRecipientAddressing = retMsg.mRecipientAddressing.replaceAll("-", "");
        } catch (Exception e) {
        }
        NfLog.d(this.TAG, "mSenderAddressing " + retMsg.mSenderAddressing);
        NfLog.d(this.TAG, "RecipientAddressing " + retMsg.mRecipientAddressing);
        NfLog.d(this.TAG, "getDisplayName " + retMsg.mSenderName);
        this.mIphoneMsg = retMsg;
        if (!getMsgFromCache(retMsg)) {
            getMessagesListingInternal(getStringFromFolder(this.mCurrentFolder), 1, 0, null, 0);
        }
    }

    private synchronized boolean getMsgFromCache(BluetoothMapMessage msg) {
        boolean z;
        BluetoothMapMessage cached;
        if (this.mMsgListHash != null) {
            NfLog.d(this.TAG, "getMsgFromCache size" + this.mMsgListHash.size());
        }
        NfLog.d(this.TAG, "getMsgFromCache Handle " + msg.mHandle);
        if (this.mMsgListHash == null || (cached = this.mMsgListHash.get(msg.mHandle)) == null) {
            z = false;
        } else {
            NfLog.d(this.TAG, "getMsgFromCache Success! ");
            NfLog.d(this.TAG, "getDisplayName " + cached.mSenderName);
            NfLog.d(this.TAG, String.format("%s S:%s, R:%s", cached.mDateTime, cached.mSenderAddressing, cached.mRecipientAddressing));
            NfLog.d(this.TAG, String.format("%s S:%s, R:%s", msg.mDateTime, msg.mSenderAddressing, msg.mRecipientAddressing));
            msg.mDateTime = msg.mDateTime;
            if (!cached.mSenderAddressing.equals("unknown")) {
                msg.mSenderAddressing = cached.mSenderAddressing;
            }
            if (!cached.mSenderName.equals("unknown")) {
                msg.mSenderName = cached.mSenderName;
            }
            if (!cached.mRecipientAddressing.equals("unknown")) {
                msg.mRecipientAddressing = cached.mRecipientAddressing;
            }
            sendMessageContentFromMSG(msg);
            this.mIphoneMsg = null;
            z = true;
        }
        return z;
    }

    private synchronized void sendMessageContentFromMSG(BluetoothMapMessage listing) {
        synchronized (this) {
            if (listing != null) {
                if (listing.mDateTime != null) {
                    if (!isInterruptedLog("sendMessageContentFromBMSG interrupted")) {
                        NfLog.d(this.TAG, "sendMessageContentFromMSG:" + this.cnt_progress);
                        onJniReceiveMessageContent(this.mAddress, listing.mHandle, listing.mSenderName, listing.mSenderAddressing, dateToString(listing.mDateTime), listing.mRecipientAddressing, 0, this.mCurrentFolder, listing.mSubject, listing.msg, listing.mRead ? 1 : 0, this.cnt_progress, this.cnt_total);
                        listing.msg = null;
                        listing.mSubject = null;
                        this.cnt_progress++;
                        if (this.cnt_progress > this.cnt_total) {
                            NfLog.d(this.TAG, " MAP_REASON_DOWNLOAD_FINISH... " + this.cnt_progress);
                            this.mMsgListArray = null;
                            onServiceStat(3, 5);
                        }
                    }
                }
            }
            NfLog.d(this.TAG, " download failed ");
            onServiceStat(3, 8);
        }
    }

    public String dateToString(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date == null) {
            cal.setTime(new Date());
        } else {
            cal.setTime(date);
        }
        return String.format(Locale.US, "%04d%02d%02dT%02d%02d%02dZ", Integer.valueOf(cal.get(1)), Integer.valueOf(cal.get(2) + 1), Integer.valueOf(cal.get(5)), Integer.valueOf(cal.get(11)), Integer.valueOf(cal.get(12)), Integer.valueOf(cal.get(13)));
    }

    public boolean connect() {
        this.mDownloadInterrupt = 0;
        processSendMessageDelayed(14, 16500);
        return connectInternal();
    }

    private synchronized boolean connectInternal() {
        boolean z = true;
        synchronized (this) {
            NfLog.e(this.TAG, "connectInternal ");
            onServiceStat(1);
            if (this.mMapConnectionStatus != 0) {
                NfLog.d(this.TAG, "Service not in disconnected state. " + this.mMapConnectionStatus);
                z = false;
            } else {
                this.mMapConnectionStatus = 7;
                connectToSdpRecord(null);
            }
        }
        return z;
    }

    private synchronized void connectToSdpRecord(SdpMasRecord sdpRecord) {
        NfLog.d(this.TAG, "connectToSdpRecord");
        if (this.mMapConnectionStatus == 7) {
            this.mMapConnectionStatus = 1;
            if (this.mMasClient != null) {
                this.mClient = this.mMasClient;
                this.mClient.setDevice(this.mDevice);
            } else {
                if (sdpRecord == null) {
                    sdpRecord = new SdpMasRecord(0, 6, 6, 1, 6, 6, "MAP");
                }
                BluetoothMasClient bluetoothMasClient = new BluetoothMasClient(this.mDevice, sdpRecord, this.mJniHandler);
                this.mMasClient = bluetoothMasClient;
                this.mClient = bluetoothMasClient;
            }
            this.mClient.connect();
        }
    }

    private synchronized void connectionSuccessful() {
        if (this.mMapConnectionStatus == 1) {
            this.mMapConnectionStatus = 3;
            onServiceConnected();
        }
    }

    private void onServiceConnected() {
        NfLog.d(this.TAG, "onServiceConnected");
        this.mJniHandler.removeMessages(14);
        onServiceStat(3);
    }

    private void onServiceStatDelay(int state, int delay) {
        onServiceStatDelay(state, 0, delay);
    }

    private void onServiceStatDelay(int state, int reason, int delay) {
        onServiceStat(state, reason, delay);
    }

    private boolean isJniHandlerNull() {
        boolean ret = this.mJniHandler == null;
        if (ret) {
            NfLog.d(this.TAG, "JniHandler is null");
        }
        return ret;
    }

    private void onNotificationStat() {
        NfLog.d(this.TAG, "onNotificationStat");
        resetCurrentMnsFlight();
        if (!isJniHandlerNull() && this.mCurrentCommand == null && getProfileState() != 160 && getProfileState() != 170 && !this.mJniHandler.hasMessages(NfJni.onJniMapConnectionStateChanged)) {
            NfLog.d(this.TAG, "onNotificationStat sendmsg");
            if (NfPrimitive.isBtEnabled()) {
                onServiceStatDelay(3, 100);
            }
        }
    }

    private void onServiceStat(int state) {
        onServiceStat(state, 0, 0);
    }

    private void onServiceStat(int state, int reason) {
        onServiceStat(state, reason, 0);
    }

    private void onServiceStat(int state, int reason, int delay) {
        if (state != 2) {
            NfLog.d(this.TAG, "onServiceStat " + this.mAddress + " newState: " + state);
            NfLog.d(this.TAG, "onServiceStat reason: " + reason);
            NfLog.d(this.TAG, "onServiceStat delay: " + delay);
            NfJniBundle bundle = new NfJniBundle();
            bundle.setString1(this.mAddress);
            bundle.setInt1(state);
            bundle.setInt2(reason);
            processSendMessageDelayed(NfJni.onJniMapConnectionStateChanged, delay, bundle);
        }
    }

    public void disconnect(int reason) {
        disconnectInternalNoLock(reason);
    }

    private void disconnectInternalNoLock(int reason) {
        NfLog.d(true, this.TAG, "disconnectInternalNoLock ");
        setDownloadNotifyFrequency(0);
        this.mDownloadInterrupt = 1;
        this.mMapConnectionStatus = 0;
        resetProgress(-10);
        this.mJniHandler.removeMessages(14);
        if (isNotificationRegistered()) {
            NfLog.d(this.TAG, "disconnectInternalNoLock do unRegister first");
            enableNotifications(false);
        } else if (this.mClient != null) {
            NfLog.d(this.TAG, "disconnectInternalNoLock do mas disconnect");
            try {
                if (NfConfig.isPtsTest()) {
                    NfLog.d(this.TAG, "disconnectInternalNoLock do mas2 disconnect");
                    if (this.mClient2 != null) {
                        this.mClient2.disconnect();
                    }
                }
                this.mClient.disconnect();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            onServiceStatDelay(0, reason, 100);
        } else {
            onServiceStatDelay(0, reason, 100);
        }
    }

    private void cleanMas() {
        NfLog.d(this.TAG, "===cleanMas" + this.mClient);
        this.mFolder = "";
        this.mClient = null;
        if (this.mMsgListHash != null) {
            this.mMsgListHash.clear();
        }
        this.mMsgListHash = null;
        if (this.isForceDisconnect) {
            if (this.mMasClient != null) {
                this.mMasClient.finalize();
            }
            this.mMasClient = null;
        }
        this.mDevice = null;
        NfLog.d(this.TAG, "===cleanMas end");
    }

    private synchronized boolean isInterruptedLog(String s) {
        boolean ret;
        ret = isInterrupted();
        if (ret) {
            NfLog.d(this.TAG, s);
        }
        return ret;
    }

    private synchronized boolean isIphoneGetPhoneNumber() {
        boolean ret;
        ret = false;
        if (this.mIphoneMsg != null && this.mIphoneMsg.mHandle != null && this.mIphoneMsg.mHandle.length() > 0) {
            ret = true;
        }
        NfLog.d(this.TAG, "isDownloadOneGetNumber: " + ret);
        return ret;
    }

    private synchronized boolean isInterrupted() {
        boolean z = true;
        synchronized (this) {
            if (this.mMapConnectionStatus == 3) {
                if (this.mDownloadInterrupt <= 0) {
                    z = false;
                }
            }
        }
        return z;
    }

    private void onSetPath(String path) {
        if (path.endsWith(FOLDER_TELECOM)) {
            this.mFolder = FOLDER_TELECOM;
        } else if (path.endsWith(FOLDER_MSG)) {
            this.mFolder = FOLDER_MSG;
        } else {
            NfLog.e(this.TAG, " incorrect change folder: " + path);
            this.mFolder = "unknow";
        }
        setPath();
    }

    private void setPath() {
        String currFolder = this.mFolder;
        boolean success = true;
        if (currFolder.equals("")) {
            NfLog.d(true, this.TAG, String.valueOf(currFolder) + " set path to  " + FOLDER_TELECOM);
            success = setPathDown(FOLDER_TELECOM);
        } else if (currFolder.endsWith(FOLDER_TELECOM)) {
            NfLog.d(true, this.TAG, String.valueOf(currFolder) + " set path to  " + FOLDER_MSG);
            success = setPathDown(FOLDER_MSG);
        } else if (currFolder.endsWith(FOLDER_MSG)) {
            connectionSuccessful();
        } else {
            NfLog.e(this.TAG, "Should not be here. folder: " + currFolder);
            success = false;
        }
        if (!success) {
            disconnect(1);
        }
    }

    private boolean setPathDown(String path) {
        if (this.mMapConnectionStatus != 1) {
            NfLog.e(this.TAG, "Should not be here. state !=CONNECTING: " + path);
            return false;
        } else if (this.mClient != null) {
            return this.mClient.setFolderDown(path);
        } else {
            return true;
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void dequeueMessage(Message msg) {
        NfLog.d(true, this.TAG, "dequeueMessage from MAP client: " + msg);
        boolean isConti = false;
        if (msg.arg2 == 1) {
            NfLog.d(this.TAG, "MAS2 Event " + msg.what);
            switch (msg.what) {
                case 0:
                    NfLog.v(this.TAG, "onNextCommand");
                    break;
                case 1:
                    NfLog.d(this.TAG, "Connected via OBEX with status " + msg.arg1);
                    ((Integer) msg.obj).intValue();
                    if (msg.arg1 == 1) {
                        NfLog.d(this.TAG, "status 1: STATUS_FAILED.");
                        return;
                    }
                    break;
                case 3:
                    NfLog.d(this.TAG, "Set path: " + msg.obj);
                    break;
                case 10:
                    NfLog.d(this.TAG, "Set notifications: " + msg.obj);
                    break;
                case 11:
                    NfLog.d(this.TAG, "EVENT_EVENT_REPORT: " + msg.obj);
                    break;
                case 13:
                    NfLog.d(this.TAG, "EVENT__MNS_REG");
                    if (this.mClient2 != null) {
                        this.mClient2.setRegistered();
                        onNotificationStat();
                        break;
                    } else {
                        return;
                    }
                case 14:
                    NfLog.v(this.TAG, "onConnectTimeout");
                    break;
                case 15:
                    NfLog.d(this.TAG, "EVENT_MNS_ADD_SDP " + msg.arg2);
                    break;
                case 16:
                    NfLog.d(this.TAG, "EVENT__MNS_UNREG");
                    forceDisconnect(this.mAddress, 0);
                    break;
                case NfJni.onJniMapConnectionStateChanged /* 401 */:
                    NfLog.v(this.TAG, "onJniMapConnectionStateChanged()");
                    break;
            }
            NfLog.d(this.TAG, "MAS2 Event End");
            return;
        }
        switch (msg.what) {
            case 0:
                NfLog.v(this.TAG, "onNextCommand");
                if (msg.obj != null) {
                    processCommandInternal((NfMapCommand) msg.obj);
                    break;
                } else {
                    processCommand();
                    break;
                }
            case 1:
                NfLog.d(this.TAG, "Connected via OBEX with status " + msg.arg1);
                int reason = ((Integer) msg.obj).intValue();
                if (msg.arg1 == 1) {
                    NfLog.d(this.TAG, "EVENT_CONNECT with STATUS_FAILED.");
                    onServiceStat(2, 1);
                    disconnect(reason);
                    return;
                }
                setPath();
                if (NfConfig.isPtsTest()) {
                    this.mClient2 = new BluetoothMasClient(this.mDevice, new SdpMasRecord(1, 6, 6, 1, 6, 6, "MAP"), this.mJniHandler);
                    this.mClient2.connect();
                    break;
                }
                break;
            case 3:
                NfLog.d(this.TAG, "Set path: " + msg.obj);
                onSetPath((String) msg.obj);
                break;
            case 10:
                NfLog.d(this.TAG, "MAS1 EVENT_SET_NOTIFICATION_REGISTRATION: " + msg.obj);
                int isEnabled = ((Integer) msg.obj).intValue();
                onEnableNotifications(isEnabled);
                if (NfConfig.isPtsTest()) {
                    NfLog.d(this.TAG, "Set notifications for MAS2: " + msg.obj);
                    if (this.mClient2 != null && isEnabled == 1) {
                        this.mClient2.setNotificationRegistration(true);
                        break;
                    } else if (this.mClient2 != null && isEnabled == 0) {
                        this.mClient2.setNotificationRegistration(false);
                        break;
                    }
                }
                break;
            case 11:
                NfLog.d(this.TAG, "EVENT_EVENT_REPORT: " + msg.obj);
                if (msg.obj == null) {
                    NfLog.e(this.TAG, "Mns detect remote disconnect");
                    onNotificationStat();
                    return;
                }
                onEventReport((BluetoothMapEventReport) msg.obj);
                break;
            case 13:
                NfLog.d(this.TAG, "EVENT__MNS_REG");
                if (this.mClient != null) {
                    this.mClient.setRegistered();
                }
                onNotificationStat();
                break;
            case 14:
                NfLog.v(this.TAG, "onConnectTimeout");
                if (getProfileState() < 140) {
                    forceDisconnect(this.mAddress, 7);
                    break;
                }
                break;
            case 15:
                NfLog.d(this.TAG, "EVENT_MNS_ADD_SDP " + msg.arg2);
                this.mMnsPortID = msg.arg2;
                break;
            case 16:
                NfLog.d(this.TAG, "EVENT__MNS_UNREG");
                onNotificationStat();
                break;
            case 17:
                NfLog.d(this.TAG, "EVENT_MAS_DISCONNECTED " + msg.obj);
                forceDisconnect();
                onServiceStatDelay(0, 0, 100);
                break;
            case NfJni.onJniMapConnectionStateChanged /* 401 */:
                NfLog.v(this.TAG, "onJniMapConnectionStateChanged()");
                NfJniBundle b = (NfJniBundle) msg.obj;
                if (this.mJniHandler != null) {
                    if (this.mJniHandler.hasMessages(NfJni.onJniMapReceiveMessageContent)) {
                        NfLog.v(this.TAG, "Piggy Check still processing message. Requeue state change message.");
                        this.mJniHandler.sendMessageDelayed(msg, 50L);
                        break;
                    } else {
                        onJniMapStateChanged(b.getString1(), b.getInt1(), b.getInt2());
                        break;
                    }
                }
                break;
            default:
                isConti = true;
                break;
        }
        if (isConti) {
            if (this.mDownloadInterrupt > 0) {
                NfLog.d(this.TAG, "DownloadInterrupted. Drop event.");
                onServiceStat(3, 4, Constants.MAX_RECORDS_IN_DATABASE);
                return;
            }
            switch (msg.what) {
                case 6:
                    NfLog.d(this.TAG, "EVENT_GET_MESSAGES_LISTING");
                    if (msg.arg1 == 1) {
                        onGetMessagesListing(null);
                        return;
                    } else {
                        onGetMessagesListing((ArrayList) msg.obj);
                        break;
                    }
                case 7:
                    NfLog.d(this.TAG, "New message: " + msg.obj);
                    if (msg.arg1 == 1) {
                        onGetMessage(null);
                        return;
                    } else {
                        onGetMessage((BluetoothMapBmessage) msg.obj);
                        break;
                    }
                case 8:
                    NfLog.d(this.TAG, "EVENT_SET_MESSAGE_STATUS");
                    int reason2 = 0;
                    if (msg.arg1 == 1) {
                        NfLog.d(this.TAG, "EVENT_SET_MESSAGE_STATUS fail");
                        reason2 = 3;
                    }
                    switch (this.mCurrentCommand.getType()) {
                        case 1:
                            NfMapCommand.Delete cmd = (NfMapCommand.Delete) this.mCurrentCommand;
                            onJniMapDeleteMessageState(this.mAddress, cmd.getHandle(), reason2);
                            break;
                        case 2:
                        case 3:
                        default:
                            NfLog.e(this.TAG, "strange: curCmd is not [TYPE_DELETE/TYPE_READ_STATUS]");
                            break;
                        case 4:
                            NfMapCommand.ReadStatus cmd2 = (NfMapCommand.ReadStatus) this.mCurrentCommand;
                            onJniMapChangeReadStatusState(this.mAddress, cmd2.getHandle(), reason2);
                            break;
                    }
                    if (reason2 == 0) {
                        onServiceStat(3);
                        if (this.mClient != null) {
                            this.mClient.updateInbox();
                            break;
                        }
                    } else {
                        onServiceStat(3, 1);
                        break;
                    }
                    break;
                case 9:
                    NfLog.d(this.TAG, "Push message: " + msg.obj);
                    if (msg.arg1 == 1) {
                        NfLog.d(this.TAG, "Push fail");
                        onPushMessage(0);
                        break;
                    } else {
                        onPushMessage(1);
                        break;
                    }
                default:
                    NfLog.w(this.TAG, "Cannot handle map client event of type: " + msg.what);
                    break;
            }
            NfLog.d(true, this.TAG, "dequeueMessageEnd");
        }
    }

    public int createSdpRecord(int port) throws RemoteException {
        return 1;
    }

    private boolean isMapBusyBlock() {
        return this.mMapBusyBlock;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMapBusyBlock(boolean block) {
        if (!block) {
            this.mChangeToOtherPhoneAddress = NfDef.DEFAULT_ADDRESS;
        }
        this.mMapBusyBlock = block;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
        super.forceResetState();
        forceDisconnect();
        try {
            if (this.mInitMapConnectThread != null && this.mInitMapConnectThread.getTargetAddress().equals(this.mAddress)) {
                NfLog.d(this.TAG, "Interrupt init thread. TargetAddress: " + this.mAddress);
                this.mInitMapConnectThread.interrupt();
                this.mInitMapConnectThread = null;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        this.mAddress = NfDef.DEFAULT_ADDRESS;
        this.mMapConnectionStatus = 0;
        this.mRegisteringAddress = NfDef.DEFAULT_ADDRESS;
        this.mDevice = null;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onBtAclDisconnected(String address) {
        super.onBtAclDisconnected(address);
        if (this.mAddress.equals(address)) {
            forceResetState();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.profile._NfProfile
    public void onBtDeviceUnbonded(String address) {
        NfLog.v(this.TAG, "onBtDeviceUnbonded() " + address);
        if (getConnectedAddress().equals(address)) {
            this.mCommandProcessor.reset();
        }
        forceDisconnect(address);
        super.onBtDeviceUnbonded(address);
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
        callback().onMapStateChanged(address, prevState, state, reason);
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void onSdpUpdated(String address, ParcelUuid uuid, int l2capPsm) {
        NfLog.v(this.TAG, "Received UUID: " + uuid.toString());
        NfLog.v(this.TAG, "device: " + address);
        NfLog.v(this.TAG, "l2capPsm: " + l2capPsm);
        if (uuid.equals(ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB"))) {
            this.mL2capPsm = l2capPsm;
        }
    }
}
