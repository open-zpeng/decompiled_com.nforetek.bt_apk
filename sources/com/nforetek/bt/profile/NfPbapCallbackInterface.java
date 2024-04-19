package com.nforetek.bt.profile;

import com.nforetek.bt.manager.NfCallbackInterface;
/* loaded from: classes.dex */
public interface NfPbapCallbackInterface extends NfCallbackInterface {
    void onPbapStateChanged(String str, int i, int i2, int i3, int i4);
}
