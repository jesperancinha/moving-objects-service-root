package org.jesperancinha.objects.rest;

import org.jesperancinha.objects.dto.MovingObjectDto;
import org.jesperancinha.objects.service.ObjectsService;
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
    public Flux<MovingObjectDto> getMovingObjectsBySearchTerm(
            @PathVariable
            final String term
    ) {
        return objectsService.getObjectsByTerm(term);
    }

    @GetMapping("/code/{code}")
    public Flux<MovingObjectDto> getObjectsByCode(
            @PathVariable
            final String code
    ) {
        return from(objectsService.getObjectsByCode(code));
    }
}
