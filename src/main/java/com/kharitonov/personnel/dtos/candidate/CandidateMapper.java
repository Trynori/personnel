package com.kharitonov.personnel.dtos.candidate;

import com.kharitonov.personnel.data.models.candidate.CandidateEntity;
import com.kharitonov.personnel.dtos.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CandidateMapper extends EntityMapper<CandidateDto, CandidateEntity> {
}
