package com.nforetek.bt.res.vcard;
/* loaded from: classes.dex */
public class VCardProperty {
    String TAG = "VCardProperty";
    private String params = "";
    private String propData;
    private String propName;

    public String getPropName() {
        return this.propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropData() {
        return this.propData;
    }

    public void setPropData(String propData) {
        this.propData = propData;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
