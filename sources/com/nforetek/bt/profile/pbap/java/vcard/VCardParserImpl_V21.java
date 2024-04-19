package com.nforetek.bt.profile.pbap.java.vcard;

import android.util.Log;
import com.nforetek.bt.profile.pbap.java.vcard.exception.VCardAgentNotSupportedException;
import com.nforetek.bt.profile.pbap.java.vcard.exception.VCardException;
import com.nforetek.bt.profile.pbap.java.vcard.exception.VCardInvalidCommentLineException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class VCardParserImpl_V21 {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_ENCODING = "8BIT";
    private static final String LOG_TAG = "vCard";
    private static final int STATE_GROUP_OR_PROPERTY_NAME = 0;
    private static final int STATE_PARAMS = 1;
    private static final int STATE_PARAMS_IN_DQUOTE = 2;
    private static final String TAG = "VCardParserImpl_V21";
    private boolean mCanceled;
    protected String mCurrentCharset;
    protected String mCurrentEncoding;
    protected final String mIntermediateCharset;
    private final List<VCardInterpreter> mInterpreterList;
    protected CustomBufferedReader mReader;
    protected final Set<String> mUnknownTypeSet;
    protected final Set<String> mUnknownValueSet;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static final class CustomBufferedReader extends BufferedReader {
        private String mNextLine;
        private boolean mNextLineIsValid;
        private long mTime;

        public CustomBufferedReader(Reader in) {
            super(in);
        }

        @Override // java.io.BufferedReader
        public String readLine() throws IOException {
            if (this.mNextLineIsValid) {
                String ret = this.mNextLine;
                this.mNextLine = null;
                this.mNextLineIsValid = false;
                return ret;
            }
            long start = System.currentTimeMillis();
            String line = super.readLine();
            long end = System.currentTimeMillis();
            this.mTime += end - start;
            return line;
        }

        public String peekLine() throws IOException {
            if (!this.mNextLineIsValid) {
                long start = System.currentTimeMillis();
                String line = super.readLine();
                long end = System.currentTimeMillis();
                this.mTime += end - start;
                this.mNextLine = line;
                this.mNextLineIsValid = true;
            }
            return this.mNextLine;
        }

        public long getTotalmillisecond() {
            return this.mTime;
        }
    }

    public VCardParserImpl_V21() {
        this(VCardConfig.VCARD_TYPE_DEFAULT);
    }

    public VCardParserImpl_V21(int vcardType) {
        this.mInterpreterList = new ArrayList();
        this.mUnknownTypeSet = new HashSet();
        this.mUnknownValueSet = new HashSet();
        this.mIntermediateCharset = "ISO-8859-1";
    }

    protected boolean isValidPropertyName(String propertyName) {
        if (!getKnownPropertyNameSet().contains(propertyName.toUpperCase()) && !propertyName.startsWith("X-") && !this.mUnknownTypeSet.contains(propertyName)) {
            this.mUnknownTypeSet.add(propertyName);
            Log.w(LOG_TAG, "Property name unsupported by vCard 2.1: " + propertyName);
            return true;
        }
        return true;
    }

    protected String getLine() throws IOException {
        String s = this.mReader.readLine();
        return s;
    }

    protected String peekLine() throws IOException {
        return this.mReader.peekLine();
    }

    protected String getNonEmptyLine() throws IOException, VCardException {
        String line;
        do {
            line = getLine();
            if (line == null) {
                throw new VCardException("Reached end of buffer.");
            }
        } while (line.trim().length() <= 0);
        return line;
    }

    private boolean parseOneVCard() throws IOException, VCardException {
        this.mCurrentEncoding = "8BIT";
        this.mCurrentCharset = "UTF-8";
        if (!readBeginVCard(true)) {
            return false;
        }
        for (VCardInterpreter interpreter : this.mInterpreterList) {
            interpreter.onEntryStarted();
        }
        parseItems();
        for (VCardInterpreter interpreter2 : this.mInterpreterList) {
            interpreter2.onEntryEnded();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean readBeginVCard(boolean allowGarbage) throws IOException, VCardException {
        while (true) {
            String line = getLine();
            if (line == null) {
                return false;
            }
            if (line.trim().length() > 0) {
                String[] strArray = line.split(":", 2);
                int length = strArray.length;
                if (length == 2 && strArray[0].trim().equalsIgnoreCase("BEGIN") && strArray[1].trim().equalsIgnoreCase("VCARD")) {
                    return true;
                }
                if (!allowGarbage) {
                    throw new VCardException("Expected String \"BEGIN:VCARD\" did not come (Instead, \"" + line + "\" came)");
                }
                if (!allowGarbage) {
                    throw new VCardException("Reached where must not be reached.");
                }
            }
        }
    }

    protected void parseItems() throws IOException, VCardException {
        boolean ended = false;
        try {
            ended = parseItem();
        } catch (VCardInvalidCommentLineException e) {
            Log.e(LOG_TAG, "Invalid line which looks like some comment was found. Ignored.");
        }
        while (!ended) {
            try {
                ended = parseItem();
            } catch (VCardInvalidCommentLineException e2) {
                Log.e(LOG_TAG, "Invalid line which looks like some comment was found. Ignored.");
            }
        }
    }

    protected boolean parseItem() throws IOException, VCardException {
        this.mCurrentEncoding = "8BIT";
        String line = getNonEmptyLine();
        VCardProperty propertyData = constructPropertyData(line);
        if (propertyData == null) {
            return false;
        }
        String propertyNameUpper = propertyData.getName().toUpperCase();
        String propertyRawValue = propertyData.getRawValue();
        if (propertyNameUpper.equals("BEGIN")) {
            if (propertyRawValue.equalsIgnoreCase("VCARD")) {
                handleNest();
                return false;
            }
            throw new VCardException("Unknown BEGIN type: " + propertyRawValue);
        } else if (propertyNameUpper.equals("END")) {
            if (propertyRawValue.equalsIgnoreCase("VCARD")) {
                return true;
            }
            throw new VCardException("Unknown END type: " + propertyRawValue);
        } else {
            parseItemInter(propertyData, propertyNameUpper);
            return false;
        }
    }

    private void parseItemInter(VCardProperty property, String propertyNameUpper) throws IOException, VCardException {
        String propertyRawValue = property.getRawValue();
        if (propertyNameUpper.equals("AGENT")) {
            handleAgent(property);
        } else if (isValidPropertyName(propertyNameUpper)) {
            if (propertyNameUpper.equals("VERSION") && !propertyRawValue.equals(getVersionString())) {
                Log.d(TAG, "Vcard version is error :" + getVersionString());
            }
            handlePropertyValue(property, propertyNameUpper);
        } else {
            throw new VCardException("Unknown property name: \"" + propertyNameUpper + "\"");
        }
    }

    private void handleNest() throws IOException, VCardException {
        for (VCardInterpreter interpreter : this.mInterpreterList) {
            interpreter.onEntryStarted();
        }
        parseItems();
        for (VCardInterpreter interpreter2 : this.mInterpreterList) {
            interpreter2.onEntryEnded();
        }
    }

    protected VCardProperty constructPropertyData(String line) throws VCardException {
        VCardProperty propertyData = new VCardProperty();
        int length = line.length();
        if (length > 0 && line.charAt(0) == '#') {
            Log.i(TAG, "VCardInvalidCommentLineException() = " + line);
            return null;
        }
        int state = 0;
        int nameIndex = 0;
        int i = 0;
        while (i < length) {
            char ch = line.charAt(i);
            switch (state) {
                case 0:
                    if (ch == ':') {
                        String propertyName = line.substring(nameIndex, i);
                        propertyData.setName(propertyName);
                        propertyData.setRawValue(i < length + (-1) ? line.substring(i + 1) : "");
                        return propertyData;
                    } else if (ch == '.') {
                        String groupName = line.substring(nameIndex, i);
                        if (groupName.length() == 0) {
                            Log.w(LOG_TAG, "Empty group found. Ignoring.");
                        } else {
                            propertyData.addGroup(groupName);
                        }
                        nameIndex = i + 1;
                        break;
                    } else if (ch != ';') {
                        break;
                    } else {
                        String propertyName2 = line.substring(nameIndex, i);
                        propertyData.setName(propertyName2);
                        nameIndex = i + 1;
                        state = 1;
                        break;
                    }
                case 1:
                    if (ch != '\"') {
                        if (ch == ';') {
                            try {
                                handleParams(propertyData, line.substring(nameIndex, i));
                            } catch (Exception e) {
                                Log.e(LOG_TAG, "ex-param: " + line.substring(nameIndex, i));
                            }
                            nameIndex = i + 1;
                            break;
                        } else if (ch != ':') {
                            break;
                        } else {
                            try {
                                handleParams(propertyData, line.substring(nameIndex, i));
                            } catch (Exception e2) {
                                Log.e(LOG_TAG, "ex-param: " + line.substring(nameIndex, i));
                            }
                            propertyData.setRawValue(i < length + (-1) ? line.substring(i + 1) : "");
                            return propertyData;
                        }
                    } else {
                        if ("2.1".equalsIgnoreCase(getVersionString())) {
                            Log.w(LOG_TAG, "Double-quoted params found in vCard 2.1. Silently allow it");
                        }
                        state = 2;
                        break;
                    }
                case 2:
                    if (ch == '\"') {
                        if ("2.1".equalsIgnoreCase(getVersionString())) {
                            Log.w(LOG_TAG, "Double-quoted params found in vCard 2.1. Silently allow it");
                        }
                        state = 1;
                        break;
                    } else {
                        break;
                    }
            }
            i++;
        }
        Log.i(TAG, "ignore Line = " + line);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleParams(VCardProperty propertyData, String params) throws VCardException {
        String[] strArray = params.split("=", 2);
        if (strArray.length == 2) {
            String paramName = strArray[0].trim().toUpperCase();
            String paramValue = strArray[1].trim();
            if (paramName.equals("TYPE")) {
                handleType(propertyData, paramValue);
                return;
            } else if (paramName.equals("VALUE")) {
                handleValue(propertyData, paramValue);
                return;
            } else if (paramName.equals("ENCODING")) {
                handleEncoding(propertyData, paramValue.toUpperCase());
                return;
            } else if (paramName.equals("CHARSET")) {
                handleCharset(propertyData, paramValue);
                return;
            } else if (paramName.equals("LANGUAGE")) {
                handleLanguage(propertyData, paramValue);
                return;
            } else if (paramName.startsWith("X-")) {
                handleAnyParam(propertyData, paramName, paramValue);
                return;
            } else {
                throw new VCardException("Unknown type \"" + paramName + "\"");
            }
        }
        handleParamWithoutName(propertyData, strArray[0]);
    }

    protected void handleParamWithoutName(VCardProperty propertyData, String paramValue) {
        handleType(propertyData, paramValue);
    }

    protected void handleType(VCardProperty propertyData, String ptypeval) {
        if (!getKnownTypeSet().contains(ptypeval.toUpperCase()) && !ptypeval.startsWith("X-") && !this.mUnknownTypeSet.contains(ptypeval)) {
            this.mUnknownTypeSet.add(ptypeval);
            Log.w(LOG_TAG, String.format("TYPE unsupported by %s: ", Integer.valueOf(getVersion()), ptypeval));
        }
        propertyData.addParameter("TYPE", ptypeval);
    }

    protected void handleValue(VCardProperty propertyData, String pvalueval) {
        if (!getKnownValueSet().contains(pvalueval.toUpperCase()) && !pvalueval.startsWith("X-") && !this.mUnknownValueSet.contains(pvalueval)) {
            this.mUnknownValueSet.add(pvalueval);
            Log.w(LOG_TAG, String.format("The value unsupported by TYPE of %s: ", Integer.valueOf(getVersion()), pvalueval));
        }
        propertyData.addParameter("VALUE", pvalueval);
    }

    protected void handleEncoding(VCardProperty propertyData, String pencodingval) throws VCardException {
        if (getAvailableEncodingSet().contains(pencodingval) || pencodingval.startsWith("X-")) {
            propertyData.addParameter("ENCODING", pencodingval);
            this.mCurrentEncoding = pencodingval.toUpperCase();
            return;
        }
        throw new VCardException("Unknown encoding \"" + pencodingval + "\"");
    }

    protected void handleCharset(VCardProperty propertyData, String charsetval) {
        this.mCurrentCharset = charsetval;
        propertyData.addParameter("CHARSET", charsetval);
    }

    protected void handleLanguage(VCardProperty propertyData, String langval) throws VCardException {
        String[] strArray = langval.split("-");
        if (strArray.length != 2) {
            throw new VCardException("Invalid Language: \"" + langval + "\"");
        }
        String tmp = strArray[0];
        int length = tmp.length();
        for (int i = 0; i < length; i++) {
            if (!isAsciiLetter(tmp.charAt(i))) {
                throw new VCardException("Invalid Language: \"" + langval + "\"");
            }
        }
        String tmp2 = strArray[1];
        int length2 = tmp2.length();
        for (int i2 = 0; i2 < length2; i2++) {
            if (!isAsciiLetter(tmp2.charAt(i2))) {
                throw new VCardException("Invalid Language: \"" + langval + "\"");
            }
        }
        propertyData.addParameter("LANGUAGE", langval);
    }

    private boolean isAsciiLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    protected void handleAnyParam(VCardProperty propertyData, String paramName, String paramValue) {
        propertyData.addParameter(paramName, paramValue);
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x01f1, code lost:
        r13 = r5.toString();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void handlePropertyValue(com.nforetek.bt.profile.pbap.java.vcard.VCardProperty r25, java.lang.String r26) throws java.io.IOException, com.nforetek.bt.profile.pbap.java.vcard.exception.VCardException {
        /*
            Method dump skipped, instructions count: 562
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.profile.pbap.java.vcard.VCardParserImpl_V21.handlePropertyValue(com.nforetek.bt.profile.pbap.java.vcard.VCardProperty, java.lang.String):void");
    }

    private void handleAdrOrgN(VCardProperty property, String propertyRawValue, String sourceCharset, String targetCharset) throws VCardException, IOException {
        String quotedPrintablePart;
        List<String> encodedValueList = new ArrayList<>();
        if (this.mCurrentEncoding.equals("QUOTED-PRINTABLE")) {
            quotedPrintablePart = getQuotedPrintablePart(propertyRawValue);
        } else {
            quotedPrintablePart = propertyRawValue;
        }
        property.setRawValue(quotedPrintablePart);
        List<String> quotedPrintableValueList = VCardUtils.constructListFromValue(quotedPrintablePart, getVersion());
        for (String quotedPrintableValue : quotedPrintableValueList) {
            String encoded = VCardUtils.parseQuotedPrintable(quotedPrintableValue, false, sourceCharset, targetCharset);
            encodedValueList.add(encoded);
        }
        property.setValues(encodedValueList);
        for (VCardInterpreter interpreter : this.mInterpreterList) {
            interpreter.onPropertyCreated(property);
        }
    }

    private String getQuotedPrintablePart(String firstString) throws IOException, VCardException {
        if (firstString.trim().endsWith("=")) {
            int pos = firstString.length() - 1;
            do {
            } while (firstString.charAt(pos) != '=');
            StringBuilder builder = new StringBuilder();
            builder.append(firstString.substring(0, pos + 1));
            builder.append("\r\n");
            while (true) {
                String line = getLine();
                if (line == null) {
                    throw new VCardException("File ended during parsing a Quoted-Printable String");
                }
                if (line.trim().endsWith("=")) {
                    int pos2 = line.length() - 1;
                    do {
                    } while (line.charAt(pos2) != '=');
                    builder.append(line.substring(0, pos2 + 1));
                    builder.append("\r\n");
                } else {
                    builder.append(line);
                    return builder.toString();
                }
            }
        } else {
            return firstString;
        }
    }

    private String getPotentialMultiline(String firstString) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append(firstString);
        while (true) {
            String line = peekLine();
            if (line != null && line.length() != 0) {
                String propertyName = getPropertyNameUpperCase(line);
                if (propertyName != null) {
                    break;
                }
                getLine();
                builder.append(" ").append(line);
            } else {
                break;
            }
        }
        return builder.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002d, code lost:
        return r0.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
        android.util.Log.w(com.nforetek.bt.profile.pbap.java.vcard.VCardParserImpl_V21.LOG_TAG, "Found a next property during parsing a BASE64 string, which must not contain semi-colon or colon. Treat the line as next property.");
        android.util.Log.w(com.nforetek.bt.profile.pbap.java.vcard.VCardParserImpl_V21.LOG_TAG, "Problematic line: " + r1.trim());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.lang.String getBase64(java.lang.String r7) throws java.io.IOException, com.nforetek.bt.profile.pbap.java.vcard.exception.VCardException {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
        L8:
            java.lang.String r1 = r6.peekLine()
            if (r1 != 0) goto L16
            com.nforetek.bt.profile.pbap.java.vcard.exception.VCardException r3 = new com.nforetek.bt.profile.pbap.java.vcard.exception.VCardException
            java.lang.String r4 = "File ended during parsing BASE64 binary"
            r3.<init>(r4)
            throw r3
        L16:
            java.lang.String r3 = r1.toUpperCase()
            java.lang.String r4 = "ADR"
            boolean r3 = r3.startsWith(r4)
            if (r3 == 0) goto L2e
            java.lang.String r3 = "vCard"
            java.lang.String r4 = "Michael in getBase64, line.toUpperCase().startsWith(ADR), break"
            android.util.Log.e(r3, r4)
        L29:
            java.lang.String r3 = r0.toString()
            return r3
        L2e:
            java.lang.String r2 = r6.getPropertyNameUpperCase(r1)
            java.util.Set r3 = r6.getKnownPropertyNameSet()
            boolean r3 = r3.contains(r2)
            if (r3 != 0) goto L44
            java.lang.String r3 = "X-ANDROID-CUSTOM"
            boolean r3 = r3.equals(r2)
            if (r3 == 0) goto L64
        L44:
            java.lang.String r3 = "vCard"
            java.lang.String r4 = "Found a next property during parsing a BASE64 string, which must not contain semi-colon or colon. Treat the line as next property."
            android.util.Log.w(r3, r4)
            java.lang.String r3 = "vCard"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Problematic line: "
            r4.<init>(r5)
            java.lang.String r5 = r1.trim()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.w(r3, r4)
            goto L29
        L64:
            r6.getLine()
            int r3 = r1.length()
            if (r3 == 0) goto L29
            java.lang.String r3 = r1.trim()
            r0.append(r3)
            goto L8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.profile.pbap.java.vcard.VCardParserImpl_V21.getBase64(java.lang.String):java.lang.String");
    }

    private String getPropertyNameUpperCase(String line) {
        int minIndex;
        int colonIndex = line.indexOf(":");
        if (colonIndex > -1) {
            int semiColonIndex = line.indexOf(";");
            if (colonIndex == -1) {
                minIndex = semiColonIndex;
            } else if (semiColonIndex == -1) {
                minIndex = colonIndex;
            } else {
                minIndex = Math.min(colonIndex, semiColonIndex);
            }
            return line.substring(0, minIndex).toUpperCase();
        }
        return null;
    }

    protected void handleAgent(VCardProperty property) throws VCardException {
        if (!property.getRawValue().toUpperCase().contains("BEGIN:VCARD")) {
            for (VCardInterpreter interpreter : this.mInterpreterList) {
                interpreter.onPropertyCreated(property);
            }
            return;
        }
        throw new VCardAgentNotSupportedException("AGENT Property is not supported now.");
    }

    protected String maybeUnescapeText(String text) {
        return text;
    }

    protected String maybeUnescapeCharacter(char ch) {
        return unescapeCharacter(ch);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String unescapeCharacter(char ch) {
        if (ch == '\\' || ch == ';' || ch == ':' || ch == ',') {
            return String.valueOf(ch);
        }
        return null;
    }

    protected int getVersion() {
        return 0;
    }

    protected String getVersionString() {
        return "2.1";
    }

    protected Set<String> getKnownPropertyNameSet() {
        return VCardParser_V21.sKnownPropertyNameSet;
    }

    protected Set<String> getKnownTypeSet() {
        return VCardParser_V21.sKnownTypeSet;
    }

    protected Set<String> getKnownValueSet() {
        return VCardParser_V21.sKnownValueSet;
    }

    protected Set<String> getAvailableEncodingSet() {
        return VCardParser_V21.sAvailableEncoding;
    }

    protected String getDefaultEncoding() {
        return "8BIT";
    }

    protected String getDefaultCharset() {
        return "UTF-8";
    }

    protected String getCurrentCharset() {
        return this.mCurrentCharset;
    }

    public void addInterpreter(VCardInterpreter interpreter) {
        this.mInterpreterList.add(interpreter);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002d, code lost:
        android.util.Log.i(com.nforetek.bt.profile.pbap.java.vcard.VCardParserImpl_V21.LOG_TAG, "Cancel request has come. exitting parse operation.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void parse(java.io.InputStream r7) throws java.io.IOException, com.nforetek.bt.profile.pbap.java.vcard.exception.VCardException {
        /*
            r6 = this;
            if (r7 != 0) goto La
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "InputStream must not be null."
            r4.<init>(r5)
            throw r4
        La:
            java.io.InputStreamReader r1 = new java.io.InputStreamReader
            java.lang.String r4 = r6.mIntermediateCharset
            r1.<init>(r7, r4)
            com.nforetek.bt.profile.pbap.java.vcard.VCardParserImpl_V21$CustomBufferedReader r4 = new com.nforetek.bt.profile.pbap.java.vcard.VCardParserImpl_V21$CustomBufferedReader
            r4.<init>(r1)
            r6.mReader = r4
            long r2 = java.lang.System.currentTimeMillis()
            java.util.List<com.nforetek.bt.profile.pbap.java.vcard.VCardInterpreter> r4 = r6.mInterpreterList
            java.util.Iterator r4 = r4.iterator()
        L22:
            boolean r5 = r4.hasNext()
            if (r5 != 0) goto L42
        L28:
            monitor-enter(r6)
            boolean r4 = r6.mCanceled     // Catch: java.lang.Throwable -> L54
            if (r4 == 0) goto L4c
            java.lang.String r4 = "vCard"
            java.lang.String r5 = "Cancel request has come. exitting parse operation."
            android.util.Log.i(r4, r5)     // Catch: java.lang.Throwable -> L54
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L54
        L35:
            java.util.List<com.nforetek.bt.profile.pbap.java.vcard.VCardInterpreter> r4 = r6.mInterpreterList
            java.util.Iterator r4 = r4.iterator()
        L3b:
            boolean r5 = r4.hasNext()
            if (r5 != 0) goto L57
            return
        L42:
            java.lang.Object r0 = r4.next()
            com.nforetek.bt.profile.pbap.java.vcard.VCardInterpreter r0 = (com.nforetek.bt.profile.pbap.java.vcard.VCardInterpreter) r0
            r0.onVCardStarted()
            goto L22
        L4c:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L54
            boolean r4 = r6.parseOneVCard()
            if (r4 != 0) goto L28
            goto L35
        L54:
            r4 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L54
            throw r4
        L57:
            java.lang.Object r0 = r4.next()
            com.nforetek.bt.profile.pbap.java.vcard.VCardInterpreter r0 = (com.nforetek.bt.profile.pbap.java.vcard.VCardInterpreter) r0
            r0.onVCardEnded()
            goto L3b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nforetek.bt.profile.pbap.java.vcard.VCardParserImpl_V21.parse(java.io.InputStream):void");
    }

    public void parseOne(InputStream is) throws IOException, VCardException {
        if (is == null) {
            throw new NullPointerException("InputStream must not be null.");
        }
        InputStreamReader tmpReader = new InputStreamReader(is, this.mIntermediateCharset);
        this.mReader = new CustomBufferedReader(tmpReader);
        System.currentTimeMillis();
        for (VCardInterpreter interpreter : this.mInterpreterList) {
            interpreter.onVCardStarted();
        }
        parseOneVCard();
        for (VCardInterpreter interpreter2 : this.mInterpreterList) {
            interpreter2.onVCardEnded();
        }
    }

    public final synchronized void cancel() {
        Log.i(LOG_TAG, "ParserImpl received cancel operation.");
        this.mCanceled = true;
    }
}
