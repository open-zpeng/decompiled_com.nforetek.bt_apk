package nfore.android.db.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes.dex */
public class DB_Creater extends SQLiteOpenHelper {
    private static final String CALLHISTORY_CONTENT = "CallHistory";
    private static final String DATABASE_NAME = "db_pbap";
    private static final int DATABASE_VERSION = 1;
    private static final String PHONEBOOK_CONTENT = "PhoneBookContent";
    private static final String PHONENUMBER_DETAIL = "PhoneNumberDetail";
    private String TAG;
    private Context m_context;
    public static final String[] PHONEBOOK_CONTENT_FIELD = {"_id", "FullName", "FirstName", "LastName", "First_StreetAddress", "First_CityNameAddress", "First_FederalStateAddress", "First_ZipCodeAddress", "First_CountryAddress", "Second_StreetAddress", "Second_CityNameAddress", "Second_FederalStateAddress", "Second_ZipCodeAddress", "Second_CountryAddress", "CellPhone_Address", "StorageType"};
    public static final String[] PHONENUMBER_DETAIL_FIELD = {"_id", "Content_ID", "Type", "Number"};
    public static final String[] PHONE_TYPE_FIELD = {"Type", "TypeName"};
    private static final String PHONE_TYPE = "PhoneType";
    public static final String[] CALLHISTORY_FIELD = {"_id", "FullName", "FirstName", "LastName", "CellPhone_Address", "StorageType", PHONE_TYPE, "PhoneNumber", "HistoryDate", "HistoryTime"};

    public DB_Creater(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.TAG = "nfore_PBAP_DBHelper";
        this.m_context = context;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS PhoneBookContent (" + PHONEBOOK_CONTENT_FIELD[0] + " INTEGER primary key autoincrement, " + PHONEBOOK_CONTENT_FIELD[1] + " varchar(16), " + PHONEBOOK_CONTENT_FIELD[2] + " varchar(8), " + PHONEBOOK_CONTENT_FIELD[3] + " varchar(8), " + PHONEBOOK_CONTENT_FIELD[4] + " varchar(20), " + PHONEBOOK_CONTENT_FIELD[5] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[6] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[7] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[8] + " varchar(30), " + PHONEBOOK_CONTENT_FIELD[9] + " varchar(20), " + PHONEBOOK_CONTENT_FIELD[10] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[11] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[12] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[13] + " varchar(30), " + PHONEBOOK_CONTENT_FIELD[14] + " varchar(30), " + PHONEBOOK_CONTENT_FIELD[15] + " varchar(10));";
        db.execSQL(sql);
        String sql_2 = "CREATE TABLE IF NOT EXISTS PhoneNumberDetail (" + PHONENUMBER_DETAIL_FIELD[0] + " INTEGER primary key autoincrement, " + PHONENUMBER_DETAIL_FIELD[1] + " INTEGER, " + PHONENUMBER_DETAIL_FIELD[2] + " nvarchar(5), " + PHONENUMBER_DETAIL_FIELD[3] + " nvarchar(20),FOREIGN KEY(" + PHONENUMBER_DETAIL_FIELD[1] + ") REFERENCES " + PHONEBOOK_CONTENT + "(" + PHONEBOOK_CONTENT_FIELD[0] + ") );";
        db.execSQL(sql_2);
        String sql_3 = "CREATE TABLE IF NOT EXISTS PhoneType (" + PHONE_TYPE_FIELD[0] + " nvarchar(5) , " + PHONE_TYPE_FIELD[1] + " nvarchar(26) );";
        db.execSQL(sql_3);
        String sql_4 = "CREATE TABLE IF NOT EXISTS CallHistory (" + CALLHISTORY_FIELD[0] + " INTEGER primary key autoincrement, " + CALLHISTORY_FIELD[1] + " nvarchar(16), " + CALLHISTORY_FIELD[2] + " nvarchar(8), " + CALLHISTORY_FIELD[3] + " nvarchar(8), " + CALLHISTORY_FIELD[4] + " nvarchar(30) not null, " + CALLHISTORY_FIELD[5] + " nvarchar(10) not null, " + CALLHISTORY_FIELD[6] + " nvarchar(5) not null, " + CALLHISTORY_FIELD[7] + " nvarchar(20) not null, " + CALLHISTORY_FIELD[8] + " nvarchar(8) not null, " + CALLHISTORY_FIELD[9] + " nvarchar(6) not null);";
        db.execSQL(sql_4);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x003b -> B:5:0x0009). Please submit an issue!!! */
    public int insertNumberType(SQLiteDatabase db, ArrayList<HashMap<String, String>> list) {
        long row = -1;
        for (int x = 0; x < list.size(); x++) {
            try {
                HashMap<String, String> map = list.get(x);
                ContentValues cv = new ContentValues();
                cv.put("Type", map.get("Type"));
                cv.put("TypeName", map.get("TypeName"));
                row = db.insert(PHONE_TYPE, null, cv);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(this.TAG, e.getMessage());
            }
        }
        return (int) row;
    }

    public Cursor isEmptyPhoneType(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select count(*) as amount from PhoneType", null);
        return cursor;
    }
}
