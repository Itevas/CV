package com.lelek.cv.dao;

import com.lelek.cv.model.CV;
import com.lelek.cv.model.Contact;
import com.lelek.cv.model.JobPlace;
import com.lelek.cv.model.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class DBQuery {

    private final String GET_CV_NUMBER = "SELECT MAX(cv_id) FROM cv;";
    private final String GET_JOBPLACE_NUMBER = "SELECT MAX(jobplace_id) FROM jobplace;";

    private final String CLEAR_CONTACT = "DELETE FROM contact;";
    private final String CLEAR_JOBPLACE = "DELETE FROM jobplace;";
    private final String CLEAR_CV = "DELETE FROM cv;";
    private final String CLEAR_PERSON = "DELETE FROM person;";

    private final String INSERT_PERSON = "INSERT INTO person (id, firstname, lastname, birthday) VALUES (?, ?, ?, ?)";
    private final String INSERT_CV = "INSERT INTO cv (cv_id, person_id) VALUES (?, ?)";
    private final String INSERT_CONTACT = "INSERT INTO contact (id, phonenumber, address, email) Values (?, ? ,? ,?)";
    private final String INSERT_JOBPLACE = "INSERT INTO jobplace (jobplace_id, id, company, city, from_date, " +
            "to_date, position_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private final String GET_PERSON_BY_ID = "SELECT * FROM person WHERE id = ?; ";
    private final String GET_CONTACT_BY_ID = "SELECT * FROM contact WHERE id = ?; ";
    private final String GET_JOBPLACE_BY_ID = "SELECT * FROM jobplace WHERE id = ?; ";

    private final int INDEX = 1;
    private int cvNumber, jobPlaceNumber;
    private Connection connect;

    public DBQuery(){
        try {
            Connection connect = DBConnection.getInstance().getConnection();
            this.connect = connect;
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void clearTable() throws SQLException, ClassNotFoundException {
        List<String> queries = new LinkedList<>();
        queries.add(CLEAR_CONTACT);
        queries.add(CLEAR_JOBPLACE);
        queries.add(CLEAR_CV);
        queries.add(CLEAR_PERSON);
        for (String query : queries) {
            connect.prepareStatement(query).execute();
        }
    }

    public void writeCvInTable(CV cv) throws SQLException, ClassNotFoundException {
        Statement statementR1 = connect.createStatement();
        ResultSet cvNumberResult = statementR1.executeQuery(GET_CV_NUMBER);
        while (cvNumberResult.next()) {
            cvNumber = cvNumberResult.getInt(INDEX) + 1;
        }
        Statement statementR2 = connect.createStatement();
        ResultSet jobPlaceNumberResult = statementR2.executeQuery(GET_JOBPLACE_NUMBER);
        while (jobPlaceNumberResult.next()) {
            jobPlaceNumber = jobPlaceNumberResult.getInt(INDEX) + 1;
        }

        PreparedStatement statement = connect.prepareStatement(INSERT_PERSON);
        statement.setInt(1, cvNumber);
        statement.setString(2, cv.getPerson().getFirstName());
        statement.setString(3, cv.getPerson().getLastName());
        statement.setDate(4, java.sql.Date.valueOf(cv.getPerson().getBirthday()));
        statement.execute();

        PreparedStatement statement1 = connect.prepareStatement(INSERT_CV);
        statement1.setInt(1, cvNumber);
        statement1.setInt(2, cvNumber);
        statement1.execute();

        PreparedStatement statement2 = connect.prepareStatement(INSERT_CONTACT);
        statement2.setInt(1, cvNumber);
        statement2.setString(2, cv.getContact().getPhoneNumber());
        statement2.setString(3, cv.getContact().getAddress());
        statement2.setString(4, cv.getContact().geteMail());
        statement2.execute();

        for (JobPlace j : cv.getJobPlaces()) {
            PreparedStatement statement3 = connect.prepareStatement(INSERT_JOBPLACE);
            statement3.setInt(1, jobPlaceNumber++);
            statement3.setInt(2, cvNumber);
            statement3.setString(3, j.getCompany());
            statement3.setString(4, j.getCity());
            statement3.setDate(5, java.sql.Date.valueOf(j.getFrom()));
            statement3.setDate(6, java.sql.Date.valueOf(j.getTo()));
            statement3.setString(7, j.getPosition().toString());
            statement3.execute();
        }
    }

    public List<CV> readAllCvFromTable() throws SQLException, ClassNotFoundException{
        List<CV> cvList = new ArrayList<>();
        Statement readStatement = connect.createStatement();
        ResultSet cvNumberResult = readStatement.executeQuery(GET_CV_NUMBER);
        while (cvNumberResult.next()) {
            cvNumber = cvNumberResult.getInt(INDEX);
        }
        for (int i = 1; i<= cvNumber; i++){
            cvList.add(readCvFromTable(i));
        }
        return cvList;
    }

    public CV readCvFromTable(int cvId) throws SQLException, ClassNotFoundException {
        List<JobPlace> jobPlaces = new ArrayList<>();
        Map<String, Object> cvMap = new HashMap<>();
        CV cv = new CV();

        PreparedStatement personStatement = connect.prepareStatement(GET_PERSON_BY_ID);
        personStatement.setInt(1, cvId);
        ResultSet personResultSet = personStatement.executeQuery();
        ResultSetMetaData personMetaData = personResultSet.getMetaData();
        while (personResultSet.next()) {
            for (int i = 1; i <= personMetaData.getColumnCount(); i++) {
                cvMap.put(personMetaData.getColumnName(i), personResultSet.getObject(i));
            }
            cv.setPerson(new Person.PersonBuilder()
                    .firstName(cvMap.get("firstname").toString())
                    .lastName(cvMap.get("lastname").toString())
                    .birthday(LocalDate.parse((cvMap.get("birthday")).toString()))
                    .build());
            cvMap.clear();
        }

        PreparedStatement contactStatement = connect.prepareStatement(GET_CONTACT_BY_ID);
        contactStatement.setInt(1, cvId);
        ResultSet contactResultSet = contactStatement.executeQuery();
        ResultSetMetaData contactMetaData = contactResultSet.getMetaData();
        while (contactResultSet.next()) {
            for (int i = 1; i <= contactMetaData.getColumnCount(); i++) {
                cvMap.put(contactMetaData.getColumnName(i), contactResultSet.getObject(i));
            }
            cv.setContact(new Contact.ContactBuilder()
                    .phoneNumber(cvMap.get("phonenumber").toString())
                    .address(cvMap.get("address").toString())
                    .eMail(cvMap.get("email").toString())
                    .build());
        }

        PreparedStatement jobPlaceStatement = connect.prepareStatement(GET_JOBPLACE_BY_ID);
        jobPlaceStatement.setInt(1, cvId);
        ResultSet jobPlaceResultSet = jobPlaceStatement.executeQuery();
        ResultSetMetaData jobPlaceMetaData = jobPlaceResultSet.getMetaData();
        while (jobPlaceResultSet.next()) {
            for (int i = 1; i <= jobPlaceMetaData.getColumnCount(); i++) {
                cvMap.put(jobPlaceMetaData.getColumnName(i), jobPlaceResultSet.getObject(i));
            }
            jobPlaces.add((new JobPlace.JobPlaceBuilder())
                    .city(cvMap.get("city").toString())
                    .company(cvMap.get("company").toString())
                    .from(LocalDate.parse((cvMap.get("from_date")).toString()))
                    .to(LocalDate.parse((cvMap.get("to_date")).toString()))
                    .position(cvMap.get("position_at").toString())
                    .build());

        }
        cv.setJobPlaces(jobPlaces);
        return cv;
    }
}
