package com.nforetek.bt.jni;
/* loaded from: classes.dex */
public interface NfJniCallbackInterfaceOpp {
    void onJniOppReceiveFileInfo(String str, int i, String str2, String str3);

    void onJniOppReceiveProgress(int i);

    void onJniOppStateChanged(String str, int i, int i2, int i3);
}
