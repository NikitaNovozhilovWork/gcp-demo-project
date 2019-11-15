package com.nikitanov.demo.gcp.demoproject.chathistory.models.dao;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Accessors(chain = true)
@Table(name = "messages")
public class GitterMessge {

    @Id
    private String messageId;
    private String text;
    private String translation;
    private Date sent;
    private String userId;
    private String userUsername;
    private String userDisplayName;

}
