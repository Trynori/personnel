package com.kharitonov.personnel.services.UserDetails;

import com.kharitonov.personnel.data.models.user.UserEntity;
import com.kharitonov.personnel.data.repositories.user.UserRepository;
import com.kharitonov.personnel.dtos.user.UserDto;
import com.kharitonov.personnel.dtos.user.UserMapper;
import com.kharitonov.personnel.exceptions.BadRequestException;
import com.kharitonov.personnel.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto save(UserEntity userEntity) {
        if (userEntity == null) {
            throw new BadRequestException("User was empty");
        }
        UserEntity user = userRepository.save(userEntity);
        return userMapper.toDto(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(new UserEntity());
        if(userEntity.getId() == null) {
            throw new NotFoundException("User was not found by email:" + email);
        }
        return userEntity;
    }
}
