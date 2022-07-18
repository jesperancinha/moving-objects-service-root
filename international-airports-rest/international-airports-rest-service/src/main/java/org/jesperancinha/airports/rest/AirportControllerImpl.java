package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.dto.AirportDto;
import org.jesperancinha.airports.service.ObjectsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("airports")
public class AirportControllerImpl {

    private final ObjectsService objectsService;

    public AirportControllerImpl(ObjectsService objectsService) {
        this.objectsService = objectsService;
    }

    @GetMapping("/term/{term}/{radius}")
    public Flux<AirportDto> getAirportsBySearchTerm(
            @PathVariable
            String term,
            @PathVariable(required = false)
            Long radius) {
        return objectsService.getAirportsByTerm(term);
    }

    @GetMapping("/code/{code}/{radius}")
    public Flux<AirportDto> getAirportByCode(
            @PathVariable
            String code,
            @PathVariable(required = false)
            Long radius) {
        return Flux.from(objectsService.getAirportByCode(code));
    }

    @GetMapping("/code/{code}")
    public Flux<AirportDto> getAirportByCode(
            @PathVariable
            String code) {
        return this.getAirportByCode(code, 0L);
    }
}
