package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.BluetoothMasClient;
import com.nforetek.bt.profile.map.java.utils.ObexAppParameters;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.obex.ClientSession;
import com.nforetek.util.normal.NfLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BluetoothMasRequestGetMessage extends BluetoothMasRequest {
    private static final String TAG = "ReqGetMessage";
    private static final String TYPE = "x-bt/message";
    private BluetoothMapBmessage mBmessage;
    private String mHandle;

    public BluetoothMasRequestGetMessage(String handle, BluetoothMasClient.CharsetType charset, boolean attachment) {
        this.mHandle = handle;
        this.mHeaderSet.setHeader(1, handle);
        this.mHeaderSet.setHeader(66, TYPE);
        ObexAppParameters oap = new ObexAppParameters();
        oap.add(NfDef.AVRCP_BROWSING_STATUS_PLAYER_NOT_ADDRESSED, BluetoothMasClient.CharsetType.UTF_8.equals(charset) ? CHARSET_UTF8 : CHARSET_NATIVE);
        oap.add((byte) 10, attachment ? ATTACHMENT_ON : ATTACHMENT_OFF);
        oap.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    protected void readResponse(InputStream stream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        while (true) {
            try {
                int len = stream.read(buf);
                if (len != -1) {
                    baos.write(buf, 0, len);
                }
            } catch (IOException e) {
                NfLog.e(TAG, "I/O exception while reading response", e);
            }
            try {
                break;
            } catch (UnsupportedEncodingException e2) {
                NfLog.e(TAG, "Coudn't decode the bmessage with UTF-8. Something must be really messed up.", e2);
                return;
            }
        }
        String bmsg = baos.toString("UTF-8");
        this.mBmessage = BluetoothMapBmessageParser.createBmessage(bmsg);
        if (this.mBmessage == null) {
            this.mResponseCode = 208;
            return;
        }
        this.mBmessage.mHandle = this.mHandle;
    }

    public BluetoothMapBmessage getMessage() {
        return this.mBmessage;
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    public void execute(ClientSession session) throws IOException {
        executeGet(session);
    }
}
