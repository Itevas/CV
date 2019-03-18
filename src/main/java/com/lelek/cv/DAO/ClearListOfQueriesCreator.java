package com.lelek.cv.DAO;

import java.util.LinkedList;
import java.util.List;

public class ClearListOfQueriesCreator {

    private final String CLEAR_CONTACT = "DELETE FROM contact;";
    private final String CLEAR_JOBPLACE = "DELETE FROM jobplace;";
    private final String CLEAR_CV = "DELETE FROM cv;";
    private final String CLEAR_PERSON = "DELETE FROM person;";

    public List<String> clearTables(){
        List<String> clearList = new LinkedList<>();
        clearList.add(CLEAR_CONTACT);
        clearList.add(CLEAR_JOBPLACE);
        clearList.add(CLEAR_CV);
        clearList.add(CLEAR_PERSON);
        return clearList;
    }

}
