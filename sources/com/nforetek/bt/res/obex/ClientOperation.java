package com.nforetek.bt.res.obex;

import android.util.Log;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes.dex */
public final class ClientOperation implements Operation, BaseStream {
    private static final boolean V = true;
    private static int instCount = 0;
    private String TAG;
    private boolean mEndOfBodySent;
    private String mExceptionMessage;
    private boolean mGetFinalFlag;
    private boolean mGetOperation;
    private boolean mInputOpen;
    private int mMaxPacketSize;
    private boolean mOperationDone;
    private ClientSession mParent;
    private PrivateInputStream mPrivateInput;
    private boolean mPrivateInputOpen;
    private PrivateOutputStream mPrivateOutput;
    private boolean mPrivateOutputOpen;
    private HeaderSet mReplyHeader;
    private HeaderSet mRequestHeader;
    private boolean mSendBodyHeader = true;
    private boolean mSrmActive = false;
    private boolean mSrmEnabled = false;
    private boolean mSrmWaitingForRemote = true;
    int streamCloseMaxSendCount = 10;

    public ClientOperation(int maxSize, ClientSession p, HeaderSet header, boolean type) throws IOException {
        this.TAG = "ClientOperation";
        this.TAG = String.valueOf(this.TAG) + instCount;
        instCount++;
        this.mParent = p;
        this.mEndOfBodySent = false;
        this.mInputOpen = true;
        this.mOperationDone = false;
        this.mMaxPacketSize = maxSize;
        this.mGetOperation = type;
        this.mGetFinalFlag = false;
        this.mPrivateInputOpen = false;
        this.mPrivateOutputOpen = false;
        this.mPrivateInput = null;
        this.mPrivateOutput = null;
        this.mReplyHeader = new HeaderSet();
        this.mRequestHeader = new HeaderSet();
        int[] headerList = header.getHeaderList();
        if (headerList != null) {
            for (int element : headerList) {
                this.mRequestHeader.setHeader(element, header.getHeader(element));
            }
        }
        if (header.mAuthChall != null) {
            this.mRequestHeader.mAuthChall = new byte[header.mAuthChall.length];
            System.arraycopy(header.mAuthChall, 0, this.mRequestHeader.mAuthChall, 0, header.mAuthChall.length);
        }
        if (header.mAuthResp != null) {
            this.mRequestHeader.mAuthResp = new byte[header.mAuthResp.length];
            System.arraycopy(header.mAuthResp, 0, this.mRequestHeader.mAuthResp, 0, header.mAuthResp.length);
        }
        if (header.mConnectionID != null) {
            this.mRequestHeader.mConnectionID = new byte[4];
            System.arraycopy(header.mConnectionID, 0, this.mRequestHeader.mConnectionID, 0, 4);
        }
    }

    public void setGetFinalFlag(boolean flag) {
        this.mGetFinalFlag = flag;
    }

