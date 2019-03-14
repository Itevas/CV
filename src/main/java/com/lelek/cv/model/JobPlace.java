package com.lelek.cv.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lelek.cv.service.LocalDateDeserializer;
import com.lelek.cv.service.LocalDateSerializer;
import com.lelek.cv.service.ValidateClass;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;



public class JobPlace {

    private JobPlace(){}

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

    private static boolean currentJob;

    @NotNull
    private Position position;

    public enum Position {
        Developer, DevOps, QAEngineer
    }

    public String getCompany() {
        return company;
    }

    public String getCity() {
        return city;
    }

    public Position getPosition() {
        return position;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        if (currentJob) {
            return to = LocalDate.now();
        } else {
            return to;
        }
    }
    public static class JobPlaceBuilder{

        private JobPlace jobPlace;

        public JobPlaceBuilder(){
            jobPlace = new JobPlace();
        }

        public JobPlaceBuilder setCompany(String company) {
            jobPlace.company = company;
            return this;
        }

        public JobPlaceBuilder  setCity(String city) {
            jobPlace.city = city;
            return this;
        }

        public JobPlaceBuilder  setPosition(String position) {
            jobPlace.position = Position.valueOf(position);
            return this;
        }

        public JobPlaceBuilder setFrom(LocalDate from) {
            jobPlace.from = from;
            return this;
        }

        public JobPlaceBuilder setTo(LocalDate to) {
            if (to.equals(LocalDate.now())) {
                currentJob = true;
            } else {
                currentJob = false;
            }
            jobPlace.to = to;
            return this;
        }

        public JobPlace build(){
            (new ValidateClass()).validate(jobPlace);
            return jobPlace;
        }
    }
}