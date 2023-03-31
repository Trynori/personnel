package com.kharitonov.personnel.services.resume;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.WriteMode;
import com.kharitonov.personnel.data.models.candidate.CandidateEntity;
import com.kharitonov.personnel.data.models.resume.ResumeEntity;
import com.kharitonov.personnel.data.repositories.candidate.CandidateRepository;
import com.kharitonov.personnel.data.repositories.resume.ResumeRepository;
import com.kharitonov.personnel.dtos.resume.ResumeDto;
import com.kharitonov.personnel.dtos.resume.ResumeMapper;
import com.kharitonov.personnel.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final DbxClientV2 dbxClient;
    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;
    private final CandidateRepository candidateRepository;

    @Autowired
    public ResumeServiceImpl(DbxClientV2 dbxClient, ResumeRepository resumeRepository, ResumeMapper resumeMapper, CandidateRepository candidateRepository) {
        this.dbxClient = dbxClient;
        this.resumeRepository = resumeRepository;
        this.resumeMapper = resumeMapper;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public ResumeDto save(MultipartFile multipartFile, Long id) {
        ResumeEntity resumeEntity = new ResumeEntity();
        if (multipartFile != null) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = "/" + uuidFile + "-" + multipartFile.getOriginalFilename();
            resumeEntity.setName(multipartFile.getOriginalFilename());
            resumeEntity.setPathDropbox(resultFileName);
            resumeEntity.setCandidateEntity(candidateRepository.findById(id).orElseThrow());
            try {
                dbxClient.files().uploadBuilder(resultFileName)
                        .withMode(WriteMode.ADD)
                        .uploadAndFinish(multipartFile.getInputStream());
            } catch (DbxException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return resumeMapper.toDto(resumeRepository.save(resumeEntity));
    }

    @Override
    public Iterable<ResumeDto> findAllById(Long id) {
        List<ResumeEntity> allByCandidateEntityId = resumeRepository.findAllByCandidateEntity_Id(id);
        if (allByCandidateEntityId.isEmpty()) {
            throw new NotFoundException("Resume was not found by id:" + id);
        }
        return resumeMapper.toIterableDto(allByCandidateEntityId);
    }

}
