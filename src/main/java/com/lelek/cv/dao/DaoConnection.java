package com.lelek.cv.dao;

import java.sql.*;
import java.util.logging.Logger;

public class DaoConnection {

    private static final Logger LOGGER = Logger.getLogger("com.lelek.cv.service.DaoConnection");
    private static DaoConnection instance;

    private final String URL = "jdbc:postgresql://localhost:5432/cv_lelek";
    private final String user = "postgres";
    private final String pass = "root";
    private Connection connection;

    private DaoConnection() throws SQLException, ClassNotFoundException {
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

    public static DaoConnection getInstance() throws SQLException, ClassNotFoundException{
        if (instance == null){
            instance = new DaoConnection();
        } else if (instance.getConnection().isClosed()){
            instance = new DaoConnection();
        }
        return instance;
    }

}
