package com.nforetek.bt.profile.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import java.util.HashMap;
/* loaded from: classes.dex */
public class RecreateBondProcessor implements CheckRecreateBondThreadCallback {
    private static int IGNORE_TIME = NfJni.onJniInitFinished;
    private static int WAIT_TIME = 1500;
    private RecreateBondProcessorCallback mCallback;
    private String TAG = "RecreateBondProcessor";
    private HashMap<String, CheckRecreateBondThread> mRecreateBondDictionary = new HashMap<>();
    private HashMap<String, CheckProtectIgnoreTimeThread> mProtectIgnoreTimeDictionary = new HashMap<>();

    public RecreateBondProcessor(RecreateBondProcessorCallback callback) {
        this.mCallback = callback;
    }

    public void onDestroy() {
        this.mCallback = null;
    }

    public void onAclDisconnectCallbackFromSystem(String address) {
        CheckRecreateBondThread t = getCheckThreadFromDictionary(address);
        if (t != null) {
            t.interrupt(true);
        }
    }

    public void onRecreateBondCallbackFromSystem(String address, int is_connect) {
        NfLog.d(this.TAG, "onRecreateBondCallbackFromSystem() " + address + " is_connect: " + is_connect);
        if (isNeedIgnoreRecreateBond(address) && is_connect != 2) {
            NfLog.e(this.TAG, "Recreate bond occured in " + IGNORE_TIME + "ms, ignore recreate bond this time.");
            return;
        }
        addIgnoreThreadToDictionary(address);
        switch (is_connect) {
            case 0:
                removeCheckThreadFromDictionary(address);
                setCheckThreadToDictionary(address);
                return;
            case 1:
                NfLog.d(this.TAG, "KC Check onNeedBasicConnect for sure");
                NfPrimitive.unPair(address);
                CheckBondStateThread t = new CheckBondStateThread(address);
                t.start();
                return;
            case 2:
                removeCheckThreadFromDictionary(address);
                return;
            default:
                return;
        }
    }

    @Override // com.nforetek.bt.profile.bluetooth.CheckRecreateBondThreadCallback
    public void onRecreateBond(String address, boolean isNeedCallback) {
        NfLog.e(this.TAG, "Piggy Check onRecreateBond finish: " + address + " needCallback: " + isNeedCallback);
        if (isNeedCallback) {
            NfLog.e(this.TAG, "KC do unpair and pair");
            NfPrimitive.unPair(address);
            CheckBondStateThread t = new CheckBondStateThread(address);
            t.start();
        }
        removeCheckThreadFromDictionary(address);
    }

    @Override // com.nforetek.bt.profile.bluetooth.CheckRecreateBondThreadCallback
    public void onProtectIgnoreTimeFinished(String address) {
        NfLog.e(this.TAG, "Piggy Check onProtectIgnoreTimeFinished finish: " + address);
        removeIgnoreThreadFromDictionary(address);
    }

    private void removeCheckThreadFromDictionary(String address) {
        CheckRecreateBondThread t = this.mRecreateBondDictionary.get(address);
        if (t != null) {
            t.interrupt(false);
            this.mRecreateBondDictionary.remove(address);
            NfLog.e(this.TAG, "Piggy Check remove address: " + address + " from dictionary.");
        }
    }

    private CheckRecreateBondThread getCheckThreadFromDictionary(String address) {
        CheckRecreateBondThread t = this.mRecreateBondDictionary.get(address);
        return t;
    }

    private void setCheckThreadToDictionary(String address) {
        CheckRecreateBondThread t = this.mRecreateBondDictionary.get(address);
        if (t != null) {
            t.interrupt();
            this.mRecreateBondDictionary.remove(address);
        } else {
            NfLog.i(this.TAG, "New check recreate bond device. address: " + address);
        }
        CheckRecreateBondThread rbt = new CheckRecreateBondThread(address, this);
        rbt.start();
        this.mRecreateBondDictionary.put(address, rbt);
    }

