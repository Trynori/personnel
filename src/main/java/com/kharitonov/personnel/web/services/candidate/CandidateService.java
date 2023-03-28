package com.kharitonov.personnel.web.services.candidate;

import com.kharitonov.personnel.data.models.candidate.CandidateEntity;
import com.kharitonov.personnel.dtos.candidate.CandidateDto;

import java.util.List;

public interface CandidateService {
    List<CandidateDto> findAll();
    CandidateDto findById(Long id);
    CandidateDto save(CandidateEntity candidateEntity);
    void deleteById(Long id);
}
