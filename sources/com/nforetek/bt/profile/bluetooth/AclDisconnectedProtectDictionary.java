package com.nforetek.bt.profile.bluetooth;

import com.nforetek.util.normal.NfLog;
import java.util.HashMap;
/* loaded from: classes.dex */
public class AclDisconnectedProtectDictionary implements ProtectTimeFinishedCallback {
    public static int PROTECT_TIME = 2000;
    private String TAG = "AclDisconnectedProtectDictionary";
    private HashMap<String, CheckProtectTimeThread> mProtectTimeDictionary = new HashMap<>();
    private HashMap<String, Long> mHfpDisconnectedTimeDictionary = new HashMap<>();

    public void onDestroy() {
    }

    @Override // com.nforetek.bt.profile.bluetooth.ProtectTimeFinishedCallback
    public void onProtectTimeFinished(String address) {
        removeProtectThreadFromDictionary(address);
    }

    private void removeProtectThreadFromDictionary(String address) {
        CheckProtectTimeThread t = this.mProtectTimeDictionary.get(address);
        if (t != null) {
            t.interrupt();
            this.mProtectTimeDictionary.remove(address);
            NfLog.e(this.TAG, "Piggy Check remove protect thread address: " + address + " from dictionary.");
        }
    }

    public boolean isStillInAclDisconnectProtectTime(String address) {
        CheckProtectTimeThread t = this.mProtectTimeDictionary.get(address);
        return t != null;
    }

    public void onAclDisconnected(String address) {
        CheckProtectTimeThread t = this.mProtectTimeDictionary.get(address);
        if (t == null && isNeedDelayProtectTime(address)) {
            NfLog.i(this.TAG, "New acl disconnect protect thread address: " + address);
            CheckProtectTimeThread ptt = new CheckProtectTimeThread(address, this);
            ptt.start();
            this.mProtectTimeDictionary.put(address, ptt);
            return;
        }
        NfLog.e(this.TAG, "Piggy Check protect thread address: " + address + " already in dictionary.");
    }

    public void onHfpDisconnected(String address) {
        Long l = this.mHfpDisconnectedTimeDictionary.get(address);
        if (l == null) {
            NfLog.e(this.TAG, "Add HFP diconnected time dictionay, current time is " + System.currentTimeMillis());
        } else {
            NfLog.e(this.TAG, "Update HFP diconnected time dictionay, current time is " + System.currentTimeMillis() + " last HFP disconnected time is " + l);
        }
        this.mHfpDisconnectedTimeDictionary.put(address, Long.valueOf(System.currentTimeMillis()));
    }

    private boolean isNeedDelayProtectTime(String address) {
        Long l = this.mHfpDisconnectedTimeDictionary.get(address);
        if (l == null || System.currentTimeMillis() - l.longValue() >= 2000) {
            return false;
        }
        NfLog.d(this.TAG, "System current time is " + System.currentTimeMillis() + " last HFP disconnected time is " + l + " need delay pretect time.");
        return true;
    }

    public void reset() {
        this.mProtectTimeDictionary.clear();
        this.mHfpDisconnectedTimeDictionary.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CheckProtectTimeThread extends Thread {
        private String mAddress;
        private AclDisconnectedProtectDictionary mCallback;

        public CheckProtectTimeThread(String address, AclDisconnectedProtectDictionary callback) {
            this.mCallback = callback;
            this.mAddress = address;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(AclDisconnectedProtectDictionary.PROTECT_TIME);
            } catch (InterruptedException e) {
            }
            this.mCallback.onProtectTimeFinished(this.mAddress);
        }
    }
}
