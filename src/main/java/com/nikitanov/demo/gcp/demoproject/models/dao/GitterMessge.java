package com.nikitanov.demo.gcp.demoproject.models.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "gitter_messages")
public class GitterMessge {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String messageId;
    private String text;
    private ZonedDateTime sent;
    private String userId;
    private String userUsername;
    private String userDisplayName;

}
