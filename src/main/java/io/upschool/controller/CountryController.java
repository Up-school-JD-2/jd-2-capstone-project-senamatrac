package io.upschool.controller;

import io.upschool.dto.request.create.CountryCreateRequest;
import io.upschool.dto.request.search.CountrySearchRequest;
import io.upschool.dto.response.BaseResponse;
import io.upschool.dto.response.CountryResponse;
import io.upschool.entity.Country;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.CountyResponseMapper;
import io.upschool.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    private final CountyResponseMapper countyResponseMapper;

    //--------> CREATE <--------\\
    @PostMapping
    public ResponseEntity<BaseResponse<CountryResponse>> create(@Valid @RequestBody CountryCreateRequest countryCreateRequest) throws DuplicateEntryException {
        Country country = countryService.save(countryCreateRequest);
        var response = BaseResponse.<CountryResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(countyResponseMapper.map(country))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/all")
    public ResponseEntity<BaseResponse<List<CountryResponse>>> create(@Valid @RequestBody List<CountryCreateRequest> countryCreateRequestList) throws DuplicateEntryException {
        List<Country> countries = countryService.saveAll(countryCreateRequestList);
        var response = BaseResponse.<List<CountryResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(countyResponseMapper.map(countries))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<BaseResponse<Page<CountryResponse>>> getAllCountries(Pageable pageable) {
        Page<Country> countryPage = countryService.findAll(pageable);
        var response = BaseResponse.<Page<CountryResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(countryPage.map(countyResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<CountryResponse>>> getAllCountries() {
        List<Country> countryList = countryService.findAll();
        var response = BaseResponse.<List<CountryResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(countyResponseMapper.map(countryList))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<CountryResponse>> getById(@PathVariable Long id) throws DataNotFoundException {
        Country country = countryService.findById(id);
        var response = BaseResponse.<CountryResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(countyResponseMapper.map(country))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<CountryResponse>>> search(CountrySearchRequest countrySearchRequest, Pageable pageable) {

        Page<Country> countries = countryService.search(countrySearchRequest, pageable);
        var response = BaseResponse.<Page<CountryResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(countries.map(countyResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> DELETE <--------\\
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<String>> delete(@PathVariable Long id) throws DataNotFoundException, DataCannotDelete {
        countryService.deleteById(id);
        var response = BaseResponse.<String>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody("soon")
                .build();
        return ResponseEntity.ok(response);
    }
}
