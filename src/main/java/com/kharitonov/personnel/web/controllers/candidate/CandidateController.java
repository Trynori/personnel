package com.kharitonov.personnel.web.controllers.candidate;

import com.kharitonov.personnel.dtos.candidate.CandidateDto;
import com.kharitonov.personnel.services.candidate.CandidateService;
import com.kharitonov.personnel.web.contracts.router.ApiRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiRouter.CandidateRouter.BASE_URL)
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<Iterable<CandidateDto>> fetchAll() {
        return ResponseEntity.ok(candidateService.findAll());
    }

    @GetMapping(ApiRouter.CandidateRouter.FETCH_BY_ID)
    public ResponseEntity<CandidateDto> fetchById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(candidateService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CandidateDto> save(@RequestBody CandidateDto candidateDto) {
        return ResponseEntity.ok(candidateService.save(candidateDto));
    }

    @DeleteMapping(ApiRouter.CandidateRouter.DELETE_BY_ID)
    public ResponseEntity<Long> deleteById(@PathVariable("id") Long id) {
        candidateService.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
