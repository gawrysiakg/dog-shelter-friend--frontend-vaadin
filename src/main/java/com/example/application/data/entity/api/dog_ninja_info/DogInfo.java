package com.example.application.data.entity.api.dog_ninja_info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DogInfo {

    @JsonProperty("image_link")
    public String imageLink;
    @JsonProperty("good_with_children")
    public int goodWithChildren;
    @JsonProperty("good_with_other_dogs")
    public int goodWithOtherDogs;
    @JsonProperty("playfulness")
    public int playfulness;
    @JsonProperty("protectiveness")
    public int protectiveness;
    @JsonProperty("trainability")
    public int trainability;
    @JsonProperty("barking")
    public int barking;
    @JsonProperty("max_life_expectancy")
    public double maxLifeExpectancy;
    @JsonProperty("name")
    public String name;

}
