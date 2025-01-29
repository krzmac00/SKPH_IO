package com.example.skph.controller;

import com.example.skph.controller.dto.ResourceForm;
import com.example.skph.model.Resource;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.enums.ResourceType;
import com.example.skph.model.resources.*;
import com.example.skph.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/resources")
public class ResourceViewController {

    private final ResourceService resourceService;

    @Autowired
    public ResourceViewController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    // 1) Wyświetlanie listy zasobów
    @GetMapping
    public String listAllResources(Model model) {
        List<Resource> resources = resourceService.getAllResources();
        model.addAttribute("resources", resources);
        return "resourceList";
    }

    // 2) Wyświetlanie dostępnych
    @GetMapping("/available")
    public String listAvailableResources(Model model) {
        List<Resource> available = resourceService.findByStatus(ResourceStatus.AVAILABLE);
        model.addAttribute("resources", available);
        return "resourceList";
    }

    // 3) Wyświetlanie przydzielonych
    @GetMapping("/assigned")
    public String listAssignedResources(Model model) {
        List<Resource> assigned = resourceService.getAllResources().stream()
                .filter(r -> r.getStatus() == ResourceStatus.IN_USE || r.getStatus() == ResourceStatus.ALLOCATED)
                .toList();
        model.addAttribute("resources", assigned);
        return "resourceList";
    }

    // 4) Formularz tworzenia
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // Tworzymy pusty obiekt ResourceForm
        ResourceForm form = new ResourceForm();
        form.setStatus(ResourceStatus.AVAILABLE); // domyślnie AVAILABLE
        form.setResourceType(ResourceType.PHYSICAL); // np. domyślnie PHYSICAL
        model.addAttribute("resourceForm", form);
        return "resourceCreate"; // nazwa pliku HTML
    }

    // 5) Obsługa POST z formularza
    @PostMapping("/new")
    public String createResource(@ModelAttribute("resourceForm") ResourceForm form) {
        // Na podstawie resourceType budujemy odpowiednią sub-encję
        Resource resource = convertFormToResource(form);
        resourceService.addResource(resource);
        return "redirect:/resources";
    }

    // 6) Szczegóły zasobu
    @GetMapping("/{id}")
    public String showResourceDetails(@PathVariable Long id, Model model) {
        Resource resource = resourceService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found ID=" + id));
        model.addAttribute("resource", resource);
        return "resourceDetails"; // stwórz np. resourceDetails.html
    }

    /**
     * Metoda pomocnicza do konwersji ResourceForm -> właściwy Resource (PhysicalResource, HumanResource itp.)
     */
    private Resource convertFormToResource(ResourceForm form) {
        switch (form.getResourceType()) {
            case PHYSICAL -> {
                PhysicalResource pr = new PhysicalResource();
                pr.setName(form.getName());
                pr.setAmount(form.getAmount());
                pr.setToGive(form.isToGive());
                pr.setStatus(form.getStatus());
                pr.setType(form.getPhysicalType()); // np. FOOD, WATER
                pr.setQuantity(form.getAmount());   // zakładamy, że quantity = amount
                return pr;
            }
            case FINANCIAL -> {
                FinancialResource fr = new FinancialResource();
                fr.setName(form.getName());
                fr.setAmount(form.getAmount());
                fr.setToGive(form.isToGive());
                fr.setStatus(form.getStatus());
                fr.setValue(form.getFinancialValue() != null ? form.getFinancialValue() : BigDecimal.ZERO);
                fr.setCurrency(form.getCurrency());
                return fr;
            }
            case HUMAN -> {
                HumanResource hr = new HumanResource();
                hr.setName(form.getName());
                hr.setAmount(form.getAmount());
                hr.setToGive(form.isToGive());
                hr.setStatus(form.getStatus());
                hr.setRole(form.getHumanRole());
                hr.setAvailability(form.isAvailability());
                return hr;
            }
            case TRANSPORT -> {
                TransportResource tr = new TransportResource();
                tr.setName(form.getName());
                tr.setAmount(form.getAmount());
                tr.setToGive(form.isToGive());
                tr.setStatus(form.getStatus());
                tr.setCapacity(form.getCapacity());
                tr.setType(form.getTransportType());
                return tr;
            }
            case OTHER -> {
                OtherResource or = new OtherResource();
                or.setName(form.getName());
                or.setAmount(form.getAmount());
                or.setToGive(form.isToGive());
                or.setStatus(form.getStatus());
                or.setDescription(form.getDescription());
                return or;
            }
            default -> throw new IllegalStateException("Nieznany ResourceType: " + form.getResourceType());
        }
    }
}
