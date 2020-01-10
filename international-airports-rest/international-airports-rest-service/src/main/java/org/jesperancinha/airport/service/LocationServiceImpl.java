package org.jesperancinha.airport.service;

import org.jesperancinha.airport.model.Location;
import org.jesperancinha.airport.repository.LocationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Flux<Location> getAllLocations() {
        return locationRepository.findAllLocatioms();
    }

    public Flux<Location> getFilteredLocations(int size, int page, String lang, String term) {
        return locationRepository.finaLocationBySizeAndPageAndLangAndTerm(size, page, lang, term);
    }

    @Override
    public Flux<Location> getFilteredLocations(int size, int page, String lang) {
        return locationRepository.finaLocationBySizeAndPageAndLangAndTerm(size, page, lang);
    }

    @Override
    public Flux<Location> getFilteredLocations(int size, int page) {
        return locationRepository.finaLocationBySizeAndPageAndLangAndTerm(size, page);
    }

    @Override
    public Flux<Location> getFilteredLocations(int size) {
        return locationRepository.finaLocationBySizeAndPageAndLangAndTerm(size);
    }
}
