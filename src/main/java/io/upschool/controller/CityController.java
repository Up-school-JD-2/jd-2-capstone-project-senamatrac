package io.upschool.controller;

import io.upschool.dto.request.CityRequest;
import io.upschool.dto.response.CityResponse;
import io.upschool.entity.City;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
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

    @GetMapping
    public ResponseEntity<Page<CityResponse>> getAllCities(Pageable pageable){
        return ResponseEntity.ok(cityService.findAll(pageable).map(cityResponseMapper::map));
    };

    @GetMapping("/all")
    public ResponseEntity<List<CityResponse>> getAllCities(){
        return ResponseEntity.ok(cityResponseMapper.map(cityService.findAll()));
    }

    @PostMapping
    public ResponseEntity<CityResponse> create(@Valid @RequestBody CityRequest cityRequest) throws DataNotFoundException, DuplicateEntryException {
        City city = cityService.save(cityRequest);
        return ResponseEntity.ok(cityResponseMapper.map(city));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> update(@PathVariable Long id, @Valid @RequestBody CityRequest cityRequest) throws DataNotFoundException, DuplicateEntryException {
        return ResponseEntity.ok(cityResponseMapper.map(cityService.update(id,cityRequest)));
    }


}
