package org.jesperancinha.airports.service;

import org.jesperancinha.airports.model.Airport;
import reactor.core.publisher.Flux;

public interface AirportsService {
    Flux<Airport> getAllAirports();

    Flux<Airport> getFilteredAirports(int size, int page, String lang, String term);

    Flux<Airport> getFilteredAirports(int size, int page, String lang);

    Flux<Airport> getFilteredAirports(int size, int page);

    Flux<Airport> getFilteredAirports(int size);
}
