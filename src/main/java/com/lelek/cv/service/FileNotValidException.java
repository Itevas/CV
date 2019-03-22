package com.lelek.cv.service;

public class FileNotValidException extends RuntimeException {
    public FileNotValidException(String errorMessage){
        super(errorMessage);
    }
}
