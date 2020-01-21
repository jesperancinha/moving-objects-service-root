package org.jesperancinha.airports.sst.live.services;

import org.jesperancinha.airports.sst.data.AirportDto;
import org.jesperancinha.airports.sst.live.repo.AirportsRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AirportsServiceImpl implements AirportsService {

    private final AirportsRepo airportsRepo;

    public AirportsServiceImpl(AirportsRepo airportsRepo) {
        this.airportsRepo = airportsRepo;
    }

    public Flux<AirportDto> getAirportsBySearchWord(final String searchWord) {
        return airportsRepo.findAirportsByCitySearchWord(searchWord)
                .map(AirportConverter::toAirportDto);
    }

    @Override
    public Mono<AirportDto> getAirportByCode(String code) {
        return airportsRepo.findAirportByCode(code)
                .map(AirportConverter::toAirportDto);
    }
}
