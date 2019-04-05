package com.lelek.cv.dao;

import com.lelek.cv.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoList {

    private Connection connect;

    private final String GET_LIST = "SELECT DISTINCT person.id, person.first_name, person.last_name,\n" +
            "person.birthday, skills.skill, contact.phone_number, contact.e_mail\n" +
            "FROM person\n" +
            "JOIN skills ON person.id = skills.person_id\n" +
            "JOIN contact ON person.id = contact.person_id\n" +
            "ORDER BY person.id;";

    public DaoList() {
        try {
            Connection connect = DaoConnection.getInstance().getConnection();
            this.connect = connect;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cv> get() throws SQLException {
        int count = -1;
        List<Cv> cvList = new ArrayList<>();
        List<List<Skill>> skillsList = new ArrayList<>();
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_LIST);
        int id = -1;
        while (resultSet.next()) {
            if (resultSet.getInt("id") != id) {
                count++;
                cvList.add(new Cv());
                cvList.get(count).setId(resultSet.getInt("id"));
                cvList.get(count).setPerson(new Person.PersonBuilder()
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .birthday(resultSet.getDate("birthday").toLocalDate())
                        .build());
                cvList.get(count).setContact(new Contact.ContactBuilder()
                        .phoneNumber(resultSet.getString("phone_number"))
                        .eMail(resultSet.getString("e_mail"))
                        .build());
                skillsList.add(new ArrayList<>());
                skillsList.get(count).add(Skill.getByName(resultSet.getString("skill")));
                id = cvList.get(count).getId();
            } else {
                int i = 0;
                for (Skill skill : skillsList.get(count)) {
                    if (skill.equals(Skill.getByName(resultSet.getString("skill")))) {
                        i++;
                    }
                }
                if (i == 0) {
                    skillsList.get(count).add(Skill.getByName(resultSet.getString("skill")));
                }
            }
            cvList.get(count).setSkills(skillsList.get(count));
        }
        return cvList;
    }
}