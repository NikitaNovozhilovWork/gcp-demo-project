package com.nikitanov.demo.gcp.demoproject.gitterhistory.services;

import com.nikitanov.demo.gcp.demoproject.gitterhistory.dao.GitterMessageRepository;
import com.nikitanov.demo.gcp.demoproject.gitterhistory.models.dao.GitterMessge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.stereotype.Service;

/**
 * Gitter message listener class to process new messages
 * from Pub/Sub base on subscription and save into database
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GitterMessageListener implements CommandLineRunner {

    private static final String SUBSCRIPTION = "chat-messages-history-ds";

    private final GitterMessageRepository gitterMessageRepository;
    private final PubSubTemplate pubSubTemplate;

    @Override
    public void run(String... args) throws Exception {
        pubSubTemplate.subscribeAndConvert(
                SUBSCRIPTION,
                receivedMessage -> {
                    GitterMessge message = receivedMessage.getPayload();
                    log.info("Received message: " + message);
                    gitterMessageRepository.save(message);
                    receivedMessage.ack();
                },
                GitterMessge.class);
    }

}
