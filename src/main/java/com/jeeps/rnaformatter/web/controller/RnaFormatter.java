package com.jeeps.rnaformatter.web.controller;

import com.jeeps.rnaformatter.model.TargetSite;
import com.jeeps.rnaformatter.model.TargetSitesForm;
import com.jeeps.rnaformatter.service.RnaFormatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

@Controller
public class RnaFormatter {
//    @Value("${server.servlet.context-path}")
//    private String context;

    @Autowired
    private RnaFormatterService rnaFormatterService;

    @GetMapping("/gRNA_Designer")
    public String homePage(Model model) {
        TargetSitesForm targetSitesForm = new TargetSitesForm();
        targetSitesForm.setTargetSites(new ArrayList<>(Collections.singletonList(new TargetSite())));
        if (model.containsAttribute("targetSitesForm"))
            targetSitesForm = (TargetSitesForm) model.getAttribute("targetSitesForm");
        model.addAttribute("targetSitesForm", targetSitesForm);
        model.addAttribute("action", /*context +*/ "/result");
        model.addAttribute("actionBtn", "Download");
        return "grnaDesigner";
    }

    @PostMapping("/result")
    public String result(HttpServletResponse response,
                         Model model,
                         TargetSitesForm targetSitesForm) {
        targetSitesForm.getTargetSites().forEach(targetSite -> targetSite.setName(targetSitesForm.getName()));
        try {
            InputStream is = rnaFormatterService.getRnaResultDoc(targetSitesForm);

            String type = "gRNA";
            if (targetSitesForm.getDownloadType().equals("primers"))
                type = "primers";
            response.setHeader("Content-disposition", "attachment; filename="+ targetSitesForm.getName() + "_" + type + ".docx");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException("IOError writing file to output stream");
        }
        return "redirect:/gRNA_Designer";
    }
}
