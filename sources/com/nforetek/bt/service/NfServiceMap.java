package com.nforetek.bt.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackMap;
import com.nforetek.bt.aidl.INfCommandMap;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public class NfServiceMap extends NfService {
    private INfCommandMap.Stub mBinder = new INfCommandMap.Stub() { // from class: com.nforetek.bt.service.NfServiceMap.1
        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean isMapServiceReady() throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "isMapServiceReady()");
            return NfServiceMap.this.isNfServiceConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean registerMapCallback(INfCallbackMap cb) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "registerMapCallback()");
            if (NfServiceMap.this.command() == null) {
                NfServiceMap.this.addPendingCallback(cb);
                return true;
            }
            return NfServiceMap.this.command().registerMapCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean unregisterMapCallback(INfCallbackMap cb) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "unregisterMapCallback");
            if (NfServiceMap.this.command() == null) {
                return false;
            }
            return NfServiceMap.this.command().unregisterMapCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapDownloadSingleMessage(String address, int folder, String handle, int storage) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "reqMapDownloadSingleMessage() " + address);
            if (NfServiceMap.this.command() == null) {
                return false;
            }
            return NfServiceMap.this.command().reqMapDownloadSingleMessage(address, folder, handle, storage);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapDownloadMessage(String address, int folder, boolean isContentDownload, int count, int startPos, int storage, String periodBegin, String periodEnd, String sender, String recipient, int readStatus, int type) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "reqMapDownloadMessage() " + address);
            if (NfServiceMap.this.command() == null) {
                return false;
            }
            return NfServiceMap.this.command().reqMapDownloadMessage(address, folder, isContentDownload, count, startPos, storage, periodBegin, periodEnd, sender, recipient, readStatus, type);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapRegisterNotification(String address, boolean downloadNewMessage) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "reqMapRegisterNotification() " + address);
            if (NfServiceMap.this.command() == null) {
                return false;
            }
            return NfServiceMap.this.command().reqMapRegisterNotification(address, downloadNewMessage);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public void reqMapUnregisterNotification(String address) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "reqMapUnregisterNotification() " + address);
            if (NfServiceMap.this.command() != null) {
                NfServiceMap.this.command().reqMapUnregisterNotification(address);
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean isMapNotificationRegistered(String address) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "isMapNotificationRegistered() " + address);
            if (NfServiceMap.this.command() == null) {
                return false;
            }
            return NfServiceMap.this.command().isMapNotificationRegistered(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapDownloadInterrupt(String address) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "reqMapDownloadInterrupt() " + address);
            if (NfServiceMap.this.command() == null) {
                return false;
            }
            return NfServiceMap.this.command().reqMapDownloadInterrupt(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public void reqMapDatabaseAvailable() throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "reqMapDatabaseAvailable()");
            if (NfServiceMap.this.command() != null) {
                NfServiceMap.this.command().reqMapDatabaseAvailable();
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public void reqMapDeleteDatabaseByAddress(String address) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "reqMapDeleteDatabaseByAddress() " + address);
            if (NfServiceMap.this.command() != null) {
                NfServiceMap.this.command().reqMapDeleteDatabaseByAddress(address);
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public void reqMapCleanDatabase() throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "reqMapCleanDatabase()");
            if (NfServiceMap.this.command() != null) {
                NfServiceMap.this.command().reqMapCleanDatabase();
            }
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public int getMapCurrentState(String address) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "getMapCurrentState() " + address);
            if (NfServiceMap.this.command() == null) {
                return 100;
            }
            return NfServiceMap.this.command().getMapCurrentState(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public int getMapRegisterState(String address) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "getMapRegisterState() " + address);
            if (NfServiceMap.this.command() == null) {
                return 100;
            }
            return NfServiceMap.this.command().getMapRegisterState(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapSendMessage(String address, String message, String target) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "reqMapSendMessage() " + address);
            if (NfServiceMap.this.command() == null) {
                return false;
            }
            return NfServiceMap.this.command().reqMapSendMessage(address, message, target);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapDeleteMessage(String address, int folder, String handle) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "reqMapDeleteMessage() " + address);
            if (NfServiceMap.this.command() == null) {
                return false;
            }
            return NfServiceMap.this.command().reqMapDeleteMessage(address, folder, handle);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean reqMapChangeReadStatus(String address, int folder, String handle, boolean isReadStatus) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "reqMapChangeReadStatus() " + address);
            if (NfServiceMap.this.command() == null) {
                return false;
            }
            return NfServiceMap.this.command().reqMapChangeReadStatus(address, folder, handle, isReadStatus);
        }

        @Override // com.nforetek.bt.aidl.INfCommandMap
        public boolean setMapDownloadNotify(int frequency) throws RemoteException {
            NfLog.v(NfServiceMap.this.TAG, "setMapDownloadNotify() " + frequency);
            if (NfServiceMap.this.command() == null) {
                return false;
            }
            return NfServiceMap.this.command().setMapDownloadNotify(frequency);
        }
    };

    @Override // com.nforetek.bt.service.NfService
    protected String getLogTag() {
        return "NfServiceMap";
    }

    @Override // com.nforetek.bt.service.NfService
    public boolean isEnableService() {
        return NfConfig.isEnableMap();
    }

    @Override // com.nforetek.bt.service.NfService
    IBinder getBinder() {
        return this.mBinder;
    }

    @Override // com.nforetek.bt.service.NfService
    protected void onNfServiceConnected(IInterface cb) throws RemoteException {
        command().registerMapCallback((INfCallbackMap) cb);
    }
}
