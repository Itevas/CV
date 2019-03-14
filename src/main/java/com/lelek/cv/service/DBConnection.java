package com.lelek.cv.service;

import java.sql.*;
import java.util.logging.Logger;

public class DBConnection {

    private static final Logger LOGGER = Logger.getLogger("com.lelek.cv.service.DBConnection");

    private final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String pass = "postgres";
    private Connection connection;

    public Connection connect() throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL, user, pass);
        if(connection != null) {
        LOGGER.info("Connection established =" + connection);
        }
        return connection;
    }

    public void writeInTable(Connection connect)throws SQLException{

        String query = "INSERT INTO person VALUES ('Vasyl', 'Lelek', '26.07.1985')";
        Statement statement = connect.createStatement();
        statement.executeUpdate(query);

    }

    public void readFromTable (Connection connect) throws  SQLException{
        String query = "SELECT * FROM person";
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            System.out.println();
            for (int i = 1; i<4; i++) {
                System.out.print(resultSet.getString(i) + "\t");
            }
        }
    }
}
