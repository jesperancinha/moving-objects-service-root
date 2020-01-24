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

@AllArgsConstructor
@RestController
@RequestMapping("airportwebcams")
public class AirportWebCamsControllerImpl implements AirportController {

    private final AirportsService airportsService;

    private final WebCamService webCamService;

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

    public Flux<AirportDto> getAirportByCode(String code) {
        return this.getAirportByCode(code, 0L);
    }
}
