package com.example.application.data.client;

import com.example.application.data.config.BackendConfig;
import com.example.application.data.entity.ImageDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class GalleryClient {

    private final RestTemplate restTemplate;
    private final BackendConfig backendConfig;
    public List<ImageDto> getImages() {
        ImageDto[] response = restTemplate.getForObject(
                backendConfig.getAppEndpoint() + backendConfig.getGalleryEndpoint(),
                ImageDto[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public ImageDto uploadImage(String link) {
        ImageDto imageDto = new ImageDto(link);
       // ImageDto[] response
          ImageDto dto  = restTemplate.postForObject(
                backendConfig.getAppEndpoint() + backendConfig.getGalleryEndpoint(), imageDto,
                ImageDto.class);
//        List <ImageDto> list = Optional.ofNullable(response)
//                .map(Arrays::asList)
//                .orElse(Collections.emptyList());
        return dto;
    }
}
