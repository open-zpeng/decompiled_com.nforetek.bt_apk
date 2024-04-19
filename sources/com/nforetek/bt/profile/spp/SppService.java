package com.nforetek.bt.profile.spp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.nforetek.util.bt.NfPrimitive;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
/* loaded from: classes.dex */
public class SppService {
    private static final boolean DEBUG = true;
    public static final int MAX_CONNECTED_NUMBER = 7;
    public static ArrayList<SppConnectedThread> SppConnectedThreadList = null;
    private static final String TAG = "nFore_SppService";
    public static boolean isAcceptThreadAlive;
    public ConnectSppThread connectSppThread;
    public int mRfcommChannel;
    private SppAcceptThread mSppAcceptThread;
    private SppConnectedThread mSppConnectedThread;
    private SppDoCallBack mSppDoCallBack;
    private static final UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final UUID IAP_UUID = UUID.fromString("00000000-deca-fade-deca-deafdecacafe");
    private boolean bPendingOutgoingConnection = false;
    public BluetoothAdapter mAdapter = null;
    private Handler mServiceHandler = null;
    private boolean isDestroyingClass = false;
    private int mState = 110;
    boolean isNeedCallbackSppAppIapAuthenticationRequest = false;

    public SppService(Context context, SppDoCallBack sppDoCallBack) {
        Log.e(TAG, "Spp Service init()");
        this.mSppDoCallBack = sppDoCallBack;
        SppConnectedThreadList = new ArrayList<>();
        initProfile();
    }

    private void initProfile() {
        Log.d(TAG, "In initProfile");
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mSppAcceptThread = new SppAcceptThread(this, this.mSppDoCallBack);
        this.mSppAcceptThread.start();
    }

    public synchronized void closeConnectedThread(String mDeivceAddress) {
        synchronized (SppConnectedThreadList) {
            if (SppConnectedThreadList != null) {
                for (int i = 0; i < SppConnectedThreadList.size(); i++) {
                    if (SppConnectedThreadList.get(i).getsRemoteDeviceAddress().equals(mDeivceAddress)) {
                        SppConnectedThreadList.remove(i);
                    }
                }
            }
        }
    }

    public synchronized void addSppConnectedThread(SppConnectedThread sppConnectedThread) {
        synchronized (SppConnectedThreadList) {
            if (checkConnected(sppConnectedThread.getsRemoteDeviceAddress()) && SppConnectedThreadList != null) {
                SppConnectedThreadList.add(sppConnectedThread);
            }
        }
    }

    public ArrayList<SppConnectedThread> getSppConnectedThreadList() {
        return SppConnectedThreadList;
    }

    public boolean checkConnected(String sppDeviceAddress) {
        Iterator<SppConnectedThread> it = SppConnectedThreadList.iterator();
        while (it.hasNext()) {
            SppConnectedThread e = it.next();
            if (e.getsRemoteDeviceAddress().equals(sppDeviceAddress)) {
                return false;
            }
        }
        return true;
    }

