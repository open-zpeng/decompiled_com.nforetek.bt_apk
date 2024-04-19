package com.nforetek.bt.res.vcard;
/* loaded from: classes.dex */
public enum VCardParserState {
    cp_PropName,
    cp_ParamBN,
    cp_ParamIN,
    cp_ValueBV,
    cp_ValueIV,
    cp_PropData,
    cp_ValueCheck;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static VCardParserState[] valuesCustom() {
        VCardParserState[] valuesCustom = values();
        int length = valuesCustom.length;
        VCardParserState[] vCardParserStateArr = new VCardParserState[length];
        System.arraycopy(valuesCustom, 0, vCardParserStateArr, 0, length);
        return vCardParserStateArr;
    }
}
