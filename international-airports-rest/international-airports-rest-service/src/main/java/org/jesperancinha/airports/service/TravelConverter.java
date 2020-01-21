package org.jesperancinha.airports.service;

import org.jesperancinha.airports.data.AirportDto;
import org.jesperancinha.airports.data.CoordinateDto;
import org.jesperancinha.airports.data.WebCamDto;
import org.jesperancinha.airports.data.WebCamImageDto;
import org.jesperancinha.airports.model.Airport;
import org.jesperancinha.airports.model.Coordinate;
import org.jesperancinha.airports.model.WebCam;
import org.jesperancinha.airports.model.WebCamImage;

public class TravelConverter {

    public static WebCamDto toWebCamDto(final WebCam webCam) {
        return WebCamDto.builder()
                .active(webCam.isActive())
                .coordinate(toCoordinatesDto(webCam.getCoordinate()))
                .title(webCam.getTitle())
                .webCamImage(toWebCamImageDto(webCam.getWebCamImage()))
                .wikiInfo(webCam.getWikiInfo())
                .build();
    }

    private static WebCamImageDto toWebCamImageDto(WebCamImage webCamImage) {
        return WebCamImageDto.builder()
                .iconUrl(webCamImage.getIconUrl())
                .previewUrl(webCamImage.getPreviewUrl())
                .thumbnailUrl(webCamImage.getThumbnailUrl())
                .toenailUrl(webCamImage.getToenailUrl())
                .build();
    }

    public static AirportDto toAirportDto(final Airport location) {
        return AirportDto.builder()
                .code(location.getCode())
                .name(location.getName())
                .coordinates(toCoordinatesDto(location.getCoordinates()))
                .build();
    }

    private static CoordinateDto toCoordinatesDto(final Coordinate coordinate) {
        return CoordinateDto.builder()
                .latitude(coordinate.getLatitude())
                .longitude(coordinate.getLongitude())
                .build();
    }
}
