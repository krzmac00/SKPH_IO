package com.example.skph.repository;

import com.example.skph.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.username = :username")
//    Optional<User> findByUsername(String username);
//
//    @Query("SELECT u FROM User u WHERE u.email = :email")
//    Optional<User> findByEmail(String email);
//
//    @Query("SELECT u FROM User u WHERE u.role = :role")
//    Optional<User> findByRole(String email);
}
