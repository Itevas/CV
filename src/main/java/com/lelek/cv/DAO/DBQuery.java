package com.lelek.cv.DAO;

import com.lelek.cv.model.CV;
import com.lelek.cv.model.Contact;
import com.lelek.cv.model.JobPlace;
import com.lelek.cv.model.Person;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class DBQuery {

    private Connection connect;

    public DBQuery() throws ClassNotFoundException {
        try {
            this.connect = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
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
        List<String> queries = (new ClearListOfQueriesCreator().clearTables());
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

    public List<CV> readCVFromTable(String query) throws SQLException {
        List<JobPlace> jobPlaces = new ArrayList<>();
        List<CV> cvList = new LinkedList<>();
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData metaData = resultSet.getMetaData();
        Map<String, Object> cvMap = new HashMap<>();
        int k = 0;

        while (resultSet.next()) {

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                cvMap.put(metaData.getColumnName(i), resultSet.getObject(i));
                if (metaData.getColumnName(i).equals("position")) {
                    jobPlaces.add((new JobPlace.JobPlaceBuilder())
                            .setCity(cvMap.get("city").toString())
                            .setCompany(cvMap.get("company").toString())
                            .setPosition(cvMap.get("position").toString())
                            .setFrom(LocalDate.parse((cvMap.get("from")).toString()))
                            .setTo(LocalDate.parse((cvMap.get("to")).toString()))
                            .build());
                }

            }

            cvList.add(new CV());
            cvList.get(k).setJobPlaces(jobPlaces);
            cvList.get(k).setContact(new Contact.ContactBuilder()
                    .setPhoneNumber(cvMap.get("phonenumber").toString())
                    .setAddress(cvMap.get("address").toString())
                    .seteMail(cvMap.get("eMail").toString())
                    .build());
            cvList.get(k).setPerson(new Person.PersonBuilder()
                    .setFirstName(cvMap.get("firstname").toString())
                    .setLastName(cvMap.get("lastname").toString())
                    .setBirthday(LocalDate.parse((cvMap.get("birthday")).toString()))
                    .build());
            cvMap.clear();

            k++;


        }

        return cvList;
    }
}
