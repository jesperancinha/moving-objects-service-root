package org.jesperancinha.airports.sst.client.webcams;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.jesperancinha.airports.sst.client.webcams.model.WebCamResponse;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;

@Builder
@AllArgsConstructor
public class WebCamsSSTClientImpl extends OkHttpClient implements WebCamsSSTClient {

    private final String url;

    private final String xRapidAPIHost;

    private final String xRapidAPIKey;

    @Builder.Default
    private final Gson gson = new Gson();

    public Mono<WebCamResponse> findWebCamsByPageSizeAndOffset(int pageSize, int pageOffSet) {
        return Mono.from(Mono.fromCallable(() -> {
            final Response response = this.newCall(callWebCamsByPageSizeAndOffset(pageSize, pageOffSet)).execute();
            return gson.fromJson(response.body().string(), WebCamResponse.class);
        }));
    }

    private Request callWebCamsByPageSizeAndOffset(int pageSize, int pageOffSet) throws MalformedURLException {
        return getBuild(this.url.concat("/list/limit=%d,%d"), pageSize, pageOffSet);
    }


    private Request getBuild(String url, Object... properties) throws MalformedURLException {
        return new Request.Builder()
                .url(String.format(url, properties).concat("?lang=en&show=webcams:image,location"))
                .get()
                .addHeader("x-rapidapi-host", xRapidAPIHost)
                .addHeader("x-rapidapi-key", xRapidAPIKey)
                .build();
    }
}
