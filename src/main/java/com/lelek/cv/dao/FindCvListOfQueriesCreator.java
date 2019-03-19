package com.lelek.cv.dao;

public class FindCvListOfQueriesCreator {

    static String getAllFromTable() {
        return "SELECT * FROM person " +
                "INNER JOIN contact USING (id) " +
                "INNER JOIN jobplace USING (id);";
    }

    static String getByIdFromTable(int id) {
        return "SELECT * FROM person " +
                "INNER JOIN contact USING (id) " +
                "INNER JOIN jobplace USING (id) " +
                "WHERE id = " + id + ";";
    }

    static String getByFirstNameFromTable(String firstName) {
        return "SELECT * FROM person " +
                "INNER JOIN contact USING (id) " +
                "INNER JOIN jobplace USING (id) " +
                "WHERE firstname = '" + firstName + "';";
    }

    static String getByLastNameFromTable(String lastName) {
        return "SELECT * FROM person " +
                "INNER JOIN contact USING (id) " +
                "INNER JOIN jobplace USING (id) " +
                "WHERE lastname = '" + lastName + "';";
    }

    static String getByCompanyFromTable(String company) {
        return "SELECT * FROM person " +
                "INNER JOIN contact USING (id) " +
                "INNER JOIN jobplace USING (id) " +
                "WHERE company = '" + company+ "';";
    }
}
