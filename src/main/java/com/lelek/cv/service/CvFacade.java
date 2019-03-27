package com.lelek.cv.service;

import com.lelek.cv.dao.DBQuery;
import com.lelek.cv.model.Cv;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CvFacade {

    private static final String PATH = "C:/Users/vleletc/IdeaProjects/cv/src/main/resources/";

    public Cv readCvFromFile(String fileName) throws IOException {
        return new ReadFrom().readFile(fileName);
    }

    public void writeCvInFile(String fileName, Cv cv) throws IOException {
        new WriterInFile().writeInYmlFile(fileName, cv);
    }

    public void writeCvInTableFromTmpFile() throws IOException, SQLException, ClassNotFoundException {
        Cv cv = readCvFromFile(PATH + "temp.yml");
        new DBQuery().addCV(cv);
    }

    public void writeCvInFile(Cv cv) throws IOException {
        new WriterInFile().writeInYmlFile(cv);
    }

    public void writeCvInTable(Cv cv) throws SQLException, ClassNotFoundException {
        new DBQuery().addCV(cv);
    }

    public void clearAllTables() throws SQLException, ClassNotFoundException {
        new DBQuery().clearTables();
    }

    public Cv readCvFromTable(int cvId) throws SQLException, ClassNotFoundException {
        return new DBQuery().getCv(cvId);
    }

    public List<Cv> readAllCvFromTable() throws SQLException, ClassNotFoundException{
        return new DBQuery().getAllCvs();
    }

    public void deleteCvFromTable (int cvId){
        new DBQuery().deleteCvFromTable(cvId);
        System.out.println(cvId+"222222222222222222222222222222222222222222222222222222222222222");
    }
}
