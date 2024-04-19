package com.nforetek.bt.res.obex;

import android.util.Log;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes.dex */
public final class ClientSession extends ObexSession {
    private static final boolean DBG = true;
    private static int instCount = 0;
    private String TAG;
    private boolean isAbort;
    private byte[] mConnectionId;
    private final InputStream mInput;
    private boolean mInterrupted;
    private boolean mIsStopObex;
    private final boolean mLocalSrmSupported;
    private int mMaxTxPacketSize;
    private boolean mObexConnected;
    private boolean mOpen;
    private final OutputStream mOutput;
    private boolean mRequestActive;
    private final ObexTransport mTransport;
    private int open_sup_feature;

    public ClientSession(ObexTransport trans) throws IOException {
        this(trans, trans.isSrmSupported());
    }

    public ClientSession(ObexTransport trans, boolean supportsSrm) throws IOException {
        this.TAG = "ClientSession";
        this.mInterrupted = false;
        this.mConnectionId = null;
        this.mMaxTxPacketSize = 255;
        this.open_sup_feature = 0;
        this.mIsStopObex = false;
        this.isAbort = false;
        this.TAG = String.valueOf(this.TAG) + instCount;
        instCount++;
        this.mInput = trans.openInputStream();
        this.mOutput = trans.openOutputStream();
        this.mOpen = true;
        this.mRequestActive = false;
        this.mLocalSrmSupported = supportsSrm;
        this.mTransport = trans;
    }

    public HeaderSet connect(HeaderSet header) throws IOException {
        ensureOpen();
        if (this.mObexConnected) {
            throw new IOException("Already connected to server");
        }
        setRequestActive();
        this.mInterrupted = false;
        int totalLength = 4;
        byte[] head = null;
        if (header != null) {
            if (header.nonce != null) {
                this.mChallengeDigest = new byte[16];
                System.arraycopy(header.nonce, 0, this.mChallengeDigest, 0, 16);
            }
            head = ObexHelper.createHeader(header, false);
            totalLength = 4 + head.length;
        }
        byte[] support_feature = new byte[9];
        if (NfConfig.isPtsTest() && this.open_sup_feature % 2 == 0) {
            totalLength += 9;
            support_feature[0] = 76;
            support_feature[1] = 0;
            support_feature[2] = 9;
            support_feature[3] = NfDef.AVRCP_BROWSING_STATUS_SEARCH_NOT_SUPPORT;
            support_feature[4] = 4;
            support_feature[5] = 0;
            support_feature[6] = 0;
            support_feature[7] = 0;
            support_feature[8] = 1;
        }
        byte[] requestPacket = new byte[totalLength];
        requestPacket[0] = NfDef.AVRCP_BROWSING_STATUS_SEARCH_NOT_SUPPORT;
        requestPacket[1] = 0;
        requestPacket[2] = (byte) 32;
        requestPacket[3] = (byte) 0;
        if (head != null) {
            System.arraycopy(head, 0, requestPacket, 4, head.length);
        }
        if (NfConfig.isPtsTest()) {
            if (this.open_sup_feature % 2 == 0) {
                NfLog.d(true, this.TAG, "Jade - start copy data");
                System.arraycopy(support_feature, 0, requestPacket, head.length + 4, 9);
                NfLog.d(true, this.TAG, "Jade - copy data done.");
            }
            this.open_sup_feature++;
        }
        if (requestPacket.length + 3 > 65534) {
            throw new IOException("Packet size exceeds max packet size for connect");
        }
        HeaderSet returnHeaderSet = new HeaderSet();
        sendRequest(128, requestPacket, returnHeaderSet, null, false);
        if (returnHeaderSet.responseCode == 160) {
            this.mObexConnected = true;
        }
        setRequestInactive();
        return returnHeaderSet;
    }

    public Operation get(HeaderSet header) throws IOException {
        HeaderSet head;
        if (!this.mObexConnected) {
            throw new IOException("Not connected to the server");
        }
        setRequestActive();
        ensureOpen();
        if (header == null) {
            head = new HeaderSet();
        } else {
            head = header;
            if (head.nonce != null) {
                this.mChallengeDigest = new byte[16];
                System.arraycopy(head.nonce, 0, this.mChallengeDigest, 0, 16);
            }
        }
        if (this.mConnectionId != null) {
            head.mConnectionID = new byte[4];
            System.arraycopy(this.mConnectionId, 0, head.mConnectionID, 0, 4);
        }
        if (this.mLocalSrmSupported) {
            head.setHeader(HeaderSet.SINGLE_RESPONSE_MODE, (byte) 1);
        }
        return new ClientOperation(this.mMaxTxPacketSize, this, head, true);
    }

