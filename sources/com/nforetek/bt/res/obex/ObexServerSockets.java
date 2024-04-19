package com.nforetek.bt.res.obex;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import com.nforetek.util.bt.NfReflection;
import com.nforetek.util.normal.NfLog;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class ObexServerSockets {
    private static final boolean D = true;
    private static final int NUMBER_OF_SOCKET_TYPES = 2;
    private static final String STAG = "ObexServerSockets";
    private static volatile int sInstanceCounter = 0;
    private final String TAG;
    private final IObexConnectionHandler mConHandler;
    private final BluetoothServerSocket mL2capSocket;
    private final BluetoothServerSocket mRfcommSocket;
    private SocketAcceptThread mRfcommThread = null;
    private SocketAcceptThread mL2capThread = null;
    private volatile boolean mConAccepted = false;

    private ObexServerSockets(IObexConnectionHandler conHandler, BluetoothServerSocket rfcommSocket, BluetoothServerSocket l2capSocket) {
        this.mConHandler = conHandler;
        this.mRfcommSocket = rfcommSocket;
        this.mL2capSocket = l2capSocket;
        StringBuilder sb = new StringBuilder(STAG);
        int i = sInstanceCounter;
        sInstanceCounter = i + 1;
        this.TAG = sb.append(i).toString();
    }

    public static ObexServerSockets create(IObexConnectionHandler validator) {
        NfLog.d(STAG, "N: create ObexServerSockets");
        return create(validator, -2, -2);
    }

    private static ObexServerSockets create(IObexConnectionHandler validator, int rfcommChannel, int l2capPsm) {
        NfLog.d(STAG, "N: create(rfcomm = " + rfcommChannel + ", l2capPsm = " + l2capPsm + ")");
        BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();
        NfLog.d(STAG, "N: create( got adapter )");
        if (bt == null) {
            throw new RuntimeException("No bluetooth adapter...");
        }
        BluetoothServerSocket rfcommSocket = null;
        BluetoothServerSocket l2capSocket = null;
        boolean initSocketOK = false;
        int i = 0;
        while (true) {
            if (i < 10) {
                NfLog.d(STAG, "N: for CREATE_RETRY_TIME " + i);
                if (rfcommSocket == null) {
                    try {
                        rfcommSocket = NfReflection.listenUsingRfcommOn(rfcommChannel);
                    } catch (Exception e) {
                        NfLog.e(STAG, "Error create ServerSockets ", e);
                        initSocketOK = false;
                    }
                }
                if (l2capSocket == null) {
                    l2capSocket = listenUsingL2capOn(bt, l2capPsm);
                }
                if (rfcommSocket != null && l2capSocket != null) {
                    initSocketOK = true;
                }
                if (!initSocketOK) {
                    NfLog.d(STAG, "N: !initSocketOK");
                    int state = bt.getState();
                    if (state != 11 && state != 12) {
                        NfLog.w(STAG, "initServerSockets failed as BT is (being) turned off");
                        break;
                    }
                    try {
                        NfLog.v(STAG, "waiting 300 ms...");
                        Thread.sleep(300L);
                        i++;
                    } catch (InterruptedException e2) {
                        NfLog.e(STAG, "create() was interrupted");
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        NfLog.d(STAG, "N: initSocketOK= " + initSocketOK);
        if (initSocketOK) {
            NfLog.d(STAG, "Succeed to create listening sockets ");
            ObexServerSockets sockets = new ObexServerSockets(validator, rfcommSocket, l2capSocket);
            sockets.startAccept();
            NfLog.d(STAG, "N: create ObexServerSockets success");
            return sockets;
        }
        NfLog.e(STAG, "Error to create listening socket after 10 try");
        return null;
    }

    public int getRfcommChannel() {
        return getChannel(this.mRfcommSocket);
    }

    public int getL2capPsm() {
        return getChannel(this.mL2capSocket);
    }

    private static BluetoothServerSocket listenUsingL2capOn(BluetoothAdapter s, int l2capPsm) {
        if (s == null) {
            NfLog.e(STAG, "listenUsingL2capOn: adapter null ");
            return null;
        }
        try {
            Method m = s.getClass().getMethod("listenUsingL2capOn", Integer.TYPE);
            return (BluetoothServerSocket) m.invoke(s, Integer.valueOf(l2capPsm));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (SecurityException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        } catch (Exception e6) {
            e6.printStackTrace();
            return null;
        }
    }

    private int getChannel(BluetoothServerSocket s) {
        if (s == null) {
            NfLog.e(STAG, "listenUsingL2capOn: BluetoothServerSocket null ");
            return -1;
        }
        try {
            Method m = s.getClass().getMethod("getChannel", new Class[0]);
            return ((Integer) m.invoke(s, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return -1;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return -1;
        } catch (SecurityException e4) {
            e4.printStackTrace();
            return -1;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return -1;
        } catch (Exception e6) {
            e6.printStackTrace();
            return -1;
        }
    }

    private void startAccept() {
        NfLog.d(this.TAG, "startAccept()");
        prepareForNewConnect();
        this.mRfcommThread = new SocketAcceptThread(this.mRfcommSocket);
        this.mRfcommThread.start();
        this.mL2capThread = new SocketAcceptThread(this.mL2capSocket);
        this.mL2capThread.start();
    }

    public void prepareForNewConnect() {
        NfLog.d(this.TAG, "prepareForNewConnect()");
        this.mConAccepted = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean onConnect(BluetoothDevice device, BluetoothSocket conSocket) {
        boolean z = true;
        synchronized (this) {
            NfLog.d(this.TAG, "onConnect() socket: " + conSocket + " mConAccepted = " + this.mConAccepted);
            if (this.mConAccepted || !this.mConHandler.onConnect(device, conSocket)) {
                z = false;
            } else {
                this.mConAccepted = true;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onAcceptFailed() {
        NfLog.w(this.TAG, "onAcceptFailed() calling shutdown...");
        this.mConHandler.onAcceptFailed();
        shutdown(false);
    }

    public synchronized void shutdown(boolean block) {
        NfLog.d(this.TAG, "shutdown(block = " + block + ")");
        if (this.mRfcommThread != null) {
            this.mRfcommThread.shutdown();
        }
        if (this.mL2capThread != null) {
            this.mL2capThread.shutdown();
        }
        if (block) {
            while (true) {
                if (this.mRfcommThread == null && this.mL2capThread == null) {
                    break;
                }
                try {
                    if (this.mRfcommThread != null) {
                        this.mRfcommThread.join();
                        this.mRfcommThread = null;
                    }
                    if (this.mL2capThread != null) {
                        this.mL2capThread.join();
                        this.mL2capThread = null;
                    }
                } catch (InterruptedException e) {
                    NfLog.i(this.TAG, "shutdown() interrupted, continue waiting...", e);
                }
            }
        } else {
            this.mRfcommThread = null;
            this.mL2capThread = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SocketAcceptThread extends Thread {
        private final BluetoothServerSocket mServerSocket;
        private boolean mStopped = false;

        public SocketAcceptThread(BluetoothServerSocket serverSocket) {
            if (serverSocket == null) {
                throw new IllegalArgumentException("serverSocket cannot be null");
            }
            this.mServerSocket = serverSocket;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!this.mStopped) {
                try {
                    try {
                        NfLog.d(ObexServerSockets.this.TAG, "Accepting socket connection...");
                        BluetoothSocket connSocket = this.mServerSocket.accept();
                        NfLog.d(ObexServerSockets.this.TAG, "Accepted socket connection from: " + this.mServerSocket);
                        if (connSocket == null) {
                            NfLog.w(ObexServerSockets.this.TAG, "connSocket is null - reattempt accept");
                        } else {
                            BluetoothDevice device = connSocket.getRemoteDevice();
                            if (device == null) {
                                NfLog.i(ObexServerSockets.this.TAG, "getRemoteDevice() = null - reattempt accept");
                                try {
                                    connSocket.close();
                                } catch (IOException e) {
                                    NfLog.w(ObexServerSockets.this.TAG, "Error closing the socket. ignoring...", e);
                                }
                            } else {
                                boolean isValid = ObexServerSockets.this.onConnect(device, connSocket);
                                if (!isValid) {
                                    NfLog.i(ObexServerSockets.this.TAG, "RemoteDevice is invalid - creating ObexRejectServer.");
                                    BluetoothObexTransport obexTrans = new BluetoothObexTransport(connSocket);
                                    new ServerSession(obexTrans, new ObexRejectServer(211, connSocket), null);
                                }
                            }
                        }
                    } catch (IOException ex) {
                        if (!this.mStopped) {
                            NfLog.w(ObexServerSockets.this.TAG, "Accept exception for " + this.mServerSocket, ex);
                            ObexServerSockets.this.onAcceptFailed();
                        }
                        this.mStopped = true;
                    }
                } finally {
                    NfLog.d(ObexServerSockets.this.TAG, "AcceptThread ended for: " + this.mServerSocket);
                }
            }
        }

        public void shutdown() {
            if (!this.mStopped) {
                this.mStopped = true;
                try {
                    this.mServerSocket.close();
                } catch (IOException e) {
                    NfLog.d(ObexServerSockets.this.TAG, "Exception while thread shutdown:", e);
                }
            }
            if (!Thread.currentThread().equals(this)) {
                NfLog.d(ObexServerSockets.this.TAG, "shutdown called from another thread - interrupt().");
                interrupt();
            }
        }
    }
}
