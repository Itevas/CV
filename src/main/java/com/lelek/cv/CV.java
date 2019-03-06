package com.lelek.cv;

import java.io.IOException;

public class CV {
    private Person person = new Person();
    private Contact contact = new Contact();
    private JobPlace jobPlace = new JobPlace();

    public void setPerson(Person person) {
        this.person = person;
        PropertiesMap.addToMapProperties("person", "");
    }

    public Person getPerson() {
        return person;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        PropertiesMap.addToMapProperties("contact", "");
    }

    public Contact getContact() {
        return contact;
    }

    public void setJobPlace(JobPlace jobPlace) {
        this.jobPlace = jobPlace;
        PropertiesMap.addToMapProperties("jobPlace", "");
    }

    public JobPlace getJobPlace() {
        return jobPlace;
    }

    public static void main(String[] args) throws IOException {
        ParseXML parseXML = new ParseXML();
        CV cv = parseXML.getInfoFromXML();
        (new CreateYAML()).create(PropertiesMap.getFields());

        parseXML.writeInFile(cv);

    }
}
