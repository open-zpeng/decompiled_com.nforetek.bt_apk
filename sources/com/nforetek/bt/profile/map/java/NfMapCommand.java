package com.nforetek.bt.profile.map.java;

import android.bluetooth.BluetoothAdapter;
import com.nforetek.bt.profile.map.java.BluetoothMasClient;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.normal.NfLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes.dex */
public class NfMapCommand {
    public static final int COUNT_DOWNLOAD_ALL = 65535;
    public static final int TYPE_DELETE = 1;
    public static final int TYPE_DOWNLOAD_ALL = 3;
    public static final int TYPE_DOWNLOAD_SINGLE = 2;
    public static final int TYPE_READ_STATUS = 4;
    public static final int TYPE_SEND = 0;
    protected String TAG = getClass().getSimpleName();
    private String mAddress;
    private int mType;

    /* JADX INFO: Access modifiers changed from: protected */
    public String getAddress() {
        return this.mAddress;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getType() {
        return this.mType;
    }

    public NfMapCommand(String address, int type) {
        this.mType = -1;
        this.mAddress = NfDef.DEFAULT_ADDRESS;
        if (BluetoothAdapter.checkBluetoothAddress(address)) {
            this.mAddress = address;
        }
        if (type >= 0 || type <= 4) {
            this.mType = type;
        }
    }

    /* loaded from: classes.dex */
    public static class Send extends NfMapCommand {
        private String mMsg;
        private String mTarget;

        public Send(String address, int type, String msg, String target) {
            super(address, type);
            this.mTarget = target;
            this.mMsg = msg;
        }

        public String getTarget() {
            return this.mTarget;
        }

        public String getMessage() {
            return this.mMsg;
        }

        public String toString() {
            return "NfSend{mAddr: " + getAddress() + ", mTarget: " + this.mTarget + ", mMsg: " + this.mMsg + "}";
        }
    }

    /* loaded from: classes.dex */
    public static class Delete extends NfMapCommand {
        private int mFolder;
        private String mHandle;

        public Delete(String address, int type, int folder, String handle) {
            super(address, type);
            this.mHandle = handle;
            this.mFolder = folder;
        }

        public int getFolder() {
            return this.mFolder;
        }

        public String getHandle() {
            return this.mHandle;
        }

        public String toString() {
            return "NfDelete{mAddr: " + getAddress() + ", mFolder: " + this.mFolder + ", mHandle: " + this.mHandle + "}";
        }
    }

    /* loaded from: classes.dex */
    public static class DownloadSingle extends NfMapCommand {
        private int mDownloadType;
        private int mFolder;
        private String mHandle;
        private int mStorage;

        public DownloadSingle(String address, int type, String handle, int downloadType, int folder, int storage) {
            super(address, type);
            this.mHandle = handle;
            this.mDownloadType = downloadType;
            this.mFolder = folder;
            this.mStorage = storage;
        }

        public int getFolder() {
            return this.mFolder;
        }

        public int getDownloadType() {
            return this.mDownloadType;
        }

        public int getStorage() {
            return this.mStorage;
        }

        public String getHandle() {
            return this.mHandle;
        }

        public String toString() {
            return "NfDownloadOne{mAddr: " + getAddress() + ", mFolder: " + this.mFolder + ", mDownloadType: " + this.mDownloadType + ", mHandle: " + this.mHandle + ", mDownloadType: " + this.mDownloadType + ", mStorage: " + this.mStorage + "}";
        }
    }

    /* loaded from: classes.dex */
    public static class DownloadAll extends NfMapCommand {
        public static final byte MESSAGE_TYPE_ALL = 0;
        public static final byte MESSAGE_TYPE_EMAIL = 4;
        public static final byte MESSAGE_TYPE_MMS = 8;
        public static final byte MESSAGE_TYPE_SMS_CDMA = 2;
        public static final byte MESSAGE_TYPE_SMS_GSM = 1;
        private BluetoothMasClient.MessagesFilter filter;
        boolean filtered;
        private int mCount;
        private int mDownloadType;
        private int mFolder;
        private boolean mIsDetail;
        private int mStartPos;
        private int mStorage;

        public DownloadAll(String address, int type, int downloadType, int folder, int storage, boolean isDetail) {
            super(address, type);
            this.filtered = false;
            this.filter = null;
            this.mDownloadType = downloadType;
            this.mFolder = folder;
            this.mStorage = storage;
            this.mIsDetail = isDetail;
            this.mCount = NfMapCommand.COUNT_DOWNLOAD_ALL;
            this.mStartPos = 0;
        }

        public DownloadAll(String address, int type, int downloadType, int folder, int storage, boolean isDetail, int count, int startPos) {
            super(address, type);
            this.filtered = false;
            this.filter = null;
            this.mDownloadType = downloadType;
            this.mFolder = folder;
            this.mStorage = storage;
            this.mIsDetail = isDetail;
            if (count == -1) {
                this.mCount = NfMapCommand.COUNT_DOWNLOAD_ALL;
            } else {
                this.mCount = count;
            }
            this.mStartPos = startPos;
            if (this.mStartPos < 0) {
                this.mStartPos = 0;
            }
        }

        public boolean setFilter(String periodBegin, String periodEnd, String sender, String recipient, int readStatus, int type) {
            if (readStatus > 2 || readStatus < 0) {
                NfLog.e(this.TAG, "readStatus invalid!! type: " + type);
                return false;
            } else if (type > 15 || type < 0) {
                NfLog.e(this.TAG, "type invalid!! type: " + type);
                return false;
            } else {
                if (type == 15) {
                    type = 0;
                }
                byte typeFilter = 0;
                if (type != 0) {
                    if ((type & 1) == 0) {
                        typeFilter = (byte) 1;
                    }
                    if ((type & 2) == 0) {
                        typeFilter = (byte) (typeFilter + 2);
                    }
                    if ((type & 4) == 0) {
                        typeFilter = (byte) (typeFilter + 8);
                    }
                    if ((type & 8) == 0) {
                        typeFilter = (byte) (typeFilter + 4);
                    }
                }
                if (periodBegin == null && periodEnd == null && sender == null && recipient == null && readStatus == 0 && typeFilter == 0) {
                    NfLog.d("DownloadAll", "filter nothing!!");
                    return true;
                }
                Date dateBegin = null;
                SimpleDateFormat parser = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
                if (periodBegin != null) {
                    try {
                        dateBegin = parser.parse(periodBegin);
                    } catch (ParseException e) {
                        NfLog.e(this.TAG, "setFilter Failed to parse periodBegin/periodEnd " + periodBegin + "/" + periodEnd);
                        return false;
                    }
                }
                Date dateEnd = periodEnd != null ? parser.parse(periodEnd) : null;
                this.filter = new BluetoothMasClient.MessagesFilter();
                this.filter.setOriginator(sender);
                this.filter.setRecipient(recipient);
                this.filter.setMessageType(typeFilter);
                this.filter.setReadStatus((byte) readStatus);
                this.filter.setPeriod(dateBegin, dateEnd);
                this.filtered = true;
                return true;
            }
        }

        public int getFolder() {
            return this.mFolder;
        }

        public int getDownloadType() {
            return this.mDownloadType;
        }

        public int getStorage() {
            return this.mStorage;
        }

        public int getCount() {
            return this.mCount;
        }

        public int getStartPos() {
            return this.mStartPos;
        }

        public boolean isDetail() {
            return this.mIsDetail;
        }

        public boolean isFiltered() {
            return this.filtered;
        }

        public BluetoothMasClient.MessagesFilter getFilter() {
            return this.filter;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder("NfDownloadAll{mAddr: ");
            builder.append(getAddress());
            builder.append(", mFolder: ");
            builder.append(this.mFolder);
            builder.append(", mDownloadType: ");
            builder.append(this.mDownloadType);
            builder.append(", mStorage: ");
            builder.append(this.mStorage);
            builder.append(", mIsDetail: ");
            builder.append(this.mIsDetail);
            builder.append(", startPos: ");
            builder.append(this.mStartPos);
            builder.append(", mCount: ");
            builder.append(this.mCount);
            if (!this.filtered) {
                return builder.append("}").toString();
            }
            builder.append(this.filter.toString());
            return builder.append("}").toString();
        }
    }

    /* loaded from: classes.dex */
    public static class ReadStatus extends NfMapCommand {
        private int mFolder;
        private String mHandle;
        private boolean mIsRead;

        public ReadStatus(String address, int type, int folder, String handle, boolean isRead) {
            super(address, type);
            this.mFolder = folder;
            this.mHandle = handle;
            this.mIsRead = isRead;
        }

        public int getFolder() {
            return this.mFolder;
        }

        public String getHandle() {
            return this.mHandle;
        }

        public boolean isRead() {
            return this.mIsRead;
        }

        public String toString() {
            return "NfReadStatus{mAddr: " + getAddress() + ", mFolder: " + this.mFolder + ", mHandle: " + this.mHandle + ", mIsRead: " + this.mIsRead + "}";
        }
    }

    public static boolean isCommandEquals(NfMapCommand a, NfMapCommand b) {
        if (a == null || b == null) {
            return false;
        }
        return a.toString().equals(b.toString());
    }
}
