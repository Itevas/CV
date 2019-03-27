package com.lelek.cv.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lelek.cv.service.mapper.PositionDeserializer;
import com.lelek.cv.service.mapper.PositionSerializer;

import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = PositionDeserializer.class)
@JsonSerialize(using = PositionSerializer.class)
public enum Position {

    DEVELOPER("Developer"),
    DEV_OPS("DevOps"),
    QA_ENGINEER("QAEngineer");

    private String position;

    Position(String name) {
        this.position = name;
    }

    public String getPosition() {
        return position;
    }

    public static Position getByName(String name){
        return VALUES.get(name);
    }

    private static final Map<String, Position> VALUES = new HashMap<>();

    static {
        for(Position position : values()){
            VALUES.put(position.getPosition(), position);
        }
    }
}
