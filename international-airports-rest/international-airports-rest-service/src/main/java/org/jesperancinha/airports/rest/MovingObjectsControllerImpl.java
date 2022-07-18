package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.dto.MovingObjectsDto;
import org.jesperancinha.airports.service.ObjectsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static reactor.core.publisher.Flux.from;

@RestController
@RequestMapping("airports")
public class MovingObjectsControllerImpl {

    private final ObjectsService objectsService;

    public MovingObjectsControllerImpl(ObjectsService objectsService) {
        this.objectsService = objectsService;
    }

    @GetMapping("/term/{term}/{radius}")
    public Flux<MovingObjectsDto> getAirportsBySearchTerm(
            @PathVariable
            final String term,
            @PathVariable(required = false)
            final Long radius
    ) {
        return objectsService.getAirportsByTerm(term);
    }

    @GetMapping("/code/{code}/{radius}")
    public Flux<MovingObjectsDto> getAirportByCode(
            @PathVariable
            final String code,
            @PathVariable(required = false)
            final Long radius
    ) {
        return from(objectsService.getAirportByCode(code));
    }

    @GetMapping("/code/{code}")
    public Flux<MovingObjectsDto> getAirportByCode(
            @PathVariable
            final String code
    ) {
        return this.getAirportByCode(code, 0L);
    }
}
