package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.utils.ObexAppParameters;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.obex.ClientSession;
import com.nforetek.bt.res.obex.HeaderSet;
import com.nforetek.util.normal.NfLog;
import java.io.IOException;
/* loaded from: classes.dex */
final class BluetoothMasRequestGetMessagesListingSize extends BluetoothMasRequest {
    private static final String TAG = "ReqListSize";
    private static final String TYPE = "x-bt/MAP-msg-listing";
    private int mSize;

    public BluetoothMasRequestGetMessagesListingSize() {
        this.mHeaderSet.setHeader(1, "");
        this.mHeaderSet.setHeader(66, TYPE);
        ObexAppParameters oap = new ObexAppParameters();
        oap.add((byte) 1, (short) 0);
        oap.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    protected void readResponseHeaders(HeaderSet headerset) {
        ObexAppParameters oap = ObexAppParameters.fromHeaderSet(headerset);
        NfLog.d(TAG, oap.toString());
        this.mSize = oap.getShort(NfDef.AVRCP_BROWSING_STATUS_INVALID_PLAYER_ID);
    }

    public int getSize() {
        return this.mSize;
    }

    public int getSize2() {
        NfLog.d(TAG, "getSize2");
        HeaderSet s = getHeaderSet();
        if (s == null) {
            NfLog.d(TAG, "getHeaderSet null");
        }
        NfLog.d(TAG, s.toString());
        ObexAppParameters oap = ObexAppParameters.fromHeaderSet(s);
        NfLog.d(TAG, oap.toString());
        this.mSize = oap.getShort(NfDef.AVRCP_BROWSING_STATUS_INVALID_PLAYER_ID);
        return this.mSize;
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    public void execute(ClientSession session) throws IOException {
        executeGet(session);
    }
}
