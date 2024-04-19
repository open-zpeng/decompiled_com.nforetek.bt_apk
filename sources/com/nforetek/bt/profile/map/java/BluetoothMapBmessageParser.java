package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.BluetoothMapBmessage;
import com.nforetek.bt.profile.map.java.BluetoothMapMessage;
import com.nforetek.bt.profile.map.java.utils.BmsgTokenizer;
import com.nforetek.bt.profile.map.java.vcard.VCardEntry;
import com.nforetek.bt.profile.map.java.vcard.VCardEntryConstructor;
import com.nforetek.bt.profile.map.java.vcard.VCardEntryHandler;
import com.nforetek.bt.profile.map.java.vcard.VCardParser;
import com.nforetek.bt.profile.map.java.vcard.VCardParser_V21;
import com.nforetek.bt.profile.map.java.vcard.VCardParser_V30;
import com.nforetek.bt.profile.map.java.vcard.exception.VCardException;
import com.nforetek.bt.profile.map.java.vcard.exception.VCardVersionException;
import com.nforetek.util.normal.NfLog;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BluetoothMapBmessageParser {
    private static final String CRLF = "\r\n";
    private static final int CRLF_LEN = 2;
    private static final boolean DBG = false;
    private static final int MSG_CONTAINER_LEN = 22;
    private static final String TAG = "BluetoothMapBmessageParser";
    private final BluetoothMapBmessage mBmsg = new BluetoothMapBmessage();
    private BmsgTokenizer mParser;
    private static final BmsgTokenizer.Property BEGIN_BMSG = new BmsgTokenizer.Property("BEGIN", "BMSG");
    private static final BmsgTokenizer.Property END_BMSG = new BmsgTokenizer.Property("END", "BMSG");
    private static final BmsgTokenizer.Property BEGIN_VCARD = new BmsgTokenizer.Property("BEGIN", "VCARD");
    private static final BmsgTokenizer.Property END_VCARD = new BmsgTokenizer.Property("END", "VCARD");
    private static final BmsgTokenizer.Property BEGIN_BENV = new BmsgTokenizer.Property("BEGIN", "BENV");
    private static final BmsgTokenizer.Property END_BENV = new BmsgTokenizer.Property("END", "BENV");
    private static final BmsgTokenizer.Property BEGIN_BBODY = new BmsgTokenizer.Property("BEGIN", "BBODY");
    private static final BmsgTokenizer.Property END_BBODY = new BmsgTokenizer.Property("END", "BBODY");
    private static final BmsgTokenizer.Property BEGIN_MSG = new BmsgTokenizer.Property("BEGIN", "MSG");
    private static final BmsgTokenizer.Property END_MSG = new BmsgTokenizer.Property("END", "MSG");

    private BluetoothMapBmessageParser() {
    }

    public static BluetoothMapBmessage createBmessage(String str) {
        BluetoothMapBmessageParser p = new BluetoothMapBmessageParser();
        NfLog.d(false, TAG, str);
        try {
            p.parse(str);
            return p.mBmsg;
        } catch (IOException e) {
            NfLog.e(TAG, "I/O exception when parsing bMessage", e);
            return null;
        } catch (ParseException e2) {
            NfLog.e(TAG, "Cannot parse bMessage", e2);
            return null;
        } catch (Exception e3) {
            NfLog.e(TAG, "When parsing bMessage!");
            return null;
        }
    }

    private ParseException expected(BmsgTokenizer.Property... props) {
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (BmsgTokenizer.Property prop : props) {
            if (!first) {
                sb.append(" or ");
            }
            sb.append(prop);
            first = false;
        }
        return new ParseException("Expected: " + sb.toString(), this.mParser.pos());
    }

    private void parse(String str) throws Exception, IOException, ParseException {
        this.mParser = new BmsgTokenizer(String.valueOf(str) + "\r\n");
        BmsgTokenizer.Property prop = this.mParser.next();
        if (!prop.equals(BEGIN_BMSG)) {
            throw expected(BEGIN_BMSG);
        }
        BmsgTokenizer.Property prop2 = parseProperties();
        while (prop2.equals(BEGIN_VCARD)) {
            StringBuilder vcard = new StringBuilder();
            prop2 = extractVcard(vcard);
            VCardEntry entry = parseVcard(vcard.toString());
            this.mBmsg.mOriginators.add(entry);
        }
        if (!prop2.equals(BEGIN_BENV)) {
            throw expected(BEGIN_BENV);
        }
        BmsgTokenizer.Property prop3 = parseEnvelope(1);
        if (!prop3.equals(END_BMSG)) {
            throw expected(END_BENV);
        }
        this.mParser = null;
    }

    private BmsgTokenizer.Property parseProperties() throws ParseException {
        BmsgTokenizer.Property prop;
        do {
            prop = this.mParser.next();
            if (prop.name.equals("VERSION")) {
                this.mBmsg.mBmsgVersion = prop.value;
            } else if (prop.name.equals("STATUS")) {
                BluetoothMapBmessage.Status[] valuesCustom = BluetoothMapBmessage.Status.valuesCustom();
                int length = valuesCustom.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    BluetoothMapBmessage.Status s = valuesCustom[i];
                    if (!prop.value.equals(s.toString())) {
                        i++;
                    } else {
                        this.mBmsg.mBmsgStatus = s;
                        break;
                    }
                }
            } else if (prop.name.equals("TYPE")) {
                BluetoothMapMessage.Type[] valuesCustom2 = BluetoothMapMessage.Type.valuesCustom();
                int length2 = valuesCustom2.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        break;
                    }
                    BluetoothMapMessage.Type t = valuesCustom2[i2];
                    if (!prop.value.equals(t.toString())) {
                        i2++;
                    } else {
                        this.mBmsg.mBmsgType = t;
                        break;
                    }
                }
            } else if (prop.name.equals("FOLDER")) {
                this.mBmsg.mBmsgFolder = prop.value;
            }
            if (prop.equals(BEGIN_VCARD)) {
                break;
            }
        } while (!prop.equals(BEGIN_BENV));
        return prop;
    }

    private BmsgTokenizer.Property parseEnvelope(int level) throws Exception, IOException, ParseException {
        BmsgTokenizer.Property prop;
        if (level > 3) {
            throw new ParseException("bEnvelope is nested more than 3 times", this.mParser.pos());
        }
        BmsgTokenizer.Property prop2 = this.mParser.next();
        while (prop2.equals(BEGIN_VCARD)) {
            StringBuilder vcard = new StringBuilder();
            prop2 = extractVcard(vcard);
            if (level == 1) {
                VCardEntry entry = parseVcard(vcard.toString());
                this.mBmsg.mRecipients.add(entry);
            }
        }
        if (prop2.equals(BEGIN_BENV)) {
            prop = parseEnvelope(level + 1);
        } else if (!prop2.equals(BEGIN_BBODY)) {
            throw expected(BEGIN_BENV, BEGIN_BBODY);
        } else {
            prop = parseBody();
        }
        if (prop.equals(END_BENV)) {
            return this.mParser.next();
        }
        throw expected(END_BENV);
    }

    private BmsgTokenizer.Property parseBody() throws Exception, IOException, ParseException {
        BmsgTokenizer.Property prop;
        NfLog.e(TAG, "parseBody start ");
        do {
            prop = this.mParser.next();
            if (prop != null) {
                NfLog.d(false, TAG, "Prop1: " + prop.toString());
                if (prop.name != null && prop.name.length() != 0 && !prop.name.equals("PARTID")) {
                    if (prop.name.equals("ENCODING")) {
                        this.mBmsg.mBbodyEncoding = prop.value;
                    } else if (prop.name.equals("CHARSET")) {
                        this.mBmsg.mBbodyCharset = prop.value;
                    } else if (prop.name.equals("LANGUAGE")) {
                        this.mBmsg.mBbodyLanguage = prop.value;
                    } else if (prop.name.equals("LENGTH")) {
                        try {
                            this.mBmsg.mBbodyLength = Integer.parseInt(prop.value);
                            NfLog.d(false, TAG, "len: " + this.mBmsg.mBbodyLength);
                        } catch (NumberFormatException e) {
                            throw new ParseException("Invalid LENGTH value", this.mParser.pos());
                        }
                    }
                }
                if (prop == null) {
                    break;
                }
            } else {
                break;
            }
        } while (!prop.equals(BEGIN_MSG));
        if (this.mBmsg.mBbodyCharset == null) {
            NfLog.e(TAG, "check mBbodyCharset!");
            this.mBmsg.mBbodyCharset = "UTF-8";
        }
        if (!this.mBmsg.mBbodyCharset.equals("UTF-8")) {
            NfLog.e(TAG, "The charset was not set to charset UTF-8: " + this.mBmsg.mBbodyCharset);
        }
        int messageLen = this.mBmsg.mBbodyLength - 22;
        int offset = messageLen + 2;
        int restartPos = this.mParser.pos() + offset;
        String remng = this.mParser.remaining();
        byte[] data = remng.getBytes();
        this.mParser = new BmsgTokenizer(new String(data, offset, data.length - offset), restartPos);
        BmsgTokenizer.Property prop2 = this.mParser.next(true);
        if (prop2 != null) {
            NfLog.d(false, TAG, "Prop2: " + prop2.toString());
            if (prop2.equals(END_MSG)) {
                if (this.mBmsg.mBbodyCharset.equals("UTF-8")) {
                    this.mBmsg.mMessage = new String(data, 0, messageLen, "UTF-8");
                } else {
                    this.mBmsg.mMessage = null;
                }
            } else {
                NfLog.e(TAG, "Prop Invalid: " + prop2.toString());
                NfLog.e(TAG, "Possible Invalid LENGTH value");
                throw expected(END_MSG);
            }
        } else if (offset < 0 || offset > remng.length()) {
            throw new ParseException("Invalid LENGTH value", this.mParser.pos());
        } else {
            NfLog.w(TAG, "byte LENGTH seems to be invalid, trying with char length");
            this.mParser = new BmsgTokenizer(remng.substring(offset));
            BmsgTokenizer.Property prop3 = this.mParser.next();
            NfLog.d(false, TAG, "Prop3: " + prop3.toString());
            if (!prop3.equals(END_MSG)) {
                throw expected(END_MSG);
            }
            if (this.mBmsg.mBbodyCharset.equals("UTF-8")) {
                this.mBmsg.mMessage = remng.substring(0, messageLen);
            } else {
                this.mBmsg.mMessage = null;
            }
        }
        NfLog.d(false, TAG, "parseBody before end");
        if (this.mParser.next().equals(END_BBODY)) {
            NfLog.d(false, TAG, "parseBody end");
            return this.mParser.next();
        }
        throw expected(END_BBODY);
    }

    private BmsgTokenizer.Property extractVcard(StringBuilder out) throws IOException, ParseException {
        BmsgTokenizer.Property prop;
        out.append(BEGIN_VCARD).append("\r\n");
        do {
            prop = this.mParser.next();
            out.append(prop).append("\r\n");
        } while (!prop.equals(END_VCARD));
        return this.mParser.next();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class VcardHandler implements VCardEntryHandler {
        VCardEntry vcard;

        private VcardHandler() {
        }

        /* synthetic */ VcardHandler(BluetoothMapBmessageParser bluetoothMapBmessageParser, VcardHandler vcardHandler) {
            this();
        }

        @Override // com.nforetek.bt.profile.map.java.vcard.VCardEntryHandler
        public void onStart() {
        }

        @Override // com.nforetek.bt.profile.map.java.vcard.VCardEntryHandler
        public void onEntryCreated(VCardEntry entry) {
            this.vcard = entry;
        }

        @Override // com.nforetek.bt.profile.map.java.vcard.VCardEntryHandler
        public void onEnd() {
        }
    }

    private VCardEntry parseVcard(String str) throws IOException, ParseException {
        VCardEntry vcard = null;
        try {
            VCardParser p = new VCardParser_V21();
            VCardEntryConstructor c = new VCardEntryConstructor();
            VcardHandler handler = new VcardHandler(this, null);
            c.addEntryHandler(handler);
            p.addInterpreter(c);
            p.parse(new ByteArrayInputStream(str.getBytes()));
            vcard = handler.vcard;
        } catch (VCardVersionException e) {
            try {
                VCardParser p2 = new VCardParser_V30();
                VCardEntryConstructor c2 = new VCardEntryConstructor();
                VcardHandler handler2 = new VcardHandler(this, null);
                c2.addEntryHandler(handler2);
                p2.addInterpreter(c2);
                p2.parse(new ByteArrayInputStream(str.getBytes()));
                vcard = handler2.vcard;
            } catch (VCardVersionException e2) {
            } catch (VCardException e3) {
            }
        } catch (VCardException e4) {
        }
        if (vcard == null) {
            throw new ParseException("Cannot parse vCard object (neither 2.1 nor 3.0?)", this.mParser.pos());
        }
        return vcard;
    }
}
