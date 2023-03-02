package com.example.application.data.client;

import com.example.application.data.config.BackendConfig;
import com.example.application.data.entity.DogDto;
import com.example.application.data.entity.VolunteerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VolunteerClient {

    private final RestTemplate restTemplate;
    private final BackendConfig backendConfig;


    public List<VolunteerDto> getVolunteers() {
        VolunteerDto[] response = restTemplate.getForObject(
                backendConfig.getAppEndpoint() + backendConfig.getVolunteersEndpoint(),
                VolunteerDto[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public VolunteerDto getVolunteerById(Long id) {
        return restTemplate.getForObject(
                backendConfig.getAppEndpoint() + backendConfig.getVolunteersEndpoint()+"/"+id,
                VolunteerDto.class);
    }

    public VolunteerDto getVolunteerByUsername(String name) {
        return restTemplate.getForObject(
                backendConfig.getAppEndpoint() + backendConfig.getVolunteersEndpoint()+"/login/"+name,
                VolunteerDto.class);
    }

    public VolunteerDto getVolunteerByEmail(String mail) {
        return restTemplate.getForObject(
                backendConfig.getAppEndpoint() + backendConfig.getVolunteersEndpoint()+"/"+mail,
                VolunteerDto.class);
    }

    public void addNewVolunteer(VolunteerDto volunteerDto) {
        restTemplate.postForObject(backendConfig.getAppEndpoint()+backendConfig
                .getVolunteersEndpoint(), volunteerDto, VolunteerDto.class);
    }

    public VolunteerDto updateVolunteer(VolunteerDto volunteerDto) {
        return restTemplate.patchForObject(backendConfig.getAppEndpoint()+backendConfig
                .getVolunteersEndpoint(), volunteerDto, VolunteerDto.class);
    }

    public void deleteVolunteer(Long id) {
        restTemplate.delete(backendConfig.getAppEndpoint()+backendConfig
                .getVolunteersEndpoint()+"/"+ id, VolunteerDto.class);
    }
}
