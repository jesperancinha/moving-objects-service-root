package org.jesperancinha.objects.domain;

import jakarta.json.bind.annotation.JsonbCreator;

public record Page(int pageSize, int totalElements, int pageNumber, int totalPages, MovingObjects movingObjects) {
    @JsonbCreator
    public Page {
    }
}
