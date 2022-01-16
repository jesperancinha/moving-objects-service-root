package org.jesperancinha.airports.sst.client.airports;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.extern.slf4j.Slf4j;
import org.jesperancinha.airports.sst.client.airports.model.Airport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.logging.Logger;

@Builder
@AllArgsConstructor
@Slf4j
public class AirportsSSTClientImpl extends OkHttpClient implements AirportsSSTClient {

    private final String url;

    private final String xRapidAPIHost;

    private final String xRapidAPIKey;

    @Default
    private final Gson gson = new Gson();

    public Flux<Airport> findAiportsBySearchWord(final String searchWord) {
        return Mono.fromCallable(() -> {
            final Response response = this.newCall(callAirportsBySearchWord(searchWord)).execute();
            final String textResponse = response.body().string();
            log.info(textResponse);
            return gson.fromJson(textResponse, Airport[].class);
        }).flatMapMany(Flux::fromArray);
    }

    public Mono<Airport> findAirportByCode(final String code) {
        return Mono.fromCallable(() -> {
            final Response response = this.newCall(callAirportByCode(code)).execute();
            final String string = response.body().string();
            if (string.equals("[]")) {
                return null;
            }
            return gson.fromJson(string, Airport[].class)[0];
        });
    }

    private Request callAirportsBySearchWord(String searchWord) throws MalformedURLException {
        return getBuild(this.url.concat("/by-text?text=%s"), searchWord);
    }

    private Request callAirportByCode(String code) throws MalformedURLException {
        return getBuild(this.url.concat("/by-code?code=%s"), code);
    }

    private Request getBuild(String url, Object... properties) {
        return new Request.Builder()
                .url(String.format(Locale.US, url, properties))
                .get()
                .addHeader("x-rapidapi-host", xRapidAPIHost)
                .addHeader("x-rapidapi-key", xRapidAPIKey)
                .build();
    }
}
