package br.com.faculdadeinovatech.inovatech2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class InovatechController {

    @GetMapping({"/", "/inovatech"})
    public String index(Model model) {
        return "index"; 
    }
    
}
