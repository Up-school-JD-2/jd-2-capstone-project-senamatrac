package io.upschool.service;

import io.upschool.dto.request.CityRequest;
import io.upschool.entity.City;
import io.upschool.entity.Country;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.repository.CityRepository;
import io.upschool.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    public City save(CityRequest cityRequest) throws DuplicateEntryException, DataNotFoundException {
        isExistsCode(cityRequest);
        Country country = countryRepository.findById(cityRequest.getCountryId()).orElseThrow(() -> new DataNotFoundException("The country cannot found."));
        City city = City.builder()
                .code(cityRequest.getCode())
                .name(cityRequest.getName())
                .country(country).build();
        return cityRepository.save(city);
    }

    @Transactional
    public List<City> saveAll(List<CityRequest> cityRequests) {
        List<City> cities = cityRequests.stream().map(cityRequest -> City.builder().
                        code(cityRequest.getCode()).
                        name(cityRequest.getName()).build()).toList();

        return cityRepository.saveAll(cities);
    }

    public City update(Long id, CityRequest cityRequest) throws DataNotFoundException, DuplicateEntryException {
        City city = cityRepository.findById(id).orElseThrow(() -> new DataNotFoundException("The city cannot found."));
        Country country = countryRepository.findById(cityRequest.getCountryId()).orElseThrow(() -> new DataNotFoundException("The country cannot found."));
        isExistsCode(cityRequest);
        city.setCode(cityRequest.getCode());
        city.setName(cityRequest.getName());
        city.setCountry(country);

        return cityRepository.save(city);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    public Optional<City> findByCode(String code) {
        return cityRepository.findByCode(code);
    }

    private void isExistsCode(CityRequest cityRequest) throws DuplicateEntryException {
        boolean isExists = cityRepository.existsByCode(cityRequest.getCode());
        if (isExists) {
            throw new DuplicateEntryException("The code '" + cityRequest.getCode() + "' is associated with another country.");
        }
    }
}
