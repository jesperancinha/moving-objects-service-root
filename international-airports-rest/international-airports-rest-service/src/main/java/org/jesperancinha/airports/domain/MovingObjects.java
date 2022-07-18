package org.jesperancinha.airports.domain;

import jakarta.json.bind.annotation.JsonbCreator;

import java.util.List;

public record MovingObjects(List<MovingObject> movingObjects) {
    @JsonbCreator
    public MovingObjects {
    }
}
