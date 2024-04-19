package com.nforetek.bt.profile.pbap.java;

import android.util.Log;
import com.nforetek.bt.res.obex.HeaderSet;
/* loaded from: classes.dex */
class BluetoothPbapRequestPullPhoneBookSize extends BluetoothPbapRequest {
    private static final String TAG = "BluetoothPbapRequestPullPhoneBookSize";
    private static final String TYPE = "x-bt/phonebook";
    private int mSize;
    public String pbName;

    public BluetoothPbapRequestPullPhoneBookSize(String pbName) {
        this.mHeaderSet.setHeader(1, pbName);
        Log.d(TAG, "path: " + pbName);
        this.pbName = pbName;
        this.mHeaderSet.setHeader(66, TYPE);
        com.nforetek.bt.profile.pbap.java.utils.ObexAppParameters oap = new com.nforetek.bt.profile.pbap.java.utils.ObexAppParameters();
        oap.add((byte) 4, (short) 0);
        oap.addToHeaderSet(this.mHeaderSet);
    }

    @Override // com.nforetek.bt.profile.pbap.java.BluetoothPbapRequest
    protected void readResponseHeaders(HeaderSet headerset) {
        Log.v(TAG, "readResponseHeaders");
        com.nforetek.bt.profile.pbap.java.utils.ObexAppParameters oap = com.nforetek.bt.profile.pbap.java.utils.ObexAppParameters.fromHeaderSet(headerset);
        this.mSize = oap.getShort((byte) 8);
        Log.d(TAG, "PhoneBookSize: " + this.mSize);
    }

    public int getSize() {
        return this.mSize;
    }
}
