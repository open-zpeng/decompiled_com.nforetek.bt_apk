package com.beetstra.jutf7;

import java.util.Arrays;
/* loaded from: classes.dex */
class Base64Util {
    private static final int ALPHABET_LENGTH = 64;
    private final char[] alphabet;
    private final int[] inverseAlphabet;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Base64Util(String alphabet) {
        this.alphabet = alphabet.toCharArray();
        if (alphabet.length() != 64) {
            throw new IllegalArgumentException(new StringBuffer().append("alphabet has incorrect length (should be 64, not ").append(alphabet.length()).append(")").toString());
        }
        this.inverseAlphabet = new int[128];
        Arrays.fill(this.inverseAlphabet, -1);
        for (int i = 0; i < this.alphabet.length; i++) {
            char ch = this.alphabet[i];
            if (ch >= 128) {
                throw new IllegalArgumentException(new StringBuffer().append("invalid character in alphabet: ").append(ch).toString());
            }
            this.inverseAlphabet[ch] = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSextet(byte ch) {
        if (ch >= 128) {
            return -1;
        }
        return this.inverseAlphabet[ch];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean contains(char ch) {
        return ch < 128 && this.inverseAlphabet[ch] >= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte getChar(int sextet) {
        return (byte) this.alphabet[sextet];
    }
}
