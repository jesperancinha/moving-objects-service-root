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

    public Flux<AirportDto> getAirportsBySearchTerm(String term, Long radius) {
        return airportsService.getAirportsByTerm(term);
    }

    public Flux<AirportDto> getAirportByCode(String code, Long radius) {
        return Flux.from(airportsService.getAirportByCode(code));
    }

    public Flux<AirportDto> getAirportByCode(String code) {
        return this.getAirportByCode(code, 0L);
    }
}
