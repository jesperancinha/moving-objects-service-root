package org.jesperancinha.airports.sst.live.repo;

import org.jesperancinha.airports.sst.client.airports.model.Airport;
import reactor.core.publisher.Flux;

public interface AirportsRepo {
    Flux<Airport> findAllAirportsByCitySearchWord(String searchWord);
}
