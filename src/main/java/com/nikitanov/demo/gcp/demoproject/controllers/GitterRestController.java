package com.nikitanov.demo.gcp.demoproject.controllers;

import com.nikitanov.demo.gcp.demoproject.models.dao.GitterMessge;
import com.nikitanov.demo.gcp.demoproject.services.GitterMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Message history controller - read messages from service based on
 * timestamp of previous message and page size
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class GitterRestController {

    @Autowired
    private GitterMessageService gitterMessageService;

    @PostMapping("/history")
    public List<GitterMessge> getPreviousMessagesByDateTime(@RequestBody Map<String, String> body) {
        return gitterMessageService.getPreviousByDate(
                ZonedDateTime.parse(body.get("from"), DateTimeFormatter.ISO_DATE_TIME),
                Integer.parseInt(body.get("size")));
    }

}
