package com.nforetek.bt.profile.hid;

import android.os.Message;
import com.nforetek.bt.aidl.INfCallbackHid;
import com.nforetek.bt.callback.NfDoCallbackHid;
import com.nforetek.bt.jni.NfJniBundle;
import com.nforetek.bt.jni.NfJniCallbackInterfaceHid;
import com.nforetek.bt.profile.NfHidCallbackInterface;
import com.nforetek.bt.profile._NfProfile;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.obex.ResponseCodes;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import com.nforetek.util.normal.NfUtils;
/* loaded from: classes.dex */
public final class _NfHid extends _NfProfile<INfCallbackHid, NfDoCallbackHid, NfHidCallbackInterface> implements NfJniCallbackInterfaceHid {
    private static final int BTAK_STATE_CONNECTED = 3;
    private static final int BTAK_STATE_CONNECTING = 1;
    private static final int BTAK_STATE_DISCONNECTED = 0;
    private static final int BTAK_STATE_DISCONNECTING = 2;
    private static final int BTAK_STATE_STREAMING = 4;
    private NfHidStateMachine mStateMachine;

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfHid";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return 4;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
        NfLog.i(this.TAG, "onDestroy()");
        this.mStateMachine.doQuit();
        super.onDestroy();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onCreate() {
        NfLog.i(this.TAG, "onCreate");
        super.onCreate();
        this.mStateMachine = NfHidStateMachine.make(this, jni());
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
        super.forceResetState();
    }

    public boolean connect(String address) {
        NfLog.v(this.TAG, "connect() " + address);
        if (NfPrimitive.isAddressValid(address)) {
            if (jni() == null) {
                NfLog.e(this.TAG, "jni interface is null. return here.");
                return false;
            }
            return jni().reqHidConnect(address);
        }
        return false;
    }

    public boolean disconnect(String address) {
        NfLog.v(this.TAG, "disconnect() " + address);
        if (NfPrimitive.isAddressValid(address)) {
            if (jni() == null) {
                NfLog.e(this.TAG, "jni interface is null. return here.");
                return false;
            }
            return jni().reqHidDisconnect(address);
        }
        return false;
    }

    public boolean reqSendHidMouseCommand(int button, int offset_x, int offset_y, int wheel) {
        NfLog.v(this.TAG, "reqSendHidMouseCommand - button: " + button + ", offset_x: " + offset_x + ", offset_y: " + offset_y + ", wheel: " + wheel);
        String data = String.format("%02x%02x%02x%02x%02x%02x%02x%02x", Integer.valueOf((int) ResponseCodes.OBEX_HTTP_CREATED), 2, Integer.valueOf(offset_x & 255), Integer.valueOf((offset_x >> 8) & 255), Integer.valueOf(offset_y & 255), Integer.valueOf((offset_y >> 8) & 255), Integer.valueOf(wheel & 255), Integer.valueOf(button & 255));
        return jni().reqSendHidMouseCommand(getConnectedAddress(), data);
    }

    public boolean reqSendHidVirtualKeyCommand(int key_1, int key_2) {
        NfLog.v(this.TAG, "reqSendHidMouseCommand - key_1: " + key_1 + ", key_2: " + key_2);
        String data = String.format("%02x%02x%02x%02x%02x%02x", Integer.valueOf((int) ResponseCodes.OBEX_HTTP_CREATED), 3, Integer.valueOf(key_1 & 255), Integer.valueOf((key_1 >> 8) & 255), Integer.valueOf(key_2 & 255), Integer.valueOf((key_2 >> 8) & 255));
        return jni().reqSendHidVirtualKeyCommand(getConnectedAddress(), data);
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
        callback().onHidStateChanged(address, prevState, state, reason);
    }

    public boolean isConnected() {
        return getProfileState() >= 140;
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceHid
    public void onJniHidStateChanged(byte[] address, int newState, int reason) {
        String addr = NfUtils.getAddressStringFromByte(address);
        NfLog.i(this.TAG, "onJniHidStateChanged() " + addr + " state: " + newState);
        setState(addr, getNfDefState(newState), reason);
        if (callback() == null) {
            NfLog.e(this.TAG, "Callback is null!!");
        }
    }

    public void onConnectionStateChanged(String address, int prevState, int newState, int reason) {
        if (callback() == null) {
            NfLog.e(this.TAG, "Callback is null!!");
        } else {
            callback().onHidStateChanged(address, prevState, newState, reason);
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
            case 101:
                NfLog.v(this.TAG, "onJniHidStateChanged() " + NfUtils.getAddressStringFromByte(b.getByteArray1()) + " ,state: " + b.getInt1() + " ,reason: " + b.getInt2());
                onJniHidStateChanged(b.getByteArray1(), b.getInt1(), b.getInt2());
                return;
            default:
                return;
        }
    }
}
