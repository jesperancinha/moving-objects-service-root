package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.data.AirportDto;
import org.jesperancinha.airports.service.AirportsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("airports")
public class AirportControllerImpl implements AirportController {

    private final AirportsService airportsService;

    public AirportControllerImpl(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    @Override
    public Flux<AirportDto> getAirportsBySearchTerm(String term) {
        return airportsService.getAirportsByTerm(term);
    }

    @Override
    public Mono<AirportDto> getAirportByCode(String code) {
        return airportsService.getAirportByCode(code);
    }
}
