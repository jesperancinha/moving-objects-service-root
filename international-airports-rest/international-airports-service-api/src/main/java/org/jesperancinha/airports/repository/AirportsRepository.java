package org.jesperancinha.airports.repository;

import org.jesperancinha.airports.model.Airport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportsRepository {
    Mono<Airport> findAirportById(String codeId);
    
    Flux<Airport> finaAirportByTerm(String term);

}
