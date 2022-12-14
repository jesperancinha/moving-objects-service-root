package org.jesperancinha.objects.rest;

import org.jesperancinha.objects.dto.WebCamDto;
import org.jesperancinha.objects.service.WebCamService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("webcams")
public class MovingObjectsController {

    private final WebCamService webCamService;

    public MovingObjectsController(WebCamService webCamService) {
        this.webCamService = webCamService;
    }

    @GetMapping("/page/{pageSize}/{pageOffset}")
    public Flux<WebCamDto> getCamsByPageSizeAndPageOffset(
            @PathVariable
            final int pageSize,
            @PathVariable
            final int pageOffset
    ) {
        return webCamService.getCamsByPageSizeAndPageOffset(pageSize, pageOffset);
    }

    @GetMapping("/location/{x}/{y}/{radius}")
    public Flux<WebCamDto> getCamsByLocationAndRadius(
            @PathVariable
            final BigDecimal x,
            @PathVariable
            final BigDecimal y,
            @PathVariable
            final long radius
    ) {
        return webCamService.getCamsByLocationAndRadius(x, y, radius);
    }

    @GetMapping(value = "/camera/{code}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public Mono<byte[]> getImageProtected(
            @PathVariable("code")
            String code
    ) {
        return webCamService.getImageFromCode(code);
    }
}
