package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.utils.ObexAppParameters;
import com.nforetek.bt.res.obex.ClientSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BluetoothMasRequestGetFolderListing extends BluetoothMasRequest {
    private static final String TYPE = "x-obex/folder-listing";
    private BluetoothMapFolderListing mResponse = null;

    public BluetoothMasRequestGetFolderListing(int maxListCount, int listStartOffset) {
        if (maxListCount < 0 || maxListCount > 65535) {
            throw new IllegalArgumentException("maxListCount should be [0..65535]");
        }
        if (listStartOffset < 0 || listStartOffset > 65535) {
            throw new IllegalArgumentException("listStartOffset should be [0..65535]");
        }
        this.mHeaderSet.setHeader(66, TYPE);
        ObexAppParameters oap = new ObexAppParameters();
        if (maxListCount >= 0) {
            oap.add((byte) 1, (short) maxListCount);
        }
        if (listStartOffset > 0) {
            oap.add((byte) 2, (short) listStartOffset);
        }
        oap.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    protected void readResponse(InputStream stream) {
        this.mResponse = new BluetoothMapFolderListing(stream);
    }

    public ArrayList<String> getList() {
        if (this.mResponse == null) {
            return null;
        }
        return this.mResponse.getList();
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    public void execute(ClientSession session) throws IOException {
        executeGet(session);
    }
}
