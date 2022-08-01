package org.jesperancinha.objects;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "OpenAPI definition"),
        servers = {
                @Server(url = "${objects.okta.server.url}/aggregator",
                        description = "Server URL")
        }
)
public class ObjectsSupportLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ObjectsSupportLauncher.class, args);
    }

}
