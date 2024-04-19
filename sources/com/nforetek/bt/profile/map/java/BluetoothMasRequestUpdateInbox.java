package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.res.obex.ClientSession;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BluetoothMasRequestUpdateInbox extends BluetoothMasRequest {
    private static final String TYPE = "x-bt/MAP-messageUpdate";

    public BluetoothMasRequestUpdateInbox() {
        this.mHeaderSet.setHeader(66, TYPE);
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    public void execute(ClientSession session) throws IOException {
        executePut(session, FILLER_BYTE);
    }
}
