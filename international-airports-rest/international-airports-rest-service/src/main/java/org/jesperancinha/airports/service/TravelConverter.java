package org.jesperancinha.airports.service;

import org.jesperancinha.airports.dto.AirportDto;
import org.jesperancinha.airports.dto.CoordinatesDto;
import org.jesperancinha.airports.dto.WebCamDto;
import org.jesperancinha.airports.dto.WebCamImageDto;
import org.jesperancinha.airports.model.Airport;
import org.jesperancinha.airports.model.Coordinate;
import org.jesperancinha.airports.model.WebCam;
import org.jesperancinha.airports.model.WebCamImage;

import java.util.List;
import java.util.stream.Collectors;

public class TravelConverter {

    public static WebCamDto toWebCamDto(final WebCam webCam) {
        return WebCamDto.builder()
                .active(webCam.isActive())
                .coordinates(toCoordinatesDto(webCam.getCoordinate()))
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

    public static AirportDto toAirportDto(final Airport airport) {
        return AirportDto.builder()
                .code(airport.getCode())
                .name(airport.getName())
                .coordinates(toCoordinatesDto(airport.getCoordinates()))
                .webCams(toWebCamDtoList(airport.getWebCams()))
                .build();
    }

    private static List<WebCamDto> toWebCamDtoList(List<WebCam> webCams) {
        return webCams.stream().map(TravelConverter::toWebCamDto).collect(Collectors.toList());
    }

    private static CoordinatesDto toCoordinatesDto(final Coordinate coordinate) {
        return CoordinatesDto.builder()
                .latitude(coordinate.getLatitude())
                .longitude(coordinate.getLongitude())
                .build();
    }
}
