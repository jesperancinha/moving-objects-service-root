package org.jesperancinha.airports.sst.live;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class AirportsSSTLiveLauncher {

    public static void main(String[] args) {
        SpringApplication.run(AirportsSSTLiveLauncher.class, args);
    }
}
