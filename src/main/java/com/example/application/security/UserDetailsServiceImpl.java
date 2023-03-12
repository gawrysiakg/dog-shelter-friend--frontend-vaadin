package com.example.application.security;

import com.example.application.data.entity.VolunteerDto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.application.data.service.VolunteerService;
import com.vaadin.flow.server.VaadinSession;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl extends VolunteerDto implements UserDetailsService {

    private final VolunteerService volunteerService;


    public UserDetailsServiceImpl(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        VolunteerDto volunteerDto = volunteerService.fetchByUsername(username);
        if (volunteerDto == null) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {

            return new org.springframework.security.core.userdetails.User(volunteerDto.getName(), volunteerDto.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + volunteerDto.getRole())));
        }
    }

    private static List<GrantedAuthority> getAuthorities(VolunteerDto volunteerDto) {
//        return volunteerDto.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toList());
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_" + volunteerDto.getRole()));
        return list;
    }

}
