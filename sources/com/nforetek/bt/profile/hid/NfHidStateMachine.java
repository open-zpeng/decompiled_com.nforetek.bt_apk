package com.nforetek.bt.profile.hid;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.util.Log;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.BluetoothUuid;
import com.nforetek.bt.res.bt.IState;
import com.nforetek.bt.res.bt.State;
import com.nforetek.bt.res.bt.StateMachine;
import com.nforetek.util.normal.NfUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class NfHidStateMachine extends StateMachine {
    static final int AUDIO_STATE_REMOTE_SUSPEND = 0;
    static final int AUDIO_STATE_STARTED = 2;
    static final int AUDIO_STATE_STOPPED = 1;
    static final int CONNECT = 1;
    static final int CONNECTION_STATE_CONNECTED = 3;
    static final int CONNECTION_STATE_CONNECTING = 1;
    static final int CONNECTION_STATE_DISCONNECTED = 0;
    static final int CONNECTION_STATE_DISCONNECTING = 2;
    static final int CONNECTION_STATE_STREAMING = 4;
    private static final int CONNECT_TIMEOUT = 201;
    private static final boolean DBG = false;
    static final int DISCONNECT = 2;
    private static final int EVENT_TYPE_AUDIO_STATE_CHANGED = 2;
    private static final int EVENT_TYPE_CONNECTION_STATE_CHANGED = 1;
    private static final int EVENT_TYPE_NONE = 0;
    private static final ParcelUuid[] HID_UUIDS = {BluetoothUuid.Hid};
    private static final int MSG_CONNECTION_STATE_CHANGED = 0;
    private static final int STACK_EVENT = 101;
    private BluetoothAdapter mAdapter;
    private Connected mConnected;
    private BluetoothDevice mCurrentDevice;
    private Disconnected mDisconnected;
    private _NfHid mHid;
    private BluetoothDevice mIncomingDevice;
    private IntentBroadcastHandler mIntentBroadcastHandler;
    private NfJni mJni;
    private Pending mPending;
    private BluetoothDevice mPlayingHidDevice;
    private BluetoothDevice mTargetDevice;

    private NfHidStateMachine(_NfHid svc, NfJni jni) {
        super("NfHidStateMachine");
        this.mCurrentDevice = null;
        this.mTargetDevice = null;
        this.mIncomingDevice = null;
        this.mPlayingHidDevice = null;
        this.mHid = svc;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mDisconnected = new Disconnected(this, null);
        this.mPending = new Pending(this, null);
        this.mConnected = new Connected(this, null);
        addState(this.mDisconnected);
        addState(this.mPending);
        addState(this.mConnected);
        setInitialState(this.mDisconnected);
        this.mJni = jni;
        this.mIntentBroadcastHandler = new IntentBroadcastHandler(this, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NfHidStateMachine make(_NfHid svc, NfJni jni) {
        Log.d("NfHidStateMachine", "make");
        NfHidStateMachine hidSm = new NfHidStateMachine(svc, jni);
        hidSm.start();
        return hidSm;
    }

    public void doQuit() {
        quitNow();
        this.mJni = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class Disconnected extends State {
        private Disconnected() {
        }

        /* synthetic */ Disconnected(NfHidStateMachine nfHidStateMachine, Disconnected disconnected) {
            this();
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public void enter() {
            NfHidStateMachine.this.log("NfHidStateMachine - Enter Disconnected: " + NfHidStateMachine.this.getCurrentMessage().what);
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public boolean processMessage(Message message) {
            NfHidStateMachine.this.log("Disconnected process message: " + message.what);
            if (NfHidStateMachine.this.mCurrentDevice != null || NfHidStateMachine.this.mTargetDevice != null || NfHidStateMachine.this.mIncomingDevice != null) {
                NfHidStateMachine.this.loge("ERROR: current, target, or mIncomingDevice not null in Disconnected");
                return false;
            }
            switch (message.what) {
                case 1:
                    BluetoothDevice device = (BluetoothDevice) message.obj;
                    NfHidStateMachine.this.broadcastConnectionState(device, 120, 110);
                    if (!NfHidStateMachine.this.mJni.reqHidConnect(device.getAddress())) {
                        synchronized (NfHidStateMachine.this) {
                            NfHidStateMachine.this.mTargetDevice = device;
                            NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mPending);
                        }
                        NfHidStateMachine.this.sendMessageDelayed(201, 30000L);
                        return true;
                    }
                    NfHidStateMachine.this.broadcastConnectionState(device, 110, 120);
                    return true;
                case 2:
                    return true;
                case 101:
                    StackEvent event = (StackEvent) message.obj;
                    switch (event.type) {
                        case 1:
                            processConnectionEvent(event.valueInt, event.device);
                            return true;
                        default:
                            NfHidStateMachine.this.loge("Unexpected stack event: " + event.type);
                            return true;
                    }
                default:
                    return false;
            }
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public void exit() {
            NfHidStateMachine.this.log("Exit Disconnected: " + NfHidStateMachine.this.getCurrentMessage().what);
        }

        private void processConnectionEvent(int state, BluetoothDevice device) {
            switch (state) {
                case 0:
                    NfHidStateMachine.this.logw("Ignore HF DISCONNECTED event, device: " + device);
                    return;
                case 1:
                    if (NfHidStateMachine.this.okToConnect(device)) {
                        NfHidStateMachine.this.logi("Incoming HID accepted");
                        NfHidStateMachine.this.broadcastConnectionState(device, 120, 110);
                        synchronized (NfHidStateMachine.this) {
                            NfHidStateMachine.this.mIncomingDevice = device;
                            NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mPending);
                        }
                        return;
                    }
                    NfHidStateMachine.this.logi("Incoming HID rejected");
                    NfHidStateMachine.this.mJni.reqHidDisconnect(device.getAddress());
                    NfHidStateMachine.this.mHid.disconnect(device.getAddress());
                    return;
                case 2:
                    NfHidStateMachine.this.logw("Ignore HID DISCONNECTING event, device: " + device);
                    return;
                case 3:
                    NfHidStateMachine.this.logw("HID Connected from Disconnected state");
                    if (NfHidStateMachine.this.okToConnect(device)) {
                        NfHidStateMachine.this.logi("Incoming HID accepted");
                        NfHidStateMachine.this.broadcastConnectionState(device, 140, 110);
                        synchronized (NfHidStateMachine.this) {
                            NfHidStateMachine.this.mCurrentDevice = device;
                            NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mConnected);
                        }
                        return;
                    }
                    NfHidStateMachine.this.logi("Incoming HID rejected");
                    NfHidStateMachine.this.mHid.disconnect(device.getAddress());
                    return;
                default:
                    NfHidStateMachine.this.loge("Incorrect state: " + state);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class Pending extends State {
        private Pending() {
        }

        /* synthetic */ Pending(NfHidStateMachine nfHidStateMachine, Pending pending) {
            this();
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public void enter() {
            NfHidStateMachine.this.log("NfHidStateMachine - Enter Pending: " + NfHidStateMachine.this.getCurrentMessage().what);
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public boolean processMessage(Message message) {
            NfHidStateMachine.this.log("Pending process message: " + message.what);
            switch (message.what) {
                case 1:
                    NfHidStateMachine.this.deferMessage(message);
                    return true;
                case 2:
                    BluetoothDevice device = (BluetoothDevice) message.obj;
                    if (NfHidStateMachine.this.mCurrentDevice == null || NfHidStateMachine.this.mTargetDevice == null || !NfHidStateMachine.this.mTargetDevice.equals(device)) {
                        NfHidStateMachine.this.deferMessage(message);
                        return true;
                    }
                    NfHidStateMachine.this.broadcastConnectionState(device, 110, 120);
                    synchronized (NfHidStateMachine.this) {
                        NfHidStateMachine.this.mTargetDevice = null;
                    }
                    return true;
                case 101:
                    StackEvent event = (StackEvent) message.obj;
                    switch (event.type) {
                        case 1:
                            NfHidStateMachine.this.removeMessages(201);
                            processConnectionEvent(event.valueInt, event.device);
                            return true;
                        default:
                            NfHidStateMachine.this.loge("Unexpected stack event: " + event.type);
                            return true;
                    }
                case 201:
                    NfHidStateMachine.this.onConnectionStateChanged(0, NfHidStateMachine.this.getByteAddress(NfHidStateMachine.this.mTargetDevice));
                    return true;
                default:
                    return false;
            }
        }

        private void processConnectionEvent(int state, BluetoothDevice device) {
            switch (state) {
                case 0:
                    if (NfHidStateMachine.this.mCurrentDevice == null || !NfHidStateMachine.this.mCurrentDevice.equals(device)) {
                        if (NfHidStateMachine.this.mTargetDevice == null || !NfHidStateMachine.this.mTargetDevice.equals(device)) {
                            if (NfHidStateMachine.this.mIncomingDevice == null || !NfHidStateMachine.this.mIncomingDevice.equals(device)) {
                                NfHidStateMachine.this.loge("Unknown device Disconnected: " + device);
                                return;
                            }
                            NfHidStateMachine.this.broadcastConnectionState(NfHidStateMachine.this.mIncomingDevice, 110, 120);
                            synchronized (NfHidStateMachine.this) {
                                NfHidStateMachine.this.mIncomingDevice = null;
                                NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mDisconnected);
                            }
                            return;
                        }
                        NfHidStateMachine.this.broadcastConnectionState(NfHidStateMachine.this.mTargetDevice, 110, 120);
                        synchronized (NfHidStateMachine.this) {
                            NfHidStateMachine.this.mTargetDevice = null;
                            NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mDisconnected);
                        }
                        return;
                    }
                    NfHidStateMachine.this.broadcastConnectionState(NfHidStateMachine.this.mCurrentDevice, 110, NfDef.STATE_DISCONNECTING);
                    synchronized (NfHidStateMachine.this) {
                        NfHidStateMachine.this.mCurrentDevice = null;
                    }
                    if (NfHidStateMachine.this.mTargetDevice != null) {
                        if (NfHidStateMachine.this.mJni.reqHidConnect(device.getAddress())) {
                            return;
                        }
                        NfHidStateMachine.this.broadcastConnectionState(NfHidStateMachine.this.mTargetDevice, 110, 120);
                        synchronized (NfHidStateMachine.this) {
                            NfHidStateMachine.this.mTargetDevice = null;
                            NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mDisconnected);
                        }
                        return;
                    }
                    synchronized (NfHidStateMachine.this) {
                        NfHidStateMachine.this.mIncomingDevice = null;
                        NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mDisconnected);
                    }
                    return;
                case 1:
                    if (NfHidStateMachine.this.mCurrentDevice != null && NfHidStateMachine.this.mCurrentDevice.equals(device)) {
                        NfHidStateMachine.this.log("current device tries to connect back");
                        return;
                    } else if (NfHidStateMachine.this.mTargetDevice != null && NfHidStateMachine.this.mTargetDevice.equals(device)) {
                        NfHidStateMachine.this.log("Stack and target device are connecting");
                        return;
                    } else if (NfHidStateMachine.this.mIncomingDevice == null || !NfHidStateMachine.this.mIncomingDevice.equals(device)) {
                        NfHidStateMachine.this.log("Incoming connection while pending, ignore");
                        return;
                    } else {
                        NfHidStateMachine.this.loge("Another connecting event on the incoming device");
                        return;
                    }
                case 2:
                    if (NfHidStateMachine.this.mCurrentDevice == null || !NfHidStateMachine.this.mCurrentDevice.equals(device)) {
                        if (NfHidStateMachine.this.mTargetDevice != null && NfHidStateMachine.this.mTargetDevice.equals(device)) {
                            NfHidStateMachine.this.loge("TargetDevice is getting disconnected");
                            return;
                        } else if (NfHidStateMachine.this.mIncomingDevice == null || !NfHidStateMachine.this.mIncomingDevice.equals(device)) {
                            NfHidStateMachine.this.loge("Disconnecting unknow device: " + device);
                            return;
                        } else {
                            NfHidStateMachine.this.loge("IncomingDevice is getting disconnected");
                            return;
                        }
                    }
                    return;
                case 3:
                    if (NfHidStateMachine.this.mCurrentDevice == null || !NfHidStateMachine.this.mCurrentDevice.equals(device)) {
                        if (NfHidStateMachine.this.mTargetDevice == null || !NfHidStateMachine.this.mTargetDevice.equals(device)) {
                            if (NfHidStateMachine.this.mIncomingDevice == null || !NfHidStateMachine.this.mIncomingDevice.equals(device)) {
                                NfHidStateMachine.this.loge("Unknown device Connected: " + device);
                                NfHidStateMachine.this.broadcastConnectionState(device, 140, 110);
                                synchronized (NfHidStateMachine.this) {
                                    NfHidStateMachine.this.mCurrentDevice = device;
                                    NfHidStateMachine.this.mTargetDevice = null;
                                    NfHidStateMachine.this.mIncomingDevice = null;
                                    NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mConnected);
                                }
                                return;
                            }
                            NfHidStateMachine.this.broadcastConnectionState(NfHidStateMachine.this.mIncomingDevice, 140, 120);
                            synchronized (NfHidStateMachine.this) {
                                NfHidStateMachine.this.mCurrentDevice = NfHidStateMachine.this.mIncomingDevice;
                                NfHidStateMachine.this.mIncomingDevice = null;
                                NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mConnected);
                            }
                            return;
                        }
                        NfHidStateMachine.this.broadcastConnectionState(NfHidStateMachine.this.mTargetDevice, 140, 120);
                        synchronized (NfHidStateMachine.this) {
                            NfHidStateMachine.this.mCurrentDevice = NfHidStateMachine.this.mTargetDevice;
                            NfHidStateMachine.this.mTargetDevice = null;
                            NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mConnected);
                        }
                        return;
                    }
                    NfHidStateMachine.this.broadcastConnectionState(NfHidStateMachine.this.mCurrentDevice, 140, NfDef.STATE_DISCONNECTING);
                    if (NfHidStateMachine.this.mTargetDevice != null) {
                        NfHidStateMachine.this.broadcastConnectionState(NfHidStateMachine.this.mTargetDevice, 110, 120);
                    }
                    synchronized (NfHidStateMachine.this) {
                        NfHidStateMachine.this.mTargetDevice = null;
                        NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mConnected);
                    }
                    return;
                default:
                    NfHidStateMachine.this.loge("Incorrect state: " + state);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class Connected extends State {
        private Connected() {
        }

        /* synthetic */ Connected(NfHidStateMachine nfHidStateMachine, Connected connected) {
            this();
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public void enter() {
            NfHidStateMachine.this.log("NfHidStateMachine - Enter Connected: " + NfHidStateMachine.this.getCurrentMessage().what);
        }

        @Override // com.nforetek.bt.res.bt.State, com.nforetek.bt.res.bt.IState
        public boolean processMessage(Message message) {
            NfHidStateMachine.this.log("Connected process message: " + message.what);
            if (NfHidStateMachine.this.mCurrentDevice == null) {
                NfHidStateMachine.this.loge("ERROR: mCurrentDevice is null in Connected");
                return false;
            }
            switch (message.what) {
                case 1:
                    BluetoothDevice device = (BluetoothDevice) message.obj;
                    if (NfHidStateMachine.this.mCurrentDevice.equals(device)) {
                        return true;
                    }
                    NfHidStateMachine.this.broadcastConnectionState(device, 120, 110);
                    if (NfHidStateMachine.this.mJni.reqHidDisconnect(device.getAddress())) {
                        synchronized (NfHidStateMachine.this) {
                            NfHidStateMachine.this.mTargetDevice = device;
                            NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mPending);
                        }
                        return true;
                    }
                    NfHidStateMachine.this.broadcastConnectionState(device, 110, 120);
                    return true;
                case 2:
                    BluetoothDevice device2 = (BluetoothDevice) message.obj;
                    if (!NfHidStateMachine.this.mCurrentDevice.equals(device2)) {
                        return true;
                    }
                    NfHidStateMachine.this.broadcastConnectionState(device2, NfDef.STATE_DISCONNECTING, 140);
                    if (NfHidStateMachine.this.mJni.reqHidDisconnect(device2.getAddress())) {
                        NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mPending);
                        return true;
                    }
                    NfHidStateMachine.this.broadcastConnectionState(device2, 140, 110);
                    return true;
                case 101:
                    StackEvent event = (StackEvent) message.obj;
                    switch (event.type) {
                        case 1:
                            processConnectionEvent(event.valueInt, event.device);
                            return true;
                        default:
                            NfHidStateMachine.this.loge("Unexpected stack event: " + event.type);
                            return true;
                    }
                default:
                    return false;
            }
        }

        private void processConnectionEvent(int state, BluetoothDevice device) {
            switch (state) {
                case 0:
                    if (!NfHidStateMachine.this.mCurrentDevice.equals(device)) {
                        NfHidStateMachine.this.loge("Disconnected from unknown device: " + device);
                        return;
                    }
                    NfHidStateMachine.this.broadcastConnectionState(NfHidStateMachine.this.mCurrentDevice, 110, 140);
                    synchronized (NfHidStateMachine.this) {
                        NfHidStateMachine.this.mCurrentDevice = null;
                        NfHidStateMachine.this.transitionTo(NfHidStateMachine.this.mDisconnected);
                    }
                    return;
                default:
                    NfHidStateMachine.this.loge("Connection State Device: " + device + " bad state: " + state);
                    return;
            }
        }
    }

    int getConnectionState(BluetoothDevice device) {
        int i = 110;
        if (getCurrentState() != this.mDisconnected) {
            synchronized (this) {
                IState currentState = getCurrentState();
                if (currentState == this.mPending) {
                    if (this.mTargetDevice != null && this.mTargetDevice.equals(device)) {
                        i = 120;
                    } else if (this.mCurrentDevice != null && this.mCurrentDevice.equals(device)) {
                        i = NfDef.STATE_DISCONNECTING;
                    } else if (this.mIncomingDevice != null && this.mIncomingDevice.equals(device)) {
                        i = 120;
                    }
                } else if (currentState == this.mConnected) {
                    if (this.mCurrentDevice.equals(device)) {
                        i = 140;
                    }
                } else {
                    loge("Bad currentState: " + currentState);
                }
            }
        }
        return i;
    }

    List<BluetoothDevice> getConnectedDevices() {
        List<BluetoothDevice> devices = new ArrayList<>();
        synchronized (this) {
            if (getCurrentState() == this.mConnected) {
                devices.add(this.mCurrentDevice);
            }
        }
        return devices;
    }

    boolean isPlaying(BluetoothDevice device) {
        synchronized (this) {
            return device.equals(this.mPlayingHidDevice);
        }
    }

    boolean okToConnect(BluetoothDevice device) {
        if (device.getBondState() == 10) {
            return false;
        }
        return true;
    }

    synchronized List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        List<BluetoothDevice> deviceList;
        deviceList = new ArrayList<>();
        Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
        for (BluetoothDevice device : bondedDevices) {
            ParcelUuid[] featureUuids = device.getUuids();
            if (BluetoothUuid.containsAnyUuid(featureUuids, HID_UUIDS)) {
                int connectionState = getConnectionState(device);
                for (int i : states) {
                    if (connectionState == i) {
                        deviceList.add(device);
                    }
                }
            }
        }
        return deviceList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastConnectionState(BluetoothDevice device, int newState, int prevState) {
        log("HID Connection state : device: " + device + " State:" + prevState + "->" + newState);
        this.mIntentBroadcastHandler.sendMessageDelayed(this.mIntentBroadcastHandler.obtainMessage(0, prevState, newState, device), (long) NfDef.BT_STATE_OFF);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getByteAddress(BluetoothDevice device) {
        return NfUtils.getBytesFromAddress(device.getAddress());
    }

    public void onConnectionStateChanged(int state, byte[] address) {
        StackEvent event = new StackEvent(this, 1, null);
        event.valueInt = state;
        event.device = getDevice(address);
        sendMessage(101, event);
    }

    private BluetoothDevice getDevice(byte[] address) {
        return this.mAdapter.getRemoteDevice(NfUtils.getAddressStringFromByte(address));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class StackEvent {
        BluetoothDevice device;
        int type;
        int valueInt;

        private StackEvent(int type) {
            this.type = 0;
            this.valueInt = 0;
            this.device = null;
            this.type = type;
        }

        /* synthetic */ StackEvent(NfHidStateMachine nfHidStateMachine, int i, StackEvent stackEvent) {
            this(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class IntentBroadcastHandler extends Handler {
        private IntentBroadcastHandler() {
        }

        /* synthetic */ IntentBroadcastHandler(NfHidStateMachine nfHidStateMachine, IntentBroadcastHandler intentBroadcastHandler) {
            this();
        }

        private void onConnectionStateChanged(BluetoothDevice device, int prevState, int state) {
            NfHidStateMachine.this.mHid.onConnectionStateChanged(device.getAddress(), prevState, state, 0);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    onConnectionStateChanged((BluetoothDevice) msg.obj, msg.arg1, msg.arg2);
                    return;
                default:
                    return;
            }
        }
    }
}
