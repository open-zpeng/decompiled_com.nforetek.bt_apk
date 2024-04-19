package com.nforetek.bt.profile.pbap.java.vcard;

import android.accounts.Account;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class VCardEntryConstructor implements VCardInterpreter {
    private static String LOG_TAG = "vCard";
    private final Account mAccount;
    private VCardEntry mCurrentEntry;
    private final List<VCardEntryHandler> mEntryHandlers;
    private final List<VCardEntry> mEntryStack;
    private final int mVCardType;

    public VCardEntryConstructor() {
        this(-1073741824, null, null);
    }

    public VCardEntryConstructor(int vcardType) {
        this(vcardType, null, null);
    }

    public VCardEntryConstructor(int vcardType, Account account) {
        this(vcardType, account, null);
    }

    @Deprecated
    public VCardEntryConstructor(int vcardType, Account account, String targetCharset) {
        this.mEntryStack = new ArrayList();
        this.mEntryHandlers = new ArrayList();
        this.mVCardType = vcardType;
        this.mAccount = account;
    }

    public void addEntryHandler(VCardEntryHandler entryHandler) {
        this.mEntryHandlers.add(entryHandler);
    }

    @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardInterpreter
    public void onVCardStarted() {
        for (VCardEntryHandler entryHandler : this.mEntryHandlers) {
            entryHandler.onStart();
        }
    }

    @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardInterpreter
    public void onVCardEnded() {
        for (VCardEntryHandler entryHandler : this.mEntryHandlers) {
            entryHandler.onEnd();
        }
    }

    public void clear() {
        this.mCurrentEntry = null;
        this.mEntryStack.clear();
    }

    @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardInterpreter
    public void onEntryStarted() {
        this.mCurrentEntry = new VCardEntry(this.mVCardType, this.mAccount);
        this.mEntryStack.add(this.mCurrentEntry);
    }

    @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardInterpreter
    public void onEntryEnded() {
        this.mCurrentEntry.consolidateFields();
        for (VCardEntryHandler entryHandler : this.mEntryHandlers) {
            entryHandler.onEntryCreated(this.mCurrentEntry);
        }
        int size = this.mEntryStack.size();
        if (size > 1) {
            VCardEntry parent = this.mEntryStack.get(size - 2);
            parent.addChild(this.mCurrentEntry);
            this.mCurrentEntry = parent;
        } else {
            this.mCurrentEntry = null;
        }
        this.mEntryStack.remove(size - 1);
    }

    @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardInterpreter
    public void onPropertyCreated(VCardProperty property) {
        this.mCurrentEntry.addProperty(property);
    }
}
