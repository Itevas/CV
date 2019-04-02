package com.lelek.cv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DaoCv {

    private Connection connect;

    private final String DELETE_CONTACT = "DELETE FROM contact WHERE person_id = ?;";
    private final String DELETE_JOB_PLACE = "DELETE FROM job_place WHERE person_id = ?;";
    private final String DELETE_SKILLS = "DELETE FROM skills WHERE person_id = ?;";
    private final String DELETE_PERSON = "DELETE FROM person WHERE id = ?;";

    public DaoCv(){
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
}
