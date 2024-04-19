package com.beetstra.jutf7;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.net.StringEncodings;
/* loaded from: classes.dex */
abstract class UTF7StyleCharset extends Charset {
    private static final List CONTAINED = Arrays.asList(StringEncodings.US_ASCII, "ISO-8859-1", "UTF-8", "UTF-16", "UTF-16LE", "UTF-16BE");
    Base64Util base64;
    final boolean strict;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean canEncodeDirectly(char c);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte shift();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte unshift();

    /* JADX INFO: Access modifiers changed from: protected */
    public UTF7StyleCharset(String canonicalName, String[] aliases, String alphabet, boolean strict) {
        super(canonicalName, aliases);
        this.base64 = new Base64Util(alphabet);
        this.strict = strict;
    }

    @Override // java.nio.charset.Charset
    public boolean contains(Charset cs) {
        return CONTAINED.contains(cs.name());
    }

    @Override // java.nio.charset.Charset
    public CharsetDecoder newDecoder() {
        return new UTF7StyleCharsetDecoder(this, this.base64, this.strict);
    }

    @Override // java.nio.charset.Charset
    public CharsetEncoder newEncoder() {
        return new UTF7StyleCharsetEncoder(this, this.base64, this.strict);
    }
}
