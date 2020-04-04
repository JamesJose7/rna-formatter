package com.jeeps.rnaformatter.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RnaFormatter {

    @GetMapping("/")
    public String homePage(Model model) {
        return "index";
    }
}
