package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.res.obex.ClientOperation;
import com.nforetek.bt.res.obex.ClientSession;
import com.nforetek.bt.res.obex.HeaderSet;
import com.nforetek.bt.res.obex.Operation;
import com.nforetek.util.normal.NfLog;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class BluetoothMasRequest {
    protected static final byte OAP_TAGID_ATTACHMENT = 10;
    protected static final byte OAP_TAGID_CHARSET = 20;
    protected static final byte OAP_TAGID_FILTER_MESSAGE_TYPE = 3;
    protected static final byte OAP_TAGID_FILTER_ORIGINATOR = 8;
    protected static final byte OAP_TAGID_FILTER_PERIOD_BEGIN = 4;
    protected static final byte OAP_TAGID_FILTER_PERIOD_END = 5;
    protected static final byte OAP_TAGID_FILTER_PRIORITY = 9;
    protected static final byte OAP_TAGID_FILTER_READ_STATUS = 6;
    protected static final byte OAP_TAGID_FILTER_RECIPIENT = 7;
    protected static final byte OAP_TAGID_FOLDER_LISTING_SIZE = 17;
    protected static final byte OAP_TAGID_MAS_INSTANCE_ID = 15;
    protected static final byte OAP_TAGID_MAX_LIST_COUNT = 1;
    protected static final byte OAP_TAGID_MESSAGES_LISTING_SIZE = 18;
    protected static final byte OAP_TAGID_MSE_TIME = 25;
    protected static final byte OAP_TAGID_NEW_MESSAGE = 13;
    protected static final byte OAP_TAGID_NOTIFICATION_STATUS = 14;
    protected static final byte OAP_TAGID_PARAMETER_MASK = 16;
    protected static final byte OAP_TAGID_RETRY = 12;
    protected static final byte OAP_TAGID_START_OFFSET = 2;
    protected static final byte OAP_TAGID_STATUS_INDICATOR = 23;
    protected static final byte OAP_TAGID_STATUS_VALUE = 24;
    protected static final byte OAP_TAGID_SUBJECT_LENGTH = 19;
    protected static final byte OAP_TAGID_TRANSPARENT = 11;
    private static String TAG = "MasRequest";
    protected static byte NOTIFICATION_ON = 1;
    protected static byte NOTIFICATION_OFF = 0;
    protected static byte ATTACHMENT_ON = 1;
    protected static byte ATTACHMENT_OFF = 0;
    protected static byte CHARSET_NATIVE = 0;
    protected static byte CHARSET_UTF8 = 1;
    protected static byte STATUS_INDICATOR_READ = 0;
    protected static byte STATUS_INDICATOR_DELETED = 1;
    protected static byte STATUS_NO = 0;
    protected static byte STATUS_YES = 1;
    protected static byte TRANSPARENT_OFF = 0;
    protected static byte TRANSPARENT_ON = 1;
    protected static byte RETRY_OFF = 0;
    protected static byte RETRY_ON = 1;
    protected static final byte[] FILLER_BYTE = {48};
    protected int mResponseCode = -1;
    ClientOperation op = null;
    InputStream is = null;
    private int mdownloadInterrupt = 0;
    protected HeaderSet mHeaderSet = new HeaderSet();

    public abstract void execute(ClientSession clientSession) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public void executeGet(ClientSession session) throws IOException {
        try {
            this.op = (ClientOperation) session.get(this.mHeaderSet);
            this.op.setGetFinalFlag(true);
            if (!isInterrupted()) {
                this.op.continueOperation(true, false);
                this.is = this.op.openInputStream();
                readResponse(this.is);
                if (this.op.getOperationDone()) {
                    NfLog.d(TAG, "obexget mOperationDone true ");
                } else {
                    NfLog.e(TAG, "obexget mOperationDone not done ");
                }
                NfLog.d(TAG, "obexget op.getHeaderLength() " + this.op.getHeaderLength());
                NfLog.d(TAG, "obexget op.getLength() " + this.op.getLength());
                if (!isInterrupted()) {
                    readResponseHeaders(this.op.getReceivedHeader());
                    this.mResponseCode = this.op.getResponseCode();
                    this.is.close();
                    this.op.close();
                }
            }
        } catch (IOException e) {
            this.mResponseCode = 208;
            NfLog.e(TAG, "executeGet got exception: ", e);
            if (!isInterrupted()) {
                throw e;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void executePut(ClientSession session, byte[] body) throws IOException {
        this.mHeaderSet.setHeader(195, Long.valueOf(body.length));
        try {
            Operation op = session.put(this.mHeaderSet);
            DataOutputStream out = op.openDataOutputStream();
            out.write(body);
            out.close();
            readResponseHeaders(op.getReceivedHeader());
            op.close();
            this.mResponseCode = op.getResponseCode();
        } catch (IOException e) {
            NfLog.e(TAG, "executePut got exception: ", e);
            this.mResponseCode = 208;
            throw e;
        }
    }

    public boolean abort() {
        NfLog.d(TAG, "try to abort!!");
        this.mdownloadInterrupt = 1;
        try {
            if (this.op != null) {
                this.op.abort();
                return true;
            }
        } catch (Exception e) {
            NfLog.e(TAG, "mas abort got exception: ", e);
        }
        return false;
    }

    public int isOut() {
        return this.mdownloadInterrupt;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isInterrupted() {
        if (this.mdownloadInterrupt != 0) {
            NfLog.d(TAG, "downloadInterrupt detected");
            try {
                if (this.is != null) {
                    this.is.close();
                }
                if (this.op != null) {
                    this.op.close();
                }
            } catch (Exception e) {
                NfLog.e(TAG, "mas isInterrupted got exception: ", e);
            }
            return true;
        }
        return false;
    }

    public HeaderSet getHeaderSet() {
        try {
            if (this.op != null) {
                return this.op.getReceivedHeader();
            }
        } catch (Exception e) {
            NfLog.e(TAG, "mas getHeaderSet got exception: ", e);
        }
        return null;
    }

    public final boolean isSuccess() {
        return this.mResponseCode == 160;
    }

    protected void readResponse(InputStream stream) throws IOException {
    }

    protected void readResponseHeaders(HeaderSet headerset) {
    }
}
