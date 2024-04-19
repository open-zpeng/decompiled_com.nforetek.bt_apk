package com.nforetek.bt.profile.pbap.java;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.jni.NfJniBundle;
import com.nforetek.bt.profile.pbap._NfPbap;
import com.nforetek.bt.profile.pbap.java.vcard.VCardEntry;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.BluetoothUuid;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.db.DbHelperPbap;
import com.nforetek.util.normal.NfLog;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public class _NfPbapJava extends _NfPbap {
    private static final boolean DBG = true;
    private static final int HANDLER_EVENT_PBAP_DB_QUERY = 12;
    private static final int HANDLER_EVENT_PBAP_DOWNLOAD_FINISH = 10;
    private static final int HANDLER_EVENT_PBAP_NAME_LIKE_QUERY = 13;
    private static final int HANDLER_EVENT_PBAP_NAME_QUERY = 11;
    public static final boolean IS_NEED_SET_UNKNOWN_NAME_STRING = true;
    private static final int PBAP_QUERY_DELAY_TIME_MS = 1000;
    private static final int STORAGE_TYPE_BY_PASS = 0;
    private static final int STORAGE_TYPE_TO_CONTACTS_PROVIDER = 2;
    private static final int STORAGE_TYPE_TO_DATABASE = 1;
    public static final String UNKNOWN_NAME_STRING = "Private Number";
    private SQLiteDatabase db;
    private DbHelperPbap dbHelper;
    private PbapClientService mClient;
    private ArrayList<String> mQueryFullNumberList;
    private ArrayList<String> mQueryPartialNumberList;
    private int mStorage = -1;
    private boolean isNeedDeleteCallLog = true;
    private boolean isNeedDeleteContact = true;
    private boolean isDownloadSuccess = false;
    private int downloadFailReason = -1;
    private int mStorageType = -1;
    private int downloadCount = 0;
    private boolean isDownloading = false;
    private boolean isRealyStartDownload = false;
    private boolean mIsDownloadInterrupted = false;
    private int mContactsTotalCount = 0;
    private int mContactsCurrentCount = 0;
    private int mContactsParsingCount = 0;
    private int mDownloadNotifyFrequency = 0;
    private boolean isGetPhoneBookSizeFinished = false;
    private int mOffset = 0;
    private int mMaxCount = 0;
    private int count_Index = 0;
    private ArrayList<ContentProviderOperation> contacts_ops = new ArrayList<>();
    private ArrayList<ContentProviderOperation> callLog_ops = new ArrayList<>();
    private long All_total_time = 0;
    private final ExecutorService exService = Executors.newSingleThreadExecutor();
    private final int ContactsProvider_DownloadContact = 0;
    private final int ContactsProvider_DownloadCallLog = 1;
    private final int ContactsProvider_DeleteContact = 2;
    private final int ContactsProvider_DeleteCallLog = 3;
    private boolean isAlreadyQueryName = false;
    private boolean mInterrupIsRunning = false;
    private int mL2capPsm = -1;
    String mDownloadingAddress = NfDef.DEFAULT_ADDRESS;
    String mConnectingAddress = NfDef.DEFAULT_ADDRESS;
    private int mFilter = NfDef.GATT_STATUS_ILLEGAL_PARAMETER;
    private final Handler mHandler = new Handler() { // from class: com.nforetek.bt.profile.pbap.java._NfPbapJava.1
        /* JADX WARN: Removed duplicated region for block: B:61:0x036a  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x0370  */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public synchronized void handleMessage(android.os.Message r25) {
            /*
                Method dump skipped, instructions count: 992
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.profile.pbap.java._NfPbapJava.AnonymousClass1.handleMessage(android.os.Message):void");
        }
    };

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfPbap";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return 6;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
        this.mClient.disconnect(null);
        this.mHandler.removeCallbacksAndMessages(null);
        this.mQueryFullNumberList.clear();
        this.mQueryPartialNumberList.clear();
        this.mQueryFullNumberList = null;
        this.mQueryPartialNumberList = null;
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.profile._NfProfile
    public void onCreate() {
        NfLog.e(this.TAG, "Piggy Check is context() is null ?" + (context() == null ? "YES" : "NO"));
        this.mQueryFullNumberList = new ArrayList<>();
        this.mQueryPartialNumberList = new ArrayList<>();
        this.mClient = new PbapClientService(context(), this);
        super.onCreate();
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean isDownloading() {
        return this.isDownloading || this.mHandler.hasMessages(12);
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public String getDownloadingAddress() {
        return getConnectedAddress();
    }

    private boolean isAddressWrong(String address) {
        if (address == null || address.equals(NfDef.DEFAULT_ADDRESS) || !BluetoothAdapter.checkBluetoothAddress(address)) {
            NfLog.e(this.TAG, "address is not valid. Reject command. address = " + address);
            return true;
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean downloadByPass(String address, int storage, int property, int startPos, int offset) {
        NfLog.i(this.TAG, "start downloadByPass: " + address + " storage: " + storage);
        if (this.isDownloading) {
            NfLog.e(this.TAG, "isDownloading, state: " + getProfileState() + " isQueryDbAvaible? " + this.mHandler.hasMessages(12));
            return false;
        } else if (isAddressWrong(address)) {
            return false;
        } else {
            this.All_total_time = System.currentTimeMillis();
            this.isDownloading = true;
            this.mIsDownloadInterrupted = false;
            this.mInterrupIsRunning = false;
            this.mStorage = storage;
            this.mStorageType = 0;
            this.mFilter = property;
            this.mOffset = startPos;
            this.mMaxCount = offset;
            setNeedGetPhoneBookSize(storage);
            BluetoothDevice d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
            if (this.mClient != null) {
                this.mConnectingAddress = address;
                this.mClient.connect(d, storage, property, startPos, offset, this.mL2capPsm);
            } else {
                NfLog.e(this.TAG, "pce is null.");
            }
            return true;
        }
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean downloadToDb(String address, int storage, int property, int startPos, int offset) {
        NfLog.i(this.TAG, "start downloadToDb");
        if (this.isDownloading) {
            NfLog.e(this.TAG, "isDownloading, state: " + getProfileState());
            return false;
        } else if (isAddressWrong(address)) {
            return false;
        } else {
            this.All_total_time = System.currentTimeMillis();
            this.isDownloading = true;
            this.mIsDownloadInterrupted = false;
            this.mInterrupIsRunning = false;
            this.mStorage = storage;
            this.mStorageType = 1;
            this.mFilter = property;
            this.mOffset = startPos;
            this.mMaxCount = offset;
            setNeedGetPhoneBookSize(storage);
            BluetoothDevice d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
            if (this.mClient != null) {
                this.mConnectingAddress = address;
                NfLog.d(this.TAG, "mL2capPsm: " + this.mL2capPsm);
                this.mClient.connect(d, storage, property, startPos, offset, this.mL2capPsm);
            } else {
                NfLog.e(this.TAG, "pce is null.");
            }
            return true;
        }
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean downloadToProvider(String address, int storage, int property, int startPos, int offset) {
        NfLog.i(this.TAG, "start downloadToProvider");
        if (this.isDownloading) {
            NfLog.e(this.TAG, "isDownloading, state: " + getProfileState());
            return false;
        } else if (isAddressWrong(address)) {
            return false;
        } else {
            this.All_total_time = System.currentTimeMillis();
            this.count_Index = 0;
            this.isDownloading = true;
            this.mIsDownloadInterrupted = false;
            this.mInterrupIsRunning = false;
            this.mStorage = storage;
            this.mStorageType = 2;
            this.mFilter = property;
            this.mOffset = startPos;
            this.mMaxCount = offset;
            setNeedGetPhoneBookSize(storage);
            BluetoothDevice d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
            if (this.mClient != null) {
                this.mConnectingAddress = address;
                this.mClient.connect(d, storage, property, startPos, offset, this.mL2capPsm);
            } else {
                NfLog.e(this.TAG, "pce is null.");
            }
            return true;
        }
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public void queryNameByNumber(String address, String target) {
        if (this.mQueryFullNumberList.contains(target)) {
            Log.e(this.TAG, "Already query full number: " + target + " Reject query command!");
            return;
        }
        this.mQueryFullNumberList.add(target);
        if (NfPrimitive.checkAddress(address) && target != null) {
            this.isAlreadyQueryName = true;
            Message mNewMsg = Message.obtain(this.mHandler, 11);
            Bundle mBundle = new Bundle();
            mBundle.putString(NfDef.EXTRA_DEVICE_ADDRESS, address);
            mBundle.putString(NfDef.EXTRA_PHONENUMBER, target);
            mNewMsg.setData(mBundle);
            this.mHandler.sendMessage(mNewMsg);
            return;
        }
        NfLog.e(this.TAG, "queryNameByNumber() failed !! target: " + target);
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public void queryNameByPartial(String address, String target, int findPosition) {
        if (this.mQueryPartialNumberList.contains(target)) {
            Log.e(this.TAG, "Already query partial number: " + target + " Reject query command!");
            return;
        }
        this.mQueryPartialNumberList.add(target);
        if (NfPrimitive.checkAddress(address) && target != null) {
            this.isAlreadyQueryName = true;
            Message mNewMsg = Message.obtain(this.mHandler, 13);
            Bundle mBundle = new Bundle();
            mBundle.putString(NfDef.EXTRA_DEVICE_ADDRESS, address);
            mBundle.putString(NfDef.EXTRA_PHONENUMBER, target);
            mBundle.putInt(NfDef.EXTRA_POSITION, findPosition);
            mNewMsg.setData(mBundle);
            this.mHandler.sendMessage(mNewMsg);
            return;
        }
        NfLog.e(this.TAG, "queryNameByPartial() failed !! target: " + target);
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public void reqDatabaseAvailable(String address) {
        if (NfPrimitive.checkAddress(address) && !this.mHandler.hasMessages(12)) {
            Message mNewMsg = Message.obtain(this.mHandler, 12);
            Bundle mBundle = new Bundle();
            mBundle.putString(NfDef.EXTRA_DEVICE_ADDRESS, address);
            mNewMsg.setData(mBundle);
            this.mHandler.sendMessage(mNewMsg);
            return;
        }
        NfLog.e(this.TAG, "reqDatabaseAvailable() failed !!");
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public void deleteDatabase(String address) {
        if (!isAddressWrong(address)) {
            deleteDatabaseByAddress(address);
        }
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public void cleanDatabase() {
        cleanTable();
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean downloadInterrupt() {
        if (this.mClient != null) {
            if (!this.mInterrupIsRunning && this.isDownloading && this.mClient.isDownloading()) {
                NfLog.d(this.TAG, "pbapDownloadInterrupt()");
                this.downloadFailReason = 8;
                this.mIsDownloadInterrupted = true;
                this.mInterrupIsRunning = true;
                this.mClient.pbapDownloadInterrupt();
                this.mJniHandler.removeCallbacksAndMessages(null);
                checkClientDisconnect(-1);
                return true;
            } else if (!this.mInterrupIsRunning && this.isDownloading && this.mClient.changeStateMachineToDisconnecting()) {
                this.downloadFailReason = 8;
                this.mInterrupIsRunning = true;
                resetFlag();
                return true;
            } else {
                NfLog.d(this.TAG, "StateMachine state =" + this.mClient.getMostRecentState());
                return false;
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean downloadInterrupt(String address) {
        NfLog.d(this.TAG, "downloadInterrupt() " + address);
        if (isAddressWrong(address)) {
            return false;
        }
        return downloadInterrupt();
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean setPbapDownloadNotify(int frequency) {
        if (frequency >= 0) {
            this.mDownloadNotifyFrequency = frequency;
            return true;
        }
        return false;
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfacePbap
    public void onJniPbapConnectionStateChanged(String address, int state) {
        NfLog.d(this.TAG, "onJniPbapConnectionStateChanged() " + address + " state: " + state);
        int newState = state == 0 ? 110 : 160;
        int reason = -1;
        int count = -1;
        if (newState == 110) {
            reason = this.isDownloadSuccess ? 1 : 2;
            count = this.downloadCount;
        }
        boolean isUserReject = newState == 110 && !this.isRealyStartDownload;
        if (state == 0) {
            resetFlag();
        }
        if (isUserReject) {
            setDownloadState(address, newState, -1, 4, count);
        } else {
            setDownloadState(address, newState, -1, reason, count);
        }
        if (state == 2) {
            this.mDownloadingAddress = address;
            this.isRealyStartDownload = true;
            if (!this.isGetPhoneBookSizeFinished) {
                jni().reqPbapGetSize(address, this.mStorage);
            } else {
                jni().reqPbapStartDownload(address, this.mStorage, this.mFilter, this.mOffset, this.mMaxCount);
            }
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfacePbap
    public void onJniPbapDownloadStateChanged(String address, int state) {
        NfLog.d(this.TAG, "onJniPbapDownloadStateChanged() " + address + " state: " + state);
        this.isDownloadSuccess = false;
        if (state == 2) {
            if (!this.isGetPhoneBookSizeFinished) {
                this.isGetPhoneBookSizeFinished = true;
                jni().reqPbapStartDownload(address, this.mStorage, this.mFilter, this.mOffset, this.mMaxCount);
                return;
            }
            this.isNeedDeleteCallLog = true;
            this.isNeedDeleteContact = true;
            this.isDownloadSuccess = true;
            NfLog.d(this.TAG, "Download Complete All_total_time=" + (System.currentTimeMillis() - this.All_total_time));
            if (this.contacts_ops.size() > 0 && this.mStorageType == 2) {
                Log.d(this.TAG, "content_ops.size()=" + this.contacts_ops.size());
                ArrayList<ContentProviderOperation> temp_ops = new ArrayList<>(this.contacts_ops);
                this.contacts_ops.clear();
                ApplyBatchThread abt = new ApplyBatchThread(temp_ops, this.mContactsCurrentCount, 0);
                this.exService.execute(abt);
            }
            if (this.callLog_ops.size() > 0 && this.mStorageType == 2) {
                Log.d(this.TAG, "content_ops.size()=" + this.callLog_ops.size());
                ArrayList<ContentProviderOperation> temp_ops2 = new ArrayList<>(this.callLog_ops);
                this.callLog_ops.clear();
                ApplyBatchThread abt2 = new ApplyBatchThread(temp_ops2, this.mContactsCurrentCount, 1);
                this.exService.execute(abt2);
            }
            PbapDisconnectThread t = new PbapDisconnectThread(address);
            t.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PbapDisconnectThread extends Thread {
        private String mAddress;

        public PbapDisconnectThread(String address) {
            this.mAddress = address;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            _NfPbapJava.this.jni().reqPbapDisconnect(this.mAddress);
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfacePbap
    public void retJniPbapReceivePhonebookSize(String address, int access, int size) {
        NfLog.d(this.TAG, "retJniPbapReceivePhonebookSize() " + address + " access: " + access + " size: " + size);
        this.mContactsTotalCount = size;
    }

    private void checkClientDisconnect(int size) {
        NfLog.i(this.TAG, "checkDisconnect() size: " + size);
        this.mJniHandler.removeMessages(10);
        if (!this.mIsDownloadInterrupted) {
            this.isDownloadSuccess = true;
        }
        if (this.mClient != null && this.mClient.disconnect(null) && this.mStorageType == 2) {
            if (this.mStorage == 1 || this.mStorage == 2 || this.mStorage == 3 || this.mStorage == 4) {
                ArrayList<ContentProviderOperation> temp_ops = new ArrayList<>(this.contacts_ops);
                this.contacts_ops.clear();
                ApplyBatchThread abt = new ApplyBatchThread(temp_ops, -1, 0);
                this.exService.execute(abt);
            } else if (this.mStorage == 6 || this.mStorage == 5 || this.mStorage == 7) {
                ArrayList<ContentProviderOperation> temp_ops2 = new ArrayList<>(this.callLog_ops);
                this.callLog_ops.clear();
                ApplyBatchThread abt2 = new ApplyBatchThread(temp_ops2, -1, 1);
                this.exService.execute(abt2);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0178 A[Catch: all -> 0x0227, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0011, B:7:0x0016, B:9:0x001b, B:11:0x0024, B:12:0x002a, B:14:0x0030, B:16:0x0080, B:19:0x0087, B:38:0x0105, B:40:0x010b, B:41:0x012f, B:22:0x0090, B:25:0x0097, B:42:0x0133, B:44:0x0139, B:45:0x015d, B:27:0x009e, B:29:0x00a4, B:30:0x00c8, B:32:0x00ce, B:46:0x0161, B:48:0x0169, B:49:0x016d, B:51:0x0172, B:53:0x0178, B:55:0x017e, B:56:0x01cc, B:58:0x01dc, B:60:0x01e7, B:62:0x01f1, B:63:0x0208, B:65:0x0212, B:67:0x021d, B:108:0x02ed, B:110:0x02f5, B:113:0x02ff, B:114:0x0329, B:116:0x032f, B:81:0x0240, B:83:0x0246, B:85:0x0251, B:87:0x0259, B:88:0x0262, B:90:0x0289, B:91:0x029d, B:93:0x02a3, B:94:0x02b0, B:96:0x02b5, B:98:0x02bb, B:142:0x03ae, B:144:0x03b4, B:145:0x03ba, B:147:0x03c2, B:149:0x03ca, B:150:0x03f2, B:152:0x03f8, B:125:0x035e, B:127:0x0364, B:128:0x036a, B:130:0x0370, B:133:0x037e, B:172:0x04ac, B:174:0x04b4, B:176:0x04bc, B:177:0x04e6, B:179:0x051b, B:181:0x0525, B:161:0x0425, B:163:0x0464, B:165:0x046e, B:37:0x00de), top: B:185:0x0001 }] */
    @Override // com.nforetek.bt.jni.NfJniCallbackInterfacePbap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void retJniPbapReceiveContacts(java.lang.String r41, int r42, java.lang.String r43, java.lang.String r44, java.lang.String r45, java.lang.String r46, int r47, java.lang.String[] r48, int[] r49, java.lang.String r50, int r51, byte[] r52, int r53, int[] r54, java.lang.String[] r55, int[] r56, java.lang.String[] r57, java.lang.String r58) {
        /*
            Method dump skipped, instructions count: 1382
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.profile.pbap.java._NfPbapJava.retJniPbapReceiveContacts(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String[], int[], java.lang.String, int, byte[], int, int[], java.lang.String[], int[], java.lang.String[], java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ApplyBatchThread extends Thread {
        private int choose;
        private int data_index;
        private ArrayList<ContentProviderOperation> ops;

        ApplyBatchThread(ArrayList<ContentProviderOperation> ops, int data_index, int choose) {
            this.ops = ops;
            this.data_index = data_index;
            this.choose = choose;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            switch (this.choose) {
                case 0:
                    _NfPbapJava.this.ApplyBatchContact(this.ops, this.data_index);
                    return;
                case 1:
                    _NfPbapJava.this.ApplyBatchCallLog(this.ops, this.data_index);
                    return;
                case 2:
                    _NfPbapJava.this.delete_all_ContactsData();
                    return;
                case 3:
                    _NfPbapJava.this.delete_all_CallLog();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ApplyBatchCallLog(ArrayList<ContentProviderOperation> callLog_ops, int data_index) {
        NfLog.d(this.TAG, "ApplyBatchCallLog");
        try {
            context().getContentResolver().applyBatch("call_log", callLog_ops);
        } catch (OperationApplicationException e) {
            Log.d(this.TAG, "CallLog applyBatch OperationApplicationException");
            Log.e(this.TAG, e.toString());
            e.printStackTrace();
        } catch (RemoteException e2) {
            Log.d(this.TAG, "CallLog applyBatch RemoteException");
            Log.e(this.TAG, e2.toString());
            e2.printStackTrace();
        }
        Log.d(this.TAG, "CallLog All_total Time = " + (System.currentTimeMillis() - this.All_total_time) + " index=" + data_index + " callLog_ops's size=" + callLog_ops.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ApplyBatchContact(ArrayList<ContentProviderOperation> contacts_ops, int data_index) {
        NfLog.d(this.TAG, "ApplyBatchContact");
        try {
            context().getContentResolver().applyBatch("com.android.contacts", contacts_ops);
        } catch (OperationApplicationException e) {
            Log.d(this.TAG, "ContactsContract applyBatch OperationApplicationException");
            Log.e(this.TAG, e.toString());
            e.printStackTrace();
        } catch (RemoteException e2) {
            Log.d(this.TAG, "ContactsContract applyBatch RemoteException");
            Log.e(this.TAG, e2.toString());
            e2.printStackTrace();
        }
        Log.d(this.TAG, "Contact All_total Time = " + (System.currentTimeMillis() - this.All_total_time) + " index=" + data_index + " contacts_ops's size=" + contacts_ops.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delete_all_ContactsData() {
        NfLog.d(this.TAG, "delete_all_ContactsData");
        ArrayList<ContentProviderOperation> delete_data_ops = new ArrayList<>();
        delete_data_ops.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection(null, null).build());
        delete_data_ops.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI).withSelection(null, null).build());
        try {
            context().getContentResolver().applyBatch("com.android.contacts", delete_data_ops);
        } catch (OperationApplicationException e) {
            Log.d(this.TAG, "Delete ContactsContract applyBatch OperationApplicationException");
            Log.e(this.TAG, e.toString());
            e.printStackTrace();
        } catch (RemoteException e2) {
            Log.d(this.TAG, "Delete ContactsContract applyBatch RemoteException");
            Log.e(this.TAG, e2.toString());
            e2.printStackTrace();
        }
        Log.d(this.TAG, "Delete Contact All_total Time = " + (System.currentTimeMillis() - this.All_total_time));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delete_all_CallLog() {
        NfLog.d(this.TAG, "delete_all_CallLog");
        ArrayList<ContentProviderOperation> delete_calllog_ops = new ArrayList<>();
        delete_calllog_ops.add(ContentProviderOperation.newDelete(CallLog.Calls.CONTENT_URI).withSelection(null, null).build());
        try {
            context().getContentResolver().applyBatch("call_log", delete_calllog_ops);
        } catch (OperationApplicationException e) {
            Log.d(this.TAG, "Delete CallLog applyBatch OperationApplicationException");
            Log.e(this.TAG, e.toString());
            e.printStackTrace();
        } catch (RemoteException e2) {
            Log.d(this.TAG, "Delete CallLog applyBatch RemoteException");
            Log.e(this.TAG, e2.toString());
            e2.printStackTrace();
        }
        Log.d(this.TAG, "Delete CallLog All_total Time = " + (System.currentTimeMillis() - this.All_total_time));
    }

    private void setNeedGetPhoneBookSize(int storage) {
        if (storage == 1 || storage == 2) {
            this.isGetPhoneBookSizeFinished = false;
        } else {
            this.isGetPhoneBookSizeFinished = false;
        }
    }

    private void cleanTable() {
        String packageName;
        Log.e(this.TAG, "pbapCleanTable()");
        while (getProfileState() > 110) {
            try {
                Log.e(this.TAG, "Piggy Check waiting for Download finished ...");
                try {
                    Thread.sleep(getProfileState() == 140 ? 1000 : NfDef.BT_STATE_OFF);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                Log.e(this.TAG, "Critical Exception !! " + e2 + " \n Please contact with Piggy , thanks !");
                callback().retPbapCleanDatabaseCompleted(false);
            }
        }
        if (this.dbHelper != null) {
            this.dbHelper.close();
        }
        ContentResolver cr = context().getContentResolver();
        ContentProviderClient cpc = cr.acquireContentProviderClient(NfDef.DEFAULT_AUTHORITIES);
        try {
            packageName = cpc.getType(Uri.parse("content://nfore.db.provider"));
        } catch (RemoteException e1) {
            e1.printStackTrace();
            Log.e(this.TAG, "getPackageName of DB get Exception");
        }
        Context _context = null;
        try {
            _context = context().createPackageContext(packageName, 2);
        } catch (PackageManager.NameNotFoundException e3) {
            Log.e(this.TAG, "createPackageContext: \"" + packageName + "\", NameNotFoundException");
        }
        this.dbHelper = new DbHelperPbap(_context);
        this.db = this.dbHelper.getWritableDatabase();
        this.db.beginTransaction();
        this.dbHelper.deleteAllTableContent(this.db);
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
        this.db.close();
        callback().retPbapCleanDatabaseCompleted(true);
    }

    private synchronized void deleteDatabaseByAddress(String address) {
        String packageName;
        Log.e(this.TAG, "pbapDeleteDatabaseByAddress " + address);
        while (getProfileState() > 110) {
            try {
                Log.e(this.TAG, "Piggy Check waiting for Download finished ...");
                try {
                    Thread.sleep(getProfileState() == 140 ? 1000 : NfDef.BT_STATE_OFF);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                Log.e(this.TAG, "Critical Exception !! " + e2 + " \n Please contact with Piggy , thanks !");
                callback().retPbapDeleteDatabaseByAddressCompleted(address, false);
            }
        }
        if (this.dbHelper != null) {
            this.dbHelper.close();
        }
        ContentResolver cr = context().getContentResolver();
        ContentProviderClient cpc = cr.acquireContentProviderClient(NfDef.DEFAULT_AUTHORITIES);
        try {
            packageName = cpc.getType(Uri.parse("content://nfore.db.provider"));
        } catch (RemoteException e1) {
            e1.printStackTrace();
            Log.e(this.TAG, "getPackageName of DB get Exception");
        }
        Context _context = null;
        try {
            _context = context().createPackageContext(packageName, 2);
        } catch (PackageManager.NameNotFoundException e3) {
            Log.e(this.TAG, "createPackageContext: \"" + packageName + "\", NameNotFoundException");
        }
        this.dbHelper = new DbHelperPbap(_context);
        this.db = this.dbHelper.getWritableDatabase();
        this.db.beginTransaction();
        Log.e(this.TAG, "Piggy Check delete SIM count            : " + this.dbHelper.deleteVcardInfo(this.db, address, 1));
        Log.e(this.TAG, "Piggy Check delete Memory count         : " + this.dbHelper.deleteVcardInfo(this.db, address, 2));
        Log.e(this.TAG, "Piggy Check delete Dial Calls count     : " + this.dbHelper.deleteCallHistoryInfo(this.db, address, 3));
        Log.e(this.TAG, "Piggy Check delete Missed Calls count   : " + this.dbHelper.deleteCallHistoryInfo(this.db, address, 4));
        Log.e(this.TAG, "Piggy Check delete Received Calls count : " + this.dbHelper.deleteCallHistoryInfo(this.db, address, 5));
        this.db.setTransactionSuccessful();
        this.db.endTransaction();
        this.db.close();
        callback().retPbapDeleteDatabaseByAddressCompleted(address, true);
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void dequeueMessage(Message msg) {
        NfJniBundle b = (NfJniBundle) msg.obj;
        switch (msg.what) {
            case 10:
                NfLog.v(this.TAG, "ckeck disconnet =" + msg.arg1);
                checkClientDisconnect(msg.arg1);
                return;
            case 301:
                NfLog.v(this.TAG, "onJniPbapConnectionStateChanged()");
                onJniPbapConnectionStateChanged(b.getString1(), b.getInt1());
                return;
            case 302:
                NfLog.v(this.TAG, "onJniPbapDownloadStateChanged()");
                onJniPbapDownloadStateChanged(b.getString1(), b.getInt1());
                return;
            case 303:
                NfLog.v(this.TAG, "retJniPbapReceivePhonebookSize()");
                retJniPbapReceivePhonebookSize(b.getString1(), b.getInt1(), b.getInt2());
                return;
            case NfJni.retJniPbapReceiveContacts /* 304 */:
                NfLog.v(this.TAG, "retJniPbapReceiveContacts()");
                if (!detectPbapInterrupted()) {
                    retJniPbapReceiveContacts(b.getString1(), b.getInt1(), b.getString7(), b.getString2(), b.getString3(), b.getString4(), b.getInt2(), b.getStringArray1(), b.getIntArray1(), b.getString5(), b.getInt3(), b.getByteArray1(), b.getInt4(), b.getIntArray2(), b.getStringArray2(), b.getIntArray3(), b.getStringArray3(), b.getString6());
                    return;
                }
                return;
            default:
                return;
        }
    }

    private boolean detectPbapInterrupted() {
        if (!NfPrimitive.isBtEnabled() || this.downloadFailReason == 8 || this.mIsDownloadInterrupted) {
            NfLog.v(this.TAG, "detectPbapInterrupted remove retJniPbapReceiveContacts");
            this.mJniHandler.removeMessages(NfJni.retJniPbapReceiveContacts);
            return true;
        }
        return false;
    }

    private void resetFlag() {
        NfLog.v(this.TAG, "resetFlag");
        this.mDownloadingAddress = NfDef.DEFAULT_ADDRESS;
        this.mConnectingAddress = NfDef.DEFAULT_ADDRESS;
        this.mStorage = -1;
        this.mFilter = -1;
        this.isDownloadSuccess = false;
        this.downloadFailReason = -1;
        this.downloadCount = 0;
        this.isDownloading = false;
        this.isRealyStartDownload = false;
        this.mContactsTotalCount = 0;
        this.mContactsCurrentCount = 0;
        this.mContactsParsingCount = 0;
        this.mOffset = 0;
        this.mMaxCount = 0;
        this.isGetPhoneBookSizeFinished = true;
    }

    public void onPhoneBookPullDoneCheckSize(int size) {
        NfLog.v(this.TAG, "downloadingFinish " + size);
        Message m2 = Message.obtain(this.mJniHandler, 10, size, -1);
        m2.sendToTarget();
    }

    public void onPhoneBookSizeDone(int size) {
        NfLog.v(this.TAG, "onPhoneBookSizeDone() size:" + size);
        NfJniBundle b2 = new NfJniBundle();
        b2.setString1(null);
        b2.setInt1(-1);
        b2.setInt2(size);
        Message m2 = Message.obtain(this.mJniHandler, 303, b2);
        m2.sendToTarget();
    }

    public void onPhoneBookPullDone(String address, VCardEntry e, int contactsType) {
        NfLog.v(this.TAG, "onPhoneBookPullDone() type:" + contactsType);
        if (e == null) {
            NfLog.v(this.TAG, "onPhoneBookPullDone got null?? return");
            this.isDownloadSuccess = false;
            return;
        }
        this.mJniHandler.removeMessages(10);
        this.mJniHandler.sendEmptyMessageDelayed(10, 125000L);
        if (!detectPbapInterrupted()) {
            int index = this.mContactsParsingCount;
            this.mContactsParsingCount++;
            NfLog.v(this.TAG, "Piggy Check index: " + index);
            NfJniBundle b = new NfJniBundle();
            b.setString1(address);
            b.setInt1(index);
            b.setString2(e.getNameData().getGiven() == null ? "" : e.getNameData().getGiven());
            b.setString3(e.getNameData().getMiddle() == null ? "" : e.getNameData().getMiddle());
            b.setString4(e.getNameData().getFamily() == null ? "" : e.getNameData().getFamily());
            b.setString7(e.getNameData().getFormatted() == null ? "" : e.getNameData().getFormatted());
            b.setInt2(-1);
            String[] phoneList = new String[0];
            int[] phoneTypeList = new int[0];
            if (e.getPhoneList() != null) {
                phoneList = new String[e.getPhoneList().size()];
                phoneTypeList = new int[e.getPhoneList().size()];
                int i = 0;
                for (VCardEntry.PhoneData d : e.getPhoneList()) {
                    phoneList[i] = d.getNumber();
                    phoneTypeList[i] = d.getType();
                    i++;
                }
            }
            b.setStringArray1(phoneList);
            b.setIntArray1(phoneTypeList);
            b.setString5("");
            try {
                b.setString5(e.dateTime);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            b.setInt3(contactsType);
            if (e.dialType != -1) {
                b.setInt3(e.dialType);
            }
            List<VCardEntry.PhotoData> photoList = e.getPhotoList();
            if (photoList != null && photoList.size() > 0) {
                byte[] resultPhoto = photoList.get(0).getBytes();
                b.setByteArray1(resultPhoto);
                b.setInt4(-1);
            } else {
                b.setByteArray1(null);
            }
            String[] emailList = new String[0];
            int[] emailTypeList = new int[0];
            if (e.getEmailList() != null) {
                emailList = new String[e.getEmailList().size()];
                emailTypeList = new int[e.getEmailList().size()];
                int i2 = 0;
                for (VCardEntry.EmailData d2 : e.getEmailList()) {
                    emailList[i2] = d2.getAddress();
                    emailTypeList[i2] = d2.getType();
                    NfLog.d(this.TAG, "Check emailTypeList " + d2.getType());
                    i2++;
                }
            }
            b.setIntArray2(emailTypeList);
            b.setStringArray2(emailList);
            String[] addressList = new String[0];
            int[] addressTypeList = new int[0];
            if (e.getPostalList() != null) {
                addressList = new String[e.getPostalList().size()];
                addressTypeList = new int[e.getPostalList().size()];
                int i3 = 0;
                for (VCardEntry.PostalData d3 : e.getPostalList()) {
                    addressList[i3] = d3.getFormattedAddress();
                    addressTypeList[i3] = d3.getType();
                    NfLog.d(this.TAG, "Check addressTypeList " + d3.getType());
                    i3++;
                }
            }
            b.setIntArray3(addressTypeList);
            b.setStringArray3(addressList);
            if (e.getOrganizationList() != null && e.getOrganizationList().size() > 0) {
                b.setString6(e.getOrganizationList().get(0).getOrganizationName());
            }
            Message m = Message.obtain(this.mJniHandler, NfJni.retJniPbapReceiveContacts, b);
            m.sendToTarget();
            int i4 = index + 1;
            NfLog.v(" ", " ");
        }
    }

    public void onPhoneBookPullDone(String address, List<VCardEntry> entries, int contactsType) {
        NfLog.v(this.TAG, "onPhoneBookPullDone() type:" + contactsType);
        if (entries == null) {
            NfLog.v(this.TAG, "onPhoneBookPullDone got null?? return");
            this.isDownloadSuccess = false;
            return;
        }
        NfLog.v(this.TAG, "Piggy Check contacts size: " + entries.size());
    }

    public void onConnectionStateChanged(String address, int prevState, int state, int pbap_reason) {
        int newState;
        NfLog.d(this.TAG, "onConnectionStateChanged() " + address + " state: " + state);
        switch (state) {
            case 0:
                newState = 110;
                break;
            case 1:
                newState = 120;
                break;
            case 2:
                newState = 160;
                break;
            case 3:
                newState = 140;
                break;
            default:
                return;
        }
        int reason = -1;
        int count = -1;
        boolean isUserReject = newState == 110 && prevState < 160;
        if (newState == 110 || newState == 140) {
            reason = pbap_reason == -1 ? 2 : pbap_reason;
            if (this.isDownloadSuccess) {
                NfLog.d(this.TAG, "REASON_DOWNLOAD_FULL_CONTENT_COMPLETED");
                reason = 1;
            } else if (this.downloadFailReason != -1 && reason != 1) {
                NfLog.d(this.TAG, "downloadFailReason " + this.downloadFailReason);
                reason = this.downloadFailReason;
            } else {
                if (isUserReject) {
                    reason = 4;
                }
                NfLog.d(this.TAG, "Fail " + reason);
            }
            count = this.downloadCount;
        }
        setDownloadState(address, newState, -1, reason, count);
        if (state == 0) {
            resetFlag();
        }
    }

    public int getNfDefPhoneType(int type) {
        switch (type) {
            case 1:
                return 3;
            case 2:
                return 7;
            case 3:
                return 2;
            case 4:
            case 5:
                return 5;
            case 6:
                return 8;
            default:
                return 0;
        }
    }

    public int getNfDefEmailType(int type) {
        switch (type) {
            case 1:
                return 5;
            case 2:
                return 4;
            default:
                return 0;
        }
    }

    public int getNfDefAddressType(int type) {
        switch (type) {
            case 1:
                return 6;
            case 2:
                return 5;
            default:
                return 0;
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
        callback().onPbapStateChanged(address, prevState, state, reason, count);
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onBtAclDisconnected(String address) {
        Log.i(this.TAG, "onBtAclDisconnected: " + address);
        Log.i(this.TAG, "mClient.getMostRecentState(): " + this.mClient.getMostRecentState());
        if ((!this.mDownloadingAddress.equals(NfDef.DEFAULT_ADDRESS) && this.mDownloadingAddress.equals(address)) || (!this.mConnectingAddress.equals(NfDef.DEFAULT_ADDRESS) && this.mConnectingAddress.equals(address))) {
            forceResetState();
        }
        super.onBtAclDisconnected(address);
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
        Log.i(this.TAG, "forceResetState");
        if (this.mClient.getMostRecentState() != 2) {
            this.mClient.changeStateMachineToDisconnecting();
        } else {
            this.mJniHandler.removeCallbacksAndMessages(null);
            checkClientDisconnect(-1);
        }
        resetFlag();
        super.forceResetState();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void onSdpUpdated(String address, ParcelUuid uuid, int l2capPsm) {
        NfLog.v(this.TAG, "Received UUID: " + uuid.toString());
        NfLog.v(this.TAG, "device: " + address);
        NfLog.v(this.TAG, "l2capPsm: " + l2capPsm);
        if (uuid.equals(BluetoothUuid.PBAP_PSE)) {
            this.mL2capPsm = l2capPsm;
        }
    }
}
