package org.jesperancinha.airports.sst.client.webcams.model;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WebCamResponseTest {

    private Gson gson = new Gson();

    @Test
    public void testWebCamResponse_whenParse_ThenOkResult() throws IOException {
        final InputStream resourceAsStream = getClass().getResourceAsStream("/webcams-query-1.json");
        assertThat(resourceAsStream).isNotNull();
        final String analysisTest = IOUtils.toString(resourceAsStream, Charset.defaultCharset());

        final WebCamResponse webCamResponse = gson.fromJson(analysisTest, WebCamResponse.class);

        WebCamPage result = webCamResponse.getResult();
        List<WebCam> webcams = result.getWebcams();
        final WebCam webCam0 = webcams.get(0);
        assertThat(webCam0).isNotNull();
        assertThat(webCam0.getLocation()).isNotNull();
        assertThat(webCam0.getImage()).isNotNull();
    }

    @Test
    public void testWebCamResponse_whenShortAnswerPars_ThenOkResult() throws IOException {
        final InputStream resourceAsStream = getClass().getResourceAsStream("/webcams-no-extras.json");
        assertThat(resourceAsStream).isNotNull();
        final String analysisTest = IOUtils.toString(resourceAsStream, Charset.defaultCharset());

        final WebCamResponse webCamResponse = gson.fromJson(analysisTest, WebCamResponse.class);

        final WebCamPage result = webCamResponse.getResult();
        List<WebCam> webcams = result.getWebcams();
        final WebCam webCam0 = webcams.get(0);
        assertThat(webCam0).isNotNull();
        assertThat(webCam0.getLocation()).isNull();
        assertThat(webCam0.getImage()).isNull();
    }
}