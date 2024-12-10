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

//    @Query("SELECT l FROM Location l WHERE ST_DWithin(l.coordinates, :point, :radius) = true")
//    List<Location> findLocationsWithinRadius(@Param("point") Point point, @Param("radius") double radius);
//
//    @Query("SELECT l FROM Location l WHERE ST_Within(l.coordinates, :geometry) = true")
//    List<Location> findLocationsWithinGeometry(@Param("geometry") Geometry geometry);
//
//    List<Location> findByName(String name);
}
