package com.nforetek.util.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.nforetek.bt.res.MsgOutline;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class DbHelperMapCache extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db_map_cache";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "MessageContent";
    private final String SQL_DELETE_All_MESSAGE;
    private final String SQL_DELETE_FOLDER;
    private final String SQL_DELETE_MESSAGE;
    private final String SQL_DELETE_MESSAGE_BY_FOLDER;
    private final String SQL_DELETE_ONE_MESSAGE;
    private final String SQL_MESSAGE;
    private final String SQL_SELECT_MESSAGE;
    private final String SQL_SELECT_MESSGE_BY_FOLDER_AND_HANDLE;
    private final String SQL_SELECT_ONE_MESSAGE;
    private String TAG;
    private String _id;
    private String datetime;
    private String folder;
    private String handle;
    private Object helper;
    private Context m_context;
    private String macAddress;
    private String message;
    private String read;
    private String recipient_addressing;
    private String sender_addressing;
    private String sender_name;
    private String size;
    private String subject;

    public DbHelperMapCache(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.TAG = "DbHelperMapCache";
        this._id = "_id";
        this.handle = "handle";
        this.subject = "subject";
        this.message = "message";
        this.datetime = "datetime";
        this.sender_name = "sender_name";
        this.sender_addressing = "sender_addressing";
        this.recipient_addressing = "recipient_addressing";
        this.size = "size";
        this.read = "read";
        this.macAddress = "macAddress";
        this.folder = "folder";
        this.SQL_MESSAGE = "select * from MessageContent where condition = ?";
        this.SQL_SELECT_MESSAGE = "select * from MessageContent where macAddress = ?";
        this.SQL_SELECT_ONE_MESSAGE = "select * from MessageContent where macAddress = ? and handle = ? and folder = ?";
        this.SQL_SELECT_MESSGE_BY_FOLDER_AND_HANDLE = "select * from MessageContent where folder = ? and handle = ? and macAddress = ?";
        this.SQL_DELETE_MESSAGE = "delete from MessageContent where macAddress = ?";
        this.SQL_DELETE_FOLDER = "delete from MessageContent where macAddress = ? and folder = ?";
        this.SQL_DELETE_ONE_MESSAGE = "delete from MessageContent where macAddress = ? and handle = ? and datetime=?";
        this.SQL_DELETE_All_MESSAGE = "delete from MessageContent";
        this.SQL_DELETE_MESSAGE_BY_FOLDER = "delete from MessageContent where folder = ? and handle = ? and macAddress = ?";
        Log.d(this.TAG, "DbHelperMap constucter");
        this.m_context = context;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Log.d(this.TAG, "onCreate() Piggy check");
        String sql = "CREATE TABLE IF NOT EXISTS MessageContent (" + this._id + " Integer primary key autoincrement, " + this.folder + " varchar(6), " + this.handle + " varchar(256), " + this.subject + " varchar(256), " + this.message + " varchar(256), " + this.datetime + " varchar(256), " + this.sender_name + " varchar(256), " + this.sender_addressing + " varchar(256), " + this.recipient_addressing + " varchar(256), " + this.read + " varchar(3), " + this.macAddress + " varchar(17)) ";
        db.execSQL(sql);
    }

    public void insertMessageInfo(SQLiteDatabase db, String handle, int folder, String subject, String message, String date, String senderName, String senderAddr, String recipientAddr, String address, int readStatus) {
        Log.e(this.TAG, "insertMessageInfo");
        ContentValues cv = new ContentValues();
        Log.e(this.TAG, "insertMessageInfo " + handle);
        cv.put("Folder", Integer.valueOf(folder));
        cv.put("Handle", handle);
        cv.put("Subject", subject);
        cv.put("Message", message);
        cv.put("Datetime", date);
        cv.put("Sender_Name", senderName);
        cv.put("Sender_Addressing", senderAddr);
        cv.put("Recipient_Addressing", recipientAddr);
        cv.put("Read", readStatus == 1 ? "yes" : "no");
        cv.put("macAddress", address);
        db.insert(TABLE_NAME, null, cv);
    }

    public void insertMessageInfo(SQLiteDatabase db, MsgOutline msgOutline) {
        Log.e(this.TAG, "insertMessageInfo");
        ContentValues cv = new ContentValues();
        if (msgOutline != null) {
            Log.e(this.TAG, "insertMessageInfo " + msgOutline.getHandle());
            cv.put("Folder", msgOutline.getFolder());
            cv.put("Handle", msgOutline.getHandle());
            cv.put("Subject", msgOutline.getSubject());
            cv.put("Message", msgOutline.getMessage());
            cv.put("Datetime", msgOutline.getDatetime());
            cv.put("Sender_Name", msgOutline.getSender_name());
            cv.put("Sender_Addressing", msgOutline.getSender_addressing());
            cv.put("Recipient_Addressing", msgOutline.getRecipient_addressing());
            cv.put("macAddress", msgOutline.getMacAddress());
            db.insert(TABLE_NAME, null, cv);
        }
    }

    public ArrayList<String> queryMessageInfo(String request, SQLiteDatabase db) {
        Cursor cursor = db.query(TABLE_NAME, new String[]{request}, null, null, null, null, null);
        int rows_num = cursor.getCount();
        ArrayList<String> sMessage = new ArrayList<>();
        Log.e(this.TAG, "Piggy Check rows counts : " + rows_num);
        if (rows_num != 0) {
            for (int i = 0; i < rows_num; i++) {
                if (i == 0) {
                    cursor.moveToFirst();
                } else {
                    cursor.moveToNext();
                }
                String strCr = cursor.getString(0);
                sMessage.add(strCr);
            }
            cursor.close();
        }
        return sMessage;
    }

    public Cursor queryMessage(SQLiteDatabase db, String condition) {
        Cursor cursor = db.rawQuery("select * from MessageContent where condition = ?", new String[]{condition});
        return cursor;
    }

    public void onUpdate(SQLiteDatabase db, String message) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS config");
        onCreate(db);
    }

    public void deleteAllTableContent(SQLiteDatabase db) {
        db.delete(TABLE_NAME, null, null);
    }

    public MsgOutline queryMessageByfolderAndHandle(SQLiteDatabase db, String folder, String handle, String address) {
        Cursor cursor = db.rawQuery("select * from MessageContent where folder = ? and handle = ? and macAddress = ?", new String[]{folder, handle, address});
        MsgOutline msgOutline = new MsgOutline();
        cursor.moveToFirst();
        msgOutline.setFolder(cursor.getString(1));
        msgOutline.setHandle(cursor.getString(2));
        msgOutline.setSubject(cursor.getString(3));
        msgOutline.setMessage(cursor.getString(4));
        msgOutline.setDatetime(cursor.getString(5));
        msgOutline.setSender_name(cursor.getString(6));
        msgOutline.setSender_addressing(cursor.getString(7));
        msgOutline.setRecipient_addressing(cursor.getString(8));
        msgOutline.setSize(cursor.getString(9));
        msgOutline.setRead(cursor.getString(10));
        cursor.close();
        return msgOutline;
    }

    public Cursor queryMessageByMacAddress(SQLiteDatabase db, String message) {
        Cursor cursor = db.rawQuery("select * from MessageContent where macAddress = ?", new String[]{message});
        return cursor;
    }

    public Cursor queryOneMessageByMacAddress(SQLiteDatabase db, String address, String handle, String folder) {
        if (!folder.equals("inbox") && !folder.equals("sent")) {
            Log.e(this.TAG, "folder parameter error !");
            return null;
        }
        return db.rawQuery("select * from MessageContent where macAddress = ? and handle = ? and folder = ?", new String[]{address, handle, folder});
    }

    public boolean isMessageInDatabase(SQLiteDatabase db, String address, String handle, String folder) {
        Log.e(this.TAG, "Piggy Check isMessageInDatabase address : " + address + " handle : " + handle + " folder : " + folder);
        if (!folder.equals("inbox") && !folder.equals("sent")) {
            Log.e(this.TAG, "folder parameter error !");
            return false;
        }
        Cursor cursor = db.rawQuery("select * from MessageContent where macAddress = ? and handle = ? and folder = ?", new String[]{address, handle, folder});
        Log.e(this.TAG, "Piggy Check isMessageInDatabase cursor count : " + cursor.getCount());
        cursor.moveToFirst();
        String checkID = cursor.getString(cursor.getColumnIndex("_id"));
        String checkHandle = cursor.getString(cursor.getColumnIndex("handle"));
        String checkDate = cursor.getString(cursor.getColumnIndex("datetime"));
        Log.e(this.TAG, "Piggy Check ID : " + checkID + " handle : " + checkHandle + " DateTime : " + checkDate);
        if (cursor.getCount() == 1) {
            return true;
        }
        if (cursor.getCount() > 1) {
            Log.e(this.TAG, "Piggy Check have same handle same folder in database. don't know why .");
            return true;
        }
        return false;
    }

    public void deleteAllMessage(SQLiteDatabase db, String message) {
        db.execSQL("delete from MessageContent", new String[]{message});
    }

    public void deleteMessageByMacAddress(SQLiteDatabase db, String address) {
        db.execSQL("delete from MessageContent where macAddress = ?", new String[]{address});
    }

    public void deleteMessageByMacAddressAndFolder(SQLiteDatabase db, String address, int folder) {
        db.execSQL("delete from MessageContent where macAddress = ? and folder = ?", new String[]{address, new StringBuilder().append(folder).toString()});
    }

    public void deleteOneMessageByMacAddress(SQLiteDatabase db, String address) {
        db.execSQL("delete from MessageContent where macAddress = ? and handle = ? and datetime=?", new String[]{address});
    }

    public void deleteMessageByFolderAndHandle(SQLiteDatabase db, String folder, String handle, String address) {
        db.execSQL("delete from MessageContent where folder = ? and handle = ? and macAddress = ?", new String[]{folder, handle, address});
    }
}
