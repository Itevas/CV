package com.lelek.cv.dao;

import com.lelek.cv.exception.TableIsEmptyException;
import com.lelek.cv.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

public class DaoCv {

    private Connection connect;
    private final static Logger LOGGER = Logger.getLogger("com.lelek.cv.dao.DaoCv");

// don't works with empty job_place ((((
    private final String GET_CV = "SELECT DISTINCT *\n" +
            "FROM person\n" +
            "JOIN skills ON person.id = skills.person_id\n" +
            "JOIN contact ON person.id = contact.person_id\n" +
            "JOIN job_place ON person.id = job_place.person_id\n" +
            "WHERE id =?;";

    private final String UPDATE_PERSON = "UPDATE person\n" +
            "SET first_name = ?,\n" +
            "last_name = ?,\n" +
            "birthday = ?\n" +
            "WHERE\n" +
            "id = ?;";
    private final String UPDATE_CONTACT = "UPDATE contact\n" +
            "SET phone_number = ?,\n" +
            "address = ?,\n" +
            "e_mail = ?\n" +
            "WHERE\n" +
            "person_id = ?;";
    private final String UPDATE_SKILLS = "UPDATE skills\n" +
            "SET skill = ?\n" +
            "WHERE\n" +
            "person_id = ?;";
    private final String UPDATE_JOBP_LACE = "UPDATE job_place\n" +
            "SET company = ?,\n" +
            "city = ?,\n" +
            "from_date = ?,\n" +
            "to_date = ?,\n" +
            "position_at = ?\n" +
            "WHERE\n" +
            "person_id = ?;";

    private final String INSERT_PERSON =
            "INSERT INTO person (firstname, lastname, birthday) VALUES (?, ?, ?) RETURNING id";
    private final String INSERT_CONTACT = "INSERT INTO contact (phonenumber, address, email, person_id) VALUES (? ,? ,?, ?)";
    private final String INSERT_SKILLS = "INSERT INTO skills (skill, person_id) VALUES (? ,?)";
    private final String INSERT_JOBP_LACE = "INSERT INTO job_place (company, city, from_date, " +
            "to_date, position_at, id) VALUES (?, ?, ?, ?, ?, ?)";

    private final String DELETE_CONTACT = "DELETE FROM contact WHERE person_id = ?;";
    private final String DELETE_JOB_PLACE = "DELETE FROM job_place WHERE person_id = ?;";
    private final String DELETE_SKILLS = "DELETE FROM skills WHERE person_id = ?;";
    private final String DELETE_PERSON = "DELETE FROM person WHERE id = ?;";

    public DaoCv() {
        try {
            Connection connect = DBConnection.getInstance().getConnection();
            this.connect = connect;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Cv cv) throws SQLException {
        PreparedStatement statement = connect.prepareStatement(DELETE_SKILLS + DELETE_JOB_PLACE);
        statement.setInt(1, cv.getId());
        statement.setInt(2, cv.getId());
        statement.executeUpdate();
        set(cv, UPDATE_PERSON, UPDATE_CONTACT, INSERT_SKILLS, INSERT_JOBP_LACE);
    }

    public void add(Cv cv) throws SQLException {
        set(cv, INSERT_PERSON, INSERT_CONTACT, INSERT_SKILLS, INSERT_JOBP_LACE);
    }

    private void set(Cv cv, String person, String contact, String skills, String jobPlaces) throws SQLException {
        PreparedStatement personStatement = connect.prepareStatement(person);
        personStatement.setString(1, cv.getPerson().getFirstName());
        personStatement.setString(2, cv.getPerson().getLastName());
        personStatement.setDate(3, java.sql.Date.valueOf(cv.getPerson().getBirthday()));
        if(cv.getId() == -1){
            ResultSet resultSet = personStatement.executeQuery();
            if (resultSet.next()) {
                cv.setId(resultSet.getInt("id"));
            } else {
                throw new TableIsEmptyException("TABLE IS EMPTY");
            }
        } else {
            personStatement.setInt(4, cv.getId());
            personStatement.executeUpdate();
        }

        PreparedStatement contactStatement = connect.prepareStatement(contact);
        contactStatement.setString(1, cv.getContact().getPhoneNumber());
        contactStatement.setString(2, cv.getContact().getAddress());
        contactStatement.setString(3, cv.getContact().geteMail());
        contactStatement.setInt(4, cv.getId());
        contactStatement.executeUpdate();

        for (Skill skill : cv.getSkills()) {
            PreparedStatement SkillsStatement = connect.prepareStatement(skills);
            SkillsStatement.setString(1, skill.getSkill());
            SkillsStatement.setInt(2, cv.getId());
            SkillsStatement.executeUpdate();
        }

        for (JobPlace jobPlace : cv.getJobPlaces()) {
            PreparedStatement jobPlaceStatement = connect.prepareStatement(jobPlaces);
            jobPlaceStatement.setString(1, jobPlace.getCompany());
            jobPlaceStatement.setString(2, jobPlace.getCity());
            jobPlaceStatement.setDate(3, java.sql.Date.valueOf(jobPlace.getFrom()));
            jobPlaceStatement.setDate(4, java.sql.Date.valueOf(jobPlace.getTo()));
            jobPlaceStatement.setString(5, jobPlace.getPosition().toString());
            jobPlaceStatement.setInt(6, cv.getId());
            jobPlaceStatement.executeUpdate();
        }
    }

    public Cv get(int id) throws SQLException {
        Cv cv = new Cv();
        Set<Skill> skills = new LinkedHashSet<>();
        Set<JobPlace> jobPlaces = new LinkedHashSet<>();
        cv.setId(id);
        LOGGER.severe("id = " + cv.getId());
        PreparedStatement statement = connect.prepareStatement(GET_CV);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        cv.setPerson(new Person.PersonBuilder()
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .birthday(LocalDate.parse(resultSet.getString("birthday")))
                .build());
        cv.setContact(new Contact.ContactBuilder()
                .phoneNumber(resultSet.getString("phone_number"))
                .address(resultSet.getString("address"))
                .eMail(resultSet.getString("e_mail"))
                .build());
        while (resultSet.next()) {
            skills.add(Skill.getByName(resultSet.getString("skill")));
            jobPlaces.add(new JobPlace.JobPlaceBuilder()
                    .city(resultSet.getString("city"))
                    .company(resultSet.getString("company"))
                    .from(resultSet.getDate("from_date").toLocalDate())
                    .to(resultSet.getDate("to_date").toLocalDate())
                    .position(Position.valueOf(resultSet.getString("position_at")))
                    .build());
        }
        cv.setSkills(new ArrayList<>(skills));
        cv.setJobPlaces(new ArrayList<>(jobPlaces));
        return cv;
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

}
