package com.nforetek.bt.manager;

import com.nforetek.bt.profile.a2dp._NfA2dp;
import com.nforetek.bt.profile.avrcp._NfAvrcp;
import com.nforetek.bt.profile.bluetooth.BasicConnectThread;
import com.nforetek.bt.profile.bluetooth._NfBluetooth;
import com.nforetek.bt.profile.hfp._NfHfp;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.normal.NfLog;
import java.util.HashSet;
/* loaded from: classes.dex */
public class NfConnectionMaintainer {
    private _NfA2dp mA2dp;
    private _NfAvrcp mAvrcp;
    private _NfHfp mHfp;
    private ProfileMonitorThread mMonitorThread;
    private String TAG = "NfConnectionMaintainer";
    private byte[] lock = new byte[0];
    private HashSet<String> mDetectedDeviceList = new HashSet<>();
    private HashSet<String> mDontDetectDeviceList = new HashSet<>();
    private BasicConnectThread.BasicConnectCallbackInterface mCallback = new BasicConnectThread.BasicConnectCallbackInterface() { // from class: com.nforetek.bt.manager.NfConnectionMaintainer.1
        @Override // com.nforetek.bt.profile.bluetooth.BasicConnectThread.BasicConnectCallbackInterface
        public void basicConnectDidFinished(String address, boolean isHfpSuccess, boolean isA2dpSuccess, boolean isAvrcpSuccess) {
            NfLog.v(NfConnectionMaintainer.this.TAG, "basicConnectDidFinished() " + address + " HFP: " + isHfpSuccess + " A2DP: " + isA2dpSuccess + " AVRCP: " + isAvrcpSuccess);
        }
    };

    public NfConnectionMaintainer(_NfHfp hfp, _NfA2dp a2dp, _NfAvrcp avrcp) {
        NfLog.d(this.TAG, "NfConnectionMaintainer() init");
        this.mHfp = hfp;
        this.mA2dp = a2dp;
        this.mAvrcp = avrcp;
    }

    public void onBtAclStateDisconnected(String address) {
        removeDeviceFromLists(address);
    }

    public void onHfpStateChanged(String address, int state) {
        if (state >= 140) {
            checkIfNeedAddToDetectedDeviceList(address);
            checkIsBasicProfileConnected(address);
        }
    }

    public void onA2dpStateChanged(String address, int state) {
        if (state >= 140) {
            checkIfNeedAddToDetectedDeviceList(address);
            checkIsBasicProfileConnected(address);
        }
    }

    public void onAvrcpStateChanged(String address, int state) {
        if (state >= 140) {
            checkIfNeedAddToDetectedDeviceList(address);
            checkIsBasicProfileConnected(address);
        }
    }

    public void onBtOn() {
        NfLog.v(this.TAG, "onBtOn()");
        resetLists();
        initMonitorThread();
    }

    public void onBtOff() {
        NfLog.v(this.TAG, "onBtOff()");
        if (this.mMonitorThread != null) {
            this.mMonitorThread.interrupt();
        }
        resetLists();
    }

