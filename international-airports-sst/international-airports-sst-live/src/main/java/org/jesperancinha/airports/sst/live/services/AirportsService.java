package org.jesperancinha.airports.sst.live.services;

import org.jesperancinha.airports.sst.data.AirportDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportsService {
    Flux<AirportDto> getAirportsBySearchWord(final String searchWord);

    Mono<AirportDto> getAirportByCode(final String code);
}
