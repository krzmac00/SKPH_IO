package com.example.skph.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class LocationTypeDeserializer extends JsonDeserializer<LocationType> {
    @Override
    public LocationType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int value = p.getIntValue();
        return LocationType.fromValue(value);
    }
}

