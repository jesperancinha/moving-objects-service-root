package org.jesperancinha.objects.service;

import org.jesperancinha.objects.dto.MovingObjectDto;
import org.jesperancinha.objects.repository.ObjectsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ObjectsService {

    private final ObjectsRepository airportsRepository;

    public ObjectsService(ObjectsRepository airportsRepository) {
        this.airportsRepository = airportsRepository;
    }

    public Flux<MovingObjectDto> getObjectsByTerm(String searchTerm) {
        return airportsRepository.findObjectsBySearchTerm(searchTerm)
                .map(TravelConverter::toMovingObjectDto);
    }

    public Mono<MovingObjectDto> getObjectsByCode(String code) {
        return airportsRepository.findObjectsByCode(code)
                .map(TravelConverter::toMovingObjectDto);
    }
}
