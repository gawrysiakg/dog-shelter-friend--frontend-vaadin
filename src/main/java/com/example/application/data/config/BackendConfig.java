package com.example.application.data.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BackendConfig {

    @Value("${app.endpoint}")
    private String appEndpoint;

    @Value("${dogs.api.endpoint}")
    private String dogsEndpoint;

    @Value("${volunteers.api.endpoint}")
    private String volunteersEndpoint;

    @Value("${walks.api.endpoint}")
    private String walksEndpoint;

    @Value("${gallery.api.endpoint}")
    private String galleryEndpoint;
    @Value("randomDog.api.endpoint")
    private String randomDogEndpoint;

}
