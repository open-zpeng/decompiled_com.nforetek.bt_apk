package com.nforetek.bt.profile.pbap.java;

import android.accounts.Account;
import com.nforetek.bt.profile.pbap.java.vcard.VCardEntry;
import com.nforetek.bt.profile.pbap.java.vcard.VCardEntryConstructor;
import com.nforetek.bt.profile.pbap.java.vcard.VCardEntryCounter;
import com.nforetek.bt.profile.pbap.java.vcard.VCardEntryHandler;
import com.nforetek.bt.profile.pbap.java.vcard.VCardParser;
import com.nforetek.bt.profile.pbap.java.vcard.VCardParser_V30;
import com.nforetek.bt.profile.pbap.java.vcard.exception.VCardException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BluetoothPbapVcardList {
    private final Account mAccount;
    private BluetoothPbapRequestPullPhoneBook mBluetoothPbapRequestPullPhoneBook;
    private final ArrayList<VCardEntry> mCards = new ArrayList<>();
    private VCardParser parser;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class CardEntryHandler implements VCardEntryHandler {
        CardEntryHandler() {
        }

        @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardEntryHandler
        public void onStart() {
        }

        @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardEntryHandler
        public void onEntryCreated(VCardEntry entry) {
            BluetoothPbapVcardList.this.mBluetoothPbapRequestPullPhoneBook.callbackVCardEntry(entry);
        }

        @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardEntryHandler
        public void onEnd() {
            BluetoothPbapVcardList.this.mBluetoothPbapRequestPullPhoneBook.downloadingFinish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothPbapVcardList(BluetoothPbapRequestPullPhoneBook bluetoothPbapRequestPullPhoneBook, Account account, InputStream in, byte format) throws IOException {
        this.mBluetoothPbapRequestPullPhoneBook = bluetoothPbapRequestPullPhoneBook;
        this.mAccount = account;
    }

    public void parse(InputStream in, byte format, boolean isCancel) throws IOException {
        this.parser = new VCardParser_V30();
        if (isCancel) {
            this.parser.cancel();
        }
        VCardEntryConstructor constructor = new VCardEntryConstructor(-1073741824, this.mAccount);
        VCardEntryCounter counter = new VCardEntryCounter();
        CardEntryHandler handler = new CardEntryHandler();
        constructor.addEntryHandler(handler);
        this.parser.addInterpreter(constructor);
        this.parser.addInterpreter(counter);
        try {
            this.parser.parse(in);
        } catch (VCardException e) {
            e.printStackTrace();
        }
    }

    public void onCancelDownload() {
        if (this.parser != null) {
            this.parser.cancel();
        }
    }

    public int getCount() {
        return this.mCards.size();
    }
}
