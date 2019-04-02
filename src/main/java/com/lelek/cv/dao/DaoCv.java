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
    private final String DELETE_CV = "";

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
}
