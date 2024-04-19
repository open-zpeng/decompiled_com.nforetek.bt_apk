package com.nforetek.bt.jni;
/* loaded from: classes.dex */
public interface NfJniCallbackInterfaceAvrcp {
    void onJniAvrcpBrowseStateChanged(int i, byte[] bArr);

    void onJniAvrcpGetElementAttributes(int[] iArr, String[] strArr);

    void onJniAvrcpRegisterEventCallbackPlaybackPosChanged(int i);

    void onJniAvrcpRegisterEventCallbackPlaybackStatusChanged(byte b);

    void onJniAvrcpRegisterEventCallbackSettingChanged(int i, byte[] bArr, byte[] bArr2);

    void onJniAvrcpRegisterEventCallbackTrackChanged(byte[] bArr);

    void onJniAvrcpRegisterEventWatcherSuccess(byte b);

    void onJniAvrcpRegisterEventWatcherSuccessPlaybackStatusChanged(byte b, byte b2);

    void onJniAvrcpRetCapabilitiesSupportEvent(int i, byte[] bArr);

    void onJniAvrcpStateChanged(int i, byte[] bArr);

    void retJniAvrcp13PlayStatus(long j, long j2, byte b);

    void retJniAvrcp13PlayerSettingAttributesList(byte[] bArr);

    void retJniAvrcp13PlayerSettingCurrentValues(byte[] bArr, byte[] bArr2);

    void retJniAvrcp13PlayerSettingValuesList(byte b, byte[] bArr);

    void retJniAvrcp13SetPlayerSettingValueSuccess(int i);

    void retJniAvrcp14AddToNowPlaying(byte b);

    void retJniAvrcp14ChangePath(byte b, int i);

    void retJniAvrcp14FolderItem(byte b, byte b2, byte[] bArr, int i, byte[] bArr2, long[] jArr, byte[] bArr3, byte[] bArr4, char[] cArr, String[] strArr, boolean z);

    void retJniAvrcp14GetItemAttributes(byte b, byte b2, int i, byte[] bArr, char[] cArr, String[] strArr);

    void retJniAvrcp14MediaItem(byte b, byte b2, byte[] bArr, int i, byte[] bArr2, long[] jArr, byte[] bArr3, char[] cArr, String[] strArr, boolean z);

    void retJniAvrcp14MediaItemElementAttribute(byte[] bArr, long j, int i, int i2, String str);

    void retJniAvrcp14MediaPlayerItem(byte b, byte b2, byte[] bArr, int i, byte[] bArr2, char[] cArr, byte[] bArr3, int[] iArr, byte[] bArr4, char[] cArr2, String[] strArr, boolean z);

    void retJniAvrcp14PlayItem(byte b);

    void retJniAvrcp14SearchString(byte b, int i);

    void retJniAvrcp14SetBrowsedPlayer(byte b, byte b2, byte[] bArr, int i, char c, byte b3, String[] strArr);

    void retJniAvrcpQueryVersion(int i);
}
