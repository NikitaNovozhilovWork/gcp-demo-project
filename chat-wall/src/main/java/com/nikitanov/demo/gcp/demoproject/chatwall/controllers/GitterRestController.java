package com.nikitanov.demo.gcp.demoproject.chatwall.controllers;

import com.nikitanov.demo.gcp.demoproject.chatwall.models.dao.GitterMessage;
import com.nikitanov.demo.gcp.demoproject.chatwall.services.GitterMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GitterRestController {

    @Autowired
    private GitterMessageService gitterMessageService;

    @PostMapping("/history")
    public List<GitterMessage> getPreviousMessages(@RequestBody Map<String, String> body) {
        return gitterMessageService.getPreviousByDate(
                ZonedDateTime.parse(body.get("from"), DateTimeFormatter.ISO_DATE_TIME),
                Integer.parseInt(body.get("size")));
    }

}
