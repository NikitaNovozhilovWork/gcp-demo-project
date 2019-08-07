package com.nikitanov.demo.gcp.demoproject.chatwall.services;

import com.nikitanov.demo.gcp.demoproject.chatwall.models.dao.GitterMessge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GitterMessageListener implements CommandLineRunner {

    private static final String SUBSCRIPTION = "chat-messages-updater-sp";
    private static final String DESTINATION = "/topic/gitter-messages";

    private final MessageSendingOperations<String> messagingTemplate;
    private final PubSubTemplate pubSubTemplate;

    @Override
    public void run(String... args) throws Exception {
        pubSubTemplate.subscribeAndConvert(
                SUBSCRIPTION,
                receivedMessage -> {
                    GitterMessge message = receivedMessage.getPayload();
                    log.info("Received message: " + message);
                    messagingTemplate.convertAndSend(
                            DESTINATION,
                            message);
                    receivedMessage.ack();
                },
                GitterMessge.class);
    }

}
