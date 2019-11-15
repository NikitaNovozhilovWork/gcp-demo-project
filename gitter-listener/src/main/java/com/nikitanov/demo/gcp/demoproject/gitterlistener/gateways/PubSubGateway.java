package com.nikitanov.demo.gcp.demoproject.gitterlistener.gateways;

import com.nikitanov.demo.gcp.demoproject.gitterlistener.models.dao.GitterMessge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.stereotype.Service;

/**
 * Pub/Sub gateway to send gitter messages to Pub/sub base on topic
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PubSubGateway {

    private static final String TOPIC = "chat-messages";

    private final PubSubTemplate pubSubTemplate;

    public void send(GitterMessge messge) {
        pubSubTemplate.publish(TOPIC, messge);
    }

}
