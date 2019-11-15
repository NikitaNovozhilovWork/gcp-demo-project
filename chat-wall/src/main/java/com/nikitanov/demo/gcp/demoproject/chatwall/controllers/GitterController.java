package com.nikitanov.demo.gcp.demoproject.chatwall.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * UI MVC controller
 * appName - parameter to display application name in header on ui
 * appIcon - parameter where to get shortcut icon on ui
 * historyLink - link to the message history service
 */
@Controller
public class GitterController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.icon}")
    private String appIcon;

    @Value("${spring.application.historyLink}")
    private String historyLink;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("appIcon", appIcon);
        model.addAttribute("historyLink", historyLink);
        return "chat";
    }

}
