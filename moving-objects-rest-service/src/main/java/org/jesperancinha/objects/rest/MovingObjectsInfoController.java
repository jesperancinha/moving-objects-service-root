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
@RequestMapping("objectswebcams")
public class MovingObjectsInfoController {

    private final ObjectsAggregatorService objectsAggregatorService;

    @GetMapping("/term/{term}/{radius}")
    public Flux<MovingObjectDto> getAirportsBySearchTerm(
            @PathVariable
            String term,
            @PathVariable(required = false)
            Long radius
    ) {
        return objectsAggregatorService.getObjectsAndCamsBySearchTermAndRadius(term, radius);
    }

    @GetMapping("/code/{code}/{radius}")
    public Flux<MovingObjectDto> getObjectsByCode(
            @PathVariable
            final String code,
            @PathVariable(required = false)
            final  Long radius
    ) {
        return objectsAggregatorService.getObjectsAndCamsByCodeAndRadius(code, radius);
    }

    @GetMapping("/code/{code}")
    public Flux<MovingObjectDto> getObjectsByCode(
            @PathVariable
            final  String code
    ) {
        return this.getObjectsByCode(code, 0L);
    }
}
