package com.example.thymeleaf.controller;

import com.example.thymeleaf.config.AppConfig;
import com.example.thymeleaf.entity.ContactMessage;
import com.example.thymeleaf.service.MessageService;
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

    /**
     * Endpoint declaration for the view template (home page).
     * This endpoint covers 3 mappings:
     * <ul>
     *      <li><a href="http://localhost:8080">http://localhost:8080</a></li>
     *      <li><a href="http://localhost:8080/">http://localhost:8080/</a></li>
     *      <li><a href="http://localhost:8080/index">http://localhost:8080/index</a></li>
     *  </ul>
     * @param model The context model for transporting data to and from the view template
     * @return The "index.html" view template
     */
    @GetMapping({"", "/", "/index"})
    public String index(Model model){
        model.addAllAttributes(Map.of(
                "siteTitle", appConfig.getAppDisplayName(),
                "pageTitle", "Home",
                "messages", messageService.getAllMessages()
        ));
        return "/index";
    }

    /**
     * GET mapping for the "about" view template
     * @param model The context model for transporting data to and from the view template
     * @return The "about.html" view template
     */
    @GetMapping("/about")
    public String about(Model model){
        model.addAllAttributes(Map.of(
                "siteTitle", appConfig.getAppDisplayName(),
                "pageTitle", "About"
        ));
        return "/about";
    }

    /**
     * GET mapping for the "contact" view template.
     * This is the default mapping that is called when a user first visits the contact page
     * @param model The context model for transporting data to and from the view template
     * @return The "contact.html" view template
     */
    @GetMapping("/contact")
    public String contactForm(Model model){
        model.addAllAttributes(Map.of(
                "siteTitle", appConfig.getAppDisplayName(),
                "pageTitle", "Contact",
                "contactMessage", new ContactMessage()
        ));
        return "/contact";
    }

    /**
     * POST mapping for the "contact" view template.
     * This mapping is called after a user submits the contact form, accepting the posted message.
     * @param contactMessage The newly created {@link ContactMessage} to be saved
     * @param model The context model for transporting data to and from the view template
     * @return A redirection to the index (home) view template
     */
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

    /**
     * Deletes a {@link ContactMessage} by its id
     * @param id The message id
     * @return A redirection to the index (home) view template
     */
    @GetMapping("/delete/{id}")
    public RedirectView deleteMessage(@PathVariable Long id){
        var message = messageService.deleteMessageById(id);
        log.info(message);
        return new RedirectView("/");
    }

    /**
     * Edits a {@link ContactMessage} by its id
     * @param id The message id
     * @return A redirection to the index (home) view template
     */
    @GetMapping("/edit/{id}")
    public RedirectView editMessage(@PathVariable Long id){
        log.info("\"Edit\" is not implemented yet :-(");
        return new RedirectView("/");
    }
}
