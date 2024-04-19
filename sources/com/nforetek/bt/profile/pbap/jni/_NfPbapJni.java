package com.nforetek.bt.profile.pbap.jni;

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
import android.os.RemoteException;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import com.nforetek.bt.aidl.NfPbapContact;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.jni.NfJniBundle;
import com.nforetek.bt.profile.pbap._NfPbap;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.db.DbHelperPbap;
import com.nforetek.util.normal.ContactsHelper;
import com.nforetek.util.normal.NfLog;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public class _NfPbapJni extends _NfPbap {
    private static final int HANDLER_EVENT_PBAP_DB_QUERY = 12;
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
    private ArrayList<String> mQueryFullNumberList;
    private ArrayList<String> mQueryPartialNumberList;
    private int mStorage = -1;
    private String mAddress = NfDef.DEFAULT_ADDRESS;
    private boolean isNeedDeleteCallLogAll = true;
    private boolean isNeedDeleteCallLogMissed = true;
    private boolean isNeedDeleteCallLogDailed = true;
    private boolean isNeedDeleteCallLogReceived = true;
    private boolean isNeedDeleteContact = true;
    private boolean isDownloadSuccess = true;
    private int mStorageType = -1;
    private int downloadCount = 0;
    private boolean isDownloading = false;
    private boolean isRealyStartDownload = false;
    private int mContactsTotalCount = 0;
    private int mContactsCurrentCount = 0;
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
    private final int ContactsProvider_DeleteCallLogAll = 3;
    private final int ContactsProvider_DeleteCallLogMissed = 4;
    private final int ContactsProvider_DeleteCallLogDailed = 5;
    private final int ContactsProvider_DeleteCallLogReceive = 6;
    private boolean isAlreadyQueryName = false;
    private int mFilter = NfDef.GATT_STATUS_ILLEGAL_PARAMETER;
    private boolean mIsDownloadInterrupted = false;
    private final Handler mHandler = new Handler() { // from class: com.nforetek.bt.profile.pbap.jni._NfPbapJni.1
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
            throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.profile.pbap.jni._NfPbapJni.AnonymousClass1.handleMessage(android.os.Message):void");
        }
    };

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfPbapJni";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return 6;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
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
        super.onCreate();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
        if (getProfileState() >= 110) {
            resetFlag();
        }
        super.forceResetState();
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean isDownloading() {
        return this.isDownloading || this.mHandler.hasMessages(12);
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public String getDownloadingAddress() {
        return getConnectedAddress();
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean downloadByPass(String address, int storage, int property, int startPos, int offset) {
        boolean success = false;
        if (NfPrimitive.isAddressValid(address)) {
            if (this.isDownloading) {
                NfLog.e(this.TAG, "isDownloading, state: " + getProfileState() + " isQueryDbAvaible? " + this.mHandler.hasMessages(12));
            } else {
                this.All_total_time = System.currentTimeMillis();
                this.isDownloading = true;
                this.mIsDownloadInterrupted = false;
                this.mStorage = storage;
                this.mStorageType = 0;
                this.mFilter = property;
                this.mOffset = startPos;
                this.mMaxCount = offset;
                setNeedGetPhoneBookSize(storage);
                success = jni().reqPbapConnect(address, true);
                if (success) {
                    setDownloadState(address, 160, -1, -1, -1);
                } else {
                    resetFlag();
                }
            }
        }
        return success;
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean downloadToDb(String address, int storage, int property, int startPos, int offset) {
        boolean success = false;
        if (NfPrimitive.isAddressValid(address)) {
            if (this.isDownloading) {
                NfLog.e(this.TAG, "isDownloading, state: " + getProfileState());
            } else {
                this.All_total_time = System.currentTimeMillis();
                this.isDownloading = true;
                this.mIsDownloadInterrupted = false;
                this.mStorage = storage;
                this.mStorageType = 1;
                this.mFilter = property;
                this.mOffset = startPos;
                this.mMaxCount = startPos + offset;
                setNeedGetPhoneBookSize(storage);
                success = jni().reqPbapConnect(address, true);
                if (success) {
                    setDownloadState(address, 160, -1, -1, -1);
                } else {
                    resetFlag();
                }
            }
        }
        return success;
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean downloadToProvider(String address, int storage, int property, int startPos, int offset) {
        boolean success = false;
        if (NfPrimitive.isAddressValid(address)) {
            if (this.isDownloading) {
                NfLog.e(this.TAG, "isDownloading, state: " + getProfileState());
            } else {
                this.All_total_time = System.currentTimeMillis();
                this.count_Index = 0;
                this.isDownloading = true;
                this.mIsDownloadInterrupted = false;
                this.mStorage = storage;
                this.mStorageType = 2;
                this.mFilter = property;
                this.mOffset = startPos;
                this.mMaxCount = startPos + offset;
                setNeedGetPhoneBookSize(storage);
                success = jni().reqPbapConnect(address, true);
                if (success) {
                    setDownloadState(address, 160, -1, -1, -1);
                } else {
                    resetFlag();
                }
            }
        }
        return success;
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
        deleteDatabaseByAddress(address);
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public void cleanDatabase() {
        cleanTable();
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean downloadInterrupt() {
        return false;
    }

    @Override // com.nforetek.bt.profile.pbap._NfPbap
    public boolean downloadInterrupt(String address) {
        if (NfPrimitive.isAddressValid(address) && !address.equals(NfDef.DEFAULT_ADDRESS)) {
            String str = this.TAG;
            StringBuilder sb = new StringBuilder("Is interrupt downloading susses? ");
            boolean reqPbapDisconnect = jni().reqPbapDisconnect(address);
            this.mIsDownloadInterrupted = reqPbapDisconnect;
            NfLog.i(str, sb.append(reqPbapDisconnect).toString());
            if (this.mIsDownloadInterrupted && this.mStorageType == 2 && this.contacts_ops.size() > 0) {
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
            return this.mIsDownloadInterrupted;
        }
        return false;
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
            this.mAddress = address;
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
            this.isNeedDeleteCallLogAll = true;
            this.isNeedDeleteContact = true;
            this.isDownloadSuccess = true;
            NfLog.d(this.TAG, "Nick Download Complete All_total_time=" + (System.currentTimeMillis() - this.All_total_time));
            if (this.contacts_ops.size() > 0 && this.mStorageType == 2) {
                Log.d(this.TAG, "Nick content_ops.size()=" + this.contacts_ops.size());
                ArrayList<ContentProviderOperation> temp_ops = new ArrayList<>(this.contacts_ops);
                this.contacts_ops.clear();
                ApplyBatchThread abt = new ApplyBatchThread(temp_ops, this.mContactsCurrentCount, 0);
                this.exService.execute(abt);
            }
            if (this.callLog_ops.size() > 0 && this.mStorageType == 2) {
                Log.d(this.TAG, "Nick content_ops.size()=" + this.callLog_ops.size());
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
            if (NfPrimitive.isAddressValid(this.mAddress) && !this.mAddress.equals(NfDef.DEFAULT_ADDRESS)) {
                _NfPbapJni.this.jni().reqPbapDisconnect(this.mAddress);
            }
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfacePbap
    public void retJniPbapReceivePhonebookSize(String address, int access, int size) {
        NfLog.d(this.TAG, "retJniPbapReceivePhonebookSize() " + address + " access: " + access + " size: " + size);
        this.mContactsTotalCount = size;
        if (this.mStorage != access) {
            NfLog.e(this.TAG, "mStorage and access is different ! mStorage: " + this.mStorage + " access: " + access);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.nforetek.bt.jni.NfJniCallbackInterfacePbap
    public synchronized void retJniPbapReceiveContacts(String bd_address, int contactsId, String fullName, String firstName, String middleName, String lastName, int encode, String[] number, int[] phoneType, String timestamp, int contactType, byte[] photo, int photoType, int[] emailType, String[] email, int[] addressType, String[] address, String org2) {
        boolean isNeedDelete;
        boolean isNeedDelete2;
        String firstNumber = "";
        int firstPhoneType = -1;
        this.downloadCount++;
        Log.i(this.TAG, "retJniPbapReceiveContacts");
        if (number != null && number.length > 0) {
            firstNumber = number[0];
            firstPhoneType = phoneType[0];
        }
        NfLog.i(this.TAG, "retJniPbapReceiveContacts() " + firstName + "|" + middleName + "|" + lastName + " (" + firstNumber + "|" + firstPhoneType + ")");
        if (emailType != null && emailType.length != 0) {
            for (int i = 0; i < emailType.length; i++) {
                NfLog.i(this.TAG, "retJniPbapReceiveContacts() email: (" + emailType[i] + ") " + email[i]);
            }
        }
        if (addressType != null && addressType.length != 0) {
            for (int i2 = 0; i2 < addressType.length; i2++) {
                NfLog.i(this.TAG, "retJniPbapReceiveContacts() address: (" + addressType[i2] + ") " + address[i2]);
            }
        }
        NfLog.v(this.TAG, "in retJniPbapReceiveContacts: contactType = " + contactType);
        if (!NfPrimitive.isBtEnabled()) {
            NfLog.v(this.TAG, "BT Off now. drop this contact");
        } else {
            switch (this.mStorageType) {
                case 0:
                    if (contactType == 1 || contactType == 2 || contactType == 3 || contactType == 4) {
                        if (photo == null) {
                            NfLog.v(this.TAG, "Piggy Check photo is null");
                        } else if (photo.length > 0) {
                            NfLog.v(this.TAG, "Piggy Check photo.length: " + photo.length);
                        }
                        if (photo != null && (this.mFilter & 8) == 0) {
                            NfLog.v(this.TAG, "Didn't requie photo but receive photo byte array.");
                        }
                        NfPbapContact contact = new NfPbapContact(bd_address, contactType, firstName, middleName, lastName, phoneType, number, photoType, photo, emailType, email, addressType, address, org2);
                        NfLog.v(this.TAG, new StringBuilder().append(contact).toString());
                        callback().retPbapDownloadedContact(contact);
                        break;
                    } else {
                        if (contactType == 0 || contactType == 6 || contactType == 5 || contactType == 7) {
                            if (this.mStorage != 8 && this.mStorage != contactType && contactType != 0) {
                                NfLog.e(this.TAG, "Get wrong contact type not match as request! request type is " + this.mStorage + ", receivce type is " + contactType);
                                break;
                            } else {
                                if ((timestamp.length() != 15 && timestamp.length() != 16) || !isCharcterIsDigit(timestamp.charAt(0))) {
                                    NfLog.e(this.TAG, "TimeStamp length invalid! timestamp length is " + timestamp.length() + " timestamp: " + timestamp);
                                    timestamp = "";
                                }
                                callback().retPbapDownloadedCallLog(this.mAddress, firstName, middleName, lastName, firstNumber, contactType, timestamp);
                                break;
                            }
                        }
                        break;
                    }
                    break;
                case 1:
                    if (contactType == 1 || contactType == 2 || contactType == 3 || contactType == 4) {
                        if (!this.isNeedDeleteContact) {
                            isNeedDelete = false;
                        } else {
                            isNeedDelete = true;
                            this.isNeedDeleteContact = false;
                        }
                        ContactsHelper.addContactsToDatabases(context(), this.mAddress, contactType, firstName, middleName, lastName, number, phoneType, isNeedDelete, (this.mFilter & 8) != 0 ? photo : null, emailType, email, addressType, address, org2);
                        break;
                    } else {
                        if (contactType == 0 || contactType == 6 || contactType == 5 || contactType == 7) {
                            if (!this.isNeedDeleteCallLogAll) {
                                isNeedDelete2 = false;
                            } else {
                                isNeedDelete2 = true;
                                this.isNeedDeleteCallLogAll = false;
                            }
                            if (this.mStorage != 8 && this.mStorage != contactType) {
                                NfLog.e(this.TAG, "Get wrong contact type not match as request! request type is " + this.mStorage + ", receivce type is " + contactType);
                                break;
                            } else {
                                if (timestamp.length() != 15 || timestamp.length() != 16) {
                                    NfLog.e(this.TAG, "TimeStamp length invalid! timestamp length is " + timestamp.length() + "timestamp: " + timestamp);
                                    timestamp = "";
                                }
                                ContactsHelper.addCallLogToDatabases(context(), this.mAddress, firstName, middleName, lastName, firstNumber, firstPhoneType, timestamp, contactType, isNeedDelete2);
                                break;
                            }
                        }
                        break;
                    }
                    break;
                case 2:
                    if (contactType == 1 || contactType == 2 || contactType == 3 || contactType == 4) {
                        if (this.isNeedDeleteContact) {
                            ApplyBatchThread abt = new ApplyBatchThread(this.contacts_ops, this.count_Index, 2);
                            this.exService.execute(abt);
                            this.isNeedDeleteContact = false;
                        }
                        ContactsHelper.addContactToProvider(this.contacts_ops, this.contacts_ops.size(), context(), fullName, firstName, middleName, lastName, number, phoneType, photo, emailType, email, addressType, address, org2);
                        this.count_Index++;
                        if (this.count_Index % 50 == 0 || this.count_Index == this.mContactsTotalCount) {
                            ArrayList<ContentProviderOperation> temp_ops = new ArrayList<>(this.contacts_ops);
                            this.contacts_ops.clear();
                            ApplyBatchThread abt2 = new ApplyBatchThread(temp_ops, this.count_Index, 0);
                            this.exService.execute(abt2);
                            break;
                        }
                        break;
                    } else if (contactType == 6 || contactType == 5 || contactType == 7) {
                        if (this.mStorage != 8 && this.mStorage != contactType) {
                            NfLog.e(this.TAG, "Get wrong contact type not match as request! request type is " + this.mStorage + ", receivce type is " + contactType);
                            break;
                        } else {
                            if (this.isNeedDeleteCallLogAll) {
                                ApplyBatchThread abt3 = new ApplyBatchThread(this.callLog_ops, this.count_Index, 3);
                                this.exService.execute(abt3);
                                this.isNeedDeleteCallLogAll = false;
                            }
                            ContactsHelper.addCallLogToProvider(this.callLog_ops, this.callLog_ops.size(), context(), firstName, middleName, lastName, "", firstNumber, timestamp, contactType);
                            this.count_Index++;
                            if (this.count_Index % 50 == 0 || this.count_Index == this.mContactsTotalCount) {
                                ArrayList<ContentProviderOperation> temp_ops2 = new ArrayList<>(this.callLog_ops);
                                this.callLog_ops.clear();
                                ApplyBatchThread abt4 = new ApplyBatchThread(temp_ops2, this.count_Index, 1);
                                this.exService.execute(abt4);
                                break;
                            }
                        }
                    }
                    break;
            }
            this.mContactsCurrentCount++;
            if (this.mDownloadNotifyFrequency != 0 && (this.mContactsCurrentCount % this.mDownloadNotifyFrequency == 0 || this.mContactsCurrentCount == this.mContactsTotalCount)) {
                callback().onPbapDownloadNotify(bd_address, this.mStorage, this.mContactsTotalCount, this.mContactsCurrentCount);
            }
            NfLog.d(this.TAG, "retJniPbapReceiveContacts() " + firstName + "|" + middleName + "|" + lastName + " (" + firstNumber + "|" + firstPhoneType + ") finished.");
        }
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
                    _NfPbapJni.this.ApplyBatchContact(this.ops, this.data_index);
                    return;
                case 1:
                    _NfPbapJni.this.ApplyBatchCallLog(this.ops, this.data_index);
                    return;
                case 2:
                    _NfPbapJni.this.delete_all_ContactsData();
                    return;
                case 3:
                    _NfPbapJni.this.delete_all_CallLog();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ApplyBatchCallLog(ArrayList<ContentProviderOperation> callLog_ops, int data_index) {
        NfLog.d(this.TAG, "Nick ApplyBatchCallLog");
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
        Log.d(this.TAG, "Nick CallLog All_total Time = " + (System.currentTimeMillis() - this.All_total_time) + " index=" + data_index + " callLog_ops's size=" + callLog_ops.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ApplyBatchContact(ArrayList<ContentProviderOperation> contacts_ops, int data_index) {
        NfLog.d(this.TAG, "Nick ApplyBatchContact");
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
        Log.d(this.TAG, "Nick Contact All_total Time = " + (System.currentTimeMillis() - this.All_total_time) + " index=" + data_index + " contacts_ops's size=" + contacts_ops.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delete_all_ContactsData() {
        NfLog.d(this.TAG, "Nick delete_all_ContactsData");
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
        Log.d(this.TAG, "Nick Delete Contact All_total Time = " + (System.currentTimeMillis() - this.All_total_time));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delete_all_CallLog() {
        NfLog.d(this.TAG, "Nick delete_all_CallLog");
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
        Log.d(this.TAG, "Nick Delete CallLog All_total Time = " + (System.currentTimeMillis() - this.All_total_time));
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
                if (!NfPrimitive.isBtEnabled() || this.mIsDownloadInterrupted) {
                    this.mJniHandler.removeMessages(NfJni.retJniPbapReceiveContacts);
                    return;
                } else {
                    retJniPbapReceiveContacts(b.getString1(), b.getInt1(), null, b.getString2(), b.getString3(), b.getString4(), b.getInt2(), b.getStringArray1(), b.getIntArray1(), b.getString5(), b.getInt3(), b.getByteArray1(), b.getInt4(), b.getIntArray2(), b.getStringArray2(), b.getIntArray3(), b.getStringArray3(), b.getString6());
                    return;
                }
            default:
                return;
        }
    }

    private void resetFlag() {
        NfLog.v(this.TAG, "resetFlag()");
        this.mAddress = NfDef.DEFAULT_ADDRESS;
        this.mStorage = -1;
        this.mFilter = -1;
        this.downloadCount = 0;
        this.isDownloading = false;
        this.isRealyStartDownload = false;
        this.mContactsTotalCount = 0;
        this.mContactsCurrentCount = 0;
        this.mOffset = 0;
        this.mMaxCount = 0;
        this.isGetPhoneBookSizeFinished = true;
    }

    private boolean isCharcterIsDigit(char c) {
        return c >= '0' && c <= '9';
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
        callback().onPbapStateChanged(address, prevState, state, reason, count);
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onBtAclDisconnected(String address) {
        NfLog.i(this.TAG, "onBtAclDisconnected(): " + address);
        resetFlag();
        super.onBtAclDisconnected(address);
    }
}
