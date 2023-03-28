package com.kharitonov.personnel.dtos.resume;

import com.kharitonov.personnel.data.models.resume.ResumeEntity;
import com.kharitonov.personnel.dtos.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResumeMapper extends EntityMapper<ResumeDto, ResumeEntity> {
}
