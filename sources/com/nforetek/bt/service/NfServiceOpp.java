package com.nforetek.bt.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackOpp;
import com.nforetek.bt.aidl.INfCommandOpp;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class NfServiceOpp extends NfService {
    private INfCommandOpp.Stub mBinder = new INfCommandOpp.Stub() { // from class: com.nforetek.bt.service.NfServiceOpp.1
        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean isOppServiceReady() throws RemoteException {
            NfLog.v(NfServiceOpp.this.TAG, "isOppServiceReady()");
            return NfServiceOpp.this.isNfServiceConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean registerOppCallback(INfCallbackOpp cb) throws RemoteException {
            NfLog.v(NfServiceOpp.this.TAG, "registerOppCallback()");
            if (NfServiceOpp.this.command() == null) {
                NfServiceOpp.this.addPendingCallback(cb);
                return true;
            }
            return NfServiceOpp.this.command().registerOppCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean unregisterOppCallback(INfCallbackOpp cb) throws RemoteException {
            NfLog.v(NfServiceOpp.this.TAG, "unregisterOppCallback()");
            if (NfServiceOpp.this.command() == null) {
                return false;
            }
            return NfServiceOpp.this.command().unregisterOppCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean setOppFilePath(String path) throws RemoteException {
            NfLog.v(NfServiceOpp.this.TAG, "setOppFilePath()");
            if (NfServiceOpp.this.command() == null) {
                return false;
            }
            return NfServiceOpp.this.command().setOppFilePath(path);
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public String getOppFilePath() throws RemoteException {
            NfLog.v(NfServiceOpp.this.TAG, "getOppFilePath()");
            return NfServiceOpp.this.command() == null ? "" : NfServiceOpp.this.command().getOppFilePath();
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean reqOppAcceptReceiveFile(boolean accept) throws RemoteException {
            NfLog.v(NfServiceOpp.this.TAG, "reqOppAcceptReceiveFile()");
            if (NfServiceOpp.this.command() == null) {
                return false;
            }
            return NfServiceOpp.this.command().reqOppAcceptReceiveFile(accept);
        }

        @Override // com.nforetek.bt.aidl.INfCommandOpp
        public boolean reqOppInterruptReceiveFile() throws RemoteException {
            NfLog.v(NfServiceOpp.this.TAG, "reqOppInterruptReceiveFile()");
            if (NfServiceOpp.this.command() == null) {
                return false;
            }
            return NfServiceOpp.this.command().reqOppInterruptReceiveFile();
        }
    };

    @Override // com.nforetek.bt.service.NfService
    protected String getLogTag() {
        return "NfServiceOpp";
    }

    @Override // com.nforetek.bt.service.NfService
    public boolean isEnableService() {
        return NfConfig.isEnableOpp();
    }

    @Override // com.nforetek.bt.service.NfService
    IBinder getBinder() {
        return this.mBinder;
    }

    @Override // com.nforetek.bt.service.NfService
    protected void onNfServiceConnected(IInterface cb) throws RemoteException {
        command().registerOppCallback((INfCallbackOpp) cb);
    }
}
