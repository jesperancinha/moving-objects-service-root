package org.jesperancinha.airports.rest;

import lombok.AllArgsConstructor;
import org.jesperancinha.airports.dto.AirportDto;
import org.jesperancinha.airports.service.ObjectsAggregatorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@RestController
@RequestMapping("airportwebcams")
public class AirportWebCamsControllerImpl {

    private final ObjectsAggregatorService objectsAggregatorService;

    @org.springframework.web.bind.annotation.GetMapping("/term/{term}/{radius}")
    public Flux<AirportDto> getAirportsBySearchTerm(
            @org.springframework.web.bind.annotation.PathVariable
            String term, @org.springframework.web.bind.annotation.PathVariable(required = false)
            Long radius) {
        return objectsAggregatorService.getAirportsBySearchTerm(term, radius);
    }

    @org.springframework.web.bind.annotation.GetMapping("/code/{code}/{radius}")
    public Flux<AirportDto> getAirportByCode(
            @org.springframework.web.bind.annotation.PathVariable
            String code, @org.springframework.web.bind.annotation.PathVariable(required = false)
            Long radius) {
        return objectsAggregatorService.getAirportByCode(code, radius);
    }

    @org.springframework.web.bind.annotation.GetMapping("/code/{code}")
    public Flux<AirportDto> getAirportByCode(
            @org.springframework.web.bind.annotation.PathVariable
            String code) {
        return this.getAirportByCode(code, 0L);
    }
}
