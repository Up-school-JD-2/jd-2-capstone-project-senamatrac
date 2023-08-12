package io.upschool.controller;

import io.upschool.dto.response.BaseResponse;
import io.upschool.dto.request.CityRequest;
import io.upschool.dto.request.search.CitySearchRequest;
import io.upschool.dto.response.CityResponse;
import io.upschool.entity.City;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.CityResponseMapper;
import io.upschool.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public BaseResponse<CityResponse> create(@Valid @RequestBody CityRequest cityRequest) throws DataNotFoundException, DuplicateEntryException {
        City city = cityService.save(cityRequest);
        return BaseResponse.<CityResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(cityResponseMapper.map(city))
                .build();
    }

    @PostMapping("/all")
    public BaseResponse<List<CityResponse>> create(@Valid @RequestBody List<CityRequest> cityRequests) throws DataNotFoundException, DuplicateEntryException {
        List<City> cities = cityService.saveAll(cityRequests);
        return BaseResponse.<List<CityResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(cityResponseMapper.map(cities))
                .build();
    }

    //--------> READ <--------\\
    @GetMapping
    public BaseResponse<Page<CityResponse>> getAllCities(Pageable pageable) {
        Page<City> cities = cityService.findAll(pageable);
        return BaseResponse.<Page<CityResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(cities.map(cityResponseMapper::map))
                .build();
    }

    @GetMapping("/all")
    public BaseResponse<List<CityResponse>> getAllCities() {
        List<City> cities = cityService.findAll();
        return BaseResponse.<List<CityResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(cityResponseMapper.map(cities))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<CityResponse> getById(@PathVariable Long id) throws DataNotFoundException {
        City city = cityService.findById(id);
        return BaseResponse.<CityResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(cityResponseMapper.map(city))
                .build();
    }

    @GetMapping("/search")
    public BaseResponse<Page<CityResponse>> search(Pageable pageable, @Valid @RequestBody CitySearchRequest citySearchRequest) {
        Page<City> cities = cityService.search(citySearchRequest, pageable);
        return BaseResponse.<Page<CityResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(cities.map(cityResponseMapper::map))
                .build();
    }

    //--------> DELETE <--------\\
    @DeleteMapping({"/{id}"})
    public BaseResponse<String> delete(@PathVariable Long id) throws DataNotFoundException, DataCannotDelete {
        return BaseResponse.<String>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody("soon")
                .build();
    }
}
