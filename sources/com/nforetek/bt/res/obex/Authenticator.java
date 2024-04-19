package com.nforetek.bt.res.obex;
/* loaded from: classes.dex */
public interface Authenticator {
    PasswordAuthentication onAuthenticationChallenge(String str, boolean z, boolean z2);

    byte[] onAuthenticationResponse(byte[] bArr);
}
