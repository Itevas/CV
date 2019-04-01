package com.lelek.cv.dao;

import com.lelek.cv.exception.DaoException;
import com.lelek.cv.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class DBQuery {

    private final String CLEAR_CONTACT = "DELETE FROM contact;";
    private final String CLEAR_JOB_PLACE = "DELETE FROM job_place;";
    private final String CLEAR_SKILLS = "DELETE FROM skills;";
    private final String CLEAR_PERSON = "DELETE FROM person;";

    private final String DELETE_CONTACT = "DELETE FROM contact WHERE person_id = ?;";
    private final String DELETE_JOB_PLACE = "DELETE FROM job_place WHERE person_id = ?;";
    private final String DELETE_SKILLS = "DELETE FROM skills WHERE person_id = ?;";
    private final String DELETE_PERSON = "DELETE FROM person WHERE id = ?;";

    private final String INSERT_PERSON =
            "INSERT INTO person (first_name, last_name, birthday) VALUES (?, ?, ?) RETURNING id";
    private final String INSERT_CONTACT = "INSERT INTO contact (phone_number, address, e_mail, person_id) VALUES (? ,? ,?, ?)";
    private final String INSERT_SKILLS = "INSERT INTO skills (person_id, skill) VALUES (? ,?)";
    private final String INSERT_JOBP_LACE = "INSERT INTO job_place (company, city, from_date, " +
            "to_date, position_at, person_id) VALUES (?, ?, ?, ?, ?, ?)";

    private final String GET_PERSON = "SELECT * FROM person WHERE id = ?; ";
    private final String GET_CONTACT = "SELECT * FROM contact WHERE person_id = ?; ";
    private final String GET_JOB_PLACE = "SELECT * FROM job_place WHERE person_id = ?; ";
    private final String GET_SKILLS = "SELECT * FROM skills WHERE person_id = ?; ";

    private Connection connect;

    public DBQuery() {
        try {
            Connection connect = DBConnection.getInstance().getConnection();
            this.connect = connect;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int cvId) throws SQLException {
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

    public void clear() throws SQLException {
        List<String> queries = new LinkedList<>();
        queries.add(CLEAR_CONTACT);
        queries.add(CLEAR_JOB_PLACE);
        queries.add(CLEAR_SKILLS);
        queries.add(CLEAR_PERSON);
        for (String query : queries) {
            connect.prepareStatement(query).execute();
        }
    }

    public void add(Cv cv) throws SQLException {
        int cvId = -1;
        PreparedStatement personStatement = connect.prepareStatement(INSERT_PERSON);
        personStatement.setString(1, cv.getPerson().getFirstName());
        personStatement.setString(2, cv.getPerson().getLastName());
        personStatement.setDate(3, java.sql.Date.valueOf(cv.getPerson().getBirthday()));
        ResultSet resultSet = personStatement.executeQuery();

        if (resultSet.next()) {
            cvId = resultSet.getInt("id");
        } else {
            throw new DaoException("TABLE IS EMPTY");
        }

        PreparedStatement contactStatement = connect.prepareStatement(INSERT_CONTACT);
        contactStatement.setString(1, cv.getContact().getPhoneNumber());
        contactStatement.setString(2, cv.getContact().getAddress());
        contactStatement.setString(3, cv.getContact().geteMail());
        contactStatement.setInt(4, cvId);
        contactStatement.execute();

        for (JobPlace jobPlace : cv.getJobPlaces()) {
            PreparedStatement jobPlaceStatement = connect.prepareStatement(INSERT_JOBP_LACE);
            jobPlaceStatement.setString(1, jobPlace.getCompany());
            jobPlaceStatement.setString(2, jobPlace.getCity());
            jobPlaceStatement.setDate(3, java.sql.Date.valueOf(jobPlace.getFrom()));
            jobPlaceStatement.setDate(4, java.sql.Date.valueOf(jobPlace.getTo()));
            jobPlaceStatement.setString(5, jobPlace.getPosition().toString());
            jobPlaceStatement.setInt(6, cvId);
            jobPlaceStatement.execute();
        }
        for (Skill skill : cv.getSkills()) {
            PreparedStatement skillStatement = connect.prepareStatement(INSERT_SKILLS);
            skillStatement.setInt(1, cvId);
            skillStatement.setString(2, skill.getSkill());
            skillStatement.execute();
        }
    }

    @Deprecated
    public Cv getCv(int cvId) throws SQLException {
        List<JobPlace> jobPlaces = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();
        Cv cv = new Cv();

        PreparedStatement personStatement = connect.prepareStatement(GET_PERSON);
        personStatement.setInt(1, cvId);
        ResultSet personResultSet = personStatement.executeQuery();
        personResultSet.next();
        cv.setPerson(new Person.PersonBuilder()
                .firstName(personResultSet.getString("first_name"))
                .lastName(personResultSet.getString("last_name"))
                .birthday(LocalDate.parse(personResultSet.getString("birthday")))
                .build());
        cv.setId(personResultSet.getInt("id"));

        PreparedStatement contactStatement = connect.prepareStatement(GET_CONTACT);
        contactStatement.setInt(1, cvId);
        ResultSet contactResultSet = contactStatement.executeQuery();
        if(contactResultSet.next()) {
            cv.setContact(new Contact.ContactBuilder()
                    .phoneNumber(contactResultSet.getString("phone_number"))
                    .address(contactResultSet.getString("address"))
                    .eMail(contactResultSet.getString("e_mail"))
                    .build());
        }

        PreparedStatement skillsStatement = connect.prepareStatement(GET_SKILLS);
        skillsStatement.setInt(1, cvId);
        ResultSet skillsResultSet = skillsStatement.executeQuery();
        while (skillsResultSet.next()) {
            skills.add(Skill.getByName(skillsResultSet.getString("skill")));
        }

        PreparedStatement jobPlaceStatement = connect.prepareStatement(GET_JOB_PLACE);
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
