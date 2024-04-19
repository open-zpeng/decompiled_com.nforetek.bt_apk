package com.beetstra.jutf7;

import com.nforetek.bt.profile.map.java.NfMapCommand;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
/* loaded from: classes.dex */
class UTF7StyleCharsetDecoder extends CharsetDecoder {
    private final Base64Util base64;
    private boolean base64mode;
    private int bitsRead;
    private boolean justShifted;
    private boolean justUnshifted;
    private final byte shift;
    private final boolean strict;
    private int tempChar;
    private final byte unshift;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UTF7StyleCharsetDecoder(UTF7StyleCharset cs, Base64Util base64, boolean strict) {
        super(cs, 0.6f, 1.0f);
        this.base64 = base64;
        this.strict = strict;
        this.shift = cs.shift();
        this.unshift = cs.unshift();
    }

    @Override // java.nio.charset.CharsetDecoder
    protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
        while (in.hasRemaining()) {
            byte b = in.get();
            if (this.base64mode) {
                if (b == this.unshift) {
                    if (base64bitsWaiting()) {
                        return malformed(in);
                    }
                    if (this.justShifted) {
                        if (!out.hasRemaining()) {
                            return overflow(in);
                        }
                        out.put((char) this.shift);
                    } else {
                        this.justUnshifted = true;
                    }
                    setUnshifted();
                } else if (!out.hasRemaining()) {
                    return overflow(in);
                } else {
                    CoderResult result = handleBase64(in, out, b);
                    if (result != null) {
                        return result;
                    }
                }
                this.justShifted = false;
            } else if (b == this.shift) {
                this.base64mode = true;
                if (this.justUnshifted && this.strict) {
                    return malformed(in);
                }
                this.justShifted = true;
            } else if (!out.hasRemaining()) {
                return overflow(in);
            } else {
                out.put((char) b);
                this.justUnshifted = false;
            }
        }
        return CoderResult.UNDERFLOW;
    }

    private CoderResult overflow(ByteBuffer in) {
        in.position(in.position() - 1);
        return CoderResult.OVERFLOW;
    }

    private CoderResult handleBase64(ByteBuffer in, CharBuffer out, byte lastRead) {
        CoderResult result = null;
        int sextet = this.base64.getSextet(lastRead);
        if (sextet >= 0) {
            this.bitsRead += 6;
            if (this.bitsRead < 16) {
                this.tempChar += sextet << (16 - this.bitsRead);
            } else {
                this.bitsRead -= 16;
                this.tempChar += sextet >> this.bitsRead;
                out.put((char) this.tempChar);
                this.tempChar = (sextet << (16 - this.bitsRead)) & NfMapCommand.COUNT_DOWNLOAD_ALL;
            }
        } else if (this.strict) {
            return malformed(in);
        } else {
            out.put((char) lastRead);
            if (base64bitsWaiting()) {
                result = malformed(in);
            }
            setUnshifted();
        }
        return result;
    }

    @Override // java.nio.charset.CharsetDecoder
    protected CoderResult implFlush(CharBuffer out) {
        return ((this.base64mode && this.strict) || base64bitsWaiting()) ? CoderResult.malformedForLength(1) : CoderResult.UNDERFLOW;
    }

    @Override // java.nio.charset.CharsetDecoder
    protected void implReset() {
        setUnshifted();
        this.justUnshifted = false;
    }

    private CoderResult malformed(ByteBuffer in) {
        in.position(in.position() - 1);
        return CoderResult.malformedForLength(1);
    }

    private boolean base64bitsWaiting() {
        return this.tempChar != 0 || this.bitsRead >= 6;
    }

    private void setUnshifted() {
        this.base64mode = false;
        this.bitsRead = 0;
        this.tempChar = 0;
    }
}
