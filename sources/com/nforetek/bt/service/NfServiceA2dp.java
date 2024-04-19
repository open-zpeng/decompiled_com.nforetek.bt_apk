package com.nforetek.bt.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackA2dp;
import com.nforetek.bt.aidl.INfCommandA2dp;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class NfServiceA2dp extends NfService {
    private INfCommandA2dp.Stub mBinder = new INfCommandA2dp.Stub() { // from class: com.nforetek.bt.service.NfServiceA2dp.1
        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean isA2dpServiceReady() throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "isA2dpServiceReady()");
            return NfServiceA2dp.this.isNfServiceConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean registerA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "registerA2dpCallback()");
            if (NfServiceA2dp.this.command() == null) {
                NfServiceA2dp.this.addPendingCallback(cb);
                return true;
            }
            return NfServiceA2dp.this.command().registerA2dpCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean unregisterA2dpCallback(INfCallbackA2dp cb) throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "unregisterA2dpCallback()");
            if (NfServiceA2dp.this.command() == null) {
                return false;
            }
            return NfServiceA2dp.this.command().unregisterA2dpCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public int getA2dpConnectionState() throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "getA2dpConnectionState()");
            if (NfServiceA2dp.this.command() == null) {
                return 100;
            }
            return NfServiceA2dp.this.command().getA2dpConnectionState();
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean isA2dpConnected() throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "isA2dpConnected()");
            if (NfServiceA2dp.this.command() == null) {
                return false;
            }
            return NfServiceA2dp.this.command().isA2dpConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public String getA2dpConnectedAddress() throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "getA2dpConnectedAddress()");
            return NfServiceA2dp.this.command() == null ? NfDef.DEFAULT_ADDRESS : NfServiceA2dp.this.command().getA2dpConnectedAddress();
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean reqA2dpConnect(String address) throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "reqA2dpConnect() " + address);
            if (NfServiceA2dp.this.command() == null) {
                return false;
            }
            return NfServiceA2dp.this.command().reqA2dpConnect(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean reqA2dpDisconnect(String address) throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "reqA2dpDisconnect() " + address);
            if (NfServiceA2dp.this.command() == null) {
                return false;
            }
            return NfServiceA2dp.this.command().reqA2dpDisconnect(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public void pauseA2dpRender() throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "pauseA2dpRender()");
            if (NfServiceA2dp.this.command() != null) {
                NfServiceA2dp.this.command().pauseA2dpRender();
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public void startA2dpRender() throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "startA2dpRender()");
            if (NfServiceA2dp.this.command() != null) {
                NfServiceA2dp.this.command().startA2dpRender();
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean setA2dpLocalVolume(float vol) throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "setA2dpLocalVolume() " + vol);
            if (NfServiceA2dp.this.command() == null) {
                return false;
            }
            return NfServiceA2dp.this.command().setA2dpLocalVolume(vol);
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public boolean setA2dpStreamType(int type) throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "setA2dpStreamType() " + type);
            if (NfServiceA2dp.this.command() == null) {
                return false;
            }
            return NfServiceA2dp.this.command().setA2dpStreamType(type);
        }

        @Override // com.nforetek.bt.aidl.INfCommandA2dp
        public int getA2dpStreamType() throws RemoteException {
            NfLog.v(NfServiceA2dp.this.TAG, "setA2dpLocalVolume()");
            if (NfServiceA2dp.this.command() == null) {
                return -1;
            }
            return NfServiceA2dp.this.command().getA2dpStreamType();
        }
    };

    @Override // com.nforetek.bt.service.NfService
    protected String getLogTag() {
        return "NfServiceA2dp";
    }

    @Override // com.nforetek.bt.service.NfService
    public boolean isEnableService() {
        return NfConfig.isEnableA2dp();
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
        command().registerA2dpCallback((INfCallbackA2dp) cb);
    }
}
