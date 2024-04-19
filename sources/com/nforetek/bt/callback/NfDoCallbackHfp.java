package com.nforetek.bt.callback;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackHfp;
import com.nforetek.bt.aidl.NfHfpClientCall;
import com.nforetek.bt.callback.def.NfDefBroadcastHfp;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public final class NfDoCallbackHfp extends NfDoCallback<INfCallbackHfp> {
    private final int onHfpAudioStateChanged;
    private final int onHfpCallChanged;
    private final int onHfpRemoteBatteryIndicator;
    private final int onHfpRemoteRoamingStatus;
    private final int onHfpRemoteSignalStrength;
    private final int onHfpRemoteTelecomService;
    private final int onHfpStateChanged;
    private final int onHfpVoiceDial;
    private final int retHfpRemoteNetworkOperator;
    private final int retHfpRemoteSubscriberNumber;

    public NfDoCallbackHfp(Context context) {
        super(context);
        this.onHfpStateChanged = 1;
        this.onHfpAudioStateChanged = 2;
        this.onHfpVoiceDial = 3;
        this.retHfpRemoteNetworkOperator = 4;
        this.retHfpRemoteSubscriberNumber = 5;
        this.onHfpRemoteTelecomService = 6;
        this.onHfpRemoteRoamingStatus = 7;
        this.onHfpRemoteBatteryIndicator = 8;
        this.onHfpRemoteSignalStrength = 9;
        this.onHfpCallChanged = 10;
    }

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected String getLogTag() {
        return "NfDoCallbackHfp";
    }

    public void onHfpStateChanged(String address, int prevState, int newState) {
        NfLog.d(this.TAG, "onHfpStateChanged() " + prevState + " -> " + newState);
        Message m = getMessage(1);
        m.arg1 = prevState;
        m.arg2 = newState;
        m.obj = address;
        enqueueMessage(m);
    }

    public void onHfpAudioStateChanged(String address, int prevState, int newState) {
        NfLog.d(this.TAG, "onHfpAudioStateChanged() " + prevState + " -> " + newState);
        Message m = getMessage(2);
        m.obj = address;
        m.arg1 = prevState;
        m.arg2 = newState;
        enqueueMessage(m);
    }

    public void onHfpVoiceDial(String address, boolean isVoiceDialOn) {
        NfLog.d(this.TAG, "onHfpVoiceDial() " + address);
        Message m = getMessage(3);
        m.obj = address;
        Bundle b = new Bundle();
        b.putBoolean("isVoiceDialOn", isVoiceDialOn);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retHfpRemoteNetworkOperator(String address, String mode, int format, String operator) {
        NfLog.d(this.TAG, "retHfpRemoteNetworkOperator() " + address);
        Message m = getMessage(4);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("mode", mode);
        b.putInt("format", format);
        b.putString("operator", operator);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retHfpRemoteSubscriberNumber(String address, String number, int type, int service) {
        NfLog.d(this.TAG, "retHfpRemoteSubscriberNumber() " + address);
        Message m = getMessage(5);
        m.obj = address;
        Bundle b = new Bundle();
        b.putString("number", number);
        b.putInt("type", type);
        b.putInt("service", service);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onHfpRemoteTelecomService(String address, boolean isTelecomServiceOn) {
        NfLog.d(this.TAG, "onHfpRemoteTelecomService() " + address);
        Message m = getMessage(6);
        m.obj = address;
        Bundle b = new Bundle();
        b.putBoolean("isTelecomServiceOn", isTelecomServiceOn);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onHfpRemoteRoamingStatus(String address, boolean isRoamingOn) {
        NfLog.d(this.TAG, "onHfpRemoteRoamingStatus() " + address);
        Message m = getMessage(7);
        m.obj = address;
        Bundle b = new Bundle();
        b.putBoolean("isRoamingOn", isRoamingOn);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onHfpRemoteBatteryIndicator(String address, int currentValue, int maxValue, int minValue) {
        NfLog.d(this.TAG, "onHfpRemoteBatteryIndicator() " + address);
        Message m = getMessage(8);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("currentValue", currentValue);
        b.putInt("maxValue", maxValue);
        b.putInt("minValue", minValue);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onHfpRemoteSignalStrength(String address, int currentStrength, int maxStrength, int minStrength) {
        NfLog.d(this.TAG, "onHfpRemoteSignalStrength() " + address);
        Message m = getMessage(9);
        m.obj = address;
        Bundle b = new Bundle();
        b.putInt("currentStrength", currentStrength);
        b.putInt("maxStrength", maxStrength);
        b.putInt("minStrength", minStrength);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onHfpCallChanged(String address, NfHfpClientCall cc) {
        NfLog.d(this.TAG, "onHfpCallChanged() " + address);
        Message m = getMessage(10);
        m.obj = address;
        Bundle b = new Bundle();
        b.putParcelable("cc", cc);
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
                    callbackOnHfpStateChanged(address, prevState, newState);
                    break;
                case 2:
                    callbackOnHfpAudioStateChanged(address, prevState, newState);
                    break;
                case 3:
                    callbackOnHfpVoiceDial(address, b.getBoolean("isVoiceDialOn"));
                    break;
                case 4:
                case 5:
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    break;
                case 6:
                    callbackOnHfpRemoteTelecomService(address, b.getBoolean("isTelecomServiceOn"));
                    break;
                case 7:
                    callbackOnHfpRemoteRoamingStatus(address, b.getBoolean("isRoamingOn"));
                    break;
                case 8:
                    callbackOnHfpRemoteBatteryIndicator(address, b.getInt("currentValue"), b.getInt("maxValue"), b.getInt("minValue"));
                    break;
                case 9:
                    callbackOnHfpRemoteSignalStrength(address, b.getInt("currentStrength"), b.getInt("maxStrength"), b.getInt("minStrength"));
                    break;
                case 10:
                    callbackOnHfpCallChanged(address, (NfHfpClientCall) b.getParcelable("cc"));
                    break;
            }
        }
        if (NfConfig.isCallbackByBroadcast()) {
            switch (msg.what) {
                case 1:
                    broadcastOnHfpStateChanged(address, prevState, newState);
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.callback.NfDoCallback
    public synchronized void callbackOnServiceReady(INfCallbackHfp cb) throws RemoteException {
        cb.onHfpServiceReady();
    }

    private synchronized void callbackOnHfpStateChanged(String address, int prevState, int newState) {
        NfLog.v(this.TAG, "callbackOnHfpStateChanged() " + address + " state: " + prevState + "->" + newState);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onHfpStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackHfp) this.mRemoteCallbacks.getBroadcastItem(i)).onHfpStateChanged(address, prevState, newState);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onHfpStateChanged CallBack Finish.");
    }

    private synchronized void callbackOnHfpAudioStateChanged(String address, int prevState, int newState) {
        NfLog.v(this.TAG, "callbackOnHfpAudioStateChanged() " + address + " state: " + prevState + "->" + newState);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onHfpAudioStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackHfp) this.mRemoteCallbacks.getBroadcastItem(i)).onHfpAudioStateChanged(address, prevState, newState);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onHfpScoStateChanged CallBack Finish.");
    }

    private synchronized void callbackOnHfpVoiceDial(String address, boolean isVoiceDialOn) {
        NfLog.v(this.TAG, "callbackOnHfpVoiceDial() " + address + " isVoiceDialOn: " + isVoiceDialOn);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onHfpVoiceDial beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackHfp) this.mRemoteCallbacks.getBroadcastItem(i)).onHfpVoiceDial(address, isVoiceDialOn);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onHfpStateChanged CallBack Finish.");
    }

    private synchronized void callbackOnHfpRemoteTelecomService(String address, boolean isTelecomServiceOn) {
        NfLog.v(this.TAG, "callbackOnHfpRemoteTelecomService() " + address + " isTelecomServiceOn: " + isTelecomServiceOn);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onHfpRemoteTelecomService beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackHfp) this.mRemoteCallbacks.getBroadcastItem(i)).onHfpRemoteTelecomService(address, isTelecomServiceOn);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onHfpRemoteTelecomService CallBack Finish.");
    }

    private synchronized void callbackOnHfpRemoteRoamingStatus(String address, boolean isRoamingOn) {
        NfLog.v(this.TAG, "callbackOnHfpRemoteRoamingStatus() " + address + " isRoamingOn: " + isRoamingOn);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onHfpRemoteRoamingStatus beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackHfp) this.mRemoteCallbacks.getBroadcastItem(i)).onHfpRemoteRoamingStatus(address, isRoamingOn);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onHfpRemoteRoamingStatus CallBack Finish.");
    }

    private synchronized void callbackOnHfpRemoteBatteryIndicator(String address, int currentValue, int maxValue, int minValue) {
        NfLog.v(this.TAG, "callbackOnHfpRemoteBatteryIndicator() " + address + " currentValue: " + currentValue + " (" + minValue + "-" + maxValue + ")");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onHfpRemoteBatteryIndicator beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackHfp) this.mRemoteCallbacks.getBroadcastItem(i)).onHfpRemoteBatteryIndicator(address, currentValue, maxValue, minValue);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onHfpRemoteBatteryIndicator CallBack Finish.");
    }

    private synchronized void callbackOnHfpRemoteSignalStrength(String address, int currentStrength, int maxStrength, int minStrength) {
        NfLog.v(this.TAG, "callbackOnHfpRemoteSignalStrength() " + address + " currentStrength: " + currentStrength + " (" + maxStrength + "-" + minStrength + ")");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onHfpRemoteSignalStrength beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackHfp) this.mRemoteCallbacks.getBroadcastItem(i)).onHfpRemoteSignalStrength(address, currentStrength, maxStrength, minStrength);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onHfpRemoteSignalStrength CallBack Finish.");
    }

    private synchronized void callbackOnHfpCallChanged(String address, NfHfpClientCall cc) {
        NfLog.v(this.TAG, "callbackOnHfpCallChanged() " + address);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onHfpCallChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackHfp) this.mRemoteCallbacks.getBroadcastItem(i)).onHfpCallChanged(address, cc);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onHfpCallChanged CallBack Finish.");
    }

    private synchronized void broadcastOnHfpStateChanged(String address, int prevState, int newState) {
        NfLog.v(this.TAG, "broadcastOnHfpStateChanged() " + address + " state: " + prevState + "->" + newState);
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
            case 140:
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
            case 140:
                newState = 2;
                break;
        }
        Intent intent = new Intent(NfDefBroadcastHfp.ACTION_CONNECTION_STATE_CHANGED);
        BluetoothDevice device = NfPrimitive.getDevice(address);
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onHfpStateChanged broadcast Finish.");
    }

    private synchronized void broadcastOnHfpAudioStateChanged(String address, int prevState, int newState) {
        NfLog.v(this.TAG, "broadcastOnHfpAudioStateChanged() " + address + " state: " + prevState + "->" + newState);
        switch (prevState) {
            case 110:
                prevState = 0;
                break;
            case 120:
                prevState = 1;
                break;
            case 140:
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
            case 140:
                newState = 2;
                break;
        }
        BluetoothDevice device = NfPrimitive.getDevice(address);
        Intent intent = new Intent(NfDefBroadcastHfp.ACTION_AUDIO_STATE_CHANGED);
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onHfpScoStateChanged broadcast Finish.");
    }

    private synchronized void broadcastOnHfpVoiceDial(String address, boolean isVoiceDialOn) {
        NfLog.v(this.TAG, "broadcastOnHfpVoiceDial() " + address + " isVoiceDialOn: " + isVoiceDialOn);
        BluetoothDevice device = NfPrimitive.getDevice(address);
        Intent intent = new Intent(NfDefBroadcastHfp.ACTION_VOICE_DAIL_CHANGED);
        intent.putExtra(NfDefBroadcastHfp.EXTRA_VOICE_DIAL_VALUE, isVoiceDialOn);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onHfpStateChanged broadcast Finish.");
    }

    private synchronized void broadcastOnHfpRemoteTelecomService(String address, boolean isTelecomServiceOn) {
        NfLog.v(this.TAG, "broadcastOnHfpRemoteTelecomService() " + address + " isTelecomServiceOn: " + isTelecomServiceOn);
        BluetoothDevice device = NfPrimitive.getDevice(address);
        Intent intent = new Intent(NfDefBroadcastHfp.ACTION_AG_EVENT);
        intent.putExtra(NfDefBroadcastHfp.EXTRA_NETWORK_STATUS, isTelecomServiceOn ? 1 : 0);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onHfpRemoteTelecomService broadcast Finish.");
    }

    private synchronized void broadcastOnHfpRemoteRoamingStatus(String address, boolean isRoamingOn) {
        NfLog.v(this.TAG, "broadcastOnHfpRemoteRoamingStatus() " + address + " isRoamingOn: " + isRoamingOn);
        BluetoothDevice device = NfPrimitive.getDevice(address);
        Intent intent = new Intent(NfDefBroadcastHfp.ACTION_AG_EVENT);
        intent.putExtra(NfDefBroadcastHfp.EXTRA_NETWORK_ROAMING, isRoamingOn ? 1 : 0);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onHfpRemoteRoamingStatus broadcast Finish.");
    }

    private synchronized void broadcastOnHfpRemoteBatteryIndicator(String address, int currentValue, int maxValue, int minValue) {
        NfLog.v(this.TAG, "broadcastOnHfpRemoteBatteryIndicator() " + address + " currentValue: " + currentValue + " (" + minValue + "-" + maxValue + ")");
        BluetoothDevice device = NfPrimitive.getDevice(address);
        Intent intent = new Intent(NfDefBroadcastHfp.ACTION_AG_EVENT);
        intent.putExtra(NfDefBroadcastHfp.EXTRA_BATTERY_LEVEL, currentValue);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onHfpRemoteBatteryIndicator broadcast Finish.");
    }

    private synchronized void broadcastOnHfpRemoteSignalStrength(String address, int currentStrength, int maxStrength, int minStrength) {
        NfLog.v(this.TAG, "broadcastOnHfpRemoteSignalStrength() " + address + " currentStrength: " + currentStrength + " (" + maxStrength + "-" + minStrength + ")");
        BluetoothDevice device = NfPrimitive.getDevice(address);
        Intent intent = new Intent(NfDefBroadcastHfp.ACTION_AG_EVENT);
        intent.putExtra(NfDefBroadcastHfp.EXTRA_NETWORK_SIGNAL_STRENGTH, currentStrength);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onHfpRemoteSignalStrength broadcast Finish.");
    }

    private synchronized void broadcastOnHfpCallChanged(String address, NfHfpClientCall cc) {
        NfLog.v(this.TAG, "broadcastOnHfpCallChanged() " + address);
        BluetoothDevice device = NfPrimitive.getDevice(address);
        BluetoothHeadsetClientCall c = new BluetoothHeadsetClientCall(device, cc.getId(), cc.getState(), cc.getNumber(), cc.isMultiParty(), cc.isOutgoing(), false);
        Intent intent = new Intent(NfDefBroadcastHfp.ACTION_CALL_CHANGED);
        intent.addFlags(268435456);
        intent.putExtra(NfDefBroadcastHfp.EXTRA_CALL, c);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onHfpCallChanged broadcast Finish.");
    }
}
