package org.jesperancinha.objects.repository;

import org.jesperancinha.objects.config.JwtClient;
import org.jesperancinha.objects.domain.MovingObjectSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ObjectsRepository {

    @Value("${objects.jwt.moving}")
    private String movingObjectsEndpoint;

    private final JwtClient jwtClient;

    public ObjectsRepository(JwtClient jwtClient) {
        this.jwtClient = jwtClient;
    }

    public Mono<MovingObjectSource> findObjectsByCode(final String codeId) {
        return jwtClient.get()
                .uri(movingObjectsEndpoint + "/code/{codeId}", codeId)
                .retrieve()
                .bodyToMono(MovingObjectSource.class);
    }

    public Flux<MovingObjectSource> findObjectsBySearchTerm(final String searchTerm) {
        return jwtClient.get()
                .uri(movingObjectsEndpoint + "/search/{searchTerm}", searchTerm)
                .retrieve()
                .bodyToFlux(MovingObjectSource.class);
    }

}
