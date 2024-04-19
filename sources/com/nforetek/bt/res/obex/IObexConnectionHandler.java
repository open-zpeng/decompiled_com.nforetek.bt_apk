package com.nforetek.bt.res.obex;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
/* loaded from: classes.dex */
public interface IObexConnectionHandler {
    void onAcceptFailed();

    boolean onConnect(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket);
}
