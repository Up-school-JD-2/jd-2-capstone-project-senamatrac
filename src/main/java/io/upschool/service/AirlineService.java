package io.upschool.service;

import io.upschool.dto.request.create.AirlineCreateRequest;
import io.upschool.dto.request.search.AirlineSearchRequest;
import io.upschool.entity.Airline;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.AirlineMapper;
import io.upschool.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineService {
    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;

    //--------> CREATE <--------\\
    @Transactional
    public Airline save(AirlineCreateRequest airlineCreateRequest) throws DuplicateEntryException {
        ServiceExceptionUtil.check(airlineRepository::existsByIataCode, airlineCreateRequest.getIataCode(), () -> new DuplicateEntryException("iata code"));
        Airline airline = airlineMapper.map(airlineCreateRequest);
        return airlineRepository.save(airline);
    }

    public List<Airline> saveAll(List<AirlineCreateRequest> airlines) {
        return airlines.stream().map(airline -> {
            try {
                return save(airline);
            } catch (DuplicateEntryException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    //--------> READ <--------\\
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    public Page<Airline> findAll(Pageable pageable) {
        return airlineRepository.findAll(pageable);
    }

    public Airline findById(Long id) throws DataNotFoundException {
        return airlineRepository.findById(id).orElseThrow(() -> new DataNotFoundException("id:" + id));
    }

    public Page<Airline> search(AirlineSearchRequest airlineSearchRequest, Pageable pageable) {
        Airline airline = airlineMapper.map(airlineSearchRequest);
        Example<Airline> example = Example.of(airline, ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return airlineRepository.findAll(example, pageable);
    }

    //--------> UPDATE <--------\\
    public Airline update(Long id, AirlineCreateRequest airlineCreateRequest) throws DataNotFoundException, DuplicateEntryException {
        ServiceExceptionUtil.check(airlineRepository::existsByIataCode, airlineCreateRequest.getIataCode(), () -> new DuplicateEntryException("iata code"));

        Airline airline = airlineRepository.findById(id).orElseThrow(() -> new DataNotFoundException("airline id:" + id));
        airline.setName(airlineCreateRequest.getName());
        airline.setIataCode(airline.getIataCode());
        return airline;
    }

}
