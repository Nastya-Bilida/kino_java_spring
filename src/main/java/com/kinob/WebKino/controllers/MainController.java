package com.kinob.WebKino.controllers;

import com.kinob.WebKino.models.KinoPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MainController {

    @GetMapping("/")
    public String welcome(Model model){
        model.addAttribute("title", "Добро пожаловать на KinoGuide!");
        return "welcome";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Про нас");
        return "about";
    }

}
