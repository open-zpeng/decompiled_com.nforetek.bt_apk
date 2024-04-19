package com.nforetek.bt.res.obex;

import android.util.Log;
import com.nforetek.bt.res.NfDef;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
/* loaded from: classes.dex */
public final class ObexHelper {
    public static final int BASE_PACKET_LENGTH = 3;
    public static final boolean DBG = true;
    public static final int LOWER_LIMIT_MAX_PACKET_SIZE = 255;
    public static final int MAX_CLIENT_PACKET_SIZE = 64512;
    public static final int MAX_PACKET_SIZE_INT = 65534;
    public static final int OBEX_AUTH_REALM_CHARSET_ASCII = 0;
    public static final int OBEX_AUTH_REALM_CHARSET_ISO_8859_1 = 1;
    public static final int OBEX_AUTH_REALM_CHARSET_ISO_8859_2 = 2;
    public static final int OBEX_AUTH_REALM_CHARSET_ISO_8859_3 = 3;
    public static final int OBEX_AUTH_REALM_CHARSET_ISO_8859_4 = 4;
    public static final int OBEX_AUTH_REALM_CHARSET_ISO_8859_5 = 5;
    public static final int OBEX_AUTH_REALM_CHARSET_ISO_8859_6 = 6;
    public static final int OBEX_AUTH_REALM_CHARSET_ISO_8859_7 = 7;
    public static final int OBEX_AUTH_REALM_CHARSET_ISO_8859_8 = 8;
    public static final int OBEX_AUTH_REALM_CHARSET_ISO_8859_9 = 9;
    public static final int OBEX_AUTH_REALM_CHARSET_UNICODE = 255;
    public static final int OBEX_OPCODE_ABORT = 255;
    public static final int OBEX_OPCODE_CONNECT = 128;
    public static final int OBEX_OPCODE_DISCONNECT = 129;
    public static final int OBEX_OPCODE_FINAL_BIT_MASK = 128;
    public static final int OBEX_OPCODE_GET = 3;
    public static final int OBEX_OPCODE_GET_FINAL = 131;
    public static final int OBEX_OPCODE_PUT = 2;
    public static final int OBEX_OPCODE_PUT_FINAL = 130;
    public static final int OBEX_OPCODE_RESERVED = 4;
    public static final int OBEX_OPCODE_RESERVED_FINAL = 132;
    public static final int OBEX_OPCODE_SETPATH = 133;
    public static final byte OBEX_SRMP_WAIT = 1;
    public static final byte OBEX_SRM_DISABLE = 0;
    public static final byte OBEX_SRM_ENABLE = 1;
    public static final byte OBEX_SRM_SUPPORT = 2;
    private static final String TAG = "ObexHelper";

    private ObexHelper() {
    }

