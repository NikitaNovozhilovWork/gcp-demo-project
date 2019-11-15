package com.nikitanov.demo.gcp.demoproject.chathistory.controllers;

import com.nikitanov.demo.gcp.demoproject.chathistory.models.dao.GitterMessge;
import com.nikitanov.demo.gcp.demoproject.chathistory.services.GitterMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Message history controller - read messages from service based on
 * timestamp of previous message and page size
 */
@RestController
@RequestMapping("/api")
public class GitterRestController {

    @Autowired
    private GitterMessageService gitterMessageService;

    @CrossOrigin(origins = "*")
    @PostMapping("/history")
    public List<GitterMessge> getPreviousMessages(@RequestBody Map<String, String> body) throws ParseException {
        return gitterMessageService.getPreviousByDate(
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(body.get("from")),
                Integer.parseInt(body.get("size")));
    }

}
