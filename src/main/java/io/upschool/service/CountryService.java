package io.upschool.service;

import io.upschool.dto.request.CountryRequest;
import io.upschool.entity.Country;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public Country save(CountryRequest countryRequest) throws DuplicateEntryException {
        ServiceExceptionUtil.check(countryRepository::existsByCode,countryRequest.getCode(),()->new DuplicateEntryException("The code already exists."));
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

        ServiceExceptionUtil.check(countryRepository::existsByCode, countryRequest.getCode(),()->new DuplicateEntryException("The code already exists."));

        Country country = countryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("The country cannot found."));
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());

        return countryRepository.save(country);
    }

    public List<Country> findAll() {
        return countryRepository.findAllByIsDeleted(false);
    }

    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAllByIsDeleted(false,pageable);
    }

    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    public Optional<Country> findByCode(String code) {
        return countryRepository.findByCode(code);
    }

    public Country softDelete(Long id) throws DataNotFoundException, DataCannotDelete {
        Country country = countryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("This country cannot found"));
        ServiceExceptionUtil.check(()->country.getCities().size() > 0, ()->new DataCannotDelete("The country cannot delete cause has cities"));
        country.setIsDeleted(true);
        return countryRepository.save(country);
    }



}
