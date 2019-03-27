package com.lelek.cv.service;

import com.lelek.cv.dao.DBQuery;
import com.lelek.cv.model.CV;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CvFacade {

    private static final String PATH = "C:/Users/vleletc/IdeaProjects/cv/src/main/resources/";

    public CV readCvFromFile(String fileName) throws IOException {
        return new ReadFrom().readFile(fileName);
    }

    public void writeCvInFile(String fileName, CV cv) throws IOException {
        new WriterInFile().writeInYmlFile(fileName, cv);
    }

    public void writeCvInTableFromTmpFile() throws IOException, SQLException, ClassNotFoundException {
        CV cv = readCvFromFile(PATH + "temp.yml");
        new DBQuery().writeCvInTable(cv);
    }

    public void writeCvInFile(CV cv) throws IOException {
        new WriterInFile().writeInYmlFile(cv);
    }

    public void writeCvInTable(CV cv) throws SQLException, ClassNotFoundException {
        new DBQuery().writeCvInTable(cv);
    }

    public void clearAllTables() throws SQLException, ClassNotFoundException {
        new DBQuery().clearTable();
    }

    public CV readCvFromTable(int cvId) throws SQLException, ClassNotFoundException {
        return new DBQuery().readCvFromTable(cvId);
    }

    public List<CV> readAllCvFromTable() throws SQLException, ClassNotFoundException{
        return new DBQuery().readAllCvFromTable();
    }

    public void deleteCvFromTable (int cvId){
        new DBQuery().deleteCvFromTable(cvId);
        System.out.println(cvId+"222222222222222222222222222222222222222222222222222222222222222");
    }
}
