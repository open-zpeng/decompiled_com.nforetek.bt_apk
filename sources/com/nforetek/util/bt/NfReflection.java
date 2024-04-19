package com.nforetek.util.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.ParcelUuid;
import com.nforetek.util.normal.NfLog;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class NfReflection {
    private static String TAG = "NfReflection";
    private static BluetoothAdapter sAdapter = null;

    public static boolean createBond(BluetoothDevice btDevice) {
        try {
            Class btClass = btDevice.getClass();
            Method createBondMethod = btClass.getMethod("createBond", new Class[0]);
            Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice, new Object[0]);
            return returnValue.booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeBond(BluetoothDevice btDevice) {
        try {
            Method removeBondMethod = btDevice.getClass().getMethod("removeBond", new Class[0]);
            Boolean returnValue = (Boolean) removeBondMethod.invoke(btDevice, new Object[0]);
            return returnValue.booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean cancelBondProcess(BluetoothDevice btDevice) {
        try {
            Method cancelBondMethod = btDevice.getClass().getMethod("cancelBondProcess", new Class[0]);
            Boolean returnValue = (Boolean) cancelBondMethod.invoke(btDevice, new Object[0]);
            return returnValue.booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sdpSearch(BluetoothDevice btDevice, ParcelUuid uuid) {
        try {
            Class btClass = btDevice.getClass();
            Method sdpSearchMethod = btClass.getDeclaredMethod("sdpSearch", ParcelUuid.class);
            Boolean returnValue = (Boolean) sdpSearchMethod.invoke(btDevice, uuid);
            return returnValue.booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean doesClassMatch(BluetoothClass btClass, int value) {
        boolean z = false;
        try {
            try {
                try {
                    try {
                        Class btC = btClass.getClass();
                        Method doesClassMatchMethod = btC.getDeclaredMethod("doesClassMatch", Integer.TYPE);
                        Boolean returnValue = (Boolean) doesClassMatchMethod.invoke(btClass, Integer.valueOf(value));
                        NfLog.v("returnValue", new StringBuilder().append(returnValue).toString());
                        z = returnValue.booleanValue();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (SecurityException e3) {
                e3.printStackTrace();
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return z;
    }

    public static boolean setScanMode(int value) {
        boolean z = false;
        try {
            try {
                try {
                    Class baClass = getAdapter().getClass();
                    Method setScanModeMethod = baClass.getDeclaredMethod("setScanMode", Integer.TYPE);
                    Boolean returnValue = (Boolean) setScanModeMethod.invoke(getAdapter(), Integer.valueOf(value));
                    NfLog.v("returnValue", new StringBuilder().append(returnValue).toString());
                    z = returnValue.booleanValue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (SecurityException e3) {
                e3.printStackTrace();
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return z;
    }

    public static void setDiscoverableTimeout(int value) {
        try {
            try {
                try {
                    Class baClass = getAdapter().getClass();
                    Method setDiscoverableTimeoutMethod = baClass.getDeclaredMethod("setDiscoverableTimeout", Integer.TYPE);
                    setDiscoverableTimeoutMethod.invoke(getAdapter(), Integer.valueOf(value));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (SecurityException e3) {
                e3.printStackTrace();
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public static BluetoothServerSocket listenUsingRfcommOn(int value) {
        try {
            try {
                try {
                    Class baClass = getAdapter().getClass();
                    Method listenUsingRfcommOnMethod = baClass.getDeclaredMethod("listenUsingRfcommOn", Integer.TYPE);
                    BluetoothServerSocket returnValue = (BluetoothServerSocket) listenUsingRfcommOnMethod.invoke(getAdapter(), Integer.valueOf(value));
                    NfLog.v("returnValue", new StringBuilder().append(returnValue).toString());
                    return returnValue;
                } catch (SecurityException e) {
                    e.printStackTrace();
                    return null;
                }
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                return null;
            } catch (Exception e3) {
                e3.printStackTrace();
                return null;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static BluetoothSocket createBluetoothSocket(int type, int fd, boolean auth, boolean encrypt, BluetoothDevice device, int port, ParcelUuid uuid) {
        NfLog.d(TAG, "createBluetoothSocket() type: " + type + " port: " + port);
        Object obj = null;
        try {
            try {
                try {
                    Constructor con = BluetoothSocket.class.getDeclaredConstructor(Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE, BluetoothDevice.class, Integer.TYPE, ParcelUuid.class);
                    con.setAccessible(true);
                    obj = con.newInstance(Integer.valueOf(type), Integer.valueOf(fd), Boolean.valueOf(auth), Boolean.valueOf(encrypt), device, Integer.valueOf(port), uuid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (SecurityException e3) {
                e3.printStackTrace();
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return (BluetoothSocket) obj;
    }

    private static BluetoothAdapter getAdapter() {
        sAdapter = BluetoothAdapter.getDefaultAdapter();
        if (sAdapter == null) {
            NfLog.e(TAG, "BluetoothAdapter is null.");
        }
        return sAdapter;
    }

    public static boolean setPin(Class btClass, BluetoothDevice btDevice, String str) throws Exception {
        NfLog.d(TAG, "setPin() type: " + str);
        try {
            Method setPinMethod = btClass.getDeclaredMethod("setPin", byte[].class);
            Boolean returnValue = (Boolean) setPinMethod.invoke(btDevice, str.getBytes());
            NfLog.d("returnValue", new StringBuilder().append(returnValue).toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return true;
    }

    public static boolean createBond(Class btClass, BluetoothDevice btDevice) throws Exception {
        NfLog.d(TAG, "createBond() " + btDevice.getAddress());
        Method createBondMethod = btClass.getMethod("createBond", new Class[0]);
        Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice, new Object[0]);
        return returnValue.booleanValue();
    }
}
