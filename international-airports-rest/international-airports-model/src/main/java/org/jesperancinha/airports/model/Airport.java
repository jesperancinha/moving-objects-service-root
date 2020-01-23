package org.jesperancinha.airports.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Airport {

    private String code;

    private String name;

    private String city;

    private List<String> themeList;

    private Coordinate coordinates;

    private List<String> pointsOfSale;

    private List<WebCam> webCams;
}
