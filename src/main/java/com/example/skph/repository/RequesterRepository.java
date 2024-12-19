package com.example.skph.repository;

import com.example.skph.model.victimRequest.Requester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequesterRepository extends JpaRepository<Requester, Long> {
    @Query("SELECT r FROM Requester r WHERE r.id = :id")
    Optional<Requester> findById(@Param("id") Long id);

    @Query("SELECT r FROM Requester r WHERE r.lastName = :lastName")
    List<Requester> findByLastName(@Param("lastName") String lastName);

    @Query("SELECT r FROM Requester r WHERE r.lastName = :lastName AND r.firstName = :firstName")
    List<Requester> findByFirstAndLastName(@Param("lastName") String lastName, @Param("firstName") String firstName);
}
