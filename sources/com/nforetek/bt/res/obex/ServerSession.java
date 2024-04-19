package com.nforetek.bt.res.obex;

import android.util.Log;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes.dex */
public final class ServerSession extends ObexSession implements Runnable {
    private static final boolean DBG = true;
    private static final String TAG = "Obex ServerSession";
    private boolean mClosed;
    private InputStream mInput;
    private ServerRequestHandler mListener;
    private int mMaxPacketLength;
    private OutputStream mOutput;
    private Thread mProcessThread;
    private ObexTransport mTransport;

    public ServerSession(ObexTransport trans, ServerRequestHandler handler, Authenticator auth) throws IOException {
        this.mAuthenticator = auth;
        this.mTransport = trans;
        this.mInput = this.mTransport.openInputStream();
        this.mOutput = this.mTransport.openOutputStream();
        this.mListener = handler;
        this.mMaxPacketLength = 256;
        this.mClosed = false;
        this.mProcessThread = new Thread(this);
        this.mProcessThread.start();
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean done = false;
        while (!done) {
            try {
            } catch (NullPointerException e) {
                Log.d(TAG, "Exception occured - ignoring", e);
            } catch (Exception e2) {
                Log.d(TAG, "Exception occured - ignoring", e2);
            }
            if (!this.mClosed) {
                NfLog.d(true, TAG, "Waiting for incoming request...");
                int requestType = this.mInput.read();
                NfLog.d(true, TAG, "Read request: " + requestType);
                switch (requestType) {
                    case -1:
                        done = true;
                        break;
                    case 2:
                    case 130:
                        handlePutRequest(requestType);
                        break;
                    case 3:
                    case 131:
                        handleGetRequest(requestType);
                        break;
                    case 128:
                        handleConnectRequest();
                        break;
                    case 129:
                        handleDisconnectRequest();
                        done = true;
                        break;
                    case 133:
                        handleSetPathRequest();
                        break;
                    case 255:
                        handleAbortRequest();
                        break;
                    default:
                        int length = this.mInput.read();
                        int length2 = (length << 8) + this.mInput.read();
                        for (int i = 3; i < length2; i++) {
                            this.mInput.read();
                        }
                        sendResponse(209, null);
                        break;
                }
            } else {
                close();
            }
        }
        close();
    }

    private void handleAbortRequest() throws IOException {
        int code;
        HeaderSet request = new HeaderSet();
        HeaderSet reply = new HeaderSet();
        int length = (this.mInput.read() << 8) + this.mInput.read();
        if (length > ObexHelper.getMaxRxPacketSize(this.mTransport)) {
            code = 206;
        } else {
            for (int i = 3; i < length; i++) {
                this.mInput.read();
            }
            int code2 = this.mListener.onAbort(request, reply);
            Log.v(TAG, "onAbort request handler return value- " + code2);
            code = validateResponseCode(code2);
        }
        sendResponse(code, null);
    }

    private void handlePutRequest(int type) throws IOException {
        int response;
        ServerOperation op = new ServerOperation(this, this.mInput, type, this.mMaxPacketLength, this.mListener);
        try {
            if (op.finalBitSet && !op.isValidBody()) {
                if (NfConfig.isPtsTest()) {
                    response = 160;
                } else {
                    response = validateResponseCode(this.mListener.onDelete(op.requestHeader, op.replyHeader));
                }
            } else {
                response = validateResponseCode(this.mListener.onPut(op));
            }
            if (response != 160 && !op.isAborted) {
                op.sendReply(response);
            } else if (!op.isAborted) {
                while (!op.finalBitSet) {
                    op.sendReply(ResponseCodes.OBEX_HTTP_CONTINUE);
                }
                op.sendReply(response);
            }
        } catch (Exception e) {
            NfLog.e(TAG, "Exception occured - sending OBEX_HTTP_INTERNAL_ERROR reply", e);
            if (!op.isAborted) {
                sendResponse(208, null);
            }
        }
    }

