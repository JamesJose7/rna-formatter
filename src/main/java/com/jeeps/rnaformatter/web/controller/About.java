package com.jeeps.rnaformatter.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class About {
    @RequestMapping("/about")
    public String about(Model model) { return "about"; }
}
