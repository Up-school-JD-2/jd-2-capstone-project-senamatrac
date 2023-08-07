package io.upschool.service;

import io.upschool.dto.request.CountryRequest;
import io.upschool.entity.Country;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

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

        Country country = countryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("country with id: "+id));
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());

        return countryRepository.save(country);
    }

    @Transactional
    public Country softDelete(Long id) throws DataNotFoundException, DataCannotDelete {
        Country country = countryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("country with id:"+id));
        ServiceExceptionUtil.check(() -> !country.getCities().isEmpty(), () -> new DataCannotDelete("The country cannot delete cause has cities"));
        country.setDeleted(true);
        country.setDeletedDateTime(LocalDateTime.now());
        return countryRepository.save(country);
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAll( pageable);
    }

    public List<Country> search(Country country) {
        Example<Country> example = Example.of(country, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return countryRepository.findAll(example);
    }

    public Country findById(Long id) throws DataNotFoundException {
        Country country = countryRepository.findById(id).orElseThrow(()->new DataNotFoundException("airport id:" +id));
        return country;
    }
}
