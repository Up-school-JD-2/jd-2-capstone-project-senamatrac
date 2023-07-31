package io.upschool.controller;

import io.upschool.dto.request.CountryRequest;
import io.upschool.entity.Country;
import io.upschool.exception.DuplicateEntryException;
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

    @GetMapping
    public ResponseEntity<Page<Country>> getAllCountries(Pageable pageable){
        return ResponseEntity.ok(countryService.findAll(pageable));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Country>> getAllCountries(){
        return ResponseEntity.ok(countryService.findAll());
    }

    @PostMapping
    public Country create(@Valid @RequestBody CountryRequest countryRequest) throws DuplicateEntryException {
        Country countrySaved = countryService.save(countryRequest);
        return countrySaved;
    }
}
