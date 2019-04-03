package com.lelek.cv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Cv {

    @JsonIgnore
    private int id;

    private Person person;
    private Contact contact;
    private List<JobPlace> jobPlaces = new ArrayList<>();
    private List<Skill> skills = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

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

    public void setSkills(List<Skill> skills){
        this.skills=skills;
    }

    public List<Skill> getSkills(){
        return skills;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", person=" + person +
                ", contact=" + contact +
                ", jobPlaces=" + jobPlaces +
                ", skills=" + skills +
                '}';
    }
}
