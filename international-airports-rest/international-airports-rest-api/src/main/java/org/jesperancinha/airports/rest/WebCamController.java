package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.data.WebCamDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

public interface WebCamController {
    @GetMapping("/{originCode}/{destinationCode}")
    Mono<WebCamDto> getFare(@PathVariable final String originCode, @PathVariable final String destinationCode);
}
