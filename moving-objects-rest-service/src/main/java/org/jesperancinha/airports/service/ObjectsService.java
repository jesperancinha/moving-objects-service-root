package org.jesperancinha.airports.service;

import org.jesperancinha.airports.dto.MovingObjectsDto;
import org.jesperancinha.airports.repository.ObjectsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ObjectsService {

    private final ObjectsRepository airportsRepository;

    public ObjectsService(ObjectsRepository airportsRepository) {
        this.airportsRepository = airportsRepository;
    }

    public Flux<MovingObjectsDto> getObjectsByTerm(String searchTerm) {
        return airportsRepository.findObjectsBySearchTerm(searchTerm)
                .map(TravelConverter::toAirportDto);
    }

    public Mono<MovingObjectsDto> getObjectsByCode(String code) {
        return airportsRepository.findObjectsByCode(code)
                .map(TravelConverter::toAirportDto);
    }
}
