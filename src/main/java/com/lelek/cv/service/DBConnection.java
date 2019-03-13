package com.lelek.cv.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBConnection {

    private static final Logger LOGGER = Logger.getLogger("com.lelek.cv.service.DBConnection");

    private final String URL = "jdbc:postgresql://localhost/cv";
    private String user = "postgresql";
    private String pass = "postgresql";

    public Connection connect(){
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, user, pass);
            LOGGER.info("Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
