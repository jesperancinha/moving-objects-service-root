package org.jesperancinha.airports.sst.client.webcams.config;

import org.jesperancinha.airports.sst.client.webcams.WebCamsSSTClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
public class WebCamsSSTClientConfiguration {

    @Bean
    public WebCamsSSTClient webClient(
            @Value("${org.jesperancinha.airports.sst.client.url}") URL url,
            @Value("${org.jesperancinha.airports.sst.client.webcams.x.rapidapi.host}") String xRapidAPIHost,
            @Value("${org.jesperancinha.airports.sst.client.webcams.x.rapidapi.key}") String xRapidAPIKey) {
        return WebCamsSSTClient.builder().url(url)
                .xRapidAPIHost(xRapidAPIHost)
                .xRapidAPIKey(xRapidAPIKey)
                .build();
    }

}
