package com.nforetek.bt.res.vcard;

import android.util.Log;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.codec.net.QuotedPrintableCodec;
/* loaded from: classes.dex */
public class VCardParseProcessor {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$nforetek$bt$res$vcard$VCardParserState;
    String TAG = "VCardParseProcessor";

    static /* synthetic */ int[] $SWITCH_TABLE$com$nforetek$bt$res$vcard$VCardParserState() {
        int[] iArr = $SWITCH_TABLE$com$nforetek$bt$res$vcard$VCardParserState;
        if (iArr == null) {
            iArr = new int[VCardParserState.valuesCustom().length];
            try {
                iArr[VCardParserState.cp_ParamBN.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VCardParserState.cp_ParamIN.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VCardParserState.cp_PropData.ordinal()] = 6;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[VCardParserState.cp_PropName.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[VCardParserState.cp_ValueBV.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[VCardParserState.cp_ValueCheck.ordinal()] = 7;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[VCardParserState.cp_ValueIV.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            $SWITCH_TABLE$com$nforetek$bt$res$vcard$VCardParserState = iArr;
        }
        return iArr;
    }

    public Set<Set<VCardProperty>> doParsing(VCardParser vp, String content) throws Exception {
        Log.e(this.TAG, "Piggy check doParsing()");
        Set<Set<VCardProperty>> set = vcard_process_char(vp, content);
        return set;
    }

    public Set<Set<VCardProperty>> vcard_process_char(VCardParser vp, String content) throws Exception {
        Set<Set<VCardProperty>> vCardSet = new HashSet<>();
        String[] individualVcards = content.split("END:VCARD");
        for (String str : individualVcards) {
            char[] tmpChars = str.toCharArray();
            Log.d(this.TAG, "Piggy Check vCard Data : " + new String(tmpChars));
            if (tmpChars.length > 0) {
                Set<VCardProperty> aVCard = new HashSet<>();
                VCardProperty prop = new VCardProperty();
                for (char c : tmpChars) {
                    switch ($SWITCH_TABLE$com$nforetek$bt$res$vcard$VCardParserState()[vp.getState().ordinal()]) {
                        case 1:
                            switch (c) {
                                case '\n':
                                    vcard_clear_line(vp, prop);
                                    vp.setState(VCardParserState.cp_PropName);
                                    break;
                                case '\r':
                                    break;
                                case ':':
                                    vcard_clear_line(vp, prop);
                                    vp.setState(VCardParserState.cp_PropData);
                                    break;
                                case ';':
                                    vcard_clear_line(vp, prop);
                                    vp.setState(VCardParserState.cp_ParamBN);
                                    break;
                                default:
                                    vp.getLineBuf().append(c);
                                    break;
                            }
                        case 2:
                            switch (c) {
                                case ':':
                                    vcard_clear_line(vp, prop);
                                    vp.setState(VCardParserState.cp_PropData);
                                    break;
                                default:
                                    if (c != ' ') {
                                        vp.getLineBuf().append(c);
                                        vp.setState(VCardParserState.cp_ParamIN);
                                        break;
                                    } else {
                                        break;
                                    }
                            }
                        case 3:
                            switch (c) {
                                case ':':
                                    vcard_clear_line(vp, prop);
                                    vp.setState(VCardParserState.cp_PropData);
                                    break;
                                case ';':
                                    vp.getLineBuf().append(c);
                                    vcard_clear_line(vp, prop);
                                    vp.setState(VCardParserState.cp_ParamBN);
                                    break;
                                case '<':
                                default:
                                    if (c != ' ') {
                                        vp.getLineBuf().append(c);
                                        break;
                                    } else {
                                        break;
                                    }
                                case '=':
                                    vp.getLineBuf().append(c);
                                    vcard_clear_line(vp, prop);
                                    vp.setState(VCardParserState.cp_ValueBV);
                                    break;
                            }
                        case 4:
                            switch (c) {
                                case ':':
                                    vcard_clear_line(vp, prop);
                                    vp.setState(VCardParserState.cp_PropData);
                                    break;
                                case ';':
                                    vp.getLineBuf().append(c);
                                    vp.setState(VCardParserState.cp_ParamBN);
                                    break;
                                default:
                                    if (c != ' ') {
                                        vp.getLineBuf().append(c);
                                        vp.setState(VCardParserState.cp_ValueIV);
                                        break;
                                    } else {
                                        break;
                                    }
                            }
                        case 5:
                            switch (c) {
                                case ':':
                                    vcard_clear_line(vp, prop);
                                    vp.setState(VCardParserState.cp_PropData);
                                    break;
                                case ';':
                                    vp.getLineBuf().append(c);
                                    vcard_clear_line(vp, prop);
                                    vp.setState(VCardParserState.cp_ParamBN);
                                    break;
                                default:
                                    if (c != ' ') {
                                        vp.getLineBuf().append(c);
                                        break;
                                    } else {
                                        break;
                                    }
                            }
                        case 6:
                            switch (c) {
                                case '\n':
                                    vcard_clear_line(vp, prop);
                                    vp.setState(VCardParserState.cp_PropName);
                                    aVCard.add(prop);
                                    prop = new VCardProperty();
                                    break;
                                case 11:
                                case '\f':
                                default:
                                    if (c == '=') {
                                        vp.setState(VCardParserState.cp_ValueCheck);
                                        break;
                                    } else {
                                        vp.getLineBuf().append(c);
                                        break;
                                    }
                                case '\r':
                                    break;
                            }
                        case 7:
                            switch (c) {
                                case '\n':
                                    vcard_clear_line(vp, prop);
                                    aVCard.add(prop);
                                    prop = new VCardProperty();
                                    vp.setState(VCardParserState.cp_PropName);
                                    break;
                                case '\r':
                                    break;
                                case '0':
                                    vp.setState(VCardParserState.cp_PropData);
                                    break;
                                default:
                                    vp.setState(VCardParserState.cp_PropData);
                                    vp.getLineBuf().append('=');
                                    vp.getLineBuf().append(c);
                                    break;
                            }
                    }
                }
                vCardSet.add(aVCard);
            }
        }
        return vCardSet;
    }

    void vcard_clear_line(VCardParser vp, VCardProperty prop) throws Exception {
        switch ($SWITCH_TABLE$com$nforetek$bt$res$vcard$VCardParserState()[vp.getState().ordinal()]) {
            case 1:
                prop.setPropName(vp.getLineBuf().toString());
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                prop.setParams(String.valueOf(prop.getParams()) + vp.getLineBuf().toString());
                break;
            case 6:
            case 7:
                vp.getLineBuf().toString().replaceAll(":", "");
                String decodeMsg = vp.getLineBuf().toString().replaceAll(";", "");
                if (prop.getParams().trim().length() > 0) {
                    String[] params = prop.getParams().indexOf(";") != -1 ? prop.getParams().split(";") : new String[]{prop.getParams()};
                    for (String str : params) {
                        String[] partition = str.split("=");
                        if (partition[0].trim().equals("ENCODING")) {
                            String paramValue = partition[1];
                            if (paramValue.trim().equals("QUOTED-PRINTABLE")) {
                                QuotedPrintableCodec qpDecoder = new QuotedPrintableCodec();
                                decodeMsg = qpDecoder.decode(decodeMsg);
                            }
                        }
                    }
                }
                prop.setPropData(decodeMsg);
                break;
        }
        vp.getLineBuf().delete(0, vp.getLineBuf().length());
    }

    public static void main(String[] args) {
        VCardParseProcessor pp = new VCardParseProcessor();
        VCardParser vp = new VCardParser();
        try {
            new HashSet();
            pp.doParsing(vp, "BEGIN:VCARD..\r\nFN;CHARSET=UTF-7:+XeZmU3Oy-..\r\nN;CHARSET=UTF-7:+XeZmU3Oy-..\r\nTEL;CELL:10607522961819..\r\nEND:VCARD");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
