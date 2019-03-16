package com.lelek.cv.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {

    String getCvNumber = "SELECT MAX(cv_id) FROM cv;";
    String getjobPlaceNumber = "SELECT MAX(id) FROM jobplace;";
    private int cvNumber, jobPlaceNumber;

    public void writeInTable(Connection connect, String query)throws SQLException {
        Statement statement = connect.createStatement();
        statement.executeUpdate(query);

    }

    public ResultSet readFromTable (Connection connect, String query) throws  SQLException {
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;

    }
}
