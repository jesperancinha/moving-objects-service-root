package org.jesperancinha.airports.service;

import org.jesperancinha.airports.data.AirportDto;
import org.jesperancinha.airports.repository.AirportsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AirportsServiceImpl implements AirportsService {

    private final AirportsRepository airportsRepository;

    public AirportsServiceImpl(AirportsRepository airportsRepository) {
        this.airportsRepository = airportsRepository;
    }

    @Override
    public Flux<AirportDto> getAirportsByTerm(String searchTerm) {
        return airportsRepository.finaAirportByTerm(searchTerm)
                .map(TravelConverter::toAirportDto);
    }

    @Override
    public Mono<AirportDto> getAirportByCode(String code) {
        return airportsRepository.findAirportById(code)
                .map(TravelConverter::toAirportDto);
    }
}
