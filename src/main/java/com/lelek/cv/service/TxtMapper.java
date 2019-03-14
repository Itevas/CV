package com.lelek.cv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lelek.cv.model.CV;
import com.lelek.cv.model.Contact;
import com.lelek.cv.model.JobPlace;
import com.lelek.cv.model.Person;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class TxtMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu");
    private String fileName;
    private Map<String, Object> map = new HashMap<>();

    public TxtMapper(String fileName) {
        this.fileName = fileName;
    }

    public CV readValue() throws IOException {
        CV cv = new CV();
        int i = 0;
        List<JobPlace> jobPlaces = new ArrayList<>();
        Scanner scanTxt = new Scanner(new File(fileName)).useDelimiter(":");

        while (scanTxt.hasNextLine()) {
            String line = scanTxt.next().trim();

            if (line.equals("firstName")) {
                map.put(line, (scanTxt.next().trim()));
            } else if (line.equals("lastName")) {
                map.put(line, (scanTxt.next().trim()));
            } else if (line.equals("birthday")) {
                map.put(line, (LocalDate.parse(scanTxt.next().trim(), FORMATTER)));
            } else if (line.equals("phoneNumber")) {
                map.put(line, (scanTxt.next().trim()));
            } else if (line.equals("address")) {
                map.put(line, (scanTxt.next().trim()));
            } else if (line.equals("eMail")) {
                map.put(line, (scanTxt.next().trim()));
            } else if (line.equals("company")) {
                jobPlaces.add(new JobPlace());
                jobPlaces.get(i).setCompany(scanTxt.next().trim());
            } else if (line.equals("city")) {
                jobPlaces.get(i).setCity(scanTxt.next().trim());
            } else if (line.equals("from")) {
                jobPlaces.get(i).setFrom(LocalDate.parse(scanTxt.next().trim(), FORMATTER));
            } else if (line.equals("to")) {
                String date = scanTxt.next().trim();
                if (date.equals("This time")) {
                    jobPlaces.get(i).setTo(LocalDate.now());
                } else {
                    jobPlaces.get(i).setTo(LocalDate.parse(date, FORMATTER));
                }
            } else if (line.equals("position")) {
                jobPlaces.get(i).setPosition(scanTxt.next().trim());
                i++;
            }
        }
        scanTxt.close();

        cv.setJobPlaces(jobPlaces);
        cv.setContact(new Contact.ContactBuilder()
                .setPhoneNumber(map.get("phoneNumber").toString())
                .setAddress(map.get("address").toString())
                .seteMail(map.get("eMail").toString())
                .build());
        cv.setPerson(new Person.PersonBuilder()
                .setFirstName(map.get("firstName").toString())
                .setLastName(map.get("lastName").toString())
                .setBirthday((LocalDate) map.get("birthday"))
                .build());
        return cv;
    }


}
