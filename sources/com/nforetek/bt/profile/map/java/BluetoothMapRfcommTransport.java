package com.nforetek.bt.profile.map.java;

import android.bluetooth.BluetoothSocket;
import com.nforetek.bt.res.obex.ObexTransport;
import com.nforetek.util.normal.NfLog;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes.dex */
class BluetoothMapRfcommTransport implements ObexTransport {
    private static final String TAG = "MapRfcomm";
    private final BluetoothSocket mSocket;

    public BluetoothMapRfcommTransport(BluetoothSocket socket) {
        this.mSocket = socket;
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public void create() throws IOException {
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public void listen() throws IOException {
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public void close() {
        NfLog.d(TAG, "close mSocket");
        try {
            this.mSocket.close();
        } catch (Exception e) {
            NfLog.e(TAG, "close mSocket fail.", e);
            e.printStackTrace();
        }
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public void connect() throws IOException {
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public void disconnect() throws IOException {
        NfLog.d(TAG, "disconnect");
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
    public DataInputStream openDataInputStream() throws IOException {
        return new DataInputStream(openInputStream());
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public DataOutputStream openDataOutputStream() throws IOException {
        return new DataOutputStream(openOutputStream());
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public int getMaxTransmitPacketSize() {
        return -1;
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public int getMaxReceivePacketSize() {
        return -1;
    }

    @Override // com.nforetek.bt.res.obex.ObexTransport
    public boolean isSrmSupported() {
        return false;
    }
}
