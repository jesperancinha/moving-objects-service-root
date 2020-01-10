package org.jesperancinha.airport.repository;

import org.jesperancinha.airport.model.Fare;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Repository
public class FareRepositioryImpl implements FareRepository {

    @Value("${org.jesperancinha.airport.fares:http://localhost:8080/fares}")
    private String endpointFares;

    private final WebClient webClient;

    public FareRepositioryImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<Fare> findFare(String originCode, String destinationCode) {
        return webClient.get()
                .uri(endpointFares + "/{originCode}/{destinationCode}", originCode, destinationCode)
                .retrieve()
                .bodyToFlux(Fare.class);
    }
}
