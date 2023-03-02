package com.example.application.data.service;

import com.example.application.data.client.VolunteerClient;
import com.example.application.data.entity.VolunteerDto;
import com.sun.jdi.CharValue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerService {

    @Autowired
    VolunteerClient volunteerClient;
    @Autowired
    PasswordEncoder passwordEncoder;

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
        volunteerDto.setPassword(passwordEncoder.encode(volunteerDto.getPassword()));
        volunteerClient.addNewVolunteer(volunteerDto);
    }

    public VolunteerDto updateUser(VolunteerDto volunteerDto) {

        return volunteerClient.updateVolunteer(volunteerDto);
    }

    public void deleteUser(Long id) {
        volunteerClient.deleteVolunteer(id);
    }
}
