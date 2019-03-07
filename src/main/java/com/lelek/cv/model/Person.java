package com.lelek.cv.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() { return firstName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setBirthday(String stringDate) {
        birthday = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    }

    public String getBirthday() {
        return birthday.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    }
}

