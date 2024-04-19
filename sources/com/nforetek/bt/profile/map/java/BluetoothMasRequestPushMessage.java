package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.BluetoothMasClient;
import com.nforetek.bt.profile.map.java.utils.ObexAppParameters;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.obex.ClientSession;
import com.nforetek.bt.res.obex.HeaderSet;
import java.io.IOException;
import java.math.BigInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BluetoothMasRequestPushMessage extends BluetoothMasRequest {
    private static final String TYPE = "x-bt/message";
    private String mMsg;
    private String mMsgHandle;

    private BluetoothMasRequestPushMessage(String folder) {
        this.mHeaderSet.setHeader(66, TYPE);
        this.mHeaderSet.setHeader(1, folder == null ? "" : folder);
    }

    public BluetoothMasRequestPushMessage(String folder, String msg, BluetoothMasClient.CharsetType charset, boolean transparent, boolean retry) {
        this(folder);
        this.mMsg = msg;
        ObexAppParameters oap = new ObexAppParameters();
        oap.add((byte) 11, transparent ? TRANSPARENT_ON : TRANSPARENT_OFF);
        oap.add((byte) 12, retry ? RETRY_ON : RETRY_OFF);
        oap.add(NfDef.AVRCP_BROWSING_STATUS_PLAYER_NOT_ADDRESSED, charset == BluetoothMasClient.CharsetType.NATIVE ? CHARSET_NATIVE : CHARSET_UTF8);
        oap.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    protected void readResponseHeaders(HeaderSet headerset) {
        try {
            String handle = (String) headerset.getHeader(1);
            if (handle != null) {
                new BigInteger(handle, 16);
                this.mMsgHandle = handle;
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.mResponseCode = 208;
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            this.mResponseCode = 208;
        }
    }

    public String getMsgHandle() {
        return this.mMsgHandle;
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    public void execute(ClientSession session) throws IOException {
        executePut(session, this.mMsg.getBytes());
    }
}
