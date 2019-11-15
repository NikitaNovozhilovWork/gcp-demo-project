package com.nikitanov.demo.gcp.demoproject.chatwall.services;

import com.nikitanov.demo.gcp.demoproject.chatwall.gateways.GitterGateway;
import com.nikitanov.demo.gcp.demoproject.chatwall.models.dao.GitterMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * Gitter message listener class to process new messages
 * from gitterGateway, translate them using translation service
 * and send to websocket
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GitterMessageListener implements CommandLineRunner {

    private final GitterGateway gitterGateway;
    private final TranslationService translationService;
    private final MessageSendingOperations<String> messagingTemplate;

    private static final String DESTINATION = "/topic/gitter-messages";

    @Override
    public void run(String... args) throws Exception {
        gitterGateway.gitterMessagesStream().subscribe(
                event -> {
                    log.info("Received message: " + event);
                    GitterMessage gitterMessage = new GitterMessage()
                            .setMessageId(event.getId())
                            .setSent(event.getSent())
                            .setText(event.getText())
                            .setTranslation(translationService.translate(event.getText()))
                            .setUserId(event.getFromUser().getId())
                            .setUserDisplayName(event.getFromUser().getDisplayName())
                            .setUserUsername(event.getFromUser().getUsername());
                    messagingTemplate.convertAndSend(
                            DESTINATION,
                            gitterMessage);
                    },
                error -> log.error("Gitter receive message error", error));
    }

}
