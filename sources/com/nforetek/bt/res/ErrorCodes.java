package com.nforetek.bt.res;
/* loaded from: classes.dex */
public class ErrorCodes {
    public static String getErrorName(int reason) {
        switch (reason) {
            case 0:
                return "Success";
            case 1:
                return "Unknown HCI Command";
            case 2:
                return "Unknown Connection Identifier";
            case 3:
                return "Hardware Failure";
            case 4:
                return "Page Timeout";
            case 5:
                return "Authentication Failure";
            case 6:
                return "PIN or Key Missing";
            case 7:
                return "Memory Capacity Exceeded";
            case 8:
                return "Connection Timeout";
            case 9:
                return "Connection Limit Exceeded";
            case 10:
                return "Synchronous Connection Limit To A Device Exceeded";
            case 11:
                return "ACL Connection Already Exists";
            case 12:
                return "Command Disallowed";
            case 13:
                return "Connection Rejected due to Limited Resources";
            case 14:
                return "Connection Rejected Due To Security Reasons";
            case 15:
                return "Connection Rejected due to Unacceptable BD_ADDR";
            case 16:
                return "Connection Accept Timeout Exceeded";
            case 17:
                return "Unsupported Feature or Parameter Value";
            case 18:
                return "Invalid HCI Command Parameters";
            case 19:
                return "Remote User Terminated Connection";
            case 20:
                return "Remote Device Terminated Connection due to Low Resources";
            case 21:
                return "Remote Device Terminated Connection due to Power Off";
            case 22:
                return "Connection Terminated By Local Host";
            case 23:
                return "Repeated Attempts";
            case 24:
                return "Pairing Not Allowed";
            case NfDef.TIME_EVENT_SCO_ON_CLOSED_MS /* 25 */:
                return "Unknown LMP PDU";
            case 26:
                return "Unsupported Remote Feature / Unsupported LMP Feature";
            case 27:
                return "SCO Offset Rejected";
            case 28:
                return "SCO Interval Rejected";
            case 29:
                return "SCO Air Mode Rejected";
            case 30:
                return "Invalid LMP Parameters";
            case 31:
                return "Unspecified Error";
            case 32:
                return "Unsupported LMP Parameter Value";
            case 33:
                return "Role Change Not Allowed";
            case 34:
                return "LMP Response Timeout";
            case 35:
                return "LMP Error Transaction Collision";
            case 36:
                return "LMP PDU Not Allowed";
            case 37:
                return "Encryption Mode Not Acceptable";
            case 38:
                return "Link Key Can Not be Changed";
            case 39:
                return "Requested QoS Not Supported";
            case 40:
                return "Instant Passed";
            case 41:
                return "Pairing With Unit Key Not Supported";
            case 42:
                return "Different Transaction Collision";
            case 43:
                return "Reserved";
            case 44:
                return "QoS Unacceptable Parameter";
            case 45:
                return "QoS Rejected";
            case 46:
                return "Channel Classification Not Supported";
            case 47:
                return "Insufficient Security";
            case 48:
                return "Parameter Out Of Mandatory Range";
            case 49:
                return "Reserved";
            case 50:
                return "Role Switch Pending";
            case 51:
                return "Reserved";
            case 52:
                return "Reserved Slot Violation";
            case 53:
                return "Role Switch Failed";
            case 54:
                return "Extended Inquiry Response Too Large";
            case 55:
                return "Secure Simple Pairing Not Supported By Host.";
            case 56:
                return "Host Busy - Pairing";
            case 57:
                return "Connection Rejected due to No Suitable Channel Found";
            default:
                return "";
        }
    }
}
