package com.nforetek.bt.res.obex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes.dex */
public interface ObexTransport {
    void close() throws IOException;

    void connect() throws IOException;

    void create() throws IOException;

    void disconnect() throws IOException;

    int getMaxReceivePacketSize();

    int getMaxTransmitPacketSize();

    boolean isSrmSupported();

    void listen() throws IOException;

    DataInputStream openDataInputStream() throws IOException;

    DataOutputStream openDataOutputStream() throws IOException;

    InputStream openInputStream() throws IOException;

    OutputStream openOutputStream() throws IOException;
}
