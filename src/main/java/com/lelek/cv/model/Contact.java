package com.lelek.cv.model;

import com.lelek.cv.service.ValidateClass;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Contact {

    private Contact(){}

    @Size(min = 10, max=10, message = "wrong phone number")
    private String phoneNumber;

    private String address;

    @Email
    private String eMail;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String geteMail(){
        return eMail;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }

    public static class ContactBuilder{

        private Contact contact;

        public ContactBuilder (){
            contact = new Contact();
        }

        public ContactBuilder phoneNumber(String phoneNumber) {
            contact.phoneNumber = phoneNumber;
            return this;
        }

        public ContactBuilder address(String address) {
            contact.address = address;
            return this;
        }

        public ContactBuilder eMail(String eMail) {
            contact.eMail = eMail;
            return this;
        }

        public Contact build(){
            (new ValidateClass()).validate(contact);
            return contact;
        }
    }
}
