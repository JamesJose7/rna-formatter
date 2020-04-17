package com.jeeps.rnaformatter.web.controller;

import com.jeeps.rnaformatter.model.HighlighterForm;
import com.jeeps.rnaformatter.service.SequenceHighlighterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class SequenceHighlighter {
//    @Value("${server.servlet.context-path}")
//    private String context;

    @Autowired
    private SequenceHighlighterService sequenceHighlighterService;

    @GetMapping("/highlighter")
    public String highlighter(Model model) {
        HighlighterForm highlighterForm = new HighlighterForm();
        if (model.containsAttribute("highlighterForm"))
            highlighterForm = (HighlighterForm) model.getAttribute("highlighterForm");
        model.addAttribute("highlighterForm", highlighterForm);
        model.addAttribute("action", /*context +*/ "/download");
        model.addAttribute("actionBtn", "Download");
        return "highlighter";
    }

    @PostMapping("/download")
    public String result(HttpServletResponse response,
                         Model model,
                         HighlighterForm highlighterForm) {
        try {
            String content = sequenceHighlighterService.getApeContent(highlighterForm.getName(), highlighterForm.getUrl());
            InputStream is = new ByteArrayInputStream(content.getBytes());

            response.setHeader("Content-disposition", "attachment; filename="+ highlighterForm.getName() + ".flat");
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException("IOError writing file to output stream");
        }
        return "redirect:/highlighter";
    }
}
