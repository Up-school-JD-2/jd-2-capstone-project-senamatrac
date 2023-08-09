package io.upschool.service;

import io.upschool.dto.request.AirportRequest;
import io.upschool.dto.request.search.AirportSearchRequest;
import io.upschool.entity.Airport;
import io.upschool.entity.City;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.AirportMapper;
import io.upschool.repository.AirportRepository;
import io.upschool.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;
    private final AirportMapper airportMapper;

    public Optional<Airport> findByCityId(Long cityId) {
        Optional<Airport> city = airportRepository.findByCity_Id(cityId);
        return city;
    }

    public Page<Airport> findAll(Pageable pageable) {
        return airportRepository.findAll(pageable);
    }

    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    public Airport findById(Long id) throws DataNotFoundException {
        Airport airport = airportRepository.findById(id).orElseThrow(() -> new DataNotFoundException("airport id:" + id));
        return airport;
    }

    public Page<Airport> search(AirportSearchRequest airportSearchRequest, Pageable pageable) {
        Airport airport = airportMapper.map(airportSearchRequest);
        Example<Airport> search = Example.of(airport,
                ExampleMatcher.matching().
                        withIgnoreCase().
                        withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return airportRepository.findAll(search, pageable);
    }

    public Airport save(AirportRequest airportRequest) throws DuplicateEntryException, DataNotFoundException {
        ServiceExceptionUtil.check(airportRepository::existsAirportByIataCode, airportRequest.getIataCode(), () -> new DuplicateEntryException("iataCode"));

        City city = cityRepository.findById(airportRequest.getCityId()).orElseThrow(() -> new DataNotFoundException("city with id:" + airportRequest.getCityId()));

        Airport airport = Airport.builder().
                name(airportRequest.getName()).
                iataCode(airportRequest.getIataCode()).
                city(city).build();

        return airportRepository.save(airport);
    }

    public List<Airport> saveAll(List<AirportRequest> airportRequests) {
        return airportRequests.stream().map(x -> {
            try {
                return save(x);
            } catch (DuplicateEntryException | DataNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public Airport update(Long id, AirportRequest airportRequest) throws DuplicateEntryException, DataNotFoundException {
        ServiceExceptionUtil.check(airportRepository::existsAirportByIataCode, airportRequest.getIataCode(), () -> new DuplicateEntryException("iataCode"));

        City city = cityRepository.findById(id).orElseThrow(() -> new DataNotFoundException("city with id:" + id));
        Airport airport = airportRepository.findById(id).orElseThrow(() -> new DataNotFoundException("airport id:" + id));
        airport.setName(airportRequest.getName());
        airport.setIataCode(airport.getIataCode());
        airport.setCity(city);

        return airportRepository.save(airport);
    }
}
