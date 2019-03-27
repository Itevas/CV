package com.lelek.cv.service.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.lelek.cv.model.Position;

import java.io.IOException;

public class PositionSerializer extends StdSerializer<Position> {

    protected PositionSerializer() {
        super(Position.class);
    }

    public void serialize(Position value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName("position");
        if (!value.getPosition().isEmpty()) {
            generator.writeString(value.getPosition());
        }
        generator.writeEndObject();
    }
}