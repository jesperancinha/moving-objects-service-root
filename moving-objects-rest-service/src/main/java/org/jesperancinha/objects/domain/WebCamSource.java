package org.jesperancinha.objects.domain;


import jakarta.json.bind.annotation.JsonbCreator;

public record WebCamSource(String title, CoordinateSource coordinate, String wikiInfo, boolean active, WebCamImageSource webCamImage) {
    @JsonbCreator
    public WebCamSource {
    }
}
