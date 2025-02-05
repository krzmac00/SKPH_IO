package com.example.skph.controller;

import com.example.skph.controller.dto.ResourceForm;
import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import com.example.skph.model.User;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.enums.ResourceType;
import com.example.skph.model.enums.UserRole;
import com.example.skph.model.resources.*;
import com.example.skph.service.ResourceService;
import com.example.skph.service.TaskService;
import com.example.skph.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Kontroler obsługujący widoki (Thymeleaf) dla zasobów.
 */
@Controller
@RequestMapping("/resources")
public class ResourceViewController {

    private final ResourceService resourceService;
    private final TaskService taskService;
    private final UserService userService; // do obsługi zalogowanego użytkownika

    @Autowired
    public ResourceViewController(ResourceService resourceService,
                                  TaskService taskService,
                                  UserService userService) {
        this.resourceService = resourceService;
        this.taskService = taskService;
        this.userService = userService;
    }

    /**
     * 1. Lista wszystkich zasobów
     */
    @GetMapping
    public String listAllResources(Model model) {
        List<Resource> resources = resourceService.getAllResources();
        model.addAttribute("resources", resources);
        return "resourceList"; // nazwa widoku: resourceList.html
    }

    /**
     * 2. Zasoby dostępne (status = AVAILABLE)
     */
    @GetMapping("/available")
    public String listAvailableResources(Model model) {
        List<Resource> available = resourceService.findByStatus(ResourceStatus.AVAILABLE);
        model.addAttribute("resources", available);
        return "resourceList";
    }

    /**
     * 3. Zasoby przydzielone (IN_USE lub ALLOCATED)
     */
    @GetMapping("/assigned")
    public String listAssignedResources(Model model) {
        List<Resource> assigned = resourceService.getAllResources().stream()
                .filter(r -> r.getStatus() == ResourceStatus.IN_USE || r.getStatus() == ResourceStatus.ALLOCATED)
                .toList();
        model.addAttribute("resources", assigned);
        return "resourceList";
    }

    /**
     * 4. Zasoby ludzkie (resourceType = HUMAN)
     */
    @GetMapping("/human")
    public String listHumanResources(Model model) {
        List<Resource> humanResources = resourceService.findResourcesByType(ResourceType.HUMAN);
        model.addAttribute("resources", humanResources);
        return "resourceList";
    }

    /**
     * 5. Zasoby fizyczne (resourceType = PHYSICAL)
     */
    @GetMapping("/physical")
    public String listPhysicalResources(Model model) {
        List<Resource> physicalResources = resourceService.findResourcesByType(ResourceType.PHYSICAL);
        model.addAttribute("resources", physicalResources);
        return "resourceList";
    }

    /**
     * 6. Formularz tworzenia nowego zasobu (GET)
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // Pusty obiekt DTO
        ResourceForm form = new ResourceForm();
        form.setStatus(ResourceStatus.AVAILABLE);
        form.setResourceType(ResourceType.PHYSICAL); // np. domyślnie PHYSICAL
        model.addAttribute("resourceForm", form);
        return "resourceCreate"; // widok: resourceCreate.html
    }

    /**
     * 7. Obsługa POST z formularza nowego zasobu
     */
    @PostMapping("/new")
    public String createResource(@ModelAttribute("resourceForm") ResourceForm form) {
        // Metoda pomocnicza (np. w tym kontrolerze) do konwersji z ResourceForm -> Resource
        Resource resource = convertFormToResource(form);
        resourceService.addResource(resource);
        return "redirect:/resources";
    }

    /**
     * 8. Szczegóły konkretnego zasobu
     */
    @GetMapping("/{id}")
    public String showResourceDetails(@PathVariable Long id, Model model) {
        Resource resource = resourceService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found ID=" + id));
        model.addAttribute("resource", resource);
        return "resourceDetails"; // widok: resourceDetails.html
    }

    /**
     * 9. Wyświetlenie formularza przydzielania zasobu do zadania
     */
    @GetMapping("/{id}/assign")
    public String showAssignForm(@PathVariable Long id, Model model) {
        Resource resource = resourceService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found ID=" + id));

        List<Task> tasks = taskService.getAllTasks(); // w razie potrzeby filtruj wg organizacji

        model.addAttribute("resource", resource);
        model.addAttribute("tasks", tasks);

        AssignResourceForm form = new AssignResourceForm();
        form.setResourceId(resource.getId());
        model.addAttribute("assignForm", form);

        return "resourceAssign"; // widok z formularzem (resourceAssign.html)
    }

