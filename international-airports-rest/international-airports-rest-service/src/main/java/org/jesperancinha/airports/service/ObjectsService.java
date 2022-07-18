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

    public Flux<MovingObjectsDto> getAirportsByTerm(String searchTerm) {
        return airportsRepository.finaAirportByTerm(searchTerm)
                .map(TravelConverter::toAirportDto);
    }

    public Mono<MovingObjectsDto> getAirportByCode(String code) {
        return airportsRepository.findAirportById(code)
                .map(TravelConverter::toAirportDto);
    }
}
