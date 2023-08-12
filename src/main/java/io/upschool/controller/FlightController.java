package io.upschool.controller;

import io.upschool.dto.request.FlightRequest;
import io.upschool.dto.request.search.FlightSearchRequest;
import io.upschool.dto.response.BaseResponse;
import io.upschool.dto.response.FlightResponse;
import io.upschool.entity.Flight;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.FlightResponseMapper;
import io.upschool.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public BaseResponse<FlightResponse> create(@Valid @RequestBody FlightRequest flightRequest) throws DataNotFoundException, DuplicateEntryException {
        Flight flight = flightService.save(flightRequest);
        return BaseResponse.<FlightResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(flightResponseMapper.map(flight))
                .build();
    }

    @PostMapping("/all")
    public BaseResponse<List<FlightResponse>> create(@Valid @RequestBody List<FlightRequest> flightRequests) throws DataNotFoundException, DuplicateEntryException {
        List<Flight> flights = flightService.saveAll(flightRequests);
        return BaseResponse.<List<FlightResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(flightResponseMapper.map(flights))
                .build();
    }
    //--------> READ <--------\\
    @GetMapping
    public BaseResponse<Page<FlightResponse>> getAllFlights(Pageable pageable) {
        Page<Flight> flights = flightService.findAll(pageable);
        return BaseResponse.<Page<FlightResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(flights.map(flightResponseMapper::map))
                .build();
    }

    @GetMapping("/all")
    public BaseResponse<List<FlightResponse>> getAllFlights() {
        List<Flight> flights = flightService.findAll();
        return BaseResponse.<List<FlightResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(flightResponseMapper.map(flights))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<FlightResponse> getById(@PathVariable Long id) throws DataNotFoundException {
        Flight flight = flightService.findById(id);
        return BaseResponse.<FlightResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(flightResponseMapper.map(flight))
                .build();
    }

    @GetMapping("/search")
    public BaseResponse<Page<FlightResponse>> search(Pageable pageable, @Valid @RequestBody FlightSearchRequest flightSearchRequest) {
        Page<Flight> flights = flightService.search(flightSearchRequest, pageable);
        return BaseResponse.<Page<FlightResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(flights.map(flightResponseMapper::map))
                .build();
    }
    //--------> UPDATE <--------\\
    @PutMapping("/{id}/cancel")
    public BaseResponse<FlightResponse> cancel(@PathVariable Long id) throws DataNotFoundException, DuplicateEntryException {
        Flight flight = flightService.cancel(id);
        return BaseResponse.<FlightResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(flightResponseMapper.map(flight))
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
