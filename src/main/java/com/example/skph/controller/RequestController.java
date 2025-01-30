package com.example.skph.controller;
//
import com.example.skph.DTO.DayDTO;
import com.example.skph.DTO.RequestDTO;
import com.example.skph.DTO.TaskDTO;
import com.example.skph.enums.Status;
import com.example.skph.model.*;
import com.example.skph.service.RequestResourceService;
import com.example.skph.service.RequestService;
import com.example.skph.service.ResourceService;
import com.example.skph.service.TaskService;
import com.example.skph.service.StatusHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static java.time.LocalDateTime.now;

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

    // Endpointy REST pozostają bez zmian
    /*@GetMapping("/{id}")
    public Request getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id);
    }*/

//    @GetMapping("/search")
//    public String getAllRequests(@RequestParam(value = "requesterId", defaultValue = "16") Long requesterId, Model model) {
//        List<RequestDTO> result = requestService.getRequestsByRequesterId(requesterId).stream()
//                .map(request -> new RequestDTO(
//                        request.getId(),
//                        request.getStartDate(),
//                        request.getEndDate(),
//                        request.getAddress().getAddress(),
//                        taskService.getTasksByRequestId(request.getId()).stream()
//                                .map(task -> new TaskDTO(
//                                        task.getId(),
//                                        task.getResource().getName(),
//                                        statusHistoryService.getDaysByTaskId(task.getId()).stream()
//                                                .map(day -> new DayDTO(day.getStatus(), day.getTime()))
//                                                .toList()
//                                )).toList()
//                )).toList();
//
//        model.addAttribute("result", result); // Dodanie do modelu
//        return "requestSearch2";
//    }


//    @GetMapping("/search")
//    public String getAllRequests(@RequestParam(value = "requesterId", defaultValue = "16") Long requesterId, Model model) {
//        // Pobierz listę RequestDTO
//        List<RequestDTO> result = requestService.getRequestsByRequesterId(requesterId).stream()
//                .map(request -> new RequestDTO(
//                        request.getId(),
//                        request.getStartDate(),
//                        request.getEndDate(),
//                        request.getAddress().getAddress(),
//                        taskService.getTasksByRequestId(request.getId()).stream()
//                                .map(task -> new TaskDTO(
//                                        task.getId(),
//                                        task.getResource().getName(),
//                                        statusHistoryService.getDaysByTaskId(task.getId()).stream()
//                                                .map(day -> new DayDTO(day.getStatus(), day.getTime()))
//                                                .toList()
//                                )).toList()
//                )).toList();
//
//        // Dodanie listy result do modelu
//        model.addAttribute("result", result);
//
//        // Wyświetlenie danych na konsoli (opcjonalne)
//        result.forEach(System.out::println);
//
//        // Zwrócenie nazwy widoku
//        return "requestSearch2";
//    }


//    @GetMapping("/search")
//    public String getAllRequests(@RequestParam(value = "requesterId", defaultValue = "16") Long requesterId) {
//        List<RequestDTO> result = requestService.getRequestsByRequesterId(requesterId).stream()
//                .map(request -> new RequestDTO(
//                        request.getId(),
//                        request.getStartDate(),
//                        request.getEndDate(),
//                        request.getAddress().getAddress(),
//                        taskService.getTasksByRequestId(request.getId()).stream()
//                                .map(task -> new TaskDTO(
//                                        task.getId(),
//                                        task.getResource().getName(),
//                                        statusHistoryService.getDaysByTaskId(task.getId()).stream()
//                                                .map(day -> new DayDTO(day.getStatus(), day.getTime()))
//                                                .toList()
//                                )).toList()
//                )).toList();
//        result.stream().forEach(System.out::println);
//        return "requestSearch2";
//    }


    @PostMapping("/submit")
    public String submitRequest(
            @RequestParam String requesterFirstName,
            @RequestParam String requesterLastName,
            @RequestParam String address,
            @RequestParam String resourceType,
            @RequestParam(required = false) String foodName,
            @RequestParam(required = false) Integer foodAmount,
            @RequestParam(required = false) String foodTemperature,
            @RequestParam(required = false) Boolean foodAllergyFree,  // Zmieniamy na Boolean
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
//        Request request = new Request(requester, requestAddress, LocalDateTime.now().plusDays(7)); // Przykład daty końcowej
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

        model.addAttribute("successMessage", "Request successfully submitted.");
//        return "formSuccess"; // Możesz zmienić na stronę z sukcesem lub przekierowanie
        return "redirect:/requests/formSuccess";
    }


