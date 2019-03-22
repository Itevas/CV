package com.lelek.cv.service;

import com.lelek.cv.model.CV;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

//Create WEB
//Create Client-server
//Patterns

public class Main {

    private static final String PATH = "src/main/resources/out.yml";

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        CvFacade facade = new CvFacade();

//        facade.writeCvInFile(PATH, cv);
//        facade.writeCvInTable(facade.getCvFromFile("src/main/resources/cv.txt"));
//        facade.writeCvInTable(facade.getCvFromFile("src/main/resources/cv.txt"));

//        for (int i = 0; i < 20; i++) {
//            System.out.println(i + " " + facade.readCvFromTable(i));
//        }

//        facade.clearAllTables();


    }
}
