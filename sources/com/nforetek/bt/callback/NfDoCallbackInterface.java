package com.nforetek.bt.callback;

import com.nforetek.bt.aidl.INfCallbackA2dp;
import com.nforetek.bt.aidl.INfCallbackAvrcp;
import com.nforetek.bt.aidl.INfCallbackBluetooth;
import com.nforetek.bt.aidl.INfCallbackGattServer;
import com.nforetek.bt.aidl.INfCallbackHfp;
import com.nforetek.bt.aidl.INfCallbackHid;
import com.nforetek.bt.aidl.INfCallbackMap;
import com.nforetek.bt.aidl.INfCallbackOpp;
import com.nforetek.bt.aidl.INfCallbackPbap;
import com.nforetek.bt.aidl.INfCallbackSpp;
/* loaded from: classes.dex */
public interface NfDoCallbackInterface {
    void forceStopAutoConnectThread();

    boolean initDelayPairThread(String str);

    boolean isAclConnected(String str);

    boolean registerA2dpCallback(INfCallbackA2dp iNfCallbackA2dp);

    boolean registerAvrcpCallback(INfCallbackAvrcp iNfCallbackAvrcp);

    boolean registerBluetoothCallback(INfCallbackBluetooth iNfCallbackBluetooth);

    boolean registerGattServerCallback(INfCallbackGattServer iNfCallbackGattServer);

    boolean registerHfpCallback(INfCallbackHfp iNfCallbackHfp);

    boolean registerHidCallback(INfCallbackHid iNfCallbackHid);

    boolean registerMapCallback(INfCallbackMap iNfCallbackMap);

    boolean registerOppCallback(INfCallbackOpp iNfCallbackOpp);

    boolean registerPbapCallback(INfCallbackPbap iNfCallbackPbap);

    boolean registerSppCallback(INfCallbackSpp iNfCallbackSpp);

    boolean unregisterA2dpCallback(INfCallbackA2dp iNfCallbackA2dp);

    boolean unregisterAvrcpCallback(INfCallbackAvrcp iNfCallbackAvrcp);

    boolean unregisterBluetoothCallback(INfCallbackBluetooth iNfCallbackBluetooth);

    boolean unregisterGattServerCallback(INfCallbackGattServer iNfCallbackGattServer);

    boolean unregisterHfpCallback(INfCallbackHfp iNfCallbackHfp);

    boolean unregisterHidCallback(INfCallbackHid iNfCallbackHid);

    boolean unregisterMapCallback(INfCallbackMap iNfCallbackMap);

    boolean unregisterOppCallback(INfCallbackOpp iNfCallbackOpp);

    boolean unregisterPbapCallback(INfCallbackPbap iNfCallbackPbap);

    boolean unregisterSppCallback(INfCallbackSpp iNfCallbackSpp);
}
