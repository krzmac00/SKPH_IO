package com.example.skph.repository;

import com.example.skph.model.users.AidOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AidOrganizationRepository extends JpaRepository<AidOrganization, Long> {
}
