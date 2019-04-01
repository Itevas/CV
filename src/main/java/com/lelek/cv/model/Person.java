package com.lelek.cv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lelek.cv.service.mapper.LocalDateDeserializer;
import com.lelek.cv.service.mapper.LocalDateSerializer;
import com.lelek.cv.service.ValidateClass;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;

public class Person {

    private Person() {
    }

    @NotNull(message = "firstName might not be NULL")
    @NotEmpty(message = "Empty firstName")
    @Size(min = 1, max = 16, message = "Invalid lenght firstName")
    private String firstName;

    @NotNull(message = "lastName might not be NULL")
    @NotEmpty(message = "Empty lastName")
    @Size(min = 1, max = 16, message = "Invalid lenght lastName")
    private String lastName;

    @NotNull(message = "birthday might not be NULL")
    @Past(message = "birthday must be in past")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthday;

    @JsonIgnore
    private String age;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getAge() {
        return String.valueOf("(" + Period.between(getBirthday(), LocalDate.now()).getYears() + " years old)");
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
            new ValidateClass().validate(person);
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

