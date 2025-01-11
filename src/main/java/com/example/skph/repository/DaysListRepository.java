package com.example.skph.repository;

import com.example.skph.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DaysListRepository extends JpaRepository<Day, Long> {
    @Query("SELECT r FROM Request r WHERE r.id = :id")
    Optional<Day> findById(@Param("id") Long id);
}
