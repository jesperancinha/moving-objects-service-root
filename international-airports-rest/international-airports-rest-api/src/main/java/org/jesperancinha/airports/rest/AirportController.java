package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.model.Airport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

public interface AirportController {
    @GetMapping
    Flux<Airport> getAllAirports();

    @GetMapping("/{size}/{page}/{lang}/{term}")
    Flux<Airport> getFilteredAirports(@PathVariable final int size, @PathVariable final int page, @PathVariable final String lang, @PathVariable final String term);

    @GetMapping("/{size}/{page}/{lang}")
    Flux<Airport> getFilteredAirports(@PathVariable final int size, @PathVariable final int page, @PathVariable final String lang);

    @GetMapping("/{size}/{page}")
    Flux<Airport> getFilteredAirports(@PathVariable final int size, @PathVariable final int page);

    @GetMapping("/{size}")
    Flux<Airport> getFilteredAirports(@PathVariable final int size);
}
