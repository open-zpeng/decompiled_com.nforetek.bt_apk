package com.nforetek.bt.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackPbap;
import com.nforetek.bt.aidl.INfCommandPbap;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class NfServicePbap extends NfService {
    private INfCommandPbap.Stub mBinder = new INfCommandPbap.Stub() { // from class: com.nforetek.bt.service.NfServicePbap.1
        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean isPbapServiceReady() throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "isPbapServiceReady()");
            return NfServicePbap.this.isNfServiceConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean registerPbapCallback(INfCallbackPbap cb) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "registerPbapCallback()");
            if (NfServicePbap.this.command() == null) {
                NfServicePbap.this.addPendingCallback(cb);
                return true;
            }
            return NfServicePbap.this.command().registerPbapCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean unregisterPbapCallback(INfCallbackPbap cb) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "unregisterPbapCallback()");
            if (NfServicePbap.this.command() == null) {
                return false;
            }
            return NfServicePbap.this.command().unregisterPbapCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public int getPbapConnectionState() throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "getPbapConnectionState()");
            if (NfServicePbap.this.command() == null) {
                return 100;
            }
            return NfServicePbap.this.command().getPbapConnectionState();
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean isPbapDownloading() throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "isPbapDownloading()");
            if (NfServicePbap.this.command() == null) {
                return false;
            }
            return NfServicePbap.this.command().isPbapDownloading();
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public String getPbapDownloadingAddress() throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "getPbapDownloadingAddress()");
            return NfServicePbap.this.command() == null ? NfDef.DEFAULT_ADDRESS : NfServicePbap.this.command().getPbapDownloadingAddress();
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownload(String address, int storage, int property) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapDownload() " + address + " storage: " + storage + " property: " + property);
            if (NfServicePbap.this.command() == null) {
                return false;
            }
            return NfServicePbap.this.command().reqPbapDownload(address, storage, property);
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadRange(String address, int storage, int property, int startPos, int offset) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapDownloadRange() " + address + " storage: " + storage + " property: " + property + " startPos: " + startPos + " offset: " + offset);
            if (NfServicePbap.this.command() == null) {
                return false;
            }
            return NfServicePbap.this.command().reqPbapDownloadRange(address, storage, property, startPos, offset);
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadToDatabase(String address, int storage, int property) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapDownloadToDatabase() " + address + " storage: " + storage + " property: " + property);
            if (NfServicePbap.this.command() == null) {
                return false;
            }
            return NfServicePbap.this.command().reqPbapDownloadToDatabase(address, storage, property);
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadRangeToDatabase(String address, int storage, int property, int startPos, int offset) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapDownloadRangeToContactsProvider() " + address + " storage: " + storage + " property: " + property + "(" + startPos + "->" + offset);
            if (NfServicePbap.this.command() == null) {
                return false;
            }
            return NfServicePbap.this.command().reqPbapDownloadRangeToDatabase(address, storage, property, startPos, offset);
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadToContactsProvider(String address, int storage, int property) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapDownloadToContactsProvider() " + address + " storage: " + storage + " property: " + property);
            if (NfServicePbap.this.command() == null) {
                return false;
            }
            return NfServicePbap.this.command().reqPbapDownloadToContactsProvider(address, storage, property);
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadRangeToContactsProvider(String address, int storage, int property, int startPos, int offset) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapDownloadRangeToContactsProvider() " + address + " storage: " + storage + " property: " + property + "(" + startPos + "->" + offset);
            if (NfServicePbap.this.command() == null) {
                return false;
            }
            return NfServicePbap.this.command().reqPbapDownloadRangeToContactsProvider(address, storage, property, startPos, offset);
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public void reqPbapDatabaseQueryNameByNumber(String address, String target) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapDatabaseQueryNameByNumber() " + address + " target: " + target);
            if (NfServicePbap.this.command() != null) {
                NfServicePbap.this.command().reqPbapDatabaseQueryNameByNumber(address, target);
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public void reqPbapDatabaseQueryNameByPartialNumber(String address, String target, int findPosition) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapDatabaseQueryNameByPartialNumber() " + address + " target: " + target + " findPosition: " + findPosition);
            if (NfServicePbap.this.command() != null) {
                NfServicePbap.this.command().reqPbapDatabaseQueryNameByPartialNumber(address, target, findPosition);
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public void reqPbapDatabaseAvailable(String address) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapDatabaseAvailable() " + address);
            if (NfServicePbap.this.command() != null) {
                NfServicePbap.this.command().reqPbapDatabaseAvailable(address);
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public void reqPbapDeleteDatabaseByAddress(String address) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapDeleteDatabaseByAddress() " + address);
            if (NfServicePbap.this.command() != null) {
                NfServicePbap.this.command().reqPbapDeleteDatabaseByAddress(address);
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public void reqPbapCleanDatabase() throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapCleanDatabase()");
            if (NfServicePbap.this.command() != null) {
                NfServicePbap.this.command().reqPbapCleanDatabase();
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean reqPbapDownloadInterrupt(String address) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "reqPbapDownloadInterrupt() " + address);
            if (NfServicePbap.this.command() == null) {
                return false;
            }
            return NfServicePbap.this.command().reqPbapDownloadInterrupt(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandPbap
        public boolean setPbapDownloadNotify(int frequency) throws RemoteException {
            NfLog.v(NfServicePbap.this.TAG, "setPbapDownloadNotify() " + frequency);
            if (NfServicePbap.this.command() == null) {
                return false;
            }
            NfServicePbap.this.command().setPbapDownloadNotify(frequency);
            return true;
        }
    };

    @Override // com.nforetek.bt.service.NfService
    protected String getLogTag() {
        return "NfServicePbap";
    }

    @Override // com.nforetek.bt.service.NfService
    public boolean isEnableService() {
        return NfConfig.isEnablePbap();
    }

    @Override // com.nforetek.bt.service.NfService
    IBinder getBinder() {
        return this.mBinder;
    }

    @Override // com.nforetek.bt.service.NfService
    protected void onNfServiceConnected(IInterface cb) throws RemoteException {
        command().registerPbapCallback((INfCallbackPbap) cb);
    }
}
