package com.nforetek.bt.profile;

import com.nforetek.bt.manager.NfCallbackInterface;
/* loaded from: classes.dex */
public interface NfAvrcpCallbackInterface extends NfCallbackInterface {
    void onAvrcpBrowseStateChanged(String str, int i, int i2);

    void onAvrcpStateChanged(String str, int i, int i2);
}
