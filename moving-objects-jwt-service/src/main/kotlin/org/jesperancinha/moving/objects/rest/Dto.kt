package org.jesperancinha.moving.objects.rest

import java.math.BigDecimal

data class CoordinateSourceDto(
    val x: BigDecimal,
    val y: BigDecimal
);

data class WebCamImageSourceDto(
    val iconUrl: String,
    val thumbnailUrl: String,
    val previewUrl: String,
    val toenailUrlString: String
)

data class WebCamSourceDto(
    val title: String? = null,
    val coordinates: CoordinateSourceDto,
    val wikiInfo: String,
    val active: Boolean,
    val webCamImage: WebCamImageSourceDto
)

data class InfoObjectSourceDto(
    val code: String,
    val name: String,
    val color: String,
    val size: Int,
    val coordinates: CoordinateSourceDto? = null,
)

data class LocationObjectSourceDto(
    val code: String,
    val city: String,
    val themeList: List<String> = emptyList(),
    val coordinates: CoordinateSourceDto? = null,
    val pointsOfSale: List<String> = emptyList(),
    val webCamSources: List<WebCamSourceDto> = emptyList()
)

data class MovingObjectsDto(val movingObjects: MutableList<InfoObjectSourceDto>)

data class LocationObjectSourcesDto(val movingObjects: MutableList<LocationObjectSourceDto>)

data class PageLocationDto(
    val pageSize: Int,
    val totalElements: Int,
    val pageNumber: Int,
    var totalPages: Int,
    val movingObjects: LocationObjectSourcesDto
)
data class PageInfoDto(
    val pageSize: Int,
    val totalElements: Int,
    val pageNumber: Int,
    var totalPages: Int,
    val movingObjects: MovingObjectsDto
)
