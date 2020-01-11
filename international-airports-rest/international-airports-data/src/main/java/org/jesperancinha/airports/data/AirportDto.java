package org.jesperancinha.airports.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AirportDto {

    private String code;

    private String name;

    private CoordinateDto coordinates;
}
