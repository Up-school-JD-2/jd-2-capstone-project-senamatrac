package io.upschool.service;

import io.upschool.mapper.entity.AircraftMapper;
import io.upschool.repository.AircraftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AircraftService {
    private final AircraftRepository aircraftRepository;
    private final AircraftMapper aircraftMapper;
}
