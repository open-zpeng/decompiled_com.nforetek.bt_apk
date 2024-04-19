package com.nforetek.bt.res.obex;

import android.util.Log;
import com.nforetek.bt.res.NfDef;
import java.io.IOException;
/* loaded from: classes.dex */
public class ObexSession {
    private static final String TAG = "ObexSession";
    private static final boolean V = true;
    protected Authenticator mAuthenticator;
    protected byte[] mChallengeDigest;

    public boolean handleAuthChall(HeaderSet header) throws IOException {
        byte[] password;
        Log.e(TAG, "===handleAuthChall start");
        if (this.mAuthenticator == null) {
            return false;
        }
        byte[] challenge = ObexHelper.getTagValue((byte) 0, header.mAuthChall);
        byte[] option = ObexHelper.getTagValue((byte) 1, header.mAuthChall);
        byte[] description = ObexHelper.getTagValue((byte) 2, header.mAuthChall);
        String realm = null;
        if (description != null) {
            Log.e(TAG, "handleAuthChall description!null");
            byte[] realmString = new byte[description.length - 1];
            System.arraycopy(description, 1, realmString, 0, realmString.length);
            switch (description[0] & NfDef.AVRCP_PLAYING_STATUS_ID_ERROR) {
                case 0:
                case 1:
                    try {
                        realm = new String(realmString, "ISO8859_1");
                        break;
                    } catch (Exception e) {
                        throw new IOException("Unsupported Encoding Scheme");
                    }
                case 255:
                    realm = ObexHelper.convertToUnicode(realmString, false);
                    break;
                default:
                    throw new IOException("Unsupported Encoding Scheme");
            }
        }
        boolean isUserIDRequired = false;
        boolean isFullAccess = true;
        if (option != null) {
            if ((option[0] & 1) != 0) {
                Log.e(TAG, "isUserIDRequired is true");
                isUserIDRequired = true;
            }
            if ((option[0] & 2) != 0) {
                Log.e(TAG, "isFullAccess is false");
                isFullAccess = false;
            }
        }
        header.mAuthChall = null;
        Log.e(TAG, "onAuthenticationChallenge");
        try {
            PasswordAuthentication result = this.mAuthenticator.onAuthenticationChallenge(realm, isUserIDRequired, isFullAccess);
            if (result == null || (password = result.getPassword()) == null) {
                return false;
            }
            byte[] userName = result.getUserName();
            if (userName != null) {
                header.mAuthResp = new byte[userName.length + 38];
                header.mAuthResp[36] = 1;
                header.mAuthResp[37] = (byte) userName.length;
                System.arraycopy(userName, 0, header.mAuthResp, 38, userName.length);
            } else {
                header.mAuthResp = new byte[36];
            }
            Log.e(TAG, "Create the secret String");
            byte[] digest = new byte[challenge.length + password.length + 1];
            System.arraycopy(challenge, 0, digest, 0, challenge.length);
            digest[challenge.length] = 58;
            System.arraycopy(password, 0, digest, challenge.length + 1, password.length);
            header.mAuthResp[0] = 0;
            header.mAuthResp[1] = NfDef.AVRCP_BROWSING_STATUS_SEARCH_NOT_SUPPORT;
            System.arraycopy(ObexHelper.computeMd5Hash(digest), 0, header.mAuthResp, 2, 16);
            header.mAuthResp[18] = 2;
            header.mAuthResp[19] = NfDef.AVRCP_BROWSING_STATUS_SEARCH_NOT_SUPPORT;
            System.arraycopy(challenge, 0, header.mAuthResp, 20, 16);
            Log.e(TAG, "===handleAuthChall end");
            return true;
        } catch (Exception e2) {
            Log.d(TAG, "Exception occured - returning false", e2);
            return false;
        }
    }

    public boolean handleAuthResp(byte[] authResp) {
        byte[] correctPassword;
        if (this.mAuthenticator == null || (correctPassword = this.mAuthenticator.onAuthenticationResponse(ObexHelper.getTagValue((byte) 1, authResp))) == null) {
            return false;
        }
        byte[] temp = new byte[correctPassword.length + 16];
        System.arraycopy(this.mChallengeDigest, 0, temp, 0, 16);
        System.arraycopy(correctPassword, 0, temp, 16, correctPassword.length);
        byte[] correctResponse = ObexHelper.computeMd5Hash(temp);
        byte[] actualResponse = ObexHelper.getTagValue((byte) 0, authResp);
        for (int i = 0; i < 16; i++) {
            if (correctResponse[i] != actualResponse[i]) {
                return false;
            }
        }
        return true;
    }
}
