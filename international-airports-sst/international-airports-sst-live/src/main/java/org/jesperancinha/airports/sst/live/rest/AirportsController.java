package org.jesperancinha.airports.sst.live.rest;

import org.jesperancinha.airports.sst.data.AirportDto;
import org.jesperancinha.airports.sst.live.services.AirportsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/live/airports")
public class AirportsController {

    private final AirportsService airportsService;

    public AirportsController(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    @GetMapping(value = "/search/{searchWord}")
    public Flux<AirportDto> getAirportsBySearchWord(@PathVariable String searchWord) {
        return airportsService.getAirportsBySearchWord(searchWord);
    }

    @GetMapping(value = "/code/{code}")
    public Mono<AirportDto> getAirportsByCode(@PathVariable String code) {
        return airportsService.getAirportByCode(code);
    }
}
