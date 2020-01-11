package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.model.Airport;
import org.jesperancinha.airports.service.AirportsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("airports")
public class AirportControllerImpl implements AirportController {

    private final AirportsService airportsService;

    public AirportControllerImpl(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    public Flux<Airport> getAllAirports() {
        return airportsService.getAllAirports();
    }

    public Flux<Airport> getFilteredAirports(int size, int page, String lang, String term) {
        return airportsService.getFilteredAirports(size, page, lang, term);
    }

    public Flux<Airport> getFilteredAirports(int size, int page, String lang) {
        return airportsService.getFilteredAirports(size, page, lang);
    }

    public Flux<Airport> getFilteredAirports(int size, int page) {
        return airportsService.getFilteredAirports(size, page);
    }

    public Flux<Airport> getFilteredAirports(int size) {
        return airportsService.getFilteredAirports(size);
    }
}
