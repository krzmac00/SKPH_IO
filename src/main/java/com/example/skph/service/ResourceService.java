package com.example.skph.service;

import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.enums.ResourceType;
import com.example.skph.repository.ResourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResourceService {

//    private final ResourceRepository resourceRepository;
//
//    @Autowired
//    public ResourceService(ResourceRepository resourceRepository) {
//        this.resourceRepository = resourceRepository;
//    }
//
//    public Resource addResource(Resource resource) {
//        return resourceRepository.save(resource);
//    }
//
//    public void removeResource(Long resourceId) {
//        resourceRepository.deleteById(resourceId);
//    }
//
//    public List<Resource> getAllResources() {
//        return resourceRepository.findAll();
//    }
//
//    public Optional<Resource> findById(Long id) {
//        return resourceRepository.findById(id);
//    }
//
//    public List<Resource> findResourcesByTaskId(Long taskId) {
//        return resourceRepository.findByAssignedTaskId(taskId);
//    }
//
//    public List<Resource> findResourcesByType(ResourceType type) {
//        return resourceRepository.findByResourceType(type);
//    }
//
//    @Transactional
//    public Resource assignResourceToTask(Long resourceId, Task task) {
//        Optional<Resource> optionalResource = resourceRepository.findById(resourceId);
//        if (optionalResource.isPresent()) {
//            Resource resource = optionalResource.get();
//            resource.assignTask(task);
//            return resourceRepository.save(resource);
//        } else {
//            throw new IllegalArgumentException("Resource not found with ID: " + resourceId);
//        }
//    }
//
//    /**
//     * Alokuje zasób do konkretnego zadania, jeśli jest dostępny.
//     * @param resource Zasób, który ma zostać przydzielony.
//     * @param task Zadanie, do którego przypisujemy zasób.
//     * @throws IllegalStateException jeśli zasób nie jest dostępny.
//     */
//    public void allocateResource(Resource resource, Task task) {
//        if (resource.getStatus() != ResourceStatus.AVAILABLE) {
//            throw new IllegalStateException("Zasób nie jest dostępny do alokacji.");
//        }
//        resource.setStatus(ResourceStatus.ALLOCATED);
//        task.assignResource(resource);
//    }
//
//    /**
//     * Znajduje zasoby nieprzypisane do żadnej organizacji pomocowej.
//     * @return Lista zasobów bez przypisanej organizacji.
//     */
//    public List<Resource> findUnassignedResources() {
//        List<Resource> resources = resourceRepository.findAll();
//        return resources.stream()
//                .filter(resource -> resource.getAssignedOrganization() == null)
//                .collect(Collectors.toList());
//    }
}
