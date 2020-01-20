package org.jesperancinha.airports.sst.live.services;

import org.jesperancinha.airports.sst.data.WebCamDto;
import org.jesperancinha.airports.sst.live.repo.WebCamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class WebCamServiceImpl implements WebCamService {

    private final WebCamRepository webCamRepository;

    public WebCamServiceImpl(WebCamRepository webCamRepository) {
        this.webCamRepository = webCamRepository;
    }

    @Override
    public Flux<WebCamDto> getCamsByPageSizeAndPageOffse(int pageSize, int pageOffset) {
        return webCamRepository.findCamsByPageSizeAndPageOffset(pageSize, pageOffset)
                .map(webCamResponse -> webCamResponse.getResult().getWebcams())
                .flatMapMany(Flux::fromIterable)
                .map(WebCamConverter::toWebCamDto);

    }
}
