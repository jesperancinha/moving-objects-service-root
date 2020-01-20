package org.jesperancinha.airports.sst.live.services;

import org.jesperancinha.airports.sst.client.webcams.model.WebCam;
import org.jesperancinha.airports.sst.client.webcams.model.WebCamImage;
import org.jesperancinha.airports.sst.client.webcams.model.WebCamLocation;
import org.jesperancinha.airports.sst.client.webcams.model.WebCamUrls;
import org.jesperancinha.airports.sst.data.CoordinateDto;
import org.jesperancinha.airports.sst.data.WebCamDto;
import org.jesperancinha.airports.sst.data.WebCamImageDto;

import java.util.Objects;

public class WebCamConverter {

    public static WebCamDto toWebCamDto(WebCam webCam) {
        final WebCamLocation location = webCam.getLocation();
        final String wikipedia = Objects.isNull(location) ? null : location.getWikipedia();
        return WebCamDto.builder()
                .active(webCam.getStatus().equals("active"))
                .title(webCam.getTitle())
                .webCamImage(toWebCamImageDto(webCam.getImage()))
                .wikiInfo(wikipedia)
                .coordinateDto(toCoordinateDto(location))
                .build();
    }

    private static CoordinateDto toCoordinateDto(WebCamLocation location) {
        if(Objects.isNull(location)){
            return CoordinateDto.builder().build();
        }
        return CoordinateDto.builder()
                .longitude(location.getLongitude())
                .latitude(location.getLatitude())
                .build();
    }

    private static WebCamImageDto toWebCamImageDto(WebCamImage image) {
        if (Objects.isNull(image)) {
            return WebCamImageDto.builder().build();
        }
        final WebCamUrls imageCurrent = image.getCurrent();
        return WebCamImageDto.builder()
                .iconUrl(imageCurrent.getIcon())
                .thumbnailUrl(imageCurrent.getThumbnail())
                .previewUrl(imageCurrent.getPreview())
                .toenailUrl(imageCurrent.getToenail())
                .build();
    }
}
