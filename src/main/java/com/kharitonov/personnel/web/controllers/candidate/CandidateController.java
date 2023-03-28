package com.kharitonov.personnel.web.controllers.candidate;

import com.kharitonov.personnel.data.models.candidate.CandidateEntity;
import com.kharitonov.personnel.dtos.candidate.CandidateDto;
import com.kharitonov.personnel.web.contracts.ApiRouter;
import com.kharitonov.personnel.web.services.candidate.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRouter.CandidateRouter.BASE_URL)
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<CandidateDto> fetchAll() {
        return candidateService.findAll();
    }

    @GetMapping(ApiRouter.CandidateRouter.FETCH_BY_ID)
    public CandidateDto fetchById(@PathVariable("id") Long id) {
        return candidateService.findById(id);
    }

    @PostMapping
    public CandidateDto save(@RequestBody CandidateEntity candidateEntity) {
        return candidateService.save(candidateEntity);
    }

    @DeleteMapping(ApiRouter.CandidateRouter.DELETE_BY_ID)
    public void deleteById(@PathVariable("id") Long id) {
        candidateService.deleteById(id);
    }
}
