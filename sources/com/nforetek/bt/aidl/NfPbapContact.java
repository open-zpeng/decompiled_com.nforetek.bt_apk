package com.nforetek.bt.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import com.nforetek.bt.profile.pbap.java.vcard.VCardConstants;
/* loaded from: classes.dex */
public class NfPbapContact implements Parcelable {
    public static final int ADDRESS_TYPE_DOM = 1;
    public static final int ADDRESS_TYPE_HOME = 6;
    public static final int ADDRESS_TYPE_INTL = 2;
    public static final int ADDRESS_TYPE_NULL = 0;
    public static final int ADDRESS_TYPE_PARCEL = 4;
    public static final int ADDRESS_TYPE_POSTAL = 3;
    public static final int ADDRESS_TYPE_WORK = 5;
    public static final Parcelable.Creator<NfPbapContact> CREATOR = new Parcelable.Creator<NfPbapContact>() { // from class: com.nforetek.bt.aidl.NfPbapContact.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NfPbapContact createFromParcel(Parcel in) {
            String r1 = in.readString();
            int r2 = in.readInt();
            String r3 = in.readString();
            String r4 = in.readString();
            String r5 = in.readString();
            int[] r6 = in.createIntArray();
            String[] r7 = in.createStringArray();
            int r8 = in.readInt();
            byte[] r9 = in.createByteArray();
            int[] r10 = in.createIntArray();
            String[] r11 = in.createStringArray();
            int[] r12 = in.createIntArray();
            String[] r13 = in.createStringArray();
            String r14 = in.readString();
            return new NfPbapContact(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NfPbapContact[] newArray(int size) {
            return new NfPbapContact[size];
        }
    };
    public static final int EMAIL_TYPE_HOME = 5;
    public static final int EMAIL_TYPE_INTERNET = 2;
    public static final int EMAIL_TYPE_NULL = 0;
    public static final int EMAIL_TYPE_PREF = 1;
    public static final int EMAIL_TYPE_WORK = 4;
    public static final int EMAIL_TYPE_X400 = 3;
    public static final int NUMBER_TYPE_CELL = 7;
    public static final int NUMBER_TYPE_FAX = 5;
    public static final int NUMBER_TYPE_HOME = 3;
    public static final int NUMBER_TYPE_MSG = 6;
    public static final int NUMBER_TYPE_NULL = 0;
    public static final int NUMBER_TYPE_PAGER = 8;
    public static final int NUMBER_TYPE_PREF = 1;
    public static final int NUMBER_TYPE_VOICE = 4;
    public static final int NUMBER_TYPE_WORK = 2;
    public static final int STORAGE_TYPE_CALL_LOGS = 8;
    public static final int STORAGE_TYPE_DIALED_CALLS = 7;
    public static final int STORAGE_TYPE_FAVORITE = 4;
    public static final int STORAGE_TYPE_MISSED_CALLS = 5;
    public static final int STORAGE_TYPE_PHONE_MEMORY = 2;
    public static final int STORAGE_TYPE_RECEIVED_CALLS = 6;
    public static final int STORAGE_TYPE_SIM = 1;
    public static final int STORAGE_TYPE_SPEEDDIAL = 3;
    private final String[] mAddress;
    private final int[] mAddressType;
    private final String[] mEmail;
    private final int[] mEmailType;
    private final String mFirstName;
    private final String mLastName;
    private final String mMiddleName;
    private final String[] mNumber;
    private final String mOrg;
    private final int[] mPhoneType;
    private final byte[] mPhoto;
    private final int mPhotoType;
    private final String mRemoteAddress;
    private final int mStorageType;

    public NfPbapContact(String bt_address, int storage, String firstName, String middleName, String lastName, int[] phoneType, String[] number, int photoType, byte[] photo, int[] emailType, String[] email, int[] addressType, String[] address, String org2) {
        this.mRemoteAddress = bt_address;
        this.mStorageType = storage;
        this.mFirstName = firstName;
        this.mMiddleName = middleName;
        this.mLastName = lastName;
        this.mPhoneType = phoneType;
        this.mNumber = number;
        this.mPhotoType = photoType;
        photo = (photo == null || photo.length <= 0) ? null : null;
        this.mPhoto = photo;
        this.mEmailType = emailType;
        this.mEmail = email;
        this.mAddressType = addressType;
        this.mAddress = address;
        this.mOrg = org2;
    }

    public String getRemoteAddress() {
        return this.mRemoteAddress;
    }

    public int getStorageType() {
        return this.mStorageType;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getMiddleName() {
        return this.mMiddleName;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public int[] getPhoneTypeArray() {
        return this.mPhoneType;
    }

    public String[] getNumberArray() {
        return this.mNumber;
    }

    public int getPhotoType() {
        return this.mPhotoType;
    }

    public byte[] getPhotoByteArray() {
        return this.mPhoto;
    }

    public int[] getEmailTypeArray() {
        return this.mEmailType;
    }

    public String[] getEmailArray() {
        return this.mEmail;
    }

    public int[] getAddressType() {
        return this.mAddressType;
    }

    public String[] getAddressArray() {
        return this.mAddress;
    }

    public String getOrg() {
        return this.mOrg;
    }

    public String toString() {
        int under_line;
        StringBuilder builder = new StringBuilder("===NfPbapContact ");
        switch (this.mStorageType) {
            case 1:
                builder.append("Sim");
                break;
            case 2:
                builder.append("Memory");
                break;
            case 3:
                builder.append("Speed Dial");
                break;
            case 4:
                builder.append("Favorite");
                break;
            case 5:
                builder.append("Missed Calls");
                break;
            case 6:
                builder.append("Received Calls");
                break;
            case 7:
                builder.append("Dialed Calls");
                break;
            case 8:
                builder.append("Combine Calllogs");
                break;
            default:
                builder.append(this.mStorageType);
                break;
        }
        builder.append("===\n   RemoteAddress: ");
        builder.append(String.valueOf(this.mRemoteAddress) + "\n");
        builder.append("   Name: " + this.mFirstName + "|" + this.mMiddleName + "|" + this.mLastName + "\n");
        if (this.mNumber != null) {
            for (int i = 0; i < this.mNumber.length; i++) {
                builder.append("   Number: (" + getNumberTypeString(this.mPhoneType[i]) + ") " + this.mNumber[i] + "\n");
            }
        }
        if (this.mPhoto != null && this.mPhoto.length > 0) {
            builder.append("   Photo: yes\n");
        } else {
            builder.append("   Photo: no\n");
        }
        if (this.mEmail != null) {
            for (int i2 = 0; i2 < this.mEmail.length; i2++) {
                builder.append("   e-mail: (" + getEmailTypeString(this.mEmailType[i2]) + ") " + this.mEmail[i2] + "\n");
            }
        }
        if (this.mAddress != null) {
            for (int i3 = 0; i3 < this.mAddress.length; i3++) {
                builder.append("   Address: (" + getAddressTypeString(this.mAddressType[i3]) + ") " + this.mAddress[i3] + "\n");
            }
        }
        if (this.mOrg != null) {
            builder.append("   Org: " + this.mOrg + "\n");
        }
        switch (this.mStorageType) {
            case 1:
                under_line = 20 + 3;
                break;
            case 2:
                under_line = 20 + 6;
                break;
            case 3:
                under_line = 20 + 10;
                break;
            case 4:
                under_line = 20 + 8;
                break;
            case 5:
                under_line = 20 + 12;
                break;
            case 6:
                under_line = 20 + 14;
                break;
            case 7:
                under_line = 20 + 12;
                break;
            case 8:
                under_line = 20 + 16;
                break;
            default:
                under_line = 20 + 1;
                break;
        }
        for (int i4 = 0; i4 < under_line; i4++) {
            builder.append("=");
        }
        return builder.toString();
    }

    private String getEmailTypeString(int type) {
        switch (type) {
            case 0:
                return "Null";
            case 1:
                return "Pref";
            case 2:
                return "Internet";
            case 3:
                return VCardConstants.PARAM_TYPE_X400;
            case 4:
                return "Work";
            case 5:
                return "Home";
            default:
                return "Unknown";
        }
    }

    private String getAddressTypeString(int type) {
        switch (type) {
            case 0:
                return "Null";
            case 1:
                return "Pref";
            case 2:
                return "International";
            case 3:
                return "Postal";
            case 4:
                return "Parcel";
            case 5:
                return "Work";
            case 6:
                return "Home";
            default:
                return "Unknown";
        }
    }

    private String getNumberTypeString(int type) {
        switch (type) {
            case 0:
                return "Null";
            case 1:
                return "Pref";
            case 2:
                return "Work";
            case 3:
                return "Home";
            case 4:
                return "Voice";
            case 5:
                return "Fax";
            case 6:
                return "Msg";
            case 7:
                return "Cell";
            case 8:
                return "Pager";
            default:
                return "Unknown";
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.mRemoteAddress);
        out.writeInt(this.mStorageType);
        out.writeString(this.mFirstName);
        out.writeString(this.mMiddleName);
        out.writeString(this.mLastName);
        out.writeIntArray(this.mPhoneType);
        out.writeStringArray(this.mNumber);
        out.writeInt(this.mPhotoType);
        out.writeByteArray(this.mPhoto != null ? this.mPhoto : new byte[0]);
        out.writeIntArray(this.mEmailType);
        out.writeStringArray(this.mEmail);
        out.writeIntArray(this.mAddressType);
        out.writeStringArray(this.mAddress);
        out.writeString(this.mOrg);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
