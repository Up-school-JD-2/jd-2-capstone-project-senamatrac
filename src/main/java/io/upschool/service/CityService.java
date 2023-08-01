package io.upschool.service;

import io.upschool.dto.request.CityRequest;
import io.upschool.dto.request.CountryRequest;
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
    public Country save(CityRequest cityRequest) throws DuplicateEntryException {
        isExistsCode(cityRequest);
        Optional<Country> country = countryRepository.findById(cityRequest.getCountryId());
        if(country.isEmpty()){
            throw new DataNotFoundException("")
        }
        City country = City.builder()
                .code(cityRequest.getCode())
                .name(cityRequest.getName())
                .country().build();
        return cityRepository.save(country);
    }

    @Transactional
    public List<Country> saveAll(List<CityRequest> countryRequests) {
        List<Country> countries = countryRequests.stream()
                .map(cityRequest -> Country.builder().
                        code(cityRequest.getCode()).
                        name(cityRequest.getName()).build()).toList();

        return cityRepository.saveAll(countries);
    }

    public Country update(Long id, CityRequest cityRequest) throws DataNotFoundException, DuplicateEntryException {
        Country country = cityRepository.findById(id).orElseThrow(() -> new DataNotFoundException("The country cannot found."));
        isExistsCode(cityRequest);
        country.setCode(cityRequest.getCode());
        country.setName(cityRequest.getName());

        return cityRepository.save(country);
    }

    public List<Country> findAll() {
        return cityRepository.findAll();
    }

    public Page<Country> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    public Optional<Country> findById(Long id) {
        return cityRepository.findById(id);
    }

    public Optional<Country> findByCode(String code) {
        return cityRepository.findByCode(code);
    }

    private void isExistsCode(CityRequest cityRequest) throws DuplicateEntryException {
        boolean isExists = cityRepository.existsByCode(cityRequest.getCode());
        if (isExists) {
            throw new DuplicateEntryException("The code '" + cityRequest.getCode() + "' is associated with another country.");
        }
    }
}
