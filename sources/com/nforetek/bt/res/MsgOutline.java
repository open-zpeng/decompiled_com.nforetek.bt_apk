package com.nforetek.bt.res;

import java.io.Serializable;
/* loaded from: classes.dex */
public class MsgOutline implements Serializable {
    private static final long serialVersionUID = 1;
    private int _id;
    private String attachment_size;
    private String datetime;
    private String folder;
    private String handle;
    private String macAddress;
    private String message;
    private String priority;
    private String protect;
    private String read;
    private String reception_status;
    private String recipient_addressing;
    private String recipient_name;
    private String replyto_addressing;
    private String sender_addressing;
    private String sender_name;
    private String sent;
    private String size;
    private String subject;
    private String text;
    private String type;

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getHandle() {
        return this.handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getSender_name() {
        return this.sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getSender_addressing() {
        return this.sender_addressing;
    }

    public void setSender_addressing(String sender_addressing) {
        this.sender_addressing = sender_addressing;
    }

    public String getReplyto_addressing() {
        return this.replyto_addressing;
    }

    public void setReplyto_addressing(String replyto_addressing) {
        this.replyto_addressing = replyto_addressing;
    }

    public String getRecipient_name() {
        return this.recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getRecipient_addressing() {
        return this.recipient_addressing;
    }

    public void setRecipient_addressing(String recipient_addressing) {
        this.recipient_addressing = recipient_addressing;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReception_status() {
        return this.reception_status;
    }

    public void setReception_status(String reception_status) {
        this.reception_status = reception_status;
    }

    public String getAttachment_size() {
        return this.attachment_size;
    }

    public void setAttachment_size(String attachment_size) {
        this.attachment_size = attachment_size;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRead() {
        return this.read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getSent() {
        return this.sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getProtect() {
        return this.protect;
    }

    public void setProtect(String protect) {
        this.protect = protect;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFolder() {
        return this.folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
