package com.nforetek.bt.profile.bluetooth;

import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.profile.a2dp._NfA2dp;
import com.nforetek.bt.profile.avrcp._NfAvrcp;
import com.nforetek.bt.profile.hfp._NfHfp;
import com.nforetek.bt.profile.hid._NfHid;
import com.nforetek.bt.profile.map._NfMap;
import com.nforetek.bt.profile.pbap._NfPbap;
import com.nforetek.bt.profile.spp._NfSpp;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.Constants;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class BasicConnectThread extends Thread {
    private static _NfA2dp sA2dp;
    private static _NfAvrcp sAvrcp;
    private static _NfBluetooth sBluetooth;
    private static _NfHfp sHfp;
    private static _NfHid sHid;
    private static _NfMap sMap;
    private static _NfPbap sPbap;
    private static _NfSpp sSpp;
    private static BasicConnectThread sThread;
    private String mAddress;
    private BasicConnectCallbackInterface mCallback;
    private boolean mIsAutoConnect;
    private boolean mIsSingleTry;
    private static String TAG = "BasicConnectThread";
    private static boolean sIsNeedInterrupt = false;
    private static boolean sIsInitited = false;
    private static boolean sIsConnecting = false;
    private boolean isNeedConnectHfp = false;
    private boolean isNeedConnectA2dp = false;
    private boolean isNeedConnectAvrcp = false;
    private boolean isNeedDisconnectHfp = false;
    private boolean isNeedDisconnectA2dp = false;
    private boolean isNeedDisconnectAvrcp = false;
    private boolean isNeedDisconnectHid = false;
    private boolean isNeedDisconnectMap = false;
    private boolean isNeedDisconnectPbap = false;
    private boolean isNeedDisconnectSpp = false;
    private boolean mIsSingleTried = false;
    private boolean mIsAnyConnectedAddressSameAsTargetAddress = false;
    private int mState = 100;
    private int mPrevState = 100;

    /* loaded from: classes.dex */
    public interface BasicConnectCallbackInterface {
        void basicConnectDidFinished(String str, boolean z, boolean z2, boolean z3);
    }

    public static void initProfile(_NfHfp hfp, _NfA2dp a2dp, _NfAvrcp avrcp, _NfHid hid, _NfMap map, _NfPbap pbap, _NfSpp spp, _NfBluetooth bt) {
        sHfp = hfp;
        sA2dp = a2dp;
        sAvrcp = avrcp;
        sHid = hid;
        sMap = map;
        sPbap = pbap;
        sSpp = spp;
        sBluetooth = bt;
        sIsInitited = true;
    }

    private void onDestroy() {
        NfLog.e(TAG, "onDestroy()");
        if (NfConfig.isUnconnectableAfterDeviceConnected()) {
            if (sHfp.getProfileState() >= 140 && sA2dp.getProfileState() >= 140 && sAvrcp.getProfileState() >= 140) {
                NfPrimitive.setBtConnectable(false);
            }
        } else {
            NfPrimitive.setBtConnectable(true);
        }
        boolean isHfpSuccess = false;
        boolean isA2dpSuccess = false;
        boolean isAvrcpSuccess = false;
        try {
            isHfpSuccess = this.isNeedConnectHfp ? sHfp.getProfileState() >= 140 : true;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            isA2dpSuccess = this.isNeedConnectA2dp ? sA2dp.getProfileState() >= 140 : true;
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
        try {
            isAvrcpSuccess = this.isNeedConnectAvrcp ? sAvrcp.getProfileState() >= 140 : true;
        } catch (NullPointerException e3) {
            e3.printStackTrace();
        }
        String addr = this.mAddress;
        if (this.mCallback != null) {
            this.mCallback.basicConnectDidFinished(this.mAddress, isHfpSuccess, isA2dpSuccess, isAvrcpSuccess);
        }
        this.mAddress = NfDef.DEFAULT_ADDRESS;
        if (this.mCallback == null) {
            NfLog.e(TAG, "Need to check why already onDestroy.");
        }
        this.mCallback = null;
        sIsNeedInterrupt = false;
        sThread = null;
        sIsConnecting = false;
        setState(addr, 100);
    }

    public static boolean isConnecting() {
        return sIsConnecting;
    }

    public static BasicConnectThread getSharedBasicConnectThread() {
        return sThread;
    }

    public static BasicConnectThread createBasicConnectThread(String address, BasicConnectCallbackInterface callback, boolean singleTry, boolean isAutoConnect) {
        if (sThread == null) {
            NfLog.v(TAG, "createBasicConnectThread() for " + address);
            if (address == null || address.equals(NfDef.DEFAULT_ADDRESS)) {
                return null;
            }
            sIsConnecting = true;
            sThread = new BasicConnectThread(address, callback, singleTry, isAutoConnect);
            return sThread;
        }
        try {
            NfLog.e(TAG, "BasicConnecThread is still connect to " + sThread.getAddress());
            return null;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private BasicConnectThread(String address, BasicConnectCallbackInterface callback, boolean singleTry, boolean isAutoConnect) {
        this.mAddress = NfDef.DEFAULT_ADDRESS;
        this.mIsSingleTry = false;
        this.mIsAutoConnect = true;
        NfLog.v(TAG, "BasicConnectThread() address: " + address + " singleTry: " + singleTry + " isAutoConnect: " + isAutoConnect);
        this.mAddress = address;
        setState(110);
        this.mIsSingleTry = singleTry;
        this.mCallback = callback;
        this.mIsAutoConnect = isAutoConnect;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            setState(NfDef.STATE_BASIC_CONNECT_BEGIN);
            if (NfConfig.getBasicConnectBeforeConnectDelayTime() > 0) {
                try {
                    Thread.sleep(NfConfig.getBasicConnectBeforeConnectDelayTime());
                } catch (InterruptedException e) {
                    checkIsNeedInterrupt();
                }
            }
            checkIsNeedInterrupt();
            setState(NfDef.STATE_BASIC_CONNECT_CHECKING_NEED_DISCONNECT_PROFILE);
        } catch (BasicConnectInterruptException e2) {
            e2.printStackTrace();
            if (sIsNeedInterrupt) {
                onDestroy();
                return;
            }
        }
        if (!NfPrimitive.isAdapterEnabled()) {
            throw new BasicConnectInterruptException("BluetoothAdapter is not enable!!");
        }
        for (int waitCount = 20; !NfJni.isJniProfileInitialed() && waitCount > 0; waitCount--) {
            NfLog.d(TAG, "Wainting until NfJni initted profile...");
            try {
                Thread.sleep(250L);
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
        }
        if (!NfJni.isJniProfileInitialed()) {
            throw new BasicConnectInterruptException("NfJni profile not init yet.");
        }
        this.isNeedDisconnectHfp = isNeedDisconnectHfp();
        this.isNeedDisconnectA2dp = isNeedDisconnectA2dp();
        this.isNeedDisconnectAvrcp = isNeedDisconnectAvrcp();
        this.isNeedDisconnectHid = isNeedDisconnectHid();
        this.isNeedDisconnectMap = isNeedDisconnectMap();
        this.isNeedDisconnectPbap = isNeedDisconnectPbap();
        this.isNeedDisconnectSpp = isNeedDisconnectSpp();
        String[] needDisconectAddresses = getNeedDisconectAddresses();
        NfLog.d(TAG, "Need disconnect isHfp = " + this.isNeedDisconnectHfp + " isA2dp = " + this.isNeedDisconnectA2dp + " isAvrcp = " + this.isNeedDisconnectAvrcp);
        NfLog.d(TAG, "Need disconnect isHid = " + this.isNeedDisconnectHid + " isPbap = " + this.isNeedDisconnectPbap + " isMap = " + this.isNeedDisconnectMap + " isSpp = " + this.isNeedDisconnectSpp);
        setState(NfDef.STATE_BASIC_CONNECT_DISCONNECTING_PROFILE);
        if (this.isNeedDisconnectPbap) {
            NfLog.d(TAG, "Try to disconnect PBAP. address: " + sPbap.getDownloadingAddress());
            if (NfConfig.isPbapImplementByJava()) {
                sPbap.downloadInterrupt();
            } else {
                sPbap.downloadInterrupt(sPbap.getDownloadingAddress());
            }
        }
        if (this.isNeedDisconnectHid) {
            NfLog.d(TAG, "Try to disconnect HID. address: " + sHid.getConnectedAddress());
            sHid.disconnect(sHid.getConnectedAddress());
        }
        if (this.isNeedDisconnectMap) {
            NfLog.d(TAG, "Try to disconnect MAP. address: " + sMap.getConnetedAddress());
            sMap.forceDisconnect();
        }
        if (this.isNeedDisconnectSpp) {
            NfLog.d(TAG, "Try to disconnect SPP");
            sSpp.disconnectAllConnectedConntection();
        }
        if (this.isNeedDisconnectA2dp) {
            if (sA2dp.isConnecting()) {
                NfLog.d(TAG, "Try to disconnect A2DP. connecting address: " + sA2dp.getConnectingAddress());
                sA2dp.disconnect(sA2dp.getConnectingAddress());
            } else if (sA2dp.isConnected()) {
                NfLog.d(TAG, "Try to disconnect A2DP. connected address: " + sA2dp.getConnectedAddress());
                sA2dp.disconnect(sA2dp.getConnectedAddress());
            } else {
                NfLog.d(TAG, "A2DP not connected.");
            }
        }
        if (this.isNeedDisconnectAvrcp) {
            if (sAvrcp.isConnecting()) {
                NfLog.d(TAG, "Try to disconnect AVRCP. connecting address: " + sAvrcp.getConnectingAddress());
                sAvrcp.disconnect(sAvrcp.getConnectingAddress());
            } else if (sAvrcp.isConnected()) {
                NfLog.d(TAG, "Try to disconnect AVRCP. connected address: " + sAvrcp.getConnectedAddress());
                sAvrcp.disconnect(sAvrcp.getConnectedAddress());
            } else {
                NfLog.d(TAG, "AVRCP not connected.");
            }
        }
        if (this.isNeedDisconnectHfp) {
            if (sHfp.isConnecting()) {
                NfLog.d(TAG, "Try to disconnect HFP. connecting address: " + sHfp.getConnectingAddress());
                sHfp.disconnect(sHfp.getConnectingAddress());
            } else if (sHfp.isConnected()) {
                NfLog.d(TAG, "Try to disconnect HFP. connected address: " + sHfp.getConnectedAddress());
                sHfp.disconnect(sHfp.getConnectedAddress());
            } else {
                NfLog.d(TAG, "HFP not connected.");
            }
        }
        setState(NfDef.STATE_BASIC_CONNECT_WAITING_FOR_PROFILE_DISCONNECTED);
        if (this.isNeedDisconnectHfp || this.isNeedDisconnectA2dp || this.isNeedDisconnectAvrcp || this.isNeedDisconnectHid || this.isNeedDisconnectPbap || this.isNeedDisconnectMap || this.isNeedDisconnectSpp) {
            int r = 20;
            while (r > 0 && ((this.isNeedDisconnectHfp && !isHfpDisconnected()) || ((this.isNeedDisconnectA2dp && !isA2dpDisconnected()) || ((this.isNeedDisconnectAvrcp && !isAvrcpDisconnected()) || ((this.isNeedDisconnectHid && !isHidDisconnected()) || ((this.isNeedDisconnectMap && !isMapDisconnected()) || ((this.isNeedDisconnectPbap && !isPbapDisconnected()) || (this.isNeedDisconnectSpp && !isSppDisconnected())))))))) {
                try {
                    Thread.sleep(250L);
                } catch (InterruptedException e4) {
                    checkIsNeedInterrupt();
                }
                r--;
            }
            NfLog.d(TAG, "After disconnect needed profile.");
            NfLog.d(TAG, "HFP State: " + (sHfp != null ? sHfp.getProfileState() : 100));
            NfLog.d(TAG, "A2DP State: " + (sA2dp != null ? sA2dp.getProfileState() : 100));
            NfLog.d(TAG, "AVRCP State: " + (sAvrcp != null ? sAvrcp.getProfileState() : 100));
            NfLog.d(TAG, "HID State: " + (sHid != null ? sHid.getProfileState() : 100));
            NfLog.d(TAG, "PBAP State: " + (sPbap != null ? sPbap.getProfileState() : 100));
            NfLog.d(TAG, "MAP State: " + (sMap != null ? sMap.getProfileState() : 100));
            NfLog.d(TAG, "Waiting count is: " + r);
        }
        int needDisconnectIndex = 0;
        int waitingDisconnectCount = 0;
        while (needDisconnectIndex < needDisconectAddresses.length && waitingDisconnectCount < 50) {
            if (sBluetooth != null && !needDisconectAddresses[needDisconnectIndex].equals(NfDef.DEFAULT_ADDRESS) && !_NfBluetooth.isDeviceAclDisconnected(needDisconectAddresses[needDisconnectIndex])) {
                NfLog.e(TAG, "address: " + needDisconectAddresses[needDisconnectIndex] + " ACL still connected");
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e5) {
                    checkIsNeedInterrupt();
                }
                waitingDisconnectCount++;
            } else if (sBluetooth == null) {
                break;
            } else {
                needDisconnectIndex++;
            }
        }
        NfLog.d(TAG, "Waiting ACL disconnect count is : " + waitingDisconnectCount);
        NfLog.d(TAG, "Is any connected address same as target address? " + (this.mIsAnyConnectedAddressSameAsTargetAddress ? "YES" : "NO"));
        if (!this.mIsAnyConnectedAddressSameAsTargetAddress) {
            setState(120);
            try {
                Thread.sleep(700L);
            } catch (InterruptedException e6) {
                checkIsNeedInterrupt();
            }
        }
        if (sBluetooth.isAnyDeviceAclDisconnectedBeforeInTime(Constants.MAX_RECORDS_IN_DATABASE)) {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e7) {
                checkIsNeedInterrupt();
            }
        }
        checkIsNeedInterrupt();
        processBasicConnectPart();
        onDestroy();
    }

    private void processBasicConnectPart() throws BasicConnectInterruptException {
        NfLog.d(TAG, "processBasicConnectPart()");
        this.isNeedConnectHfp = isNeedConnectHfp();
        this.isNeedConnectA2dp = isNeedConnectA2dp();
        this.isNeedConnectAvrcp = isNeedConnectAvrcp();
        NfLog.d(TAG, "Need connect isHfp = " + this.isNeedConnectHfp + " isA2dp = " + this.isNeedConnectA2dp + " isAvrcp = " + this.isNeedConnectAvrcp);
        if (!this.isNeedConnectHfp && !this.isNeedConnectA2dp && !this.isNeedConnectAvrcp) {
            throw new BasicConnectInterruptException("All basic profile is connecting or connected.");
        }
        checkIsNeedInterrupt();
        if (!this.mIsAutoConnect) {
            NfLog.d(TAG, "Not auto connect, set BT unconnectable.");
            NfPrimitive.setBtConnectable(false);
        }
        boolean isHfpTryToConnect = false;
        if (this.isNeedConnectHfp) {
            NfLog.d(TAG, "Try to connect HFP. address: " + this.mAddress);
            setState(121);
            isHfpTryToConnect = true;
            sHfp.connect(this.mAddress);
            this.mIsSingleTried = true;
        }
        if (isHfpTryToConnect && (this.isNeedConnectA2dp || this.isNeedConnectAvrcp)) {
            int r = 80;
            while (true) {
                if (r > 0) {
                    try {
                        Thread.sleep(250L);
                    } catch (InterruptedException e) {
                        checkIsNeedInterrupt();
                    }
                    if (isHfpConnected()) {
                        NfLog.d(TAG, "HFP connected!! state: " + sHfp.getProfileState() + " address: " + sHfp.getConnectedAddress());
                        break;
                    } else if (isHfpDisconnected() && r < 76) {
                        NfLog.d(TAG, "HFP disconnected!! state: " + sHfp.getProfileState());
                        break;
                    } else {
                        r--;
                    }
                } else {
                    break;
                }
            }
            checkIsNeedInterrupt();
        }
        if (this.isNeedConnectHfp && !isHfpConnected() && this.mIsSingleTry && this.mIsSingleTried) {
            NfLog.e(TAG, "Is single tried after try HFP. Finish here.");
            throw new BasicConnectInterruptException("Is single tried after try HFP. Finish here.");
        }
        if (isHfpTryToConnect) {
            try {
                Thread.sleep(NfConfig.getBasicConnectEachProfileDelayTime());
            } catch (InterruptedException e2) {
                checkIsNeedInterrupt();
            }
        }
        checkIsNeedInterrupt();
        if (sHfp.getProfileState() == 110) {
            NfLog.d(TAG, "HFP connect fail, set BT connectable.");
            NfPrimitive.setBtConnectable(true);
        }
        boolean isA2dpTryToConnect = false;
        if (this.isNeedConnectA2dp) {
            if (sA2dp.getProfileState() == 110) {
                NfLog.d(TAG, "Try to connect A2DP. address: " + this.mAddress);
                isA2dpTryToConnect = true;
                setState(122);
                sA2dp.connect(this.mAddress);
                this.mIsSingleTried = true;
            } else {
                NfLog.d(TAG, "A2DP state: " + sA2dp.getProfileState() + " address: " + sA2dp.getConnectedAddress());
            }
        }
        if (this.isNeedConnectAvrcp) {
            int r2 = 80;
            while (true) {
                if (r2 > 0) {
                    try {
                        Thread.sleep(250L);
                    } catch (InterruptedException e3) {
                        checkIsNeedInterrupt();
                    }
                    if (isA2dpConnected()) {
                        NfLog.d(TAG, "A2DP connected!! state: " + sA2dp.getProfileState() + " address: " + sA2dp.getConnectedAddress());
                        break;
                    } else if (isA2dpDisconnected() && r2 < 76) {
                        NfLog.d(TAG, "A2DP disconnected !! state: " + sA2dp.getProfileState());
                        break;
                    } else {
                        r2--;
                    }
                } else {
                    break;
                }
            }
            checkIsNeedInterrupt();
            if (isA2dpTryToConnect) {
                try {
                    Thread.sleep(NfConfig.getBasicConnectEachProfileDelayTime());
                } catch (InterruptedException e4) {
                    checkIsNeedInterrupt();
                }
            }
            checkIsNeedInterrupt();
            if (this.isNeedConnectAvrcp) {
                if (sA2dp.getProfileState() < 140) {
                    throw new BasicConnectInterruptException("A2DP disconnect or connect fail, don't connect AVRCP.");
                }
                if (sAvrcp.getProfileState() == 110) {
                    NfLog.d(TAG, "Try to connect AVRCP. address: " + (sA2dp.getConnectedAddress().equals(NfDef.DEFAULT_ADDRESS) ? this.mAddress : sA2dp.getConnectedAddress()));
                    setState(123);
                    sAvrcp.connect(sA2dp.getConnectedAddress().equals(NfDef.DEFAULT_ADDRESS) ? this.mAddress : sA2dp.getConnectedAddress());
                }
            }
            if (this.isNeedConnectAvrcp) {
                int r3 = 40;
                while (true) {
                    if (r3 > 0) {
                        try {
                            Thread.sleep(250L);
                        } catch (InterruptedException e5) {
                            checkIsNeedInterrupt();
                        }
                        if (isAvrcpConnected()) {
                            NfLog.d(TAG, "AVRCP connected!! state: " + sAvrcp.getProfileState() + " address: " + sAvrcp.getConnectedAddress());
                            break;
                        } else if (isAvrcpDisconnected() && r3 < 36) {
                            NfLog.d(TAG, "AVRCP disconnected !! state: " + sAvrcp.getProfileState());
                            break;
                        } else {
                            r3--;
                        }
                    } else {
                        break;
                    }
                }
                checkIsNeedInterrupt();
            }
        }
    }

    private boolean isNeedDisconnectHfp() {
        boolean need = false;
        if (sHfp == null) {
            NfLog.e(TAG, "NfHfp is not initialized.");
            return false;
        }
        if (sHfp.getProfileState() >= 140) {
            if (!this.mAddress.equals(sHfp.getConnectedAddress())) {
                need = true;
            } else {
                NfLog.e(TAG, "HFP connected address is same as target address.");
                this.mIsAnyConnectedAddressSameAsTargetAddress = true;
            }
        } else if (sHfp.getProfileState() == 120) {
            if (!this.mAddress.equals(sHfp.getConnectingAddress())) {
                need = true;
            } else {
                NfLog.e(TAG, "HFP connecting address is same as target address.");
                this.mIsAnyConnectedAddressSameAsTargetAddress = true;
            }
        }
        return need;
    }

    private boolean isNeedDisconnectA2dp() {
        boolean need = false;
        if (sA2dp == null) {
            NfLog.e(TAG, "NfA2dp is not initialized.");
            return false;
        }
        if (sA2dp.getProfileState() >= 140) {
            if (!this.mAddress.equals(sA2dp.getConnectedAddress())) {
                need = true;
            } else {
                NfLog.e(TAG, "A2DP connected address is same as target address.");
                this.mIsAnyConnectedAddressSameAsTargetAddress = true;
            }
        } else if (sA2dp.getProfileState() == 120) {
            if (!this.mAddress.equals(sA2dp.getConnectingAddress())) {
                need = true;
            } else {
                NfLog.e(TAG, "A2DP connecting address is same as target address.");
                this.mIsAnyConnectedAddressSameAsTargetAddress = true;
            }
        }
        return need;
    }

    private boolean isNeedDisconnectAvrcp() {
        boolean need = false;
        if (sAvrcp == null) {
            NfLog.e(TAG, "NfAvrcp is not initialized.");
            return false;
        }
        if (sAvrcp.getProfileState() >= 140) {
            if (!this.mAddress.equals(sAvrcp.getConnectedAddress())) {
                need = true;
            } else {
                NfLog.e(TAG, "AVRCP connected address is same as target address.");
                this.mIsAnyConnectedAddressSameAsTargetAddress = true;
            }
        } else if (sAvrcp.getProfileState() == 120) {
            if (!this.mAddress.equals(sAvrcp.getConnectingAddress())) {
                need = true;
            } else {
                NfLog.e(TAG, "AVRCP connecting address is same as target address.");
                this.mIsAnyConnectedAddressSameAsTargetAddress = true;
            }
        }
        return need;
    }

    private boolean isNeedDisconnectHid() {
        boolean need = false;
        if (sHid == null) {
            NfLog.e(TAG, "NfHid is not initialized.");
            return false;
        }
        if (sHid.getProfileState() >= 140 || sHid.getProfileState() == 120) {
            if (!this.mAddress.equals(sHid.getConnectedAddress())) {
                need = true;
            } else if (!this.mAddress.equals(sHid.getConnectingAddress())) {
                need = true;
            } else {
                NfLog.e(TAG, "HID connected address is same as target address.");
                this.mIsAnyConnectedAddressSameAsTargetAddress = true;
            }
        }
        return need;
    }

    private boolean isNeedDisconnectMap() {
        boolean need = false;
        if (sMap == null) {
            NfLog.e(TAG, "NfMap is not initialized.");
            return false;
        }
        if (sMap.getProfileState() >= 140 || sMap.getProfileState() == 120) {
            if (!this.mAddress.equals(sMap.getConnectedAddress())) {
                need = true;
            } else if (!this.mAddress.equals(sMap.getConnectingAddress())) {
                need = true;
            } else {
                NfLog.e(TAG, "MAP connected address is same as target address.");
                this.mIsAnyConnectedAddressSameAsTargetAddress = true;
            }
            if (this.mAddress.equals(sMap.getConnectingAddress())) {
                NfLog.e(TAG, "MAP connecting address is same as target address.");
                need = false;
            }
        }
        return need;
    }

    private boolean isNeedDisconnectPbap() {
        boolean need = false;
        if (sPbap == null) {
            NfLog.e(TAG, "NfPbap is not initialized.");
            return false;
        }
        if (sPbap.getProfileState() >= 140 || sPbap.getProfileState() == 120) {
            if (!this.mAddress.equals(sPbap.getConnectedAddress()) && !this.mAddress.equals(sPbap.getConnectingAddress())) {
                need = true;
            } else {
                NfLog.e(TAG, "PBAP connected address is same as target address.");
                this.mIsAnyConnectedAddressSameAsTargetAddress = true;
            }
            if (this.mAddress.equals(sPbap.getConnectingAddress())) {
                NfLog.e(TAG, "PBAP connecting address is same as target address.");
                need = false;
            }
        }
        return need;
    }

    private boolean isNeedDisconnectSpp() {
        if (sSpp == null) {
            return false;
        }
        if (sSpp.hasAnyConnectedConntetion()) {
            return true;
        }
        if (!sSpp.isConnected(this.mAddress)) {
            return false;
        }
        NfLog.e(TAG, "SPP connected address is same as target address.");
        this.mIsAnyConnectedAddressSameAsTargetAddress = true;
        return false;
    }

    private String[] getNeedDisconectAddresses() {
        ArrayList<String> resultArray = new ArrayList<>();
        if (isNeedDisconnectHfp()) {
            String address = sHfp.getConnectedAddress();
            if (!address.equals(NfDef.DEFAULT_ADDRESS) && !resultArray.contains(address)) {
                resultArray.add(address);
            }
        }
        if (isNeedDisconnectA2dp()) {
            String address2 = sA2dp.getConnectedAddress();
            if (!address2.equals(NfDef.DEFAULT_ADDRESS) && !resultArray.contains(address2)) {
                resultArray.add(address2);
            }
        }
        if (isNeedDisconnectAvrcp()) {
            String address3 = sAvrcp.getConnectedAddress();
            if (!address3.equals(NfDef.DEFAULT_ADDRESS) && !resultArray.contains(address3)) {
                resultArray.add(address3);
            }
        }
        if (isNeedDisconnectHid()) {
            String address4 = sHid.getConnectedAddress();
            if (!address4.equals(NfDef.DEFAULT_ADDRESS) && !resultArray.contains(address4)) {
                resultArray.add(address4);
            }
        }
        if (isNeedDisconnectMap()) {
            String address5 = sMap.getConnectedAddress();
            if (!address5.equals(NfDef.DEFAULT_ADDRESS) && !resultArray.contains(address5)) {
                resultArray.add(address5);
            }
        }
        if (isNeedDisconnectPbap()) {
            String address6 = sPbap.getDownloadingAddress();
            if (!address6.equals(NfDef.DEFAULT_ADDRESS) && !resultArray.contains(address6)) {
                resultArray.add(address6);
            }
        }
        if (isNeedDisconnectSpp()) {
            List<String> sppConnectedAddresses = sSpp.getConnectedAddressList();
            for (int i = 0; i < sppConnectedAddresses.size(); i++) {
                String address7 = sppConnectedAddresses.get(i);
                if (!address7.equals(NfDef.DEFAULT_ADDRESS) && !resultArray.contains(address7)) {
                    resultArray.add(address7);
                }
            }
        }
        String[] result = (String[]) resultArray.toArray(new String[resultArray.size()]);
        for (int i2 = 0; i2 < result.length; i2++) {
            NfLog.v(TAG, "getNeedDisconectAddresses(): " + result[i2]);
        }
        return result;
    }

    private boolean isNeedConnectHfp() {
        boolean need = false;
        if (sHfp == null) {
            NfLog.e(TAG, "NfHfp is not initialized.");
            return false;
        }
        if (sHfp.getProfileState() == 110) {
            need = true;
        }
        return need;
    }

    private boolean isNeedConnectA2dp() {
        boolean need = false;
        if (sA2dp == null) {
            NfLog.e(TAG, "NfA2dp is not initialized.");
            return false;
        }
        if (sA2dp.getProfileState() == 110) {
            need = true;
        }
        return need;
    }

    private boolean isNeedConnectAvrcp() {
        boolean need = false;
        if (sAvrcp == null) {
            NfLog.e(TAG, "NfAvrcp is not initialized.");
            return false;
        }
        if (sAvrcp.getProfileState() == 110) {
            need = true;
        }
        return need;
    }

    private boolean isHfpConnected() {
        boolean connected = false;
        if (sHfp == null) {
            NfLog.e(TAG, "NfHfp is not initialized.");
            return false;
        }
        if (sHfp.getProfileState() >= 140) {
            connected = true;
        }
        return connected;
    }

    private boolean isA2dpConnected() {
        boolean connected = false;
        if (sA2dp == null) {
            NfLog.e(TAG, "NfA2dp is not initialized.");
            return false;
        }
        if (sA2dp.getProfileState() >= 140) {
            connected = true;
        }
        return connected;
    }

    private boolean isAvrcpConnected() {
        boolean connected = false;
        if (sAvrcp == null) {
            NfLog.e(TAG, "NfAvrcp is not initialized.");
            return false;
        }
        if (sAvrcp.getProfileState() >= 140) {
            connected = true;
        }
        return connected;
    }

    private boolean isHfpDisconnected() {
        boolean disconnected = false;
        if (sHfp == null) {
            NfLog.e(TAG, "NfHfp is not initialized.");
            return true;
        }
        if (sHfp.getProfileState() <= 110) {
            disconnected = true;
        }
        return disconnected;
    }

    private boolean isA2dpDisconnected() {
        boolean disconnected = false;
        if (sA2dp == null) {
            NfLog.e(TAG, "NfA2dp is not initialized.");
            return true;
        }
        if (sA2dp.getProfileState() <= 110) {
            disconnected = true;
        }
        return disconnected;
    }

    private boolean isAvrcpDisconnected() {
        boolean disconnected = false;
        if (sAvrcp == null) {
            NfLog.e(TAG, "NfAvrcp is not initialized.");
            return true;
        }
        if (sAvrcp.getProfileState() <= 110) {
            disconnected = true;
        }
        return disconnected;
    }

    private boolean isHidDisconnected() {
        boolean disconnected = false;
        if (sHid == null) {
            NfLog.e(TAG, "NfHid is not initialized.");
            return true;
        }
        if (sHid.getProfileState() <= 110) {
            disconnected = true;
        }
        return disconnected;
    }

    private boolean isPbapDisconnected() {
        boolean disconnected = false;
        if (sPbap == null) {
            NfLog.e(TAG, "NfPbap is not initialized.");
            return true;
        }
        if (sPbap.getProfileState() <= 110) {
            disconnected = true;
        }
        return disconnected;
    }

    private boolean isMapDisconnected() {
        boolean disconnected = false;
        if (sMap == null) {
            NfLog.e(TAG, "NfMap is not initialized.");
            return true;
        }
        if (sMap.getProfileState() <= 110) {
            disconnected = true;
        }
        return disconnected;
    }

    private boolean isSppDisconnected() {
        boolean disconnected = false;
        if (sSpp == null) {
            NfLog.e(TAG, "NfSpp is not initialized.");
            return true;
        }
        if (!sSpp.hasAnyConnectedConntetion()) {
            disconnected = true;
        }
        return disconnected;
    }

    private void checkIsNeedInterrupt() throws BasicConnectInterruptException {
        if (sIsNeedInterrupt) {
            throw new BasicConnectInterruptException("isNeedInterrupt is trigger !!");
        }
        if (!NfPrimitive.isAdapterEnabled()) {
            throw new BasicConnectInterruptException("BluetoothAdapter is disabled !!");
        }
        if (isAutoConnect() && NfPrimitive.isDiscovering()) {
            throw new BasicConnectInterruptException("isAutoConnect and isDiscovering !!");
        }
        if (NfConfig.isEnableHfp() && sHfp == null) {
            throw new BasicConnectInterruptException("HFP Profile is disabled !!");
        }
        if (NfConfig.isEnableA2dp() && sA2dp == null) {
            throw new BasicConnectInterruptException("A2DP Profile is disabled !!");
        }
        if (NfConfig.isEnableAvrcp() && sAvrcp == null) {
            throw new BasicConnectInterruptException("AVRCP Profile is disabled !!");
        }
    }

    public static void setInterrupt() {
        NfLog.d(TAG, "setInterrupt");
        if (sIsConnecting) {
            sIsNeedInterrupt = true;
            try {
                sThread.interrupt();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getNeedConnectMask(String address, _NfHfp hfp, _NfA2dp a2dp, _NfAvrcp avrcp) {
        NfLog.d(TAG, "getNeedConnectMask() " + address);
        int result = 0;
        if (NfConfig.isEnableHfp()) {
            if (hfp != null && !hfp.getConnectedAddress().equals(address)) {
                result = 0 | 1;
            }
        } else {
            NfLog.e(TAG, "NfConfig didn't support HFP profile!");
        }
        if (NfConfig.isEnableA2dp()) {
            if (a2dp != null && !a2dp.getConnectedAddress().equals(address)) {
                result |= 2;
            }
        } else {
            NfLog.e(TAG, "NfConfig didn't support A2DP profile!");
        }
        if (NfConfig.isEnableAvrcp()) {
            if (avrcp != null && !avrcp.getConnectedAddress().equals(address)) {
                result |= 4;
            }
        } else {
            NfLog.e(TAG, "NfConfig didn't support AVRCP profile!");
        }
        NfLog.d(TAG, "getNeedConnectMask() " + address + " mask: " + result);
        return result;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public boolean isAutoConnect() {
        return this.mIsAutoConnect;
    }

    public int getBasicConnectState() {
        return this.mState;
    }

    private void setState(int state) {
        this.mPrevState = this.mState;
        this.mState = state;
        NfLog.d(TAG, "setState() " + this.mAddress + " " + this.mPrevState + " -> " + this.mState);
    }

    private void setState(String addr, int state) {
        this.mPrevState = this.mState;
        this.mState = state;
        NfLog.d(TAG, "setState() " + addr + " " + this.mPrevState + " -> " + this.mState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class BasicConnectInterruptException extends Exception {
        public BasicConnectInterruptException(String msg) {
            super(msg);
            NfLog.e(BasicConnectThread.TAG, "BasicConnectInterruptException: " + msg);
        }
    }
}
