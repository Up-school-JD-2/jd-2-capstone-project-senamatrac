package io.upschool.controller;

import io.upschool.dto.request.CountryRequest;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    private final CountyResponseMapper countyResponseMapper;

    //--------> CREAD <--------\\
    @PostMapping
    public BaseResponse<CountryResponse> create(@Valid @RequestBody CountryRequest countryRequest) throws DuplicateEntryException {
        Country country = countryService.save(countryRequest);
        return BaseResponse.<CountryResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(countyResponseMapper.map(country))
                .build();
    }

    @PostMapping("/all")
    public BaseResponse<List<CountryResponse>> create(@Valid @RequestBody List<CountryRequest> countryRequestList) throws DuplicateEntryException {
        List<Country> countries = countryService.saveAll(countryRequestList);
        return BaseResponse.<List<CountryResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(countyResponseMapper.map(countries))
                .build();
    }

    //--------> READ <--------\\
    @GetMapping
    public BaseResponse<Page<CountryResponse>> getAllCountries(Pageable pageable) {
        Page<Country> countryPage = countryService.findAll(pageable);
        return BaseResponse.<Page<CountryResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(countryPage.map(countyResponseMapper::map))
                .build();
    }

    @GetMapping("/all")
    public BaseResponse<List<CountryResponse>> getAllCountries() {
        List<Country> countryList = countryService.findAll();
        return BaseResponse.<List<CountryResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(countyResponseMapper.map(countryList))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<CountryResponse> getById(@PathVariable Long id) throws DataNotFoundException {
        Country country = countryService.findById(id);
        return BaseResponse.<CountryResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(countyResponseMapper.map(country))
                .build();
    }

    @GetMapping("/search")
    public BaseResponse<Page<CountryResponse>> search(CountrySearchRequest countrySearchRequest, Pageable pageable) {
        Page<Country> countries = countryService.search(countrySearchRequest, pageable);
        return BaseResponse.<Page<CountryResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(countries.map(countyResponseMapper::map))
                .build();
    }

    //--------> DELETE <--------\\
    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) throws DataNotFoundException, DataCannotDelete {
        countryService.deleteById(id);
        return BaseResponse.<String>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .build();
    }
}
