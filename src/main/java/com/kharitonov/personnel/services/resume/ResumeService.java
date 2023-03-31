package com.kharitonov.personnel.services.resume;

import com.kharitonov.personnel.dtos.resume.ResumeDto;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

    ResumeDto save(MultipartFile multipartFile, Long id);
    Iterable<ResumeDto> findAllById(Long id);
}

