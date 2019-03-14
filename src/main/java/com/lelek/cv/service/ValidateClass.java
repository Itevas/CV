package com.lelek.cv.service;

import com.lelek.cv.model.CV;
import com.lelek.cv.model.Contact;
import com.lelek.cv.model.JobPlace;
import com.lelek.cv.model.Person;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class ValidateClass {

    private static final Logger LOGGER = Logger.getLogger("com.lelek.cv.service.WriteInFile");

    public void validate(CV cv) {

        List<JobPlace> jobPlaces = cv.getJobPlaces();

        Set<ConstraintViolation<Person>> personViolations = getValidator().validate(cv.getPerson());
        for (ConstraintViolation<Person> personViolation : personViolations) {
            LOGGER.severe(personViolation.getMessage());
        }

        Set<ConstraintViolation<Contact>> contactViolations = getValidator().validate(cv.getContact());
        for (ConstraintViolation<Contact> contactViolation : contactViolations) {
            LOGGER.severe(contactViolation.getMessage());
        }

        for (JobPlace jobPlace : jobPlaces) {
            Set<ConstraintViolation<JobPlace>> jabPlaceViolations = getValidator().validate(jobPlace);
            for (ConstraintViolation<JobPlace> jobPlaceViolation : jabPlaceViolations) {
                LOGGER.severe(jobPlaceViolation.getMessage());
            }
        }

    }

    public static Validator getValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }
}
