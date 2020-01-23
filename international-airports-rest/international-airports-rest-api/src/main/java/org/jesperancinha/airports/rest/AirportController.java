package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.data.AirportDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportController {

    @GetMapping("/term/{term}/{radius}")
    Flux<AirportDto> getAirportsBySearchTerm(@PathVariable final String term, @PathVariable(required = false) final Long radius);


    @GetMapping("/code/{code}/{radius}")
    Flux<AirportDto> getAirportByCode(@PathVariable final String code, @PathVariable(required = false) final Long radius);


}
