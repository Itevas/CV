package com.lelek.cv.dao;

import java.sql.*;
import java.util.logging.Logger;

public class DBConnection {

    private static final Logger LOGGER = Logger.getLogger("com.lelek.cv.service.DBConnection");
    private static DBConnection instance;

    private final String URL = "jdbc:postgresql://localhost:5432/cv";
    private final String user = "postgres";
    private final String pass = "root";
    private Connection connection;

    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(URL, user, pass);
        if (connection != null) {
            LOGGER.info("!!!Connection established = " + connection);
        } else {
            LOGGER.info("!!!Connection is failed = " + connection);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException{
        if (instance == null){
            instance = new DBConnection();
        } else if (instance.getConnection().isClosed()){
            instance = new DBConnection();
        }
        return instance;
    }

}
