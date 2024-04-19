package com.nforetek.bt.manager;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.ParcelUuid;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackA2dp;
import com.nforetek.bt.aidl.INfCallbackAvrcp;
import com.nforetek.bt.aidl.INfCallbackBluetooth;
import com.nforetek.bt.aidl.INfCallbackGattServer;
import com.nforetek.bt.aidl.INfCallbackHfp;
import com.nforetek.bt.aidl.INfCallbackHid;
import com.nforetek.bt.aidl.INfCallbackMap;
import com.nforetek.bt.aidl.INfCallbackOpp;
import com.nforetek.bt.aidl.INfCallbackPbap;
import com.nforetek.bt.aidl.INfCallbackSpp;
import com.nforetek.bt.aidl.INfCommandManager;
import com.nforetek.bt.aidl.NfHfpClientCall;
import com.nforetek.bt.callback.NfDoCallbackInterface;
import com.nforetek.bt.profile.a2dp._NfA2dp;
import com.nforetek.bt.profile.avrcp._NfAvrcp;
import com.nforetek.bt.profile.bluetooth.BasicConnectThread;
import com.nforetek.bt.profile.bluetooth.BasicConnectionInterface;
import com.nforetek.bt.profile.bluetooth._NfBluetooth;
import com.nforetek.bt.profile.gatt._NfGatt;
import com.nforetek.bt.profile.hfp._NfHfp;
import com.nforetek.bt.profile.hid._NfHid;
import com.nforetek.bt.profile.map._NfMap;
import com.nforetek.bt.profile.map.java.NfMapCommand;
import com.nforetek.bt.profile.opp._NfOpp;
import com.nforetek.bt.profile.pbap._NfPbap;
import com.nforetek.bt.profile.spp._NfSpp;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.bt.res.bt.NfPreference;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import com.nforetek.util.normal.NfUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class NfServiceManagerBinder extends INfCommandManager.Stub {
    private static String TAG = "NfServiceManagerBinder";
    private _NfA2dp mA2dp;
    private _NfAvrcp mAvrcp;
    private BasicConnectionInterface mBasic;
    private _NfBluetooth mBluetooth;
    private _NfGatt mGatt;
    private _NfHfp mHfp;
    private _NfHid mHid;
    private NfDoCallbackInterface mManager;
    private _NfMap mMap;
    private _NfOpp mOpp;
    private _NfPbap mPbap;
    private _NfSpp mSpp;
    private String mVersionName;
    private String STRING_HFP_NOT_ENABLE = "HFP Profile is not enable";
    private String STRING_A2DP_NOT_ENABLE = "A2DP Profile is not enable";
    private String STRING_AVRCP_NOT_ENABLE = "AVRCP Profile is not enable";
    private String STRING_PBAP_NOT_ENABLE = "PBAP Profile is not enable";
    private String STRING_HID_NOT_ENABLE = "HID Profile is not enable";
    private String STRING_SPP_NOT_ENABLE = "SPP Profile is not enable";
    private String STRING_MAP_NOT_ENABLE = "MAP Profile is not enable";
    private String STRING_OPP_NOT_ENABLE = "OPP Profile is not enable";
    private String STRING_GATT_NOT_ENABLE = "Gatt Profile is not enable";

    public void onDestroy() {
        NfLog.d(TAG, "onDestroy()");
        this.mManager = null;
        this.mBluetooth = null;
        this.mHfp = null;
        this.mA2dp = null;
        this.mAvrcp = null;
        this.mPbap = null;
        this.mHid = null;
        this.mSpp = null;
        this.mMap = null;
        this.mOpp = null;
        this.mGatt = null;
    }

    public void setInstace(BasicConnectionInterface instance) {
        this.mBasic = instance;
    }

    public void setInstance(_NfBluetooth instance) {
        this.mBluetooth = instance;
    }

    public void setInstance(_NfHfp instance) {
        this.mHfp = instance;
    }

    public void setInstance(_NfA2dp instance) {
        this.mA2dp = instance;
    }

    public void setInstance(_NfAvrcp instance) {
        this.mAvrcp = instance;
    }

    public void setInstance(_NfPbap instance) {
        this.mPbap = instance;
    }

    public void setInstance(_NfHid instance) {
        this.mHid = instance;
    }

    public void setInstance(_NfSpp instance) {
        this.mSpp = instance;
    }

    public void setInstance(_NfMap instance) {
        this.mMap = instance;
    }

    public void setInstance(_NfOpp instance) {
        this.mOpp = instance;
    }

    public void setInstance(_NfGatt instance) {
        this.mGatt = instance;
    }

    public void releaseBluetooth() {
        this.mBluetooth = null;
    }

    public void releaseHfp() {
        this.mHfp = null;
    }

    public void releaseA2dp() {
        this.mA2dp = null;
    }

    public void releaseAvrcp() {
        this.mAvrcp = null;
    }

    public void releasePbap() {
        this.mPbap = null;
    }

    public void releaseHid() {
        this.mHid = null;
    }

    public void releaseSpp() {
        this.mSpp = null;
    }

    public void releaseMap() {
        this.mMap = null;
    }

    public void releaseOpp() {
        this.mOpp = null;
    }

    public void releaseGatt() {
        this.mGatt = null;
    }

    public NfServiceManagerBinder(NfDoCallbackInterface inf) {
        this.mVersionName = "";
        this.mVersionName = NfUtils.printAppVersion((Context) inf);
        TAG = "NfServiceManagerBinder_" + this.mVersionName;
        NfLog.v(TAG, "NfServiceManagerBinder() init");
        this.mManager = inf;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean registerA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
        NfLog.v(TAG, "registerA2dpCallback()");
        return this.mManager.registerA2dpCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean unregisterA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
        NfLog.v(TAG, "unregisterA2dpCallback()");
        return this.mManager.unregisterA2dpCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getA2dpConnectionState() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getA2dpConnectionState()");
            if (this.mA2dp == null) {
                NfLog.e(TAG, this.STRING_A2DP_NOT_ENABLE);
                return 100;
            }
            return this.mA2dp.getProfileState();
        }
        return 100;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isA2dpConnected() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isA2dpConnected()");
            if (this.mA2dp != null) {
                return this.mA2dp.getProfileState() >= 140;
            }
            NfLog.e(TAG, this.STRING_A2DP_NOT_ENABLE);
            return false;
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getA2dpConnectedAddress() throws RemoteException {
        if (!isBtEnable()) {
            return NfDef.DEFAULT_ADDRESS;
        }
        NfLog.v(TAG, "getA2dpConnectedAddress()");
        if (this.mA2dp == null) {
            NfLog.e(TAG, this.STRING_A2DP_NOT_ENABLE);
            return NfDef.DEFAULT_ADDRESS;
        }
        return this.mA2dp.getConnectedAddress();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqA2dpConnect(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqA2dpConnect() " + address);
            if (this.mA2dp == null) {
                NfLog.e(TAG, this.STRING_A2DP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mA2dp.connect(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqA2dpDisconnect(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqA2dpDisconnect() " + address);
            if (this.mBasic.isBasicDisconnecting()) {
                return false;
            }
            if (this.mA2dp == null) {
                NfLog.e(TAG, this.STRING_A2DP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mA2dp.disconnect(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void pauseA2dpRender() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "pauseA2dpRender()");
            this.mA2dp.pauseRender();
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void startA2dpRender() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "startA2dpRender()");
            this.mA2dp.startRender();
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean setA2dpLocalVolume(float vol) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "setA2dpLocalVolume() " + vol);
            if (this.mA2dp == null) {
                NfLog.e(TAG, this.STRING_A2DP_NOT_ENABLE);
                return false;
            }
            return this.mA2dp.setLocalVolume(vol);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean setA2dpStreamType(int type) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "setA2dpStreamType() " + type);
            if (this.mA2dp == null) {
                NfLog.e(TAG, this.STRING_A2DP_NOT_ENABLE);
                return false;
            }
            return this.mA2dp.setStreamType(type);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getA2dpStreamType() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getA2dpStreamType()");
            if (this.mA2dp == null) {
                NfLog.e(TAG, this.STRING_A2DP_NOT_ENABLE);
                return -1;
            }
            return this.mA2dp.getStreamType();
        }
        return -1;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean registerAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
        NfLog.v(TAG, "registerAvrcpCallback()");
        return this.mManager.registerAvrcpCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean unregisterAvrcpCallback(INfCallbackAvrcp cb) throws RemoteException {
        NfLog.v(TAG, "unregisterAvrcpCallback()");
        return this.mManager.unregisterAvrcpCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getAvrcpConnectionState() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getAvrcpConnectionState()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return 100;
            }
            return this.mAvrcp.getProfileState();
        }
        return 100;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isAvrcpConnected() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isAvrcpConnected()");
            if (this.mAvrcp != null) {
                return this.mAvrcp.getProfileState() >= 140;
            }
            NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
            return false;
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getAvrcpConnectedAddress() throws RemoteException {
        if (!isBtEnable()) {
            return NfDef.DEFAULT_ADDRESS;
        }
        NfLog.v(TAG, "getAvrcpConnectedAddress()");
        if (this.mAvrcp == null) {
            NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
            return NfDef.DEFAULT_ADDRESS;
        }
        return this.mAvrcp.getConnectedAddress();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpConnect(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpConnect() " + address);
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mAvrcp.connect(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpDisconnect(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpDisconnect() " + address);
            if (this.mBasic.isBasicDisconnecting()) {
                return false;
            }
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mAvrcp.disconnect(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isAvrcp13Supported(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isAvrcp13Supported() " + address);
            return !isAddressInvalid(address) && (this.mBluetooth.getProfiles(address) & 8) > 0;
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isAvrcp14Supported(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isAvrcp14Supported() " + address);
            return !isAddressInvalid(address) && (this.mBluetooth.getProfiles(address) & 16) > 0;
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpPlay() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpPlay()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.play();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpStop() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpStop()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.stop();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpPause() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpPause()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.pause();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpForward() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpForward()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.forward();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpBackward() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpBackward()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.backward();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpVolumeUp() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpVolumeUp()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.volumeUp();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpVolumeDown() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpVolumeDown()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.volumeDown();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpStartFastForward() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpStartFastForward()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.startFastForward();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpStopFastForward() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpStopFastForward()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.stopFastForward();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpStartRewind() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpStartRewind()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.startRewind();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpStopRewind() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpStopRewind()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.stopRewind();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp13GetCapabilitiesSupportEvent() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp13GetCapabilitiesSupportEvent()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.getCapabilities();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp13GetPlayerSettingAttributesList() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp13GetPlayerSettingAttributesList()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.getAttributesList();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp13GetPlayerSettingValuesList(byte attributeId) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp13GetPlayerSettingValuesList() attributeId: " + ((int) attributeId));
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.getValuesList(attributeId);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp13GetPlayerSettingCurrentValues() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp13GetPlayerSettingCurrentValues()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.getValues();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp13SetPlayerSettingValue(byte attributeId, byte valueId) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp13SetPlayerSettingValue() attributeId: " + ((int) attributeId) + " valueId: " + ((int) valueId));
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.setValue(attributeId, valueId);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp13GetElementAttributesPlaying() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp13GetElementAttributesPlaying()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.getElementAttributes();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp13GetPlayStatus() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp13GetPlayStatus()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.getPlayStatus();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpRegisterEventWatcher(byte eventId, long interval) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpRegisterEventWatcher() eventId: " + ((int) eventId) + " interval: " + interval);
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.registerEvent(eventId, (int) interval);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcpUnregisterEventWatcher(byte eventId) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcpUnregisterEventWatcher() eventId: " + ((int) eventId));
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.unregisterEvent(eventId);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp13NextGroup() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp13NextGroup()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.nextGroup();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp13PreviousGroup() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp13PreviousGroup()");
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.previousGroup();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp13GetPlayerSettingAttributeText(byte attributeId) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp13GetPlayerSettingAttributeText() attributeId: " + ((int) attributeId));
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.getAttributeText(attributeId);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp13GetPlayerSettingValueText(byte attributeId, byte valueId) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp13GetPlayerSettingValueText() attributeId: " + ((int) attributeId) + " valueId: " + ((int) valueId));
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.getValuesList(attributeId);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isAvrcp14BrowsingChannelEstablished() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isAvrcp14BrowsingChannelEstablished()");
            if (this.mAvrcp != null) {
                return this.mAvrcp.getBrowseState() == 140;
            }
            NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
            return false;
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp14SetAddressedPlayer(int playerId) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp14SetAddressedPlayer() playerId: " + playerId);
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp14SetBrowsedPlayer(int playerId) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp14SetBrowsedPlayer() playerId: " + playerId);
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.setBrowsedPlayer(playerId);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp14GetFolderItems(byte scopeId) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp14GetFolderItems() scopeId: " + ((int) scopeId));
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.getFolderItem(scopeId);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp14ChangePath(int uidCounter, long uid, byte direction) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp14ChangePath() uidCounter: " + uidCounter + " uid: " + uid + " direction: " + ((int) direction));
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.changePath(direction, uid);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp14GetItemAttributes(byte scopeId, int uidCounter, long uid) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp14GetItemAttributes() scopeId: " + ((int) scopeId) + " uidCounter: " + uidCounter + " uid: " + uid);
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.getItemAttributes(scopeId, uid, uidCounter);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp14PlaySelectedItem(byte scopeId, int uidCounter, long uid) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp14PlaySelectedItem() scopeId: " + ((int) scopeId) + " uidCounter: " + uidCounter + " uid: " + uid);
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.playItem(uid, uidCounter);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp14Search(String text) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp14Search() text: " + text);
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.searchString(text);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp14AddToNowPlaying(byte scopeId, int uidCounter, long uid) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp14AddToNowPlaying() scopeId: " + ((int) scopeId) + " uidCounter: " + uidCounter + " uid: " + uid);
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
                return false;
            }
            return this.mAvrcp.addToNowPlaying(uid, uidCounter);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqAvrcp14SetAbsoluteVolume(byte volume) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqAvrcp14SetAbsoluteVolume() volume: " + ((int) volume));
            if (this.mAvrcp == null) {
                NfLog.e(TAG, this.STRING_AVRCP_NOT_ENABLE);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean registerBtCallback(INfCallbackBluetooth cb) throws RemoteException {
        NfLog.v(TAG, "registerBtCallback()");
        return this.mManager.registerBluetoothCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean unregisterBtCallback(INfCallbackBluetooth cb) throws RemoteException {
        NfLog.v(TAG, "unregisterBtCallback()");
        return this.mManager.unregisterBluetoothCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getNfServiceVersionName() throws RemoteException {
        NfLog.v(TAG, "getNfServiceVersionName()");
        return NfConfig.isAfterAndroid9() ? String.valueOf(this.mVersionName) + "," + NfPreference.getBluetoothApkVersion() + "," + NfPreference.getBluedroidVersion() : this.mVersionName;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean setBtEnable(boolean enable) throws RemoteException {
        NfLog.v(TAG, "setBtEnable() " + enable);
        return this.mBluetooth.setBtEnable(enable);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean setBtDiscoverableTimeout(int timeout) throws RemoteException {
        if (!isBtEnable()) {
            return false;
        }
        NfLog.v(TAG, "setBtDiscoverableTimeout() timeout: " + timeout);
        return this.mBluetooth.setDiscoverableTimeout(timeout);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean startBtDiscovery() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "startBtDiscovery()");
            if (this.mBasic.isBasicConnecting()) {
                NfLog.e(TAG, "Basic connect is connecting! reject scan request.");
                return false;
            } else if (NfPrimitive.isAnyDevicePairing()) {
                NfLog.e(TAG, "Some device is pairing! reject scan request.");
                return false;
            } else {
                if (this.mBasic.isAutoConnecting(true)) {
                    NfLog.e(TAG, "Auto connect is connecting! Interrupt it !");
                } else if (NfConfig.isEnableHfp() && this.mHfp != null && this.mHfp.isConnecting()) {
                    NfLog.e(TAG, "HFP is connecting! reject scan request.");
                    return false;
                } else if (NfConfig.isEnableA2dp() && this.mA2dp != null && this.mA2dp.isConnecting()) {
                    NfLog.e(TAG, "A2DP is connecting! reject scan request.");
                    return false;
                } else if (NfConfig.isEnableAvrcp() && this.mAvrcp != null && this.mAvrcp.isConnecting()) {
                    NfLog.e(TAG, "AVRCP is connecting! reject scan request.");
                    return false;
                } else if (NfConfig.isEnablePbap() && this.mPbap != null && this.mPbap.getConnectedAddressList().size() > 0) {
                    NfLog.e(TAG, "PBAP is downloading! reject scan request.");
                    return false;
                }
                return this.mBluetooth.startDiscovery();
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean cancelBtDiscovery() throws RemoteException {
        if (!isBtEnable()) {
            return false;
        }
        NfLog.v(TAG, "cancelBtDiscovery()");
        return this.mBluetooth.cancelDiscovery();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqBtPair(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqBtPair() " + address);
            if (this.mHfp != null && this.mHfp.getConnectedAddress().equals(address)) {
                NfLog.e(TAG, "This device HFP is already connected.");
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                if (NfPrimitive.isDevicePaired(address)) {
                    NfLog.e(TAG, "Device already paired!");
                    return false;
                } else if (NfPrimitive.isDevicePairing(address)) {
                    NfLog.e(TAG, "Device pairing!");
                    return false;
                } else if (NfPrimitive.isAnyDevicePairing()) {
                    NfLog.e(TAG, "Other Devices pairing!");
                    return false;
                } else {
                    if (BasicConnectThread.isConnecting()) {
                        try {
                            if (BasicConnectThread.getSharedBasicConnectThread().isAutoConnect()) {
                                NfLog.e(TAG, "Auto connecting! Stop auto connect thread.");
                                this.mManager.forceStopAutoConnectThread();
                            } else {
                                NfLog.e(TAG, "Basic connecting! reject pairing command.");
                                return false;
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                    if (this.mManager.isAclConnected(address)) {
                        if (this.mManager.initDelayPairThread(address)) {
                            NfLog.d(TAG, "This device ACL is connected. Try pair later.");
                            return true;
                        }
                        NfLog.e(TAG, "This device ACL is already waiting for pairing.");
                        return false;
                    }
                    return this.mBluetooth.pair(address);
                }
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqBtUnpair(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqBtUnpair() " + address);
            if (isAddressInvalid(address)) {
                return false;
            }
            if (_NfBluetooth.isDeviceAclDisconnected(address)) {
                return this.mBluetooth.unpair(address);
            }
            NfLog.v(TAG, "Device ACL still connected, disconnect it first.");
            if (this.mBasic.basicDisconnect(address, true) == 0) {
                NfLog.v(TAG, "No need to disconnect any profile, unpair it.");
                return this.mBluetooth.unpair(address);
            }
            return true;
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqBtPairedDevices() throws RemoteException {
        if (!isBtEnable()) {
            return false;
        }
        NfLog.v(TAG, "reqBtPairedDevices()");
        return this.mBluetooth.reqPairedDevices();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getBtLocalName() throws RemoteException {
        if (!isBtEnable()) {
            return null;
        }
        NfLog.v(TAG, "getBtLocalName()");
        return this.mBluetooth.getLocalAdapterName();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getBtRemoteDeviceName(String address) throws RemoteException {
        if (!isBtEnable()) {
            return null;
        }
        NfLog.v(TAG, "getBtRemoteDeviceName() " + address);
        if (address == null || !NfPrimitive.isAddressValid(address)) {
            return "";
        }
        String result = this.mBluetooth.getRemoteDeviceName(address);
        NfLog.v(TAG, "getBtRemoteDeviceName() name: " + result);
        return result;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getBtLocalAddress() throws RemoteException {
        if (!isBtEnable()) {
            return NfDef.DEFAULT_ADDRESS;
        }
        NfLog.v(TAG, "getBtLocalAddress()");
        return this.mBluetooth.getLocalAdapterAddress();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean setBtLocalName(String name) throws RemoteException {
        if (!isBtEnable()) {
            return false;
        }
        NfLog.v(TAG, "setBtLocalName() name: " + name);
        return this.mBluetooth.setLocalAdapterName(name);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isBtEnabled() throws RemoteException {
        if (!isBtEnable()) {
            return false;
        }
        NfLog.v(TAG, "isBtEnabled()");
        return this.mBluetooth.isAdapterEnabled();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getBtState() throws RemoteException {
        NfLog.v(TAG, "getBtState()");
        return this.mBluetooth.getAdapterState();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isBtDiscovering() throws RemoteException {
        if (!isBtEnable()) {
            return false;
        }
        NfLog.v(TAG, "isBtDiscovering()");
        return this.mBluetooth.isDiscovering();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isBtDiscoverable() throws RemoteException {
        if (!isBtEnable()) {
            return false;
        }
        NfLog.v(TAG, "isBtDiscoverable()");
        return this.mBluetooth.isDiscoverable();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isBtAutoConnectEnable() throws RemoteException {
        NfLog.v(TAG, "isBtAutoConnectEnable()");
        return NfPreference.getAutoConnectCondition() > 0;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int reqBtConnectHfpA2dp(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqBtConnectHfpA2dp() " + address);
            if (address == null || !NfPrimitive.isAddressValid(address)) {
                return -1;
            }
            return this.mBasic.basicConnect(address, false, false, false);
        }
        return -1;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int reqBtDisconnectAll() throws RemoteException {
        if (!isBtEnable()) {
            return -1;
        }
        NfLog.v(TAG, "reqBtDisconnectAll()");
        return this.mBasic.basicDisconnect();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getBtRemoteUuids(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getBtRemoteUuids() " + address);
            if (address == null || !NfPrimitive.isAddressValid(address)) {
                return -1;
            }
            return this.mBluetooth.getProfiles(address);
        }
        return -1;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isDeviceAclDisconnected(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isDeviceAclDisconnected() " + address);
            if (isAddressInvalid(address)) {
                return false;
            }
            return _NfBluetooth.isDeviceAclDisconnected(address);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean switchBtRoleMode(int mode) throws RemoteException {
        NfLog.v(TAG, "switchBtRoleMode() " + mode);
        if (isBtEnable()) {
            if (!NfConfig.isAllowRoleSwitch()) {
                NfLog.e(TAG, "Not allow role switch !!");
                return false;
            }
            if (mode == 2) {
                if (this.mA2dp.getProfileState() != 110 || this.mAvrcp.getProfileState() != 110) {
                    this.mBasic.disconnectA2dpAvrcp();
                    this.mA2dp.disconnect(this.mHfp.getConnectedAddress());
                    this.mAvrcp.disconnect(this.mHfp.getConnectedAddress());
                } else {
                    this.mBluetooth.switchRoleMode(2);
                }
            } else {
                this.mBluetooth.switchRoleMode(mode);
            }
            return true;
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getBtRoleMode() throws RemoteException {
        NfLog.v(TAG, "getBtRoleMode()");
        if (isBtEnable()) {
            return this.mBluetooth.getRoleMode();
        }
        return -1;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void setBtAutoConnect(int condition, int period) throws RemoteException {
        NfLog.v(TAG, "setBtAutoConnect() condition: " + condition + " perioid: " + period);
        if (condition >= 0 && condition <= 7) {
            NfPreference.setAutoConnectCondition(condition);
            if (condition == 0) {
                this.mManager.forceStopAutoConnectThread();
            }
        } else {
            NfLog.e(TAG, "condition value invalid. condition value: " + condition);
        }
        if (period >= 0) {
            NfPreference.setAutoConnectPeriod(period);
        } else {
            NfLog.e(TAG, "period value invalid. period value: " + period);
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getBtAutoConnectCondition() throws RemoteException {
        NfLog.v(TAG, "getBtAutoConnectCondition()");
        return NfPreference.getAutoConnectCondition();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getBtAutoConnectPeriod() throws RemoteException {
        NfLog.v(TAG, "getBtAutoConnectPeriod()");
        return NfPreference.getAutoConnectPeriod();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getBtAutoConnectState() throws RemoteException {
        NfLog.v(TAG, "getBtAutoConnectState()");
        return this.mBasic.getAutoConnectState();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getBtAutoConnectingAddress() throws RemoteException {
        NfLog.v(TAG, "getBtAutoConnectingAddress()");
        return NfPreference.getAutoConnectAddress();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean registerHfpCallback(INfCallbackHfp cb) throws RemoteException {
        NfLog.v(TAG, "registerHfpCallback()");
        return this.mManager.registerHfpCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean unregisterHfpCallback(INfCallbackHfp cb) throws RemoteException {
        NfLog.v(TAG, "unregisterHfpCallback()");
        return this.mManager.unregisterHfpCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getHfpConnectionState() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getHfpConnectionState()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return 100;
            }
            return this.mHfp.getProfileState();
        }
        return 100;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isHfpConnected() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isHfpConnected() " + this.mHfp.isConnected());
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.isConnected();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getHfpConnectedAddress() throws RemoteException {
        if (!isBtEnable()) {
            return NfDef.DEFAULT_ADDRESS;
        }
        NfLog.v(TAG, "getHfpConnectedAddress()");
        if (this.mHfp == null) {
            NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
            return NfDef.DEFAULT_ADDRESS;
        }
        return this.mHfp.getConnectedAddress();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getHfpAudioConnectionState() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getHfpAudioConnectionState()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return 100;
            }
            return this.mHfp.getScoState();
        }
        return 100;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpConnect(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpConnect() " + address);
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mHfp.connect(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpDisconnect(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpDisconnect() " + address);
            if (this.mBasic.isBasicDisconnecting()) {
                return false;
            }
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mHfp.disconnect(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public List<NfHfpClientCall> getHfpCallList() throws RemoteException {
        if (!isBtEnable()) {
            return null;
        }
        NfLog.v(TAG, "getHfpCallList()");
        if (this.mHfp == null) {
            NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
            return new ArrayList();
        }
        return this.mHfp.getCallList();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getHfpRemoteSignalStrength() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getHfpRemoteSignalStrength()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return -1;
            }
            return this.mHfp.getSignal();
        }
        return -1;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isHfpRemoteOnRoaming() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isHfpRemoteOnRoaming()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.isRoaming();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getHfpRemoteBatteryIndicator() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getHfpRemoteBatteryIndicator()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return -1;
            }
            return this.mHfp.getBattery();
        }
        return -1;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isHfpRemoteTelecomServiceOn() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isHfpRemoteTelecomServiceOn()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.isTelServiceOn();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isHfpRemoteVoiceDialOn() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isHfpRemoteVoiceDialOn()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.isVoiceDialOn();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpDialCall(String number) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpDialCall() number: " + number);
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.dial(number);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpReDial() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpReDial()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.redial();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpMemoryDial(String index) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpMemoryDial() index: " + index);
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.memoryDial(index);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpAnswerCall(int flag) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpAnswerCall() flag: " + flag);
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.answerCall(flag);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpRejectIncomingCall() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpRejectIncomingCall()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.rejectIncomingCall();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpTerminateCurrentCall() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpTerminateCurrentCall()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.reqHfpTerminateCall(0);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpSendDtmf(String number) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpSendDtmf() number: " + number);
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.sendDtmf(number);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpAudioTransferToCarkit() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpAudioTransferToCarkit()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.transferToCarkit();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpAudioTransferToPhone() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpAudioTransferToPhone()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.transferToPhone(false);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getHfpRemoteNetworkOperator() throws RemoteException {
        if (!isBtEnable()) {
            return "";
        }
        NfLog.v(TAG, "getHfpRemoteNetworkOperator()");
        if (this.mHfp == null) {
            NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
            return "";
        }
        return this.mHfp.getNetworkOperator();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getHfpRemoteSubscriberNumber() throws RemoteException {
        if (!isBtEnable()) {
            return "";
        }
        NfLog.v(TAG, "getHfpRemoteSubscriberNumber()");
        if (this.mHfp == null) {
            NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
            return "";
        }
        return this.mHfp.getSubscriberNumber();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHfpVoiceDial(boolean enable) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHfpVoiceDial() enable: " + enable);
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.voiceDial(enable);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void pauseHfpRender() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "pauseHfpRender()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
            } else {
                this.mHfp.pauseRender();
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void startHfpRender() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "startHfpRender()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
            } else {
                this.mHfp.startRender();
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isHfpMicMute() throws RemoteException {
        if (!isBtEnable()) {
            return false;
        }
        NfLog.v(TAG, "isHfpMicMute()");
        return this.mHfp.isHfpMicMute();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void muteHfpMic(boolean mute) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "muteHfpMic() " + mute);
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
            } else {
                this.mHfp.muteMic(mute);
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isHfpInBandRingtoneSupport() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isHfpInbandRingtoneSupport()");
            if (this.mHfp == null) {
                NfLog.e(TAG, this.STRING_HFP_NOT_ENABLE);
                return false;
            }
            return this.mHfp.isSupportInBandRingtone();
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean registerPbapCallback(INfCallbackPbap cb) throws RemoteException {
        NfLog.v(TAG, "registerPbapCallback()");
        return this.mManager.registerPbapCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean unregisterPbapCallback(INfCallbackPbap cb) throws RemoteException {
        NfLog.v(TAG, "unregisterPbapCallback()");
        return this.mManager.unregisterPbapCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getPbapConnectionState() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getPbapConnectionState()");
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
                return 100;
            }
            return this.mPbap.getProfileState();
        }
        return 100;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isPbapDownloading() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isPbapDownloading()");
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
                return false;
            }
            boolean result = this.mPbap.isDownloading();
            NfLog.v(TAG, "isPbapDownloading() return " + result);
            return result;
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getPbapDownloadingAddress() throws RemoteException {
        if (!isBtEnable()) {
            return NfDef.DEFAULT_ADDRESS;
        }
        NfLog.v(TAG, "getPbapDownloadingAddress()");
        if (this.mPbap == null) {
            NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
            return NfDef.DEFAULT_ADDRESS;
        }
        return this.mPbap.getDownloadingAddress();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqPbapDownload(String address, int storage, int property) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapDownload() address: " + address + " storage: " + storage + " property: " + property);
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mPbap.downloadByPass(address, storage, property, 0, NfMapCommand.COUNT_DOWNLOAD_ALL);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqPbapDownloadRange(String address, int storage, int property, int startPos, int offset) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapDownloadRange() address: " + address + " storage: " + storage + " property: " + property);
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mPbap.downloadByPass(address, storage, property, startPos, offset);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqPbapDownloadToDatabase(String address, int storage, int property) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapDownloadToDatabase() " + address + " storage: " + storage + " property: " + property);
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mPbap.downloadToDb(address, storage, property, 0, NfMapCommand.COUNT_DOWNLOAD_ALL);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqPbapDownloadRangeToDatabase(String address, int storage, int property, int startPos, int offset) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapDownloadRangeToDatabase() " + address + " storage: " + storage + " property: " + property + " startPos: " + startPos + " offset: " + offset);
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mPbap.downloadToDb(address, storage, property, startPos, offset);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqPbapDownloadToContactsProvider(String address, int storage, int property) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapDownloadToContactsProvider() " + address + " storage: " + storage + " property: " + property);
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mPbap.downloadToProvider(address, storage, property, 0, NfMapCommand.COUNT_DOWNLOAD_ALL);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqPbapDownloadRangeToContactsProvider(String address, int storage, int property, int startPos, int offset) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapDownloadToContactsProvider() " + address + " storage: " + storage + " property: " + property + " startPos: " + startPos + " offset: " + offset);
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mPbap.downloadToProvider(address, storage, property, startPos, offset);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void reqPbapDatabaseQueryNameByNumber(String address, String target) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapDatabaseQueryNameByNumber() " + address + " target: " + target);
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
            } else if (address != null && NfPrimitive.isAddressValid(address)) {
                this.mPbap.queryNameByNumber(address, target);
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void reqPbapDatabaseQueryNameByPartialNumber(String address, String target, int findPosition) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapDatabaseQueryNameByPartialNumber() " + address + " target: " + target + " findPosition: " + findPosition);
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
            } else if (address != null && NfPrimitive.isAddressValid(address)) {
                this.mPbap.queryNameByPartial(address, target, findPosition);
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void reqPbapDatabaseAvailable(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapDatabaseAvailable() " + address);
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
            } else if (address != null && NfPrimitive.isAddressValid(address)) {
                this.mPbap.reqDatabaseAvailable(address);
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void reqPbapDeleteDatabaseByAddress(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapDeleteDatabaseByAddress() " + address);
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
            } else if (address != null && NfPrimitive.isAddressValid(address)) {
                this.mPbap.deleteDatabase(address);
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void reqPbapCleanDatabase() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapCleanDatabase()");
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
            } else {
                this.mPbap.cleanDatabase();
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqPbapDownloadInterrupt(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqPbapDownloadInterrupt() " + address);
            if (this.mPbap == null) {
                NfLog.e(TAG, this.STRING_PBAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mPbap.downloadInterrupt(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean setPbapDownloadNotify(int frequency) throws RemoteException {
        NfLog.v(TAG, "setPbapDownloadNotify() " + frequency);
        if (this.mPbap == null) {
            return false;
        }
        return this.mPbap.setPbapDownloadNotify(frequency);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean registerHidCallback(INfCallbackHid cb) throws RemoteException {
        NfLog.v(TAG, "registerHidCallback()");
        return this.mManager.registerHidCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean unregisterHidCallback(INfCallbackHid cb) throws RemoteException {
        NfLog.v(TAG, "unregisterHidCallback()");
        return this.mManager.unregisterHidCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getHidConnectionState() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getHidConnectionState()");
            if (this.mHid == null) {
                NfLog.e(TAG, this.STRING_HID_NOT_ENABLE);
                return 100;
            }
            return this.mHid.getProfileState();
        }
        return 100;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isHidConnected() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isHidConnected()");
            if (this.mHid != null) {
                return this.mHid.getProfileState() >= 140;
            }
            NfLog.e(TAG, this.STRING_HID_NOT_ENABLE);
            return false;
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getHidConnectedAddress() throws RemoteException {
        if (!isBtEnable()) {
            return NfDef.DEFAULT_ADDRESS;
        }
        NfLog.v(TAG, "getHidConnectedAddress()");
        if (this.mHid == null) {
            NfLog.e(TAG, this.STRING_HID_NOT_ENABLE);
            return NfDef.DEFAULT_ADDRESS;
        }
        return this.mHid.getConnectedAddress();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHidConnect(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHidConnect() " + address);
            if (this.mHid == null) {
                NfLog.e(TAG, this.STRING_HID_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mHid.connect(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqHidDisconnect(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqHidDisconnect() " + address);
            if (this.mBasic.isBasicDisconnecting()) {
                return false;
            }
            if (this.mHid == null) {
                NfLog.e(TAG, this.STRING_HID_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mHid.disconnect(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqSendHidMouseCommand(int button, int offset_x, int offset_y, int wheel) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqSendHidMouseCommand - button: " + button + ", offset_x: " + offset_x + ", offset_y: " + offset_y + ", wheel: " + wheel);
            if (this.mHid == null) {
                NfLog.e(TAG, this.STRING_HID_NOT_ENABLE);
                return false;
            }
            return this.mHid.reqSendHidMouseCommand(button, offset_x, offset_y, wheel);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqSendHidVirtualKeyCommand(int key_1, int key_2) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqSendHidMouseCommand - key_1: " + key_1 + ", key_2: " + key_2);
            if (this.mHid == null) {
                NfLog.e(TAG, this.STRING_HID_NOT_ENABLE);
                return false;
            }
            return this.mHid.reqSendHidVirtualKeyCommand(key_1, key_2);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean registerSppCallback(INfCallbackSpp cb) throws RemoteException {
        NfLog.v(TAG, "registerSppCallback()");
        return this.mManager.registerSppCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean unregisterSppCallback(INfCallbackSpp cb) throws RemoteException {
        NfLog.v(TAG, "unregisterSppCallback()");
        return this.mManager.unregisterSppCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqSppConnect(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqSppConnect() " + address);
            if (this.mSpp == null) {
                NfLog.e(TAG, this.STRING_SPP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mSpp.connect(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqSppDisconnect(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqSppDisconnect() " + address);
            if (this.mSpp == null) {
                NfLog.e(TAG, this.STRING_SPP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mSpp.disconnect(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void reqSppConnectedDeviceAddressList() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqSppConnectedDeviceAddressList()");
            if (this.mSpp == null) {
                NfLog.e(TAG, this.STRING_SPP_NOT_ENABLE);
            } else {
                this.mSpp.getConnectedList();
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isSppConnected(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isSppConnected() " + address);
            if (this.mSpp == null) {
                NfLog.e(TAG, this.STRING_SPP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mSpp.isConnected(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void reqSppSendData(String address, byte[] sppData) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqSppSendData() " + address);
            if (this.mSpp == null) {
                NfLog.e(TAG, this.STRING_SPP_NOT_ENABLE);
            } else if (address != null && NfPrimitive.isAddressValid(address)) {
                this.mSpp.sendData(address, sppData);
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean registerMapCallback(INfCallbackMap cb) throws RemoteException {
        NfLog.v(TAG, "registerMapCallback()");
        return this.mManager.registerMapCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean unregisterMapCallback(INfCallbackMap cb) throws RemoteException {
        NfLog.v(TAG, "unregisterMapCallback()");
        return this.mManager.unregisterMapCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqMapDownloadSingleMessage(String address, int folder, String handle, int storage) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqMapDownloadSingleMessage() " + address + " folder: " + folder + " handle: " + handle);
            if (this.mMap == null) {
                NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mMap.downloadOne(address, folder, handle, storage);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqMapDownloadMessage(String address, int folder, boolean isContentDownload, int count, int startPos, int storage, String periodBegin, String periodEnd, String sender, String recipient, int readStatus, int type) throws RemoteException {
        if (!isBtEnable()) {
            return false;
        }
        NfLog.v(TAG, "reqMapDownloadMessage() " + address + " folder: " + folder);
        if (this.mMap == null) {
            NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
            return false;
        } else if (isAddressInvalid(address)) {
            return false;
        } else {
            return this.mMap.downloadAll(address, folder, isContentDownload, count, startPos, storage, periodBegin, periodEnd, sender, recipient, readStatus, type);
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqMapRegisterNotification(String address, boolean downloadNewMessage) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqMapRegisterNotification()");
            if (this.mMap == null) {
                NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mMap.registerNotification(address, true);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void reqMapUnregisterNotification(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqMapUnregisterNotification() " + address);
            if (this.mMap == null) {
                NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
            } else if (address != null && NfPrimitive.isAddressValid(address)) {
                this.mMap.registerNotification(address, false);
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean isMapNotificationRegistered(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "isMapNotificationRegistered() " + address);
            if (this.mMap == null) {
                NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mMap.isNotificationRegistered();
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqMapDownloadInterrupt(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqMapDownloadInterrupt() " + address);
            if (this.mMap == null) {
                NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mMap.downloadInterrupt(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void reqMapDatabaseAvailable() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqMapDatabaseAvailable()");
            if (this.mMap == null) {
                NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
            } else {
                this.mMap.queryDatabaseAvailable();
            }
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void reqMapDeleteDatabaseByAddress(String address) throws RemoteException {
        NfLog.v(TAG, "reqMapDeleteDatabaseByAddress()");
        if (this.mMap == null) {
            NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
        } else if (address != null && NfPrimitive.isAddressValid(address)) {
            this.mMap.deleteDatabaseByAddress(address);
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public void reqMapCleanDatabase() throws RemoteException {
        NfLog.v(TAG, "reqMapCleanDatabase()");
        if (this.mMap == null) {
            NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
        } else {
            this.mMap.cleanDatabase();
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getMapCurrentState(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getMapCurrentState() " + address);
            if (this.mMap == null) {
                NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
                return -1;
            }
            return this.mMap.getProfileState();
        }
        return -1;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getMapRegisterState(String address) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "getMapRegisterState() " + address);
            if (this.mMap == null) {
                NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
            } else if (address == null || !NfPrimitive.isAddressValid(address)) {
            }
        }
        return -1;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqMapSendMessage(String address, String message, String target) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqMapSendMessage() " + address + " target: " + target);
            if (this.mMap == null) {
                NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mMap.sendMessage(address, message, target);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqMapDeleteMessage(String address, int folder, String handle) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqMapDeleteMessage() " + address);
            if (this.mMap == null) {
                NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mMap.deleteMessage(address, folder, handle);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqMapChangeReadStatus(String address, int folder, String handle, boolean isReadStatus) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqMapChangeReadStatus()");
            if (this.mMap == null) {
                NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
                return false;
            } else if (isAddressInvalid(address)) {
                return false;
            } else {
                return this.mMap.changeReadStatus(address, folder, handle, isReadStatus);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean setMapDownloadNotify(int frequency) throws RemoteException {
        NfLog.v(TAG, "setMapDownloadNotify() " + frequency);
        if (this.mMap == null) {
            NfLog.e(TAG, this.STRING_MAP_NOT_ENABLE);
            return false;
        }
        this.mMap.setDownloadNotifyFrequency(frequency);
        return true;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean registerOppCallback(INfCallbackOpp cb) throws RemoteException {
        NfLog.v(TAG, "registerOppCallback()");
        return this.mManager.registerOppCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean unregisterOppCallback(INfCallbackOpp cb) throws RemoteException {
        NfLog.v(TAG, "unregisterOppCallback()");
        return this.mManager.unregisterOppCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean setOppFilePath(String path) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "setOppFilePath()");
            if (this.mOpp == null) {
                NfLog.e(TAG, this.STRING_OPP_NOT_ENABLE);
                return false;
            }
            return this.mOpp.setOppFilePath(path);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public String getOppFilePath() throws RemoteException {
        if (!isBtEnable()) {
            return null;
        }
        NfLog.v(TAG, "getOppFilePath()");
        if (this.mOpp == null) {
            NfLog.e(TAG, this.STRING_OPP_NOT_ENABLE);
            return "";
        }
        return this.mOpp.getOppFilePath();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqOppAcceptReceiveFile(boolean accept) throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqOppAcceptReceiveFile()");
            if (this.mOpp == null) {
                NfLog.e(TAG, this.STRING_OPP_NOT_ENABLE);
                return false;
            }
            return this.mOpp.reqOppAcceptReceiveFile(accept);
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqOppInterruptReceiveFile() throws RemoteException {
        if (isBtEnable()) {
            NfLog.v(TAG, "reqOppInterruptReceiveFile()");
            if (this.mOpp == null) {
                NfLog.e(TAG, this.STRING_OPP_NOT_ENABLE);
                return false;
            }
            return this.mOpp.reqOppInterruptReceiveFile();
        }
        return false;
    }

    private boolean isBtEnable() {
        if (BluetoothAdapter.getDefaultAdapter() == null) {
            NfLog.e(TAG, "BluetoothAdapter is null.");
        } else {
            int state = BluetoothAdapter.getDefaultAdapter().getState();
            on = state == 12;
            if (!on) {
                NfLog.e(TAG, "BluetoothAdapter state is not ON. state: " + state);
            }
        }
        return on;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean registerGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
        NfLog.v(TAG, "registerGattServerCallback()");
        return this.mManager.registerGattServerCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean unregisterGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
        NfLog.v(TAG, "unregisterGattServerCallback()");
        return this.mManager.unregisterGattServerCallback(cb);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public int getGattServerConnectionState() throws RemoteException {
        NfLog.v(TAG, "getGattServerConnectionState()");
        if (this.mGatt == null) {
            NfLog.e(TAG, this.STRING_GATT_NOT_ENABLE);
            return -1;
        }
        return this.mGatt.getProfileState();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqGattServerDisconnect(String address) throws RemoteException {
        NfLog.v(TAG, "reqGattServerDisconnect() " + address);
        if (this.mGatt == null) {
            NfLog.e(TAG, this.STRING_GATT_NOT_ENABLE);
        } else if (isAddressInvalid(address)) {
        }
        return false;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqGattServerListen(boolean listen) throws RemoteException {
        NfLog.v(TAG, "reqGattServerListen()");
        if (this.mGatt == null) {
            NfLog.e(TAG, this.STRING_GATT_NOT_ENABLE);
            return false;
        }
        return this.mGatt.reqGattServerListen(listen);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqGattServerBeginServiceDeclaration(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
        NfLog.v(TAG, "reqGattServerBeginServiceDeclaration()");
        if (this.mGatt == null) {
            NfLog.e(TAG, this.STRING_GATT_NOT_ENABLE);
            return false;
        }
        return this.mGatt.beginServiceDeclaration(srvcType, srvcUuid);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqGattServerAddCharacteristic(ParcelUuid charUuid, int properties, int permissions) throws RemoteException {
        NfLog.v(TAG, "reqGattServerAddCharacteristic()");
        if (this.mGatt == null) {
            NfLog.e(TAG, this.STRING_GATT_NOT_ENABLE);
            return false;
        }
        return this.mGatt.addCharacteristic(charUuid, properties, permissions);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqGattServerAddDescriptor(ParcelUuid descUuid, int permissions) throws RemoteException {
        NfLog.v(TAG, "reqGattServerAddDescriptor()");
        if (this.mGatt == null) {
            NfLog.e(TAG, this.STRING_GATT_NOT_ENABLE);
            return false;
        }
        return this.mGatt.addDescriptor(descUuid, permissions);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqGattServerEndServiceDeclaration() throws RemoteException {
        NfLog.v(TAG, "reqGattServerEndServiceDeclaration()");
        if (this.mGatt == null) {
            NfLog.e(TAG, this.STRING_GATT_NOT_ENABLE);
            return false;
        }
        return this.mGatt.endServiceDeclaration();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqGattServerRemoveService(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
        NfLog.v(TAG, "reqGattServerRemoveService()");
        if (this.mGatt == null) {
            NfLog.e(TAG, this.STRING_GATT_NOT_ENABLE);
            return false;
        }
        return this.mGatt.removeService(srvcType, srvcUuid);
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqGattServerClearServices() throws RemoteException {
        NfLog.v(TAG, "reqGattServerClearServices()");
        if (this.mGatt == null) {
            NfLog.e(TAG, this.STRING_GATT_NOT_ENABLE);
            return false;
        }
        return this.mGatt.clearServices();
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqGattServerSendResponse(String address, int requestId, int status, int offset, byte[] value) throws RemoteException {
        NfLog.v(TAG, "reqGattServerSendResponse()");
        if (this.mGatt == null) {
            NfLog.e(TAG, this.STRING_GATT_NOT_ENABLE);
            return false;
        } else if (isAddressInvalid(address)) {
            return false;
        } else {
            return this.mGatt.sendResponse(address, requestId, status, offset, value);
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public boolean reqGattServerSendNotification(String address, int srvcType, ParcelUuid srvcUuid, ParcelUuid charUuid, boolean confirm, byte[] value) throws RemoteException {
        NfLog.v(TAG, "reqGattServerSendNotification()");
        if (this.mGatt == null) {
            NfLog.e(TAG, this.STRING_GATT_NOT_ENABLE);
            return false;
        } else if (isAddressInvalid(address)) {
            return false;
        } else {
            return this.mGatt.sendNotification(address, srvcType, srvcUuid, charUuid, confirm, value);
        }
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public List<ParcelUuid> getGattAddedGattServiceUuidList() throws RemoteException {
        NfLog.v(TAG, "getGattAddedGattServiceUuidList()");
        return null;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public List<ParcelUuid> getGattAddedGattCharacteristicUuidList(ParcelUuid srvcUuid) throws RemoteException {
        NfLog.v(TAG, "getGattAddedGattCharacteristicUuidList()");
        return null;
    }

    @Override // com.nforetek.bt.aidl.INfCommandManager
    public List<ParcelUuid> getGattAddedGattDescriptorUuidList(ParcelUuid srvcUuid, ParcelUuid charUuid) throws RemoteException {
        NfLog.v(TAG, "getGattAddedGattDescriptorUuidList()");
        return null;
    }

    private boolean isAddressInvalid(String address) {
        if (address == null || !NfPrimitive.isAddressValid(address)) {
            NfLog.e(TAG, "Address invalid!! address: " + address);
            return true;
        }
        return false;
    }
}
