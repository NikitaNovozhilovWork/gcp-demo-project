package com.nikitanov.demo.gcp.demoproject.gateways;

import com.nikitanov.demo.gcp.demoproject.configs.GitterProperties;
import com.nikitanov.demo.gcp.demoproject.models.GitterRoomEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyExtractors.toFlux;

@Slf4j
@Service
@RequiredArgsConstructor
public class GitterGateway {

    private final GitterProperties gitterProperties;

    public Flux<GitterRoomEvent> gitterMessagesStream() throws URISyntaxException {
        String api = "v1/rooms/" + gitterProperties.getCommunity() + "/chatMessages";
        URI streamDataUri = new URI("https://stream.gitter.im/" + api);
        WebClient client = WebClient.create();

        log.debug(format("Gitter streaming params: room - %s, token - %s",
                gitterProperties.getCommunity(), gitterProperties.getToken()));

        return client.get()
                .uri(streamDataUri)
                .header("Authorization", "Bearer " + gitterProperties.getToken())
                .accept(APPLICATION_JSON)
                .exchange()
                .flatMapMany(response -> response.body(toFlux(GitterRoomEvent.class)));

    }

}
