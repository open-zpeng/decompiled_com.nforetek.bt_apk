package com.beetstra.jutf7;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
/* loaded from: classes.dex */
class UTF7StyleCharsetEncoder extends CharsetEncoder {
    private static final float AVG_BYTES_PER_CHAR = 1.5f;
    private static final float MAX_BYTES_PER_CHAR = 5.0f;
    static boolean useUglyHackToForceCallToFlushInJava5;
    private final Base64Util base64;
    private boolean base64mode;
    private int bitsToOutput;
    private final UTF7StyleCharset cs;
    private int sextet;
    private final byte shift;
    private final boolean strict;
    private final byte unshift;

    static {
        String version = System.getProperty("java.specification.version");
        String vendor = System.getProperty("java.vm.vendor");
        useUglyHackToForceCallToFlushInJava5 = "1.4".equals(version) || "1.5".equals(version);
        useUglyHackToForceCallToFlushInJava5 &= "Sun Microsystems Inc.".equals(vendor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UTF7StyleCharsetEncoder(UTF7StyleCharset cs, Base64Util base64, boolean strict) {
        super(cs, AVG_BYTES_PER_CHAR, MAX_BYTES_PER_CHAR);
        this.cs = cs;
        this.base64 = base64;
        this.strict = strict;
        this.shift = cs.shift();
        this.unshift = cs.unshift();
    }

    @Override // java.nio.charset.CharsetEncoder
    protected void implReset() {
        this.base64mode = false;
        this.sextet = 0;
        this.bitsToOutput = 0;
    }

    @Override // java.nio.charset.CharsetEncoder
    protected CoderResult implFlush(ByteBuffer out) {
        if (this.base64mode) {
            if (out.remaining() < 2) {
                return CoderResult.OVERFLOW;
            }
            if (this.bitsToOutput != 0) {
                out.put(this.base64.getChar(this.sextet));
            }
            out.put(this.unshift);
        }
        return CoderResult.UNDERFLOW;
    }

    @Override // java.nio.charset.CharsetEncoder
    protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
        while (in.hasRemaining()) {
            if (out.remaining() < 4) {
                return CoderResult.OVERFLOW;
            }
            char ch = in.get();
            if (this.cs.canEncodeDirectly(ch)) {
                unshift(out, ch);
                out.put((byte) ch);
            } else if (!this.base64mode && ch == this.shift) {
                out.put(this.shift);
                out.put(this.unshift);
            } else {
                encodeBase64(ch, out);
            }
        }
        if (this.base64mode && useUglyHackToForceCallToFlushInJava5 && out.limit() != MAX_BYTES_PER_CHAR * in.limit()) {
            return CoderResult.OVERFLOW;
        }
        return CoderResult.UNDERFLOW;
    }

    private void unshift(ByteBuffer out, char ch) {
        if (this.base64mode) {
            if (this.bitsToOutput != 0) {
                out.put(this.base64.getChar(this.sextet));
            }
            if (this.base64.contains(ch) || ch == this.unshift || this.strict) {
                out.put(this.unshift);
            }
            this.base64mode = false;
            this.sextet = 0;
            this.bitsToOutput = 0;
        }
    }

    private void encodeBase64(char ch, ByteBuffer out) {
        if (!this.base64mode) {
            out.put(this.shift);
        }
        this.base64mode = true;
        this.bitsToOutput += 16;
        while (this.bitsToOutput >= 6) {
            this.bitsToOutput -= 6;
            this.sextet += ch >> this.bitsToOutput;
            this.sextet &= 63;
            out.put(this.base64.getChar(this.sextet));
            this.sextet = 0;
        }
        this.sextet = (ch << (6 - this.bitsToOutput)) & 63;
    }
}
