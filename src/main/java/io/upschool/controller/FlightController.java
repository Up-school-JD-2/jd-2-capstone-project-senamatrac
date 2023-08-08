package io.upschool.controller;

import io.upschool.dto.request.CountryRequest;
import io.upschool.dto.request.FlightRequest;
import io.upschool.dto.response.CountryResponse;
import io.upschool.entity.Country;
import io.upschool.entity.Flight;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public Flight create(@Valid @RequestBody FlightRequest flightRequest){
        return flightService.save(flightRequest);
    }
}
