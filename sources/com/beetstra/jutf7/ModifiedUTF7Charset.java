package com.beetstra.jutf7;
/* loaded from: classes.dex */
class ModifiedUTF7Charset extends UTF7StyleCharset {
    private static final String MODIFIED_BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+,";

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModifiedUTF7Charset(String name, String[] aliases) {
        super(name, aliases, MODIFIED_BASE64_ALPHABET, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.beetstra.jutf7.UTF7StyleCharset
    public boolean canEncodeDirectly(char ch) {
        return ch != shift() && ch >= ' ' && ch <= '~';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.beetstra.jutf7.UTF7StyleCharset
    public byte shift() {
        return (byte) 38;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.beetstra.jutf7.UTF7StyleCharset
    public byte unshift() {
        return (byte) 45;
    }
}
