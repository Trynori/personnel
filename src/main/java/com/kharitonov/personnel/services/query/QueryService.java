package com.kharitonov.personnel.services.query;

import com.kharitonov.personnel.data.models.query.QueryEntity;
import com.kharitonov.personnel.dtos.query.QueryDto;

public interface QueryService {

    Iterable<QueryDto> findAll();
    Iterable<QueryDto> findAllByUserEntityId(Long id);
    QueryDto save(QueryDto QueryDto);
    Long deleteById(Long id);
}
