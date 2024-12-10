package com.example.skph.model;

import org.locationtech.jts.geom.*;

public class GeometryHelper {
    private static final GeometryFactory geometryFactory = new GeometryFactory();

    public static Point createPoint(double latitude, double longitude) {
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }

    public static LineString createLineString(Coordinate[] coordinates) {
        return geometryFactory.createLineString(coordinates);
    }
}