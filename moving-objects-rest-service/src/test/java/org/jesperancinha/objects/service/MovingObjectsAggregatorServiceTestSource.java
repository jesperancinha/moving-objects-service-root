package org.jesperancinha.objects.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.jesperancinha.objects.config.OAuthConfigTest;
import org.jesperancinha.objects.repository.ObjectsRepository;
import org.jesperancinha.objects.repository.WebCamRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {
                ObjectsAggregatorService.class,
                ObjectsService.class,
                WebCamService.class,
                ObjectsRepository.class,
                WebCamRepository.class,
                OAuthConfigTest.class

        })
@ExtendWith(SpringExtension.class)
@Disabled
public class MovingObjectsAggregatorServiceTestSource {

    @Autowired
    private ObjectsAggregatorService objectsAggregatorService;

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void beforeAll() throws IOException {
        wireMockServer = new WireMockServer(8081);
        wireMockServer.start();
        configureFor("127.0.0.1", 8081);
        stubFor(get(urlEqualTo("/international/airports/live/airports/code/AMS"))
                .willReturn(aResponse()
                        .withBody(IOUtils.toString(MovingObjectsAggregatorServiceTestSource.class.getResourceAsStream("/airport-response-AMS.json")))
                        .withHeader("Content-Type", "application/json")
                ));
        stubFor(get(urlEqualTo("/international/airports/live/webcams/location/52.308056/4.764167/10"))
                .willReturn(aResponse()
                        .withBody(IOUtils.toString(MovingObjectsAggregatorServiceTestSource.class.getResourceAsStream("/webcams-response-AMS-10.json")))
                        .withHeader("Content-Type", "application/json")
                ));
        BlockHound.install();

    }

    @Test
    @Disabled
    public void testGetAirportByCode_whenCode_Get10Cameras() {
        val testCode = "AMS";
        val testRadius = 10L;
        val airportByCode = objectsAggregatorService.getAirportByCode(testCode, testRadius);
        val airportDtos = airportByCode.toIterable();
        Assertions.assertThat(airportDtos).isNotNull();
        Assertions.assertThat(airportDtos).isNotEmpty();
        val airportDtoList = StreamSupport.stream(airportDtos.spliterator(), false).collect(Collectors.toList());
        assertThat(airportDtoList).hasSize(10);
        val webCams = (airportDtoList.get(0).webCams());
        Assertions.assertThat(webCams).isNotEmpty();
        Assertions.assertThat(webCams).hasSize(10);
    }

    @Test
    @Disabled
    public void testGetAirportByCode_whenMonoCode_Get1Camera() {
        val testCode = "AMS";
        val testRadius = 10L;
        val airportByCode = Mono.from(objectsAggregatorService.getAirportByCode(testCode, testRadius));
        val airportDto = airportByCode.block();
        assertThat(airportDto).isNotNull();
        val webCams = airportDto.webCams();
        Assertions.assertThat(webCams).isNotEmpty();
        Assertions.assertThat(webCams).hasSize(1);
    }

    @AfterAll
    public static void afterAll() {
        wireMockServer.stop();
    }
}