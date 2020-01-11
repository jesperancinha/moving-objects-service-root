package org.jesperancinha.airports.sst.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AirportsDto {
    List<AirportDto> locations;
}
