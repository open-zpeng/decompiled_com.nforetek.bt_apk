package com.nfore.provider;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import nfore.android.db.provider.DB_Creater;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/* loaded from: classes.dex */
public class DBProviderService extends Service {
    private final String TAG = "DBProviderService";
    private DB_Creater helper = null;
    private SQLiteDatabase db = null;

    @Override // android.app.Service
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        Log.d("DBProviderService", "DBProviderService Created");
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(getResources().getAssets().open("phonetype.xml"));
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
                this.helper = new DB_Creater(this);
                this.db = this.helper.getWritableDatabase();
                this.helper.onCreate(this.db);
                Cursor cursor = this.helper.isEmptyPhoneType(this.db);
                if (cursor != null) {
                    cursor.moveToNext();
                    if (cursor.getInt(cursor.getColumnIndex("amount")) <= 0) {
                        this.helper.insertNumberType(this.db, list);
                    }
                }
                cursor.close();
                this.db.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("DBProviderService", e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onLowMemory() {
        closeResource();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("DBProviderService", "StartAtBootSerivce -- onStartCommand()");
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.v("DBProviderService", "DBProviderService Destroyed");
        closeResource();
    }

    public void closeResource() {
        if (this.db != null) {
            this.db.close();
        }
        if (this.helper != null) {
            this.helper.close();
        }
    }
}
