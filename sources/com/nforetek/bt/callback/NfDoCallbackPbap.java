package com.nforetek.bt.callback;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackPbap;
import com.nforetek.bt.aidl.NfPbapContact;
import com.nforetek.bt.callback.def.NfDefBroadcastPbap;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public final class NfDoCallbackPbap extends NfDoCallback<INfCallbackPbap> {
    private final int onPbapDownloadNotify;
    private final int onPbapStateChanged;
    private final int retPbapCleanDatabaseCompleted;
    private final int retPbapDatabaseAvailable;
    private final int retPbapDatabaseQueryNameByNumber;
    private final int retPbapDatabaseQueryNameByPartialNumber;
    private final int retPbapDeleteDatabaseByAddressCompleted;
    private final int retPbapDownloadedCallLog;
    private final int retPbapDownloadedContact;

    public NfDoCallbackPbap(Context context) {
        super(context);
        this.onPbapStateChanged = 1;
        this.retPbapDatabaseQueryNameByNumber = 2;
        this.retPbapDatabaseQueryNameByPartialNumber = 3;
        this.retPbapDatabaseAvailable = 4;
        this.retPbapDeleteDatabaseByAddressCompleted = 5;
        this.retPbapCleanDatabaseCompleted = 6;
        this.retPbapDownloadedContact = 7;
        this.retPbapDownloadedCallLog = 8;
        this.onPbapDownloadNotify = 9;
    }

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected String getLogTag() {
        return "NfDoCallbackPbap";
    }

    public void onPbapStateChanged(String address, int prevState, int newState, int reason, int counts) {
        NfLog.d(this.TAG, "onPbapStateChanged() " + address + " state: " + prevState + " -> " + newState + " reason: " + reason + " counts: " + counts);
        Message m = getMessage(1);
        m.obj = address;
        m.arg1 = prevState;
        m.arg2 = newState;
        Bundle b = new Bundle();
        b.putInt("reason", reason);
        b.putInt("counts", counts);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retPbapDatabaseQueryNameByNumber(String address, String target, String name, boolean isSuccess) {
        NfLog.d(this.TAG, "retPbapDatabaseQueryNameByNumber() " + address);
        Message m = getMessage(2);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("target", target);
        b.putString("name", name);
        b.putBoolean("isSuccess", isSuccess);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retPbapDatabaseQueryNameByPartialNumber(String address, String target, String[] names, String[] numbers, boolean isSuccess) {
        NfLog.d(this.TAG, "retPbapDatabaseQueryNameByPartialNumber() " + address);
        Message m = getMessage(3);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("target", target);
        b.putStringArray("names", names);
        b.putStringArray("numbers", numbers);
        b.putBoolean("isSuccess", isSuccess);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retPbapDatabaseAvailable(String address) {
        NfLog.d(this.TAG, "retPbapDatabaseAvailable() " + address);
        Message m = getMessage(4);
        m.obj = address;
        enqueueMessage(m);
    }

    public void retPbapDeleteDatabaseByAddressCompleted(String address, boolean isSuccess) {
        NfLog.d(this.TAG, "retPbapDeleteDatabaseByAddressCompleted() " + address);
        Message m = getMessage(5);
        m.obj = address;
        Bundle b = new Bundle();
        b.putBoolean("isSuccess", isSuccess);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retPbapCleanDatabaseCompleted(boolean isSuccess) {
        NfLog.d(this.TAG, "retPbapCleanDatabaseCompleted() isSuccess: " + isSuccess);
        Message m = getMessage(6);
        Bundle b = new Bundle();
        b.putBoolean("isSuccess", isSuccess);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retPbapDownloadedContact(String address, String firstName, String middleName, String lastName, String[] numbers, int[] types, byte[] photoBytes, int photoType) {
        NfLog.v(this.TAG, "retPbapDownloadedContact()");
        Message m = getMessage(7);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("firstName", firstName);
        b.putString("middleName", middleName);
        b.putString("lastName", lastName);
        b.putStringArray("numbers", numbers);
        b.putIntArray("types", types);
        b.putByteArray("photoBytes", photoBytes);
        b.putInt("photoType", photoType);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retPbapDownloadedContact(NfPbapContact contact) {
        NfLog.v(this.TAG, "retPbapDownloadedContact()");
        Message m = getMessage(7);
        Bundle b = new Bundle();
        b.putParcelable("contact", contact);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retPbapDownloadedCallLog(String address, String firstName, String middleName, String lastName, String number, int type, String timestamp) {
        NfLog.v(this.TAG, "retPbapDownloadedCallLog() " + timestamp);
        Message m = getMessage(8);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("firstName", firstName);
        b.putString("middleName", middleName);
        b.putString("lastName", lastName);
        b.putString("number", number);
        b.putString("timestamp", timestamp);
        b.putInt("type", type);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onPbapDownloadNotify(String address, int storage, int totalContacts, int downloadedContacts) {
        NfLog.v(this.TAG, "onPbapDownloadNotify()");
        Message m = getMessage(9);
        this.mHandler.removeMessages(9);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("storage", storage);
        b.putInt("totalContacts", totalContacts);
        b.putInt("downloadedContacts", downloadedContacts);
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
                    callbackOnPbapStateChanged(address, prevState, newState, b.getInt("reason"), b.getInt("counts"));
                    break;
                case 2:
                    callbackRetPbapDatabaseQueryNameByNumber(address, b.getString("target"), b.getString("name"), b.getBoolean("isSuccess"));
                    break;
                case 3:
                    callbackRetPbapDatabaseQueryNameByPartialNumber(address, b.getString("target"), b.getStringArray("names"), b.getStringArray("numbers"), b.getBoolean("isSuccess"));
                    break;
                case 4:
                    callbackRetPbapDatabaseAvailable(address);
                    break;
                case 5:
                    callbackRetPbapDeleteDatabaseByAddressCompleted(address, b.getBoolean("isSuccess"));
                    break;
                case 6:
                    callbackRetPbapCleanDatabaseCompleted(b.getBoolean("isSuccess"));
                    break;
                case 7:
                    callbackRetPbapDownloadedContact((NfPbapContact) b.getParcelable("contact"));
                    break;
                case 8:
                    callbackRetPbapDownloadedCallLog(address, b.getString("firstName"), b.getString("middleName"), b.getString("lastName"), b.getString("number"), b.getInt("type"), b.getString("timestamp"));
                    break;
                case 9:
                    callbackOnPbapDownloadNotify(address, b.getInt("storage"), b.getInt("totalContacts"), b.getInt("downloadedContacts"));
                    break;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    break;
            }
        }
        if (NfConfig.isCallbackByBroadcast()) {
            switch (msg.what) {
                case 1:
                    broadcastOnPbapStateChanged(address, prevState, newState, b.getInt("reason"), b.getInt("counts"));
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.callback.NfDoCallback
    public synchronized void callbackOnServiceReady(INfCallbackPbap cb) throws RemoteException {
        cb.onPbapServiceReady();
    }

    private synchronized void callbackOnPbapStateChanged(String address, int prevState, int newState, int reason, int counts) {
        NfLog.v(this.TAG, "callbackOnPbapStateChanged() " + address + " state: " + prevState + "->" + newState + " reason: " + reason + " count: " + counts);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onPbapStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackPbap) this.mRemoteCallbacks.getBroadcastItem(i)).onPbapStateChanged(address, prevState, newState, reason, counts);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onPbapStateChanged CallBack Finish.");
    }

    private synchronized void callbackRetPbapDownloadedContact(NfPbapContact contact) {
        NfLog.d(this.TAG, "callbackRetPbapDownloadedContact()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retPbapDownloadedContact beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackPbap) this.mRemoteCallbacks.getBroadcastItem(i)).retPbapDownloadedContact(contact);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e1) {
                    e1.printStackTrace();
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retPbapDownloadedContact CallBack Finish.");
    }

    private synchronized void callbackRetPbapDownloadedCallLog(String address, String firstName, String middleName, String lastName, String number, int type, String timestamp) {
        NfLog.v(this.TAG, "callbackRetPbapDownloadedCallLog()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retPbapDownloadedCallLog beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    try {
                        ((INfCallbackPbap) this.mRemoteCallbacks.getBroadcastItem(i)).retPbapDownloadedCallLog(address, firstName, middleName, lastName, number, type, timestamp);
                    } catch (NullPointerException e) {
                        checkCallbacksValid(i);
                    }
                } catch (RemoteException e2) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retPbapDownloadedCallLog CallBack Finish.");
    }

    private synchronized void callbackRetPbapDatabaseQueryNameByNumber(String address, String target, String name, boolean isSuccess) {
        NfLog.v(this.TAG, "callbackRetPbapDatabaseQueryNameByNumber() " + address + " target: " + target + " name: " + name + " isSuccess: " + isSuccess);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retPbapDatabaseQueryNameByNumber beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackPbap) this.mRemoteCallbacks.getBroadcastItem(i)).retPbapDatabaseQueryNameByNumber(address, target, name, isSuccess);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retPbapDatabaseQueryNameByNumber CallBack Finish.");
    }

    private synchronized void callbackRetPbapDatabaseQueryNameByPartialNumber(String address, String target, String[] names, String[] numbers, boolean isSuccess) {
        NfLog.v(this.TAG, "callbackRetPbapDatabaseQueryNameByPartialNumber() " + address + " target: " + target + " isSuccess: " + isSuccess);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retPbapDatabaseQueryNameByPartialNumber beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    try {
                        ((INfCallbackPbap) this.mRemoteCallbacks.getBroadcastItem(i)).retPbapDatabaseQueryNameByPartialNumber(address, target, names, numbers, isSuccess);
                    } catch (RemoteException e) {
                        NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                    }
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retPbapDatabaseQueryNameByPartialNumber CallBack Finish.");
    }

    private synchronized void callbackRetPbapDatabaseAvailable(String address) {
        NfLog.v(this.TAG, "callbackRetPbapDatabaseAvailable() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retPbapDatabaseAvailable beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackPbap) this.mRemoteCallbacks.getBroadcastItem(i)).retPbapDatabaseAvailable(address);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retPbapDatabaseAvailable CallBack Finish.");
    }

    private synchronized void callbackRetPbapDeleteDatabaseByAddressCompleted(String address, boolean isSuccess) {
        NfLog.v(this.TAG, "callbackRetPbapDeleteDatabaseByAddressCompleted() " + address + " isSuccess: " + isSuccess);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retPbapDeleteDatabaseByAddressCompleted beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackPbap) this.mRemoteCallbacks.getBroadcastItem(i)).retPbapDeleteDatabaseByAddressCompleted(address, isSuccess);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retPbapDeleteDatabaseByAddressCompleted CallBack Finish.");
    }

    private synchronized void callbackRetPbapCleanDatabaseCompleted(boolean isSuccess) {
        NfLog.v(this.TAG, "callbackRetPbapCleanDatabaseCompleted() isSuccess: " + isSuccess);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retPbapCleanDatabaseCompleted beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackPbap) this.mRemoteCallbacks.getBroadcastItem(i)).retPbapCleanDatabaseCompleted(isSuccess);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retPbapCleanDatabaseCompleted CallBack Finish.");
    }

    private synchronized void callbackOnPbapDownloadNotify(String address, int storage, int totalContacts, int downloadedContacts) {
        NfLog.v(this.TAG, "callbackOnPbapDownloadNotify() " + address + " storage: " + storage + " downloaded: " + downloadedContacts + "/" + totalContacts);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onPbapDownloadNotify beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackPbap) this.mRemoteCallbacks.getBroadcastItem(i)).onPbapDownloadNotify(address, storage, totalContacts, downloadedContacts);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onPbapDownloadNotify CallBack Finish.");
    }

    private synchronized void broadcastOnPbapStateChanged(String address, int prevState, int newState, int reason, int counts) {
        NfLog.v(this.TAG, "broadcastOnPbapStateChanged() " + address + " state: " + prevState + "->" + newState + " reason: " + reason + " count: " + counts);
        switch (prevState) {
            case 110:
                prevState = 0;
                break;
            case 120:
                prevState = 1;
                break;
            case NfDef.STATE_DISCONNECTING /* 125 */:
                prevState = 3;
                break;
            case 160:
                prevState = 2;
                break;
        }
        switch (newState) {
            case 110:
                newState = 0;
                break;
            case 120:
                newState = 1;
                break;
            case NfDef.STATE_DISCONNECTING /* 125 */:
                newState = 3;
                break;
            case 160:
                newState = 2;
                break;
        }
        Intent intent = new Intent(NfDefBroadcastPbap.ACTION_CONNECTION_STATE_CHANGED);
        BluetoothDevice device = NfPrimitive.getDevice(address);
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onPbapStateChanged CallBack Finish.");
    }
}
