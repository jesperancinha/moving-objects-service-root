package org.jesperancinha.airports.service;

import org.jesperancinha.airports.model.Airport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportsService {
    Flux<Airport> getAirportsByTerm(final String term);

    Mono<Airport> getAirportByCode(final String code);
}
