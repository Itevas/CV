package com.lelek.cv.service;

import java.io.IOException;
import java.sql.SQLException;

// Builder + validation
// test
// pull request
// List<CV> from one file
//http://zetcode.com/java/postgresql/
//http://josql.sourceforge.net/

public class Main {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

 //       readFrom("cv.xml");
        DBConnection dbConnection = new DBConnection();

        dbConnection.writeInTable(dbConnection.connect());

        dbConnection.readFromTable(dbConnection.connect());


    }
}
