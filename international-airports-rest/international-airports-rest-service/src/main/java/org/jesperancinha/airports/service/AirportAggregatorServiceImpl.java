package org.jesperancinha.airports.service;

import org.jesperancinha.airports.data.AirportDto;
import org.jesperancinha.airports.data.CoordinatesDto;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AirportAggregatorServiceImpl implements AirportsAggregatorService {

    private final AirportsService airportsService;

    private final WebCamService webCamService;

    public AirportAggregatorServiceImpl(AirportsService airportsService, WebCamService webCamService) {
        this.airportsService = airportsService;
        this.webCamService = webCamService;
    }

    public Flux<AirportDto> getAirportsBySearchTerm(String term, Long radius) {
        return airportsService.getAirportsByTerm(term)
                .map(airportDto -> {
                    CoordinatesDto coordinates = airportDto.getCoordinates();
                    return webCamService.getCamsByLocationAndRadius(coordinates.getLatitude(), coordinates.getLongitude(), radius)
                            .map(webCamDto -> Pair.of(airportDto, webCamDto));
                })
                .flatMap(Flux::share)
                .map(webCamDto -> {
                    webCamDto.getFirst().getWebCams().add(webCamDto.getSecond());
                    return webCamDto.getFirst();
                }).distinct();

    }

    public Flux<AirportDto> getAirportByCode(String code, Long radius) {
        return Flux.from(airportsService.getAirportByCode(code))
                .map(airportDto -> {
                    CoordinatesDto coordinates = airportDto.getCoordinates();
                    return webCamService.getCamsByLocationAndRadius(coordinates.getLatitude(), coordinates.getLongitude(), radius)
                            .map(webCamDto -> Pair.of(airportDto, webCamDto));
                })
                .flatMap(Flux::share)
                .map(webCamDto -> {
                    webCamDto.getFirst().getWebCams().add(webCamDto.getSecond());
                    return webCamDto.getFirst();
                }).distinct();
    }

}
