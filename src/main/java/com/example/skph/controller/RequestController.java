package com.example.skph.controller;

import com.example.skph.enums.Status;
import com.example.skph.model.*;
import com.example.skph.service.RequestResourceService;
import com.example.skph.service.RequestService;
import com.example.skph.service.ResourceService;
import com.example.skph.service.TaskService;
import com.example.skph.service.DaysListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Controller  // Zmieniamy na @Controller
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;
    private final RequestResourceService requestResourceService;
    private final ResourceService resourceService;
    private final TaskService taskService;
    private final DaysListService daysListService;

    @Autowired
    public RequestController(RequestService requestService, RequestResourceService requestResourceService,
                             ResourceService resourceService, TaskService taskService, DaysListService daysListService) {
        this.requestService = requestService;
        this.requestResourceService = requestResourceService;
        this.resourceService = resourceService;
        this.taskService = taskService;
        this.daysListService = daysListService;
    }

    // Endpointy REST pozostają bez zmian
    @GetMapping("/{id}")
    public Request getRequestById(@PathVariable Long id) {
        return requestService.getRequestById(id);
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


        /*model.addAttribute("food", new Food());
        model.addAttribute("clothes", new Clothes());
        model.addAttribute("shelter", new Shelter());
        model.addAttribute("other", new Other());*/
        return "requestForm"; // Użycie Thymeleaf template 'requestForm.html'
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
    //public String submitRequest(@ModelAttribute Request request, Model model, RedirectAttributes redirectAttributes) {
    public String submitRequest(
            @ModelAttribute Request request,
            @ModelAttribute Food food,
            @ModelAttribute Clothes clothes,
            @ModelAttribute Shelter shelter,
            @ModelAttribute Other other,
            Model model,
            RedirectAttributes redirectAttributes) {
        HashSet<RequestResource> requestResources = new HashSet<>();

        requestService.saveRequest(request);
        if (food != null) {
            resourceService.saveResource(food);
            RequestResource rr = new RequestResource(request, food);
            requestResources.add(rr);
            requestResourceService.saveRequestResource(rr);
        }
        if (clothes != null) {
            resourceService.saveResource(clothes);
            RequestResource rr = new RequestResource(request, clothes);
            requestResources.add(rr);
            requestResourceService.saveRequestResource(rr);
        }
        if (shelter != null) {
            resourceService.saveResource(shelter);
            RequestResource rr = new RequestResource(request, shelter);
            requestResources.add(rr);
            requestResourceService.saveRequestResource(rr);
        }
        if (other != null) {
            resourceService.saveResource(other);
            RequestResource rr = new RequestResource(request, other);
            requestResources.add(rr);
            requestResourceService.saveRequestResource(rr);
        }
        request.setResourceList(requestResources);
        request.generateTasks();
        for (Task task : request.getTaskList()) {
            taskService.saveTask(task);
//            int amountOfDays = task.getDaysList().getLast().getDayIndex();
//            for (int i = 0; i < amountOfDays; i ++) {
//                Day day = task.getDaysList().get(i);
//                daysListService.saveDaysList(day);
//            }
            Day day = new Day(Status.fromValue(1), 0);

            daysListService.saveDaysList(day);
            taskService.saveTask(task);
        }
        requestService.saveRequest(request); //saveRequest uses save method which in case of finding a request with same
        //id should modify an existing one, instead of creating a new one

        // Dodaj wiadomość do atrybutów sesji
        model.addAttribute("successMessage", "Request submitted successfully!");
//        redirectAttributes.addFlashAttribute("message", "Request submitted successfully!");

        // Przekieruj na stronę formularza
        return "formSuccess";
    }
}

