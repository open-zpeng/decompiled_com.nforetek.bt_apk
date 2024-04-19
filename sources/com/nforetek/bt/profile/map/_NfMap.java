package com.nforetek.bt.profile.map;

import com.nforetek.bt.aidl.INfCallbackMap;
import com.nforetek.bt.callback.NfDoCallbackMap;
import com.nforetek.bt.jni.NfJniCallbackInterfaceMap;
import com.nforetek.bt.profile.NfMapCallbackInterface;
import com.nforetek.bt.profile._NfProfile;
/* loaded from: classes.dex */
public abstract class _NfMap extends _NfProfile<INfCallbackMap, NfDoCallbackMap, NfMapCallbackInterface> implements NfJniCallbackInterfaceMap {
    public abstract boolean changeReadStatus(String str, int i, String str2, boolean z);

    public abstract boolean cleanDatabase();

    public abstract boolean deleteDatabaseByAddress(String str);

    public abstract boolean deleteMessage(String str, int i, String str2);

    public abstract boolean downloadAll(String str, int i, boolean z, int i2, int i3, int i4, String str2, String str3, String str4, String str5, int i5, int i6);

    public abstract boolean downloadInterrupt(String str);

    public abstract boolean downloadOne(String str, int i, String str2, int i2);

    public abstract boolean forceDisconnect();

    @Override // com.nforetek.bt.profile._NfProfile
    public abstract String getConnectedAddress();

    public abstract String getConnetedAddress();

    public abstract boolean isConnected();

    public abstract boolean isNotificationRegistered();

    public abstract void queryDatabaseAvailable();

    public abstract boolean registerNotification(String str, boolean z);

    public abstract boolean sendMessage(String str, String str2, String str3);

    public abstract void setDownloadNotifyFrequency(int i);
}
