package com.lelek.cv.service;

import com.lelek.cv.DAO.DBQuery;
import com.lelek.cv.model.CV;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CVRedactorFacade {
    private CV cv;

    public CVRedactorFacade(String fileName) throws IOException {
        cv = (new ReadFrom()).readFile(fileName);
    }

    public CV getCV() {
        return cv;
    }

    public void writeCV() throws IOException {
        (new WriterInFile()).writeInYmlFile(cv);
    }

    public void writeCVInDatabase() throws SQLException, ClassNotFoundException{
        (new DBQuery()).writeCvInTable(cv);
    }

    public void clearAllTables() throws SQLException, ClassNotFoundException{
        (new DBQuery()).clearTable();
    }

    public List<CV> readCVFromDatabase(String cvId) throws SQLException, ClassNotFoundException{
        return ((new DBQuery()).readCVFromTable(cvId));
    }
}
