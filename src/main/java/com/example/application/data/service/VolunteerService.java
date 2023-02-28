package com.example.application.data.service;

import com.example.application.data.client.VolunteerClient;
import com.example.application.data.entity.VolunteerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerService {

    @Autowired
    VolunteerClient volunteerClient;

    public List<VolunteerDto> fetchVolunteers() {
        return volunteerClient.getVolunteers();
    }

    public VolunteerDto fetchById(Long id) {
        return volunteerClient.getVolunteerById(id);
    }

    public VolunteerDto fetchByUsername(String username) {
        return volunteerClient.getVolunteerByUsername(username);
    }



    public void createVolunteer(VolunteerDto volunteerDto) {
        volunteerClient.addNewVolunteer(volunteerDto);
    }

    public VolunteerDto updateUser(VolunteerDto volunteerDto) {

        return volunteerClient.updateVolunteer(volunteerDto);
    }

    public void deleteUser(Long id) {
        volunteerClient.deleteVolunteer(id);
    }
}
