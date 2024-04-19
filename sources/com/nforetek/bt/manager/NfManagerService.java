package com.nforetek.bt.manager;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.nforetek.bt.res.bt.NfPreference;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import com.nforetek.util.normal.NfUtils;
/* loaded from: classes.dex */
public abstract class NfManagerService extends Service {
    protected String TAG = "";
    private final BroadcastReceiver mTimeChangedReceiver = new BroadcastReceiver() { // from class: com.nforetek.bt.manager.NfManagerService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.TIME_SET") || action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                NfManagerService.this.onSystemTimeChanged();
            }
        }
    };

    protected abstract String getLogTag();

    public abstract void onNfJniServiceConnected(boolean z);

    @Override // android.app.Service
    public void onDestroy() {
        NfLog.i(this.TAG, "onDestroy()");
        NfPreference.setContext(null);
        NfPrimitive.setContext(null);
        unregisterReceiver(this.mTimeChangedReceiver);
    }

    @Override // android.app.Service, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        NfLog.i(this.TAG, "onTrimMemory() level: " + level);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        NfPreference.setContext(this);
        NfPrimitive.setContext(this);
        this.TAG = String.valueOf(getLogTag()) + "_" + NfUtils.printAppVersion(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.TIMEZONE_CHANGED");
        filter.addAction("android.intent.action.TIME_SET");
        registerReceiver(this.mTimeChangedReceiver, filter);
    }

    protected void onSystemTimeChanged() {
    }
}
