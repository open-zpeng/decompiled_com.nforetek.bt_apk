package com.nforetek.bt.profile.spp;

import android.os.Message;
import com.nforetek.bt.aidl.INfCallbackSpp;
import com.nforetek.bt.callback.NfDoCallbackSpp;
import com.nforetek.bt.profile.NfSppCallbackInterface;
import com.nforetek.bt.profile._NfProfile;
import com.nforetek.util.bt.NfPrimitive;
/* loaded from: classes.dex */
public final class _NfSpp extends _NfProfile<INfCallbackSpp, NfDoCallbackSpp, NfSppCallbackInterface> {
    private SppDoCallBack mOldCallback;
    private SppDoCommand mOldDoCommand;
    private SppService mOldSpp;

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfSpp";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return -1;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getMaxDeviceCount() {
        return 7;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
        if (this.mOldSpp != null) {
            this.mOldSpp.destroyClass();
            this.mOldSpp = null;
        }
        if (this.mOldCallback != null) {
            this.mOldCallback.onDestroy();
            this.mOldCallback = null;
        }
        if (this.mOldDoCommand != null) {
            this.mOldDoCommand.onDestroy();
            this.mOldDoCommand = null;
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.profile._NfProfile
    public void onCreate() {
        super.onCreate();
        this.mOldCallback = new SppDoCallBack(callback());
        this.mOldSpp = new SppService(context(), this.mOldCallback);
        this.mOldDoCommand = new SppDoCommand(this.mOldSpp, this.mOldCallback);
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
    }

    public boolean connect(String address) {
        if (NfPrimitive.isAddressValid(address) && this.mOldDoCommand != null) {
            return this.mOldDoCommand.Connect(address);
        }
        return false;
    }

    public boolean disconnect(String address) {
        if (NfPrimitive.isAddressValid(address) && this.mOldDoCommand != null && this.mOldDoCommand.isConnected(address)) {
            this.mOldDoCommand.Disconnect(address);
            return true;
        }
        return false;
    }

    public void getConnectedList() {
        if (this.mOldDoCommand != null) {
            this.mOldDoCommand.getConnectedList();
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
    }

    public boolean isConnected(String address) {
        if (NfPrimitive.isAddressValid(address) && this.mOldDoCommand != null) {
            return this.mOldDoCommand.isConnected(address);
        }
        return false;
    }

    public void sendData(String address, byte[] data) {
        if (NfPrimitive.isAddressValid(address) && this.mOldDoCommand != null) {
            this.mOldDoCommand.SendData(address, data);
        }
    }

    public boolean hasAnyConnectedConntetion() {
        if (this.mOldDoCommand == null) {
            return false;
        }
        return this.mOldDoCommand.hasAnyConnectedConntetion();
    }

    public void disconnectAllConnectedConntection() {
        if (this.mOldDoCommand != null) {
            this.mOldDoCommand.disconnectAllConnectedConntection();
            this.mOldDoCommand.hasAnyConnectedConntetion();
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void dequeueMessage(Message msg) {
    }
}
