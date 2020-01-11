package org.jesperancinha.airports.sst.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageDto {
    private int pageSize;

    private int totalElements;

    private int pageNumber;

    private int totalPages;

    private AirportsDto locations;
}
