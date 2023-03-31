package com.kharitonov.personnel.services.candidate;

import com.kharitonov.personnel.dtos.candidate.CandidateDto;

public interface CandidateService {
    Iterable<CandidateDto> findAll();
    CandidateDto findById(Long id);
    CandidateDto save(CandidateDto candidateDto);
    Long deleteById(Long id);
}
