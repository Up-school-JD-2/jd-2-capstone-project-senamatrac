package io.upschool.service;

import io.upschool.dto.request.create.CityCreateRequest;
import io.upschool.dto.request.search.CitySearchRequest;
import io.upschool.entity.City;
import io.upschool.entity.Country;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.CityMapper;
import io.upschool.repository.CityRepository;
import jakarta.validation.Valid;
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
public class CityService {
    private final CityRepository cityRepository;
    private final CountryService countryService;
    private final CityMapper cityMapper;

    //--------> CREATE <--------\\
    @Transactional
    public City save(CityCreateRequest cityCreateRequest) throws DuplicateEntryException, DataNotFoundException {
        ServiceExceptionUtil.check(cityRepository::existsByCode, cityCreateRequest.getCode(), () -> new DuplicateEntryException("code"));
        Country country = countryService.findById(cityCreateRequest.getCountryId());
        City city = City.builder()
                .code(cityCreateRequest.getCode())
                .name(cityCreateRequest.getName())
                .country(country).build();

        return cityRepository.save(city);
    }

    @Transactional
    public List<City> saveAll(List<CityCreateRequest> cityCreateRequests) {
        return cityCreateRequests.stream().map(x -> {
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
        return cityRepository.findById(id).orElseThrow(() -> new DataNotFoundException("airport id:" + id));
    }

    public Page<City> search(@Valid CitySearchRequest citySearchRequest, Pageable pageable) {
        City city = cityMapper.map(citySearchRequest);
        Example<City> search = Example.of(city,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return cityRepository.findAll(search, pageable);
    }
}
