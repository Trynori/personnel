package com.kharitonov.personnel.web.controllers.resume;

import com.dropbox.core.DbxException;
import com.kharitonov.personnel.dtos.resume.ResumeDto;
import com.kharitonov.personnel.services.resume.ResumeService;
import com.kharitonov.personnel.web.contracts.router.ApiRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(ApiRouter.ResumeController.BASE_URL)
public class ResumeController {

   private final ResumeService resumeService;


   @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
   }

   @GetMapping(ApiRouter.ResumeController.FETCH_BY_ID)
   public ResponseEntity<Iterable<ResumeDto>> fetchAllById(@PathVariable("id") Long id) {
       return ResponseEntity.ok(resumeService.findAllById(id));
   }

   @GetMapping(ApiRouter.ResumeController.DOWNLOAD_BY_ID)
   public ResponseEntity<String> fetchLinkById(@PathVariable("id") Long id) throws DbxException {
       return ResponseEntity.ok(resumeService.getDownloadLinkFile(id));
   }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResumeDto save(@RequestParam("file")MultipartFile multipartFile,
                          @RequestParam("candidate_id") Long id) {
       return resumeService.save(multipartFile, id);
    }


}
