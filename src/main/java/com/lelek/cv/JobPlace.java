package com.lelek.cv;

import java.time.LocalDate;

public class JobPlace {
    private String company;
    private String city;
    private LocalDate yearMonthStart;
    private LocalDate yearMonthEnd;
    private Position position;

    private enum Position {
        Developer, DevOps, QAEngineer;
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

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setYearMonthStart(LocalDate yearMonthStart) {
        this.yearMonthStart = yearMonthStart;
    }

    public LocalDate getYearMonthStart() {
        return yearMonthStart;
    }

    public void setYearMonthEnd(LocalDate yearMonthEnd) {
        this.yearMonthEnd = yearMonthEnd;
    }

    public LocalDate getYearMonthEnd() {
        return yearMonthEnd;
    }
}