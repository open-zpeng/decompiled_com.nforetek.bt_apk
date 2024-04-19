package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.utils.ObexAppParameters;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.obex.ClientSession;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BluetoothMasRequestSetNotificationRegistration extends BluetoothMasRequest {
    private static final String TYPE = "x-bt/MAP-NotificationRegistration";
    private final boolean mStatus;

    public BluetoothMasRequestSetNotificationRegistration(boolean status) {
        this.mStatus = status;
        this.mHeaderSet.setHeader(66, TYPE);
        ObexAppParameters oap = new ObexAppParameters();
        oap.add(NfDef.AVRCP_BROWSING_STATUS_PLAYING_LIST_FULL, status ? NOTIFICATION_ON : NOTIFICATION_OFF);
        oap.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    public void execute(ClientSession session) throws IOException {
        executePut(session, FILLER_BYTE);
    }

    public boolean getStatus() {
        return this.mStatus;
    }
}
