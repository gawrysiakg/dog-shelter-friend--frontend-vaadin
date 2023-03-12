package com.example.application.data.service;

import com.example.application.data.client.DogClient;
import com.example.application.data.entity.DogBreedDto;
import com.example.application.data.entity.DogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DogService {

    private final DogClient dogClient;
    public List<DogDto> getAllDogs() {
        return dogClient.getDogs();
    }

    public List<DogDto> getAllDogsByBreed(DogBreedDto dto) {
        return dogClient.getDogsByBreed(dto);
    }

    public DogDto get(Long id) {
        return dogClient.getDogById(id);
    }
    public DogDto getByName(String value) {
        return dogClient.getDogByName(value);
    }
    public void addDog(DogDto dog) {
        dogClient.addNewDog(dog);
    }

    public void update(DogDto dog) {
        dogClient.updateDog(dog);
    }
    public void deleteDog(Long id) {
        dogClient.deleteDog(id);
    }

}