//    @GetMapping("/search")
//    @ResponseBody  // Wymusza zwrócenie JSON zamiast widoku HTML
//    public List<RequestDTO> getAllRequests(@RequestParam(value = "requesterId", defaultValue = "11") Long requesterId) {
//        return requestService.getRequestsByRequesterId(requesterId).stream()
//                .map(request -> new RequestDTO(
//                        request.getId(),
//                        request.getStartDate(),
//                        request.getEndDate(),
//                        request.getAddress().getAddress(),
//                        taskService.getTasksByRequestId(request.getId()).stream()
//                                .map(task -> new TaskDTO(
//                                        task.getId(),
//                                        task.getResource().getName(),
//                                        statusHistoryService.getDaysByTaskId(task.getId()).stream()
//                                                .map(day -> new DayDTO(day.getStatus(), day.getTime()))
//                                                .toList()
//                                )).toList()
//                )).toList();
//    }

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

        return "requestDetails"; // Widok HTML (np. requestDetails.html)
    }

    @GetMapping("/search-page")
    public String showSearchPage() {
        return "requestSearch2"; // Nazwa pliku HTML w katalogu templates (bez rozszerzenia .html)
    }



//    @GetMapping("/byRequester")
//    public List<Request> getRequestsByRequester(@RequestBody Requester requester) {
//        return requestService.getRequestsByRequester(requester);
//    }

    @GetMapping("/byStartDate")
    public List<Request> getRequestsByStartDate(@RequestParam String startDate) {
        LocalDate date = LocalDate.parse(startDate);
        return requestService.getRequestsByStartDate(date);
    }

    // Nowa metoda do wyświetlania formularza
//    @GetMapping("/form")
//    public String showRequestForm(Model model) {
//        Request request = new Request();
//        model.addAttribute("request", request); // Dodanie pustego obiektu Request do modelu
////        model.addAttribute("resourceList", request.resourceList;
//        Food food = new Food();
//        Clothes clothes = new Clothes();
//        Shelter shelter = new Shelter();
//        Other other = new Other();
//        food.toGive = false;
//        clothes.toGive = false;
//        shelter.toGive = false;
//        other.toGive = false;
//        model.addAttribute("food", food);
//        model.addAttribute("clothes", clothes);
//        model.addAttribute("shelter", shelter);
//        model.addAttribute("other", other);
//
//        return "requestForm"; // Użycie Thymeleaf template 'requestForm.html'
//    }


    @GetMapping("/form")
    public String showRequestForm() {
//    public String showRequestForm(Model model) {
//        Request request = new Request();
//        model.addAttribute("request", request);
//
//        Food food = new Food();
//        Clothes clothes = new Clothes();
//        Shelter shelter = new Shelter();
//        Other other = new Other();
//        food.toGive = false;
//        clothes.toGive = false;
//        shelter.toGive = false;
//        other.toGive = false;
//
//        model.addAttribute("food", food);
//        model.addAttribute("clothes", clothes);
//        model.addAttribute("shelter", shelter);
//        model.addAttribute("other", other);

        return "requestForm"; // Thymeleaf template
    }


    // Metoda do obsługi zgłoszeń z formularza
//    @PostMapping("/submit")
//    public String submitRequest(@ModelAttribute Request request, Model model) {
//        // Tutaj zapisujesz dane do bazy, możesz dodać logikę walidacji itp.
//        requestService.saveRequest(request); // Zakładając, że masz metodę saveRequest w RequestService
//        model.addAttribute("message", "Request submitted successfully!");
//        return "redirect:/requests/form"; // Przekierowanie z powrotem na formularz lub stronę z potwierdzeniem
//    }

//    @PostMapping("/submit")
//    public String submitForm(@RequestParam(name = "resourceType") String resourceType,
//                             @RequestParam(required = false) String foodName,
//            @RequestParam(required = false) Integer foodAmount,
//            @RequestParam(required = false) String foodTemperature,
//            @RequestParam(required = false) Boolean foodAllergyFree,
//            @RequestParam(required = false) String clothesName,
//            @RequestParam(required = false) Integer clothesAmount,
//            @RequestParam(required = false) String clothesSize,
//            @RequestParam(required = false) String clothesSex,
//            @RequestParam(required = false) String shelterName,
//            @RequestParam(required = false) Integer shelterAmount,
//            @RequestParam(required = false) Boolean shelterWithAnimals,
//            @RequestParam(required = false) String otherName,
//            @RequestParam(required = false) Integer otherAmount,
//            @RequestParam(required = false) String otherDescription) {
//        if ("food".equals(resourceType)) {
//            // Jeśli zasób to "food", przypisujemy dane do zasobu Food
//            Food food = new Food();
//            food.setName(foodName);
//            food.setAmount(foodAmount);
//            // Zapisz food w bazie danych lub wykonaj inne operacje
//        } else if ("clothes".equals(resourceType)) {
//            // Jeśli zasób to "clothes", przypisujemy dane do zasobu Clothes
//            Clothes clothes = new Clothes();
//            clothes.setName(clothesName);
//            clothes.setSize(clothesSize);
//            // Zapisz clothes w bazie danych lub wykonaj inne operacje
//        }   else if ("clothes".equals(resourceType)) {
//            // Jeśli zasób to "clothes", przypisujemy dane do zasobu Clothes
//            Clothes clothes = new Clothes();
//            clothes.setName(clothesName);
//            clothes.setSize(clothesSize);
//            // Zapisz clothes w bazie danych lub wykonaj inne operacje
//    }

