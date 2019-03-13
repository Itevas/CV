package com.lelek.cv.model;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.*;

public class PersonTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    private Person person = new Person();

    @Test
    public void testGetSetFirstName() {
        person.setFirstName("Chad");
        assertEquals("Chad", person.getFirstName());
    }

    @Test
    public void testGetSetLastName() {
        person.setLastName("Kroeger");
        assertEquals("Kroeger", person.getLastName());
    }

    @Test
    public void testGetSetBirthday() {
        person.setBirthday(LocalDate.parse("26.07.1985", FORMATTER));
        assertEquals(LocalDate.parse("26.07.1985", FORMATTER), person.getBirthday());
    }

}