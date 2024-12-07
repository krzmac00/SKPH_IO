package com.example.skph.repository;

import com.example.skph.model.Requester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequesterRepository extends JpaRepository<Requester, Long> {
    @Query("SELECT r.id, r.firstName, r.lastName FROM Requester r WHERE r.id = :id")
    List<Requester> findById(@Param("id") long id);

    @Query("SELECT r.id, r.firstName, r.lastName FROM Requester r WHERE r.lastName = :lastName")
    List<Requester> findByLastName(@Param("id") long id);

    @Query("SELECT r.id, r.firstName, r.lastName FROM Requester r WHERE r.lastName = :lastName AND r.firstName = :firstName")
    List<Requester> findByFirstAndLastName(@Param("id") long id);
}
