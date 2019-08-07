package com.nikitanov.demo.gcp.demoproject.services;

import com.nikitanov.demo.gcp.demoproject.dao.GitterMessageRepository;
import com.nikitanov.demo.gcp.demoproject.gateways.GitterGateway;
import com.nikitanov.demo.gcp.demoproject.models.dao.GitterMessge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class GitterMessageListener implements CommandLineRunner {

    private final GitterGateway gitterGateway;
    private final GitterMessageRepository gitterMessageRepository;
    private final MessageSendingOperations<String> messagingTemplate;

    private static final String DESTINATION = "/topic/gitter-messages";

    @Override
    public void run(String... args) throws Exception {
        gitterGateway.gitterMessagesStream().subscribe(
                event -> {
                    log.info("Received message: " + event);
                    GitterMessge gitterMessage = new GitterMessge()
                            .setMessageId(event.getId())
                            .setSent(event.getSent()).setText(event.getText())
                            .setUserId(event.getFromUser().getId())
                            .setUserDisplayName(event.getFromUser().getDisplayName())
                            .setUserUsername(event.getFromUser().getUsername());
                    gitterMessageRepository.save(gitterMessage);
                    messagingTemplate.convertAndSend(
                            DESTINATION,
                            gitterMessage);
                    },
                error -> log.error("Gitter receive message error", error));
    }

}