    private void removeIgnoreThreadFromDictionary(String address) {
        CheckProtectIgnoreTimeThread t = this.mProtectIgnoreTimeDictionary.get(address);
        if (t != null) {
            t.interrupt();
            this.mProtectIgnoreTimeDictionary.remove(address);
            NfLog.e(this.TAG, "Piggy Check remove ignore thread address: " + address + " from dictionary.");
            return;
        }
        NfLog.e(this.TAG, "Piggy Check remove ignore thread address: " + address + " already is null.");
    }

    private boolean isNeedIgnoreRecreateBond(String address) {
        CheckProtectIgnoreTimeThread t = this.mProtectIgnoreTimeDictionary.get(address);
        return t != null;
    }

    private void addIgnoreThreadToDictionary(String address) {
        CheckProtectIgnoreTimeThread t = this.mProtectIgnoreTimeDictionary.get(address);
        if (t == null) {
            NfLog.i(this.TAG, "New ignore thread address: " + address);
            CheckProtectIgnoreTimeThread pitt = new CheckProtectIgnoreTimeThread(address, this);
            pitt.start();
            this.mProtectIgnoreTimeDictionary.put(address, pitt);
            return;
        }
        NfLog.e(this.TAG, "Piggy Check ignore thread address: " + address + " already in dictionary.");
    }

    /* loaded from: classes.dex */
    private class CheckBondStateThread extends Thread {
        private String mAddress;
        BluetoothDevice mDevice;

