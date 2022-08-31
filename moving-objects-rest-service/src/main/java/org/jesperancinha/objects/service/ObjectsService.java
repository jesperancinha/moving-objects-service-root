package org.jesperancinha.objects.service;

import org.jesperancinha.objects.dto.MovingObjectDto;
import org.jesperancinha.objects.repository.ObjectsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ObjectsService {

    private final ObjectsRepository objectsRepository;

    public ObjectsService(ObjectsRepository objectsRepository) {
        this.objectsRepository = objectsRepository;
    }

    public Flux<MovingObjectDto> getObjectsByTerm(String searchTerm) {
        return objectsRepository.findObjectsBySearchTerm(searchTerm)
                .map(TravelConverter::toMovingObjectDto);
    }

    public Mono<MovingObjectDto> getObjectsByCode(String code) {
        return objectsRepository.findObjectsByCode(code)
                .map(TravelConverter::toMovingObjectDto);
    }
}
