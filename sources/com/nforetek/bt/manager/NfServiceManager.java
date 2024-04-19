package com.nforetek.bt.manager;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.nforetek.bt.aidl.INfCallbackA2dp;
import com.nforetek.bt.aidl.INfCallbackAvrcp;
import com.nforetek.bt.aidl.INfCallbackBluetooth;
import com.nforetek.bt.aidl.INfCallbackGattServer;
import com.nforetek.bt.aidl.INfCallbackHfp;
import com.nforetek.bt.aidl.INfCallbackHid;
import com.nforetek.bt.aidl.INfCallbackMap;
import com.nforetek.bt.aidl.INfCallbackOpp;
import com.nforetek.bt.aidl.INfCallbackPbap;
import com.nforetek.bt.aidl.INfCallbackSpp;
import com.nforetek.bt.callback.NfDoCallbackA2dp;
import com.nforetek.bt.callback.NfDoCallbackAvrcp;
import com.nforetek.bt.callback.NfDoCallbackBluetooth;
import com.nforetek.bt.callback.NfDoCallbackGattServer;
import com.nforetek.bt.callback.NfDoCallbackHfp;
import com.nforetek.bt.callback.NfDoCallbackHid;
import com.nforetek.bt.callback.NfDoCallbackInterface;
import com.nforetek.bt.callback.NfDoCallbackMap;
import com.nforetek.bt.callback.NfDoCallbackOpp;
import com.nforetek.bt.callback.NfDoCallbackPbap;
import com.nforetek.bt.callback.NfDoCallbackSpp;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.profile.NfA2dpCallbackInterface;
import com.nforetek.bt.profile.NfAvrcpCallbackInterface;
import com.nforetek.bt.profile.NfBluetoothCallbackInterface;
import com.nforetek.bt.profile.NfGattCallbackInterface;
import com.nforetek.bt.profile.NfHfpCallbackInterface;
import com.nforetek.bt.profile.NfHidCallbackInterface;
import com.nforetek.bt.profile.NfMapCallbackInterface;
import com.nforetek.bt.profile.NfOppCallbackInterface;
import com.nforetek.bt.profile.NfPbapCallbackInterface;
import com.nforetek.bt.profile.NfSppCallbackInterface;
import com.nforetek.bt.profile.a2dp._NfA2dp;
import com.nforetek.bt.profile.avrcp._NfAvrcp;
import com.nforetek.bt.profile.bluetooth.AclDisconnectedProtectDictionary;
import com.nforetek.bt.profile.bluetooth.AutoConnectListener;
import com.nforetek.bt.profile.bluetooth.AutoConnectThread;
import com.nforetek.bt.profile.bluetooth.BasicConnectThread;
import com.nforetek.bt.profile.bluetooth.BasicConnectionInterface;
import com.nforetek.bt.profile.bluetooth.BasicDisconnectThread;
import com.nforetek.bt.profile.bluetooth.CheckAclDisconnectedThread;
import com.nforetek.bt.profile.bluetooth.CheckProfileDisconnectedThread;
import com.nforetek.bt.profile.bluetooth.DelayPairThread;
import com.nforetek.bt.profile.bluetooth.RecreateBondProcessor;
import com.nforetek.bt.profile.bluetooth.RecreateBondProcessorCallback;
import com.nforetek.bt.profile.bluetooth._NfBluetooth;
import com.nforetek.bt.profile.gatt._NfGatt;
import com.nforetek.bt.profile.hfp._NfHfp;
import com.nforetek.bt.profile.hid._NfHid;
import com.nforetek.bt.profile.map._NfMap;
import com.nforetek.bt.profile.map.java._NfMapJava;
import com.nforetek.bt.profile.map.jni._NfMapJni;
import com.nforetek.bt.profile.opp._NfOpp;
import com.nforetek.bt.profile.pbap._NfPbap;
import com.nforetek.bt.profile.pbap.java._NfPbapJava;
import com.nforetek.bt.profile.pbap.jni._NfPbapJni;
import com.nforetek.bt.profile.spp._NfSpp;
import com.nforetek.bt.res.ErrorCodes;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.bt.res.bt.NfPreference;
import com.nforetek.bt.service.NfServiceA2dp;
import com.nforetek.bt.service.NfServiceAvrcp;
import com.nforetek.bt.service.NfServiceBluetooth;
import com.nforetek.bt.service.NfServiceGattServer;
import com.nforetek.bt.service.NfServiceHfp;
import com.nforetek.bt.service.NfServiceHid;
import com.nforetek.bt.service.NfServiceMap;
import com.nforetek.bt.service.NfServiceOpp;
import com.nforetek.bt.service.NfServicePbap;
import com.nforetek.bt.service.NfServiceSpp;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class NfServiceManager extends NfManagerService implements BasicConnectionInterface, BasicConnectThread.BasicConnectCallbackInterface, BasicDisconnectThread.BasicDisconnectCallbackInterface, NfServiceManagerInterface, NfDoCallbackInterface, RecreateBondProcessorCallback, NfBluetoothCallbackInterface, NfHfpCallbackInterface, NfA2dpCallbackInterface, NfAvrcpCallbackInterface, NfPbapCallbackInterface, NfHidCallbackInterface, NfSppCallbackInterface, NfMapCallbackInterface, NfOppCallbackInterface, NfGattCallbackInterface, CheckAclDisconnectedThread.CheckAclDisconnectedCallbackInterface, CheckProfileDisconnectedThread.CheckProfileDisconnectedCallbackInterface, AutoConnectListener {
    private _NfA2dp mA2dp;
    private NfDoCallbackA2dp mA2dpDoCallback;
    private AclDisconnectedProtectDictionary mAclDisconnectProtectDictionary;
    private AutoConnectThread mArThread;
    private AutoConnectListener mAutoConnectListener;
    private _NfAvrcp mAvrcp;
    private NfDoCallbackAvrcp mAvrcpDoCallback;
    private NfServiceManagerBinder mBinder;
    private _NfBluetooth mBluetooth;
    private NfDoCallbackBluetooth mBluetoothDoCallback;
    private CheckA2dpAvrcpDisconnectedThread mCaadThread;
    private CheckAvrcpStateThread mCasThread;
    private CheckA2dpStuckThread mCheckA2dpStuckThread;
    private CheckAvrcpStuckThread mCheckAvrcpStuckThread;
    private CheckJniBindedThread mCheckJniBindedThread;
    private NfConnectionMaintainer mConnectionMaintainer;
    private BasicDisconnectThread mDisconnectThread;
    private _NfGatt mGatt;
    private NfDoCallbackGattServer mGattServerDoCallback;
    private _NfHfp mHfp;
    private NfDoCallbackHfp mHfpDoCallback;
    private _NfHid mHid;
    private NfDoCallbackHid mHidDoCallback;
    private NfJni mJni;
    private _NfMap mMap;
    private NfDoCallbackMap mMapDoCallback;
    private _NfOpp mOpp;
    private NfDoCallbackOpp mOppDoCallback;
    private _NfPbap mPbap;
    private NfDoCallbackPbap mPbapDoCallback;
    private QueueForScanThread mQueueForScanThread;
    private RecreateBondProcessor mRecreateProcessor;
    private _NfSpp mSpp;
    private NfDoCallbackSpp mSppDoCallback;
    private VerifyLocalBtStateThread mVlbsThread;
    private Boolean tabletMode = false;
    private String mHfpConnectingAddress = NfDef.DEFAULT_ADDRESS;
    private String mA2dpConnectingAddress = NfDef.DEFAULT_ADDRESS;
    private String mAvrcpConnectingAddress = NfDef.DEFAULT_ADDRESS;
    private boolean mIsJniInitFinished = false;
    private HashMap<String, CheckAclDisconnectedThread> mCheckAclDisconnectedDictionary = new HashMap<>();
    private Set<String> mIsAclConnectedSet = new HashSet();
    private byte[] atLock = new byte[0];
    private byte[] btoffLock = new byte[0];
    private HashMap<String, DelayPairThread> mDelayPairDictionary = new HashMap<>();
    private HashMap<String, DelayCallbackBondStateThread> mDelayCallbackBondStateDictionary = new HashMap<>();
    boolean isHfpDisconnected = true;
    boolean isA2dpDisconnected = true;
    boolean isAvrcpDisconnected = true;

    @Override // com.nforetek.bt.manager.NfManagerService
    protected String getLogTag() {
        return "NfServiceManager";
    }

    private void interruptCheckAclDisconnectedThread(String address, boolean isAclDisconnected) {
        CheckAclDisconnectedThread t = this.mCheckAclDisconnectedDictionary.get(address);
        if (t != null) {
            t.interrupt(isAclDisconnected);
        }
    }

    private void initCheckAclDisconnectedToDictionary(String address) {
        CheckAclDisconnectedThread thread = this.mCheckAclDisconnectedDictionary.get(address);
        if (thread != null) {
            this.mCheckAclDisconnectedDictionary.remove(address);
            return;
        }
        CheckAclDisconnectedThread thread2 = new CheckAclDisconnectedThread(address, this, this.mA2dp, this.mAvrcp, this.mPbap, this.mHid, this.mSpp, this.mMap, this.mOpp);
        NfLog.v(this.TAG, "New CheckAclDisconnectedThread. init CheckAclDisconnectedDictionary element. address: " + address);
        this.mCheckAclDisconnectedDictionary.put(address, thread2);
    }

    private void interruptDelayPairThread(String address) {
        DelayPairThread t = this.mDelayPairDictionary.get(address);
        if (t != null) {
            NfLog.d(this.TAG, "This address is waiting for pairing.");
            t.interrupt();
            this.mDelayPairDictionary.remove(address);
        }
    }

    private boolean initDelayPairToDictionary(String address) {
        if (this.mDelayPairDictionary.containsKey(address)) {
            return false;
        }
        if (this.mDelayPairDictionary.get(address) != null) {
            NfLog.e(this.TAG, "After check contansKey " + address + " but get thread is not null !?");
            return false;
        }
        DelayPairThread thread = new DelayPairThread(address);
        NfLog.v(this.TAG, "New DelayPairThread. init DelayPairDictionary element. address: " + address);
        thread.start();
        this.mDelayPairDictionary.put(address, thread);
        this.mJni.reqBtRemoveAclConnection(address);
        return true;
    }

    @Override // com.nforetek.bt.manager.NfManagerService, android.app.Service
    public void onDestroy() {
        NfLog.v(this.TAG, "onDestroy()");
        destroyDoCallback();
        releaseBinder();
        stopCommandService();
        destroyProfiles();
        NfPrimitive.destroy();
        super.onDestroy();
    }

    @Override // com.nforetek.bt.manager.NfManagerService, android.app.Service
    public void onCreate() {
        super.onCreate();
        NfLog.d(this.TAG, "onCreate()");
        NfConfig.readConfig();
        NfPreference.printPreference();
        this.mBinder = new NfServiceManagerBinder(this);
        initJni();
        initDoCallback();
        initBluetoothHelper();
        initProfiles();
        BasicConnectThread.initProfile(this.mHfp, this.mA2dp, this.mAvrcp, this.mHid, this.mMap, this.mPbap, this.mSpp, this.mBluetooth);
        startCommandService();
        if (NfConfig.isAllBasicProfileEnabled() && NfConfig.isRedoBasicConnectEnable()) {
            if (this.mConnectionMaintainer == null) {
                this.mConnectionMaintainer = new NfConnectionMaintainer(this.mHfp, this.mA2dp, this.mAvrcp);
            }
            if (NfPrimitive.isBtEnabled()) {
                this.mConnectionMaintainer.initMonitorThread();
            }
        }
        this.mRecreateProcessor = new RecreateBondProcessor(this);
        this.mAclDisconnectProtectDictionary = new AclDisconnectedProtectDictionary();
        this.mAutoConnectListener = this;
        if (NfPrimitive.isBtEnabled()) {
            NfPrimitive.updateCachePairedDevices();
            if (NfPreference.isAllowAutoConnectWhenBtOn()) {
                Thread t = new Thread() { // from class: com.nforetek.bt.manager.NfServiceManager.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        super.run();
                        if (NfServiceManager.this.mJni != null) {
                            while (!NfServiceManager.this.mJni.isServiceBinded()) {
                                try {
                                    Thread.sleep(200L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            if (NfPreference.isAllowAutoConnectWhenBtOn()) {
                                synchronized (NfServiceManager.this.atLock) {
                                    NfServiceManager.this.interruptAutoConnectThreadDirectly();
                                    if (!NfPreference.getAutoConnectAddress().equals(NfDef.DEFAULT_ADDRESS)) {
                                        NfServiceManager.this.initAutoConnectThreadDirectly(NfPreference.getAutoConnectAddress(), 1, -1);
                                    }
                                }
                                return;
                            }
                            NfLog.v(NfServiceManager.this.TAG, "Auto connect after BtOn not enable.");
                        }
                    }
                };
                t.start();
            }
            if (NfPreference.isDiscoverableForever()) {
                NfPrimitive.setBtDiscoverable(true, 0);
            }
        }
        NfLog.v(this.TAG, "onCreate() finished");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        NfLog.d(this.TAG, "onBind() intent:" + intent + "intent.type: " + intent.getData());
        return this.mBinder;
    }

    private void releaseBinder() {
        NfLog.v(this.TAG, "releaseBinder()");
        this.mBinder.onDestroy();
        this.mBinder = null;
    }

    private void startCommandService() {
        NfLog.v(this.TAG, "startCommandService()");
        if (NfConfig.isEnableHfp()) {
            startService(new Intent(this, NfServiceHfp.class));
        }
        if (NfConfig.isEnableA2dp()) {
            startService(new Intent(this, NfServiceA2dp.class));
        }
        if (NfConfig.isEnableAvrcp()) {
            startService(new Intent(this, NfServiceAvrcp.class));
        }
        if (NfConfig.isEnablePbap()) {
            startService(new Intent(this, NfServicePbap.class));
        }
        if (NfConfig.isEnableSpp()) {
            startService(new Intent(this, NfServiceSpp.class));
        }
        if (NfConfig.isEnableHid()) {
            startService(new Intent(this, NfServiceHid.class));
        }
        if (NfConfig.isEnableMap()) {
            startService(new Intent(this, NfServiceMap.class));
        }
        if (NfConfig.isEnableOpp()) {
            startService(new Intent(this, NfServiceOpp.class));
        }
        if (NfConfig.isEnableGattServer()) {
            startService(new Intent(this, NfServiceGattServer.class));
        }
        startService(new Intent(this, NfServiceBluetooth.class));
    }

    private void stopCommandService() {
        NfLog.v(this.TAG, "stopCommandService()");
        stopService(new Intent(this, NfServiceHfp.class));
        stopService(new Intent(this, NfServiceA2dp.class));
        stopService(new Intent(this, NfServiceAvrcp.class));
        stopService(new Intent(this, NfServicePbap.class));
        stopService(new Intent(this, NfServiceHid.class));
        stopService(new Intent(this, NfServiceBluetooth.class));
        stopService(new Intent(this, NfServiceSpp.class));
        stopService(new Intent(this, NfServiceMap.class));
        stopService(new Intent(this, NfServiceOpp.class));
        stopService(new Intent(this, NfServiceGattServer.class));
    }

    private void initDoCallback() {
        NfLog.v(this.TAG, "initDoCallback()");
        this.mBluetoothDoCallback = new NfDoCallbackBluetooth();
        if (NfConfig.isEnableHfp()) {
            this.mHfpDoCallback = new NfDoCallbackHfp(this);
        }
        if (NfConfig.isEnableA2dp()) {
            this.mA2dpDoCallback = new NfDoCallbackA2dp(this);
        }
        if (NfConfig.isEnableAvrcp()) {
            this.mAvrcpDoCallback = new NfDoCallbackAvrcp(this);
        }
        if (NfConfig.isEnablePbap()) {
            this.mPbapDoCallback = new NfDoCallbackPbap(this);
        }
        if (NfConfig.isEnableHid()) {
            this.mHidDoCallback = new NfDoCallbackHid();
        }
        if (NfConfig.isEnableSpp()) {
            this.mSppDoCallback = new NfDoCallbackSpp();
        }
        if (NfConfig.isEnableMap()) {
            this.mMapDoCallback = new NfDoCallbackMap(this);
        }
        this.mOppDoCallback = new NfDoCallbackOpp();
        if (NfConfig.isEnableGattServer()) {
            this.mGattServerDoCallback = new NfDoCallbackGattServer();
        }
    }

    private void destroyDoCallback() {
        NfLog.v(this.TAG, "destroyDoCallback()");
        if (this.mBluetoothDoCallback != null) {
            this.mBluetoothDoCallback.kill();
        }
        if (this.mHfpDoCallback != null) {
            this.mHfpDoCallback.kill();
        }
        if (this.mA2dpDoCallback != null) {
            this.mA2dpDoCallback.kill();
        }
        if (this.mAvrcpDoCallback != null) {
            this.mAvrcpDoCallback.kill();
        }
        if (this.mPbapDoCallback != null) {
            this.mPbapDoCallback.kill();
        }
        if (this.mHidDoCallback != null) {
            this.mHidDoCallback.kill();
        }
        if (this.mSppDoCallback != null) {
            this.mSppDoCallback.kill();
        }
        if (this.mMapDoCallback != null) {
            this.mMapDoCallback.kill();
        }
        if (this.mOppDoCallback != null) {
            this.mOppDoCallback.kill();
        }
        if (this.mGattServerDoCallback != null) {
            this.mGattServerDoCallback.kill();
        }
    }

    private void destroyProfiles() {
        NfLog.v(this.TAG, "destroyAllProfiles()");
        releaseJni();
        releaseBt();
        releaseHfp();
        releaseA2dp();
        releaseAvrcp();
        releasePbap();
        releaseHid();
        releaseSpp();
        releaseMap();
        releaseOpp();
        releaseGatt();
    }

    private void releaseJni() {
        if (this.mJni != null) {
            this.mJni.unbindService();
            this.mJni.onDestroy();
            this.mJni = null;
        }
    }

    private void releaseBt() {
        if (this.mBluetooth != null) {
            this.mBluetooth.resetJni();
        }
    }

    private void releaseHfp() {
        if (this.mHfp != null) {
            this.mHfp.onDestroy();
            this.mHfp = null;
        }
    }

    private void releaseA2dp() {
        if (this.mA2dp != null) {
            this.mA2dp.onDestroy();
            this.mA2dp = null;
        }
    }

    private void releaseAvrcp() {
        if (this.mAvrcp != null) {
            this.mAvrcp.onDestroy();
            this.mAvrcp = null;
        }
    }

    private void releasePbap() {
        if (this.mPbap != null) {
            this.mPbap.onDestroy();
            this.mPbap = null;
        }
    }

    private void releaseHid() {
        if (this.mHid != null) {
            this.mHid.onDestroy();
            this.mHid = null;
        }
    }

    private void releaseSpp() {
        if (this.mSpp != null) {
            this.mSpp.onDestroy();
            this.mSpp = null;
        }
    }

    private void releaseMap() {
        if (this.mMap != null) {
            this.mMap.onDestroy();
            this.mMap = null;
        }
    }

    private void releaseOpp() {
        if (this.mOpp != null) {
            this.mOpp.onDestroy();
            this.mOpp = null;
        }
    }

    private void releaseGatt() {
        if (this.mGatt != null) {
            this.mGatt.onDestroy();
            this.mGatt = null;
        }
    }

    private void initProfiles() {
        NfLog.i(this.TAG, "initProfiles");
        this.mBinder.setInstace(this);
        if (NfConfig.isEnableHfp()) {
            initHfpProfile();
        }
        if (NfConfig.isEnableA2dp()) {
            initA2dpProfile();
        }
        if (NfConfig.isEnableAvrcp()) {
            initAvrcpProfile();
        }
        if (NfConfig.isEnablePbap()) {
            initPbapProfile();
        }
        if (NfConfig.isEnableHid()) {
            initHidProfile();
        }
        if (NfConfig.isEnableSpp()) {
            initSppProfile();
        }
        if (NfConfig.isEnableMap()) {
            initMapProfile();
        }
        if (NfConfig.isEnableOpp()) {
            initOppProfile();
        }
        if (NfConfig.isEnableGattServer()) {
            initGattProfile();
        }
    }

    private void initBluetoothHelper() {
        NfLog.i(this.TAG, "initBluetoothHelper()");
        if (this.mBluetooth == null) {
            this.mBluetooth = new _NfBluetooth();
            this.mBluetooth.onCreate(this, this.mBluetoothDoCallback, this.mJni, this);
            this.mBinder.setInstance(this.mBluetooth);
        }
    }

    private void initJni() {
        if (this.mJni == null) {
            NfLog.i(this.TAG, "initJni()");
            this.mJni = new NfJni(this);
            if (!NfPrimitive.isBtEnabled()) {
                if (this.mCheckJniBindedThread != null) {
                    NfLog.e(this.TAG, "mCheckJniBindedThread is not null !!? imposible !!");
                    this.mCheckJniBindedThread.interrupt();
                    this.mCheckJniBindedThread = null;
                }
                this.mCheckJniBindedThread = new CheckJniBindedThread(this, null);
                this.mCheckJniBindedThread.start();
            }
        }
        if (NfPrimitive.isBtEnabled()) {
            NfLog.i(this.TAG, "Bind Jni Service when initJni.");
            this.mJni.bindService();
        }
    }

    private void initHfpProfile() {
        if (this.mHfp == null) {
            NfLog.i(this.TAG, "initHfpProfile()");
            this.mHfp = new _NfHfp();
            this.mHfp.onCreate(this, this.mHfpDoCallback, this.mJni, this);
            this.mBinder.setInstance(this.mHfp);
        }
    }

    private void initA2dpProfile() {
        if (this.mA2dp == null) {
            NfLog.i(this.TAG, "initA2dpProfile()");
            this.mA2dp = new _NfA2dp();
            this.mA2dp.onCreate(this, this.mA2dpDoCallback, this.mJni, this);
            this.mBinder.setInstance(this.mA2dp);
        }
    }

    private void initAvrcpProfile() {
        if (this.mAvrcp == null) {
            NfLog.i(this.TAG, "initAvrcpProfile()");
            this.mAvrcp = new _NfAvrcp();
            this.mAvrcp.onCreate(this, this.mAvrcpDoCallback, this.mJni, this);
            this.mBinder.setInstance(this.mAvrcp);
        }
    }

    private void initPbapProfile() {
        if (this.mPbap == null) {
            NfLog.i(this.TAG, "initPbapProfile()");
            if (NfConfig.isPbapImplementByJava()) {
                this.mPbap = new _NfPbapJava();
            } else {
                this.mPbap = new _NfPbapJni();
            }
            this.mPbap.onCreate(this, this.mPbapDoCallback, this.mJni, this);
            this.mBinder.setInstance(this.mPbap);
        }
        if (NfConfig.isUsePbapAddSdp()) {
            NfLog.d(this.TAG, "initJniPbap USE_PBAP_ADD_SDP");
            try {
                this.mJni.createSdpRecord("PCE", 1, -1, -1, -1, -1);
            } catch (RemoteException e) {
                e.printStackTrace();
                NfLog.e(this.TAG, "createSdpRecord fail");
            }
        }
    }

    private void initHidProfile() {
        if (this.mHid == null) {
            NfLog.i(this.TAG, "initHidProfile()");
            this.mHid = new _NfHid();
            this.mHid.onCreate(this, this.mHidDoCallback, this.mJni, this);
            this.mBinder.setInstance(this.mHid);
        }
    }

    private void initSppProfile() {
        if (this.mSpp == null) {
            NfLog.i(this.TAG, "initSppProfile()");
            this.mSpp = new _NfSpp();
            this.mSpp.onCreate(this, this.mSppDoCallback, this);
            this.mBinder.setInstance(this.mSpp);
        }
    }

    private void initMapProfile() {
        if (this.mMap == null) {
            NfLog.i(this.TAG, "initMapProfile()");
            if (NfConfig.isMapImplementByJava()) {
                this.mMap = new _NfMapJava();
            } else {
                this.mMap = new _NfMapJni();
            }
            this.mMap.onCreate(this, this.mMapDoCallback, this.mJni, this);
            this.mBinder.setInstance(this.mMap);
        }
    }

    private void initOppProfile() {
        if (this.mOpp == null) {
            NfLog.i(this.TAG, "initOppProfile()");
            this.mOpp = new _NfOpp();
            this.mOpp.onCreate(this, this.mOppDoCallback, this.mJni, this);
            this.mBinder.setInstance(this.mOpp);
        }
    }

    private void initGattProfile() {
        if (this.mGatt == null) {
            NfLog.i(this.TAG, "initGattProfile()");
            this.mGatt = new _NfGatt();
            this.mGatt.onCreate(this, this.mGattServerDoCallback, this.mJni, this);
            this.mBinder.setInstance(this.mGatt);
        }
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onBtOn() {
        NfLog.d(this.TAG, "onBtOn()");
        NfConfig.readConfig();
        NfPreference.printPreference();
        NfPrimitive.updateCachePairedDevices();
        initProfiles();
        if (this.mCheckJniBindedThread != null) {
            this.mCheckJniBindedThread.interrupt();
            this.mCheckJniBindedThread = null;
        }
        if (this.mJni != null) {
            this.mJni.bindService();
        } else {
            initJni();
        }
        if (NfPreference.isAllowAutoConnectWhenBtOn()) {
            Thread t = new Thread() { // from class: com.nforetek.bt.manager.NfServiceManager.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    super.run();
                    if (NfServiceManager.this.mJni != null) {
                        while (!NfServiceManager.this.mJni.isServiceBinded()) {
                            try {
                                Thread.sleep(200L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (NfPreference.isAllowAutoConnectWhenBtOn()) {
                            synchronized (NfServiceManager.this.atLock) {
                                NfServiceManager.this.interruptAutoConnectThreadDirectly();
                                if (!NfPreference.getAutoConnectAddress().equals(NfDef.DEFAULT_ADDRESS)) {
                                    NfServiceManager.this.initAutoConnectThreadDirectly(NfPreference.getAutoConnectAddress(), 1, -1);
                                }
                            }
                            return;
                        }
                        NfLog.v(NfServiceManager.this.TAG, "Auto connect after BtOn not enable.");
                        return;
                    }
                    NfLog.e(NfServiceManager.this.TAG, "mJni is null!!");
                }
            };
            t.start();
        }
        if (NfConfig.isAllBasicProfileEnabled() && NfConfig.isRedoBasicConnectEnable() && this.mConnectionMaintainer != null) {
            this.mConnectionMaintainer.onBtOn();
        }
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onBtOff() {
        synchronized (this.atLock) {
            NfLog.d(this.TAG, "onBtOff()");
            if (this.mVlbsThread != null) {
                try {
                    this.mVlbsThread.interrupt();
                    this.mVlbsThread = null;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            if (NfPrimitive.isAnyDevicePairing() && !NfPrimitive.getPairingDevice().equals(NfDef.DEFAULT_ADDRESS)) {
                String address = NfPrimitive.getPairingDevice();
                String name = NfPrimitive.getRemoteDeviceName(address);
                this.mBluetoothDoCallback.onDeviceBondStateChanged(address, name, NfDef.BOND_BONDING, NfDef.BOND_NONE);
                NfLog.d(this.TAG, "Try to send a fake bond state change broadcast.");
                Intent intent = new Intent("android.bluetooth.device.action.BOND_STATE_CHANGED");
                intent.putExtra("android.bluetooth.device.extra.DEVICE", BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address));
                intent.putExtra("android.bluetooth.device.extra.BOND_STATE", 10);
                intent.putExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", 11);
                sendBroadcast(intent);
            }
            NfPrimitive.setAnyDevicePairing(false, NfDef.DEFAULT_ADDRESS);
            if (NfConfig.isAllBasicProfileEnabled() && NfConfig.isRedoBasicConnectEnable() && this.mConnectionMaintainer != null) {
                this.mConnectionMaintainer.onBtOff();
            }
            if (this.mAclDisconnectProtectDictionary != null) {
                this.mAclDisconnectProtectDictionary.reset();
            }
            if (this.mJni != null) {
                this.mJni.unbindService();
            }
            if (this.mA2dp != null) {
                this.mA2dp.forceResetState();
            }
            if (this.mAvrcp != null) {
                this.mAvrcp.forceResetState();
            }
            if (this.mGatt != null) {
                this.mGatt.forceResetState();
            }
            if (this.mHfp != null) {
                this.mHfp.forceResetState();
            }
            if (this.mHid != null) {
                this.mHid.forceResetState();
            }
            if (this.mMap != null) {
                this.mMap.forceResetState();
            }
            if (this.mOpp != null) {
                this.mOpp.forceResetState();
            }
            if (this.mPbap != null) {
                this.mPbap.forceResetState();
            }
            if (this.mSpp != null) {
                releaseSpp();
            }
            if (this.mIsAclConnectedSet != null && this.mIsAclConnectedSet.size() != 0) {
                this.mIsAclConnectedSet.clear();
            }
            if (this.mCheckAclDisconnectedDictionary != null && this.mCheckAclDisconnectedDictionary.size() != 0) {
                this.mCheckAclDisconnectedDictionary.clear();
            }
            _NfBluetooth.resetAclStateDictionary(this.mBluetoothDoCallback);
        }
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onBtTurningOn() {
        NfLog.d(this.TAG, "onBtTurningOn()");
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onBtTurningOff() {
        NfLog.d(this.TAG, "onBtTurningOff()");
        try {
            if (BasicConnectThread.isConnecting()) {
                NfLog.e(this.TAG, "BasicConnectThread is still running. Interrupt it.");
                BasicConnectThread.setInterrupt();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onBtAclStateChanged(String address, int state, int reason) {
        NfLog.d(this.TAG, "onBtAclStateChanged() " + address + " state: " + state + " reason: " + reason + " (" + ErrorCodes.getErrorName(reason) + ")");
        if (state == 1) {
            if (this.mIsAclConnectedSet.contains(address)) {
                this.mIsAclConnectedSet.remove(address);
            }
            interruptDelayPairThread(address);
            if (!NfPreference.isAllowAutoConnectWhenPaired()) {
                synchronized (this.atLock) {
                    interruptAutoConnectThreadDirectly(address);
                }
            }
            if (NfConfig.isUnconnectableAfterDeviceConnected()) {
                NfPrimitive.setBtConnectable(true);
            }
            this.mAclDisconnectProtectDictionary.onAclDisconnected(address);
            if (NfConfig.isAllBasicProfileEnabled() && NfConfig.isRedoBasicConnectEnable() && this.mConnectionMaintainer != null) {
                this.mConnectionMaintainer.onBtAclStateDisconnected(address);
            }
        } else if (state == 0) {
            this.mIsAclConnectedSet.add(address);
        } else {
            NfLog.e(this.TAG, "ACL state is " + state);
            NfLog.v(this.TAG, "mIsAclConnectedSet: " + this.mIsAclConnectedSet);
        }
        if (state == 1) {
            interruptCheckAclDisconnectedThread(address, true);
        }
        if (state == 1 && (reason == 19 || reason == 22 || reason == 14)) {
            this.mRecreateProcessor.onAclDisconnectCallbackFromSystem(address);
        }
        if (state == 1) {
            if (!NfConfig.isAfterAndroid6()) {
                if (this.mOpp != null) {
                    this.mOpp.forceCallbackDisconnected();
                }
                if (this.mHfp != null && this.mHfp.getProfileState() == 120 && this.mHfpConnectingAddress.equals(address)) {
                    this.mHfp.disconnect(address);
                    NfLog.d(this.TAG, "Disconnect connecting HFP");
                }
                if (this.mA2dp != null && this.mA2dp.getProfileState() == 120 && this.mA2dpConnectingAddress.equals(address)) {
                    this.mA2dp.disconnect(address);
                    NfLog.d(this.TAG, "Disconnect connecting A2DP");
                }
                if (this.mAvrcp != null && this.mAvrcp.getProfileState() == 120 && this.mAvrcpConnectingAddress.equals(address)) {
                    this.mAvrcp.disconnect(address);
                    NfLog.d(this.TAG, "Disconnect connecting AVRCP");
                }
            }
            if (this.mA2dp != null) {
                this.mA2dp.onBtAclDisconnected(address);
            }
            if (this.mAvrcp != null) {
                this.mAvrcp.onBtAclDisconnected(address);
            }
            if (this.mGatt != null) {
                this.mGatt.onBtAclDisconnected(address);
            }
            if (this.mHfp != null) {
                this.mHfp.onBtAclDisconnected(address);
            }
            if (this.mHid != null) {
                this.mHid.onBtAclDisconnected(address);
            }
            if (this.mMap != null) {
                this.mMap.onBtAclDisconnected(address);
            }
            if (this.mOpp != null) {
                this.mOpp.onBtAclDisconnected(address);
            }
            if (this.mPbap != null) {
                this.mPbap.onBtAclDisconnected(address);
            }
        }
        if (state == 1) {
            try {
                try {
                    if (BasicConnectThread.isConnecting() && BasicConnectThread.getSharedBasicConnectThread().getAddress().equals(address) && !NfPreference.isAllowAutoConnectWhenPaired() && BasicConnectThread.getSharedBasicConnectThread().getBasicConnectState() > 120) {
                        NfLog.e(this.TAG, "BasicConnectThread(" + BasicConnectThread.getSharedBasicConnectThread().hashCode() + ") address: " + BasicConnectThread.getSharedBasicConnectThread().getAddress() + "is interrupt.");
                        NfLog.d(this.TAG, "BasicConnectThread state is " + BasicConnectThread.getSharedBasicConnectThread().getBasicConnectState());
                        BasicConnectThread.setInterrupt();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                CheckProfileDisconnectedThread t = new CheckProfileDisconnectedThread(address, this, this.mHfp, this.mA2dp, this.mAvrcp, this.mPbap, this.mHid, this.mSpp, this.mMap, this.mOpp);
                t.start();
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onBtRecreateBond(String address, int is_connect) {
        NfLog.d(this.TAG, "onBtRecreateBond() " + address + " is_connect " + is_connect);
        this.mRecreateProcessor.onRecreateBondCallbackFromSystem(address, is_connect);
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onBtDeviceBondStateChanged(String address, String name, int prevState, int newState) {
        NfLog.d(this.TAG, "onBtDeviceBondStateChanged() " + address + " name: " + name + " state: " + prevState + " -> " + newState);
        if (newState == 331) {
            NfPrimitive.setAnyDevicePairing(true, address);
            stopDelayCallbackBondStateThread(address);
        } else if (prevState == 331) {
            NfPrimitive.setAnyDevicePairing(false, address);
        }
        if (newState == 330) {
            DelayCallbackBondStateThread t = this.mDelayCallbackBondStateDictionary.get(address);
            if (t != null) {
                t.interrupt();
            }
            if (NfPreference.getAutoConnectAddress().equals(address)) {
                NfPreference.setAutoConnectAddress(NfDef.DEFAULT_ADDRESS);
            }
            synchronized (this.atLock) {
                interruptAutoConnectThreadDirectly(address);
            }
            try {
                if (BasicConnectThread.isConnecting()) {
                    try {
                        if (BasicConnectThread.getSharedBasicConnectThread().getAddress().equals(address)) {
                            BasicConnectThread.setInterrupt();
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            }
            if (NfConfig.isAllBasicProfileEnabled() && NfConfig.isRedoBasicConnectEnable() && this.mConnectionMaintainer != null) {
                this.mConnectionMaintainer.onBtDeviceUnpaired(address);
            }
        } else if (newState == 331) {
            if (NfPrimitive.isDiscovering()) {
                NfLog.d(this.TAG, "Device " + name + "(" + address + ") is pairing but BT discovering. Cancel it.");
                NfPrimitive.cancelDiscovery();
            }
            synchronized (this.atLock) {
                interruptAutoConnectThreadDirectly(address);
            }
        }
        if (prevState == 331 && newState == 332) {
            if (NfPreference.isAllowAutoConnectWhenPaired()) {
                if (!isAnyBasicProfileConnected()) {
                    synchronized (this.atLock) {
                        interruptAutoConnectThreadDirectly();
                        initAutoConnectThreadDirectly(address, 2, -1);
                    }
                }
            } else {
                NfLog.v(this.TAG, "Auto connect after paired not enable.");
            }
            if (isAclConnected(address)) {
                if (this.mDelayCallbackBondStateDictionary.get(address) == null) {
                    DelayCallbackBondStateThread t2 = new DelayCallbackBondStateThread(address, name);
                    this.mDelayCallbackBondStateDictionary.put(address, t2);
                    t2.start();
                    return;
                }
                NfLog.v(this.TAG, "Already have one delay callback thread in map.");
            }
        }
        this.mBluetoothDoCallback.onDeviceBondStateChanged(address, name, prevState, newState);
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onBtRemoteDeviceOutOfRange(String address) {
        NfLog.d(this.TAG, "onBtRemoteDeviceOutOfRange() " + address);
        if (NfPreference.isAllowAutoConnectWhenOor() && address.equals(NfPreference.getAutoConnectAddress())) {
            synchronized (this.atLock) {
                interruptAutoConnectThreadDirectly();
                initAutoConnectThreadDirectly(address, 4, NfPreference.getAutoConnectPeriod());
            }
            return;
        }
        NfLog.v(this.TAG, "Auto connect after OOR not enable.");
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onBtSwitchToCarMode(String address) {
        NfLog.d(this.TAG, "onBtSwitchToCarMode() " + address);
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onBtStartScan() {
        NfLog.d(this.TAG, "onBtStartScan()");
        synchronized (this.atLock) {
            interruptAutoConnectThreadDirectly();
        }
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public synchronized boolean isAlreadyQueueForScan() {
        return this.mQueueForScanThread != null;
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public boolean tryToQueueForScan() {
        NfLog.d(this.TAG, "tryToQueueForScan()");
        if (this.mQueueForScanThread != null) {
            NfLog.e(this.TAG, "In tryToQueueForScan, but mQueueForScanThread != null.");
            return true;
        }
        boolean isNeedQueueForScan = false;
        if (this.mHfp != null && this.mHfp.isConnecting()) {
            isNeedQueueForScan = true;
        }
        if (this.mA2dp != null && this.mA2dp.isConnecting()) {
            isNeedQueueForScan = true;
        }
        if (this.mAvrcp != null && this.mAvrcp.isConnecting()) {
            isNeedQueueForScan = true;
        }
        if (isNeedQueueForScan) {
            if (this.mQueueForScanThread == null) {
                this.mQueueForScanThread = new QueueForScanThread(this, null);
                this.mQueueForScanThread.start();
                return true;
            }
            return true;
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onJniInitFinished(boolean finished) {
        NfLog.d(this.TAG, "onJniInitFinished() " + finished);
        this.mIsJniInitFinished = finished;
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onAdapterDiscoveryStarted() {
        NfLog.d(this.TAG, "onAdapterDiscoveryStarted()");
        forceStopAutoConnectThread();
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onAdapterDiscoveryFinished() {
        NfLog.d(this.TAG, "onAdapterDiscoveryFinished()");
    }

    /* loaded from: classes.dex */
    private abstract class OneRunnable implements Runnable {
        protected String mAddress;

        public OneRunnable(String a) {
            this.mAddress = a;
        }
    }

    @Override // com.nforetek.bt.profile.bluetooth.RecreateBondProcessorCallback
    public void onNeedBasicConnect(String address) {
        NfLog.d(this.TAG, "onNeedBasicConnect() " + address);
        Thread t = new Thread(new OneRunnable(this, address) { // from class: com.nforetek.bt.manager.NfServiceManager.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    NfLog.d(this.TAG, "Before delay 1500ms for recreate bond");
                    Thread.sleep(1500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                NfLog.d(this.TAG, "After delay 1500ms for recreate bond");
                if (this.mHfp != null) {
                    if (this.mHfp != null && this.mHfp.getConnectedAddress().equals(NfDef.DEFAULT_ADDRESS)) {
                        this.basicConnect(this.mAddress, true, true, false);
                        return;
                    }
                    return;
                }
                NfLog.e(this.TAG, "HFP Profile is null !!");
            }
        });
        t.start();
    }

    @Override // com.nforetek.bt.profile.NfHfpCallbackInterface
    public void onHfpStateChanged(String address, int prevState, int newState) {
        NfLog.d(this.TAG, "onHfpStateChanged() " + address + " state: " + prevState + "->" + newState);
        if (newState == 120 || newState == 140) {
            try {
                if (BasicConnectThread.isConnecting() && !BasicConnectThread.getSharedBasicConnectThread().getAddress().equals(address) && BasicConnectThread.getSharedBasicConnectThread().isAutoConnect()) {
                    NfLog.e(this.TAG, "HFP connecting and auto basic connect target address is " + BasicConnectThread.getSharedBasicConnectThread().getAddress() + ", interrupt!");
                    BasicConnectThread.setInterrupt();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        if (newState == 120) {
            this.mHfpConnectingAddress = address;
            NfLog.d(this.TAG, "Connecting address is:  " + address);
        }
        if (newState == 140) {
            stopDelayCallbackBondStateThread(address);
            NfPreference.setAutoConnectAddress(address);
            if (NfConfig.isUnconnectableAfterDeviceConnected() && this.mA2dp != null && this.mA2dp.isConnected() && this.mAvrcp != null && this.mAvrcp.isConnected()) {
                NfLog.d(this.TAG, "hfp/a2dp/avrcp connected! so unconnectable");
                NfPrimitive.setBtConnectable(false);
            }
        } else if (newState <= 110 && this.mAclDisconnectProtectDictionary != null) {
            this.mAclDisconnectProtectDictionary.onHfpDisconnected(address);
        }
        this.isHfpDisconnected = newState <= 110;
        if (this.isHfpDisconnected && NfConfig.isUseForceDisconnectMapPbap()) {
            if (this.mCaadThread != null) {
                this.mCaadThread.interrupt();
                this.mCaadThread = null;
            }
            this.mCaadThread = new CheckA2dpAvrcpDisconnectedThread();
            this.mCaadThread.start();
        }
        if (prevState == 140 && newState == 110) {
            initCheckAclDisconnectedToDictionary(address);
        }
        if (NfConfig.isAllBasicProfileEnabled() && NfConfig.isRedoBasicConnectEnable() && this.mConnectionMaintainer != null) {
            this.mConnectionMaintainer.onHfpStateChanged(address, newState);
        }
    }

    @Override // com.nforetek.bt.profile.NfBluetoothCallbackInterface
    public void onBtAdapterStateChanged(int prevState, int newState) {
        NfLog.d(this.TAG, "onBtAdapterStateChanged() state: " + prevState + "->" + newState);
    }

    @Override // com.nforetek.bt.profile.NfA2dpCallbackInterface
    public void onA2dpStateChanged(String address, int prevState, int newState) {
        NfLog.d(this.TAG, "onA2dpStateChanged() " + address + " state: " + prevState + "->" + newState);
        if (this.mAvrcp != null) {
            this.mAvrcp.onA2dpStateChanged(address, newState);
        }
        if (newState == 120 || newState == 140) {
            try {
                if (BasicConnectThread.isConnecting() && !BasicConnectThread.getSharedBasicConnectThread().getAddress().equals(address) && BasicConnectThread.getSharedBasicConnectThread().isAutoConnect()) {
                    NfLog.e(this.TAG, "A2DP connecting and auto basic connect target address is " + BasicConnectThread.getSharedBasicConnectThread().getAddress() + ", interrupt!");
                    BasicConnectThread.setInterrupt();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        if (newState == 120) {
            this.mA2dpConnectingAddress = address;
            NfLog.d(this.TAG, "Connecting address is:  " + address);
            if (this.mCheckA2dpStuckThread != null) {
                this.mCheckA2dpStuckThread.interrupt();
                this.mCheckA2dpStuckThread = null;
            }
            this.mCheckA2dpStuckThread = new CheckA2dpStuckThread(address);
            this.mCheckA2dpStuckThread.start();
        } else if (newState == 140) {
            stopDelayCallbackBondStateThread(address);
            this.isA2dpDisconnected = false;
            if (this.mCasThread != null) {
                this.mCasThread.interrupt();
                this.mCasThread = null;
            }
            this.mCasThread = new CheckAvrcpStateThread(address);
            this.mCasThread.start();
            if (this.mCheckA2dpStuckThread != null) {
                this.mCheckA2dpStuckThread.interrupt();
                this.mCheckA2dpStuckThread = null;
            }
            if (NfConfig.isUnconnectableAfterDeviceConnected() && this.mHfp != null && this.mHfp.isConnected() && this.mAvrcp != null && this.mAvrcp.isConnected()) {
                NfLog.d(this.TAG, "hfp/a2dp/avrcp connected! so unconnectable");
                NfPrimitive.setBtConnectable(false);
            }
        } else if (newState == 110) {
            this.isA2dpDisconnected = true;
            if (this.tabletMode.booleanValue() && this.isAvrcpDisconnected) {
                try {
                    this.mBluetooth.switchRoleMode(2);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
                this.tabletMode = false;
            }
            if (this.mCasThread != null) {
                this.mCasThread.interrupt();
                this.mCasThread = null;
            }
            if (this.mCheckA2dpStuckThread != null) {
                this.mCheckA2dpStuckThread.interrupt();
                this.mCheckA2dpStuckThread = null;
            }
        }
        this.isA2dpDisconnected = newState <= 110;
        if (NfConfig.isAllBasicProfileEnabled() && NfConfig.isRedoBasicConnectEnable() && this.mConnectionMaintainer != null) {
            this.mConnectionMaintainer.onA2dpStateChanged(address, newState);
        }
    }

    @Override // com.nforetek.bt.profile.NfAvrcpCallbackInterface
    public void onAvrcpStateChanged(String address, int prevState, int newState) {
        NfLog.d(this.TAG, "onAvrcpStateChanged() " + address + " state: " + prevState + "->" + newState);
        switch (newState) {
            case 110:
                this.isAvrcpDisconnected = true;
                if (this.tabletMode.booleanValue() && this.isA2dpDisconnected) {
                    try {
                        this.mBluetooth.switchRoleMode(2);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    this.tabletMode = false;
                }
                if (this.mCheckAvrcpStuckThread != null) {
                    this.mCheckAvrcpStuckThread.interrupt();
                    this.mCheckAvrcpStuckThread = null;
                    break;
                }
                break;
            case 120:
                this.mAvrcpConnectingAddress = address;
                NfLog.d(this.TAG, "Connecting address is:  " + address);
                if (this.mCheckAvrcpStuckThread != null) {
                    this.mCheckAvrcpStuckThread.interrupt();
                    this.mCheckAvrcpStuckThread = null;
                }
                this.mCheckAvrcpStuckThread = new CheckAvrcpStuckThread(address);
                this.mCheckAvrcpStuckThread.start();
                break;
            case 140:
                stopDelayCallbackBondStateThread(address);
                this.isAvrcpDisconnected = false;
                if (this.mCheckAvrcpStuckThread != null) {
                    this.mCheckAvrcpStuckThread.interrupt();
                    this.mCheckAvrcpStuckThread = null;
                }
                if (NfConfig.isUnconnectableAfterDeviceConnected() && this.mA2dp != null && this.mA2dp.isConnected() && this.mHfp != null && this.mHfp.isConnected()) {
                    NfLog.d(this.TAG, "hfp/a2dp/avrcp connected! so unconnectable");
                    NfPrimitive.setBtConnectable(false);
                }
                if (this.mA2dp.getProfileState(address) == 110) {
                    NfLog.d(this.TAG, "AVRCP connected but A2DP disconnected. Disconnect AVRCP here.");
                    this.mAvrcp.disconnect(address);
                    break;
                }
                break;
        }
        this.isAvrcpDisconnected = newState <= 110;
        if (NfConfig.isAllBasicProfileEnabled() && NfConfig.isRedoBasicConnectEnable() && this.mConnectionMaintainer != null) {
            this.mConnectionMaintainer.onAvrcpStateChanged(address, newState);
        }
    }

    @Override // com.nforetek.bt.profile.NfAvrcpCallbackInterface
    public void onAvrcpBrowseStateChanged(String address, int prevState, int newState) {
        NfLog.v(this.TAG, "onAvrcpBrowseStateChanged() " + address + " state: " + prevState + "->" + newState);
    }

    @Override // com.nforetek.bt.profile.NfPbapCallbackInterface
    public void onPbapStateChanged(String address, int prevState, int newState, int reason, int counts) {
        NfLog.v(this.TAG, "onPbapStateChanged() " + address + " state: " + prevState + "->" + newState + " reason: " + reason + " counts: " + counts);
    }

    @Override // com.nforetek.bt.profile.NfHidCallbackInterface
    public void onHidStateChanged(String address, int prevState, int newState, int reason) {
        NfLog.v(this.TAG, "onHidStateChanged() " + address + " state: " + prevState + "->" + newState + ", reason: " + reason);
    }

    @Override // com.nforetek.bt.profile.NfSppCallbackInterface
    public void onSppStateChanged(String address, int prevState, int newState) {
        NfLog.v(this.TAG, "onSppStateChanged() " + address + " state: " + prevState + "->" + newState);
    }

    @Override // com.nforetek.bt.profile.NfMapCallbackInterface
    public void onMapStateChanged(String address, int prevState, int newState, int reason) {
        NfLog.v(this.TAG, "onMapStateChanged() " + address + " state: " + prevState + "->" + newState + " reason: " + reason);
    }

    @Override // com.nforetek.bt.profile.NfOppCallbackInterface
    public void onOppStateChanged(String address, int prevState, int newState, int reason) {
        NfLog.v(this.TAG, "onOppStateChanged() " + address + " state: " + prevState + "->" + newState + " reason: " + reason);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean registerHfpCallback(INfCallbackHfp cb) {
        NfLog.v(this.TAG, "registerHfpCallback()");
        if (this.mHfpDoCallback == null) {
            return false;
        }
        return this.mHfpDoCallback.register(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean unregisterHfpCallback(INfCallbackHfp cb) {
        NfLog.v(this.TAG, "unregisterHfpCallback()");
        if (this.mHfpDoCallback == null) {
            return false;
        }
        return this.mHfpDoCallback.unregister(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean registerA2dpCallback(INfCallbackA2dp cb) {
        NfLog.v(this.TAG, "registerA2dpCallback()");
        if (this.mA2dpDoCallback == null) {
            return false;
        }
        return this.mA2dpDoCallback.register(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean unregisterA2dpCallback(INfCallbackA2dp cb) {
        NfLog.v(this.TAG, "unregisterA2dpCallback()");
        if (this.mA2dpDoCallback == null) {
            return false;
        }
        return this.mA2dpDoCallback.unregister(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean registerAvrcpCallback(INfCallbackAvrcp cb) {
        NfLog.v(this.TAG, "registerAvrcpCallback()");
        if (this.mAvrcpDoCallback == null) {
            return false;
        }
        return this.mAvrcpDoCallback.register(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean unregisterAvrcpCallback(INfCallbackAvrcp cb) {
        NfLog.v(this.TAG, "unregisterAvrcpCallback()");
        if (this.mAvrcpDoCallback == null) {
            return false;
        }
        return this.mAvrcpDoCallback.unregister(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean registerPbapCallback(INfCallbackPbap cb) {
        NfLog.v(this.TAG, "registerPbapCallback()");
        if (this.mPbapDoCallback == null) {
            return false;
        }
        return this.mPbapDoCallback.register(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean unregisterPbapCallback(INfCallbackPbap cb) {
        NfLog.v(this.TAG, "unregisterPbapCallback()");
        if (this.mPbapDoCallback == null) {
            return false;
        }
        return this.mPbapDoCallback.unregister(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean registerBluetoothCallback(INfCallbackBluetooth cb) {
        NfLog.v(this.TAG, "registerBluetoothCallback()");
        if (this.mBluetoothDoCallback == null) {
            return false;
        }
        return this.mBluetoothDoCallback.register(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean unregisterBluetoothCallback(INfCallbackBluetooth cb) {
        NfLog.v(this.TAG, "unregisterBluetoothCallback()");
        if (this.mBluetoothDoCallback == null) {
            return false;
        }
        return this.mBluetoothDoCallback.unregister(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean registerHidCallback(INfCallbackHid cb) {
        NfLog.v(this.TAG, "registerHidCallback()");
        if (this.mHidDoCallback == null) {
            return false;
        }
        return this.mHidDoCallback.register(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean unregisterHidCallback(INfCallbackHid cb) {
        NfLog.v(this.TAG, "unregisterHidCallback()");
        if (this.mHidDoCallback == null) {
            return false;
        }
        return this.mHidDoCallback.unregister(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean registerSppCallback(INfCallbackSpp cb) {
        NfLog.v(this.TAG, "registerSppCallback()");
        if (this.mSppDoCallback == null) {
            return false;
        }
        return this.mSppDoCallback.register(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean unregisterSppCallback(INfCallbackSpp cb) {
        NfLog.v(this.TAG, "unregisterSppCallback()");
        if (this.mSppDoCallback == null) {
            return false;
        }
        return this.mSppDoCallback.unregister(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean registerMapCallback(INfCallbackMap cb) {
        NfLog.v(this.TAG, "registerMapCallback()");
        if (this.mMapDoCallback == null) {
            return false;
        }
        return this.mMapDoCallback.register(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean unregisterMapCallback(INfCallbackMap cb) {
        NfLog.v(this.TAG, "unregisterMapCallback()");
        if (this.mMapDoCallback == null) {
            return false;
        }
        return this.mMapDoCallback.unregister(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean registerOppCallback(INfCallbackOpp cb) {
        NfLog.v(this.TAG, "registerOppCallback()");
        if (this.mOppDoCallback == null) {
            NfLog.e(this.TAG, "mOppDoCallback == null");
            return false;
        }
        return this.mOppDoCallback.register(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean unregisterOppCallback(INfCallbackOpp cb) {
        NfLog.v(this.TAG, "unregisterOppCallback()");
        if (this.mOppDoCallback == null) {
            return false;
        }
        return this.mOppDoCallback.unregister(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean registerGattServerCallback(INfCallbackGattServer cb) {
        NfLog.v(this.TAG, "registerGattServerCallback()");
        if (this.mGattServerDoCallback == null) {
            return false;
        }
        return this.mGattServerDoCallback.register(cb);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean unregisterGattServerCallback(INfCallbackGattServer cb) {
        NfLog.v(this.TAG, "unregisterGattServerCallback()");
        if (this.mGattServerDoCallback == null) {
            return false;
        }
        return this.mGattServerDoCallback.unregister(cb);
    }

    @Override // com.nforetek.bt.profile.bluetooth.BasicConnectThread.BasicConnectCallbackInterface
    public void basicConnectDidFinished(String address, boolean isHfpSuccess, boolean isA2dpSuccess, boolean isAvrcpSuccess) {
        NfLog.v(this.TAG, "basicConnectDidFinished() " + address + " HFP: " + isHfpSuccess + " A2DP: " + isA2dpSuccess + " AVRCP: " + isAvrcpSuccess);
    }

    @Override // com.nforetek.bt.profile.bluetooth.BasicDisconnectThread.BasicDisconnectCallbackInterface
    public void basicDisconnectDidFinished(boolean isHfpSuccess, boolean isA2dpSuccess, boolean isAvrcpSuccess) {
        NfLog.v(this.TAG, "basicDisconnectDidFinished() HFP: " + isHfpSuccess + " A2DP: " + isA2dpSuccess + " AVRCP: " + isAvrcpSuccess);
        this.mDisconnectThread = null;
    }

    @Override // com.nforetek.bt.manager.NfServiceManagerInterface
    public boolean isJniInitFinished() {
        NfLog.v(this.TAG, "isJniInitFinished() " + this.mIsJniInitFinished);
        return this.mIsJniInitFinished;
    }

    @Override // com.nforetek.bt.manager.NfServiceManagerInterface
    public boolean isServiceReady() {
        NfLog.v(this.TAG, "isServiceReady()");
        if (!(NfPrimitive.isBtEnabled() && this.mIsJniInitFinished) && NfPrimitive.isBtEnabled()) {
            NfLog.v(this.TAG, "NfPrimitive.isBtEnabled() " + NfPrimitive.isBtEnabled());
            NfLog.v(this.TAG, "mIsJniInitFinished: " + this.mIsJniInitFinished);
            return false;
        }
        return true;
    }

    @Override // com.nforetek.bt.profile.bluetooth.CheckAclDisconnectedThread.CheckAclDisconnectedCallbackInterface
    public void onCheckAclDisconnectedFinidhed(String address, boolean isNeedRemoveAclConnection) {
        NfLog.v(this.TAG, "onCheckAclDisconnectedFinidhed() " + address + " isNeedRemoveAclConnection: " + isNeedRemoveAclConnection);
        if (isNeedRemoveAclConnection && this.mJni != null) {
            this.mJni.reqBtRemoveAclConnection(address);
        }
        if (this.mCheckAclDisconnectedDictionary != null) {
            this.mCheckAclDisconnectedDictionary.remove(address);
        }
    }

    @Override // com.nforetek.bt.profile.bluetooth.CheckProfileDisconnectedThread.CheckProfileDisconnectedCallbackInterface
    public void onCheckProfileDisconnectedFinidhed(String address) {
        NfLog.v(this.TAG, "onCheckProfileDisconnectedFinidhed() " + address);
    }

    @Override // com.nforetek.bt.profile.bluetooth.BasicConnectionInterface
    public int basicConnect(String address, boolean forceConnect, boolean singleTry, boolean isAutoConnect) {
        int result = -1;
        NfLog.v(this.TAG, "basicConnect() " + address + " isAutoConnect: " + isAutoConnect + " forceConnect: " + forceConnect);
        if (this.mAclDisconnectProtectDictionary.isStillInAclDisconnectProtectTime(address) && !forceConnect) {
            NfLog.e(this.TAG, "In ACL Disconnected " + AclDisconnectedProtectDictionary.PROTECT_TIME + "ms time, reject connect command.");
        } else if (BasicConnectThread.isConnecting() && BasicConnectThread.getSharedBasicConnectThread().isAutoConnect() && BasicConnectThread.getSharedBasicConnectThread().getAddress().equals(address) && !forceConnect) {
            NfLog.e(this.TAG, "Basic connect target address is same as auto connect thread address: " + BasicConnectThread.getSharedBasicConnectThread().getAddress() + ". Reject command.");
        } else if (BasicConnectThread.isConnecting() && !BasicConnectThread.getSharedBasicConnectThread().isAutoConnect() && !forceConnect) {
            NfLog.e(this.TAG, "Basic connect thread is running, target address is " + BasicConnectThread.getSharedBasicConnectThread().getAddress() + ". Reject command.");
        } else {
            if (!isAutoConnect) {
                synchronized (this.atLock) {
                    interruptAutoConnectThreadDirectly();
                }
            }
            if (NfPrimitive.isDiscovering()) {
                if (!isAutoConnect) {
                    NfPrimitive.startScan(false);
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            result = BasicConnectThread.getNeedConnectMask(address, this.mHfp, this.mA2dp, this.mAvrcp);
            if (result > 0 || forceConnect) {
                try {
                    if (BasicConnectThread.isConnecting()) {
                        NfLog.e(this.TAG, "BasicConnectThread is still running (" + BasicConnectThread.getSharedBasicConnectThread().hashCode() + "). Interrupt it.");
                        BasicConnectThread.setInterrupt();
                    }
                } catch (NullPointerException e2) {
                    e2.printStackTrace();
                }
                for (int count = 50; count > 0 && BasicConnectThread.isConnecting(); count--) {
                    NfLog.v(this.TAG, "Waiting for basic connect thread stop.");
                    try {
                        Thread.sleep(20L);
                    } catch (InterruptedException e3) {
                    }
                }
                Thread t = BasicConnectThread.createBasicConnectThread(address, this, singleTry, isAutoConnect);
                if (t != null) {
                    t.start();
                } else {
                    NfLog.e(this.TAG, "BasicConnectThread is still running (" + BasicConnectThread.getSharedBasicConnectThread().hashCode() + ").");
                }
            }
        }
        return result;
    }

    @Override // com.nforetek.bt.profile.bluetooth.BasicConnectionInterface
    public int basicDisconnect() {
        NfLog.v(this.TAG, "basicDisconnect()");
        synchronized (this.atLock) {
            interruptAutoConnectThreadDirectly();
        }
        if (this.mDisconnectThread != null) {
            NfLog.e(this.TAG, "BasicDisconnectThread is still running. " + this.mDisconnectThread.hashCode() + " Reject command.");
            return -1;
        }
        try {
            if (BasicConnectThread.isConnecting()) {
                NfLog.e(this.TAG, "BasicConnectThread is still running. " + BasicConnectThread.getSharedBasicConnectThread().hashCode() + " interrupt the lastest.");
                BasicConnectThread.setInterrupt();
            }
        } catch (NullPointerException e) {
            try {
                e.printStackTrace();
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            }
        }
        int result = BasicDisconnectThread.getNeedDisconnectMask(NfDef.DEFAULT_ADDRESS, this.mHfp, this.mA2dp, this.mAvrcp, this.mHid, this.mMap, this.mPbap, this.mSpp);
        if (result > 0) {
            this.mDisconnectThread = new BasicDisconnectThread(this.mHfp, this.mA2dp, this.mAvrcp, this.mHid, this.mMap, this.mPbap, this.mSpp, this);
            this.mDisconnectThread.start();
            return result;
        }
        return result;
    }

    @Override // com.nforetek.bt.profile.bluetooth.BasicConnectionInterface
    public int basicDisconnect(String address, boolean isUnpair) {
        NfLog.v(this.TAG, "basicDisconnect() " + address + " isUnpair: " + isUnpair);
        synchronized (this.atLock) {
            interruptAutoConnectThreadDirectly();
        }
        if (this.mDisconnectThread != null) {
            NfLog.e(this.TAG, "BasicDisconnectThread is still running. " + this.mDisconnectThread.hashCode() + " Reject command.");
            return -1;
        }
        try {
            if (BasicConnectThread.isConnecting()) {
                NfLog.e(this.TAG, "BasicConnectThread is still running. " + BasicConnectThread.getSharedBasicConnectThread().hashCode() + " interrupt the lastest.");
                BasicConnectThread.setInterrupt();
            }
        } catch (NullPointerException e) {
            try {
                e.printStackTrace();
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            }
        }
        int result = BasicDisconnectThread.getNeedDisconnectMask(address, this.mHfp, this.mA2dp, this.mAvrcp, this.mHid, this.mMap, this.mPbap, this.mSpp);
        if (result > 0) {
            this.mDisconnectThread = new BasicDisconnectThread(this.mHfp, this.mA2dp, this.mAvrcp, this.mHid, this.mMap, this.mPbap, this.mSpp, this, isUnpair, address);
            this.mDisconnectThread.start();
            return result;
        }
        return result;
    }

    @Override // com.nforetek.bt.profile.bluetooth.BasicConnectionInterface
    public int disconnectA2dpAvrcp() {
        NfLog.v(this.TAG, "disconnectA2dpAvrcp()");
        this.tabletMode = true;
        return 0;
    }

    @Override // com.nforetek.bt.profile.bluetooth.BasicConnectionInterface
    public boolean isBasicConnecting() {
        NfLog.v(this.TAG, "isBasicConnecting()");
        try {
            if (BasicConnectThread.isConnecting() && !BasicConnectThread.getSharedBasicConnectThread().isAutoConnect()) {
                NfLog.e(this.TAG, "BasicConnectThread is still running. " + BasicConnectThread.getSharedBasicConnectThread().hashCode());
                return true;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.bluetooth.BasicConnectionInterface
    public boolean isBasicDisconnecting() {
        NfLog.v(this.TAG, "isBasicDisconnecting()");
        if (this.mDisconnectThread != null) {
            NfLog.e(this.TAG, "BasicDisconnectThread is still running." + this.mDisconnectThread.hashCode());
            return true;
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.bluetooth.BasicConnectionInterface
    public boolean isAutoConnecting(boolean forceStop) {
        NfLog.v(this.TAG, "isAutoConnecting()");
        NfLog.v(this.TAG, "mArThread: " + this.mArThread);
        NfLog.v(this.TAG, "BasicConnectThread: " + BasicConnectThread.getSharedBasicConnectThread());
        boolean result = false;
        try {
            if (BasicConnectThread.isConnecting()) {
                NfLog.v(this.TAG, "mConnectThread is auto connect? " + BasicConnectThread.getSharedBasicConnectThread().isAutoConnect());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (this.mArThread != null) {
            NfLog.e(this.TAG, "AutoConnectThread is still running. " + this.mArThread.hashCode());
            result = true;
        }
        try {
            if (BasicConnectThread.isConnecting() && BasicConnectThread.getSharedBasicConnectThread().isAutoConnect()) {
                NfLog.e(this.TAG, "mConnectThread(isAutoConnect) is still running. " + BasicConnectThread.getSharedBasicConnectThread().hashCode());
                result = true;
            }
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
        if (forceStop) {
            synchronized (this.atLock) {
                interruptAutoConnectThreadDirectly();
            }
            try {
                if (BasicConnectThread.isConnecting() && BasicConnectThread.getSharedBasicConnectThread().isAutoConnect()) {
                    BasicConnectThread.setInterrupt();
                }
            } catch (NullPointerException e3) {
                e3.printStackTrace();
            }
        }
        return result;
    }

    @Override // com.nforetek.bt.profile.bluetooth.BasicConnectionInterface
    public int getAutoConnectState() {
        if (NfPreference.getAutoConnectCondition() == 0) {
            return 100;
        }
        if (this.mArThread != null) {
            try {
                return this.mArThread.getAutoConnectState();
            } catch (NullPointerException e) {
                return 110;
            }
        }
        return 110;
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean isAclConnected(String address) {
        if (this.mIsAclConnectedSet != null) {
            return this.mIsAclConnectedSet.contains(address);
        }
        return false;
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public boolean initDelayPairThread(String address) {
        return initDelayPairToDictionary(address);
    }

    @Override // com.nforetek.bt.callback.NfDoCallbackInterface
    public void forceStopAutoConnectThread() {
        NfLog.d(this.TAG, "forceStopAutoConnectThread()");
        synchronized (this.atLock) {
            interruptAutoConnectThreadDirectly();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CheckJniBindedThread extends Thread {
        private CheckJniBindedThread() {
        }

        /* synthetic */ CheckJniBindedThread(NfServiceManager nfServiceManager, CheckJniBindedThread checkJniBindedThread) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            NfLog.d(NfServiceManager.this.TAG, "CheckJniBindedThread(" + hashCode() + ") started.");
            while (!NfPrimitive.isBtEnabled()) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    NfLog.d(NfServiceManager.this.TAG, "CheckJniBindedThread(" + hashCode() + ") interrupted.");
                    return;
                }
            }
            if (NfServiceManager.this.mJni != null && !NfServiceManager.this.mJni.isServiceBinded()) {
                NfLog.d(NfServiceManager.this.TAG, "in CheckJniBindedThread and jni service not binded. bind it.");
                NfServiceManager.this.mJni.bindService();
            }
            NfLog.d(NfServiceManager.this.TAG, "CheckJniBindedThread(" + hashCode() + ") stopped.");
        }
    }

    /* loaded from: classes.dex */
    private class CheckAvrcpStuckThread extends Thread {
        private String mAddr;

        public CheckAvrcpStuckThread(String addr) {
            this.mAddr = NfDef.DEFAULT_ADDRESS;
            this.mAddr = addr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            NfLog.d(NfServiceManager.this.TAG, "CheckAvrcpStuckThread(" + hashCode() + ") started.");
            try {
                Thread.sleep(3000L);
                try {
                    if (NfServiceManager.this.mAvrcp.getProfileState(this.mAddr) == 120) {
                        NfLog.d(NfServiceManager.this.TAG, "CheckAvrcpStuckThread(" + hashCode() + ") AVRCP device: " + this.mAddr + " is still connecting, disconnect it.");
                        NfServiceManager.this.mAvrcp.disconnect(this.mAddr);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                NfLog.d(NfServiceManager.this.TAG, "CheckAvrcpStuckThread(" + hashCode() + ") stopped.");
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                NfLog.d(NfServiceManager.this.TAG, "CheckAvrcpStuckThread(" + hashCode() + ") interrupted.");
            }
        }
    }

    /* loaded from: classes.dex */
    private class CheckA2dpStuckThread extends Thread {
        private String mAddr;

        public CheckA2dpStuckThread(String addr) {
            this.mAddr = NfDef.DEFAULT_ADDRESS;
            this.mAddr = addr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            NfLog.d(NfServiceManager.this.TAG, "CheckA2dpStuckThread(" + hashCode() + ") started.");
            try {
                Thread.sleep(10000L);
                try {
                    if (NfServiceManager.this.mA2dp.getProfileState(this.mAddr) == 120) {
                        NfLog.d(NfServiceManager.this.TAG, "CheckA2dpStuckThread(" + hashCode() + ") A2DP device: " + this.mAddr + " is still connecting, disconnect it.");
                        NfServiceManager.this.mA2dp.disconnect(this.mAddr);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                NfLog.d(NfServiceManager.this.TAG, "CheckA2dpStuckThread(" + hashCode() + ") stopped.");
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                NfLog.d(NfServiceManager.this.TAG, "CheckA2dpStuckThread(" + hashCode() + ") interrupted.");
            }
        }
    }

    /* loaded from: classes.dex */
    private class CheckAvrcpStateThread extends Thread {
        private String TAG = "CheckAvrcpStateThread";
        private String mTargetAddress = NfDef.DEFAULT_ADDRESS;

        public CheckAvrcpStateThread(String address) {
            onCreate(address);
        }

        private void onCreate(String address) {
            NfLog.d(this.TAG, "CheckAvrcpStateThread(" + hashCode() + ") onCreate(): " + address);
            this.mTargetAddress = address;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            NfLog.d(this.TAG, "CheckAvrcpStateThread(" + hashCode() + ") started.");
            try {
                Thread.sleep(1000L);
                if (NfServiceManager.this.mAvrcp.getProfileState() == 110 && NfServiceManager.this.mA2dp.getProfileState() >= 140) {
                    NfLog.d(this.TAG, "After A2DP connected but AVRCP still disconnected. Try to Connect AVRCP.");
                    NfServiceManager.this.mAvrcp.connect(this.mTargetAddress);
                }
                NfLog.d(this.TAG, "CheckAvrcpStateThread(" + hashCode() + " stopped.");
            } catch (InterruptedException e) {
                e.printStackTrace();
                NfLog.d(this.TAG, "CheckAvrcpStateThread(" + hashCode() + ") interrupted.");
            }
        }
    }

    /* loaded from: classes.dex */
    private class RmAutoConnectThread extends AutoConnectThread {
        public RmAutoConnectThread(String address) {
            super(NfServiceManager.this.mAutoConnectListener, address, 0);
        }

        public RmAutoConnectThread(String address, int autoConnectType) {
            super(NfServiceManager.this.mAutoConnectListener, address, autoConnectType);
        }
    }

    /* loaded from: classes.dex */
    private class CheckA2dpAvrcpDisconnectedThread extends Thread {
        String TAG = "CheckA2dpAvrcpDisconnectedThread";

        public CheckA2dpAvrcpDisconnectedThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            NfLog.d(this.TAG, "CheckA2dpAvrcpDisconnectedThread(" + hashCode() + ") started.");
            int logCount = 0;
            int timeoutCount = 0;
            while (true) {
                if (!NfPrimitive.isBtEnabled()) {
                    break;
                }
                try {
                    if (NfServiceManager.this.isHfpDisconnected && NfServiceManager.this.isA2dpDisconnected && NfServiceManager.this.isAvrcpDisconnected) {
                        NfLog.d(this.TAG, "CheckA2dpAvrcpDisconnectedThread catch disconnected");
                        break;
                    }
                    logCount++;
                    timeoutCount++;
                    if (logCount >= 10) {
                        NfLog.d(this.TAG, "CheckA2dpAvrcpDisconnectedThread detecting");
                        logCount = 0;
                    }
                    if (timeoutCount >= 400) {
                        NfLog.d(this.TAG, "CheckA2dpAvrcpDisconnectedThread detecting timeout!");
                        return;
                    }
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    NfLog.d(this.TAG, "CheckA2dpAvrcpDisconnectedThread(" + hashCode() + ") interrupted.");
                    return;
                }
            }
            NfLog.d(this.TAG, "CheckA2dpAvrcpDisconnectedThread(" + hashCode() + ") stopped.");
            try {
                if (NfServiceManager.this.mMap != null) {
                    NfServiceManager.this.mMap.forceResetState();
                }
            } catch (Exception e2) {
                NfLog.e(this.TAG, "CheckA2dpAvrcpDisconnectedThread mMap got error.");
                e2.printStackTrace();
            }
            try {
                if (NfServiceManager.this.mPbap != null) {
                    NfServiceManager.this.mPbap.downloadInterrupt();
                }
            } catch (Exception e3) {
                NfLog.e(this.TAG, "CheckA2dpAvrcpDisconnectedThread mPbap got error.");
                e3.printStackTrace();
            }
        }
    }

    private void stopDelayCallbackBondStateThread(String address) {
        NfLog.d(this.TAG, "stopDelayCallbackBondStateThread(): " + address);
        DelayCallbackBondStateThread t = this.mDelayCallbackBondStateDictionary.get(address);
        if (t != null) {
            t.interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class DelayCallbackBondStateThread extends Thread {
        String TAG = "DelayCallbackBondStateThread";
        String mAddress;
        String mName;

        public DelayCallbackBondStateThread(String address, String name) {
            this.mAddress = NfDef.DEFAULT_ADDRESS;
            this.mName = "";
            this.mAddress = address;
            this.mName = name;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            NfLog.d(this.TAG, "DelayCallbackBondStateThread(" + hashCode() + ") started.");
            int count = 20;
            while (true) {
                if (count <= 0) {
                    break;
                }
                try {
                    Thread.sleep(250L);
                    if (NfServiceManager.this.isAclConnected(this.mAddress)) {
                        if (NfServiceManager.this.mHfp == null || !NfServiceManager.this.mHfp.isConnected() || !NfServiceManager.this.mHfp.getConnectedAddress().equals(this.mAddress)) {
                            if (NfServiceManager.this.mA2dp == null || !NfServiceManager.this.mA2dp.isConnected() || !NfServiceManager.this.mA2dp.getConnectedAddress().equals(this.mAddress)) {
                                if (NfServiceManager.this.mAvrcp != null && NfServiceManager.this.mAvrcp.isConnected() && NfServiceManager.this.mAvrcp.getConnectedAddress().equals(this.mAddress)) {
                                    NfLog.d(this.TAG, "AVRCP is connected! callback now.");
                                    break;
                                } else if (!NfPrimitive.isBtEnabled()) {
                                    NfLog.d(this.TAG, "BT is off! callback now.");
                                    break;
                                } else {
                                    count--;
                                }
                            } else {
                                NfLog.d(this.TAG, "A2DP is connected! callback now.");
                                break;
                            }
                        } else {
                            NfLog.d(this.TAG, "HFP is connected! callback now.");
                            break;
                        }
                    } else {
                        NfLog.d(this.TAG, "ACL is disconnected! callback now.");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (count <= 0) {
                NfLog.d(this.TAG, "After 5 seconds ACL is still connected. callback now.");
            }
            NfServiceManager.this.mDelayCallbackBondStateDictionary.remove(this.mAddress);
            NfServiceManager.this.mBluetoothDoCallback.onDeviceBondStateChanged(this.mAddress, this.mName, NfDef.BOND_BONDING, NfDef.BOND_BONDED);
            NfLog.d(this.TAG, "DelayCallbackBondStateThread(" + hashCode() + ") finished.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class VerifyLocalBtStateThread extends Thread {
        String TAG = "VerifyLocalBtStateThread";

        public VerifyLocalBtStateThread() {
            NfLog.d(this.TAG, "VerifyLocalBtStateThread(" + hashCode() + ")");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(2000L);
                if (NfServiceManager.this.mBluetooth.getBtLocalCachedState() != NfPrimitive.getAdapterState()) {
                    NfLog.d(this.TAG, "Cached BT state is not equal with system BT state. CB: " + NfServiceManager.this.mBluetooth.getBtLocalCachedState() + " <-> SB: " + NfPrimitive.getAdapterState());
                    if (NfServiceManager.this.mBluetooth.getBtLocalCachedState() != 300) {
                        NfServiceManager.this.mBluetooth.setBtLocalCachedState(NfDef.BT_STATE_OFF);
                        NfServiceManager.this.mBluetoothDoCallback.onAdapterStateChanged(NfServiceManager.this.mBluetooth.getBtLocalCachedState(), NfPrimitive.getAdapterState());
                    }
                }
                NfServiceManager.this.mVlbsThread = null;
                NfLog.d(this.TAG, "VerifyLocalBtStateThread(" + hashCode() + ") finished.");
            } catch (InterruptedException e) {
                NfLog.d(this.TAG, "VerifyLocalBtStateThread(" + hashCode() + ") interrupted.");
                NfServiceManager.this.mVlbsThread = null;
            }
        }
    }

    public void onQueueForScanThreadFinished() {
        this.mQueueForScanThread = null;
        if (NfPrimitive.isAdapterEnabled()) {
            this.mBluetooth.startDiscovery();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class QueueForScanThread extends Thread {
        long mTimeoutTime;

        private QueueForScanThread() {
        }

        /* synthetic */ QueueForScanThread(NfServiceManager nfServiceManager, QueueForScanThread queueForScanThread) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            NfLog.d(NfServiceManager.this.TAG, "QueueForScanThread(" + hashCode() + ") started.");
            this.mTimeoutTime = System.currentTimeMillis() + 10000;
            while (this.mTimeoutTime > System.currentTimeMillis()) {
                boolean isProfileConnecting = false;
                if (NfServiceManager.this.mHfp != null && NfServiceManager.this.mHfp.isConnecting()) {
                    isProfileConnecting = true;
                }
                if (NfServiceManager.this.mA2dp != null && NfServiceManager.this.mA2dp.isConnecting()) {
                    isProfileConnecting = true;
                }
                if (NfServiceManager.this.mAvrcp != null && NfServiceManager.this.mAvrcp.isConnecting()) {
                    isProfileConnecting = true;
                }
                if (!isProfileConnecting) {
                    break;
                }
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            NfServiceManager.this.onQueueForScanThreadFinished();
            NfLog.d(NfServiceManager.this.TAG, "QueueForScanThread(" + hashCode() + ") finished.");
        }

        public void onSystemTimeChanged() {
            this.mTimeoutTime = System.currentTimeMillis() + 10000;
            NfLog.d(NfServiceManager.this.TAG, "QueueForScanThread(" + hashCode() + ") onSystemTimeChanged, timeout set to " + this.mTimeoutTime);
        }
    }

    private boolean isAnyBasicProfileConnected() {
        boolean resultHfp = NfConfig.isEnableHfp() && this.mHfp != null && this.mHfp.isConnected();
        boolean resultA2dp = NfConfig.isEnableA2dp() && this.mA2dp != null && this.mA2dp.isConnected();
        boolean resultAvrcp = NfConfig.isEnableAvrcp() && this.mAvrcp != null && this.mAvrcp.isConnected();
        boolean result = resultHfp || resultA2dp || resultAvrcp;
        NfLog.v(this.TAG, "isAnyBasicProfileConnected: " + result);
        return result;
    }

    @Override // com.nforetek.bt.manager.NfManagerService
    public void onNfJniServiceConnected(boolean isConnected) {
        NfLog.v(this.TAG, "onNfJniServiceConnected() isConnected: " + isConnected);
        if (this.mVlbsThread == null && !isConnected) {
            this.mVlbsThread = new VerifyLocalBtStateThread();
            this.mVlbsThread.start();
        }
        if (!isConnected) {
            NfLog.v(this.TAG, "Force reset internal state when jni service disconnected.");
            onBtOff();
        }
    }

    @Override // com.nforetek.bt.manager.NfManagerService
    protected void onSystemTimeChanged() {
        if (this.mAclDisconnectProtectDictionary != null) {
            this.mAclDisconnectProtectDictionary.reset();
        }
        if (this.mQueueForScanThread != null) {
            try {
                this.mQueueForScanThread.onSystemTimeChanged();
            } catch (NullPointerException e) {
            }
        }
    }

    @Override // com.nforetek.bt.profile.bluetooth.AutoConnectListener
    public int doBasicConnect(String address, boolean forceConnect, boolean singleTry, boolean isAutoConnect) {
        return basicConnect(address, forceConnect, singleTry, isAutoConnect);
    }

    @Override // com.nforetek.bt.profile.bluetooth.AutoConnectListener
    public boolean isHfpConnected() {
        if (this.mHfp != null) {
            return this.mHfp.isConnected();
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.bluetooth.AutoConnectListener
    public boolean isA2dpConnected() {
        if (this.mA2dp != null) {
            return this.mA2dp.isConnected();
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.bluetooth.AutoConnectListener
    public boolean isAvrcpConnected() {
        if (this.mAvrcp != null) {
            return this.mAvrcp.isConnected();
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.bluetooth.AutoConnectListener
    public boolean isHfpConnecting() {
        if (this.mHfp != null) {
            return this.mHfp.isConnecting();
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.bluetooth.AutoConnectListener
    public boolean isA2dpConnecting() {
        if (this.mA2dp != null) {
            return this.mA2dp.isConnecting();
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.bluetooth.AutoConnectListener
    public boolean isAvrcpConnecting() {
        if (this.mAvrcp != null) {
            return this.mAvrcp.isConnecting();
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.bluetooth.AutoConnectListener
    public void onBtAutoConnectStateChanged(String address, int prevState, int newState) {
        this.mBluetoothDoCallback.onBtAutoConnectStateChanged(address, prevState, newState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void interruptAutoConnectThreadDirectly() {
        try {
            if (this.mArThread != null) {
                this.mArThread.interrupt();
                this.mArThread = null;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void interruptAutoConnectThreadDirectly(String address) {
        try {
            if (this.mArThread != null && this.mArThread.getTargetAddress().equals(address)) {
                this.mArThread.interrupt();
                this.mArThread = null;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAutoConnectThreadDirectly(String address, int type, int period) {
        if (period == -1) {
            this.mArThread = new AutoConnectThread(this.mAutoConnectListener, address, type);
        } else {
            this.mArThread = new AutoConnectThread(this.mAutoConnectListener, address, type, period);
        }
        this.mArThread.start();
    }
}
