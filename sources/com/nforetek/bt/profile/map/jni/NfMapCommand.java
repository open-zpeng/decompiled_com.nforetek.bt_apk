package com.nforetek.bt.profile.map.jni;

import android.bluetooth.BluetoothAdapter;
import com.nforetek.bt.res.NfDef;
/* loaded from: classes.dex */
public class NfMapCommand {
    public static final int TYPE_DELETE = 1;
    public static final int TYPE_DOWNLOAD_ALL = 3;
    public static final int TYPE_DOWNLOAD_SINGLE = 2;
    public static final int TYPE_READ_STATUS = 4;
    public static final int TYPE_SEND = 0;
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
            return "NfMapCommand.Send{mAddress: " + getAddress() + ", mTarget: " + this.mTarget + ", mMsg: " + this.mMsg + "}";
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
            return "NfMapCommand.Delete{mAddress: " + getAddress() + ", mFolder: " + this.mFolder + ", mHandle: " + this.mHandle + "}";
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
            return "NfMapCommand.DownloadSingle{mAddress: " + getAddress() + ", mFolder: " + this.mFolder + ", mDownloadType: " + this.mDownloadType + ", mHandle: " + this.mHandle + ", mDownloadType: " + this.mDownloadType + ", mStorage: " + this.mStorage + "}";
        }
    }

    /* loaded from: classes.dex */
    public static class DownloadAll extends NfMapCommand {
        private int mDownloadType;
        private int mFolder;
        private boolean mIsDetail;
        private int mStorage;

        public DownloadAll(String address, int type, int downloadType, int folder, int storage, boolean isDetail) {
            super(address, type);
            this.mDownloadType = downloadType;
            this.mFolder = folder;
            this.mStorage = storage;
            this.mIsDetail = isDetail;
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

        public boolean isDetail() {
            return this.mIsDetail;
        }

        public String toString() {
            return "NfMapCommand.DownloadAll{mAddress: " + getAddress() + ", mFolder: " + this.mFolder + ", mDownloadType: " + this.mDownloadType + ", mDownloadType: " + this.mDownloadType + ", mStorage: " + this.mStorage + ", mIsDetail: " + this.mIsDetail + "}";
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
            return "NfMapCommand.ReadStatus{mAddress: " + getAddress() + ", mFolder: " + this.mFolder + ", mHandle: " + this.mHandle + ", mIsRead: " + this.mIsRead + "}";
        }
    }

    public static boolean isCommandEquals(NfMapCommand a, NfMapCommand b) {
        if (a == null || b == null) {
            return false;
        }
        return a.toString().equals(b.toString());
    }
}
