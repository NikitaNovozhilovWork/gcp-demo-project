package com.nikitanov.demo.gcp.demoproject.gitterlistener.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gcp.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Google Cloud Pub/Sub
 */
@Configuration
public class PubSubConfig {

    /**
     * This bean enables serialization/deserialization of Java objects to JSON allowing you
     * utilize JSON message payloads in Cloud Pub/Sub.
     * @param objectMapper the object mapper to use
     * @return a Jackson message converter
     */
    @Bean
    public JacksonPubSubMessageConverter jacksonPubSubMessageConverter(ObjectMapper objectMapper) {
        return new JacksonPubSubMessageConverter(objectMapper);
    }

}
