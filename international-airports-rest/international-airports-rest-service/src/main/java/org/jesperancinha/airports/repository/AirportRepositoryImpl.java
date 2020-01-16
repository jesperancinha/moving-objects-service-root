package org.jesperancinha.airports.repository;

import org.jesperancinha.airports.model.Airport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Repository
public class AirportRepositoryImpl implements AirportsRepository {

    @Value("${org.jesperancinha.airport.airports:http://localhost:8080/airports}")
    private String airportEndpoint;

    private final WebClient webClient;

    public AirportRepositoryImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Airport> findAirportById(final String codeId) {
        return webClient.get()
                .uri(airportEndpoint + "/{codeId}", codeId)
                .retrieve()
                .bodyToMono(Airport.class);
    }

    public Flux<Airport> findAllLocatioms() {
        return webClient.get().uri(URI
                .create(airportEndpoint))
                .retrieve().bodyToFlux(Airport.class);
    }

    public Flux<Airport> finaAirportBySizeAndPageAndLangAndTerm(int size, int page, String lang, String term) {
        return findAirportByUrlAndParams("?size={size}&page={page}&lang={lang}&term={term}", size, page, lang, term);
    }

    @Override
    public Flux<Airport> finaAirportBySizeAndPageAndLangAndTerm(int size, int page, String lang) {
        return findAirportByUrlAndParams("?size={size}&page={page}&lang={lang}", size, page, lang);
    }

    @Override
    public Flux<Airport> finaAirportBySizeAndPageAndLangAndTerm(int size, int page) {
        return findAirportByUrlAndParams("?size={size}&page={page}", size, page);
    }

    @Override
    public Flux<Airport> finaAirportBySizeAndPageAndLangAndTerm(int size) {
        return findAirportByUrlAndParams("?size={size}", size);
    }

    private Flux<Airport> findAirportByUrlAndParams(String paramsUrl, Object... params) {
        return webClient.get()
                .uri(airportEndpoint + paramsUrl, params)
                .retrieve().bodyToFlux(Airport.class);
    }
}