    private void handleGetRequest(int type) throws IOException {
        ServerOperation op = new ServerOperation(this, this.mInput, type, this.mMaxPacketLength, this.mListener);
        try {
            int response = validateResponseCode(this.mListener.onGet(op));
            if (!op.isAborted) {
                op.sendReply(response);
            }
        } catch (Exception e) {
            NfLog.e(TAG, "Exception occured - sending OBEX_HTTP_INTERNAL_ERROR reply", e);
            sendResponse(208, null);
        }
    }

    public void sendResponse(int code, byte[] header) throws IOException {
        byte[] data;
        OutputStream op = this.mOutput;
        if (op != null) {
            if (header != null) {
                int totalLength = 3 + header.length;
                data = new byte[totalLength];
                data[0] = (byte) code;
                data[1] = (byte) (totalLength >> 8);
                data[2] = (byte) totalLength;
                System.arraycopy(header, 0, data, 3, header.length);
            } else {
                data = new byte[]{(byte) code, 0, (byte) 3};
            }
            op.write(data);
            op.flush();
        }
    }

    private void handleSetPathRequest() throws IOException {
        int totalLength = 3;
        byte[] head = null;
        int code = -1;
        HeaderSet request = new HeaderSet();
        HeaderSet reply = new HeaderSet();
        int length = (this.mInput.read() << 8) + this.mInput.read();
        int flags = this.mInput.read();
        this.mInput.read();
        if (length > ObexHelper.getMaxRxPacketSize(this.mTransport)) {
            code = 206;
            totalLength = 3;
        } else {
            if (length > 5) {
                byte[] headers = new byte[length - 5];
                int bytesReceived = this.mInput.read(headers);
                while (bytesReceived != headers.length) {
                    bytesReceived += this.mInput.read(headers, bytesReceived, headers.length - bytesReceived);
                }
                ObexHelper.updateHeaderSet(request, headers);
                if (this.mListener.getConnectionId() != -1 && request.mConnectionID != null) {
                    this.mListener.setConnectionId(ObexHelper.convertToLong(request.mConnectionID));
                } else {
                    this.mListener.setConnectionId(1L);
                }
                if (request.mAuthResp != null) {
                    if (!handleAuthResp(request.mAuthResp)) {
                        code = ResponseCodes.OBEX_HTTP_UNAUTHORIZED;
                        this.mListener.onAuthenticationFailure(ObexHelper.getTagValue((byte) 1, request.mAuthResp));
                    }
                    request.mAuthResp = null;
                }
            }
            if (code != 193) {
                if (request.mAuthChall != null) {
                    handleAuthChall(request);
                    reply.mAuthResp = new byte[request.mAuthResp.length];
                    System.arraycopy(request.mAuthResp, 0, reply.mAuthResp, 0, reply.mAuthResp.length);
                    request.mAuthChall = null;
                    request.mAuthResp = null;
                }
                boolean backup = false;
                boolean create = true;
                if ((flags & 1) != 0) {
                    backup = true;
                }
                if ((flags & 2) != 0) {
                    create = false;
                }
                try {
                    code = validateResponseCode(this.mListener.onSetPath(request, reply, backup, create));
                    if (reply.nonce != null) {
                        this.mChallengeDigest = new byte[16];
                        System.arraycopy(reply.nonce, 0, this.mChallengeDigest, 0, 16);
                    } else {
                        this.mChallengeDigest = null;
                    }
                    long id = this.mListener.getConnectionId();
                    if (id == -1) {
                        reply.mConnectionID = null;
                    } else {
                        reply.mConnectionID = ObexHelper.convertToByteArray(id);
                    }
                    head = ObexHelper.createHeader(reply, false);
                    totalLength = 3 + head.length;
                    if (totalLength > this.mMaxPacketLength) {
                        totalLength = 3;
                        head = null;
                        code = 208;
                    }
                } catch (Exception e) {
                    NfLog.e(TAG, "Exception occured - sending OBEX_HTTP_INTERNAL_ERROR reply", e);
                    sendResponse(208, null);
                    return;
                }
            }
        }
        byte[] replyData = new byte[totalLength];
        replyData[0] = (byte) code;
        replyData[1] = (byte) (totalLength >> 8);
        replyData[2] = (byte) totalLength;
        if (head != null) {
            System.arraycopy(head, 0, replyData, 3, head.length);
        }
        this.mOutput.write(replyData);
        this.mOutput.flush();
    }

