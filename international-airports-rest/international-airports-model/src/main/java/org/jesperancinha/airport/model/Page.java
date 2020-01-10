package org.jesperancinha.airport.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Page {
    private int size;

    private int totalElements;

    private int totalPages;

    private int number;
}
