package com.example.skph.repository;

import com.example.skph.model.Request;
import com.example.skph.model.RequestResource;
import com.example.skph.model.Requester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequestResourceRepository extends JpaRepository<RequestResource, Long> {
    @Query("SELECT rr FROM RequestResource rr WHERE rr.id = :id")
    Optional<RequestResource> findById(@Param("id") Long id);

    @Query("SELECT rr FROM RequestResource rr WHERE rr.request.id = :request_id")
    List<RequestResource> findByRequest(@Param("request_id") Long request_id);

    @Query("SELECT rr FROM RequestResource rr WHERE rr.resource.id = :resource_id")
    List<RequestResource> findByResource(@Param("resource_id") Long resource_id);
}
