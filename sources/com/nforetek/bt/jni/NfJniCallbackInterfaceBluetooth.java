package com.nforetek.bt.jni;
/* loaded from: classes.dex */
public interface NfJniCallbackInterfaceBluetooth {
    void onJniBluetoothAclStateChanged(int i, byte[] bArr, int i2, int i3);

    void onJniBtRoleModeChanged(int i);

    void onJniInitFinished(boolean z);

    void onJniRecreateBondDevice(String str, int i);
}
