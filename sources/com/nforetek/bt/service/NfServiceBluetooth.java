package com.nforetek.bt.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackBluetooth;
import com.nforetek.bt.aidl.INfCommandBluetooth;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class NfServiceBluetooth extends NfService {
    private INfCommandBluetooth.Stub mBinder = new INfCommandBluetooth.Stub() { // from class: com.nforetek.bt.service.NfServiceBluetooth.1
        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isBluetoothServiceReady() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "isBluetoothServiceReady()");
            return NfServiceBluetooth.this.isNfServiceConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean registerBtCallback(INfCallbackBluetooth cb) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "registerBtCallback()");
            if (NfServiceBluetooth.this.command() == null) {
                NfServiceBluetooth.this.addPendingCallback(cb);
                return true;
            }
            return NfServiceBluetooth.this.command().registerBtCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean unregisterBtCallback(INfCallbackBluetooth cb) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "unregisterBtCallback()");
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().unregisterBtCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public String getNfServiceVersionName() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "getNfServiceVersionName()");
            if (NfServiceBluetooth.this.command() == null) {
                NfLog.e(NfServiceBluetooth.this.TAG, "Piggy Check command is null! ");
                return "";
            }
            return NfServiceBluetooth.this.command().getNfServiceVersionName();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean setBtEnable(boolean enable) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "setBtEnable() " + enable);
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().setBtEnable(enable);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean setBtDiscoverableTimeout(int timeout) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "setBtDiscoverableTimeout() timeout: " + timeout);
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().setBtDiscoverableTimeout(timeout);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean startBtDiscovery() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "startBtDiscovery()");
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().startBtDiscovery();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean cancelBtDiscovery() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "cancelBtDiscovery()");
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().cancelBtDiscovery();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean reqBtPair(String address) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "reqBtPair() " + address);
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().reqBtPair(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean reqBtUnpair(String address) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "reqBtUnpair() " + address);
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().reqBtUnpair(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean reqBtPairedDevices() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "reqBtPairedDevices()");
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().reqBtPairedDevices();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public String getBtLocalName() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "getBtLocalName()");
            return NfServiceBluetooth.this.command() == null ? "" : NfServiceBluetooth.this.command().getBtLocalName();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public String getBtRemoteDeviceName(String address) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "getBtRemoteDeviceName() " + address);
            return NfServiceBluetooth.this.command() == null ? "" : NfServiceBluetooth.this.command().getBtRemoteDeviceName(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public String getBtLocalAddress() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "getBtLocalAddress()");
            return NfServiceBluetooth.this.command() == null ? NfDef.DEFAULT_ADDRESS : NfServiceBluetooth.this.command().getBtLocalAddress();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean setBtLocalName(String name) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "setBtLocalName() name: " + name);
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().setBtLocalName(name);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isBtEnabled() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "isBtEnabled()");
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().isBtEnabled();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtState() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "getBtState()");
            if (NfServiceBluetooth.this.command() == null) {
                return 100;
            }
            return NfServiceBluetooth.this.command().getBtState();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isBtDiscovering() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "isBtDiscovering()");
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().isBtDiscovering();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isBtDiscoverable() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "isBtDiscoverable()");
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().isBtDiscoverable();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isBtAutoConnectEnable() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "isBtAutoConnectEnable()");
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().isBtAutoConnectEnable();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int reqBtConnectHfpA2dp(String address) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "reqBtConnectHfpA2dp() " + address);
            if (NfServiceBluetooth.this.command() == null) {
                return -1;
            }
            return NfServiceBluetooth.this.command().reqBtConnectHfpA2dp(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int reqBtDisconnectAll() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "reqBtDisconnectAll()");
            if (NfServiceBluetooth.this.command() == null) {
                return -1;
            }
            return NfServiceBluetooth.this.command().reqBtDisconnectAll();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtRemoteUuids(String address) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "getBtRemoteUuids() " + address);
            if (NfServiceBluetooth.this.command() == null) {
                return -1;
            }
            return NfServiceBluetooth.this.command().getBtRemoteUuids(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean isDeviceAclDisconnected(String address) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "isDeviceAclDisconnected() " + address);
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().isDeviceAclDisconnected(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public boolean switchBtRoleMode(int mode) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "switchBtRoleMode() " + mode);
            if (NfServiceBluetooth.this.command() == null) {
                return false;
            }
            return NfServiceBluetooth.this.command().switchBtRoleMode(mode);
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtRoleMode() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "getBtRoleMode()");
            if (NfServiceBluetooth.this.command() == null) {
                return -1;
            }
            return NfServiceBluetooth.this.command().getBtRoleMode();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public void setBtAutoConnect(int condition, int peroid) throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "setBtAutoConnect() condition: " + condition + " peroid: " + peroid);
            if (NfServiceBluetooth.this.command() != null) {
                NfServiceBluetooth.this.command().setBtAutoConnect(condition, peroid);
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtAutoConnectCondition() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "getBtAutoConnectCondition()");
            if (NfServiceBluetooth.this.command() == null) {
                return -1;
            }
            return NfServiceBluetooth.this.command().getBtAutoConnectCondition();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtAutoConnectPeriod() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "getBtAutoConnectPeriod()");
            if (NfServiceBluetooth.this.command() == null) {
                return -1;
            }
            return NfServiceBluetooth.this.command().getBtAutoConnectPeriod();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public int getBtAutoConnectState() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "getBtAutoConnectState()");
            if (NfServiceBluetooth.this.command() == null) {
                return -1;
            }
            return NfServiceBluetooth.this.command().getBtAutoConnectState();
        }

        @Override // com.nforetek.bt.aidl.INfCommandBluetooth
        public String getBtAutoConnectingAddress() throws RemoteException {
            NfLog.v(NfServiceBluetooth.this.TAG, "getBtAutoConnectingAddress()");
            return NfServiceBluetooth.this.command() == null ? NfDef.DEFAULT_ADDRESS : NfServiceBluetooth.this.command().getBtAutoConnectingAddress();
        }
    };

    @Override // com.nforetek.bt.service.NfService
    protected String getLogTag() {
        return "NfServiceBluetooth";
    }

    @Override // com.nforetek.bt.service.NfService
    public boolean isEnableService() {
        return true;
    }

    @Override // com.nforetek.bt.service.NfService
    IBinder getBinder() {
        return this.mBinder;
    }

    @Override // com.nforetek.bt.service.NfService
    protected void onNfServiceConnected(IInterface cb) throws RemoteException {
        this.mNfCommand.registerBtCallback((INfCallbackBluetooth) cb);
    }
}