    /**
     * 10. Obsługa POST przypisania zasobu do zadania
     */
    @PostMapping("/{id}/assign")
    public String assignResource(@PathVariable Long id,
                                 @ModelAttribute("assignForm") AssignResourceForm form) {
        Long taskId = form.getTaskId();
        resourceService.assignResourceToTask(id, taskId);
        // Po przydzieleniu wracamy do listy zasobów (lub gdzie chcesz)
        return "redirect:/resources";
    }

    /**
     * 11. Wyświetlanie zasobów dla AidOrganization:
     *  - przypisane do niej,
     *  - wszystkie w systemie (opcjonalnie).
     */
    @GetMapping("/org")
    public String listOrgResources(Model model) {
        // 1. Odczytaj aktualnie zalogowanego użytkownika z kontekstu Security
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // 2. Wyciągnij username i znajdź w bazie
        String username = auth.getName();
        User user = userService.findByUsername(username);

        // 3. Sprawdź, czy rola to AID_ORGANIZATION
        if (user.getRole() != UserRole.AID_ORGANIZATION) {
            // Możesz zwrócić 403 lub np. przekierować na jakąś stronę błędu
            return "redirect:/error403";
        }

        // 4. Pobierz ID tej organizacji
        Long orgId = user.getId();

        // 5. Zasoby przypisane do tej organizacji
        List<Resource> assignedToOrg = resourceService.getResourcesAssignedToOrganization(orgId);

        // 6. Jeśli uprawnienia pozwalają - wszystkie zasoby
        List<Resource> allResources = resourceService.getAllResources();

        // 7. Wrzuć obie listy do modelu
        model.addAttribute("myResources", assignedToOrg);
        model.addAttribute("allResources", allResources);

        // 8. Zwróć widok orgResourceList.html
        return "orgResourceList";
    }

    /**
     * Metoda pomocnicza do konwersji ResourceForm -> właściwa klasa Resource
     * (PhysicalResource, FinancialResource, HumanResource, etc.)
     */
    private Resource convertFormToResource(ResourceForm form) {
        // Na podstawie form.getResourceType() tworzysz odpowiednią subklasę
        switch (form.getResourceType()) {
            case PHYSICAL:
                PhysicalResource pr = new PhysicalResource();
                pr.setName(form.getName());
                pr.setAmount(form.getAmount());
                pr.setStatus(form.getStatus());
                // Ustaw typ (choć w PhysicalResource jest @PrePersist, to i tak:
                pr.setResourceType(ResourceType.PHYSICAL);

                // Ewentualnie w ResourceForm dajesz pole physicalType i przypisujesz pr.setType(...)
                // pr.setQuantity(...) itd.

                return pr;

            case HUMAN:
                HumanResource hr = new HumanResource();
                hr.setName(form.getName());
                hr.setAmount(form.getAmount());
                hr.setStatus(form.getStatus());
                hr.setResourceType(ResourceType.HUMAN);
                // hr.setRole(form.getHumanRole());
                // hr.setAvailability(form.isAvailability());
                return hr;

            case FINANCIAL:
                FinancialResource fr = new FinancialResource();
                fr.setName(form.getName());
                fr.setAmount(form.getAmount());
                fr.setStatus(form.getStatus());
                fr.setResourceType(ResourceType.FINANCIAL);
                // fr.setValue(form.getFinancialValue());
                // fr.setCurrency(form.getCurrency());
                return fr;

            case TRANSPORT:
                TransportResource tr = new TransportResource();
                tr.setName(form.getName());
                tr.setAmount(form.getAmount());
                tr.setStatus(form.getStatus());
                tr.setResourceType(ResourceType.TRANSPORT);
                // tr.setCapacity(form.getCapacity());
                // tr.setType(form.getTransportType());
                return tr;

            case OTHER:
            default:
                OtherResource or = new OtherResource();
                or.setName(form.getName());
                or.setAmount(form.getAmount());
                or.setStatus(form.getStatus());
                or.setResourceType(ResourceType.OTHER);
                // or.setDescription(form.getDescription());
                return or;
        }
    }


    /**
     * Wewnętrzna klasa pomocnicza do formularza przydzielania.
     */
    public static class AssignResourceForm {
        private Long resourceId;
        private Long taskId;

        public Long getResourceId() {
            return resourceId;
        }
        public void setResourceId(Long resourceId) {
            this.resourceId = resourceId;
        }
        public Long getTaskId() {
            return taskId;
        }
        public void setTaskId(Long taskId) {
            this.taskId = taskId;
        }
    }
}
