package com.nforetek.bt.profile.pbap.java;

import com.nforetek.bt.res.obex.HeaderSet;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class ObexAppParameters {
    private final HashMap<Byte, byte[]> mParams = new HashMap<>();

    public ObexAppParameters() {
    }

    public ObexAppParameters(byte[] raw) {
        if (raw != 0) {
            int i = 0;
            while (i < raw.length && raw.length - i >= 2) {
                int i2 = i + 1;
                byte tag = raw[i];
                int i3 = i2 + 1;
                int i4 = raw[i2];
                if ((raw.length - i3) - i4 >= 0) {
                    byte[] val = new byte[i4];
                    System.arraycopy(raw, i3, val, 0, i4);
                    add(tag, val);
                    i = i3 + i4;
                } else {
                    return;
                }
            }
        }
    }

    public static ObexAppParameters fromHeaderSet(HeaderSet headerset) {
        try {
            byte[] raw = (byte[]) headerset.getHeader(76);
            return new ObexAppParameters(raw);
        } catch (IOException e) {
            return null;
        }
    }

    public byte[] getHeader() {
        int length = 0;
        for (Map.Entry<Byte, byte[]> entry : this.mParams.entrySet()) {
            length += entry.getValue().length + 2;
        }
        byte[] ret = new byte[length];
        int idx = 0;
        for (Map.Entry<Byte, byte[]> entry2 : this.mParams.entrySet()) {
            int length2 = entry2.getValue().length;
            int idx2 = idx + 1;
            ret[idx] = entry2.getKey().byteValue();
            int idx3 = idx2 + 1;
            ret[idx2] = (byte) length2;
            System.arraycopy(entry2.getValue(), 0, ret, idx3, length2);
            idx = idx3 + length2;
        }
        return ret;
    }

    public void addToHeaderSet(HeaderSet headerset) {
        if (this.mParams.size() > 0) {
            headerset.setHeader(76, getHeader());
        }
    }

    public boolean exists(byte tag) {
        return this.mParams.containsKey(Byte.valueOf(tag));
    }

    public void add(byte tag, byte val) {
        byte[] bval = ByteBuffer.allocate(1).put(val).array();
        this.mParams.put(Byte.valueOf(tag), bval);
    }

    public void add(byte tag, short val) {
        byte[] bval = ByteBuffer.allocate(2).putShort(val).array();
        this.mParams.put(Byte.valueOf(tag), bval);
    }

    public void add(byte tag, int val) {
        byte[] bval = ByteBuffer.allocate(4).putInt(val).array();
        this.mParams.put(Byte.valueOf(tag), bval);
    }

    public void add(byte tag, long val) {
        byte[] bval = ByteBuffer.allocate(8).putLong(val).array();
        this.mParams.put(Byte.valueOf(tag), bval);
    }

    public void add(byte tag, String val) {
        byte[] bval = val.getBytes();
        this.mParams.put(Byte.valueOf(tag), bval);
    }

    public void add(byte tag, byte[] bval) {
        this.mParams.put(Byte.valueOf(tag), bval);
    }

    public byte getByte(byte tag) {
        byte[] bval = this.mParams.get(Byte.valueOf(tag));
        if (bval == null || bval.length < 1) {
            return (byte) 0;
        }
        return ByteBuffer.wrap(bval).get();
    }

    public short getShort(byte tag) {
        byte[] bval = this.mParams.get(Byte.valueOf(tag));
        if (bval == null || bval.length < 2) {
            return (short) 0;
        }
        return ByteBuffer.wrap(bval).getShort();
    }

    public int getInt(byte tag) {
        byte[] bval = this.mParams.get(Byte.valueOf(tag));
        if (bval == null || bval.length < 4) {
            return 0;
        }
        return ByteBuffer.wrap(bval).getInt();
    }

    public String getString(byte tag) {
        byte[] bval = this.mParams.get(Byte.valueOf(tag));
        if (bval == null) {
            return null;
        }
        return new String(bval);
    }

    public byte[] getByteArray(byte tag) {
        byte[] bval = this.mParams.get(Byte.valueOf(tag));
        return bval;
    }

    public String toString() {
        return this.mParams.toString();
    }
}
