package com.lelek.cv;

import java.time.LocalDate;

public class Person {

    private String firstName;
    private String lastName;
    private LocalDate birthday; //YYYY-MM-DD

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        PropertiesMap.addToMapProperties("firstName", firstName);
    }

    public String getFirstName() { return firstName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        PropertiesMap.addToMapProperties("lastName", lastName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthday(String stringDate) {
        birthday = LocalDate.parse(stringDate);
        PropertiesMap.addToMapProperties("birthday", stringDate);
    }

    public String getBirthday() {
        return birthday.toString();
    }

}

