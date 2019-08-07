package com.nikitanov.demo.gcp.demoproject.gitterlistener.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class GitterRoomEvent {

    private String id;
    private String text;
    private String html;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date sent;
    private UserInfo fromUser;
    private String v;
    
}
