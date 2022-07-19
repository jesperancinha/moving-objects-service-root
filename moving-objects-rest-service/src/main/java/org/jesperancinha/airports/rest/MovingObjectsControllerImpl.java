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

    @GetMapping("/term/{term}")
    public Flux<MovingObjectsDto> getMovingObjectsBySearchTerm(
            @PathVariable
            final String term
    ) {
        return objectsService.getObjectsByTerm(term);
    }

    @GetMapping("/code/{code}")
    public Flux<MovingObjectsDto> getObjectsByCode(
            @PathVariable
            final String code
    ) {
        return from(objectsService.getObjectsByCode(code));
    }
}
