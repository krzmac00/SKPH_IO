////package com.example.skph.controller;
////
////
////import com.example.skph.model.UserForm;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class UserController {
//
//    @GetMapping("/form")
//    public String showForm(Model model) {
//        model.addAttribute("userForm", new UserForm());
//        return "form";
//    }
//
//    @PostMapping("/form")
//    public String handleFormSubmission(@ModelAttribute UserForm userForm, Model model) {
//        // Handle the user input
//        model.addAttribute("message", "Form submitted successfully!");
//        model.addAttribute("userData", userForm);
//        return "form-result";
//    }
//}
