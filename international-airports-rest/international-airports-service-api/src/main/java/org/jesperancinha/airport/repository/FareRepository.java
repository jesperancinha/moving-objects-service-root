package org.jesperancinha.airport.repository;

import org.jesperancinha.airport.model.Fare;
import reactor.core.publisher.Flux;

public interface FareRepository {
    Flux<Fare> findFare(String originCode, String destinationCode);
}
