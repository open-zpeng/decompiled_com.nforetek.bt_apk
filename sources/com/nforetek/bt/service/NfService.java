package com.nforetek.bt.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCommandManager;
import com.nforetek.bt.manager.NfServiceManager;
import com.nforetek.util.normal.NfLog;
import com.nforetek.util.normal.NfUtils;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
/* loaded from: classes.dex */
public abstract class NfService extends Service {
    protected String mName;
    protected INfCommandManager mNfCommand;
    protected String TAG = "";
    private Queue<IInterface> pendingCallbacks = null;
    private boolean isNfServiceBinded = false;
    private Object syncObject = new Object();
    private PendingRegisterThread mPendingCbThread = null;
    private ServiceConnection mConnection = new ServiceConnection() { // from class: com.nforetek.bt.service.NfService.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName className, IBinder service) {
            NfLog.v(NfService.this.TAG, "ready onServiceConnected");
            NfService.this.mNfCommand = INfCommandManager.Stub.asInterface(service);
            if (NfService.this.mNfCommand == null) {
                NfLog.e(NfService.this.TAG, "mNfCommand is null!!");
                return;
            }
            NfService.this.onNfServiceBinded();
            NfLog.v(NfService.this.TAG, "end onServiceConnected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName className) {
            NfLog.v(NfService.this.TAG, "ready onServiceDisconnected");
            NfService.this.onNfServiceUnBinded();
            NfService.this.onNfServiceDisconnected(className);
            NfService.this.mNfCommand = null;
            NfLog.v(NfService.this.TAG, "end onServiceDisconnected");
        }
    };

    abstract IBinder getBinder();

    protected abstract String getLogTag();

    abstract boolean isEnableService();

    protected abstract void onNfServiceConnected(IInterface iInterface) throws RemoteException;

    /* loaded from: classes.dex */
    private class PendingRegisterThread extends Thread {
        private PendingRegisterThread() {
        }

        /* synthetic */ PendingRegisterThread(NfService nfService, PendingRegisterThread pendingRegisterThread) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (NfService.this.isPendingCallback()) {
                try {
                    Thread.sleep(100L);
                    if (NfService.this.isNfServiceConnected()) {
                        try {
                            IInterface cb = NfService.this.getPendingCallback();
                            if (cb != null) {
                                NfService.this.onNfServiceConnected(cb);
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                    return;
                }
            }
            NfService.this.mPendingCbThread = null;
            super.run();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (isEnableService() && isNfServiceConnected()) {
            try {
                unbindService(this.mConnection);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        resetPendingCallback();
        if (this.mPendingCbThread != null) {
            this.mPendingCbThread.interrupt();
            this.mPendingCbThread = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onCreate() {
        this.TAG = getLogTag();
        super.onCreate();
        NfLog.v(this.TAG, "onCreate()");
        if (isEnableService()) {
            startService(new Intent(this, NfServiceManager.class));
            getApplicationContext().bindService(NfUtils.createExplicitFromImplicitIntent(getApplicationContext(), new Intent("com.nforetek.bt.manager.NfServiceManager")), this.mConnection, 1);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        NfLog.v(this.TAG, "onBind() intent:" + intent + "intent.type: " + intent.getData());
        if (isEnableService()) {
            return getBinder();
        }
        return null;
    }

    protected void onNfServiceConnected(ComponentName className, IBinder service) {
    }

    protected void onNfServiceDisconnected(ComponentName className) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onNfServiceBinded() {
        this.isNfServiceBinded = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onNfServiceUnBinded() {
        this.isNfServiceBinded = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isNfServiceConnected() {
        return this.isNfServiceBinded;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized boolean addPendingCallback(IInterface cb) {
        boolean z;
        synchronized (this.syncObject) {
            if (this.pendingCallbacks == null) {
                this.pendingCallbacks = new LinkedList();
            }
            if (!this.pendingCallbacks.contains(cb) && cb != null) {
                this.pendingCallbacks.add(cb);
                NfLog.d(this.TAG, "setPendingCallback: " + cb);
                if (this.mPendingCbThread == null) {
                    this.mPendingCbThread = new PendingRegisterThread(this, null);
                    this.mPendingCbThread.start();
                }
                z = true;
            } else {
                NfLog.e(this.TAG, cb + " is already add in pending callbacks !!");
                z = false;
            }
        }
        return z;
    }

    protected synchronized IInterface getPendingCallback() {
        IInterface cb;
        cb = null;
        synchronized (this.syncObject) {
            if (this.pendingCallbacks != null && this.pendingCallbacks.size() > 0) {
                try {
                    cb = this.pendingCallbacks.poll();
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
            }
        }
        NfLog.d(this.TAG, "getPendingCallback: " + cb);
        return cb;
    }

    protected boolean isPendingCallback() {
        return this.pendingCallbacks != null && this.pendingCallbacks.size() > 0;
    }

    private void resetPendingCallback() {
        synchronized (this.syncObject) {
            if (this.pendingCallbacks != null) {
                this.pendingCallbacks.clear();
                this.pendingCallbacks = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public INfCommandManager command() {
        return this.mNfCommand;
    }
}
