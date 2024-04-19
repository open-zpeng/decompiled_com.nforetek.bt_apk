package com.nforetek.bt.profile.pbap.java;

import android.bluetooth.BluetoothSocket;
import com.nforetek.bt.res.obex.ObexTransport;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes.dex */
class BluetoothPbapObexTransport implements ObexTransport {
    private BluetoothSocket mSocket;

    BluetoothPbapObexTransport(BluetoothSocket rfs) {
        this.mSocket = null;
        this.mSocket = rfs;
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
        return this.mSocket.isConnected();
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
