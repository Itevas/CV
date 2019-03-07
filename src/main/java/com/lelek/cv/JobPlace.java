package com.lelek.cv;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JobPlace {
    private String company;
    private String city;
    private LocalDate from;
    private LocalDate to;
    private Position position;

    private enum Position {
        Developer, DevOps, QAEngineer
    }

    public void setCompany(String company) {
        this.company = company;
        PropertiesMap.addToMapProperties("company", company);
    }

    public String getCompany() {
        return company;
    }

    public void setCity(String city) {
        this.city = city;
        PropertiesMap.addToMapProperties("city", city);
    }

    public String getCity() {
        return city;
    }

    public void setPosition(Position position) {
        this.position = position;
        PropertiesMap.addToMapProperties("position", position.name());
    }

    public Position getPosition() {
        return position;
    }

    public void setFrom(String fromSt) {
        from = LocalDate.parse(fromSt, DateTimeFormatter.ofPattern("MM.uuuu"));
        PropertiesMap.addToMapProperties("from", from.toString());
    }

    public String getFrom() {
        if(from == null){return null;}
        return from.format(DateTimeFormatter.ofPattern("MM.uuuu"));
    }

    public void setTo(String toSt) {
        to = LocalDate.parse(toSt, DateTimeFormatter.ofPattern("MM.uuuu"));
        PropertiesMap.addToMapProperties("to", to.toString());
    }

    public String getTo() {
        if(to == null){return null;}
        return to.format(DateTimeFormatter.ofPattern("MM.uuuu"));
    }
}