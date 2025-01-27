package com.example.skph.model;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LocationTypeConverter implements AttributeConverter<LocationType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(LocationType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getDatabaseValue();
    }

    @Override
    public LocationType convertToEntityAttribute(Integer dbValue) {
        if (dbValue == null) {
            return null;
        }
        return LocationType.fromDatabaseValue(dbValue);
    }
}