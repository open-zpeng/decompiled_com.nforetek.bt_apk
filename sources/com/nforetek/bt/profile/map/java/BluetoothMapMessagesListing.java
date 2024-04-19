package com.nforetek.bt.profile.map.java;

import com.nforetek.util.normal.NfLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
/* loaded from: classes.dex */
class BluetoothMapMessagesListing {
    private static final boolean DBG = false;
    private static final String TAG = "BluetoothMapMessagesListing";
    InputStream is;
    int mdownloadInterrupt = 0;
    private final ArrayList<BluetoothMapMessage> mMessages = new ArrayList<>();

    public BluetoothMapMessagesListing(InputStream in) {
    }

    public int isOut() {
        return this.mdownloadInterrupt;
    }

    public void downloadInterrupt() {
        NfLog.d(TAG, "downloadInterrupt set to 1");
        this.mdownloadInterrupt = 1;
    }

    public void parse(InputStream in) {
        this.is = in;
        try {
            XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
            xpp.setInput(in, "utf-8");
            for (int event = xpp.getEventType(); event != 1; event = xpp.next()) {
                switch (event) {
                    case 2:
                        if (xpp.getName().equals("msg")) {
                            HashMap<String, String> attrs = new HashMap<>();
                            for (int i = 0; i < xpp.getAttributeCount(); i++) {
                                attrs.put(xpp.getAttributeName(i), xpp.getAttributeValue(i));
                            }
                            try {
                                BluetoothMapMessage msg = new BluetoothMapMessage(attrs);
                                this.mMessages.add(msg);
                            } catch (IllegalArgumentException e) {
                                NfLog.w(TAG, "Invalid <msg/>");
                            }
                        }
                        NfLog.d(false, TAG, "parsed: " + this.mMessages.size());
                        continue;
                }
            }
        } catch (IOException e2) {
            NfLog.e(TAG, "I/O error when parsing XML", e2);
        } catch (XmlPullParserException e3) {
            NfLog.e(TAG, "XML parser error when parsing XML", e3);
        }
    }

    public ArrayList<BluetoothMapMessage> getList() {
        return this.mMessages;
    }
}
