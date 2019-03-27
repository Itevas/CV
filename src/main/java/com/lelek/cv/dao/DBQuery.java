package com.lelek.cv.dao;

import com.lelek.cv.model.*;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class DBQuery {

    private final String CLEAR_CONTACT = "DELETE FROM contact;";
    private final String CLEAR_JOBPLACE = "DELETE FROM jobplace;";
    private final String CLEAR_CV = "DELETE FROM cv;";
    private final String CLEAR_PERSON = "DELETE FROM person;";

    private final String INSERT_PERSON = "INSERT INTO person (firstname, lastname, birthday) VALUES (?, ?, ?)";
    private final String INSERT_CV = "INSERT INTO cv (person_id) VALUES (?)";
    private final String INSERT_CONTACT = "INSERT INTO contact (phonenumber, address, email) VALUES (? ,? ,?)";
    private final String INSERT_SKILLS = "INSERT INTO skills (id, skill) VALUES (? ,?)";
    private final String INSERT_JOBPLACE = "INSERT INTO jobplace (company, city, from_date, " +
            "to_date, position_at, id) VALUES (?, ?, ?, ?, ?, ?)";

    private final String GET_PERSON_BY_ID = "SELECT * FROM person WHERE id = ?; ";
    private final String GET_CONTACT_BY_ID = "SELECT * FROM contact WHERE id = ?; ";
    private final String GET_JOBPLACE_BY_ID = "SELECT * FROM jobplace WHERE id = ?; ";

    private Connection connect;
    private int cvId = -1;

    public DBQuery() {
        try {
            Connection connect = DBConnection.getInstance().getConnection();
            this.connect = connect;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCvFromTable(int cvId) {

    }

    public void clearTables() throws SQLException {
        List<String> queries = new LinkedList<>();
        queries.add(CLEAR_CONTACT);
        queries.add(CLEAR_JOBPLACE);
        queries.add(CLEAR_CV);
        queries.add(CLEAR_PERSON);
        for (String query : queries) {
            connect.prepareStatement(query).execute();
        }
    }

    public void addCV(Cv cv) throws SQLException {
        PreparedStatement statement = connect.prepareStatement(INSERT_PERSON);
        statement.setString(1, cv.getPerson().getFirstName());
        statement.setString(2, cv.getPerson().getLastName());
        statement.setDate(3, java.sql.Date.valueOf(cv.getPerson().getBirthday()));
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if(resultSet.next()) {
            cvId = resultSet.getInt("id");
            System.out.println(cvId);
        } else {
            System.out.println("ID ERROR !!");
        }

        PreparedStatement statement1 = connect.prepareStatement(INSERT_CV);
        statement1.setInt(1, cvId);
        statement1.execute();

        PreparedStatement statement2 = connect.prepareStatement(INSERT_CONTACT);
        statement2.setString(1, cv.getContact().getPhoneNumber());
        statement2.setString(2, cv.getContact().getAddress());
        statement2.setString(3, cv.getContact().geteMail());
        statement2.execute();

        for (JobPlace j : cv.getJobPlaces()) {
            PreparedStatement statement3 = connect.prepareStatement(INSERT_JOBPLACE);
            statement3.setString(1, j.getCompany());
            statement3.setString(2, j.getCity());
            statement3.setDate(3, java.sql.Date.valueOf(j.getFrom()));
            statement3.setDate(4, java.sql.Date.valueOf(j.getTo()));
            statement3.setString(5, j.getPosition().toString());
            statement3.setInt(6, cvId);
            statement3.execute();
        }
        for (Skill skill : cv.getSkills()) {
            PreparedStatement statement4 = connect.prepareStatement(INSERT_SKILLS);
            statement4.setInt(1, cvId);
            statement4.setString(2, skill.getSkill());
            statement4.execute();
        }
    }

    @Deprecated
    public List<Cv> getAllCvs() throws SQLException {
        List<Cv> cvList = new ArrayList<>();
        int cvId = connect.createStatement().executeQuery("SELECT MAX(id) FROM person;").getInt("id");
        for (int i = 1; i <= cvId; i++) {
            cvList.add(getCv(i));
        }
        return cvList;
    }

    public Cv getCv(int cvId) throws SQLException {
        List<JobPlace> jobPlaces = new ArrayList<>();
        Cv cv = new Cv();

        PreparedStatement personStatement = connect.prepareStatement(GET_PERSON_BY_ID);
        personStatement.setInt(1, cvId);
        ResultSet personResultSet = personStatement.executeQuery();
        cv.setId(personResultSet.getInt("id"));
        cv.setPerson(new Person.PersonBuilder()
                .firstName(personResultSet.getString("firstname"))
                .lastName(personResultSet.getString("lastname"))
                .birthday(LocalDate.parse(personResultSet.getString("birthday")))
                .build());

        PreparedStatement contactStatement = connect.prepareStatement(GET_CONTACT_BY_ID);
        contactStatement.setInt(1, cvId);
        ResultSet contactResultSet = contactStatement.executeQuery();
        cv.setContact(new Contact.ContactBuilder()
                .phoneNumber(contactResultSet.getString("phonenumber"))
                .address(contactResultSet.getString("address"))
                .eMail(contactResultSet.getString("email"))
                .build());

        PreparedStatement jobPlaceStatement = connect.prepareStatement(GET_JOBPLACE_BY_ID);
        jobPlaceStatement.setInt(1, cvId);
        ResultSet jobPlaceResultSet = jobPlaceStatement.executeQuery();
        while (jobPlaceResultSet.next()) {
            jobPlaces.add((new JobPlace.JobPlaceBuilder())
                    .city(jobPlaceResultSet.getString("city"))
                    .company(jobPlaceResultSet.getString("company"))
                    .from(jobPlaceResultSet.getDate("from_date").toLocalDate())
                    .to(jobPlaceResultSet.getDate("to_date").toLocalDate())
                    .position(Position.valueOf(jobPlaceResultSet.getString("position")))
                    .build());
        }
        cv.setJobPlaces(jobPlaces);
        return cv;
    }
}
