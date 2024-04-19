package com.nforetek.bt.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackSpp;
import com.nforetek.bt.aidl.INfCommandSpp;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class NfServiceSpp extends NfService {
    private INfCommandSpp.Stub mBinder = new INfCommandSpp.Stub() { // from class: com.nforetek.bt.service.NfServiceSpp.1
        @Override // com.nforetek.bt.aidl.INfCommandSpp
        public boolean isSppServiceReady() throws RemoteException {
            NfLog.v(NfServiceSpp.this.TAG, "isSppServiceReady()");
            return NfServiceSpp.this.isNfServiceConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandSpp
        public boolean registerSppCallback(INfCallbackSpp cb) throws RemoteException {
            NfLog.v(NfServiceSpp.this.TAG, "registerSppCallback()");
            if (NfServiceSpp.this.command() == null) {
                NfServiceSpp.this.addPendingCallback(cb);
                return true;
            }
            return NfServiceSpp.this.command().registerSppCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandSpp
        public boolean unregisterSppCallback(INfCallbackSpp cb) throws RemoteException {
            NfLog.v(NfServiceSpp.this.TAG, "unregisterSppCallback");
            if (NfServiceSpp.this.command() == null) {
                return false;
            }
            return NfServiceSpp.this.command().unregisterSppCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandSpp
        public boolean reqSppConnect(String address) throws RemoteException {
            NfLog.v(NfServiceSpp.this.TAG, "reqSppConnect() " + address);
            if (NfServiceSpp.this.command() == null) {
                return false;
            }
            return NfServiceSpp.this.command().reqSppConnect(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandSpp
        public boolean reqSppDisconnect(String address) throws RemoteException {
            NfLog.v(NfServiceSpp.this.TAG, "reqSppDisconnect() " + address);
            if (NfServiceSpp.this.command() == null) {
                return false;
            }
            return NfServiceSpp.this.command().reqSppDisconnect(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandSpp
        public void reqSppConnectedDeviceAddressList() throws RemoteException {
            NfLog.v(NfServiceSpp.this.TAG, "reqSppConnectedDeviceAddressList()");
            if (NfServiceSpp.this.command() != null) {
                NfServiceSpp.this.command().reqSppConnectedDeviceAddressList();
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandSpp
        public boolean isSppConnected(String address) throws RemoteException {
            NfLog.v(NfServiceSpp.this.TAG, "isSppConnected() " + address);
            if (NfServiceSpp.this.command() == null) {
                return false;
            }
            return NfServiceSpp.this.command().isSppConnected(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandSpp
        public void reqSppSendData(String address, byte[] sppData) throws RemoteException {
            NfLog.v(NfServiceSpp.this.TAG, "reqSppSendData() " + address);
            if (NfServiceSpp.this.command() != null) {
                NfServiceSpp.this.command().reqSppSendData(address, sppData);
            }
        }
    };

    @Override // com.nforetek.bt.service.NfService
    protected String getLogTag() {
        return "NfServiceSpp";
    }

    @Override // com.nforetek.bt.service.NfService
    public boolean isEnableService() {
        return NfConfig.isEnableSpp();
    }

    @Override // com.nforetek.bt.service.NfService
    IBinder getBinder() {
        return this.mBinder;
    }

    @Override // com.nforetek.bt.service.NfService
    protected void onNfServiceConnected(IInterface cb) throws RemoteException {
        command().registerSppCallback((INfCallbackSpp) cb);
    }
}
