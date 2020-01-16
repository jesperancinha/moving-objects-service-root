package org.jesperancinha.airports.sst.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirportsDto {
    private List<AirportDto> locations;
}
