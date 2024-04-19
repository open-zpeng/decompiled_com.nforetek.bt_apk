package com.nforetek.bt.profile.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.nforetek.bt.aidl.INfCallbackBluetooth;
import com.nforetek.bt.callback.NfDoCallbackBluetooth;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.jni.NfJniBundle;
import com.nforetek.bt.jni.NfJniCallbackInterfaceBluetooth;
import com.nforetek.bt.profile.NfBluetoothCallbackInterface;
import com.nforetek.bt.profile._NfProfile;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.BluetoothUuid;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.bt.res.bt.NfPreference;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.bt.NfReflection;
import com.nforetek.util.normal.NfLog;
import com.nforetek.util.normal.NfUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class _NfBluetooth extends _NfProfile<INfCallbackBluetooth, NfDoCallbackBluetooth, NfBluetoothCallbackInterface> implements NfJniCallbackInterfaceBluetooth {
    private CallbackRoleChangeThread mRoleChangeThread;
    private static HashMap<String, Integer> sAclStateDictionary = new HashMap<>();
    private static byte[] aclDictionaryLock = new byte[0];
    private int mPrevScanMode = 20;
    private int mBtRoleMode = -1;
    private long mLastDeviceAclDisconnectedTime = 0;
    private int mBtLocalCacheState = NfDef.BT_STATE_OFF;
    private int mBtLocalCachePrevState = NfDef.BT_STATE_OFF;
    private boolean isAutoAcceptPairingRequest = true;
    BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.nforetek.bt.profile.bluetooth._NfBluetooth.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                NfLog.i(_NfBluetooth.this.TAG, "In onReceive() : [BluetoothAdapter.ACTION_STATE_CHANGED]");
                int prevState = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", -1);
                int newState = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                NfPrimitive.updateCachePairedDevices();
                if (prevState >= 0 && newState >= 0) {
                    ((NfBluetoothCallbackInterface) _NfBluetooth.this.manager()).onBtAdapterStateChanged(prevState, newState);
                    if (_NfBluetooth.getNfDefState(prevState) != _NfBluetooth.this.getBtLocalCachedState()) {
                        NfLog.e(_NfBluetooth.this.TAG, "Adapter prevState is not equal with cached state, workaround callback it.");
                        ((NfDoCallbackBluetooth) _NfBluetooth.this.callback()).onAdapterStateChanged(_NfBluetooth.this.getBtLocalCachedState(), _NfBluetooth.getNfDefState(prevState));
                    }
                    if (newState == 12 || newState == 13 || newState == 11 || newState == 10) {
                        _NfBluetooth.this.setBtLocalCachedPrevState(_NfBluetooth.getNfDefState(prevState));
                        _NfBluetooth.this.setBtLocalCachedState(_NfBluetooth.getNfDefState(newState));
                    }
                    switch (newState) {
                        case Integer.MIN_VALUE:
                            NfLog.e(_NfBluetooth.this.TAG, "In mMainBroadcastReceiver() : [BluetoothAdapter.ERROR]");
                            break;
                        case 10:
                            NfLog.i(_NfBluetooth.this.TAG, "In mMainBroadcastReceiver() : [BluetoothAdapter.STATE_OFF]");
                            ((NfBluetoothCallbackInterface) _NfBluetooth.this.manager()).onBtOff();
                            _NfBluetooth.this.mBtRoleMode = -1;
                            _NfBluetooth.this.mLastDeviceAclDisconnectedTime = 0L;
                            _NfBluetooth.this.jni().setJniProfileInitialed(false);
                            break;
                        case 11:
                            NfLog.i(_NfBluetooth.this.TAG, "In mMainBroadcastReceiver() : [BluetoothAdapter.STATE_TURNING_ON]");
                            ((NfBluetoothCallbackInterface) _NfBluetooth.this.manager()).onBtTurningOn();
                            break;
                        case 12:
                            NfLog.i(_NfBluetooth.this.TAG, "In mMainBroadcastReceiver() : [BluetoothAdapter.STATE_ON]");
                            ((NfBluetoothCallbackInterface) _NfBluetooth.this.manager()).onBtOn();
                            break;
                        case 13:
                            NfLog.i(_NfBluetooth.this.TAG, "In mMainBroadcastReceiver() : [BluetoothAdapter.STATE_TURNING_OFF]");
                            ((NfBluetoothCallbackInterface) _NfBluetooth.this.manager()).onBtTurningOff();
                            break;
                        default:
                            NfLog.e(_NfBluetooth.this.TAG, "In mMainBroadcastReceiver() : Unknown event !!! newState: " + newState + " prevState: " + prevState);
                            break;
                    }
                    if (newState != 10) {
                        if (newState == 14 && prevState == 10) {
                            ((NfDoCallbackBluetooth) _NfBluetooth.this.callback()).onAdapterStateChanged(10, 11);
                            return;
                        } else if (newState != 15 || prevState != 14) {
                            if (newState != 12) {
                                ((NfDoCallbackBluetooth) _NfBluetooth.this.callback()).onAdapterStateChanged(_NfBluetooth.getNfDefState(prevState), _NfBluetooth.getNfDefState(newState));
                                return;
                            } else {
                                _NfBluetooth.this.startDelayAdapterStateCallbackThreadCauseNfJniInitital(prevState, newState);
                                return;
                            }
                        } else {
                            return;
                        }
                    } else if (System.currentTimeMillis() > NfPrimitive.getBtOffAvailableTime()) {
                        ((NfDoCallbackBluetooth) _NfBluetooth.this.callback()).onAdapterStateChanged(_NfBluetooth.getNfDefState(prevState), _NfBluetooth.getNfDefState(newState));
                        return;
                    } else {
                        _NfBluetooth.this.startDelayAdapterStateCallbackThreadCauseProtectTime(prevState, newState);
                        return;
                    }
                }
                NfLog.e(_NfBluetooth.this.TAG, "Receive wrong state broadcast, prevState: " + prevState + " newState: " + newState);
            } else if (action.equals("android.intent.action.BOOT_COMPLETED")) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [BOOT_COMPLETED] +++");
                NfPreference.printPreference();
            } else if (action.equals("android.intent.action.LOCKED_BOOT_COMPLETED")) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_LOCKED_BOOT_COMPLETED] +++");
                NfPreference.printPreference();
            } else if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_DISCOVERY_STARTED] +++");
                ((NfBluetoothCallbackInterface) _NfBluetooth.this.manager()).onAdapterDiscoveryStarted();
                ((NfDoCallbackBluetooth) _NfBluetooth.this.callback()).onAdapterDiscoveryStarted();
            } else if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_DISCOVERY_FINISHED] +++");
                ((NfBluetoothCallbackInterface) _NfBluetooth.this.manager()).onAdapterDiscoveryFinished();
                ((NfDoCallbackBluetooth) _NfBluetooth.this.callback()).onAdapterDiscoveryFinished();
            } else if (action.equals("android.bluetooth.adapter.action.SCAN_MODE_CHANGED")) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_SCAN_MODE_CHANGED] +++");
                int mode = intent.getIntExtra("android.bluetooth.adapter.extra.SCAN_MODE", Integer.MIN_VALUE);
                ((NfDoCallbackBluetooth) _NfBluetooth.this.callback()).onAdapterDiscoverableModeChanged(_NfBluetooth.getNfDefState(_NfBluetooth.this.mPrevScanMode), _NfBluetooth.getNfDefState(mode));
                _NfBluetooth.this.mPrevScanMode = mode;
            } else if (action.equals("android.bluetooth.device.action.FOUND")) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_FOUND] +++");
                if (!NfPrimitive.isBtEnabled()) {
                    NfLog.e(_NfBluetooth.this.TAG, "BT Not enable!");
                    return;
                }
                if (!NfPrimitive.isDiscovering()) {
                    NfLog.e(_NfBluetooth.this.TAG, "Receiving ACTION_FOUND, but Bluetooth is not on scanning...");
                }
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (device == null) {
                    NfLog.e(_NfBluetooth.this.TAG, "device is null, BUT it should not be !!!");
                    return;
                }
                String address = device.getAddress();
                String name = device.getName();
                if (name == null) {
                    NfLog.e(_NfBluetooth.this.TAG, "name is null, Try it again...");
                    name = device.getName();
                    if (name == null) {
                        name = device.getAddress();
                        NfLog.e(_NfBluetooth.this.TAG, "name is still null, set name to mac address " + name);
                    }
                }
                ((NfDoCallbackBluetooth) _NfBluetooth.this.callback()).onDeviceFound(address, name, NfPrimitive.getClass(device.getAddress()));
            } else if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_BOND_STATE_CHANGED] +++");
                NfPrimitive.updateCachePairedDevices();
                BluetoothDevice device2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int prevBondState = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", Integer.MIN_VALUE);
                int bondState = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                int prevBondState2 = _NfBluetooth.this.getBondStateNfDef(prevBondState);
                int bondState2 = _NfBluetooth.this.getBondStateNfDef(bondState);
                String address2 = device2.getAddress();
                String name2 = device2.getName();
                if (name2 == null) {
                    name2 = device2.getAddress();
                }
                NfLog.i(_NfBluetooth.this.TAG, "Piggy Check Bond State : " + prevBondState2 + " -> " + bondState2);
                if (NfConfig.isPtsTest() && bondState2 == 332) {
                    NfReflection.sdpSearch(device2, BluetoothUuid.PBAP_PSE);
                }
                if (NfConfig.isPtsTest() && bondState2 == 332) {
                    ParcelUuid MasUuid = ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB");
                    NfReflection.sdpSearch(device2, MasUuid);
                }
                ((NfBluetoothCallbackInterface) _NfBluetooth.this.manager()).onBtDeviceBondStateChanged(address2, name2, prevBondState2, bondState2);
            } else if (action.equals("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED")) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_LOCAL_NAME_CHANGED] +++");
                String name3 = intent.getStringExtra("android.bluetooth.adapter.extra.LOCAL_NAME");
                ((NfDoCallbackBluetooth) _NfBluetooth.this.callback()).onLocalAdapterNameChanged(name3);
            } else if (action.equals("android.bluetooth.device.action.NAME_CHANGED")) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_NAME_CHANGED] +++");
                NfPrimitive.updateCachePairedDevices();
            } else if (action.equals("android.bluetooth.device.action.UUID")) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_UUID] +++");
                BluetoothDevice device3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                Parcelable[] uuids = intent.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
                if (uuids == null) {
                    NfLog.e(_NfBluetooth.this.TAG, "Uuids is null !!");
                    return;
                }
                ParcelUuid[] parcelUuids = new ParcelUuid[uuids.length];
                for (int j = 0; j < uuids.length; j++) {
                    parcelUuids[j] = ParcelUuid.fromString(uuids[j].toString());
                    NfLog.d(_NfBluetooth.this.TAG, "In onReceive() : UUID " + j + " = " + parcelUuids[j].toString());
                }
                String address3 = device3.getAddress();
                String name4 = device3.getName() != null ? device3.getName() : "";
                int profiles = NfPrimitive.getProfiles(_NfBluetooth.this.jni(), address3, parcelUuids);
                ((NfDoCallbackBluetooth) _NfBluetooth.this.callback()).onDeviceUuidsUpdated(device3.getAddress(), name4, profiles);
            } else if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_PAIRING_REQUEST] +++");
                BluetoothDevice device4 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int type = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
                Log.e(_NfBluetooth.this.TAG, "type: " + type);
                if (_NfBluetooth.this.isAutoAcceptPairingRequest) {
                    if (type == 2) {
                        Log.e(_NfBluetooth.this.TAG, "PAIRING_VARIANT_PASSKEY_CONFIRMATION");
                        try {
                            device4.getClass().getMethod("setPairingConfirmation", Boolean.TYPE).invoke(device4, true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (type == 0) {
                        Log.e(_NfBluetooth.this.TAG, "PAIRING_VARIANT_PIN");
                        try {
                            NfReflection.setPin(device4.getClass(), device4, "0000");
                            NfReflection.createBond(device4.getClass(), device4);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        Log.e(_NfBluetooth.this.TAG, "Unkown paring type" + type);
                    }
                }
            } else if (action.equals(NfDef.ACTION_LOCAL_OOB_DATA)) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_LOCAL_OOB_DATA] +++");
                byte[] key_c = intent.getByteArrayExtra(NfDef.EXTRA_LOCAL_OOB_DATA_KEY_C);
                byte[] key_r = intent.getByteArrayExtra(NfDef.EXTRA_LOCAL_OOB_DATA_KEY_R);
                String keyC_String = "";
                String keyR_String = "";
                for (int i = 0; i < 16; i++) {
                    keyC_String = String.valueOf(keyC_String) + String.format("%02X", Byte.valueOf(key_c[i]));
                    keyR_String = String.valueOf(keyR_String) + String.format("%02X", Byte.valueOf(key_r[i]));
                }
                NfLog.d(_NfBluetooth.this.TAG, "keyC_String: " + keyC_String);
                NfLog.d(_NfBluetooth.this.TAG, "keyR_String: " + keyR_String);
            } else if (action.equals(NfDef.ACTION_EXTEND_LOCAL_OOB_DATA)) {
                NfLog.i(_NfBluetooth.this.TAG, "+++ In onReceive() : [ACTION_EXTEND_LOCAL_OOB_DATA] +++");
                byte[] key_c192 = intent.getByteArrayExtra(NfDef.EXTRA_LOCAL_OOB_DATA_KEY_C192);
                byte[] key_r192 = intent.getByteArrayExtra(NfDef.EXTRA_LOCAL_OOB_DATA_KEY_R192);
                byte[] key_c256 = intent.getByteArrayExtra(NfDef.EXTRA_LOCAL_OOB_DATA_KEY_C256);
                byte[] key_r256 = intent.getByteArrayExtra(NfDef.EXTRA_LOCAL_OOB_DATA_KEY_R256);
                String keyC192_String = "";
                String keyR192_String = "";
                String keyC256_String = "";
                String keyR256_String = "";
                for (int i2 = 0; i2 < 16; i2++) {
                    keyC192_String = String.valueOf(keyC192_String) + String.format("%02X", Byte.valueOf(key_c192[i2]));
                    keyR192_String = String.valueOf(keyR192_String) + String.format("%02X", Byte.valueOf(key_r192[i2]));
                    keyC256_String = String.valueOf(keyC256_String) + String.format("%02X", Byte.valueOf(key_c256[i2]));
                    keyR256_String = String.valueOf(keyR256_String) + String.format("%02X", Byte.valueOf(key_r256[i2]));
                }
                NfLog.d(_NfBluetooth.this.TAG, "keyC192_String: " + keyC192_String);
                NfLog.d(_NfBluetooth.this.TAG, "keyR192_String: " + keyR192_String);
                NfLog.d(_NfBluetooth.this.TAG, "keyC256_String: " + keyC256_String);
                NfLog.d(_NfBluetooth.this.TAG, "keyR256_String: " + keyR256_String);
            }
        }
    };

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfBluetooth";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return 5;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
        context().unregisterReceiver(this.mReceiver);
        super.onDestroy();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void onCreate() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.BOOT_COMPLETED");
        filter.addAction("android.intent.action.LOCKED_BOOT_COMPLETED");
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        filter.addAction("android.bluetooth.adapter.action.SCAN_MODE_CHANGED");
        filter.addAction("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED");
        filter.addAction("android.bluetooth.device.action.FOUND");
        filter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        filter.addAction("android.bluetooth.device.action.NAME_CHANGED");
        filter.addAction("android.bluetooth.device.action.UUID");
        filter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        filter.addAction(NfDef.ACTION_LOCAL_OOB_DATA);
        filter.addAction(NfDef.ACTION_EXTEND_LOCAL_OOB_DATA);
        setBtLocalCachedState(NfPrimitive.getAdapterState());
        context().registerReceiver(this.mReceiver, filter);
        super.onCreate();
    }

    public boolean setBtEnable(boolean enable) {
        NfLog.v(this.TAG, "setBtEnable() enable: " + enable);
        return NfPrimitive.setBtEnable(enable);
    }

    public boolean setDiscoverableTimeout(int timeout) {
        NfLog.v(this.TAG, "setDiscoverableTimeout() timeout: " + timeout);
        if (timeout >= 0) {
            if (timeout > 300) {
                timeout = NfDef.BT_STATE_OFF;
            }
            return NfPrimitive.setBtDiscoverable(true, timeout);
        }
        return NfPrimitive.setBtDiscoverable(false, -1);
    }

    public boolean startDiscovery() {
        NfLog.v(this.TAG, "startDiscovery()");
        if (manager().isAlreadyQueueForScan()) {
            NfLog.d(this.TAG, "isAlreadyQueueForScan");
            return false;
        } else if (manager().tryToQueueForScan()) {
            NfLog.d(this.TAG, "tryToQueueForScan return true. queue for scan");
            return true;
        } else {
            manager().onBtStartScan();
            return NfPrimitive.startScan(true);
        }
    }

    public boolean cancelDiscovery() {
        NfLog.v(this.TAG, "cancelDiscovery()");
        return NfPrimitive.startScan(false);
    }

    public boolean pair(String address) {
        NfLog.v(this.TAG, "pair() " + address);
        if (NfPrimitive.isDiscovering()) {
            NfPrimitive.startScan(false);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return NfPrimitive.pair(address);
    }

    public boolean unpair(String address) {
        NfLog.v(this.TAG, "unPair() " + address);
        return NfPrimitive.unPair(address);
    }

    public boolean reqPairedDevices() {
        String[] mAddress;
        String[] name;
        int[] mSupportProfiles;
        byte[] mCat;
        NfLog.v(this.TAG, "reqPairedDevices()");
        int elements = 0;
        Set<BluetoothDevice> pairedDevices = NfPrimitive.getPairedDevice();
        if (pairedDevices != null && (elements = pairedDevices.size()) > 0) {
            mAddress = new String[elements];
            name = new String[elements];
            mSupportProfiles = new int[elements];
            mCat = new byte[elements];
            int index = 0;
            for (BluetoothDevice device : pairedDevices) {
                if (device != null) {
                    mAddress[index] = device.getAddress();
                    name[index] = device.getName();
                    NfLog.v(this.TAG, "Before getProfiles()...");
                    mSupportProfiles[index] = NfPrimitive.getProfiles(jni(), device.getAddress());
                    NfLog.v(this.TAG, "After getProfiles()...");
                    mCat[index] = NfPrimitive.getClass(device.getAddress());
                    index++;
                }
            }
        } else {
            mAddress = new String[1];
            name = new String[1];
            mSupportProfiles = new int[1];
            mCat = new byte[1];
        }
        for (int i = 0; i < elements; i++) {
            NfLog.v(this.TAG, "PairedDevice: " + name[i] + "(" + mAddress[i] + ") profiles: " + mSupportProfiles[i] + " category: " + ((int) mCat[i]));
        }
        callback().retPairedDevices(elements, mAddress, name, mSupportProfiles, mCat);
        return true;
    }

    public String getLocalAdapterName() {
        return NfPrimitive.getLocalAdapterName();
    }

    public String getLocalAdapterAddress() {
        return NfPrimitive.getLocalAdapterAddress();
    }

    public boolean setLocalAdapterName(String name) {
        return NfPrimitive.setLocalAdapterName(name);
    }

    public String getRemoteDeviceName(String address) {
        return NfPrimitive.getRemoteDeviceName(address);
    }

    public boolean isAdapterEnabled() {
        return NfPrimitive.isAdapterEnabled();
    }

    public int getAdapterState() {
        int state = NfPrimitive.getAdapterState();
        jni();
        if (!NfJni.isJniProfileInitialed() && state == 302) {
            NfLog.e(this.TAG, "Binder called get local BT state but jni profile not initialed, return prevState: " + getBtLocalCachedPrevState());
            return getBtLocalCachedPrevState();
        }
        return state;
    }

    public boolean isDiscovering() {
        return NfPrimitive.isDiscovering();
    }

    public boolean isDiscoverable() {
        return NfPrimitive.isDiscoverable();
    }

    public int getProfiles(String address) {
        if (jni() == null) {
            NfLog.e(this.TAG, "Jni is null");
            return -1;
        }
        return NfPrimitive.getProfiles(jni(), address);
    }

    public static boolean isDeviceAclDisconnected(String address) {
        return address.equals(NfDef.DEFAULT_ADDRESS) || getAclStateFromDictionary(address) == 1;
    }

    public boolean isAnyDeviceAclDisconnectedBeforeInTime(int time) {
        if (this.mLastDeviceAclDisconnectedTime == 0) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        long timePast = currentTime - this.mLastDeviceAclDisconnectedTime;
        return timePast < ((long) time);
    }

    public boolean switchRoleMode(int mode) throws RemoteException {
        NfLog.v(this.TAG, "switchRoleMode() " + mode);
        if (this.mBtRoleMode == -1) {
            NfLog.e(this.TAG, "BT is not enable !");
            return false;
        } else if (mode == 2 || mode == 1) {
            if (this.mBtRoleMode != mode) {
                return jni().switchRoleMode(mode);
            }
            NfLog.e(this.TAG, "Role switch with same mode. current mode is " + this.mBtRoleMode);
            return false;
        } else {
            NfLog.e(this.TAG, "Role swith with illegal mode: " + mode);
            return false;
        }
    }

    public int getRoleMode() throws RemoteException {
        NfLog.v(this.TAG, "getRoleMode()");
        return this.mBtRoleMode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getNfDefState(int state) {
        switch (state) {
            case 10:
                return NfDef.BT_STATE_OFF;
            case 11:
                return 301;
            case 12:
                return 302;
            case 13:
                return 303;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 22:
            default:
                return -1;
            case 20:
                return NfDef.BT_MODE_NONE;
            case 21:
                return NfDef.BT_MODE_CONNECTABLE;
            case 23:
                return NfDef.BT_MODE_CONNECTABLE_DISCOVERABLE;
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceBluetooth
    public void onJniBluetoothAclStateChanged(int status, byte[] address, int state, int reason) {
        String addr = NfUtils.getAddressStringFromByte(address);
        setAclStateToDictionary(addr, state);
        manager().onBtAclStateChanged(NfUtils.getAddressStringFromByte(address), state, reason);
        if (state == 1) {
            callback().onDeviceAclDisconnected(addr);
            this.mLastDeviceAclDisconnectedTime = System.currentTimeMillis();
        }
        if (reason == 8 || reason == 34) {
            if (!NfPrimitive.isDevicePaired(addr)) {
                NfLog.e(this.TAG, "Device not paired! Don't callback OOR.");
                return;
            }
            callback().onDeviceOutOfRange(addr);
            manager().onBtRemoteDeviceOutOfRange(addr);
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceBluetooth
    public void onJniRecreateBondDevice(String address, int is_connect) {
        manager().onBtRecreateBond(address, is_connect);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceBluetooth
    public void onJniBtRoleModeChanged(int mode) {
        NfLog.e(this.TAG, "onJniBtRoleModeChanged() " + mode);
        if (this.mRoleChangeThread != null) {
            try {
                if (this.mRoleChangeThread.getMode() != mode) {
                    this.mRoleChangeThread.interrupt();
                } else {
                    return;
                }
            } catch (NullPointerException e) {
            }
        }
        this.mRoleChangeThread = new CallbackRoleChangeThread(callback(), mode);
        this.mRoleChangeThread.start();
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceBluetooth
    public void onJniInitFinished(boolean finished) {
        NfLog.e(this.TAG, "onJniInitFinished() " + finished);
        manager().onJniInitFinished(finished);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class CallbackRoleChangeThread extends Thread {
        NfDoCallbackBluetooth mCallback;
        int mMode;

        public CallbackRoleChangeThread(NfDoCallbackBluetooth c, int m) {
            this.mCallback = c;
            this.mMode = m;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            _NfBluetooth.this.mBtRoleMode = this.mMode;
            this.mCallback.onBtRoleModeChanged(this.mMode);
            _NfBluetooth.this.mRoleChangeThread = null;
        }

        public int getMode() {
            return this.mMode;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBondStateNfDef(int state) {
        switch (state) {
            case 10:
                return NfDef.BOND_NONE;
            case 11:
                return NfDef.BOND_BONDING;
            case 12:
                return NfDef.BOND_BONDED;
            default:
                return -1;
        }
    }

    private static int getAclStateFromDictionary(String address) {
        synchronized (aclDictionaryLock) {
            Integer ver = sAclStateDictionary.get(address);
            if (ver != null) {
                return ver.intValue();
            }
            return 1;
        }
    }

    private static void setAclStateToDictionary(String address, int state) {
        if (state != 0 && state != 1) {
            NfLog.e("NfBluetooth", "setAclStateToDictionary but state invalid: " + state);
        }
        synchronized (aclDictionaryLock) {
            if (sAclStateDictionary.get(address) != null) {
                sAclStateDictionary.remove(address);
                sAclStateDictionary.put(address, Integer.valueOf(state));
            } else {
                NfLog.i("NfBluetooth", "New Device ACL state. init AclStateDictionary.");
                sAclStateDictionary.put(address, Integer.valueOf(state));
            }
        }
    }

    public static void resetAclStateDictionary(NfDoCallbackBluetooth callback) {
        synchronized (aclDictionaryLock) {
            for (Map.Entry<String, Integer> entry : sAclStateDictionary.entrySet()) {
                if (entry.getValue().intValue() == 0) {
                    String address = entry.getKey();
                    NfLog.i("NfBluetooth", "When reset ACL state map but acl(" + address + ") still connected, callback disconnted.");
                    callback.onDeviceAclDisconnected(address);
                }
            }
            sAclStateDictionary.clear();
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void dequeueMessage(Message msg) {
        NfJniBundle b = (NfJniBundle) msg.obj;
        switch (msg.what) {
            case 16:
                NfLog.v(this.TAG, "onJniBluetoothAclStateChanged()");
                onJniBluetoothAclStateChanged(b.getInt1(), b.getByteArray1(), b.getInt2(), b.getInt3());
                return;
            case 701:
                NfLog.v(this.TAG, "onJniRecreateBondDevice()");
                onJniRecreateBondDevice(b.getString1(), b.getInt1());
                return;
            case 702:
                NfLog.v(this.TAG, "onJniBtRoleModeChanged()");
                onJniBtRoleModeChanged(b.getInt1());
                return;
            case NfJni.onJniInitFinished /* 5000 */:
                NfLog.v(this.TAG, "onJniInitFinished");
                onJniInitFinished(b.getBoolean1());
                return;
            default:
                return;
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void onSystemTimeChanged() {
        super.onSystemTimeChanged();
        this.mLastDeviceAclDisconnectedTime = 0L;
    }

    /* loaded from: classes.dex */
    private abstract class RunnableII implements Runnable {
        protected int newState;
        protected int prevState;

        public RunnableII(int v1, int v2) {
            this.prevState = 0;
            this.newState = 0;
            this.prevState = v1;
            this.newState = v2;
        }
    }

    public int getBtLocalCachedState() {
        NfLog.v(this.TAG, "getBtLocalCachedState() state: " + this.mBtLocalCacheState);
        return this.mBtLocalCacheState;
    }

    public void setBtLocalCachedState(int state) {
        NfLog.v(this.TAG, "setBtLocalCachedState() state: " + state);
        this.mBtLocalCacheState = state;
    }

    public int getBtLocalCachedPrevState() {
        NfLog.v(this.TAG, "getBtLocalCachedPrevState() state: " + this.mBtLocalCachePrevState);
        return this.mBtLocalCachePrevState;
    }

    public void setBtLocalCachedPrevState(int state) {
        NfLog.v(this.TAG, "setBtLocalCachedPrevState() state: " + state);
        this.mBtLocalCachePrevState = state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDelayAdapterStateCallbackThreadCauseProtectTime(int prevState, int newState) {
        Thread t = new Thread(new RunnableII(this, prevState, newState) { // from class: com.nforetek.bt.profile.bluetooth._NfBluetooth.2
            @Override // java.lang.Runnable
            public void run() {
                NfLog.v(this.TAG, "startDelayAdapterStateCallbackThreadCauseProtectTime() " + this.prevState + " -> " + this.newState);
                while (System.currentTimeMillis() < NfPrimitive.getBtOffAvailableTime()) {
                    try {
                        Thread.sleep(100L);
                        NfLog.v(this.TAG, "Waiting for delay callback BT Off state.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (this.getBtLocalCachedState() != 300) {
                        NfLog.v(this.TAG, "In delay callback BT Off thread but local cached state is " + this.getBtLocalCachedState());
                        return;
                    }
                }
                ((NfDoCallbackBluetooth) this.callback()).onAdapterStateChanged(_NfBluetooth.getNfDefState(this.prevState), _NfBluetooth.getNfDefState(this.newState));
            }
        });
        t.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDelayAdapterStateCallbackThreadCauseNfJniInitital(int prevState, int newState) {
        Thread t = new Thread(new RunnableII(this, prevState, newState) { // from class: com.nforetek.bt.profile.bluetooth._NfBluetooth.3
            @Override // java.lang.Runnable
            public void run() {
                NfLog.v(this.TAG, "startDelayAdapterStateCallbackThreadCauseNfJniInitital() " + this.prevState + " -> " + this.newState);
                do {
                    this.jni();
                    if (NfJni.isJniProfileInitialed()) {
                        ((NfDoCallbackBluetooth) this.callback()).onAdapterStateChanged(_NfBluetooth.getNfDefState(this.prevState), _NfBluetooth.getNfDefState(this.newState));
                        return;
                    }
                    try {
                        Thread.sleep(100L);
                        NfLog.v(this.TAG, "Waiting for delay callback when NfJni profile not initialed.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (this.getBtLocalCachedState() == 302);
                NfLog.v(this.TAG, "In delay callback BT On thread but local cached state is " + this.getBtLocalCachedState());
            }
        });
        t.start();
    }
}
