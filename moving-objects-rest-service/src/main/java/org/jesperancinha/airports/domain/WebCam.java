package org.jesperancinha.airports.domain;


import jakarta.json.bind.annotation.JsonbCreator;

public record WebCam(String title, Coordinate coordinate, String wikiInfo, boolean active, WebCamImage webCamImage) {
    @JsonbCreator
    public WebCam {
    }
}
