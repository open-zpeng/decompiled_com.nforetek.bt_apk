package com.nforetek.bt.profile.map.java;

import android.os.Handler;
import com.nforetek.bt.profile.map.java.utils.ObexAppParameters;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.obex.HeaderSet;
import com.nforetek.bt.res.obex.ObexServerSockets;
import com.nforetek.bt.res.obex.Operation;
import com.nforetek.bt.res.obex.ResponseCodes;
import com.nforetek.bt.res.obex.ServerRequestHandler;
import com.nforetek.util.normal.NfLog;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes.dex */
class MnsObexServer extends ServerRequestHandler {
    private static final byte[] MNS_TARGET = {-69, 88, 43, 65, 66, 12, NfDef.AVRCP_BROWSING_STATUS_SEARCH_IN_PROGRESS, -37, -80, -34, 8, 0, 32, 12, -102, 102};
    private static final String TAG = "MnsObexServer";
    private static final String TYPE = "x-bt/MAP-event-report";
    private boolean DBG = BluetoothMasClient.DBG.booleanValue();
    private final Handler mCallback;
    private final ObexServerSockets mObexServerSockets;

    public MnsObexServer(Handler callback, ObexServerSockets socketOriginator) {
        this.mObexServerSockets = socketOriginator;
        this.mCallback = callback;
    }

    @Override // com.nforetek.bt.res.obex.ServerRequestHandler
    public int onConnect(HeaderSet request, HeaderSet reply) {
        NfLog.d(this.DBG, TAG, "onConnect");
        NfLog.d(this.DBG, TAG, "MAP MNS onConnect");
        this.mObexServerSockets.prepareForNewConnect();
        try {
            byte[] uuid = (byte[]) request.getHeader(70);
            if (!Arrays.equals(uuid, MNS_TARGET)) {
                return ResponseCodes.OBEX_HTTP_NOT_ACCEPTABLE;
            }
            try {
                this.mCallback.obtainMessage(2, null).sendToTarget();
            } catch (Exception e) {
                NfLog.e(TAG, "onConnect without callback handler", e);
            }
            reply.setHeader(74, MNS_TARGET);
            return 160;
        } catch (IOException e2) {
            return 208;
        }
    }

    @Override // com.nforetek.bt.res.obex.ServerRequestHandler
    public void onDisconnect(HeaderSet request, HeaderSet reply) {
        NfLog.d(this.DBG, TAG, "onDisconnect");
        try {
            this.mCallback.obtainMessage(3, null).sendToTarget();
        } catch (Exception e) {
            NfLog.e(TAG, "onDisconnect without callback handler", e);
        }
    }

    @Override // com.nforetek.bt.res.obex.ServerRequestHandler
    public int onGet(Operation op) {
        NfLog.d(this.DBG, TAG, "onGet");
        return 192;
    }

    @Override // com.nforetek.bt.res.obex.ServerRequestHandler
    public int onPut(Operation op) {
        NfLog.d(this.DBG, TAG, "onPut");
        try {
            HeaderSet headerset = op.getReceivedHeader();
            String type = (String) headerset.getHeader(66);
            ObexAppParameters oap = ObexAppParameters.fromHeaderSet(headerset);
            if (!TYPE.equals(type) || !oap.exists((byte) 15)) {
                return 192;
            }
            Byte inst = Byte.valueOf(oap.getByte((byte) 15));
            BluetoothMapEventReport ev = BluetoothMapEventReport.fromStream(op.openDataInputStream());
            op.close();
            if (this.mCallback != null) {
                this.mCallback.obtainMessage(1, inst.byteValue(), 0, ev).sendToTarget();
            }
            return 160;
        } catch (IOException e) {
            NfLog.e(TAG, "I/O exception when handling PUT request", e);
            return 208;
        }
    }

    @Override // com.nforetek.bt.res.obex.ServerRequestHandler
    public int onAbort(HeaderSet request, HeaderSet reply) {
        NfLog.d(this.DBG, TAG, "onAbort");
        return 209;
    }

    @Override // com.nforetek.bt.res.obex.ServerRequestHandler
    public int onSetPath(HeaderSet request, HeaderSet reply, boolean backup, boolean create) {
        NfLog.d(this.DBG, TAG, "onSetPath");
        return 192;
    }

    @Override // com.nforetek.bt.res.obex.ServerRequestHandler
    public void onClose() {
        NfLog.d(this.DBG, TAG, "onClose");
        try {
            this.mCallback.obtainMessage(1, 0, 1).sendToTarget();
        } catch (Exception e) {
            NfLog.e(TAG, "onClose without callback handler", e);
        }
    }
}
