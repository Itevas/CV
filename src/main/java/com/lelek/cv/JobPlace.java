package com.lelek.cv;

import java.time.LocalDate;

public class JobPlace {
    private String company;
    private String city;
    private LocalDate from;
    private LocalDate to;
    private Position position;

    private enum Position {
        Developer, DevOps, QAEngineer;
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

    public void setYearMonthStart(LocalDate yearMonthStart) {
        this.from = yearMonthStart;
        PropertiesMap.addToMapProperties("from", from.toString());
    }

    public LocalDate getYearMonthStart() {
        return from;
    }

    public void setYearMonthEnd(LocalDate yearMonthEnd) {
        this.to = yearMonthEnd;
        PropertiesMap.addToMapProperties("to", to.toString());
    }

    public LocalDate getYearMonthEnd() {
        return to;
    }
}