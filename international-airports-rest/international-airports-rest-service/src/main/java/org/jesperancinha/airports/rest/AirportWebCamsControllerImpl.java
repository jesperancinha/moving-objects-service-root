package org.jesperancinha.airports.rest;

import lombok.AllArgsConstructor;
import org.jesperancinha.airports.data.AirportDto;
import org.jesperancinha.airports.data.CoordinateDto;
import org.jesperancinha.airports.service.AirportsService;
import org.jesperancinha.airports.service.WebCamService;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("airportwebcams")
public class AirportWebCamsControllerImpl implements AirportController {

    private final AirportsService airportsService;

    private final WebCamService webCamService;

    @Override
    public Flux<AirportDto> getAirportsBySearchTerm(String term) {
        return airportsService.getAirportsByTerm(term);
    }

    @Override
    public Mono<AirportDto> getAirportByCode(String code) {
        List<AirportDto> airportDtos = new ArrayList<>();
        return Mono.from(airportsService.getAirportByCode(code)
                .map(airportDto -> {
                    airportDtos.add(airportDto);
                    CoordinateDto coordinates = airportDto.getCoordinates();
                    return webCamService.getCamsByLocationAndRadius(coordinates.getLatitude(), coordinates.getLongitude(), 100);
                }).flatMapMany(Flux::from)
                .map(webCamDto -> {
                    airportDtos.get(0).getWebCamDtoList().add(webCamDto);
                    return airportDtos.get(0);
                }));
    }
}
