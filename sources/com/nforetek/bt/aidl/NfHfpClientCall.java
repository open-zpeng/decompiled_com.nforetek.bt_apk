package com.nforetek.bt.aidl;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class NfHfpClientCall implements Parcelable {
    public static final int CALL_STATE_ACTIVE = 0;
    public static final int CALL_STATE_ALERTING = 3;
    public static final int CALL_STATE_DIALING = 2;
    public static final int CALL_STATE_HELD = 1;
    public static final int CALL_STATE_HELD_BY_RESPONSE_AND_HOLD = 6;
    public static final int CALL_STATE_INCOMING = 4;
    public static final int CALL_STATE_TERMINATED = 7;
    public static final int CALL_STATE_WAITING = 5;
    public static final Parcelable.Creator<NfHfpClientCall> CREATOR = new Parcelable.Creator<NfHfpClientCall>() { // from class: com.nforetek.bt.aidl.NfHfpClientCall.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NfHfpClientCall createFromParcel(Parcel in) {
            return new NfHfpClientCall(in.readInt(), in.readInt(), in.readString(), in.readInt() == 1, in.readInt() == 1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NfHfpClientCall[] newArray(int size) {
            return new NfHfpClientCall[size];
        }
    };
    private final int mId;
    private boolean mMultiParty;
    private String mNumber;
    private final boolean mOutgoing;
    private int mState;

    public NfHfpClientCall(int id, int state, String number, boolean multiParty, boolean outgoing) {
        this.mId = id;
        this.mState = state;
        this.mNumber = number == null ? "" : number;
        this.mMultiParty = multiParty;
        this.mOutgoing = outgoing;
    }

    public void setState(int state) {
        this.mState = state;
    }

    public void setNumber(String number) {
        this.mNumber = number;
    }

    public void setMultiParty(boolean multiParty) {
        this.mMultiParty = multiParty;
    }

    public int getId() {
        return this.mId;
    }

    public int getState() {
        return this.mState;
    }

    public String getNumber() {
        return this.mNumber;
    }

    public boolean isMultiParty() {
        return this.mMultiParty;
    }

    public boolean isOutgoing() {
        return this.mOutgoing;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("NfHeadsetClientCall{mId: ");
        builder.append(this.mId);
        builder.append(", mState: ");
        switch (this.mState) {
            case 0:
                builder.append("ACTIVE");
                break;
            case 1:
                builder.append("HELD");
                break;
            case 2:
                builder.append("DIALING");
                break;
            case 3:
                builder.append("ALERTING");
                break;
            case 4:
                builder.append("INCOMING");
                break;
            case 5:
                builder.append("WAITING");
                break;
            case 6:
                builder.append("HELD_BY_RESPONSE_AND_HOLD");
                break;
            case 7:
                builder.append("TERMINATED");
                break;
            default:
                builder.append(this.mState);
                break;
        }
        builder.append(", mNumber: ");
        builder.append(this.mNumber);
        builder.append(", mMultiParty: ");
        builder.append(this.mMultiParty);
        builder.append(", mOutgoing: ");
        builder.append(this.mOutgoing);
        builder.append("}");
        return builder.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.mId);
        out.writeInt(this.mState);
        out.writeString(this.mNumber);
        out.writeInt(this.mMultiParty ? 1 : 0);
        out.writeInt(this.mOutgoing ? 1 : 0);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
