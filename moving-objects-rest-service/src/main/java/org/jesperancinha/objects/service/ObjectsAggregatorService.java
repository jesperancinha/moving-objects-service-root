package org.jesperancinha.objects.service;

import org.jesperancinha.objects.dto.MovingObjectDto;
import org.jesperancinha.objects.dto.CoordinatesDto;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ObjectsAggregatorService {

    private final ObjectsService objectsService;

    private final WebCamService webCamService;

    public ObjectsAggregatorService(ObjectsService objectsService, WebCamService webCamService) {
        this.objectsService = objectsService;
        this.webCamService = webCamService;
    }

    public Flux<MovingObjectDto> getObjectsAndCamsBySearchTermAndRadius(String term, Long radius) {
        return objectsService.getObjectsByTerm(term)
                .map(objectDto -> {
                    CoordinatesDto coordinates = objectDto.coordinates();
                    return webCamService.getCamsByLocationAndRadius(coordinates.x(), coordinates.y(), radius)
                            .map(webCamDto -> Pair.of(objectDto, webCamDto));
                })
                .flatMap(Flux::share)
                .map(webCamDto -> {
                    webCamDto.getFirst().webCams().add(webCamDto.getSecond());
                    return webCamDto.getFirst();
                }).distinct();

    }

    public Flux<MovingObjectDto> getObjectsAndCamsByCodeAndRadius(String code, Long radius) {
        return Flux.from(objectsService.getObjectsByCode(code))
                .map(airportDto -> {
                    CoordinatesDto coordinates = airportDto.coordinates();
                    return webCamService.getCamsByLocationAndRadius(coordinates.x(), coordinates.y(), radius)
                            .map(webCamDto -> Pair.of(airportDto, webCamDto));
                })
                .flatMap(Flux::share)
                .map(webCamDto -> {
                    webCamDto.getFirst().webCams().add(webCamDto.getSecond());
                    return webCamDto.getFirst();
                }).distinct();
    }

}
