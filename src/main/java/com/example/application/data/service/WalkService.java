package com.example.application.data.service;

import com.example.application.data.client.WalkClient;
import com.example.application.data.entity.WalkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkService {

    private final WalkClient walkClient;
    public void saveWalk(WalkDto walkDto) {
     walkClient.addNewWalk(walkDto);
    }

    public List<WalkDto> fetchPlannedWalks(){
        return walkClient.getPlannedWalks();
    }

    public void deleteWalk(Long id) {
        walkClient.removeWalk(id);
    }

    public List<WalkDto> fetchPlannedWalksForVolunteer(String username) {
        return walkClient.getPlannedWalksForVolunteer(username);
    }

    public void updateWalk(WalkDto walkDto) {
        walkClient.updateWalk(walkDto);
    }
}
