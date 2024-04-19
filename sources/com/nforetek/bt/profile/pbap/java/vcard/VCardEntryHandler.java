package com.nforetek.bt.profile.pbap.java.vcard;
/* loaded from: classes.dex */
public interface VCardEntryHandler {
    void onEnd();

    void onEntryCreated(VCardEntry vCardEntry);

    void onStart();
}
