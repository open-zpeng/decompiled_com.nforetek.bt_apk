package com.nforetek.bt.profile.pbap.java;

import android.accounts.Account;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.util.Log;
import com.nforetek.bt.profile.pbap.java.vcard.VCardEntry;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class PhonebookPullRequest extends PullRequest {
    private static final int MAX_OPS = 250;
    private static final String TAG = "PbapPhonebookPullRequest";
    private static final boolean VDBG = false;
    public boolean complete = false;
    private final Account mAccount;
    private final Context mContext;

    public PhonebookPullRequest(Context context, Account account) {
        this.mContext = context;
        this.mAccount = account;
        this.path = PbapClientConnectionHandler.PB_PATH;
    }

    @Override // com.nforetek.bt.profile.pbap.java.PullRequest
    public void onPullComplete() {
        try {
            if (this.mEntries == null) {
                Log.e(TAG, "onPullComplete entries is null.");
                return;
            }
            ContentResolver contactsProvider = this.mContext.getContentResolver();
            ArrayList<ContentProviderOperation> insertOperations = new ArrayList<>();
            Iterator<VCardEntry> it = this.mEntries.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                VCardEntry e = it.next();
                if (Thread.currentThread().isInterrupted()) {
                    Log.e(TAG, "Interrupted durring insert.");
                    break;
                }
                int numberOfOperations = insertOperations.size();
                e.constructInsertOperations(contactsProvider, insertOperations);
                if (insertOperations.size() >= MAX_OPS) {
                    insertOperations.subList(numberOfOperations, insertOperations.size()).clear();
                    contactsProvider.applyBatch("com.android.contacts", insertOperations);
                    insertOperations = e.constructInsertOperations(contactsProvider, null);
                    if (insertOperations.size() >= MAX_OPS) {
                        insertOperations.clear();
                    }
                }
            }
            if (insertOperations.size() > 0) {
                contactsProvider.applyBatch("com.android.contacts", insertOperations);
                insertOperations.clear();
            }
        } catch (OperationApplicationException e2) {
            Log.e(TAG, "Got exception: ", e2);
        } catch (RemoteException e1) {
            Log.e(TAG, "Got exception: ", e1);
        } catch (NumberFormatException e22) {
            Log.e(TAG, "Got exception: ", e22);
        } finally {
            this.complete = true;
        }
    }
}
