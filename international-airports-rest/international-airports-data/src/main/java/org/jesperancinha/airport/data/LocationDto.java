package org.jesperancinha.airport.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LocationDto {

    private String code;

    private String name;

    private String description;

    private CoordinateDto coordinates;
}
