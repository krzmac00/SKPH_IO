package com.example.skph.repository;

import com.example.skph.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityRepository extends JpaRepository<Entity, Long> {

    @Query("SELECT e FROM Entity e WHERE e.id = :id")
    @Override
    Optional<Entity> findById(@Param("id") Long id);
}
