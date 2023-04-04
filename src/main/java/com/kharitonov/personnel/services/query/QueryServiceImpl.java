package com.kharitonov.personnel.services.query;

import com.kharitonov.personnel.data.models.query.QueryEntity;
import com.kharitonov.personnel.data.repositories.query.QueryRepository;
import com.kharitonov.personnel.dtos.query.QueryDto;
import com.kharitonov.personnel.dtos.query.QueryMapper;
import com.kharitonov.personnel.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImpl implements QueryService{

    private final QueryRepository queryRepository;
    private final QueryMapper queryMapper;

    @Autowired
    public QueryServiceImpl(QueryRepository queryRepository, QueryMapper queryMapper) {
        this.queryRepository = queryRepository;
        this.queryMapper = queryMapper;
    }

    @Override
    public Iterable<QueryDto> findAll() {
        return queryMapper.toIterableDto(queryRepository.findAll());
    }

    @Override
    public Iterable<QueryDto> findAllByUserEntityId(Long id) {
        Iterable<QueryEntity> allByForUserId = queryRepository.findAllByForUser_Id(id);
        return queryMapper.toIterableDto(allByForUserId);
    }

    @Override
    public QueryDto findById(Long id) {
        return null;
    }

    @Override
    public QueryDto save(QueryDto queryDto) {
        if (queryDto == null) {
            throw new BadRequestException("Query was empty");
        }
        QueryEntity queryEntity = queryMapper.toEntity(queryDto);
        return queryMapper.toDto(queryRepository.save(queryEntity));
    }

    @Override
    public Long deleteById(Long id) {
        queryRepository.deleteById(id);
        return id;
    }
}
