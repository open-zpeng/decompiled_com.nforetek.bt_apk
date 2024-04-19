package com.nforetek.util.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.ParcelUuid;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.Constants;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.bt.res.bt.NfPreference;
import com.nforetek.util.normal.NfLog;
import java.lang.reflect.Method;
import java.util.Set;
/* loaded from: classes.dex */
public final class NfPrimitive {
    private static final int DEFAULT_DISCOVERABLE_TIMEOUT = 120;
    private static final int HANDLER_MESSAGE_DISABLE_DISCOVRRABLE = 1;
    private static final int SCAN_EXPIRATION_MS = 1500;
    private static final String TAG = "NfPrimitive";
    private static Handler sHandler;
    private static HandlerThread sHandlerThread;
    private static BluetoothAdapter sAdapter = BluetoothAdapter.getDefaultAdapter();
    private static long sLastScanTime = 0;
    private static long sLastBtOnTime = 0;
    private static long sLastBtOffTime = 0;
    private static boolean sIsAnyDevicePairing = false;
    private static Context sContext = null;
    static int sLastDiscoverablePeriod = 0;
    static int sLastScanMode = 0;
    static long sLastSetBtDiscoverableTime = 0;
    public static String sPairingDevice = NfDef.DEFAULT_ADDRESS;
    private static Set<BluetoothDevice> sPairedDevices = null;

    public static void updateLastSystemTime(long time) {
        sLastScanTime = 0L;
        sLastBtOnTime = 0L;
        sLastBtOffTime = 0L;
    }

    public static void setContext(Context c) {
        sContext = c;
        if (sHandlerThread == null) {
            sHandlerThread = new HandlerThread(TAG);
            sHandlerThread.start();
        }
        sHandler = initHandler();
    }

