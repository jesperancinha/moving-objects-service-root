package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.model.Airport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportController {

    @GetMapping("/term/{term}")
    Flux<Airport> getAirportsBySearchTerm(@PathVariable final String term);


    @GetMapping("/code/{code}")
    Mono<Airport> getAirportByCode(@PathVariable final String code);


}
