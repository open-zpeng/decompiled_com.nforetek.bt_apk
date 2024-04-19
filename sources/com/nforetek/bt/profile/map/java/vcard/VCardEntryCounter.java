package com.nforetek.bt.profile.map.java.vcard;
/* loaded from: classes.dex */
public class VCardEntryCounter implements VCardInterpreter {
    private int mCount;

    public int getCount() {
        return this.mCount;
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardInterpreter
    public void onVCardStarted() {
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardInterpreter
    public void onVCardEnded() {
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardInterpreter
    public void onEntryStarted() {
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardInterpreter
    public void onEntryEnded() {
        this.mCount++;
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardInterpreter
    public void onPropertyCreated(VCardProperty property) {
    }
}
