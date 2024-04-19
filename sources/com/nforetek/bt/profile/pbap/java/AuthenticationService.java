package com.nforetek.bt.profile.pbap.java;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/* loaded from: classes.dex */
public class AuthenticationService extends Service {
    private Authenticator mAuthenticator;

    @Override // android.app.Service
    public void onCreate() {
        this.mAuthenticator = new Authenticator(this);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mAuthenticator.getIBinder();
    }
}
