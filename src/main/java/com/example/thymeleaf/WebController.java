package com.example.thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Slf4j
@Controller
public class WebController {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private MessageService messageService;

    @GetMapping({"", "/", "index"})
    public String index(Model model){
        model.addAllAttributes(Map.of(
                "siteTitle", appConfig.getAppDisplayName(),
                "pageTitle", "Home",
                "messages", messageService.getAllMessages()
        ));
        return "/index";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAllAttributes(Map.of(
                "siteTitle", appConfig.getAppDisplayName(),
                "pageTitle", "About"
        ));
        return "/about";
    }

    @GetMapping("/contact")//When a user visits the contact page, this is the mapping that will be called
    public String contactForm(Model model){
        model.addAllAttributes(Map.of(
                "siteTitle", appConfig.getAppDisplayName(),
                "pageTitle", "Contact",
                "contactMessage", new ContactMessage()
        ));
        return "/contact";
    }

    @PostMapping("/contact")//When a user submits the contact form, this is the mapping that gets called
    public RedirectView contactSubmit(@ModelAttribute ContactMessage contactMessage, Model model){
        model.addAllAttributes(Map.of(
                "siteTitle", appConfig.getAppDisplayName(),
                "pageTitle", "Contact",
                "contactMessage", contactMessage
        ));
        var message = messageService.addMessage(contactMessage);
        log.info("Message added: "+ message);
        return new RedirectView("/");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteMessage(@PathVariable Long id){
        var message = messageService.deleteMessageById(id);
        log.info(message);
        return new RedirectView("/");
    }

    @GetMapping("/edit/{id}")
    public RedirectView editMessage(@PathVariable Long id){
        log.info("\"Edit\" is not implemented yet :-(");
        return new RedirectView("/");
    }
}
