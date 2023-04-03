package com.kharitonov.personnel.dtos.user;

import com.kharitonov.personnel.data.models.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private Role role;
    private String email;
    private String password;
}
