package com.nikitanov.demo.gcp.demoproject.gitterlistener.services;

import com.nikitanov.demo.gcp.demoproject.gitterlistener.gateways.GitterGateway;
import com.nikitanov.demo.gcp.demoproject.gitterlistener.gateways.PubSubGateway;
import com.nikitanov.demo.gcp.demoproject.gitterlistener.models.dao.GitterMessge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GitterMessageListener implements CommandLineRunner {

    private final TranslationService translationService;
    private final GitterGateway gitterGateway;
    private final PubSubGateway pubSubGateway;

    @Override
    public void run(String... args) throws Exception {
        gitterGateway.gitterMessagesStream().subscribe(
                event -> {
                    log.info("Received message: " + event);
                    GitterMessge gitterMessage = new GitterMessge()
                            .setMessageId(event.getId())
                            .setSent(event.getSent())
                            .setText(event.getText())
                            .setTranslation(translationService.translate(event.getText()))
                            .setUserId(event.getFromUser().getId())
                            .setUserDisplayName(event.getFromUser().getDisplayName())
                            .setUserUsername(event.getFromUser().getUsername());
                    pubSubGateway.send(gitterMessage);
                    },
                error -> log.error("Gitter receive message error", error));
    }

}
