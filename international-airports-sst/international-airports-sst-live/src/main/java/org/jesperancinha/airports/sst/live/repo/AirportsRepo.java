package org.jesperancinha.airports.sst.live.repo;

import org.jesperancinha.airports.sst.client.airports.model.Airport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportsRepo {
    Flux<Airport> findAirportsByCitySearchWord(String searchWord);

    Mono<Airport> findAirportByCode(String code);
}
