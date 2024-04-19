package com.nforetek.bt.profile.pbap.java;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
/* loaded from: classes.dex */
public class Authenticator extends AbstractAccountAuthenticator {
    private static final String TAG = "PbapAuthenticator";

    public Authenticator(Context context) {
        super(context);
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle editProperties(AccountAuthenticatorResponse r, String s) {
        Log.d(TAG, "got call", new Exception());
        throw new UnsupportedOperationException();
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle addAccount(AccountAuthenticatorResponse r, String s, String s2, String[] strings, Bundle bundle) throws NetworkErrorException {
        Log.d(TAG, "got call", new Exception());
        throw new UnsupportedOperationException();
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle confirmCredentials(AccountAuthenticatorResponse r, Account account, Bundle bundle) throws NetworkErrorException {
        Log.d(TAG, "got call", new Exception());
        return null;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle getAuthToken(AccountAuthenticatorResponse r, Account account, String s, Bundle bundle) throws NetworkErrorException {
        Log.d(TAG, "got call", new Exception());
        throw new UnsupportedOperationException();
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public String getAuthTokenLabel(String s) {
        Log.d(TAG, "got call", new Exception());
        return null;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle updateCredentials(AccountAuthenticatorResponse r, Account account, String s, Bundle bundle) throws NetworkErrorException {
        Log.d(TAG, "got call", new Exception());
        return null;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle hasFeatures(AccountAuthenticatorResponse r, Account account, String[] strings) throws NetworkErrorException {
        Log.d(TAG, "got call", new Exception());
        Bundle result = new Bundle();
        result.putBoolean("booleanResult", false);
        return result;
    }
}
