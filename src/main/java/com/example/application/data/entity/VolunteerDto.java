package com.example.application.data.entity;

import com.example.application.data.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerDto  {

    private Long id;
    private String firstName;
    private String lastName;
    private String name;
    private String password;
    private String email;
    private int phone;
    private Role role;
    public Role getRole (){
        return role;
    }

    public void setRole(Role role){
        this.role=role;
    }

    public VolunteerDto(String firstName, String lastName, String name, String password, String email, int phone, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role=role;
    }
}
