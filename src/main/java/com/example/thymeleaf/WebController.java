package com.example.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping({"/",""})
    public String index(Model model){
        model.addAttribute("dynamicContent","This is some dynamic content");
        model.addAttribute("siteTitle","Thymeleaf Demo");
        return "/index";
    }
}
