package com.nforetek.bt.profile.bluetooth;
/* loaded from: classes.dex */
public interface BasicConnectionInterface {
    int basicConnect(String str, boolean z, boolean z2, boolean z3);

    int basicDisconnect();

    int basicDisconnect(String str, boolean z);

    int disconnectA2dpAvrcp();

    int getAutoConnectState();

    boolean isAutoConnecting(boolean z);

    boolean isBasicConnecting();

    boolean isBasicDisconnecting();
}
