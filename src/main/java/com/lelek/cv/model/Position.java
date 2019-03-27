package com.lelek.cv.model;

public enum Position {

    DEVELOPER("Developer"),
    DEV_OPS("DevOps"),
    QA_ENGINEER("QAEngineer");

    private String name;

    Position(String name) {
        this.name = name;
    }
}
