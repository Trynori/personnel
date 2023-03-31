package com.kharitonov.personnel.data.repositories.candidate;

import com.kharitonov.personnel.data.models.candidate.CandidateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends CrudRepository<CandidateEntity, Long> {
}
