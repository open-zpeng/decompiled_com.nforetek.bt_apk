package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.utils.ObexAppParameters;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.obex.ClientSession;
import com.nforetek.bt.res.obex.HeaderSet;
import java.io.IOException;
/* loaded from: classes.dex */
final class BluetoothMasRequestGetFolderListingSize extends BluetoothMasRequest {
    private static final String TYPE = "x-obex/folder-listing";
    private int mSize;

    public BluetoothMasRequestGetFolderListingSize() {
        this.mHeaderSet.setHeader(66, TYPE);
        ObexAppParameters oap = new ObexAppParameters();
        oap.add((byte) 1, 0);
        oap.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    protected void readResponseHeaders(HeaderSet headerset) {
        ObexAppParameters oap = ObexAppParameters.fromHeaderSet(headerset);
        this.mSize = oap.getShort(NfDef.AVRCP_BROWSING_STATUS_SEARCH_IN_PROGRESS);
    }

    public int getSize() {
        return this.mSize;
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    public void execute(ClientSession session) throws IOException {
        executeGet(session);
    }
}
