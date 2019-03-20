package com.lelek.cv.dao;

import com.lelek.cv.model.CV;
import com.lelek.cv.model.Contact;
import com.lelek.cv.model.JobPlace;
import com.lelek.cv.model.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import static com.lelek.cv.dao.FindCvListOfQueriesCreator.getByIdFromTable;

public class DBQuery {

    private Connection connect;

    public DBQuery() {
        try {
            this.connect = DBConnection.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeCvInTable(CV cv) throws SQLException, ClassNotFoundException {

        Statement statement = connect.createStatement();
        List<String> queries = (new WriteListOfQueriesCreator(cv)).getListOfQueries();
        for (String query : queries) {
            statement.executeUpdate(query);
        }
        statement.close();
        connect.close();
    }

    public void clearTable() throws SQLException {
        Statement statement = connect.createStatement();
        List<String> queries = (new ClearListOfQueries().clearTables());
        for (String query : queries) {
            statement.executeUpdate(query);
        }
    }

    public void removeCvFromTable(String cvId) {

    }

    public ResultSet readFromTable(String query) throws SQLException {
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public CV readCVFromTable(int cvId) throws SQLException {
        List<JobPlace> jobPlaces = new ArrayList<>();
        CV cv = new CV();
        String query = getByIdFromTable(cvId);
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData metaData = resultSet.getMetaData();
        Map<String, Object> cvMap = new HashMap<>();

        while (resultSet.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                cvMap.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            jobPlaces.add((new JobPlace.JobPlaceBuilder())
                    .city(cvMap.get("city").toString())
                    .company(cvMap.get("company").toString())
                    .from(LocalDate.parse((cvMap.get("from")).toString()))
                    .to(LocalDate.parse((cvMap.get("to")).toString()))
                    .position(cvMap.get("position").toString())
                    .build());
            cv.setContact(new Contact.ContactBuilder()
                    .phoneNumber(cvMap.get("phonenumber").toString())
                    .address(cvMap.get("address").toString())
                    .eMail(cvMap.get("eMail").toString())
                    .build());
            cv.setPerson(new Person.PersonBuilder()
                    .firstName(cvMap.get("firstname").toString())
                    .lastName(cvMap.get("lastname").toString())
                    .birthday(LocalDate.parse((cvMap.get("birthday")).toString()))
                    .build());
        }
        cv.setJobPlaces(jobPlaces);
        return cv;
    }
}
