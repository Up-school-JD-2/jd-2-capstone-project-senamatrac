package io.upschool.service;

import io.upschool.dto.request.CountryRequest;
import io.upschool.entity.Country;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
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
public class CountryService {
    private final CountryRepository countryRepository;

    public Country save(CountryRequest countryRequest) throws DuplicateEntryException {
        isExistsCode(countryRequest);
        Country country = Country.builder()
                .code(countryRequest.getCode())
                .name(countryRequest.getName()).build();
        return countryRepository.save(country);
    }

    @Transactional
    public List<Country> saveAll(List<CountryRequest> countryRequests) {
        List<Country> countries = countryRequests.stream()
                .map(countryRequest -> Country.builder().
                        code(countryRequest.getCode()).
                        name(countryRequest.getName()).build()).toList();

        return countryRepository.saveAll(countries);
    }

    public Country update(Long id, CountryRequest countryRequest) throws DataNotFoundException, DuplicateEntryException {
        Country country = countryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("The country cannot found."));
        isExistsCode(countryRequest);
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());

        return countryRepository.save(country);
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    public Optional<Country> findByCode(String code) {
        return countryRepository.findByCode(code);
    }

    private void isExistsCode(CountryRequest countryRequest) throws DuplicateEntryException {
        boolean isExists = countryRepository.existsByCode(countryRequest.getCode());
        if (isExists) {
            throw new DuplicateEntryException("The code '" + countryRequest.getCode() + "' is associated with another country.");
        }
    }

}
