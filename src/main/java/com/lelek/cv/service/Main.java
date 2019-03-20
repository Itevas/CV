package com.lelek.cv.service;

import com.lelek.cv.model.CV;

import java.io.IOException;
import java.sql.SQLException;

//Create WEB
//Create Client-server
//Patterns

public class Main {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        CvFacade facade = new CvFacade();
//        facade.writeCvInTable(facade.getCvFromFile("cv2.txt"));
//        facade.writeCvInTable(facade.getCvFromFile("cv.txt"));

//        facade.writeCvInFile(facade.readCvFromTable(1));

//        facade.clearAllTables();


    }
}
