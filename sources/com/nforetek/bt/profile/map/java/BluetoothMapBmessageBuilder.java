package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.vcard.VCardEntry;
import java.util.Iterator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BluetoothMapBmessageBuilder {
    private static final String BBODY_BEGIN = "BEGIN:BBODY";
    private static final String BBODY_CHARSET = "CHARSET:";
    private static final String BBODY_ENCODING = "ENCODING:";
    private static final String BBODY_END = "END:BBODY";
    private static final String BBODY_LANGUAGE = "LANGUAGE:";
    private static final String BBODY_LENGTH = "LENGTH:";
    private static final String BENV_BEGIN = "BEGIN:BENV";
    private static final String BENV_END = "END:BENV";
    private static final String BMSG_BEGIN = "BEGIN:BMSG";
    private static final String BMSG_END = "END:BMSG";
    private static final String BMSG_FOLDER = "FOLDER:";
    private static final String BMSG_STATUS = "STATUS:";
    private static final String BMSG_TYPE = "TYPE:";
    private static final String BMSG_VERSION = "VERSION:1.0";
    private static final String CRLF = "\r\n";
    private static final String MSG_BEGIN = "BEGIN:MSG";
    private static final String MSG_END = "END:MSG";
    private static final String VCARD_BEGIN = "BEGIN:VCARD";
    private static final String VCARD_EMAIL = "EMAIL:";
    private static final String VCARD_END = "END:VCARD";
    private static final String VCARD_N = "N:";
    private static final String VCARD_TEL = "TEL:";
    private static final String VCARD_VERSION = "VERSION:2.1";
    private final StringBuilder mBmsg = new StringBuilder();

    private BluetoothMapBmessageBuilder() {
    }

    public static String createBmessage(BluetoothMapBmessage bmsg) {
        BluetoothMapBmessageBuilder b = new BluetoothMapBmessageBuilder();
        b.build(bmsg);
        return b.mBmsg.toString();
    }

    private void build(BluetoothMapBmessage bmsg) {
        int bodyLen = MSG_BEGIN.length() + MSG_END.length() + ("\r\n".length() * 3) + bmsg.mMessage.getBytes().length;
        this.mBmsg.append(BMSG_BEGIN).append("\r\n");
        this.mBmsg.append(BMSG_VERSION).append("\r\n");
        this.mBmsg.append(BMSG_STATUS).append(bmsg.mBmsgStatus).append("\r\n");
        this.mBmsg.append(BMSG_TYPE).append(bmsg.mBmsgType).append("\r\n");
        this.mBmsg.append(BMSG_FOLDER).append(bmsg.mBmsgFolder).append("\r\n");
        Iterator<VCardEntry> it = bmsg.mOriginators.iterator();
        while (it.hasNext()) {
            VCardEntry vcard = it.next();
            buildVcard(vcard);
        }
        this.mBmsg.append(BENV_BEGIN).append("\r\n");
        Iterator<VCardEntry> it2 = bmsg.mRecipients.iterator();
        while (it2.hasNext()) {
            VCardEntry vcard2 = it2.next();
            buildVcard(vcard2);
        }
        this.mBmsg.append(BBODY_BEGIN).append("\r\n");
        if (bmsg.mBbodyEncoding != null) {
            this.mBmsg.append(BBODY_ENCODING).append(bmsg.mBbodyEncoding).append("\r\n");
        }
        if (bmsg.mBbodyCharset != null) {
            this.mBmsg.append(BBODY_CHARSET).append(bmsg.mBbodyCharset).append("\r\n");
        }
        if (bmsg.mBbodyLanguage != null) {
            this.mBmsg.append(BBODY_LANGUAGE).append(bmsg.mBbodyLanguage).append("\r\n");
        }
        this.mBmsg.append(BBODY_LENGTH).append(bodyLen).append("\r\n");
        this.mBmsg.append(MSG_BEGIN).append("\r\n");
        this.mBmsg.append(bmsg.mMessage).append("\r\n");
        this.mBmsg.append(MSG_END).append("\r\n");
        this.mBmsg.append(BBODY_END).append("\r\n");
        this.mBmsg.append(BENV_END).append("\r\n");
        this.mBmsg.append(BMSG_END).append("\r\n");
    }

    private void buildVcard(VCardEntry vcard) {
        String n = buildVcardN(vcard);
        List<VCardEntry.PhoneData> tel = vcard.getPhoneList();
        List<VCardEntry.EmailData> email = vcard.getEmailList();
        this.mBmsg.append(VCARD_BEGIN).append("\r\n");
        this.mBmsg.append(VCARD_VERSION).append("\r\n");
        this.mBmsg.append(VCARD_N).append(n).append("\r\n");
        if (tel != null && tel.size() > 0) {
            this.mBmsg.append(VCARD_TEL).append(tel.get(0).getNumber()).append("\r\n");
        }
        if (email != null && email.size() > 0) {
            this.mBmsg.append(VCARD_EMAIL).append(email.get(0).getAddress()).append("\r\n");
        }
        this.mBmsg.append(VCARD_END).append("\r\n");
    }

    private String buildVcardN(VCardEntry vcard) {
        VCardEntry.NameData nd = vcard.getNameData();
        StringBuilder sb = new StringBuilder();
        sb.append(nd.getFamily()).append(";");
        sb.append(nd.getGiven() == null ? "" : nd.getGiven()).append(";");
        sb.append(nd.getMiddle() == null ? "" : nd.getMiddle()).append(";");
        sb.append(nd.getPrefix() == null ? "" : nd.getPrefix()).append(";");
        sb.append(nd.getSuffix() == null ? "" : nd.getSuffix());
        return sb.toString();
    }
}
