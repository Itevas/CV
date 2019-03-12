package com.lelek.cv.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Contact {

    @Size(min = 10, max=10, message = "wrong phone number")
    private long phoneNumber;

    @NotNull
    private String address;

    @Email
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
