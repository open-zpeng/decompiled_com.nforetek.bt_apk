package com.nforetek.util.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.nforetek.bt.res.PhoneInfo;
import com.nforetek.bt.res.VCardList;
import com.nforetek.bt.res.VCardPack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/* loaded from: classes.dex */
public class DbHelperPbap extends SQLiteOpenHelper {
    private static final String ADDRESS_DETAIL = "AddressDetail";
    private static final String CALLHISTORY_CONTENT = "CallHistory";
    private static final String DATABASE_NAME = "db_pbap";
    private static final int DATABASE_VERSION = 1;
    private static final String EMAIL_DETAIL = "EmailDetail";
    private static final String PHONEBOOK_CONTENT = "PhoneBookContent";
    private static final String PHONENUMBER_DETAIL = "PhoneNumberDetail";
    private final boolean D;
    private final String SQL_DELETE_CONTACTER;
    private final String SQL_DELETE_PHONENUMBER;
    private final String SQL_DELETE_PHONENUMBER_BY_FULLNAME;
    private final String SQL_EXPRESS_TOTAL;
    private final String SQL_QUERY_CALLHISTORY_BY_ADDRESS_STORAGETYPE;
    private final String SQL_QUERY_CALLHISTORY_BY_SPECIFIED_COLUMNS;
    private final String SQL_QUERY_CONTACTER;
    private final String SQL_QUERY_CONTACTERS;
    private final String SQL_QUERY_FULLNAME_BY_PHONENUM_CELLPHONEADDRESS;
    private final String SQL_QUERY_FULLNAME_LIKE_BY_PHONENUM_CELLPHONEADDRESS;
    private final String SQL_QUERY_PHONEBOOKCONTENT;
    private final String SQL_QUERY_PHONEBOOKCONTENT_BY_PHONENUM;
    private final String SQL_QUERY_PHONEDATA_BY_PAGE;
    private final String SQL_QUERY_PHONENUMBERDETAIL;
    private final String SQL_QUERY_PHONETYPE_NAME;
    private final String SQL_QUERY_PHONE_BY_CONTENT_ID;
    private String TAG;
    private Context m_context;
    public static final String[] PHONEBOOK_CONTENT_FIELD = {"_id", "FullName", "FirstName", "MiddleName", "LastName", "First_StreetAddress", "First_CityNameAddress", "First_FederalStateAddress", "First_ZipCodeAddress", "First_CountryAddress", "Second_StreetAddress", "Second_CityNameAddress", "Second_FederalStateAddress", "Second_ZipCodeAddress", "Second_CountryAddress", "CellPhone_Address", "StorageType", "PhotoPath", "Org"};
    public static final String[] PHONENUMBER_DETAIL_FIELD = {"_id", "Content_ID", "Type", "Number"};
    public static final String[] ADDRESS_DETAIL_FIELD = {"_id", "Content_ID", "AddressType", "Address"};
    public static final String[] EMAIL_DETAIL_FIELD = {"_id", "Content_ID", "EmailType", "Email"};
    public static final String[] PHONE_TYPE_FIELD = {"Type", "TypeName"};
    private static final String PHONE_TYPE = "PhoneType";
    public static final String[] CALLHISTORY_FIELD = {"_id", "FullName", "FirstName", "LastName", "CellPhone_Address", "StorageType", PHONE_TYPE, "PhoneNumber", "HistoryDate", "HistoryTime"};

