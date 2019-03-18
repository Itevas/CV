package com.lelek.cv.DAO;

public class FindCvListOfQueriesCreator {

    public String getAllFromTable() {
        return "SELECT * FROM person " +
                "INNER JOIN contact USING (id)" +
                "INNER JOIN jobplace USING (id);";
    }

    private String getByIdFromTable(int id) {
        return "SELECT * FROM person" +
                "INNER JOIN contact USING (id)" +
                "INNER JOIN jobplace USING (id)" +
                "WHERE id = " + id + ";";
    }

    private String getByFirstNameFromTable(String firstName) {
        return "SELECT * FROM person" +
                "INNER JOIN contact USING (id)" +
                "INNER JOIN jobplace USING (id)" +
                "WHERE firstname = '" + firstName + "';";
    }

    private String getByLastNameFromTable(String lastName) {
        return "SELECT * FROM person" +
                "INNER JOIN contact USING (id)" +
                "INNER JOIN jobplace USING (id)" +
                "WHERE lastname = '" + lastName + "';";
    }

    private String getByCompanyFromTable(String company) {
        return "SELECT * FROM person" +
                "INNER JOIN contact USING (id)" +
                "INNER JOIN jobplace USING (id)" +
                "WHERE company = '" + company+ "';";
    }
}
