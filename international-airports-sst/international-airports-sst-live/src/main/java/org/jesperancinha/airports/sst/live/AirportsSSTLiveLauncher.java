package org.jesperancinha.airports.sst.live;

import org.jesperancinha.airports.sst.client.airports.config.AirportsSSTClientConfiguration;
import org.jesperancinha.airports.sst.client.webcams.config.WebCamsSSTClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@Import({
        AirportsSSTClientConfiguration.class,
        WebCamsSSTClientConfiguration.class
})
public class AirportsSSTLiveLauncher {

    public static void main(String[] args) {
        SpringApplication.run(AirportsSSTLiveLauncher.class, args);
    }
}
