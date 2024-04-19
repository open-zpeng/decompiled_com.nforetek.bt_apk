package com.nforetek.bt.profile.pbap.java.vcard;
/* loaded from: classes.dex */
public interface VCardInterpreter {
    void onEntryEnded();

    void onEntryStarted();

    void onPropertyCreated(VCardProperty vCardProperty);

    void onVCardEnded();

    void onVCardStarted();
}
