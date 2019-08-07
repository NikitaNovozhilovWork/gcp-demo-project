package com.nikitanov.demo.gcp.demoproject.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class GitterRoomEvent {

    private String id;
    private String text;
    private String html;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime sent;
    private UserInfo fromUser;
    private String v;
    
}
