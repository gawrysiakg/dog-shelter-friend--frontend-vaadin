package com.example.application.security;

import com.example.application.data.entity.VolunteerDto;
import com.example.application.data.service.VolunteerService;
import com.vaadin.flow.spring.security.AuthenticationContext;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {

    private final VolunteerService volunteerService;
    private final AuthenticationContext authenticationContext;

    public AuthenticatedUser(AuthenticationContext authenticationContext, VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
        this.authenticationContext = authenticationContext;
    }

    public Optional<VolunteerDto> get() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                .map(userDetails -> volunteerService.fetchByUsername(userDetails.getUsername()));
    }

    public void logout() {
        authenticationContext.logout();
    }


}
