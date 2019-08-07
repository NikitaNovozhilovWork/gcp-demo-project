package com.nikitanov.demo.gcp.demoproject.gitterlistener.models.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class GitterMessge {

    private String messageId;
    private String text;
    private String translation;
    private Date sent;
    private String userId;
    private String userUsername;
    private String userDisplayName;

}
