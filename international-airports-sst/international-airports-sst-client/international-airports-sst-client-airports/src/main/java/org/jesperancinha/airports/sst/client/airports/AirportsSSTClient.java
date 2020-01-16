package org.jesperancinha.airports.sst.client.airports;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.jesperancinha.airports.sst.client.airports.model.Airport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URL;

@Builder
@AllArgsConstructor
public class AirportsSSTClient extends OkHttpClient {

    private final URL url;

    private final Request request;

    @Builder.Default
    private final Gson gson = new Gson();

    public Flux<Airport> findAllAiportsBySearchWord(final String searchWord) {
        return Mono.fromCallable(() -> {
            Response response = this.newCall(request).execute();
            return gson.fromJson(response.body().string(), Airport[].class);
        }).flatMapMany(Flux::fromArray);
    }
}
