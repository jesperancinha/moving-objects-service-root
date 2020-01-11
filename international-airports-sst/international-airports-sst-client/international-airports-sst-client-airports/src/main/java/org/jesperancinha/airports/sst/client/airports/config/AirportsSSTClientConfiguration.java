package org.jesperancinha.airports.sst.client.airports.config;

import org.jesperancinha.airports.sst.client.airports.AirportsSSTClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
public class AirportsSSTClientConfiguration {

    @Bean
    public AirportsSSTClient webClient(
            @Value("${org.jesperancinha.airports.sst.client.url}") URL url,
            @Value("${org.jesperancinha.airports.sst.client.airports.x.rapidapi.host}") String xRapidAPIHost,
            @Value("${org.jesperancinha.airports.sst.client.airports.x.rapidapi.key}") String xRapidAPIKey) {
        return AirportsSSTClient.builder().url(url)
                .xRapidAPIHost(xRapidAPIHost)
                .xRapidAPIKey(xRapidAPIKey)
                .build();
    }

}
