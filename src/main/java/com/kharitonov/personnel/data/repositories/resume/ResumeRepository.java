package com.kharitonov.personnel.data.repositories.resume;

import com.kharitonov.personnel.data.models.resume.ResumeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends CrudRepository<ResumeEntity, Long> {
    List<ResumeEntity> findAllByCandidateEntity_Id(Long id);
}
