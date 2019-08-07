package com.nikitanov.demo.gcp.demoproject.models.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Data
@Accessors(chain = true)
public class GitterMessge {

    private String messageId;
    private String text;
    private ZonedDateTime sent;
    private String userId;
    private String userUsername;
    private String userDisplayName;

}
