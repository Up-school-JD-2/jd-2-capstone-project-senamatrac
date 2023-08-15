package io.upschool.controller;

import io.upschool.dto.request.create.AirportCreateRequest;
import io.upschool.dto.request.search.AirportSearchRequest;
import io.upschool.dto.response.AirportResponse;
import io.upschool.dto.response.BaseResponse;
import io.upschool.entity.Airport;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.AirportResponseMapper;
import io.upschool.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponse<AirportResponse>> create(@Valid @RequestBody AirportCreateRequest airportCreateRequest) throws DataNotFoundException, DuplicateEntryException {
        Airport airport = airportService.save(airportCreateRequest);
        var response = BaseResponse.<AirportResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(airportResponseMapper.map(airport))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/all")
    public ResponseEntity<BaseResponse<List<AirportResponse>>> createAll(@RequestBody List<AirportCreateRequest> airportCreateRequests) throws DataNotFoundException, DuplicateEntryException {
        List<Airport> airports = airportService.saveAll(airportCreateRequests);
        var response = BaseResponse.<List<AirportResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(airportResponseMapper.map(airports))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<BaseResponse<Page<AirportResponse>>> getPageable(Pageable pageable) {
        Page<Airport> airportPage = airportService.findAll(pageable);
        var response = BaseResponse.<Page<AirportResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airportPage.map(airportResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<AirportResponse>>> getAll() {
        List<Airport> airportList = airportService.findAll();
        var response = BaseResponse.<List<AirportResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airportResponseMapper.map(airportList))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<AirportResponse>> getById(@PathVariable Long id) throws DataNotFoundException {
        Airport airport = airportService.findById(id);
        var response = BaseResponse.<AirportResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airportResponseMapper.map(airport))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<AirportResponse>>> search(@RequestBody AirportSearchRequest airportSearchRequest, Pageable pageable) {
        Page<Airport> airportPage = airportService.search(airportSearchRequest, pageable);
        var response = BaseResponse.<Page<AirportResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airportPage.map(airportResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> DELETE <--------\\
}
