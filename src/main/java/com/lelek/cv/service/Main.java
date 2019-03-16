package com.lelek.cv.service;

import com.lelek.cv.DAO.DBQuery;
import com.lelek.cv.model.CV;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

//Create DB
//SELECT CV and writeInYml
//SELECT FLNames to List
//SELECT arbitrary fields to list
//Create WEB
//Create Client-server
//Add singleTone
// Розказати про паттерн Facade і продемонструвати приклад коду

public class Main {

    private static final String file = "cv.txt";

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        //Check in office
//        DBQuery dbQuery = new DBQuery();
//        dbQuery.writeCvInTable(readFromFile());

//        writeInYaml(readFromFile());
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
