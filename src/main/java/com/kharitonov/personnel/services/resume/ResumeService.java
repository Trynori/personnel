package com.kharitonov.personnel.services.resume;

import com.dropbox.core.DbxException;
import com.kharitonov.personnel.dtos.resume.ResumeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResumeService {

    ResumeDto save(MultipartFile multipartFile, Long id);
    Iterable<ResumeDto> findAllById(Long id);
    String getDownloadLinkFile(Long id) throws DbxException;
}

