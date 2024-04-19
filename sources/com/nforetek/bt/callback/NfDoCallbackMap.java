package com.nforetek.bt.callback;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackMap;
import com.nforetek.bt.callback.def.NfDefBroadcastMap;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public final class NfDoCallbackMap extends NfDoCallback<INfCallbackMap> {
    private final int onMapDownloadNotify;
    private final int onMapMemoryAvailable;
    private final int onMapMessageDeleted;
    private final int onMapMessageDeliverStatus;
    private final int onMapMessageSendingStatus;
    private final int onMapMessageShifted;
    private final int onMapNewMessageReceived;
    private final int onMapStateChanged;
    private final int retMapChangeReadStatusCompleted;
    private final int retMapCleanDatabaseCompleted;
    private final int retMapDatabaseAvailable;
    private final int retMapDeleteDatabaseByAddressCompleted;
    private final int retMapDeleteMessageCompleted;
    private final int retMapDownloadedMessage;
    private final int retMapSendMessageCompleted;

    public NfDoCallbackMap(Context context) {
        super(context);
        this.onMapStateChanged = 1;
        this.retMapDownloadedMessage = 2;
        this.onMapNewMessageReceived = 3;
        this.onMapDownloadNotify = 4;
        this.retMapDatabaseAvailable = 5;
        this.retMapDeleteDatabaseByAddressCompleted = 6;
        this.retMapCleanDatabaseCompleted = 7;
        this.retMapSendMessageCompleted = 8;
        this.retMapDeleteMessageCompleted = 9;
        this.retMapChangeReadStatusCompleted = 10;
        this.onMapMemoryAvailable = 11;
        this.onMapMessageSendingStatus = 12;
        this.onMapMessageDeliverStatus = 13;
        this.onMapMessageShifted = 14;
        this.onMapMessageDeleted = 15;
    }

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected String getLogTag() {
        return "NfDoCallbackMap";
    }

    public void onMapStateChanged(String address, int prevState, int newState, int reason) {
        NfLog.d(this.TAG, "onMapStateChanged() " + address);
        Message m = getMessage(1);
        m.obj = address;
        m.arg1 = prevState;
        m.arg2 = newState;
        Bundle b = new Bundle();
        b.putInt("reason", reason);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retMapDownloadedMessage(String address, String handle, String senderName, String senderNumber, String date, String recipientAddr, int type, int folder, int readStatus, String subject, String message) {
        NfLog.d(this.TAG, "retMapDownloadedMessage() " + address);
        Message m = getMessage(2);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("handle", handle);
        b.putString("senderName", senderName);
        b.putString("senderNumber", senderNumber);
        b.putString("date", date);
        b.putString("recipientAddr", recipientAddr);
        b.putInt("type", type);
        b.putInt("folder", folder);
        b.putInt("readStatus", readStatus);
        b.putString("subject", subject);
        b.putString("message", message);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onMapNewMessageReceived(String address, String handle, String sender, String message) {
        NfLog.d(this.TAG, "onMapNewMessageReceived()");
        Message m = getMessage(3);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("handle", handle);
        b.putString("sender", sender);
        b.putString("message", message);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onMapDownloadNotify(String address, int folder, int totalMessages, int currentMessages) {
        NfLog.d(this.TAG, "onMapDownloadNotify() " + address);
        Message m = getMessage(4);
        this.mHandler.removeMessages(4);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("folder", folder);
        b.putInt("totalMessages", totalMessages);
        b.putInt("currentMessages", currentMessages);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retMapDatabaseAvailable() {
        NfLog.d(this.TAG, "retMapDatabaseAvailable()");
        Message m = getMessage(5);
        enqueueMessage(m);
    }

    public void retMapDeleteDatabaseByAddressCompleted(String address, boolean isSuccess) {
        NfLog.d(this.TAG, "retMapDeleteDatabaseByAddressCompleted()");
        Message m = getMessage(6);
        m.obj = address;
        Bundle b = new Bundle();
        b.putBoolean("isSuccess", isSuccess);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retMapCleanDatabaseCompleted(boolean isSuccess) {
        NfLog.d(this.TAG, "retMapCleanDatabaseCompleted()");
        Message m = getMessage(7);
        Bundle b = new Bundle();
        b.putBoolean("isSuccess", isSuccess);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retMapSendMessageCompleted(String address, String target, int state) {
        NfLog.d(this.TAG, "retMapSendMessageCompleted() " + address);
        Message m = getMessage(8);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("target", target);
        b.putInt("message", state);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retMapDeleteMessageCompleted(String address, String handle, int reason) {
        NfLog.d(this.TAG, "retMapDeleteMessageCompleted() " + address);
        Message m = getMessage(9);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("handle", handle);
        b.putInt("reason", reason);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retMapChangeReadStatusCompleted(String address, String handle, int reason) {
        NfLog.d(this.TAG, "retMapChangeReadStatusCompleted() " + address);
        Message m = getMessage(10);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("handle", handle);
        b.putInt("reason", reason);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onMapMemoryAvailable(String address, int structure, boolean available) {
        NfLog.d(this.TAG, "onMapMemoryAvailable() " + address);
        Message m = getMessage(11);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("structure", structure);
        b.putBoolean("available", available);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onMapMessageSendingStatus(String address, String handle, boolean isSuccess) {
        NfLog.d(this.TAG, "onMapMessageSendingStatus() " + address);
        Message m = getMessage(12);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("handle", handle);
        b.putBoolean("isSuccess", isSuccess);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onMapMessageDeliverStatus(String address, String handle, boolean isSuccess) {
        NfLog.d(this.TAG, "onMapMessageDeliverStatus() " + address);
        Message m = getMessage(13);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("handle", handle);
        b.putBoolean("isSuccess", isSuccess);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onMapMessageShifted(String address, String handle, int type, int newFolder, int oldFolder) {
        NfLog.d(this.TAG, "onMapMessageShifted() " + address);
        Message m = getMessage(14);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("handle", handle);
        b.putInt("type", type);
        b.putInt("newFolder", newFolder);
        b.putInt("oldFolder", oldFolder);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onMapMessageDeleted(String address, String handle, int type, int folder) {
        NfLog.d(this.TAG, "onMapMessageDeleted() " + address);
        Message m = getMessage(15);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("handle", handle);
        b.putInt("type", type);
        b.putInt("folder", folder);
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
                    callbackOnMapStateChanged(address, prevState, newState, b.getInt("reason"));
                    break;
                case 2:
                    callbackRetMapDownloadedMessage(address, b.getString("handle"), b.getString("senderName"), b.getString("senderNumber"), b.getString("date"), b.getString("recipientAddr"), b.getInt("type"), b.getInt("folder"), b.getInt("readStatus"), b.getString("subject"), b.getString("message"));
                    break;
                case 3:
                    callbackOnMapNewMessageReceived(address, b.getString("handle"), b.getString("sender"), b.getString("message"));
                    break;
                case 4:
                    callbackOnMapDownloadNotify(address, b.getInt("folder"), b.getInt("totalMessages"), b.getInt("currentMessages"));
                    break;
                case 5:
                    callbackRetMapDatabaseAvailable();
                    break;
                case 6:
                    callbackRetMapDeleteDatabaseByAddressCompleted(address, b.getBoolean("isSuccess"));
                    break;
                case 7:
                    callbackRetMapCleanDatabaseCompleted(b.getBoolean("isSuccess"));
                    break;
                case 8:
                    callbackRetMapSendMessageCompleted(address, b.getString("target"), b.getInt("state"));
                    break;
                case 9:
                    callbackRetMapDeleteMessageCompleted(address, b.getString("handle"), b.getInt("reason"));
                    break;
                case 10:
                    callbackRetMapChangeReadStatusCompleted(address, b.getString("handle"), b.getInt("reason"));
                    break;
                case 11:
                    callbackOnMapMemoryAvailable(address, b.getInt("structure"), b.getBoolean("available"));
                    break;
                case 12:
                    callbackOnMapMessageSendingStatus(address, b.getString("handle"), b.getBoolean("isSuccess"));
                    break;
                case 13:
                    callbackOnMapMessageDeliverStatus(address, b.getString("handle"), b.getBoolean("isSuccess"));
                    break;
                case 14:
                    callbackOnMapMessageShifted(address, b.getString("handle"), b.getInt("type"), b.getInt("newFolder"), b.getInt("oldFolder"));
                    break;
                case 15:
                    callbackOnMapMessageDeleted(address, b.getString("handle"), b.getInt("type"), b.getInt("folder"));
                    break;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    break;
            }
        }
        if (NfConfig.isCallbackByBroadcast()) {
            switch (msg.what) {
                case 1:
                    broadcastOnMapStateChanged(address, prevState, newState, b.getInt("reason"));
                    return;
                case 12:
                    broadcastOnMapMessageSendingStatus(address, b.getString("handle"), b.getBoolean("isSuccess"));
                    return;
                case 13:
                    broadcastOnMapMessageDeliverStatus(address, b.getString("handle"), b.getBoolean("isSuccess"));
                    return;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.callback.NfDoCallback
    public synchronized void callbackOnServiceReady(INfCallbackMap cb) throws RemoteException {
        cb.onMapServiceReady();
    }

    private synchronized void callbackOnMapStateChanged(String address, int prevState, int newState, int reason) {
        NfLog.v(this.TAG, "callbackOnMapStateChanged() " + address + " state: " + prevState + "->" + newState);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onMapStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).onMapStateChanged(address, prevState, newState, reason);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onMapStateChanged CallBack Finish.");
    }

    private synchronized void callbackRetMapDownloadedMessage(String address, String handle, String senderName, String senderNumber, String date, String recipientAddr, int type, int folder, int readStatus, String subject, String message) {
        Boolean isRead;
        NfLog.v(this.TAG, "callbackRetMapDownloadedMessage() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retMapDownloadedMessage beginBroadcast()");
            Boolean.valueOf(false);
            if (readStatus == 1) {
                isRead = true;
            } else {
                isRead = false;
            }
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).retMapDownloadedMessage(address, handle, senderName, senderNumber, recipientAddr, date, type, folder, isRead.booleanValue(), subject, message);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retMapDownloadedMessage CallBack Finish.");
    }

    private synchronized void callbackOnMapNewMessageReceived(String address, String handle, String sender, String message) {
        NfLog.v(this.TAG, "callbackOnMapNewMessageReceived() " + address + " handle: " + handle);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onMapNewMessageReceived beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).onMapNewMessageReceivedEvent(address, handle, sender, message);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onMapNewMessageReceived CallBack Finish.");
    }

    private synchronized void callbackOnMapDownloadNotify(String address, int folder, int totalMessages, int currentMessages) {
        NfLog.v(this.TAG, "callbackOnMapDownloadNotify() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onMapDownloadNotify beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).onMapDownloadNotify(address, folder, totalMessages, currentMessages);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onMapDownloadNotify CallBack Finish.");
    }

    private synchronized void callbackRetMapDatabaseAvailable() {
        NfLog.v(this.TAG, "callbackRetMapDatabaseAvailable()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retMapDatabaseAvailable beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).retMapDatabaseAvailable();
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retMapDatabaseAvailable CallBack Finish.");
    }

    private synchronized void callbackRetMapDeleteDatabaseByAddressCompleted(String address, boolean isSuccess) {
        NfLog.v(this.TAG, "callbackRetMapDeleteDatabaseByAddressCompleted() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retMapDeleteDatabaseByAddressCompleted beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).retMapDeleteDatabaseByAddressCompleted(address, isSuccess);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retMapDeleteDatabaseByAddressCompleted CallBack Finish.");
    }

    private synchronized void callbackRetMapCleanDatabaseCompleted(boolean isSuccess) {
        NfLog.v(this.TAG, "callbackRetMapCleanDatabaseCompleted() " + isSuccess);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retMapCleanDatabaseCompleted beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).retMapCleanDatabaseCompleted(isSuccess);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retMapCleanDatabaseCompleted CallBack Finish.");
    }

    private synchronized void callbackRetMapSendMessageCompleted(String address, String target, int state) {
        NfLog.v(this.TAG, "callbackRetMapSendMessageCompleted() " + state);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "callbackRetMapSendMessageCompleted beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).retMapSendMessageCompleted(address, target, state);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retMapSendMessageCompleted CallBack Finish.");
    }

    private synchronized void callbackRetMapDeleteMessageCompleted(String address, String handle, int reason) {
        NfLog.v(this.TAG, "callbackRetMapDeleteMessageCompleted() " + reason);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "callbackRetMapDeleteMessageCompleted beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).retMapDeleteMessageCompleted(address, handle, reason);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retMapDeleteMessageCompleted CallBack Finish.");
    }

    private synchronized void callbackRetMapChangeReadStatusCompleted(String address, String handle, int reason) {
        NfLog.v(this.TAG, "callbackRetMapReadStatusCompleted() " + reason);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "callbackRetMapReadStatusCompleted beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).retMapChangeReadStatusCompleted(address, handle, reason);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retMapReadStatusCompleted CallBack Finish.");
    }

    private synchronized void callbackOnMapMemoryAvailable(String address, int structure, boolean available) {
        NfLog.v(this.TAG, "callbackOnMapMemoryAvailable() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onMapMemoryAvailable beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).onMapMemoryAvailableEvent(address, structure, available);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onMapMemoryAvailable CallBack Finish.");
    }

    private synchronized void callbackOnMapMessageSendingStatus(String address, String handle, boolean isSuccess) {
        NfLog.v(this.TAG, "callbackOnMapMessageSendingStatus() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onMapMessageSendingStatus beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).onMapMessageSendingStatusEvent(address, handle, isSuccess);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onMapMessageSendingStatus CallBack Finish.");
    }

    private synchronized void callbackOnMapMessageDeliverStatus(String address, String handle, boolean isSuccess) {
        NfLog.v(this.TAG, "callbackOnMapMessageSendingStatus() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onMapMessageDeliverStatus beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).onMapMessageDeliverStatusEvent(address, handle, isSuccess);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onMapMessageDeliverStatus CallBack Finish.");
    }

    private synchronized void callbackOnMapMessageShifted(String address, String handle, int type, int newFolder, int oldFolder) {
        NfLog.v(this.TAG, "callbackOnMapMessageShifted() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onMapMessageShifted beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).onMapMessageShiftedEvent(address, handle, type, newFolder, oldFolder);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onMapMessageShifted CallBack Finish.");
    }

    private synchronized void callbackOnMapMessageDeleted(String address, String handle, int type, int folder) {
        NfLog.v(this.TAG, "callbackOnMapMessageDeleted() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onMapMessageDeleted beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackMap) this.mRemoteCallbacks.getBroadcastItem(i)).onMapMessageDeletedEvent(address, handle, type, folder);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onMapMessageDeleted CallBack Finish.");
    }

    private synchronized void broadcastOnMapStateChanged(String address, int prevState, int newState, int reason) {
        NfLog.v(this.TAG, "broadcastOnMapStateChanged() " + address + " state: " + prevState + "->" + newState);
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
            case 150:
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
            case 150:
            case 160:
                newState = 2;
                break;
        }
        Intent intent = new Intent(NfDefBroadcastMap.ACTION_CONNECTION_STATE_CHANGED);
        BluetoothDevice device = NfPrimitive.getDevice(address);
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onMapStateChanged broadcast Finish.");
    }

    private synchronized void broadcastOnMapMessageSendingStatus(String address, String handle, boolean isSuccess) {
        NfLog.v(this.TAG, "broadcastOnMapMessageSendingStatus() " + address);
        Intent intent = new Intent(NfDefBroadcastMap.ACTION_MESSAGE_SENT_SUCCESSFULLY);
        BluetoothDevice device = NfPrimitive.getDevice(address);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra(NfDefBroadcastMap.EXTRA_SENT_STATE, isSuccess);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onMapMessageSendingStatus broadcast Finish.");
    }

    private synchronized void broadcastOnMapMessageDeliverStatus(String address, String handle, boolean isSuccess) {
        NfLog.v(this.TAG, "broadcastOnMapMessageSendingStatus() " + address);
        Intent intent = new Intent(NfDefBroadcastMap.ACTION_MESSAGE_DELIVERED_SUCCESSFULLY);
        BluetoothDevice device = NfPrimitive.getDevice(address);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra(NfDefBroadcastMap.EXTRA_DELIVER_STATE, isSuccess);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onMapMessageDeliverStatus broadcast Finish.");
    }
}
