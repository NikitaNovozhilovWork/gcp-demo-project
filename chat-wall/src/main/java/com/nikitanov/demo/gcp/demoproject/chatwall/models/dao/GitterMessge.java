package com.nikitanov.demo.gcp.demoproject.chatwall.models.dao;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Accessors(chain = true)
@Entity(name = "gitter_messages")
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
