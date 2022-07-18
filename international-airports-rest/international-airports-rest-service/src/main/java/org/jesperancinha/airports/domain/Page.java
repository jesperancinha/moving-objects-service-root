package org.jesperancinha.airports.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Page {
    private int pageSize;

    private int totalElements;

    private int pageNumber;

    private int totalPages;

    private Airports airports;
}
