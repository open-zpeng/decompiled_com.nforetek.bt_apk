package com.nforetek.bt.jni;
/* loaded from: classes.dex */
public interface NfJniCallbackInterfaceMap {
    void onJniMapChangeReadStatusState(String str, String str2, int i);

    void onJniMapDeleteMessageState(String str, String str2, int i);

    void onJniMapMemoryAvailable(String str, int i, int i2, int i3);

    void onJniMapMessageDeleted(String str, String str2, int i, int i2);

    void onJniMapMessageDeliverStatus(String str, String str2, int i);

    void onJniMapMessageSendingStatus(String str, String str2, int i);

    void onJniMapMessageShifted(String str, String str2, int i, int i2, int i3);

    void onJniMapSendMessage(String str, String str2, int i);

    void onJniMapStateChanged(String str, int i, int i2);

    void onJniReceiveMessageContent(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, String str7, String str8, int i3, int i4, int i5);

    void onReceiveNewMessage(String str, String str2, int i, int i2);
}