//    @PostMapping("/submit")
//    //public String submitRequest(@ModelAttribute Request request, Model model, RedirectAttributes redirectAttributes) {
//    public String submitRequest(
//            @ModelAttribute Request request,
//            @ModelAttribute Food food,
//            @ModelAttribute Clothes clothes,
//            @ModelAttribute Shelter shelter,
//            @ModelAttribute Other other,
//            Model model,
//            RedirectAttributes redirectAttributes) {
//        HashSet<RequestResource> requestResources = new HashSet<>();
//
//        requestService.saveRequest(request);
////        if (food != null && !food.name.isEmpty()) {
//        if (food != null) {
//                resourceService.saveResource(food);
//                RequestResource rr = new RequestResource(request, food);
//                requestResources.add(rr);
//                requestResourceService.saveRequestResource(rr);
////            }
//        }
//        if (clothes != null) {
//            resourceService.saveResource(clothes);
//            RequestResource rr = new RequestResource(request, clothes);
//            requestResources.add(rr);
//            requestResourceService.saveRequestResource(rr);
//        }
//        if (shelter != null) {
//            resourceService.saveResource(shelter);
//            RequestResource rr = new RequestResource(request, shelter);
//            requestResources.add(rr);
//            requestResourceService.saveRequestResource(rr);
//        }
//        if (other != null) {
//            resourceService.saveResource(other);
//            RequestResource rr = new RequestResource(request, other);
//            requestResources.add(rr);
//            requestResourceService.saveRequestResource(rr);
//        }
//        request.setResourceList(requestResources);
//        request.generateTasks();
//        for (Task task : request.getTaskList()) {
//            Day day = new Day(Status.fromValue(1), 0);
//
//            statusHistoryService.saveStatusHistory(day);
//            List<Day> statuses = new ArrayList<>();
//            statuses.add(day);
//            task.setStatusHistory(statuses);
//            taskService.saveTask(task);
//        }
//        requestService.saveRequest(request); //saveRequest uses save method which in case of finding a request with same
//        //id should modify an existing one, instead of creating a new one
//
//        // Dodaj wiadomość do atrybutów sesji
//        model.addAttribute("successMessage", "Request submitted successfully!");
////        redirectAttributes.addFlashAttribute("message", "Request submitted successfully!");
//
//        // Przekieruj na stronę formularza
//        return "formSuccess";
//    }


