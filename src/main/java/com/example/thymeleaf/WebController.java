package com.example.thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
@Slf4j
@Controller
public class WebController {

    @Autowired
    private MessageService messageService;

    @GetMapping({"/",""})//Catch get requests with and without a trailing forward slash
    public String index(Model model){
        //Add the website title
        model.addAttribute("siteTitle","Thymeleaf Demo");
        model.addAttribute("pageTitle", "Home");

        //Pass a list of Student objects
        model.addAttribute("messages", messageService.getAllMessages());

        return "/index";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("siteTitle","Thymeleaf Demo");
        model.addAttribute("pageTitle", "About");
        return "/about";
    }

    @GetMapping("/contact")//When a user visits the contact page, this is the mapping that will be called
    public String contactForm(Model model){
        model.addAttribute("siteTitle","Thymeleaf Demo");
        model.addAttribute("pageTitle", "Contact");
        //Pass the contact page a new blank instance of ContactMessage
        model.addAttribute("contactMessage", new ContactMessage());
        return "/contact";
    }

    @PostMapping("/contact")//When a user submits the contact form, this is the mapping that gets called
    public RedirectView contactSubmit(@ModelAttribute ContactMessage contactMessage, Model model){
        model.addAttribute("siteTitle","Thymeleaf Demo");
        model.addAttribute("pageTitle", "Contact");
        model.addAttribute("contactMessage", contactMessage);
        var message = messageService.addMessage(contactMessage);
        log.info("Saved: "+ message);
        return new RedirectView("/");
    }
}
