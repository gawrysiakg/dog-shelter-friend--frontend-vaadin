package com.example.application.data.entity.api.open_meteo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Weather {

    @JsonProperty("daily_units")
    public DailyUnits dailyUnits;
    @JsonProperty("daily")
    public Daily daily;

}