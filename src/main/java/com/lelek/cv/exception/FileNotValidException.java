package com.lelek.cv.exception;

public class FileNotValidException extends RuntimeException {
    public FileNotValidException(String errorMessage){
        super(errorMessage);
    }
}
