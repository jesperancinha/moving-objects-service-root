package org.jesperancinha.airports.sst.client.airports;

import com.squareup.okhttp.OkHttpClient;
import lombok.Builder;

import java.net.URL;

@Builder
public class AirportsSSTClient extends OkHttpClient {

    private final URL url;

    private final String xRapidAPIHost;

    private final String xRapidAPIKey;

    public AirportsSSTClient(URL url, String xRapidAPIHost, String xRapidAPIKey) {
        this.url = url;
        this.xRapidAPIHost = xRapidAPIHost;
        this.xRapidAPIKey = xRapidAPIKey;
    }
}
