package org.jesperancinha.airports.sst.client.airports;

import org.jesperancinha.airports.sst.client.airports.model.Airport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportsSSTClient {
    Flux<Airport> findAirportsBySearchWord(final String searchWord);

    Mono<Airport> findAirportByCode(final String code);
}