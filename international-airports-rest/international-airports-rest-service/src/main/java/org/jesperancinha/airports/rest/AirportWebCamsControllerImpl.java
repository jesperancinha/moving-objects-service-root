package org.jesperancinha.airports.rest;

import lombok.AllArgsConstructor;
import org.jesperancinha.airports.data.AirportDto;
import org.jesperancinha.airports.service.AirportsAggregatorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@RestController
@RequestMapping("airportwebcams")
public class AirportWebCamsControllerImpl implements AirportController {

    private final AirportsAggregatorService airportsAggregatorService;

    public Flux<AirportDto> getAirportsBySearchTerm(String term, Long radius) {
        return airportsAggregatorService.getAirportsBySearchTerm(term, radius);
    }

    public Flux<AirportDto> getAirportByCode(String code, Long radius) {
        return airportsAggregatorService.getAirportByCode(code, radius);
    }

    public Flux<AirportDto> getAirportByCode(String code) {
        return this.getAirportByCode(code, 0L);
    }
}
