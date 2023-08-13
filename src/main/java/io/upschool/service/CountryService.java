package io.upschool.service;

import io.upschool.dto.request.CountryRequest;
import io.upschool.dto.request.search.CountrySearchRequest;
import io.upschool.entity.Country;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.CountryMapper;
import io.upschool.repository.CountryRepository;
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
public class CountryService {
    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    @Transactional
    public Country save(CountryRequest countryRequest) throws DuplicateEntryException {
        ServiceExceptionUtil.check(countryRepository::existsByCode, countryRequest.getCode(), () -> new DuplicateEntryException("code"));
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

    @Transactional
    public Country update(Long id, CountryRequest countryRequest) throws DataNotFoundException, DuplicateEntryException {

        ServiceExceptionUtil.check(countryRepository::existsByCode, countryRequest.getCode(), () -> new DuplicateEntryException("code"));

        Country country = countryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("country with id: " + id));
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());

        return countryRepository.save(country);
    }

    @Transactional
    public void deleteById(Long id) throws DataNotFoundException {
        Country country = countryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("country with id: " + id));
        countryRepository.delete(country);
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    public Page<Country> search(CountrySearchRequest countrySearchRequest, Pageable pageable) {
        Example<Country> example = Example.of(countryMapper.map(countrySearchRequest), ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return countryRepository.findAll(example, pageable);
    }

    public Country findById(Long id) throws DataNotFoundException {
        Country country = countryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("airport id:" + id));
        return country;
    }
}
