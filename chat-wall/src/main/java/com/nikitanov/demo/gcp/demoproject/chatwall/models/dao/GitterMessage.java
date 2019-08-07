package com.nikitanov.demo.gcp.demoproject.chatwall.models.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "gitter_messages")
public class GitterMessage {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String messageId;
    private String text;
    private String translation;
    private ZonedDateTime sent;
    private String userId;
    private String userUsername;
    private String userDisplayName;

}
