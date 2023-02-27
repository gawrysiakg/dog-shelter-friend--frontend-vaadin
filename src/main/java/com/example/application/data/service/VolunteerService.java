package com.example.application.data.service;

import com.example.application.data.entity.VolunteerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VolunteerService {
    public VolunteerDto fetchUsers() {

        return new VolunteerDto("doger", "pass1", "asd@ef.gh", "ADMIN");
    }

    public void createUser(VolunteerDto userDto) {
    }

    public void updateUser(VolunteerDto volunteerDto) {
    }

    public void deleteUser(Long id) {
    }
}
