package com.example.skph.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.example.skph.enums.UserRole;
import java.io.IOException;
public class UserRoleDeserializer  extends JsonDeserializer<UserRole> {
    @Override
    public UserRole deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int value = p.getIntValue();
        return UserRole.fromValue(value);
    }
}
