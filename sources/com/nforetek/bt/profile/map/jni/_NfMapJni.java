package com.nforetek.bt.profile.map.jni;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.nforetek.bt.callback.NfDoCallbackMap;
import com.nforetek.bt.jni.NfJni;
import com.nforetek.bt.jni.NfJniBundle;
import com.nforetek.bt.profile.map._NfMap;
import com.nforetek.bt.profile.map.jni.NfMapCommand;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.Constants;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.MessageHelper;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public final class _NfMapJni extends _NfMap {
    private static final int BTMCE_STATE_CONNECTED = 3;
    private static final int BTMCE_STATE_CONNECTED_MNS = 4;
    private static final int BTMCE_STATE_CONNECTING = 1;
    private static final int BTMCE_STATE_DISCONNECTED = 0;
    private static final int BTMCE_STATE_DISCONNECTING = 2;
    private static final int BTMCE_STATE_DOWNLOADING = 5;
    private static final int BTMCE_STATE_UPLOADING = 6;
    private static final int HANDLER_MESSAGE_DB_AVAILABLE = 1;
    private static final int HANDLER_MESSAGE_DB_CLEAN = 3;
    private static final int HANDLER_MESSAGE_DB_DELETE = 2;
    private static final int STORAGE_TYPE_BY_PASS = 0;
    private static final int STORAGE_TYPE_TO_DATABASE = 1;
    HandlerThread ht;
    NfMapCommandProcessor mCommandProcessor;
    NfMapCommand mCurrentCommand;
    Handler mCheckDbAvailableHandler = null;
    private String mAddress = NfDef.DEFAULT_ADDRESS;
    private int mNotifyFreq = 0;

    @Override // com.nforetek.bt.profile._NfProfile
    protected String getLogTag() {
        return "NfMapJni";
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected int getProfileCode() {
        return 7;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onDestroy() {
        if (this.mCheckDbAvailableHandler != null) {
            this.mCheckDbAvailableHandler.removeCallbacksAndMessages(null);
            this.mCheckDbAvailableHandler = null;
        }
        this.ht.getLooper().quit();
        this.ht = null;
        this.mCommandProcessor.release();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.profile._NfProfile
    public void onCreate() {
        super.onCreate();
        this.ht = new HandlerThread("NfMapHandler");
        this.ht.start();
        this.mCheckDbAvailableHandler = initCheckDbAvaliableHandler();
        this.mCommandProcessor = new NfMapCommandProcessor();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void forceResetState() {
        this.mAddress = NfDef.DEFAULT_ADDRESS;
        this.mCurrentCommand = null;
        this.mCommandProcessor.reset();
        super.forceResetState();
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onBtAclDisconnected(String address) {
        super.onBtAclDisconnected(address);
        if (this.mAddress.equals(address)) {
            forceResetState();
        }
    }

    private Handler initCheckDbAvaliableHandler() {
        return this.mCheckDbAvailableHandler == null ? new Handler(this.ht.getLooper()) { // from class: com.nforetek.bt.profile.map.jni._NfMapJni.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String address = bundle.getString(NfDef.EXTRA_DEVICE_ADDRESS);
                NfLog.v(_NfMapJni.this.TAG, "CheckDbAvailableHandler handleMessage: " + msg.what);
                switch (msg.what) {
                    case 1:
                        if (!_NfMapJni.this.mCommandProcessor.isCommandQueueEmpty()) {
                            NfLog.v(_NfMapJni.this.TAG, "Waiting for command processing. command queue size: " + _NfMapJni.this.mCommandProcessor.getCommandQueueSize());
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        } else if (hasMessages(2) || hasMessages(3)) {
                            NfLog.v(_NfMapJni.this.TAG, "Waiting for DB delete.");
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        } else if (!MessageHelper.isUpdating()) {
                            ((NfDoCallbackMap) _NfMapJni.this.callback()).retMapDatabaseAvailable();
                            return;
                        } else {
                            NfLog.v(_NfMapJni.this.TAG, "Waiting for DB updating.");
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        }
                    case 2:
                        if (!_NfMapJni.this.mCommandProcessor.isCommandQueueEmpty()) {
                            NfLog.v(_NfMapJni.this.TAG, "Waiting for command processing. command queue size: " + _NfMapJni.this.mCommandProcessor.getCommandQueueSize());
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        } else if (!MessageHelper.isUpdating()) {
                            MessageHelper.deleteMessageByMacAddress(_NfMapJni.this.context(), address);
                            return;
                        } else {
                            NfLog.v(_NfMapJni.this.TAG, "Waiting for DB updating.");
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        }
                    case 3:
                        if (!_NfMapJni.this.mCommandProcessor.isCommandQueueEmpty()) {
                            NfLog.v(_NfMapJni.this.TAG, "Waiting for command processing. command queue size: " + _NfMapJni.this.mCommandProcessor.getCommandQueueSize());
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        } else if (!MessageHelper.isUpdating()) {
                            MessageHelper.deleteAllMessage(_NfMapJni.this.context());
                            return;
                        } else {
                            NfLog.v(_NfMapJni.this.TAG, "Waiting for DB updating.");
                            processSendMessageDelayed(msg, Constants.MAX_RECORDS_IN_DATABASE);
                            return;
                        }
                    default:
                        NfLog.e(_NfMapJni.this.TAG, "Unknown message type: " + msg.what);
                        return;
                }
            }

            private void processSendMessageDelayed(Message msg, int delay) {
                Message newMsg = obtainMessage();
                newMsg.what = msg.what;
                removeMessages(msg.what);
                sendMessageDelayed(newMsg, delay);
            }
        } : this.mCheckDbAvailableHandler;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean isConnected() {
        return getProfileState() >= 140;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public String getConnetedAddress() {
        return NfDef.DEFAULT_ADDRESS;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public void setDownloadNotifyFrequency(int notify) {
        if (notify < 0) {
            this.mNotifyFreq = 0;
        } else {
            this.mNotifyFreq = notify;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0023 A[Catch: all -> 0x007e, TryCatch #0 {, blocks: (B:3:0x0001, B:10:0x000f, B:11:0x001b, B:13:0x0023, B:15:0x0047, B:17:0x004f, B:18:0x0066, B:20:0x006c, B:22:0x0079, B:27:0x0081, B:28:0x0089, B:14:0x003a), top: B:30:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0047 A[Catch: all -> 0x007e, TryCatch #0 {, blocks: (B:3:0x0001, B:10:0x000f, B:11:0x001b, B:13:0x0023, B:15:0x0047, B:17:0x004f, B:18:0x0066, B:20:0x006c, B:22:0x0079, B:27:0x0081, B:28:0x0089, B:14:0x003a), top: B:30:0x0001 }] */
    @Override // com.nforetek.bt.profile.map._NfMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean downloadAll(java.lang.String r10, int r11, boolean r12, int r13, int r14, int r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, int r20, int r21) {
        /*
            r9 = this;
            monitor-enter(r9)
            boolean r2 = com.nforetek.util.bt.NfPrimitive.isAddressValid(r10)     // Catch: java.lang.Throwable -> L7e
            if (r2 != 0) goto La
            r8 = 0
        L8:
            monitor-exit(r9)
            return r8
        La:
            switch(r15) {
                case 0: goto Lf;
                case 1: goto L3a;
                default: goto Ld;
            }
        Ld:
            r8 = 0
            goto L8
        Lf:
            com.nforetek.bt.profile.map.jni.NfMapCommand$DownloadAll r1 = new com.nforetek.bt.profile.map.jni.NfMapCommand$DownloadAll     // Catch: java.lang.Throwable -> L7e
            r3 = 3
            r6 = 0
            r2 = r10
            r4 = r21
            r5 = r11
            r7 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L7e
        L1b:
            com.nforetek.bt.profile.map.jni.NfMapCommand r2 = r9.mCurrentCommand     // Catch: java.lang.Throwable -> L7e
            boolean r2 = com.nforetek.bt.profile.map.jni.NfMapCommand.isCommandEquals(r2, r1)     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L47
            java.lang.String r2 = r9.TAG     // Catch: java.lang.Throwable -> L7e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7e
            java.lang.String r4 = "Current Command already processing ! cmd: "
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L7e
            java.lang.StringBuilder r3 = r3.append(r1)     // Catch: java.lang.Throwable -> L7e
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L7e
            com.nforetek.util.normal.NfLog.e(r2, r3)     // Catch: java.lang.Throwable -> L7e
            r1 = 0
            r8 = 0
            goto L8
        L3a:
            com.nforetek.bt.profile.map.jni.NfMapCommand$DownloadAll r1 = new com.nforetek.bt.profile.map.jni.NfMapCommand$DownloadAll     // Catch: java.lang.Throwable -> L7e
            r3 = 3
            r6 = 1
            r2 = r10
            r4 = r21
            r5 = r11
            r7 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L7e
            goto L1b
        L47:
            com.nforetek.bt.profile.map.jni.NfMapCommandProcessor r2 = r9.mCommandProcessor     // Catch: java.lang.Throwable -> L7e
            boolean r2 = r2.isCommandInQueue(r1)     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L66
            java.lang.String r2 = r9.TAG     // Catch: java.lang.Throwable -> L7e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7e
            java.lang.String r4 = "Command already in command queue! cmd: "
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L7e
            java.lang.StringBuilder r3 = r3.append(r1)     // Catch: java.lang.Throwable -> L7e
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L7e
            com.nforetek.util.normal.NfLog.e(r2, r3)     // Catch: java.lang.Throwable -> L7e
            r1 = 0
            r8 = 0
            goto L8
        L66:
            boolean r2 = r9.isNoAnyCommandProcessing()     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L89
            r8 = 0
            com.nforetek.bt.jni.NfJni r2 = r9.jni()     // Catch: java.lang.Throwable -> L7e
            r0 = r21
            boolean r8 = r2.reqMapDownloadAll(r10, r0, r11, r12)     // Catch: java.lang.Throwable -> L7e
            if (r8 == 0) goto L81
            r9.mAddress = r10     // Catch: java.lang.Throwable -> L7e
            r9.mCurrentCommand = r1     // Catch: java.lang.Throwable -> L7e
            goto L8
        L7e:
            r2 = move-exception
            monitor-exit(r9)
            throw r2
        L81:
            java.lang.String r2 = r9.TAG     // Catch: java.lang.Throwable -> L7e
            java.lang.String r3 = "Command return fail."
            com.nforetek.util.normal.NfLog.e(r2, r3)     // Catch: java.lang.Throwable -> L7e
            goto L8
        L89:
            boolean r8 = r9.addToCommandQueue(r1)     // Catch: java.lang.Throwable -> L7e
            goto L8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.profile.map.jni._NfMapJni.downloadAll(java.lang.String, int, boolean, int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0022 A[Catch: all -> 0x0079, TryCatch #0 {, blocks: (B:4:0x0003, B:10:0x000f, B:11:0x001a, B:13:0x0022, B:15:0x0044, B:17:0x004c, B:18:0x0062, B:20:0x0068, B:22:0x0074, B:27:0x007c, B:28:0x0084, B:30:0x008c, B:32:0x0096, B:33:0x00ae, B:14:0x0038), top: B:35:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0044 A[Catch: all -> 0x0079, TryCatch #0 {, blocks: (B:4:0x0003, B:10:0x000f, B:11:0x001a, B:13:0x0022, B:15:0x0044, B:17:0x004c, B:18:0x0062, B:20:0x0068, B:22:0x0074, B:27:0x007c, B:28:0x0084, B:30:0x008c, B:32:0x0096, B:33:0x00ae, B:14:0x0038), top: B:35:0x0003 }] */
    @Override // com.nforetek.bt.profile.map._NfMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean downloadOne(java.lang.String r10, int r11, java.lang.String r12, int r13) {
        /*
            r9 = this;
            r8 = 1
            r7 = 0
            monitor-enter(r9)
            boolean r1 = com.nforetek.util.bt.NfPrimitive.isAddressValid(r10)     // Catch: java.lang.Throwable -> L79
            if (r1 != 0) goto Lb
        L9:
            monitor-exit(r9)
            return r7
        Lb:
            switch(r13) {
                case 0: goto Lf;
                case 1: goto L38;
                default: goto Le;
            }
        Le:
            goto L9
        Lf:
            com.nforetek.bt.profile.map.jni.NfMapCommand$DownloadSingle r0 = new com.nforetek.bt.profile.map.jni.NfMapCommand$DownloadSingle     // Catch: java.lang.Throwable -> L79
            r2 = 2
            r4 = 0
            r6 = 0
            r1 = r10
            r3 = r12
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L79
        L1a:
            com.nforetek.bt.profile.map.jni.NfMapCommand r1 = r9.mCurrentCommand     // Catch: java.lang.Throwable -> L79
            boolean r1 = com.nforetek.bt.profile.map.jni.NfMapCommand.isCommandEquals(r1, r0)     // Catch: java.lang.Throwable -> L79
            if (r1 == 0) goto L44
            java.lang.String r1 = r9.TAG     // Catch: java.lang.Throwable -> L79
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            java.lang.String r3 = "Current Command already processing ! cmd: "
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L79
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch: java.lang.Throwable -> L79
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L79
            com.nforetek.util.normal.NfLog.e(r1, r2)     // Catch: java.lang.Throwable -> L79
            r0 = 0
            goto L9
        L38:
            com.nforetek.bt.profile.map.jni.NfMapCommand$DownloadSingle r0 = new com.nforetek.bt.profile.map.jni.NfMapCommand$DownloadSingle     // Catch: java.lang.Throwable -> L79
            r2 = 2
            r4 = 0
            r6 = 1
            r1 = r10
            r3 = r12
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L79
            goto L1a
        L44:
            com.nforetek.bt.profile.map.jni.NfMapCommandProcessor r1 = r9.mCommandProcessor     // Catch: java.lang.Throwable -> L79
            boolean r1 = r1.isCommandInQueue(r0)     // Catch: java.lang.Throwable -> L79
            if (r1 == 0) goto L62
            java.lang.String r1 = r9.TAG     // Catch: java.lang.Throwable -> L79
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            java.lang.String r3 = "Command already in command queue! cmd: "
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L79
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch: java.lang.Throwable -> L79
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L79
            com.nforetek.util.normal.NfLog.e(r1, r2)     // Catch: java.lang.Throwable -> L79
            r0 = 0
            goto L9
        L62:
            boolean r1 = r9.isNoAnyCommandProcessing()     // Catch: java.lang.Throwable -> L79
            if (r1 == 0) goto L84
            r7 = 0
            com.nforetek.bt.jni.NfJni r1 = r9.jni()     // Catch: java.lang.Throwable -> L79
            r2 = 0
            boolean r7 = r1.reqMapDownloadOne(r10, r12, r2, r11)     // Catch: java.lang.Throwable -> L79
            if (r7 == 0) goto L7c
            r9.mAddress = r10     // Catch: java.lang.Throwable -> L79
            r9.mCurrentCommand = r0     // Catch: java.lang.Throwable -> L79
            goto L9
        L79:
            r1 = move-exception
            monitor-exit(r9)
            throw r1
        L7c:
            java.lang.String r1 = r9.TAG     // Catch: java.lang.Throwable -> L79
            java.lang.String r2 = "Command return fail."
            com.nforetek.util.normal.NfLog.e(r1, r2)     // Catch: java.lang.Throwable -> L79
            goto L9
        L84:
            java.lang.String r1 = r9.mAddress     // Catch: java.lang.Throwable -> L79
            boolean r1 = r1.equals(r10)     // Catch: java.lang.Throwable -> L79
            if (r1 != 0) goto Lae
            java.lang.String r1 = r9.mAddress     // Catch: java.lang.Throwable -> L79
            java.lang.String r2 = "00:00:00:00:00:00"
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Throwable -> L79
            if (r1 != 0) goto Lae
            java.lang.String r1 = r9.TAG     // Catch: java.lang.Throwable -> L79
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            java.lang.String r3 = "Address is not equals with current target address. current: "
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L79
            java.lang.String r3 = r9.mAddress     // Catch: java.lang.Throwable -> L79
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L79
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L79
            com.nforetek.util.normal.NfLog.e(r1, r2)     // Catch: java.lang.Throwable -> L79
            goto L9
        Lae:
            java.lang.String r1 = r9.TAG     // Catch: java.lang.Throwable -> L79
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            java.lang.String r3 = "Map is busy ! Add into command queue. cmd: "
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L79
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch: java.lang.Throwable -> L79
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L79
            com.nforetek.util.normal.NfLog.d(r1, r2)     // Catch: java.lang.Throwable -> L79
            com.nforetek.bt.profile.map.jni.NfMapCommandProcessor r1 = r9.mCommandProcessor     // Catch: java.lang.Throwable -> L79
            r1.add(r0)     // Catch: java.lang.Throwable -> L79
            r7 = r8
            goto L9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.profile.map.jni._NfMapJni.downloadOne(java.lang.String, int, java.lang.String, int):boolean");
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean registerNotification(String address, boolean enable) {
        if (NfPrimitive.isAddressValid(address)) {
            if ((getProfileState() != 110 || !enable) && (getProfileState() != 150 || enable)) {
                NfLog.e(this.TAG, "Map is busy ! Reject !: " + getProfileState());
                return false;
            }
            return jni().reqMapRegisterNotification(address, enable);
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean isNotificationRegistered() {
        return jni().isMapNotificationRegistered();
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean downloadInterrupt(String address) {
        if (NfPrimitive.isAddressValid(address)) {
            if (getProfileState() == 120 || getProfileState() == 150 || getProfileState() == 110) {
                NfLog.e(this.TAG, "Download interrupt reject cause state is connecting/registered/ready.");
                return false;
            } else if (getProfileState() == 140) {
                return jni().reqMapDisconnect(getConnetedAddress());
            } else {
                return jni().reqMapDownloadInterrupt(address);
            }
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean forceDisconnect() {
        if (getProfileState() > 110) {
            this.mCommandProcessor.reset();
            return jni().reqMapDisconnect(getConnetedAddress());
        }
        return false;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public void queryDatabaseAvailable() {
        if (this.mCheckDbAvailableHandler.hasMessages(1)) {
            this.mCheckDbAvailableHandler.removeMessages(1);
        }
        this.mCheckDbAvailableHandler.obtainMessage(1).sendToTarget();
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public synchronized boolean sendMessage(String address, String msg, String target) {
        boolean success = false;
        synchronized (this) {
            if (address != null) {
                if (!address.equals(NfDef.DEFAULT_ADDRESS) && BluetoothAdapter.checkBluetoothAddress(address)) {
                    NfMapCommand.Send cmd = new NfMapCommand.Send(address, 0, msg, target);
                    if (NfMapCommand.isCommandEquals(this.mCurrentCommand, cmd)) {
                        NfLog.e(this.TAG, "Current Command already processing ! cmd: " + cmd);
                    } else if (this.mCommandProcessor.isCommandInQueue(cmd)) {
                        NfLog.e(this.TAG, "Command already in command queue! cmd: " + cmd);
                    } else if (isNoAnyCommandProcessing()) {
                        if (msg.length() > 1024) {
                            NfLog.e(this.TAG, "The length of message cannot exceed 1024 bytes. current message length is " + msg.length());
                        } else {
                            success = jni().reqMapSendMessage(address, msg, target);
                            if (success) {
                                this.mAddress = address;
                                this.mCurrentCommand = cmd;
                            } else {
                                NfLog.e(this.TAG, "Command return fail.");
                            }
                        }
                    } else {
                        success = addToCommandQueue(cmd);
                    }
                }
            }
            NfLog.e(this.TAG, "Address not valid!! address: " + address);
        }
        return success;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public synchronized boolean deleteMessage(String address, int folder, String handle) {
        boolean success = false;
        synchronized (this) {
            if (address != null) {
                if (!address.equals(NfDef.DEFAULT_ADDRESS) && BluetoothAdapter.checkBluetoothAddress(address)) {
                    NfMapCommand.Delete cmd = new NfMapCommand.Delete(address, 1, folder, handle);
                    if (NfMapCommand.isCommandEquals(this.mCurrentCommand, cmd)) {
                        NfLog.e(this.TAG, "Current Command already processing ! cmd: " + cmd);
                    } else if (this.mCommandProcessor.isCommandInQueue(cmd)) {
                        NfLog.e(this.TAG, "Command already in command queue! cmd: " + cmd);
                    } else if (isNoAnyCommandProcessing()) {
                        success = jni().reqMapDeleteMessage(address, folder, handle);
                        if (success) {
                            this.mAddress = address;
                            this.mCurrentCommand = cmd;
                        } else {
                            NfLog.e(this.TAG, "Command return fail.");
                        }
                    } else {
                        success = addToCommandQueue(cmd);
                    }
                }
            }
            NfLog.e(this.TAG, "Address not valid!! address: " + address);
        }
        return success;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public synchronized boolean changeReadStatus(String address, int folder, String handle, boolean isReadStatus) {
        boolean success = false;
        synchronized (this) {
            if (address != null) {
                if (!address.equals(NfDef.DEFAULT_ADDRESS) && BluetoothAdapter.checkBluetoothAddress(address)) {
                    NfMapCommand.ReadStatus cmd = new NfMapCommand.ReadStatus(address, 4, folder, handle, isReadStatus);
                    if (NfMapCommand.isCommandEquals(this.mCurrentCommand, cmd)) {
                        NfLog.e(this.TAG, "Current Command already processing ! cmd: " + cmd);
                    } else if (this.mCommandProcessor.isCommandInQueue(cmd)) {
                        NfLog.e(this.TAG, "Command already in command queue! cmd: " + cmd);
                    } else if (isNoAnyCommandProcessing()) {
                        success = jni().reqMapChangeReadStatus(address, folder, handle, isReadStatus);
                        if (success) {
                            this.mAddress = address;
                            this.mCurrentCommand = cmd;
                        } else {
                            NfLog.e(this.TAG, "Command return fail.");
                        }
                    } else {
                        success = addToCommandQueue(cmd);
                    }
                }
            }
            NfLog.e(this.TAG, "Address not valid!! address: " + address);
        }
        return success;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean deleteDatabaseByAddress(String address) {
        if (address == null || address.equals(NfDef.DEFAULT_ADDRESS) || !BluetoothAdapter.checkBluetoothAddress(address)) {
            NfLog.e(this.TAG, "Address not valid!! address: " + address);
            return false;
        }
        Message msg = Message.obtain(this.mCheckDbAvailableHandler, 2);
        Bundle bundle = new Bundle();
        bundle.putString(NfDef.EXTRA_DEVICE_ADDRESS, address);
        msg.setData(bundle);
        this.mCheckDbAvailableHandler.sendMessage(msg);
        return true;
    }

    @Override // com.nforetek.bt.profile.map._NfMap
    public boolean cleanDatabase() {
        if (this.mCheckDbAvailableHandler.hasMessages(2)) {
            this.mCheckDbAvailableHandler.removeMessages(2);
        }
        Message msg = Message.obtain(this.mCheckDbAvailableHandler, 3);
        this.mCheckDbAvailableHandler.sendMessage(msg);
        return true;
    }

    private boolean isNoAnyCommandProcessing() {
        return (getProfileState() == 110 || getProfileState() == 150) && this.mCommandProcessor.isCommandQueueEmpty() && this.mCurrentCommand == null;
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapStateChanged(String address, int state, int reason) {
        NfLog.d(this.TAG, "onJniMapStateChanged() " + address + " state: " + state + " reason: " + reason);
        int prevState = getProfileState();
        setState(address, getNfDefState(state), reason);
        if (prevState > 140 && getProfileState() == 140) {
            jni().reqMapDisconnect(address);
        }
        if (getProfileState() == 110) {
            if (!this.mCommandProcessor.isCommandQueueEmpty()) {
                processCommand(this.mCommandProcessor.poll());
            } else {
                this.mAddress = NfDef.DEFAULT_ADDRESS;
                this.mCurrentCommand = null;
                this.mCommandProcessor.reset();
            }
        }
        if (getProfileState() == 150) {
            if (!this.mCommandProcessor.isCommandQueueEmpty()) {
                processCommand(this.mCommandProcessor.poll());
            } else {
                this.mCurrentCommand = null;
            }
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniReceiveMessageContent(String address, String handle, String senderName, String senderAddr, String date, String recipientAddr, int type, int folder, String subject, String message, int readStatus, int currentCount, int totalCount) {
        NfLog.d(this.TAG, "onJniReceiveMessageContent() " + address + " handle: " + handle + " senderName: " + senderName + " senderAddr: " + senderAddr + " date: " + date + " recipientAddr: " + recipientAddr + " type: " + type + " folder: " + folder + " subject: " + subject + " message: " + message);
        NfLog.d(this.TAG, "onJniReceiveMessageContent() readStatus: " + readStatus + " process: " + currentCount + "/" + totalCount);
        int storage = -1;
        if (this.mCurrentCommand instanceof NfMapCommand.DownloadAll) {
            storage = ((NfMapCommand.DownloadAll) this.mCurrentCommand).getStorage();
        } else if (this.mCurrentCommand instanceof NfMapCommand.DownloadSingle) {
            storage = ((NfMapCommand.DownloadSingle) this.mCurrentCommand).getStorage();
        }
        switch (storage) {
            case 0:
                callback().retMapDownloadedMessage(address, handle, senderName, senderAddr, date, recipientAddr, type, folder, readStatus, subject, message);
                break;
            case 1:
                NfLog.e(this.TAG, "mCurrentCommand: " + this.mCurrentCommand);
                NfLog.e(this.TAG, "mCurrentCommand.getType(): " + this.mCurrentCommand.getType());
                if (this.mCurrentCommand.getType() == 2) {
                    int rows = MessageHelper.updateMessageToDatabases(context(), address, folder, handle, message);
                    if (rows != 1) {
                        if (rows == 0) {
                            MessageHelper.addMessageToDatabases(context(), address, handle, senderName, senderAddr, date, recipientAddr, type, folder, subject, message, readStatus);
                            break;
                        } else {
                            NfLog.e(this.TAG, "Update message with weird rows: " + rows);
                            break;
                        }
                    }
                } else if (this.mCurrentCommand.getType() == 3) {
                    if (currentCount == 1) {
                        NfLog.e(this.TAG, "currentCount: " + currentCount);
                        MessageHelper.deleteMessageByMacAddressAndFolder(context(), address, folder);
                    }
                    MessageHelper.addMessageToDatabases(context(), address, handle, senderName, senderAddr, date, recipientAddr, type, folder, subject, message, readStatus);
                    break;
                }
                break;
            default:
                NfLog.e(this.TAG, "Unknown storage type: " + storage);
                break;
        }
        if (this.mNotifyFreq != 0) {
            if (currentCount % this.mNotifyFreq == 0 || currentCount == totalCount) {
                callback().onMapDownloadNotify(address, folder, totalCount, currentCount);
            }
        }
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onReceiveNewMessage(String address, String handle, int type, int folder) {
        NfLog.d(this.TAG, "onReceiveNewMessage() " + address + " handle: " + handle + " type: " + type + " folder: " + folder);
        callback().onMapNewMessageReceived(address, handle, "", "");
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapSendMessage(String address, String target, int state) {
        NfLog.d(this.TAG, "onJniMapSendMessageState() " + address + " target: " + target + " state: " + state);
        callback().retMapSendMessageCompleted(address, target, state);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapDeleteMessageState(String address, String handle, int reason) {
        NfLog.d(this.TAG, "onJniMapDeleteMessageState() " + address + " handle: " + handle + " reason: " + reason);
        callback().retMapDeleteMessageCompleted(address, handle, reason);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapChangeReadStatusState(String address, String handle, int reason) {
        NfLog.d(this.TAG, "onJniMapChangeReadStatusState() " + address + " handle: " + handle + " reason: " + reason);
        callback().retMapChangeReadStatusCompleted(address, handle, reason);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapMemoryAvailable(String address, int type, int structure, int available) {
        NfLog.d(this.TAG, "onJniMapMemoryAvailable() " + address + " structure: " + structure + " available: " + available);
        callback().onMapMemoryAvailable(address, structure, available == 1);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapMessageSendingStatus(String address, String handle, int isSuccess) {
        NfLog.d(this.TAG, "onJniMapMessageSendingStatus() " + address + " handle: " + handle + " status: " + isSuccess);
        callback().onMapMessageSendingStatus(address, handle, isSuccess == 1);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapMessageDeliverStatus(String address, String handle, int isSuccess) {
        NfLog.d(this.TAG, "onJniMapMessageShifted() " + address + " handle: " + handle + " isSuccess: " + isSuccess);
        callback().onMapMessageDeliverStatus(address, handle, isSuccess == 1);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapMessageShifted(String address, String handle, int type, int newFolder, int oldFolder) {
        NfLog.d(this.TAG, "onJniMapMessageShifted() " + address + " handle: " + handle + " type: " + type + " folder: " + oldFolder + " -> " + newFolder);
        callback().onMapMessageShifted(address, handle, type, newFolder, oldFolder);
    }

    @Override // com.nforetek.bt.jni.NfJniCallbackInterfaceMap
    public void onJniMapMessageDeleted(String address, String handle, int type, int folder) {
        NfLog.d(this.TAG, "onJniMapMessageDeleted() " + address + " handle: " + handle + " type: " + type + " folder: " + folder);
        callback().onMapMessageDeleted(address, handle, type, folder);
    }

    private static int getNfDefState(int state) {
        switch (state) {
            case 0:
                return 110;
            case 1:
                return 120;
            case 2:
                return NfDef.STATE_DISCONNECTING;
            case 3:
                return 140;
            case 4:
                return 150;
            case 5:
                return 160;
            case 6:
                return NfDef.STATE_UPLOADING;
            default:
                return 100;
        }
    }

    private static String getConnectionStateString(int state) {
        switch (state) {
            case 110:
                return "Ready";
            case 120:
                return "Connecting";
            case NfDef.STATE_DISCONNECTING /* 125 */:
                return "Disconnecting";
            case 140:
                return "Connected";
            case 150:
                return "Connected and Registered";
            case 160:
                return "Downloading";
            case NfDef.STATE_UPLOADING /* 170 */:
                return "Uploading";
            default:
                return "Unknown";
        }
    }

    private boolean processCommand(NfMapCommand command) {
        NfLog.d(this.TAG, "processCommand() command: " + command);
        if (command == null) {
            NfLog.d(this.TAG, "processCommand() command queue maybe empty, return here");
            this.mCurrentCommand = null;
            return false;
        }
        boolean success = false;
        switch (command.getType()) {
            case 0:
                NfMapCommand.Send cmd = (NfMapCommand.Send) command;
                success = jni().reqMapSendMessage(this.mAddress, cmd.getMessage(), cmd.getTarget());
                break;
            case 1:
                NfMapCommand.Delete cmd2 = (NfMapCommand.Delete) command;
                success = jni().reqMapDeleteMessage(this.mAddress, cmd2.getFolder(), cmd2.getHandle());
                break;
            case 2:
                NfMapCommand.DownloadSingle cmd3 = (NfMapCommand.DownloadSingle) command;
                success = jni().reqMapDownloadOne(this.mAddress, cmd3.getHandle(), cmd3.getDownloadType(), cmd3.getFolder());
                break;
            case 3:
                NfMapCommand.DownloadAll cmd4 = (NfMapCommand.DownloadAll) command;
                success = jni().reqMapDownloadAll(this.mAddress, cmd4.getDownloadType(), cmd4.getFolder(), cmd4.isDetail());
                break;
            case 4:
                NfMapCommand.ReadStatus cmd5 = (NfMapCommand.ReadStatus) command;
                success = jni().reqMapChangeReadStatus(this.mAddress, cmd5.getFolder(), cmd5.getHandle(), cmd5.isRead());
                break;
        }
        if (success) {
            NfLog.d(this.TAG, "processCommand() command: " + command + " return success.");
            this.mCurrentCommand = command;
            this.mAddress = this.mCurrentCommand.getAddress();
            return success;
        }
        NfLog.d(this.TAG, "processCommand() command: " + command + " return fail. Process next command if needed.");
        if (!this.mCommandProcessor.isCommandQueueEmpty()) {
            boolean success2 = processCommand(this.mCommandProcessor.poll());
            return success2;
        }
        this.mCurrentCommand = null;
        return success;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    protected void dequeueMessage(Message msg) {
        NfJniBundle b = (NfJniBundle) msg.obj;
        switch (msg.what) {
            case NfJni.onJniMapConnectionStateChanged /* 401 */:
                NfLog.v(this.TAG, "onJniMapConnectionStateChanged()");
                if (this.mJniHandler.hasMessages(NfJni.onJniMapReceiveMessageContent)) {
                    NfLog.v(this.TAG, "Piggy Check still processing message. Requeue state change message.");
                    if (this.mJniHandler != null) {
                        Message m = this.mJniHandler.obtainMessage();
                        m.copyFrom(msg);
                        this.mJniHandler.sendMessageDelayed(m, 50L);
                        return;
                    }
                    NfLog.e(this.TAG, "mJniHandler is null!");
                    return;
                }
                onJniMapStateChanged(b.getString1(), b.getInt1(), b.getInt2());
                return;
            case NfJni.onJniMapReceiveMessageContent /* 402 */:
                NfLog.v(this.TAG, "onJniMapReceiveMessageContent()");
                onJniReceiveMessageContent(b.getString1(), b.getString2(), b.getString3(), b.getString4(), b.getString5(), b.getString6(), b.getInt1(), b.getInt2(), b.getString7(), b.getString8(), b.getInt3(), b.getInt4(), b.getInt5());
                return;
            case NfJni.onJniMapReceiveNewMessage /* 403 */:
                NfLog.v(this.TAG, "onJniMapReceiveNewMessage()");
                onReceiveNewMessage(b.getString1(), b.getString2(), b.getInt1(), b.getInt2());
                return;
            case NfJni.onJniMapSendMessageState /* 404 */:
                NfLog.v(this.TAG, "onJniMapSendMessageState()");
                onJniMapSendMessage(b.getString1(), b.getString2(), b.getInt1());
                return;
            case NfJni.onJniMapDeleteMessageState /* 405 */:
                NfLog.v(this.TAG, "onJniMapDeleteMessageState()");
                onJniMapDeleteMessageState(b.getString1(), b.getString2(), b.getInt1());
                return;
            case NfJni.onJniMapChangeReadStatusState /* 406 */:
                NfLog.v(this.TAG, "onJniMapChangeReadStatusState()");
                onJniMapChangeReadStatusState(b.getString1(), b.getString2(), b.getInt1());
                return;
            case NfJni.onJniMapMemoryAvailable /* 407 */:
                NfLog.v(this.TAG, "onJniMapMemoryAvailable()");
                onJniMapMemoryAvailable(b.getString1(), b.getInt1(), b.getInt2(), b.getInt3());
                return;
            case NfJni.onJniMapMessageSendingStatus /* 408 */:
                NfLog.v(this.TAG, "onJniMapMessageSendingStatus()");
                onJniMapMessageSendingStatus(b.getString1(), b.getString2(), b.getInt1());
                return;
            case NfJni.onJniMapMessageDeliverStatus /* 409 */:
                NfLog.v(this.TAG, "onJniMapMessageDeliverStatus()");
                onJniMapMessageDeliverStatus(b.getString1(), b.getString2(), b.getInt1());
                return;
            case NfJni.onJniMapMessageShifted /* 410 */:
                NfLog.v(this.TAG, "onJniMapMessageShifted()");
                onJniMapMessageShifted(b.getString1(), b.getString2(), b.getInt1(), b.getInt2(), b.getInt3());
                return;
            case NfJni.onJniMapMessageDeleted /* 411 */:
                NfLog.v(this.TAG, "onJniMapMessageDeleted()");
                onJniMapMessageDeleted(b.getString1(), b.getString2(), b.getInt1(), b.getInt2());
                return;
            default:
                return;
        }
    }

    private boolean addToCommandQueue(NfMapCommand cmd) {
        if (!this.mAddress.equals(cmd.getAddress()) && !this.mAddress.equals(NfDef.DEFAULT_ADDRESS)) {
            NfLog.e(this.TAG, "Address is not equals with current target address. current: " + this.mAddress + " state: " + getProfileState());
            return false;
        }
        NfLog.d(this.TAG, "Map is busy ! Add into command queue. cmd: " + cmd);
        this.mCommandProcessor.add(cmd);
        return true;
    }

    @Override // com.nforetek.bt.profile._NfProfile
    public void onStateChangedCallback(String address, int prevState, int state, int storage, int reason, int count) {
        callback().onMapStateChanged(address, prevState, state, reason);
    }

    @Override // com.nforetek.bt.profile.map._NfMap, com.nforetek.bt.profile._NfProfile
    public String getConnectedAddress() {
        return getProfileState() > 140 ? this.mAddress : NfDef.DEFAULT_ADDRESS;
    }
}
