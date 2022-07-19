package org.jesperancinha.objects.repository;

import org.jesperancinha.objects.domain.MovingObjectSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ObjectsRepository {

    @Value("${org.jesperancinha.airport.airports:http://localhost:8080/airports}")
    private String airportEndpoint;

    private final WebClient webClient;

    public ObjectsRepository(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<MovingObjectSource> findObjectsByCode(final String codeId) {
        return webClient.get()
                .uri(airportEndpoint + "/code/{codeId}", codeId)
                .retrieve()
                .bodyToMono(MovingObjectSource.class);
    }

    public Flux<MovingObjectSource> findObjectsBySearchTerm(final String searchTerm) {

        return webClient.get()
                .uri(airportEndpoint + "/search/{codeId}", searchTerm)
                .retrieve()
                .bodyToFlux(MovingObjectSource.class);
    }

}
