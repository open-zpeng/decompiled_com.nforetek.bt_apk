package com.nforetek.bt.res.vcard;
/* loaded from: classes.dex */
public class VCardParser {
    String TAG = "VCardParser";
    private VCardParserState state = VCardParserState.cp_PropName;
    private StringBuffer lineBuf = new StringBuffer();
    private VCardParserEncoding encoding = VCardParserEncoding.cp_EncodingNone;

    public VCardParserState getState() {
        return this.state;
    }

    public void setState(VCardParserState state) {
        this.state = state;
    }

    public StringBuffer getLineBuf() {
        return this.lineBuf;
    }

    public void setLineBuf(StringBuffer lineBuf) {
        this.lineBuf = lineBuf;
    }
}
