package com.nforetek.bt.jni;
/* loaded from: classes.dex */
public interface NfJniCallbackInterfacePbap {
    void onJniPbapConnectionStateChanged(String str, int i);

    void onJniPbapDownloadStateChanged(String str, int i);

    void retJniPbapReceiveContacts(String str, int i, String str2, String str3, String str4, String str5, int i2, String[] strArr, int[] iArr, String str6, int i3, byte[] bArr, int i4, int[] iArr2, String[] strArr2, int[] iArr3, String[] strArr3, String str7);

    void retJniPbapReceivePhonebookSize(String str, int i, int i2);
}
