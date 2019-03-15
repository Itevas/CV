package com.lelek.cv.service;

import java.sql.*;
import java.util.logging.Logger;

public class DBConnection {

    private static final Logger LOGGER = Logger.getLogger("com.lelek.cv.service.DBConnection");

    String getCvNumber = "SELECT MAX(cv_id) FROM cv;";
    String getjobPlaceNumber = "SELECT MAX(id) FROM jobplace;";
    private int cvNumber, jobPlaceNumber;

    private final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String pass = "postgres";
    private Connection connection;

    //make as SingleTone
    public Connection connect() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL, user, pass);
        if(connection != null) {
        LOGGER.info("!!!Connection established = " + connection);
        }
        return connection;
    }

    public void writeInTable(Connection connect, String query)throws SQLException{
        Statement statement = connect.createStatement();
        statement.executeUpdate(query);

    }

    public ResultSet readFromTable (Connection connect, String query) throws  SQLException {

        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;

    }
}