    public void setConnectionID(long id) {
        if (id < 0 || id > 4294967295L) {
            throw new IllegalArgumentException("Connection ID is not in a valid range");
        }
        this.mConnectionId = ObexHelper.convertToByteArray(id);
    }

    public HeaderSet delete(HeaderSet header) throws IOException {
        Operation op = put(header);
        op.getResponseCode();
        HeaderSet returnValue = op.getReceivedHeader();
        op.close();
        return returnValue;
    }

    public HeaderSet disconnect(HeaderSet header) throws IOException {
        NfLog.d(true, this.TAG, "disconnect start ");
        if (!this.mObexConnected) {
            throw new IOException("Not connected to the server");
        }
        this.mInterrupted = true;
        NfLog.d(this.TAG, "clientSession disconnect");
        setRequestActive();
        ensureOpen();
        try {
            HeaderSet returnHeaderSet1 = new HeaderSet();
            sendRequest(255, null, returnHeaderSet1, null, false);
        } catch (Exception e) {
            NfLog.e(this.TAG, "OBEX_OPCODE_ABORT: ", e);
        }
        byte[] head = null;
        if (header != null) {
            NfLog.d(true, this.TAG, "disconnect phase 1a ");
            if (header.nonce != null) {
                this.mChallengeDigest = new byte[16];
                System.arraycopy(header.nonce, 0, this.mChallengeDigest, 0, 16);
            }
            if (this.mConnectionId != null) {
                header.mConnectionID = new byte[4];
                System.arraycopy(this.mConnectionId, 0, header.mConnectionID, 0, 4);
            }
            head = ObexHelper.createHeader(header, false);
            if (head.length + 3 > this.mMaxTxPacketSize) {
                throw new IOException("Packet size exceeds max packet size");
            }
        } else {
            NfLog.d(true, this.TAG, "disconnect phase 1b ");
            if (this.mConnectionId != null) {
                head = new byte[5];
                head[0] = -53;
                System.arraycopy(this.mConnectionId, 0, head, 1, 4);
            }
        }
        NfLog.d(true, this.TAG, "disconnect phase 2 ");
        HeaderSet returnHeaderSet = new HeaderSet();
        sendRequest(129, head, returnHeaderSet, null, false);
        NfLog.d(true, this.TAG, "disconnect phase 3 ");
        synchronized (this) {
            this.mObexConnected = false;
            setRequestInactive();
        }
        NfLog.d(true, this.TAG, "disconnect success ");
        return returnHeaderSet;
    }

    public long getConnectionID() {
        if (this.mConnectionId == null) {
            return -1L;
        }
        return ObexHelper.convertToLong(this.mConnectionId);
    }

    public Operation put(HeaderSet header) throws IOException {
        HeaderSet head;
        if (!this.mObexConnected) {
            throw new IOException("Not connected to the server");
        }
        setRequestActive();
        ensureOpen();
        if (header == null) {
            head = new HeaderSet();
        } else {
            head = header;
            if (head.nonce != null) {
                this.mChallengeDigest = new byte[16];
                System.arraycopy(head.nonce, 0, this.mChallengeDigest, 0, 16);
            }
        }
        if (this.mConnectionId != null) {
            head.mConnectionID = new byte[4];
            System.arraycopy(this.mConnectionId, 0, head.mConnectionID, 0, 4);
        }
        if (this.mLocalSrmSupported) {
            head.setHeader(HeaderSet.SINGLE_RESPONSE_MODE, (byte) 1);
        }
        return new ClientOperation(this.mMaxTxPacketSize, this, head, false);
    }

    public void setAuthenticator(Authenticator auth) throws IOException {
        if (auth == null) {
            throw new IOException("Authenticator may not be null");
        }
        this.mAuthenticator = auth;
    }

    public HeaderSet setPath(HeaderSet header, boolean backup, boolean create) throws IOException {
        HeaderSet headset;
        if (!this.mObexConnected) {
            throw new IOException("Not connected to the server");
        }
        setRequestActive();
        ensureOpen();
        if (header == null) {
            headset = new HeaderSet();
        } else {
            headset = header;
            if (headset.nonce != null) {
                this.mChallengeDigest = new byte[16];
                System.arraycopy(headset.nonce, 0, this.mChallengeDigest, 0, 16);
            }
        }
        if (headset.nonce != null) {
            this.mChallengeDigest = new byte[16];
            System.arraycopy(headset.nonce, 0, this.mChallengeDigest, 0, 16);
        }
        if (this.mConnectionId != null) {
            headset.mConnectionID = new byte[4];
            System.arraycopy(this.mConnectionId, 0, headset.mConnectionID, 0, 4);
        }
        byte[] head = ObexHelper.createHeader(headset, false);
        int totalLength = 2 + head.length;
        if (totalLength > this.mMaxTxPacketSize) {
            throw new IOException("Packet size exceeds max packet size");
        }
        int flags = 0;
        if (backup) {
            flags = 0 + 1;
        }
        if (!create) {
            flags |= 2;
        }
        byte[] packet = new byte[totalLength];
        packet[0] = (byte) flags;
        packet[1] = 0;
        if (headset != null) {
            System.arraycopy(head, 0, packet, 2, head.length);
        }
        HeaderSet returnHeaderSet = new HeaderSet();
        sendRequest(133, packet, returnHeaderSet, null, false);
        setRequestInactive();
        return returnHeaderSet;
    }

