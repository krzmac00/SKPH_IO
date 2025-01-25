package com.example.skph.repository;

import com.example.skph.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusHistoryRepository extends JpaRepository<Day, Long> {
    @Query("SELECT dl FROM Day dl WHERE dl.id = :id")
    List<Day> findByDayId(@Param("id") Long id);
}
