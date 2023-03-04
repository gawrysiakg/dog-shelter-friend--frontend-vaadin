package com.example.application.data.client;

import com.example.application.data.config.BackendConfig;
import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.entity.WalkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WalkClient {

    private final RestTemplate restTemplate;
    private final BackendConfig backendConfig;

    public void addNewWalk(WalkDto walkDto) {
        restTemplate.postForObject(backendConfig.getAppEndpoint()+backendConfig
                .getWalksEndpoint(), walkDto, WalkDto.class);
    }

    public List<WalkDto> getPlannedWalks() {
       WalkDto[] list =  restTemplate.getForObject(backendConfig.getAppEndpoint()+backendConfig
                .getWalksEndpoint()+"/planned",  WalkDto[].class);
       return  Optional.ofNullable(list).map(Arrays::asList).orElse(Collections.emptyList());
    }

    public void removeWalk(Long id) {
        restTemplate.delete(backendConfig.getAppEndpoint()+backendConfig.getWalksEndpoint()+"/"+id);
    }

    public List<WalkDto> getPlannedWalksForVolunteer(String username) {
        WalkDto[] list =  restTemplate.getForObject(backendConfig.getAppEndpoint()+backendConfig
                .getWalksEndpoint()+"/planned/"+username,  WalkDto[].class);
        return  Optional.ofNullable(list).map(Arrays::asList).orElse(Collections.emptyList());
    }
}
