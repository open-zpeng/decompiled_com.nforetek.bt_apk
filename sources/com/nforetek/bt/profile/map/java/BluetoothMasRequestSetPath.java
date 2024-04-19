package com.nforetek.bt.profile.map.java;

import com.nforetek.bt.res.obex.ClientSession;
import com.nforetek.bt.res.obex.HeaderSet;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BluetoothMasRequestSetPath extends BluetoothMasRequest {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMasRequestSetPath$SetPathDir;
    SetPathDir mDir;
    String mName;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum SetPathDir {
        ROOT,
        UP,
        DOWN;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static SetPathDir[] valuesCustom() {
            SetPathDir[] valuesCustom = values();
            int length = valuesCustom.length;
            SetPathDir[] setPathDirArr = new SetPathDir[length];
            System.arraycopy(valuesCustom, 0, setPathDirArr, 0, length);
            return setPathDirArr;
        }
    }

    static /* synthetic */ int[] $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMasRequestSetPath$SetPathDir() {
        int[] iArr = $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMasRequestSetPath$SetPathDir;
        if (iArr == null) {
            iArr = new int[SetPathDir.valuesCustom().length];
            try {
                iArr[SetPathDir.DOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SetPathDir.ROOT.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SetPathDir.UP.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMasRequestSetPath$SetPathDir = iArr;
        }
        return iArr;
    }

    public BluetoothMasRequestSetPath(String name) {
        this.mDir = SetPathDir.DOWN;
        this.mName = name;
        this.mHeaderSet.setHeader(1, name);
    }

    public BluetoothMasRequestSetPath(boolean goRoot) {
        this.mHeaderSet.setEmptyNameHeader();
        if (goRoot) {
            this.mDir = SetPathDir.ROOT;
        } else {
            this.mDir = SetPathDir.UP;
        }
    }

    @Override // com.nforetek.bt.profile.map.java.BluetoothMasRequest
    public void execute(ClientSession session) {
        HeaderSet hs = null;
        try {
            switch ($SWITCH_TABLE$com$nforetek$bt$profile$map$java$BluetoothMasRequestSetPath$SetPathDir()[this.mDir.ordinal()]) {
                case 1:
                case 3:
                    hs = session.setPath(this.mHeaderSet, false, false);
                    break;
                case 2:
                    hs = session.setPath(this.mHeaderSet, true, false);
                    break;
            }
            this.mResponseCode = hs.getResponseCode();
        } catch (IOException e) {
            this.mResponseCode = 208;
        }
    }
}
