package com.nforetek.bt.res.obex;
/* loaded from: classes.dex */
public class ServerRequestHandler {
    private long mConnectionId = -1;

    public void setConnectionId(long connectionId) {
        if (connectionId < -1 || connectionId > 4294967295L) {
            throw new IllegalArgumentException("Illegal Connection ID");
        }
        this.mConnectionId = connectionId;
    }

    public long getConnectionId() {
        return this.mConnectionId;
    }

    public int onConnect(HeaderSet request, HeaderSet reply) {
        return 160;
    }

    public void onDisconnect(HeaderSet request, HeaderSet reply) {
    }

    public int onSetPath(HeaderSet request, HeaderSet reply, boolean backup, boolean create) {
        return 209;
    }

    public int onDelete(HeaderSet request, HeaderSet reply) {
        return 209;
    }

    public int onAbort(HeaderSet request, HeaderSet reply) {
        return 209;
    }

    public int onPut(Operation operation) {
        return 209;
    }

    public int onGet(Operation operation) {
        return 209;
    }

    public void onAuthenticationFailure(byte[] userName) {
    }

    public void updateStatus(String message) {
    }

    public void onClose() {
    }

    public boolean isSrmSupported() {
        return false;
    }
}
