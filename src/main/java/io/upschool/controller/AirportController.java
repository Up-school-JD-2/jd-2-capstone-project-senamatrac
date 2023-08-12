package io.upschool.controller;

import io.upschool.dto.response.BaseResponse;
import io.upschool.dto.request.AirportRequest;
import io.upschool.dto.request.search.AirportSearchRequest;
import io.upschool.dto.response.AirportResponse;
import io.upschool.entity.Airport;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.AirportResponseMapper;
import io.upschool.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;
    private final AirportResponseMapper airportResponseMapper;

    //--------> CREATE <--------\\
    @PostMapping
    public BaseResponse<AirportResponse> create(@Valid @RequestBody AirportRequest airportRequest) throws DataNotFoundException, DuplicateEntryException {
        Airport airport = airportService.save(airportRequest);
        return BaseResponse.<AirportResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(airportResponseMapper.map(airport))
                .build();
    }

    @PostMapping("/all")
    public BaseResponse<List<AirportResponse>> createAll(@RequestBody List<AirportRequest> airportRequests) throws DataNotFoundException, DuplicateEntryException  {
        List<Airport> airports = airportService.saveAll(airportRequests);
        return BaseResponse.<List<AirportResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(airportResponseMapper.map(airports))
                .build();
    }

    //--------> READ <--------\\
    @GetMapping
    public BaseResponse<Page<AirportResponse>> getPageable(Pageable pageable) {
        Page<Airport> airportPage = airportService.findAll(pageable);
        return BaseResponse.<Page<AirportResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airportPage.map(airportResponseMapper::map))
                .build();
    }

    @GetMapping("/all")
    public BaseResponse<List<AirportResponse>> getAll() {
        List<Airport> airportList = airportService.findAll();
        return BaseResponse.<List<AirportResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airportResponseMapper.map(airportList))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<AirportResponse> getById(@PathVariable Long id) throws DataNotFoundException {
        Airport airport = airportService.findById(id);
        return BaseResponse.<AirportResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airportResponseMapper.map(airport))
                .build();
    }

    @GetMapping("/search")
    public BaseResponse<Page<AirportResponse>> search(@RequestBody AirportSearchRequest airportSearchRequest, Pageable pageable) {
        Page<Airport> airportPage = airportService.search(airportSearchRequest, pageable);
        return BaseResponse.<Page<AirportResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airportPage.map(airportResponseMapper::map))
                .build();
    }

    //--------> DELETE <--------\\
    @DeleteMapping(("/{id}"))
    public BaseResponse<String> delete(@PathVariable Long id)  throws DataNotFoundException, DataCannotDelete {
        return BaseResponse.<String>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody("soon")
                .build();
    }
}
