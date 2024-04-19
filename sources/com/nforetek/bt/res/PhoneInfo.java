package com.nforetek.bt.res;

import java.io.Serializable;
/* loaded from: classes.dex */
public class PhoneInfo implements Serializable {
    private String calledHistoryDate;
    private String calledHistoryTime;
    private String phoneNumber;
    private String phoneType;
    private String phoneTypeName;

    public String getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneTypeName() {
        return this.phoneTypeName;
    }

    public void setPhoneTypeName(String phoneTypeName) {
        this.phoneTypeName = phoneTypeName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCalledHistoryDate() {
        return this.calledHistoryDate;
    }

    public void setCalledHistoryDate(String calledHistoryDate) {
        this.calledHistoryDate = calledHistoryDate;
    }

    public String getCalledHistoryTime() {
        return this.calledHistoryTime;
    }

    public void setCalledHistoryTime(String calledHistoryTime) {
        this.calledHistoryTime = calledHistoryTime;
    }
}
