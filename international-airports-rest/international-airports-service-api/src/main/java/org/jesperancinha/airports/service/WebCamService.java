package org.jesperancinha.airports.service;

import org.jesperancinha.airports.data.WebCamDto;
import reactor.core.publisher.Mono;

public interface WebCamService {
    Mono<WebCamDto> getFare(String originCode, String destinationCode);
}
