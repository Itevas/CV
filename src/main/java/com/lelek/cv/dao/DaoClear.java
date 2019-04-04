package com.lelek.cv.dao;

import java.sql.*;
import java.util.*;

public class DaoClear {

    private final String CLEAR_CONTACT = "DELETE FROM contact;";
    private final String CLEAR_JOB_PLACE = "DELETE FROM jobplace;";
    private final String CLEAR_SKILLS = "DELETE FROM skills;";
    private final String CLEAR_PERSON = "DELETE FROM person;";

    private Connection connect;

    public DaoClear() {
        try {
            Connection connect = DBConnection.getInstance().getConnection();
            this.connect = connect;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAll() throws SQLException {
        List<String> queries = new LinkedList<>();
        queries.add(CLEAR_CONTACT);
        queries.add(CLEAR_JOB_PLACE);
        queries.add(CLEAR_SKILLS);
        queries.add(CLEAR_PERSON);
        for (String query : queries) {
            connect.prepareStatement(query).execute();
        }
    }
}
