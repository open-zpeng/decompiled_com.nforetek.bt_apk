package com.nforetek.bt.profile.pbap.java;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.nforetek.bt.profile.pbap.java.PbapClientStateMachine;
import com.nforetek.bt.profile.pbap.java.vcard.VCardEntry;
import com.nforetek.util.normal.NfLog;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public class PbapClientService implements PbapClientStateMachine.PbapListener {
    public static final String BLUETOOTH_ADMIN_PERM = "android.permission.BLUETOOTH_ADMIN";
    public static final String BLUETOOTH_PERM = "android.permission.BLUETOOTH";
    private static final boolean DBG = false;
    private static final int MAXIMUM_DEVICES = 10;
    private static final String TAG = "PbapClientService";
    private _NfPbapJava mCallback;
    private Context mContext;
    private BluetoothDevice mDevice;
    private PbapClientStateMachine mPbapClientStateMachine;
    private Map<BluetoothDevice, PbapClientStateMachine> mPbapClientStateMachineMap = new ConcurrentHashMap();
    private String mAddress = "";
    private int mDownloadPath = 0;
    private int mFilter = 0;
    private int mStartPos = 0;
    private int mOffset = 0;
    private int mL2capPsm = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PbapClientService(Context context, _NfPbapJava callback) {
        this.mContext = context;
        this.mCallback = callback;
    }

    @Override // com.nforetek.bt.profile.pbap.java.PbapClientStateMachine.PbapListener
    public void onPhoneBookPullDoneCheckSize(int size) {
        this.mCallback.onPhoneBookPullDoneCheckSize(size);
    }

    @Override // com.nforetek.bt.profile.pbap.java.PbapClientStateMachine.PbapListener
    public void onPhoneBookPullDone(VCardEntry arrayList) {
        this.mCallback.onPhoneBookPullDone(this.mAddress, arrayList, this.mDownloadPath);
    }

    @Override // com.nforetek.bt.profile.pbap.java.PbapClientStateMachine.PbapListener
    public void onConnectionStateChanged(BluetoothDevice device, int prevState, int state) {
        this.mCallback.onConnectionStateChanged(device.getAddress(), prevState, state, -1);
    }

    @Override // com.nforetek.bt.profile.pbap.java.PbapClientStateMachine.PbapListener
    public void onResponePhoneBookSize(int size) {
        this.mCallback.onPhoneBookSizeDone(size);
    }

    public void pbapDownloadInterrupt() {
        if (this.mPbapClientStateMachine != null) {
            NfLog.d(TAG, "pbapDownloadInterrupt()");
            this.mPbapClientStateMachine.pbapDownloadInterrupt();
            return;
        }
        NfLog.d(TAG, "mPbapClientStateMachine == null");
    }

    public boolean isDownloading() {
        if (this.mPbapClientStateMachine != null) {
            return this.mPbapClientStateMachine.isDownloading();
        }
        return false;
    }

    public int getMostRecentState() {
        if (this.mPbapClientStateMachine == null) {
            return 0;
        }
        return this.mPbapClientStateMachine.getMostRecentState();
    }

    public boolean changeStateMachineToDisconnecting() {
        if (this.mPbapClientStateMachine == null) {
            NfLog.d(TAG, "mPbapClientStateMachine is null");
            return false;
        }
        this.mPbapClientStateMachine.changeStateMachineToDisconnecting();
        this.mPbapClientStateMachine = null;
        return true;
    }

    @Override // com.nforetek.bt.profile.pbap.java.PbapClientStateMachine.PbapListener
    public void resetStateMachine() {
        NfLog.d(TAG, "resetMachine");
        disconnect(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean disconnect(BluetoothDevice device) {
        if (this.mPbapClientStateMachine != null) {
            this.mPbapClientStateMachine.disconnect(this.mDevice);
            this.mPbapClientStateMachine = null;
            return true;
        }
        return false;
    }

    public void connect(BluetoothDevice device, int downloadPath, int property, int startPos, int offset, int l2capPsm) {
        this.mAddress = device.getAddress();
        this.mDownloadPath = downloadPath;
        if (downloadPath < 5) {
            this.mFilter = property;
        } else {
            this.mFilter = 268435591;
        }
        this.mStartPos = startPos;
        this.mOffset = offset;
        this.mL2capPsm = l2capPsm;
        NfLog.d(TAG, "mL2capPsm: " + this.mL2capPsm);
        connect(device);
    }

    public boolean connect(BluetoothDevice device) {
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        this.mDevice = device;
        if (this.mPbapClientStateMachine != null) {
            NfLog.d(TAG, "mPbapClientStateMachine isn't null");
            this.mPbapClientStateMachine = null;
        }
        this.mPbapClientStateMachine = new PbapClientStateMachine(this, device, this.mContext);
        this.mPbapClientStateMachine.setDownloadPath(this.mDownloadPath);
        this.mPbapClientStateMachine.setFilter(this.mFilter);
        this.mPbapClientStateMachine.setTargetDevice(device);
        this.mPbapClientStateMachine.setOffset(this.mOffset);
        this.mPbapClientStateMachine.setStartPos(this.mStartPos);
        this.mPbapClientStateMachine.setL2capPsm(this.mL2capPsm);
        this.mPbapClientStateMachine.start();
        return true;
    }

    public boolean setPriority(BluetoothDevice device, int priority) {
        return true;
    }

    public int getPriority(BluetoothDevice device) {
        return -1;
    }

    @Override // com.nforetek.bt.profile.pbap.java.PbapClientStateMachine.PbapListener
    public void cleanupDevice(BluetoothDevice device) {
    }
}
