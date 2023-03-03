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

//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        VolunteerDto that = (VolunteerDto) o;
//        return phone == that.phone && Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(role, that.role);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, firstName, lastName, name, password, email, phone, role);
//    }


}