        public CheckBondStateThread(String address) {
            this.mAddress = address;
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = adapter.getRemoteDevice(address);
            this.mDevice = device;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    if (this.mDevice.getBondState() == 10) {
                        try {
                            Thread.sleep(500L);
                            NfLog.e(RecreateBondProcessor.this.TAG, "KC Start to pair!");
                            NfPrimitive.pair(this.mAddress);
                            CheckBondStatePairThread t1 = new CheckBondStatePairThread(this.mAddress);
                            t1.start();
                            return;
                        } catch (Exception e) {
                        }
                    } else {
                        Thread.sleep(1000L);
                    }
                } catch (InterruptedException e2) {
                    return;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    private class CheckBondStatePairThread extends Thread {
        private String mAddress;
        BluetoothDevice mDevice;

        public CheckBondStatePairThread(String address) {
            this.mAddress = address;
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = adapter.getRemoteDevice(address);
            this.mDevice = device;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            for (int i = 0; i < 30; i++) {
                try {
                    if (!NfPrimitive.isBtEnabled()) {
                        NfLog.e(RecreateBondProcessor.this.TAG, "BT is not enabled! Stop CheckBondStatePairThread.");
                        return;
                    }
                    if (this.mDevice.getBondState() == 12) {
                        try {
                            Thread.sleep(500L);
                            NfLog.e(RecreateBondProcessor.this.TAG, "KC Start to connect!");
                            RecreateBondProcessor.this.mCallback.onNeedBasicConnect(this.mAddress);
                            return;
                        } catch (Exception e) {
                        }
                    } else {
                        Thread.sleep(1000L);
                    }
                } catch (InterruptedException e2) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CheckRecreateBondThread extends Thread {
        private String mAddress;
        private CheckRecreateBondThreadCallback mCallback;
        private boolean mIsNeedCallback = false;

        public CheckRecreateBondThread(String address, CheckRecreateBondThreadCallback callback) {
            this.mCallback = callback;
            this.mAddress = address;
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
            com.nforetek.util.normal.NfLog.e(r4.this$0.TAG, "BT is not enabled! Stop CheckRecreateBondThread.");
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r4 = this;
                r0 = 0
            L1:
                int r1 = com.nforetek.bt.profile.bluetooth.RecreateBondProcessor.access$2()     // Catch: java.lang.InterruptedException -> L25
                int r1 = r1 / 100
                if (r0 < r1) goto L13
            L9:
                com.nforetek.bt.profile.bluetooth.CheckRecreateBondThreadCallback r1 = r4.mCallback
                java.lang.String r2 = r4.mAddress
                boolean r3 = r4.mIsNeedCallback
                r1.onRecreateBond(r2, r3)
                return
            L13:
                boolean r1 = com.nforetek.util.bt.NfPrimitive.isBtEnabled()     // Catch: java.lang.InterruptedException -> L25
                if (r1 != 0) goto L27
                com.nforetek.bt.profile.bluetooth.RecreateBondProcessor r1 = com.nforetek.bt.profile.bluetooth.RecreateBondProcessor.this     // Catch: java.lang.InterruptedException -> L25
                java.lang.String r1 = com.nforetek.bt.profile.bluetooth.RecreateBondProcessor.access$0(r1)     // Catch: java.lang.InterruptedException -> L25
                java.lang.String r2 = "BT is not enabled! Stop CheckRecreateBondThread."
                com.nforetek.util.normal.NfLog.e(r1, r2)     // Catch: java.lang.InterruptedException -> L25
                goto L9
            L25:
                r1 = move-exception
                goto L9
            L27:
                r2 = 100
                java.lang.Thread.sleep(r2)     // Catch: java.lang.InterruptedException -> L25
                int r0 = r0 + 1
                goto L1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.profile.bluetooth.RecreateBondProcessor.CheckRecreateBondThread.run():void");
        }

        public void interrupt(boolean isNeedCallback) {
            this.mIsNeedCallback = isNeedCallback;
            super.interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CheckProtectIgnoreTimeThread extends Thread {
        private String mAddress;
        private CheckRecreateBondThreadCallback mCallback;

        public CheckProtectIgnoreTimeThread(String address, CheckRecreateBondThreadCallback callback) {
            this.mCallback = callback;
            this.mAddress = address;
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x0017, code lost:
            com.nforetek.util.normal.NfLog.e(r4.this$0.TAG, "BT is not enabled! Stop CheckProtectIgnoreTimeThread.");
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r4 = this;
                r0 = 0
            L1:
                int r1 = com.nforetek.bt.profile.bluetooth.RecreateBondProcessor.access$3()     // Catch: java.lang.InterruptedException -> L23
                int r1 = r1 / 500
                if (r0 < r1) goto L11
            L9:
                com.nforetek.bt.profile.bluetooth.CheckRecreateBondThreadCallback r1 = r4.mCallback
                java.lang.String r2 = r4.mAddress
                r1.onProtectIgnoreTimeFinished(r2)
                return
            L11:
                boolean r1 = com.nforetek.util.bt.NfPrimitive.isBtEnabled()     // Catch: java.lang.InterruptedException -> L23
                if (r1 != 0) goto L25
                com.nforetek.bt.profile.bluetooth.RecreateBondProcessor r1 = com.nforetek.bt.profile.bluetooth.RecreateBondProcessor.this     // Catch: java.lang.InterruptedException -> L23
                java.lang.String r1 = com.nforetek.bt.profile.bluetooth.RecreateBondProcessor.access$0(r1)     // Catch: java.lang.InterruptedException -> L23
                java.lang.String r2 = "BT is not enabled! Stop CheckProtectIgnoreTimeThread."
                com.nforetek.util.normal.NfLog.e(r1, r2)     // Catch: java.lang.InterruptedException -> L23
                goto L9
            L23:
                r1 = move-exception
                goto L9
            L25:
                r2 = 500(0x1f4, double:2.47E-321)
                java.lang.Thread.sleep(r2)     // Catch: java.lang.InterruptedException -> L23
                int r0 = r0 + 1
                goto L1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.profile.bluetooth.RecreateBondProcessor.CheckProtectIgnoreTimeThread.run():void");
        }
    }
}
