package io.upschool.controller;

import io.upschool.dto.request.create.FlightCreateRequest;
import io.upschool.dto.request.search.FlightSearchRequest;
import io.upschool.dto.response.BaseResponse;
import io.upschool.dto.response.FlightResponse;
import io.upschool.entity.Flight;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.FlightResponseMapper;
import io.upschool.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    private final FlightResponseMapper flightResponseMapper;

    //--------> CREATE <--------\\
    @PostMapping
    public ResponseEntity<BaseResponse<FlightResponse>> create(@Valid @RequestBody FlightCreateRequest flightCreateRequest) throws DataNotFoundException, DuplicateEntryException {
        Flight flight = flightService.save(flightCreateRequest);
        var response = BaseResponse.<FlightResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(flightResponseMapper.map(flight))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/all")
    public ResponseEntity<BaseResponse<List<FlightResponse>>> create(@Valid @RequestBody List<FlightCreateRequest> flightCreateRequests) throws DataNotFoundException, DuplicateEntryException {
        List<Flight> flights = flightService.saveAll(flightCreateRequests);
        var response = BaseResponse.<List<FlightResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(flightResponseMapper.map(flights))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<BaseResponse<Page<FlightResponse>>> getAllFlights(Pageable pageable) {
        Page<Flight> flights = flightService.findAll(pageable);
        var response = BaseResponse.<Page<FlightResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(flights.map(flightResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<FlightResponse>>> getAllFlights() {
        List<Flight> flights = flightService.findAll();
        var response = BaseResponse.<List<FlightResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(flightResponseMapper.map(flights))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<FlightResponse>> getById(@PathVariable Long id) throws DataNotFoundException {
        Flight flight = flightService.findById(id);
        var response = BaseResponse.<FlightResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(flightResponseMapper.map(flight))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<FlightResponse>>> search(Pageable pageable, @Valid @RequestBody FlightSearchRequest flightSearchRequest) {
        Page<Flight> flights = flightService.search(flightSearchRequest, pageable);
        var response = BaseResponse.<Page<FlightResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(flights.map(flightResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}/cancel")
    public ResponseEntity<BaseResponse<FlightResponse>> cancel(@PathVariable Long id) throws DataNotFoundException {
        Flight flight = flightService.cancel(id);
        var response = BaseResponse.<FlightResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(flightResponseMapper.map(flight))
                .build();
        return ResponseEntity.ok(response);
    }
}
