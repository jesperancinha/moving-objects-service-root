package org.jesperancinha.objects.service;

import org.jesperancinha.objects.domain.CoordinateSource;
import org.jesperancinha.objects.domain.MovingObjectSource;
import org.jesperancinha.objects.domain.WebCamImageSource;
import org.jesperancinha.objects.domain.WebCamSource;
import org.jesperancinha.objects.dto.CoordinatesDto;
import org.jesperancinha.objects.dto.MovingObjectDto;
import org.jesperancinha.objects.dto.WebCamDto;
import org.jesperancinha.objects.dto.WebCamImageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TravelConverter {

    public static WebCamDto toWebCamDto(final WebCamSource webCam) {
        return WebCamDto.builder()
                .active(webCam.active())
                .coordinates(toCoordinatesDto(webCam.coordinates()))
                .title(webCam.title())
                .webCamImage(toWebCamImageDto(webCam.webCamImage()))
                .wikiInfo(webCam.wikiInfo())
                .build();
    }

    private static WebCamImageDto toWebCamImageDto(final WebCamImageSource webCamImage) {
        return WebCamImageDto.builder()
                .iconUrl(webCamImage.iconUrl())
                .previewUrl(webCamImage.previewUrl())
                .thumbnailUrl(webCamImage.thumbnailUrl())
                .toenailUrl(webCamImage.toenailUrl())
                .build();
    }

    public static MovingObjectDto toMovingObjectDto(final MovingObjectSource movingObjectSource) {
        return MovingObjectDto.builder()
                .code(movingObjectSource.code())
                .name(movingObjectSource.name())
                .coordinates(toCoordinatesDto(movingObjectSource.coordinates()))
                .webCams(toWebCamDtoList(movingObjectSource.webCamSources()))
                .build();
    }

    private static List<WebCamDto> toWebCamDtoList(final List<WebCamSource> webCamSources) {
        if (webCamSources == null) {
            return new ArrayList<>();
        }
        return webCamSources.stream().map(TravelConverter::toWebCamDto).collect(Collectors.toList());
    }

    private static CoordinatesDto toCoordinatesDto(final CoordinateSource coordinates) {
        return CoordinatesDto.builder()
                .x(coordinates.x())
                .y(coordinates.y())
                .build();
    }
}
