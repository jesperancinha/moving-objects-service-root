package org.jesperancinha.airports.sst.client.airports;

import org.jesperancinha.airports.sst.client.airports.model.Airport;
import reactor.core.publisher.Flux;

public interface AirportsSSTClient {
    Flux<Airport> findAllAiportsBySearchWord(String searchWord);
}
