package com.nforetek.bt.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.ParcelUuid;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackGattServer;
import com.nforetek.bt.aidl.INfCommandGattServer;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
import java.util.List;
/* loaded from: classes.dex */
public class NfServiceGattServer extends NfService {
    private INfCommandGattServer.Stub mBinder = new INfCommandGattServer.Stub() { // from class: com.nforetek.bt.service.NfServiceGattServer.1
        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean isGattServiceReady() throws RemoteException {
            return NfServiceGattServer.this.isNfServiceConnected();
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean registerGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "registerGattServerCallback()");
            if (NfServiceGattServer.this.command() == null) {
                NfServiceGattServer.this.addPendingCallback(cb);
                return true;
            }
            return NfServiceGattServer.this.command().registerGattServerCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean unregisterGattServerCallback(INfCallbackGattServer cb) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "unregisterGattServerCallback()");
            if (NfServiceGattServer.this.command() == null) {
                return false;
            }
            return NfServiceGattServer.this.command().unregisterGattServerCallback(cb);
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public int getGattServerConnectionState() throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "getGattServerConnectionState()");
            if (NfServiceGattServer.this.command() == null) {
                return -1;
            }
            return NfServiceGattServer.this.command().getGattServerConnectionState();
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerListen(boolean listen) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "reqGattServerListen() " + listen);
            if (NfServiceGattServer.this.command() == null) {
                return false;
            }
            return NfServiceGattServer.this.command().reqGattServerListen(listen);
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerBeginServiceDeclaration(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "reqGattServerBeginServiceDeclaration()");
            if (NfServiceGattServer.this.command() == null) {
                return false;
            }
            return NfServiceGattServer.this.command().reqGattServerBeginServiceDeclaration(srvcType, srvcUuid);
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerAddCharacteristic(ParcelUuid charUuid, int properties, int permissions) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "reqGattServerAddCharacteristic()");
            if (NfServiceGattServer.this.command() == null) {
                return false;
            }
            return NfServiceGattServer.this.command().reqGattServerAddCharacteristic(charUuid, properties, permissions);
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerAddDescriptor(ParcelUuid descUuid, int permissions) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "reqGattServerAddDescriptor()");
            if (NfServiceGattServer.this.command() == null) {
                return false;
            }
            return NfServiceGattServer.this.command().reqGattServerAddDescriptor(descUuid, permissions);
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerEndServiceDeclaration() throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "reqGattServerEndServiceDeclaration()");
            if (NfServiceGattServer.this.command() == null) {
                return false;
            }
            return NfServiceGattServer.this.command().reqGattServerEndServiceDeclaration();
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerRemoveService(int srvcType, ParcelUuid srvcUuid) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "reqGattServerRemoveService()");
            if (NfServiceGattServer.this.command() == null) {
                return false;
            }
            return NfServiceGattServer.this.command().reqGattServerRemoveService(srvcType, srvcUuid);
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerClearServices() throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "reqGattServerClearServices()");
            if (NfServiceGattServer.this.command() == null) {
                return false;
            }
            return NfServiceGattServer.this.command().reqGattServerClearServices();
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerSendResponse(String address, int requestId, int status, int offset, byte[] value) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "reqGattServerSendResponse()");
            if (NfServiceGattServer.this.command() == null) {
                return false;
            }
            return NfServiceGattServer.this.command().reqGattServerSendResponse(address, requestId, status, offset, value);
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerSendNotification(String address, int srvcType, ParcelUuid srvcUuid, ParcelUuid charUuid, boolean confirm, byte[] value) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "reqGattServerSendNotification()");
            if (NfServiceGattServer.this.command() == null) {
                return false;
            }
            return NfServiceGattServer.this.command().reqGattServerSendNotification(address, srvcType, srvcUuid, charUuid, confirm, value);
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public boolean reqGattServerDisconnect(String address) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "reqGattServerDisconnect() " + address);
            return NfServiceGattServer.this.command().reqGattServerDisconnect(address);
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public List<ParcelUuid> getGattAddedGattServiceUuidList() throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "getGattAddedGattServiceUuidList()");
            return NfServiceGattServer.this.command().getGattAddedGattServiceUuidList();
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public List<ParcelUuid> getGattAddedGattCharacteristicUuidList(ParcelUuid srvcUuid) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "getGattAddedGattCharacteristicUuidList()");
            return NfServiceGattServer.this.command().getGattAddedGattCharacteristicUuidList(srvcUuid);
        }

        @Override // com.nforetek.bt.aidl.INfCommandGattServer
        public List<ParcelUuid> getGattAddedGattDescriptorUuidList(ParcelUuid srvcUuid, ParcelUuid charUuid) throws RemoteException {
            NfLog.v(NfServiceGattServer.this.TAG, "getGattAddedGattDescriptorUuidList()");
            return NfServiceGattServer.this.command().getGattAddedGattDescriptorUuidList(srvcUuid, charUuid);
        }
    };

    @Override // com.nforetek.bt.service.NfService
    protected String getLogTag() {
        return "NfServiceGattServer";
    }

    @Override // com.nforetek.bt.service.NfService
    public boolean isEnableService() {
        return NfConfig.isEnableGattServer();
    }

    @Override // com.nforetek.bt.service.NfService
    IBinder getBinder() {
        return this.mBinder;
    }

    @Override // com.nforetek.bt.service.NfService
    protected void onNfServiceConnected(IInterface cb) throws RemoteException {
        command().registerGattServerCallback((INfCallbackGattServer) cb);
    }
}
