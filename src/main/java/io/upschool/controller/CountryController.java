package io.upschool.controller;

import io.upschool.dto.request.CountryRequest;
import io.upschool.dto.request.search.CountrySearchRequest;
import io.upschool.dto.response.CountryResponse;
import io.upschool.entity.Country;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.entity.CountryMapper;
import io.upschool.mapper.response.CountyResponseMapper;
import io.upschool.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    private final CountyResponseMapper countyResponseMapper;
    private final CountryMapper countryMapper;

    //--------> CREAD <--------\\
    @PostMapping
    public ResponseEntity<CountryResponse> create(@Valid @RequestBody CountryRequest countryRequest) throws DuplicateEntryException {
        Country country = countryService.save(countryRequest);
        return ResponseEntity.ok(countyResponseMapper.map(country));
    }

    @PostMapping("/all")
    public ResponseEntity<List<CountryResponse>> create(@Valid @RequestBody List<CountryRequest> countryRequestList) throws DuplicateEntryException {
        List<Country> countries = countryService.saveAll(countryRequestList);
        return ResponseEntity.ok(countyResponseMapper.map(countries));
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<Page<CountryResponse>> getAllCountries(Pageable pageable) {
        return ResponseEntity.ok(countryService.findAll(pageable).map(countyResponseMapper::map));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CountryResponse>> getAllCountries() {
        return ResponseEntity.ok(countyResponseMapper.map(countryService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getById(@PathVariable Long id) throws DataNotFoundException {

        Country country = countryService.findById(id);
        return ResponseEntity.ok(countyResponseMapper.map(country));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CountryResponse>> search(@Valid @RequestBody CountrySearchRequest countrySearchRequest) {

        List<Country> countries = countryService.search(countryMapper.map(countrySearchRequest));
        return ResponseEntity.ok(countyResponseMapper.map(countries));
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}")
    public ResponseEntity<CountryResponse> update(@PathVariable Long id, @Valid @RequestBody CountryRequest countryRequest) throws DuplicateEntryException, DataNotFoundException {
        Country country = countryService.update(id, countryRequest);
        return ResponseEntity.ok(countyResponseMapper.map(country));
    }

    //--------> DELETE <--------\\
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) throws DataNotFoundException, DataCannotDelete {
        var country = countryService.softDelete(id);
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("isSuccess", true);
        objectBody.put("message", country.getName() + " is deleted");
        return ResponseEntity.ok().body(objectBody);
    }


}
