package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.utils.ObexAppParameters;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.obex.ClientSession;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BluetoothMasRequestSetMessageStatus extends BluetoothMasRequest {
    private static final String TYPE = "x-bt/messageStatus";

    /* loaded from: classes.dex */
    public enum StatusIndicator {
        READ,
        DELETED;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static StatusIndicator[] valuesCustom() {
            StatusIndicator[] valuesCustom = values();
            int length = valuesCustom.length;
            StatusIndicator[] statusIndicatorArr = new StatusIndicator[length];
            System.arraycopy(valuesCustom, 0, statusIndicatorArr, 0, length);
            return statusIndicatorArr;
        }
    }

    public BluetoothMasRequestSetMessageStatus(String handle, StatusIndicator statusInd, boolean statusValue) {
        this.mHeaderSet.setHeader(66, TYPE);
        this.mHeaderSet.setHeader(1, handle);
        ObexAppParameters oap = new ObexAppParameters();
        oap.add(NfDef.AVRCP_BROWSING_STATUS_ADDRESS_PLAYER_CHANGED, statusInd == StatusIndicator.READ ? STATUS_INDICATOR_READ : STATUS_INDICATOR_DELETED);
        oap.add((byte) 24, statusValue ? STATUS_YES : STATUS_NO);
        oap.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    public void execute(ClientSession session) throws IOException {
        executePut(session, FILLER_BYTE);
    }
}
