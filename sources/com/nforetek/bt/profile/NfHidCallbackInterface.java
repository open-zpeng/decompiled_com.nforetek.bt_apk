package com.nforetek.bt.profile;

import com.nforetek.bt.manager.NfCallbackInterface;
/* loaded from: classes.dex */
public interface NfHidCallbackInterface extends NfCallbackInterface {
    void onHidStateChanged(String str, int i, int i2, int i3);
}
