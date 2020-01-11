package org.jesperancinha.airports.repository;

import org.jesperancinha.airports.model.WebCam;
import reactor.core.publisher.Flux;

public interface WebCamRepository {
    Flux<WebCam> findFare(String originCode, String destinationCode);
}
