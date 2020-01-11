package org.jesperancinha.airports.repository;

import org.jesperancinha.airports.model.Airport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportsRepository {
    Mono<Airport> findAirportById(String codeId);

    Flux<Airport> findAllLocatioms();

    Flux<Airport> finaAirportBySizeAndPageAndLangAndTerm(int size, int page, String lang, String term);

    Flux<Airport> finaAirportBySizeAndPageAndLangAndTerm(int size, int page, String lang);

    Flux<Airport> finaAirportBySizeAndPageAndLangAndTerm(int size, int page);

    Flux<Airport> finaAirportBySizeAndPageAndLangAndTerm(int size);
}
