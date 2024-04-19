package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.BluetoothMapMessage;
import com.nforetek.bt.profile.map.java.vcard.VCardEntry;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class BluetoothMapBmessage {
    String mBbodyCharset;
    String mBbodyEncoding;
    String mBbodyLanguage;
    int mBbodyLength;
    String mBmsgFolder;
    Status mBmsgStatus;
    BluetoothMapMessage.Type mBmsgType;
    String mBmsgVersion;
    public String mHandle;
    String mMessage;
    ArrayList<VCardEntry> mOriginators = new ArrayList<>();
    ArrayList<VCardEntry> mRecipients = new ArrayList<>();

    /* loaded from: classes.dex */
    public enum Status {
        READ,
        UNREAD;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static Status[] valuesCustom() {
            Status[] valuesCustom = values();
            int length = valuesCustom.length;
            Status[] statusArr = new Status[length];
            System.arraycopy(valuesCustom, 0, statusArr, 0, length);
            return statusArr;
        }
    }

    public VCardEntry getOriginator() {
        if (this.mOriginators.size() > 0) {
            return this.mOriginators.get(0);
        }
        return null;
    }

    public ArrayList<VCardEntry> getOriginators() {
        return this.mOriginators;
    }

    public BluetoothMapBmessage addOriginator(VCardEntry vcard) {
        this.mOriginators.add(vcard);
        return this;
    }

    public ArrayList<VCardEntry> getRecipients() {
        return this.mRecipients;
    }

    public BluetoothMapBmessage addRecipient(VCardEntry vcard) {
        this.mRecipients.add(vcard);
        return this;
    }

    public Status getStatus() {
        return this.mBmsgStatus;
    }

    public BluetoothMapBmessage setStatus(Status status) {
        this.mBmsgStatus = status;
        return this;
    }

    public BluetoothMapMessage.Type getType() {
        return this.mBmsgType;
    }

    public BluetoothMapBmessage setType(BluetoothMapMessage.Type type) {
        this.mBmsgType = type;
        return this;
    }

    public String getFolder() {
        return this.mBmsgFolder;
    }

    public BluetoothMapBmessage setFolder(String folder) {
        this.mBmsgFolder = folder;
        return this;
    }

    public String getEncoding() {
        return this.mBbodyEncoding;
    }

    public BluetoothMapBmessage setEncoding(String encoding) {
        this.mBbodyEncoding = encoding;
        return this;
    }

    public String getCharset() {
        return this.mBbodyCharset;
    }

    public BluetoothMapBmessage setCharset(String charset) {
        this.mBbodyCharset = charset;
        return this;
    }

    public String getLanguage() {
        return this.mBbodyLanguage;
    }

    public BluetoothMapBmessage setLanguage(String language) {
        this.mBbodyLanguage = language;
        return this;
    }

    public String getBodyContent() {
        return this.mMessage;
    }

    public BluetoothMapBmessage setBodyContent(String body) {
        this.mMessage = body;
        return this;
    }

    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("status", this.mBmsgStatus);
            json.put("type", this.mBmsgType);
            json.put("folder", this.mBmsgFolder);
            json.put("charset", this.mBbodyCharset);
            json.put("message", this.mMessage);
        } catch (JSONException e) {
        }
        return json.toString();
    }
}
