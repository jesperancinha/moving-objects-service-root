package org.jesperancinha.airport.rest;

import org.jesperancinha.airport.model.Location;
import org.jesperancinha.airport.service.LocationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("locations")
public class LocationControllerImpl implements LocationController {

    private final LocationService locationService;

    public LocationControllerImpl(LocationService locationService) {
        this.locationService = locationService;
    }

    public Flux<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    public Flux<Location> getFilteredLocations(int size, int page, String lang, String term) {
        return locationService.getFilteredLocations(size,page,lang,term);
    }

    public Flux<Location> getFilteredLocations(int size, int page, String lang) {
        return locationService.getFilteredLocations(size,page,lang);
    }

    public Flux<Location> getFilteredLocations(int size, int page) {
        return locationService.getFilteredLocations(size,page);
    }

    public Flux<Location> getFilteredLocations(int size) {
        return locationService.getFilteredLocations(size);
    }
}
