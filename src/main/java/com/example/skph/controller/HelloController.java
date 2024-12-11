package com.example.skph.controller;

import com.example.skph.model.Request;
import com.example.skph.model.Requester;
import com.example.skph.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@Profile("dev")
public class HelloController {

    @Autowired
    private EntityService entityService;

//    @GetMapping("/")
//    public String index(Model model) {
//        String message = entityService.getEntity(1L).getName();
////        String message = "Zaraz nie wytrzymam.";
//        model.addAttribute("message", message);
//        return "ThymeleafDemo";
//    }


//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("request", new Request()); // Dodanie pustego obiektu `Request` dla formularza
//
//        return "requestForm";  // Zmieniamy nazwę na requestForm, jeśli formularz jest w tym pliku
//    }
//
//    @GetMapping("/form")
//    public String showRequestForm(Model model) {
//        // Tworzymy nowy obiekt `Request` i `Requester`
//        Request request = new Request();
//        Requester requester = new Requester();
//
//        // Powiązujemy requester z obiektem request (jeśli model Request to obsługuje)
////        request.setRequester(requester);
//
//        // Dodajemy obiekt request do modelu, żeby był widoczny w widoku
//        model.addAttribute("request", request);
//        model.addAttribute("requester", requester);
//
//        // Zwracamy widok formularza (np. requestForm.html)
//        return "requestForm";
//    }

//    @Autowired
//    private EntityService entityService;
//
//    @GetMapping("/")
//    public String index(Model model) {
//        String message = entityService.getEntity(1L).getName();
//        model.addAttribute("message", message);
//        return "ThymeleafDemo";
//    }

}
