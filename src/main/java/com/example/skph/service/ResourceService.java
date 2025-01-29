package com.example.skph.service;

import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.enums.ResourceType;
import com.example.skph.repository.AidOrganizationRepository;
import com.example.skph.repository.ResourceRepository;
import com.example.skph.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final TaskRepository taskRepository;
    private final AidOrganizationRepository aidOrgRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository,
                           TaskRepository taskRepository,
                           AidOrganizationRepository aidOrgRepository) {
        this.resourceRepository = resourceRepository;
        this.taskRepository = taskRepository;
        this.aidOrgRepository = aidOrgRepository;
    }

    public Resource addResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public void removeResource(Long resourceId) {
        resourceRepository.deleteById(resourceId);
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Optional<Resource> findById(Long id) {
        return resourceRepository.findById(id);
    }

    public List<Resource> findResourcesByTaskId(Long taskId) {
        return resourceRepository.findByAssignedTaskId(taskId);
    }

    public List<Resource> findResourcesByType(ResourceType type) {
        return resourceRepository.findByResourceType(type);
    }

    public List<Resource> findByStatus(ResourceStatus status) {
        List<Resource> all = resourceRepository.findAll();
        return all.stream()
                .filter(r -> r.getStatus() == status)
                .toList();
    }

    public List<Resource> getResourcesAssignedToOrganization(Long orgId) {
        // W ResourceRepository mamy metodę findByAssignedOrganizationId(orgId)
        return resourceRepository.findByAssignedOrganizationId(orgId);
    }

    /**
     * Przypisuje zasób o ID resourceId do zadania o ID taskId.
     * Zwraca zaktualizowany zasób.
     */
    @Transactional
    public Resource assignResourceToTask(Long resourceId, Long taskId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new NoSuchElementException("Resource not found with ID: " + resourceId));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

        task.assignResource(resource);
        resourceRepository.save(resource);
        return resource;
    }

    /**
     * Przykładowa metoda do aktualizacji zasobu, używana np. w TaskController
     * podczas assignResourceToTask().
     */
    @Transactional
    public Resource update(Resource resource) {
        return resourceRepository.save(resource);
    }
}

//package com.example.skph.service;
//
//import com.example.skph.model.Resource;
//import com.example.skph.model.Task;
//import com.example.skph.model.enums.ResourceStatus;
//import com.example.skph.model.enums.ResourceType;
//import com.example.skph.repository.ResourceRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class ResourceService {

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
//}
