package com.example.skph.controller;

import com.example.skph.model.resources.Other;
import com.example.skph.model.resources.Shelter;
import com.example.skph.model.resources.physical.Clothes;
import com.example.skph.model.resources.physical.Food;
import com.example.skph.model.victimRequest.Request;
import com.example.skph.service.victimRequest.RequestService;
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

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
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


//        model.addAttribute("food", new Food());
//        model.addAttribute("clothes", new Clothes());
//        model.addAttribute("shelter", new Shelter());
//        model.addAttribute("other", new Other());
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

