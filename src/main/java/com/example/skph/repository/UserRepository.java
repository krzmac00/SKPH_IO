package com.example.skph.repository;

import com.example.skph.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Entity, Long> {
}
