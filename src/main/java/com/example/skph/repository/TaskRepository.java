package com.example.skph.repository;

import com.example.skph.model.Entity;
import com.example.skph.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Entity, Long> {
    @Query("SELECT t FROM Task t WHERE t.volunteer.id = :id")
    List<Task> findByVolunteerId(@Param("id") Long resourceId);

    @Query("SELECT t FROM Task t WHERE t.organisation.id = :id")
    List<Task> findByOrganisationId(@Param("id") Long resourceId);

}
