package com.nforetek.bt.profile.spp;
/* loaded from: classes.dex */
public class SppDoCommand {
    private SppDoCallBack mSppDoCallBack;
    private SppService mSppService;

    public void onDestroy() {
        this.mSppService = null;
        this.mSppDoCallBack = null;
    }

    public SppDoCommand(SppService sppService, SppDoCallBack sppDoCallBack) {
        this.mSppService = sppService;
        this.mSppDoCallBack = sppDoCallBack;
    }

    public boolean Connect(String address) {
        return this.mSppService.connectSppByThread(address);
    }

    public void Disconnect(String address) {
        this.mSppService.disconnect(address);
    }

    public void SendData(String address, byte[] sppData) {
        this.mSppService.sendData(address, sppData);
    }

    public boolean isConnected(String address) {
        return this.mSppService.isConnected(address);
    }

    public void getConnectedList() {
        this.mSppService.getConnectedList();
    }

    public String[] getConnectedAddressList() {
        return this.mSppService.getConnectedAddressList();
    }

    public boolean hasAnyConnectedConntetion() {
        return this.mSppService.hasAnyConnectedConntetion();
    }

    public void disconnectAllConnectedConntection() {
        this.mSppService.disconnectAllConnectedConntection();
    }
}
