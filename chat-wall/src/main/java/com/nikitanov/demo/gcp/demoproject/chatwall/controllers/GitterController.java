package com.nikitanov.demo.gcp.demoproject.chatwall.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GitterController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.icon}")
    private String appIcon;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("appIcon", appIcon);
        return "chat";
    }

}
