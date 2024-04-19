package com.nforetek.bt.jni;

import android.os.ParcelUuid;
/* loaded from: classes.dex */
public interface NfJniCallbackInterfaceGatt {
    void onJniGattListen(int i);

    void onJniGattServerCharacteristicReadRequest(String str, int i, int i2, boolean z, int i3, int i4, ParcelUuid parcelUuid, int i5, ParcelUuid parcelUuid2);

    void onJniGattServerCharacteristicWriteRequest(String str, int i, int i2, int i3, boolean z, boolean z2, int i4, int i5, ParcelUuid parcelUuid, int i6, ParcelUuid parcelUuid2, byte[] bArr);

    void onJniGattServerConnectionState(String str, int i, boolean z);

    void onJniGattServerDescriptorReadRequest(String str, int i, int i2, boolean z, int i3, int i4, ParcelUuid parcelUuid, int i5, ParcelUuid parcelUuid2, ParcelUuid parcelUuid3);

    void onJniGattServerDescriptorWriteRequest(String str, int i, int i2, int i3, boolean z, boolean z2, int i4, int i5, ParcelUuid parcelUuid, int i6, ParcelUuid parcelUuid2, ParcelUuid parcelUuid3, byte[] bArr);

    void onJniGattServerExecuteWrite(String str, int i, boolean z);

    void onJniGattServerScanResult(String str, int i, byte[] bArr);

    void onJniGattServerServerRegistered(int i);

    void onJniGattServerServiceAdded(int i, int i2, int i3, ParcelUuid parcelUuid);
}
