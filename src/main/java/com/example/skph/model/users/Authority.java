package com.example.skph.model.users;

import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
public class Authority extends User {

    @OneToMany
    @JoinColumn(name = "authority_id") // Powiązanie zasobów dodawanych przez Authority
    private List<Resource> resources;

    /**
     * Dodaje zasób publiczny do systemu.
     * @param resource Zasób, który ma zostać dodany.
     */
    public void addPublicResource(Resource resource) {
        resource.setStatus(ResourceStatus.AVAILABLE);
        resources.add(resource);
        // Może wysyłać powiadomienie do odpowiednich użytkowników poprzez moduł komunikacji.
    }

    /**
     * Alokuje zasób do konkretnego zadania, jeśli jest dostępny.
     * @param resource Zasób, który ma zostać przydzielony.
     * @param task Zadanie, do którego przypisujemy zasób.
     * @throws IllegalStateException jeśli zasób nie jest dostępny.
     */
    public void allocateResource(Resource resource, Task task) {
        if (resource.getStatus() != ResourceStatus.AVAILABLE) {
            throw new IllegalStateException("Zasób nie jest dostępny do alokacji.");
        }
        resource.setStatus(ResourceStatus.ALLOCATED);
        task.assignResource(resource);
    }

    /**
     * Zwraca listę wszystkich zasobów w systemie, które mogą być widoczne dla przedstawiciela władz.
     * @return Lista zasobów w systemie.
     */
    public List<Resource> viewAllResources() {
        // Przy założeniu, że mamy dostęp do repozytorium zasobów.
        // W rzeczywistym przypadku mogłoby być to repozytorium lub serwis.
        return resources;
    }

    /**
     * Znajduje zasoby nieprzypisane do żadnej organizacji pomocowej.
     * @return Lista zasobów bez przypisanej organizacji.
     */
    public List<Resource> findUnassignedResources() {
        return resources.stream()
                .filter(resource -> resource.getAssignedOrganization() == null)
                .collect(Collectors.toList());
    }
}