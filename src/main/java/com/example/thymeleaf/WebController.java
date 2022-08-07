package com.example.thymeleaf;

import lombok.extern.slf4j.Slf4j;
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

    @GetMapping({"/",""})//Catch get requests with and without a trailing forward slash
    public String index(Model model){
        //Add the website title
        model.addAttribute("siteTitle","Thymeleaf Demo");
        model.addAttribute("pageTitle", "Home");

        //Pass some content to the index page
        model.addAttribute("dynamicContent","This is some dynamic content");

        //Pass a list of items to the index page
        model.addAttribute("items", List.of("Item A", "Item B", "Item C", "Item D"));

        //Pass a list of Student objects
        model.addAttribute("students", List.of(
                new Student(1, "Tommy Tester", "t.tester@gmail.com"),
                new Student(2, "Harry Hacker", "hackerh@gmail.com"),
                new Student(3, "Jacky Java", "jj@gmail.com"),
                new Student(4, "Devon Developer", "devon.d@gmail.com")
        ));

        //Return index.html (No need to specify the ".html" part)
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
        log.info("Received: "+ contactMessage);
        return new RedirectView("/");
    }
}
