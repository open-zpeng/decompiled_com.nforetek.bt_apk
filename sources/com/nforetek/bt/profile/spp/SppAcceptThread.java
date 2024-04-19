package com.nforetek.bt.profile.spp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import java.io.IOException;
import java.util.UUID;
/* loaded from: classes.dex */
public class SppAcceptThread extends Thread {
    private static final boolean DEBUG = true;
    private static final UUID SPP_UUID = UUID.fromString(NfConfig.getSppListenUuid());
    private static final String TAG = "nFore_SppAcceptThread";
    private BluetoothServerSocket mServerSocket;
    private BluetoothSocket mSocket;
    private SppConnectedThread mSppConnectedThread;
    private SppDoCallBack mSppDoCallBack;
    private SppService mSppService;
    public String sRemoteDeviceAddress;
    private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();
    private volatile boolean bStopThread = false;

    public SppAcceptThread(SppService sppService, SppDoCallBack sppDoCallBack) {
        this.mSppService = sppService;
        this.mSppDoCallBack = sppDoCallBack;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        SppService.isAcceptThreadAlive = true;
        Log.d(TAG, "AcceptThread.run() : START !! ");
        Log.d(TAG, "AcceptThread.run() : Before listenUsingRfcommOn()");
        for (int count = 0; !NfPrimitive.isBtEnabled() && count <= 25; count++) {
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            this.mServerSocket = this.mAdapter.listenUsingRfcommWithServiceRecord("nFore Spp", SPP_UUID);
            while (!this.bStopThread) {
                while (this.mServerSocket != null) {
                    try {
                        Log.e(TAG, "Before mServerSocket.accept()");
                        this.mSocket = this.mServerSocket.accept();
                        Log.e(TAG, "After mServerSocket.accept()");
                        if (this.mAdapter.isDiscovering()) {
                            this.mAdapter.cancelDiscovery();
                        }
                        if (this.mSocket != null) {
                            synchronized (this) {
                                if (this.mSppService.getConnectedDeviceCount() >= 7) {
                                    try {
                                        this.mSocket.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                    for (int i = SppService.SppConnectedThreadList.size() - 1; i > 6; i--) {
                                        SppService.SppConnectedThreadList.get(i).setStopThread(true);
                                    }
                                    this.mSppService.updateConnectedList();
                                    Log.e(TAG, "Michael Check reach MAX connected number : 7");
                                    setStopThread();
                                    SppService.isAcceptThreadAlive = false;
                                    Log.e(TAG, "in SppAcceptThread : getConnectedDeviceCount >= MAX_CONNECTED_NUMBER close SppAcceptThread");
                                } else if (!this.mSppService.checkConnected(this.mSocket.getRemoteDevice().getAddress())) {
                                    try {
                                        this.mSocket.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                    Log.e(TAG, "have same address connected mSocket.close()");
                                } else {
                                    this.mSppConnectedThread = new SppConnectedThread(this.mSppService, this.mSocket, this.mSppDoCallBack);
                                    this.mSppConnectedThread.start();
                                    this.mSppService.addSppConnectedThread(this.mSppConnectedThread);
                                    this.mSppService.updateConnectedList();
                                }
                            }
                        }
                        Log.e(TAG, "Can not get client socket from accepting");
                        try {
                            this.mSocket.close();
                        } catch (IOException e4) {
                            Log.d(TAG, "AcceptThread.run() : mSocket.close() exception");
                        }
                    } catch (IOException e5) {
                        Log.e(TAG, "mServerSocket.accept() failed");
                        e5.printStackTrace();
                        Log.e(TAG, "Piggy Check is mSocket is null ? " + (this.mSocket == null));
                        Log.e(TAG, "Piggy Check is mServerSocket is null ? " + (this.mServerSocket == null));
                        try {
                            this.mServerSocket.close();
                            this.mServerSocket = null;
                            return;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            return;
                        } catch (NullPointerException e22) {
                            e22.printStackTrace();
                            return;
                        }
                    }
                }
                Log.e(TAG, "mmServerSocket == null");
                return;
            }
            Log.d(TAG, "AcceptThread.run() : END !! ");
        } catch (Exception e6) {
            Log.e(TAG, "Create RFComm Listener failed, AcceptThread failed");
            if (this.mServerSocket != null) {
                try {
                    this.mServerSocket.close();
                } catch (IOException e12) {
                    e12.printStackTrace();
                }
            }
        }
    }

    public void cancel() {
        Log.d(TAG, "AcceptThread cancel");
        try {
            if (this.mServerSocket == null) {
                Log.e(TAG, "mServerSocket is null");
            } else {
                this.mServerSocket.close();
                this.mServerSocket = null;
                this.bStopThread = true;
            }
        } catch (IOException e) {
            Log.d(TAG, "close() of server socket failed");
        }
    }

    public void setStopThread() {
        Log.d(TAG, "+++ setStopThread() +++");
        this.bStopThread = true;
        Thread.currentThread().interrupt();
        cancel();
    }
}
