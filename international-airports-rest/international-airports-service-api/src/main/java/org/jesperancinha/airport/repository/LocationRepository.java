package org.jesperancinha.airport.repository;

import org.jesperancinha.airport.model.Location;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LocationRepository {
    Mono<Location> findLocationById(String codeId);

    Flux<Location> findAllLocatioms();

    Flux<Location> finaLocationBySizeAndPageAndLangAndTerm(int size, int page, String lang, String term);

    Flux<Location> finaLocationBySizeAndPageAndLangAndTerm(int size, int page, String lang);

    Flux<Location> finaLocationBySizeAndPageAndLangAndTerm(int size, int page);

    Flux<Location> finaLocationBySizeAndPageAndLangAndTerm(int size);
}
