package com.nikitanov.demo.gcp.demoproject.gitterlistener.services;

import com.nikitanov.demo.gcp.demoproject.gitterlistener.dao.GitterMessageRepository;
import com.nikitanov.demo.gcp.demoproject.gitterlistener.gateways.GitterGateway;
import com.nikitanov.demo.gcp.demoproject.gitterlistener.models.dao.GitterMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * Gitter message listener class to process new messages
 * from gitterGateway, translate them using translation service
 * and save into database
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GitterMessageListener implements CommandLineRunner {

    private final TranslationService translationService;
    private final GitterGateway gitterGateway;
    private final GitterMessageRepository gitterMessageRepository;

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
                    gitterMessageRepository.save(gitterMessage);
                    },
                error -> log.error("Gitter receive message error", error));
    }

}
