package com.nforetek.bt.profile.pbap.java.vcard;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.nforetek.bt.profile.pbap.java.vcard.VCardUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class VCardBuilder {
    public static final int DEFAULT_EMAIL_TYPE = 3;
    public static final int DEFAULT_PHONE_TYPE = 1;
    public static final int DEFAULT_POSTAL_TYPE = 1;
    private static final String LOG_TAG = "vCard";
    private static final String SHIFT_JIS = "SHIFT_JIS";
    private static final String VCARD_DATA_PUBLIC = "PUBLIC";
    private static final String VCARD_DATA_SEPARATOR = ":";
    private static final String VCARD_DATA_VCARD = "VCARD";
    public static final String VCARD_END_OF_LINE = "\r\n";
    private static final String VCARD_ITEM_SEPARATOR = ";";
    private static final String VCARD_PARAM_ENCODING_BASE64_AS_B = "ENCODING=B";
    private static final String VCARD_PARAM_ENCODING_BASE64_V21 = "ENCODING=BASE64";
    private static final String VCARD_PARAM_ENCODING_QP = "ENCODING=QUOTED-PRINTABLE";
    private static final String VCARD_PARAM_EQUAL = "=";
    private static final String VCARD_PARAM_SEPARATOR = ";";
    private static final String VCARD_WS = " ";
    private static final Set<String> sAllowedAndroidPropertySet = Collections.unmodifiableSet(new HashSet(Arrays.asList("vnd.android.cursor.item/nickname", "vnd.android.cursor.item/contact_event", "vnd.android.cursor.item/relation")));
    private static final Map<Integer, Integer> sPostalTypePriorityMap = new HashMap();
    private final boolean mAppendTypeParamName;
    private StringBuilder mBuilder;
    private final String mCharset;
    private boolean mEndAppended;
    private final boolean mIsDoCoMo;
    private final boolean mIsJapaneseMobilePhone;
    private final boolean mIsV30OrV40;
    private final boolean mNeedsToConvertPhoneticString;
    private final boolean mOnlyOneNoteFieldIsAvailable;
    private final boolean mRefrainsQPToNameProperties;
    private final boolean mShouldAppendCharsetParam;
    private final boolean mShouldUseQuotedPrintable;
    private final boolean mUsesAndroidProperty;
    private final boolean mUsesDefactProperty;
    private final String mVCardCharsetParameter;
    private final int mVCardType;

    static {
        sPostalTypePriorityMap.put(1, 0);
        sPostalTypePriorityMap.put(2, 1);
        sPostalTypePriorityMap.put(3, 2);
        sPostalTypePriorityMap.put(0, 3);
    }

    public VCardBuilder(int vcardType) {
        this(vcardType, null);
    }

    public VCardBuilder(int vcardType, String charset) {
        boolean z = false;
        this.mVCardType = vcardType;
        if (VCardConfig.isVersion40(vcardType)) {
            Log.w(LOG_TAG, "Should not use vCard 4.0 when building vCard. It is not officially published yet.");
        }
        this.mIsV30OrV40 = VCardConfig.isVersion30(vcardType) || VCardConfig.isVersion40(vcardType);
        this.mShouldUseQuotedPrintable = VCardConfig.shouldUseQuotedPrintable(vcardType);
        this.mIsDoCoMo = VCardConfig.isDoCoMo(vcardType);
        this.mIsJapaneseMobilePhone = VCardConfig.needsToConvertPhoneticString(vcardType);
        this.mOnlyOneNoteFieldIsAvailable = VCardConfig.onlyOneNoteFieldIsAvailable(vcardType);
        this.mUsesAndroidProperty = VCardConfig.usesAndroidSpecificProperty(vcardType);
        this.mUsesDefactProperty = VCardConfig.usesDefactProperty(vcardType);
        this.mRefrainsQPToNameProperties = VCardConfig.shouldRefrainQPToNameProperties(vcardType);
        this.mAppendTypeParamName = VCardConfig.appendTypeParamName(vcardType);
        this.mNeedsToConvertPhoneticString = VCardConfig.needsToConvertPhoneticString(vcardType);
        this.mShouldAppendCharsetParam = (VCardConfig.isVersion30(vcardType) && "UTF-8".equalsIgnoreCase(charset)) ? z : true;
        if (VCardConfig.isDoCoMo(vcardType)) {
            if (!SHIFT_JIS.equalsIgnoreCase(charset)) {
                if (TextUtils.isEmpty(charset)) {
                    this.mCharset = SHIFT_JIS;
                } else {
                    this.mCharset = charset;
                }
            } else {
                this.mCharset = charset;
            }
            this.mVCardCharsetParameter = "CHARSET=SHIFT_JIS";
        } else if (TextUtils.isEmpty(charset)) {
            Log.i(LOG_TAG, "Use the charset \"UTF-8\" for export.");
            this.mCharset = "UTF-8";
            this.mVCardCharsetParameter = "CHARSET=UTF-8";
        } else {
            this.mCharset = charset;
            this.mVCardCharsetParameter = "CHARSET=" + charset;
        }
        clear();
    }

    public void clear() {
        this.mBuilder = new StringBuilder();
        this.mEndAppended = false;
        appendLine("BEGIN", VCARD_DATA_VCARD);
        if (VCardConfig.isVersion40(this.mVCardType)) {
            appendLine("VERSION", "4.0");
        } else if (VCardConfig.isVersion30(this.mVCardType)) {
            appendLine("VERSION", "3.0");
        } else {
            if (!VCardConfig.isVersion21(this.mVCardType)) {
                Log.w(LOG_TAG, "Unknown vCard version detected.");
            }
            appendLine("VERSION", "2.1");
        }
    }

    private boolean containsNonEmptyName(ContentValues contentValues) {
        String familyName = contentValues.getAsString("data3");
        String middleName = contentValues.getAsString("data5");
        String givenName = contentValues.getAsString("data2");
        String prefix = contentValues.getAsString("data4");
        String suffix = contentValues.getAsString("data6");
        String phoneticFamilyName = contentValues.getAsString("data9");
        String phoneticMiddleName = contentValues.getAsString("data8");
        String phoneticGivenName = contentValues.getAsString("data7");
        String displayName = contentValues.getAsString("data1");
        return (TextUtils.isEmpty(familyName) && TextUtils.isEmpty(middleName) && TextUtils.isEmpty(givenName) && TextUtils.isEmpty(prefix) && TextUtils.isEmpty(suffix) && TextUtils.isEmpty(phoneticFamilyName) && TextUtils.isEmpty(phoneticMiddleName) && TextUtils.isEmpty(phoneticGivenName) && TextUtils.isEmpty(displayName)) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0048, code lost:
        if (containsNonEmptyName(r0) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004a, code lost:
        r4 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.content.ContentValues getPrimaryContentValueWithStructuredName(java.util.List<android.content.ContentValues> r8) {
        /*
            r7 = this;
            r3 = 0
            r4 = 0
            java.util.Iterator r5 = r8.iterator()
        L6:
            boolean r6 = r5.hasNext()
            if (r6 != 0) goto L12
        Lc:
            if (r3 != 0) goto L11
            if (r4 == 0) goto L4c
            r3 = r4
        L11:
            return r3
        L12:
            java.lang.Object r0 = r5.next()
            android.content.ContentValues r0 = (android.content.ContentValues) r0
            if (r0 == 0) goto L6
            java.lang.String r6 = "is_super_primary"
            java.lang.Integer r2 = r0.getAsInteger(r6)
            if (r2 == 0) goto L2a
            int r6 = r2.intValue()
            if (r6 <= 0) goto L2a
            r3 = r0
            goto Lc
        L2a:
            if (r3 != 0) goto L6
            java.lang.String r6 = "is_primary"
            java.lang.Integer r1 = r0.getAsInteger(r6)
            if (r1 == 0) goto L42
            int r6 = r1.intValue()
            if (r6 <= 0) goto L42
            boolean r6 = r7.containsNonEmptyName(r0)
            if (r6 == 0) goto L42
            r3 = r0
            goto L6
        L42:
            if (r4 != 0) goto L6
            boolean r6 = r7.containsNonEmptyName(r0)
            if (r6 == 0) goto L6
            r4 = r0
            goto L6
        L4c:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            goto L11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.profile.pbap.java.vcard.VCardBuilder.getPrimaryContentValueWithStructuredName(java.util.List):android.content.ContentValues");
    }

    private VCardBuilder appendNamePropertiesV40(List<ContentValues> contentValuesList) {
        if (this.mIsDoCoMo || this.mNeedsToConvertPhoneticString) {
            Log.w(LOG_TAG, "Invalid flag is used in vCard 4.0 construction. Ignored.");
        }
        if (contentValuesList == null || contentValuesList.isEmpty()) {
            appendLine("FN", "");
        } else {
            ContentValues contentValues = getPrimaryContentValueWithStructuredName(contentValuesList);
            String familyName = contentValues.getAsString("data3");
            String middleName = contentValues.getAsString("data5");
            String givenName = contentValues.getAsString("data2");
            String prefix = contentValues.getAsString("data4");
            String suffix = contentValues.getAsString("data6");
            String formattedName = contentValues.getAsString("data1");
            if (TextUtils.isEmpty(familyName) && TextUtils.isEmpty(givenName) && TextUtils.isEmpty(middleName) && TextUtils.isEmpty(prefix) && TextUtils.isEmpty(suffix)) {
                if (TextUtils.isEmpty(formattedName)) {
                    appendLine("FN", "");
                } else {
                    familyName = formattedName;
                }
            }
            String phoneticFamilyName = contentValues.getAsString("data9");
            String phoneticMiddleName = contentValues.getAsString("data8");
            String phoneticGivenName = contentValues.getAsString("data7");
            String escapedFamily = escapeCharacters(familyName);
            String escapedGiven = escapeCharacters(givenName);
            String escapedMiddle = escapeCharacters(middleName);
            String escapedPrefix = escapeCharacters(prefix);
            String escapedSuffix = escapeCharacters(suffix);
            this.mBuilder.append("N");
            if (!TextUtils.isEmpty(phoneticFamilyName) || !TextUtils.isEmpty(phoneticMiddleName) || !TextUtils.isEmpty(phoneticGivenName)) {
                this.mBuilder.append(";");
                String sortAs = String.valueOf(escapeCharacters(phoneticFamilyName)) + ';' + escapeCharacters(phoneticGivenName) + ';' + escapeCharacters(phoneticMiddleName);
                this.mBuilder.append("SORT-AS=").append(VCardUtils.toStringAsV40ParamValue(sortAs));
            }
            this.mBuilder.append(VCARD_DATA_SEPARATOR);
            this.mBuilder.append(escapedFamily);
            this.mBuilder.append(";");
            this.mBuilder.append(escapedGiven);
            this.mBuilder.append(";");
            this.mBuilder.append(escapedMiddle);
            this.mBuilder.append(";");
            this.mBuilder.append(escapedPrefix);
            this.mBuilder.append(";");
            this.mBuilder.append(escapedSuffix);
            this.mBuilder.append("\r\n");
            if (TextUtils.isEmpty(formattedName)) {
                Log.w(LOG_TAG, "DISPLAY_NAME is empty.");
                String escaped = escapeCharacters(VCardUtils.constructNameFromElements(VCardConfig.getNameOrderType(this.mVCardType), familyName, middleName, givenName, prefix, suffix));
                appendLine("FN", escaped);
            } else {
                String escapedFormatted = escapeCharacters(formattedName);
                this.mBuilder.append("FN");
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(escapedFormatted);
                this.mBuilder.append("\r\n");
            }
            appendPhoneticNameFields(contentValues);
        }
        return this;
    }

    public VCardBuilder appendNameProperties(List<ContentValues> contentValuesList) {
        String formattedName;
        String encodedFamily;
        String encodedGiven;
        String encodedMiddle;
        String encodedPrefix;
        String encodedSuffix;
        if (VCardConfig.isVersion40(this.mVCardType)) {
            return appendNamePropertiesV40(contentValuesList);
        }
        if (contentValuesList == null || contentValuesList.isEmpty()) {
            if (VCardConfig.isVersion30(this.mVCardType)) {
                appendLine("N", "");
                appendLine("FN", "");
                return this;
            } else if (this.mIsDoCoMo) {
                appendLine("N", "");
                return this;
            } else {
                return this;
            }
        }
        ContentValues contentValues = getPrimaryContentValueWithStructuredName(contentValuesList);
        String familyName = contentValues.getAsString("data3");
        String middleName = contentValues.getAsString("data5");
        String givenName = contentValues.getAsString("data2");
        String prefix = contentValues.getAsString("data4");
        String suffix = contentValues.getAsString("data6");
        String displayName = contentValues.getAsString("data1");
        if (!TextUtils.isEmpty(familyName) || !TextUtils.isEmpty(givenName)) {
            boolean reallyAppendCharsetParameterToName = shouldAppendCharsetParam(familyName, givenName, middleName, prefix, suffix);
            boolean reallyUseQuotedPrintableToName = (this.mRefrainsQPToNameProperties || (VCardUtils.containsOnlyNonCrLfPrintableAscii(familyName) && VCardUtils.containsOnlyNonCrLfPrintableAscii(givenName) && VCardUtils.containsOnlyNonCrLfPrintableAscii(middleName) && VCardUtils.containsOnlyNonCrLfPrintableAscii(prefix) && VCardUtils.containsOnlyNonCrLfPrintableAscii(suffix))) ? false : true;
            if (!TextUtils.isEmpty(displayName)) {
                formattedName = displayName;
            } else {
                formattedName = VCardUtils.constructNameFromElements(VCardConfig.getNameOrderType(this.mVCardType), familyName, middleName, givenName, prefix, suffix);
            }
            boolean reallyAppendCharsetParameterToFN = shouldAppendCharsetParam(formattedName);
            boolean reallyUseQuotedPrintableToFN = (this.mRefrainsQPToNameProperties || VCardUtils.containsOnlyNonCrLfPrintableAscii(formattedName)) ? false : true;
            if (reallyUseQuotedPrintableToName) {
                encodedFamily = encodeQuotedPrintable(familyName);
                encodedGiven = encodeQuotedPrintable(givenName);
                encodedMiddle = encodeQuotedPrintable(middleName);
                encodedPrefix = encodeQuotedPrintable(prefix);
                encodedSuffix = encodeQuotedPrintable(suffix);
            } else {
                encodedFamily = escapeCharacters(familyName);
                encodedGiven = escapeCharacters(givenName);
                encodedMiddle = escapeCharacters(middleName);
                encodedPrefix = escapeCharacters(prefix);
                encodedSuffix = escapeCharacters(suffix);
            }
            String encodedFormattedname = reallyUseQuotedPrintableToFN ? encodeQuotedPrintable(formattedName) : escapeCharacters(formattedName);
            this.mBuilder.append("N");
            if (this.mIsDoCoMo) {
                if (reallyAppendCharsetParameterToName) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (reallyUseQuotedPrintableToName) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(formattedName);
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
            } else {
                if (reallyAppendCharsetParameterToName) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (reallyUseQuotedPrintableToName) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(encodedFamily);
                this.mBuilder.append(";");
                this.mBuilder.append(encodedGiven);
                this.mBuilder.append(";");
                this.mBuilder.append(encodedMiddle);
                this.mBuilder.append(";");
                this.mBuilder.append(encodedPrefix);
                this.mBuilder.append(";");
                this.mBuilder.append(encodedSuffix);
            }
            this.mBuilder.append("\r\n");
            this.mBuilder.append("FN");
            if (reallyAppendCharsetParameterToFN) {
                this.mBuilder.append(";");
                this.mBuilder.append(this.mVCardCharsetParameter);
            }
            if (reallyUseQuotedPrintableToFN) {
                this.mBuilder.append(";");
                this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
            }
            this.mBuilder.append(VCARD_DATA_SEPARATOR);
            this.mBuilder.append(encodedFormattedname);
            this.mBuilder.append("\r\n");
        } else if (!TextUtils.isEmpty(displayName)) {
            buildSinglePartNameField("N", displayName);
            this.mBuilder.append(";");
            this.mBuilder.append(";");
            this.mBuilder.append(";");
            this.mBuilder.append(";");
            this.mBuilder.append("\r\n");
            buildSinglePartNameField("FN", displayName);
            this.mBuilder.append("\r\n");
        } else if (VCardConfig.isVersion30(this.mVCardType)) {
            appendLine("N", "");
            appendLine("FN", "");
        } else if (this.mIsDoCoMo) {
            appendLine("N", "");
        }
        appendPhoneticNameFields(contentValues);
        return this;
    }

    private void buildSinglePartNameField(String property, String part) {
        String encodedPart;
        boolean reallyUseQuotedPrintable = (this.mRefrainsQPToNameProperties || VCardUtils.containsOnlyNonCrLfPrintableAscii(part)) ? false : true;
        if (reallyUseQuotedPrintable) {
            encodedPart = encodeQuotedPrintable(part);
        } else {
            encodedPart = escapeCharacters(part);
        }
        this.mBuilder.append(property);
        if (shouldAppendCharsetParam(part)) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (reallyUseQuotedPrintable) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(encodedPart);
    }

    private void appendPhoneticNameFields(ContentValues contentValues) {
        String phoneticFamilyName;
        String phoneticMiddleName;
        String phoneticGivenName;
        String encodedPhoneticFamilyName;
        String encodedPhoneticMiddleName;
        String encodedPhoneticGivenName;
        String encodedPhoneticFamilyName2;
        String encodedPhoneticMiddleName2;
        String encodedPhoneticGivenName2;
        String tmpPhoneticFamilyName = contentValues.getAsString("data9");
        String tmpPhoneticMiddleName = contentValues.getAsString("data8");
        String tmpPhoneticGivenName = contentValues.getAsString("data7");
        if (this.mNeedsToConvertPhoneticString) {
            phoneticFamilyName = VCardUtils.toHalfWidthString(tmpPhoneticFamilyName);
            phoneticMiddleName = VCardUtils.toHalfWidthString(tmpPhoneticMiddleName);
            phoneticGivenName = VCardUtils.toHalfWidthString(tmpPhoneticGivenName);
        } else {
            phoneticFamilyName = tmpPhoneticFamilyName;
            phoneticMiddleName = tmpPhoneticMiddleName;
            phoneticGivenName = tmpPhoneticGivenName;
        }
        if (TextUtils.isEmpty(phoneticFamilyName) && TextUtils.isEmpty(phoneticMiddleName) && TextUtils.isEmpty(phoneticGivenName)) {
            if (this.mIsDoCoMo) {
                this.mBuilder.append("SOUND");
                this.mBuilder.append(";");
                this.mBuilder.append("X-IRMC-N");
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append("\r\n");
                return;
            }
            return;
        }
        if (!VCardConfig.isVersion40(this.mVCardType)) {
            if (VCardConfig.isVersion30(this.mVCardType)) {
                String sortString = VCardUtils.constructNameFromElements(this.mVCardType, phoneticFamilyName, phoneticMiddleName, phoneticGivenName);
                this.mBuilder.append("SORT-STRING");
                if (VCardConfig.isVersion30(this.mVCardType) && shouldAppendCharsetParam(sortString)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(escapeCharacters(sortString));
                this.mBuilder.append("\r\n");
            } else if (this.mIsJapaneseMobilePhone) {
                this.mBuilder.append("SOUND");
                this.mBuilder.append(";");
                this.mBuilder.append("X-IRMC-N");
                if ((this.mRefrainsQPToNameProperties || (VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticFamilyName) && VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticMiddleName) && VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticGivenName))) ? false : true) {
                    encodedPhoneticFamilyName2 = encodeQuotedPrintable(phoneticFamilyName);
                    encodedPhoneticMiddleName2 = encodeQuotedPrintable(phoneticMiddleName);
                    encodedPhoneticGivenName2 = encodeQuotedPrintable(phoneticGivenName);
                } else {
                    encodedPhoneticFamilyName2 = escapeCharacters(phoneticFamilyName);
                    encodedPhoneticMiddleName2 = escapeCharacters(phoneticMiddleName);
                    encodedPhoneticGivenName2 = escapeCharacters(phoneticGivenName);
                }
                if (shouldAppendCharsetParam(encodedPhoneticFamilyName2, encodedPhoneticMiddleName2, encodedPhoneticGivenName2)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                boolean first = true;
                if (!TextUtils.isEmpty(encodedPhoneticFamilyName2)) {
                    this.mBuilder.append(encodedPhoneticFamilyName2);
                    first = false;
                }
                if (!TextUtils.isEmpty(encodedPhoneticMiddleName2)) {
                    if (first) {
                        first = false;
                    } else {
                        this.mBuilder.append(' ');
                    }
                    this.mBuilder.append(encodedPhoneticMiddleName2);
                }
                if (!TextUtils.isEmpty(encodedPhoneticGivenName2)) {
                    if (!first) {
                        this.mBuilder.append(' ');
                    }
                    this.mBuilder.append(encodedPhoneticGivenName2);
                }
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append("\r\n");
            }
        }
        if (this.mUsesDefactProperty) {
            if (!TextUtils.isEmpty(phoneticGivenName)) {
                boolean reallyUseQuotedPrintable = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticGivenName);
                if (reallyUseQuotedPrintable) {
                    encodedPhoneticGivenName = encodeQuotedPrintable(phoneticGivenName);
                } else {
                    encodedPhoneticGivenName = escapeCharacters(phoneticGivenName);
                }
                this.mBuilder.append("X-PHONETIC-FIRST-NAME");
                if (shouldAppendCharsetParam(phoneticGivenName)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (reallyUseQuotedPrintable) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(encodedPhoneticGivenName);
                this.mBuilder.append("\r\n");
            }
            if (!TextUtils.isEmpty(phoneticMiddleName)) {
                boolean reallyUseQuotedPrintable2 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticMiddleName);
                if (reallyUseQuotedPrintable2) {
                    encodedPhoneticMiddleName = encodeQuotedPrintable(phoneticMiddleName);
                } else {
                    encodedPhoneticMiddleName = escapeCharacters(phoneticMiddleName);
                }
                this.mBuilder.append("X-PHONETIC-MIDDLE-NAME");
                if (shouldAppendCharsetParam(phoneticMiddleName)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (reallyUseQuotedPrintable2) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(encodedPhoneticMiddleName);
                this.mBuilder.append("\r\n");
            }
            if (!TextUtils.isEmpty(phoneticFamilyName)) {
                boolean reallyUseQuotedPrintable3 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticFamilyName);
                if (reallyUseQuotedPrintable3) {
                    encodedPhoneticFamilyName = encodeQuotedPrintable(phoneticFamilyName);
                } else {
                    encodedPhoneticFamilyName = escapeCharacters(phoneticFamilyName);
                }
                this.mBuilder.append("X-PHONETIC-LAST-NAME");
                if (shouldAppendCharsetParam(phoneticFamilyName)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (reallyUseQuotedPrintable3) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(encodedPhoneticFamilyName);
                this.mBuilder.append("\r\n");
            }
        }
    }

    public VCardBuilder appendNickNames(List<ContentValues> contentValuesList) {
        boolean useAndroidProperty;
        if (this.mIsV30OrV40) {
            useAndroidProperty = false;
        } else {
            useAndroidProperty = this.mUsesAndroidProperty ? true : true;
            return this;
        }
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                String nickname = contentValues.getAsString("data1");
                if (!TextUtils.isEmpty(nickname)) {
                    if (useAndroidProperty) {
                        appendAndroidSpecificProperty("vnd.android.cursor.item/nickname", contentValues);
                    } else {
                        appendLineWithCharsetAndQPDetection("NICKNAME", nickname);
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendPhones(List<ContentValues> contentValuesList, VCardPhoneNumberTranslationCallback translationCallback) {
        boolean isPrimary;
        String formatted;
        boolean phoneLineExists = false;
        if (contentValuesList != null) {
            Set<String> phoneSet = new HashSet<>();
            for (ContentValues contentValues : contentValuesList) {
                Integer typeAsObject = contentValues.getAsInteger("data2");
                String label = contentValues.getAsString("data3");
                Integer isPrimaryAsInteger = contentValues.getAsInteger("is_primary");
                if (isPrimaryAsInteger != null) {
                    isPrimary = isPrimaryAsInteger.intValue() > 0;
                } else {
                    isPrimary = false;
                }
                String phoneNumber = contentValues.getAsString("data1");
                if (phoneNumber != null) {
                    phoneNumber = phoneNumber.trim();
                }
                if (!TextUtils.isEmpty(phoneNumber)) {
                    int type = typeAsObject != null ? typeAsObject.intValue() : 1;
                    if (translationCallback != null) {
                        String phoneNumber2 = translationCallback.onValueReceived(phoneNumber, type, label, isPrimary);
                        if (!phoneSet.contains(phoneNumber2)) {
                            phoneSet.add(phoneNumber2);
                            appendTelLine(Integer.valueOf(type), label, phoneNumber2, isPrimary);
                        }
                    } else if (type == 6 || VCardConfig.refrainPhoneNumberFormatting(this.mVCardType)) {
                        phoneLineExists = true;
                        if (!phoneSet.contains(phoneNumber)) {
                            phoneSet.add(phoneNumber);
                            appendTelLine(Integer.valueOf(type), label, phoneNumber, isPrimary);
                        }
                    } else {
                        List<String> phoneNumberList = splitPhoneNumbers(phoneNumber);
                        if (!phoneNumberList.isEmpty()) {
                            phoneLineExists = true;
                            for (String actualPhoneNumber : phoneNumberList) {
                                if (!phoneSet.contains(actualPhoneNumber)) {
                                    String numberWithControlSequence = actualPhoneNumber.replace(',', 'p').replace(';', 'w');
                                    if (TextUtils.equals(numberWithControlSequence, actualPhoneNumber)) {
                                        StringBuilder digitsOnlyBuilder = new StringBuilder();
                                        int length = actualPhoneNumber.length();
                                        for (int i = 0; i < length; i++) {
                                            char ch = actualPhoneNumber.charAt(i);
                                            if (Character.isDigit(ch) || ch == '+') {
                                                digitsOnlyBuilder.append(ch);
                                            }
                                        }
                                        int phoneFormat = VCardUtils.getPhoneNumberFormat(this.mVCardType);
                                        formatted = VCardUtils.PhoneNumberUtilsPort.formatNumber(digitsOnlyBuilder.toString(), phoneFormat);
                                    } else {
                                        formatted = numberWithControlSequence;
                                    }
                                    if (VCardConfig.isVersion40(this.mVCardType) && !TextUtils.isEmpty(formatted) && !formatted.startsWith("tel:")) {
                                        formatted = "tel:" + formatted;
                                    }
                                    phoneSet.add(actualPhoneNumber);
                                    appendTelLine(Integer.valueOf(type), label, formatted, isPrimary);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!phoneLineExists && this.mIsDoCoMo) {
            appendTelLine(1, "", "", false);
        }
        return this;
    }

    private List<String> splitPhoneNumbers(String phoneNumber) {
        List<String> phoneList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        int length = phoneNumber.length();
        for (int i = 0; i < length; i++) {
            char ch = phoneNumber.charAt(i);
            if (ch == '\n' && builder.length() > 0) {
                phoneList.add(builder.toString());
                builder = new StringBuilder();
            } else {
                builder.append(ch);
            }
        }
        if (builder.length() > 0) {
            phoneList.add(builder.toString());
        }
        return phoneList;
    }

    public VCardBuilder appendEmails(List<ContentValues> contentValuesList) {
        boolean isPrimary;
        boolean emailAddressExists = false;
        if (contentValuesList != null) {
            Set<String> addressSet = new HashSet<>();
            for (ContentValues contentValues : contentValuesList) {
                String emailAddress = contentValues.getAsString("data1");
                if (emailAddress != null) {
                    emailAddress = emailAddress.trim();
                }
                if (!TextUtils.isEmpty(emailAddress)) {
                    Integer typeAsObject = contentValues.getAsInteger("data2");
                    int type = typeAsObject != null ? typeAsObject.intValue() : 3;
                    String label = contentValues.getAsString("data3");
                    Integer isPrimaryAsInteger = contentValues.getAsInteger("is_primary");
                    if (isPrimaryAsInteger != null) {
                        isPrimary = isPrimaryAsInteger.intValue() > 0;
                    } else {
                        isPrimary = false;
                    }
                    emailAddressExists = true;
                    if (!addressSet.contains(emailAddress)) {
                        addressSet.add(emailAddress);
                        appendEmailLine(type, label, emailAddress, isPrimary);
                    }
                }
            }
        }
        if (!emailAddressExists && this.mIsDoCoMo) {
            appendEmailLine(1, "", "", false);
        }
        return this;
    }

    public VCardBuilder appendPostals(List<ContentValues> contentValuesList) {
        if (contentValuesList == null || contentValuesList.isEmpty()) {
            if (this.mIsDoCoMo) {
                this.mBuilder.append("ADR");
                this.mBuilder.append(";");
                this.mBuilder.append("HOME");
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append("\r\n");
            }
        } else if (this.mIsDoCoMo) {
            appendPostalsForDoCoMo(contentValuesList);
        } else {
            appendPostalsForGeneric(contentValuesList);
        }
        return this;
    }

    private void appendPostalsForDoCoMo(List<ContentValues> contentValuesList) {
        int currentPriority = Integer.MAX_VALUE;
        int currentType = Integer.MAX_VALUE;
        ContentValues currentContentValues = null;
        for (ContentValues contentValues : contentValuesList) {
            if (contentValues != null) {
                Integer typeAsInteger = contentValues.getAsInteger("data2");
                Integer priorityAsInteger = sPostalTypePriorityMap.get(typeAsInteger);
                int priority = priorityAsInteger != null ? priorityAsInteger.intValue() : Integer.MAX_VALUE;
                if (priority < currentPriority) {
                    currentPriority = priority;
                    currentType = typeAsInteger.intValue();
                    currentContentValues = contentValues;
                    if (priority == 0) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        if (currentContentValues == null) {
            Log.w(LOG_TAG, "Should not come here. Must have at least one postal data.");
            return;
        }
        String label = currentContentValues.getAsString("data3");
        appendPostalLine(currentType, label, currentContentValues, false, true);
    }

    private void appendPostalsForGeneric(List<ContentValues> contentValuesList) {
        boolean isPrimary;
        for (ContentValues contentValues : contentValuesList) {
            if (contentValues != null) {
                Integer typeAsInteger = contentValues.getAsInteger("data2");
                int type = typeAsInteger != null ? typeAsInteger.intValue() : 1;
                String label = contentValues.getAsString("data3");
                Integer isPrimaryAsInteger = contentValues.getAsInteger("is_primary");
                if (isPrimaryAsInteger != null) {
                    isPrimary = isPrimaryAsInteger.intValue() > 0;
                } else {
                    isPrimary = false;
                }
                appendPostalLine(type, label, contentValues, isPrimary, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PostalStruct {
        final String addressData;
        final boolean appendCharset;
        final boolean reallyUseQuotedPrintable;

        public PostalStruct(boolean reallyUseQuotedPrintable, boolean appendCharset, String addressData) {
            this.reallyUseQuotedPrintable = reallyUseQuotedPrintable;
            this.appendCharset = appendCharset;
            this.addressData = addressData;
        }
    }

    private PostalStruct tryConstructPostalStruct(ContentValues contentValues) {
        String encodedFormattedAddress;
        String rawLocality2;
        String encodedPoBox;
        String encodedStreet;
        String encodedLocality;
        String encodedRegion;
        String encodedPostalCode;
        String encodedCountry;
        String rawPoBox = contentValues.getAsString("data5");
        String rawNeighborhood = contentValues.getAsString("data6");
        String rawStreet = contentValues.getAsString("data4");
        String rawLocality = contentValues.getAsString("data7");
        String rawRegion = contentValues.getAsString("data8");
        String rawPostalCode = contentValues.getAsString("data9");
        String rawCountry = contentValues.getAsString("data10");
        String[] rawAddressArray = {rawPoBox, rawNeighborhood, rawStreet, rawLocality, rawRegion, rawPostalCode, rawCountry};
        if (!VCardUtils.areAllEmpty(rawAddressArray)) {
            boolean reallyUseQuotedPrintable = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawAddressArray);
            boolean appendCharset = !VCardUtils.containsOnlyPrintableAscii(rawAddressArray);
            if (TextUtils.isEmpty(rawLocality)) {
                if (TextUtils.isEmpty(rawNeighborhood)) {
                    rawLocality2 = "";
                } else {
                    rawLocality2 = rawNeighborhood;
                }
            } else if (TextUtils.isEmpty(rawNeighborhood)) {
                rawLocality2 = rawLocality;
            } else {
                rawLocality2 = String.valueOf(rawLocality) + VCARD_WS + rawNeighborhood;
            }
            if (reallyUseQuotedPrintable) {
                encodedPoBox = encodeQuotedPrintable(rawPoBox);
                encodedStreet = encodeQuotedPrintable(rawStreet);
                encodedLocality = encodeQuotedPrintable(rawLocality2);
                encodedRegion = encodeQuotedPrintable(rawRegion);
                encodedPostalCode = encodeQuotedPrintable(rawPostalCode);
                encodedCountry = encodeQuotedPrintable(rawCountry);
            } else {
                encodedPoBox = escapeCharacters(rawPoBox);
                encodedStreet = escapeCharacters(rawStreet);
                encodedLocality = escapeCharacters(rawLocality2);
                encodedRegion = escapeCharacters(rawRegion);
                encodedPostalCode = escapeCharacters(rawPostalCode);
                encodedCountry = escapeCharacters(rawCountry);
                escapeCharacters(rawNeighborhood);
            }
            return new PostalStruct(reallyUseQuotedPrintable, appendCharset, encodedPoBox + ";;" + encodedStreet + ";" + encodedLocality + ";" + encodedRegion + ";" + encodedPostalCode + ";" + encodedCountry);
        }
        String rawFormattedAddress = contentValues.getAsString("data1");
        if (TextUtils.isEmpty(rawFormattedAddress)) {
            return null;
        }
        boolean reallyUseQuotedPrintable2 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawFormattedAddress);
        boolean appendCharset2 = !VCardUtils.containsOnlyPrintableAscii(rawFormattedAddress);
        if (reallyUseQuotedPrintable2) {
            encodedFormattedAddress = encodeQuotedPrintable(rawFormattedAddress);
        } else {
            encodedFormattedAddress = escapeCharacters(rawFormattedAddress);
        }
        return new PostalStruct(reallyUseQuotedPrintable2, appendCharset2, ";" + encodedFormattedAddress + ";;;;;");
    }

    public VCardBuilder appendIms(List<ContentValues> contentValuesList) {
        String propertyName;
        String typeAsString;
        boolean isPrimary;
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                Integer protocolAsObject = contentValues.getAsInteger("data5");
                if (protocolAsObject != null && (propertyName = VCardUtils.getPropertyNameForIm(protocolAsObject.intValue())) != null) {
                    String data = contentValues.getAsString("data1");
                    if (data != null) {
                        data = data.trim();
                    }
                    if (!TextUtils.isEmpty(data)) {
                        Integer typeAsInteger = contentValues.getAsInteger("data2");
                        switch (typeAsInteger != null ? typeAsInteger.intValue() : 3) {
                            case 0:
                                String label = contentValues.getAsString("data3");
                                if (label != null) {
                                    typeAsString = "X-" + label;
                                    break;
                                } else {
                                    typeAsString = null;
                                    break;
                                }
                            case 1:
                                typeAsString = "HOME";
                                break;
                            case 2:
                                typeAsString = "WORK";
                                break;
                            default:
                                typeAsString = null;
                                break;
                        }
                        List<String> parameterList = new ArrayList<>();
                        if (!TextUtils.isEmpty(typeAsString)) {
                            parameterList.add(typeAsString);
                        }
                        Integer isPrimaryAsInteger = contentValues.getAsInteger("is_primary");
                        if (isPrimaryAsInteger != null) {
                            isPrimary = isPrimaryAsInteger.intValue() > 0;
                        } else {
                            isPrimary = false;
                        }
                        if (isPrimary) {
                            parameterList.add("PREF");
                        }
                        appendLineWithCharsetAndQPDetection(propertyName, parameterList, data);
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendWebsites(List<ContentValues> contentValuesList) {
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                String website = contentValues.getAsString("data1");
                if (website != null) {
                    website = website.trim();
                }
                if (!TextUtils.isEmpty(website)) {
                    appendLineWithCharsetAndQPDetection("URL", website);
                }
            }
        }
        return this;
    }

    public VCardBuilder appendOrganizations(List<ContentValues> contentValuesList) {
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                String company = contentValues.getAsString("data1");
                if (company != null) {
                    company = company.trim();
                }
                String department = contentValues.getAsString("data5");
                if (department != null) {
                    department = department.trim();
                }
                String title = contentValues.getAsString("data4");
                if (title != null) {
                    title = title.trim();
                }
                StringBuilder orgBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(company)) {
                    orgBuilder.append(company);
                }
                if (!TextUtils.isEmpty(department)) {
                    if (orgBuilder.length() > 0) {
                        orgBuilder.append(';');
                    }
                    orgBuilder.append(department);
                }
                String orgline = orgBuilder.toString();
                appendLine("ORG", orgline, !VCardUtils.containsOnlyPrintableAscii(orgline), this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(orgline));
                if (!TextUtils.isEmpty(title)) {
                    appendLine("TITLE", title, !VCardUtils.containsOnlyPrintableAscii(title), this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(title));
                }
            }
        }
        return this;
    }

    public VCardBuilder appendPhotos(List<ContentValues> contentValuesList) {
        byte[] data;
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                if (contentValues != null && (data = contentValues.getAsByteArray("data15")) != null) {
                    String photoType = VCardUtils.guessImageType(data);
                    if (photoType == null) {
                        Log.d(LOG_TAG, "Unknown photo type. Ignored.");
                    } else {
                        String photoString = new String(Base64.encode(data, 2));
                        if (!TextUtils.isEmpty(photoString)) {
                            appendPhotoLine(photoString, photoType);
                        }
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendNotes(List<ContentValues> contentValuesList) {
        if (contentValuesList != null) {
            if (this.mOnlyOneNoteFieldIsAvailable) {
                StringBuilder noteBuilder = new StringBuilder();
                boolean first = true;
                for (ContentValues contentValues : contentValuesList) {
                    String note = contentValues.getAsString("data1");
                    if (note == null) {
                        note = "";
                    }
                    if (note.length() > 0) {
                        if (first) {
                            first = false;
                        } else {
                            noteBuilder.append('\n');
                        }
                        noteBuilder.append(note);
                    }
                }
                String noteStr = noteBuilder.toString();
                boolean shouldAppendCharsetInfo = !VCardUtils.containsOnlyPrintableAscii(noteStr);
                boolean reallyUseQuotedPrintable = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(noteStr);
                appendLine("NOTE", noteStr, shouldAppendCharsetInfo, reallyUseQuotedPrintable);
            } else {
                for (ContentValues contentValues2 : contentValuesList) {
                    String noteStr2 = contentValues2.getAsString("data1");
                    if (!TextUtils.isEmpty(noteStr2)) {
                        boolean shouldAppendCharsetInfo2 = !VCardUtils.containsOnlyPrintableAscii(noteStr2);
                        boolean reallyUseQuotedPrintable2 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(noteStr2);
                        appendLine("NOTE", noteStr2, shouldAppendCharsetInfo2, reallyUseQuotedPrintable2);
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendEvents(List<ContentValues> contentValuesList) {
        int eventType;
        boolean isSuperPrimary;
        boolean isPrimary;
        if (contentValuesList != null) {
            String primaryBirthday = null;
            String secondaryBirthday = null;
            Iterator<ContentValues> it = contentValuesList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ContentValues contentValues = it.next();
                if (contentValues != null) {
                    Integer eventTypeAsInteger = contentValues.getAsInteger("data2");
                    if (eventTypeAsInteger != null) {
                        eventType = eventTypeAsInteger.intValue();
                    } else {
                        eventType = 2;
                    }
                    if (eventType == 3) {
                        String birthdayCandidate = contentValues.getAsString("data1");
                        if (birthdayCandidate == null) {
                            continue;
                        } else {
                            Integer isSuperPrimaryAsInteger = contentValues.getAsInteger("is_super_primary");
                            if (isSuperPrimaryAsInteger != null) {
                                isSuperPrimary = isSuperPrimaryAsInteger.intValue() > 0;
                            } else {
                                isSuperPrimary = false;
                            }
                            if (isSuperPrimary) {
                                primaryBirthday = birthdayCandidate;
                                break;
                            }
                            Integer isPrimaryAsInteger = contentValues.getAsInteger("is_primary");
                            if (isPrimaryAsInteger != null) {
                                isPrimary = isPrimaryAsInteger.intValue() > 0;
                            } else {
                                isPrimary = false;
                            }
                            if (isPrimary) {
                                primaryBirthday = birthdayCandidate;
                            } else if (secondaryBirthday == null) {
                                secondaryBirthday = birthdayCandidate;
                            }
                        }
                    } else if (this.mUsesAndroidProperty) {
                        appendAndroidSpecificProperty("vnd.android.cursor.item/contact_event", contentValues);
                    }
                }
            }
            if (primaryBirthday != null) {
                appendLineWithCharsetAndQPDetection("BDAY", primaryBirthday.trim());
            } else if (secondaryBirthday != null) {
                appendLineWithCharsetAndQPDetection("BDAY", secondaryBirthday.trim());
            }
        }
        return this;
    }

    public VCardBuilder appendRelation(List<ContentValues> contentValuesList) {
        if (this.mUsesAndroidProperty && contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                if (contentValues != null) {
                    appendAndroidSpecificProperty("vnd.android.cursor.item/relation", contentValues);
                }
            }
        }
        return this;
    }

    public void appendPostalLine(int type, String label, ContentValues contentValues, boolean isPrimary, boolean emitEveryTime) {
        boolean reallyUseQuotedPrintable;
        boolean appendCharset;
        String addressValue;
        PostalStruct postalStruct = tryConstructPostalStruct(contentValues);
        if (postalStruct == null) {
            if (emitEveryTime) {
                reallyUseQuotedPrintable = false;
                appendCharset = false;
                addressValue = "";
            } else {
                return;
            }
        } else {
            reallyUseQuotedPrintable = postalStruct.reallyUseQuotedPrintable;
            appendCharset = postalStruct.appendCharset;
            addressValue = postalStruct.addressData;
        }
        List<String> parameterList = new ArrayList<>();
        if (isPrimary) {
            parameterList.add("PREF");
        }
        switch (type) {
            case 0:
                if (!TextUtils.isEmpty(label) && VCardUtils.containsOnlyAlphaDigitHyphen(label)) {
                    parameterList.add("X-" + label);
                    break;
                }
                break;
            case 1:
                parameterList.add("HOME");
                break;
            case 2:
                parameterList.add("WORK");
                break;
            case 3:
                break;
            default:
                Log.e(LOG_TAG, "Unknown StructuredPostal type: " + type);
                break;
        }
        this.mBuilder.append("ADR");
        if (!parameterList.isEmpty()) {
            this.mBuilder.append(";");
            appendTypeParameters(parameterList);
        }
        if (appendCharset) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (reallyUseQuotedPrintable) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(addressValue);
        this.mBuilder.append("\r\n");
    }

    public void appendEmailLine(int type, String label, String rawValue, boolean isPrimary) {
        String typeAsString;
        switch (type) {
            case 0:
                if (!VCardUtils.isMobilePhoneLabel(label)) {
                    if (!TextUtils.isEmpty(label) && VCardUtils.containsOnlyAlphaDigitHyphen(label)) {
                        typeAsString = "X-" + label;
                        break;
                    } else {
                        typeAsString = null;
                        break;
                    }
                } else {
                    typeAsString = "CELL";
                    break;
                }
                break;
            case 1:
                typeAsString = "HOME";
                break;
            case 2:
                typeAsString = "WORK";
                break;
            case 3:
                typeAsString = null;
                break;
            case 4:
                typeAsString = "CELL";
                break;
            default:
                Log.e(LOG_TAG, "Unknown Email type: " + type);
                typeAsString = null;
                break;
        }
        List<String> parameterList = new ArrayList<>();
        if (isPrimary) {
            parameterList.add("PREF");
        }
        if (!TextUtils.isEmpty(typeAsString)) {
            parameterList.add(typeAsString);
        }
        appendLineWithCharsetAndQPDetection("EMAIL", parameterList, rawValue);
    }

    public void appendTelLine(Integer typeAsInteger, String label, String encodedValue, boolean isPrimary) {
        int type;
        this.mBuilder.append("TEL");
        this.mBuilder.append(";");
        if (typeAsInteger == null) {
            type = 7;
        } else {
            type = typeAsInteger.intValue();
        }
        ArrayList<String> parameterList = new ArrayList<>();
        switch (type) {
            case 0:
                if (TextUtils.isEmpty(label)) {
                    parameterList.add("VOICE");
                    break;
                } else if (VCardUtils.isMobilePhoneLabel(label)) {
                    parameterList.add("CELL");
                    break;
                } else if (this.mIsV30OrV40) {
                    parameterList.add(label);
                    break;
                } else {
                    String upperLabel = label.toUpperCase();
                    if (!VCardUtils.isValidInV21ButUnknownToContactsPhoteType(upperLabel)) {
                        if (VCardUtils.containsOnlyAlphaDigitHyphen(label)) {
                            parameterList.add("X-" + label);
                            break;
                        }
                    } else {
                        parameterList.add(upperLabel);
                        break;
                    }
                }
                break;
            case 1:
                parameterList.addAll(Arrays.asList("HOME"));
                break;
            case 2:
                parameterList.add("CELL");
                break;
            case 3:
                parameterList.addAll(Arrays.asList("WORK"));
                break;
            case 4:
                parameterList.addAll(Arrays.asList("WORK", "FAX"));
                break;
            case 5:
                parameterList.addAll(Arrays.asList("HOME", "FAX"));
                break;
            case 6:
                if (this.mIsDoCoMo) {
                    parameterList.add("VOICE");
                    break;
                } else {
                    parameterList.add("PAGER");
                    break;
                }
            case 7:
                parameterList.add("VOICE");
                break;
            case 9:
                parameterList.add("CAR");
                break;
            case 10:
                parameterList.add("WORK");
                isPrimary = true;
                break;
            case 11:
                parameterList.add("ISDN");
                break;
            case 12:
                isPrimary = true;
                break;
            case 13:
                parameterList.add("FAX");
                break;
            case 15:
                parameterList.add("TLX");
                break;
            case 17:
                parameterList.addAll(Arrays.asList("WORK", "CELL"));
                break;
            case 18:
                parameterList.add("WORK");
                if (this.mIsDoCoMo) {
                    parameterList.add("VOICE");
                    break;
                } else {
                    parameterList.add("PAGER");
                    break;
                }
            case 20:
                parameterList.add("MSG");
                break;
        }
        if (isPrimary) {
            parameterList.add("PREF");
        }
        if (parameterList.isEmpty()) {
            appendUncommonPhoneType(this.mBuilder, Integer.valueOf(type));
        } else {
            appendTypeParameters(parameterList);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(encodedValue);
        this.mBuilder.append("\r\n");
    }

    private void appendUncommonPhoneType(StringBuilder builder, Integer type) {
        if (this.mIsDoCoMo) {
            builder.append("VOICE");
            return;
        }
        String phoneType = VCardUtils.getPhoneTypeString(type);
        if (phoneType != null) {
            appendTypeParameter(phoneType);
        } else {
            Log.e(LOG_TAG, "Unknown or unsupported (by vCard) Phone type: " + type);
        }
    }

    public void appendPhotoLine(String encodedValue, String photoType) {
        StringBuilder tmpBuilder = new StringBuilder();
        tmpBuilder.append("PHOTO");
        tmpBuilder.append(";");
        if (this.mIsV30OrV40) {
            tmpBuilder.append(VCARD_PARAM_ENCODING_BASE64_AS_B);
        } else {
            tmpBuilder.append(VCARD_PARAM_ENCODING_BASE64_V21);
        }
        tmpBuilder.append(";");
        appendTypeParameter(tmpBuilder, photoType);
        tmpBuilder.append(VCARD_DATA_SEPARATOR);
        tmpBuilder.append(encodedValue);
        String tmpStr = tmpBuilder.toString();
        StringBuilder tmpBuilder2 = new StringBuilder();
        int lineCount = 0;
        int length = tmpStr.length();
        int maxNumForFirstLine = 75 - "\r\n".length();
        int maxNumInGeneral = maxNumForFirstLine - VCARD_WS.length();
        int maxNum = maxNumForFirstLine;
        for (int i = 0; i < length; i++) {
            tmpBuilder2.append(tmpStr.charAt(i));
            lineCount++;
            if (lineCount > maxNum) {
                tmpBuilder2.append("\r\n");
                tmpBuilder2.append(VCARD_WS);
                maxNum = maxNumInGeneral;
                lineCount = 0;
            }
        }
        this.mBuilder.append(tmpBuilder2.toString());
        this.mBuilder.append("\r\n");
        this.mBuilder.append("\r\n");
    }

    public VCardBuilder appendSipAddresses(List<ContentValues> contentValuesList) {
        boolean useXProperty;
        String propertyName;
        if (this.mIsV30OrV40) {
            useXProperty = false;
        } else {
            useXProperty = this.mUsesDefactProperty ? true : true;
            return this;
        }
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                String sipAddress = contentValues.getAsString("data1");
                if (!TextUtils.isEmpty(sipAddress)) {
                    if (useXProperty) {
                        if (sipAddress.startsWith("sip:")) {
                            if (sipAddress.length() != 4) {
                                sipAddress = sipAddress.substring(4);
                            }
                        }
                        appendLineWithCharsetAndQPDetection("X-SIP", sipAddress);
                    } else {
                        if (!sipAddress.startsWith("sip:")) {
                            sipAddress = "sip:" + sipAddress;
                        }
                        if (VCardConfig.isVersion40(this.mVCardType)) {
                            propertyName = "TEL";
                        } else {
                            propertyName = "IMPP";
                        }
                        appendLineWithCharsetAndQPDetection(propertyName, sipAddress);
                    }
                }
            }
        }
        return this;
    }

    public void appendAndroidSpecificProperty(String mimeType, ContentValues contentValues) {
        String encodedValue;
        if (sAllowedAndroidPropertySet.contains(mimeType)) {
            List<String> rawValueList = new ArrayList<>();
            for (int i = 1; i <= 15; i++) {
                String value = contentValues.getAsString("data" + i);
                if (value == null) {
                    value = "";
                }
                rawValueList.add(value);
            }
            boolean needCharset = this.mShouldAppendCharsetParam && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawValueList);
            boolean reallyUseQuotedPrintable = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawValueList);
            this.mBuilder.append("X-ANDROID-CUSTOM");
            if (needCharset) {
                this.mBuilder.append(";");
                this.mBuilder.append(this.mVCardCharsetParameter);
            }
            if (reallyUseQuotedPrintable) {
                this.mBuilder.append(";");
                this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
            }
            this.mBuilder.append(VCARD_DATA_SEPARATOR);
            this.mBuilder.append(mimeType);
            for (String rawValue : rawValueList) {
                if (reallyUseQuotedPrintable) {
                    encodedValue = encodeQuotedPrintable(rawValue);
                } else {
                    encodedValue = escapeCharacters(rawValue);
                }
                this.mBuilder.append(";");
                this.mBuilder.append(encodedValue);
            }
            this.mBuilder.append("\r\n");
        }
    }

    public void appendLineWithCharsetAndQPDetection(String propertyName, String rawValue) {
        appendLineWithCharsetAndQPDetection(propertyName, (List<String>) null, rawValue);
    }

    public void appendLineWithCharsetAndQPDetection(String propertyName, List<String> rawValueList) {
        appendLineWithCharsetAndQPDetection(propertyName, (List<String>) null, rawValueList);
    }

    public void appendLineWithCharsetAndQPDetection(String propertyName, List<String> parameterList, String rawValue) {
        boolean needCharset = !VCardUtils.containsOnlyPrintableAscii(rawValue);
        boolean reallyUseQuotedPrintable = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawValue);
        appendLine(propertyName, parameterList, rawValue, needCharset, reallyUseQuotedPrintable);
    }

    public void appendLineWithCharsetAndQPDetection(String propertyName, List<String> parameterList, List<String> rawValueList) {
        boolean needCharset = this.mShouldAppendCharsetParam && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawValueList);
        boolean reallyUseQuotedPrintable = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawValueList);
        appendLine(propertyName, parameterList, rawValueList, needCharset, reallyUseQuotedPrintable);
    }

    public void appendLine(String propertyName, String rawValue) {
        appendLine(propertyName, rawValue, false, false);
    }

    public void appendLine(String propertyName, List<String> rawValueList) {
        appendLine(propertyName, rawValueList, false, false);
    }

    public void appendLine(String propertyName, String rawValue, boolean needCharset, boolean reallyUseQuotedPrintable) {
        appendLine(propertyName, (List<String>) null, rawValue, needCharset, reallyUseQuotedPrintable);
    }

    public void appendLine(String propertyName, List<String> parameterList, String rawValue) {
        appendLine(propertyName, parameterList, rawValue, false, false);
    }

    public void appendLine(String propertyName, List<String> parameterList, String rawValue, boolean needCharset, boolean reallyUseQuotedPrintable) {
        String encodedValue;
        this.mBuilder.append(propertyName);
        if (parameterList != null && parameterList.size() > 0) {
            this.mBuilder.append(";");
            appendTypeParameters(parameterList);
        }
        if (needCharset) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (reallyUseQuotedPrintable) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
            encodedValue = encodeQuotedPrintable(rawValue);
        } else {
            encodedValue = escapeCharacters(rawValue);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(encodedValue);
        this.mBuilder.append("\r\n");
    }

    public void appendLine(String propertyName, List<String> rawValueList, boolean needCharset, boolean needQuotedPrintable) {
        appendLine(propertyName, (List<String>) null, rawValueList, needCharset, needQuotedPrintable);
    }

    public void appendLine(String propertyName, List<String> parameterList, List<String> rawValueList, boolean needCharset, boolean needQuotedPrintable) {
        String encodedValue;
        this.mBuilder.append(propertyName);
        if (parameterList != null && parameterList.size() > 0) {
            this.mBuilder.append(";");
            appendTypeParameters(parameterList);
        }
        if (needCharset) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (needQuotedPrintable) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        boolean first = true;
        for (String rawValue : rawValueList) {
            if (needQuotedPrintable) {
                encodedValue = encodeQuotedPrintable(rawValue);
            } else {
                encodedValue = escapeCharacters(rawValue);
            }
            if (first) {
                first = false;
            } else {
                this.mBuilder.append(";");
            }
            this.mBuilder.append(encodedValue);
        }
        this.mBuilder.append("\r\n");
    }

    private void appendTypeParameters(List<String> types) {
        String encoded;
        boolean first = true;
        for (String typeValue : types) {
            if (VCardConfig.isVersion30(this.mVCardType) || VCardConfig.isVersion40(this.mVCardType)) {
                if (VCardConfig.isVersion40(this.mVCardType)) {
                    encoded = VCardUtils.toStringAsV40ParamValue(typeValue);
                } else {
                    encoded = VCardUtils.toStringAsV30ParamValue(typeValue);
                }
                if (!TextUtils.isEmpty(encoded)) {
                    if (first) {
                        first = false;
                    } else {
                        this.mBuilder.append(";");
                    }
                    appendTypeParameter(encoded);
                }
            } else if (VCardUtils.isV21Word(typeValue)) {
                if (first) {
                    first = false;
                } else {
                    this.mBuilder.append(";");
                }
                appendTypeParameter(typeValue);
            }
        }
    }

    private void appendTypeParameter(String type) {
        appendTypeParameter(this.mBuilder, type);
    }

    private void appendTypeParameter(StringBuilder builder, String type) {
        if (VCardConfig.isVersion40(this.mVCardType) || ((VCardConfig.isVersion30(this.mVCardType) || this.mAppendTypeParamName) && !this.mIsDoCoMo)) {
            builder.append("TYPE").append(VCARD_PARAM_EQUAL);
        }
        builder.append(type);
    }

    private boolean shouldAppendCharsetParam(String... propertyValueList) {
        if (this.mShouldAppendCharsetParam) {
            for (String propertyValue : propertyValueList) {
                if (!VCardUtils.containsOnlyPrintableAscii(propertyValue)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private String encodeQuotedPrintable(String str) {
        byte[] strArray;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int index = 0;
        int lineCount = 0;
        try {
            strArray = str.getBytes(this.mCharset);
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "Charset " + this.mCharset + " cannot be used. Try default charset");
            strArray = str.getBytes();
        }
        while (index < strArray.length) {
            builder.append(String.format("=%02X", Byte.valueOf(strArray[index])));
            index++;
            lineCount += 3;
            if (lineCount >= 67) {
                builder.append("=\r\n");
                lineCount = 0;
            }
        }
        return builder.toString();
    }

    private String escapeCharacters(String unescaped) {
        if (TextUtils.isEmpty(unescaped)) {
            return "";
        }
        StringBuilder tmpBuilder = new StringBuilder();
        int length = unescaped.length();
        for (int i = 0; i < length; i++) {
            char ch = unescaped.charAt(i);
            switch (ch) {
                case '\r':
                    if (i + 1 < length) {
                        char nextChar = unescaped.charAt(i);
                        if (nextChar == '\n') {
                            break;
                        }
                    }
                case '\n':
                    tmpBuilder.append("\\n");
                    break;
                case ',':
                    if (this.mIsV30OrV40) {
                        tmpBuilder.append("\\,");
                        break;
                    } else {
                        tmpBuilder.append(ch);
                        break;
                    }
                case ';':
                    tmpBuilder.append('\\');
                    tmpBuilder.append(';');
                    break;
                case '\\':
                    if (this.mIsV30OrV40) {
                        tmpBuilder.append("\\\\");
                        break;
                    }
                case '<':
                case '>':
                    if (this.mIsDoCoMo) {
                        tmpBuilder.append('\\');
                        tmpBuilder.append(ch);
                        break;
                    } else {
                        tmpBuilder.append(ch);
                        break;
                    }
                default:
                    tmpBuilder.append(ch);
                    break;
            }
        }
        return tmpBuilder.toString();
    }

    public String toString() {
        if (!this.mEndAppended) {
            if (this.mIsDoCoMo) {
                appendLine("X-CLASS", VCARD_DATA_PUBLIC);
                appendLine("X-REDUCTION", "");
                appendLine("X-NO", "");
                appendLine("X-DCM-HMN-MODE", "");
            }
            appendLine("END", VCARD_DATA_VCARD);
            this.mEndAppended = true;
        }
        return this.mBuilder.toString();
    }
}
