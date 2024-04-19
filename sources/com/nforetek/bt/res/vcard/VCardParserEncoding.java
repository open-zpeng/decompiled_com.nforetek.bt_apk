package com.nforetek.bt.res.vcard;
/* loaded from: classes.dex */
public enum VCardParserEncoding {
    cp_EncodingNone,
    cp_EncodingQP,
    cp_EncodingBase64,
    cp_Encoding8Bit;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static VCardParserEncoding[] valuesCustom() {
        VCardParserEncoding[] valuesCustom = values();
        int length = valuesCustom.length;
        VCardParserEncoding[] vCardParserEncodingArr = new VCardParserEncoding[length];
        System.arraycopy(valuesCustom, 0, vCardParserEncodingArr, 0, length);
        return vCardParserEncodingArr;
    }
}
