package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.profile.map.java.BluetoothMasClient;
import com.nforetek.bt.profile.map.java.utils.ObexAppParameters;
import com.nforetek.bt.profile.map.java.utils.ObexTime;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.obex.ClientSession;
import com.nforetek.bt.res.obex.HeaderSet;
import com.nforetek.util.normal.NfLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BluetoothMasRequestGetMessagesListing extends BluetoothMasRequest {
    private static final boolean BlockCode = true;
    private static final boolean DBG = false;
    private static final String TAG = "NfMapReqMsgListing";
    private static final String TYPE = "x-bt/MAP-msg-listing";
    private HeaderSet defaultHeaderSet;
    private HeaderSet defaultHeaderSet2;
    private HeaderSet finalHeaderSet;
    private BluetoothMasClient.MessagesFilter mFilter;
    private String mFolderName;
    private int mListStartOffset;
    private int mMaxListCount;
    private int mParameters;
    private int mSubjectLength;
    private BluetoothMapMessagesListing mResponse = null;
    private boolean mNewMessage = false;
    private Date mServerTime = null;
    private int failcount = 0;

    public void setDefault(String folderName) {
        NfLog.d(TAG, "mHeaderSet only folderName");
        this.mHeaderSet = new HeaderSet();
        this.mHeaderSet.setHeader(66, TYPE);
        if (folderName == null) {
            this.mHeaderSet.setHeader(1, "");
        } else {
            this.mHeaderSet.setHeader(1, folderName);
        }
    }

    public void setFinal() {
        NfLog.d(TAG, "setFinal");
        this.mHeaderSet = setParamters(this.mFolderName, 0, null, -1, 0, 0);
    }

    public void setDefault(String folderName, int subjectLength, int maxListCount) {
        NfLog.d(TAG, "mHeaderSet without mSubjectLength");
        this.mHeaderSet = setParamters(this.mFolderName, this.mParameters, this.mFilter, -1, this.mMaxListCount, this.mListStartOffset);
    }

    public void setDefault(double d) {
        NfLog.d(TAG, "mHeaderSet without paramters");
        this.mHeaderSet = setParamters(this.mFolderName, 0, this.mFilter, this.mSubjectLength, this.mMaxListCount, this.mListStartOffset);
    }

    public BluetoothMasRequestGetMessagesListing(String folderName, int parameters, BluetoothMasClient.MessagesFilter filter, int subjectLength, int maxListCount, int listStartOffset) {
        subjectLength = subjectLength > 255 ? 255 : subjectLength;
        maxListCount = maxListCount > 65535 ? NfMapCommand.COUNT_DOWNLOAD_ALL : maxListCount;
        listStartOffset = listStartOffset > 65535 ? NfMapCommand.COUNT_DOWNLOAD_ALL : listStartOffset;
        this.mFolderName = folderName;
        this.mParameters = parameters;
        this.mFilter = filter;
        this.mSubjectLength = subjectLength;
        if (this.mSubjectLength == 0) {
            this.mParameters = 30;
        }
        this.mMaxListCount = maxListCount;
        this.mListStartOffset = listStartOffset;
        this.mHeaderSet = setParamters(folderName, this.mParameters, filter, subjectLength, maxListCount, listStartOffset);
    }

    public HeaderSet setParamters(String folderName, int parameters, BluetoothMasClient.MessagesFilter filter, int subjectLength, int maxListCount, int listStartOffset) {
        HeaderSet headerSet = new HeaderSet();
        headerSet.setHeader(66, TYPE);
        if (folderName == null) {
            headerSet.setHeader(1, "");
        } else {
            headerSet.setHeader(1, folderName);
        }
        ObexAppParameters oap = new ObexAppParameters();
        oap.add((byte) 3, (byte) 12);
        if (filter != null) {
            if (filter.messageType != 0) {
                oap.add((byte) 3, filter.messageType);
            }
            if (filter.periodBegin != null) {
                NfLog.d(TAG, "periodBegin " + filter.periodBegin);
                oap.add((byte) 4, filter.periodBegin);
            }
            if (filter.periodEnd != null) {
                NfLog.d(TAG, "periodEnd " + filter.periodEnd);
                oap.add((byte) 5, filter.periodEnd);
            }
            if (filter.readStatus != 0) {
                NfLog.d(TAG, "readStatus " + ((int) filter.readStatus));
                oap.add((byte) 6, filter.readStatus);
            }
            if (filter.recipient != null) {
                NfLog.d(TAG, "recipient " + filter.recipient);
                oap.add((byte) 7, filter.recipient);
            }
            if (filter.originator != null) {
                NfLog.d(TAG, "originator " + filter.originator);
                oap.add((byte) 8, filter.originator);
            }
            if (filter.priority != 0) {
                oap.add((byte) 9, filter.priority);
            }
        }
        if (subjectLength >= 0) {
            NfLog.d(TAG, "subjectLength " + ((int) ((byte) subjectLength)));
            oap.add(NfDef.AVRCP_BROWSING_STATUS_PLAYER_NOT_BROWSABLE, (byte) subjectLength);
        }
        if (parameters > 0) {
            oap.add(NfDef.AVRCP_BROWSING_STATUS_SEARCH_NOT_SUPPORT, parameters);
        }
        if (maxListCount >= 0) {
            oap.add((byte) 1, (short) maxListCount);
        }
        if (listStartOffset > 0) {
            oap.add((byte) 2, (short) listStartOffset);
        }
        oap.addToHeaderSet(headerSet);
        return headerSet;
    }

    public void downloadInterrupt() {
        NfLog.d(TAG, "Request downloadInterrupt");
        this.mResponse.downloadInterrupt();
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    protected void readResponse(InputStream stream) {
        NfLog.d(false, TAG, "readResponse");
        this.mResponse = new BluetoothMapMessagesListing(stream);
        if (!isInterrupted()) {
            this.mResponse.parse(stream);
            NfLog.d(false, TAG, "readResponse end");
        }
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    protected void readResponseHeaders(HeaderSet headerset) {
        NfLog.d(false, TAG, "readResponseHeaders");
        ObexAppParameters oap = ObexAppParameters.fromHeaderSet(headerset);
        this.mNewMessage = (oap.getByte((byte) 13) & 1) == 1;
        NfLog.d(false, TAG, oap.toString());
        if (this.mNewMessage) {
            NfLog.d("oap", "mNewMessage");
        }
        if (oap.exists((byte) 25)) {
            String mseTime = oap.getString((byte) 25);
            NfLog.d(" ", "requestList, get header mseTime: " + mseTime);
            if (mseTime != null) {
                this.mServerTime = new ObexTime(mseTime).getTime();
            }
            NfLog.d(TAG, "mServerTime: " + dateToString(this.mServerTime));
        }
        if (oap.exists(NfDef.AVRCP_BROWSING_STATUS_INVALID_PLAYER_ID)) {
            int listSize = oap.getShort(NfDef.AVRCP_BROWSING_STATUS_INVALID_PLAYER_ID);
            NfLog.d("listSize", " " + listSize);
        }
        NfLog.d(false, TAG, "readResponseHeaders end");
    }

    public String dateToString(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date == null) {
            cal.setTime(new Date());
        } else {
            cal.setTime(date);
        }
        return String.format(Locale.US, "%04d%02d%02dT%02d%02d%02dZ", Integer.valueOf(cal.get(1)), Integer.valueOf(cal.get(2) + 1), Integer.valueOf(cal.get(5)), Integer.valueOf(cal.get(11)), Integer.valueOf(cal.get(12)), Integer.valueOf(cal.get(13)));
    }

    public ArrayList<BluetoothMapMessage> getList() {
        if (this.mResponse == null) {
            return null;
        }
        return this.mResponse.getList();
    }

    public boolean getNewMessageStatus() {
        return this.mNewMessage;
    }

    public Date getMseTime() {
        return this.mServerTime;
    }

    public String getFolder() {
        return this.mFolderName;
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    public void execute(ClientSession session) throws IOException {
        this.failcount++;
        NfLog.d(TAG, "execute count " + this.failcount);
        if (this.failcount == 2) {
            setDefault(this.mFolderName, this.mSubjectLength, this.mMaxListCount);
        } else if (this.failcount == 3) {
            setDefault(3.3d);
        } else if (this.failcount == 4) {
            setDefault(this.mFolderName);
        } else if (this.failcount == 5) {
            setFinal();
        }
        executeGet(session);
    }
}
