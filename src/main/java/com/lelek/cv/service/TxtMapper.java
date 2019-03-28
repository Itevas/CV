package com.lelek.cv.service;

import com.lelek.cv.model.*;

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

    public Cv readValue() throws IOException {
        Cv cv = new Cv();
        List<JobPlace> jobPlaces = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();
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
                map.put(line, (scanTxt.next().trim()));
            } else if (line.equals("city")) {
                map.put(line, (scanTxt.next().trim()));
            } else if (line.equals("from")) {
                map.put(line, (LocalDate.parse(scanTxt.next().trim(), FORMATTER)));
            } else if (line.equals("to")) {
                String date = scanTxt.next().trim();
                if (date.equals("This time")) {
                    map.put(line, (LocalDate.now()));
                } else {
                    map.put(line, (LocalDate.parse(date, FORMATTER)));
                }
            } else if (line.equals("position")) {
                map.put(line, (scanTxt.next().trim()));

                jobPlaces.add((new JobPlace.JobPlaceBuilder())
                        .city(map.get("city").toString())
                        .company(map.get("company").toString())
                        .position(Position.getByName(map.get("position").toString()))
                        .from((LocalDate) map.get("from"))
                        .to((LocalDate) map.get("to"))
                        .build());
            } else if (line.equals("skill")){
                skills.add(Skill.getByName(scanTxt.next().trim()));
            }
        }
        scanTxt.close();

        cv.setJobPlaces(jobPlaces);
        cv.setContact(new Contact.ContactBuilder()
                .phoneNumber(map.get("phoneNumber").toString())
                .address(map.get("address").toString())
                .eMail(map.get("eMail").toString())
                .build());
        cv.setPerson(new Person.PersonBuilder()
                .firstName(map.get("firstName").toString())
                .lastName(map.get("lastName").toString())
                .birthday((LocalDate) map.get("birthday"))
                .build());
        cv.setSkills(skills);
        return cv;
    }
}
