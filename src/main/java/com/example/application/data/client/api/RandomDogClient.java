package com.example.application.data.client.api;

import com.example.application.data.config.BackendConfig;
import com.example.application.data.entity.api.RandomDog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
@RequiredArgsConstructor
public class RandomDogClient {

    private final RestTemplate restTemplate;
    private final BackendConfig backendConfig;

    public RandomDog getRandomDogByBreed(String  breed) {
        RandomDog[] dogs =restTemplate.getForObject(
                backendConfig.getAppEndpoint() + backendConfig.getDogsEndpoint(),
                RandomDog[].class, breed);

        return dogs[0];
    }
}
