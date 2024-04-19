package com.nforetek.bt.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackHid;
import com.nforetek.bt.aidl.INfCommandHid;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class NfServiceHid extends NfService {
    private INfCommandHid.Stub mBinder = new INfCommandHid.Stub() { // from class: com.nforetek.bt.service.NfServiceHid.1
        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean isHidServiceReady() throws RemoteException {
            NfLog.v(NfServiceHid.this.TAG, "isHidServiceReady()");
            return NfServiceHid.this.isNfServiceConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean registerHidCallback(INfCallbackHid cb) throws RemoteException {
            NfLog.v(NfServiceHid.this.TAG, "registerHidCallback()");
            if (NfServiceHid.this.command() == null) {
                NfServiceHid.this.addPendingCallback(cb);
                return true;
            }
            return NfServiceHid.this.command().registerHidCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean unregisterHidCallback(INfCallbackHid cb) throws RemoteException {
            NfLog.v(NfServiceHid.this.TAG, "unregisterHidCallback()");
            if (NfServiceHid.this.command() == null) {
                return false;
            }
            return NfServiceHid.this.command().unregisterHidCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public int getHidConnectionState() throws RemoteException {
            NfLog.v(NfServiceHid.this.TAG, "getHidConnectionState()");
            if (NfServiceHid.this.command() == null) {
                return 100;
            }
            return NfServiceHid.this.command().getHidConnectionState();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean isHidConnected() throws RemoteException {
            NfLog.v(NfServiceHid.this.TAG, "isHidConnected()");
            if (NfServiceHid.this.command() == null) {
                return false;
            }
            return NfServiceHid.this.command().isHidConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public String getHidConnectedAddress() throws RemoteException {
            NfLog.v(NfServiceHid.this.TAG, "getHidConnectedAddress()");
            return NfServiceHid.this.command() == null ? NfDef.DEFAULT_ADDRESS : NfServiceHid.this.command().getHidConnectedAddress();
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean reqHidConnect(String address) throws RemoteException {
            NfLog.v(NfServiceHid.this.TAG, "reqHidConnect() " + address);
            if (NfServiceHid.this.command() == null) {
                return false;
            }
            return NfServiceHid.this.command().reqHidConnect(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean reqHidDisconnect(String address) throws RemoteException {
            NfLog.v(NfServiceHid.this.TAG, "reqHidDisconnect() " + address);
            if (NfServiceHid.this.command() == null) {
                return false;
            }
            return NfServiceHid.this.command().reqHidDisconnect(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean reqSendHidMouseCommand(int button, int offset_x, int offset_y, int wheel) throws RemoteException {
            NfLog.v(NfServiceHid.this.TAG, "reqSendHidMouseCommand - button: " + button + ", offset_x: " + offset_x + ", offset_y: " + offset_y + ", wheel: " + wheel);
            if (NfServiceHid.this.command() == null) {
                return false;
            }
            return NfServiceHid.this.command().reqSendHidMouseCommand(button, offset_x, offset_y, wheel);
        }

        @Override // com.nforetek.bt.aidl.INfCommandHid
        public boolean reqSendHidVirtualKeyCommand(int key_1, int key_2) throws RemoteException {
            NfLog.v(NfServiceHid.this.TAG, "reqSendHidMouseCommand - key_1: " + key_1 + ", key_2: " + key_2);
            if (NfServiceHid.this.command() == null) {
                return false;
            }
            return NfServiceHid.this.command().reqSendHidVirtualKeyCommand(key_1, key_2);
        }
    };

    @Override // com.nforetek.bt.service.NfService
    protected String getLogTag() {
        return "NfServiceHid";
    }

    @Override // com.nforetek.bt.service.NfService
    public boolean isEnableService() {
        return NfConfig.isEnableHid();
    }

    @Override // com.nforetek.bt.service.NfService
    IBinder getBinder() {
        Method[] declaredMethods;
        NfLog.e(this.TAG, "getBinder");
        Class c = this.mBinder.getClass();
        for (Method method : c.getDeclaredMethods()) {
            NfLog.e(this.TAG, "Method name: " + method.getName());
        }
        NfLog.e(this.TAG, "IBinder service: " + this.mBinder.hashCode());
        return this.mBinder;
    }

    @Override // com.nforetek.bt.service.NfService
    protected void onNfServiceConnected(IInterface cb) throws RemoteException {
        command().registerHidCallback((INfCallbackHid) cb);
    }
}
