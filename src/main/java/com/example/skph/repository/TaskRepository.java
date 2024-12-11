//package com.example.skph.repository;
//
//import com.example.skph.model.Requester;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface TaskRepository extends JpaRepository<Requester, Long> {
//
//    @Query("SELECT r.id, r.firstName, r.lastName FROM Requester r WHERE r.id = :id")
//    List<Requester> findById(@Param("id") long id);
//
//    @Query("SELECT r.id, r.firstName, r.lastName FROM Requester r WHERE r.lastName = :lastName")
//    List<Requester> findByLastName(@Param("lastName") String lastName);
//
//    @Query("SELECT r.id, r.firstName, r.lastName FROM Requester r WHERE r.lastName = :lastName AND r.firstName = :firstName")
//    List<Requester> findByFirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
//}

package com.example.skph.repository;

import com.example.skph.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Wyszukaj wszystkie Task, które mają konkretny Resource
    @Query("SELECT t FROM Task t WHERE t.resource.id = :resourceId")
    List<Task> findByResourceId(@Param("resourceId") Long resourceId);

    // Wyszukaj wszystkie Task, które są ukończone
    @Query("SELECT t FROM Task t WHERE t.accomplished = true")
    List<Task> findAccomplishedTasks();

//    // Wyszukaj Task po liście dni (dokładne dopasowanie listy dni)
//    @Query("SELECT t FROM Task t WHERE t.daysList = :daysList")
//    List<Task> findByDaysList(@Param("daysList") List<String> daysList);

    // Wyszukaj Task po zasobie (Resource) i stanie ukończenia
    @Query("SELECT t FROM Task t WHERE t.resource.id = :resourceId AND t.accomplished = :accomplished")
    List<Task> findByResourceAndAccomplished(@Param("resourceId") Long resourceId, @Param("accomplished") boolean accomplished);
}

