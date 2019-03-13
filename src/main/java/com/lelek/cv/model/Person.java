package com.lelek.cv.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lelek.cv.service.LocalDateDeserializer;
import com.lelek.cv.service.LocalDateSerializer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 16)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 16)
    private String lastName;

    @NotNull
 //   @NotEmpty
    @Past
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
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

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}

