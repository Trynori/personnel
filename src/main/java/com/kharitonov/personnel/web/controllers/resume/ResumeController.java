package com.kharitonov.personnel.web.controllers.resume;

import com.kharitonov.personnel.data.models.resume.ResumeEntity;
import com.kharitonov.personnel.dtos.resume.ResumeDto;
import com.kharitonov.personnel.web.contracts.ApiRouter;
import com.kharitonov.personnel.web.services.resume.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(ApiRouter.ResumeController.BASE_URL)
public class ResumeController {

   private final ResumeService resumeService;

   @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResumeDto save(@RequestParam("file")MultipartFile multipartFile) {
       return resumeService.save(multipartFile);
    }

}
