package org.jesperancinha.airport.service;

import org.jesperancinha.airport.data.FareDto;
import reactor.core.publisher.Mono;

public interface FareService {
    Mono<FareDto> getFare(String originCode, String destinationCode);
}
