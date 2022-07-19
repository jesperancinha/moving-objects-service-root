package org.jesperancinha.objects.domain;

import jakarta.json.bind.annotation.JsonbCreator;

import java.util.List;

public record MovingObjects(List<MovingObjectSource> movingObjectSources) {
    @JsonbCreator
    public MovingObjects {
    }
}
