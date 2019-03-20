package com.lelek.cv.service;

import com.lelek.cv.dao.DBQuery;
import com.lelek.cv.model.CV;

import java.io.IOException;
import java.sql.SQLException;

public class CvFacade {

    public CV getCvFromFile(String fileName) throws IOException {
        return (new ReadFrom()).readFile(fileName);
    }

    public void writeCvInFile(String fileName, CV cv) throws IOException {
        (new WriterInFile()).writeInYmlFile(fileName, cv);
    }

    public void writeCvInFile(CV cv) throws IOException {
        (new WriterInFile()).writeInYmlFile(cv);
    }

    public void writeCvInTable(CV cv) throws SQLException, ClassNotFoundException {
        (new DBQuery()).writeCvInTable(cv);
    }

    public void clearAllTables() throws SQLException, ClassNotFoundException {
        (new DBQuery()).clearTable();
    }

    public CV readCvFromTable(int cvId) throws SQLException, ClassNotFoundException {

        return new DBQuery().readCVFromTable(cvId);
    }
}
