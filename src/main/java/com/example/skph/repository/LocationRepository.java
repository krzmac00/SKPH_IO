package com.example.skph.repository;

import com.example.skph.model.Location;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = """
        SELECT * FROM locations 
        WHERE ST_DWithin(coordinates, ST_SetSRID(ST_Point(:longitude, :latitude), 4326), :radius / 111319.9)
    """, nativeQuery = true)
    List<Location> findNearbyLocations(double latitude, double longitude, double radius);


    List<Location> findByNameIgnoreCase(String name);
}
