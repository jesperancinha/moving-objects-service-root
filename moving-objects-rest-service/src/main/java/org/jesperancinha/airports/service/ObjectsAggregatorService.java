package org.jesperancinha.airports.service;

import org.jesperancinha.airports.dto.MovingObjectsDto;
import org.jesperancinha.airports.dto.CoordinatesDto;
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

    public Flux<MovingObjectsDto> getAirportsBySearchTerm(String term, Long radius) {
        return objectsService.getAirportsByTerm(term)
                .map(airportDto -> {
                    CoordinatesDto coordinates = airportDto.coordinates();
                    return webCamService.getCamsByLocationAndRadius(coordinates.latitude(), coordinates.longitude(), radius)
                            .map(webCamDto -> Pair.of(airportDto, webCamDto));
                })
                .flatMap(Flux::share)
                .map(webCamDto -> {
                    webCamDto.getFirst().webCams().add(webCamDto.getSecond());
                    return webCamDto.getFirst();
                }).distinct();

    }

    public Flux<MovingObjectsDto> getAirportByCode(String code, Long radius) {
        return Flux.from(objectsService.getAirportByCode(code))
                .map(airportDto -> {
                    CoordinatesDto coordinates = airportDto.coordinates();
                    return webCamService.getCamsByLocationAndRadius(coordinates.latitude(), coordinates.longitude(), radius)
                            .map(webCamDto -> Pair.of(airportDto, webCamDto));
                })
                .flatMap(Flux::share)
                .map(webCamDto -> {
                    webCamDto.getFirst().webCams().add(webCamDto.getSecond());
                    return webCamDto.getFirst();
                }).distinct();
    }

}
