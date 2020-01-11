package org.jesperancinha.airports.sst.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AirportDto {

    private String code;

    private String name;

    private String city;

    private List<String> themeList;

    private CoordinateDto coordinates;

    private List<String> pointsOfSale;

    private List<WebCamDto> webCams;
}