    public static byte[] updateHeaderSet(HeaderSet header, byte[] headerArray) throws IOException {
        int index = 0;
        byte[] body = null;
        while (index < headerArray.length) {
            try {
                int headerID = headerArray[index] & 255;
                switch (headerID & 192) {
                    case 0:
                    case 64:
                        boolean trimTail = true;
                        int index2 = index + 1;
                        int length = headerArray[index2] & 255;
                        int index3 = index2 + 1;
                        int length2 = ((length << 8) + (headerArray[index3] & 255)) - 3;
                        index = index3 + 1;
                        if (length2 < 0) {
                            length2 = 0;
                            Log.e("obxHelper", "length < 0, set length to 0");
                        }
                        byte[] value = new byte[length2];
                        try {
                            System.arraycopy(headerArray, index, value, 0, length2);
                            if (length2 == 0 || (length2 > 0 && value[length2 - 1] != 0)) {
                                trimTail = false;
                            }
                            switch (headerID) {
                                case HeaderSet.TYPE /* 66 */:
                                    if (!trimTail) {
                                        try {
                                            header.setHeader(headerID, new String(value, 0, value.length, "ISO8859_1"));
                                            break;
                                        } catch (UnsupportedEncodingException e) {
                                            throw e;
                                        }
                                    } else {
                                        header.setHeader(headerID, new String(value, 0, value.length - 1, "ISO8859_1"));
                                        break;
                                    }
                                case 67:
                                case 69:
                                case HeaderSet.TARGET /* 70 */:
                                case HeaderSet.HTTP /* 71 */:
                                case HeaderSet.WHO /* 74 */:
                                case 75:
                                case HeaderSet.APPLICATION_PARAMETER /* 76 */:
                                default:
                                    if ((headerID & 192) == 0) {
                                        header.setHeader(headerID, convertToUnicode(value, true));
                                        break;
                                    } else {
                                        header.setHeader(headerID, value);
                                        break;
                                    }
                                case HeaderSet.TIME_ISO_8601 /* 68 */:
                                    try {
                                        String dateString = new String(value, "ISO8859_1");
                                        Calendar temp = Calendar.getInstance();
                                        if (dateString.length() == 16 && dateString.charAt(15) == 'Z') {
                                            temp.setTimeZone(TimeZone.getTimeZone("UTC"));
                                        }
                                        temp.set(1, Integer.parseInt(dateString.substring(0, 4)));
                                        temp.set(2, Integer.parseInt(dateString.substring(4, 6)));
                                        temp.set(5, Integer.parseInt(dateString.substring(6, 8)));
                                        temp.set(11, Integer.parseInt(dateString.substring(9, 11)));
                                        temp.set(12, Integer.parseInt(dateString.substring(11, 13)));
                                        temp.set(13, Integer.parseInt(dateString.substring(13, 15)));
                                        header.setHeader(68, temp);
                                        break;
                                    } catch (UnsupportedEncodingException e2) {
                                        throw e2;
                                    }
                                    break;
                                case HeaderSet.BODY /* 72 */:
                                case HeaderSet.END_OF_BODY /* 73 */:
                                    body = new byte[length2 + 1];
                                    body[0] = (byte) headerID;
                                    try {
                                        System.arraycopy(headerArray, index, body, 1, length2);
                                        break;
                                    } catch (Exception e3) {
                                        if (length2 > headerArray.length) {
                                            Log.e("obxHelper", "length out off bound");
                                            break;
                                        } else {
                                            Log.e("obxHelper", "out off bound issue!");
                                            break;
                                        }
                                    }
                                case HeaderSet.AUTH_CHALLENGE /* 77 */:
                                    header.mAuthChall = new byte[length2];
                                    try {
                                        System.arraycopy(headerArray, index, header.mAuthChall, 0, length2);
                                        break;
                                    } catch (Exception e4) {
                                        if (length2 > headerArray.length) {
                                            Log.e("obxHelper", "length out off bound");
                                            break;
                                        } else {
                                            Log.e("obxHelper", "out off bound issue!");
                                            break;
                                        }
                                    }
                                case HeaderSet.AUTH_RESPONSE /* 78 */:
                                    header.mAuthResp = new byte[length2];
                                    try {
                                        System.arraycopy(headerArray, index, header.mAuthResp, 0, length2);
                                        break;
                                    } catch (Exception e5) {
                                        if (length2 > headerArray.length) {
                                            Log.e("obxHelper", "length out off bound");
                                            break;
                                        } else {
                                            Log.e("obxHelper", "out off bound issue!");
                                            break;
                                        }
                                    }
                            }
                            index += length2;
                            break;
                        } catch (Exception e6) {
                            if (length2 > headerArray.length) {
                                Log.e("obxHelper", "length out off bound");
                                break;
                            } else {
                                Log.e("obxHelper", "out off bound issue!");
                                break;
                            }
                        }
                    case 128:
                        int index4 = index + 1;
                        try {
                            header.setHeader(headerID, Byte.valueOf(headerArray[index4]));
                        } catch (Exception e7) {
                        }
                        index = index4 + 1;
                        break;
                    case 192:
                        int index5 = index + 1;
                        byte[] value2 = new byte[4];
                        try {
                            System.arraycopy(headerArray, index5, value2, 0, 4);
                        } catch (Exception e8) {
                            if (4 > headerArray.length) {
                                Log.e("obxHelper", "length out off bound");
                            } else {
                                Log.e("obxHelper", "out off bound issue!");
                            }
                        }
                        if (headerID != 196) {
                            if (headerID == 203) {
                                try {
                                    header.mConnectionID = new byte[4];
                                    try {
                                        System.arraycopy(value2, 0, header.mConnectionID, 0, 4);
                                    } catch (Exception e9) {
                                        if (4 > headerArray.length) {
                                            Log.e("obxHelper", "length out off bound");
                                        } else {
                                            Log.e("obxHelper", "out off bound issue!");
                                        }
                                    }
                                } catch (Exception e10) {
                                    throw new IOException("Header was not formatted properly", e10);
                                }
                            } else {
                                header.setHeader(headerID, Long.valueOf(convertToLong(value2)));
                            }
                        } else {
                            Calendar temp2 = Calendar.getInstance();
                            temp2.setTime(new Date(convertToLong(value2) * 1000));
                            header.setHeader(196, temp2);
                        }
                        index = index5 + 4;
                        break;
                }
            } catch (IOException e11) {
                throw new IOException("Header was not formatted properly", e11);
            }
        }
        return body;
    }

