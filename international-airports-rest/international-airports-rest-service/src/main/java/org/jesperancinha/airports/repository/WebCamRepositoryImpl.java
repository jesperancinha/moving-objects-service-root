package org.jesperancinha.airports.repository;

import org.jesperancinha.airports.model.WebCam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Repository
public class WebCamRepositoryImpl implements WebCamRepository {

    @Value("${org.jesperancinha.airport.webcams:http://localhost:8080/webcams}")
    private String endpointFares;

    private final WebClient webClient;

    public WebCamRepositoryImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<WebCam> findFare(String originCode, String destinationCode) {
        return webClient.get()
                .uri(endpointFares + "/{originCode}/{destinationCode}", originCode, destinationCode)
                .retrieve()
                .bodyToFlux(WebCam.class);
    }
}