    public DbHelperPbap(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.D = true;
        this.TAG = "DbHelperPbap";
        this.SQL_QUERY_PHONEDATA_BY_PAGE = "select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName limit 10 offset ?) and StorageType=? and CellPhone_Address = ? order by FullName";
        this.SQL_QUERY_CONTACTERS = "select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName) and StorageType=? and CellPhone_Address = ? order by ";
        this.SQL_QUERY_CALLHISTORY_BY_ADDRESS_STORAGETYPE = "select a._id, a.FullName, a.StorageType, a.PhoneNumber, a.PhoneType, a.HistoryDate, a.HistoryTime from CallHistory a where a.CellPhone_Address = ? and a.StorageType=? order by ";
        this.SQL_QUERY_CALLHISTORY_BY_SPECIFIED_COLUMNS = "select * from CallHistory a where a.StorageType = ? and a.CellPhone_Address = ?";
        this.SQL_QUERY_PHONE_BY_CONTENT_ID = "select a.*, b.TypeName as TypeName from PhoneNumberDetail a inner join PhoneType b on a.Type = b.Type where a.Content_ID = ?";
        this.SQL_QUERY_PHONEBOOKCONTENT = "select * from PhoneBookContent where FullName like ? and StorageType = ? and CellPhone_Address = ?";
        this.SQL_QUERY_PHONEBOOKCONTENT_BY_PHONENUM = "select * from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number like ?) and StorageType = ? and CellPhone_Address = ?";
        this.SQL_QUERY_FULLNAME_BY_PHONENUM_CELLPHONEADDRESS = "select FullName from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number = ?) and CellPhone_Address = ?";
        this.SQL_QUERY_FULLNAME_LIKE_BY_PHONENUM_CELLPHONEADDRESS = "select a.FullName, b.Number from PhoneBookcontent a inner join phonenumberdetail b on a._id = b.Content_id where b.Number like ? and a.cellphone_address = ? order by b.Number;";
        this.SQL_QUERY_PHONENUMBERDETAIL = "select a.*, b.TypeName from PhoneNumberDetail.a inner join PhoneType b on a.Type = b.Type where Number = ?";
        this.SQL_DELETE_CONTACTER = "delete from PhoneBookContent where FullName = ?";
        this.SQL_DELETE_PHONENUMBER = "delete from PhoneNumberDetail where Number = ?";
        this.SQL_DELETE_PHONENUMBER_BY_FULLNAME = "delete from PhoneNumberDetail where Content_ID in (select _id from PhoneBookContent where FullName = ?)";
        this.SQL_QUERY_PHONETYPE_NAME = "select TypeName from PhoneType where Type = ? ";
        this.SQL_EXPRESS_TOTAL = "select a.FullName from PhoneBookContent a where a.CellPhone_Address=? and StorageType=? group by a.FullName";
        this.SQL_QUERY_CONTACTER = "select FullName from PhoneBookContent where _id = (select Content_ID from PhoneNumberDetail where Number like ? limit 1)";
        this.m_context = context;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS PhoneBookContent (" + PHONEBOOK_CONTENT_FIELD[0] + " INTEGER primary key autoincrement, " + PHONEBOOK_CONTENT_FIELD[1] + " varchar(24), " + PHONEBOOK_CONTENT_FIELD[2] + " varchar(8), " + PHONEBOOK_CONTENT_FIELD[3] + " varchar(8), " + PHONEBOOK_CONTENT_FIELD[4] + " varchar(8), " + PHONEBOOK_CONTENT_FIELD[5] + " varchar(20), " + PHONEBOOK_CONTENT_FIELD[6] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[7] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[8] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[9] + " varchar(30), " + PHONEBOOK_CONTENT_FIELD[10] + " varchar(20), " + PHONEBOOK_CONTENT_FIELD[11] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[12] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[13] + " varchar(12), " + PHONEBOOK_CONTENT_FIELD[14] + " varchar(30), " + PHONEBOOK_CONTENT_FIELD[15] + " varchar(30), " + PHONEBOOK_CONTENT_FIELD[16] + " varchar(10), " + PHONEBOOK_CONTENT_FIELD[17] + " varchar(50), " + PHONEBOOK_CONTENT_FIELD[18] + " varchar(50) );";
        db.execSQL(sql);
        String sql_2 = "CREATE TABLE IF NOT EXISTS PhoneNumberDetail (" + PHONENUMBER_DETAIL_FIELD[0] + " INTEGER primary key autoincrement, " + PHONENUMBER_DETAIL_FIELD[1] + " INTEGER, " + PHONENUMBER_DETAIL_FIELD[2] + " nvarchar(5), " + PHONENUMBER_DETAIL_FIELD[3] + " nvarchar(20),FOREIGN KEY(" + PHONENUMBER_DETAIL_FIELD[1] + ") REFERENCES " + PHONEBOOK_CONTENT + "(" + PHONEBOOK_CONTENT_FIELD[0] + ") );";
        db.execSQL(sql_2);
        String sql_3 = "CREATE TABLE IF NOT EXISTS AddressDetail (" + ADDRESS_DETAIL_FIELD[0] + " INTEGER primary key autoincrement, " + ADDRESS_DETAIL_FIELD[1] + " INTEGER, " + ADDRESS_DETAIL_FIELD[2] + " INTEGER, " + ADDRESS_DETAIL_FIELD[3] + " nvarchar(100),FOREIGN KEY(" + ADDRESS_DETAIL_FIELD[1] + ") REFERENCES " + PHONEBOOK_CONTENT + "(" + PHONEBOOK_CONTENT_FIELD[0] + ") );";
        db.execSQL(sql_3);
        String sql_4 = "CREATE TABLE IF NOT EXISTS EmailDetail (" + EMAIL_DETAIL_FIELD[0] + " INTEGER primary key autoincrement, " + EMAIL_DETAIL_FIELD[1] + " INTEGER, " + EMAIL_DETAIL_FIELD[2] + " INTEGER, " + EMAIL_DETAIL_FIELD[3] + " nvarchar(100),FOREIGN KEY(" + EMAIL_DETAIL_FIELD[1] + ") REFERENCES " + PHONEBOOK_CONTENT + "(" + PHONEBOOK_CONTENT_FIELD[0] + ") );";
        db.execSQL(sql_4);
        String sql_5 = "CREATE TABLE IF NOT EXISTS PhoneType (" + PHONE_TYPE_FIELD[0] + " nvarchar(5) , " + PHONE_TYPE_FIELD[1] + " nvarchar(26) );";
        db.execSQL(sql_5);
        String sql_6 = "CREATE TABLE IF NOT EXISTS CallHistory (" + CALLHISTORY_FIELD[0] + " INTEGER primary key autoincrement, " + CALLHISTORY_FIELD[1] + " nvarchar(16), " + CALLHISTORY_FIELD[2] + " nvarchar(8), " + CALLHISTORY_FIELD[3] + " nvarchar(8), " + CALLHISTORY_FIELD[4] + " nvarchar(30) not null, " + CALLHISTORY_FIELD[5] + " nvarchar(10) not null, " + CALLHISTORY_FIELD[6] + " nvarchar(5) not null, " + CALLHISTORY_FIELD[7] + " nvarchar(20) not null, " + CALLHISTORY_FIELD[8] + " nvarchar(8) not null, " + CALLHISTORY_FIELD[9] + " nvarchar(6) not null);";
        db.execSQL(sql_6);
        insertPhoneType(db);
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

    public void insertPhoneType(SQLiteDatabase db) {
        Log.e(this.TAG, "insertPhoneType");
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(this.m_context.getResources().getAssets().open("phonetype.xml"));
            try {
                Element root = doc.getDocumentElement();
                NodeList nodeList = root.getElementsByTagName("phoneType");
                ArrayList<HashMap<String, String>> list = new ArrayList<>();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    HashMap<String, String> columnValues = new HashMap<>();
                    Node nd = nodeList.item(i);
                    NamedNodeMap map = nd.getAttributes();
                    if (map.getNamedItem("Type").getTextContent().trim().length() > 0) {
                        columnValues.put("Type", map.getNamedItem("Type").getTextContent());
                    }
                    if (map.getNamedItem("TypeName").getTextContent().trim().length() > 0) {
                        columnValues.put("TypeName", map.getNamedItem("TypeName").getTextContent());
                    }
                    list.add(columnValues);
                }
                Cursor cursor = isEmptyPhoneType(db);
                if (cursor != null) {
                    cursor.moveToNext();
                    if (cursor.getInt(cursor.getColumnIndex("amount")) <= 0) {
                        insertNumberType(db, list);
                    }
                }
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(this.TAG, e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public VCardList queryContacterInfo(SQLiteDatabase db, String fullName, String phoneNum, int StorageType, String address) {
        boolean gotName = fullName != null && fullName.trim().length() > 0;
        boolean gotNumber = phoneNum != null && phoneNum.trim().length() > 0;
        boolean gotBoth = gotName && gotNumber;
        Cursor cursor = null;
        if (gotName) {
            cursor = queryContacterByFullName(db, fullName, StorageType, address);
        } else if (gotNumber) {
            cursor = queryContacterByPhoneNum(db, phoneNum, StorageType, address);
        }
        List<VCardPack> vcardPacks = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int colIdx = cursor.getColumnIndex("_id");
                Cursor numCursor = queryPhoneNumberByContentId(db, cursor.getString(colIdx));
                int idxNumer = numCursor.getColumnIndex("Number");
                int idxTypeName = numCursor.getColumnIndex("TypeName");
                Set<PhoneInfo> phoneInfos = new HashSet<>();
                while (numCursor.moveToNext() && (!gotBoth || numCursor.getString(idxNumer).trim().indexOf(phoneNum) != -1)) {
                    PhoneInfo phoneInfo = new PhoneInfo();
                    phoneInfo.setPhoneNumber(numCursor.getString(idxNumer));
                    phoneInfo.setPhoneTypeName(numCursor.getString(idxTypeName));
                    phoneInfos.add(phoneInfo);
                }
                if (phoneInfos.size() > 0) {
                    VCardPack vcardPack = new VCardPack(cursor);
                    vcardPack.setPhoneNumbers(phoneInfos);
                    vcardPacks.add(vcardPack);
                }
            }
        }
        VCardList vcardList = new VCardList();
        vcardList.setVcardPacks(vcardPacks);
        return vcardList;
    }

    public Cursor queryPhoneTypeName(SQLiteDatabase db, String phoneType) {
        if (phoneType == null) {
            phoneType = "C";
        }
        Cursor cursor = db.rawQuery("select TypeName from PhoneType where Type = ? ", new String[]{phoneType});
        return cursor;
    }

    private Cursor queryContacterByFullName(SQLiteDatabase db, String fullName, int StorageType, String address) {
        Cursor cursor = db.rawQuery("select * from PhoneBookContent where FullName like ? and StorageType = ? and CellPhone_Address = ?", new String[]{String.valueOf(fullName) + "%", new StringBuilder().append(StorageType).toString(), address});
        return cursor;
    }

    private Cursor queryContacterByPhoneNum(SQLiteDatabase db, String phoneNum, int StorageType, String address) {
        Cursor cursor = db.rawQuery("select * from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number like ?) and StorageType = ? and CellPhone_Address = ?", new String[]{"%" + phoneNum + "%", new StringBuilder().append(StorageType).toString(), address});
        return cursor;
    }

    public Cursor queryContacterByPhoneNumAndAddress(SQLiteDatabase db, String phoneNum, String address) {
        Cursor cursor = db.rawQuery("select FullName from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number = ?) and CellPhone_Address = ?", new String[]{phoneNum, address});
        return cursor;
    }

    public Cursor queryContacterByLikePhoneNumAndAddress(SQLiteDatabase db, String phoneNum, String address, int position) {
        String result = phoneNum;
        switch (position) {
            case 0:
                result = "%" + phoneNum + "%";
                break;
            case 1:
                result = String.valueOf(phoneNum) + "%";
                break;
            case 2:
                result = "%" + phoneNum;
                break;
        }
        Cursor cursor = db.rawQuery("select a.FullName, b.Number from PhoneBookcontent a inner join phonenumberdetail b on a._id = b.Content_id where b.Number like ? and a.cellphone_address = ? order by b.Number;", new String[]{result, address});
        return cursor;
    }

    public Cursor queryNumberDetailByPhoneNumber(SQLiteDatabase db, String phoneNum) {
        Cursor cursor = db.rawQuery("select a.*, b.TypeName from PhoneNumberDetail.a inner join PhoneType b on a.Type = b.Type where Number = ?", new String[]{phoneNum});
        return cursor;
    }

    public Cursor queryPhoneNumberByContentId(SQLiteDatabase db, String ContentId) {
        Cursor cursor = db.rawQuery("select a.*, b.TypeName as TypeName from PhoneNumberDetail a inner join PhoneType b on a.Type = b.Type where a.Content_ID = ?", new String[]{ContentId});
        return cursor;
    }

    public void deleteContacterByFullName(SQLiteDatabase db, String fullName) {
        db.execSQL("delete from PhoneBookContent where FullName = ?", new String[]{fullName});
    }

    public void deletePhoneNumber(SQLiteDatabase db, String phoneNum) {
        db.execSQL("delete from PhoneNumberDetail where Number = ?", new String[]{phoneNum});
    }

    public void deletePhoneNumberByFullName(SQLiteDatabase db, String fullName) {
        db.execSQL("delete from PhoneNumberDetail where Content_ID in (select _id from PhoneBookContent where FullName = ?)", new String[]{fullName});
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int deleteVcardInfo(android.database.sqlite.SQLiteDatabase r14, java.lang.String r15, int r16) {
        /*
            r13 = this;
            r9 = 0
            java.lang.String r1 = "PhoneBookContent"
            r0 = 1
            java.lang.String[] r2 = new java.lang.String[r0]
            r0 = 0
            java.lang.String r3 = "_id, PhotoPath"
            r2[r0] = r3
            java.lang.String r3 = "CellPhone_Address=? and StorageType=?"
            r0 = 2
            java.lang.String[] r4 = new java.lang.String[r0]
            r0 = 0
            r4[r0] = r15
            r0 = 1
            java.lang.String r5 = java.lang.String.valueOf(r16)
            r4[r0] = r5
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r14
            android.database.Cursor r8 = r0.query(r1, r2, r3, r4, r5, r6, r7)
            r10 = 0
            int r0 = r8.getCount()
            if (r0 <= 0) goto L61
            boolean r0 = r8.moveToNext()
            if (r0 == 0) goto L61
            r12 = 0
        L30:
            java.lang.String r0 = "PhoneNumberDetail"
            java.lang.String r1 = "Content_ID=?"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]
            r3 = 0
            r4 = 0
            java.lang.String r4 = r8.getString(r4)
            r2[r3] = r4
            int r12 = r14.delete(r0, r1, r2)
            int r10 = r10 + r12
            java.lang.String r0 = "PhotoPath"
            int r0 = r8.getColumnIndex(r0)
            java.lang.String r11 = r8.getString(r0)
            if (r11 == 0) goto L5b
            java.lang.String r0 = ""
            boolean r0 = r11.equals(r0)
            if (r0 != 0) goto L5b
            com.nforetek.util.normal.NfUtils.deleteFile(r11)
        L5b:
            boolean r0 = r8.moveToNext()
            if (r0 != 0) goto L30
        L61:
            if (r10 < 0) goto L79
            java.lang.String r0 = "PhoneBookContent"
            java.lang.String r1 = "CellPhone_Address=? and StorageType=?"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]
            r3 = 0
            r2[r3] = r15
            r3 = 1
            java.lang.String r4 = java.lang.String.valueOf(r16)
            r2[r3] = r4
            int r0 = r14.delete(r0, r1, r2)
            int r9 = r9 + r0
        L79:
            r8.close()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.util.db.DbHelperPbap.deleteVcardInfo(android.database.sqlite.SQLiteDatabase, java.lang.String, int):int");
    }

    public int deleteCallHistoryInfo(SQLiteDatabase db, String address, int StorageType) {
        int delCount_Content = db.delete(CALLHISTORY_CONTENT, "CellPhone_Address=? and StorageType=?", new String[]{address, String.valueOf(StorageType)});
        return delCount_Content;
    }

    public void insertVcardInfo(SQLiteDatabase db, VCardPack vcardPack, int type) {
        ContentValues cv = new ContentValues();
        if (vcardPack != null) {
            String name = vcardPack.getFullName();
            int numName = 0;
            try {
                numName = Integer.valueOf(name).intValue();
                Log.v(this.TAG, "numName : " + numName);
            } catch (NumberFormatException e) {
            }
            if (numName < 0) {
                Log.e(this.TAG, "name is " + numName + " , automatically set it to \"Private Number\"");
                name = "Private Number";
            }
            cv.put("FullName", name);
            cv.put("FirstName", vcardPack.getFirstName());
            cv.put("LastName", vcardPack.getLastName());
            cv.put("First_StreetAddress", vcardPack.getFirst_StreetAddress());
            cv.put("First_CityNameAddress", vcardPack.getFirst_CityNameAddress());
            cv.put("First_FederalStateAddress", vcardPack.getFirst_FederalStateAddress());
            cv.put("First_ZipCodeAddress", vcardPack.getFirst_ZipCodeAddress());
            cv.put("First_CountryAddress", vcardPack.getFirst_CountryAddress());
            cv.put("Second_StreetAddress", vcardPack.getSecond_StreetAddress());
            cv.put("Second_CityNameAddress", vcardPack.getSecond_CityNameAddress());
            cv.put("Second_FederalStateAddress", vcardPack.getSecond_FederalStateAddress());
            cv.put("Second_ZipCodeAddress", vcardPack.getSecond_ZipCodeAddress());
            cv.put("Second_CountryAddress", vcardPack.getSecond_CountryAddress());
            cv.put("CellPhone_Address", vcardPack.getCellPhone_Address());
            cv.put("StorageType", Integer.valueOf(type));
            long row = db.insert(PHONEBOOK_CONTENT, null, cv);
            Set<PhoneInfo> phoneInfos = vcardPack.getPhoneNumbers();
            for (PhoneInfo phoneInfo : phoneInfos) {
                ContentValues _cv = new ContentValues();
                _cv.put("Content_ID", Long.valueOf(row));
                _cv.put("Type", phoneInfo.getPhoneType());
                _cv.put("Number", phoneInfo.getPhoneNumber());
                db.insert(PHONENUMBER_DETAIL, null, _cv);
            }
        }
    }

    public void insertCallHistoryInfo(SQLiteDatabase db, VCardPack vcardPack, int type) {
        ContentValues cv = new ContentValues();
        if (vcardPack != null) {
            String name = vcardPack.getFullName();
            int numName = 0;
            try {
                numName = Integer.valueOf(name).intValue();
                Log.v(this.TAG, "numName : " + numName);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (numName < 0) {
                Log.e(this.TAG, "name is " + numName + " , automatically set it to \"Private Number\"");
                name = "Private Number";
            }
            cv.put("FullName", name == null ? "" : vcardPack.getFullName());
            cv.put("FirstName", vcardPack.getFirstName() == null ? "" : vcardPack.getFirstName());
            cv.put("LastName", vcardPack.getLastName() == null ? "" : vcardPack.getLastName());
            cv.put("CellPhone_Address", vcardPack.getCellPhone_Address() == null ? "" : vcardPack.getCellPhone_Address());
            cv.put("StorageType", Integer.valueOf(type));
            Set<PhoneInfo> phoneInfos = vcardPack.getPhoneNumbers();
            for (PhoneInfo phoneInfo : phoneInfos) {
                cv.put(PHONE_TYPE, phoneInfo.getPhoneType() == null ? "" : phoneInfo.getPhoneType());
                cv.put("PhoneNumber", phoneInfo.getPhoneNumber() == null ? "" : phoneInfo.getPhoneNumber());
            }
            cv.put("HistoryDate", vcardPack.getHistoryDate() == null ? "" : vcardPack.getHistoryDate());
            cv.put("HistoryTime", vcardPack.getHistoryTime() == null ? "" : vcardPack.getHistoryTime());
            db.insert(CALLHISTORY_CONTENT, null, cv);
        }
    }

    public List<VCardPack> queryPhoneDataByPage(SQLiteDatabase db, String address, int whichPage, int pageSize, String storageType) {
        Cursor cursor = db.rawQuery("select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName limit 10 offset ?) and StorageType=? and CellPhone_Address = ? order by FullName", new String[]{address, storageType, String.valueOf(whichPage * pageSize), storageType, address});
        List<VCardPack> vcardPacks = collectionData(cursor);
        cursor.close();
        for (VCardPack pack : vcardPacks) {
            new HashSet();
            for (int x = 0; x < pack.getPhoneNumbers().size(); x++) {
                Set<PhoneInfo> phoneInfos = pack.getPhoneNumbers();
                for (PhoneInfo phoneInfo : phoneInfos) {
                    Cursor cursor2 = queryPhoneTypeName(db, phoneInfo.getPhoneType());
                    if (cursor2.moveToNext()) {
                        phoneInfo.setPhoneTypeName(cursor2.getString(0));
                    }
                    cursor2.close();
                }
            }
        }
        return vcardPacks;
    }

    public List<VCardPack> queryContactersInfo(SQLiteDatabase db, String address, String storageType, String columnName) {
        Cursor cursor = db.rawQuery(String.valueOf("select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName) and StorageType=? and CellPhone_Address = ? order by ") + columnName, new String[]{address, storageType, storageType, address});
        List<VCardPack> vcardPacks = collectionData(cursor);
        cursor.close();
        for (VCardPack pack : vcardPacks) {
            new HashSet();
            for (int x = 0; x < pack.getPhoneNumbers().size(); x++) {
                Set<PhoneInfo> phoneInfos = pack.getPhoneNumbers();
                for (PhoneInfo phoneInfo : phoneInfos) {
                    Cursor cursor2 = queryPhoneTypeName(db, phoneInfo.getPhoneType());
                    if (cursor2.moveToNext()) {
                        phoneInfo.setPhoneTypeName(cursor2.getString(0));
                    }
                    cursor2.close();
                }
            }
        }
        return vcardPacks;
    }

    public VCardList callHistoryBySpecifiedColumns(SQLiteDatabase db, int storageType, String address, String historyDate, String historyTime, String phoneNumber) {
        ArrayList<String> queryStrings = new ArrayList<>();
        queryStrings.add(String.valueOf(storageType));
        queryStrings.add(address);
        String sql = "select * from CallHistory a where a.StorageType = ? and a.CellPhone_Address = ?";
        if (historyDate.trim().length() > 0) {
            sql = String.valueOf("select * from CallHistory a where a.StorageType = ? and a.CellPhone_Address = ?") + " and a.HistoryDate like ?";
            queryStrings.add("%" + historyDate + "%");
        }
        if (historyTime.trim().length() > 0) {
            sql = String.valueOf(sql) + " and a.HistoryTime like ?";
            queryStrings.add("%" + historyTime + "%");
        }
        if (phoneNumber.trim().length() > 0) {
            sql = String.valueOf(sql) + " and a.PhoneNumber like ?";
            queryStrings.add("%" + phoneNumber + "%");
        }
        String[] queryColumns = new String[queryStrings.size()];
        Cursor cursor = db.rawQuery(sql, (String[]) queryStrings.toArray(queryColumns));
        List<VCardPack> vcardPacks = collectionData(cursor);
        cursor.close();
        VCardList vcardList = new VCardList();
        vcardList.setVcardPacks(vcardPacks);
        return vcardList;
    }

    public List<VCardPack> queryCallHistoryByAddressAndStorageType(SQLiteDatabase db, String address, String storageType, String orderByColumn) {
        Cursor cursor = db.rawQuery(String.valueOf("select a._id, a.FullName, a.StorageType, a.PhoneNumber, a.PhoneType, a.HistoryDate, a.HistoryTime from CallHistory a where a.CellPhone_Address = ? and a.StorageType=? order by ") + orderByColumn, new String[]{address, storageType});
        List<VCardPack> vcardPacks = collectionData(cursor);
        cursor.close();
        for (VCardPack pack : vcardPacks) {
            new HashSet();
            for (int x = 0; x < pack.getPhoneNumbers().size(); x++) {
                Set<PhoneInfo> phoneInfos = pack.getPhoneNumbers();
                for (PhoneInfo phoneInfo : phoneInfos) {
                    Cursor cursor2 = queryPhoneTypeName(db, phoneInfo.getPhoneType());
                    if (cursor2.moveToNext()) {
                        phoneInfo.setPhoneTypeName(cursor2.getString(0));
                    }
                    cursor2.close();
                }
            }
        }
        return vcardPacks;
    }

    private List<VCardPack> collectionData(Cursor cursor) {
        List<VCardPack> items = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                VCardPack pack = new VCardPack();
                pack.setFullName(cursor.getString(cursor.getColumnIndex("FullName")));
                pack.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                PhoneInfo pInfo = new PhoneInfo();
                Set<PhoneInfo> set = new HashSet<>();
                String storageType = cursor.getString(cursor.getColumnIndex("StorageType"));
                int int_StorageType = Integer.parseInt(storageType);
                if (int_StorageType < 3 && int_StorageType > 0) {
                    pInfo.setPhoneNumber(cursor.getString(cursor.getColumnIndex("Number")));
                    pInfo.setPhoneType(cursor.getString(cursor.getColumnIndex("Type")));
                } else if (int_StorageType >= 3 && int_StorageType <= 5) {
                    pInfo.setPhoneNumber(cursor.getString(cursor.getColumnIndex("PhoneNumber")));
                    pInfo.setPhoneType(cursor.getString(cursor.getColumnIndex(PHONE_TYPE)));
                    pack.setHistoryDate(cursor.getString(cursor.getColumnIndex("HistoryDate")));
                    pack.setHistoryTime(cursor.getString(cursor.getColumnIndex("HistoryTime")));
                }
                set.add(pInfo);
                pack.setPhoneNumbers(set);
                pack.setStorageType(storageType);
                items.add(pack);
            } while (cursor.moveToNext());
            return items;
        }
        return items;
    }

    public void deleteContacterById(SQLiteDatabase db, int id) {
        db.delete(PHONENUMBER_DETAIL, "Content_ID=?", new String[]{new StringBuilder().append(id).toString()});
        db.delete(PHONEBOOK_CONTENT, "_id=?", new String[]{new StringBuilder().append(id).toString()});
    }

    public void deleteCallHistoryById(SQLiteDatabase db, int id) {
        db.delete(CALLHISTORY_CONTENT, "_id=?", new String[]{new StringBuilder().append(id).toString()});
    }

    public int queryTotalAmount(SQLiteDatabase db, String address, String type) {
        Cursor cursor = db.rawQuery("select a.FullName from PhoneBookContent a where a.CellPhone_Address=? and StorageType=? group by a.FullName", new String[]{address, type});
        int amount = cursor.getCount();
        cursor.close();
        return amount;
    }

    public String queryNameByPhoneNum(String phoneNum) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select FullName from PhoneBookContent where _id = (select Content_ID from PhoneNumberDetail where Number like ? limit 1)", new String[]{String.valueOf(phoneNum) + "%"});
        String contacter = "N/A";
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            contacter = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return contacter;
    }

    public void deleteAllTableContent(SQLiteDatabase db) {
        db.delete(PHONENUMBER_DETAIL, null, null);
        db.delete(PHONEBOOK_CONTENT, null, null);
        db.delete(CALLHISTORY_CONTENT, null, null);
    }
}
