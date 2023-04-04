package com.kharitonov.personnel.dtos.query;

import com.kharitonov.personnel.data.models.query.QueryEntity;
import com.kharitonov.personnel.dtos.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QueryMapper extends EntityMapper<QueryDto, QueryEntity> {
}
