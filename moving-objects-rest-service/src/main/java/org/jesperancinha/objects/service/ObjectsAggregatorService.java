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
                .flatMap(objectDto -> {
                    CoordinatesDto coordinates = objectDto.coordinates();
                    return webCamService.getCamsByLocationAndRadius(coordinates.x(), coordinates.y(), radius)
                            .collectList().map(webCamDto -> Pair.of(objectDto, webCamDto));
                })
                .map(webCamDtoPackage -> {
                    final MovingObjectDto movingObjectDto = webCamDtoPackage.getFirst();
                    webCamDtoPackage.getSecond().forEach(webCam -> movingObjectDto.webCams().add(webCam));
                    return movingObjectDto;
                }).distinct();

    }

    public Flux<MovingObjectDto> getObjectsAndCamsByCodeAndRadius(String code, Long radius) {
        return Flux.from(objectsService.getObjectsByCode(code))
                .flatMap(airportDto -> {
                    CoordinatesDto coordinates = airportDto.coordinates();
                    return webCamService.getCamsByLocationAndRadius(coordinates.x(), coordinates.y(), radius)
                            .collectList().map(webCamDto -> Pair.of(airportDto, webCamDto));
                })
                .map(webCamDtoPackage -> {
                    webCamDtoPackage.getSecond().forEach(webCam -> webCamDtoPackage.getFirst().webCams().add(webCam));
                    return webCamDtoPackage.getFirst();
                }).distinct();
    }

}
