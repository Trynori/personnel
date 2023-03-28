package com.kharitonov.personnel.web.services.resume;

import com.kharitonov.personnel.data.models.resume.ResumeEntity;
import com.kharitonov.personnel.dtos.resume.ResumeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

    ResumeDto save(MultipartFile multipartFile);
}
