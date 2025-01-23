package com.example.skph.repository;

import com.example.skph.model.Task;
import com.example.skph.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Wyszukaj wszystkie Task, które mają konkretny Resource
    //@Query("SELECT t FROM Task t WHERE t.resource.id = :resourceId")

    @Query("SELECT t FROM Task t JOIN t.assignedResources r WHERE r.id = :resourceId")
    List<Task> findByResourceId(@Param("resourceId") Long resourceId);

    // Wyszukaj wszystkie Task, które są ukończone
    @Query("SELECT t FROM Task t WHERE t.status = 'COMPLETED'")
    List<Task> findAccomplishedTasks();

    @Query("SELECT t FROM Task t WHERE t.volunteer.id = :id")
    List<Task> findByVolunteerId(@Param("id") Long resourceId);

    @Query("SELECT t FROM Task t WHERE t.organization.id = :id")
    List<Task> findByOrganisationId(@Param("id") Long resourceId);

//    // Wyszukaj Task po liście dni (dokładne dopasowanie listy dni)
//    @Query("SELECT t FROM Task t WHERE t.daysList = :daysList")
//    List<Task> findByDaysList(@Param("daysList") List<String> daysList);

    // Wyszukaj Task po zasobie (Resource) i stanie ukończenia
    //@Query("SELECT t FROM Task t WHERE t.resource.id = :resourceId AND t.status = :status")
    //List<Task> findByResourceAndAccomplished(@Param("resourceId") Long resourceId, @Param("accomplished") TaskStatus status);
}

