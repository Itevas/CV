package com.lelek.cv.DAO;

import com.lelek.cv.model.CV;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class WriteInDBListOfQueries {

    private final String GET_CV_NUMBER = "SELECT MAX(cv_id) FROM cv;";
    private final String GET_JOBPLACE_NUMBER = "SELECT MAX(id) FROM jobplace;";

    private final int INDEX = 1;
    private int cvNumber, jobPlaceNumber;

    private CV cv;
    private int countOfJobs;

    private List<String> queries = new LinkedList<>();

    public WriteInDBListOfQueries(CV cv) {
        this.cv = cv;
        countOfJobs = cv.getJobPlaces().size();
    }

    private String personInfoWriteQuery() {
        return "INSERT INTO person VALUES (" + cvNumber + ", '" + cv.getPerson().getFirstName() + "', '" +
                cv.getPerson().getLastName() + "', '" + cv.getPerson().getBirthday() + "');";
    }

    private String cvInfoWriteQuery() {
        return "INSERT INTO cv VALUES (" + cvNumber + "," + cvNumber + ");";
    }

    private String contactInfoWriteQuery() {
        return "INSERT INTO contact Values (" + cvNumber + ", '" + cv.getContact().getPhoneNumber() + "', '" +
                cv.getContact().getAddress() + "', '" + cv.getContact().geteMail() + "');";
    }


    private String jobPlaceInfoWriteQuery(int i) {
        return "INSERT INTO jobplace VALUES (" + jobPlaceNumber + ", '" + cv.getJobPlaces().get(i).getCompany() +
                "', '" + cv.getJobPlaces().get(i).getCity() + "', '" + cv.getJobPlaces().get(i).getFrom() + "', '" +
                cv.getJobPlaces().get(i).getTo() + "', '" + cv.getJobPlaces().get(i).getPosition() + "', " +
                cvNumber + ")";
    }

    public List<String> getListOfQueries() throws SQLException, ClassNotFoundException {
        getIndexes();
        queries.add(personInfoWriteQuery());
        queries.add(cvInfoWriteQuery());
        queries.add(contactInfoWriteQuery());
        for (int c = 1; c <= countOfJobs; c++) {
            queries.add(jobPlaceInfoWriteQuery(c));
            jobPlaceNumber++;
        }
        return queries;
    }

    private void getIndexes() throws SQLException, ClassNotFoundException {
        DBQuery dbQuery = new DBQuery();
        ResultSet cvNumberResult = dbQuery.readFromTable(GET_CV_NUMBER);
        while (cvNumberResult.next()) {
            cvNumber = cvNumberResult.getInt(INDEX) + 1;
        }
        cvNumberResult.close();
        ResultSet jobPlaceNumberResult = dbQuery.readFromTable(GET_JOBPLACE_NUMBER);

        while (jobPlaceNumberResult.next()) {
            jobPlaceNumber = jobPlaceNumberResult.getInt(INDEX) + 1;
        }
        jobPlaceNumberResult.close();
    }

}
