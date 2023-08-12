package io.upschool.controller;

import io.upschool.dto.request.AirlineRequest;
import io.upschool.dto.request.search.AirlineSearchRequest;
import io.upschool.dto.response.AirlineResponse;
import io.upschool.dto.response.BaseResponse;
import io.upschool.entity.Airline;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.AirlineResponseMapper;
import io.upschool.service.AirlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public BaseResponse<AirlineResponse> create(@Valid @RequestBody AirlineRequest airlineRequest) throws DuplicateEntryException {
        Airline airline = airlineService.save(airlineRequest);
        return BaseResponse.<AirlineResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(airlineResponseMapper.map(airline))
                .build();
    }

    @PostMapping("/all")
    public BaseResponse<List<AirlineResponse>> createAll(@RequestBody List<AirlineRequest> airlineRequests) {
        List<Airline> airlines = airlineService.saveAll(airlineRequests);
        return BaseResponse.<List<AirlineResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(airlineResponseMapper.map(airlines))
                .build();
    }

    //--------> READ <--------\\
    @GetMapping
    public BaseResponse<Page<AirlineResponse>> getPageable(Pageable pageable) {
        Page<Airline> airlinePage = airlineService.findAll(pageable);
        return BaseResponse.<Page<AirlineResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airlinePage.map(airlineResponseMapper::map))
                .build();
    }

    @GetMapping("/all")
    public BaseResponse<List<AirlineResponse>> getAll() {
        List<Airline> airlineList = airlineService.findAll();
        return BaseResponse.<List<AirlineResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airlineResponseMapper.map(airlineList))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<AirlineResponse> getById(@PathVariable Long id) throws DataNotFoundException {
        Airline city = airlineService.findById(id);
        return BaseResponse.<AirlineResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airlineResponseMapper.map(city))
                .build();
    }

    @GetMapping("/search")
    public BaseResponse<Page<AirlineResponse>> search(@RequestBody AirlineSearchRequest airlineSearchRequest, Pageable pageable) {
        Page<Airline> airlinePage = airlineService.search(airlineSearchRequest, pageable);
        return BaseResponse.<Page<AirlineResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(airlinePage.map(airlineResponseMapper::map))
                .build();
    }

    //--------> DELETE <--------\\
    @DeleteMapping(("/{id}"))
    public BaseResponse<String> delete(@PathVariable Long id) throws DataNotFoundException, DataCannotDelete {
        return BaseResponse.<String>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody("soon")
                .build();
    }
}