    private void handleDisconnectRequest() throws IOException {
        byte[] replyData;
        int code = 160;
        int totalLength = 3;
        byte[] head = null;
        HeaderSet request = new HeaderSet();
        HeaderSet reply = new HeaderSet();
        int length = (this.mInput.read() << 8) + this.mInput.read();
        if (length > ObexHelper.getMaxRxPacketSize(this.mTransport)) {
            code = 206;
            totalLength = 3;
        } else {
            if (length > 3) {
                byte[] headers = new byte[length - 3];
                int bytesReceived = this.mInput.read(headers);
                while (bytesReceived != headers.length) {
                    bytesReceived += this.mInput.read(headers, bytesReceived, headers.length - bytesReceived);
                }
                ObexHelper.updateHeaderSet(request, headers);
            }
            if (this.mListener.getConnectionId() != -1 && request.mConnectionID != null) {
                this.mListener.setConnectionId(ObexHelper.convertToLong(request.mConnectionID));
            } else {
                this.mListener.setConnectionId(1L);
            }
            if (request.mAuthResp != null) {
                if (!handleAuthResp(request.mAuthResp)) {
                    code = ResponseCodes.OBEX_HTTP_UNAUTHORIZED;
                    this.mListener.onAuthenticationFailure(ObexHelper.getTagValue((byte) 1, request.mAuthResp));
                }
                request.mAuthResp = null;
            }
            if (code != 193) {
                if (request.mAuthChall != null) {
                    handleAuthChall(request);
                    request.mAuthChall = null;
                }
                try {
                    this.mListener.onDisconnect(request, reply);
                    long id = this.mListener.getConnectionId();
                    if (id == -1) {
                        reply.mConnectionID = null;
                    } else {
                        reply.mConnectionID = ObexHelper.convertToByteArray(id);
                    }
                    head = ObexHelper.createHeader(reply, false);
                    totalLength = 3 + head.length;
                    if (totalLength > this.mMaxPacketLength) {
                        totalLength = 3;
                        head = null;
                        code = 208;
                    }
                } catch (Exception e) {
                    NfLog.e(TAG, "Exception occured - sending OBEX_HTTP_INTERNAL_ERROR reply", e);
                    sendResponse(208, null);
                    return;
                }
            }
        }
        if (head != null) {
            replyData = new byte[head.length + 3];
        } else {
            replyData = new byte[3];
        }
        replyData[0] = (byte) code;
        replyData[1] = (byte) (totalLength >> 8);
        replyData[2] = (byte) totalLength;
        if (head != null) {
            System.arraycopy(head, 0, replyData, 3, head.length);
        }
        this.mOutput.write(replyData);
        this.mOutput.flush();
    }