    public void fabort() throws IOException {
        NfLog.d(this.TAG, "fabort called");
        if (!this.mOperationDone || this.mReplyHeader.responseCode == 144) {
            this.mExceptionMessage = "Operation aborted";
            if (!this.mOperationDone && this.mReplyHeader.responseCode == 144) {
                NfLog.d(this.TAG, "abort processing, sendRequest");
                this.mOperationDone = true;
                this.mParent.sendRequest(255, null, this.mReplyHeader, null, false);
                if (this.mReplyHeader.responseCode != 160) {
                    NfLog.e(this.TAG, "abort response fail!!");
                    throw new IOException("Invalid response code from server");
                } else {
                    NfLog.d(this.TAG, "fabort done");
                    this.mExceptionMessage = null;
                }
            }
            NfLog.d(this.TAG, "!abort processing, sendRequest");
            close();
        }
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public synchronized void abort() throws IOException {
        NfLog.d(this.TAG, "abort called");
        ensureOpen();
        if (this.mOperationDone && this.mReplyHeader.responseCode != 144) {
            throw new IOException("Operation has already ended");
        }
        this.mExceptionMessage = "Operation aborted";
        if (!this.mOperationDone && this.mReplyHeader.responseCode == 144) {
            NfLog.d(this.TAG, "abort processing, sendRequest");
            this.mOperationDone = true;
            this.mParent.sendRequest(255, null, this.mReplyHeader, null, false);
            if (this.mReplyHeader.responseCode != 160) {
                NfLog.e(this.TAG, "abort response fail!!");
                throw new IOException("Invalid response code from server");
            } else {
                NfLog.d(this.TAG, "abort done");
                this.mExceptionMessage = null;
            }
        }
        close();
    }

    public boolean getOperationDone() {
        return this.mOperationDone;
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public synchronized int getResponseCode() throws IOException {
        if (this.mReplyHeader.responseCode == -1 || this.mReplyHeader.responseCode == 144) {
            validateConnection();
        }
        return this.mReplyHeader.responseCode;
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public String getEncoding() {
        return null;
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public String getType() {
        try {
            return (String) this.mReplyHeader.getHeader(66);
        } catch (IOException e) {
            Log.d(this.TAG, "Exception occured - returning null", e);
            return null;
        }
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public long getLength() {
        try {
            Long temp = (Long) this.mReplyHeader.getHeader(195);
            if (temp == null) {
                return -1L;
            }
            return temp.longValue();
        } catch (IOException e) {
            Log.d(this.TAG, "Exception occured - returning -1", e);
            return -1L;
        }
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public InputStream openInputStream() throws IOException {
        ensureOpen();
        if (this.mPrivateInputOpen) {
            throw new IOException("no more input streams available");
        }
        if (this.mGetOperation) {
            validateConnection();
        } else if (this.mPrivateInput == null) {
            this.mPrivateInput = new PrivateInputStream(this);
        }
        this.mPrivateInputOpen = true;
        return this.mPrivateInput;
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public DataInputStream openDataInputStream() throws IOException {
        return new DataInputStream(openInputStream());
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public OutputStream openOutputStream() throws IOException {
        ensureOpen();
        ensureNotDone();
        if (this.mPrivateOutputOpen) {
            throw new IOException("no more output streams available");
        }
        if (this.mPrivateOutput == null) {
            this.mPrivateOutput = new PrivateOutputStream(this, getMaxPacketSize());
        }
        this.mPrivateOutputOpen = true;
        return this.mPrivateOutput;
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public int getMaxPacketSize() {
        return (this.mMaxPacketSize - 6) - getHeaderLength();
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public int getHeaderLength() {
        byte[] headerArray = ObexHelper.createHeader(this.mRequestHeader, false);
        return headerArray.length;
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public DataOutputStream openDataOutputStream() throws IOException {
        return new DataOutputStream(openOutputStream());
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public void close() throws IOException {
        this.mInputOpen = false;
        this.mPrivateInputOpen = false;
        this.mPrivateOutputOpen = false;
        this.mParent.setRequestInactive();
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public HeaderSet getReceivedHeader() throws IOException {
        ensureOpen();
        return this.mReplyHeader;
    }

    public HeaderSet getReceivedHeaderEnd() throws IOException {
        return this.mReplyHeader;
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public void sendHeaders(HeaderSet headers) throws IOException {
        NfLog.d(this.TAG, "sendHeaders ");
        ensureOpen();
        if (this.mOperationDone) {
            throw new IOException("Operation has already exchanged all data");
        }
        if (headers == null) {
            throw new IOException("Headers may not be null");
        }
        int[] headerList = headers.getHeaderList();
        if (headerList != null) {
            for (int element : headerList) {
                this.mRequestHeader.setHeader(element, headers.getHeader(element));
            }
        }
    }

    @Override // com.nforetek.bt.res.obex.BaseStream
    public void ensureNotDone() throws IOException {
        if (this.mOperationDone) {
            throw new IOException("Operation has completed");
        }
    }

    @Override // com.nforetek.bt.res.obex.BaseStream
    public void ensureOpen() throws IOException {
        this.mParent.ensureOpen();
        if (this.mExceptionMessage != null) {
            throw new IOException(this.mExceptionMessage);
        }
        if (!this.mInputOpen) {
            throw new IOException("Operation has already ended");
        }
    }

    private void validateConnection() throws IOException {
        ensureOpen();
        if (this.mPrivateInput == null) {
            startProcessing();
        }
    }

    private boolean sendRequest(int opCode) throws IOException {
        boolean ret = sendRequest(opCode, 1);
        return ret;
    }

    private boolean sendRequest(int opCode, int nicktest) throws IOException {
        boolean returnValue = false;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int bodyLength = -1;
        byte[] headerArray = ObexHelper.createHeader(this.mRequestHeader, true);
        if (this.mPrivateOutput != null) {
            bodyLength = this.mPrivateOutput.size();
        }
        if (headerArray.length + 3 + 3 > this.mMaxPacketSize) {
            int end = 0;
            int start = 0;
            while (end != headerArray.length) {
                end = ObexHelper.findHeaderEnd(headerArray, start, this.mMaxPacketSize - 3);
                if (end == -1) {
                    this.mOperationDone = true;
                    abort();
                    this.mExceptionMessage = "Header larger then can be sent in a packet";
                    this.mInputOpen = false;
                    if (this.mPrivateInput != null) {
                        this.mPrivateInput.close();
                    }
                    if (this.mPrivateOutput != null) {
                        this.mPrivateOutput.close();
                    }
                    throw new IOException("OBEX Packet exceeds max packet size");
                }
                byte[] sendHeader = new byte[end - start];
                System.arraycopy(headerArray, start, sendHeader, 0, sendHeader.length);
                if (!this.mParent.sendRequest(opCode, sendHeader, this.mReplyHeader, this.mPrivateInput, false) || this.mReplyHeader.responseCode != 144) {
                    return false;
                }
                start = end;
            }
            checkForSrm();
            if (bodyLength > 0) {
                return true;
            }
            return false;
        }
        if (!this.mSendBodyHeader) {
            opCode |= 128;
        }
        out.write(headerArray);
        if (bodyLength > 0) {
            if (bodyLength > (this.mMaxPacketSize - headerArray.length) - 6) {
                returnValue = true;
                bodyLength = (this.mMaxPacketSize - headerArray.length) - 6;
            }
            byte[] body = this.mPrivateOutput.readBytes(bodyLength);
            if (this.mPrivateOutput.isClosed() && !returnValue && !this.mEndOfBodySent && (opCode & 128) != 0) {
                out.write(73);
                this.mEndOfBodySent = true;
            } else {
                out.write(72);
            }
            bodyLength += 3;
            out.write((byte) (bodyLength >> 8));
            out.write((byte) bodyLength);
            if (body != null) {
                out.write(body);
            }
        }
        if (this.mPrivateOutputOpen && bodyLength <= 0 && !this.mEndOfBodySent) {
            if ((opCode & 128) == 0) {
                out.write(72);
            } else {
                out.write(73);
                this.mEndOfBodySent = true;
            }
            out.write((byte) 0);
            out.write((byte) 3);
        }
        if (out.size() == 0) {
            if (!this.mParent.sendRequest(opCode, null, this.mReplyHeader, this.mPrivateInput, this.mSrmActive)) {
                return false;
            }
            checkForSrm();
            return returnValue;
        }
        if (out.size() > 0) {
            if (!this.mParent.sendRequest(opCode, out.toByteArray(), this.mReplyHeader, this.mPrivateInput, this.mSrmActive)) {
                return false;
            }
        }
        checkForSrm();
        if (this.mPrivateOutput != null && this.mPrivateOutput.size() > 0) {
            returnValue = true;
        }
        return returnValue;
    }

    private void checkForSrm() throws IOException {
        Byte srmMode = (Byte) this.mReplyHeader.getHeader(HeaderSet.SINGLE_RESPONSE_MODE);
        if (this.mParent.isSrmSupported() && srmMode != null && srmMode.byteValue() == 1) {
            this.mSrmEnabled = true;
        }
        if (this.mSrmEnabled) {
            this.mSrmWaitingForRemote = false;
            Byte srmp = (Byte) this.mReplyHeader.getHeader(HeaderSet.SINGLE_RESPONSE_MODE_PARAMETER);
            if (srmp != null && srmp.byteValue() == 1) {
                this.mSrmWaitingForRemote = true;
                this.mReplyHeader.setHeader(HeaderSet.SINGLE_RESPONSE_MODE_PARAMETER, null);
            }
        }
        if (!this.mSrmWaitingForRemote && this.mSrmEnabled) {
            this.mSrmActive = true;
        }
    }

    private synchronized void startProcessing() throws IOException {
        NfLog.d(this.TAG, "startProcessing ");
        if (this.mPrivateInput == null) {
            this.mPrivateInput = new PrivateInputStream(this);
        }
        boolean more = true;
        if (this.mGetOperation) {
            if (!this.mOperationDone) {
                if (!this.mGetFinalFlag) {
                    this.mReplyHeader.responseCode = ResponseCodes.OBEX_HTTP_CONTINUE;
                    while (more && this.mReplyHeader.responseCode == 144) {
                        NfLog.d(this.TAG, "startProcessing get loop");
                        more = sendRequest(3);
                    }
                    if (this.mReplyHeader.responseCode == 144) {
                        this.mParent.sendRequest(131, null, this.mReplyHeader, this.mPrivateInput, this.mSrmActive);
                    }
                    if (this.mReplyHeader.responseCode != 144) {
                        this.mOperationDone = true;
                    } else {
                        checkForSrm();
                    }
                } else if (sendRequest(131)) {
                    throw new IOException("FINAL_GET forced, data didn't fit into one packet");
                } else {
                    this.mOperationDone = true;
                }
            }
        } else {
            if (!this.mOperationDone) {
                this.mReplyHeader.responseCode = ResponseCodes.OBEX_HTTP_CONTINUE;
                while (more && this.mReplyHeader.responseCode == 144) {
                    NfLog.d(this.TAG, "startProcessing put loop");
                    more = sendRequest(2);
                }
            }
            if (this.mReplyHeader.responseCode == 144) {
                this.mParent.sendRequest(130, null, this.mReplyHeader, this.mPrivateInput, this.mSrmActive);
            }
            if (this.mReplyHeader.responseCode != 144) {
                this.mOperationDone = true;
            }
        }
        NfLog.d(this.TAG, "startProcessing end");
    }

    public void interruptObex() {
        if (this.mParent != null) {
            this.mParent.interruptObex();
        }
    }

    @Override // com.nforetek.bt.res.obex.BaseStream
    public synchronized boolean continueOperation(boolean sendEmpty, boolean inStream) throws IOException {
        boolean ret;
        NfLog.d(this.TAG, "continueOperation sendEmpty:" + sendEmpty + " inStream: " + inStream);
        ret = continueOperation(sendEmpty, inStream, 1);
        NfLog.d(this.TAG, "continueOperation end " + ret);
        return ret;
    }

    public synchronized boolean continueOperation(boolean sendEmpty, boolean inStream, int nn) throws IOException {
        boolean z = false;
        synchronized (this) {
            if (!NfPrimitive.isBtEnabled()) {
                NfLog.e(this.TAG, "Jade check : continueOperation - Adapter not on...");
            } else if (this.mGetOperation) {
                if (inStream && !this.mOperationDone) {
                    NfLog.d(this.TAG, "continueOperation inStream !done send OBEX_OPCODE_GET_FINAL");
                    this.mParent.sendRequest(131, null, this.mReplyHeader, this.mPrivateInput, this.mSrmActive);
                    if (this.mReplyHeader.responseCode != 144) {
                        this.mOperationDone = true;
                    } else {
                        checkForSrm();
                    }
                    z = true;
                } else if (!inStream && !this.mOperationDone) {
                    if (this.mPrivateInput == null) {
                        this.mPrivateInput = new PrivateInputStream(this);
                    }
                    if (!this.mGetFinalFlag) {
                        NfLog.d(this.TAG, "continueOperation outStream !done send OBEX_OPCODE_GET");
                        sendRequest(3);
                    } else {
                        NfLog.d(this.TAG, "continueOperation outStream !done send OBEX_OPCODE_GET_FINAL");
                        sendRequest(131);
                    }
                    if (this.mReplyHeader.responseCode != 144) {
                        this.mOperationDone = true;
                    }
                    z = true;
                } else if (this.mOperationDone) {
                }
            } else if (!inStream && !this.mOperationDone) {
                if (this.mReplyHeader.responseCode == -1) {
                    this.mReplyHeader.responseCode = ResponseCodes.OBEX_HTTP_CONTINUE;
                }
                NfLog.d(this.TAG, "continueOperation2 outStream !done send OBEX_OPCODE_PUT");
                sendRequest(2);
                z = true;
            } else if ((!inStream || this.mOperationDone) && this.mOperationDone) {
            }
        }
        return z;
    }

    @Override // com.nforetek.bt.res.obex.BaseStream
    public void streamClosed(boolean inStream) throws IOException {
        NfLog.d(this.TAG, "streamClosed isInStream " + inStream);
        if (!this.mGetOperation) {
            if (!inStream && !this.mOperationDone) {
                NfLog.d(this.TAG, "streamClosed outStream !mOperationDone");
                boolean more = true;
                if (this.mPrivateOutput != null && this.mPrivateOutput.size() <= 0) {
                    byte[] headerArray = ObexHelper.createHeader(this.mRequestHeader, false);
                    if (headerArray.length <= 0) {
                        more = false;
                    }
                }
                if (this.mReplyHeader.responseCode == -1) {
                    this.mReplyHeader.responseCode = ResponseCodes.OBEX_HTTP_CONTINUE;
                }
                for (int i = 0; i < this.streamCloseMaxSendCount; i++) {
                    boolean condition = more && this.mReplyHeader.responseCode == 144;
                    if (!condition) {
                        break;
                    }
                    NfLog.d(this.TAG, "streamClosed outStream !mOperationDone: loop send OBEX_OPCODE_PUT");
                    more = sendRequest(2);
                }
                for (int i2 = 0; i2 < this.streamCloseMaxSendCount; i2++) {
                    boolean condition2 = this.mReplyHeader.responseCode == 144;
                    if (!condition2) {
                        break;
                    }
                    NfLog.d(this.TAG, "streamClosed outStream !mOperationDone: loop send OBEX_OPCODE_PUT_FINAL");
                    sendRequest(130);
                }
                this.mOperationDone = true;
            } else if (inStream && this.mOperationDone) {
                NfLog.d(this.TAG, "streamClosed inStream !mOperationDone");
                this.mOperationDone = true;
            }
        } else if (inStream && !this.mOperationDone) {
            NfLog.d(this.TAG, "streamClosed2 inStream !mOperationDone");
            if (this.mReplyHeader.responseCode == -1) {
                this.mReplyHeader.responseCode = ResponseCodes.OBEX_HTTP_CONTINUE;
            }
            for (int i3 = 0; i3 < this.streamCloseMaxSendCount; i3++) {
                boolean condition3 = this.mReplyHeader.responseCode == 144 && !this.mOperationDone;
                if (!condition3) {
                    break;
                }
                NfLog.d(this.TAG, "streamClosed2 inStream !mOperationDone: loop send OBEX_OPCODE_PUT");
                if (!sendRequest(131)) {
                    break;
                }
            }
            for (int i4 = 0; i4 < this.streamCloseMaxSendCount; i4++) {
                boolean condition4 = this.mReplyHeader.responseCode == 144 && !this.mOperationDone;
                if (!condition4) {
                    break;
                }
                NfLog.d(this.TAG, "streamClosed2 inStream !mOperationDone: loop send OBEX_OPCODE_PUT_FINAL");
                this.mParent.sendRequest(131, null, this.mReplyHeader, this.mPrivateInput, false);
            }
            this.mOperationDone = true;
        } else if (!inStream && !this.mOperationDone) {
            boolean more2 = true;
            if (this.mPrivateOutput != null && this.mPrivateOutput.size() <= 0) {
                byte[] headerArray2 = ObexHelper.createHeader(this.mRequestHeader, false);
                if (headerArray2.length <= 0) {
                    more2 = false;
                }
            }
            if (this.mPrivateInput == null) {
                this.mPrivateInput = new PrivateInputStream(this);
            }
            if (this.mPrivateOutput != null && this.mPrivateOutput.size() <= 0) {
                more2 = false;
            }
            this.mReplyHeader.responseCode = ResponseCodes.OBEX_HTTP_CONTINUE;
            for (int i5 = 0; i5 < this.streamCloseMaxSendCount; i5++) {
                boolean condition5 = more2 && this.mReplyHeader.responseCode == 144;
                if (!condition5) {
                    break;
                }
                NfLog.d(this.TAG, "streamClosed2 outStream !mOperationDone: loop send OBEX_OPCODE_GET");
                more2 = sendRequest(3);
            }
            sendRequest(131);
            if (this.mReplyHeader.responseCode != 144) {
                this.mOperationDone = true;
            }
        }
        NfLog.d(this.TAG, "streamClosed end");
    }

    @Override // com.nforetek.bt.res.obex.Operation
    public void noBodyHeader() {
        this.mSendBodyHeader = false;
    }
}
