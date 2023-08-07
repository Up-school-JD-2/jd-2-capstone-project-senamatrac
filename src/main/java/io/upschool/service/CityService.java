package io.upschool.service;

import io.upschool.dto.request.CityRequest;
import io.upschool.entity.City;
import io.upschool.entity.Country;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.repository.AirportRepository;
import io.upschool.repository.CityRepository;
import io.upschool.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final AirportRepository airportRepository;

    //--------> CREATE <--------\\
    @Transactional
    public City save(CityRequest cityRequest) throws DuplicateEntryException, DataNotFoundException {
        ServiceExceptionUtil.check(cityRepository::existsByCode,cityRequest.getCode(),()-> new DuplicateEntryException("code"));
        Country country = countryRepository.findById(cityRequest.getCountryId()).orElseThrow(() -> new DataNotFoundException("city's country id: " + cityRequest.getCountryId()));

        City city = City.builder()
                .code(cityRequest.getCode())
                .name(cityRequest.getName())
                .country(country).build();

        return cityRepository.save(city);
    }

    @Transactional
    public List<City> saveAll(List<CityRequest> cityRequests) {
        return cityRequests.stream().map(x -> {
            try {
                return save(x);
            } catch (DuplicateEntryException | DataNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    //--------> READ <--------\\

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    public City findById(Long id) throws DataNotFoundException {
        City city = cityRepository.findById(id).orElseThrow(()->new DataNotFoundException("airport id:" +id));
        return city;
    }

    public Page<City> search(City city, Pageable pageable) {
        Example<City> search = Example.of(city,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return cityRepository.findAll(search, pageable);
    }

    //--------> UPDATE <--------\\
    @Transactional
    public City update(Long id, CityRequest cityRequest) throws DataNotFoundException, DuplicateEntryException {
        City city = cityRepository.findById(id).orElseThrow(() -> new DataNotFoundException("city id: " + id));
        Country country = countryRepository.findById(cityRequest.getCountryId()).orElseThrow(() -> new DataNotFoundException("city's country id:" + cityRequest.getCountryId()));
        ServiceExceptionUtil.check(cityRepository::existsByCode, cityRequest.getCode(), ()-> new DuplicateEntryException("code"));

        city.setCode(cityRequest.getCode());
        city.setName(cityRequest.getName());
        city.setCountry(country);

        return cityRepository.save(city);
    }

    //--------> DELETE <--------\\
    @Transactional
    public void softDelete(Long id) throws DataNotFoundException, DataCannotDelete {
        City city = cityRepository.findById(id).orElseThrow(() -> new DataNotFoundException("city with id:" + id));
        ServiceExceptionUtil.check(airportRepository::existsAirportByCity_Id, city.getId(), () -> new DataCannotDelete(""));

        city.setDeleted(true);
        city.setDeletedDateTime(LocalDateTime.now());
        cityRepository.save(city);
    }
}
