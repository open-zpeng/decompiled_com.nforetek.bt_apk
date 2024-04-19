package com.nforetek.bt.profile.spp;

import com.nforetek.bt.callback.NfDoCallbackSpp;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class SppDoCallBack {
    private static final boolean D = true;
    private static final String TAG = "nFore_SppDoCallBack";
    NfDoCallbackSpp mCallback;

    public SppDoCallBack(NfDoCallbackSpp cb) {
        NfLog.e(TAG, "SppDoCallBack()");
        this.mCallback = cb;
    }

    public void onDestroy() {
        this.mCallback = null;
    }

    public synchronized void onSppStateChanged(String address, String deviceName, int prevState, int newState) {
        if (this.mCallback != null) {
            this.mCallback.onSppStateChanged(address, deviceName, prevState, newState);
        }
    }

    public synchronized void onSppErrorResponse(String address, int errorCode) {
        if (this.mCallback != null) {
            this.mCallback.onSppErrorResponse(address, errorCode);
        }
    }

    public synchronized void retSppConnectedDeviceAddressList(int totalNum, String[] addressList, String[] nameList) {
        if (this.mCallback != null) {
            this.mCallback.retSppConnectedDeviceAddressList(totalNum, addressList, nameList);
        }
    }

    public synchronized void onSppDataReceived(String address, byte[] receivedData) {
        if (this.mCallback != null) {
            this.mCallback.onSppDataReceived(address, receivedData);
        }
    }

    public synchronized void onSppSendData(String address, int length) {
        if (this.mCallback != null) {
            this.mCallback.onSppSendData(address, length);
        }
    }

    public synchronized void onSppAppleIapAuthenticationRequest(String address) {
        if (this.mCallback != null) {
            this.mCallback.onSppAppleIapAuthenticationRequest(address);
        }
    }
}
