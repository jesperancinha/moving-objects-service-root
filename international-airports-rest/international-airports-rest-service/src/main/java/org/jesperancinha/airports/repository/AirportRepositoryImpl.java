package org.jesperancinha.airports.repository;

import org.jesperancinha.airports.model.Airport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class AirportRepositoryImpl implements AirportsRepository {

    @Value("${org.jesperancinha.airport.airports:http://localhost:8080/airports}")
    private String airportEndpoint;

    private final WebClient webClient;

    public AirportRepositoryImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Airport> findAirportById(final String codeId) {
        return webClient.get()
                .uri(airportEndpoint + "/code/{codeId}", codeId)
                .retrieve()
                .bodyToMono(Airport.class);
    }

    public Flux<Airport> finaAirportByTerm(final String searchTerm) {
        return webClient.get()
                .uri(airportEndpoint + "/search/{codeId}", searchTerm)
                .retrieve()
                .bodyToFlux(Airport.class);
    }

}
