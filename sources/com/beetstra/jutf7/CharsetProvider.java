package com.beetstra.jutf7;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.codec.net.StringEncodings;
/* loaded from: classes.dex */
public class CharsetProvider extends java.nio.charset.spi.CharsetProvider {
    private static final String UTF7_M_NAME = "X-MODIFIED-UTF-7";
    private static final String UTF7_NAME = "UTF-7";
    private static final String UTF7_O_NAME = "X-UTF-7-OPTIONAL";
    private static final String[] UTF7_ALIASES = {"UNICODE-1-1-UTF-7", "CSUNICODE11UTF7", "X-RFC2152", "X-RFC-2152"};
    private static final String[] UTF7_O_ALIASES = {"X-RFC2152-OPTIONAL", "X-RFC-2152-OPTIONAL"};
    private static final String[] UTF7_M_ALIASES = {"X-IMAP-MODIFIED-UTF-7", "X-IMAP4-MODIFIED-UTF7", "X-IMAP4-MODIFIED-UTF-7", "X-RFC3501", "X-RFC-3501"};
    private Charset utf7charset = new UTF7Charset(UTF7_NAME, UTF7_ALIASES, false);
    private Charset utf7oCharset = new UTF7Charset(UTF7_O_NAME, UTF7_O_ALIASES, true);
    private Charset imap4charset = new ModifiedUTF7Charset(UTF7_M_NAME, UTF7_M_ALIASES);
    private List charsets = Arrays.asList(this.utf7charset, this.imap4charset, this.utf7oCharset);

    @Override // java.nio.charset.spi.CharsetProvider
    public Charset charsetForName(String charsetName) {
        String charsetName2 = charsetName.toUpperCase(Locale.US);
        for (Charset charset : this.charsets) {
            if (charset.name().equals(charsetName2)) {
                return charset;
            }
        }
        for (Charset charset2 : this.charsets) {
            if (charset2.aliases().contains(charsetName2)) {
                return charset2;
            }
        }
        return null;
    }

    @Override // java.nio.charset.spi.CharsetProvider
    public Iterator charsets() {
        return this.charsets.iterator();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        if (args.length < 2) {
            showUsage();
        } else if ("encode".equalsIgnoreCase(args[0])) {
            byte[] encoded = args[1].getBytes(UTF7_NAME);
            System.out.println(new String(encoded, StringEncodings.US_ASCII));
        } else if ("decode".equalsIgnoreCase(args[0])) {
            byte[] bytes = args[1].getBytes(StringEncodings.US_ASCII);
            System.out.println(new String(bytes, UTF7_NAME));
        } else {
            showUsage();
        }
    }

    private static void showUsage() {
        System.out.println("Usage: java -jar jutf7.jar [encode|decode] <text>");
        System.out.println();
        System.out.println("Example: java -jar jutf7 encode cafï¿½");
        System.out.println("Result: caf+AOk-");
    }
}
