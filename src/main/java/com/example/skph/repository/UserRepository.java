package com.example.skph.repository;

import com.example.skph.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByEmail(String email);


    //@Query("SELECT DISTINCT u.role FROM User u WHERE u.role LIKE %:query%")
    //List<Role> findDistinctRoles(@Param("query") String query);

//    @Query("SELECT u FROM User u WHERE u.username = :username")
//    Optional<User> findByUsername(String username);
//
//    @Query("SELECT u FROM User u WHERE u.email = :email")
//    Optional<User> findByEmail(String email);
//
//    @Query("SELECT u FROM User u WHERE u.role = :role")
//    Optional<User> findByRole(String email);
}
