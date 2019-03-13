package com.lelek.cv.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lelek.cv.service.LocalDateDeserializer;
import com.lelek.cv.service.LocalDateSerializer;
import com.sun.istack.internal.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class JobPlace {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    @NotNull
    private String company;

    @NotNull
    private String city;

    @Past
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate from;

    @PastOrPresent
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate to;

    private boolean currentJob;

    @NotNull
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

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setTo(LocalDate to) {
        if (to.equals(LocalDate.now())) {
            currentJob = true;
        } else {
            currentJob = false;
        }
        this.to = to;
    }

    public LocalDate getTo() {
        if (currentJob) {
            return to = LocalDate.now();
        } else {
            return to;
        }
    }
}