    public synchronized boolean connectSppByThread(String address) {
        boolean z = true;
        synchronized (this) {
            if (this.connectSppThread != null) {
                Log.e(TAG, "Spp Connecting !!!");
                z = false;
            } else if (getConnectedDeviceCount() >= 7) {
                Log.e(TAG, "the number of connectedThread is greater than or equal to MAX_CONNECTED_NUMBER !!! ");
                this.mSppDoCallBack.onSppErrorResponse(address, 99);
                for (int i = SppConnectedThreadList.size() - 1; i > 6; i--) {
                    SppConnectedThreadList.get(i).setStopThread(true);
                }
                updateConnectedList();
                z = false;
            } else {
                Iterator<SppConnectedThread> it = SppConnectedThreadList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        SppConnectedThread e = it.next();
                        if (address.equals(e.getsRemoteDeviceAddress())) {
                            Log.e(TAG, "in connectSppByThread() : have same address connected !!!");
                            this.mSppDoCallBack.onSppErrorResponse(address, 100);
                            z = false;
                            break;
                        }
                    } else {
                        String deviceName = "";
                        if (this.mAdapter != null) {
                            deviceName = this.mAdapter.getRemoteDevice(address).getName();
                        }
                        setState(address, deviceName, 120);
                        this.connectSppThread = new ConnectSppThread(address);
                        this.connectSppThread.start();
                    }
                }
            }
        }
        return z;
    }

    /* loaded from: classes.dex */
    private class ConnectSppThread extends Thread {
        private String mAddress;

        public ConnectSppThread(String address) {
            Log.i(SppService.TAG, "ConnectSppThread init() : " + address);
            this.mAddress = address;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            SppService.this.connect(this.mAddress);
            SppService.this.connectSppThread = null;
        }
    }

    public synchronized void connect(String address) {
        BluetoothSocket mSocket;
        Log.d(TAG, " +++ Begin of connect() : " + address + " +++ ");
        if (address == null) {
            Log.e(TAG, "In connect() : BT address is invalid !!!");
        } else if (!checkConnected(address)) {
            Log.e(TAG, "In connect() : have same address connected !!!");
            this.mSppDoCallBack.onSppErrorResponse(address, 100);
        } else if (this.mAdapter == null) {
            Log.e(TAG, "In connect() : Default local BT adapter is NULL !!!");
            this.mSppDoCallBack.onSppErrorResponse(address, 95);
        } else {
            while (this.bPendingOutgoingConnection) {
                Log.d(TAG, "In connect() : Another outgoing connection establishment is running, wait !!!");
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    Log.e(TAG, "In connect() : Exception when doing sleep !!!");
                    e.printStackTrace();
                }
            }
            this.bPendingOutgoingConnection = true;
            BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
            if (device == null) {
                Log.d(TAG, "In connect() : Could not get remote device !!!");
                this.bPendingOutgoingConnection = false;
                this.mSppDoCallBack.onSppErrorResponse(address, 98);
            } else {
                try {
                    if ((NfPrimitive.getProfiles(address) & 8192) > 1) {
                        Log.e(TAG, "Piggy Check is iPhone device (iAP1) . connect specific UUID ");
                        mSocket = device.createRfcommSocketToServiceRecord(IAP_UUID);
                        setNeedCallbackSppIapAuthenticationRequest(true);
                    } else {
                        mSocket = device.createRfcommSocketToServiceRecord(SPP_UUID);
                    }
                    if (mSocket != null) {
                        Log.d(TAG, "In connect() : Begin to find the Null ConnectedThread to use ");
                        if (this.mAdapter.isDiscovering()) {
                            this.mAdapter.cancelDiscovery();
                        }
                        try {
                            mSocket.connect();
                            Log.e(TAG, "in connect : mSocket.connect()");
                            this.mSppConnectedThread = new SppConnectedThread(this, mSocket, this.mSppDoCallBack);
                            this.mSppConnectedThread.start();
                            this.bPendingOutgoingConnection = false;
                            addSppConnectedThread(this.mSppConnectedThread);
                            updateConnectedList();
                        } catch (IOException e1) {
                            Log.d(TAG, "In connect() : Exception when doing mSocket.connect()");
                            try {
                                mSocket.close();
                                setState(address, this.mAdapter.getRemoteDevice(address).getName(), 110);
                                Log.d(TAG, "In connect() : Connect fail, mSocket.close()");
                            } catch (IOException e2) {
                                Log.d(TAG, "In connect() : Exception when doing mSocket.close()");
                                e2.printStackTrace();
                            }
                            e1.printStackTrace();
                            this.bPendingOutgoingConnection = false;
                            this.mSppDoCallBack.onSppErrorResponse(address, 98);
                            setNeedCallbackSppIapAuthenticationRequest(false);
                        }
                    } else {
                        Log.e(TAG, "In connect() : ERROR !!! [mSocket == null]");
                        this.bPendingOutgoingConnection = false;
                        this.mSppDoCallBack.onSppErrorResponse(address, 98);
                    }
                } catch (IOException e3) {
                    Log.e(TAG, "In connect() : Exception when doing createRfcommSocketToServiceRecord() !!!");
                    this.bPendingOutgoingConnection = false;
                    this.mSppDoCallBack.onSppErrorResponse(address, 98);
                    setNeedCallbackSppIapAuthenticationRequest(false);
                }
            }
        }
    }

    public synchronized void disconnect(String address) {
        Log.d(TAG, " +++ Begin of disconnect() : " + address + " +++ ");
        if (address == null) {
            Log.e(TAG, "In disconnect() : BT address is invalid !!!");
        } else {
            synchronized (SppConnectedThreadList) {
                if (SppConnectedThreadList != null) {
                    for (int i = 0; i < SppConnectedThreadList.size(); i++) {
                        if (SppConnectedThreadList.get(i).getsRemoteDeviceAddress().equals(address)) {
                            SppConnectedThreadList.get(i).setStopThread(true);
                        }
                    }
                }
            }
            Log.d(TAG, " +++ End of disconnect() +++ ");
        }
    }

    public synchronized void sendData(String sppDeviceAddress, byte[] sppData) {
        if (sppData == null) {
            Log.v(TAG, "In sendData data is null");
        } else {
            Log.v(TAG, "In sendData() " + sppDeviceAddress);
            if (SppConnectedThreadList != null) {
                Iterator<SppConnectedThread> it = SppConnectedThreadList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    SppConnectedThread e = it.next();
                    if (e.getsRemoteDeviceAddress().equals(sppDeviceAddress)) {
                        e.sendMessage(sppData);
                        break;
                    }
                }
            }
        }
    }

    public synchronized boolean isConnected(String mSppDeviceAddress) {
        boolean z;
        if (SppConnectedThreadList != null) {
            synchronized (SppConnectedThreadList) {
                Iterator<SppConnectedThread> it = SppConnectedThreadList.iterator();
                while (it.hasNext()) {
                    SppConnectedThread e = it.next();
                    if (e.getsRemoteDeviceAddress().equals(mSppDeviceAddress)) {
                        Log.v(TAG, "Device " + mSppDeviceAddress + " is connected");
                        z = true;
                        break;
                    }
                }
            }
        }
        Log.v(TAG, "Device " + mSppDeviceAddress + " is NOT connected.");
        z = false;
        return z;
    }

    public synchronized boolean isConnected() {
        boolean z;
        if (SppConnectedThreadList != null) {
            z = SppConnectedThreadList.size() > 0;
        }
        return z;
    }

    public synchronized void setState(String address, String name, int state) {
        Log.d(TAG, "setState() " + name + "(" + address + ") : " + this.mState + " -> " + state);
        int preState = this.mState;
        if (state != preState) {
            this.mState = state;
            this.mSppDoCallBack.onSppStateChanged(address, name, preState, state);
        }
    }

    public synchronized void getConnectedList() {
        String[] DeviceAddressList = new String[SppConnectedThreadList.size()];
        String[] DeviceNameList = new String[SppConnectedThreadList.size()];
        int deviceIndex = 0;
        if (SppConnectedThreadList != null) {
            Iterator<SppConnectedThread> it = SppConnectedThreadList.iterator();
            while (it.hasNext()) {
                SppConnectedThread e = it.next();
                DeviceAddressList[deviceIndex] = e.getsRemoteDeviceAddress();
                DeviceNameList[deviceIndex] = e.getRemoteDeviceName();
                deviceIndex++;
            }
        }
        this.mSppDoCallBack.retSppConnectedDeviceAddressList(SppConnectedThreadList.size(), DeviceAddressList, DeviceNameList);
    }

    public synchronized String[] getConnectedAddressList() {
        String[] DeviceAddressList;
        DeviceAddressList = new String[SppConnectedThreadList.size()];
        int deviceIndex = 0;
        if (SppConnectedThreadList != null) {
            Iterator<SppConnectedThread> it = SppConnectedThreadList.iterator();
            while (it.hasNext()) {
                SppConnectedThread e = it.next();
                DeviceAddressList[deviceIndex] = e.getsRemoteDeviceAddress();
                deviceIndex++;
            }
        }
        if (deviceIndex == 0) {
            DeviceAddressList = new String[0];
        }
        return DeviceAddressList;
    }

    public synchronized void updateConnectedList() {
        String[] DeviceAddressList = new String[SppConnectedThreadList.size()];
        String[] DeviceNameList = new String[SppConnectedThreadList.size()];
        int deviceIndex = 0;
        if (SppConnectedThreadList != null) {
            Iterator<SppConnectedThread> it = SppConnectedThreadList.iterator();
            while (it.hasNext()) {
                SppConnectedThread e = it.next();
                DeviceAddressList[deviceIndex] = e.getsRemoteDeviceAddress();
                DeviceNameList[deviceIndex] = e.getRemoteDeviceName();
                deviceIndex++;
            }
        }
        this.mSppDoCallBack.retSppConnectedDeviceAddressList(SppConnectedThreadList.size(), DeviceAddressList, DeviceNameList);
    }

    public synchronized int getConnectedDeviceCount() {
        return SppConnectedThreadList != null ? SppConnectedThreadList.size() : 0;
    }

    public synchronized boolean hasAnyConnectedConntetion() {
        boolean z = false;
        synchronized (this) {
            if (SppConnectedThreadList != null) {
                if (SppConnectedThreadList.size() > 0) {
                    z = true;
                } else {
                    Log.e(TAG, "in hasAnyConnectedConntetion : SppConnectedThreadList.size() = 0");
                }
            } else {
                Log.d(TAG, "In hasAnyConnectedConntetion : SppConnectedThreadList == null");
            }
        }
        return z;
    }

    public synchronized void disconnectAllConnectedConntection() {
        if (SppConnectedThreadList != null) {
            if (SppConnectedThreadList.size() > 0) {
                Iterator<SppConnectedThread> it = SppConnectedThreadList.iterator();
                while (it.hasNext()) {
                    SppConnectedThread e = it.next();
                    e.setStopThread(true);
                }
            } else {
                Log.d(TAG, "In disconnectAllConnectedConntection : no device connected");
            }
        } else {
            Log.d(TAG, "In disconnectAllConnectedConntection : SppConnectedThreadList == null");
        }
    }

    public void destroyClass() {
        Log.e(TAG, "+++ Begin of destroyClass() +++");
        this.isDestroyingClass = true;
        if (SppConnectedThreadList != null) {
            Iterator<SppConnectedThread> it = SppConnectedThreadList.iterator();
            while (it.hasNext()) {
                SppConnectedThread e = it.next();
                e.setStopThread(true);
            }
        }
        if (this.mSppAcceptThread != null) {
            this.mSppAcceptThread.setStopThread();
            this.mSppAcceptThread = null;
        }
        this.mAdapter = null;
        Log.e(TAG, "************************* SPP class has been destroyed *************************");
    }

    private synchronized void setNeedCallbackSppIapAuthenticationRequest(boolean need) {
        this.isNeedCallbackSppAppIapAuthenticationRequest = need;
    }

    public synchronized void callbackSppAppIapAuthenticationRequestIfNeed(String address) {
        if (this.isNeedCallbackSppAppIapAuthenticationRequest) {
            this.mSppDoCallBack.onSppAppleIapAuthenticationRequest(address);
            this.isNeedCallbackSppAppIapAuthenticationRequest = false;
        }
    }
}
