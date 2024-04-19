package com.nforetek.util.normal;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.db.DbHelperMapCache;
/* loaded from: classes.dex */
public class MessageCacheHelper {
    private static final String TAG = "MessageCacheHelper";
    private static boolean mIsUpdating = false;

    public static synchronized void setUpdating(boolean updating) {
        synchronized (MessageCacheHelper.class) {
            mIsUpdating = updating;
        }
    }

    public static synchronized boolean isUpdating() {
        boolean z;
        synchronized (MessageCacheHelper.class) {
            z = mIsUpdating;
        }
        return z;
    }

    public static void addMessageToDatabases(Context context, String address, String handle, String senderName, String senderAddr, String date, String recipientAddr, int type, int folder, String subject, String message, int readStatus) {
        setUpdating(true);
        ContentResolver cr = context.getContentResolver();
        ContentProviderClient cpc = cr.acquireContentProviderClient(NfDef.DEFAULT_AUTHORITIES);
        String packageName = "";
        try {
            packageName = cpc.getType(Uri.parse("content://nfore.db.provider"));
        } catch (RemoteException e1) {
            e1.printStackTrace();
            NfLog.e(TAG, "getPackageName of DB get Exception");
        }
        Context _context = null;
        try {
            _context = context.createPackageContext(packageName, 2);
        } catch (PackageManager.NameNotFoundException e) {
            NfLog.e(TAG, "createPackageContext: \"" + packageName + "\", NameNotFoundException");
        }
        DbHelperMapCache dbHelper = new DbHelperMapCache(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        dbHelper.insertMessageInfo(db, handle, folder, subject, message, date, senderName, senderAddr, recipientAddr, address, readStatus);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        setUpdating(false);
    }

    public static void deleteMessageByMacAddressAndFolder(Context context, String address, int folder) {
        setUpdating(true);
        ContentResolver cr = context.getContentResolver();
        ContentProviderClient cpc = cr.acquireContentProviderClient(NfDef.DEFAULT_AUTHORITIES);
        String packageName = "";
        try {
            packageName = cpc.getType(Uri.parse("content://nfore.db.provider"));
        } catch (RemoteException e1) {
            e1.printStackTrace();
            NfLog.e(TAG, "getPackageName of DB get Exception");
        }
        Context _context = null;
        try {
            _context = context.createPackageContext(packageName, 2);
        } catch (PackageManager.NameNotFoundException e) {
            NfLog.e(TAG, "createPackageContext: \"" + packageName + "\", NameNotFoundException");
        }
        DbHelperMapCache dbHelper = new DbHelperMapCache(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        dbHelper.deleteMessageByMacAddressAndFolder(db, address, folder);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        setUpdating(false);
    }

    public static void deleteMessageByMacAddress(Context context, String address) {
        setUpdating(true);
        ContentResolver cr = context.getContentResolver();
        ContentProviderClient cpc = cr.acquireContentProviderClient(NfDef.DEFAULT_AUTHORITIES);
        String packageName = "";
        try {
            packageName = cpc.getType(Uri.parse("content://nfore.db.provider"));
        } catch (RemoteException e1) {
            e1.printStackTrace();
            NfLog.e(TAG, "getPackageName of DB get Exception");
        }
        Context _context = null;
        try {
            _context = context.createPackageContext(packageName, 2);
        } catch (PackageManager.NameNotFoundException e) {
            NfLog.e(TAG, "createPackageContext: \"" + packageName + "\", NameNotFoundException");
        }
        DbHelperMapCache dbHelper = new DbHelperMapCache(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        dbHelper.deleteMessageByMacAddress(db, address);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        setUpdating(false);
    }

    public static void updateMessageToDatabases(Context context, String address, String handle, String message) {
        setUpdating(true);
        ContentResolver cr = context.getContentResolver();
        ContentProviderClient cpc = cr.acquireContentProviderClient(NfDef.DEFAULT_AUTHORITIES);
        String packageName = "";
        try {
            packageName = cpc.getType(Uri.parse("content://nfore.db.provider"));
        } catch (RemoteException e1) {
            e1.printStackTrace();
            NfLog.e(TAG, "getPackageName of DB get Exception");
        }
        Context _context = null;
        try {
            _context = context.createPackageContext(packageName, 2);
        } catch (PackageManager.NameNotFoundException e) {
            NfLog.e(TAG, "createPackageContext: \"" + packageName + "\", NameNotFoundException");
        }
        DbHelperMapCache dbHelper = new DbHelperMapCache(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        db.beginTransaction();
        cv.clear();
        cv.put("Message", message);
        db.update("MessageContent", cv, "handle = ? and macAddress = ?", new String[]{handle, address});
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        setUpdating(false);
    }

    public static MessageCache getMessageByNacAddressAndFoler(Context context, String address, String handle, int folder) {
        Log.e(TAG, "getMessageByNacAddressAndFoler " + address + " handle: " + handle + " folder : " + folder);
        ContentResolver cr = context.getContentResolver();
        ContentProviderClient cpc = cr.acquireContentProviderClient(NfDef.DEFAULT_AUTHORITIES);
        String packageName = "";
        try {
            packageName = cpc.getType(Uri.parse("content://nfore.db.provider"));
            Log.d(TAG, "Package Name : " + packageName);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
        DbHelperMapCache dbHelper = new DbHelperMapCache(context);
        try {
            Log.d(TAG, "start createPackageContext");
            Context _context = context.createPackageContext(packageName, 2);
            Log.d(TAG, "start new helper");
            dbHelper = null;
            DbHelperMapCache dbHelper2 = new DbHelperMapCache(_context);
            dbHelper = dbHelper2;
        } catch (Exception e) {
            Log.e(TAG, "createPackageContext: \"" + packageName + "\", NameNotFoundException");
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Log.e(TAG, "Piggy Check 1");
        Cursor mCursor = db.rawQuery("select * from MessageContent where macAddress  = ? and folder = ? and handle = ?;", new String[]{address, String.valueOf(folder), handle});
        Log.e(TAG, "Piggy Check 2");
        Log.e(TAG, "Piggy Check 3");
        if (mCursor.getCount() <= 0) {
            Log.e(TAG, "Piggy Check 4");
            mCursor.close();
            db.close();
            return null;
        }
        Log.e(TAG, "===== Message list start ! =====");
        mCursor.moveToFirst();
        MessageCache m = new MessageCache();
        if (mCursor.getCount() != 0) {
            do {
                int i = mCursor.getColumnIndex("handle");
                m.handle = mCursor.getString(i);
                int i2 = mCursor.getColumnIndex("folder");
                m.folder = mCursor.getInt(i2);
                int i3 = mCursor.getColumnIndex("subject");
                m.subject = mCursor.getString(i3);
                int i4 = mCursor.getColumnIndex("message");
                m.message = mCursor.getString(i4);
                int i5 = mCursor.getColumnIndex("datetime");
                m.date = mCursor.getString(i5);
                int i6 = mCursor.getColumnIndex("sender_name");
                m.senderName = mCursor.getString(i6);
                int i7 = mCursor.getColumnIndex("sender_addressing");
                m.senderAddr = mCursor.getString(i7);
                int i8 = mCursor.getColumnIndex("recipient_addressing");
                m.recipientAddr = mCursor.getString(i8);
                int i9 = mCursor.getColumnIndex("macAddress");
                m.address = mCursor.getString(i9);
                int i10 = mCursor.getColumnIndex("read");
                m.readStatus = mCursor.getString(i10).equalsIgnoreCase("yes") ? 1 : 0;
            } while (mCursor.moveToNext());
            Log.e(TAG, "===== Contacts list ended ! =====");
            mCursor.close();
            db.close();
            return m;
        }
        Log.e(TAG, "===== Contacts list ended ! =====");
        mCursor.close();
        db.close();
        return m;
    }
}
