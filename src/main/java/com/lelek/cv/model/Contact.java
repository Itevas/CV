package com.lelek.cv.model;

public class Contact {
    private long phoneNumber;
    private String address;
    private String eMail;

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
    public String geteMail(){
        return eMail;
    }
}
