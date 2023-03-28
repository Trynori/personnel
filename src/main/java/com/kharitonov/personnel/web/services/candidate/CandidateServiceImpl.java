package com.kharitonov.personnel.web.services.candidate;

import com.kharitonov.personnel.data.models.candidate.CandidateEntity;
import com.kharitonov.personnel.data.repositories.candidate.CandidateRepository;
import com.kharitonov.personnel.dtos.candidate.CandidateDto;
import com.kharitonov.personnel.dtos.candidate.CandidateMapper;
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
    public List<CandidateDto> findAll() {
        return candidateMapper.toDto(candidateRepository.findAll());
    }

    @Override
    public CandidateDto findById(Long id) {
        if (id == null) {
            return null;
        }

        return candidateMapper.toDto(candidateRepository.findById(id).get());
    }

    @Override
    public CandidateDto save(CandidateEntity candidateEntity) {
        if (candidateEntity == null) {
            return null;
        }
        candidateRepository.save(candidateEntity);
        return candidateMapper.toDto(candidateEntity);
    }

    @Override
    public void deleteById(Long id) {
        candidateRepository.deleteById(id);
    }
}
