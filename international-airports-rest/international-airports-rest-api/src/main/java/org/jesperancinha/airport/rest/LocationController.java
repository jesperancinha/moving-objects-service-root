package org.jesperancinha.airport.rest;

import org.jesperancinha.airport.model.Location;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

public interface LocationController {
    @GetMapping
    Flux<Location> getAllLocations();

    @GetMapping("/{size}/{page}/{lang}/{term}")
    Flux<Location> getFilteredLocations(@PathVariable final int size, @PathVariable final int page, @PathVariable final String lang, @PathVariable final String term);

    @GetMapping("/{size}/{page}/{lang}")
    Flux<Location> getFilteredLocations(@PathVariable final int size, @PathVariable final int page, @PathVariable final String lang);

    @GetMapping("/{size}/{page}")
    Flux<Location> getFilteredLocations(@PathVariable final int size, @PathVariable final int page);

    @GetMapping("/{size}")
    Flux<Location> getFilteredLocations(@PathVariable final int size);
}
