package com.lelek.cv.service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.logging.Logger;

public class ValidateClass {

    private static final Logger LOGGER = Logger.getLogger("com.lelek.cv.service.WriteInFile");

    public void validate(Object object){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<Object>> personViolations = validatorFactory.getValidator().validate(object);
        for (ConstraintViolation<Object> personViolation : personViolations) {

            LOGGER.severe(personViolation.getMessage());
        }
    }
}
