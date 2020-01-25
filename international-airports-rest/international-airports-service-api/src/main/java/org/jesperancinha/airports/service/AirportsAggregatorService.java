package org.jesperancinha.airports.service;

import org.jesperancinha.airports.data.AirportDto;
import reactor.core.publisher.Flux;

public interface AirportsAggregatorService {

    Flux<AirportDto> getAirportsBySearchTerm(String term, Long radius);

    Flux<AirportDto> getAirportByCode(String code, Long radius);
}
