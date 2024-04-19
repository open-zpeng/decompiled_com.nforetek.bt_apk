package com.nforetek.bt.res.bt;

import android.os.Build;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.normal.NfLog;
import java.util.Properties;
/* loaded from: classes.dex */
public class NfConfig {
    private static final String CALLBACK_BY_AIDL = "CALLBACK_BY_AIDL";
    private static final String CALLBACK_BY_BROADCAST = "CALLBACK_BY_BROADCAST";
    private static final String KEY_A2DP_PROFILE_ENABLE = "A2DP_PROFILE_ENABLE";
    private static final String KEY_AFTER_DISCONNECT_DELAY_TIME = "AFTER_DISCONNECT_DELAY_TIME";
    private static final String KEY_ALLOW_MULTI_CONNECTION = "ALLOW_MULTI_CONNECTION";
    private static final String KEY_ALLOW_ROLE_SWITCH = "ALLOW_ROLE_SWITCH";
    private static final String KEY_ALLOW_SCO_TRANSFER_BEFORE_CALL_ACTIVE = "ALLOW_SCO_TRANSFER_BEFORE_CALL_ACTIVE";
    private static final String KEY_AUTO_CONNECT_HIGH_FREQUENCY_DELAY_TIME = "AUTO_CONNECT_HIGH_FREQUENCY_DELAY_TIME";
    private static final String KEY_AUTO_CONNECT_HIGH_FREQUENCY_INTERVAL = "AUTO_CONNECT_HIGH_FREQUENCY_INTERVAL";
    private static final String KEY_AUTO_CONNECT_LOW_FREQUENCY_DELAY_TIME = "AUTO_CONNECT_LOW_FREQUENCY_DELAY_TIME";
    private static final String KEY_AVRCP_10COMMAND_PROTECT_TIME = "AVRCP_10COMMAND_PROTECT_TIME";
    private static final String KEY_AVRCP_PROFILE_ENABLE = "AVRCP_PROFILE_ENABLE";
    private static final String KEY_BEFORE_CONNECT_ALL_DELAY_TIME = "BEFORE_CONNECT_DELAY_TIME";
    private static final String KEY_BT_ONOFF_COMMAND_PROTECT_TIME = "BT_ONOFF_COMMAND_PROTECT_TIME";
    private static final String KEY_DEFAULT_CAR_MODE = "DEFAULT_CAR_MODE";
    private static final String KEY_DUMP_PBAP_CONTACT_IN_LOG = "DUMP_PBAP_CONTACT_IN_LOG";
    private static final String KEY_DUN_PROFILE_ENABLE = "DUN_PROFILE_ENABLE";
    private static final String KEY_EACH_PROFILE_DELAY_TIME = "EACH_PROFILE_DELAY_TIME";
    private static final String KEY_FTP_PROFILE_ENABLE = "FTP_PROFILE_ENABLE";
    private static final String KEY_GATTS_PROFILE_ENABLE = "GATTS_PROFILE_ENABLE";
    private static final String KEY_HFP_MIC_DEFAULT_MUTE = "HfpMicDefaultMute";
    private static final String KEY_HFP_PROFILE_ENABLE = "HFP_PROFILE_ENABLE";
    private static final String KEY_HFP_TERMINATE_COMMAND_PROTECT_TIME = "HFP_TERMINATE_COMMAND_PROTECT_TIME";
    private static final String KEY_HID_PROFILE_ENABLE = "HID_PROFILE_ENABLE";
    private static final String KEY_HSP_PROFILE_ENABLE = "HSP_PROFILE_ENABLE";
    private static final String KEY_LOG_LEVEL_DEBUG = "LOG_LEVEL_DEBUG";
    private static final String KEY_LOG_LEVEL_INFO = "LOG_LEVEL_INFO";
    private static final String KEY_LOG_LEVEL_VERBOSE = "LOG_LEVEL_VERBOSE";
    private static final String KEY_LOG_LEVEL_WARNING = "LOG_LEVEL_WARNING";
    private static final String KEY_MAP_PROFILE_ENABLE = "MAP_PROFILE_ENABLE";
    private static final String KEY_OPP_PROFILE_ENABLE = "OPP_PROFILE_ENABLE";
    private static final String KEY_PBAP_PROFILE_ENABLE = "PBAP_PROFILE_ENABLE";
    private static final String KEY_PCM_SCO_ENABLE = "PcmScoEnable";
    private static final String KEY_PTS_TEST = "PtsTest";
    private static final String KEY_REDO_BASIC_CONNECT = "REDO_BASIC_CONNECT";
    private static final String KEY_SET_UNCONNECTABLE_AFTER_DEVICE_CONNECTED = "SET_UNCONNECTABLE_AFTER_DEVICE_CONNECTED";
    private static final String KEY_SPP_LISTEN_UUID = "SPP_LISTEN_UUID";
    private static final String KEY_SPP_PROFILE_ENABLE = "SPP_PROFILE_ENABLE";
    private static final boolean PRIVATE_PROFILE_ENABLE_A2DP = true;
    private static final boolean PRIVATE_PROFILE_ENABLE_AVRCP = true;
    private static final boolean PRIVATE_PROFILE_ENABLE_DUN;
    private static final boolean PRIVATE_PROFILE_ENABLE_FTP;
    private static final boolean PRIVATE_PROFILE_ENABLE_GATT_SERVER;
    private static final boolean PRIVATE_PROFILE_ENABLE_HFP = true;
    private static final boolean PRIVATE_PROFILE_ENABLE_HID;
    private static final boolean PRIVATE_PROFILE_ENABLE_HSP;
    private static final boolean PRIVATE_PROFILE_ENABLE_MAP = true;
    private static final boolean PRIVATE_PROFILE_ENABLE_OPP = false;
    private static final boolean PRIVATE_PROFILE_ENABLE_PBAP = true;
    private static final boolean PRIVATE_PROFILE_ENABLE_SPP = true;
    private static final String PROPERTY_FILE = "/system/etc/bluetooth/nForeBluetooth.properties";
    private static String TAG = "NfConfig";
    private static int sAfterDisconnectDelayTime;
    private static int sAutoConnectHighFrequencyDelayTime;
    private static int sAutoConnectHighFrequencyInterval;
    private static int sAutoConnectLowFrequencyDelayTime;
    private static int sBeforeConnectDelayTime;
    private static int sEachProfileDelayTime;
    private static boolean sIsAllowMultiConnection;
    private static boolean sIsAllowRoleSwitch;
    private static boolean sIsAllowScoTransferBeforeCallActive;
    private static boolean sIsCallbackByAidl;
    private static boolean sIsCallbackByBroadcast;
    private static boolean sIsDefaultCarMode;
    private static boolean sIsEnableA2dp;
    private static boolean sIsEnableAvrcp;
    private static boolean sIsEnableDun;
    private static boolean sIsEnableFtp;
    private static boolean sIsEnableGattServer;
    private static boolean sIsEnableHfp;
    private static boolean sIsEnableHid;
    private static boolean sIsEnableHsp;
    private static boolean sIsEnableMap;
    private static boolean sIsEnableOpp;
    private static boolean sIsEnablePbap;
    private static boolean sIsEnableSpp;
    private static boolean sIsHfpMicDefaultMute;
    private static boolean sIsLogLevelDebug;
    private static boolean sIsLogLevelInfo;
    private static boolean sIsLogLevelVerbose;
    private static boolean sIsLogLevelWarning;
    private static boolean sIsNeedDumpPbapContactInLog;
    private static boolean sIsPcmScoEnable;
    private static boolean sIsPtsTest;
    private static boolean sIsRedoBasicConnect;
    private static boolean sIsUnconnectableAfterDeviceConnected;
    private static int sProtectTimeAvrcp10Command;
    private static int sProtectTimeBtOnOff;
    private static int sProtectTimeHfpTerminateCommand;
    private static String sSppListenUuid;

