package com.nforetek.util.normal;

import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.db.DbHelperPbap;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class ContactsHelper {
    private static final String ADDRESS_DETAIL = "AddressDetail";
    private static final String CALLHISTORY_CONTENT = "CallHistory";
    private static final String EMAIL_DETAIL = "EmailDetail";
    private static final boolean IS_NEED_SET_UNKNOWN_NAME_STRING = true;
    private static final String PHONEBOOK_CONTENT = "PhoneBookContent";
    private static final String PHONENUMBER_DETAIL = "PhoneNumberDetail";
    private static final String TAG = "ContactsHelper";
    private static final String UNKNOWN_NAME_STRING = "Unknown";
    private static long total_time = 0;

    public static void addContactToProvider(ArrayList<ContentProviderOperation> operationList, int backReferenceIndex, Context context, String fullName, String firstName, String middleName, String lastName, String[] number, int[] phoneType, byte[] photoBytes, int[] emailType, String[] email, int[] addressType, String[] address, String org2) {
        Log.d(TAG, "addContacts() " + firstName + "  " + middleName + "  " + lastName + " backReferenceIndex=" + backReferenceIndex);
        ContentProviderOperation.Builder account_builder = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI);
        account_builder.withValue("account_name", null);
        account_builder.withValue("account_type", null);
        operationList.add(account_builder.build());
        ContentProviderOperation.Builder name_builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
        name_builder.withValueBackReference("raw_contact_id", backReferenceIndex);
        name_builder.withValue("mimetype", "vnd.android.cursor.item/name");
        name_builder.withValue("data2", " ");
        name_builder.withValue("data3", " ");
        name_builder.withValue("data5", " ");
        if (fullName != null && fullName.trim().length() > 0) {
            name_builder.withValue("data4", fullName);
        } else {
            name_builder.withValue("data4", String.valueOf(firstName) + middleName + lastName);
        }
        if (number != null && number.length > 0) {
            name_builder.withValue("data1", number[0]);
        } else {
            name_builder.withValue("data1", "No Number");
        }
        operationList.add(name_builder.build());
        if (number != null) {
            for (int i = 0; i < number.length; i++) {
                ContentProviderOperation.Builder phone_builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
                phone_builder.withValueBackReference("raw_contact_id", backReferenceIndex);
                phone_builder.withValue("mimetype", "vnd.android.cursor.item/phone_v2");
                phone_builder.withValue("data2", Integer.valueOf(phoneTypeTo(phoneType[i])));
                phone_builder.withValue("data1", number[i]);
                if (number != null && number.length > 0) {
                    phone_builder.withValue("data4", number[0]);
                } else {
                    phone_builder.withValue("data4", "No Number");
                }
                operationList.add(phone_builder.build());
            }
        }
        if (org2 != null) {
            ContentProviderOperation.Builder org_builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
            org_builder.withValueBackReference("raw_contact_id", backReferenceIndex);
            org_builder.withValue("mimetype", "vnd.android.cursor.item/organization");
            org_builder.withValue("data1", org2);
            if (number != null && number.length > 0) {
                org_builder.withValue("data4", number[0]);
            } else {
                org_builder.withValue("data4", "No Number");
            }
            operationList.add(org_builder.build());
        }
        ContentProviderOperation.Builder photo_builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
        photo_builder.withValueBackReference("raw_contact_id", backReferenceIndex);
        photo_builder.withValue("mimetype", "vnd.android.cursor.item/photo");
        if (number != null && number.length > 0) {
            photo_builder.withValue("data4", number[0]);
        } else {
            photo_builder.withValue("data4", "No Number");
        }
        photo_builder.withValue("data15", photoBytes);
        operationList.add(photo_builder.build());
        if (email != null) {
            for (int i2 = 0; i2 < email.length; i2++) {
                ContentProviderOperation.Builder email_builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
                email_builder.withValueBackReference("raw_contact_id", backReferenceIndex);
                email_builder.withValue("mimetype", "vnd.android.cursor.item/email_v2");
                email_builder.withValue("data2", Integer.valueOf(emailType[i2]));
                email_builder.withValue("data1", email[i2]);
                if (number != null && number.length > 0) {
                    email_builder.withValue("data4", number[0]);
                } else {
                    email_builder.withValue("data4", "No Number");
                }
                operationList.add(email_builder.build());
            }
        }
        if (address != null) {
            for (int i3 = 0; i3 < address.length; i3++) {
                ContentProviderOperation.Builder address_builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
                address_builder.withValueBackReference("raw_contact_id", backReferenceIndex);
                address_builder.withValue("mimetype", "vnd.android.cursor.item/postal-address_v2");
                address_builder.withValue("data2", Integer.valueOf(addressType[i3]));
                address_builder.withValue("data1", address[i3]);
                if (number != null && number.length > 0) {
                    address_builder.withValue("data4", number[0]);
                } else {
                    address_builder.withValue("data4", "No Number");
                }
                operationList.add(address_builder.build());
            }
        }
    }

    public static void addCallLogToProvider(ArrayList<ContentProviderOperation> calllog_ops, int backReferenceIndex, Context context, String fullName, String firstName, String middleName, String lastName, String number, String timestamp, int calllogType) {
        Log.d(TAG, "addCalllog() name:" + firstName + middleName + lastName + " " + number + " " + timestamp);
        int targetType = 0;
        if (calllogType == 7) {
            targetType = 2;
        } else if (calllogType == 5) {
            targetType = 3;
        } else if (calllogType == 6) {
            targetType = 1;
        }
        ContentValues values = new ContentValues();
        values.put("number", number);
        values.put("name", String.valueOf(firstName) + middleName + lastName);
        values.put("date", timestamp);
        values.put("type", Integer.valueOf(targetType));
        ContentProviderOperation operation = ContentProviderOperation.newInsert(CallLog.Calls.CONTENT_URI).withValues(values).build();
        calllog_ops.add(operation);
    }

    private static int phoneTypeTo(int phoneTypeNumber) {
        switch (phoneTypeNumber) {
            case 0:
                return 0;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 1;
            case 4:
                return 7;
            case 5:
                return 4;
            case 6:
                return 20;
            case 7:
                return 2;
            case 8:
                return 6;
            default:
                return 7;
        }
    }

    public static void addContactsToDatabases(Context context, String bd_address, int storageType, String firstName, String middleName, String lastName, String[] number, int[] phoneType, boolean isNeedDelete, byte[] photo, int[] emailType, String[] email, int[] addressType, String[] address, String org2) {
        ContentResolver cr = context.getContentResolver();
        ContentProviderClient cpc = cr.acquireContentProviderClient(NfDef.DEFAULT_AUTHORITIES);
        String packageName = "";
        try {
            packageName = cpc.getType(Uri.parse("content://nfore.db.provider"));
        } catch (RemoteException e1) {
            e1.printStackTrace();
            Log.e(TAG, "getPackageName of DB get Exception");
        }
        Context _context = null;
        try {
            _context = context.createPackageContext(packageName, 2);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "createPackageContext: \"" + packageName + "\", NameNotFoundException");
        }
        NfLog.e(TAG, "addContactsToDatabases()");
        DbHelperPbap dbHelper = new DbHelperPbap(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        db.beginTransaction();
        if (isNeedDelete) {
            int delNum = dbHelper.deleteVcardInfo(db, bd_address, storageType);
            Log.v(TAG, "Delete Contacts count : " + delNum);
        }
        String name = "";
        if (firstName != null) {
            name = String.valueOf(firstName) + " ";
        }
        if (middleName != null) {
            name = String.valueOf(name) + middleName + " ";
        }
        if (lastName != null) {
            name = String.valueOf(name) + lastName;
        }
        cv.put("FullName", name);
        if (firstName == null) {
            firstName = "";
        }
        cv.put("FirstName", firstName);
        if (middleName == null) {
            middleName = "";
        }
        cv.put("MiddleName", middleName);
        if (lastName == null) {
            lastName = "";
        }
        cv.put("LastName", lastName);
        cv.put("CellPhone_Address", bd_address);
        cv.put("StorageType", Integer.valueOf(storageType));
        cv.put("Org", org2);
        long row = db.insert(PHONEBOOK_CONTENT, null, cv);
        String photoPath = NfUtils.createPhotoFile(new StringBuilder().append(row).toString(), photo);
        cv.clear();
        cv.put("PhotoPath", photoPath);
        String where = "_id = " + row;
        db.update(PHONEBOOK_CONTENT, cv, where, null);
        if (number != null) {
            for (int i = 0; i < number.length; i++) {
                ContentValues _cv = new ContentValues();
                String typeString = getPhoneType(phoneType[i]);
                _cv.put("Content_ID", Long.valueOf(row));
                _cv.put("Type", typeString);
                String num = filterNumber(number[i]);
                _cv.put("Number", num);
                db.insert(PHONENUMBER_DETAIL, null, _cv);
            }
        }
        if (email != null) {
            for (int i2 = 0; i2 < email.length; i2++) {
                NfLog.d(TAG, "EmailType[" + i2 + "]: " + emailType[i2]);
                NfLog.d(TAG, "Email[" + i2 + "]: " + email[i2]);
                ContentValues _cv2 = new ContentValues();
                _cv2.put("Content_ID", Long.valueOf(row));
                _cv2.put("EmailType", Integer.valueOf(emailType[i2]));
                _cv2.put("Email", email[i2]);
                db.insert(EMAIL_DETAIL, null, _cv2);
            }
        }
        if (address != null) {
            for (int i3 = 0; i3 < address.length; i3++) {
                NfLog.d(TAG, "AddressType[" + i3 + "]: " + addressType[i3]);
                NfLog.d(TAG, "Address[" + i3 + "]: " + address[i3]);
                ContentValues _cv3 = new ContentValues();
                _cv3.put("Content_ID", Long.valueOf(row));
                _cv3.put("AddressType", Integer.valueOf(addressType[i3]));
                _cv3.put("Address", address[i3]);
                db.insert(ADDRESS_DETAIL, null, _cv3);
            }
        }
        try {
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLiteFullException e2) {
            e2.printStackTrace();
        } finally {
            db.close();
            cpc.release();
        }
    }

    public static void addCallLogToDatabases(Context context, String address, String firstName, String middleName, String lastName, String number, int phoneType, String timestamp, int calllogType, boolean isNeedDelete) {
        ContentResolver cr = context.getContentResolver();
        ContentProviderClient cpc = cr.acquireContentProviderClient(NfDef.DEFAULT_AUTHORITIES);
        String packageName = "";
        try {
            packageName = cpc.getType(Uri.parse("content://nfore.db.provider"));
        } catch (RemoteException e1) {
            e1.printStackTrace();
            Log.e(TAG, "getPackageName of DB get Exception");
        }
        Context _context = null;
        try {
            _context = context.createPackageContext(packageName, 2);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "createPackageContext: \"" + packageName + "\", NameNotFoundException");
        }
        DbHelperPbap dbHelper = new DbHelperPbap(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        if (isNeedDelete) {
            int delNum = dbHelper.deleteCallHistoryInfo(db, address, calllogType);
            NfLog.v(TAG, "Delete " + delNum + " call history.");
        }
        ContentValues cv = new ContentValues();
        String name = "";
        if (firstName != null) {
            name = String.valueOf(firstName) + " ";
        } else if (middleName != null) {
            name = String.valueOf("") + middleName + " ";
        } else if (lastName != null) {
            name = String.valueOf("") + lastName;
        }
        int numName = 0;
        try {
            numName = Integer.valueOf(name).intValue();
            Log.v(TAG, "numName : " + numName);
        } catch (NumberFormatException e2) {
        }
        if (numName < 0) {
            Log.e(TAG, "name is " + numName + " , automatically set it to \"" + UNKNOWN_NAME_STRING + "\"");
            name = UNKNOWN_NAME_STRING;
        }
        if (name == null) {
            name = "";
        }
        cv.put("FullName", name);
        if (firstName == null) {
            firstName = "";
        }
        cv.put("FirstName", firstName);
        if (lastName == null) {
            lastName = "";
        }
        cv.put("LastName", lastName);
        if (address == null) {
            address = "";
        }
        cv.put("CellPhone_Address", address);
        cv.put("StorageType", Integer.valueOf(calllogType));
        if (number == null) {
            number = "";
        }
        cv.put("PhoneNumber", number);
        cv.put("PhoneType", Integer.valueOf(phoneType));
        String historyDate = "";
        String historyTime = "";
        if (timestamp.split("T").length == 2) {
            historyDate = timestamp.split("T")[0];
            historyTime = timestamp.split("T")[1].split("Z")[0];
        }
        cv.put("HistoryDate", historyDate);
        cv.put("HistoryTime", historyTime);
        db.insert(CALLHISTORY_CONTENT, null, cv);
        try {
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLiteFullException e3) {
            e3.printStackTrace();
        } finally {
            db.close();
            cpc.release();
        }
    }

    private static String getPhoneType(int type) {
        switch (type) {
            case 0:
                return "NULL";
            case 1:
                return "PREF";
            case 2:
                return "WORK";
            case 3:
                return "HOME";
            case 4:
                return "VOICE";
            case 5:
                return "FAX";
            case 6:
                return "MSG";
            case 7:
                return "CELL";
            case 8:
                return "PAGER";
            default:
                return "";
        }
    }

    public static String filterNumber(String strNumber) {
        String strNumber2 = strNumber.replace("P", ",").replace('p', ',').replace('W', ';').replace('w', ';');
        Pattern p = Pattern.compile("[^0-9/+/*/,/#/;]");
        Matcher m = p.matcher(strNumber2);
        return m.replaceAll("").trim();
    }
}