    public void onBtDeviceUnpaired(String address) {
        NfLog.v(this.TAG, "onBtDeviceUnpaired() " + address);
        removeDeviceFromLists(address);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ProfileMonitorThread extends Thread {
        private boolean mInterrupted;

        private ProfileMonitorThread() {
            this.mInterrupted = false;
        }

        /* synthetic */ ProfileMonitorThread(NfConnectionMaintainer nfConnectionMaintainer, ProfileMonitorThread profileMonitorThread) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean isConnectByPeer;
            String str;
            super.run();
            NfLog.v(NfConnectionMaintainer.this.TAG, "ProfileConnectThread() started.");
            while (!this.mInterrupted) {
                try {
                    if (!NfConnectionMaintainer.this.isDetectListEmpty()) {
                        if (BasicConnectThread.isConnecting()) {
                            NfLog.v(NfConnectionMaintainer.this.TAG, "Start waiting until basic connect thread finished.");
                            while (BasicConnectThread.isConnecting()) {
                                Thread.sleep(1000L);
                            }
                            NfLog.v(NfConnectionMaintainer.this.TAG, "Basic connect thread finished.");
                            isConnectByPeer = false;
                        } else {
                            isConnectByPeer = true;
                        }
                        if (!NfConnectionMaintainer.this.isDetectListEmpty()) {
                            String[] list = NfConnectionMaintainer.this.getCopyDetectList();
                            for (int i = 0; i < list.length; i++) {
                                if (!NfConnectionMaintainer.this.isDeviceInDontDetectList(list[i])) {
                                    NfLog.v(NfConnectionMaintainer.this.TAG, "DetectedDeviceList(" + i + "): " + list[i]);
                                }
                            }
                            for (String device : list) {
                                if (!NfConnectionMaintainer.this.isDeviceInDontDetectList(device)) {
                                    NfLog.v(NfConnectionMaintainer.this.TAG, "Start monitor device " + device + ".");
                                    int count = isConnectByPeer ? 10 : 4;
                                    while (true) {
                                        if (count > 0) {
                                            if (!NfConnectionMaintainer.this.isDeviceAllBasicProfileConnected(device)) {
                                                if (NfConnectionMaintainer.this.isDeviceAllBasicProfileDisconnected(device) || NfConnectionMaintainer.this.isDetectListEmpty()) {
                                                    break;
                                                }
                                                Thread.sleep(500L);
                                                count--;
                                            } else {
                                                NfConnectionMaintainer.this.addToDontDetectedDeviceList(device);
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                    String str2 = NfConnectionMaintainer.this.TAG;
                                    StringBuilder append = new StringBuilder("Finish monitor device ").append(device);
                                    if (NfConnectionMaintainer.this.isDeviceAllBasicProfileConnected(device)) {
                                        str = " all basic profile connected.";
                                    } else {
                                        str = " not all basic profile connected.";
                                    }
                                    NfLog.v(str2, append.append(str).toString());
                                    if (_NfBluetooth.isDeviceAclDisconnected(device)) {
                                        NfLog.v(NfConnectionMaintainer.this.TAG, "Device ACL already disconnected.");
                                    } else if (!NfConnectionMaintainer.this.isDeviceAllBasicProfileConnected(device) && !NfConnectionMaintainer.this.isDeviceAllBasicProfileDisconnected(device)) {
                                        if (NfConfig.isAllowMultiConnection() || !NfConnectionMaintainer.this.isConnectToDifferentDevice()) {
                                            NfLog.v(NfConnectionMaintainer.this.TAG, "Try to connect missing profile.");
                                            NfConnectionMaintainer.this.addToDontDetectedDeviceList(device);
                                            if (BasicConnectThread.isConnecting()) {
                                                NfLog.v(NfConnectionMaintainer.this.TAG, "Basic connect thread is running, ignore.");
                                            } else {
                                                Thread t = BasicConnectThread.createBasicConnectThread(device, NfConnectionMaintainer.this.mCallback, false, false);
                                                if (t == null) {
                                                    NfLog.e(NfConnectionMaintainer.this.TAG, "BasicConnectThread is still running (" + BasicConnectThread.getSharedBasicConnectThread().hashCode() + ").");
                                                } else {
                                                    t.start();
                                                }
                                            }
                                        } else {
                                            NfLog.e(NfConnectionMaintainer.this.TAG, "Only allow single connection but HFP and A2DP connected with different device.");
                                            NfConnectionMaintainer.this.addToDontDetectedDeviceList(device);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                }
            }
            NfConnectionMaintainer.this.mMonitorThread = null;
            NfLog.v(NfConnectionMaintainer.this.TAG, "ProfileConnectThread() finished.");
        }

        @Override // java.lang.Thread
        public void interrupt() {
            NfLog.d(NfConnectionMaintainer.this.TAG, "ProfileConnectThread interrupt()");
            this.mInterrupted = true;
            super.interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDeviceAllBasicProfileConnected(String address) {
        return this.mHfp.getProfileState(address) >= 140 && this.mA2dp.getProfileState(address) >= 140 && this.mAvrcp.getProfileState(address) >= 140;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDeviceAllBasicProfileDisconnected(String address) {
        return this.mHfp.getProfileState(address) <= 110 && this.mA2dp.getProfileState(address) <= 110 && this.mAvrcp.getProfileState(address) <= 110;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isConnectToDifferentDevice() {
        if (this.mHfp == null || this.mA2dp == null || !this.mHfp.isAnyConnectedDevices() || !this.mA2dp.isAnyConnectedDevices()) {
            return false;
        }
        String hfpDevice = this.mHfp.getConnectedAddress();
        String a2dpDevice = this.mA2dp.getConnectedAddress();
        return !hfpDevice.equals(a2dpDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDetectListEmpty() {
        boolean isEmpty;
        synchronized (this.lock) {
            isEmpty = this.mDetectedDeviceList.isEmpty();
        }
        return isEmpty;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] getCopyDetectList() {
        String[] result;
        synchronized (this.lock) {
            if (this.mDetectedDeviceList.isEmpty()) {
                result = new String[0];
            } else {
                Object[] array = this.mDetectedDeviceList.toArray();
                result = new String[array.length];
                for (int i = 0; i < array.length; i++) {
                    result[i] = (String) array[i];
                }
            }
        }
        return result;
    }

    private boolean isDeviceInDetectList(String address) {
        boolean contains;
        synchronized (this.lock) {
            contains = this.mDetectedDeviceList.contains(address);
        }
        return contains;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDeviceInDontDetectList(String address) {
        boolean contains;
        synchronized (this.lock) {
            contains = this.mDontDetectDeviceList.contains(address);
        }
        return contains;
    }

    private void checkIsBasicProfileConnected(String address) {
        if (isDeviceAllBasicProfileConnected(address)) {
            addToDontDetectedDeviceList(address);
        }
    }

    private void checkIfNeedAddToDetectedDeviceList(String address) {
        if (!this.mDetectedDeviceList.contains(address) && !this.mDontDetectDeviceList.contains(address)) {
            addToDetectedDeviceList(address);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addToDontDetectedDeviceList(String address) {
        NfLog.d(this.TAG, "Device " + address + " add to don't detect device list.");
        synchronized (this.lock) {
            this.mDontDetectDeviceList.add(address);
        }
    }

    private void addToDetectedDeviceList(String address) {
        NfLog.d(this.TAG, "Device " + address + " add to detect device list.");
        synchronized (this.lock) {
            this.mDetectedDeviceList.add(address);
        }
    }

    private void removeDeviceFromLists(String address) {
        synchronized (this.lock) {
            if (this.mDetectedDeviceList.contains(address)) {
                this.mDetectedDeviceList.remove(address);
            }
            if (this.mDontDetectDeviceList.contains(address)) {
                this.mDontDetectDeviceList.remove(address);
            }
        }
    }

    private void resetLists() {
        synchronized (this.lock) {
            this.mDetectedDeviceList.clear();
            this.mDontDetectDeviceList.clear();
        }
    }

    public void initMonitorThread() {
        if (this.mMonitorThread == null) {
            this.mMonitorThread = new ProfileMonitorThread(this, null);
            this.mMonitorThread.start();
            return;
        }
        NfLog.d(this.TAG, "initMonitorThread but thread already initialed.");
    }
}
