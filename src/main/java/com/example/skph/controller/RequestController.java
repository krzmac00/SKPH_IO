package com.example.skph.controller;

import com.example.skph.model.*;
import com.example.skph.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;


@Controller  // Zmieniamy na @Controller
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;
    private final RequestResourceService requestResourceService;
    private final ResourceService resourceService;
    private final TaskService taskService;
    private final StatusHistoryService statusHistoryService;

    @Autowired
    public RequestController(RequestService requestService, RequestResourceService requestResourceService,
                             ResourceService resourceService, TaskService taskService, StatusHistoryService statusHistoryService) {
        this.requestService = requestService;
        this.requestResourceService = requestResourceService;
        this.resourceService = resourceService;
        this.taskService = taskService;
        this.statusHistoryService = statusHistoryService;
    }

    @GetMapping("/")
    public String showRequestsPage() {
        return "requestChoice";  // Refers to requests.html in templates folder
    }

    @GetMapping("/form")
    public String showRequestForm() {

        return "requestForm";
    }

    @PostMapping("/submit")
    public String submitRequest(
            @RequestParam String requesterFirstName,
            @RequestParam String requesterLastName,
            @RequestParam String address,
            @RequestParam String resourceType,
            @RequestParam(required = false) String foodName,
            @RequestParam(required = false) Integer foodAmount,
            @RequestParam(required = false) String foodTemperature,
            @RequestParam(required = false) Boolean foodAllergyFree,
            @RequestParam(required = false) String clothesName,
            @RequestParam(required = false) Integer clothesAmount,
            @RequestParam(required = false) String clothesSize,
            @RequestParam(required = false) String clothesSex,
            @RequestParam(required = false) String shelterName,
            @RequestParam(required = false) Integer shelterAmount,
            @RequestParam(required = false) Boolean shelterWithAnimals,
            @RequestParam(required = false) String otherName,
            @RequestParam(required = false) Integer otherAmount,
            @RequestParam(required = false) String otherDescription,
            Model model) {

        // Utworzenie obiektu Request
        Requester requester = new Requester(requesterFirstName, requesterLastName);
        Address requestAddress = new Address(address);
        Request request = new Request(requester, requestAddress); // Przykład daty końcowej
        requestService.saveRequest(request); // Zapisujemy Request

        // Na podstawie wybranego typu zasobu tworzymy odpowiedni obiekt
        Resource resource = null;

        if ("food".equals(resourceType)) {
            // Sprawdzamy, czy foodAllergyFree jest null, jeśli tak, ustawiamy domyślnie false
            foodAllergyFree = (foodAllergyFree != null) ? foodAllergyFree : false;

            Food food = new Food(foodName, foodAmount, foodTemperature, foodAllergyFree);
            resourceService.saveResource(food);
            resource = food;
        } else if ("clothes".equals(resourceType)) {
            Clothes clothes = new Clothes(clothesName, clothesAmount, clothesSize, clothesSex);
            resourceService.saveResource(clothes);
            resource = clothes;
        } else if ("shelter".equals(resourceType)) {
            // Sprawdzamy, czy shelterWithAnimals jest null, jeśli tak, ustawiamy domyślnie false
            shelterWithAnimals = (shelterWithAnimals != null) ? shelterWithAnimals : false;

            Shelter shelter = new Shelter(shelterName, shelterAmount, shelterWithAnimals);
            resourceService.saveResource(shelter);
            resource = shelter;
        } else if ("other".equals(resourceType)) {
            Other other = new Other(otherName, otherAmount, otherDescription);
            resourceService.saveResource(other);
            resource = other;
        }

        // Tworzymy powiązanie Request - Resource
        if (resource != null) {
            RequestResource requestResource = new RequestResource(request, resource);
            requestResourceService.saveRequestResource(requestResource);
        }

        // Generowanie zadań na podstawie powiązanych zasobów
        request.generateTasks();

        model.addAttribute("successMessage", "Request submitted successfully.");
        return "formSuccess";
    }


    @GetMapping("/request-details")
    public String getRequestDetails(@RequestParam("requesterId") Long requesterId, Model model) {
        // Pobieramy dane na podstawie requesterId
        RequestDTO requestDTO = requestService.getRequestDetailsByRequesterId(requesterId);

        // Dodajemy dane do modelu, które będą dostępne w widoku HTML
        if (requestDTO != null) {
            model.addAttribute("request", requestDTO);
        } else {
            model.addAttribute("error", "Request not found for requester ID: " + requesterId);
        }

        return "requestDetails";
    }




    @GetMapping("/byStartDate")
    public List<Request> getRequestsByStartDate(@RequestParam String startDate) {
        LocalDate date = LocalDate.parse(startDate);
        return requestService.getRequestsByStartDate(date);
    }

    // Nowa metoda do wyświetlania formularza
    @GetMapping("/form")
    public String showRequestForm(Model model) {
        Request request = new Request();
        model.addAttribute("request", request); // Dodanie pustego obiektu Request do modelu
//        model.addAttribute("resourceList", request.resourceList;
        Food food = new Food();
        Clothes clothes = new Clothes();
        Shelter shelter = new Shelter();
        Other other = new Other();
        model.addAttribute("food", food);
        model.addAttribute("clothes", clothes);
        model.addAttribute("shelter", shelter);
        model.addAttribute("other", other);


}

    // Metoda do obsługi zgłoszeń z formularza
//    @PostMapping("/submit")
//    public String submitRequest(@ModelAttribute Request request, Model model) {
//        // Tutaj zapisujesz dane do bazy, możesz dodać logikę walidacji itp.
//        requestService.saveRequest(request); // Zakładając, że masz metodę saveRequest w RequestService
//        model.addAttribute("message", "Request submitted successfully!");
//        return "redirect:/requests/form"; // Przekierowanie z powrotem na formularz lub stronę z potwierdzeniem
//    }

    @PostMapping("/submit")
    public String submitRequest(@ModelAttribute Request request, Model model, RedirectAttributes redirectAttributes) {
        // Zapisz zgłoszenie do bazy danych
//        List<Resource> resourceList = new ArrayList<>();
//        if (food != null) resourceList.add(food);
//        if (clothes != null) resourceList.add(clothes);
//        if (shelter != null) resourceList.add(shelter);
//        if (other != null) resourceList.add(other);
        requestService.saveRequest(request);

        // Dodaj wiadomość do atrybutów sesji
        model.addAttribute("successMessage", "Request submitted successfully!");
//        redirectAttributes.addFlashAttribute("message", "Request submitted successfully!");

        // Przekieruj na stronę formularza
        return "formSuccess";
    }

}