    public static byte[] createHeader(HeaderSet head, boolean nullOut) {
        byte[] result;
        byte[] lengthArray = new byte[2];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            if (head.mConnectionID != null && head.getHeader(70) == null) {
                out.write(-53);
                out.write(head.mConnectionID);
            }
            Long intHeader = (Long) head.getHeader(192);
            if (intHeader != null) {
                out.write(-64);
                out.write(convertToByteArray(intHeader.longValue()));
                if (nullOut) {
                    head.setHeader(192, null);
                }
            }
            String stringHeader = (String) head.getHeader(1);
            if (stringHeader != null) {
                out.write(1);
                byte[] value = convertToUnicodeByteArray(stringHeader);
                int length = value.length + 3;
                lengthArray[0] = (byte) ((length >> 8) & 255);
                lengthArray[1] = (byte) (length & 255);
                out.write(lengthArray);
                out.write(value);
                if (nullOut) {
                    head.setHeader(1, null);
                }
            } else if (head.getEmptyNameHeader()) {
                out.write(1);
                lengthArray[0] = 0;
                lengthArray[1] = 3;
                out.write(lengthArray);
            }
            String stringHeader2 = (String) head.getHeader(66);
            if (stringHeader2 != null) {
                out.write(66);
                try {
                    byte[] value2 = stringHeader2.getBytes("ISO8859_1");
                    int length2 = value2.length + 4;
                    lengthArray[0] = (byte) ((length2 >> 8) & 255);
                    lengthArray[1] = (byte) (length2 & 255);
                    out.write(lengthArray);
                    out.write(value2);
                    out.write(0);
                    if (nullOut) {
                        head.setHeader(66, null);
                    }
                } catch (UnsupportedEncodingException e) {
                    throw e;
                }
            }
            Long intHeader2 = (Long) head.getHeader(195);
            if (intHeader2 != null) {
                out.write(-61);
                out.write(convertToByteArray(intHeader2.longValue()));
                if (nullOut) {
                    head.setHeader(195, null);
                }
            }
            Calendar dateHeader = (Calendar) head.getHeader(68);
            if (dateHeader != null) {
                StringBuffer buffer = new StringBuffer();
                try {
                    int temp = dateHeader.get(1);
                    for (int i = temp; i < 1000; i *= 10) {
                        buffer.append("0");
                    }
                    buffer.append(temp);
                    int temp2 = dateHeader.get(2);
                    if (temp2 < 10) {
                        buffer.append("0");
                    }
                    buffer.append(temp2);
                    int temp3 = dateHeader.get(5);
                    if (temp3 < 10) {
                        buffer.append("0");
                    }
                    buffer.append(temp3);
                    buffer.append("T");
                    int temp4 = dateHeader.get(11);
                    if (temp4 < 10) {
                        buffer.append("0");
                    }
                    buffer.append(temp4);
                    int temp5 = dateHeader.get(12);
                    if (temp5 < 10) {
                        buffer.append("0");
                    }
                    buffer.append(temp5);
                    int temp6 = dateHeader.get(13);
                    if (temp6 < 10) {
                        buffer.append("0");
                    }
                    buffer.append(temp6);
                    if (dateHeader.getTimeZone().getID().equals("UTC")) {
                        buffer.append("Z");
                    }
                    try {
                        byte[] value3 = buffer.toString().getBytes("ISO8859_1");
                        int length3 = value3.length + 3;
                        lengthArray[0] = (byte) ((length3 >> 8) & 255);
                        lengthArray[1] = (byte) (length3 & 255);
                        out.write(68);
                        out.write(lengthArray);
                        out.write(value3);
                        if (nullOut) {
                            head.setHeader(68, null);
                        }
                    } catch (UnsupportedEncodingException e2) {
                        throw e2;
                    }
                } catch (IOException e3) {
                    result = out.toByteArray();
                    try {
                        out.close();
                    } catch (Exception e4) {
                    }
                    return result;
                } catch (Throwable th) {
                    th = th;
                    out.toByteArray();
                    try {
                        out.close();
                    } catch (Exception e5) {
                    }
                    throw th;
                }
            }
            Calendar dateHeader2 = (Calendar) head.getHeader(196);
            if (dateHeader2 != null) {
                out.write(196);
                out.write(convertToByteArray(dateHeader2.getTime().getTime() / 1000));
                if (nullOut) {
                    head.setHeader(196, null);
                }
            }
            String stringHeader3 = (String) head.getHeader(5);
            if (stringHeader3 != null) {
                out.write(5);
                byte[] value4 = convertToUnicodeByteArray(stringHeader3);
                int length4 = value4.length + 3;
                lengthArray[0] = (byte) ((length4 >> 8) & 255);
                lengthArray[1] = (byte) (length4 & 255);
                out.write(lengthArray);
                out.write(value4);
                if (nullOut) {
                    head.setHeader(5, null);
                }
            }
            byte[] value5 = (byte[]) head.getHeader(70);
            if (value5 != null) {
                out.write(70);
                int length5 = value5.length + 3;
                lengthArray[0] = (byte) ((length5 >> 8) & 255);
                lengthArray[1] = (byte) (length5 & 255);
                out.write(lengthArray);
                out.write(value5);
                if (nullOut) {
                    head.setHeader(70, null);
                }
            }
            byte[] value6 = (byte[]) head.getHeader(71);
            if (value6 != null) {
                out.write(71);
                int length6 = value6.length + 3;
                lengthArray[0] = (byte) ((length6 >> 8) & 255);
                lengthArray[1] = (byte) (length6 & 255);
                out.write(lengthArray);
                out.write(value6);
                if (nullOut) {
                    head.setHeader(71, null);
                }
            }
            byte[] value7 = (byte[]) head.getHeader(74);
            if (value7 != null) {
                out.write(74);
                int length7 = value7.length + 3;
                lengthArray[0] = (byte) ((length7 >> 8) & 255);
                lengthArray[1] = (byte) (length7 & 255);
                out.write(lengthArray);
                out.write(value7);
                if (nullOut) {
                    head.setHeader(74, null);
                }
            }
            byte[] value8 = (byte[]) head.getHeader(76);
            if (value8 != null) {
                out.write(76);
                int length8 = value8.length + 3;
                lengthArray[0] = (byte) ((length8 >> 8) & 255);
                lengthArray[1] = (byte) (length8 & 255);
                out.write(lengthArray);
                out.write(value8);
                if (nullOut) {
                    head.setHeader(76, null);
                }
            }
            byte[] value9 = (byte[]) head.getHeader(79);
            if (value9 != null) {
                out.write(79);
                int length9 = value9.length + 3;
                lengthArray[0] = (byte) ((length9 >> 8) & 255);
                lengthArray[1] = (byte) (length9 & 255);
                out.write(lengthArray);
                out.write(value9);
                if (nullOut) {
                    head.setHeader(79, null);
                }
            }
            for (int i2 = 0; i2 < 16; i2++) {
                String stringHeader4 = (String) head.getHeader(i2 + 48);
                if (stringHeader4 != null) {
                    out.write(((byte) i2) + 48);
                    byte[] value10 = convertToUnicodeByteArray(stringHeader4);
                    int length10 = value10.length + 3;
                    lengthArray[0] = (byte) ((length10 >> 8) & 255);
                    lengthArray[1] = (byte) (length10 & 255);
                    out.write(lengthArray);
                    out.write(value10);
                    if (nullOut) {
                        head.setHeader(i2 + 48, null);
                    }
                }
                byte[] value11 = (byte[]) head.getHeader(i2 + NfDef.STATE_BASIC_CONNECT_CHECKING_NEED_DISCONNECT_PROFILE);
                if (value11 != null) {
                    out.write(((byte) i2) + 112);
                    int length11 = value11.length + 3;
                    lengthArray[0] = (byte) ((length11 >> 8) & 255);
                    lengthArray[1] = (byte) (length11 & 255);
                    out.write(lengthArray);
                    out.write(value11);
                    if (nullOut) {
                        head.setHeader(i2 + NfDef.STATE_BASIC_CONNECT_CHECKING_NEED_DISCONNECT_PROFILE, null);
                    }
                }
                Byte byteHeader = (Byte) head.getHeader(i2 + ResponseCodes.OBEX_HTTP_MULT_CHOICE);
                if (byteHeader != null) {
                    out.write(((byte) i2) + 176);
                    out.write(byteHeader.byteValue());
                    if (nullOut) {
                        head.setHeader(i2 + ResponseCodes.OBEX_HTTP_MULT_CHOICE, null);
                    }
                }
                Long intHeader3 = (Long) head.getHeader(i2 + 240);
                if (intHeader3 != null) {
                    out.write(((byte) i2) + 240);
                    out.write(convertToByteArray(intHeader3.longValue()));
                    if (nullOut) {
                        head.setHeader(i2 + 240, null);
                    }
                }
            }
            if (head.mAuthChall != null) {
                out.write(77);
                int length12 = head.mAuthChall.length + 3;
                lengthArray[0] = (byte) ((length12 >> 8) & 255);
                lengthArray[1] = (byte) (length12 & 255);
                out.write(lengthArray);
                out.write(head.mAuthChall);
                if (nullOut) {
                    head.mAuthChall = null;
                }
            }
            if (head.mAuthResp != null) {
                out.write(78);
                int length13 = head.mAuthResp.length + 3;
                lengthArray[0] = (byte) ((length13 >> 8) & 255);
                lengthArray[1] = (byte) (length13 & 255);
                out.write(lengthArray);
                out.write(head.mAuthResp);
                if (nullOut) {
                    head.mAuthResp = null;
                }
            }
            Byte byteHeader2 = (Byte) head.getHeader(HeaderSet.SINGLE_RESPONSE_MODE);
            if (byteHeader2 != null) {
                out.write(-105);
                out.write(byteHeader2.byteValue());
                if (nullOut) {
                    head.setHeader(HeaderSet.SINGLE_RESPONSE_MODE, null);
                }
            }
            Byte byteHeader3 = (Byte) head.getHeader(HeaderSet.SINGLE_RESPONSE_MODE_PARAMETER);
            if (byteHeader3 != null) {
                out.write(-104);
                out.write(byteHeader3.byteValue());
                if (nullOut) {
                    head.setHeader(HeaderSet.SINGLE_RESPONSE_MODE_PARAMETER, null);
                }
            }
            result = out.toByteArray();
            try {
                out.close();
            } catch (Exception e6) {
            }
        } catch (IOException e7) {
        } catch (Throwable th2) {
            th = th2;
        }
        return result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int findHeaderEnd(byte[] headerArray, int start, int maxSize) {
        int fullLength = 0;
        int lastLength = -1;
        int index = start;
        while (fullLength < maxSize && index < headerArray.length) {
            int headerID = headerArray[index] < 0 ? headerArray[index] + 256 : headerArray[index];
            lastLength = fullLength;
            switch (headerID & 192) {
                case 0:
                case 64:
                    int index2 = index + 1;
                    int length = headerArray[index2] < 0 ? headerArray[index2] + 256 : headerArray[index2];
                    int index3 = index2 + 1;
                    int length2 = ((length << 8) + (headerArray[index3] < 0 ? headerArray[index3] + 256 : headerArray[index3])) - 3;
                    index = index3 + 1 + length2;
                    fullLength += length2 + 3;
                    break;
                case 128:
                    index = index + 1 + 1;
                    fullLength += 2;
                    break;
                case 192:
                    index += 5;
                    fullLength += 5;
                    break;
            }
        }
        if (lastLength == 0) {
            if (fullLength < maxSize) {
                return headerArray.length;
            }
            return -1;
        }
        return lastLength + start;
    }

