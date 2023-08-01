package io.upschool.controller;

import io.upschool.dto.request.CountryRequest;
import io.upschool.dto.response.CountryResponse;
import io.upschool.entity.Country;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.CountyResponseMapper;
import io.upschool.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    private final CountyResponseMapper countyResponseMapper;
    @GetMapping
    public ResponseEntity<Page<CountryResponse>> getAllCountries(Pageable pageable){

        return ResponseEntity.ok(countryService.findAll(pageable).map(countyResponseMapper::map));
    }
    @GetMapping("/all")
    public ResponseEntity<List<CountryResponse>> getAllCountries(){
        return ResponseEntity.ok(countyResponseMapper.map(countryService.findAll()));
    }

    @PostMapping
    public ResponseEntity<CountryResponse> create(@Valid @RequestBody CountryRequest countryRequest) throws DuplicateEntryException {
        Country country = countryService.save(countryRequest);
        return ResponseEntity.ok(countyResponseMapper.map(country));
    }
    @PostMapping("/all")
    public ResponseEntity<List<CountryResponse>> create(@Valid @RequestBody List<CountryRequest> countryRequestList) throws DuplicateEntryException {
        List<Country> country = countryService.saveAll(countryRequestList);
        return ResponseEntity.ok(countyResponseMapper.map(country));
    }
    @PutMapping("/{id}")
    public Country update(@PathVariable Long id, @Valid @RequestBody CountryRequest countryRequest) throws DuplicateEntryException, DataNotFoundException {
        return countryService.update(id,countryRequest);
    }


}
