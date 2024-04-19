package com.nforetek.bt.profile.opp;

import android.os.Environment;
import android.os.Message;
import com.nforetek.bt.aidl.INfCallbackOpp;
import com.nforetek.bt.callback.NfDoCallbackOpp;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.jni.NfJniBundle;
import com.nforetek.bt.jni.NfJniCallbackInterfaceOpp;
import com.nforetek.bt.profile.NfOppCallbackInterface;
import com.nforetek.bt.profile._NfProfile;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfPreference;
import com.nforetek.util.normal.NfLog;
import java.io.File;
/* loaded from: classes.dex */
public final class _NfOpp extends _NfProfile<INfCallbackOpp, NfDoCallbackOpp, NfOppCallbackInterface> implements NfJniCallbackInterfaceOpp {
    private static final int BTOPP_STATE_CONNECTED = 1;
    private static final int BTOPP_STATE_LISTENING = 0;
    private static final int BTOPP_STATE_UPLOADING = 2;
    private String mFileName = "";
    boolean isCancelDownloadLastTime = false;

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfOpp";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return 8;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.profile._NfProfile
    public void onCreate() {
        super.onCreate();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
        super.forceResetState();
    }

    public void forceCallbackDisconnected() {
        if (getProfileState() == 140) {
            reqOppAcceptReceiveFile(false);
        } else if (getProfileState() == 170) {
            reqOppInterruptReceiveFile();
        }
    }

    public boolean setOppFilePath(String path) {
        boolean result = false;
        NfLog.d(this.TAG, "setOppFilePath(): " + path);
        if (getProfileState() != 110) {
            NfLog.e(this.TAG, "It is not ready state ! Reject !");
        } else if (path != null && path.length() >= 2 && path.charAt(0) == '/' && (result = jni().setOppFilePath(path))) {
            NfPreference.setOppPath(path);
        }
        return result;
    }

    public String getOppFilePath() {
        return jni().getOppFilePath();
    }

    public boolean reqOppAcceptReceiveFile(boolean accept) {
        if (getProfileState() == 170) {
            NfLog.e(this.TAG, "Uploading state ! Reject !");
            return false;
        }
        return jni().acceptOppReceiveFile(accept);
    }

    public boolean reqOppInterruptReceiveFile() {
        if (getProfileState() == 170) {
            return jni().interruptOppReceiveFile();
        }
        NfLog.e(this.TAG, "It's not Uploading state ! Reject !");
        return false;
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceOpp
    public void onJniOppStateChanged(String address, int prestate, int currentstate, int reason) {
        NfLog.d(this.TAG, "onJniOppStateChanged() " + address + " prestate: " + prestate + " currentstate: " + currentstate + " reason: " + reason);
        String sPath = String.valueOf(Environment.getExternalStorageDirectory().getPath()) + getOppFilePath() + "/" + this.mFileName;
        if (2 == reason) {
            NfLog.d(this.TAG, "sPath " + sPath);
            File file = new File(sPath);
            if (file.exists()) {
                NfLog.d(this.TAG, "delete file Path " + sPath);
                file.delete();
            } else {
                NfLog.d(this.TAG, "There is no file");
            }
        }
        setState(address, getNfDefState(prestate), getNfDefState(currentstate), reason);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceOpp
    public void onJniOppReceiveFileInfo(String fileName, int fileSize, String devName, String savePath) {
        this.mFileName = fileName;
        NfLog.d(this.TAG, "onJniOppReceiveFileInfo() fileName" + fileName + " fileSize: " + fileSize + " devName: " + devName + " savePath: " + savePath);
        if (!this.isCancelDownloadLastTime) {
            callback().onOppReceiveFileInfo(fileName, fileSize, devName, savePath);
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceOpp
    public void onJniOppReceiveProgress(int receiveOffset) {
        NfLog.d(this.TAG, "onJniOppReceiveProgress() receiveOffset" + receiveOffset);
        callback().onOppReceiveProgress(receiveOffset);
    }

    private static int getNfDefState(int state) {
        switch (state) {
            case 0:
                return 110;
            case 1:
                return 140;
            case 2:
                return NfDef.STATE_UPLOADING;
            default:
                return 100;
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void dequeueMessage(Message msg) {
        NfJniBundle b = (NfJniBundle) msg.obj;
        switch (msg.what) {
            case NfJni.onJniOppStateChanged /* 601 */:
                NfLog.v(this.TAG, "onJniOppStateChanged()");
                onJniOppStateChanged(b.getString1(), b.getInt1(), b.getInt2(), b.getInt3());
                return;
            case NfJni.onJniOppReceiveFileInfo /* 602 */:
                NfLog.v(this.TAG, "onJniOppReceiveFileInfo()");
                onJniOppReceiveFileInfo(b.getString1(), b.getInt1(), b.getString2(), b.getString3());
                return;
            case NfJni.onJniOppReceiveProgress /* 603 */:
                NfLog.v(this.TAG, "onJniOppReceiveProgress()");
                onJniOppReceiveProgress(b.getInt1());
                return;
            default:
                return;
        }
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
        callback().onOppStateChanged(address, prevState, state, reason);
    }
}
