package org.jesperancinha.objects.rest;

import lombok.AllArgsConstructor;
import org.jesperancinha.objects.dto.MovingObjectDto;
import org.jesperancinha.objects.service.ObjectsAggregatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@RestController
@RequestMapping("airportwebcams")
public class MovingObjectsInfoController {

    private final ObjectsAggregatorService objectsAggregatorService;

    @GetMapping("/term/{term}/{radius}")
    public Flux<MovingObjectDto> getAirportsBySearchTerm(
            @PathVariable
            String term,
            @PathVariable(required = false)
            Long radius
    ) {
        return objectsAggregatorService.getAirportsBySearchTerm(term, radius);
    }

    @GetMapping("/code/{code}/{radius}")
    public Flux<MovingObjectDto> getAirportByCode(
            @PathVariable
            final String code,
            @PathVariable(required = false)
            final  Long radius
    ) {
        return objectsAggregatorService.getAirportByCode(code, radius);
    }

    @GetMapping("/code/{code}")
    public Flux<MovingObjectDto> getAirportByCode(
            @PathVariable
            final  String code
    ) {
        return this.getAirportByCode(code, 0L);
    }
}
