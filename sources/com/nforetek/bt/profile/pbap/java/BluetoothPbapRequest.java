package com.nforetek.bt.profile.pbap.java;

import com.nforetek.bt.res.obex.ClientOperation;
import com.nforetek.bt.res.obex.ClientSession;
import com.nforetek.bt.res.obex.HeaderSet;
import com.nforetek.util.normal.NfLog;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
abstract class BluetoothPbapRequest {
    protected static final byte OAP_TAGID_FILTER = 6;
    protected static final byte OAP_TAGID_FORMAT = 7;
    protected static final byte OAP_TAGID_LIST_START_OFFSET = 5;
    protected static final byte OAP_TAGID_MAX_LIST_COUNT = 4;
    protected static final byte OAP_TAGID_NEW_MISSED_CALLS = 9;
    protected static final byte OAP_TAGID_ORDER = 1;
    protected static final byte OAP_TAGID_PBAP_SUPPORTED_FEATURES = 16;
    protected static final byte OAP_TAGID_PHONEBOOK_SIZE = 8;
    protected static final byte OAP_TAGID_SEARCH_ATTRIBUTE = 3;
    protected static final byte OAP_TAGID_SEARCH_VALUE = 2;
    private static final String TAG = "BluetoothPbapRequest";
    protected int mResponseCode;
    private boolean mAborted = false;
    private ClientOperation mOp = null;
    protected HeaderSet mHeaderSet = new HeaderSet();

    public final boolean isSuccess() {
        return this.mResponseCode == 160;
    }

    public void execute(ClientSession session) throws IOException {
        NfLog.v(TAG, "execute");
        if (this.mAborted) {
            NfLog.v(TAG, "in case request is aborted before can be executed");
            this.mResponseCode = 208;
            return;
        }
        try {
            this.mOp = (ClientOperation) session.get(this.mHeaderSet);
            this.mOp.setGetFinalFlag(true);
            this.mOp.continueOperation(true, false);
            readResponseHeaders(this.mOp.getReceivedHeader());
            InputStream is = this.mOp.openInputStream();
            readResponse(is);
            is.close();
            this.mOp.close();
            this.mResponseCode = this.mOp.getResponseCode();
            NfLog.d(TAG, "mResponseCode=" + this.mResponseCode);
            checkResponseCode(this.mResponseCode);
        } catch (IOException e) {
            NfLog.e(TAG, "IOException occured when processing request", e);
            this.mResponseCode = 208;
            throw e;
        }
    }

    public void abort() {
        this.mAborted = true;
        if (this.mOp != null) {
            try {
                this.mOp.abort();
            } catch (IOException e) {
                NfLog.e(TAG, "Exception occured when trying to abort", e);
            }
        }
    }

    protected void readResponse(InputStream stream) throws IOException {
        NfLog.v(TAG, "readResponse");
    }

    protected void readResponseHeaders(HeaderSet headerset) {
        NfLog.v(TAG, "readResponseHeaders");
    }

    protected void checkResponseCode(int responseCode) throws IOException {
        NfLog.v(TAG, "checkResponseCode");
    }

    public void interruptObex() {
        if (this.mOp != null) {
            this.mOp.interruptObex();
        }
    }
}
