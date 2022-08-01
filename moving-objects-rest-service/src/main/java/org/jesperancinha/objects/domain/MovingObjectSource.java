package org.jesperancinha.objects.domain;

import jakarta.json.bind.annotation.JsonbCreator;

import java.util.List;

public record MovingObjectSource(
        String code,
        String name,
        String city,
        List<String> themeList,
        CoordinateSource coordinates,
        List<String> pointsOfSale,
        List<WebCamSource> webCamSources

) {
    @JsonbCreator
    public MovingObjectSource {
    }
}
