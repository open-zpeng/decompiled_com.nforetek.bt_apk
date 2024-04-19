package com.nforetek.bt.profile.spp;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.normal.NfLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes.dex */
public class SppConnectedThread extends Thread {
    private static final boolean DEBUG = true;
    private static final String TAG = "nFore_SppConnectedThread";
    private static final boolean TEST = false;
    private boolean bStopThread;
    HandlerThread ht;
    public boolean isPendingSCOEvent;
    Handler mHandler;
    private InputStream mInStream;
    private OutputStream mOutStream;
    private BluetoothSocket mSocket;
    private SppDoCallBack mSppDoCallBack;
    private SppService mSppService;
    public String remoteDeviceName;
    public String sRemoteDeviceAddress;
    private int CALLBACK_DELAY_TIME = NfDef.BT_STATE_OFF;
    boolean mIsBlocked = false;
    CheckBlockThread mCheckBlockThread = null;
    private int mSendMessageDelay = 0;

    public String getsRemoteDeviceAddress() {
        return this.sRemoteDeviceAddress;
    }

    public String getRemoteDeviceName() {
        return this.remoteDeviceName;
    }

    public SppConnectedThread(SppService sppService, BluetoothSocket socket, SppDoCallBack sppDoCallBack) {
        this.sRemoteDeviceAddress = null;
        this.remoteDeviceName = null;
        Log.v(TAG, "in SppConnectedThread");
        this.mSppService = sppService;
        this.mSocket = socket;
        this.mSppDoCallBack = sppDoCallBack;
        this.isPendingSCOEvent = false;
        this.bStopThread = false;
        BluetoothDevice dev = this.mSocket.getRemoteDevice();
        if (dev == null) {
            Log.d(TAG, "Fail to get remote device");
        } else {
            this.sRemoteDeviceAddress = dev.getAddress();
            this.remoteDeviceName = dev.getName();
        }
        try {
            this.mInStream = this.mSocket.getInputStream();
            this.mOutStream = this.mSocket.getOutputStream();
            if (this.mInStream == null) {
                Log.d(TAG, "The InputStream is Null");
            } else if (this.mOutStream == null) {
                Log.d(TAG, "The mmOutStream is Null");
            } else {
                this.bStopThread = false;
                this.ht = new HandlerThread("SendDelayThread");
                this.ht.start();
                this.mHandler = new Handler(this.ht.getLooper()) { // from class: com.nforetek.bt.profile.spp.SppConnectedThread.1
                    @Override // android.os.Handler
                    public void handleMessage(Message msg) {
                        Log.d(SppConnectedThread.TAG, "handleMessage() " + hashCode());
                        if (SppConnectedThread.this.mSendMessageDelay > 0) {
                            try {
                                Thread.sleep(SppConnectedThread.this.mSendMessageDelay);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                        Bundle bundle = msg.getData();
                        byte[] buffer = bundle.getByteArray("msg");
                        if (buffer == null) {
                            Log.d(SppConnectedThread.TAG, "Buffer in write() is Null");
                        }
                        try {
                            SppConnectedThread.this.mIsBlocked = true;
                            NfLog.d(SppConnectedThread.TAG, "Send message blocked.");
                            if (SppConnectedThread.this.mCheckBlockThread != null) {
                                SppConnectedThread.this.mCheckBlockThread.interrupt();
                                SppConnectedThread.this.mCheckBlockThread = null;
                            }
                            SppConnectedThread.this.mCheckBlockThread = new CheckBlockThread(SppConnectedThread.this, null);
                            SppConnectedThread.this.mCheckBlockThread.start();
                            if (SppConnectedThread.this.mOutStream != null) {
                                SppConnectedThread.this.mOutStream.write(buffer);
                            } else {
                                Log.e(SppConnectedThread.TAG, "mOutStream is null !?");
                            }
                            SppConnectedThread.this.mIsBlocked = false;
                            NfLog.d(SppConnectedThread.TAG, "Send message unblock.");
                            if (SppConnectedThread.this.mCheckBlockThread != null) {
                                SppConnectedThread.this.mCheckBlockThread.interrupt();
                                SppConnectedThread.this.mCheckBlockThread = null;
                            }
                            SppConnectedThread.this.mSppDoCallBack.onSppSendData(SppConnectedThread.this.sRemoteDeviceAddress, buffer.length);
                        } catch (IOException e) {
                            Log.e(SppConnectedThread.TAG, "Exception during write", e);
                        }
                        Log.d(SppConnectedThread.TAG, "handleMessage() " + hashCode() + " finished.");
                    }
                };
                Log.d(TAG, "+++ End of ConnectedThread constructor +++");
            }
        } catch (IOException e) {
            Log.d(TAG, "Get in/out stream failed");
            this.mSppDoCallBack.onSppErrorResponse(this.sRemoteDeviceAddress, 101);
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Log.d(TAG, "+++ Begin of run() in ConnectedThread +++");
        while (!this.bStopThread) {
            byte[] buffer = new byte[1024];
            this.mSppService.setState(this.sRemoteDeviceAddress, this.remoteDeviceName, 140);
            this.mSppService.callbackSppAppIapAuthenticationRequestIfNeed(this.sRemoteDeviceAddress);
            Log.d(TAG, "Waitting for respond");
            while (true) {
                try {
                    int bytes = this.mInStream.read(buffer);
                    if (bytes > 0) {
                        byte[] tmpBuffer = new byte[bytes];
                        System.arraycopy(buffer, 0, tmpBuffer, 0, bytes);
                        this.mSppDoCallBack.onSppDataReceived(this.sRemoteDeviceAddress, tmpBuffer);
                    } else {
                        Log.e(TAG, "Received bytes = " + bytes);
                    }
                } catch (Exception e) {
                    if (e instanceof IOException) {
                        Log.e(TAG, "There is an error in reading socket");
                        e.printStackTrace();
                    } else {
                        Log.e(TAG, "Unknown exception !!!");
                    }
                    if (this.mHandler != null) {
                        this.mHandler.removeMessages(0);
                    }
                    this.bStopThread = true;
                    releaseResource();
                    this.mSppService.closeConnectedThread(this.sRemoteDeviceAddress);
                    this.mSppService.updateConnectedList();
                    this.mSppService.setState(this.sRemoteDeviceAddress, this.remoteDeviceName, 110);
                    if (this.mSppService.getConnectedDeviceCount() < 7 && !SppService.isAcceptThreadAlive) {
                        SppAcceptThread mSppAcceptThread = new SppAcceptThread(this.mSppService, this.mSppDoCallBack);
                        mSppAcceptThread.start();
                        Log.e(TAG, "Michael new AcceptThread !!!");
                    }
                }
            }
        }
        if (this.ht != null) {
            this.ht.getLooper().quit();
            this.ht = null;
        } else {
            Log.e(TAG, "HandlerThread is null !?");
        }
        Log.d(TAG, "+++ End of ConnectedThread in the run() +++");
    }

    public synchronized void setStopThread(boolean value) {
        Log.d(TAG, " +++ Begin of setStopThread +++ ");
        this.bStopThread = value;
        Thread.currentThread().interrupt();
        if (this.mInStream != null) {
            try {
                this.mInStream.close();
                Log.e(TAG, "close mInStream of  setStopThread!!!");
            } catch (Exception e) {
                Log.e(TAG, "Exception when doing mInStream.close() !!!");
            }
        }
        if (this.mHandler != null) {
            this.mHandler.removeMessages(0);
        }
        Log.d(TAG, " +++ End of setStopThread +++ ");
    }

    private synchronized void releaseResource() {
        Log.d(TAG, " +++ Begin of releaseResource() +++ ");
        if (this.mInStream != null) {
            try {
                this.mInStream.close();
                Log.e(TAG, "close mInStream of releaseResource!!!");
            } catch (Exception e) {
                Log.e(TAG, "Exception when doing mInStream.close() !!!");
            }
            this.mInStream = null;
        }
        if (this.mOutStream != null) {
            try {
                this.mOutStream.close();
                Log.e(TAG, "close mOutStream of releaseResource!!!");
            } catch (Exception e2) {
                Log.e(TAG, "Exception when doing mOutStream.close() !!!");
            }
            this.mOutStream = null;
        }
        if (this.mSocket != null) {
            try {
                this.mSocket.close();
                Log.e(TAG, "close mSocket of releaseResource!!!");
            } catch (Exception e3) {
                Log.e(TAG, "Exception when doing mSocket.close() !!!");
            }
            this.mSocket = null;
        }
        if (this.mHandler != null) {
            this.mHandler.removeMessages(0);
        }
    }

    public void sendMessage(byte[] message) {
        if (message.length != 0) {
            writeData(message);
            Log.v(TAG, "sendMessage() is done.");
        }
    }

    public void writeData(byte[] buffer) {
        Log.v(TAG, "In the Write_buffer");
        Message msg = this.mHandler.obtainMessage();
        msg.what = 0;
        Bundle bundle = new Bundle();
        bundle.putByteArray("msg", buffer);
        msg.setData(bundle);
        this.mHandler.sendMessage(msg);
        Log.e(TAG, "WriteDate finished.");
    }

    /* loaded from: classes.dex */
    private class CheckBlockThread extends Thread {
        private CheckBlockThread() {
        }

        /* synthetic */ CheckBlockThread(SppConnectedThread sppConnectedThread, CheckBlockThread checkBlockThread) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            try {
                Log.e("CheckBlockThread", "start sleep.");
                Thread.sleep(4000L);
                if (SppConnectedThread.this.mIsBlocked) {
                    Log.e("CheckBlockThread", "OutStream is blocked. close it !");
                    if (SppConnectedThread.this.mOutStream != null) {
                        try {
                            SppConnectedThread.this.mOutStream.close();
                            Log.e("CheckBlockThread", "close mOutStream of releaseResource!!!");
                        } catch (Exception e) {
                            Log.e("CheckBlockThread", "Exception when doing mOutStream.close() !!!");
                        }
                        SppConnectedThread.this.mOutStream = null;
                    }
                }
            } catch (InterruptedException e2) {
                Log.e("CheckBlockThread", "Interrupted.");
            }
            Log.e("CheckBlockThread", "finished.");
        }
    }
}
