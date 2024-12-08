package com.example.skph.controller;

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

    @GetMapping("/")
    public String index(Model model) {
        String message = entityService.getEntity(1L).getName();
//        String message = "Zaraz nie wytrzymam.";
        model.addAttribute("message", message);
        return "ThymeleafDemo";
    }

}
