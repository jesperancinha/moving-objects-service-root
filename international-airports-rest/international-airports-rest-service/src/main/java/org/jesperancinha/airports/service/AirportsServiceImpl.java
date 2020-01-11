package org.jesperancinha.airports.service;

import org.jesperancinha.airports.model.Airport;
import org.jesperancinha.airports.repository.AirportsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AirportsServiceImpl implements AirportsService {

    private final AirportsRepository locationRepository;

    public AirportsServiceImpl(AirportsRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Flux<Airport> getAllAirports() {
        return locationRepository.findAllLocatioms();
    }

    public Flux<Airport> getFilteredAirports(int size, int page, String lang, String term) {
        return locationRepository.finaAirportBySizeAndPageAndLangAndTerm(size, page, lang, term);
    }

    @Override
    public Flux<Airport> getFilteredAirports(int size, int page, String lang) {
        return locationRepository.finaAirportBySizeAndPageAndLangAndTerm(size, page, lang);
    }

    @Override
    public Flux<Airport> getFilteredAirports(int size, int page) {
        return locationRepository.finaAirportBySizeAndPageAndLangAndTerm(size, page);
    }

    @Override
    public Flux<Airport> getFilteredAirports(int size) {
        return locationRepository.finaAirportBySizeAndPageAndLangAndTerm(size);
    }
}
