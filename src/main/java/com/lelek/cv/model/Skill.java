package com.lelek.cv.model;

public enum Skill {

    JAVA("Java"),
    CSHARP("C#"),
    SQL("SQL"),
    PYTHON("Python"),
    JS("JavaScript"),
    PHP("PHP"),
    C_PLUS_PLUS("C++"),
    TYPE_SCRIPT("TypeScript"),
    SWIFT("Swift"),
    RUBY("Ruby"),
    KOTLIN("Kotlin"),
    C("C"),
    SCALA("Scala");


    private String name;

    Skill(String name) {
        this.name = name;
    }
}
