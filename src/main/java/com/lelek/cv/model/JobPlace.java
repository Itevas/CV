package com.lelek.cv.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class JobPlace {
    private String company;
    private String city;
    private LocalDate from;
    private LocalDate to;
    private boolean currentJob;
    private Position position;

    enum Position {
        Developer, DevOps, QAEngineer
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setPosition(String position) {
        this.position = Position.valueOf(position);
    }

    public Position getPosition() {
        return position;
    }

    public void setFrom(String fromSt) {
        from = LocalDate.parse(fromSt, DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    }

    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    }

    public void setTo(String toSt) {
        if (toSt.equals("This time")) {
            currentJob = true;
            to = LocalDate.parse("01.01.1971", DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        } else {
            currentJob = false;
            to = LocalDate.parse(toSt, DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        }
    }

    public String getTo() {
        if (currentJob) {
            return "This time";
        } else {
            return to.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        }
    }
}