    public static long convertToLong(byte[] b) {
        long result = 0;
        long power = 0;
        for (int i = b.length - 1; i >= 0; i--) {
            long value = b[i];
            if (value < 0) {
                value += 256;
            }
            result |= value << ((int) power);
            power += 8;
        }
        return result;
    }

    public static byte[] convertToByteArray(long l) {
        byte[] b = {(byte) ((l >> 24) & 255), (byte) ((l >> 16) & 255), (byte) ((l >> 8) & 255), (byte) (255 & l)};
        return b;
    }

    public static byte[] convertToUnicodeByteArray(String s) {
        if (s == null) {
            return null;
        }
        char[] c = s.toCharArray();
        byte[] result = new byte[(c.length * 2) + 2];
        for (int i = 0; i < c.length; i++) {
            result[i * 2] = (byte) (c[i] >> '\b');
            result[(i * 2) + 1] = (byte) c[i];
        }
        result[result.length - 2] = 0;
        result[result.length - 1] = 0;
        return result;
    }

    public static byte[] getTagValue(byte tag, byte[] triplet) {
        int index = findTag(tag, triplet);
        if (index == -1) {
            return null;
        }
        int index2 = index + 1;
        int length = triplet[index2] & 255;
        byte[] result = new byte[length];
        try {
            System.arraycopy(triplet, index2 + 1, result, 0, length);
            return result;
        } catch (Exception e) {
            if (length > triplet.length) {
                Log.e("obxHelper", "length out off bound");
                return result;
            }
            Log.e("obxHelper", "out off bound issue!");
            return result;
        }
    }

