package com.example.application.data.service;

import com.example.application.data.client.api.DogInfoClient;
import com.example.application.data.entity.api.dog_ninja_info.DogInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogInfoService {

    private final DogInfoClient dogInfoClient;

    public DogInfo findDogInfoByBreed(String breed){
        return dogInfoClient.getDogInfoByBreed(breed);
    }
}
