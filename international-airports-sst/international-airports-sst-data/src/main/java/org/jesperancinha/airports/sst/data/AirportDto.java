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
public class AirportDto {

    private String code;

    private String name;

    private String city;

    private List<String> themeList;

    private CoordinateDto coordinates;

    private List<String> pointsOfSale;

    private List<WebCamDto> webCams;
}