//    @PostMapping("/submit")
//    public String submitRequest(
//            @ModelAttribute Request request,
//            @ModelAttribute Food food,
//            @ModelAttribute Clothes clothes,
//            @ModelAttribute Shelter shelter,
//            @ModelAttribute Other other,
//            Model model,
//            RedirectAttributes redirectAttributes) {
//        HashSet<RequestResource> requestResources = new HashSet<>();
//
//        // Sprawdzanie, czy zasób został wybrany, i zapisanie
//        if (food != null && food.name != null) {
//            resourceService.saveResource(food);
//            RequestResource rr = new RequestResource(request, food);
//            requestResources.add(rr);
//            requestResourceService.saveRequestResource(rr);
//        }
//        if (clothes != null && clothes.name != null) {
//            resourceService.saveResource(clothes);
//            RequestResource rr = new RequestResource(request, clothes);
//            requestResources.add(rr);
//            requestResourceService.saveRequestResource(rr);
//        }
//        if (shelter != null && shelter.name != null) {
//            resourceService.saveResource(shelter);
//            RequestResource rr = new RequestResource(request, shelter);
//            requestResources.add(rr);
//            requestResourceService.saveRequestResource(rr);
//        }
//        if (other != null && other.name != null) {
//            resourceService.saveResource(other);
//            RequestResource rr = new RequestResource(request, other);
//            requestResources.add(rr);
//            requestResourceService.saveRequestResource(rr);
//        }
//
//        request.setResourceList(requestResources);
//        request.generateTasks();
//
//        // Tworzenie zadań związanych z zgłoszeniem
//        for (Task task : request.getTaskList()) {
//            Day day = new Day(Status.fromValue(1), 0);
//            statusHistoryService.saveStatusHistory(day);
//            List<Day> statuses = new ArrayList<>();
//            statuses.add(day);
//            task.setStatusHistory(statuses);
//            taskService.saveTask(task);
//        }
//
//        // Zapisz zgłoszenie
//        requestService.saveRequest(request);
//
//        // Dodaj wiadomość o sukcesie
//        model.addAttribute("successMessage", "Request submitted successfully!");
//
//        // Przekierowanie do strony sukcesu
//        return "formSuccess";
//    }
//
//    @PostMapping("/submit")
//    public String submitRequest(
//            @ModelAttribute Request request,
//            @ModelAttribute Food food,
//            @ModelAttribute Clothes clothes,
//            @ModelAttribute Shelter shelter,
//            @ModelAttribute Other other,
//            Model model,
//            RedirectAttributes redirectAttributes) {
//
//        HashSet<RequestResource> requestResources = new HashSet<>();
//
//        // Save the resources first to ensure they are persistent
//        if (food != null && food.name != null) {
//            resourceService.saveResource(food);
//            RequestResource rr = new RequestResource(request, food);
//            requestResources.add(rr);
//            requestResourceService.saveRequestResource(rr);
//        }
//        if (clothes != null && clothes.name != null) {
//            resourceService.saveResource(clothes);
//            RequestResource rr = new RequestResource(request, clothes);
//            requestResources.add(rr);
//            requestResourceService.saveRequestResource(rr);
//        }
//        if (shelter != null && shelter.name != null) {
//            resourceService.saveResource(shelter);
//            RequestResource rr = new RequestResource(request, shelter);
//            requestResources.add(rr);
//            requestResourceService.saveRequestResource(rr);
//        }
//        if (other != null && other.name != null) {
//            resourceService.saveResource(other);
//            RequestResource rr = new RequestResource(request, other);
//            requestResources.add(rr);
//            requestResourceService.saveRequestResource(rr);
//        }
//
//        // Set the resource list in the request
//        request.setResourceList(requestResources);
//
//        // Now save the request object, which will cascade to RequestResource
//        requestService.saveRequest(request);
//
//        // Generate tasks and handle status history
//        request.generateTasks();
//        for (Task task : request.getTaskList()) {
//            Day day = new Day(Status.fromValue(1), 0);
//            statusHistoryService.saveStatusHistory(day);
//            List<Day> statuses = new ArrayList<>();
//            statuses.add(day);
//            task.setStatusHistory(statuses);
//            taskService.saveTask(task);
//        }
//
//        // Add success message
//        model.addAttribute("successMessage", "Request submitted successfully!");
//
//        return "formSuccess";
//    }



}



//package com.example.skph.controller;

//import com.example.skph.model.*;
//import com.example.skph.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;


//    public RequestController(RequestService requestService, RequestResourceService requestResourceService,
//                             ResourceService resourceService, TaskService taskService, StatusHistoryService statusHistoryService) {
//        this.requestService = requestService;
//        this.requestResourceService = requestResourceService;
//        this.resourceService = resourceService;
//        this.taskService = taskService;
//        this.statusHistoryService = statusHistoryService;
//    }


