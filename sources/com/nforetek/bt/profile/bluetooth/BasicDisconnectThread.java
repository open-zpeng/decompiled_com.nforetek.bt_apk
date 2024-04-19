package com.nforetek.bt.profile.bluetooth;

import com.nforetek.bt.profile.a2dp._NfA2dp;
import com.nforetek.bt.profile.avrcp._NfAvrcp;
import com.nforetek.bt.profile.hfp._NfHfp;
import com.nforetek.bt.profile.hid._NfHid;
import com.nforetek.bt.profile.map._NfMap;
import com.nforetek.bt.profile.pbap._NfPbap;
import com.nforetek.bt.profile.spp._NfSpp;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class BasicDisconnectThread extends Thread {
    private static String TAG = "BasicDisconnectThread";
    private boolean isA2dp;
    private boolean isAvrcp;
    private boolean isHfp;
    private boolean isHid;
    private boolean isMap;
    private boolean isPbap;
    private boolean isSpp;
    private _NfA2dp mA2dp;
    private _NfAvrcp mAvrcp;
    private BasicDisconnectCallbackInterface mCallback;
    private _NfHfp mHfp;
    private _NfHid mHid;
    private boolean mIsUnpair;
    private _NfMap mMap;
    private _NfPbap mPbap;
    private _NfSpp mSpp;
    private String mUnPairAddress;

    /* loaded from: classes.dex */
    public interface BasicDisconnectCallbackInterface {
        void basicDisconnectDidFinished(boolean z, boolean z2, boolean z3);
    }

    private void onDestroy() {
        NfLog.d(TAG, "onDestroy()");
        boolean isHfpSuccess = this.isHfp ? this.mHfp.getProfileState() <= 110 : false;
        boolean isA2dpSuccess = this.isA2dp ? this.mA2dp.getProfileState() <= 110 : false;
        boolean isAvrcpSuccess = this.isAvrcp ? this.mAvrcp.getProfileState() <= 110 : false;
        if (this.mCallback != null) {
            this.mCallback.basicDisconnectDidFinished(isHfpSuccess, isA2dpSuccess, isAvrcpSuccess);
        }
        if (this.mIsUnpair) {
            NfPrimitive.unPair(this.mUnPairAddress);
        }
        this.mHfp = null;
        this.mA2dp = null;
        this.mAvrcp = null;
        this.mHid = null;
        this.mMap = null;
        this.mPbap = null;
        this.mSpp = null;
        this.mCallback = null;
    }

    public BasicDisconnectThread(_NfHfp hfp, _NfA2dp a2dp, _NfAvrcp avrcp, _NfHid hid, _NfMap map, _NfPbap pbap, _NfSpp spp, BasicDisconnectCallbackInterface cb) {
        this.isHfp = false;
        this.isA2dp = false;
        this.isAvrcp = false;
        this.isHid = false;
        this.isMap = false;
        this.isPbap = false;
        this.isSpp = false;
        this.mIsUnpair = false;
        this.mUnPairAddress = NfDef.DEFAULT_ADDRESS;
        TAG = "BasicDisconnectThread_" + hashCode();
        NfLog.d(TAG, "BasicDisconnectThread()");
        this.mHfp = hfp;
        this.mA2dp = a2dp;
        this.mAvrcp = avrcp;
        this.mHid = hid;
        this.mMap = map;
        this.mPbap = pbap;
        this.mSpp = spp;
        this.mCallback = cb;
    }

    public BasicDisconnectThread(_NfHfp hfp, _NfA2dp a2dp, _NfAvrcp avrcp, _NfHid hid, _NfMap map, _NfPbap pbap, _NfSpp spp, BasicDisconnectCallbackInterface cb, boolean isUnpair, String unpairAddress) {
        this.isHfp = false;
        this.isA2dp = false;
        this.isAvrcp = false;
        this.isHid = false;
        this.isMap = false;
        this.isPbap = false;
        this.isSpp = false;
        this.mIsUnpair = false;
        this.mUnPairAddress = NfDef.DEFAULT_ADDRESS;
        TAG = "BasicDisconnectThread_" + hashCode();
        NfLog.d(TAG, "BasicDisconnectThread()");
        this.mHfp = hfp;
        this.mA2dp = a2dp;
        this.mAvrcp = avrcp;
        this.mHid = hid;
        this.mMap = map;
        this.mPbap = pbap;
        this.mSpp = spp;
        this.mCallback = cb;
        this.mIsUnpair = isUnpair;
        this.mUnPairAddress = unpairAddress;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        NfLog.d(TAG, "BasicDisconnectThread() in run()");
        if (!NfPrimitive.isAdapterEnabled()) {
            NfLog.e(TAG, "BluetoothAdapter is not enable!!");
            onDestroy();
            return;
        }
        this.isHfp = isHfpNeedDisconnect();
        this.isA2dp = isA2dpNeedDisconnect();
        this.isAvrcp = isAvrcpNeedDisconnect();
        this.isHid = isHidNeedDisconnect();
        this.isPbap = isPbapNeedDisconnect();
        this.isMap = isMapNeedDisconnect();
        this.isSpp = isSppConnected();
        NfLog.d(TAG, "Need disconnect isHfp = " + this.isHfp + " isA2dp = " + this.isA2dp + " isAvrcp = " + this.isAvrcp);
        NfLog.d(TAG, "Need disconnect isHid = " + this.isHid + " isPbap = " + this.isPbap + " isMap = " + this.isMap + " isSpp = " + this.isSpp);
        if (this.isHfp) {
            if (!this.mHfp.getConnectedAddress().equals(NfDef.DEFAULT_ADDRESS)) {
                NfLog.d(TAG, "Try to disconnect HFP connected address: " + this.mHfp.getConnectedAddress());
                this.mHfp.disconnect(this.mHfp.getConnectedAddress());
            } else {
                NfLog.d(TAG, "Try to disconnect HFP connecting address: " + this.mHfp.getConnectingAddress());
                this.mHfp.disconnect(this.mHfp.getConnectingAddress());
            }
        }
        if (this.isA2dp) {
            if (!this.mA2dp.getConnectedAddress().equals(NfDef.DEFAULT_ADDRESS)) {
                NfLog.d(TAG, "Try to disconnect A2DP connected address: " + this.mA2dp.getConnectedAddress());
                this.mA2dp.disconnect(this.mA2dp.getConnectedAddress());
            } else {
                NfLog.d(TAG, "Try to disconnect A2DP connecting address: " + this.mA2dp.getConnectingAddress());
                this.mA2dp.disconnect(this.mA2dp.getConnectingAddress());
            }
        }
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.mA2dp == null || this.mA2dp.getProfileState() == 110) {
                break;
            }
        }
        if (this.isAvrcp) {
            if (!this.mAvrcp.getConnectedAddress().equals(NfDef.DEFAULT_ADDRESS)) {
                NfLog.d(TAG, "Try to disconnect AVRCP connected address: " + this.mAvrcp.getConnectedAddress());
                this.mAvrcp.disconnect(this.mAvrcp.getConnectedAddress());
            } else {
                NfLog.d(TAG, "Try to disconnect AVRCP connecting address: " + this.mAvrcp.getConnectingAddress());
                this.mAvrcp.disconnect(this.mAvrcp.getConnectingAddress());
            }
        }
        if (this.isHid) {
            NfLog.d(TAG, "Try to disconnect HID. address: " + this.mHid.getConnectedAddress());
            this.mHid.disconnect(this.mHid.getConnectedAddress());
        }
        if (this.isPbap) {
            NfLog.d(TAG, "Try to disconnect PBAP. address: " + this.mPbap.getDownloadingAddress());
            if (NfConfig.isPbapImplementByJava()) {
                this.mPbap.downloadInterrupt();
            } else {
                this.mPbap.downloadInterrupt(this.mPbap.getDownloadingAddress());
            }
        }
        if (this.isMap) {
            NfLog.d(TAG, "Try to disconnect MAP. address: " + this.mMap.getConnetedAddress());
            this.mMap.forceDisconnect();
        }
        if (this.isSpp) {
            NfLog.d(TAG, "Try to disconnect SPP");
            this.mSpp.disconnectAllConnectedConntection();
        }
        int r = 5;
        while (r > 0 && (isHfpNeedDisconnect() || isA2dpNeedDisconnect() || isAvrcpNeedDisconnect() || isHidNeedDisconnect() || isPbapNeedDisconnect() || isMapNeedDisconnect() || isSppConnected())) {
            try {
                Thread.sleep(300L);
                r--;
            } catch (InterruptedException e2) {
                onDestroy();
                return;
            }
        }
        NfLog.d(TAG, "After disconnect needed profile.");
        NfLog.d(TAG, "HFP State: " + (this.mHfp != null ? this.mHfp.getProfileState() : 100));
        NfLog.d(TAG, "A2DP State: " + (this.mA2dp != null ? this.mA2dp.getProfileState() : 100));
        NfLog.d(TAG, "AVRCP State: " + (this.mAvrcp != null ? this.mAvrcp.getProfileState() : 100));
        NfLog.d(TAG, "Waiting count is : " + r);
        onDestroy();
    }

    private boolean isHfpNeedDisconnect() {
        if (this.mHfp == null) {
            return false;
        }
        if (this.mHfp.getProfileState() < 140 && this.mHfp.getProfileState() != 120) {
            return false;
        }
        return true;
    }

    private boolean isA2dpNeedDisconnect() {
        if (this.mA2dp == null) {
            return false;
        }
        if (this.mA2dp.getProfileState() < 140 && this.mA2dp.getProfileState() != 120) {
            return false;
        }
        return true;
    }

    private boolean isAvrcpNeedDisconnect() {
        if (this.mAvrcp == null) {
            return false;
        }
        if (this.mAvrcp.getProfileState() < 140 && this.mAvrcp.getProfileState() != 120) {
            return false;
        }
        return true;
    }

    private boolean isHidNeedDisconnect() {
        if (this.mHid == null) {
            return false;
        }
        if (this.mHid.getProfileState() < 140 && this.mHid.getProfileState() != 120) {
            return false;
        }
        return true;
    }

    private boolean isMapNeedDisconnect() {
        if (this.mMap == null) {
            return false;
        }
        if (this.mMap.getProfileState() < 140 && this.mMap.getProfileState() != 120) {
            return false;
        }
        return true;
    }

    private boolean isPbapNeedDisconnect() {
        if (this.mPbap == null) {
            return false;
        }
        if (this.mPbap.getProfileState() < 140 && this.mPbap.getProfileState() != 120) {
            return false;
        }
        return true;
    }

    private boolean isSppConnected() {
        if (this.mSpp == null || !this.mSpp.hasAnyConnectedConntetion()) {
            return false;
        }
        return true;
    }

    public static int getNeedDisconnectMask(String address, _NfHfp hfp, _NfA2dp a2dp, _NfAvrcp avrcp, _NfHid hid, _NfMap map, _NfPbap pbap, _NfSpp spp) {
        NfLog.d(TAG, "getNeedDisconnectMask()");
        int result = 0;
        if (hfp != null) {
            int state = address.equals(NfDef.DEFAULT_ADDRESS) ? hfp.getProfileState() : hfp.getProfileState(address);
            if (state >= 140) {
                result = 0 | 1;
            }
        }
        if (a2dp != null) {
            int state2 = address.equals(NfDef.DEFAULT_ADDRESS) ? a2dp.getProfileState() : a2dp.getProfileState(address);
            if (state2 >= 140) {
                result |= 2;
            }
        }
        if (avrcp != null) {
            int state3 = address.equals(NfDef.DEFAULT_ADDRESS) ? avrcp.getProfileState() : avrcp.getProfileState(address);
            if (state3 >= 140) {
                result |= 4;
            }
        }
        if (hid != null) {
            int state4 = address.equals(NfDef.DEFAULT_ADDRESS) ? hid.getProfileState() : hid.getProfileState(address);
            if (state4 >= 140) {
                result |= 256;
            }
        }
        if (map != null) {
            int state5 = address.equals(NfDef.DEFAULT_ADDRESS) ? map.getProfileState() : map.getProfileState(address);
            if (state5 >= 140) {
                result |= 64;
            }
        }
        if (pbap != null) {
            int state6 = address.equals(NfDef.DEFAULT_ADDRESS) ? pbap.getProfileState() : pbap.getProfileState(address);
            if (state6 >= 140) {
                result |= 32;
            }
        }
        if (spp != null) {
            if (address.equals(NfDef.DEFAULT_ADDRESS)) {
                if (spp.hasAnyConnectedConntetion()) {
                    result |= 128;
                }
            } else if (spp.isConnected(address)) {
                result |= 128;
            }
        }
        NfLog.d(TAG, "getNeedDisconnectMask() mask: " + result);
        return result;
    }
}
