package org.jesperancinha.airports.service;

import org.jesperancinha.airports.model.Airport;
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
    public Flux<Airport> getAirportsByTerm(String searchTerm) {
        return airportsRepository.finaAirportByTerm(searchTerm);
    }

    @Override
    public Mono<Airport> getAirportByCode(String code) {
        return airportsRepository.findAirportById(code);
    }
}
