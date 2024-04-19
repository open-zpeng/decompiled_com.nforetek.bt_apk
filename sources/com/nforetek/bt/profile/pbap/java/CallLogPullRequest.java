package com.nforetek.bt.profile.pbap.java;

import android.accounts.Account;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Pair;
import com.nforetek.bt.profile.pbap.java.vcard.VCardEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes.dex */
public class CallLogPullRequest extends PullRequest {
    private static final boolean DBG = true;
    private static final String TAG = "PbapCallLogPullRequest";
    private static final String TIMESTAMP_FORMAT = "yyyyMMdd'T'HHmmss";
    private static final String TIMESTAMP_PROPERTY = "X-IRMC-CALL-DATETIME";
    private static final boolean VDBG = false;
    private final Account mAccount;
    private HashMap<String, Integer> mCallCounter;
    private Context mContext;

    public CallLogPullRequest(Context context, String path, HashMap<String, Integer> map, Account account) {
        this.mContext = context;
        this.path = path;
        this.mCallCounter = map;
        this.mAccount = account;
    }

    @Override // com.nforetek.bt.profile.pbap.java.PullRequest
    public void onPullComplete() {
        int type;
        if (this.mEntries == null) {
            Log.e(TAG, "onPullComplete entries is null.");
            return;
        }
        Log.d(TAG, "onPullComplete");
        try {
            if (this.path.equals(PbapClientConnectionHandler.ICH_PATH)) {
                type = 1;
            } else if (this.path.equals(PbapClientConnectionHandler.OCH_PATH)) {
                type = 2;
            } else if (this.path.equals(PbapClientConnectionHandler.MCH_PATH)) {
                type = 3;
            } else {
                Log.w(TAG, "Unknown path type:" + this.path);
                return;
            }
            ArrayList<ContentProviderOperation> ops = new ArrayList<>();
            for (VCardEntry vcard : this.mEntries) {
                ContentValues values = new ContentValues();
                values.put("type", Integer.valueOf(type));
                List<VCardEntry.PhoneData> phones = vcard.getPhoneList();
                if (phones == null || phones.get(0).getNumber().equals(";")) {
                    values.put("number", "");
                } else {
                    String phoneNumber = phones.get(0).getNumber();
                    values.put("number", phoneNumber);
                    if (this.mCallCounter.get(phoneNumber) != null) {
                        int updateCounter = this.mCallCounter.get(phoneNumber).intValue() + 1;
                        this.mCallCounter.put(phoneNumber, Integer.valueOf(updateCounter));
                    } else {
                        this.mCallCounter.put(phoneNumber, 1);
                    }
                }
                List<Pair<String, String>> irmc = vcard.getUnknownXData();
                SimpleDateFormat parser = new SimpleDateFormat(TIMESTAMP_FORMAT);
                if (irmc != null) {
                    for (Pair<String, String> pair : irmc) {
                        if (((String) pair.first).startsWith("X-IRMC-CALL-DATETIME")) {
                            try {
                                values.put("date", Long.valueOf(parser.parse((String) pair.second).getTime()));
                            } catch (ParseException e) {
                                Log.d(TAG, "Failed to parse date ");
                            }
                        }
                    }
                }
                ops.add(ContentProviderOperation.newInsert(CallLog.Calls.CONTENT_URI).withValues(values).withYieldAllowed(true).build());
            }
            this.mContext.getContentResolver().applyBatch("call_log", ops);
            Log.d(TAG, "Updated call logs.");
            if (type == 2) {
                updateTimesContacted();
            }
        } catch (OperationApplicationException e2) {
            Log.d(TAG, "Failed to update call log for path=" + this.path, e2);
        } catch (RemoteException e1) {
            Log.d(TAG, "Failed to update call log for path=" + this.path, e1);
        }
        synchronized (this) {
            notify();
        }
    }

    private void updateTimesContacted() {
        for (String key : this.mCallCounter.keySet()) {
            ContentValues values = new ContentValues();
            values.put("times_contacted", this.mCallCounter.get(key));
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(key));
            Cursor c = this.mContext.getContentResolver().query(uri, null, null, null, null);
            if (c != null && c.getCount() > 0) {
                c.moveToNext();
                String contactId = c.getString(c.getColumnIndex("contact_id"));
                String where = "contact_id=" + contactId;
                this.mContext.getContentResolver().update(ContactsContract.RawContacts.CONTENT_URI, values, where, null);
            }
        }
        Log.d(TAG, "Updated TIMES_CONTACTED");
    }
}
