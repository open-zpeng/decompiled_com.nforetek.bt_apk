package com.nforetek.bt.callback;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.nforetek.bt.aidl.INfCallbackAvrcp;
import com.nforetek.bt.callback.def.NfDefBroadcastAvrcp;
import com.nforetek.bt.res.NfDef;
import com.nforetek.bt.res.bt.NfConfig;
import com.nforetek.util.bt.NfPrimitive;
import com.nforetek.util.normal.NfLog;
/* loaded from: classes.dex */
public final class NfDoCallbackAvrcp extends NfDoCallback<INfCallbackAvrcp> {
    private final int onAvrcp13EventBatteryStatusChanged;
    private final int onAvrcp13EventPlaybackPosChanged;
    private final int onAvrcp13EventPlaybackStatusChanged;
    private final int onAvrcp13EventPlayerSettingChanged;
    private final int onAvrcp13EventSystemStatusChanged;
    private final int onAvrcp13EventTrackChanged;
    private final int onAvrcp13EventTrackReachedEnd;
    private final int onAvrcp13EventTrackReachedStart;
    private final int onAvrcp13RegisterEventWatcherFail;
    private final int onAvrcp13RegisterEventWatcherSuccess;
    private final int onAvrcp14EventAddressedPlayerChanged;
    private final int onAvrcp14EventAvailablePlayerChanged;
    private final int onAvrcp14EventNowPlayingContentChanged;
    private final int onAvrcp14EventUidsChanged;
    private final int onAvrcp14EventVolumeChanged;
    private final int onAvrcpErrorResponse;
    private final int onAvrcpStateChanged;
    private final int retAvrcp13CapabilitiesSupportEvent;
    private final int retAvrcp13ElementAttributesPlaying;
    private final int retAvrcp13PlayStatus;
    private final int retAvrcp13PlayerSettingAttributeText;
    private final int retAvrcp13PlayerSettingAttributesList;
    private final int retAvrcp13PlayerSettingCurrentValues;
    private final int retAvrcp13PlayerSettingValueText;
    private final int retAvrcp13PlayerSettingValuesList;
    private final int retAvrcp13SetPlayerSettingValueSuccess;
    private final int retAvrcp14AddToNowPlayingSuccess;
    private final int retAvrcp14ChangePathSuccess;
    private final int retAvrcp14FolderItems;
    private final int retAvrcp14ItemAttributes;
    private final int retAvrcp14MediaItems;
    private final int retAvrcp14PlaySelectedItemSuccess;
    private final int retAvrcp14SearchResult;
    private final int retAvrcp14SetAbsoluteVolumeSuccess;
    private final int retAvrcp14SetAddressedPlayerSuccess;
    private final int retAvrcp14SetBrowsedPlayerSuccess;

    public NfDoCallbackAvrcp(Context context) {
        super(context);
        this.onAvrcpStateChanged = 1;
        this.retAvrcp13CapabilitiesSupportEvent = 2;
        this.retAvrcp13PlayerSettingAttributesList = 3;
        this.retAvrcp13PlayerSettingValuesList = 4;
        this.retAvrcp13PlayerSettingCurrentValues = 5;
        this.retAvrcp13SetPlayerSettingValueSuccess = 6;
        this.retAvrcp13ElementAttributesPlaying = 7;
        this.retAvrcp13PlayStatus = 8;
        this.onAvrcp13EventPlaybackStatusChanged = 9;
        this.onAvrcp13EventTrackChanged = 10;
        this.onAvrcp13EventTrackReachedEnd = 11;
        this.onAvrcp13EventTrackReachedStart = 12;
        this.onAvrcp13EventPlaybackPosChanged = 13;
        this.onAvrcp13EventBatteryStatusChanged = 14;
        this.onAvrcp13EventSystemStatusChanged = 15;
        this.onAvrcp13EventPlayerSettingChanged = 16;
        this.retAvrcp13PlayerSettingAttributeText = 17;
        this.retAvrcp13PlayerSettingValueText = 18;
        this.onAvrcp14EventNowPlayingContentChanged = 19;
        this.onAvrcp14EventAvailablePlayerChanged = 20;
        this.onAvrcp14EventAddressedPlayerChanged = 21;
        this.onAvrcp14EventUidsChanged = 22;
        this.onAvrcp14EventVolumeChanged = 23;
        this.retAvrcp14SetAddressedPlayerSuccess = 24;
        this.retAvrcp14SetBrowsedPlayerSuccess = 25;
        this.retAvrcp14FolderItems = 26;
        this.retAvrcp14MediaItems = 27;
        this.retAvrcp14ChangePathSuccess = 28;
        this.retAvrcp14ItemAttributes = 29;
        this.retAvrcp14PlaySelectedItemSuccess = 30;
        this.retAvrcp14SearchResult = 31;
        this.retAvrcp14AddToNowPlayingSuccess = 32;
        this.retAvrcp14SetAbsoluteVolumeSuccess = 33;
        this.onAvrcpErrorResponse = 34;
        this.onAvrcp13RegisterEventWatcherSuccess = 35;
        this.onAvrcp13RegisterEventWatcherFail = 36;
    }

    @Override // com.nforetek.bt.callback.NfDoCallback
    protected String getLogTag() {
        return "NfDoCallbackAvrcp";
    }

    public void onAvrcpStateChanged(String address, int prevState, int newState) {
        NfLog.d(this.TAG, "onAvrcpStateChanged(): " + prevState + " -> " + newState);
        Message m = getMessage(1);
        m.arg1 = prevState;
        m.arg2 = newState;
        m.obj = address;
        enqueueMessage(m);
    }

