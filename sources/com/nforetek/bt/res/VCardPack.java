package com.nforetek.bt.res;

import android.database.Cursor;
import java.io.Serializable;
import java.util.Set;
/* loaded from: classes.dex */
public class VCardPack implements Serializable {
    private String HistoryDate;
    private String HistoryTime;
    private int _id;
    private String cellPhone_Address;
    private String firstName;
    private String first_CityNameAddress;
    private String first_CountryAddress;
    private String first_FederalStateAddress;
    private String first_StreetAddress;
    private String first_ZipCodeAddress;
    private String fullName;
    private String lastName;
    private Set<PhoneInfo> phoneNumbers;
    private String second_CityNameAddress;
    private String second_CountryAddress;
    private String second_FederalStateAddress;
    private String second_StreetAddress;
    private String second_ZipCodeAddress;
    private String storageType;

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirst_StreetAddress() {
        return this.first_StreetAddress;
    }

    public void setFirst_StreetAddress(String first_StreetAddress) {
        this.first_StreetAddress = first_StreetAddress;
    }

    public String getFirst_CityNameAddress() {
        return this.first_CityNameAddress;
    }

    public void setFirst_CityNameAddress(String first_CityNameAddress) {
        this.first_CityNameAddress = first_CityNameAddress;
    }

    public String getFirst_FederalStateAddress() {
        return this.first_FederalStateAddress;
    }

    public void setFirst_FederalStateAddress(String first_FederalStateAddress) {
        this.first_FederalStateAddress = first_FederalStateAddress;
    }

    public String getFirst_ZipCodeAddress() {
        return this.first_ZipCodeAddress;
    }

    public void setFirst_ZipCodeAddress(String first_ZipCodeAddress) {
        this.first_ZipCodeAddress = first_ZipCodeAddress;
    }

    public String getFirst_CountryAddress() {
        return this.first_CountryAddress;
    }

    public void setFirst_CountryAddress(String first_CountryAddress) {
        this.first_CountryAddress = first_CountryAddress;
    }

    public String getSecond_StreetAddress() {
        return this.second_StreetAddress;
    }

    public void setSecond_StreetAddress(String second_StreetAddress) {
        this.second_StreetAddress = second_StreetAddress;
    }

    public String getSecond_CityNameAddress() {
        return this.second_CityNameAddress;
    }

    public void setSecond_CityNameAddress(String second_CityNameAddress) {
        this.second_CityNameAddress = second_CityNameAddress;
    }

    public String getSecond_FederalStateAddress() {
        return this.second_FederalStateAddress;
    }

    public void setSecond_FederalStateAddress(String second_FederalStateAddress) {
        this.second_FederalStateAddress = second_FederalStateAddress;
    }

    public String getSecond_ZipCodeAddress() {
        return this.second_ZipCodeAddress;
    }

    public void setSecond_ZipCodeAddress(String second_ZipCodeAddress) {
        this.second_ZipCodeAddress = second_ZipCodeAddress;
    }

    public String getSecond_CountryAddress() {
        return this.second_CountryAddress;
    }

    public void setSecond_CountryAddress(String second_CountryAddress) {
        this.second_CountryAddress = second_CountryAddress;
    }

    public String getCellPhone_Address() {
        return this.cellPhone_Address;
    }

    public void setCellPhone_Address(String cellPhone_Address) {
        this.cellPhone_Address = cellPhone_Address;
    }

    public Set<PhoneInfo> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneInfo> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getStorageType() {
        return this.storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public VCardPack() {
    }

    public VCardPack(Cursor cursor) {
        int idxId = cursor.getColumnIndex("_id");
        int idxFullName = cursor.getColumnIndex("FullName");
        int idxFirstName = cursor.getColumnIndex("FirstName");
        int idxLastName = cursor.getColumnIndex("LastName");
        int idxFirst_StreetAddress = cursor.getColumnIndex("First_StreetAddress");
        int idxFirst_CityNameAddress = cursor.getColumnIndex("First_CityNameAddress");
        int idxFirst_FederalStateAddress = cursor.getColumnIndex("First_FederalStateAddress");
        int idxFirst_ZipCodeAddress = cursor.getColumnIndex("First_ZipCodeAddress");
        int idxFirst_CountryAddress = cursor.getColumnIndex("First_CountryAddress");
        int idxSecond_StreetAddress = cursor.getColumnIndex("Second_StreetAddress");
        int idxSecond_CityNameAddress = cursor.getColumnIndex("Second_CityNameAddress");
        int idxSecond_FederalStateAddress = cursor.getColumnIndex("Second_FederalStateAddress");
        int idxSecond_ZipCodeAddress = cursor.getColumnIndex("Second_ZipCodeAddress");
        int idxSecond_CountryAddress = cursor.getColumnIndex("Second_CountryAddress");
        int idxCellPhoneAddress = cursor.getColumnIndex("CellPhone_Address");
        int idxStroageType = cursor.getColumnIndex("StorageType");
        set_id(cursor.getInt(idxId));
        setFullName(cursor.getString(idxFullName));
        setFirstName(cursor.getString(idxFirstName));
        setLastName(cursor.getString(idxLastName));
        setFirst_StreetAddress(cursor.getString(idxFirst_StreetAddress));
        setFirst_CityNameAddress(cursor.getString(idxFirst_CityNameAddress));
        setFirst_FederalStateAddress(cursor.getString(idxFirst_FederalStateAddress));
        setFirst_ZipCodeAddress(cursor.getString(idxFirst_ZipCodeAddress));
        setFirst_CountryAddress(cursor.getString(idxFirst_CountryAddress));
        setSecond_StreetAddress(cursor.getString(idxSecond_StreetAddress));
        setSecond_CityNameAddress(cursor.getString(idxSecond_CityNameAddress));
        setSecond_FederalStateAddress(cursor.getString(idxSecond_FederalStateAddress));
        setSecond_ZipCodeAddress(cursor.getString(idxSecond_ZipCodeAddress));
        setSecond_CountryAddress(cursor.getString(idxSecond_CountryAddress));
        setCellPhone_Address(cursor.getString(idxCellPhoneAddress));
        setStorageType(cursor.getString(idxStroageType));
    }

    public String getHistoryDate() {
        return this.HistoryDate;
    }

    public void setHistoryDate(String historyDate) {
        this.HistoryDate = historyDate;
    }

    public String getHistoryTime() {
        return this.HistoryTime;
    }

    public void setHistoryTime(String historyTime) {
        this.HistoryTime = historyTime;
    }
}
