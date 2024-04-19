package com.nforetek.bt.profile.bluetooth;
/* loaded from: classes.dex */
public interface AutoConnectListener {
    int doBasicConnect(String str, boolean z, boolean z2, boolean z3);

    boolean isA2dpConnected();

    boolean isA2dpConnecting();

    boolean isAvrcpConnected();

    boolean isAvrcpConnecting();

    boolean isHfpConnected();

    boolean isHfpConnecting();

    void onBtAutoConnectStateChanged(String str, int i, int i2);
}
