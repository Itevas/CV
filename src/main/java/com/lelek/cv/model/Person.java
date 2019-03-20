package com.lelek.cv.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lelek.cv.service.LocalDateDeserializer;
import com.lelek.cv.service.LocalDateSerializer;
import com.lelek.cv.service.ValidateClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Logger;


public class Person {

    private String firstName;

    private Person() {
    }

    @NotNull(message = "lastName might not be NULL")
    @NotEmpty(message = "Empty lastName")
    @Size(min = 1, max = 16, message = "Invalid lenght lastName")
    private String lastName;

    @NotNull(message = "birthday might not be NULL")
    @Past(message = "birthday must be in past")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthday;

    @NotNull(message = "firstName might not be NULL")
    @NotEmpty(message = "Empty firstName")
    @Size(min = 1, max = 16, message = "Invalid lenght firstName")
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public static class PersonBuilder {

        private Person person;

        public PersonBuilder() {
            person = new Person();
        }

        public PersonBuilder firstName(String firstName) {
            person.firstName = firstName;
            return this;
        }

        public PersonBuilder lastName(String lastName) {
            person.lastName = lastName;
            return this;
        }

        public PersonBuilder birthday(LocalDate birthday) {
            person.birthday = birthday;
            return this;
        }

        public Person build() {
            (new ValidateClass()).validate(person);
            System.out.println("BUILDER");
            return person;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

