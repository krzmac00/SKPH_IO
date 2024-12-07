package com.example.skph.repository;

import com.example.skph.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT r FROM Request r WHERE r.id = :id")
    List<Request> findById(@Param("id") long id);

    @Query("SELECT r FROM Request r WHERE r.requester = :requester")
    List<Request> findByRequester(@Param("id") long id);

    @Query("SELECT r FROM Request r WHERE r.startDate = :startDate")
    List<Request> findByStartDate(@Param("id") long id);
}
