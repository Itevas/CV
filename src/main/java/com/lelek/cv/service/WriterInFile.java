package com.lelek.cv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.lelek.cv.model.CV;
import com.lelek.cv.model.Contact;
import com.lelek.cv.model.JobPlace;
import com.lelek.cv.model.Person;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


class WriterInFile {

    private static final String OUT_PATH_YAML = "src/main/resources/out.yml";

    private static final Logger LOGGER = Logger.getLogger("com.lelek.cv.service.WriteInFile");

    static void writeInYmlFile(CV cv) throws IOException {

        List<JobPlace> jobPlaces = cv.getJobPlaces();


        Set<ConstraintViolation<Person>> personViolations = getValidator().validate(cv.getPerson());
        for (ConstraintViolation<Person> personViolation : personViolations){
            LOGGER.severe(personViolation.getMessage());
        }

        Set<ConstraintViolation<Contact>> contactViolations = getValidator().validate(cv.getContact());
        for (ConstraintViolation<Contact> contactViolation : contactViolations){
            LOGGER.severe(contactViolation.getMessage());
        }

        for (JobPlace jobPlace : jobPlaces){
            Set<ConstraintViolation<JobPlace>> jabPlaceViolations = getValidator().validate(jobPlace);
            for (ConstraintViolation<JobPlace> jobPlaceViolation : jabPlaceViolations){
                LOGGER.severe(jobPlaceViolation.getMessage());
            }
        }
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File(OUT_PATH_YAML), cv);
    }

    public static Validator getValidator(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }
}
