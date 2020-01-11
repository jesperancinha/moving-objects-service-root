package org.jesperancinha.airports.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Page {
    private int pageSize;

    private int totalElements;

    private int pageNumber;

    private int totalPages;

    private Airports airports;
}
