package com.lelek.cv.dao;

import com.lelek.cv.exception.TableIsEmptyException;
import com.lelek.cv.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class DBQuery {

    private final String CLEAR_CONTACT = "DELETE FROM contact;";
    private final String CLEAR_JOB_PLACE = "DELETE FROM jobplace;";
    private final String CLEAR_SKILLS = "DELETE FROM skills;";
    private final String CLEAR_PERSON = "DELETE FROM person;";

    private final String DELETE_CONTACT = "DELETE FROM contact WHERE id = ?;";
    private final String DELETE_JOB_PLACE = "DELETE FROM jobplace WHERE id = ?;";
    private final String DELETE_SKILLS = "DELETE FROM skills WHERE id = ?;";
    private final String DELETE_PERSON = "DELETE FROM person WHERE id = ?;";

    private final String INSERT_PERSON =
            "INSERT INTO person (firstname, lastname, birthday) VALUES (?, ?, ?) RETURNING id";
    private final String INSERT_CONTACT = "INSERT INTO contact (phonenumber, address, email, id) VALUES (? ,? ,?, ?)";
    private final String INSERT_SKILLS = "INSERT INTO skills (id, skill) VALUES (? ,?)";
    private final String INSERT_JOBP_LACE = "INSERT INTO jobplace (company, city, from_date, " +
            "to_date, position_at, id) VALUES (?, ?, ?, ?, ?, ?)";

    private final String GET_PERSON_BY_ID = "SELECT * FROM person WHERE id = ?; ";
    private final String GET_CONTACT_BY_ID = "SELECT * FROM contact WHERE id = ?; ";
    private final String GET_JOB_PLACE_BY_ID = "SELECT * FROM jobplace WHERE id = ?; ";
    private final String GET_SKILLS_BY_ID = "SELECT * FROM skills WHERE id = ?; ";

    private Connection connect;

    public DBQuery() {
        try {
            Connection connect = DBConnection.getInstance().getConnection();
            this.connect = connect;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCv(int cvId) throws SQLException {
        PreparedStatement contactStatement = connect.prepareStatement(DELETE_JOB_PLACE);
        contactStatement.setInt(1, cvId);
        contactStatement.execute();
        PreparedStatement jobPlaceStatement = connect.prepareStatement(DELETE_CONTACT);
        jobPlaceStatement.setInt(1, cvId);
        jobPlaceStatement.execute();
        PreparedStatement skillsStatement = connect.prepareStatement(DELETE_SKILLS);
        skillsStatement.setInt(1, cvId);
        skillsStatement.execute();
        PreparedStatement personStatement = connect.prepareStatement(DELETE_PERSON);
        personStatement.setInt(1, cvId);
        personStatement.execute();
    }

    public void clearTables() throws SQLException {
        List<String> queries = new LinkedList<>();
        queries.add(CLEAR_CONTACT);
        queries.add(CLEAR_JOB_PLACE);
        queries.add(CLEAR_SKILLS);
        queries.add(CLEAR_PERSON);
        for (String query : queries) {
            connect.prepareStatement(query).execute();
        }
    }

    public void addCV(Cv cv) throws SQLException {
        int cvId = -1;
        PreparedStatement statement = connect.prepareStatement(INSERT_PERSON);
        statement.setString(1, cv.getPerson().getFirstName());
        statement.setString(2, cv.getPerson().getLastName());
        statement.setDate(3, java.sql.Date.valueOf(cv.getPerson().getBirthday()));
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            cvId = resultSet.getInt("id");
        } else {
            throw new TableIsEmptyException("TABLE IS EMPTY");
        }

        PreparedStatement statement2 = connect.prepareStatement(INSERT_CONTACT);
        statement2.setString(1, cv.getContact().getPhoneNumber());
        statement2.setString(2, cv.getContact().getAddress());
        statement2.setString(3, cv.getContact().geteMail());
        statement2.setInt(4, cvId);
        statement2.execute();

        for (JobPlace j : cv.getJobPlaces()) {
            PreparedStatement statement3 = connect.prepareStatement(INSERT_JOBP_LACE);
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

    public List<Cv> getAllCvs() throws SQLException {
        List<Cv> cvList = new ArrayList<>();
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM person");
        List<Integer> idList = new ArrayList<>();
        while (resultSet.next()){
            idList.add(resultSet.getInt("id"));
        }
        for (int id:idList) {
            cvList.add(getCv(id));
        }
        return cvList;
    }

    public Cv getCv(int cvId) throws SQLException {
        List<JobPlace> jobPlaces = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();
        Cv cv = new Cv();

        PreparedStatement personStatement = connect.prepareStatement(GET_PERSON_BY_ID);
        personStatement.setInt(1, cvId);
        ResultSet personResultSet = personStatement.executeQuery();
        personResultSet.next();
        cv.setPerson(new Person.PersonBuilder()
                .firstName(personResultSet.getString("firstname"))
                .lastName(personResultSet.getString("lastname"))
                .birthday(LocalDate.parse(personResultSet.getString("birthday")))
                .build());
        cv.setId(personResultSet.getInt("id"));

        PreparedStatement contactStatement = connect.prepareStatement(GET_CONTACT_BY_ID);
        contactStatement.setInt(1, cvId);
        ResultSet contactResultSet = contactStatement.executeQuery();
        if(contactResultSet.next()) {
            cv.setContact(new Contact.ContactBuilder()
                    .phoneNumber(contactResultSet.getString("phonenumber"))
                    .address(contactResultSet.getString("address"))
                    .eMail(contactResultSet.getString("email"))
                    .build());
        }

        PreparedStatement skillsStatement = connect.prepareStatement(GET_SKILLS_BY_ID);
        skillsStatement.setInt(1, cvId);
        ResultSet skillsResultSet = skillsStatement.executeQuery();
        while (skillsResultSet.next()) {
            skills.add(Skill.getByName(skillsResultSet.getString("skill")));
        }

        PreparedStatement jobPlaceStatement = connect.prepareStatement(GET_JOB_PLACE_BY_ID);
        jobPlaceStatement.setInt(1, cvId);
        ResultSet jobPlaceResultSet = jobPlaceStatement.executeQuery();
        while (jobPlaceResultSet.next()) {
            jobPlaces.add(new JobPlace.JobPlaceBuilder()
                    .city(jobPlaceResultSet.getString("city"))
                    .company(jobPlaceResultSet.getString("company"))
                    .from(jobPlaceResultSet.getDate("from_date").toLocalDate())
                    .to(jobPlaceResultSet.getDate("to_date").toLocalDate())
                    .position(Position.valueOf(jobPlaceResultSet.getString("position_at")))
                    .build());
        }
        cv.setSkills(skills);
        cv.setJobPlaces(jobPlaces);
        return cv;
    }
}
