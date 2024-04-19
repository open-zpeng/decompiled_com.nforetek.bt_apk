package com.nforetek.bt.profile.pbap.java;

import android.os.Handler;
import android.util.Log;
import com.nforetek.bt.res.obex.PasswordAuthentication;
/* loaded from: classes.dex */
class BluetoothPbapObexAuthenticator implements com.nforetek.bt.res.obex.Authenticator {
    private static final String TAG = "BluetoothPbapObexAuthenticator";
    private final Handler mCallback;
    private String mSessionKey = "0000";

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothPbapObexAuthenticator(Handler callback) {
        this.mCallback = callback;
    }

    @Override // com.nforetek.bt.res.obex.Authenticator
    public PasswordAuthentication onAuthenticationChallenge(String description, boolean isUserIdRequired, boolean isFullAccess) {
        Log.v(TAG, "onAuthenticationChallenge: starting");
        if (this.mSessionKey != null && this.mSessionKey.length() != 0) {
            Log.v(TAG, "onAuthenticationChallenge: mSessionKey=" + this.mSessionKey);
            PasswordAuthentication pa = new PasswordAuthentication(null, this.mSessionKey.getBytes());
            return pa;
        }
        Log.v(TAG, "onAuthenticationChallenge: mSessionKey is empty, timeout/cancel occured");
        return null;
    }

    @Override // com.nforetek.bt.res.obex.Authenticator
    public byte[] onAuthenticationResponse(byte[] userName) {
        Log.v(TAG, "onAuthenticationResponse: " + userName);
        return null;
    }
}
