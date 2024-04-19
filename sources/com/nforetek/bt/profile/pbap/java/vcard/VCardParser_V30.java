package com.nforetek.bt.profile.pbap.java.vcard;

import com.nforetek.bt.profile.pbap.java.vcard.exception.VCardException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class VCardParser_V30 extends VCardParser {
    private final VCardParserImpl_V30 mVCardParserImpl;
    static final Set<String> sKnownPropertyNameSet = Collections.unmodifiableSet(new HashSet(Arrays.asList("BEGIN", "END", "LOGO", "PHOTO", "LABEL", "FN", "TITLE", "SOUND", "VERSION", "TEL", "EMAIL", "TZ", "GEO", "NOTE", "URL", "BDAY", "ROLE", "REV", "UID", "KEY", "MAILER", "NAME", "PROFILE", "SOURCE", "NICKNAME", "CLASS", "SORT-STRING", "CATEGORIES", "PRODID", "IMPP")));
    static final Set<String> sAcceptableEncoding = Collections.unmodifiableSet(new HashSet(Arrays.asList("7BIT", "8BIT", "BASE64", "B")));

    public VCardParser_V30() {
        this.mVCardParserImpl = new VCardParserImpl_V30();
    }

    public VCardParser_V30(int vcardType) {
        this.mVCardParserImpl = new VCardParserImpl_V30(vcardType);
    }

    @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardParser
    public void addInterpreter(VCardInterpreter interpreter) {
        this.mVCardParserImpl.addInterpreter(interpreter);
    }

    @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardParser
    public void parse(InputStream is) throws IOException, VCardException {
        this.mVCardParserImpl.parse(is);
    }

    @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardParser
    public void parseOne(InputStream is) throws IOException, VCardException {
        this.mVCardParserImpl.parseOne(is);
    }

    @Override // com.nforetek.bt.profile.pbap.java.vcard.VCardParser
    public void cancel() {
        this.mVCardParserImpl.cancel();
    }
}
