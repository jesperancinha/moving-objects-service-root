package org.jesperancinha.airport.rest;

import org.jesperancinha.airport.data.FareDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

public interface FareController {
    @GetMapping("/{originCode}/{destinationCode}")
    Mono<FareDto> getFare(@PathVariable final String originCode, @PathVariable final String destinationCode);
}
