package com.kharitonov.personnel.services.UserDetails;

import com.kharitonov.personnel.data.models.user.UserEntity;
import com.kharitonov.personnel.dtos.user.UserDto;

import java.util.Optional;

public interface UserService {

    UserDto save(UserEntity userEntity);

    UserEntity findByEmail(String email);
}
