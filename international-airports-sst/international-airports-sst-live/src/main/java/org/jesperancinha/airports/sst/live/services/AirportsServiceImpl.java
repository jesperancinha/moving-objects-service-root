package org.jesperancinha.airports.sst.live.services;

import org.jesperancinha.airports.sst.data.AirportDto;
import org.jesperancinha.airports.sst.live.repo.AirportsRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AirportsServiceImpl implements AirportsService {

    private final AirportsRepo airportsRepo;

    public AirportsServiceImpl(AirportsRepo airportsRepo) {
        this.airportsRepo = airportsRepo;
    }

    public Flux<AirportDto> getAllAirportsBySearchWord(final String searchWord){
        return airportsRepo.findAllAirportsByCitySearchWord(searchWord)
                .map(AirportConverter::toAirportDto);
    }
}
