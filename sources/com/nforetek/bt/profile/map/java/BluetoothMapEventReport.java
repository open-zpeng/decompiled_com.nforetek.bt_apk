package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.BluetoothMapMessage;
import com.nforetek.util.normal.NfLog;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
/* loaded from: classes.dex */
public class BluetoothMapEventReport {
    public static final int STATUS_FAILED = 1;
    public static final int STATUS_OK = 0;
    private static final String TAG = "BluetoothMapEventReport";
    public static final int TYPE_DELIVERY_FAILURE = 3;
    public static final int TYPE_DELIVERY_SUCCESS = 1;
    public static final int TYPE_MESSAGE_DELETED = 5;
    public static final int TYPE_MESSAGE_SHIFT = 6;
    public static final int TYPE_NEW_MESSAGE = 1;
    public static final int TYPE_SENDING_FAILURE = 4;
    public static final int TYPE_SENDING_SUCCESS = 2;
    public String mFolder;
    public String mHandle;
    public BluetoothMapMessage.Type mMsgType;
    public String mOldFolder;
    public Type mType;

    /* loaded from: classes.dex */
    public enum Type {
        NEW_MESSAGE("NewMessage"),
        DELIVERY_SUCCESS("DeliverySuccess"),
        SENDING_SUCCESS("SendingSuccess"),
        DELIVERY_FAILURE("DeliveryFailure"),
        SENDING_FAILURE("SendingFailure"),
        MEMORY_FULL("MemoryFull"),
        MEMORY_AVAILABLE("MemoryAvailable"),
        MESSAGE_DELETED("MessageDeleted"),
        MESSAGE_SHIFT("MessageShift");
        
        private final String mSpecName;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static Type[] valuesCustom() {
            Type[] valuesCustom = values();
            int length = valuesCustom.length;
            Type[] typeArr = new Type[length];
            System.arraycopy(valuesCustom, 0, typeArr, 0, length);
            return typeArr;
        }

        Type(String specName) {
            this.mSpecName = specName;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mSpecName;
        }
    }

    private BluetoothMapEventReport(HashMap<String, String> attrs) throws IllegalArgumentException {
        this.mType = parseType(attrs.get("type"));
        if (this.mType != Type.MEMORY_FULL && this.mType != Type.MEMORY_AVAILABLE) {
            String handle = attrs.get("handle");
            try {
                new BigInteger(attrs.get("handle"), 16);
                this.mHandle = attrs.get("handle");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid value for handle:" + handle);
            }
        } else {
            this.mHandle = null;
        }
        this.mFolder = attrs.get("folder");
        this.mOldFolder = attrs.get("old_folder");
        if (this.mType != Type.MEMORY_FULL && this.mType != Type.MEMORY_AVAILABLE) {
            String s = attrs.get("msg_type");
            if ("".equals(s)) {
                this.mMsgType = null;
                return;
            } else {
                this.mMsgType = parseMsgType(s);
                return;
            }
        }
        this.mMsgType = null;
    }

    private Type parseType(String type) throws IllegalArgumentException {
        Type[] valuesCustom;
        for (Type t : Type.valuesCustom()) {
            if (t.toString().equals(type)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid value for type: " + type);
    }

    private BluetoothMapMessage.Type parseMsgType(String msgType) throws IllegalArgumentException {
        BluetoothMapMessage.Type[] valuesCustom;
        for (BluetoothMapMessage.Type t : BluetoothMapMessage.Type.valuesCustom()) {
            if (t.name().equals(msgType)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid value for msg_type: " + msgType);
    }

    public Type getType() {
        return this.mType;
    }

    public String getHandle() {
        return this.mHandle;
    }

    public String getFolder() {
        return this.mFolder;
    }

    public String getOldFolder() {
        return this.mOldFolder;
    }

    public BluetoothMapMessage.Type getMsgType() {
        return this.mMsgType;
    }

    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("type", this.mType);
            json.put("handle", this.mHandle);
            json.put("folder", this.mFolder);
            json.put("old_folder", this.mOldFolder);
            json.put("msg_type", this.mMsgType);
        } catch (JSONException e) {
        }
        return json.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BluetoothMapEventReport fromStream(DataInputStream in) {
        BluetoothMapEventReport ev = null;
        try {
            XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
            xpp.setInput(in, "utf-8");
            for (int event = xpp.getEventType(); event != 1; event = xpp.next()) {
                switch (event) {
                    case 2:
                        if (xpp.getName().equals("event")) {
                            HashMap<String, String> attrs = new HashMap<>();
                            for (int i = 0; i < xpp.getAttributeCount(); i++) {
                                attrs.put(xpp.getAttributeName(i), xpp.getAttributeValue(i));
                            }
                            BluetoothMapEventReport ev2 = new BluetoothMapEventReport(attrs);
                            ev = ev2;
                            return ev2;
                        }
                        break;
                }
            }
        } catch (IOException e) {
            NfLog.e(TAG, "I/O error when parsing XML", e);
        } catch (IllegalArgumentException e2) {
            NfLog.e(TAG, "Invalid event received", e2);
        } catch (XmlPullParserException e3) {
            NfLog.e(TAG, "XML parser error when parsing XML", e3);
        }
        return ev;
    }
}
