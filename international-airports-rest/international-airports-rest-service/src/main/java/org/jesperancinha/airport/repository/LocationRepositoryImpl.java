package org.jesperancinha.airport.repository;

import org.jesperancinha.airport.model.Location;
import org.jesperancinha.airport.model.TravelResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    @Value("${org.jesperancinha.airport.airports:http://localhost:8080/airports}")
    private String endpointAirports;

    private final WebClient webClient;

    public LocationRepositoryImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Location> findLocationById(final String codeId) {
        return webClient.get()
                .uri(endpointAirports + "/{codeId}", codeId)
                .retrieve()
                .bodyToMono(Location.class);
    }

    public Flux<Location> findAllLocatioms() {
        return webClient.get().uri(URI
                .create(endpointAirports))
                .retrieve().bodyToFlux(TravelResponse.class)
                .map(travelResponse -> travelResponse.get_embedded().getLocations())
                .flatMap(Flux::fromIterable);
    }

    public Flux<Location> finaLocationBySizeAndPageAndLangAndTerm(int size, int page, String lang, String term) {
        return findLocationByUrlAndParams( "?size={size}&page={page}&lang={lang}&term={term}", size, page, lang, term);
    }

    @Override
    public Flux<Location> finaLocationBySizeAndPageAndLangAndTerm(int size, int page, String lang) {
        return findLocationByUrlAndParams( "?size={size}&page={page}&lang={lang}", size, page, lang);
    }

    @Override
    public Flux<Location> finaLocationBySizeAndPageAndLangAndTerm(int size, int page) {
        return findLocationByUrlAndParams( "?size={size}&page={page}", size, page);
    }

    @Override
    public Flux<Location> finaLocationBySizeAndPageAndLangAndTerm(int size) {
        return findLocationByUrlAndParams( "?size={size}", size);
    }

    private Flux<Location> findLocationByUrlAndParams(String paramsUrl, Object... params) {
        return webClient.get()
                .uri(endpointAirports +paramsUrl, params)
                .retrieve().bodyToFlux(TravelResponse.class)
                .map(travelResponse -> travelResponse.get_embedded().getLocations())
                .flatMap(Flux::fromIterable);
    }
}
