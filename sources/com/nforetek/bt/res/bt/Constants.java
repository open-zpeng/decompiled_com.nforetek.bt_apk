package com.nforetek.bt.res.bt;

import android.util.Log;
import com.nforetek.bt.res.obex.HeaderSet;
import java.io.IOException;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class Constants {
    public static final String ACTION_COMPLETE_HIDE = "android.btopp.intent.action.HIDE_COMPLETE";
    public static final String ACTION_HIDE = "android.btopp.intent.action.HIDE";
    public static final String ACTION_INCOMING_FILE_CONFIRM = "android.btopp.intent.action.CONFIRM";
    public static final String ACTION_LIST = "android.btopp.intent.action.LIST";
    public static final String ACTION_OPEN = "android.btopp.intent.action.OPEN";
    public static final String ACTION_OPEN_INBOUND_TRANSFER = "android.btopp.intent.action.OPEN_INBOUND";
    public static final String ACTION_OPEN_OUTBOUND_TRANSFER = "android.btopp.intent.action.OPEN_OUTBOUND";
    public static final String ACTION_RETRY = "android.btopp.intent.action.RETRY";
    public static final int BATCH_STATUS_FAILED = 3;
    public static final int BATCH_STATUS_FINISHED = 2;
    public static final int BATCH_STATUS_PENDING = 0;
    public static final int BATCH_STATUS_RUNNING = 1;
    public static final String BLUETOOTHOPP_CHANNEL_PREFERENCE = "btopp_channels";
    public static final String BLUETOOTHOPP_NAME_PREFERENCE = "btopp_names";
    public static final boolean DEBUG = true;
    public static final String DEFAULT_STORE_SUBDIR = "/bluetooth";
    public static final int MAX_RECORDS_IN_DATABASE = 1000;
    public static final String MEDIA_SCANNED = "scanned";
    public static final int MEDIA_SCANNED_NOT_SCANNED = 0;
    public static final int MEDIA_SCANNED_SCANNED_FAILED = 2;
    public static final int MEDIA_SCANNED_SCANNED_OK = 1;
    public static final String TAG = "BluetoothOpp";
    public static final int TCP_DEBUG_PORT = 6500;
    public static final String THIS_PACKAGE_NAME = "com.android.bluetooth";
    public static final boolean USE_EMULATOR_DEBUG = false;
    public static final boolean USE_TCP_DEBUG = false;
    public static final boolean USE_TCP_SIMPLE_SERVER = false;
    public static final boolean VERBOSE = true;
    public static final String[] ACCEPTABLE_SHARE_OUTBOUND_TYPES = {"image/*", "text/x-vcard"};
    public static final String[] UNACCEPTABLE_SHARE_OUTBOUND_TYPES = {"virus/*"};
    public static final String[] ACCEPTABLE_SHARE_INBOUND_TYPES = {"image/*", "video/*", "audio/*", "text/x-vcard", "text/plain", "text/html", "application/zip", "application/vnd.ms-excel", "application/msword", "application/vnd.ms-powerpoint", "application/pdf"};
    public static final String[] UNACCEPTABLE_SHARE_INBOUND_TYPES = {"text/x-vcalendar"};
    public static String filename_SEQUENCE_SEPARATOR = "-";

    public static boolean mimeTypeMatches(String mimeType, String[] matchAgainst) {
        for (String matchType : matchAgainst) {
            if (mimeTypeMatches(mimeType, matchType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean mimeTypeMatches(String mimeType, String matchAgainst) {
        Pattern p = Pattern.compile(matchAgainst.replaceAll("\\*", "\\.\\*"), 2);
        return p.matcher(mimeType).matches();
    }

    public static void logHeader(HeaderSet hs) {
        Log.v(TAG, "Dumping HeaderSet " + hs.toString());
        try {
            Log.v(TAG, "COUNT : " + hs.getHeader(192));
            Log.v(TAG, "NAME : " + hs.getHeader(1));
            Log.v(TAG, "TYPE : " + hs.getHeader(66));
            Log.v(TAG, "LENGTH : " + hs.getHeader(195));
            Log.v(TAG, "TIME_ISO_8601 : " + hs.getHeader(68));
            Log.v(TAG, "TIME_4_BYTE : " + hs.getHeader(196));
            Log.v(TAG, "DESCRIPTION : " + hs.getHeader(5));
            Log.v(TAG, "TARGET : " + hs.getHeader(70));
            Log.v(TAG, "HTTP : " + hs.getHeader(71));
            Log.v(TAG, "WHO : " + hs.getHeader(74));
            Log.v(TAG, "OBJECT_CLASS : " + hs.getHeader(79));
            Log.v(TAG, "APPLICATION_PARAMETER : " + hs.getHeader(76));
        } catch (IOException e) {
            Log.e(TAG, "dump HeaderSet error " + e);
        }
    }
}
