package com.nforetek.bt.res.obex;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public final class PrivateInputStream extends InputStream {
    private byte[] mData = new byte[0];
    private int mIndex = 0;
    private boolean mOpen = true;
    private BaseStream mParent;

    public PrivateInputStream(BaseStream p) {
        this.mParent = p;
    }

    @Override // java.io.InputStream
    public synchronized int available() throws IOException {
        ensureOpen();
        return this.mData.length - this.mIndex;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000b, code lost:
        r0 = r3.mData;
        r1 = r3.mIndex;
        r3.mIndex = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0015, code lost:
        r0 = r0[r1] & com.nforetek.bt.res.NfDef.AVRCP_PLAYING_STATUS_ID_ERROR;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized int read() throws java.io.IOException {
        /*
            r3 = this;
            monitor-enter(r3)
            r3.ensureOpen()     // Catch: java.lang.Throwable -> L28
        L4:
            byte[] r0 = r3.mData     // Catch: java.lang.Throwable -> L28
            int r0 = r0.length     // Catch: java.lang.Throwable -> L28
            int r1 = r3.mIndex     // Catch: java.lang.Throwable -> L28
            if (r0 == r1) goto L19
            byte[] r0 = r3.mData     // Catch: java.lang.Throwable -> L28
            int r1 = r3.mIndex     // Catch: java.lang.Throwable -> L28
            int r2 = r1 + 1
            r3.mIndex = r2     // Catch: java.lang.Throwable -> L28
            r0 = r0[r1]     // Catch: java.lang.Throwable -> L28
            r0 = r0 & 255(0xff, float:3.57E-43)
        L17:
            monitor-exit(r3)
            return r0
        L19:
            r3.ensureOpen()     // Catch: java.lang.Throwable -> L28
            com.nforetek.bt.res.obex.BaseStream r0 = r3.mParent     // Catch: java.lang.Throwable -> L28
            r1 = 1
            r2 = 1
            boolean r0 = r0.continueOperation(r1, r2)     // Catch: java.lang.Throwable -> L28
            if (r0 != 0) goto L4
            r0 = -1
            goto L17
        L28:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.res.obex.PrivateInputStream.read():int");
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] b, int offset, int length) throws IOException {
        int i;
        if (b == null) {
            throw new IOException("buffer is null");
        }
        if ((offset | length) < 0 || length > b.length - offset) {
            throw new ArrayIndexOutOfBoundsException("index outof bound");
        }
        ensureOpen();
        int currentDataLength = this.mData.length - this.mIndex;
        int remainReadLength = length;
        int offset1 = offset;
        int result = 0;
        while (true) {
            if (currentDataLength <= remainReadLength) {
                System.arraycopy(this.mData, this.mIndex, b, offset1, currentDataLength);
                this.mIndex += currentDataLength;
                offset1 += currentDataLength;
                result += currentDataLength;
                remainReadLength -= currentDataLength;
                ensureOpen();
                if (!this.mParent.continueOperation(true, true)) {
                    i = result == 0 ? -1 : result;
                } else {
                    currentDataLength = this.mData.length - this.mIndex;
                }
            } else {
                if (remainReadLength > 0) {
                    System.arraycopy(this.mData, this.mIndex, b, offset1, remainReadLength);
                    this.mIndex += remainReadLength;
                    result += remainReadLength;
                }
                i = result;
            }
        }
        return i;
    }

    public synchronized void writeBytes(byte[] body, int start) {
        int length = (body.length - start) + (this.mData.length - this.mIndex);
        byte[] temp = new byte[length];
        System.arraycopy(this.mData, this.mIndex, temp, 0, this.mData.length - this.mIndex);
        System.arraycopy(body, start, temp, this.mData.length - this.mIndex, body.length - start);
        this.mData = temp;
        this.mIndex = 0;
        notifyAll();
    }

    private void ensureOpen() throws IOException {
        this.mParent.ensureOpen();
        if (!this.mOpen) {
            throw new IOException("Input stream is closed");
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mOpen = false;
        this.mParent.streamClosed(true);
    }
}
