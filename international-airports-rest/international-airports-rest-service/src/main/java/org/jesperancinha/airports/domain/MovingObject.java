package org.jesperancinha.airports.domain;

import jakarta.json.bind.annotation.JsonbCreator;

import java.util.List;

public record MovingObject(String code,
                           String name,
                           String city,
                           List<String> themeList,
                           Coordinate coordinates,
                           List<String> pointsOfSale,
                           List<WebCam> webCams

) {
    @JsonbCreator
    public MovingObject {
    }
}
