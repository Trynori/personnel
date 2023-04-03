package com.kharitonov.personnel.services.candidate;

import com.kharitonov.personnel.data.models.candidate.CandidateEntity;
import com.kharitonov.personnel.data.repositories.candidate.CandidateRepository;
import com.kharitonov.personnel.dtos.candidate.CandidateDto;
import com.kharitonov.personnel.dtos.candidate.CandidateMapper;
import com.kharitonov.personnel.exceptions.BadRequestException;
import com.kharitonov.personnel.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(value = Transactional.TxType.REQUIRED)
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
        this.candidateRepository = candidateRepository;
        this.candidateMapper = candidateMapper;
    }

    @Override
    public Iterable<CandidateDto> findAll() {
        return candidateMapper.toIterableDto(candidateRepository.findAll());
    }

    @Override
    public CandidateDto findById(Long id) {
        CandidateEntity candidateEntity = candidateRepository.findById(id).orElse(new CandidateEntity());
        if(candidateEntity.getId() == null) {
            throw new NotFoundException("Candidate was not found by id:" + id);
        }
        return candidateMapper.toDto(candidateEntity);
    }

    @Override
    public CandidateDto save(CandidateDto candidateDto) {
        if (candidateDto == null) {
            throw new BadRequestException("Candidate was empty");
        }
        CandidateEntity candidateEntity = candidateMapper.toEntity(candidateDto);
        return candidateMapper.toDto(candidateRepository.save(candidateEntity));
    }

    @Override
    public Long deleteById(Long id) {
        candidateRepository.deleteById(id);
        return id;
    }
}
