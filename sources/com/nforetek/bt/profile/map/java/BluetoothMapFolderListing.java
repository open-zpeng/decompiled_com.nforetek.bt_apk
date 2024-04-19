package com.nforetek.bt.profile.map.java;

import com.nforetek.util.normal.NfLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
/* loaded from: classes.dex */
class BluetoothMapFolderListing {
    private static final String TAG = "BluetoothMasFolderListing";
    private final ArrayList<String> mFolders = new ArrayList<>();

    public BluetoothMapFolderListing(InputStream in) {
        parse(in);
    }

    public void parse(InputStream in) {
        try {
            XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
            xpp.setInput(in, "utf-8");
            for (int event = xpp.getEventType(); event != 1; event = xpp.next()) {
                switch (event) {
                    case 2:
                        if (xpp.getName().equals("folder")) {
                            this.mFolders.add(xpp.getAttributeValue(null, "name"));
                            continue;
                        } else {
                            continue;
                        }
                }
            }
        } catch (IOException e) {
            NfLog.e(TAG, "I/O error when parsing XML", e);
        } catch (XmlPullParserException e2) {
            NfLog.e(TAG, "XML parser error when parsing XML", e2);
        }
    }

    public ArrayList<String> getList() {
        return this.mFolders;
    }
}
