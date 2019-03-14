package com.lelek.cv.service;

import com.lelek.cv.model.CV;

import java.io.IOException;
import java.sql.SQLException;

// Builder + validation = NUULOREMTY ?!?!!?
//Create DB
//Create WEB
//Create Client-server

public class Main {

    private static final String file = "cv.txt";

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        (new ValidateClass()).validate(readFromFile());
        writeInYaml(readFromFile());
//        DBConnection dbConnection = new DBConnection();
//        dbConnection.writeInTable(dbConnection.connect());
//        dbConnection.readFromTable(dbConnection.connect());

    }

    public static CV readFromFile() throws IOException {
        ReadFrom readFrom = new ReadFrom();
        return readFrom.readFile(file);
    }

    public static void writeInYaml(CV cv) throws IOException {
        WriterInFile writerInFile = new WriterInFile();
        writerInFile.writeInYmlFile(cv);
    }
}