//@Controller
//@RequestMapping("/requests")
//public class RequestController {
//
//    @Autowired
//    private ResourceService resourceService;
//
//    @Autowired
//    private RequestService requestService;
//
//    @Autowired
//    private RequestResourceService requestResourceService;
//
//    public RequestController(RequestService requestService, RequestResourceService requestResourceService,
//                             ResourceService resourceService, TaskService taskService, StatusHistoryService statusHistoryService) {
//        this.requestService = requestService;
//        this.requestResourceService = requestResourceService;
//        this.resourceService = resourceService;
//        this.taskService = taskService;
//        this.statusHistoryService = statusHistoryService;
//    }
//
//
//    @GetMapping("/")
//    public String showRequestsPage() {
//        return "requestChoice";  // Refers to requests.html in templates folder
//    }
//
//    // Display form
//    @GetMapping("/form")
//    public String showForm(Model model) {
//        model.addAttribute("request", new Request());
//        return "requestForm";
//    }
//
//    TaskService taskService;
//    StatusHistoryService statusHistoryService;
//
//    @GetMapping("/search")
//    @ResponseBody
//    public List<RequestDTO> getAllRequests(@RequestParam(value = "requesterId", defaultValue = "9") Long requesterId) {
//
//        return requestService.getRequestsByRequesterId(requesterId).stream()
//                .map(request -> new RequestDTO(
//                        request.getId(),
//                        request.getStartDate(),
//                        request.getEndDate(),
//                        request.getAddress().getAddress(),
//                        taskService.getTasksByRequestId(request.getId()).stream()
//                                .map(task -> new TaskDTO(
//                                        task.getId(),
//                                        getResourceName(task.getResource()),  // Używamy metody do uzyskania nazwy zasobu
//                                        statusHistoryService.getDaysByTaskId(task.getId()).stream()
//                                                .map(day -> new DayDTO(day.getStatus(), day.getTime()))
//                                                .toList()
//                                )).toList()
//                )).toList();
//    }
//
//    private String getResourceName(Resource resource) {
//        if (resource instanceof Food) {
//            return "Food: " + ((Food) resource).getName();  // Przykład obsługi podklasy Food
//        } else if (resource instanceof Clothes) {
//            return "Clothes: " + ((Clothes) resource).getName();  // Przykład obsługi podklasy Clothes
//        } else if (resource instanceof Shelter) {
//            return "Shelter: " + ((Shelter) resource).getName();  // Przykład obsługi podklasy Shelter
//        } else if (resource instanceof Other) {
//            return "Other: " + ((Other) resource).getName();  // Przykład obsługi podklasy Other
//        } else {
//            return "Unknown Resource";  // W przypadku nieznanych zasobów
//        }
//    }
//
//
//    @GetMapping("/search-page")
//    public String showSearchPage() {
//        return "requestSearch2"; // Nazwa pliku HTML w katalogu templates (bez rozszerzenia .html)
//    }
//
//
    // Form submission
//    @PostMapping("/submit")
//    public String submitRequest(
//            @RequestParam String requesterFirstName,
//            @RequestParam String requesterLastName,
//            @RequestParam String address,
//            @RequestParam String resourceType,
//            @RequestParam(required = false) String foodName,
//            @RequestParam(required = false) Integer foodAmount,
//            @RequestParam(required = false) String foodTemperature,
//            @RequestParam(required = false) Boolean foodAllergyFree,
//            @RequestParam(required = false) String clothesName,
//            @RequestParam(required = false) Integer clothesAmount,
//            @RequestParam(required = false) String clothesSize,
//            @RequestParam(required = false) String clothesSex,
//            @RequestParam(required = false) String shelterName,
//            @RequestParam(required = false) Integer shelterAmount,
//            @RequestParam(required = false) Boolean shelterWithAnimals,
//            @RequestParam(required = false) String otherName,
//            @RequestParam(required = false) Integer otherAmount,
//            @RequestParam(required = false) String otherDescription,
//            Model model) {
//
//        // Utworzenie obiektu Request
//        Requester requester = new Requester(requesterFirstName, requesterLastName);  // Możesz zaimplementować sposób tworzenia Requestera
//        Address requestAddress = new Address(address); // Możesz zaimplementować sposób tworzenia Address
//        Request request = new Request(requester, requestAddress, LocalDateTime.now().plusDays(7)); // Przykład daty końcowej
//        requestService.saveRequest(request); // Zapisujemy Request
//
//        // Na podstawie wybranego typu zasobu tworzymy odpowiedni obiekt
//        Resource resource = null;
//
//        if ("food".equals(resourceType)) {
//            Food food = new Food(foodTemperature, foodAllergyFree);
//            resourceService.saveResource(food);
//            resource = food;
//        } else if ("clothes".equals(resourceType)) {
//            Clothes clothes = new Clothes(clothesSize, clothesSex);
//            resourceService.saveResource(clothes);
//            resource = clothes;
//        } else if ("shelter".equals(resourceType)) {
//            Shelter shelter = new Shelter(shelterWithAnimals);
//            resourceService.saveResource(shelter);
//            resource = shelter;
//        } else if ("other".equals(resourceType)) {
//            Other other = new Other(otherDescription);
//            resourceService.saveResource(other);
//            resource = other;
//        }
//
//        // Tworzymy powiązanie Request - Resource
//        if (resource != null) {
//            RequestResource requestResource = new RequestResource(request, resource);
//            requestResourceService.saveRequestResource(requestResource);
//        }
//
//        // Generowanie zadań na podstawie powiązanych zasobów
//        request.generateTasks();
//
//        model.addAttribute("message", "Request successfully submitted.");
//        return "requestForm"; // Możesz zmienić na stronę z sukcesem lub przekierowanie
//    }
//}

