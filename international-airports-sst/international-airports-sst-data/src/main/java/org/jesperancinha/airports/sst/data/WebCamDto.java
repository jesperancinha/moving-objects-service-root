package org.jesperancinha.airports.sst.data;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WebCamDto {
    private String title;
    private CoordinateDto coordinateDto;
    private String wikiInfo;
    private boolean active;
    private WebCamImageDto webCamImage;
}
