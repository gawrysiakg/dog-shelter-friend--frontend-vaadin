package com.example.application.data.client;

import com.example.application.data.config.BackendConfig;
import com.example.application.data.entity.DogBreedDto;
import com.example.application.data.entity.DogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DogClient {

    private final RestTemplate restTemplate;
    private final BackendConfig backendConfig;


    public List<DogDto> getDogs(Pageable pageable) {
        DogDto[] response = restTemplate.getForObject(
                backendConfig.getAppEndpoint() + backendConfig.getDogsEndpoint(),
                DogDto[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<DogDto> getDogs() {
        DogDto[] response = restTemplate.getForObject(
                backendConfig.getAppEndpoint() + backendConfig.getDogsEndpoint(),
                DogDto[].class);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<DogDto> getDogsByBreed(DogBreedDto dto) {
        DogDto[] response = restTemplate.getForObject(
                backendConfig.getAppEndpoint() + backendConfig.getDogsEndpoint(),
                DogDto[].class, dto);
        return Optional.ofNullable(response)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }



    public void addNewDog(DogDto dog) {
        restTemplate.postForObject(backendConfig.getAppEndpoint()+backendConfig.getDogsEndpoint(), dog, DogDto.class);
    }
    public void updateDog(DogDto dog) {
        restTemplate.patchForObject(backendConfig.getAppEndpoint()+backendConfig.getDogsEndpoint(), dog, DogDto.class);
    }

    public void deleteDog(Long id) {
        restTemplate.delete(backendConfig.getAppEndpoint()+backendConfig.getDogsEndpoint()+"/"+ id, DogDto.class);
    }

    public DogDto getDogById(Long id) {
       return restTemplate.getForObject(
                backendConfig.getAppEndpoint() + backendConfig.getDogsEndpoint()+"/"+id,
                DogDto.class);
    }

    public DogDto getDogByName(String name) {
        return restTemplate.getForObject(
                backendConfig.getAppEndpoint() + backendConfig.getDogsEndpoint()+"/name/"+name,
                DogDto.class);
    }
}
