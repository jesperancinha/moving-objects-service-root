package org.jesperancinha.airports.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.commons.io.IOUtils;
import org.jesperancinha.airports.config.OAuthConfigTest;
import org.jesperancinha.airports.dto.AirportDto;
import org.jesperancinha.airports.dto.WebCamDto;
import org.jesperancinha.airports.repository.ObjectsRepository;
import org.jesperancinha.airports.repository.WebCamRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
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
public class ObjectsAggregatorServiceTest {

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
                        .withBody(IOUtils.toString(ObjectsAggregatorServiceTest.class.getResourceAsStream("/airport-response-AMS.json")))
                        .withHeader("Content-Type", "application/json")
                ));
        stubFor(get(urlEqualTo("/international/airports/live/webcams/location/52.308056/4.764167/10"))
                .willReturn(aResponse()
                        .withBody(IOUtils.toString(ObjectsAggregatorServiceTest.class.getResourceAsStream("/webcams-response-AMS-10.json")))
                        .withHeader("Content-Type", "application/json")
                ));
        BlockHound.install();

    }

    @Test
    public void testGetAirportByCode_whenCode_Get10Cameras() {
        final String testCode = "AMS";
        final Long testRadius = 10L;

        final Flux<AirportDto> airportByCode = objectsAggregatorService.getAirportByCode(testCode, testRadius);

        final Iterable<AirportDto> airportDtos = airportByCode.toIterable();
        assertThat(airportDtos).isNotNull();
        assertThat(airportDtos).isNotEmpty();
        final List<AirportDto> airportDtoList = StreamSupport.stream(airportDtos.spliterator(), false).collect(Collectors.toList());
        assertThat(airportDtoList).hasSize(1);
        final List<WebCamDto> webCams = airportDtoList.get(0).getWebCams();
        assertThat(webCams).isNotEmpty();
        assertThat(webCams).hasSize(10);
    }

    @Test
    public void testGetAirportByCode_whenMonoCode_Get1Camera() {
        final String testCode = "AMS";
        final Long testRadius = 10L;

        final Mono<AirportDto> airportByCode = Mono.from(objectsAggregatorService.getAirportByCode(testCode, testRadius));

        final AirportDto airportDto = airportByCode.block();
        assertThat(airportDto).isNotNull();
        final List<WebCamDto> webCams = airportDto.getWebCams();
        assertThat(webCams).isNotEmpty();
        assertThat(webCams).hasSize(1);
    }

    @AfterAll
    public static void afterAll() {
        wireMockServer.stop();
    }
}