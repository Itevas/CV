package com.lelek.cv.service;

import com.lelek.cv.model.Cv;

import java.io.IOException;
import java.sql.SQLException;

//Add skills to UI "create"
//Navigate in Servlets
//Create War and launch from console
//Handle exceptions
//Dao cut
//logs
//check all for "sout"
//remove Main class

public class Main {

    private static final String PATH = "src/main/resources/out.yml";

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        CvFacade facade = new CvFacade();

//        Cv cv1 = facade.readCvFromFile("src/main/resources/cv.txt");
//        facade.writeCvInTable(cv1);
//        Cv cv2 = facade.readCvFromFile("src/main/resources/cv2.txt");
//        facade.writeCvInTable(cv2);
//        Cv cv3 = facade.readCvFromFile("src/main/resources/cv3.txt");
//        facade.writeCvInTable(cv3);
//        Cv cv4 = facade.readCvFromFile("src/main/resources/cv4.txt");
//        facade.writeCvInTable(cv4);



//        facade.clearTables();

    }
}
