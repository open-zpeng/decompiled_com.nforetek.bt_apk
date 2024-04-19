package com.nforetek.bt.profile.bluetooth;

import com.nforetek.bt.res.NfDef;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class DelayPairThread extends Thread {
    private String TAG;
    private String mAddress;

    public DelayPairThread(String address) {
        this.TAG = "DelayPairThread";
        this.mAddress = NfDef.DEFAULT_ADDRESS;
        this.mAddress = address;
        this.TAG = "DelayPairThread_" + this.mAddress;
        NfLog.v(this.TAG, "init()");
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        try {
            Thread.sleep(1000L);
            NfLog.v(this.TAG, "finished.");
        } catch (InterruptedException e) {
            e.printStackTrace();
            NfPrimitive.pair(this.mAddress);
            NfLog.v(this.TAG, "finished after interrupt.");
        }
    }
}
