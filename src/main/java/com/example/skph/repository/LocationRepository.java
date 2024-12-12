package com.example.skph.repository;

import com.example.skph.model.Location;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByType_TypeName(String typeName);

    List<Location> findByDescriptionContainingIgnoreCase(String description);

//    @Query("SELECT l FROM Location l WHERE l.type.typeName = :typeName OR l.description LIKE %:description%")
//    List<Location> findByTypeOrDescription(@Param("typeName") String typeName, @Param("description") String description);

}
