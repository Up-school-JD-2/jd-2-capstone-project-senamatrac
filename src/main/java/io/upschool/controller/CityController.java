package io.upschool.controller;

import io.upschool.dto.request.CityRequest;
import io.upschool.dto.request.search.CitySearchRequest;
import io.upschool.dto.response.CityResponse;
import io.upschool.entity.City;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.entity.CityMapper;
import io.upschool.mapper.response.CityResponseMapper;
import io.upschool.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final CityResponseMapper cityResponseMapper;
    private final CityMapper cityMapper;

    //--------> CREAD <--------\\
    @PostMapping
    public ResponseEntity<CityResponse> create(@Valid @RequestBody CityRequest cityRequest) throws DataNotFoundException, DuplicateEntryException {
        City city = cityService.save(cityRequest);
        return ResponseEntity.ok(cityResponseMapper.map(city));
    }

    @PostMapping("/all")
    public ResponseEntity<List<CityResponse>> create(@Valid @RequestBody List<CityRequest> cityRequests) throws DataNotFoundException, DuplicateEntryException {
        List<City> cities = cityService.saveAll(cityRequests);
        return ResponseEntity.ok(cityResponseMapper.map(cities));
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<Page<CityResponse>> getAllCities(Pageable pageable) {
        return ResponseEntity.ok(cityService.findAll(pageable).map(cityResponseMapper::map));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CityResponse>> getAllCities() {
        return ResponseEntity.ok(cityResponseMapper.map(cityService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getById(@PathVariable Long id) throws DataNotFoundException {
        City city = cityService.findById(id);
        return ResponseEntity.ok(cityResponseMapper.map(city));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CityResponse>> search(Pageable pageable, @Valid @RequestBody CitySearchRequest citySearchRequest) {
        Page<City> cities = cityService.search(cityMapper.map(citySearchRequest), pageable);
        return ResponseEntity.ok(cities.map(cityResponseMapper::map));
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> update(@PathVariable Long id, @Valid @RequestBody CityRequest cityRequest) throws DataNotFoundException, DuplicateEntryException {
        City city = cityService.update(id, cityRequest);
        return ResponseEntity.ok(cityResponseMapper.map(city));
    }

    //--------> DELETE <--------\\
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Object> delete(@PathVariable Long id) throws DataNotFoundException, DataCannotDelete {
        return null;
    }

}
