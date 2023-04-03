package com.kharitonov.personnel.dtos.user;

import com.kharitonov.personnel.data.models.user.UserEntity;
import com.kharitonov.personnel.dtos.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper extends EntityMapper<UserDto, UserEntity> {
}
