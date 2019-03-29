package com.lelek.cv.service.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.lelek.cv.model.Skill;

import java.io.IOException;

public class SkillDeserializer extends StdDeserializer<Skill> {

    protected SkillDeserializer() {
        super(Skill.class);
    }

    @Override
    public Skill deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return Skill.getByName(p.getValueAsString());
    }
}