    private void handleConnectRequest() throws IOException {
        int totalLength = 7;
        byte[] head = null;
        int code = -1;
        HeaderSet request = new HeaderSet();
        HeaderSet reply = new HeaderSet();
        NfLog.d(true, TAG, "handleConnectRequest()");
        int packetLength = (this.mInput.read() << 8) + this.mInput.read();
        NfLog.d(true, TAG, "handleConnectRequest() - packetLength: " + packetLength);
        int version = this.mInput.read();
        int flags = this.mInput.read();
        this.mMaxPacketLength = this.mInput.read();
        this.mMaxPacketLength = (this.mMaxPacketLength << 8) + this.mInput.read();
        NfLog.d(true, TAG, "handleConnectRequest() - version: " + version + " MaxLength: " + this.mMaxPacketLength + " flags: " + flags);
        if (this.mMaxPacketLength > 65534) {
            this.mMaxPacketLength = ObexHelper.MAX_PACKET_SIZE_INT;
        }
        if (this.mMaxPacketLength > ObexHelper.getMaxTxPacketSize(this.mTransport)) {
            Log.w(TAG, "Requested MaxObexPacketSize " + this.mMaxPacketLength + " is larger than the max size supported by the transport: " + ObexHelper.getMaxTxPacketSize(this.mTransport) + " Reducing to this size.");
            this.mMaxPacketLength = ObexHelper.getMaxTxPacketSize(this.mTransport);
        }
        if (packetLength > ObexHelper.getMaxRxPacketSize(this.mTransport)) {
            code = 206;
            totalLength = 7;
        } else {
            if (packetLength > 7) {
                byte[] headers = new byte[packetLength - 7];
                int bytesReceived = this.mInput.read(headers);
                while (bytesReceived != headers.length) {
                    bytesReceived += this.mInput.read(headers, bytesReceived, headers.length - bytesReceived);
                }
                ObexHelper.updateHeaderSet(request, headers);
            }
            if (this.mListener.getConnectionId() != -1 && request.mConnectionID != null) {
                this.mListener.setConnectionId(ObexHelper.convertToLong(request.mConnectionID));
            } else {
                this.mListener.setConnectionId(1L);
            }
            if (request.mAuthResp != null) {
                if (!handleAuthResp(request.mAuthResp)) {
                    code = ResponseCodes.OBEX_HTTP_UNAUTHORIZED;
                    this.mListener.onAuthenticationFailure(ObexHelper.getTagValue((byte) 1, request.mAuthResp));
                }
                request.mAuthResp = null;
            }
            if (code != 193) {
                if (request.mAuthChall != null) {
                    handleAuthChall(request);
                    reply.mAuthResp = new byte[request.mAuthResp.length];
                    System.arraycopy(request.mAuthResp, 0, reply.mAuthResp, 0, reply.mAuthResp.length);
                    request.mAuthChall = null;
                    request.mAuthResp = null;
                }
                try {
                    code = validateResponseCode(this.mListener.onConnect(request, reply));
                    if (reply.nonce != null) {
                        this.mChallengeDigest = new byte[16];
                        System.arraycopy(reply.nonce, 0, this.mChallengeDigest, 0, 16);
                    } else {
                        this.mChallengeDigest = null;
                    }
                    long id = this.mListener.getConnectionId();
                    if (id == -1) {
                        reply.mConnectionID = null;
                    } else {
                        reply.mConnectionID = ObexHelper.convertToByteArray(id);
                    }
                    head = ObexHelper.createHeader(reply, false);
                    totalLength = 7 + head.length;
                    if (totalLength > this.mMaxPacketLength) {
                        totalLength = 7;
                        head = null;
                        code = 208;
                    }
                } catch (Exception e) {
                    NfLog.e(TAG, "Exception occured - sending OBEX_HTTP_INTERNAL_ERROR reply", e);
                    totalLength = 7;
                    head = null;
                    code = 208;
                }
            }
        }
        byte[] length = ObexHelper.convertToByteArray(totalLength);
        byte[] sendData = new byte[totalLength];
        int maxRxLength = ObexHelper.getMaxRxPacketSize(this.mTransport);
        sendData[0] = (byte) code;
        sendData[1] = length[2];
        sendData[2] = length[3];
        sendData[3] = NfDef.AVRCP_BROWSING_STATUS_SEARCH_NOT_SUPPORT;
        sendData[4] = 0;
        sendData[5] = (byte) (maxRxLength >> 8);
        sendData[6] = (byte) (maxRxLength & 255);
        if (head != null) {
            System.arraycopy(head, 0, sendData, 7, head.length);
        }
        this.mOutput.write(sendData);
        this.mOutput.flush();
    }

    public synchronized void close() {
        if (this.mListener != null) {
            this.mListener.onClose();
        }
        try {
            this.mClosed = true;
            if (this.mInput != null) {
                this.mInput.close();
            }
            if (this.mOutput != null) {
                this.mOutput.close();
            }
            if (this.mTransport != null) {
                this.mTransport.close();
            }
        } catch (Exception e) {
            NfLog.e(TAG, "Exception occured during close() - ignore", e);
        }
        this.mTransport = null;
        this.mInput = null;
        this.mOutput = null;
        this.mListener = null;
    }

    private int validateResponseCode(int code) {
        if (code < 160 || code > 166) {
            if (code < 176 || code > 181) {
                if (code < 192 || code > 207) {
                    if (code < 208 || code > 213) {
                        if (code < 224 || code > 225) {
                            return 208;
                        }
                        return code;
                    }
                    return code;
                }
                return code;
            }
            return code;
        }
        return code;
    }

    public ObexTransport getTransport() {
        return this.mTransport;
    }
}
