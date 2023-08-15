package io.upschool.controller;

import io.upschool.dto.request.create.CityCreateRequest;
import io.upschool.dto.request.search.CitySearchRequest;
import io.upschool.dto.response.BaseResponse;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final CityResponseMapper cityResponseMapper;

    //--------> CREATE <--------\\
    @PostMapping
    public ResponseEntity<BaseResponse<CityResponse>> create(@Valid @RequestBody CityCreateRequest cityCreateRequest) throws DataNotFoundException, DuplicateEntryException {
        City city = cityService.save(cityCreateRequest);
        var response = BaseResponse.<CityResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(cityResponseMapper.map(city))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/all")
    public ResponseEntity<BaseResponse<List<CityResponse>>> create(@Valid @RequestBody List<CityCreateRequest> cityCreateRequests) throws DataNotFoundException, DuplicateEntryException {
        List<City> cities = cityService.saveAll(cityCreateRequests);
        var response = BaseResponse.<List<CityResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(cityResponseMapper.map(cities))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<BaseResponse<Page<CityResponse>>> getAllCities(Pageable pageable) {
        Page<City> cities = cityService.findAll(pageable);
        var response = BaseResponse.<Page<CityResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(cities.map(cityResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<CityResponse>>> getAllCities() {
        List<City> cities = cityService.findAll();
        var response = BaseResponse.<List<CityResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(cityResponseMapper.map(cities))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CityResponse>> getById(@PathVariable Long id) throws DataNotFoundException {
        City city = cityService.findById(id);
        var response = BaseResponse.<CityResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(cityResponseMapper.map(city))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<CityResponse>>> search(Pageable pageable, @Valid @RequestBody CitySearchRequest citySearchRequest) {
        Page<City> cities = cityService.search(citySearchRequest, pageable);
        var response = BaseResponse.<Page<CityResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(cities.map(cityResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }
}
