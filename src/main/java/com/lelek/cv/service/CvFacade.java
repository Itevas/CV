package com.lelek.cv.service;

import com.lelek.cv.dao.DBQuery;
import com.lelek.cv.dao.DaoCv;
import com.lelek.cv.dao.DaoList;
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

    public void writeCvInTableFromTmpFile() throws IOException, SQLException {
        Cv cv = readCvFromFile(PATH + "temp.yml");
        new DBQuery().add(cv);
    }

    public void writeCvInFile(Cv cv) throws IOException {
        new WriterInFile().writeInYmlFile(cv);
    }

    public void writeCvInTable(Cv cv) throws SQLException {
        new DBQuery().add(cv);
    }

    public void clearTables() throws SQLException {
        new DBQuery().clear();
    }

    public Cv get(int cvId) throws SQLException {
        return new DaoCv().get(cvId);
    }

    public List<Cv> get() throws SQLException{
        return new DaoList().get();
    }

    public void delete(int cvId) throws SQLException {
        new DBQuery().delete(cvId);
    }
}
