package com.beetstra.jutf7;
/* loaded from: classes.dex */
class UTF7Charset extends UTF7StyleCharset {
    private static final String BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final String RULE_3 = " \t\r\n";
    private static final String SET_D = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789'(),-./:?";
    private static final String SET_O = "!\"#$%&*;<=>@[]^_`{|}";
    final String directlyEncoded;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UTF7Charset(String name, String[] aliases, boolean includeOptional) {
        super(name, aliases, BASE64_ALPHABET, false);
        if (includeOptional) {
            this.directlyEncoded = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789'(),-./:?!\"#$%&*;<=>@[]^_`{|} \t\r\n";
        } else {
            this.directlyEncoded = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789'(),-./:? \t\r\n";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.beetstra.jutf7.UTF7StyleCharset
    public boolean canEncodeDirectly(char ch) {
        return this.directlyEncoded.indexOf(ch) >= 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.beetstra.jutf7.UTF7StyleCharset
    public byte shift() {
        return (byte) 43;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.beetstra.jutf7.UTF7StyleCharset
    public byte unshift() {
        return (byte) 45;
    }
}
