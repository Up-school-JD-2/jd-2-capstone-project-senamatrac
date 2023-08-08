package io.upschool.service;

import io.upschool.dto.request.FlightRequest;
import io.upschool.dto.response.CountryResponse;
import io.upschool.entity.Flight;
import io.upschool.mapper.entity.FlightMapper;
import io.upschool.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {
    public final FlightRepository flightRepository;
    public final FlightMapper flightMapper;
    public Flight save(FlightRequest flightRequest) {
        Flight flight =  flightMapper.map(flightRequest);
        return flightRepository.save(flight);
    }
}
