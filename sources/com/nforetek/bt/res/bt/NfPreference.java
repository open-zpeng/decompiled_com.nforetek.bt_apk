package com.nforetek.bt.res.bt;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class NfPreference {
    private static final String SHARED_PREFERENCES_KEY_AUTO_CONNECT_ADDRESS = "auto_connect_address";
    private static final String SHARED_PREFERENCES_KEY_AUTO_CONNECT_CONDITION = "auto_connect_condition";
    private static final String SHARED_PREFERENCES_KEY_AUTO_CONNECT_PERIOD = "auto_connect_period";
    private static final String SHARED_PREFERENCES_KEY_BLUEDROID_VERSION = "bluedroid_version";
    private static final String SHARED_PREFERENCES_KEY_BLUETOOTH_APK_VERSION = "bluetooth_apk_version";
    private static final String SHARED_PREFERENCES_KEY_DISCOVERABLE_FOREVER = "discoverable_forever";
    private static final String SHARED_PREFERENCES_KEY_OPP_FILE_PATH = "opp_file_path";
    private static final String SHARED_PREFERENCES_NAME = "nFore_Service";
    private static String TAG = "NfPreference";
    private static Context sContext = null;

    public static void setContext(Context c) {
        sContext = c;
        printPreference();
    }

    public static String getOppPath() {
        return getStringPreference(SHARED_PREFERENCES_KEY_OPP_FILE_PATH, "");
    }

    public static int getAutoConnectCondition() {
        return getIntegerPreference(SHARED_PREFERENCES_KEY_AUTO_CONNECT_CONDITION, 0);
    }

    public static int getAutoConnectPeriod() {
        return getIntegerPreference(SHARED_PREFERENCES_KEY_AUTO_CONNECT_PERIOD, 0) * Constants.MAX_RECORDS_IN_DATABASE;
    }

    public static String getAutoConnectAddress() {
        return getStringPreference(SHARED_PREFERENCES_KEY_AUTO_CONNECT_ADDRESS, NfDef.DEFAULT_ADDRESS);
    }

    public static boolean isDiscoverableForever() {
        return getBooleanPreference(SHARED_PREFERENCES_KEY_DISCOVERABLE_FOREVER);
    }

    public static boolean setOppPath(String path) {
        return setStringPreference(SHARED_PREFERENCES_KEY_OPP_FILE_PATH, path);
    }

    public static boolean setAutoConnectCondition(int condition) {
        return setIntegerPreference(SHARED_PREFERENCES_KEY_AUTO_CONNECT_CONDITION, condition);
    }

    public static boolean setAutoConnectPeriod(int period) {
        return setIntegerPreference(SHARED_PREFERENCES_KEY_AUTO_CONNECT_PERIOD, period);
    }

    public static boolean setAutoConnectAddress(String address) {
        return setStringPreference(SHARED_PREFERENCES_KEY_AUTO_CONNECT_ADDRESS, address);
    }

    public static boolean setDiscoverableForever(boolean enable) {
        return setBooleanPreference(SHARED_PREFERENCES_KEY_DISCOVERABLE_FOREVER, enable);
    }

    public static boolean setBluedroidVersion(String version) {
        return setStringPreference(SHARED_PREFERENCES_KEY_BLUEDROID_VERSION, version);
    }

    public static boolean setBluetoothApkVersion(String version) {
        return setStringPreference(SHARED_PREFERENCES_KEY_BLUETOOTH_APK_VERSION, version);
    }

    public static String getBluedroidVersion() {
        return getStringPreference(SHARED_PREFERENCES_KEY_BLUEDROID_VERSION, "Unknown");
    }

    public static String getBluetoothApkVersion() {
        return getStringPreference(SHARED_PREFERENCES_KEY_BLUETOOTH_APK_VERSION, "Unknown");
    }

    private static synchronized String getStringPreference(String name, String fail) {
        synchronized (NfPreference.class) {
            if (sContext == null) {
                NfLog.e(TAG, "In getStringPreference sContext is null!!");
                fail = null;
            } else {
                NfLog.d(TAG, "getStringPreference() name: " + name + " defaultFailValue: " + fail);
                try {
                    String result = getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(name, "94879487");
                    if (result.equals("94879487")) {
                        NfLog.e(TAG, "In getStringPreference but fail.");
                    } else {
                        NfLog.d(TAG, "getStringPreference() name: " + name + " result: " + result);
                        fail = result;
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    NfLog.e(TAG, "In getStringPreference but fail.");
                }
            }
        }
        return fail;
    }

    private static synchronized int getIntegerPreference(String name, int fail) {
        synchronized (NfPreference.class) {
            if (sContext == null) {
                NfLog.e(TAG, "In getIntegerPreference sContext is null!!");
                fail = -1;
            } else {
                NfLog.d(TAG, "getIntegerPreference() name: " + name + " defaultFailValue: " + fail);
                try {
                    int result = getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getInt(name, 94879487);
                    if (result == 94879487) {
                        NfLog.e(TAG, "In getIntegerPreference but fail.");
                    } else {
                        NfLog.d(TAG, "getIntegerPreference() name: " + name + " result: " + result);
                        fail = result;
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    NfLog.e(TAG, "In getIntegerPreference but fail.");
                }
            }
        }
        return fail;
    }

    private static synchronized boolean getBooleanPreference(String name) {
        boolean result;
        synchronized (NfPreference.class) {
            if (sContext == null) {
                NfLog.e(TAG, "In getBooleanPreference sContext is null!!");
                result = false;
            } else {
                NfLog.d(TAG, "getBooleanPreference() name: " + name);
                try {
                    result = getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getBoolean(name, false);
                    NfLog.d(TAG, "getBooleanPreference() name: " + name + " result: " + result);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    NfLog.e(TAG, "In getIntegerPreference but fail.");
                    result = false;
                }
            }
        }
        return result;
    }

    private static synchronized boolean setStringPreference(String name, String userdata) {
        boolean success = false;
        synchronized (NfPreference.class) {
            if (sContext == null) {
                NfLog.e(TAG, "In setStringPreference sContext is null!!");
            } else {
                NfLog.d(TAG, "setStringPreference() name: " + name + " userdata: " + userdata);
                success = false;
                try {
                    SharedPreferences.Editor editor = getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
                    editor.putString(name, userdata);
                    success = editor.commit();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                NfLog.d(TAG, "setStringPreference() " + (success ? "success" : "fail"));
            }
        }
        return success;
    }

    private static synchronized boolean setIntegerPreference(String name, int userdata) {
        boolean success = false;
        synchronized (NfPreference.class) {
            if (sContext == null) {
                NfLog.e(TAG, "In setIntegerPreference sContext is null!!");
            } else {
                NfLog.d(TAG, "setIntegerPreference() name: " + name + " userdata: " + userdata);
                success = false;
                try {
                    SharedPreferences.Editor editor = getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
                    editor.putInt(name, userdata);
                    success = editor.commit();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                NfLog.d(TAG, "setIntegerPreference() " + (success ? "success" : "fail"));
            }
        }
        return success;
    }

    private static synchronized boolean setBooleanPreference(String name, boolean userdata) {
        boolean success = false;
        synchronized (NfPreference.class) {
            if (sContext == null) {
                NfLog.e(TAG, "In setBooleanPreference sContext is null!!");
            } else {
                NfLog.d(TAG, "setBooleanPreference() name: " + name + " userdata: " + userdata);
                success = false;
                try {
                    SharedPreferences.Editor editor = getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
                    editor.putBoolean(name, userdata);
                    success = editor.commit();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                NfLog.d(TAG, "setBooleanPreference() " + (success ? "success" : "fail"));
            }
        }
        return success;
    }

    public static void printPreference() {
        NfLog.i(TAG, "printPreference(): getBluedroidVersion: " + getBluedroidVersion());
        NfLog.i(TAG, "printPreference(): getBluetoothApkVersion: " + getBluetoothApkVersion());
        NfLog.i(TAG, "printPreference(): getAutoConnectCondition: " + getAutoConnectCondition());
        NfLog.i(TAG, "printPreference(): getAutoConnectPeriod: " + getAutoConnectPeriod());
        NfLog.i(TAG, "printPreference(): getAutoConnectAddress: " + getAutoConnectAddress());
        NfLog.i(TAG, "printPreference(): isDiscoverableForever: " + isDiscoverableForever());
    }

    public static boolean isAllowAutoConnectWhenBtOn() {
        return getAutoConnectCondition() > 0 && (getAutoConnectCondition() & 1) > 0;
    }

    public static boolean isAllowAutoConnectWhenPaired() {
        return getAutoConnectCondition() > 0 && (getAutoConnectCondition() & 2) > 0;
    }

    public static boolean isAllowAutoConnectWhenOor() {
        return getAutoConnectCondition() > 0 && (getAutoConnectCondition() & 4) > 0;
    }

    private static Context getContext() {
        return Build.VERSION.SDK_INT >= 24 ? sContext.createDeviceProtectedStorageContext() : sContext;
    }
}
