package com.example.application.data.service;

import com.example.application.data.client.api.WeatherClient;
import com.example.application.data.entity.api.open_meteo.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;


    public Weather getWeather(){
        return weatherClient.getWeatherForThisWeek();
    }

}
