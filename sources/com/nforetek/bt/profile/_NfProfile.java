package com.nforetek.bt.profile;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IInterface;
import android.os.Message;
import android.os.ParcelUuid;
import android.util.ArraySet;
import com.nforetek.bt.callback.NfDoCallback;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.manager.NfCallbackInterface;
import com.nforetek.bt.profile.bluetooth._NfBluetooth;
import com.nforetek.bt.res.NfDef;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes.dex */
public abstract class _NfProfile<E1 extends IInterface, E2 extends NfDoCallback<E1>, E3 extends NfCallbackInterface> {
    protected String TAG;
    private E2 mCallback;
    protected Handler mCommandHandler;
    HandlerThread mCommandHandlerThread;
    private Context mContext;
    private NfJni mJni;
    protected Handler mJniHandler;
    HandlerThread mJniHandlerThread;
    private E3 mManager;
    protected int mProfileCode;
    protected Handler mSetStateHandler;
    HandlerThread mSetStateHandlerThread;
    private byte[] lock = new byte[0];
    private HashMap<String, NfState> mStateDictionary = new HashMap<>();
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.nforetek.bt.profile._NfProfile.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.TIME_SET") || action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                NfPrimitive.updateLastSystemTime(System.currentTimeMillis());
                _NfProfile.this.onSystemTimeChanged();
            } else if (action.equals("android.bluetooth.device.action.SDP_RECORD")) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                ParcelUuid uuid = (ParcelUuid) intent.getParcelableExtra("android.bluetooth.device.extra.UUID");
                int l2capPsm = intent.getIntExtra("EXTRA_L2CAP_PSM", -1);
                _NfProfile.this.onSdpUpdated(device.getAddress(), uuid, l2capPsm);
            } else if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                _NfProfile.this.onBtOff();
            } else if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                BluetoothDevice device2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int prevBondState = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", Integer.MIN_VALUE);
                int bondState = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                if (prevBondState >= 11 && bondState == 10) {
                    _NfProfile.this.onBtDeviceUnbonded(device2.getAddress());
                }
            }
        }
    };

    protected abstract void dequeueMessage(Message message);

    protected abstract String getLogTag();

    protected abstract int getProfileCode();

    public abstract void onStateChangedCallback(String str, int i, int i2, int i3, int i4, int i5);

    protected void dequeueCommandMessage(Message msg) {
    }

    protected void dequeueSetStateMessage(String address, int prevState, int state, int storage, int reason, int count) {
        processSetState(address, prevState, state, storage, reason, count);
    }

    public void onDestroy() {
        NfLog.i(this.TAG, "onDestroy()");
        this.mCallback = null;
        this.mJni = null;
        this.mManager = null;
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
        this.mContext = null;
        this.mJniHandlerThread.getLooper().quit();
        this.mJniHandlerThread = null;
        this.mCommandHandlerThread.getLooper().quit();
        this.mCommandHandlerThread = null;
        this.mSetStateHandlerThread.getLooper().quit();
        this.mSetStateHandlerThread = null;
        this.mJniHandler.removeCallbacksAndMessages(null);
        this.mJniHandler = null;
        forceResetState();
    }

    protected int getMaxDeviceCount() {
        return 1;
    }

    public void onBtAclDisconnected(String address) {
        NfLog.i(this.TAG, "onBtAclDisconnected(): " + address);
        setDownloadState(address, 110, -1, 2, 0);
    }

    public void forceResetState() {
        NfLog.i(this.TAG, "forceResetState()");
        synchronized (this.lock) {
            Set<String> keySet = new CopyOnWriteArraySet<>(this.mStateDictionary.keySet());
            for (String key : keySet) {
                NfLog.v(this.TAG, "Device : " + key + " force set state to ready.");
                setState(key, 110);
            }
        }
    }

    public void onCreate(Context context, E2 callback, NfJni jni, E3 c) {
        this.mJni = jni;
        onCreate(context, callback, c);
    }

    public void onCreate(Context context, E2 callback, E3 c) {
        this.mManager = c;
        this.mCallback = callback;
        this.mContext = context;
        onCreate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCreate() {
        this.TAG = getLogTag();
        this.mJniHandlerThread = new HandlerThread(String.valueOf(this.TAG) + "JniHandlerThread");
        this.mJniHandlerThread.start();
        this.mCommandHandlerThread = new HandlerThread(String.valueOf(this.TAG) + "CommandHandlerThread");
        this.mCommandHandlerThread.start();
        this.mSetStateHandlerThread = new HandlerThread(String.valueOf(this.TAG) + "SetStateHandlerThread");
        this.mSetStateHandlerThread.start();
        this.mJniHandler = initJniHandler();
        this.mCommandHandler = initCommandHandler();
        this.mSetStateHandler = initSetStateHandler();
        this.mProfileCode = getProfileCode();
        setJniCallback();
        NfLog.i(this.TAG, "onCreate()");
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.TIMEZONE_CHANGED");
        filter.addAction("android.intent.action.TIME_SET");
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        filter.addAction("android.bluetooth.device.action.SDP_RECORD");
        this.mContext.registerReceiver(this.mBroadcastReceiver, filter);
        setState(NfDef.DEFAULT_ADDRESS, 110);
    }

    private Handler initJniHandler() {
        return this.mJniHandler == null ? new Handler(this.mJniHandlerThread.getLooper()) { // from class: com.nforetek.bt.profile._NfProfile.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                NfLog.v(_NfProfile.this.TAG, "handleMessage: " + msg.what);
                _NfProfile.this.dequeueMessage(msg);
            }
        } : this.mJniHandler;
    }

    public void setJniCallback() {
        if (this.mJni != null) {
            this.mJni.setCallbackHandler(this.mProfileCode, this.mJniHandler);
        } else {
            NfLog.e(this.TAG, "mJni is null !!");
        }
    }

    private Handler initCommandHandler() {
        return this.mCommandHandler == null ? new Handler(this.mCommandHandlerThread.getLooper()) { // from class: com.nforetek.bt.profile._NfProfile.3
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                NfLog.v(_NfProfile.this.TAG, "handleCommandMessage: " + msg.what);
                _NfProfile.this.dequeueCommandMessage(msg);
            }
        } : this.mCommandHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendCommandMessage(int what) {
        Message m = this.mCommandHandler.obtainMessage(what);
        m.sendToTarget();
    }

    private Handler initSetStateHandler() {
        return this.mSetStateHandler == null ? new Handler(this.mSetStateHandlerThread.getLooper()) { // from class: com.nforetek.bt.profile._NfProfile.4
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String address = bundle.getString("address");
                int prevState = bundle.getInt("prevState");
                int state = bundle.getInt("state");
                int storage = bundle.getInt("storage");
                int reason = bundle.getInt("reason");
                int count = bundle.getInt("count");
                _NfProfile.this.dequeueSetStateMessage(address, prevState, state, storage, reason, count);
            }
        } : this.mSetStateHandler;
    }

    private void sendSetStateMessage(String address, int prevState, int state, int storage, int reason, int count) {
        Message m = this.mSetStateHandler.obtainMessage();
        Bundle b = new Bundle();
        m.setTarget(this.mSetStateHandler);
        b.putString("address", address);
        b.putInt("prevState", prevState);
        b.putInt("state", state);
        b.putInt("storage", storage);
        b.putInt("reason", reason);
        b.putInt("count", count);
        m.setData(b);
        m.sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public E2 callback() {
        return this.mCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public NfJni jni() {
        return this.mJni;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Context context() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public E3 manager() {
        return this.mManager;
    }

    public void resetJni() {
        this.mJni = null;
    }

    public void setJni(NfJni jni) {
        this.mJni = jni;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSystemTimeChanged() {
    }

    protected void onSdpUpdated(String address, ParcelUuid uuid, int l2capPsm) {
    }

    protected void onBtOff() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBtDeviceUnbonded(String address) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setState(String address, int state) {
        setState(address, state, -1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setState(String address, int state, int reason) {
        setState(address, -1, state, reason);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setState(String address, int prevState, int state, int reason) {
        setState(address, prevState, state, -1, reason, -1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setDownloadState(String address, int state, int storage, int reason, int count) {
        setState(address, -1, state, storage, reason, count);
    }

    protected void setState(String address, int prevState, int state, int storage, int reason, int count) {
        sendSetStateMessage(address, prevState, state, storage, reason, count);
    }

    protected void processSetState(String address, int prevState, int state, int storage, int reason, int count) {
        int resultPrevState = 100;
        synchronized (this.lock) {
            if (address == null) {
                NfLog.d(this.TAG, "address is null, set it to NfDef.DEFAULT_ADDRESS");
                address = NfDef.DEFAULT_ADDRESS;
            }
            if (state < 0) {
                NfLog.e(this.TAG, "State invalid. newState is " + state + ". ignore it.");
            } else if (address.equals(NfDef.DEFAULT_ADDRESS) && state == 100) {
                NfLog.e(this.TAG, "address is " + address + ", state is " + state + ". Ignore this setState.");
            } else {
                if (!address.equals(NfDef.DEFAULT_ADDRESS)) {
                    NfLog.v(this.TAG, "setState() " + address + " state: " + prevState + "->" + state + " reason: " + reason);
                    if (state >= 140 && _NfBluetooth.isDeviceAclDisconnected(address)) {
                        NfLog.e(this.TAG, "Want to set state higher than CONNECTED but ACL disconnected! Ignore it.");
                        return;
                    } else if (NfPrimitive.getAdapterState() == 300 && state > 110) {
                        NfLog.e(this.TAG, "Want to set state upper then READY but BT is Off. Ignore it.");
                        return;
                    } else {
                        NfState s = getStateFromDictionaryDirectly(address);
                        if (s == null) {
                            if (state > 110) {
                                setStateToDictionaryDirectly(address, new NfState(state, prevState));
                                resultPrevState = 110;
                            } else if (state == 110) {
                                NfLog.v(this.TAG, "STATE_READY shifted, ignore it!.");
                                return;
                            }
                        } else if (s.getState() == state) {
                            NfLog.v(this.TAG, "Same state shifted, ignore it!.");
                            return;
                        } else {
                            resultPrevState = s.getState();
                            if (state <= 110) {
                                setStateToDictionaryDirectly(address, null);
                            } else {
                                s.setPrevState(s.getState());
                                s.setState(state);
                            }
                        }
                    }
                }
                onStateChangedCallback(address, resultPrevState, state, storage, reason, count);
            }
        }
    }

    public int getProfileState(String address) {
        int currentStateFromDictionaryDirectly;
        synchronized (this.lock) {
            currentStateFromDictionaryDirectly = getCurrentStateFromDictionaryDirectly(address);
        }
        return currentStateFromDictionaryDirectly;
    }

    public int getProfileState() {
        Set<String> keySet;
        int i = 110;
        new ArraySet();
        String firstAddress = "";
        synchronized (this.lock) {
            try {
                keySet = new CopyOnWriteArraySet<>(this.mStateDictionary.keySet());
            } catch (Throwable th) {
                th = th;
            }
            try {
                Iterator<String> it = keySet.iterator();
                if (it.hasNext()) {
                    String key = it.next();
                    firstAddress = key;
                }
                if (!firstAddress.equals("")) {
                    if (keySet.size() > 1) {
                        NfLog.e(this.TAG, "In getProfileState() Address set size > 1, size: " + keySet.size());
                        for (String key2 : keySet) {
                            NfLog.e(this.TAG, "Address: " + key2);
                        }
                        synchronized (this.lock) {
                            i = getCurrentStateFromDictionaryDirectly(firstAddress);
                        }
                    } else if (this.mStateDictionary.size() == 1) {
                        synchronized (this.lock) {
                            i = getCurrentStateFromDictionaryDirectly(firstAddress);
                        }
                    }
                }
                return i;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public String getConnectedAddress() {
        List<String> list = getConnectedAddressList();
        for (int i = 0; i < list.size(); i++) {
            try {
                NfLog.v(this.TAG, "ConnectedAddressList[" + i + "]: " + list.get(i));
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        return NfDef.DEFAULT_ADDRESS;
    }

    public String getConnectingAddress() {
        List<String> list = getConnectingAddressListDirectly();
        for (int i = 0; i < list.size(); i++) {
            try {
                NfLog.v(this.TAG, "ConnectingAddressList[" + i + "]: " + list.get(i));
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        return NfDef.DEFAULT_ADDRESS;
    }

    public boolean isNoAnyConnection() {
        return this.mStateDictionary.size() == 0;
    }

    public boolean isAnyConnectingDevices() {
        List<NfState> valueList;
        new ArrayList();
        synchronized (this.lock) {
            try {
                valueList = new CopyOnWriteArrayList<>(this.mStateDictionary.values());
            } catch (Throwable th) {
                th = th;
            }
            try {
                for (NfState value : valueList) {
                    if (value.getState() == 120) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public boolean isAnyConnectedDevices() {
        boolean result;
        synchronized (this.lock) {
            List<String> connectedList = getConnectedAddressListDirectly();
            result = connectedList.size() > 0;
        }
        return result;
    }

    public List<String> getConnectedAddressList() {
        List<String> connectedAddressListDirectly;
        synchronized (this.lock) {
            connectedAddressListDirectly = getConnectedAddressListDirectly();
        }
        return connectedAddressListDirectly;
    }

    private List<String> getConnectedAddressListDirectly() {
        List<String> connectedList = new ArrayList<>();
        for (Map.Entry<String, NfState> entry : this.mStateDictionary.entrySet()) {
            String key = entry.getKey();
            NfState value = entry.getValue();
            if (value.getState() >= 140) {
                connectedList.add(key);
            }
        }
        return connectedList;
    }

    private List<String> getConnectingAddressListDirectly() {
        List<String> connectingList = new ArrayList<>();
        for (Map.Entry<String, NfState> entry : this.mStateDictionary.entrySet()) {
            String key = entry.getKey();
            NfState value = entry.getValue();
            if (value.getState() == 120) {
                connectingList.add(key);
            }
        }
        return connectingList;
    }

    public boolean isDeviceConnected(String address) {
        boolean contains;
        synchronized (this.lock) {
            List<String> connectedList = getConnectedAddressListDirectly();
            contains = connectedList.contains(address);
        }
        return contains;
    }

    private void setStateToDictionaryDirectly(String address, NfState state) {
        try {
            if (state == null) {
                this.mStateDictionary.remove(address);
            } else {
                this.mStateDictionary.put(address, state);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
    }

    private NfState getStateFromDictionaryDirectly(String address) {
        try {
            NfState state = this.mStateDictionary.get(address);
            return state;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private int getCurrentStateFromDictionaryDirectly(String address) {
        NfState state = getStateFromDictionaryDirectly(address);
        if (state != null) {
            return state.getState();
        }
        return 110;
    }

    private int getPrevStateFromDictionaryDirectly(String address) {
        NfState state = getStateFromDictionaryDirectly(address);
        if (state != null) {
            return state.getPrevState();
        }
        return 110;
    }

    public void forceResetStateDictionary() {
        if (this.mStateDictionary != null) {
            this.mStateDictionary.clear();
        }
    }
}
