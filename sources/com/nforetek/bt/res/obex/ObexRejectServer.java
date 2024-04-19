package com.nforetek.bt.res.obex;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
/* loaded from: classes.dex */
public class ObexRejectServer extends ServerRequestHandler implements Handler.Callback {
    private static final int MSG_ID_TIMEOUT = 1;
    private static final String TAG = "ObexRejectServer";
    private static final int TIMEOUT_VALUE = 5000;
    private static final boolean V = true;
    private final HandlerThread mHandlerThread = new HandlerThread("TestTimeoutHandler", 10);
    private final Handler mMessageHandler;
    private final int mResult;
    private final BluetoothSocket mSocket;

    public ObexRejectServer(int result, BluetoothSocket socket) {
        this.mResult = result;
        this.mSocket = socket;
        this.mHandlerThread.start();
        Looper timeoutLooper = this.mHandlerThread.getLooper();
        this.mMessageHandler = new Handler(timeoutLooper, this);
        this.mMessageHandler.sendEmptyMessageDelayed(1, 5000L);
    }

    @Override // com.nforetek.bt.res.obex.ServerRequestHandler
    public int onConnect(HeaderSet request, HeaderSet reply) {
        Log.i(TAG, "onConnect() returning error");
        return this.mResult;
    }

    public void shutdown() {
        this.mMessageHandler.removeCallbacksAndMessages(null);
        this.mHandlerThread.quit();
        try {
            this.mSocket.close();
        } catch (IOException e) {
            Log.w(TAG, "Unable to close socket - ignoring", e);
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        Log.i(TAG, "Handling message ID: " + msg.what);
        switch (msg.what) {
            case 1:
                shutdown();
                return true;
            default:
                return false;
        }
    }
}
