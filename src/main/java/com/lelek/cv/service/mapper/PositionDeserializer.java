package com.lelek.cv.service.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.lelek.cv.model.Position;

import java.io.IOException;

public class PositionDeserializer extends StdDeserializer<Position> {

    protected PositionDeserializer() {
        super(Position.class);
    }

    @Override
    public Position deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return Position.getByName(p.getValueAsString());
    }
}
