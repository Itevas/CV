package com.lelek.cv.service.mapper;

import com.fasterxml.jackson.core.JsonGenerator;

import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.lelek.cv.model.Skill;

import java.io.IOException;

public class SkillSerializer extends StdSerializer<Skill> {

    protected SkillSerializer() {
        super(Skill.class);
    }

    public void serialize(Skill value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName("skill");
        generator.writeString(value.getSkill());
        generator.writeEndObject();
    }
}