package com.nforetek.bt.profile;

import com.nforetek.bt.manager.NfCallbackInterface;
/* loaded from: classes.dex */
public interface NfBluetoothCallbackInterface extends NfCallbackInterface {
    boolean isAlreadyQueueForScan();

    void onAdapterDiscoveryFinished();

    void onAdapterDiscoveryStarted();

    void onBtAclStateChanged(String str, int i, int i2);

    void onBtAdapterStateChanged(int i, int i2);

    void onBtDeviceBondStateChanged(String str, String str2, int i, int i2);

    void onBtOff();

    void onBtOn();

    void onBtRecreateBond(String str, int i);

    void onBtRemoteDeviceOutOfRange(String str);

    void onBtStartScan();

    void onBtSwitchToCarMode(String str);

    void onBtTurningOff();

    void onBtTurningOn();

    void onJniInitFinished(boolean z);

    boolean tryToQueueForScan();
}
