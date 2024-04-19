package com.nforetek.bt.profile.bluetooth;

import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class AutoConnectThread extends Thread {
    private AutoConnectListener mListener;
    private String TAG = "AutoConnectThread";
    private boolean mIsNeedConnectPeriod = false;
    private boolean mIsNeedConnectNonStop = false;
    private long mConnectPeriod = -1;
    private long mConnectStopTime = -1;
    private long mConnectStartTime = -1;
    private String mTargetAddress = NfDef.DEFAULT_ADDRESS;
    private int mAutoConnectType = 0;
    private int mAutoConnectState = 110;

    public AutoConnectThread(AutoConnectListener listener, String address, int autoConnectType, int period) {
        onCreate(listener, address, autoConnectType, period);
    }

    public AutoConnectThread(AutoConnectListener listener, String address, int autoConnectType) {
        onCreate(listener, address, autoConnectType);
    }

    private void onCreate(AutoConnectListener listener, String address, int autoConnectType, int period) {
        if (period > 0) {
            this.mConnectPeriod = period;
            this.mIsNeedConnectPeriod = true;
        } else if (period == 0) {
            this.mIsNeedConnectNonStop = true;
        }
        onCreate(listener, address, autoConnectType);
    }

    private void onCreate(AutoConnectListener listener, String address, int autoConnectType) {
        NfLog.d(this.TAG, "onCreate() address: " + address + " type: " + autoConnectType + " period: " + this.mConnectPeriod);
        this.mTargetAddress = address;
        this.mAutoConnectType = autoConnectType;
        this.mListener = listener;
        switch (this.mAutoConnectType) {
            case 1:
                setAutoConnectState(121);
                break;
            case 2:
                setAutoConnectState(122);
                break;
            case 4:
                setAutoConnectState(123);
                break;
        }
        this.mConnectStartTime = System.currentTimeMillis();
        if (this.mIsNeedConnectPeriod) {
            this.mConnectStopTime = this.mConnectStartTime + this.mConnectPeriod;
        }
    }

    public int getAutoConnectType() {
        return this.mAutoConnectType;
    }

    private boolean isNeedConnectAgain() {
        boolean isNeedAgain = false;
        NfLog.e(this.TAG, "mIsNeedConnectPeriod: " + this.mIsNeedConnectPeriod);
        NfLog.e(this.TAG, "mConnectStopTime: " + this.mConnectStopTime);
        NfLog.e(this.TAG, "System.currentTimeMillis(): " + System.currentTimeMillis());
        if (!NfPrimitive.isBtEnabled()) {
            NfLog.v(this.TAG, "BT is Off !!");
            return false;
        } else if (NfPrimitive.isDiscovering()) {
            NfLog.v(this.TAG, "isDiscovering is true !!");
            return false;
        } else {
            if (!isAnyBasicProfileConnected()) {
                if (this.mIsNeedConnectPeriod) {
                    isNeedAgain = System.currentTimeMillis() < this.mConnectStopTime;
                }
                if (this.mIsNeedConnectNonStop && (this.mAutoConnectType == 1 || this.mAutoConnectType == 4)) {
                    isNeedAgain = true;
                }
            }
            NfLog.v(this.TAG, "isNeedConnectAgain: " + isNeedAgain);
            return isNeedAgain;
        }
    }

    private boolean isAnyProfileConnecting() {
        if (NfConfig.isEnableHfp() && this.mListener != null && this.mListener.isHfpConnecting()) {
            NfLog.v(this.TAG, "HFP is connecting!!");
            return true;
        } else if (NfConfig.isEnableA2dp() && this.mListener != null && this.mListener.isA2dpConnecting()) {
            NfLog.v(this.TAG, "A2DP is connecting!!");
            return true;
        } else if (NfConfig.isEnableAvrcp() && this.mListener != null && this.mListener.isAvrcpConnecting()) {
            NfLog.v(this.TAG, "AVRCP is connecting!!");
            return true;
        } else {
            return false;
        }
    }

    private boolean isAnyProfileConnected() {
        if (NfConfig.isEnableHfp() && this.mListener != null && this.mListener.isHfpConnected()) {
            NfLog.v(this.TAG, "HFP is connected!!");
            return true;
        } else if (NfConfig.isEnableA2dp() && this.mListener != null && this.mListener.isA2dpConnected()) {
            NfLog.v(this.TAG, "A2DP is connected!!");
            return true;
        } else if (NfConfig.isEnableAvrcp() && this.mListener != null && this.mListener.isAvrcpConnected()) {
            NfLog.v(this.TAG, "AVRCP is connected!!");
            return true;
        } else {
            return false;
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        NfLog.d(this.TAG, "AutoConnectThread(" + hashCode() + ") started.");
        if (!NfPrimitive.isDevicePaired(this.mTargetAddress)) {
            NfLog.e(this.TAG, "Device not paired! Stop auto connect thread here.");
            setAutoConnectState(110);
            NfLog.d(this.TAG, "AutoConnectThread(" + hashCode() + ") stopped.");
            return;
        }
        do {
            try {
                Thread.sleep(3000L);
                if (this.mIsNeedConnectPeriod && NfPrimitive.isDiscovering()) {
                    NfLog.v(this.TAG, "isDiscovering is true !!");
                    setAutoConnectState(110);
                    return;
                }
                if (NfPrimitive.isAdapterEnabled() && !this.mTargetAddress.equals(NfDef.DEFAULT_ADDRESS) && !isAnyProfileConnecting() && !isAnyProfileConnected() && this.mListener != null && !NfPrimitive.isWifiConnecting()) {
                    switch (this.mAutoConnectType) {
                        case 2:
                            this.mListener.doBasicConnect(this.mTargetAddress, true, false, true);
                            break;
                        default:
                            this.mListener.doBasicConnect(this.mTargetAddress, true, true, true);
                            break;
                    }
                }
                if (isNeedConnectAgain()) {
                    try {
                        int delayTime = isHighFrequenyInterval() ? NfConfig.getAutoConnectHighFrequencyDelayTime() : NfConfig.getAutoConnectLowFrequencyDelayTime();
                        int delayAfterConnect = (delayTime + 6000) - 3000;
                        Thread.sleep(delayAfterConnect);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        NfLog.d(this.TAG, "AutoConnectThread(" + hashCode() + ") interrupted.");
                        setAutoConnectState(110);
                        return;
                    }
                }
                setAutoConnectState(110);
                NfLog.d(this.TAG, "AutoConnectThread(" + hashCode() + ") stopped.");
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                NfLog.d(this.TAG, "AutoConnectThread(" + hashCode() + ") interrupted.");
                setAutoConnectState(110);
                return;
            }
        } while (isNeedConnectAgain());
        setAutoConnectState(110);
        NfLog.d(this.TAG, "AutoConnectThread(" + hashCode() + ") stopped.");
    }

    public String getTargetAddress() {
        return this.mTargetAddress;
    }

    public int getAutoConnectState() {
        return this.mAutoConnectState;
    }

    public void setAutoConnectState(int state) {
        int tempState = this.mAutoConnectState;
        this.mAutoConnectState = state;
        if (this.mListener != null) {
            this.mListener.onBtAutoConnectStateChanged(this.mTargetAddress, tempState, this.mAutoConnectState);
        }
    }

    private boolean isAnyBasicProfileConnected() {
        if (NfConfig.isEnableHfp() && this.mListener != null && this.mListener.isHfpConnected()) {
            return true;
        }
        if (NfConfig.isEnableA2dp() && this.mListener != null && this.mListener.isA2dpConnected()) {
            return true;
        }
        return NfConfig.isEnableAvrcp() && this.mListener != null && this.mListener.isAvrcpConnected();
    }

    private boolean isHighFrequenyInterval() {
        long startedTime = System.currentTimeMillis() - this.mConnectStartTime;
        return startedTime < ((long) NfConfig.getAutoConnectHighFrequencyInterval());
    }
}
