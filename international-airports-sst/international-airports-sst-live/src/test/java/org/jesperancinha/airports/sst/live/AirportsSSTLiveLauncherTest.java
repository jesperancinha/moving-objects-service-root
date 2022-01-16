package org.jesperancinha.airports.sst.live;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "rapidapi.airports.key=key",
        "rapidapi.webcams.key=key"
})
class AirportsSSTLiveLauncherTest {

    @Test
    void main() {
    }
}