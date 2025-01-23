package com.example.skph.repository;

import com.example.skph.model.Task;
import com.example.skph.model.users.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Task, Long> {
    @Query("SELECT o FROM Organization o WHERE o.name= :name")
    List<Organization> findAllWithName(@Param("name") String name);
}
