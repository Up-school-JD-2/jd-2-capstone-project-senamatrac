package io.upschool.service;

import io.upschool.dto.request.create.AirportCreateRequest;
import io.upschool.dto.request.search.AirportSearchRequest;
import io.upschool.entity.Airport;
import io.upschool.entity.City;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.AirportMapper;
import io.upschool.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final CityService cityService;
    private final AirportMapper airportMapper;

    //--------> CREATE <--------\\
    @Transactional
    public Airport save(AirportCreateRequest airportCreateRequest) throws DuplicateEntryException, DataNotFoundException {
        ServiceExceptionUtil.check(airportRepository::existsAirportByIataCode, airportCreateRequest.getIataCode(), () -> new DuplicateEntryException("iataCode"));

        City city = cityService.findById(airportCreateRequest.getCityId());

        Airport airport = Airport.builder().
                name(airportCreateRequest.getName()).
                iataCode(airportCreateRequest.getIataCode()).
                city(city).build();

        return airportRepository.save(airport);
    }

    @Transactional
    public List<Airport> saveAll(List<AirportCreateRequest> airportCreateRequests) {
        return airportCreateRequests.stream().map(x -> {
            try {
                return save(x);
            } catch (DuplicateEntryException | DataNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    //--------> READ <--------\\
    public Optional<Airport> findByCityId(Long cityId) {
        return airportRepository.findByCity_Id(cityId);
    }

    public Page<Airport> findAll(Pageable pageable) {
        return airportRepository.findAll(pageable);
    }

    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    public Airport findById(Long id) throws DataNotFoundException {
        return airportRepository.findById(id).orElseThrow(() -> new DataNotFoundException("airport id:" + id));
    }

    public Page<Airport> search(AirportSearchRequest airportSearchRequest, Pageable pageable) {
        Airport airport = airportMapper.map(airportSearchRequest);
        Example<Airport> search = Example.of(airport,
                ExampleMatcher.matching().
                        withIgnoreCase().
                        withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return airportRepository.findAll(search, pageable);
    }
}
