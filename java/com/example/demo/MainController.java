package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ui.Model;

@Controller
public class MainController implements WebMvcConfigurer {

    @GetMapping("/")
    public String showForm(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);

        return "index";
    }

    @PostMapping("/results")
    public String submitForm(@ModelAttribute("contactForm") ContactForm contactForm) {
        System.out.println(contactForm);
        return "results";
    }
}
