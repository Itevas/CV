package com.lelek.cv.DAO;

import com.lelek.cv.model.CV;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBQuery {

    private Connection connect;

    public DBQuery() throws ClassNotFoundException {
        try {
            this.connect = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeCvInTable(CV cv) throws SQLException, ClassNotFoundException {

        Statement statement = connect.createStatement();
        List<String>queries = (new WriteCvInDBListOfQueries(cv)).getListOfQueries();
        for (String query : queries) {
            statement.executeUpdate(query);
        }
        statement.close();
        connect.close();
    }

    public ResultSet readFromTable(String query) throws SQLException {
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }
}