    public void retAvrcp13CapabilitiesSupportEvent(byte[] eventIds) {
        NfLog.d(this.TAG, "retAvrcp13CapabilitiesSupportEvent()");
        Message m = getMessage(2);
        Bundle b = new Bundle();
        b.putByteArray("eventIds", eventIds);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp13PlayerSettingAttributesList(byte[] attributeIds) {
        NfLog.d(this.TAG, "retAvrcp13PlayerSettingAttributesList()");
        Message m = getMessage(3);
        Bundle b = new Bundle();
        b.putByteArray("attributeIds", attributeIds);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp13PlayerSettingValuesList(byte attributeId, byte[] valueIds) {
        NfLog.d(this.TAG, "retAvrcp13PlayerSettingValuesList()");
        Message m = getMessage(4);
        Bundle b = new Bundle();
        b.putByte("attributeId", attributeId);
        b.putByteArray("valueIds", valueIds);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp13PlayerSettingCurrentValues(byte[] attributeIds, byte[] valueIds) {
        NfLog.d(this.TAG, "retAvrcp13PlayerSettingCurrentValues()");
        Message m = getMessage(5);
        Bundle b = new Bundle();
        b.putByteArray("attributeIds", attributeIds);
        b.putByteArray("valueIds", valueIds);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp13SetPlayerSettingValueSuccess() {
        NfLog.d(this.TAG, "retAvrcp13SetPlayerSettingValueSuccess()");
        Message m = getMessage(6);
        enqueueMessage(m);
    }

    public void retAvrcp13ElementAttributesPlaying(int[] metadataAtrributeIds, String[] texts) {
        NfLog.d(this.TAG, "retAvrcp13ElementAttributesPlaying()");
        Message m = getMessage(7);
        Bundle b = new Bundle();
        b.putIntArray("metadataAtrributeIds", metadataAtrributeIds);
        b.putStringArray("texts", texts);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp13PlayStatus(long songLen, long songPos, byte statusId) {
        NfLog.d(this.TAG, "retAvrcp13PlayStatus() songLen: " + songLen + " songPos: " + songPos + " statusId: " + ((int) statusId));
        Message m = getMessage(8);
        Bundle b = new Bundle();
        b.putLong("songLen", songLen);
        b.putLong("songPos", songPos);
        b.putByte("statusId", statusId);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onAvrcp13EventPlaybackStatusChanged(byte statusId) {
        NfLog.d(this.TAG, "onAvrcp13EventPlaybackStatusChanged() statusId: " + ((int) statusId));
        Message m = getMessage(9);
        Bundle b = new Bundle();
        b.putByte("statusId", statusId);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onAvrcp13EventTrackChanged(long elementId) {
        NfLog.d(this.TAG, "onAvrcp13EventTrackChanged()");
        Message m = getMessage(10);
        Bundle b = new Bundle();
        b.putLong("elementId", elementId);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onAvrcp13EventTrackReachedEnd() {
        NfLog.d(this.TAG, "onAvrcp13EventTrackReachedEnd()");
        Message m = getMessage(11);
        enqueueMessage(m);
    }

    public void onAvrcp13EventTrackReachedStart() {
        NfLog.d(this.TAG, "onAvrcp13EventTrackReachedStart()");
        Message m = getMessage(12);
        enqueueMessage(m);
    }

    public void onAvrcp13EventPlaybackPosChanged(long songPos) {
        NfLog.d(this.TAG, "onAvrcp13EventPlaybackPosChanged()");
        Message m = getMessage(13);
        Bundle b = new Bundle();
        b.putLong("songPos", songPos);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onAvrcp13EventBatteryStatusChanged(byte statusId) {
        NfLog.d(this.TAG, "onAvrcp13EventBatteryStatusChanged()");
        Message m = getMessage(14);
        Bundle b = new Bundle();
        b.putByte("statusId", statusId);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onAvrcp13EventSystemStatusChanged(long statusId) {
        NfLog.d(this.TAG, "onAvrcp13EventSystemStatusChanged()");
        Message m = getMessage(15);
        Bundle b = new Bundle();
        b.putLong("statusId", statusId);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onAvrcp13EventPlayerSettingChanged(byte[] attributeIds, byte[] valueIds) {
        NfLog.d(this.TAG, "onAvrcp13EventPlayerSettingChanged()");
        Message m = getMessage(16);
        Bundle b = new Bundle();
        b.putByteArray("attributeIds", attributeIds);
        b.putByteArray("valueIds", valueIds);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp13PlayerSettingAttributeText(int cause, byte attributeId, String text) {
        NfLog.d(this.TAG, "retAvrcp13PlayerSettingAttributeText()");
        Message m = getMessage(17);
        Bundle b = new Bundle();
        b.putInt("cause", cause);
        b.putByte("attributeId", attributeId);
        b.putString("text", text);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp13PlayerSettingValueText(int cause, byte valueId, String text) {
        NfLog.d(this.TAG, "retAvrcp13PlayerSettingValueText()");
        Message m = getMessage(18);
        Bundle b = new Bundle();
        b.putInt("cause", cause);
        b.putByte("valueId", valueId);
        b.putString("text", text);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onAvrcp14EventNowPlayingContentChanged() {
        NfLog.d(this.TAG, "onAvrcp14EventNowPlayingContentChanged()");
        Message m = getMessage(19);
        enqueueMessage(m);
    }

    public void onAvrcp14EventAvailablePlayerChanged() {
        NfLog.d(this.TAG, "onAvrcp14EventAvailablePlayerChanged()");
        Message m = getMessage(20);
        enqueueMessage(m);
    }

    public void onAvrcp14EventAddressedPlayerChanged(int playerId, int uidCounter) {
        NfLog.d(this.TAG, "onAvrcp14EventAddressedPlayerChanged()");
        Message m = getMessage(21);
        Bundle b = new Bundle();
        b.putInt("playerId", playerId);
        b.putInt("uidCounter", uidCounter);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onAvrcp14EventUidsChanged(int uidCounter) {
        NfLog.d(this.TAG, "onAvrcp14EventUidsChanged()");
        Message m = getMessage(22);
        Bundle b = new Bundle();
        b.putInt("uidCounter", uidCounter);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onAvrcp14EventVolumeChanged(byte volume) {
        NfLog.d(this.TAG, "onAvrcp14EventVolumeChanged()");
        Message m = getMessage(23);
        Bundle b = new Bundle();
        b.putByte("volume", volume);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp14SetAddressedPlayerSuccess() {
        NfLog.d(this.TAG, "retAvrcp14SetAddressedPlayerSuccess()");
        Message m = getMessage(24);
        enqueueMessage(m);
    }

    public void retAvrcp14SetBrowsedPlayerSuccess(String[] path, int uidCounter, long itemCount) {
        NfLog.d(this.TAG, "retAvrcp14SetBrowsedPlayerSuccess()");
        Message m = getMessage(25);
        Bundle b = new Bundle();
        b.putStringArray("path", path);
        b.putInt("uidCounter", uidCounter);
        b.putLong("itemCount", itemCount);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp14FolderItems(int uidCounter, long itemCount) {
        NfLog.d(this.TAG, "retAvrcp14FolderItems()");
        Message m = getMessage(26);
        Bundle b = new Bundle();
        b.putInt("uidCounter", uidCounter);
        b.putLong("itemCount", itemCount);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp14MediaItems(int uidCounter, long itemCount) {
        NfLog.d(this.TAG, "retAvrcp14MediaItems()");
        Message m = getMessage(27);
        Bundle b = new Bundle();
        b.putInt("uidCounter", uidCounter);
        b.putLong("itemCount", itemCount);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp14ChangePathSuccess(long itemCount) {
        NfLog.d(this.TAG, "retAvrcp14ChangePathSuccess()");
        Message m = getMessage(28);
        Bundle b = new Bundle();
        b.putLong("itemCount", itemCount);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp14ItemAttributes(int[] metadataAtrributeIds, String[] texts) {
        NfLog.d(this.TAG, "retAvrcp14ItemAttributes()");
        Message m = getMessage(29);
        Bundle b = new Bundle();
        b.putIntArray("metadataAtrributeIds", metadataAtrributeIds);
        b.putStringArray("texts", texts);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp14PlaySelectedItemSuccess() {
        NfLog.d(this.TAG, "retAvrcp14PlaySelectedItemSuccess()");
        Message m = getMessage(30);
        enqueueMessage(m);
    }

    public void retAvrcp14SearchResult(int uidCounter, long itemCount) {
        NfLog.d(this.TAG, "retAvrcp14SearchResult()");
        Message m = getMessage(31);
        Bundle b = new Bundle();
        b.putInt("uidCounter", uidCounter);
        b.putLong("itemCount", itemCount);
        m.setData(b);
        enqueueMessage(m);
    }

    public void retAvrcp14AddToNowPlayingSuccess() {
        NfLog.d(this.TAG, "retAvrcp14AddToNowPlayingSuccess()");
        Message m = getMessage(32);
        enqueueMessage(m);
    }

    public void retAvrcp14SetAbsoluteVolumeSuccess(byte volume) {
        NfLog.d(this.TAG, "retAvrcp14SetAbsoluteVolumeSuccess()");
        Message m = getMessage(33);
        Bundle b = new Bundle();
        b.putByte("volume", volume);
        m.setData(b);
        enqueueMessage(m);
    }

    public void onAvrcpErrorResponse(int opId, int reason, byte eventId) {
        NfLog.d(this.TAG, "onAvrcpErrorResponse()");
        Message m = getMessage(34);
        Bundle b = new Bundle();
        b.putInt("opId", opId);
        b.putInt("reason", reason);
        b.putByte("eventId", eventId);
        m.setData(b);
        enqueueMessage(m);
    }

    public synchronized void onAvrcp13RegisterEventWatcherSuccess(byte eventId) {
        Log.v(this.TAG, "onAvrcp13RegisterEventWatcherSuccess() ");
        Message m = getMessage(35);
        Bundle b = new Bundle();
        b.putByte("eventId", eventId);
        m.setData(b);
        enqueueMessage(m);
    }

    public synchronized void onAvrcp13RegisterEventWatcherFail(byte eventId) {
        Log.v(this.TAG, "onAvrcp13RegisterEventWatcherFail() ");
        Message m = getMessage(36);
        Bundle b = new Bundle();
        b.putByte("eventId", eventId);
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
                    callbackOnAvrcpStateChanged(address, prevState, newState);
                    break;
                case 2:
                    callbackRetAvrcp13CapabilitiesSupportEvent(b.getByteArray("eventIds"));
                    break;
                case 3:
                    callbackRetAvrcp13PlayerSettingAttributesList(b.getByteArray("attributeIds"));
                    break;
                case 4:
                    callbackRetAvrcp13PlayerSettingValuesList(b.getByte("attributeId"), b.getByteArray("valueIds"));
                    break;
                case 5:
                    callbackRetAvrcp13PlayerSettingCurrentValues(b.getByteArray("attributeIds"), b.getByteArray("valueIds"));
                    break;
                case 6:
                    callbackRetAvrcp13SetPlayerSettingValueSuccess();
                    break;
                case 7:
                    callbackRetAvrcp13ElementAttributesPlaying(b.getIntArray("metadataAtrributeIds"), b.getStringArray("texts"));
                    break;
                case 8:
                    callbackRetAvrcp13PlayStatus(b.getLong("songLen"), b.getLong("songPos"), b.getByte("statusId"));
                    break;
                case 9:
                    callbackOnAvrcp13EventPlaybackStatusChanged(b.getByte("statusId"));
                    break;
                case 10:
                    callbackOnAvrcp13EventTrackChanged(b.getLong("elementId"));
                    break;
                case 11:
                    callbackOnAvrcp13EventTrackReachedEnd();
                    break;
                case 12:
                    callbackOnAvrcp13EventTrackReachedStart();
                    break;
                case 13:
                    callbackOnAvrcp13EventPlaybackPosChanged(b.getLong("songPos"));
                    break;
                case 14:
                    callbackOnAvrcp13EventBatteryStatusChanged(b.getByte("statusId"));
                    break;
                case 15:
                    callbackOnAvrcp13EventSystemStatusChanged(b.getByte("statusId"));
                    break;
                case 16:
                    callbackOnAvrcp13EventPlayerSettingChanged(b.getByteArray("attributeIds"), b.getByteArray("valueIds"));
                    break;
                case 17:
                    callbackRetAvrcp13PlayerSettingAttributeText(b.getInt("cause"), b.getByte("attributeId"), b.getString("text"));
                    break;
                case 18:
                    callbackRetAvrcp13PlayerSettingValueText(b.getInt("cause"), b.getByte("valueId"), b.getString("text"));
                    break;
                case 19:
                    callbackOnAvrcp14EventNowPlayingContentChanged();
                    break;
                case 20:
                    callbackOnAvrcp14EventAvailablePlayerChanged();
                    break;
                case 21:
                    callbackOnAvrcp14EventAddressedPlayerChanged(b.getInt("playerId"), b.getInt("uidCounter"));
                    break;
                case 22:
                    callbackOnAvrcp14EventUidsChanged(b.getInt("uidCounter"));
                    break;
                case 23:
                    callbackOnAvrcp14EventVolumeChanged(b.getByte("volume"));
                    break;
                case 24:
                    callbackRetAvrcp14SetAddressedPlayerSuccess();
                    break;
                case NfDef.TIME_EVENT_SCO_ON_CLOSED_MS /* 25 */:
                    callbackRetAvrcp14SetBrowsedPlayerSuccess(b.getStringArray("path"), b.getInt("uidCounter"), b.getLong("itemCount"));
                    break;
                case 26:
                    callbackRetAvrcp14FolderItems(b.getInt("uidCounter"), b.getLong("itemCount"));
                    break;
                case 27:
                    callbackRetAvrcp14MediaItems(b.getInt("uidCounter"), b.getLong("itemCount"));
                    break;
                case 28:
                    callbackRetAvrcp14ChangePathSuccess(b.getLong("itemCount"));
                    break;
                case 29:
                    callbackRetAvrcp14ItemAttributes(b.getIntArray("metadataAtrributeIds"), b.getStringArray("texts"));
                    break;
                case 30:
                    callbackRetAvrcp14PlaySelectedItemSuccess();
                    break;
                case 31:
                    callbackRetAvrcp14SearchResult(b.getInt("uidCounter"), b.getLong("itemCount"));
                    break;
                case 32:
                    callbackRetAvrcp14AddToNowPlayingSuccess();
                    break;
                case 33:
                    callbackRetAvrcp14SetAbsoluteVolumeSuccess(b.getByte("volume"));
                    break;
                case 34:
                    callbackOnAvrcpErrorResponse(b.getInt("opId"), b.getInt("reason"), b.getByte("eventId"));
                    break;
                case 35:
                    callbackOnAvrcp13RegisterEventWatcherSuccess(b.getByte("eventId"));
                    break;
                case 36:
                    callbackOnAvrcp13RegisterEventWatcherFail(b.getByte("eventId"));
                    break;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    break;
            }
        }
        if (NfConfig.isCallbackByBroadcast()) {
            switch (msg.what) {
                case 1:
                    broadcastOnAvrcpStateChanged(address, prevState, newState);
                    return;
                case 2:
                    broadcastRetAvrcp13CapabilitiesSupportEvent(b.getByteArray("eventIds"));
                    return;
                case 3:
                    broadcastRetAvrcp13PlayerSettingAttributesList(b.getByteArray("attributeIds"));
                    return;
                case 4:
                    broadcastRetAvrcp13PlayerSettingValuesList(b.getByte("attributeId"), b.getByteArray("valueIds"));
                    return;
                case 5:
                    broadcastRetAvrcp13PlayerSettingCurrentValues(b.getByteArray("attributeIds"), b.getByteArray("valueIds"));
                    return;
                case 6:
                    broadcastRetAvrcp13SetPlayerSettingValueSuccess();
                    return;
                case 7:
                    broadcastRetAvrcp13ElementAttributesPlaying(b.getIntArray("metadataAtrributeIds"), b.getStringArray("texts"));
                    return;
                case 8:
                    broadcastRetAvrcp13PlayStatus(b.getLong("songLen"), b.getLong("songPos"), b.getByte("statusId"));
                    return;
                case 9:
                    broadcastOnAvrcp13EventPlaybackStatusChanged(b.getByte("statusId"));
                    return;
                case 10:
                    broadcastOnAvrcp13EventTrackChanged(b.getLong("elementId"));
                    return;
                case 11:
                    broadcastOnAvrcp13EventTrackReachedEnd();
                    return;
                case 12:
                    broadcastOnAvrcp13EventTrackReachedStart();
                    return;
                case 13:
                    broadcastOnAvrcp13EventPlaybackPosChanged(b.getLong("songPos"));
                    return;
                case 14:
                    broadcastOnAvrcp13EventBatteryStatusChanged(b.getByte("statusId"));
                    return;
                case 15:
                    broadcastOnAvrcp13EventSystemStatusChanged(b.getByte("statusId"));
                    return;
                case 16:
                    broadcastOnAvrcp13EventPlayerSettingChanged(b.getByteArray("attributeIds"), b.getByteArray("valueIds"));
                    return;
                case 17:
                    broadcastRetAvrcp13PlayerSettingAttributeText(b.getInt("cause"), b.getByte("attributeId"), b.getString("text"));
                    return;
                case 18:
                    broadcastRetAvrcp13PlayerSettingValueText(b.getInt("cause"), b.getByte("valueId"), b.getString("text"));
                    return;
                case 19:
                    broadcastOnAvrcp14EventNowPlayingContentChanged();
                    return;
                case 20:
                    broadcastOnAvrcp14EventAvailablePlayerChanged();
                    return;
                case 21:
                    broadcastOnAvrcp14EventAddressedPlayerChanged(b.getInt("playerId"), b.getInt("uidCounter"));
                    return;
                case 22:
                    broadcastOnAvrcp14EventUidsChanged(b.getInt("uidCounter"));
                    return;
                case 23:
                    broadcastOnAvrcp14EventVolumeChanged(b.getByte("volume"));
                    return;
                case 24:
                    broadcastRetAvrcp14SetAddressedPlayerSuccess();
                    return;
                case NfDef.TIME_EVENT_SCO_ON_CLOSED_MS /* 25 */:
                    broadcastRetAvrcp14SetBrowsedPlayerSuccess(b.getStringArray("path"), b.getInt("uidCounter"), b.getLong("itemCount"));
                    return;
                case 26:
                    broadcastRetAvrcp14FolderItems(b.getInt("uidCounter"), b.getLong("itemCount"));
                    return;
                case 27:
                    broadcastRetAvrcp14MediaItems(b.getInt("uidCounter"), b.getLong("itemCount"));
                    return;
                case 28:
                    broadcastRetAvrcp14ChangePathSuccess(b.getLong("itemCount"));
                    return;
                case 29:
                    broadcastRetAvrcp14ItemAttributes(b.getIntArray("metadataAtrributeIds"), b.getStringArray("texts"));
                    return;
                case 30:
                    broadcastRetAvrcp14PlaySelectedItemSuccess();
                    return;
                case 31:
                    broadcastRetAvrcp14SearchResult(b.getInt("uidCounter"), b.getLong("itemCount"));
                    return;
                case 32:
                    broadcastRetAvrcp14AddToNowPlayingSuccess();
                    return;
                case 33:
                    broadcastRetAvrcp14SetAbsoluteVolumeSuccess(b.getByte("volume"));
                    return;
                case 34:
                    broadcastOnAvrcpErrorResponse(b.getInt("opId"), b.getInt("reason"), b.getByte("eventId"));
                    return;
                case 35:
                    broadcastOnAvrcp13RegisterEventWatcherSuccess(b.getByte("eventId"));
                    return;
                case 36:
                    broadcastOnAvrcp13RegisterEventWatcherFail(b.getByte("eventId"));
                    return;
                default:
                    NfLog.e(this.TAG, "unhandle Message : " + msg.what);
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nforetek.bt.callback.NfDoCallback
    public synchronized void callbackOnServiceReady(INfCallbackAvrcp cb) throws RemoteException {
        cb.onAvrcpServiceReady();
    }

    private synchronized void callbackOnAvrcpStateChanged(String address, int prevState, int newState) {
        NfLog.v(this.TAG, "callbackOnAvrcpStateChanged() " + address + " state: " + prevState + " -> " + newState);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcpStateChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcpStateChanged(address, prevState, newState);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcpStateChanged CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp13CapabilitiesSupportEvent(byte[] eventIds) {
        NfLog.v(this.TAG, "callbackRetAvrcp13CapabilitiesSupportEvent()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp13CapabilitiesSupportEvent beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp13CapabilitiesSupportEvent(eventIds);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp13CapabilitiesSupportEvent CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp13PlayerSettingAttributesList(byte[] attributeIds) {
        NfLog.v(this.TAG, "callbackRetAvrcp13PlayerSettingAttributesList()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp13PlayerSettingAttributesList beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp13PlayerSettingAttributesList(attributeIds);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp13PlayerSettingAttributesList CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp13PlayerSettingValuesList(byte attributeId, byte[] valueIds) {
        NfLog.v(this.TAG, "callbackRetAvrcp13PlayerSettingValuesList()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp13PlayerSettingValuesList beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp13PlayerSettingValuesList(attributeId, valueIds);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp13PlayerSettingValuesList CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp13PlayerSettingCurrentValues(byte[] attributeIds, byte[] valueIds) {
        NfLog.v(this.TAG, "callbackRetAvrcp13PlayerSettingCurrentValues()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp13PlayerSettingCurrentValues beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp13PlayerSettingCurrentValues(attributeIds, valueIds);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp13PlayerSettingCurrentValues CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp13SetPlayerSettingValueSuccess() {
        NfLog.v(this.TAG, "callbackRetAvrcp13SetPlayerSettingValueSuccess()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp13SetPlayerSettingValueSuccess beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp13SetPlayerSettingValueSuccess();
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp13SetPlayerSettingValueSuccess CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp13ElementAttributesPlaying(int[] metadataAtrributeIds, String[] texts) {
        NfLog.v(this.TAG, "callbackRetAvrcp13ElementAttributesPlaying()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp13ElementAttributesPlaying beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp13ElementAttributesPlaying(metadataAtrributeIds, texts);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp13ElementAttributesPlaying CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp13PlayStatus(long songLen, long songPos, byte statusId) {
        NfLog.v(this.TAG, "callbackRetAvrcp13PlayStatus() songLen: " + songLen + " songPos: " + songPos + " statusId: " + ((int) statusId));
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp13PlayStatus beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    try {
                        ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp13PlayStatus(songLen, songPos, statusId);
                    } catch (RemoteException e) {
                        NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                    }
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp13PlayStatus CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp13EventPlaybackStatusChanged(byte statusId) {
        NfLog.v(this.TAG, "callbackOnAvrcp13EventPlaybackStatusChanged() statusId: " + ((int) statusId));
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp13EventPlaybackStatusChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp13EventPlaybackStatusChanged(statusId);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp13EventPlaybackStatusChanged CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp13EventTrackChanged(long elementId) {
        NfLog.v(this.TAG, "callbackOnAvrcp13EventTrackChanged()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp13EventTrackChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp13EventTrackChanged(elementId);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp13EventTrackChanged CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp13EventTrackReachedEnd() {
        NfLog.v(this.TAG, "callbackOnAvrcp13EventTrackReachedEnd()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp13EventTrackReachedEnd beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp13EventTrackReachedEnd();
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp13EventTrackReachedEnd CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp13EventTrackReachedStart() {
        NfLog.v(this.TAG, "callbackOnAvrcp13EventTrackReachedStart()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp13EventTrackReachedStart beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp13EventTrackReachedStart();
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp13EventTrackReachedStart CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp13EventPlaybackPosChanged(long songPos) {
        NfLog.v(this.TAG, "callbackOnAvrcp13EventPlaybackPosChanged() songPos: " + songPos);
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp13EventPlaybackPosChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp13EventPlaybackPosChanged(songPos);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp13EventPlaybackPosChanged CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp13EventBatteryStatusChanged(byte statusId) {
        NfLog.v(this.TAG, "callbackOnAvrcp13EventBatteryStatusChanged()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp13EventBatteryStatusChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp13EventBatteryStatusChanged(statusId);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp13EventBatteryStatusChanged CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp13EventSystemStatusChanged(byte statusId) {
        NfLog.v(this.TAG, "callbackOnAvrcp13EventSystemStatusChanged()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp13EventSystemStatusChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp13EventSystemStatusChanged(statusId);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp13EventSystemStatusChanged CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp13EventPlayerSettingChanged(byte[] attributeIds, byte[] valueIds) {
        NfLog.v(this.TAG, "callbackOnAvrcp13EventPlayerSettingChanged()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp13EventPlayerSettingChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp13EventPlayerSettingChanged(attributeIds, valueIds);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp13EventPlayerSettingChanged CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp13PlayerSettingAttributeText(int cause, byte attributeId, String text) {
        NfLog.v(this.TAG, "callbackRetAvrcp13PlayerSettingAttributeText()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp13PlayerSettingAttributeText beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp13PlayerSettingAttributeText CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp13PlayerSettingValueText(int cause, byte valueId, String text) {
        NfLog.v(this.TAG, "callbackRetAvrcp13PlayerSettingValueText()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp13PlayerSettingValueText beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp13PlayerSettingValueText CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp14EventNowPlayingContentChanged() {
        NfLog.v(this.TAG, "callbackOnAvrcp14EventNowPlayingContentChanged()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp14EventNowPlayingContentChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp14EventNowPlayingContentChanged();
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp14EventNowPlayingContentChanged CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp14EventAvailablePlayerChanged() {
        NfLog.v(this.TAG, "callbackOnAvrcp14EventAvailablePlayerChanged()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp14EventAvailablePlayerChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp14EventAvailablePlayerChanged();
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp14EventAvailablePlayerChanged CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp14EventAddressedPlayerChanged(int playerId, int uidCounter) {
        NfLog.v(this.TAG, "callbackOnAvrcp14EventAddressedPlayerChanged()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp14EventAddressedPlayerChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp14EventAddressedPlayerChanged(playerId, uidCounter);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp14EventAddressedPlayerChanged CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp14EventUidsChanged(int uidCounter) {
        NfLog.v(this.TAG, "callbackOnAvrcp14EventUidsChanged()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp14EventUidsChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp14EventUidsChanged(uidCounter);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp14EventUidsChanged CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp14EventVolumeChanged(byte volume) {
        NfLog.v(this.TAG, "callbackOnAvrcp14EventVolumeChanged()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcp14EventVolumeChanged beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp14EventVolumeChanged(volume);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcp14EventVolumeChanged CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp14SetAddressedPlayerSuccess() {
        NfLog.v(this.TAG, "retAvrcp14SetAddressedPlayerSuccess()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp14SetAddressedPlayerSuccess beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp14SetAddressedPlayerSuccess();
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp14SetAddressedPlayerSuccess CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp14SetBrowsedPlayerSuccess(String[] path, int uidCounter, long itemCount) {
        NfLog.v(this.TAG, "callbackRetAvrcp14SetBrowsedPlayerSuccess()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp14SetBrowsedPlayerSuccess beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp14SetBrowsedPlayerSuccess(path, uidCounter, itemCount);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp14SetBrowsedPlayerSuccess CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp14FolderItems(int uidCounter, long itemCount) {
        NfLog.v(this.TAG, "callbackRetAvrcp14FolderItems()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp14FolderItems beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp14FolderItems(uidCounter, itemCount);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp14FolderItems CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp14MediaItems(int uidCounter, long itemCount) {
        NfLog.v(this.TAG, "callbackRetAvrcp14MediaItems()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp14MediaItems beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp14MediaItems(uidCounter, itemCount);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp14MediaItems CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp14ChangePathSuccess(long itemCount) {
        NfLog.v(this.TAG, "callbackRetAvrcp14ChangePathSuccess()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp14ChangePathSuccess beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp14ChangePathSuccess(itemCount);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp14ChangePathSuccess CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp14ItemAttributes(int[] metadataAtrributeIds, String[] texts) {
        NfLog.v(this.TAG, "callbackRetAvrcp14ItemAttributes()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp14ItemAttributes beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp14ItemAttributes(metadataAtrributeIds, texts);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp14ItemAttributes CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp14PlaySelectedItemSuccess() {
        NfLog.v(this.TAG, "callbackRetAvrcp14PlaySelectedItemSuccess()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp14PlaySelectedItemSuccess beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp14PlaySelectedItemSuccess();
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp14PlaySelectedItemSuccess CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp14SearchResult(int uidCounter, long itemCount) {
        NfLog.v(this.TAG, "callbackRetAvrcp14SearchResult()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp14SearchResult beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp14SearchResult(uidCounter, itemCount);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp14SearchResult CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp14AddToNowPlayingSuccess() {
        NfLog.v(this.TAG, "callbackRetAvrcp14AddToNowPlayingSuccess()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp14AddToNowPlayingSuccess beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp14AddToNowPlayingSuccess();
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp14AddToNowPlayingSuccess CallBack Finish.");
    }

    private synchronized void callbackRetAvrcp14SetAbsoluteVolumeSuccess(byte volume) {
        NfLog.v(this.TAG, "callbackRetAvrcp14SetAbsoluteVolumeSuccess()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "retAvrcp14SetAbsoluteVolumeSuccess beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).retAvrcp14SetAbsoluteVolumeSuccess(volume);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "retAvrcp14SetAbsoluteVolumeSuccess CallBack Finish.");
    }

    private synchronized void callbackOnAvrcpErrorResponse(int opId, int reason, byte eventId) {
        NfLog.v(this.TAG, "callbackOnAvrcpErrorResponse()");
        synchronized (this.mRemoteCallbacks) {
            NfLog.v(this.TAG, "onAvrcpErrorResponse beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcpErrorResponse(opId, reason, eventId);
                } catch (RemoteException e) {
                    NfLog.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        NfLog.v(this.TAG, "onAvrcpErrorResponse CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp13RegisterEventWatcherSuccess(byte eventId) {
        Log.v(this.TAG, "callbackOnAvrcp13RegisterEventWatcherSuccess()");
        synchronized (this.mRemoteCallbacks) {
            Log.v(this.TAG, "onAvrcp13RegisterEventWatcherSuccess beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp13RegisterEventWatcherSuccess(eventId);
                } catch (RemoteException e) {
                    Log.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        Log.v(this.TAG, "onAvrcp13RegisterEventWatcherSuccess CallBack Finish.");
    }

    private synchronized void callbackOnAvrcp13RegisterEventWatcherFail(byte eventId) {
        Log.v(this.TAG, "callbackOnAvrcp13RegisterEventWatcherFail()");
        synchronized (this.mRemoteCallbacks) {
            Log.v(this.TAG, "onAvrcp13RegisterEventWatcherFail beginBroadcast()");
            int n = this.mRemoteCallbacks.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    ((INfCallbackAvrcp) this.mRemoteCallbacks.getBroadcastItem(i)).onAvrcp13RegisterEventWatcherFail(eventId);
                } catch (RemoteException e) {
                    Log.e(this.TAG, "Callback count: " + n + " Current index = " + i);
                } catch (NullPointerException e2) {
                    checkCallbacksValid(i);
                }
            }
            this.mRemoteCallbacks.finishBroadcast();
        }
        Log.v(this.TAG, "onAvrcp13RegisterEventWatcherFail CallBack Finish.");
    }

    private synchronized void broadcastOnAvrcpStateChanged(String address, int prevState, int newState) {
        NfLog.v(this.TAG, "broadcastOnAvrcpStateChanged() " + address + " state: " + prevState + " -> " + newState);
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
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_CONNECTION_STATE_CHANGED);
        BluetoothDevice device = NfPrimitive.getDevice(address);
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcpStateChanged broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp13CapabilitiesSupportEvent(byte[] eventIds) {
        NfLog.v(this.TAG, "broadcastRetAvrcp13CapabilitiesSupportEvent()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_CAPABILITY_SUPPORT_EVENT);
        Bundle bundle = new Bundle();
        bundle.putByteArray(NfDefBroadcastAvrcp.EXTRA_SUPPORT_EVENT_IDS, eventIds);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp13CapabilitiesSupportEvent broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp13PlayerSettingAttributesList(byte[] attributeIds) {
        NfLog.v(this.TAG, "broadcastRetAvrcp13PlayerSettingAttributesList()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_PLAYER_SETTING_ATTRIBUTE_LIST);
        Bundle bundle = new Bundle();
        bundle.putByteArray(NfDefBroadcastAvrcp.EXTRA_PLAYER_SETTING_ATTRIBUTE_IDS, attributeIds);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp13PlayerSettingAttributesList broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp13PlayerSettingValuesList(byte attributeId, byte[] valueIds) {
        NfLog.v(this.TAG, "broadcastRetAvrcp13PlayerSettingValuesList()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_PLAYER_SETTING_VALUES_LIST);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_SETTING_ATTRIBUTE_ID, attributeId);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_SETTING_VALUE_IDS, valueIds);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp13PlayerSettingValuesList broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp13PlayerSettingCurrentValues(byte[] attributeIds, byte[] valueIds) {
        NfLog.v(this.TAG, "broadcastRetAvrcp13PlayerSettingCurrentValues()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_PLAYER_SETTING_CURRENT_VALUES);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_SETTING_ATTRIBUTE_IDS, attributeIds);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_SETTING_ATTRIBUTE_VALUE_IDS, valueIds);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp13PlayerSettingCurrentValues broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp13SetPlayerSettingValueSuccess() {
        NfLog.v(this.TAG, "broadcastRetAvrcp13SetPlayerSettingValueSuccess()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_SET_PLAYER_SETTING_VALUE_SUCCESS);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp13SetPlayerSettingValueSuccess broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp13ElementAttributesPlaying(int[] metadataAtrributeIds, String[] texts) {
        NfLog.v(this.TAG, "broadcastRetAvrcp13ElementAttributesPlaying()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_ELEMENT_ATTRIBUTES_PLAYING);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_METADATA_ATTRIBUTE_IDS, metadataAtrributeIds);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_METADATA_ATTRIBUTE_TEXTS, texts);
        NfPrimitive.sendBroadcast(intent);
        Intent intent2 = new Intent(NfDefBroadcastAvrcp.ACTION_TRACK_EVENT);
        Bundle bundle = new Bundle();
        for (int i = 0; i < metadataAtrributeIds.length; i++) {
            if (metadataAtrributeIds[i] == 1) {
                bundle.putString(NfDefBroadcastAvrcp.METADATA_KEY_TITLE, texts[i]);
            } else if (metadataAtrributeIds[i] == 3) {
                bundle.putString(NfDefBroadcastAvrcp.METADATA_KEY_ALBUM, texts[i]);
            } else if (metadataAtrributeIds[i] == 2) {
                bundle.putString(NfDefBroadcastAvrcp.METADATA_KEY_ARTIST, texts[i]);
            } else if (metadataAtrributeIds[i] == 7) {
                bundle.putString(NfDefBroadcastAvrcp.METADATA_KEY_DURATION, texts[i]);
            }
        }
        intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_METADATA, bundle);
        NfPrimitive.sendBroadcast(intent2);
        NfLog.v(this.TAG, "retAvrcp13ElementAttributesPlaying broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp13PlayStatus(long songLen, long songPos, byte statusId) {
        NfLog.v(this.TAG, "broadcastRetAvrcp13PlayStatus() songLen: " + songLen + " songPos: " + songPos + " statusId: " + ((int) statusId));
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_PLAY_STATUS);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_SONG_LEN, songLen);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_SONG_POS, songPos);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAY_STATUS_ID, statusId);
        NfPrimitive.sendBroadcast(intent);
        Intent intent2 = new Intent(NfDefBroadcastAvrcp.ACTION_TRACK_EVENT);
        switch (statusId) {
            case 0:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 1);
                break;
            case 1:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 3);
                break;
            case 2:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 2);
                break;
            case 3:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 4);
                break;
            case 4:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 5);
                break;
            default:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 0);
                break;
        }
        NfPrimitive.sendBroadcast(intent2);
        NfLog.v(this.TAG, "retAvrcp13PlayStatus broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp13EventPlaybackStatusChanged(byte statusId) {
        NfLog.v(this.TAG, "broadcastOnAvrcp13EventPlaybackStatusChanged() statusId: " + ((int) statusId));
        Intent intent = new Intent("android.bluetooth.avrcp-controller.profile.action.PLAYBACK_STATUS_CHANGED");
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAY_STATUS_ID, statusId);
        NfPrimitive.sendBroadcast(intent);
        Intent intent2 = new Intent(NfDefBroadcastAvrcp.ACTION_TRACK_EVENT);
        switch (statusId) {
            case 0:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 1);
                break;
            case 1:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 3);
                break;
            case 2:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 2);
                break;
            case 3:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 4);
                break;
            case 4:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 5);
                break;
            default:
                intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYBACK, 0);
                break;
        }
        intent2.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAY_STATUS_ID, statusId);
        NfPrimitive.sendBroadcast(intent2);
        NfLog.v(this.TAG, "onAvrcp13EventPlaybackStatusChanged broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp13EventTrackChanged(long elementId) {
        NfLog.v(this.TAG, "broadcastOnAvrcp13EventTrackChanged()");
        Intent intent = new Intent("android.bluetooth.avrcp-controller.profile.action.PLAYBACK_STATUS_CHANGED");
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_ELEMENT_ID, elementId);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcp13EventTrackChanged broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp13EventTrackReachedEnd() {
        NfLog.v(this.TAG, "broadcastOnAvrcp13EventTrackReachedEnd()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_REACHED_END);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcp13EventTrackReachedEnd broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp13EventTrackReachedStart() {
        NfLog.v(this.TAG, "broadcastOnAvrcp13EventTrackReachedStart()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_REACHED_START);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcp13EventTrackReachedStart broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp13EventPlaybackPosChanged(long songPos) {
    }

    private synchronized void broadcastOnAvrcp13EventBatteryStatusChanged(byte statusId) {
        NfLog.v(this.TAG, "broadcastOnAvrcp13EventBatteryStatusChanged()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_BATTERY_STATUS_CHANGED);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_BATTERY_STATUS, statusId);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcp13EventBatteryStatusChanged broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp13EventSystemStatusChanged(byte statusId) {
        NfLog.v(this.TAG, "broadcastOnAvrcp13EventSystemStatusChanged()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_SYSTEM_STATUS_CHANGED);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_SYSTEM_STATUS, statusId);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcp13EventSystemStatusChanged broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp13EventPlayerSettingChanged(byte[] attributeIds, byte[] valueIds) {
        NfLog.v(this.TAG, "broadcastOnAvrcp13EventPlayerSettingChanged()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_PLAYER_SETTING_CHANGED);
        Bundle bundle = new Bundle();
        bundle.putByteArray(NfDefBroadcastAvrcp.EXTRA_PLAYER_SETTING_ATTRIBUTE_IDS, attributeIds);
        bundle.putByteArray(NfDefBroadcastAvrcp.EXTRA_PLAYER_SETTING_ATTRIBUTE_VALUE_IDS, valueIds);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcp13EventPlayerSettingChanged broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp13PlayerSettingAttributeText(int cause, byte attributeId, String text) {
        NfLog.v(this.TAG, "broadcastRetAvrcp13PlayerSettingAttributeText()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_PLAYER_SETTING_ATTRIBUTE_TEXT);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_METADATA_ATTRIBUTE_CAUSE, cause);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_METADATA_ATTRIBUTE_ID, attributeId);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_METADATA_ATTRIBUTE_TEXT, text);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp13PlayerSettingAttributeText broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp13PlayerSettingValueText(int cause, byte valueId, String text) {
        NfLog.v(this.TAG, "broadcastRetAvrcp13PlayerSettingValueText()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_PLAYER_SETTING_VALUE_TEXT);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_METADATA_ATTRIBUTE_CAUSE, cause);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_METADATA_VALUE_ID, valueId);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_METADATA_ATTRIBUTE_TEXT, text);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp13PlayerSettingValueText broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp14EventNowPlayingContentChanged() {
        NfLog.v(this.TAG, "broadcastOnAvrcp14EventNowPlayingContentChanged()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_NOW_PLAYING_CONTENT_CHANGED);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcp14EventNowPlayingContentChanged broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp14EventAvailablePlayerChanged() {
        NfLog.v(this.TAG, "broadcastOnAvrcp14EventAvailablePlayerChanged()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_AVAILABLE_PLAYER_CHANGED);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcp14EventAvailablePlayerChanged broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp14EventAddressedPlayerChanged(int playerId, int uidCounter) {
        NfLog.v(this.TAG, "broadcastOnAvrcp14EventAddressedPlayerChanged()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_ADDRESSED_PLAYER_CHANGED);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_ID, playerId);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_UID_COUNTER, uidCounter);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcp14EventAddressedPlayerChanged broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp14EventUidsChanged(int uidCounter) {
        NfLog.v(this.TAG, "broadcastOnAvrcp14EventUidsChanged()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_UIDS_CHANGED);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_UID_COUNTER, uidCounter);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcp14EventUidsChanged broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp14EventVolumeChanged(byte volume) {
        NfLog.v(this.TAG, "broadcastOnAvrcp14EventVolumeChanged()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_VOLUME_CHANGED);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_VALUME_VALUE, volume);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcp14EventVolumeChanged broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp14SetAddressedPlayerSuccess() {
        NfLog.v(this.TAG, "retAvrcp14SetAddressedPlayerSuccess()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_SET_ADDRESSED_PLAYER_SUCCESS);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp14SetAddressedPlayerSuccess broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp14SetBrowsedPlayerSuccess(String[] path, int uidCounter, long itemCount) {
        NfLog.v(this.TAG, "broadcastRetAvrcp14SetBrowsedPlayerSuccess()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_SET_BROWSED_PLAYER_SUCCESS);
        Bundle bundle = new Bundle();
        bundle.putStringArray(NfDefBroadcastAvrcp.EXTRA_PLAYER_PATH, path);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_ITEM_COUNT, itemCount);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_UID_COUNTER, uidCounter);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp14SetBrowsedPlayerSuccess broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp14FolderItems(int uidCounter, long itemCount) {
        NfLog.v(this.TAG, "broadcastRetAvrcp14FolderItems()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_FOLDER_ITEMS);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_ITEM_COUNT, itemCount);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_UID_COUNTER, uidCounter);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp14FolderItems broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp14MediaItems(int uidCounter, long itemCount) {
        NfLog.v(this.TAG, "broadcastRetAvrcp14MediaItems()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_MEDIA_ITEMS);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_ITEM_COUNT, itemCount);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_UID_COUNTER, uidCounter);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp14MediaItems broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp14ChangePathSuccess(long itemCount) {
        NfLog.v(this.TAG, "broadcastRetAvrcp14ChangePathSuccess()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_CHANGE_PATH_SUCCESS);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_ITEM_COUNT, itemCount);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp14ChangePathSuccess broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp14ItemAttributes(int[] metadataAtrributeIds, String[] texts) {
        NfLog.v(this.TAG, "broadcastRetAvrcp14ItemAttributes()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_ITEM_ATTRIBUTES);
        Bundle bundle = new Bundle();
        bundle.putIntArray(NfDefBroadcastAvrcp.EXTRA_METADATA_ATTRIBUTE_IDS, metadataAtrributeIds);
        bundle.putStringArray(NfDefBroadcastAvrcp.EXTRA_METADATA_ATTRIBUTE_TEXTS, texts);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp14ItemAttributes broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp14PlaySelectedItemSuccess() {
        NfLog.v(this.TAG, "broadcastRetAvrcp14PlaySelectedItemSuccess()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_PLAYER_SELECTED_ITEM_SUCCESS);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp14PlaySelectedItemSuccess broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp14SearchResult(int uidCounter, long itemCount) {
        NfLog.v(this.TAG, "broadcastRetAvrcp14SearchResult()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_SEARCH_RESULT);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_UID_COUNTER, uidCounter);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_PLAYER_ITEM_COUNT, itemCount);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp14SearchResult broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp14AddToNowPlayingSuccess() {
        NfLog.v(this.TAG, "broadcastRetAvrcp14AddToNowPlayingSuccess()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_ADD_TO_NOW_PLAYING_SUCCESS);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp14AddToNowPlayingSuccess broadcast Finish.");
    }

    private synchronized void broadcastRetAvrcp14SetAbsoluteVolumeSuccess(byte volume) {
        NfLog.v(this.TAG, "broadcastRetAvrcp14SetAbsoluteVolumeSuccess()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_SET_ABSOLUTE_VALUME_SUCCESS);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_ABSOLUTE_VALUME_VALUE, volume);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "retAvrcp14SetAbsoluteVolumeSuccess broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcpErrorResponse(int opId, int reason, byte eventId) {
        NfLog.v(this.TAG, "broadcastOnAvrcpErrorResponse()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_ERROR_RESPONSE);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_OPERATION_ID, opId);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_REASON_CODE, reason);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_EVENT_ID, eventId);
        NfPrimitive.sendBroadcast(intent);
        NfLog.v(this.TAG, "onAvrcpErrorResponse broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp13RegisterEventWatcherSuccess(byte eventId) {
        Log.v(this.TAG, "broadcastOnAvrcp13RegisterEventWatcherSuccess()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_REGISTER_EVENT_WATCHER_SUCCESS);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_EVENT_ID, eventId);
        NfPrimitive.sendBroadcast(intent);
        Log.v(this.TAG, "onAvrcp13RegisterEventWatcherSuccess broadcast Finish.");
    }

    private synchronized void broadcastOnAvrcp13RegisterEventWatcherFail(byte eventId) {
        Log.v(this.TAG, "broadcastOnAvrcp13RegisterEventWatcherFail()");
        Intent intent = new Intent(NfDefBroadcastAvrcp.ACTION_REGISTER_EVENT_WATCHER_FAIL);
        intent.putExtra(NfDefBroadcastAvrcp.EXTRA_EVENT_ID, eventId);
        NfPrimitive.sendBroadcast(intent);
        Log.v(this.TAG, "onAvrcp13RegisterEventWatcherFail broadcast Finish.");
    }
}
