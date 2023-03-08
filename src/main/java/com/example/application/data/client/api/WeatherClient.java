package com.example.application.data.client.api;

import com.example.application.data.config.BackendConfig;
import com.example.application.data.entity.api.open_meteo.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final RestTemplate restTemplate;
    private final BackendConfig backendConfig;

    public Weather getWeatherForThisWeek(){
        return restTemplate.getForObject(backendConfig.getAppEndpoint()+backendConfig.getWeatherEndpoint(), Weather.class);
    }
}