    public static int findTag(byte tag, byte[] value) {
        if (value == null) {
            return -1;
        }
        int index = 0;
        while (index < value.length && value[index] != tag) {
            int length = value[index + 1] & NfDef.AVRCP_PLAYING_STATUS_ID_ERROR;
            index += length + 2;
        }
        if (index >= value.length) {
            return -1;
        }
        return index;
    }

    public static String convertToUnicode(byte[] b, boolean includesNull) {
        if (b == 0 || b.length == 0) {
            return null;
        }
        int arrayLength = b.length;
        if (arrayLength % 2 != 0) {
            throw new IllegalArgumentException("Byte array not of a valid form");
        }
        int arrayLength2 = arrayLength >> 1;
        if (includesNull) {
            arrayLength2--;
        }
        char[] c = new char[arrayLength2];
        for (int i = 0; i < arrayLength2; i++) {
            int upper = b[i * 2];
            int lower = b[(i * 2) + 1];
            if (upper < 0) {
                upper += 256;
            }
            if (lower < 0) {
                lower += 256;
            }
            if (upper == 0 && lower == 0) {
                return new String(c, 0, i);
            }
            c[i] = (char) ((upper << 8) | lower);
        }
        return new String(c);
    }

    public static byte[] computeMd5Hash(byte[] in) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return md5.digest(in);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] computeAuthenticationChallenge(byte[] nonce, String realm, boolean access, boolean userID) throws IOException {
        byte[] authChall;
        if (nonce.length != 16) {
            throw new IllegalArgumentException("Nonce must be 16 bytes long");
        }
        if (realm == null) {
            authChall = new byte[21];
        } else if (realm.length() >= 255) {
            throw new IllegalArgumentException("Realm must be less then 255 bytes");
        } else {
            authChall = new byte[realm.length() + 24];
            authChall[21] = 2;
            authChall[22] = (byte) (realm.length() + 1);
            authChall[23] = 1;
            try {
                System.arraycopy(realm.getBytes("ISO8859_1"), 0, authChall, 24, realm.length());
            } catch (Exception e) {
                if (realm.length() > realm.getBytes("ISO8859_1").length) {
                    Log.e("obxHelper", "length out off bound");
                } else {
                    Log.e("obxHelper", "out off bound issue!");
                }
            }
        }
        authChall[0] = 0;
        authChall[1] = NfDef.AVRCP_BROWSING_STATUS_SEARCH_NOT_SUPPORT;
        try {
            System.arraycopy(nonce, 0, authChall, 2, 16);
        } catch (Exception e2) {
            if (16 > nonce.length) {
                Log.e("obxHelper", "length out off bound");
            } else {
                Log.e("obxHelper", "out off bound issue!");
            }
        }
        authChall[18] = 1;
        authChall[19] = 1;
        authChall[20] = 0;
        if (!access) {
            authChall[20] = (byte) (authChall[20] | 2);
        }
        if (userID) {
            authChall[20] = (byte) (authChall[20] | 1);
        }
        return authChall;
    }

    public static int getMaxTxPacketSize(ObexTransport transport) {
        int size = transport.getMaxTransmitPacketSize();
        return validateMaxPacketSize(size);
    }

    public static int getMaxRxPacketSize(ObexTransport transport) {
        int size = transport.getMaxReceivePacketSize();
        return validateMaxPacketSize(size);
    }

    private static int validateMaxPacketSize(int size) {
        if (size > 65534) {
            Log.w(TAG, "The packet size supported for the connection (" + size + ") is larger than the configured OBEX packet size: " + MAX_PACKET_SIZE_INT);
        }
        if (size != -1) {
            if (size < 255) {
                throw new IllegalArgumentException(String.valueOf(size) + " is less that the lower limit: 255");
            }
            return size;
        }
        return MAX_PACKET_SIZE_INT;
    }
}
