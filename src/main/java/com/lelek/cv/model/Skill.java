package com.lelek.cv.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lelek.cv.service.mapper.SkillDeserializer;
import com.lelek.cv.service.mapper.SkillSerializer;

import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = SkillDeserializer.class)
@JsonSerialize(using = SkillSerializer.class)
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
    SCALA("Scala"),
    HTML("HTML"),
    CSS("CSS");

    private String skill;

    Skill(String name) {
        this.skill = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setName() {
        this.skill = skill;
    }

    public static Skill getByName(String name) {
        return VALUES.get(name);
    }

    private static final Map<String, Skill> VALUES = new HashMap<>();

    static {
        for (Skill skill : values()) {
            VALUES.put(skill.getSkill(), skill);
        }
    }

}
