package com.nforetek.bt.profile.bluetooth;

import com.nforetek.bt.profile.a2dp._NfA2dp;
import com.nforetek.bt.profile.avrcp._NfAvrcp;
import com.nforetek.bt.profile.hfp._NfHfp;
import com.nforetek.bt.profile.hid._NfHid;
import com.nforetek.bt.profile.map._NfMap;
import com.nforetek.bt.profile.opp._NfOpp;
import com.nforetek.bt.profile.pbap._NfPbap;
import com.nforetek.bt.profile.spp._NfSpp;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class CheckProfileDisconnectedThread extends Thread {
    private String TAG;
    private _NfA2dp mA2dp;
    private String mAddress;
    private _NfAvrcp mAvrcp;
    CheckProfileDisconnectedCallbackInterface mCallback;
    private _NfHfp mHfp;
    private _NfHid mHid;
    private _NfMap mMap;
    private _NfOpp mOpp;
    private _NfPbap mPbap;
    private _NfSpp mSpp;

    /* loaded from: classes.dex */
    public interface CheckProfileDisconnectedCallbackInterface {
        void onCheckProfileDisconnectedFinidhed(String str);
    }

    public CheckProfileDisconnectedThread(String address, CheckProfileDisconnectedCallbackInterface cb, _NfHfp hfp, _NfA2dp a2dp, _NfAvrcp avrcp, _NfPbap pbap, _NfHid hid, _NfSpp spp, _NfMap map, _NfOpp opp) {
        this.mAddress = NfDef.DEFAULT_ADDRESS;
        this.TAG = "CheckProfileDisconnectedThread";
        this.mAddress = address;
        this.TAG = String.valueOf(this.TAG) + " (" + this.mAddress + ")";
        this.mHfp = hfp;
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
        NfLog.e(this.TAG, "onDestroy()");
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
        NfLog.d(this.TAG, "Start check profile disconnected.");
        if (this.mHfp != null && isNeedDisconnect("HFP", this.mHfp.getConnectedAddress(), this.mHfp.getProfileState())) {
            this.mHfp.disconnect(this.mAddress);
        } else if (this.mA2dp != null && isNeedDisconnect("A2DP", this.mA2dp.getConnectedAddress(), this.mA2dp.getProfileState())) {
            this.mA2dp.disconnect(this.mAddress);
        } else if (this.mAvrcp != null && isNeedDisconnect("AVRCP", this.mAvrcp.getConnectedAddress(), this.mAvrcp.getProfileState())) {
            this.mAvrcp.disconnect(this.mAddress);
        } else if (this.mPbap != null && isNeedDisconnect("PBAP", this.mPbap.getDownloadingAddress(), this.mPbap.getProfileState())) {
            this.mPbap.downloadInterrupt(this.mAddress);
        } else if (this.mHid != null && isNeedDisconnect("HID", this.mHid.getConnectedAddress(), this.mHid.getProfileState())) {
            this.mHid.disconnect(this.mAddress);
        } else if (this.mSpp != null && this.mSpp.isConnected(this.mAddress)) {
            this.mSpp.disconnect(this.mAddress);
        } else if (this.mMap != null && isNeedDisconnect("MAP", this.mMap.getConnetedAddress(), this.mMap.getProfileState())) {
            this.mMap.forceDisconnect();
        } else if (this.mOpp != null && isNeedDisconnect("OPP", this.mOpp.getConnectedAddress(), this.mOpp.getProfileState())) {
            this.mOpp.forceCallbackDisconnected();
        }
        if (this.mCallback != null) {
            this.mCallback.onCheckProfileDisconnectedFinidhed(this.mAddress);
        }
        onDestroy();
    }

    private boolean isNeedDisconnect(String profile, String address, int state) {
        if (!address.equals(this.mAddress) || (state != 120 && state <= 140)) {
            return false;
        }
        NfLog.d(this.TAG, profile + " state: " + state + " need disconnect.");
        return true;
    }

    public String getAddress() {
        return this.mAddress;
    }
}
