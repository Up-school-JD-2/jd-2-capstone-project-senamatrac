package io.upschool.controller;

import io.upschool.dto.request.create.AirlineCreateRequest;
import io.upschool.dto.request.search.AirlineSearchRequest;
import io.upschool.dto.response.AirlineResponse;
import io.upschool.dto.response.BaseResponse;
import io.upschool.entity.Airline;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.AirlineResponseMapper;
import io.upschool.service.AirlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/airlines"))
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;
    private final AirlineResponseMapper airlineResponseMapper;

    //--------> CREATE <--------\\
    @PostMapping
    public ResponseEntity<BaseResponse<AirlineResponse>> create(@Valid @RequestBody AirlineCreateRequest airlineCreateRequest) throws DuplicateEntryException {
        Airline airline = airlineService.save(airlineCreateRequest);
        var response = BaseResponse.<AirlineResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(airlineResponseMapper.map(airline))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/all")
    public ResponseEntity<BaseResponse<List<AirlineResponse>>> createAll(@RequestBody List<AirlineCreateRequest> airlineCreateRequests) {
        List<Airline> airlines = airlineService.saveAll(airlineCreateRequests);
        var response = BaseResponse.<List<AirlineResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(airlineResponseMapper.map(airlines))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<BaseResponse<Page<AirlineResponse>>> getPageable(Pageable pageable) {
        Page<Airline> airlinePage = airlineService.findAll(pageable);
        var response = BaseResponse.<Page<AirlineResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airlinePage.map(airlineResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<AirlineResponse>>> getAll() {
        List<Airline> airlineList = airlineService.findAll();
        var response = BaseResponse.<List<AirlineResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airlineResponseMapper.map(airlineList))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<AirlineResponse>> getById(@PathVariable Long id) throws DataNotFoundException {
        Airline city = airlineService.findById(id);
        var response = BaseResponse.<AirlineResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airlineResponseMapper.map(city))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<AirlineResponse>>> search(@RequestBody AirlineSearchRequest airlineSearchRequest, Pageable pageable) {
        Page<Airline> airlinePage = airlineService.search(airlineSearchRequest, pageable);
        var response = BaseResponse.<Page<AirlineResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airlinePage.map(airlineResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }
}
