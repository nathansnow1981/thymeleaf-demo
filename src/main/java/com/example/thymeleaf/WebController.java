package com.example.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

    @GetMapping({"/",""})//Catch get requests with and without a trailing forward slash
    public String index(Model model){
        //Add the website title
        model.addAttribute("siteTitle","Thymeleaf Demo");

        //Pass some content to the index page
        model.addAttribute("dynamicContent","This is some dynamic content");

        //Pass a list of items to the index page
        model.addAttribute("items", List.of("Item A", "Item B", "Item C", "Item D"));

        //Return index.html (No need to specify the ".html" part)
        return "/index";
    }
}
