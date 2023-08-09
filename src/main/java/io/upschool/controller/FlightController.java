package io.upschool.controller;

import io.upschool.dto.request.FlightRequest;
import io.upschool.dto.response.FlightResponse;
import io.upschool.exception.DataNotFoundException;
import io.upschool.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PostMapping
    public FlightResponse create(@Valid @RequestBody FlightRequest flightRequest) throws DataNotFoundException {
        return flightService.save(flightRequest);
    }
}
