package com.nforetek.bt.profile.pbap.java.vcard;

import com.nforetek.bt.profile.pbap.java.vcard.exception.VCardException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class VCardParser_V21 extends VCardParser {
    private final VCardParserImpl_V21 mVCardParserImpl;
    static final Set<String> sKnownPropertyNameSet = Collections.unmodifiableSet(new HashSet(Arrays.asList("BEGIN", "END", "LOGO", "PHOTO", "LABEL", "FN", "TITLE", "SOUND", "VERSION", "TEL", "EMAIL", "TZ", "GEO", "NOTE", "URL", "BDAY", "ROLE", "REV", "UID", "KEY", "MAILER")));
    static final Set<String> sKnownTypeSet = Collections.unmodifiableSet(new HashSet(Arrays.asList("DOM", "INTL", VCardConstants.PARAM_ADR_TYPE_POSTAL, "PARCEL", "HOME", "WORK", "PREF", "VOICE", "FAX", "MSG", "CELL", "PAGER", "BBS", "MODEM", "CAR", "ISDN", "VIDEO", "AOL", "APPLELINK", "ATTMAIL", "CIS", "EWORLD", "INTERNET", "IBMMAIL", "MCIMAIL", "POWERSHARE", "PRODIGY", "TLX", VCardConstants.PARAM_TYPE_X400, "GIF", "CGM", "WMF", "BMP", "MET", "PMB", "DIB", "PICT", "TIFF", "PDF", "PS", "JPEG", "QTIME", "MPEG", "MPEG2", "AVI", "WAVE", "AIFF", "PCM", "X509", "PGP")));
    static final Set<String> sKnownValueSet = Collections.unmodifiableSet(new HashSet(Arrays.asList("INLINE", "URL", "CONTENT-ID", "CID")));
    static final Set<String> sAvailableEncoding = Collections.unmodifiableSet(new HashSet(Arrays.asList("7BIT", "8BIT", "QUOTED-PRINTABLE", "BASE64", "B")));

    public VCardParser_V21() {
        this.mVCardParserImpl = new VCardParserImpl_V21();
    }

    public VCardParser_V21(int vcardType) {
        this.mVCardParserImpl = new VCardParserImpl_V21(vcardType);
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
        if (this.mVCardParserImpl != null) {
            this.mVCardParserImpl.cancel();
        }
    }
}
