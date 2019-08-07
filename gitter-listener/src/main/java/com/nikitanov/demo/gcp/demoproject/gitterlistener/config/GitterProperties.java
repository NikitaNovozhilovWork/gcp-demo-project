package com.nikitanov.demo.gcp.demoproject.gitterlistener.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("gitter")
public class GitterProperties {

    private String community;
    private String token;
    private Oauth oauth;

    @Data
    public static class Oauth {
        private String key;
        private String secret;
        private String redirect_url;
    }

}
