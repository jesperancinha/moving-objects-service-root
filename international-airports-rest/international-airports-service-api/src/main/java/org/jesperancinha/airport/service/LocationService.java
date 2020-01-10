package org.jesperancinha.airport.service;

import org.jesperancinha.airport.model.Location;
import reactor.core.publisher.Flux;

public interface LocationService {
    Flux<Location> getAllLocations();

    Flux<Location> getFilteredLocations(int size, int page, String lang, String term);

    Flux<Location> getFilteredLocations(int size, int page, String lang);

    Flux<Location> getFilteredLocations(int size, int page);

    Flux<Location> getFilteredLocations(int size);
}
