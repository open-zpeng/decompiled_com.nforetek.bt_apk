package com.nforetek.bt.res.obex;

import android.bluetooth.BluetoothSocket;
import com.nforetek.util.normal.NfLog;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class BluetoothObexTransport implements ObexTransport {
    private static final String TAG = "BluetoothObexTransport";
    public static final int TYPE_L2CAP = 3;
    public static final int TYPE_RFCOMM = 1;
    public static final int TYPE_SCO = 2;
    private BluetoothSocket mSocket;

    public BluetoothObexTransport(BluetoothSocket socket) {
        this.mSocket = null;
        this.mSocket = socket;
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public void close() throws IOException {
        this.mSocket.close();
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public DataInputStream openDataInputStream() throws IOException {
        return new DataInputStream(openInputStream());
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public DataOutputStream openDataOutputStream() throws IOException {
        return new DataOutputStream(openOutputStream());
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public InputStream openInputStream() throws IOException {
        return this.mSocket.getInputStream();
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public OutputStream openOutputStream() throws IOException {
        return this.mSocket.getOutputStream();
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public void connect() throws IOException {
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public void create() throws IOException {
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public void disconnect() throws IOException {
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public void listen() throws IOException {
    }

    public boolean isConnected() throws IOException {
        return true;
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public int getMaxTransmitPacketSize() {
        if (getConnectionType() != 3) {
            return -1;
        }
        return getMaxTransmitPacketSizeInternal();
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public int getMaxReceivePacketSize() {
        if (getConnectionType() != 3) {
            return -1;
        }
        return getMaxReceivePacketSizeInternal();
    }

    public String getRemoteAddress() {
        if (this.mSocket == null) {
            return null;
        }
        NfLog.d(TAG, "N: getRemoteAddress");
        return this.mSocket.getRemoteDevice().getAddress();
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public boolean isSrmSupported() {
        return getConnectionType() == 3;
    }

    private int getConnectionType() {
        try {
            Method m = this.mSocket.getClass().getMethod("getConnectionType", new Class[0]);
            return ((Integer) m.invoke(this.mSocket, new Object[0])).intValue();
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

    private int getMaxTransmitPacketSizeInternal() {
        try {
            Method m = this.mSocket.getClass().getMethod("getMaxTransmitPacketSize", new Class[0]);
            return ((Integer) m.invoke(this.mSocket, new Object[0])).intValue();
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

    private int getMaxReceivePacketSizeInternal() {
        try {
            Method m = this.mSocket.getClass().getMethod("getMaxReceivePacketSize", new Class[0]);
            return ((Integer) m.invoke(this.mSocket, new Object[0])).intValue();
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
}
