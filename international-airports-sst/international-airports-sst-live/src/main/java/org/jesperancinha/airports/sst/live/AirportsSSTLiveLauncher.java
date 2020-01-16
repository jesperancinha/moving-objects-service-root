package org.jesperancinha.airports.sst.live;

import org.jesperancinha.airports.sst.client.airports.config.AirportsSSTClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@Import({AirportsSSTClientConfiguration.class})
public class AirportsSSTLiveLauncher {

    public static void main(String[] args) {
        SpringApplication.run(AirportsSSTLiveLauncher.class, args);
    }
}
