package com.example.application.data.entity.api.open_meteo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Daily {

    @JsonProperty("time")
    public List<String> time;
    @JsonProperty("temperature_2m_max")
    public List<Double> temperature2mMax;
    @JsonProperty("temperature_2m_min")
    public List<Double> temperature2mMin;
    @JsonProperty("precipitation_sum")
    public List<Double> precipitationSum;
    @JsonProperty("precipitation_probability_max")
    public List<Integer> precipitationProbabilityMax;
    @JsonProperty("windspeed_10m_max")
    public List<Double> windspeed10mMax;

}

