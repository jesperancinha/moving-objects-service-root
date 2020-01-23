package org.jesperancinha.airports.rest;

import lombok.AllArgsConstructor;
import org.jesperancinha.airports.data.AirportDto;
import org.jesperancinha.airports.data.CoordinatesDto;
import org.jesperancinha.airports.service.AirportsService;
import org.jesperancinha.airports.service.WebCamService;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("airportwebcams")
public class AirportWebCamsControllerImpl implements AirportController {

    private final AirportsService airportsService;

    private final WebCamService webCamService;

    public Flux<AirportDto> getAirportsBySearchTerm(String term) {
        return airportsService.getAirportsByTerm(term)
                .map(airportDto -> {
                    CoordinatesDto coordinates = airportDto.getCoordinates();
                    return webCamService.getCamsByLocationAndRadius(coordinates.getLatitude(), coordinates.getLongitude(), 100)
                            .map(webCamDto -> Pair.of(airportDto, webCamDto));
                })
                .flatMap(Flux::share)
                .map(webCamDto -> {
                    webCamDto.getFirst().getWebCams().add(webCamDto.getSecond());
                    return webCamDto.getFirst();
                });

    }

    public Mono<AirportDto> getAirportByCode(String code) {
        return Mono.from(airportsService.getAirportByCode(code)
                .map(airportDto -> {
                    CoordinatesDto coordinates = airportDto.getCoordinates();
                    return webCamService.getCamsByLocationAndRadius(coordinates.getLatitude(), coordinates.getLongitude(), 100)
                            .map(webCamDto -> Pair.of(airportDto, webCamDto));
                })
                .flatMapMany(Flux::from)
                .map(webCamDto -> {
                    webCamDto.getFirst().getWebCams().add(webCamDto.getSecond());
                    return webCamDto.getFirst();
                }));
    }
}
