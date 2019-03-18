package com.lelek.cv.service;

import com.lelek.cv.DAO.DBQuery;
import com.lelek.cv.DAO.FindCvListOfQueriesCreator;
import com.lelek.cv.model.CV;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//Create WEB
//Create Client-server
// Patterns
//out List of CV
//in List of CV

public class Main {

    private static final String file = "cv.txt";

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        CVRedactorFacade facade = new CVRedactorFacade(file);

//        facade.writeCVInDatabase();


//        facade.clearAllTables();

        List<CV> cvList = (new DBQuery()).readCVFromTable((new FindCvListOfQueriesCreator()).getAllFromTable());
        for (CV cv : cvList){
            System.out.println(cv.getPerson().getFirstName());
            System.out.println(cv.getPerson().getBirthday());
        }



    }
}
