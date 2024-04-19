package com.nforetek.bt.profile.a2dp;

import android.os.Message;
import com.nforetek.bt.aidl.INfCallbackA2dp;
import com.nforetek.bt.callback.NfDoCallbackA2dp;
import com.nforetek.bt.jni.NfJniBundle;
import com.nforetek.bt.jni.NfJniCallbackInterfaceA2dp;
import com.nforetek.bt.profile.NfA2dpCallbackInterface;
import com.nforetek.bt.profile._NfProfile;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import com.nforetek.util.normal.NfUtils;
/* loaded from: classes.dex */
public final class _NfA2dp extends _NfProfile<INfCallbackA2dp, NfDoCallbackA2dp, NfA2dpCallbackInterface> implements NfJniCallbackInterfaceA2dp {
    private static final int BTAK_STATE_CONNECTED = 3;
    private static final int BTAK_STATE_CONNECTING = 1;
    private static final int BTAK_STATE_DISCONNECTED = 0;
    private static final int BTAK_STATE_DISCONNECTING = 2;
    private static final int BTAK_STATE_STREAMING = 4;
    private static final int PAUSE_RENDER = 1;
    private static final int START_RENDER = 2;
    private int mStreamType = 3;
    private int mAvrcpState = 110;

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfA2dp";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return 2;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
        NfLog.i(this.TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onCreate() {
        NfLog.i(this.TAG, "onCreate");
        super.onCreate();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
        super.forceResetState();
    }

    public boolean connect(String address) {
        NfLog.v(this.TAG, "connect() " + address);
        if (NfPrimitive.isAddressValid(address)) {
            if (getProfileState() != 110) {
                NfLog.e(this.TAG, "State is " + getProfileState() + ". Reject connect command.");
                return false;
            } else if (jni() == null) {
                NfLog.e(this.TAG, "jni interface is null. return here.");
                return false;
            } else {
                if (NfPrimitive.isDiscovering()) {
                    NfLog.e(this.TAG, "Before connect, stop scan");
                    NfPrimitive.startScan(false);
                    try {
                        Thread.sleep(50L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!NfPrimitive.isAddressValid(address) || address.equals(NfDef.DEFAULT_ADDRESS)) {
                    NfLog.e(this.TAG, "address is not valid. Reject connect command. address = " + address);
                    return false;
                }
                return jni().reqA2dpConnect(address);
            }
        }
        return false;
    }

    public boolean disconnect(String address) {
        NfLog.v(this.TAG, "disconnect() " + address);
        if (NfPrimitive.isAddressValid(address)) {
            if (getProfileState() <= 110 || getProfileState() == 125) {
                NfLog.e(this.TAG, "State is " + getProfileState() + ". Reject disconnect command.");
                return false;
            } else if (jni() == null) {
                NfLog.e(this.TAG, "jni interface is null. return here.");
                return false;
            } else {
                return jni().reqA2dpDisconnect(address);
            }
        }
        return false;
    }

    public void pauseRender() {
        NfLog.v(this.TAG, "pauseRender()");
        sendCommandMessage(1);
    }

    public void startRender() {
        NfLog.v(this.TAG, "startRender()");
        sendCommandMessage(2);
    }

    public boolean setLocalVolume(float vol) {
        NfLog.v(this.TAG, "setLocalVolume() " + vol);
        return jni().setA2dpLocalVolume(vol);
    }

    public boolean setStreamType(int type) {
        NfLog.v(this.TAG, "setStreamType() " + type);
        if (jni().setA2dpStreamType(type)) {
            this.mStreamType = type;
            return true;
        }
        return false;
    }

    public int getStreamType() {
        NfLog.v(this.TAG, "getStreamType()");
        return this.mStreamType;
    }

    public boolean isConnected() {
        return getProfileState() >= 140;
    }

    public boolean isConnecting() {
        return getProfileState() == 120;
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceA2dp
    public void onJniA2dpStateChanged(int newState, byte[] address) {
        String addr = NfUtils.getAddressStringFromByte(address);
        NfLog.i(this.TAG, "onJniA2dpStateChanged() " + addr + " state: " + newState);
        setState(addr, getNfDefState(newState));
        if (callback() == null) {
            NfLog.e(this.TAG, "Callback is null!!");
        }
    }

    public void onConnectionStateChanged(String address, int prevState, int newState) {
        NfLog.i(this.TAG, "onConnectionStateChanged() " + address + " state: " + newState);
        if (callback() == null) {
            NfLog.e(this.TAG, "Callback is null!!");
        } else {
            setState(address, prevState, newState);
        }
    }

    private static int getNfDefState(int state) {
        switch (state) {
            case 0:
                return 110;
            case 1:
                return 120;
            case 2:
                return NfDef.STATE_DISCONNECTING;
            case 3:
                return 140;
            case 4:
                return 150;
            default:
                return 100;
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void dequeueMessage(Message msg) {
        NfJniBundle b = (NfJniBundle) msg.obj;
        switch (msg.what) {
            case 0:
                NfLog.v(this.TAG, "onJniA2dpStateChanged() " + NfUtils.getAddressStringFromByte(b.getByteArray1()) + " state: " + b.getInt1());
                onJniA2dpStateChanged(b.getInt1(), b.getByteArray1());
                return;
            default:
                return;
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void dequeueCommandMessage(Message msg) {
        switch (msg.what) {
            case 1:
                jni().pauseA2dpRender();
                return;
            case 2:
                jni().startA2dpRender();
                return;
            default:
                return;
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
        manager().onA2dpStateChanged(address, prevState, state);
        callback().onA2dpStateChanged(address, prevState, state);
    }

    /* loaded from: classes.dex */
    private class CheckA2dpStateThread extends Thread {
        private String mAddress;

        public CheckA2dpStateThread(String address) {
            this.mAddress = address;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(2000L);
                NfLog.e(_NfA2dp.this.TAG, "Piggy check if A2dp state is disconnected after 2sec!");
                if (_NfA2dp.this.getProfileState() >= 140 && _NfA2dp.this.getAvrcpState() == 110) {
                    _NfA2dp.this.disconnect(this.mAddress);
                }
            } catch (Exception e) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getAvrcpState() {
        return this.mAvrcpState;
    }

    public void onAvrcpStateChanged(String address, int state) {
        NfLog.v(this.TAG, "onAvrcpStateChanged() " + address + " state: " + state);
        this.mAvrcpState = state;
        if (state == 110) {
            CheckA2dpStateThread t = new CheckA2dpStateThread(address);
            t.start();
        }
    }
}
