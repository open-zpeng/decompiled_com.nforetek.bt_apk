package com.nforetek.bt.callback;

import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackBluetooth;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public final class NfDoCallbackBluetooth extends NfDoCallback<INfCallbackBluetooth> {
    private final int onAdapterStateChanged = 1;
    private final int onAdapterDiscoverableModeChanged = 2;
    private final int onAdapterDiscoveryStarted = 3;
    private final int onAdapterDiscoveryFinished = 4;
    private final int retPairedDevices = 5;
    private final int onDeviceFound = 6;
    private final int onDeviceBondStateChanged = 7;
    private final int onDeviceUuidsUpdated = 8;
    private final int onLocalAdapterNameChanged = 9;
    private final int onDeviceOutOfRange = 10;
    private final int onDeviceAclDisconnected = 11;
    private final int onBtRoleModeChanged = 12;
    private final int onBtAutoConnectStateChanged = 13;

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected String getLogTag() {
        return "NfDoCallbackBluetooth";
    }

    public void onAdapterStateChanged(int prevState, int newState) {
        NfLog.d(this.TAG, "onAdapterStateChanged(): " + prevState + "->" + newState);
        Message m = getMessage(1);
        m.arg1 = prevState;
        m.arg2 = newState;
        enqueueMessage(m);
    }

    public void onAdapterDiscoverableModeChanged(int prevState, int newState) {
        NfLog.d(this.TAG, "onAdapterDiscoverableModeChanged() State: " + prevState + "->" + newState);
        Message m = getMessage(2);
        m.arg1 = prevState;
        m.arg2 = newState;
        enqueueMessage(m);
    }

    public void onAdapterDiscoveryStarted() {
        NfLog.d(this.TAG, "onAdapterDiscoveryStarted()");
        Message m = getMessage(3);
        enqueueMessage(m);
    }

    public void onAdapterDiscoveryFinished() {
        NfLog.d(this.TAG, "onAdapterDiscoveryFinished()");
        Message m = getMessage(4);
        enqueueMessage(m);
    }

    public void retPairedDevices(int elements, String[] address, String[] name, int[] supportProfile, byte[] category) {
        NfLog.d(this.TAG, "retPairedDevices() " + address);
        Message m = getMessage(5);
        Bundle b = new Bundle();
        b.putInt("elements", elements);
        b.putStringArray("address", address);
        b.putStringArray("name", name);
        b.putIntArray("supportProfile", supportProfile);
        b.putByteArray("category", category);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onDeviceFound(String address, String name, byte category) {
        NfLog.d(this.TAG, "onDeviceFound() " + address);
        Message m = getMessage(6);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("name", name);
        b.putByte("category", category);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onDeviceBondStateChanged(String address, String name, int prevState, int newState) {
        NfLog.d(this.TAG, "onDeviceBondStateChanged() " + address + " state: " + prevState + " -> " + newState);
        Message m = getMessage(7);
        m.obj = address;
        m.arg1 = prevState;
        m.arg2 = newState;
        Bundle b = new Bundle();
        b.putString("name", name);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onDeviceUuidsUpdated(String address, String name, int supportProfile) {
        NfLog.d(this.TAG, "onDeviceUuidsUpdated() " + address);
        Message m = getMessage(8);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("name", name);
        b.putInt("supportProfile", supportProfile);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onLocalAdapterNameChanged(String name) {
        NfLog.d(this.TAG, "onLocalAdapterNameChanged() name: " + name);
        Message m = getMessage(9);
        Bundle b = new Bundle();
        b.putString("name", name);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onDeviceOutOfRange(String address) {
        NfLog.d(this.TAG, "onDeviceOutOfRange() " + address);
        Message m = getMessage(10);
        Bundle b = new Bundle();
        b.putString("address", address);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onDeviceAclDisconnected(String address) {
        NfLog.d(this.TAG, "onAclDisconnected() " + address);
        Message m = getMessage(11);
        Bundle b = new Bundle();
        b.putString("address", address);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onBtRoleModeChanged(int mode) {
        NfLog.d(this.TAG, "onBtRoleModeChanged() " + mode);
        Message m = getMessage(12);
        Bundle b = new Bundle();
        b.putInt("mode", mode);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onBtAutoConnectStateChanged(String address, int prevState, int newState) {
        NfLog.d(this.TAG, "onBtAutoConnectStateChanged() address: " + address + " state: " + prevState + " -> " + newState);
        Message m = getMessage(13);
        Bundle b = new Bundle();
        m.obj = address;
        b.putInt("prevState", prevState);
        b.putInt("newState", newState);
        m.setData(b);
        enqueueMessage(m);
    }

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected void dequeueMessage(Message msg) {
        Bundle b = msg.getData();
        String address = (String) msg.obj;
        int prevState = msg.arg1;
        int newState = msg.arg2;
        if (NfConfig.isCallbackByAidl()) {
            switch (msg.what) {
                case 1:
                    callbackOnAdapterStateChanged(prevState, newState);
                    return;
                case 2:
                    callbackOnAdapterDiscoverableModeChanged(prevState, newState);
                    return;
                case 3:
                    callbackOnAdapterDiscoveryStarted();
                    return;
                case 4:
                    callbackOnAdapterDiscoveryFinished();
                    return;
                case 5:
                    callbackRetPairedDevices(b.getInt("elements"), b.getStringArray("address"), b.getStringArray("name"), b.getIntArray("supportProfile"), b.getByteArray("category"));
                    return;
                case 6:
                    callbackOnDeviceFound(address, b.getString("name"), b.getByte("category"));
                    return;
                case 7:
                    callbackOnDeviceBondStateChanged(address, b.getString("name"), prevState, newState);
                    return;
                case 8:
                    callbackOnDeviceUuidsUpdated(address, b.getString("name"), b.getInt("supportProfile"));
                    return;
                case 9:
                    callbackOnLocalAdapterNameChanged(b.getString("name"));
                    return;
                case 10:
                    callbackOnDeviceOutOfRange(b.getString("address"));
                    return;
                case 11:
                    callbackOnDeviceAclDisconnected(b.getString("address"));
                    return;
                case 12:
                    callbackOnBtRoleModeChanged(b.getInt("mode"));
                    return;
                case 13:
                    callbackOnBtAutoConnectStateChanged(address, b.getInt("prevState"), b.getInt("newState"));
                    return;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.callback.NfDoCallback
    public synchronized void callbackOnServiceReady(INfCallbackBluetooth cb) throws RemoteException {
        cb.onBluetoothServiceReady();
    }

    private synchronized void callbackOnAdapterStateChanged(int prevState, int newState) {
        NfLog.v(this.TAG, "callbackOnAdapterStateChanged() State: " + prevState + "->" + newState);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAdapterStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onAdapterStateChanged broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onAdapterStateChanged(prevState, newState);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAdapterStateChanged CallBack Finish.");
    }

    private synchronized void callbackOnAdapterDiscoverableModeChanged(int prevState, int newState) {
        NfLog.v(this.TAG, "callbackOnAdapterDiscoverableModeChanged() State: " + prevState + "->" + newState);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAdapterDiscoverableModeChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onAdapterDiscoverableModeChanged broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onAdapterDiscoverableModeChanged(prevState, newState);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAdapterDiscoverableModeChanged CallBack Finish.");
    }

    private synchronized void callbackOnAdapterDiscoveryStarted() {
        NfLog.v(this.TAG, "callbackOnAdapterDiscoveryStarted()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAdapterDiscoveryStarted beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onAdapterDiscoveryStarted broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onAdapterDiscoveryStarted();
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAdapterDiscoveryStarted CallBack Finish.");
    }

    private synchronized void callbackOnAdapterDiscoveryFinished() {
        NfLog.v(this.TAG, "callbackOnAdapterDiscoveryFinished()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAdapterDiscoveryFinished beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onAdapterDiscoveryFinished broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onAdapterDiscoveryFinished();
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAdapterDiscoveryFinished CallBack Finish.");
    }

    private synchronized void callbackRetPairedDevices(int elements, String[] address, String[] name, int[] supportProfile, byte[] category) {
        NfLog.v(this.TAG, "callbackRetPairedDevices() elements: " + elements);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retPairedDevices beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "retPairedDevices broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).retPairedDevices(elements, address, name, supportProfile, category);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retPairedDevices CallBack Finish.");
    }

    private synchronized void callbackOnDeviceFound(String address, String name, byte category) {
        NfLog.v(this.TAG, "callbackOnDeviceFound() " + address + " name: " + name + " category: " + ((int) category));
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onDeviceFound beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onDeviceFound broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onDeviceFound(address, name, category);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onDeviceFound CallBack Finish.");
    }

    private synchronized void callbackOnDeviceBondStateChanged(String address, String name, int prevState, int newState) {
        NfLog.v(this.TAG, "callbackOnDeviceBondStateChanged() " + address + " name: " + name + " State: " + prevState + "->" + newState);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onDeviceBondStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onDeviceBondStateChanged broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onDeviceBondStateChanged(address, name, prevState, newState);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onDeviceBondStateChanged CallBack Finish.");
    }

    private synchronized void callbackOnDeviceUuidsUpdated(String address, String name, int supportProfile) {
        NfLog.v(this.TAG, "callbackOnDeviceUuidsUpdated() " + address + " name: " + name + " supportProfile: " + supportProfile);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onDeviceUuidsUpdated beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onDeviceUuidsUpdated broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onDeviceUuidsUpdated(address, name, supportProfile);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onDeviceUuidsUpdated CallBack Finish.");
    }

    private synchronized void callbackOnLocalAdapterNameChanged(String name) {
        NfLog.v(this.TAG, "callbackOnLocalAdapterNameChanged() name: " + name);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onLocalAdapterNameChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onLocalAdapterNameChanged broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onLocalAdapterNameChanged(name);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onLocalAdapterNameChanged CallBack Finish.");
    }

    private synchronized void callbackOnDeviceOutOfRange(String address) {
        NfLog.v(this.TAG, "callbackOnDeviceOutOfRange() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onDeviceOutOfRange beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onDeviceOutOfRange broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onDeviceOutOfRange(address);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onDeviceOutOfRange CallBack Finish.");
    }

    private synchronized void callbackOnDeviceAclDisconnected(String address) {
        NfLog.v(this.TAG, "callbackOnDeviceAclDisconnected() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onDeviceAclDisconnected beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onDeviceAclDisconnected broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onDeviceAclDisconnected(address);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onDeviceAclDisconnected CallBack Finish.");
    }

    private synchronized void callbackOnBtRoleModeChanged(int mode) {
        NfLog.v(this.TAG, "callbackOnBtRoleModeChanged() " + mode);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onBtRoleModeChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onBtRoleModeChanged broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onBtRoleModeChanged(mode);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onBtRoleModeChanged CallBack Finish.");
    }

    private synchronized void callbackOnBtAutoConnectStateChanged(String address, int prevState, int newState) {
        NfLog.v(this.TAG, "callbackOnBtAutoConnectStateChanged() address: " + address + " state: " + prevState + " -> " + newState);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onBtAutoConnectStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            NfLog.v(this.TAG, "onBtAutoConnectStateChanged broadcast count : " + n);
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackBluetooth) this.mRemoteCallbacks.getBroadcastItem(i)).onBtAutoConnectStateChanged(address, prevState, newState);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onBtAutoConnectStateChanged CallBack Finish.");
    }
}
