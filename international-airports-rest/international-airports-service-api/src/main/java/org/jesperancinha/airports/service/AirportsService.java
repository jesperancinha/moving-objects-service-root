package org.jesperancinha.airports.service;

import org.jesperancinha.airports.data.AirportDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportsService {
    Flux<AirportDto> getAirportsByTerm(final String term);

    Mono<AirportDto> getAirportByCode(final String code);
}
