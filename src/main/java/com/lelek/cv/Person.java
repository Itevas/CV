package com.lelek.cv;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

    private String firstName;
    private String lastName;
    private LocalDate birthday;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        birthday = LocalDate.parse(stringDate, formatter);
        PropertiesMap.addToMapProperties("birthday", stringDate);
    }

    public String getBirthday() {
        return birthday.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    }

}

