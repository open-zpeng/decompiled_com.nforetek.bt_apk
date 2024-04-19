package com.nforetek.bt.profile.map.java.vcard;

import com.nforetek.bt.profile.map.java.vcard.exception.VCardException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class VCardParser_V40 extends VCardParser {
    private final VCardParserImpl_V40 mVCardParserImpl;
    static final Set<String> sKnownPropertyNameSet = Collections.unmodifiableSet(new HashSet(Arrays.asList("BEGIN", "END", "VERSION", "SOURCE", "KIND", "FN", "N", "NICKNAME", "PHOTO", "BDAY", "ANNIVERSARY", "GENDER", "ADR", "TEL", "EMAIL", "IMPP", "LANG", "TZ", "GEO", "TITLE", "ROLE", "LOGO", "ORG", "MEMBER", "RELATED", "CATEGORIES", "NOTE", "PRODID", "REV", "SOUND", "UID", "CLIENTPIDMAP", "URL", "KEY", "FBURL", "CALENDRURI", "CALURI", "XML")));
    static final Set<String> sAcceptableEncoding = Collections.unmodifiableSet(new HashSet(Arrays.asList("8BIT", "B")));

    public VCardParser_V40() {
        this.mVCardParserImpl = new VCardParserImpl_V40();
    }

    public VCardParser_V40(int vcardType) {
        this.mVCardParserImpl = new VCardParserImpl_V40(vcardType);
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardParser
    public void addInterpreter(VCardInterpreter interpreter) {
        this.mVCardParserImpl.addInterpreter(interpreter);
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardParser
    public void parse(InputStream is) throws IOException, VCardException {
        this.mVCardParserImpl.parse(is);
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardParser
    public void parseOne(InputStream is) throws IOException, VCardException {
        this.mVCardParserImpl.parseOne(is);
    }

    @Override // com.nforetek.bt.profile.map.java.vcard.VCardParser
    public void cancel() {
        this.mVCardParserImpl.cancel();
    }
}
