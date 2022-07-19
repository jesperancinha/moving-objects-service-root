package org.jesperancinha.airports.rest;

import lombok.AllArgsConstructor;
import org.jesperancinha.airports.dto.MovingObjectsDto;
import org.jesperancinha.airports.service.ObjectsAggregatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@RestController
@RequestMapping("airportwebcams")
public class WebCamsControllerImpl {

    private final ObjectsAggregatorService objectsAggregatorService;

    @GetMapping("/term/{term}/{radius}")
    public Flux<MovingObjectsDto> getAirportsBySearchTerm(
            @PathVariable
            String term,
            @PathVariable(required = false)
            Long radius
    ) {
        return objectsAggregatorService.getAirportsBySearchTerm(term, radius);
    }

    @GetMapping("/code/{code}/{radius}")
    public Flux<MovingObjectsDto> getAirportByCode(
            @PathVariable
            final String code,
            @PathVariable(required = false)
            final  Long radius
    ) {
        return objectsAggregatorService.getAirportByCode(code, radius);
    }

    @GetMapping("/code/{code}")
    public Flux<MovingObjectsDto> getAirportByCode(
            @PathVariable
            final  String code
    ) {
        return this.getAirportByCode(code, 0L);
    }
}
