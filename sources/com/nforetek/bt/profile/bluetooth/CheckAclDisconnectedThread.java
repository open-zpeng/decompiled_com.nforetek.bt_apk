package com.nforetek.bt.profile.bluetooth;

import com.nforetek.bt.profile.a2dp._NfA2dp;
import com.nforetek.bt.profile.avrcp._NfAvrcp;
import com.nforetek.bt.profile.hid._NfHid;
import com.nforetek.bt.profile.map._NfMap;
import com.nforetek.bt.profile.opp._NfOpp;
import com.nforetek.bt.profile.pbap._NfPbap;
import com.nforetek.bt.profile.spp._NfSpp;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class CheckAclDisconnectedThread extends Thread {
    private static String TAG = "CheckAclDisconnectedThread";
    private _NfA2dp mA2dp;
    private String mAddress;
    private _NfAvrcp mAvrcp;
    CheckAclDisconnectedCallbackInterface mCallback;
    private _NfHid mHid;
    private boolean mIsAclDisconnected = false;
    private _NfMap mMap;
    private _NfOpp mOpp;
    private _NfPbap mPbap;
    private _NfSpp mSpp;

    /* loaded from: classes.dex */
    public interface CheckAclDisconnectedCallbackInterface {
        void onCheckAclDisconnectedFinidhed(String str, boolean z);
    }

    public CheckAclDisconnectedThread(String address, CheckAclDisconnectedCallbackInterface cb, _NfA2dp a2dp, _NfAvrcp avrcp, _NfPbap pbap, _NfHid hid, _NfSpp spp, _NfMap map, _NfOpp opp) {
        this.mAddress = NfDef.DEFAULT_ADDRESS;
        this.mAddress = address;
        this.mA2dp = a2dp;
        this.mAvrcp = avrcp;
        this.mPbap = pbap;
        this.mHid = hid;
        this.mSpp = spp;
        this.mMap = map;
        this.mOpp = opp;
        this.mCallback = cb;
    }

    private void onDestroy() {
        NfLog.e(TAG, "onDetroy()");
        this.mA2dp = null;
        this.mAvrcp = null;
        this.mPbap = null;
        this.mHid = null;
        this.mSpp = null;
        this.mMap = null;
        this.mOpp = null;
        this.mCallback = null;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        boolean isNeedRemoveAclConnection = true;
        try {
            sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int enableProfileCount = 0;
        int profileReadyCount = 0;
        if (this.mA2dp != null) {
            enableProfileCount = 0 + 1;
        }
        if (this.mAvrcp != null) {
            enableProfileCount++;
        }
        if (this.mPbap != null) {
            enableProfileCount++;
        }
        if (this.mHid != null) {
            enableProfileCount++;
        }
        if (this.mSpp != null) {
            enableProfileCount++;
        }
        if (this.mMap != null) {
            enableProfileCount++;
        }
        if (this.mOpp != null) {
            enableProfileCount++;
        }
        if (!this.mIsAclDisconnected) {
            if (this.mA2dp != null && this.mA2dp.getProfileState() == 110) {
                profileReadyCount = 0 + 1;
            } else if (this.mAvrcp != null && this.mAvrcp.getProfileState() == 110) {
                profileReadyCount = 0 + 1;
            } else if (this.mPbap != null && this.mPbap.getProfileState() == 110) {
                profileReadyCount = 0 + 1;
            } else if (this.mHid != null && this.mHid.getProfileState() == 110) {
                profileReadyCount = 0 + 1;
            } else if (this.mSpp != null && !this.mSpp.hasAnyConnectedConntetion()) {
                profileReadyCount = 0 + 1;
            } else if (this.mMap != null && this.mMap.getProfileState() == 110) {
                profileReadyCount = 0 + 1;
            } else if (this.mOpp != null && this.mOpp.getProfileState() == 110) {
                profileReadyCount = 0 + 1;
            }
            NfLog.d(TAG, "enableProfileCount: " + enableProfileCount);
            NfLog.d(TAG, "profileReadyCount: " + profileReadyCount);
            if (enableProfileCount == profileReadyCount) {
                isNeedRemoveAclConnection = true;
            }
        } else {
            isNeedRemoveAclConnection = false;
        }
        if (this.mCallback != null) {
            this.mCallback.onCheckAclDisconnectedFinidhed(this.mAddress, isNeedRemoveAclConnection);
        }
        onDestroy();
    }

    public String getAddress() {
        return this.mAddress;
    }

    public void interrupt(boolean isAclDisconnected) {
        NfLog.e(TAG, "interrupt() " + this.mAddress + " isAclDisconnected: " + isAclDisconnected);
        this.mIsAclDisconnected = isAclDisconnected;
        interrupt();
    }
}
