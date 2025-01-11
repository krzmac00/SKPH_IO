package com.example.skph.repository;

import com.example.skph.model.Request;
import com.example.skph.model.Resource;
import com.example.skph.model.Requester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    @Query("SELECT r FROM Resource r WHERE r.id = :id")
    Optional<Resource> findById(@Param("id") Long id);

    /*@Query("SELECT r FROM Resource r WHERE r.resource = :resource")
    List<Request> findByRequester(@Param("requester") Requester requester);

    @Query("SELECT r FROM Resource r WHERE r.startDate = :startDate")
    List<Request> findByStartDate(@Param("startDate") LocalDate startDate);*/
}
