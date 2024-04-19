package com.nforetek.bt.profile.pbap.java;

import android.accounts.Account;
import android.util.Log;
import com.nforetek.bt.profile.pbap.java.vcard.VCardEntry;
import com.nforetek.bt.res.obex.HeaderSet;
import com.nforetek.util.normal.NfLog;
import java.io.IOException;
import java.io.InputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BluetoothPbapRequestPullPhoneBook extends BluetoothPbapRequest {
    private static final String TAG = "BluetoothPbapRequestPullPhoneBook";
    private static final String TYPE = "x-bt/phonebook";
    private static final boolean VDBG = false;
    private Account mAccount;
    private final byte mFormat;
    boolean mInterrup;
    private int mNewMissedCalls = -1;
    private PbapHandlerListener mPbapClientConnectionHandler;
    private BluetoothPbapVcardList mResponse;

    /* loaded from: classes.dex */
    public interface PbapHandlerListener {
        void onPhoneBookPullDone(VCardEntry vCardEntry);

        void onPhoneBookPullDoneCheckSize(int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothPbapRequestPullPhoneBook(PbapHandlerListener pbapClientConnectionHandler, String pbName, Account account, long filter, byte format, int maxListCount, int listStartOffset) {
        this.mInterrup = false;
        this.mInterrup = false;
        this.mPbapClientConnectionHandler = pbapClientConnectionHandler;
        this.mAccount = account;
        if (maxListCount < 0 || maxListCount > 65535) {
            throw new IllegalArgumentException("maxListCount should be [0..65535]");
        }
        if (listStartOffset < 0 || listStartOffset > 65535) {
            throw new IllegalArgumentException("listStartOffset should be [0..65535]");
        }
        this.mHeaderSet.setHeader(1, pbName);
        this.mHeaderSet.setHeader(66, TYPE);
        ObexAppParameters oap = new ObexAppParameters();
        if (format != 0 && format != 1) {
            format = 0;
        }
        if (filter != 0) {
            oap.add((byte) 6, filter);
        }
        oap.add((byte) 7, format);
        if (maxListCount > 0) {
            oap.add((byte) 4, (short) maxListCount);
        } else {
            oap.add((byte) 4, (short) -1);
        }
        if (listStartOffset > 0) {
            oap.add((byte) 5, (short) listStartOffset);
        }
        oap.addToHeaderSet(this.mHeaderSet);
        this.mFormat = format;
    }

    @Override // com.nforetek.bt.profile.pbap.java.BluetoothPbapRequest
    protected void readResponse(InputStream stream) throws IOException {
        Log.v(TAG, "readResponse");
        this.mResponse = new BluetoothPbapVcardList(this, this.mAccount, stream, this.mFormat);
        if (this.mInterrup) {
            this.mResponse.parse(stream, this.mFormat, true);
        } else {
            this.mResponse.parse(stream, this.mFormat, false);
        }
    }

    public void onDownloadCanceled() {
        NfLog.d(TAG, "onDownloadCanceled()");
        if (this.mResponse != null) {
            this.mResponse.onCancelDownload();
        }
        this.mInterrup = true;
    }

    @Override // com.nforetek.bt.profile.pbap.java.BluetoothPbapRequest
    protected void readResponseHeaders(HeaderSet headerset) {
        Log.v(TAG, "readResponseHeaders");
        ObexAppParameters oap = ObexAppParameters.fromHeaderSet(headerset);
        if (oap.exists((byte) 9)) {
            this.mNewMissedCalls = oap.getByte((byte) 9);
        }
    }

    public void callbackVCardEntry(VCardEntry entry) {
        this.mPbapClientConnectionHandler.onPhoneBookPullDone(entry);
    }

    public void onPhoneBookPullDoneCheckSize(int size) {
        this.mPbapClientConnectionHandler.onPhoneBookPullDoneCheckSize(size);
    }

    public void downloadingFinish() {
        onPhoneBookPullDoneCheckSize(this.mResponse.getCount());
    }
}