    static {
        PRIVATE_PROFILE_ENABLE_HSP = !isAfterAndroid6();
        PRIVATE_PROFILE_ENABLE_FTP = !isAfterAndroid6();
        PRIVATE_PROFILE_ENABLE_HID = !isAfterAndroid6();
        PRIVATE_PROFILE_ENABLE_DUN = !isAfterAndroid6();
        PRIVATE_PROFILE_ENABLE_GATT_SERVER = !isAfterAndroid6();
        sIsDefaultCarMode = true;
        sIsCallbackByBroadcast = true;
        sIsCallbackByAidl = true;
        sIsLogLevelVerbose = true;
        sIsLogLevelDebug = true;
        sIsLogLevelWarning = true;
        sIsLogLevelInfo = true;
        sIsAllowRoleSwitch = false;
        sBeforeConnectDelayTime = 0;
        sAfterDisconnectDelayTime = 0;
        sEachProfileDelayTime = 0;
        sAutoConnectHighFrequencyInterval = NfDef.BT_STATE_OFF;
        sAutoConnectHighFrequencyDelayTime = 5;
        sAutoConnectLowFrequencyDelayTime = 5;
        sProtectTimeBtOnOff = Constants.MAX_RECORDS_IN_DATABASE;
        sProtectTimeAvrcp10Command = NfDef.ERROR_DEVICE_PATH_NULL;
        sProtectTimeHfpTerminateCommand = 1500;
        sIsUnconnectableAfterDeviceConnected = false;
        sIsPcmScoEnable = false;
        sIsHfpMicDefaultMute = false;
        sSppListenUuid = "00001101-0000-1000-8000-00805F9B34FB";
        sIsRedoBasicConnect = false;
        sIsNeedDumpPbapContactInLog = false;
        sIsAllowMultiConnection = false;
        sIsPtsTest = false;
        sIsAllowScoTransferBeforeCallActive = false;
        readConfig();
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void readConfig() {
        /*
            java.lang.String r5 = com.nforetek.bt.res.bt.NfConfig.TAG
            java.lang.String r6 = "readConfig()"
            com.nforetek.util.normal.NfLog.i(r5, r6)
            r0 = 0
            java.util.Properties r1 = new java.util.Properties
            r1.<init>()
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.io.FileNotFoundException -> L3b java.io.IOException -> L4f java.lang.Throwable -> L63
            java.lang.String r5 = "/system/etc/bluetooth/nForeBluetooth.properties"
            r4.<init>(r5)     // Catch: java.io.FileNotFoundException -> L3b java.io.IOException -> L4f java.lang.Throwable -> L63
            r1.load(r4)     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7c java.io.FileNotFoundException -> L7f
            if (r4 == 0) goto L73
            r4.close()     // Catch: java.lang.Exception -> L6f
            r3 = r4
        L1e:
            if (r0 == 0) goto L75
            readFromDefault()
        L23:
            printConfigSetting()
            boolean r5 = com.nforetek.bt.res.bt.NfConfig.sIsLogLevelVerbose
            com.nforetek.util.normal.NfLog.setVerbose(r5)
            boolean r5 = com.nforetek.bt.res.bt.NfConfig.sIsLogLevelDebug
            com.nforetek.util.normal.NfLog.setDebug(r5)
            boolean r5 = com.nforetek.bt.res.bt.NfConfig.sIsLogLevelWarning
            com.nforetek.util.normal.NfLog.setWarn(r5)
            boolean r5 = com.nforetek.bt.res.bt.NfConfig.sIsLogLevelInfo
            com.nforetek.util.normal.NfLog.setInfo(r5)
            return
        L3b:
            r2 = move-exception
        L3c:
            java.lang.String r5 = com.nforetek.bt.res.bt.NfConfig.TAG     // Catch: java.lang.Throwable -> L63
            java.lang.String r6 = "In readConfig() : Exception FileNotFoundException"
            com.nforetek.util.normal.NfLog.e(r5, r6)     // Catch: java.lang.Throwable -> L63
            r0 = 1
            if (r3 == 0) goto L1e
            r3.close()     // Catch: java.lang.Exception -> L4a
            goto L1e
        L4a:
            r2 = move-exception
            r2.printStackTrace()
            goto L1e
        L4f:
            r2 = move-exception
        L50:
            java.lang.String r5 = com.nforetek.bt.res.bt.NfConfig.TAG     // Catch: java.lang.Throwable -> L63
            java.lang.String r6 = "readConfig() : Exception IOException"
            com.nforetek.util.normal.NfLog.e(r5, r6)     // Catch: java.lang.Throwable -> L63
            r0 = 1
            if (r3 == 0) goto L1e
            r3.close()     // Catch: java.lang.Exception -> L5e
            goto L1e
        L5e:
            r2 = move-exception
            r2.printStackTrace()
            goto L1e
        L63:
            r5 = move-exception
        L64:
            if (r3 == 0) goto L69
            r3.close()     // Catch: java.lang.Exception -> L6a
        L69:
            throw r5
        L6a:
            r2 = move-exception
            r2.printStackTrace()
            goto L69
        L6f:
            r2 = move-exception
            r2.printStackTrace()
        L73:
            r3 = r4
            goto L1e
        L75:
            readFromConfigFile(r1)
            goto L23
        L79:
            r5 = move-exception
            r3 = r4
            goto L64
        L7c:
            r2 = move-exception
            r3 = r4
            goto L50
        L7f:
            r2 = move-exception
            r3 = r4
            goto L3c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.res.bt.NfConfig.readConfig():void");
    }

    private static void readFromDefault() {
        NfLog.i(TAG, "readFromDefault()");
        sIsEnableHsp = PRIVATE_PROFILE_ENABLE_HSP;
        sIsEnableHfp = true;
        sIsEnableA2dp = true;
        sIsEnableAvrcp = true;
        sIsEnableSpp = true;
        sIsEnablePbap = true;
        sIsEnableMap = true;
        sIsEnableHid = PRIVATE_PROFILE_ENABLE_HID;
        sIsEnableFtp = PRIVATE_PROFILE_ENABLE_FTP;
        sIsEnableOpp = false;
        sIsEnableGattServer = PRIVATE_PROFILE_ENABLE_GATT_SERVER;
    }

    private static boolean getBooleanProperty(Properties configFile, String propertyName, boolean defaultValue) {
        return Boolean.parseBoolean(configFile.getProperty(propertyName, new String(new StringBuilder().append(defaultValue).toString())));
    }

    private static int getIntegerProperty(Properties configFile, String propertyName, int defaultValue) {
        return Integer.parseInt(configFile.getProperty(propertyName, new String(new StringBuilder().append(defaultValue).toString())));
    }

    private static void readFromConfigFile(Properties configFile) {
        NfLog.i(TAG, "readFromConfigFile()");
        sIsEnableHsp = getBooleanProperty(configFile, KEY_HSP_PROFILE_ENABLE, false);
        sIsEnableHfp = getBooleanProperty(configFile, KEY_HFP_PROFILE_ENABLE, false);
        sIsEnableA2dp = getBooleanProperty(configFile, KEY_A2DP_PROFILE_ENABLE, false);
        sIsEnableAvrcp = getBooleanProperty(configFile, KEY_AVRCP_PROFILE_ENABLE, false);
        sIsEnablePbap = getBooleanProperty(configFile, KEY_PBAP_PROFILE_ENABLE, false);
        sIsEnableMap = getBooleanProperty(configFile, KEY_MAP_PROFILE_ENABLE, false);
        sIsEnableSpp = getBooleanProperty(configFile, KEY_SPP_PROFILE_ENABLE, false);
        sIsEnableFtp = getBooleanProperty(configFile, KEY_FTP_PROFILE_ENABLE, false);
        sIsEnableHid = getBooleanProperty(configFile, KEY_HID_PROFILE_ENABLE, false);
        sIsEnableDun = getBooleanProperty(configFile, KEY_DUN_PROFILE_ENABLE, false);
        sIsEnableOpp = getBooleanProperty(configFile, KEY_OPP_PROFILE_ENABLE, false);
        sIsEnableGattServer = getBooleanProperty(configFile, KEY_GATTS_PROFILE_ENABLE, false);
        sIsDefaultCarMode = getBooleanProperty(configFile, KEY_DEFAULT_CAR_MODE, true);
        sIsCallbackByBroadcast = getBooleanProperty(configFile, CALLBACK_BY_BROADCAST, true);
        sIsCallbackByAidl = getBooleanProperty(configFile, CALLBACK_BY_AIDL, true);
        sIsLogLevelVerbose = getBooleanProperty(configFile, KEY_LOG_LEVEL_VERBOSE, false);
        sIsLogLevelDebug = getBooleanProperty(configFile, KEY_LOG_LEVEL_DEBUG, false);
        sIsLogLevelWarning = getBooleanProperty(configFile, KEY_LOG_LEVEL_WARNING, false);
        sIsLogLevelInfo = getBooleanProperty(configFile, KEY_LOG_LEVEL_INFO, false);
        sIsAllowRoleSwitch = getBooleanProperty(configFile, KEY_ALLOW_ROLE_SWITCH, false);
        sBeforeConnectDelayTime = getIntegerProperty(configFile, KEY_BEFORE_CONNECT_ALL_DELAY_TIME, 0);
        sAfterDisconnectDelayTime = getIntegerProperty(configFile, KEY_AFTER_DISCONNECT_DELAY_TIME, 0);
        sEachProfileDelayTime = getIntegerProperty(configFile, KEY_EACH_PROFILE_DELAY_TIME, Constants.MAX_RECORDS_IN_DATABASE);
        sAutoConnectHighFrequencyInterval = getIntegerProperty(configFile, KEY_AUTO_CONNECT_HIGH_FREQUENCY_INTERVAL, NfDef.BT_STATE_OFF);
        if (sAutoConnectHighFrequencyInterval < 60) {
            sAutoConnectHighFrequencyInterval = 60;
        }
        sAutoConnectHighFrequencyDelayTime = getIntegerProperty(configFile, KEY_AUTO_CONNECT_HIGH_FREQUENCY_DELAY_TIME, 5);
        if (sAutoConnectHighFrequencyDelayTime < 5) {
            sAutoConnectHighFrequencyDelayTime = 5;
        }
        sAutoConnectLowFrequencyDelayTime = getIntegerProperty(configFile, KEY_AUTO_CONNECT_LOW_FREQUENCY_DELAY_TIME, 5);
        if (sAutoConnectLowFrequencyDelayTime < 5) {
            sAutoConnectLowFrequencyDelayTime = 5;
        }
        sProtectTimeBtOnOff = getIntegerProperty(configFile, KEY_BT_ONOFF_COMMAND_PROTECT_TIME, sProtectTimeBtOnOff);
        if (sProtectTimeBtOnOff < 0) {
            sProtectTimeBtOnOff = 0;
        }
        sProtectTimeAvrcp10Command = getIntegerProperty(configFile, KEY_AVRCP_10COMMAND_PROTECT_TIME, sProtectTimeAvrcp10Command);
        if (sProtectTimeAvrcp10Command < 0) {
            sProtectTimeAvrcp10Command = 0;
        }
        sProtectTimeHfpTerminateCommand = getIntegerProperty(configFile, KEY_HFP_TERMINATE_COMMAND_PROTECT_TIME, 0);
        if (sProtectTimeHfpTerminateCommand < 0) {
            sProtectTimeHfpTerminateCommand = 0;
        }
        sIsUnconnectableAfterDeviceConnected = getBooleanProperty(configFile, KEY_SET_UNCONNECTABLE_AFTER_DEVICE_CONNECTED, false);
        sIsPcmScoEnable = getBooleanProperty(configFile, KEY_PCM_SCO_ENABLE, false);
        sIsHfpMicDefaultMute = getBooleanProperty(configFile, KEY_HFP_MIC_DEFAULT_MUTE, false);
        sSppListenUuid = configFile.getProperty(KEY_SPP_LISTEN_UUID, sSppListenUuid);
        sIsRedoBasicConnect = getBooleanProperty(configFile, KEY_REDO_BASIC_CONNECT, false);
        sIsNeedDumpPbapContactInLog = getBooleanProperty(configFile, KEY_DUMP_PBAP_CONTACT_IN_LOG, false);
        sIsAllowMultiConnection = Boolean.parseBoolean(configFile.getProperty(KEY_ALLOW_MULTI_CONNECTION, "false"));
        sIsPtsTest = Boolean.parseBoolean(configFile.getProperty(KEY_PTS_TEST, "false"));
        sIsAllowScoTransferBeforeCallActive = getBooleanProperty(configFile, KEY_ALLOW_SCO_TRANSFER_BEFORE_CALL_ACTIVE, false);
        sIsEnableHfp = sIsEnableHfp;
        sIsEnableHsp = PRIVATE_PROFILE_ENABLE_HSP ? sIsEnableHsp : false;
        sIsEnableA2dp = sIsEnableA2dp;
        sIsEnableAvrcp = sIsEnableAvrcp;
        sIsEnablePbap = sIsEnablePbap;
        sIsEnableMap = sIsEnableMap;
        sIsEnableSpp = sIsEnableSpp;
        sIsEnableFtp = PRIVATE_PROFILE_ENABLE_FTP ? sIsEnableFtp : false;
        sIsEnableHid = PRIVATE_PROFILE_ENABLE_HID ? sIsEnableHid : false;
        sIsEnableDun = PRIVATE_PROFILE_ENABLE_DUN ? sIsEnableDun : false;
        sIsEnableOpp = false;
        sIsEnableGattServer = PRIVATE_PROFILE_ENABLE_GATT_SERVER ? sIsEnableGattServer : false;
    }

    private static void printConfigSetting() {
        NfLog.i(TAG, "readConfig(): isEnableHsp = " + sIsEnableHsp);
        NfLog.i(TAG, "readConfig(): isEnableHfp = " + sIsEnableHfp);
        NfLog.i(TAG, "readConfig(): isEnableA2dp = " + sIsEnableA2dp);
        NfLog.i(TAG, "readConfig(): isEnableAvrcp = " + sIsEnableAvrcp);
        NfLog.i(TAG, "readConfig(): isEnablePbap = " + sIsEnablePbap);
        NfLog.i(TAG, "readConfig(): isEnableMap = " + sIsEnableMap);
        NfLog.i(TAG, "readConfig(): isEnableSpp = " + sIsEnableSpp);
        NfLog.i(TAG, "readConfig(): isEnableFtp = " + sIsEnableFtp);
        NfLog.i(TAG, "readConfig(): isEnableHid = " + sIsEnableHid);
        NfLog.i(TAG, "readConfig(): isEnableDun = " + sIsEnableDun);
        NfLog.i(TAG, "readConfig(): isEnableOpp = " + sIsEnableOpp);
        NfLog.i(TAG, "readConfig(): sIsEnableGattServer = " + sIsEnableGattServer);
        NfLog.i(TAG, "readConfig(): sIsDefaultCarMode = " + sIsDefaultCarMode);
        NfLog.i(TAG, "readConfig(): sIsLogLevelVerbose = " + sIsLogLevelVerbose);
        NfLog.i(TAG, "readConfig(): sIsLogLevelDebug = " + sIsLogLevelDebug);
        NfLog.i(TAG, "readConfig(): sIsLogLevelWarning = " + sIsLogLevelWarning);
        NfLog.i(TAG, "readConfig(): sIsLogLevelInfo = " + sIsLogLevelInfo);
        NfLog.i(TAG, "readConfig(): sIsAllowRoleSwitch = " + sIsAllowRoleSwitch);
        NfLog.i(TAG, "readConfig(): sIsCallbackByBroadcast = " + sIsCallbackByBroadcast);
        NfLog.i(TAG, "readConfig(): sIsCallbackByAidl = " + sIsCallbackByAidl);
        NfLog.i(TAG, "readConfig(): sBeforeConnectDelayTime = " + sBeforeConnectDelayTime);
        NfLog.i(TAG, "readConfig(): sAfterDisconnectDelayTime = " + sAfterDisconnectDelayTime);
        NfLog.i(TAG, "readConfig(): sEachProfileDelayTime = " + sEachProfileDelayTime);
        NfLog.i(TAG, "readConfig(): sIsPcmScoEnable = " + sIsPcmScoEnable);
        NfLog.i(TAG, "readConfig(): sIsHfpMicDefaultMute = " + sIsHfpMicDefaultMute);
        NfLog.i(TAG, "readConfig(): sAutoConnectHighFrequencyInterval = " + sAutoConnectHighFrequencyInterval);
        NfLog.i(TAG, "readConfig(): sAutoConnectHighFrequencyDelayTime = " + sAutoConnectHighFrequencyDelayTime);
        NfLog.i(TAG, "readConfig(): sAutoConnectLowFrequencyDelayTime = " + sAutoConnectLowFrequencyDelayTime);
        NfLog.i(TAG, "readConfig(): sProtectTimeBtOnOff = " + sProtectTimeBtOnOff);
        NfLog.i(TAG, "readConfig(): sProtectTimeAvrcp10Command = " + sProtectTimeAvrcp10Command);
        NfLog.i(TAG, "readConfig(): sProtectTimeHfpTerminateCommand = " + sProtectTimeHfpTerminateCommand);
        NfLog.i(TAG, "readConfig(): sIsUnconnectableAfterDeviceConnected = " + sIsUnconnectableAfterDeviceConnected);
        NfLog.i(TAG, "readConfig(): isPbapImplementByJava = " + isPbapImplementByJava());
        NfLog.i(TAG, "readConfig(): isMapImplementByJava = " + isMapImplementByJava());
        NfLog.i(TAG, "readConfig(): sSppListenUuid = " + sSppListenUuid);
        NfLog.i(TAG, "readConfig(): sIsRedoBasicConnect = " + sIsRedoBasicConnect);
        NfLog.i(TAG, "readConfig(): sIsNeedDumpPbapContactInLog = " + sIsNeedDumpPbapContactInLog);
        NfLog.i(TAG, "readConfig(): sIsAllowMultiConnection = " + sIsAllowMultiConnection);
        NfLog.i(TAG, "readConfig(): sIsPtsTest = " + sIsPtsTest);
        NfLog.i(TAG, "readConfig(): sIsAllowScoTransferBeforeCallActive = " + sIsAllowScoTransferBeforeCallActive);
        if (PRIVATE_PROFILE_ENABLE_HSP) {
            NfLog.i(TAG, "readConfig(): Check Private Enable HSP");
        }
        NfLog.i(TAG, "readConfig(): Check Private Enable HFP");
        NfLog.i(TAG, "readConfig(): Check Private Enable A2DP");
        NfLog.i(TAG, "readConfig(): Check Private Enable AVRCP");
        NfLog.i(TAG, "readConfig(): Check Private Enable PBAP");
        NfLog.i(TAG, "readConfig(): Check Private Enable MAP");
        NfLog.i(TAG, "readConfig(): Check Private Enable SPP");
        if (PRIVATE_PROFILE_ENABLE_FTP) {
            NfLog.i(TAG, "readConfig(): Check Private Enable FTP");
        }
        if (PRIVATE_PROFILE_ENABLE_HID) {
            NfLog.i(TAG, "readConfig(): Check Private Enable HID");
        }
        if (PRIVATE_PROFILE_ENABLE_DUN) {
            NfLog.i(TAG, "readConfig(): Check Private Enable DUN");
        }
        if (PRIVATE_PROFILE_ENABLE_GATT_SERVER) {
            NfLog.i(TAG, "readConfig(): Check Private Enable Gatt Server");
        }
    }

    public static boolean isEnableHfp() {
        return sIsEnableHfp;
    }

    public static boolean isEnableHsp() {
        return sIsEnableHsp;
    }

    public static boolean isEnableA2dp() {
        return sIsEnableA2dp;
    }

    public static boolean isEnableAvrcp() {
        return sIsEnableAvrcp;
    }

    public static boolean isEnablePbap() {
        return sIsEnablePbap;
    }

    public static boolean isEnableMap() {
        return sIsEnableMap;
    }

    public static boolean isEnableSpp() {
        return sIsEnableSpp;
    }

    public static boolean isEnablePan() {
        return true;
    }

    public static boolean isEnableFfp() {
        return sIsEnableFtp;
    }

    public static boolean isEnableHid() {
        return sIsEnableHid;
    }

    public static boolean isEnableDun() {
        return sIsEnableDun;
    }

    public static boolean isEnableOpp() {
        return sIsEnableOpp;
    }

    public static boolean isEnableGattServer() {
        return sIsEnableGattServer;
    }

    public static boolean isDefaultCarMode() {
        return sIsDefaultCarMode;
    }

    public static boolean isLogLevelVerbose() {
        return sIsLogLevelVerbose;
    }

    public static boolean isLogLevelDebug() {
        return sIsLogLevelDebug;
    }

    public static boolean isLogLevelWarning() {
        return sIsLogLevelWarning;
    }

    public static boolean isLogLevelInfo() {
        return sIsLogLevelInfo;
    }

    public static boolean isAllowRoleSwitch() {
        return sIsAllowRoleSwitch;
    }

    public static boolean isCallbackByBroadcast() {
        return sIsCallbackByBroadcast;
    }

    public static boolean isCallbackByAidl() {
        return sIsCallbackByAidl;
    }

    public static boolean isPtsTest() {
        return sIsPtsTest;
    }

    public static boolean isPbapImplementByJava() {
        return isAfterAndroid6();
    }

    public static boolean isMapImplementByJava() {
        return isAfterAndroid6();
    }

    public static boolean isUseMapAddSdp() {
        return false;
    }

    public static boolean isUsePbapAddSdp() {
        return false;
    }

    public static boolean isAfterAndroid6() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean isAfterAndroid8() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public static boolean isAfterAndroid9() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static boolean isUseForceDisconnectMapPbap() {
        return isAfterAndroid6();
    }

    public static int getBasicConnectBeforeConnectDelayTime() {
        return sBeforeConnectDelayTime;
    }

    public static int getBasicConnectAfterDisconnectDelayTime() {
        return sAfterDisconnectDelayTime;
    }

    public static int getBasicConnectEachProfileDelayTime() {
        return sEachProfileDelayTime;
    }

    public static boolean isPcmScoEnable() {
        return sIsPcmScoEnable;
    }

    public static boolean isHfpMicDefaultMute() {
        return sIsHfpMicDefaultMute;
    }

    public static int getAutoConnectHighFrequencyInterval() {
        return sAutoConnectHighFrequencyInterval * Constants.MAX_RECORDS_IN_DATABASE;
    }

    public static int getAutoConnectHighFrequencyDelayTime() {
        return sAutoConnectHighFrequencyDelayTime * Constants.MAX_RECORDS_IN_DATABASE;
    }

    public static int getAutoConnectLowFrequencyDelayTime() {
        return sAutoConnectLowFrequencyDelayTime * Constants.MAX_RECORDS_IN_DATABASE;
    }

    public static int getProtectTimeBtOnOff() {
        return sProtectTimeBtOnOff;
    }

    public static int getProtectTimeAvrcp10Command() {
        return sProtectTimeAvrcp10Command;
    }

    public static int getProtectTimeHfpTerminateCommand() {
        return sProtectTimeHfpTerminateCommand;
    }

    public static boolean isUnconnectableAfterDeviceConnected() {
        return sIsUnconnectableAfterDeviceConnected;
    }

    public static String getSppListenUuid() {
        return sSppListenUuid;
    }

    public static boolean isAllBasicProfileEnabled() {
        return sIsEnableHfp && sIsEnableA2dp && sIsEnableAvrcp;
    }

    public static boolean isRedoBasicConnectEnable() {
        return sIsRedoBasicConnect;
    }

    public static boolean isNeedDumpPbapContactInLog() {
        return sIsNeedDumpPbapContactInLog;
    }

    public static boolean isAllowMultiConnection() {
        return sIsAllowMultiConnection;
    }

    public static boolean isAllowScoTransferBeforeCallActive() {
        return sIsAllowScoTransferBeforeCallActive;
    }
}
