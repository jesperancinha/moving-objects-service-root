package org.jesperancinha.airports.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Airport {

    private String code;

    private String name;

    private String city;

    private List<String> themeList;

    private Coordinate coordinates;

    private List<String> pointsOfSale;

    private List<WebCam> webCams;
}
