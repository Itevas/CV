package com.lelek.cv.dao;

import com.lelek.cv.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class DaoCv {

    private Connection connect;

    private final String GET_CV = "SELECT DISTINCT *\n" +
            "FROM person\n" +
            "JOIN skills ON person.id = skills.person_id\n" +
            "JOIN contact ON person.id = contact.person_id\n" +
            "JOIN job_place ON person.id = job_place.person_id\n" +
            "WHERE id =?;";

    private final String DELETE_CONTACT = "DELETE FROM contact WHERE id = ?;";
    private final String DELETE_JOB_PLACE = "DELETE FROM jobplace WHERE id = ?;";
    private final String DELETE_SKILLS = "DELETE FROM skills WHERE id = ?;";
    private final String DELETE_PERSON = "DELETE FROM person WHERE id = ?;";

    public DaoCv(){
        try {
            Connection connect = DBConnection.getInstance().getConnection();
            this.connect = connect;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Cv get(int id)throws SQLException{
        Cv cv = new Cv();
        Set<Skill> skills = new LinkedHashSet<>();
        Set<JobPlace> jobPlaces = new LinkedHashSet<>();
        cv.setId(id);
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
        while (resultSet.next()){
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
