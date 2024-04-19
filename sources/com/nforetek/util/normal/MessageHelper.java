package com.nforetek.util.normal;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.net.Uri;
import android.os.RemoteException;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.db.DbHelperMap;
/* loaded from: classes.dex */
public class MessageHelper {
    private static final String TAG = "MessageHelper";
    private static boolean mIsUpdating = false;

    public static synchronized void setUpdating(boolean updating) {
        synchronized (MessageHelper.class) {
            mIsUpdating = updating;
        }
    }

    public static synchronized boolean isUpdating() {
        boolean z;
        synchronized (MessageHelper.class) {
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
        DbHelperMap dbHelper = new DbHelperMap(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        dbHelper.insertMessageInfo(db, handle, folder, subject, message, date, senderName, senderAddr, recipientAddr, address, readStatus);
        try {
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLiteFullException e2) {
            e2.printStackTrace();
        } finally {
            db.close();
            cpc.release();
        }
        setUpdating(false);
    }

    public static int updateMessageToDatabases(Context context, String address, int folder, String handle, String message) {
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
        DbHelperMap dbHelper = new DbHelperMap(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        db.beginTransaction();
        cv.clear();
        cv.put("Message", message);
        int rows = db.update("MessageContent", cv, "folder = ? and handle = ? and macAddress = ?", new String[]{new StringBuilder().append(folder).toString(), handle, address});
        try {
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLiteFullException e2) {
            e2.printStackTrace();
        } finally {
            db.close();
            cpc.release();
        }
        setUpdating(false);
        return rows;
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
        DbHelperMap dbHelper = new DbHelperMap(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        dbHelper.deleteMessageByMacAddressAndFolder(db, address, folder);
        try {
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLiteFullException e2) {
            e2.printStackTrace();
        } finally {
            db.close();
            cpc.release();
        }
        setUpdating(false);
    }

    public static void deleteMessageByMacAddressAndFolderAndHandle(Context context, String address, int folder, String handle) {
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
        DbHelperMap dbHelper = new DbHelperMap(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        dbHelper.deleteMessageByFolderAndHandle(db, folder, handle, address);
        try {
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLiteFullException e2) {
            e2.printStackTrace();
        } finally {
            db.close();
            cpc.release();
        }
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
        DbHelperMap dbHelper = new DbHelperMap(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        dbHelper.deleteMessageByMacAddress(db, address);
        try {
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLiteFullException e2) {
            e2.printStackTrace();
        } finally {
            db.close();
            cpc.release();
        }
        setUpdating(false);
    }

    public static void deleteAllMessage(Context context) {
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
        DbHelperMap dbHelper = new DbHelperMap(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        dbHelper.deleteAllTableContent(db);
        try {
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLiteFullException e2) {
            e2.printStackTrace();
        } finally {
            db.close();
            cpc.release();
        }
        setUpdating(false);
    }
}
