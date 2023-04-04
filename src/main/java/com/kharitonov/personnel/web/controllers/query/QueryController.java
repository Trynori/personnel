package com.kharitonov.personnel.web.controllers.query;

import com.kharitonov.personnel.dtos.query.QueryDto;
import com.kharitonov.personnel.services.query.QueryService;
import com.kharitonov.personnel.web.contracts.router.ApiRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiRouter.QueryController.BASE_URL)
public class QueryController {

    private final QueryService queryService;

    @Autowired
    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<Iterable<QueryDto>> fetchAll() {
        return ResponseEntity.ok(queryService.findAll());
    }

    @GetMapping(ApiRouter.QueryController.FETCH_ALL_FOR_USER)
    public ResponseEntity<Iterable<QueryDto>> FetchAllById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(queryService.findAllByUserEntityId(id));
    }

    @PostMapping
    public ResponseEntity<QueryDto> save(@RequestBody QueryDto queryDto) {
        return ResponseEntity.ok(queryService.save(queryDto));
    }

    @DeleteMapping(ApiRouter.QueryController.DELETE_BY_ID)
    public ResponseEntity<Long> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(queryService.deleteById(id));
    }
}
