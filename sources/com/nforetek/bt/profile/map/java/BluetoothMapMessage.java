package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.utils.ObexTime;
import com.nforetek.util.normal.NfLog;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class BluetoothMapMessage implements Comparable<BluetoothMapMessage> {
    private static final boolean DBG = false;
    private static final String TAG = "BluetoothMapMessage";
    public int mAttachmentSize;
    public Date mDateTime;
    public String mFolder;
    public String mHandle;
    public boolean mPriority;
    public boolean mProtected;
    public boolean mRead;
    public ReceptionStatus mReceptionStatus;
    public String mRecipientAddressing;
    public String mRecipientName;
    public String mReplytoAddressing;
    public String mSenderAddressing;
    public String mSenderName;
    public boolean mSent;
    public int mSize;
    public String mSubject;
    public boolean mText;
    public Type mType;
    public String msg;

    /* loaded from: classes.dex */
    public enum Type {
        UNKNOWN(16),
        EMAIL(8),
        SMS_GSM(1),
        SMS_CDMA(2),
        MMS(4);
        
        private int legIndex;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static Type[] valuesCustom() {
            Type[] valuesCustom = values();
            int length = valuesCustom.length;
            Type[] typeArr = new Type[length];
            System.arraycopy(valuesCustom, 0, typeArr, 0, length);
            return typeArr;
        }

        Type(int legIndex) {
            this.legIndex = legIndex;
        }

        public int value() {
            return this.legIndex;
        }

        public static Type get(int legIndex) {
            Type[] valuesCustom;
            for (Type l : valuesCustom()) {
                if (l.legIndex == legIndex) {
                    return l;
                }
            }
            throw new IllegalArgumentException("Leg not found. Amputated?");
        }
    }

    /* loaded from: classes.dex */
    public enum ReceptionStatus {
        UNKNOWN(0),
        COMPLETE(1),
        FRACTIONED(2),
        NOTIFICATION(3);
        
        private int legIndex;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static ReceptionStatus[] valuesCustom() {
            ReceptionStatus[] valuesCustom = values();
            int length = valuesCustom.length;
            ReceptionStatus[] receptionStatusArr = new ReceptionStatus[length];
            System.arraycopy(valuesCustom, 0, receptionStatusArr, 0, length);
            return receptionStatusArr;
        }

        ReceptionStatus(int legIndex) {
            this.legIndex = legIndex;
        }

        public int value() {
            return this.legIndex;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothMapMessage() {
        this.msg = "";
        this.mSenderName = "unknown";
        this.mSenderAddressing = "unknown";
        this.mRecipientAddressing = "unknown";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothMapMessage(HashMap<String, String> attrs) throws IllegalArgumentException {
        int size;
        int size2;
        this.msg = "";
        NfLog.d(false, TAG, attrs.toString());
        try {
            new BigInteger(attrs.get("handle"), 16);
            this.mHandle = attrs.get("handle");
            this.mSubject = attrs.get("subject");
            String dateTime = attrs.get("datetime");
            if (dateTime != null) {
                NfLog.d(false, TAG, "dateTime " + dateTime);
                this.mDateTime = new ObexTime(dateTime).getTime();
            } else {
                this.mDateTime = null;
            }
            this.mSenderName = attrs.get("sender_name");
            if (this.mSenderName == null || this.mSenderName.length() == 0) {
                this.mSenderName = "unknown";
            }
            this.mSenderAddressing = attrs.get("sender_addressing");
            if (this.mSenderAddressing == null || this.mSenderAddressing.length() == 0) {
                this.mSenderAddressing = "unknown";
            }
            this.mRecipientAddressing = attrs.get("recipient_addressing");
            if (this.mRecipientAddressing == null || this.mRecipientAddressing.length() == 0) {
                this.mRecipientAddressing = "unknown";
            }
            this.mType = strToType(attrs.get("type"));
            try {
                size = Integer.parseInt(attrs.get("size"));
            } catch (NumberFormatException e) {
                size = 999;
            }
            this.mSize = size;
            this.mText = yesnoToBoolean(attrs.get("text"));
            this.mReceptionStatus = strToReceptionStatus(attrs.get("reception_status"));
            try {
                size2 = Integer.parseInt(attrs.get("attachment_size"));
            } catch (NumberFormatException e2) {
                size2 = 0;
            }
            this.mAttachmentSize = size2;
            this.mPriority = yesnoToBoolean(attrs.get("priority"));
            this.mRead = yesnoToBoolean(attrs.get("read"));
            this.mSent = yesnoToBoolean(attrs.get("sent"));
            this.mProtected = yesnoToBoolean(attrs.get("protected"));
            NfLog.d(false, TAG, toString());
        } catch (NumberFormatException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    private boolean yesnoToBoolean(String yesno) {
        return "yes".equals(yesno);
    }

    private Type strToType(String s) {
        if ("EMAIL".equals(s)) {
            return Type.EMAIL;
        }
        if ("SMS_GSM".equals(s)) {
            return Type.SMS_GSM;
        }
        if ("SMS_CDMA".equals(s)) {
            return Type.SMS_CDMA;
        }
        if ("MMS".equals(s)) {
            return Type.MMS;
        }
        return Type.UNKNOWN;
    }

    private ReceptionStatus strToReceptionStatus(String s) {
        if ("complete".equals(s)) {
            return ReceptionStatus.COMPLETE;
        }
        if ("fractioned".equals(s)) {
            return ReceptionStatus.FRACTIONED;
        }
        if ("notification".equals(s)) {
            return ReceptionStatus.NOTIFICATION;
        }
        return ReceptionStatus.UNKNOWN;
    }

    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("handle", this.mHandle);
            json.put("subject", this.mSubject);
            json.put("datetime", this.mDateTime);
            json.put("sender_name", this.mSenderName);
            json.put("sender_addressing", this.mSenderAddressing);
            json.put("replyto_addressing", this.mReplytoAddressing);
            json.put("recipient_name", this.mRecipientName);
            json.put("recipient_addressing", this.mRecipientAddressing);
            json.put("type", this.mType);
            json.put("size", this.mSize);
            json.put("text", this.mText);
            json.put("reception_status", this.mReceptionStatus);
            json.put("attachment_size", this.mAttachmentSize);
            json.put("priority", this.mPriority);
            json.put("read", this.mRead);
            json.put("sent", this.mSent);
            json.put("protected", this.mProtected);
        } catch (JSONException e) {
        }
        return json.toString();
    }

    public String getHandle() {
        return this.mHandle;
    }

    public String getSubject() {
        return this.mSubject;
    }

    public Date getDateTime() {
        return this.mDateTime;
    }

    public String getSenderName() {
        return this.mSenderName;
    }

    public String getSenderAddressing() {
        return this.mSenderAddressing;
    }

    public String getReplytoAddressing() {
        return this.mReplytoAddressing;
    }

    public String getRecipientName() {
        return this.mRecipientName;
    }

    public String getRecipientAddressing() {
        return this.mRecipientAddressing;
    }

    public Type getType() {
        return this.mType;
    }

    public int getSize() {
        return this.mSize;
    }

    public ReceptionStatus getReceptionStatus() {
        return this.mReceptionStatus;
    }

    public int getAttachmentSize() {
        return this.mAttachmentSize;
    }

    public boolean isText() {
        return this.mText;
    }

    public boolean isPriority() {
        return this.mPriority;
    }

    public boolean isRead() {
        return this.mRead;
    }

    public boolean isSent() {
        return this.mSent;
    }

    public boolean isProtected() {
        return this.mProtected;
    }

    @Override // java.lang.Comparable
    public int compareTo(BluetoothMapMessage arg0) {
        if (getDateTime() == null) {
            NfLog.d(TAG, "compareTo fail, date null");
            return -1;
        } else if (arg0.getDateTime() == null) {
            NfLog.d(TAG, "compareTo fail, date null");
            return 1;
        } else {
            return getDateTime().compareTo(arg0.getDateTime());
        }
    }
}
