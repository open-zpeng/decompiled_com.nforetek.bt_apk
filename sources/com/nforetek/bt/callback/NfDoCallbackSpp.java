package com.nforetek.bt.callback;

import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackSpp;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public final class NfDoCallbackSpp extends NfDoCallback<INfCallbackSpp> {
    private final int onSppStateChanged = 1;
    private final int onSppErrorResponse = 2;
    private final int retSppConnectedDeviceAddressList = 3;
    private final int onSppDataReceived = 4;
    private final int onSppSendData = 5;
    private final int onSppAppleIapAuthenticationRequest = 6;

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected String getLogTag() {
        return "NfDoCallbackSpp";
    }

    public void onSppStateChanged(String address, String deviceName, int prevState, int newState) {
        NfLog.d(this.TAG, "onSppStateChanged() " + address);
        Message m = getMessage(1);
        m.obj = address;
        m.arg1 = prevState;
        m.arg2 = newState;
        Bundle b = new Bundle();
        b.putString("deviceName", deviceName);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onSppErrorResponse(String address, int errorCode) {
        NfLog.d(this.TAG, "onSppErrorResponse() " + address);
        Message m = getMessage(2);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("errorCode", errorCode);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retSppConnectedDeviceAddressList(int totalNum, String[] addressList, String[] nameList) {
        NfLog.d(this.TAG, "retSppConnectedDeviceAddressList()");
        Message m = getMessage(3);
        Bundle b = new Bundle();
        b.putInt("totalNum", totalNum);
        b.putStringArray("addressList", addressList);
        b.putStringArray("nameList", nameList);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onSppDataReceived(String address, byte[] receivedData) {
        NfLog.v(this.TAG, "onSppDataReceived() " + address);
        Message m = getMessage(4);
        m.obj = address;
        Bundle b = new Bundle();
        b.putByteArray("receivedData", receivedData);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onSppSendData(String address, int length) {
        NfLog.v(this.TAG, "onSppSendData() " + address);
        Message m = getMessage(5);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("sendDataLength", length);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onSppAppleIapAuthenticationRequest(String address) {
        NfLog.d(this.TAG, "onSppAppleIapAuthenticationRequest() " + address);
        Message m = getMessage(6);
        m.obj = address;
        Bundle b = new Bundle();
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
                    callbackOnSppStateChanged(address, b.getString("deviceName"), prevState, newState);
                    return;
                case 2:
                    callbackOnSppErrorResponse(address, b.getInt("errorCode"));
                    return;
                case 3:
                    callbackRetSppConnectedDeviceAddressList(b.getInt("totalNum"), b.getStringArray("addressList"), b.getStringArray("nameList"));
                    return;
                case 4:
                    callbackOnSppDataReceived(address, b.getByteArray("receivedData"));
                    return;
                case 5:
                    callbackOnSppSendData(address, b.getInt("sendDataLength"));
                    return;
                case 6:
                    callbackOnSppAppleIapAuthenticationRequest(address);
                    return;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.callback.NfDoCallback
    public synchronized void callbackOnServiceReady(INfCallbackSpp cb) throws RemoteException {
        cb.onSppServiceReady();
    }

    private synchronized void callbackOnSppStateChanged(String address, String deviceName, int prevState, int newState) {
        NfLog.v(this.TAG, "callbackOnSppStateChanged() " + address + " state: " + prevState + "->" + newState);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onSppStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackSpp) this.mRemoteCallbacks.getBroadcastItem(i)).onSppStateChanged(address, deviceName, prevState, newState);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onSppStateChanged CallBack Finish.");
    }

    private synchronized void callbackOnSppErrorResponse(String address, int errorCode) {
        NfLog.v(this.TAG, "callbackOnSppErrorResponse() " + address + " errorCode: " + errorCode);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onSppErrorResponse beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackSpp) this.mRemoteCallbacks.getBroadcastItem(i)).onSppErrorResponse(address, errorCode);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onSppErrorResponse CallBack Finish.");
    }

    private synchronized void callbackRetSppConnectedDeviceAddressList(int totalNum, String[] addressList, String[] nameList) {
        NfLog.v(this.TAG, "callbackRetSppConnectedDeviceAddressList() totalNum: " + totalNum);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retSppConnectedDeviceAddressList beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackSpp) this.mRemoteCallbacks.getBroadcastItem(i)).retSppConnectedDeviceAddressList(totalNum, addressList, nameList);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retSppConnectedDeviceAddressList CallBack Finish.");
    }

    private synchronized void callbackOnSppDataReceived(String address, byte[] receivedData) {
        NfLog.v(this.TAG, "callbackOnSppDataReceived() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onSppDataReceived beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackSpp) this.mRemoteCallbacks.getBroadcastItem(i)).onSppDataReceived(address, receivedData);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onSppDataReceived CallBack Finish.");
    }

    private synchronized void callbackOnSppSendData(String address, int length) {
        NfLog.v(this.TAG, "callbackOnSppSendData() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onSppSendData beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackSpp) this.mRemoteCallbacks.getBroadcastItem(i)).onSppSendData(address, length);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onSppSendData CallBack Finish.");
    }

    private synchronized void callbackOnSppAppleIapAuthenticationRequest(String address) {
        NfLog.v(this.TAG, "callbackOnSppAppleIapAuthenticationRequest() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onSppAppleIapAuthenticationRequest beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackSpp) this.mRemoteCallbacks.getBroadcastItem(i)).onSppAppleIapAuthenticationRequest(address);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onSppAppleIapAuthenticationRequest CallBack Finish.");
    }
}