    private static Handler initHandler() {
        return sHandler == null ? new Handler(sHandlerThread.getLooper()) { // from class: com.nforetek.util.bt.NfPrimitive.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                NfLog.v(NfPrimitive.TAG, "handleMessage : " + msg.what);
                switch (msg.what) {
                    case 1:
                        NfPrimitive.setBtDiscoverable(false, -1);
                        return;
                    default:
                        return;
                }
            }
        } : sHandler;
    }

    public static void destroy() {
        if (sHandlerThread != null) {
            sHandlerThread.getLooper().quit();
            sHandlerThread = null;
        }
        if (sHandler != null) {
            sHandler.removeCallbacksAndMessages(null);
            sHandler = null;
        }
    }

    public static boolean isBtEnabled() {
        if (BluetoothAdapter.getDefaultAdapter() == null) {
            NfLog.e(TAG, "BluetoothAdapter is null.");
            return false;
        }
        boolean enable = BluetoothAdapter.getDefaultAdapter().isEnabled();
        return enable;
    }

    public static boolean setBtEnable(boolean enable) {
        boolean success = false;
        NfLog.v(TAG, "setBtEnable() " + enable + " Current BT State: " + getAdapter().getState());
        if (isAdapterValid()) {
            if ((enable ? sLastBtOnTime : sLastBtOffTime) + NfConfig.getProtectTimeBtOnOff() > System.currentTimeMillis()) {
                NfLog.e(TAG, "Reject : Too frequent, LastOnOff = " + (enable ? sLastBtOnTime : sLastBtOffTime) + " currentTimeMillis = " + System.currentTimeMillis());
            } else {
                success = false;
                if (getAdapter().getState() == 10 && enable) {
                    success = getAdapter().enable();
                } else if (getAdapter().getState() == 12 && !enable) {
                    success = getAdapter().disable();
                }
                if (success) {
                    if (enable) {
                        sLastBtOnTime = System.currentTimeMillis();
                    } else {
                        sLastBtOffTime = System.currentTimeMillis();
                    }
                    NfLog.d(TAG, "setBtEnable success! LastOnOff = " + (enable ? sLastBtOnTime : sLastBtOffTime));
                }
            }
        }
        return success;
    }

    public static long getBtOnAvailableTime() {
        return sLastBtOnTime + NfConfig.getProtectTimeBtOnOff();
    }

    public static long getBtOffAvailableTime() {
        return sLastBtOffTime + NfConfig.getProtectTimeBtOnOff();
    }

    public static boolean setBtConnectable(boolean able) {
        NfLog.v(TAG, "setBtConnectable() able: " + able);
        if (!isAdapterValid()) {
            return false;
        }
        int period = 0;
        int mode = 0;
        if (able && (getAdapter().getScanMode() == 21 || getAdapter().getScanMode() == 23)) {
            NfLog.e(TAG, "Already connectable, return here.");
            return false;
        } else if (!able && getAdapter().getScanMode() == 20) {
            NfLog.e(TAG, "Already unconnectable, return here.");
            return false;
        } else if (able) {
            if (sLastScanMode == 23) {
                NfLog.v(TAG, "Last time discoverable period is " + sLastDiscoverablePeriod);
                if (sLastDiscoverablePeriod > 0) {
                    long now = System.currentTimeMillis();
                    int pastedDiscoverableTime = ((int) (now - sLastSetBtDiscoverableTime)) / Constants.MAX_RECORDS_IN_DATABASE;
                    if (pastedDiscoverableTime < sLastDiscoverablePeriod) {
                        mode = 23;
                        period = sLastDiscoverablePeriod - pastedDiscoverableTime;
                    } else {
                        mode = 21;
                        period = -1;
                    }
                } else if (sLastDiscoverablePeriod < 0) {
                    mode = 21;
                    period = -1;
                } else {
                    mode = 23;
                    period = 0;
                }
            } else if (sLastScanMode == 21) {
                mode = 21;
                period = -1;
            } else {
                NfLog.e(TAG, "After setConnectable to true but last scan mode is not discoverable or connectable. scanMode: " + sLastScanMode);
            }
            setScanMode(mode, period);
            int retryCount = 5;
            while (true) {
                int retryCount2 = retryCount;
                retryCount = retryCount2 - 1;
                if (retryCount2 <= 0 || getAdapter().getScanMode() == mode) {
                    break;
                }
                NfLog.d(TAG, "In setBtConnectable() : The state of adapter is still mode: " + getAdapter().getScanMode());
                NfReflection.setScanMode(mode);
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    NfLog.e(TAG, "In setBtConnectable() : Exception in sleep.");
                }
            }
            boolean success = getAdapter().getScanMode() == mode;
            if (success && mode == 23) {
                sLastSetBtDiscoverableTime = System.currentTimeMillis();
                sLastDiscoverablePeriod = period;
                try {
                    Thread.sleep(500L);
                    return success;
                } catch (InterruptedException e2) {
                    NfLog.e(TAG, "In setBtConnectable() success: Exception in sleep.");
                    return success;
                }
            }
            return success;
        } else {
            int retryCount3 = 5;
            sLastScanMode = getAdapter().getScanMode();
            setScanMode(20, -1);
            while (true) {
                int retryCount4 = retryCount3;
                retryCount3 = retryCount4 - 1;
                if (retryCount4 <= 0 || getAdapter().getScanMode() == 20) {
                    break;
                }
                NfLog.d(TAG, "In setBtConnectable() : The state of adapter is still mode: " + getAdapter().getScanMode());
                setScanMode(20, -1);
            }
            return getAdapter().getScanMode() == 20;
        }
    }

    public static boolean setBtDiscoverable(boolean able, int period) {
        NfLog.v(TAG, "setBtDiscoverable() able: " + able + " period: " + period);
        if (isAdapterValid()) {
            if (able) {
                setScanMode(23, period);
                int retryCount = 5;
                while (true) {
                    int retryCount2 = retryCount;
                    retryCount = retryCount2 - 1;
                    if (retryCount2 <= 0 || getAdapter().getScanMode() != 21) {
                        break;
                    }
                    NfLog.d(TAG, "In setBluetoothDiscoverable() : The state of adapter is still SCAN_MODE_CONNECTABLE !!!");
                    NfReflection.setScanMode(23);
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        NfLog.e(TAG, "In setBluetoothDiscoverable() : Exception in sleep.");
                    }
                }
                boolean success = getAdapter().getScanMode() == 23;
                if (success) {
                    sLastSetBtDiscoverableTime = System.currentTimeMillis();
                    sLastDiscoverablePeriod = period;
                    if (sLastDiscoverablePeriod > 0) {
                        sHandler.sendMessageDelayed(sHandler.obtainMessage(1), sLastDiscoverablePeriod * Constants.MAX_RECORDS_IN_DATABASE);
                        return success;
                    } else if (sLastDiscoverablePeriod == 0) {
                        NfPreference.setDiscoverableForever(true);
                        return success;
                    } else {
                        return success;
                    }
                }
                return success;
            }
            int retryCount3 = 5;
            setScanMode(21, -1);
            while (true) {
                int retryCount4 = retryCount3;
                retryCount3 = retryCount4 - 1;
                if (retryCount4 <= 0 || getAdapter().getScanMode() != 23) {
                    break;
                }
                NfLog.d(TAG, "In setBluetoothDiscoverable() : The state of adapter is still SCAN_MODE_CONNECTABLE_DISCOVERABLE !!!");
                setScanMode(21, -1);
            }
            boolean success2 = getAdapter().getScanMode() == 21;
            if (success2) {
                sLastDiscoverablePeriod = -1;
                sHandler.removeMessages(1);
                if (sLastDiscoverablePeriod == 0) {
                    NfPreference.setDiscoverableForever(false);
                    return success2;
                }
                return success2;
            }
            return success2;
        }
        return false;
    }

    public static boolean startScan(boolean start) {
        NfLog.v(TAG, "startScan(): " + start);
        if (!isAdapterValid()) {
            return false;
        }
        if (start) {
            if (getAdapter().isDiscovering()) {
                NfLog.d(TAG, "Already in Discovering");
                return false;
            } else if (sLastScanTime + 1500 > System.currentTimeMillis()) {
                NfLog.d(TAG, "Reject : Too frequent, mLastScan = " + sLastScanTime + " currentTimeMillis = " + System.currentTimeMillis());
                return false;
            } else if (!getAdapter().startDiscovery()) {
                return false;
            } else {
                sLastScanTime = System.currentTimeMillis();
                return true;
            }
        } else if (!getAdapter().isDiscovering()) {
            return false;
        } else {
            getAdapter().cancelDiscovery();
            return true;
        }
    }

    public static boolean pair(String address) {
        NfLog.v(TAG, "pair(): " + address);
        if (!isAdapterValid()) {
            return false;
        }
        BluetoothDevice device = getAdapter().getRemoteDevice(address);
        if (device != null) {
            int state = device.getBondState();
            NfLog.d(TAG, "In pair() : Device " + device.getAddress() + " bond state is : " + state);
            if (10 == state) {
                if (getAdapter().isDiscovering()) {
                    getAdapter().cancelDiscovery();
                    int retryCount = 5;
                    while (true) {
                        int retryCount2 = retryCount;
                        retryCount = retryCount2 - 1;
                        if (retryCount2 <= 0 || !getAdapter().isDiscovering()) {
                            break;
                        }
                        NfLog.d(TAG, "In pair() : still discovering.");
                        try {
                            Thread.sleep(200L);
                        } catch (InterruptedException e) {
                            NfLog.e(TAG, "In pair() : Exception in sleep.");
                        }
                    }
                }
                if (!NfReflection.createBond(device)) {
                    NfLog.d(TAG, "In pair() : CreateBond failed !!!");
                    return false;
                }
                return true;
            }
            NfLog.d(TAG, "In pair() : Device is already paired or in pairing !!!");
            return false;
        }
        NfLog.e(TAG, "In pair() : Could not get remote device with address = " + address);
        return false;
    }

    public static boolean unPair(String address) {
        NfLog.v(TAG, "unPair() " + address);
        if (!isAdapterValid()) {
            return false;
        }
        BluetoothDevice device = getAdapter().getRemoteDevice(address);
        if (device != null) {
            int state = device.getBondState();
            NfLog.d(TAG, "Device bond state: " + state);
            if (12 == state) {
                if (!NfReflection.removeBond(device)) {
                    NfLog.d(TAG, "In unPair() : RemoveBond failed !!!");
                    return false;
                }
                return true;
            } else if (11 == state) {
                if (!NfReflection.cancelBondProcess(device)) {
                    NfLog.d(TAG, "In unPair() : CancelBond failed !!!");
                    return false;
                }
                return true;
            } else {
                NfLog.d(TAG, "In unPair() : Device is already unpaired !!!");
                return false;
            }
        }
        NfLog.e(TAG, "In unPair() : Could not get remote device with address = " + address);
        return false;
    }

    public static Set<BluetoothDevice> getPairedDevice() {
        if (isAdapterValid()) {
            return getCachedPairedDevices();
        }
        return null;
    }

    public static String getLocalAdapterName() {
        return !isAdapterValid() ? "" : getAdapter().getName();
    }

    public static String getLocalAdapterAddress() {
        return !isAdapterValid() ? NfDef.DEFAULT_ADDRESS : getAdapter().getAddress();
    }

    public static boolean setLocalAdapterName(String name) {
        if (isAdapterValid()) {
            return getAdapter().setName(name);
        }
        return false;
    }

    public static String getRemoteDeviceName(String address) {
        return !isAdapterValid() ? "" : getAdapter().getRemoteDevice(address).getName();
    }

    public static boolean isAdapterEnabled() {
        if (isAdapterValid()) {
            return getAdapter().isEnabled();
        }
        return false;
    }

    public static int getAdapterState() {
        if (!isAdapterValid()) {
            return 10;
        }
        int state = getAdapter().getState();
        switch (state) {
            case 10:
                if (System.currentTimeMillis() >= getBtOffAvailableTime()) {
                    return NfDef.BT_STATE_OFF;
                }
                return 303;
            case 11:
                return 301;
            case 12:
                return 302;
            case 13:
                return 303;
            default:
                return -1;
        }
    }

    public static boolean isDevicePaired(String address) {
        NfLog.v(TAG, "isDevicePaired() " + address);
        if (!isAdapterValid()) {
            return false;
        }
        boolean success = false;
        BluetoothDevice device = getAdapter().getRemoteDevice(address);
        Set<BluetoothDevice> pairedDevices = getCachedPairedDevices();
        if (pairedDevices != null) {
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice d : pairedDevices) {
                    if (device.equals(d)) {
                        NfLog.d(TAG, "In checkPairedDevices() : Device is already paired !!!");
                        success = true;
                    }
                }
                return success;
            }
            NfLog.d(TAG, "In checkPairedDevices() : mPairedDevices's size is 0, maybe, we do not have any paired device");
            return false;
        }
        NfLog.d(TAG, "In checkPairedDevices() : mPairedDevices is null, maybe, we do not have any paired device");
        return false;
    }

    public static boolean isDevicePairing(String address) {
        NfLog.v(TAG, "isDevicePairing() " + address);
        if (isAdapterValid()) {
            BluetoothDevice device = getAdapter().getRemoteDevice(address);
            isPairing = device.getBondState() == 11;
            NfLog.v(TAG, "isPairing: " + isPairing);
        }
        return isPairing;
    }

    public static void setAnyDevicePairing(boolean isPairing, String address) {
        NfLog.v(TAG, "setAnyDevicePairing() device: " + address + " isPairing: " + isPairing);
        sIsAnyDevicePairing = isPairing;
        if (!isPairing) {
            address = NfDef.DEFAULT_ADDRESS;
        }
        sPairingDevice = address;
    }

    public static boolean isAnyDevicePairing() {
        NfLog.v(TAG, "isAnyDevicePairing(): " + sIsAnyDevicePairing);
        return sIsAnyDevicePairing;
    }

    public static String getPairingDevice() {
        NfLog.v(TAG, "getPairingDevice(): " + sPairingDevice);
        return sPairingDevice;
    }

    public static boolean isAppleDevice(String address) {
        int supportProfile = getProfiles(address);
        if ((supportProfile & 8192) > 0) {
            NfLog.v(TAG, String.valueOf(getRemoteDeviceName(address)) + "(" + address + ") is iPhone.");
            return true;
        }
        return false;
    }

    public static int getProfiles(String address) {
        NfLog.d(TAG, "getProfiles(address) " + address);
        return getProfiles(null, address);
    }

    public static int getProfiles(NfJni jni, String address) {
        NfLog.d(TAG, "getProfiles(address,uuids) " + address);
        BluetoothDevice device = getAdapter().getRemoteDevice(address);
        ParcelUuid[] uuids = device.getUuids();
        return getProfiles(jni, address, uuids);
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x017f, code lost:
        r2 = r2 | 8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getProfiles(com.nforetek.bt.jni.NfJni r7, java.lang.String r8, android.os.ParcelUuid[] r9) {
        /*
            Method dump skipped, instructions count: 456
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.util.bt.NfPrimitive.getProfiles(com.nforetek.bt.jni.NfJni, java.lang.String, android.os.ParcelUuid[]):int");
    }

    public static byte getClass(String address) {
        NfLog.d(TAG, "getClass() " + address);
        if (isAdapterValid()) {
            BluetoothDevice device = getAdapter().getRemoteDevice(address);
            BluetoothClass c = device.getBluetoothClass();
            if (c != null) {
                switch (c.getMajorDeviceClass()) {
                    case 256:
                        NfLog.d(TAG, "In getClass() : Return [CAT_COMPUTER]");
                        return (byte) 1;
                    case 512:
                        NfLog.d(TAG, "In getClass() : Return [CAT_PHONE]");
                        return (byte) 2;
                    default:
                        NfLog.d(TAG, "In getClass() : Unknown class = " + c.getMajorDeviceClass());
                        if (NfReflection.doesClassMatch(c, 1)) {
                            NfLog.d(TAG, "In getClass() : Return [CAT_STEREO_AUDIO]");
                            return (byte) 4;
                        } else if (NfReflection.doesClassMatch(c, 0)) {
                            NfLog.d(TAG, "In getClass() : Return [CAT_MONO_AUDIO]");
                            return (byte) 8;
                        }
                        break;
                }
            } else {
                NfLog.e(TAG, "In getClass() : Could not get BluetoothClass !!!");
            }
            NfLog.d(TAG, "In getClass() : Return [0x00]");
            return (byte) 0;
        }
        return (byte) 0;
    }

    public static String getBtAddress() {
        return !isAdapterValid() ? NfDef.DEFAULT_ADDRESS : getAdapter().getAddress();
    }

    public static boolean cancelDiscovery() {
        if (isAdapterValid()) {
            return getAdapter().cancelDiscovery();
        }
        return false;
    }

    public static boolean isDiscovering() {
        if (isAdapterValid()) {
            return getAdapter().isDiscovering();
        }
        return false;
    }

    public static boolean isDiscoverable() {
        return isAdapterValid() && getAdapter().getScanMode() == 23;
    }

    public static boolean checkAddress(String address) {
        if (isAdapterValid()) {
            return BluetoothAdapter.checkBluetoothAddress(address);
        }
        return false;
    }

    public static boolean setScanMode(int mode, int period) {
        NfLog.d(TAG, "setScanMode() mode: " + mode + " period: " + period);
        if (!isAdapterValid()) {
            return false;
        }
        switch (mode) {
            case 20:
                NfLog.d(TAG, "In setScanMode() : [SCAN_MODE_NONE]");
                break;
            case 21:
                NfLog.d(TAG, "In setScanMode() : [SCAN_MODE_CONNECTABLE]");
                NfReflection.setDiscoverableTimeout(120);
                break;
            case 22:
            default:
                NfLog.e(TAG, "In setScanMode() : Unknown mode = " + mode);
                return false;
            case 23:
                NfLog.d(TAG, "In setScanMode() : [SCAN_MODE_CONNECTABLE_DISCOVERABLE]");
                if (period == 0) {
                    NfLog.d(TAG, "In setScanMode() : Set discoverable Permanently");
                    NfReflection.setDiscoverableTimeout(period);
                    break;
                } else {
                    NfLog.d(TAG, "In setScanMode() : Set discoverable Temporarily " + period + " seconds");
                    NfReflection.setDiscoverableTimeout(period);
                    break;
                }
        }
        if (NfReflection.setScanMode(mode)) {
            return true;
        }
        NfLog.e(TAG, "In setScanMode() : Fail to setScanMode !!!");
        return false;
    }

    public static boolean isWifiConnecting() {
        if (sContext == null) {
            return false;
        }
        ConnectivityManager connManager = (ConnectivityManager) sContext.getSystemService("connectivity");
        NetworkInfo mWifi = connManager.getNetworkInfo(1);
        if (mWifi == null || mWifi.getState() != NetworkInfo.State.CONNECTING) {
            return false;
        }
        NfLog.d(TAG, "Wifi detail state is " + mWifi.getDetailedState());
        NfLog.d(TAG, "Wifi extra info: " + mWifi.getExtraInfo());
        return true;
    }

    private static boolean isAdapterValid() {
        return getAdapter() != null;
    }

    private static BluetoothAdapter getAdapter() {
        sAdapter = BluetoothAdapter.getDefaultAdapter();
        if (sAdapter == null) {
            NfLog.e(TAG, "BluetoothAdapter is null.");
        }
        return sAdapter;
    }

    public static boolean isAddressValid(String address) {
        if (address == null || !BluetoothAdapter.checkBluetoothAddress(address)) {
            NfLog.e(TAG, "Bluetooth address (" + address + ") not valid. ");
            return false;
        }
        return true;
    }

    public static BluetoothDevice getDevice(String address) {
        if (isAdapterValid()) {
            return getAdapter().getRemoteDevice(address);
        }
        return null;
    }

    public static void updateCachePairedDevices() {
        NfLog.v(TAG, "updateCachePairedDevices()");
        if (isAdapterValid()) {
            sPairedDevices = getAdapter().getBondedDevices();
        } else {
            NfLog.v(TAG, "In updateCachePairedDevices but adapter invalid.");
        }
    }

    public static Set<BluetoothDevice> getCachedPairedDevices() {
        return sPairedDevices;
    }

    public static void sendBroadcast(Intent intent) {
        try {
            sContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dumpClassMethod(Class c) {
        Method[] declaredMethods;
        NfLog.d(TAG, "dumpClassMethod: " + c.getName());
        for (Method method : c.getDeclaredMethods()) {
            NfLog.d(TAG, "Method name: " + method.getName());
        }
    }
}
