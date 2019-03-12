package com.lelek.cv.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

    @NotNull
    @Size(min = 1, max = 16)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 16)
    private String lastName;

    @NotNull
    @Past
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

