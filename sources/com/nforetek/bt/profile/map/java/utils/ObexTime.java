package com.nforetek.bt.profile.map.java.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes.dex */
public final class ObexTime {
    private Date mDate;

    public ObexTime(String time) {
        String time2 = time.substring(0, 15);
        SimpleDateFormat parser = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        if (time2 != null) {
            try {
                this.mDate = parser.parse(time2);
            } catch (ParseException e) {
            }
        }
    }

    public ObexTime(Date date) {
        this.mDate = date;
    }

    public Date getTime() {
        return this.mDate;
    }

    public String toString() {
        if (this.mDate == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.mDate);
        return String.format(Locale.US, "%04d%02d%02dT%02d%02d%02d", Integer.valueOf(cal.get(1)), Integer.valueOf(cal.get(2) + 1), Integer.valueOf(cal.get(5)), Integer.valueOf(cal.get(11)), Integer.valueOf(cal.get(12)), Integer.valueOf(cal.get(13)));
    }
}
