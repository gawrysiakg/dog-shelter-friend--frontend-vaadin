package com.example.application.data.client.api;

import com.example.application.data.config.BackendConfig;
import com.example.application.data.entity.api.dog_ninja_info.DogInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class DogInfoClient {

    private final RestTemplate restTemplate;
    private final BackendConfig backendConfig;

    public DogInfo getDogInfoByBreed(String breed){
        return restTemplate.getForObject(backendConfig.getAppEndpoint()+backendConfig.getDogNinjaApiEndpoint()+"/"+breed, DogInfo.class);
    }


}
