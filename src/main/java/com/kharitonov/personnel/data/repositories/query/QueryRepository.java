package com.kharitonov.personnel.data.repositories.query;

import com.kharitonov.personnel.data.models.query.QueryEntity;
import org.springframework.data.repository.CrudRepository;

public interface QueryRepository extends CrudRepository<QueryEntity, Long> {
    Iterable<QueryEntity> findAllByForUser_Id(Long id);
}
