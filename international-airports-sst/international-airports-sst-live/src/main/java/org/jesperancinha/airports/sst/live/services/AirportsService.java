package org.jesperancinha.airports.sst.live.services;

import org.jesperancinha.airports.sst.data.AirportDto;
import reactor.core.publisher.Flux;

public interface AirportsService {
    Flux<AirportDto> getAllAirportsBySearchWord(String searchWord);
}
