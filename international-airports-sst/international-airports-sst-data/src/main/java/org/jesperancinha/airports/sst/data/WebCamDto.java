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
public class WebCamDto {
    private String title;
    private CoordinateDto coordinateDto;
    private String wikiInfo;
    private boolean active;
    private WebCamImageDto webCamImage;
}