    public synchronized void ensureOpen() throws IOException {
        if (!this.mOpen) {
            throw new IOException("Connection closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setRequestInactive() {
        this.mRequestActive = false;
    }

    private synchronized void setRequestActive() throws IOException {
        if (this.mRequestActive) {
            throw new IOException("OBEX request is already being performed");
        }
        this.mRequestActive = true;
    }

    private boolean isRequestActive() {
        return this.mRequestActive;
    }

    private synchronized void setRequestActiveForce() {
        this.mRequestActive = true;
    }

    public void interruptObex() {
        this.mIsStopObex = true;
    }

    public boolean sendRequest(int opCode, byte[] head, HeaderSet header, PrivateInputStream privateInput, boolean srmActive) throws IOException {
        byte[] data;
        if (head != null && head.length + 3 > 65534) {
            throw new IOException("header too large ");
        }
        boolean skipSend = false;
        boolean skipReceive = false;
        if (srmActive) {
            if (opCode == 2) {
                skipReceive = true;
            } else if (opCode == 3) {
                skipReceive = true;
            } else if (opCode == 131) {
                skipSend = true;
            }
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write((byte) opCode);
        if (head == null) {
            out.write(0);
            out.write(3);
        } else {
            out.write((byte) ((head.length + 3) >> 8));
            out.write((byte) (head.length + 3));
            out.write(head);
        }
        if (!skipSend) {
            NfLog.d(true, this.TAG, "sendRequest !skipSend write");
            this.mOutput.write(out.toByteArray());
            NfLog.d(true, this.TAG, "sendRequest !skipSend flush");
            this.mOutput.flush();
            NfLog.d(true, this.TAG, "sendRequest !skipSend done");
        }
        if (!skipReceive) {
            NfLog.d(true, this.TAG, "sendRequest !skipReceive 1");
            header.responseCode = this.mInput.read();
            NfLog.d(true, this.TAG, "sendRequest !skipReceive 1a");
            int length = (this.mInput.read() << 8) | this.mInput.read();
            NfLog.d(true, this.TAG, "start receive, len " + length);
            NfLog.d(true, this.TAG, "start receive, responseCode " + header.responseCode);
            if (length > ObexHelper.getMaxRxPacketSize(this.mTransport)) {
                throw new IOException("Packet received exceeds packet size limit");
            }
            if (length > 3) {
                if (opCode == 128) {
                    NfLog.d(true, this.TAG, "sendRequest !skipReceive 2");
                    this.mInput.read();
                    NfLog.d(true, this.TAG, "sendRequest !skipReceive 3");
                    this.mInput.read();
                    NfLog.d(true, this.TAG, "sendRequest !skipReceive 4");
                    this.mMaxTxPacketSize = (this.mInput.read() << 8) + this.mInput.read();
                    if (this.mMaxTxPacketSize > 64512) {
                        this.mMaxTxPacketSize = ObexHelper.MAX_CLIENT_PACKET_SIZE;
                    }
                    if (this.mMaxTxPacketSize > ObexHelper.getMaxTxPacketSize(this.mTransport)) {
                        Log.w(this.TAG, "An OBEX packet size of " + this.mMaxTxPacketSize + "was requested. Transport only allows: " + ObexHelper.getMaxTxPacketSize(this.mTransport) + " Lowering limit to this value.");
                        this.mMaxTxPacketSize = ObexHelper.getMaxTxPacketSize(this.mTransport);
                    }
                    if (length > 7) {
                        data = new byte[length - 7];
                        NfLog.d(true, this.TAG, "sendRequest !skipReceive 5");
                        int bytesReceived = this.mInput.read(data);
                        while (bytesReceived != length - 7) {
                            NfLog.d(true, this.TAG, "sendRequest !skipReceive 6");
                            bytesReceived += this.mInput.read(data, bytesReceived, data.length - bytesReceived);
                        }
                    } else {
                        return true;
                    }
                } else {
                    data = new byte[length - 3];
                    NfLog.d(true, this.TAG, "sendRequest !skipReceive 7");
                    int bytesReceived2 = this.mInput.read(data);
                    int timeoutCount = 0;
                    while (bytesReceived2 != length - 3) {
                        NfLog.d(true, this.TAG, "sendRequest !skipReceive 8");
                        int readed = this.mInput.read(data, bytesReceived2, data.length - bytesReceived2);
                        if (readed == 0) {
                            timeoutCount++;
                        }
                        if (timeoutCount >= 8) {
                            throw new IOException("Timeout when receive data.");
                        }
                        bytesReceived2 += readed;
                    }
                    if (opCode == 255) {
                        return true;
                    }
                }
                byte[] body = ObexHelper.updateHeaderSet(header, data);
                if (privateInput != null && body != null) {
                    privateInput.writeBytes(body, 1);
                }
                if (header.mConnectionID != null) {
                    this.mConnectionId = new byte[4];
                    System.arraycopy(header.mConnectionID, 0, this.mConnectionId, 0, 4);
                }
                NfLog.d(true, this.TAG, "mAuthResp " + header.mAuthResp);
                if (header.mAuthResp != null) {
                    NfLog.d(true, this.TAG, "handleAuthResp start");
                    if (!handleAuthResp(header.mAuthResp)) {
                        setRequestInactive();
                        throw new IOException("Authentication Failed");
                    }
                    NfLog.d(true, this.TAG, "handleAuthResp success");
                }
                NfLog.d(true, this.TAG, "responseCode " + header.responseCode);
                NfLog.d(true, this.TAG, "wanted 193");
                if (header.responseCode == 193 && header.mAuthChall != null) {
                    NfLog.d(true, this.TAG, "handleAuthChall start");
                    if (handleAuthChall(header)) {
                        Log.d(this.TAG, "handleAuthChall success");
                        out.write(78);
                        out.write((byte) ((header.mAuthResp.length + 3) >> 8));
                        out.write((byte) (header.mAuthResp.length + 3));
                        out.write(header.mAuthResp);
                        header.mAuthChall = null;
                        header.mAuthResp = null;
                        byte[] sendHeaders = new byte[out.size() - 3];
                        System.arraycopy(out.toByteArray(), 3, sendHeaders, 0, sendHeaders.length);
                        return sendRequest(opCode, sendHeaders, header, privateInput, false);
                    }
                    NfLog.d(true, this.TAG, "handleAuthChall failed");
                }
            }
            NfLog.d(true, this.TAG, "sendRequest !skipReceive (done)");
        }
        return true;
    }

    public void setAbort() {
        this.isAbort = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0021, code lost:
        throw new java.io.IOException("Read Timeout");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void timeoutRead() throws java.io.IOException {
        /*
            r6 = this;
            java.io.InputStream r3 = r6.mInput
            if (r3 != 0) goto L5
        L4:
            return
        L5:
            r2 = 12500(0x30d4, float:1.7516E-41)
            r1 = 10
            r0 = 0
        La:
            boolean r3 = r6.mIsStopObex
            if (r3 != 0) goto L18
            java.io.InputStream r3 = r6.mInput
            int r0 = r3.available()
            if (r0 != 0) goto L18
            if (r1 < r2) goto L22
        L18:
            if (r0 != 0) goto L4
            java.io.IOException r3 = new java.io.IOException
            java.lang.String r4 = "Read Timeout"
            r3.<init>(r4)
            throw r3
        L22:
            boolean r3 = r6.isAbort
            if (r3 == 0) goto L35
            java.lang.String r3 = r6.TAG
            java.lang.String r4 = "timeoutRead got abort signal"
            com.nforetek.util.normal.NfLog.d(r3, r4)
            java.io.IOException r3 = new java.io.IOException
            java.lang.String r4 = "TimeoutReader got abort signal"
            r3.<init>(r4)
            throw r3
        L35:
            int r1 = r1 + 1
            long r4 = (long) r1
            java.lang.Thread.sleep(r4)     // Catch: java.lang.InterruptedException -> L3c
            goto La
        L3c:
            r3 = move-exception
            goto La
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.res.obex.ClientSession.timeoutRead():void");
    }

    public void close() throws IOException {
        this.mOpen = false;
        this.mInput.close();
        this.mOutput.close();
    }

    public boolean isSrmSupported() {
        NfLog.d(this.TAG, "Jade : return mLocalSrmSupported " + this.mLocalSrmSupported);
        return this.mLocalSrmSupported;
    }
}
