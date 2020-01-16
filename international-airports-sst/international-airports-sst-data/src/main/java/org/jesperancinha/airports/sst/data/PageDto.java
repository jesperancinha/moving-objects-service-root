package org.jesperancinha.airports.sst.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PageDto {
    private int pageSize;

    private int totalElements;

    private int pageNumber;

    private int totalPages;

    private AirportsDto locations;
}
