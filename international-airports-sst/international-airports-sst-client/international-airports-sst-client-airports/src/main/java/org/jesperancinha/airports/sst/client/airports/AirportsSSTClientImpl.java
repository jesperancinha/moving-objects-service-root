package org.jesperancinha.airports.sst.client.airports;

import com.google.gson.Gson;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.jesperancinha.airports.sst.client.airports.model.Airport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URL;

@Builder
@AllArgsConstructor
public class AirportsSSTClientImpl extends OkHttpClient implements AirportsSSTClient {

    private final String url;

    private final String xRapidAPIHost;

    private final String xRapidAPIKey;

    @Builder.Default
    private final Gson gson = new Gson();

    public Flux<Airport> findAllAiportsBySearchWord(final String searchWord) {
        return Mono.fromCallable(() -> {
            final Response response = this.newCall(getBuild(searchWord)).execute();
            return gson.fromJson(response.body().string(), Airport[].class);
        }).flatMapMany(Flux::fromArray);
    }

    private Request getBuild(String append) throws MalformedURLException {
        return new Request.Builder()
                .url(getFullQueryTextUrl(append))
                .get()
                .addHeader("x-rapidapi-host", xRapidAPIHost)
                .addHeader("x-rapidapi-key", xRapidAPIKey)
                .build();
    }

    private URL getFullQueryTextUrl(String append) {
        return HttpUrl.parse(url)
                .newBuilder()
                .addEncodedQueryParameter("text", append)
                .build().url();
    }
}
