package org.jesperancinha.airports.domain;

import jakarta.json.bind.annotation.JsonbCreator;

public record Page(int pageSize, int totalElements, int pageNumber, int totalPages, Airports airports) {
    @JsonbCreator
    public Page {
    }
}
