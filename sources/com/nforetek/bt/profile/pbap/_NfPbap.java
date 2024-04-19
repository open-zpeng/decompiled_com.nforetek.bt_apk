package com.nforetek.bt.profile.pbap;

import com.nforetek.bt.aidl.INfCallbackPbap;
import com.nforetek.bt.callback.NfDoCallbackPbap;
import com.nforetek.bt.jni.NfJniCallbackInterfacePbap;
import com.nforetek.bt.profile.NfPbapCallbackInterface;
import com.nforetek.bt.profile._NfProfile;
/* loaded from: classes.dex */
public abstract class _NfPbap extends _NfProfile<INfCallbackPbap, NfDoCallbackPbap, NfPbapCallbackInterface> implements NfJniCallbackInterfacePbap {
    public abstract void cleanDatabase();

    public abstract void deleteDatabase(String str);

    public abstract boolean downloadByPass(String str, int i, int i2, int i3, int i4);

    public abstract boolean downloadInterrupt();

    public abstract boolean downloadInterrupt(String str);

    public abstract boolean downloadToDb(String str, int i, int i2, int i3, int i4);

    public abstract boolean downloadToProvider(String str, int i, int i2, int i3, int i4);

    public abstract String getDownloadingAddress();

    public abstract boolean isDownloading();

    public abstract void queryNameByNumber(String str, String str2);

    public abstract void queryNameByPartial(String str, String str2, int i);

    public abstract void reqDatabaseAvailable(String str);

    public abstract boolean setPbapDownloadNotify(int i);
}
