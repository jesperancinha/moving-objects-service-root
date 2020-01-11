package org.jesperancinha.airports.sst.client.webcams;

import com.squareup.okhttp.OkHttpClient;
import lombok.Builder;

import java.net.URL;

@Builder
public class WebCamsSSTClient extends OkHttpClient {

    private final URL url;

    private final String xRapidAPIHost;

    private final String xRapidAPIKey;

    public WebCamsSSTClient(URL url, String xRapidAPIHost, String xRapidAPIKey) {
        this.url = url;
        this.xRapidAPIHost = xRapidAPIHost;
        this.xRapidAPIKey = xRapidAPIKey;
    }
}
