package com.lelek.cv.model;

import java.util.ArrayList;
import java.util.List;

public class CV {

    private int id;
    private Person person;
    private Contact contact;
    private List<JobPlace> jobPlaces = new ArrayList<>();

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    public void setJobPlaces(List<JobPlace> jobPlaces) {
        this.jobPlaces = jobPlaces;
    }

    public List<JobPlace> getJobPlaces() {
        return jobPlaces;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CV{" +
                "person=" + person +
                ", contact=" + contact +
                ", id=" + id +
                ", jobPlaces=" + jobPlaces +
                '}';
    }
}
