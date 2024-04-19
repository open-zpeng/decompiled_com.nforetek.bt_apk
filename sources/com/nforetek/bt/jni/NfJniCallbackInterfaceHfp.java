package com.nforetek.bt.jni;

import com.nforetek.bt.aidl.NfHfpClientCall;
/* loaded from: classes.dex */
public interface NfJniCallbackInterfaceHfp {
    void onJniHfpAgEvent(String str, int i, int i2, int i3, int i4, String str2, int i5, int i6, String str3);

    void onJniHfpAudioStateChanged(String str, int i, int i2);

    void onJniHfpCallChanged(NfHfpClientCall nfHfpClientCall);

    void onJniHfpConnectionStateChanged(String str, int i, int i2);
}
