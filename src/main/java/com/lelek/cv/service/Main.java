package com.lelek.cv.service;

import java.io.IOException;

import static com.lelek.cv.service.ReadFrom.readFrom;

// Builder + validation
// test
// pull request
// List<CV> from one file

public class Main {

    public static void main(String[] args) throws IOException{

        readFrom("cv.xml");
//        DBConnection dbConnection = new DBConnection();
//        dbConnection.connect();

    }
}
