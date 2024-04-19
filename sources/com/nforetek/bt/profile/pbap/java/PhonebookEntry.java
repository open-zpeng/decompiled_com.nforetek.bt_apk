package com.nforetek.bt.profile.pbap.java;

import com.nforetek.bt.profile.pbap.java.vcard.VCardEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class PhonebookEntry {
    public String id;
    public Name name = new Name();
    public List<Phone> phones = new ArrayList();

    /* loaded from: classes.dex */
    public static class Name {
        public String family;
        public String given;
        public String middle;
        public String prefix;
        public String suffix;

        public boolean equals(Object o) {
            if (o instanceof Name) {
                Name n = (Name) o;
                if (Objects.equals(this.family, n.family) || (this.family != null && this.family.equals(n.family))) {
                    if (Objects.equals(this.given, n.given) || (this.given != null && this.given.equals(n.given))) {
                        if (Objects.equals(this.middle, n.middle) || (this.middle != null && this.middle.equals(n.middle))) {
                            if (Objects.equals(this.prefix, n.prefix) || (this.prefix != null && this.prefix.equals(n.prefix))) {
                                return Objects.equals(this.suffix, n.suffix) || (this.suffix != null && this.suffix.equals(n.suffix));
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            int result = (this.family == null ? 0 : this.family.hashCode()) * 23;
            return (((((((result * 23) + (this.given == null ? 0 : this.given.hashCode())) * 23) + (this.middle == null ? 0 : this.middle.hashCode())) * 23) + (this.prefix == null ? 0 : this.prefix.hashCode())) * 23) + (this.suffix != null ? this.suffix.hashCode() : 0);
        }

        public String toString() {
            return "Name: { family: " + this.family + " given: " + this.given + " middle: " + this.middle + " prefix: " + this.prefix + " suffix: " + this.suffix + " }";
        }
    }

    /* loaded from: classes.dex */
    public static class Phone {
        public String number;
        public int type;

        public boolean equals(Object o) {
            if (o instanceof Phone) {
                Phone p = (Phone) o;
                return (Objects.equals(this.number, p.number) || (this.number != null && this.number.equals(p.number))) && this.type == p.type;
            }
            return false;
        }

        public int hashCode() {
            return (this.type * 23) + this.number.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(" Phone: { number: ");
            sb.append(this.number);
            sb.append(" type: " + this.type);
            sb.append(" }");
            return sb.toString();
        }
    }

    public boolean equals(Object object) {
        if (object instanceof PhonebookEntry) {
            return equals((PhonebookEntry) object);
        }
        return false;
    }

    public PhonebookEntry() {
    }

    public PhonebookEntry(VCardEntry v) {
        VCardEntry.NameData n = v.getNameData();
        this.name.family = n.getFamily();
        this.name.given = n.getGiven();
        this.name.middle = n.getMiddle();
        this.name.prefix = n.getPrefix();
        this.name.suffix = n.getSuffix();
        List<VCardEntry.PhoneData> vp = v.getPhoneList();
        if (vp != null && !vp.isEmpty()) {
            for (VCardEntry.PhoneData p : vp) {
                Phone phone = new Phone();
                phone.type = p.getType();
                phone.number = p.getNumber();
                this.phones.add(phone);
            }
        }
    }

    private boolean equals(PhonebookEntry p) {
        return this.name.equals(p.name) && this.phones.equals(p.phones);
    }

    public int hashCode() {
        return this.name.hashCode() + (this.phones.hashCode() * 23);
    }

    public String toString() {
        return "PhonebookEntry { id: " + this.id + " " + this.name.toString() + this.phones.toString() + " }";
    }
}
