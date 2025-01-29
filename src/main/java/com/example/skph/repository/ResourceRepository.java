package com.example.skph.repository;

import com.example.skph.model.Resource;
import com.example.skph.model.enums.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
//    List<Resource> findByAssignedTaskId(Long taskId);

    List<Resource> findByResourceType(ResourceType resourceType);

    List<Resource> findByAssignedOrganizationId(Long orgId);
}
