package org.jesperancinha.airports.sst.client.airports.config;

import com.squareup.okhttp.Request;
import org.jesperancinha.airports.sst.client.airports.AirportsSSTClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
public class AirportsSSTClientConfiguration {

    @Bean
    public AirportsSSTClient webClient(
            @Value("${org.jesperancinha.airports.sst.client.airports.url}") URL url,
            @Value("${org.jesperancinha.airports.sst.client.airports.x.rapidapi.host}") String xRapidAPIHost,
            @Value("${org.jesperancinha.airports.sst.client.airports.x.rapidapi.key}") String xRapidAPIKey) {
        return AirportsSSTClient.builder().url(url)
                .request(getBuild(xRapidAPIHost, xRapidAPIKey))
                .build();
    }

    private Request getBuild(String xRapidAPIHost, String xRapidAPIKey) {
        return new Request.Builder()
                .url("https://cometari-airportsfinder-v1.p.rapidapi.com/api/airports/by-text?text=Be")
                .get()
                .addHeader("x-rapidapi-host", xRapidAPIHost)
                .addHeader("x-rapidapi-key", xRapidAPIKey)
                .build();
    }

}
