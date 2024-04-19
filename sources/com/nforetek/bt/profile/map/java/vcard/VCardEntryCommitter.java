package com.nforetek.bt.profile.map.java.vcard;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class VCardEntryCommitter implements VCardEntryHandler {
    public static String LOG_TAG = "vCard";
    private final ContentResolver mContentResolver;
    private int mCounter;
    private final ArrayList<Uri> mCreatedUris = new ArrayList<>();
    private ArrayList<ContentProviderOperation> mOperationList;
    private long mTimeToCommit;

    public VCardEntryCommitter(ContentResolver resolver) {
        this.mContentResolver = resolver;
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardEntryHandler
    public void onStart() {
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardEntryHandler
    public void onEnd() {
        if (this.mOperationList != null) {
            this.mCreatedUris.add(pushIntoContentResolver(this.mOperationList));
        }
        if (VCardConfig.showPerformanceLog()) {
            Log.d(LOG_TAG, String.format("time to commit entries: %d ms", Long.valueOf(this.mTimeToCommit)));
        }
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardEntryHandler
    public void onEntryCreated(VCardEntry vcardEntry) {
        long start = System.currentTimeMillis();
        this.mOperationList = vcardEntry.constructInsertOperations(this.mContentResolver, this.mOperationList);
        this.mCounter++;
        if (this.mCounter >= 20) {
            this.mCreatedUris.add(pushIntoContentResolver(this.mOperationList));
            this.mCounter = 0;
            this.mOperationList = null;
        }
        this.mTimeToCommit += System.currentTimeMillis() - start;
    }

    private Uri pushIntoContentResolver(ArrayList<ContentProviderOperation> operationList) {
        try {
            ContentProviderResult[] results = this.mContentResolver.applyBatch("com.android.contacts", operationList);
            if (results == null || results.length == 0 || results[0] == null) {
                return null;
            }
            return results[0].uri;
        } catch (OperationApplicationException e) {
            Log.e(LOG_TAG, String.format("%s: %s", e.toString(), e.getMessage()));
            return null;
        } catch (RemoteException e2) {
            Log.e(LOG_TAG, String.format("%s: %s", e2.toString(), e2.getMessage()));
            return null;
        }
    }

    public ArrayList<Uri> getCreatedUris() {
        return this.mCreatedUris;
    }
}
